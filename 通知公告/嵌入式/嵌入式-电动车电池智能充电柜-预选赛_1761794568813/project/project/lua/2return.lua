--归还程序

function entry()

  ask("ok",{msg="请点击“归还”按钮"})
  ask("ok",{msg="请点击“插入”按钮"})

  etimer.delay(1000)

  local tmp = ebuff.from_hex('47 58 43 44 42 2D 31 30 31 33')
  local tmap = {data_flag=0xFF,StateofCharge=10}
  tmap["battery_id"] = tmp

  -- 发送tmap配置的报文数据
  local res = write_msg(channels.COM, protocols.Prot_scanner_send,tmap)
  print(res)

  exit()
end

-- function entry()
--   ask("ok",{msg="请点击“归还”按钮"})
--   local cabinet_state = "open"

--   -- 用户尝试借出操作
--   local borrow_attempt = true
--   if cabinet_state == "open" and borrow_attempt then
--       ask("ok",{msg="错误：柜门打开，无法进行借出操作"})
--   end

--   -- 用户尝试状态查询
--   local status_query = true
--   if cabinet_state == "open" and status_query then
--       ask("ok",{msg="错误：柜门打开，无法进行状态查询"})
--   end

--   exit()
-- end


