-- 状态自检接口

function entry()

  write_msg(channels.COM, protocols.Prot_server_send, {cmd_type=4})
  local res = read_msg(channels.COM, protocols.Prot_server_receive,50000)
  -- print(res.value)
  print("共有"..res.value.failure_battery_num.."个故障充电宝")

  for j=1,res.value.failure_battery_num do
      local rstr = ""
      for i=1,10 do
        rstr = rstr..string.char(res.value.array_failure_battery_id[j].battery_id[i])
      end
      print(rstr)
  end
  exit()
end
