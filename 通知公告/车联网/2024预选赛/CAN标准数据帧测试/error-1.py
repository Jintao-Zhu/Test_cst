asc_file_path = './error.asc' 
with open(asc_file_path, 'r') as file:
    data = file.readlines()[4:]

id_errors = 0
dlc_errors = 0
data_errors = 0

def is_invalid_message_id(message_id):
    if not isinstance(message_id, str):
        return True  
    try:
        message_id_int = int(message_id, 16)
        if message_id_int < 0x000 or message_id_int > 0x7FF:
            return True  
    except ValueError:
        return True  
    return False 

def is_valid_hex(s):
    try:
        int(s, 16) 
        return False
    except ValueError:
        return True

for line in data:
    data = line.split()
    message_id = data[2]
    if is_invalid_message_id(message_id):
        id_errors += 1
    dlc = int(data[5])
    if dlc < 0 or dlc > 8:
        dlc_errors += 1
    data_bytes = data[6:]
    if len(data_bytes) != dlc or any(len(byte) != 2 for byte in data_bytes) or any(is_valid_hex(byte) for byte in data_bytes):
        data_errors += 1
    

print(f"ID Errors: {id_errors}")
print(f"DLC Errors: {dlc_errors}")
print(f"Data Field Data Errors: {data_errors}")
