import datetime
import socket
import threading
import uuid

import cv2
import argparse
import numpy as np
import os

args = argparse.Namespace()
args.image = 'test.jpg'
args.config = 'yolov3.cfg'
args.weights = 'yolov3.weights'
args.classes = 'yolov3.txt'
args.output_dir = 'output'

if not os.path.exists(args.output_dir):
    os.makedirs(args.output_dir)


def server_socket():
    HOST = "127.0.0.1"
    PORT = 65432
    IMAGES_FOLDER = 'images'

    server_dir = os.path.abspath(os.path.dirname(__file__))
    images_dir = os.path.join(server_dir, IMAGES_FOLDER)

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind((HOST, PORT))
        s.listen()
        while True:
            conn, addr = s.accept()
            with conn:
                print(f'Connected by {addr}')
                file_paths_bytes = b''
                while True:
                    data = conn.recv(1024)
                    if not data:
                        break
                    file_paths_bytes += data

                file_paths = file_paths_bytes.decode().split('\n')
                for file_path in file_paths:
                    if not file_path:
                        continue
                    file_path = os.path.join(images_dir, file_path)

                    risk_percentage = 0

                    image = cv2.imread(file_path)

                    Width = image.shape[1]
                    Height = image.shape[0]
                    scale = 0.00392

                    classes = None

                    with open(args.classes, 'r') as f:
                        classes = [line.strip() for line in f.readlines()]

                    COLORS = np.random.uniform(0, 255, size=(len(classes), 3))

                    net = cv2.dnn.readNet(args.weights, args.config)

                    blob = cv2.dnn.blobFromImage(
                        image, scale, (416, 416), (0, 0, 0), True, crop=False)

                    net.setInput(blob)

                    layer_names = net.getLayerNames()
                    try:
                        output_layers = [layer_names[i - 1]
                                         for i in net.getUnconnectedOutLayers()]
                    except:
                        output_layers = [layer_names[i[0] - 1]
                                         for i in net.getUnconnectedOutLayers()]

                    outs = net.forward(output_layers)

                    class_ids = []
                    confidences = []
                    boxes = []
                    conf_threshold = 0.7

                    nms_threshold = 0.4

                    for out in outs:
                        for detection in out:
                            scores = detection[5:]
                            class_id = np.argmax(scores)
                            confidence = scores[class_id]
                            if confidence > conf_threshold and classes[class_id] == 'person':
                                center_x = int(detection[0] * Width)
                                center_y = int(detection[1] * Height)
                                w = int(detection[2] * Width)
                                h = int(detection[3] * Height)
                                x = center_x - w / 2
                                y = center_y - h / 2
                                class_ids.append(class_id)
                                confidences.append(float(confidence))
                                boxes.append([x, y, w, h])

                    indices = cv2.dnn.NMSBoxes(
                        boxes, confidences, conf_threshold, nms_threshold)

                    for i in indices:
                        try:
                            box = boxes[i]
                        except:
                            i = i[0]
                            box = boxes[i]

                        x = box[0]
                        y = box[1]
                        w = box[2]
                        h = box[3]

                        person_image = image[round(y):round(
                            y + h), round(x):round(x + w)]

                        person_id = str(uuid.uuid4()) + \
                            datetime.datetime.now().strftime("%Y%m%d%H%M%S")
                        output_path = os.path.join(
                            args.output_dir, f"person{person_id}.jpg")
                        cv2.imwrite(output_path, person_image)

                        # draw_prediction(image, class_ids[i], confidences[i], round(x), round(y), round(x + w), round(y + h))

                        label = str(classes[class_ids[i]])

                        color = COLORS[class_ids[i]]

                        cv2.rectangle(image, (round(x), round(y)),
                                      (round(x + w), round(y + h)), color, 2)

                        cv2.putText(image, label, (round(x) - 10, round(y) - 10),
                                    cv2.FONT_HERSHEY_SIMPLEX, 0.5, color, 2)

                    face_cascade = cv2.CascadeClassifier(
                        cv2.data.haarcascades + "haarcascade_frontalface_default.xml")

                    output_dir = 'output'

                    for filename in os.listdir(output_dir):
                        if filename.endswith(".jpg"):
                            img = cv2.imread(
                                os.path.join(output_dir, filename))

                            gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
                            faces = face_cascade.detectMultiScale(
                                gray, scaleFactor=1.1, minNeighbors=5, minSize=(30, 30))
                            for (x, y, w, h) in faces:
                                roi_gray = gray[y:y + h, x:x + w]
                                eyes = cv2.CascadeClassifier(cv2.data.haarcascades + "haarcascade_eye.xml").detectMultiScale(roi_gray,
                                                                                                                             scaleFactor=1.1,
                                                                                                                             minNeighbors=5)
                                if len(eyes) == 2:
                                    (ex1, ey1, ew1, eh1), (ex2,
                                                           ey2, ew2, eh2) = eyes
                                    if ex1 < ex2:
                                        print(
                                            f"{filename} is facing the camera")
                                        risk_percentage += 15
                                    else:
                                        print(
                                            f"{filename} is not facing the camera")
                                        risk_percentage += 5
                                else:
                                    print(
                                        f"Skipping {filename} - unable to detect eyes")

                            # conn.sendall(str(risk_percentage).encode())
                            # with open(file_path, 'rb') as f:
                            #     file_data = f.read()
                            #     print(
                            #         f'Received file {file_path} with {len(file_data)} bytes')
                            # conn.sendall(b'Successfully received files')


if __name__ == '__main__':
    print('start')
    x = threading.Thread(target=server_socket)
    x.start()
    print("Waiting for thread...")
    x.join()
