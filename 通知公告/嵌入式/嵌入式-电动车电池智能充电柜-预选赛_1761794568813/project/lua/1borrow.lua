-- -- 借出程序

-- function entry()

--   -- 服务器下发“借出”指令
--   write_msg(channels.COM, protocols.Prot_server_send, {cmd_type=1})

--   ask("ok",{msg='请点击“取走”按钮'})
--   -- 服务器读取控制器发送的信息，获取借出电池的编号
--   local res = read_msg(channels.COM, protocols.Prot_server_receive,300)
--   print("服务器读取控制器发送的信息: ",res)

--   -- 打印借出电池的编号
--   local rstr = ""
--   for i=1,10 do
--     rstr = rstr..string.char(res.value.battery_id[i])
--   end
--   print("借出电池的编号: ",rstr)

--   if(res==nil)then

--       print("服务器没有收到返回消息，借出失败")
--       exit()
--   end
--   exit()
-- end

-- 模拟重复归还相同 battery_id
function entry()
  print("测试：重复归还编号")

  local battery_id = "GXCDB-1001"  -- 直接使用字符串类型

  -- 第一次归还（正常）
  write_msg(channels.COM, protocols.Prot_scanner_send, {
      data_length = #battery_id,       -- 自动计算长度
      battery_id = battery_id,         -- 字符串类型
      StateofCharge = 80,
      data_flag = 0xFF
  })
  etimer.delay(1000)

  -- 第二次归还（重复）
  write_msg(channels.COM, protocols.Prot_scanner_send, {
      data_length = #battery_id,
      battery_id = battery_id,         -- 仍然是字符串
      StateofCharge = 60,
      data_flag = 0xFF
  })
  etimer.delay(1000)

  local res = ask("yesno", {
      title = "重复归还",
      msg = "控制器是否将第二次归还是丢包处理？（应丢包/忽略）",
      default = false
  })

  check(not res, "重复归还被丢弃/忽略 ✅", "重复归还未被忽略 ❌")
  exit()
end

