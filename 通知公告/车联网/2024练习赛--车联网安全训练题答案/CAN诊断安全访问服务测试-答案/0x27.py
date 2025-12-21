'''
Copyright: Shanghai Trusted Industrial Control Platform Co., Ltd.
Description: 主要用途与功能
Date: 2024-09-30 15:15:36
Author: xxx
'''
keywords = ['67 0', '27 0']  # 你要查找的两个关键字

# 打开并读取ASC文件
with open('0x27.asc', 'r', encoding='utf-8') as infile:
    lines = infile.readlines()

# 打开一个新文件用于保存符合条件的行
with open('0x27_copy.asc', 'w', encoding='utf-8') as outfile:
    for line in lines:
        # 检查每一行是否包含任意一个关键字
        if any(keyword in line for keyword in keywords):
            outfile.write(line)

# 计算异或数
a = 0xA15693C4
b = 0xFE7CEDDD

result = a ^ b
print(f"{hex(a)} 异或 {hex(b)} 的结果是 {hex(result)}")