
-- -- -- -- 靶体接口
-- -- -- function Target()

-- -- --   --发送包头错误的数据包
-- -- --   write_msg(channels.COM_target,protocols.target,{header=0x5511})
-- -- --     etimer.delay(1000)
-- -- --     local res = ask("yesno",{title='提示', msg='请确认是否收到了靶体信息', default=false})
-- -- --     check(not res, "包头错误有丢包处理，正确", "包头错误没有丢包处理，错误")

-- -- -- end


-- -- -- function entry()
-- -- --     -- 此处输入程序代码
-- -- --     Target()
-- -- --     exit()
-- -- -- end

-- -- -- -------------------------------------------------------
-- -- -- -- 靶体接口（Target）完整错误注入测试脚本
-- -- -- -- 覆盖所有容错规则，可直接运行
-- -- -- -------------------------------------------------------

-- -- -- --------------------------
-- -- -- -- 工具：构造靶区数据帧
-- -- -- --------------------------
-- -- -- function make_target_frame(params)
-- -- --   local header   = params.header or 0xAA55
-- -- --   local flag1    = params.flag1 or 0xCC
-- -- --   local zone     = params.zone or 1     -- 区域 1~5
-- -- --   local flag2    = params.flag2 or 0x11
-- -- --   local tail     = params.tail or 0x55AA

-- -- --   -- 正常校验和
-- -- --   local sum = (flag1 + zone + flag2) & 0xFF
-- -- --   local checksum = params.checksum or sum

-- -- --   return {
-- -- --       header  = header,
-- -- --       data1   = flag1,
-- -- --       zone    = zone,
-- -- --       data2   = flag2,
-- -- --       check   = checksum,
-- -- --       tail    = tail
-- -- --   }
-- -- -- end


-- -- -- -------------------------------------------------------
-- -- -- -- 1. 包头错误
-- -- -- -------------------------------------------------------
-- -- -- function test_header_error()
-- -- --   local frame = make_target_frame{header = 0x5511}
-- -- --   write_msg(channels.COM_target, protocols.target, frame)

-- -- --   etimer.delay(500)
-- -- --   local ok = ask("yesno",{msg="是否收到靶体信息？", default=false})
-- -- --   check(not ok, "包头错误已丢包处理（正确）", "包头错误未丢包处理（错误）")
-- -- -- end


-- -- -- -------------------------------------------------------
-- -- -- -- 2. 数据标志1（0xCC）错误
-- -- -- -------------------------------------------------------
-- -- -- function test_flag1_error()
-- -- --   local frame = make_target_frame{flag1 = 0xDD}
-- -- --   write_msg(channels.COM_target, protocols.target, frame)

-- -- --   etimer.delay(500)
-- -- --   local ok = ask("yesno",{msg="是否收到靶体信息？", default=false})
-- -- --   check(not ok, "数据标志1错误已丢包处理（正确）", "数据标志1错误未丢包（错误）")
-- -- -- end


-- -- -- -------------------------------------------------------
-- -- -- -- 3. 投中区域错误（合法区间 1~5）
-- -- -- -------------------------------------------------------
-- -- -- function test_zone_error()
-- -- --   local frame = make_target_frame{zone = 99}
-- -- --   write_msg(channels.COM_target, protocols.target, frame)

-- -- --   etimer.delay(500)
-- -- --   local ok = ask("yesno",{msg="系统是否拒收非法区域？", default=false})
-- -- --   check(not ok, "非法区域已丢包处理（正确）", "非法区域未丢包（错误）")
-- -- -- end


-- -- -- -------------------------------------------------------
-- -- -- -- 4. 数据标志2（0x11）错误
-- -- -- -------------------------------------------------------
-- -- -- function test_flag2_error()
-- -- --   local frame = make_target_frame{flag2 = 0x22}
-- -- --   write_msg(channels.COM_target, protocols.target, frame)

-- -- --   etimer.delay(500)
-- -- --   local ok = ask("yesno",{msg="是否收到靶体信息？", default=false})
-- -- --   check(not ok, "数据标志2错误已丢包处理（正确）", "数据标志2错误未丢包（错误）")
-- -- -- end


-- -- -- -------------------------------------------------------
-- -- -- -- 5. 校验和错误
-- -- -- -------------------------------------------------------
-- -- -- function test_checksum_error()
-- -- --   local frame = make_target_frame{checksum = 0x00} -- 明显错误
-- -- --   write_msg(channels.COM_target, protocols.target, frame)

