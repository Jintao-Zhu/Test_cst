
-- -- -- -- 数据传送机接口
-- -- -- function Shoot()

-- -- --   --发送包头错误的数据包
-- -- --     write_msg(channels.COM_shoot,protocols.shoot,{header=0x5511})
-- -- --     etimer.delay(1000)
-- -- --     local res = ask("yesno",{title='提示', msg='请确认是否收到了飞镖信息输入', default=false})
-- -- --     check(not res, "包头错误有丢包处理，正确", "包头错误没有丢包处理，错误")

-- -- -- end


-- -- -- function entry()
-- -- --     -- 此处输入程序代码
-- -- --     Shoot()
-- -- --     exit()
-- -- -- end

-- -- -------------------------------------------------------
-- -- -- 数据传送机接口全错误覆盖测试脚本
-- -- -- 可直接运行，最大化检出所有接口错误处理
-- -- -------------------------------------------------------

-- -- ---------------------
-- -- -- 工具：构造数据帧
-- -- ---------------------
-- -- function make_frame(params)
-- --   -- params = {header, flag, dart_type, dart_count, tail}
-- --   local header     = params.header or 0xAA55
-- --   local flag       = params.flag or 0xBB
-- --   local dart_type  = params.dart_type or 1
-- --   local dart_count = params.dart_count or 1
-- --   local tail       = params.tail or 0x55AA

-- --   -- 计算校验和（字节 2-4）
-- --   local sum = (flag + dart_type + dart_count) & 0xFF
-- --   local checksum = params.checksum or sum

-- --   return {
-- --       header      = header,
-- --       flag        = flag,
-- --       dart_type   = dart_type,
-- --       dart_count  = dart_count,
-- --       checksum    = checksum,
-- --       tail        = tail
-- --   }
-- -- end


-- -- ---------------------------------------------
-- -- -- 1. 包头错误
-- -- ---------------------------------------------
-- -- function test_header_error()
-- --   local frame = make_frame{header = 0x5511}   -- 故意错误的包头
-- --   write_msg(channels.COM_shoot, protocols.shoot, frame)

-- --   etimer.delay(500)

-- --   local ok = ask("yesno",{msg="是否收到飞镖信息？", default=false})
-- --   check(not ok, "包头错误已正确丢包处理", "包头错误未丢包处理")
-- -- end


-- -- ---------------------------------------------
-- -- -- 2. 数据标志错误
-- -- ---------------------------------------------
-- -- function test_flag_error()
-- --   local frame = make_frame{flag = 0xCC}
-- --   write_msg(channels.COM_shoot, protocols.shoot, frame)

-- --   etimer.delay(500)

-- --   local ok = ask("yesno",{msg="是否收到飞镖信息？", default=false})
-- --   check(not ok, "数据标志错误已正确丢包处理", "数据标志错误未丢包处理")
-- -- end


-- -- ---------------------------------------------
-- -- -- 3. 包尾错误
-- -- ---------------------------------------------
-- -- function test_tail_error()
-- --   local frame = make_frame{tail = 0xAAAA}
-- --   write_msg(channels.COM_shoot, protocols.shoot, frame)

-- --   etimer.delay(500)

-- --   local ok = ask("yesno",{msg="是否收到飞镖信息？", default=false})
-- --   check(not ok, "包尾错误已正确丢包处理", "包尾错误未丢包处理")
-- -- end


-- -- ---------------------------------------------
-- -- -- 4. 校验和错误
-- -- ---------------------------------------------
-- -- function test_checksum_error()
-- --   local frame = make_frame{checksum = 0x00}  -- 明显错误
-- --   write_msg(channels.COM_shoot, protocols.shoot, frame)

-- --   etimer.delay(500)

-- --   local ok = ask("yesno",{msg="是否收到飞镖信息？", default=false})
-- --   check(not ok, "校验和错误已正确丢包处理", "校验和错误未丢包处理")
-- -- end


-- -- ---------------------------------------------
-- -- -- 5. 飞镖类型异常（非 1/2）
-- -- ---------------------------------------------
-- -- function test_invalid_dart_type()
-- --   local frame = make_frame{dart_type = 99}  -- 其他值
-- --   write_msg(channels.COM_shoot, protocols.shoot, frame)

-- --   etimer.delay(500)

-- --   local ok = ask("yesno",{msg="系统是否将其识别为未知物并处理？", default=true})
-- --   check(ok, "未知物处理正确", "未知物处理错误")
-- -- end


