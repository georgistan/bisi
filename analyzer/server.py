import os
import socket

HOST = "127.0.0.1"  # Standard loopback interface address (localhost)
PORT = 65432  # Port to listen on (non-privileged ports are > 1023)
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
                with open(file_path, 'rb') as f:
                    file_data = f.read()
                    # process the file data as needed
                    print(
                        f'Received file {file_path} with {len(file_data)} bytes')