-- -- --   etimer.delay(500)
-- -- --   local ok = ask("yesno",{msg="是否收到靶体信息？", default=false})
-- -- --   check(not ok, "校验错误已丢包处理（正确）", "校验错误未丢包（错误）")
-- -- -- end


-- -- -- -------------------------------------------------------
-- -- -- -- 6. 包尾错误
-- -- -- -------------------------------------------------------
-- -- -- function test_tail_error()
-- -- --   local frame = make_target_frame{tail = 0xAAAA}
-- -- --   write_msg(channels.COM_target, protocols.target, frame)

-- -- --   etimer.delay(500)
-- -- --   local ok = ask("yesno",{msg="是否收到靶体信息？", default=false})
-- -- --   check(not ok, "包尾错误已丢包（正确）", "包尾错误未丢包（错误）")
-- -- -- end


-- -- -- -------------------------------------------------------
-- -- -- -- 7. 连续错误包压力测试
-- -- -- -------------------------------------------------------
-- -- -- function test_continuous_error_packets()
-- -- --   for i = 1, 5 do
-- -- --       local frame = make_target_frame{
-- -- --           header = 0x0000,
-- -- --           flag1  = 0x00,
-- -- --           zone   = 0xFF,
-- -- --           flag2  = 0x00,
-- -- --           checksum = 0xFF,
-- -- --           tail = 0x0000
-- -- --       }
-- -- --       write_msg(channels.COM_target, protocols.target, frame)
-- -- --       etimer.delay(100)
-- -- --   end

-- -- --   local ok = ask("yesno",{msg="连续错误后，系统是否还能正常接收下一条正确数据？", default=true})
-- -- --   check(ok, "连续错误包压力测试通过", "连续错误包导致系统异常（错误）")
-- -- -- end


-- -- -- -------------------------------------------------------
-- -- -- -- 入口函数
-- -- -- -------------------------------------------------------
-- -- -- function Target()
-- -- --   print("===== 开始靶体接口错误覆盖测试 =====")

-- -- --   test_header_error()
-- -- --   test_flag1_error()
-- -- --   test_zone_error()
-- -- --   test_flag2_error()
-- -- --   test_checksum_error()
-- -- --   test_tail_error()
-- -- --   test_continuous_error_packets()

-- -- --   print("===== 靶体接口错误测试完成 =====")
-- -- -- end


-- -- -- function entry()
-- -- --   Target()
-- -- --   exit()
-- -- -- end


-- -- -- target 接口容错覆盖测试
-- -- -- 注意：完全按照你给的“服务器接口容错测试”模式编写
-- -- -- ============================================
-- -- --      靶体接口（target）容错全面测试
-- -- -- ============================================

-- -- -- 发送靶体数据包的辅助函数
-- -- local function send_target_packet(p)
-- --   write_msg(channels.COM_target, protocols.target, p)
-- --   etimer.delay(800)
-- -- end

-- -- -- 验证是否丢包的统一函数
-- -- local function verify_drop(desc)
-- --   local res = ask("yesno", {title='提示', msg='请确认是否收到了靶体信息', default=false})
-- --   check(not res, desc .. "：正确丢包", desc .. "：未丢包（错误）")
-- -- end


-- -- --------------------------------------------------------------------
-- -- --                       1. 包头错误测试
-- -- --------------------------------------------------------------------
-- -- local function test_header_error()
-- --   -- 错误包头：全错
-- --   send_target_packet({header=0x0000})
-- --   verify_drop("包头=0x0000（无效）")

-- --   -- 错误包头：部分错误
-- --   send_target_packet({header=0xAABB})
-- --   verify_drop("包头=0xAABB（部分错误）")

-- --   -- 随机错误
-- --   send_target_packet({header=0x1234})
-- --   verify_drop("包头=0x1234（随机错误）")
-- -- end

-- -- --------------------------------------------------------------------
-- -- --                     2. 数据标志1错误 (应为0xCC)
-- -- --------------------------------------------------------------------
-- -- local function test_flag1_error()
-- --   send_target_packet({flag1=0x00})
-- --   verify_drop("flag1=0x00（错误）")

-- --   send_target_packet({flag1=0xAB})
-- --   verify_drop("flag1=0xAB（错误）")

