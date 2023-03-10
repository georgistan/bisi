import datetime
from random import randint
import firebase_admin
import random
import string
from flask import Flask, jsonify, request, make_response
from firebase_admin import firestore
from firebase_admin import credentials
from firebase_admin import storage
import time
import uuid

app = Flask(__name__)

cred = credentials.Certificate('serviceAccountKey.json')
firebase_admin.initialize_app(cred, {
    'storageBucket': 'bisi-fe60e.appspot.com'
})


@app.route('/', methods=['GET'])
def hello():
    return 'Hello, World!'

@app.route('/upload', methods=['GET', 'POST'])
def upload():
    # db = firestore.Client()
    # db.collection('test').document('test').set({'test': 'test'})
    # return 'Upload success'

    if not request.get_data():
        return make_response(jsonify({'message': 'No file'}), 400)

    if request.path != '/upload':
        return make_response(jsonify({'message': 'Invalid path'}), 404)

    image_data = request.get_data()


    # print(image_data)

    # db = firestore.client()

    user_uid = str(uuid.uuid4())
    date_created = str(datetime.datetime.now().strftime("%x_%X").replace(":", "_").replace("/", "_"))
    with open(f'./images/{datetime.datetime.now().strftime("%x_%X").replace(":", "_").replace("/", "_")}.jpeg', 'wb+') as f:
        f.write(image_data)

    bucket = storage.bucket()
    blob = bucket.blob(f'{date_created}.jpeg')
    blob.upload_from_filename(f'./images/{date_created}.jpeg')

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
    app.run(host="0.0.0.0")
