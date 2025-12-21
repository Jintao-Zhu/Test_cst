-- 程序：gasControl

function entry()
	-- 加速到80（设定速度）
	for i=1,80 do
	  write_msg(channels.setting, protocols.P_setting,{cmdType = 2, dataValue=0})
		etimer.delay(100) 
	end
	--计算电压
	read_buff(channels.gas,0,100)
	local V1 = 60 --当前车速
	local V2 = 60 --上次车速
	local V3 = 60 --上上次车速
	local OUk1 = 1 --上次输出电压
	local OUk = 1  --当前输出电压
	for i = 1,#test_data.test_gas do
	  V3 = V2
	  V2 = V1
	  V1 = test_data.test_gas[i].speed
	  OUk1 = OUk
	  OUk = getOutV(V1,V2,V3, OUk1)
    print ("预期电压值为"..OUk)

	  local res = write_msg(channels.Speed, protocols.P_speed,{speed=V1})
	  etimer.delay(1000)
    local resl = read_msg(channels.gas, protocols.P_gas,10000)
	  print ("实际电压值为"..resl.value.voltage)
	  print ("----------------")
	end

	exit()
end

--计算电压函数
function getOutV(Vk, Vk1, Vk2, Uk1)
	local Dp = 0.05
  local Di = 0.1
	local Dd = 0.1
	local Vd = 80
	local ek = (Vd - Vk)
	local ek1 = (Vd - Vk1)
	local ek2 = (Vd - Vk2)
	local Uk = Dp * ( ek - ek1) + Di * ek + Dd * (ek - 2 * ek1 + ek2) + Uk1
	return Uk
end