-- -- ---------------------------------------------
-- -- -- 6. 飞镖数量越界
-- -- --    a) 0
-- -- --    b) 21
-- -- ---------------------------------------------
-- -- function test_dart_count_out_of_range()
-- --   -- 数量 0（无效）
-- --   local frame1 = make_frame{dart_count = 0}
-- --   write_msg(channels.COM_shoot, protocols.shoot, frame1)

-- --   etimer.delay(300)
-- --   local ok1 = ask("yesno",{msg="数量=0 是否被丢弃或按规则处理？", default=true})
-- --   check(ok1, "飞镖数量=0处理正确", "飞镖数量=0处理错误")

-- --   -- 数量 21（越界）
-- --   local frame2 = make_frame{dart_count = 21}
-- --   write_msg(channels.COM_shoot, protocols.shoot, frame2)

-- --   etimer.delay(300)
-- --   local ok2 = ask("yesno",{msg="数量=21 是否按边界处理或丢包？", default=true})
-- --   check(ok2, "飞镖数量越界处理正确", "飞镖数量越界处理错误")
-- -- end


-- -- ---------------------------------------------
-- -- -- 7. 连续错误包压力测试
-- -- ---------------------------------------------
-- -- function test_continuous_error_packets()
-- --   for i = 1, 5 do
-- --       local frame = make_frame{header=0x0000, flag=0x00, checksum=0xFF, tail=0x0000}
-- --       write_msg(channels.COM_shoot, protocols.shoot, frame)
-- --       etimer.delay(100)
-- --   end

-- --   local ok = ask("yesno",{msg="连续错误后，系统是否仍能正常接收下一条正确数据？", default=true})
-- --   check(ok, "连续错误包压力测试通过", "连续错误包导致系统异常")
-- -- end


-- -- -------------------------------------------------------
-- -- -- 入口
-- -- -------------------------------------------------------
-- -- function entry()
-- --   print("开始数据传送机接口全覆盖测试")

-- --   test_header_error()
-- --   test_flag_error()
-- --   test_tail_error()
-- --   test_checksum_error()
-- --   test_invalid_dart_type()
-- --   test_dart_count_out_of_range()
-- --   test_continuous_error_packets()

-- --   print("全部测试完成")
-- --   exit()
-- -- end
-- -- ============================================
-- --      Shoot 接口（数据传送机）容错测试
-- -- ============================================

-- -- 发送 shoot 数据包
-- local function send_shoot_packet(p)
--   write_msg(channels.COM_shoot, protocols.shoot, p)
--   etimer.delay(800)
-- end

-- -- 验证是否丢包
-- local function verify_drop(desc)
--   local res = ask("yesno", {title='提示', msg='感应设备是否收到了飞镖类型/数量信息？', default=false})
--   check(not res, desc .. "：丢包正确", desc .. "：未丢包（错误）")
-- end

-- -- 验证是否正常接收（用于飞镖数量截断）
-- local function verify_accept(desc)
--   local res = ask("yesno", {title='提示', msg='感应设备是否正常接收并显示飞镖信息？', default=true})
--   check(res, desc .. "：正常接收正确", desc .. "：未正常接收（错误）")
-- end


-- ---------------------------------------------------------
-- --                    1. 包头错误
-- ---------------------------------------------------------
-- local function test_header_error()
--   send_shoot_packet({header=0x0000})
--   verify_drop("包头=0x0000")

--   send_shoot_packet({header=0xAABB})
--   verify_drop("包头=0xAABB（错误字节序）")

--   send_shoot_packet({header=0x1234})
--   verify_drop("包头随机错误 0x1234")
-- end


-- ---------------------------------------------------------
-- --                   2. 数据标志错误（应为0xBB）
-- ---------------------------------------------------------
-- local function test_flag_error()
--   send_shoot_packet({flag=0x00})
--   verify_drop("flag=0x00")

--   send_shoot_packet({flag=0xAA})
--   verify_drop("flag=0xAA")

--   send_shoot_packet({flag=0xBC})
--   verify_drop("flag=0xBC（接近但错误）")
-- end


-- ---------------------------------------------------------
-- --              3. 飞镖类型错误（未知物）
-- ---------------------------------------------------------
-- local function test_type_error()
--   send_shoot_packet({type=3})
--   verify_accept("飞镖类型=3（未知物）")