-- --   send_target_packet({flag1=0xCD})
-- --   verify_drop("flag1=0xCD（看似接近但错误）")
-- -- end


-- -- --------------------------------------------------------------------
-- -- --                     3. 投中区域字段错误 (应为1~5)
-- -- --------------------------------------------------------------------
-- -- local function test_area_error()
-- --   send_target_packet({area=0})
-- --   verify_drop("投中区域=0（下界越界）")

-- --   send_target_packet({area=6})
-- --   verify_drop("投中区域=6（上界越界）")

-- --   send_target_packet({area=255})
-- --   verify_drop("投中区域=255（随机无意义）")
-- -- end


-- -- --------------------------------------------------------------------
-- -- --                    4. 数据标志2错误（应为 0x11）
-- -- --------------------------------------------------------------------
-- -- local function test_flag2_error()
-- --   send_target_packet({flag2=0x00})
-- --   verify_drop("flag2=0x00（错误）")

-- --   send_target_packet({flag2=0x22})
-- --   verify_drop("flag2=0x22（错误）")

-- --   send_target_packet({flag2=0x10})
-- --   verify_drop("flag2=0x10（相近但错误）")
-- -- end


-- -- --------------------------------------------------------------------
-- -- --                       5. 校验和错误
-- -- --------------------------------------------------------------------
-- -- local function test_checksum_error()
-- --   send_target_packet({checksum=0x00})
-- --   verify_drop("checksum=0x00（明显错误）")

-- --   send_target_packet({checksum=0xFF})
-- --   verify_drop("checksum=0xFF（随机错误）")

-- --   -- 正常帧但故意破坏校验 = 数据字段正确但 checksum 不对
-- --   send_target_packet({
-- --       header=0xAA55,
-- --       flag1=0xCC,
-- --       area=3,
-- --       flag2=0x11,
-- --       checksum=0x12,  -- 错误校验和
-- --       tail=0x55AA
-- --   })
-- --   verify_drop("校验和错误（帧看似正常）")
-- -- end


-- -- --------------------------------------------------------------------
-- -- --                       6. 包尾错误 (应为0x55AA)
-- -- --------------------------------------------------------------------
-- -- local function test_tail_error()
-- --   send_target_packet({tail=0x0000})
-- --   verify_drop("包尾=0x0000（错误）")

-- --   send_target_packet({tail=0xAA55})
-- --   verify_drop("包尾=0xAA55（顺序反了）")

-- --   send_target_packet({tail=0x1234})
-- --   verify_drop("包尾=0x1234（随机错误）")
-- -- end


-- -- --------------------------------------------------------------------
-- -- --       7. 组合错误：多个字段一起错（非常重要）
-- -- --------------------------------------------------------------------
-- -- local function test_combination_errors()
-- --   send_target_packet({
-- --       header=0x1111,
-- --       flag1=0x00,
-- --       area=9,
-- --       flag2=0x33,
-- --       checksum=0xFF,
-- --       tail=0x2222
-- --   })
-- --   verify_drop("多字段全部错误")

-- --   send_target_packet({
-- --       header=0xAA55,
-- --       flag1=0xCC,
-- --       area=1,
-- --       flag2=0x11,
-- --       checksum=0x10, -- 错误校验
-- --       tail=0x2222    -- 错误尾部
-- --   })
-- --   verify_drop("校验 + 包尾错误组合")

-- --   send_target_packet({
-- --       header=0x1234,
-- --       flag1=0xCC,
-- --       area=7,
-- --       flag2=0x11,
-- --       checksum=0x00,
-- --       tail=0x55AA
-- --   })
-- --   verify_drop("包头错误 + 区域错误 + 校验错误")
-- -- end


-- -- --------------------------------------------------------------------
-- -- --        8. 长度不正确测试：截断帧（缺字段）
-- -- --------------------------------------------------------------------
-- -- local function test_short_frame()
-- --   write_msg(channels.COM_target, protocols.target, { header=0xAA55 }) -- 只有包头
-- --   etimer.delay(800)
-- --   verify_drop("截断帧（只有包头）")

-- --   write_msg(channels.COM_target, protocols.target, { header=0xAA55, flag1=0xCC })
-- --   etimer.delay(800)
-- --   verify_drop("截断帧（缺少剩余字段）")
-- -- end


