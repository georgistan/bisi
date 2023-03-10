from flask import Flask, jsonify, request, make_response
import firebase_admin
from firebase_admin import firestore
from firebase_admin import credentials
from firebase_admin import storage
import uuid

app = Flask(__name__)

cred = credentials.Certificate('serviceAccountKey.json')
firebase_admin.initialize_app(cred, {
    'storageBucket': 'bisi-fe60e.appspot.com'
})


@app.route('/')
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

    db = firestore.client()

    user_uid = str(uuid.uuid4())

    with open(f'./images/{user_uid}.jpeg', 'wb') as f:
        f.write(image_data)

    bucket = storage.bucket()
    blob = bucket.blob(f'{user_uid}.jpeg')
    blob.upload_from_filename(f'./images/{user_uid}.jpeg')

    doc_ref = db.collection(u'users').document(user_uid)

    doc_ref.set({
        u'uid': user_uid,
        u'image_url': blob.public_url,
        u'risk_percentage': 0,
        u'created_at': firestore.SERVER_TIMESTAMP,
    })

    return make_response(jsonify({'message': 'Upload success'}), 200)


if __name__ == '__main__':
    app.run()
