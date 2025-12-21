-- -- ===========================================
-- --  shoot.lua —— 飞镖输入 + 测试覆盖脚本
-- --  覆盖需求 2.1 / 2.2 / 2.3
-- -- ===========================================

-- -- 模拟设备内部状态
-- local STATE = {
--   IDLE = 0,
--   READY = 1,        -- 准备投镖
--   SHOOTING = 2      -- 开始投镖
-- }

-- local device_state = STATE.IDLE

-- local function log(msg)
--   print("[SHOOT-TEST] " .. msg)
-- end

-- -- ===============================
-- -- 飞镖类型映射（根据要求自定义）
-- -- ===============================
-- local TYPE_MAP = {
--   ["轻镖"] = 0x01,
--   ["重镖"] = 0x02,
--   ["未知物"] = 0x03
-- }

-- -- ===========================================
-- -- 发送 shoot 协议（shoot.prot）
-- -- ===========================================
-- function send_shoot_info(type_str, num)

--   if device_state ~= STATE.READY then
--       log("非准备状态，不接收飞镖信息：" .. device_state)
--       return
--   end

--   -- 类型转换
--   local type_code = TYPE_MAP[type_str]
--   if not type_code then
--       log("无效飞镖类型：" .. tostring(type_str))
--       return
--   end

--   -- 数量截断到 [1,20]
--   if num > 20 then 
--       log("数量 " .. num .. " 超过20，被截断为20")
--       num = 20 
--   end
--   if num < 1 then 
--       log("数量 " .. num .. " 小于1，被设置为1")
--       num = 1
--   end

--   -- 校验和：Checkcode(SUM8, data, number)
--   local check = (0xBB + num) % 256

--   log("发送飞镖信息：类型=" .. type_str .. " 数量=" .. num)

--   write_msg(channels.COM_shoot, protocols.shoot, {
--       header = 0xAA55,
--       data = 0xBB,
--       type = type_code,
--       number = num,
--       check = check,
--       tail = 0x55AA
--   })

--   etimer.delay(500)
-- end

-- -- ===============================
-- -- 状态切换
-- -- ===============================
-- function set_state(s)
--   device_state = s
--   log("设备状态切换为：" .. s)
--   etimer.delay(300)
-- end


-- -- ===========================================
-- -- 测试 2.1 默认值（轻镖 + 10）
-- -- ===========================================
-- function test_default()
--   log("测试 2.1 系统启动默认值")

--   set_state(STATE.READY)
--   send_shoot_info("轻镖", 10)
-- end


-- -- ===========================================
-- -- 测试 2.2 飞镖信息输入（合法、边界、截断）
-- -- ===========================================
-- function test_input()
--   log("测试 2.2 飞镖信息输入")

--   set_state(STATE.READY)

--   -- 合法输入
--   local cases = {
--       {"轻镖", 1}, {"轻镖", 10}, {"轻镖", 11}, {"轻镖", 20},
--       {"重镖", 5}, {"重镖", 15},
--       {"未知物", 3}, {"未知物", 18},
--   }

--   for _, c in ipairs(cases) do
--       send_shoot_info(c[1], c[2])
--   end

--   -- 截断测试
--   log("测试数量 25（应截断为 20）")
--   send_shoot_info("轻镖", 25)

--   -- 非准备状态输入
--   set_state(STATE.IDLE)
--   send_shoot_info("重镖", 6)

--   -- 恢复 READY
--   set_state(STATE.READY)
-- end


-- -- ===========================================
-- -- 2.3 开始投镖（注意：无协议，仅状态模拟）
-- -- ===========================================
-- function test_start_shoot()
--   log("测试 2.3 开始投镖")

--   -- 输入有效信息
--   set_state(STATE.READY)
--   send_shoot_info("轻镖", 10)

--   -- 开始投镖（UI行为，无协议）
--   log("准备状态 → 用户点击开始（模拟）")
--   set_state(STATE.SHOOTING)

--   -- 非准备状态点击开始（不应生效）
--   log("非准备状态点击开始（应无效）")
--   set_state(STATE.IDLE)
-- end


-- -- ===========================================
-- -- 主入口
-- -- ===========================================
-- function entry()
--   log("========= 飞镖投标自动化测试开始 =========")

--   test_default()
--   test_input()
--   test_start_shoot()

--   log("========= 飞镖投标自动化测试结束 =========")

--   exit()
-- end

-------------------------------------------------------
-- shoot.lua —— 投镖接口功能测试
-- 严格覆盖需求 2.1 ~ 2.3
-------------------------------------------------------

