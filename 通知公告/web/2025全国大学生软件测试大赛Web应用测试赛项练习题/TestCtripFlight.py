import os
from datetime import datetime
import pytest
from selenium import webdriver
from selenium.webdriver import ActionChains, Keys
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from time import sleep


@pytest.fixture(scope="function")
def driver():
    service = Service(
        #提交最终代码脚本时，请将驱动路径换回官方路径"C:\\Users\\86153\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe"
        executable_path="C:\\Users\\86153\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe")
    driver = webdriver.Chrome(service=service)
    driver.get("https://www.ctrip.com/ ")
    driver.maximize_window()
    yield driver
    driver.quit()


class TestCtripFlight:
   
   
   
    #  test-code-start


    #  请在此处插入python+selenium代码


    #  test-code-end

    @staticmethod
    def take_screenshot(driver, file_name):
        timestamp = datetime.now().strftime("%H%M%S%d%f")
        timestamped_file_name = f"{timestamp}_{file_name}"
        screenshots_dir = "screenshots"
        if not os.path.exists(screenshots_dir):
            os.makedirs(screenshots_dir)
        screenshot_file_path = os.path.join(screenshots_dir, timestamped_file_name)
        driver.save_screenshot(screenshot_file_path)