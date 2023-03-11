import datetime
from random import randint
import threading
import firebase_admin
import random
import string
from flask import Flask, jsonify, request, make_response
from firebase_admin import firestore
from firebase_admin import credentials
from firebase_admin import storage
import time
import socket
import uuid
import os

app = Flask(__name__)

cred = credentials.Certificate('serviceAccountKey.json')
firebase_admin.initialize_app(cred, {
    'storageBucket': 'bisi-fe60e.appspot.com'
})


def thread_socket():
    print('socket')
    HOST = "127.0.0.1"
    PORT = 65432
    IMAGES_FOLDER = 'images'

    server_dir = os.path.abspath(os.path.dirname(__file__))
    images_dir = os.path.join(server_dir, IMAGES_FOLDER)

    # while True:
    #     with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    #         s.connect((HOST, PORT))
    #         s.sendall(b"Hello, world")
    #         data = s.recv(1024)
    #         time.sleep(1)

    #     print(f"Received {data!r}")

    while True:
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
            s.connect((HOST, PORT))
            file_paths = []
            for filename in os.listdir(images_dir):
                file_paths.append(os.path.join(images_dir, filename))
                print(os.path.join(images_dir, filename))
            file_paths_bytes = '\n'.join(file_paths).encode()
            s.sendall(file_paths_bytes)
            print(f'Sent {len(file_paths)} file paths')
            data = s.recv(1024)
            time.sleep(1)

        # print(f'Received: {response.decode()}')

        # print(f"Server: {data!r}")


@app.route('/', methods=['GET'])
def hello():
    return 'Hello, World!'


@app.route('/upload', methods=['GET', 'POST'])
def upload():
    db = firestore.client()

    if not request.get_data():
        return make_response(jsonify({'message': 'No file'}), 400)

    if request.path != '/upload':
        return make_response(jsonify({'message': 'Invalid path'}), 404)

    image_data = request.get_data()

    user_uid = str(uuid.uuid4())
    file_name = str(datetime.datetime.now().strftime(
        "%x_%X").replace(":", "_").replace("/", "_"))+".jpeg"
    with open(f'./images/{file_name}', 'wb+') as f:
        f.write(image_data)

    bucket = storage.bucket()
    blob = bucket.blob(file_name)
    blob.upload_from_filename(f'./images/{file_name}')

    doc_ref = db.collection(u'users').document(user_uid)

    doc_ref.set({
        u'uid': user_uid,
        u'image_url': blob.public_url,
        u'risk_percentage': 0,
        u'created_at': firestore.SERVER_TIMESTAMP,
    })

    return make_response(jsonify({'message': 'Upload success'}), 200)


@app.route('/users', methods=['GET'])
def users():
    db = firestore.client()

    users_ref = db.collection(u'users')
    docs = users_ref.stream()

    users = []

    for doc in docs:
        users.append(doc.to_dict())

    return make_response(jsonify({'users': users}), 200)


@app.route('/users/<user_uid>', methods=['GET'])
def user(user_uid):
    db = firestore.client()

    users_ref = db.collection(u'users')
    doc = users_ref.document(user_uid).get()

    if not doc.exists:
        return make_response(jsonify({'message': 'User not found'}), 404)

    return make_response(jsonify(doc.to_dict()), 200)


if __name__ == '__main__':
    print('start')
    x = threading.Thread(target=thread_socket)
    x.start()
    y = threading.Thread(target=app.run, args=("0.0.0.0", 5000))
    y.start()
    # app.run(host="0.0.0.0", port=5000)
    print("Waiting for thread...")
    x.join()
    y.join()
