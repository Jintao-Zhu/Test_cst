-- 读码器接口

function entry()

  -- 报头错误的数据包
  -- write_msg(channels.COM, protocols.Prot_scanner_send, {cmd_type=1,header=0xAA11})
  -- etimer.delay(1000)
  -- local res = ask("yesno",{title='1提示', msg='请确认被测件是否完成归还，点击“归还，插入”以继续', default=false})
	-- check(not res, "包头错误有丢包处理，正确", "包头错误没有丢包处理，错误")

  ------------------------------------------------------------
  -- ① 包头错误测试（header 错误）
  ------------------------------------------------------------
  write_msg(channels.COM, protocols.Prot_scanner_send, {header = 0xAA11})
  etimer.delay(1000)
  local res = ask("yesno", {title = '包头错误', msg = '请确认被测件是否丢包？（正常应丢弃）', default = false})
  check(not res, "包头错误有丢包处理 ✅", "包头错误没有丢包处理 ❌")

  ------------------------------------------------------------
  -- ② 数据长度错误测试（data_length 错误）
  ------------------------------------------------------------
  write_msg(channels.COM, protocols.Prot_scanner_send, {data_length = 0x0A}) -- 正确应为 0x0B
  etimer.delay(1000)
  res = ask("yesno", {title = '数据长度错误', msg = '请确认被测件是否丢包？（正常应丢弃）', default = false})
  check(not res, "数据长度错误有丢包处理 ✅", "数据长度错误没有丢包处理 ❌")

  ------------------------------------------------------------
  -- ③ 校验和错误测试（checksum 错误）
  ------------------------------------------------------------
  write_msg(channels.COM, protocols.Prot_scanner_send, {check_code = 0x00}) -- 故意错误
  etimer.delay(1000)
  res = ask("yesno", {title = '校验和错误', msg = '请确认被测件是否丢包？（正常应丢弃）', default = false})
  check(not res, "校验和错误有丢包处理 ✅", "校验和错误没有丢包处理 ❌")

  ------------------------------------------------------------
  -- ④ 数据标志错误测试（data_flag 错误）
  ------------------------------------------------------------
  write_msg(channels.COM, protocols.Prot_scanner_send, {data_flag = 0xAA}) -- 应为 0xFF 或 0x0F
  etimer.delay(1000)
  res = ask("yesno", {title = '数据标志错误', msg = '请确认被测件是否丢包？（正常应丢弃）', default = false})
  check(not res, "数据标志错误有丢包处理 ✅", "数据标志错误没有丢包处理 ❌")

  ------------------------------------------------------------
  -- ⑤ 包尾错误测试（tail 错误）
  ------------------------------------------------------------
  write_msg(channels.COM, protocols.Prot_scanner_send, {tail = 0x5511}) -- 应为 0x55AA
  etimer.delay(1000)
  res = ask("yesno", {title = '包尾错误', msg = '请确认被测件是否丢包？（正常应丢弃）', default = false})
  check(not res, "包尾错误有丢包处理 ✅", "包尾错误没有丢包处理 ❌")

  ------------------------------------------------------------
  -- ⑥ 电池编号格式错误（battery_id 不以 'GXCDB-' 开头）
  ------------------------------------------------------------
  local bad_id = {}
  local fake_str = "INVALID001"  -- 非法编号
  for i = 1, #fake_str do
      table.insert(bad_id, string.byte(fake_str, i))
  end
  write_msg(channels.COM, protocols.Prot_scanner_send, {battery_id = bad_id})
  etimer.delay(1000)
  res = ask("yesno", {title = '电池编号错误', msg = '请确认被测件是否丢包？（正常应丢弃）', default = false})
  check(not res, "编号错误有丢包处理 ✅", "编号错误没有丢包处理 ❌")

  ------------------------------------------------------------
  -- ⑦ 电量范围错误（StateofCharge 超出 0–100）
  ------------------------------------------------------------
  write_msg(channels.COM, protocols.Prot_scanner_send, {StateofCharge = 150})
  etimer.delay(1000)
  res = ask("yesno", {title = '电量范围错误', msg = '请确认被测件是否丢包？（正常应丢弃）', default = false})
  check(not res, "电量范围错误有丢包处理 ✅", "电量范围错误没有丢包处理 ❌")


  exit()
end

