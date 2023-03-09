from flask import Flask, jsonify, request
import cv2
from google.cloud import firestore

app = Flask(__name__)


@app.route('/')
def hello():
    return 'Hello, World!'


@app.route('fetch-image', methods=['GET'])
def fetch_image():
    return 'asd'


if __name__ == '__main__':
    app.run()
