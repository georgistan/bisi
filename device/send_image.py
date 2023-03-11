from picamera import PiCamera
from time import sleep
import requests
import os

camera = PiCamera()
camera.resolution = (640, 480)

for i in range(10):
    camera.capture('/home/pi/send_pic/image'+str(i)+'.jpeg')
    data = open('image'+str(i)+'.jpeg', 'rb').read()
    response = requests.post(
        'http://192.168.200.1:5000/upload', data=data)
    sleep(1)
