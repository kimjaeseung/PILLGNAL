import pandas as pd
train_df = pd.read_csv("C:/pill/nsmc-master/ratings_train.txt", "\t")
test_df = pd.read_csv("C:/pill/nsmc-master/ratings_test.txt", "\t")
from konlpy.tag import Okt
okt = Okt()
def tokenize(doc):
    return ['/'.join(t) for t in okt.pos(doc, norm=True, stem=True)]
train_df.isnull().any()
train_df['document'] = train_df['document'].fillna('');
test_df.isnull().any()
test_df['document'] = test_df['document'].fillna('');
train_docs = [(tokenize(row[1]), row[2]) for row in train_df.values]
test_docs = [(tokenize(row[1]), row[2]) for row in test_df.values]
tokens = [t for d in train_docs for t in d[0]]
import nltk
text = nltk.Text(tokens, name='NMSC')
FREQUENCY_COUNT = 10000;
selected_words = [f[0] for f in text.vocab().most_common(FREQUENCY_COUNT)]
def term_frequency(doc):
    return [doc.count(word) for word in selected_words]
x_train = [term_frequency(d) for d,_ in train_docs]
x_test = [term_frequency(d) for d,_ in test_docs]
y_train = [c for _,c in train_docs]
y_test = [c for _,c in test_docs]
import numpy as np
x_train = np.asarray(x_train).astype('float32')
x_test = np.asarray(x_test).astype('float32')
y_train = np.asarray(y_train).astype('float32')
y_test = np.asarray(y_test).astype('float32')
import tensorflow as tf
model2 = tf.keras.models.load_model('C:/pill/pill_prescription.h5')
model2.summary()
results = model2.evaluate(x_test, y_test)

temp = 0
temp2 = 0
def predict_review(review):
    token = tokenize(review)
    tfq = term_frequency(token)
    data = np.expand_dims(np.asarray(tfq).astype('float32'), axis=0)
    score = float(model2.predict(data))
    global temp
    global temp2
    if(score > 0.5):
        temp = 1
        temp2 = round(score*100)
    else:
        temp = 2
        temp2 = round((1-score)*100)

import numpy as np
import json
from PIL import Image
import PIL
import torch
import torch.nn as nn
import torch.optim as optim
from torch.optim import lr_scheduler
from torchvision import transforms
import matplotlib.pyplot as plt
import time
import os
import copy
import random
from efficientnet_pytorch import EfficientNet
import torchvision
import tensorflow as tf
from torchvision import transforms, datasets

class_names = {
    "0": "원형",      # "0": "원형"
    "1": "육각형",   # "1": "육각형"
    "2": "팔각형",   # "2": "팔각형"
    "3": "타원형",  # "3": "타원형"
    "4": "오각형",   # "4": "오각형"
    "5": "직사각형",   # "5": "직사각형"
    "6": "마름모형",   # "6": "마름모형"
    "7": "반원형",   # "7": "반원형"
    "8": "정사각형",   # "8": "정사각형"
    "9": "삼각형"   # "9": "삼각형"
}

model_name = 'efficientnet-b0'  # b5
image_size = EfficientNet.get_image_size(model_name)
model = EfficientNet.from_pretrained(model_name, num_classes=10)
PATH = "C:/python/pill_model2.pt"
device = torch.device('cpu')
model.load_state_dict(torch.load(PATH, map_location=device))
from torchvision import transforms, datasets
data_transforms = transforms.Compose([
                                    transforms.Resize((224, 224)),
                                    transforms.ToTensor(),
                                    transforms.Normalize(mean=[0.485, 0.456, 0.406], std=[0.229, 0.224, 0.225]),
                                ])

import socket, threading
import urllib.request as req
def binder(client_socket, addr):		
  print('Connected by', addr);		
  try:
    while True:
      data = client_socket.recv(4)
      length = int.from_bytes(data, "little")
      data = client_socket.recv(length)
      msg = data.decode()
      if msg != '' and msg.find('https') == 0:
        save_path = "C:/python/test/temp.jpg"
        image_read = req.urlopen(msg).read()
        image_open = open(save_path,'wb')
        image_open.write(image_read)
        img = PIL.Image.open(save_path).convert('RGB')
        print(img)
        img = data_transforms(img)
        print(img)
        model.eval()
        prediction = model(img.unsqueeze(0))
        a = torch.argmax(prediction)
        temp3 = str(a.item())
        print(class_names[temp3])
        msg = class_names[temp3]
        data = msg.encode()
        length = len(data)
        client_socket.sendall(length.to_bytes(4, byteorder='little'))
        client_socket.sendall(data)

      elif msg != '':
          ttt = str(msg)
          ttt2 = ttt.split("/")
          ttt3 = ''
          for i in ttt2:
              predict_review(i)
              if(temp == 1 and temp2 == 100):
                  ttt3 = ttt3 + i + '/'
          msg = ttt3
          data = msg.encode()
          length = len(data)
          client_socket.sendall(length.to_bytes(4, byteorder='little'))
          client_socket.sendall(data)
  except:		
    print("except : " , addr);		
  finally:		
    client_socket.close();		
 		
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM);		
server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1);		
server_socket.bind(('', 9999));		
server_socket.listen();		
 		
try:		
  while True:				
    client_socket, addr = server_socket.accept();		
    th = threading.Thread(target=binder, args = (client_socket,addr));		
    th.start();		
except:		
  print("server");		
finally:		
  server_socket.close();

