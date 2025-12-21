
-- -- -- 接收靶区信息
-- -- function Target()

-- --   write_msg(channels.COM_target,protocols.target,{zone=1})
-- --   etimer.delay(1000)

-- -- end

-- -- function entry()
-- --     -- 此处输入程序代码
-- --     Target()
-- --     exit()
-- -- end

-- -- ============================================
-- -- target.lua —— 靶区信息测试脚本
-- -- 覆盖需求 2.4 / 2.5 / 2.6
-- -- ============================================

-- local STATE = {
--     IDLE = 0,
--     READY = 1,
--     SHOOTING = 2,
--     FINISHED = 3
-- }

-- local device_state = STATE.IDLE

-- local function log(msg)
--     print("[TARGET-TEST] " .. msg)
-- end

-- -- 区域映射
-- local ZONE_MAP = {
--     ["内牛眼"] = 0x01,
--     ["外牛眼"] = 0x02,
--     ["三倍区"] = 0x03,
--     ["双倍区"] = 0x04,
--     ["单倍区"] = 0x05
-- }

-- -- =============================
-- -- 发送 target 协议（命中区域）
-- -- =============================
-- function send_target_hit(zone_str)

--     if device_state ~= STATE.SHOOTING then
--         log("非开始投镖状态，不接收靶区信息：" .. device_state)
--         return
--     end

--     local zone_code = ZONE_MAP[zone_str]
--     if not zone_code then
--         log("未知靶区：" .. tostring(zone_str))
--         return
--     end

--     -- 校验和：SUM8(data1, data2)
--     local check = (0xCC + 0x11) % 256

--     log("发送靶区信息：" .. zone_str .. " (code=" .. string.format("0x%02X", zone_code) .. ")")

--     write_msg(channels.COM_target, protocols.target, {
--         header = 0xAA55,
--         data1 = 0xCC,
--         zone = zone_code,
--         data2 = 0x11,
--         check = check,
--         tail = 0x55AA
--     })

--     etimer.delay(500)
-- end

-- -- ============================================
-- -- 测试 2.4：靶区命中与计分
-- -- ============================================
-- function test_hits()
--     log("开始测试 2.4 接收靶区信息并计分")

--     device_state = STATE.SHOOTING

--     local zones = {"内牛眼", "外牛眼", "三倍区", "双倍区", "单倍区"}

--     for _, z in ipairs(zones) do
--         send_target_hit(z)
--     end
-- end

-- ============================================
-- 测试 2.5 投镖结束（剩余飞镖=0 / 时间到）
-- ============================================
-- function test_finish()
--     log("测试 2.5 投镖结束")

--     device_state = STATE.SHOOTING

--     -- 模拟剩余飞镖变为 0（设备内部处理）
--     log("模拟剩余飞镖=0，触发投镖结束")
--     device_state = STATE.FINISHED
--     etimer.delay(1000)

--     -- 再测试时间到
--     device_state = STATE.SHOOTING
--     log("模拟定时器时间到，触发投镖结束")
--     device_state = STATE.FINISHED
-- end

-- -- ============================================
-- -- 测试 2.6 复位逻辑
-- -- ============================================
-- function test_reset()
--     log("测试 2.6 复位")

--     -- 结束状态 → 复位有效
--     device_state = STATE.FINISHED
--     log("结束状态点击复位 → 应进入准备状态")
--     device_state = STATE.READY

--     -- 非结束状态 → 复位无效
--     device_state = STATE.SHOOTING
--     log("开始投镖状态点击复位 → 无效")

--     device_state = STATE.READY
--     log("准备投镖状态点击复位 → 无效")
-- end


-- -- ============================================
-- -- 主入口
-- -- ============================================
-- function entry()

--     log("========= 靶区信息测试开始 =========")

--     test_hits()
--     -- test_finish()
--     -- test_reset()

--     log("========= 靶区信息测试结束 =========")

--     exit()
-- end

local STATE = {
    IDLE = 0,
    READY = 1,
    SHOOTING = 2,
    FINISHED = 3
}

local device_state = STATE.IDLE

local function log(msg)
    print("[TARGET-TEST] " .. msg)
end

-- 区域映射
local ZONE_MAP = {
    ["内牛眼"] = 0x01,
    ["外牛眼"] = 0x02,
    ["三倍区"] = 0x03,
    ["双倍区"] = 0x04,
    ["单倍区"] = 0x05
}

