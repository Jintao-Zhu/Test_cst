from ultralytics import YOLO
model = YOLO('best.pt')
results = model("1.jpg")
print(results)