-- 模拟设备状态
local STATE = {IDLE=0, READY=1, SHOOTING=2}
local device_state = STATE.IDLE

-- 日志函数
local function log(msg)
    print("[SHOOT-TEST] " .. msg)
end

-- 飞镖类型映射
local TYPE_MAP = {["轻镖"]=1, ["重镖"]=2, ["未知物"]=3}

-- 构造投镖数据帧
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

-------------------------------------------------------
-- 1. 系统启动默认值测试（2.1）
-------------------------------------------------------
local function test_default()
    log("测试 2.1 系统启动默认值")
    device_state = STATE.READY
    local frame = make_shoot_frame{type=TYPE_MAP["轻镖"], number=10}
    write_msg(channels.COM_shoot, protocols.shoot, frame)
    etimer.delay(500)
    log("默认值发送完成")
end

-------------------------------------------------------
-- 2. 飞镖信息输入测试（2.2）
-------------------------------------------------------
local function test_input()
    log("测试 2.2 飞镖信息输入")

    device_state = STATE.READY

    -- local cases = {
    --     {"轻镖", -1}, {"轻镖", -10}, {"轻镖", 11}, {"轻镖", 20}, {"轻镖", 25},
    --     {"重镖", 0}, {"重镖", 1005},
    --     {"未知物", 3}, {"未知物", 1338},
    -- }

    local cases = {
      {"重镖", -1}, {"重镖", -10}, {"重镖", 11}, {"重镖", 20}, {"重镖", 25},
      {"重镖", 0}, {"重镖", 1005},
      {"重镖", 3}, {"重镖", 1338},
  }

    for _, c in ipairs(cases) do
        local dart_type = TYPE_MAP[c[1]]
        local number = c[2]
        -- if number > 20 then number = 20 end
        local frame = make_shoot_frame{type=dart_type, number=number}
        write_msg(channels.COM_shoot, protocols.shoot, frame)
        etimer.delay(300)
        log("发送飞镖类型=" .. c[1] .. " 数量=" .. number)
    end

    -- 非 READY 状态测试
    device_state = STATE.IDLE
    local frame = make_shoot_frame{type=TYPE_MAP["重镖"], number=6}
    write_msg(channels.COM_shoot, protocols.shoot, frame)
    etimer.delay(300)
    log("非准备状态发送应忽略")
    device_state = STATE.READY
end

-------------------------------------------------------
-- 3. 投标开始测试（2.3）
-------------------------------------------------------
local function test_start_shoot()
    log("测试 2.3 开始投镖")

    device_state = STATE.READY
    local frame = make_shoot_frame{type=TYPE_MAP["轻镖"], number=10}
    write_msg(channels.COM_shoot, protocols.shoot, frame)
    etimer.delay(300)

    log("用户点击开始 → 状态切换为 SHOOTING")
    device_state = STATE.SHOOTING

    log("非准备状态点击开始 → 无效")
    device_state = STATE.IDLE
    -- 模拟用户点击开始，不发送数据
end

-------------------------------------------------------
-- 4. 协议异常字段测试（容错测试）
-------------------------------------------------------
local function test_error_fields()
    log("开始协议字段异常测试")

    local err_cases = {
        {header=0x5511, desc="包头错误"},
        {flag=0x00, desc="数据标志错误"},
        {type=0x99, desc="飞镖类型错误"},
        {number=0xFF, desc="数量超范围"},
        {check=0x00, desc="校验和错误"},
        {tail=0xAAAA, desc="包尾错误"}
    }

    for _, c in ipairs(err_cases) do
        local frame = make_shoot_frame(c)
        write_msg(channels.COM_shoot, protocols.shoot, frame)
        etimer.delay(300)
        local res = ask("yesno",{title=c.desc, msg="系统是否接收错误包？", default=false})
        check(not res, c.desc .. " 丢包处理正确", c.desc .. " 丢包处理错误")
    end
end

-------------------------------------------------------
-- 5. 压力测试
-------------------------------------------------------
local function test_stress()
    log("连续发送压力测试 10 次")
    device_state = STATE.READY
    for i=1,10 do
        local frame = make_shoot_frame{type=TYPE_MAP["轻镖"], number=10}
        write_msg(channels.COM_shoot, protocols.shoot, frame)
        etimer.delay(50)
    end
    log("压力测试完成")
end

-------------------------------------------------------
-- 主入口
-------------------------------------------------------
function entry()
    log("========= 投镖接口功能测试开始 =========")

    test_default()
    test_input()
    test_start_shoot()
    -- test_error_fields()
    -- test_stress()

    log("========= 投镖接口功能测试结束 =========")
    exit()
end