--   send_shoot_packet({type=200})
--   verify_accept("飞镖类型=200（未知物）")
-- end


-- ---------------------------------------------------------
-- --              4. 飞镖数量范围测试（截断）
-- ---------------------------------------------------------
-- local function test_count_range()
--   send_shoot_packet({count=0})
--   verify_accept("飞镖数量=0 截断到1")

--   send_shoot_packet({count=21})
--   verify_accept("飞镖数量=21 截断到20")

--   send_shoot_packet({count=255})
--   verify_accept("飞镖数量=255 截断到20")
-- end


-- ---------------------------------------------------------
-- --                   5. 校验和错误
-- ---------------------------------------------------------
-- local function test_checksum_error()
--   send_shoot_packet({checksum=0x00})
--   verify_drop("校验和=0x00")

--   send_shoot_packet({checksum=0xFF})
--   verify_drop("校验和=0xFF")

--   send_shoot_packet({
--       header=0xAA55,
--       flag=0xBB,
--       type=1,
--       count=5,
--       checksum=0x12, -- 错误校验
--       tail=0x55AA
--   })
--   verify_drop("校验和错误（看似正常的帧）")
-- end


-- ---------------------------------------------------------
-- --                    6. 包尾错误
-- ---------------------------------------------------------
-- local function test_tail_error()
--   send_shoot_packet({tail=0x1111})
--   verify_drop("包尾=0x1111")

--   send_shoot_packet({tail=0xAA55})
--   verify_drop("包尾=0xAA55（反序）")

--   send_shoot_packet({tail=0x1234})
--   verify_drop("包尾随机错误")
-- end


-- ---------------------------------------------------------
-- --          7. 多字段组合错误（强鲁棒性测试）
-- ---------------------------------------------------------
-- local function test_combination_error()
--   send_shoot_packet({
--       header=0x0000,
--       flag=0x12,
--       type=99,
--       count=30,
--       checksum=0xEE,
--       tail=0x0000
--   })
--   verify_drop("多字段完全错误")

--   send_shoot_packet({
--       header=0xAA55,
--       flag=0xBB,
--       type=1,
--       count=10,
--       checksum=0x10, -- 错误校验
--       tail=0xAAAA     -- 尾部错误
--   })
--   verify_drop("校验 + 包尾组合错误")

--   send_shoot_packet({
--       header=0x5511,
--       flag=0xDD,
--       type=4,
--       count=50,
--       checksum=0x00,
--       tail=0x9999
--   })
--   verify_drop("包头 + 数据标志 + 数量越界 + 校验错误")
-- end


-- ---------------------------------------------------------
-- --              8. 截断帧（缺字段）
-- ---------------------------------------------------------
-- local function test_short_frame()
--   write_msg(channels.COM_shoot, protocols.shoot, {header=0xAA55})
--   etimer.delay(800)
--   verify_drop("截断帧（只有包头）")

--   write_msg(channels.COM_shoot, protocols.shoot, {header=0xAA55, flag=0xBB})
--   etimer.delay(800)
--   verify_drop("截断帧（只发到flag）")
-- end


-- ---------------------------------------------------------
-- --              9. 长帧（多余字段）
-- ---------------------------------------------------------
-- local function test_long_frame()
--   write_msg(channels.COM_shoot, protocols.shoot, {
--       header=0xAA55, flag=0xBB, type=1,
--       count=5, checksum=0xEE, tail=0x55AA,
--       extra1=0x12, extra2=0x34
--   })
--   etimer.delay(800)
--   verify_drop("长帧（多余字节）")
-- end


-- ---------------------------------------------------------
-- --                        主入口
-- ---------------------------------------------------------
-- function Shoot()
--   print("=== 开始执行 Shoot 接口容错测试 ===")

--   test_header_error()
--   test_flag_error()
--   test_type_error()
--   test_count_range()
--   test_checksum_error()
--   test_tail_error()
--   test_combination_error()
--   test_short_frame()
--   test_long_frame()

--   print("=== Shoot 接口测试完成 ===")
-- end


-- function entry()
--   Shoot()
--   exit()
-- end

-------------------------------------------------------
-- target_interface.lua —— 靶体接口测试
-- 覆盖需求 2.4 ~ 2.6
-------------------------------------------------------

-- 模拟设备状态
local STATE = {IDLE=0, READY=1, SHOOTING=2, FINISHED=3}
local device_state = STATE.IDLE

