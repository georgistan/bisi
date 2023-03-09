import requests

headers = {'Content-Type': 'image/jpeg'}

with open('./images/image.jpg', 'rb') as f:
    image_data = f.read()
    response = requests.post(
        'http://127.0.0.1:5000/upload', headers=headers, data=image_data)