-- -- --------------------------------------------------------------------
-- -- --       9. 长度过长：多余字节模拟噪声
-- -- --------------------------------------------------------------------
-- -- local function test_long_frame()
-- --   write_msg(channels.COM_target, protocols.target, {
-- --       header=0xAA55, flag1=0xCC, area=2,
-- --       flag2=0x11, checksum=0xEE, tail=0x55AA,
-- --       extra1=0x12, extra2=0x34
-- --   })
-- --   etimer.delay(800)
-- --   verify_drop("多余字节（长度过长）")
-- -- end



-- -- --------------------------------------------------------------------
-- -- --                      主入口：全部测试
-- -- --------------------------------------------------------------------
-- -- function Target()
-- --   print("=== 开始执行 target 接口容错测试 ===")

-- --   test_header_error()
-- --   test_flag1_error()
-- --   test_area_error()
-- --   test_flag2_error()
-- --   test_checksum_error()
-- --   test_tail_error()
-- --   test_combination_errors()
-- --   test_short_frame()
-- --   test_long_frame()

-- --   print("=== target 接口容错测试完成 ===")
-- -- end


-- -- function entry()
-- --   Target()
-- --   exit()
-- -- end


-- -------------------------------------------------------
-- -- shoot_target.lua —— 投镖 + 靶区功能全覆盖测试
-- -- 覆盖需求 2.1 ~ 2.6，包括轻重镖区分、评分、复位、容错
-- -------------------------------------------------------

-- -- 模拟设备状态
-- local STATE = {IDLE=0, READY=1, SHOOTING=2, FINISHED=3}
-- local device_state = STATE.IDLE

-- -- 日志函数
-- local function log(msg) print("[SHOOT-TARGET] "..msg) end

-- -- 飞镖类型
-- local TYPE_MAP = {["轻镖"]=1, ["重镖"]=2, ["未知物"]=3}
-- local current_dart_type = TYPE_MAP["轻镖"]
-- local total_darts = 10
-- local remaining_darts = 10
-- local total_score = 0

-- -- 靶区映射
-- local ZONE_MAP = {["内牛眼"]=1, ["外牛眼"]=2, ["三倍区"]=3, ["双倍区"]=4, ["单倍区"]=5}
-- local zone_count = {0,0,0,0,0} -- 各区计数

-- -- 计算每颗飞镖得分
-- local function calc_score(dart_type, zone)
--     if dart_type == TYPE_MAP["轻镖"] then
--         if zone==1 then return 5 elseif zone==2 then return 3
--         elseif zone==3 then return 1 elseif zone==4 then return 0
--         else return math.max(-2, 0) end
--     elseif dart_type == TYPE_MAP["重镖"] then
--         if zone==1 then return 4 elseif zone==2 then return 3
--         elseif zone==3 then return 2 elseif zone==4 then return 1
--         else return 0 end
--     else
--         return 0 -- 未知物
--     end
-- end

-- -- ============================================
-- -- 构造 shoot 协议数据帧
-- -- ============================================
-- local function make_shoot_frame(params)
--     local header = params.header or 0xAA55
--     local flag   = params.flag or 0xBB
--     local dart_type = params.type or TYPE_MAP["轻镖"]
--     local number = params.number or 10
--     local tail   = params.tail or 0x55AA
--     local check  = params.check or ((flag + dart_type + number) & 0xFF)
--     return {header=header, data_flag=flag, type=dart_type, number=number, check=check, tail=tail}
-- end

-- -- ============================================
-- -- 构造 target 协议数据帧
-- -- ============================================
-- local function make_target_frame(params)
--     local header = params.header or 0xAA55
--     local data1  = params.data1 or 0xCC
--     local zone   = params.zone or 1
--     local data2  = params.data2 or 0x11
--     local check  = params.check or ((data1 + zone + data2) & 0xFF)
--     local tail   = params.tail or 0x55AA
--     return {header=header, data1=data1, zone=zone, data2=data2, check=check, tail=tail}
-- end

