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
            if id_int > 0x7FF:
                print("ID")
                return False  # ID 在范围内，返回 False
        except ValueError:
            print("ID")
            return False  # 无法解析 ID 为十六进制

        # 规则2: 检查数据域中每个值是否在 0x00 - 0xFF 范围内
        data_values = self.data.split()
        
        for value in data_values:
            try:
                value_int = int(value, 16)  # 将数据值转换为十六进制整数
                if not (0x00 <= value_int <= 0xFF):
                    print("value error")
                    return False  # 如果有超出范围的值
            except ValueError:
                print("value error")
                return False  # 数据值不是有效的十六进制

        # 规则3: 检查数据域的长度是否与 DLC 匹配
        if len(data_values) != self.dlc:

            print("dlc:",len(data_values))
            return False  # 如果数据域的字节数不等于 DLC

        return True  # 如果所有规则都符合，则返回 True

data_list = []

# 打开并读取 .asc 文件
with open('error.asc', 'r', encoding='utf-8') as file:
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
        except ValueError as e:
            print(f"Error processing line: {line} - {e}")

# 打印保存的数组中的每个对象
# for data in data_list:
#     print(data)

print("-------------------------------------------------------------------")
for data in data_list:
    if not data.is_valid():
        print(f"Invalid ASCData found: {data}")