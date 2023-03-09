import firebase_admin
from firebase_admin import credentials

cred = credentials.Certificate('serviceAccountKey.json')
firebase_admin.initialize_app(cred, {
    'storageBucket': 'gs://bisi-fe60e.appspot.com'
})
