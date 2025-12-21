-- 借出程序

function entry()

  -- 服务器下发“借出”指令
  write_msg(channels.COM, protocols.Prot_server_send, {cmd_type=1})

  ask("ok",{msg='请点击“取走”按钮'})
  -- 服务器读取控制器发送的信息，获取借出电池的编号
  local res = read_msg(channels.COM, protocols.Prot_server_receive,300)
  print("服务器读取控制器发送的信息: ",res)

  -- 打印借出电池的编号
  local rstr = ""
  for i=1,10 do
    rstr = rstr..string.char(res.value.battery_id[i])
  end
  print("借出电池的编号: ",rstr)

  if(res==nil)then

      print("服务器没有收到返回消息，借出失败")
      exit()
  end
  exit()
end

