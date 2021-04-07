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
    "0": "circle",      # "0": "원형"
    "1": "hexagon",   # "1": "육각형"
    "2": "octagon",   # "2": "팔각형"
    "3": "oval",  # "3": "타원형"
    "4": "pentagon",   # "4": "오각형"
    "5": "rectangle",   # "5": "직사각형"
    "6": "rhombus",   # "6": "마름모형"
    "7": "semicircular",   # "7": "반원형"
    "8": "square",   # "8": "정사각형"
    "9": "triangle"   # "9": "삼각형"
}

model_name = 'efficientnet-b0'  # b5
image_size = EfficientNet.get_image_size(model_name)
print(image_size)
model = EfficientNet.from_pretrained(model_name, num_classes=10)
PATH = "C:/python/pill_model.pt"
device = torch.device('cpu')
model.load_state_dict(torch.load(PATH, map_location=device))

## make dataset
from torchvision import transforms, datasets
data_transforms = transforms.Compose([
                                    transforms.Resize((224, 224)),
                                    transforms.ToTensor(),
                                    transforms.Normalize(mean=[0.485, 0.456, 0.406], std=[0.229, 0.224, 0.225]),
                                ])
img = PIL.Image.open('C:/python/test/test1.jpg')
img = data_transforms(img)
model.eval()
prediction = model(img.unsqueeze(0))
a = torch.argmax(prediction)
temp = str(a.item())
print(class_names[temp])