-- -- ============================================
-- -- 发送靶区信息
-- -- ============================================
-- local function send_target_hit(zone_str)
--     if device_state ~= STATE.SHOOTING then
--         log("非投镖状态，不接收靶区信息")
--         return
--     end
--     local zone = ZONE_MAP[zone_str]
--     if not zone then
--         log("未知靶区："..tostring(zone_str))
--         return
--     end
--     local frame = make_target_frame{zone=zone}
--     write_msg(channels.COM_target, protocols.target, frame)
--     etimer.delay(200)
--     -- 更新内部状态
--     remaining_darts = remaining_darts - 1
--     zone_count[zone] = zone_count[zone] + 1
--     local score = calc_score(current_dart_type, zone)
--     total_score = total_score + score
--     log(string.format("投中%s，得分=%d，总分=%d，剩余飞镖=%d", zone_str, score, total_score, remaining_darts))
-- end

-- -- ============================================
-- -- 2.4 测试：靶区命中与计分
-- -- ============================================
-- local function test_hits()
--     log("=== 2.4 靶区命中及计分测试 ===")
--     device_state = STATE.SHOOTING
--     current_dart_type = TYPE_MAP["轻镖"]
--     remaining_darts = 5
--     total_score = 0
--     zone_count = {0,0,0,0,0}

--     local zones = {"内牛眼","外牛眼","三倍区","双倍区","单倍区"}
--     for _, z in ipairs(zones) do
--         send_target_hit(z)
--     end

--     current_dart_type = TYPE_MAP["重镖"]
--     remaining_darts = 5
--     total_score = 0
--     zone_count = {0,0,0,0,0}
--     for _, z in ipairs(zones) do
--         send_target_hit(z)
--     end
-- end

-- -- ============================================
-- -- 2.5 测试：投镖结束
-- -- ============================================
-- local function test_finish()
--     log("=== 2.5 投镖结束测试 ===")
--     device_state = STATE.SHOOTING
--     remaining_darts = 0
--     log("模拟剩余飞镖=0，触发投镖结束")
--     device_state = STATE.FINISHED
--     etimer.delay(500)

--     device_state = STATE.SHOOTING
--     remaining_darts = 3
--     log("模拟定时器到，触发投镖结束")
--     device_state = STATE.FINISHED
-- end

-- -- ============================================
-- -- 2.6 测试：复位
-- -- ============================================
-- local function test_reset()
--     log("=== 2.6 复位测试 ===")
--     -- FINISHED 状态 → 有效
--     device_state = STATE.FINISHED
--     log("结束状态点击复位 → 准备状态")
--     device_state = STATE.READY
--     remaining_darts = total_darts
--     total_score = 0
--     zone_count = {0,0,0,0,0}

--     -- 非结束状态 → 无效
--     device_state = STATE.SHOOTING
--     log("投镖中点击复位 → 无效")
--     device_state = STATE.READY
--     log("准备状态点击复位 → 无效")
-- end

-- -- ============================================
-- -- 2.4~2.6 非法/边界测试
-- -- ============================================
-- local function test_illegal()
--     log("=== 非法/边界验证 ===")
--     -- 未知靶区
--     send_target_hit("外太空")
--     -- 飞镖数量非法
--     local frame = make_shoot_frame{number=0}
--     write_msg(channels.COM_shoot, protocols.shoot, frame)
--     -- 飞镖类型非法
--     frame = make_shoot_frame{type=0x99}
--     write_msg(channels.COM_shoot, protocols.shoot, frame)
--     -- 协议异常字段
--     frame = make_target_frame{header=0x1234}
--     write_msg(channels.COM_target, protocols.target, frame)
-- end

-- -- ============================================
-- -- 主入口
-- -- ============================================
-- function entry()
--     log("========= 投镖+靶区全覆盖测试开始 =========")

--     test_hits()
--     test_finish()
--     test_reset()
--     test_illegal()

--     log("========= 测试结束 =========")
--     exit()
-- end


-------------------------------------------------------
-- shoot_interface.lua —— 数据传输机接口测试
-- 覆盖需求 2.1 / 2.2 / 2.3（飞镖输入 + 投镖开始）
-------------------------------------------------------

-- 模拟设备状态
local STATE = {IDLE=0, READY=1, SHOOTING=2, FINISHED=3}
local device_state = STATE.IDLE

-- 飞镖类型映射
local TYPE_MAP = {["轻镖"]=1, ["重镖"]=2, ["未知物"]=3}

-- 飞镖数量
local total_darts = 0
local remaining_darts = 0
local dart_type = TYPE_MAP["轻镖"]

-- 日志函数
local function log(msg)
    print("[SHOOT-IFACE-TEST] " .. msg)
