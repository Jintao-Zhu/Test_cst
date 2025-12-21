-- lua程序入口函数
function entry()
	-- 此处输入程序代码

  --被测件的初始时间
	local timeStart = os.time({year=2000, month = 1, day =1, hour =0, min =0, sec = 0})
  local resStart = ask("yesno",{title='时间设置', msg='初始时间，请确认初始时间是否设置成功', default=true})
	check(resStart, "正确", "错误")

  --设置超时时间 
	local timeOver = os.time({year=2222, month = 1, day = 1, hour = 0, min = 0, sec = 0})
  local resOver = ask("yesno",{title='时间设置', msg='超时时间，请确认超时年份是否截断成功', default=true})
	check(resOver, "正确", "错误")

  --设置当前时间 
	local tmNow = os.time({year=2525, month = 10, day = 31, hour = 22, min = 22, sec = 22})
  local resNow = ask("yesno",{title='时间设置', msg='初始时间，请确认初始时间是否设置成功', default=true})
	check(resNow, "正确", "错误")

	local currentSecends = tmNow - timeStart
  if (currentSecends < 0) then 
    currentSecends = 0 
  end
  
  --性别：男 身高：180 体重：70kg 年龄：20
	local data = { sex=test_data.testdata.gender, height=test_data.testdata.height, weight=test_data.testdata.weight, age=test_data.testdata.age } 
  data["currentSecends"] = currentSecends
	write_msg(channels.upper, protocols.P_Info, data)
  etimer.delay(100)

  clear(channels.upper)
  etimer.delay(1000)

	exit()
end