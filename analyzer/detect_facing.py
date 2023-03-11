import cv2
import os

face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + "haarcascade_frontalface_default.xml")

output_dir = 'output'

for filename in os.listdir(output_dir):
    if filename.endswith(".jpg"):
        img = cv2.imread(os.path.join(output_dir, filename))

        gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
        faces = face_cascade.detectMultiScale(gray, scaleFactor=1.1, minNeighbors=5, minSize=(30, 30))
        
        for (x, y, w, h) in faces:
            roi_gray = gray[y:y + h, x:x + w]
            eyes = cv2.CascadeClassifier(cv2.data.haarcascades + "haarcascade_eye.xml").detectMultiScale(roi_gray,
                                                                                                         scaleFactor=1.1,
                                                                                                         minNeighbors=5)
            if len(eyes) == 2:
                (ex1, ey1, ew1, eh1), (ex2, ey2, ew2, eh2) = eyes
                if ex1 < ex2:
                    print(f"{filename} is facing the camera")
                else:
                    print(f"{filename} is not facing the camera")
            else:
                print(f"Skipping {filename} - unable to detect eyes")
