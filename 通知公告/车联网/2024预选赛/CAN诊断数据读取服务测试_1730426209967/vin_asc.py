
def find_vin_in_asc(file_path):
    vin_code = ""

    # 读取 ASC 文件内容
    with open(file_path, 'r') as file:
        lines = file.readlines()[4:]

    for line in lines:
        data = line.split()
        if data[2] == '728' and data[8] == '62' and data[9] == 'F1' and data[10] == '90':
            vin_code = data[11] + data[12] + data[13]
            break
    

    return vin_code

asc_file_path = './0x22.asc' 
vin = find_vin_in_asc(asc_file_path)
vin = bytes.fromhex(vin).decode('ascii')
print("VIN 码（ASCII）：", vin)