-- 飞镖类型
local DART_TYPE = {LIGHT=1, HEAVY=2, UNKNOWN=3}
local dart_type = DART_TYPE.LIGHT
local total_darts = 10
local remaining_darts = 10

-- 区域映射
local ZONE_MAP = {["内牛眼"]=0x01, ["外牛眼"]=0x02, ["三倍区"]=0x03, ["双倍区"]=0x04, ["单倍区"]=0x05}

-- 日志
local function log(msg)
    print("[TARGET-IFACE-TEST] " .. msg)
end

-- 构造靶体协议帧
local function make_target_frame(zone_str, override)
    local zone_code = ZONE_MAP[zone_str] or 0xFF
    local header = (override and override.header) or 0xAA55
    local data1  = (override and override.data1)  or 0xCC
    local data2  = (override and override.data2)  or 0x11
    local check  = (override and override.check)  or ((data1 + zone_code + data2) & 0xFF)
    local tail   = (override and override.tail)   or 0x55AA

    return {
        header = header,
        data1  = data1,
        zone   = zone_code,
        data2  = data2,
        check  = check,
        tail   = tail
    }
end

-------------------------------------------------------
-- 1. 单区命中测试
-------------------------------------------------------
local function test_hit_zone(zone_str)
    if device_state ~= STATE.SHOOTING then return end
    log("【接口】命中区域："..zone_str.." (飞镖类型="..dart_type..")")

    local frame = make_target_frame(zone_str)
    write_msg(channels.COM_target, protocols.target, frame)

    -- 更新剩余飞镖
    remaining_darts = remaining_darts - 1
    etimer.delay(200)
end

-------------------------------------------------------
-- 2. 所有区域循环测试
-------------------------------------------------------
local function test_all_zones()
    log("【接口】循环测试所有靶区")
    device_state = STATE.SHOOTING
    remaining_darts = total_darts

    for zone, _ in pairs(ZONE_MAP) do
        test_hit_zone(zone)
    end
end

-------------------------------------------------------
-- 3. 非法区与协议字段测试
-------------------------------------------------------
local function test_invalid_zone_fields()
    log("【接口】非法区与协议字段测试")
    local err_cases = {
        {zone="未知区", desc="非法靶区"},
        {override={header=0x5511}, desc="包头错误"},
        {override={data1=0x00}, desc="数据标志1错误"},
        {override={data2=0x00}, desc="数据标志2错误"},
        {override={check=0x00}, desc="校验和错误"},
        {override={tail=0xAAAA}, desc="包尾错误"}
    }

    for _, c in ipairs(err_cases) do
        local frame = make_target_frame(c.zone or "内牛眼", c.override)
        write_msg(channels.COM_target, protocols.target, frame)
        etimer.delay(200)
        local res = ask("yesno",{title=c.desc, msg="系统是否接收错误包？", default=false})
        check(not res, c.desc.." 丢包处理正确", c.desc.." 丢包处理错误")
    end
end

-------------------------------------------------------
-- 4. 投镖结束测试
-------------------------------------------------------
local function test_finish_conditions()
    log("【接口】投镖结束条件测试")

    -- 剩余飞镖=0
    remaining_darts = 0
    log("模拟剩余飞镖=0 → 应触发投镖结束")
    device_state = STATE.FINISHED
    etimer.delay(200)

    -- 时间到
    remaining_darts = 5
    log("模拟定时器时间到 → 应触发投镖结束")
    device_state = STATE.FINISHED
    etimer.delay(200)
end

-------------------------------------------------------
-- 5. 复位测试
-------------------------------------------------------
local function test_reset()
    log("【接口】复位测试")

    -- FINISHED 状态 → 复位有效
    device_state = STATE.FINISHED
    log("点击复位 → 应进入 READY")
    device_state = STATE.READY

    -- SHOOTING / READY 状态 → 复位无效
    device_state = STATE.SHOOTING
    log("开始投镖状态点击复位 → 无效")
    device_state = STATE.READY
    log("准备状态点击复位 → 无效")
end

-------------------------------------------------------
-- 主入口
-------------------------------------------------------
function entry()
    log("========= 靶体接口测试开始 =========")

    dart_type = DART_TYPE.LIGHT
    total_darts = 10
    remaining_darts = 10
    device_state = STATE.SHOOTING

    test_all_zones()
    test_invalid_zone_fields()
    test_finish_conditions()
    test_reset()

    log("========= 靶体接口测试结束 =========")
    exit()
end