end

-- 构造协议帧
local function make_shoot_frame(params)
    local header = params.header or 0xAA55
    local flag   = params.flag or 0xBB
    local type_code = params.type or TYPE_MAP["轻镖"]
    local number = params.number or 10
    local tail   = params.tail or 0x55AA
    local check  = params.check or ((flag + type_code + number) & 0xFF)

    return {
        header = header,
        data_flag = flag,
        type = type_code,
        number = number,
        check = check,
        tail = tail
    }
end

-------------------------------------------------------
-- 1. 默认值测试
-------------------------------------------------------
local function test_default()
    log("【接口】测试 2.1 默认值：轻镖 10 枚")
    device_state = STATE.READY
    total_darts = 10
    remaining_darts = 10
    dart_type = TYPE_MAP["轻镖"]

    local frame = make_shoot_frame{type=dart_type, number=10}
    write_msg(channels.COM_shoot, protocols.shoot, frame)
end

-------------------------------------------------------
-- 2. 飞镖合法输入测试
-------------------------------------------------------
local function test_valid_input(type_str, num)
    device_state = STATE.READY
    dart_type = TYPE_MAP[type_str]
    total_darts = num
    remaining_darts = num

    log(string.format("【接口】合法输入：%s %d 枚", type_str, num))
    local frame = make_shoot_frame{type=dart_type, number=num}
    write_msg(channels.COM_shoot, protocols.shoot, frame)
end

-------------------------------------------------------
-- 3. 飞镖边界输入测试
-------------------------------------------------------
local function test_boundary_input()
    local cases = {
        {"轻镖", 0}, {"轻镖", 1}, {"轻镖", 20}, {"轻镖", 25},
        {"重镖", 0}, {"重镖", 1}, {"重镖", 20}, {"重镖", 25},
        {"未知物", 1}, {"未知物", 20}
    }
    for _, c in ipairs(cases) do
        test_valid_input(c[1], c[2])
        etimer.delay(200)
    end
end

-------------------------------------------------------
-- 4. 非法飞镖类型测试
-------------------------------------------------------
local function test_invalid_type()
    device_state = STATE.READY
    log("【接口】非法飞镖类型测试")
    local frame = make_shoot_frame{type=0x99, number=5}
    write_msg(channels.COM_shoot, protocols.shoot, frame)
    local res = ask("yesno",{title='非法类型', msg="是否接收错误包？", default=false})
    check(not res, "非法类型丢包处理正确", "非法类型丢包处理错误")
end

-------------------------------------------------------
-- 5. 非 READY 状态发送测试
-------------------------------------------------------
local function test_invalid_state()
    device_state = STATE.IDLE
    log("【接口】IDLE 状态发送 → 应忽略")
    local frame = make_shoot_frame{type=TYPE_MAP["轻镖"], number=5}
    write_msg(channels.COM_shoot, protocols.shoot, frame)

    device_state = STATE.SHOOTING
    log("【接口】SHOOTING 状态发送 → 应忽略")
    write_msg(channels.COM_shoot, protocols.shoot, frame)
end

-------------------------------------------------------
-- 6. 协议异常字段测试
-------------------------------------------------------
local function test_protocol_errors()
    log("【接口】协议异常字段测试")
    local err_cases = {
        {header=0x5511, desc="包头错误"},
        {flag=0x00, desc="数据标志错误"},
        {type=0xFF, desc="飞镖类型非法"},
        {number=0xFF, desc="数量超范围"},
        {check=0x00, desc="校验和错误"},
        {tail=0xAAAA, desc="包尾错误"}
    }

    for _, c in ipairs(err_cases) do
        local frame = make_shoot_frame(c)
        write_msg(channels.COM_shoot, protocols.shoot, frame)
        etimer.delay(200)
        local res = ask("yesno",{title=c.desc, msg="系统是否接收错误包？", default=false})
        check(not res, c.desc.." 丢包处理正确", c.desc.." 丢包处理错误")
    end
end

-------------------------------------------------------
-- 主入口
-------------------------------------------------------
function entry()
    log("========= 数据传输接口测试开始 =========")

    test_default()
    test_boundary_input()
    test_invalid_type()
    test_invalid_state()
    test_protocol_errors()

    log("========= 数据传输接口测试结束 =========")
    exit()
end