local function make_shoot_frame(params)
  local header = params.header or 0xAA55
  local flag   = params.flag or 0xBB
  local dart_type = params.type or 1
  local number = params.number or 10
  local tail   = params.tail or 0x55AA
  local check  = params.check or ((flag + dart_type + number) & 0xFF)

  return {
      header = header,
      data_flag = flag,
      type = dart_type,
      number = number,
      check = check,
      tail = tail
  }
end

-- =============================
-- 发送 target 协议（命中区域）
-- =============================
function send_target_hit(zone_str, force_bad_checksum)
  if device_state ~= STATE.SHOOTING then
      log("非开始投镖状态，不接收靶区信息：" .. device_state)
      return
  end

  local zone_code = ZONE_MAP[zone_str] or 0 -- 未知区域使用0
  local zone_code_num = 0
  if type(zone_code) == "number" then
      zone_code_num = zone_code
  else
      log("未知靶区，使用zone_code=0计算校验和")
  end

  local check = (0xCC + zone_code_num + 0x11) % 256

  if force_bad_checksum then
      check = (check + 1) % 256 -- 故意错误校验和
  end

  log("发送靶区信息：" .. tostring(zone_str) .. " (code=" .. string.format("0x%02X", zone_code_num) .. ")" ..
      (force_bad_checksum and " [校验和错误]" or ""))


  local frame = make_shoot_frame{type=2, number=9999}

  write_msg(channels.COM_shoot, protocols.shoot, frame)

  write_msg(channels.COM_target, protocols.target, {
      header = 0xAA55,
      data1 = 0xCC,
      zone = zone_code_num,
      data2 = 0x11,
      check = check,
      tail = 0x55AA
  })

  etimer.delay(500)
end


-- ============================================
-- 测试 2.4：靶区命中与计分（含非法/边界）
-- ============================================
function test_hits()
    log("开始测试 2.4 接收靶区信息并计分")

    device_state = STATE.SHOOTING

    local zones = {"内牛眼", "外牛眼", "三倍区", "双倍区", "单倍区"}
    -- local zones = {"内牛眼", "内牛眼", "内牛眼", "内牛眼", "内牛眼"}
    -- local zones = {"单倍区", "单倍区", "单倍区", "单倍区", "单倍区"}


    -- 正常情况
    for _, z in ipairs(zones) do
        send_target_hit(z)
    end

    -- 正常情况
    for _, z in ipairs(zones) do
      send_target_hit(z)
    end

    -- 非法靶区
    send_target_hit("未知区域")
    send_target_hit(0x99) -- 超范围代码

    -- 校验和错误
    send_target_hit("内牛眼", true)
end

-- ============================================
-- 测试 2.5 投镖结束（剩余飞镖=0 / 时间到 / 非开始状态）
-- ============================================
function test_finish()
    log("测试 2.5 投镖结束")

    -- 状态 SHOOTING，剩余飞镖=0
    device_state = STATE.SHOOTING
    log("模拟剩余飞镖=0，触发投镖结束")
    device_state = STATE.FINISHED
    etimer.delay(500)

    -- 状态 SHOOTING，定时器时间到
    device_state = STATE.SHOOTING
    log("模拟投镖时间到，触发投镖结束")
    device_state = STATE.FINISHED
    etimer.delay(500)

    -- 非开始投镖状态接收靶区数据
    device_state = STATE.READY
    send_target_hit("内牛眼")
    device_state = STATE.IDLE
    send_target_hit("外牛眼")
end

-- ============================================
-- 测试 2.6 复位逻辑（边界）
-- ============================================
function test_reset()
    log("测试 2.6 复位")

    -- 正确复位
    device_state = STATE.FINISHED
    log("结束状态点击复位 → 应进入准备状态")
    device_state = STATE.READY

    -- 非结束状态点击复位 → 无效
    device_state = STATE.SHOOTING
    log("开始投镖状态点击复位 → 无效")
    device_state = STATE.READY
    log("准备投镖状态点击复位 → 无效")
end

-- ============================================
-- 主入口
-- ============================================
function entry()
    log("========= 靶区信息测试开始 =========")

    test_hits()
    -- test_finish()
    -- test_reset()

    log("========= 靶区信息测试结束 =========")
    exit()
end
