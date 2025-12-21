'''
Copyright: Shanghai Trusted Industrial Control Platform Co., Ltd.
Description: 主要用途与功能
Date: 2024-11-12 10:43:58
Author: xxx
'''
class ASCData:
    def __init__(self, index, timestamp, channel, id, direction, type, dlc, data):
        self.index = index  # 编号
        self.timestamp = timestamp  # 时间戳
        self.channel = channel      # 通道
        self.id = id                # ID
        self.direction = direction  # 方向 (Rx/Tx)
        self.type = type            # 类型
        self.dlc = dlc              # 数据长度码 (DLC)
        self.data = data            # 数据域

    def __repr__(self):
        return (f"ASCData(index={self.index}, timestamp={self.timestamp}, channel={self.channel}, "
                f"id={self.id}, direction={self.direction}, type={self.type}, "
                f"dlc={self.dlc}, data={self.data})")

    def is_valid(self):
        """ 检查规则 """
        
        # 规则1: 检查 ID 是否在 0x000 - 0x7FF 范围内
        try:
            id_int = int(self.id, 16)  # 将 ID 转换为十六进制整数
            if id_int == 0x240:
                print("ID is valid")
                return True  # ID 在范围内，返回 False
        except ValueError:
            print("ID")
            return False  # 无法解析 ID 为十六进制

data_list = []
size = 0
# 打开并读取 .asc 文件
with open('path2\\test.asc', 'r', encoding='utf-8') as file:
    index = 0
    for line in file:
        # 删除开头和结尾的空格字符
        line = line.strip()

        # 使用空格分隔每一行，前 6 个部分固定，最后一个作为数据域
        parts = line.split(maxsplit=6)
        
        try:
            # 将最后一部分作为数据域
            timestamp = float(parts[0])
            channel = int(parts[1])
            id = parts[2]
            direction = parts[3]
            type = parts[4]
            dlc = int(parts[5])
            # 确保有足够的字段
            if len(parts) < 7:
                data = ""
            else:
                data = parts[6]  # 数据域可以包含多个空格分隔的值

            # 创建 ASCData 类的实例
            asc_data = ASCData(index, timestamp, channel, id, direction, type, dlc, data)
            
            # 将实例添加到数组中
            data_list.append(asc_data)
            index = index + 1
            size = size + 1
        except ValueError as e:
            print(f"Error processing line: {line} - {e}")

print("-------------------------------------------------------------------")
num = 0 
for data in data_list:

    if data.is_valid():
        print(f"Invalid ASCData found: {data}")
        num = num + 1

print(f'该asc文件中有{size}条报文')
print(f'该asc文件中有{num}条ID为240的报文')