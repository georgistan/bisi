from flask import Flask, jsonify, request
from firebase_admin import firestore
from firebase_admin import credentials
from firebase_admin import storage

app = Flask(__name__)


@app.route('/')
def hello():
    return 'Hello, World!'


@app.route('/upload', methods=['GET', 'POST'])
def upload():
    # db = firestore.Client()
    # db.collection('test').document('test').set({'test': 'test'})
    # return 'Upload success'

    image_data = request.get_data()

    # print(image_data)

    db = firestore.client()

    

    with open(f'./images/', 'wb') as f:
        f.write(image_data)

    bucket = storage.bucket()
    blob = bucket.blob('test.jpg')
    blob.upload_from_string(image_data, content_type='image/jpeg')


    return 'Upload success'


if __name__ == '__main__':
    app.run()
