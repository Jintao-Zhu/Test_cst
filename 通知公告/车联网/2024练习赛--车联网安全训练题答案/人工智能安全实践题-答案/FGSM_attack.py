import torch.nn as nn
import torch.nn.functional as F
import torch
from torchvision import datasets, transforms
from torch.utils.data import DataLoader
import matplotlib.pyplot as plt
import numpy as np
import cv2

use_cuda = False
device = torch.device("cuda" if (use_cuda and torch.cuda.is_available()) else "cpu")


# LeNet 模型定义
class Net(nn.Module):
    def __init__(self):
        super(Net, self).__init__()
        self.conv1 = nn.Conv2d(1, 10, kernel_size=5)
        self.conv2 = nn.Conv2d(10, 20, kernel_size=5)
        self.conv2_drop = nn.Dropout2d()
        self.fc1 = nn.Linear(320, 50)
        self.fc2 = nn.Linear(50, 10)

    def forward(self, x):
        x = F.relu(F.max_pool2d(self.conv1(x), 2))
        x = F.relu(F.max_pool2d(self.conv2_drop(self.conv2(x)), 2))
        x = x.view(-1, 320)
        x = F.relu(self.fc1(x))
        x = F.dropout(x, training=self.training)
        x = self.fc2(x)
        return F.log_softmax(x, dim=1)

    # MNIST数据集和数据加载器声明


transform = transforms.Compose([transforms.ToTensor()])
test_dataset = datasets.MNIST(root='./datasets/', train=False, download=False, transform=transform)
test_loader = DataLoader(test_dataset, batch_size=1, shuffle=False)

# 加载预训练模型lenet_mnist_model.pth
model = Net().to(device)
pretrained_model = "./model/lenet_mnist_model.pth"
model.load_state_dict(torch.load(pretrained_model, map_location='cpu'))
model.eval()


# FGSM attack code
def fgsm_attack(image, epsilon, data_grad):
    sign_data_grad = data_grad.sign()
    # 对抗样本perturbed_image由原始图像叠加epsilon像素级扰动量epsilon与梯度符号sign_data_grad的乘积组成
    perturbed_image = image + epsilon * sign_data_grad
    perturbed_image = torch.clamp(perturbed_image, 0, 1)
    return perturbed_image


index = 0
perturbed_data = None
data = None
for data, target in test_loader:
    low = 0
    high = 1
    epsilon = 0.5
    while low < epsilon < high:
        data, target = data.to(device), target.to(device)
        data.requires_grad = True
        output = model(data)
        init_pred = output.max(1, keepdim=True)[1]  # 选取最大的类别概率
        loss = F.nll_loss(output, target)
        model.zero_grad()
        loss.backward()
        data_grad = data.grad.data
        perturbed_data = fgsm_attack(data, epsilon, data_grad)
        output = model(perturbed_data)
        final_pred = output.max(1, keepdim=True)[1]
        if final_pred.item() == init_pred.item():  # 说明此次攻击不成功，那我们应该在更高的区间内搜寻最优解
            low = epsilon
            epsilon = round((epsilon + high) / 2, 3)  # 将精度限制在0.01
        else:  # 说明此次攻击是成功的，我们在更低的区间寻找最优解
            high = epsilon
            epsilon = round((epsilon + low) / 2, 3)
    perturbed_data = perturbed_data.squeeze().detach().cpu().numpy()
    data = data.squeeze().detach().numpy()
    tensor = (perturbed_data*255)
    cv2.imwrite('results/' + str(index) + ".png", tensor)
    index += 1
    print(str(index)+" success,epsilon is "+str(epsilon))


# plt.figure(figsize=(5,10))
# plt.title("Attack success examples")
# plt.subplot(1, 2, 1)
# plt.imshow(perturbed_data, cmap="gray")
# plt.title("Detect res:"+str(final_pred.item()))
# plt.axis("off")
# plt.subplot(1, 2, 2)
# plt.imshow(data, cmap="gray")
# plt.title("Detect res:"+str(init_pred.item()))
# plt.axis("off")
# plt.show()