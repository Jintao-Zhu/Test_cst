-- 查询接口

function entry()

  write_msg(channels.COM, protocols.Prot_server_send, {cmd_type=3})
  local res = read_msg(channels.COM, protocols.Prot_server_receive,50000)
  print(res.value)
  print("共有"..res.value.battery_num.."个充电宝")
  for j=1,res.value.battery_num do
      local rstr = ""
      for i=1,10 do
        rstr = rstr..string.char(res.value.array_battery_id[j].battery_id[i])
      end
      print(rstr)
  end
  exit()
end
