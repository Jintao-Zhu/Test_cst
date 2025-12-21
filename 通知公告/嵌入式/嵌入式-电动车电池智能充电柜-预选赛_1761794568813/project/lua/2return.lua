--归还程序

function entry()

  ask("ok",{msg="请点击“归还”按钮"})
  ask("ok",{msg="请点击“插入”按钮"})

  etimer.delay(1000)

  local tmp = ebuff.from_hex('47 58 43 44 42 2D 31 30 31 30')
  local tmap = {data_flag=0xFF,StateofCharge=10}
  tmap["battery_id"] = tmp

  -- 发送tmap配置的报文数据
  local res = write_msg(channels.COM, protocols.Prot_scanner_send,tmap)
  print(res)

  exit()
end
