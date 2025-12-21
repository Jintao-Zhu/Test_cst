-- -- 服务器接口

-- function entry()

--   -- 报头错误的数据包
--   write_msg(channels.COM, protocols.Prot_server_send, {cmd_type=1,header=0x5511})
--   etimer.delay(1000)
--   local res = ask("yesno",{title='提示', msg='请确认被测件是否开始借出，点击“取出”以继续', default=false})
-- 	check(not res, "包头错误有丢包处理，正确", "包头错误没有丢包处理，错误")


--   exit()
-- end

-- 服务器接口容错覆盖测试
function entry()
  print("=== 开始执行服务器接口容错测试 ===")

  ------------------------------------------------------------
  -- ① 包头错误测试（header 错误）
  ------------------------------------------------------------
  write_msg(channels.COM, protocols.Prot_server_send, {cmd_type = 1, header = 0x5511})
  etimer.delay(1000)
  local res = ask("yesno", {title = '包头错误', msg = '请确认被测件是否开始借出？（正常应无反应）', default = false})
  check(not res, "包头错误有丢包处理 ✅", "包头错误没有丢包处理 ❌")

  ------------------------------------------------------------
  -- ② 数据长度错误测试（data_length 错误）
  ------------------------------------------------------------
  write_msg(channels.COM, protocols.Prot_server_send, {data_length = 0x03})
  etimer.delay(1000)
  res = ask("yesno", {title = '数据长度错误', msg = '请确认被测件是否开始借出？（正常应丢包）', default = false})
  check(not res, "数据长度错误有丢包处理 ✅", "数据长度错误没有丢包处理 ❌")

  ------------------------------------------------------------
  -- ③ 指令类型错误测试（cmd_type 非法）
  ------------------------------------------------------------
  write_msg(channels.COM, protocols.Prot_server_send, {cmd_type = 0x09}) -- 无效指令
  etimer.delay(1000)
  res = ask("yesno", {title = '指令类型错误', msg = '请确认被测件是否开始借出？（正常应丢包）', default = false})
  check(not res, "指令类型错误有丢包处理 ✅", "指令类型错误没有丢包处理 ❌")

  ------------------------------------------------------------
  -- ④ 数据标志错误测试（data_flag 错误）
  ------------------------------------------------------------
  write_msg(channels.COM, protocols.Prot_server_send, {data_flag = 0x00})
  etimer.delay(1000)
  res = ask("yesno", {title = '数据标志错误', msg = '请确认被测件是否开始借出？（正常应丢包）', default = false})
  check(not res, "数据标志错误有丢包处理 ✅", "数据标志错误没有丢包处理 ❌")

  ------------------------------------------------------------
  -- ⑤ 校验和错误测试（check_code 错误）
  ------------------------------------------------------------
  write_msg(channels.COM, protocols.Prot_server_send, {check_code = 0x00})
  etimer.delay(1000)
  res = ask("yesno", {title = '校验和错误', msg = '请确认被测件是否开始借出？（正常应丢包）', default = false})
  check(not res, "校验和错误有丢包处理 ✅", "校验和错误没有丢包处理 ❌")

  ------------------------------------------------------------
  -- ⑥ 包尾错误测试（tail 错误）
  ------------------------------------------------------------
  write_msg(channels.COM, protocols.Prot_server_send, {tail = 0x5511})
  etimer.delay(1000)
  res = ask("yesno", {title = '包尾错误', msg = '请确认被测件是否开始借出？（正常应丢包）', default = false})
  check(not res, "包尾错误有丢包处理 ✅", "包尾错误没有丢包处理 ❌")

  ------------------------------------------------------------
  print("=== 服务器接口容错测试结束 ===")
  exit()
end
