-- lua程序入口函数
function entry()
    -- 此处输入程序代码
    ask("ok",{msg="请点击停止运动"})
	  -- local res = read_buff(channels.upper, 0,1000)
    local res = read_msg(channels.upper, protocols.P_report,1000)
	  print (res.value)

    exit()
end