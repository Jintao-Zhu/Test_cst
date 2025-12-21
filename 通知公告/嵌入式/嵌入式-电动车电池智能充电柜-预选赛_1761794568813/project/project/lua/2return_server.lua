
function entry()

  read_msg(channels.COM, protocols.Prot_server_receive,10000)

end

-- function log(msg)
--   print(os.date("[%H:%M:%S] ") .. msg)
-- end

-- -- 校验编号格式
-- function check_battery_id(id)
--   return (string.sub(id, 1, 7) == "GXCDB-" and #id == 12)
-- end

-- -- 模拟服务器发送归还指令
-- function send_return_cmd()
--   write_msg(channels.COM, protocols.Prot_server_send, {cmd_type = 2})
--   log("服务器发送“归还”指令")
-- end

-- -- 模拟控制器读取返回（服务器端接收）
-- function recv_controller_msg(timeout)
--   local res = read_msg(channels.COM, protocols.Prot_server_receive, timeout or 300)
--   if res == nil then
--     log("未收到控制器上报")
--     return nil
--   end
--   local result = res.value.result
--   local bid = ""
--   for i = 1, 10 do
--     bid = bid .. string.char(res.value.battery_id[i])
--   end
--   local electric = res.value.electric or 0
--   local flag = res.value.flag or 0xFF
--   return result, bid, electric, flag
-- end

-- -- 测试结果打印
-- function report(tc_name, condition, details)
--   if condition then
--     log(tc_name .. " ✅ PASS")
--   else
--     log(tc_name .. " ❌ FAIL: " .. (details or ""))
--   end
-- end

-- -- 主程序入口
-- function entry()
--   log("======== 归还流程覆盖测试开始 ========")

--   -------------------------------------------------
--   -- TC01: 正常归还
--   -------------------------------------------------
--   -- send_return_cmd()
--   -- ask("ok", {msg = "请点击“插入”按钮以完成正常归还"})
--   -- local result1, bid1, electric1, flag1 = recv_controller_msg(300)
--   -- local ok = (result1 == 0x01) and check_battery_id(bid1) and (electric1 >= 0 and electric1 <= 100) and (flag1 == 0xFF)
--   -- report("TC01 正常归还", ok, "result="..tostring(result1).." id="..bid1.." 电量="..tostring(electric1))

--   -------------------------------------------------
--   -- TC02: 电池编号重复归还
--   -------------------------------------------------
--   -- send_return_cmd()
--   -- local result2, bid2 = recv_controller_msg(200)
--   -- report("TC02 重复编号归还", result2 == nil, "应丢包")

--   -------------------------------------------------
--   -- TC03: 电池编号格式错误
--   -------------------------------------------------
--   -- send_return_cmd()
--   -- local result3, bid3 = recv_controller_msg(200)
--   -- local format_error = (result3 == 0x01 and not check_battery_id(bid3))
--   -- report("TC03 编号格式错误", format_error, "id="..(bid3 or "nil"))

--   -------------------------------------------------
--   -- TC04: 电量超出范围
--   -------------------------------------------------
--   send_return_cmd()
--   -- local result4, bid4, electric4 = recv_controller_msg(200)
--   -- local out_of_range = (electric4 > 100 or electric4 < 0)
--   -- report("TC04 电量超出范围", out_of_range and result4 == nil, "电量="..tostring(electric4))

--   -------------------------------------------------
--   -- TC05: 故障电池归还
--   -------------------------------------------------
--   -- send_return_cmd()
--   -- local result5, bid5, electric5, flag5 = recv_controller_msg(300)
--   -- report("TC05 故障电池归还", result5 == 0xFF and flag5 == 0x0F, "result="..tostring(result5).." flag="..string.format("0x%X", flag5))

--   -- -------------------------------------------------
--   -- -- TC06: 超时未插入
--   -- -------------------------------------------------
--   -- send_return_cmd()
--   -- log("等待30秒模拟超时，请勿点击“插入”按钮...")
--   -- local result6 = recv_controller_msg(35)
--   -- report("TC06 超时未插入", result6 == 0xFF, "返回码="..tostring(result6))

--   -------------------------------------------------
--   -- TC07: 控制器返回校验错误帧
--   -------------------------------------------------
--   write_msg(channels.COM, protocols.Prot_server_send, {cmd_type = 255}) -- 模拟错误帧
--   local result7 = recv_controller_msg(200)
--   report("TC07 校验错误帧丢包", result7 == nil, "应无响应")

--   log("======== 归还流程覆盖测试结束 ========")
--   exit()
-- end
