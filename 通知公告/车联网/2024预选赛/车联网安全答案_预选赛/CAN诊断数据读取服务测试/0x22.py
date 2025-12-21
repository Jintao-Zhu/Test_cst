'''
Copyright: Shanghai Trusted Industrial Control Platform Co., Ltd.
Description: 主要用途与功能
Date: 2024-10-08 17:34:50
Author: xxx
'''

keywords = [' 720 ', ' 728 ']  # 你要查找的两个关键字

# 打开并读取ASC文件
with open('0x22.asc', 'r', encoding='utf-8') as infile:
    lines = infile.readlines()

# 打开一个新文件用于保存符合条件的行
with open('0x22_copy.asc', 'w', encoding='utf-8') as outfile:
    for line in lines:
        # 检查每一行是否包含任意一个关键字
        if any(keyword in line for keyword in keywords):
            outfile.write(line)

# 找到关键报文后，开始进行数据域解析
#    243.349582 1  728       Rx   d 8 10 14 62 F1 90 43 43 46 
#    243.441302 1  728       Rx   d 8 21 53 6F 66 74 77 61 72 
#    243.441542 1  728       Rx   d 8 22 65 54 49 43 50 73 68 