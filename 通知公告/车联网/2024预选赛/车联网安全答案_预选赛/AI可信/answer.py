import torch
import torch.nn.functional as F
from ultralytics import YOLO
from torchvision.utils import save_image
from torchvision import transforms
from PIL import Image

import torch
import torch.nn.functional as F
from ultralytics import YOLO

import torch
import torch.nn as nn
from ultralytics import YOLO

import torch
import numpy as np
from ultralytics import YOLO
from PIL import Image
from torchvision import transforms

def evolutionary_attack(image, model, target_class, epsilon=0.3, population_size=10, generations=50):
    img_tensor = image
    perturbed_image = img_tensor.clone()

    # 初始种群：随机生成多个扰动
    population = [torch.randn_like(perturbed_image) * epsilon for _ in range(population_size)]

    for gen in range(generations):
        # 评估种群中的每个扰动，查看是否已达到目标分类
        fitness_scores = []
        for delta in population:
            perturbed_candidate = torch.clamp(perturbed_image + delta, 0, 1)
            with torch.no_grad():
                output = model(perturbed_candidate)[0].boxes
                # 检查是否有目标类别的输出
                target_conf = max((box.conf for box in output if box.cls == target_class), default=0)
                fitness_scores.append(target_conf)

        # 如果已经成功误分类，停止攻击
        max_fitness = max(fitness_scores)
        if max_fitness > 0.5:  # 设定阈值，例如0.5
            print(f"成功攻击！在第 {gen + 1} 代后误分类为目标类别 {target_class}")
            best_delta = population[fitness_scores.index(max_fitness)]
            return torch.clamp(perturbed_image + best_delta, 0, 1)

        # 选择表现较好的扰动，并进行交叉和变异生成下一代
        top_indices = np.argsort([float(score) for score in fitness_scores])[-population_size // 2:]  # 选择一半最优
        next_population = [population[i] for i in top_indices]

        # 交叉操作：组合两个扰动
        while len(next_population) < population_size:
            parents = np.random.choice(next_population, 2, replace=False)
            child = (parents[0] + parents[1]) / 2
            next_population.append(child)

        # 变异操作：为下一代加入小扰动
        population = [child + torch.randn_like(perturbed_image) * 0.01 for child in next_population]

    print("攻击未成功")
    return perturbed_image  # 返回初始图像



model = YOLO('runs/detect/train14/weights/best.pt')
image = Image.open(r"./被测件/163810.jpg").convert("RGB")
transformer = transforms.Compose([transforms.Resize((1280,1280)),transforms.ToTensor()])
image = transformer(image).unsqueeze(0)
res_adv = evolutionary_attack(image,model,14)
np.save('tensor.npy', res_adv)
save_image(res_adv,"res.png")