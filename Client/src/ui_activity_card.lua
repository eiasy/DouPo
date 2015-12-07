UIActivityCard = {
    state = { },
    SILVER_MONTH_CARD = 1,
    GOLD_MONTH_CARD = 2,

    STATE_GET_GOLD = 1,
    STATE_RECHARGE = 2,
    STATE_RECHARGING = 3,

    CHARGE_IDS = { "1", "9" }
}

local ui = UIActivityCard

local function sendNetData(instActivityId, cardType)
    local sendData = {
        header = StaticMsgRule.getMonthCard,
        msgdata =
        {
            int =
            {
                instActivityId = instActivityId,
            }
        }
    }
    UIManager.showLoading()
    netSendPackage(sendData, function()
        UIManager.flushWidget(ui)
        UIManager.showToast(string.format("已领取%d元宝", DictRecharge[ui.CHARGE_IDS[cardType]].noFirstAmt))
    end )
end

local function sendNetEquipData()
    local sendData = {
        header = StaticMsgRule.intoMonthCard,
        msgdata = { }
    }
    UIManager.showLoading()
    netSendPackage(sendData, function(pack)
        local thing_table = utils.getItemProp(pack.msgdata.string["1"])
        local state = pack.msgdata.int["2"]
        local buyState = pack.msgdata.int["3"]
        local function touchEvent(sender, eventType)
            if eventType == ccui.TouchEventType.ended then
                if buyState == 0 then
                    UIManager.showToast("您还没购买白银月卡，请前往充值")
                elseif state == 0 then
                    UIManager.showLoading()
                    netSendPackage( { header = StaticMsgRule.getMonthCardThing, msgdata = { } }, function()
                        UIManager.flushWidget(ui)
                        utils.showGetThings(pack.msgdata.string["1"])
                    end )
                end
            end
        end

        local btn_thing = ccui.Helper:seekNodeByName(ui.Widget, "btn_thing")
        btn_thing:setPressedActionEnabled(true)
        if buyState == 0 then
            btn_thing:setTitleText("送橙装")
            btn_thing:setEnabled(true)
            btn_thing:setBright(true)
            btn_thing:addTouchEventListener(touchEvent)
        elseif state == 0 then
            btn_thing:setTitleText("领取橙装")
            btn_thing:setEnabled(true)
            btn_thing:setBright(true)
            btn_thing:addTouchEventListener(touchEvent)
        elseif state == 1 then
            btn_thing:setTitleText("已领取")
            btn_thing:setEnabled(false)
            btn_thing:setBright(false)
        end

        local image_chip = ccui.Helper:seekNodeByName(ui.Widget, "image_chip")
        image_chip:loadTexture(thing_table.smallIcon)
        utils.showThingsInfo(image_chip, thing_table.tableTypeId, thing_table.tableFieldId)
    end )
end

function ui.getMonthCardData(type)
    local instActivityObj = nil
    if net.InstActivity then
        for key, obj in pairs(net.InstActivity) do
            local activity = net.SysActivity[tostring(obj.int["3"])]
            if activity.string["9"] == "monthCard" and obj.int["6"] == type then
                instActivityObj = obj
                return instActivityObj
            end
        end
    end
end

local function addParticleEffect(node)
    local size = node:getContentSize()
    local anchorSize = 15
    local offset = 7
    for _i = 1, 2 do
        local effect = cc.ParticleSystemQuad:create("particle/ui_anim8_effect.plist")
        node:addChild(effect)
        effect:setPositionType(cc.POSITION_TYPE_RELATIVE)
        if _i == 1 then
            effect:setPosition(anchorSize + offset, offset)
            effect:runAction(utils.MyPathFun(anchorSize + offset, size.height - 2 * offset, size.width - 2 * anchorSize - 2 * offset, 0.5, 1))
        else
            effect:setPosition(size.width - anchorSize - offset, size.height - offset)
            effect:runAction(utils.MyPathFun(anchorSize + offset, size.height - 2 * offset, size.width - 2 * anchorSize - 2 * offset, 0.5, 0))
        end
    end
end

function ui.init()
    local image_frame = ccui.Helper:seekNodeByName(ui.Widget, "image_frame")
    addParticleEffect(image_frame)

    local btn_three = ccui.Helper:seekNodeByName(ui.Widget, "btn_three")
    local btn_five = ccui.Helper:seekNodeByName(ui.Widget, "btn_five")

    btn_three:setPressedActionEnabled(true)
    btn_five:setPressedActionEnabled(true)

    local function btnEvent(sender, eventType)
        if eventType == ccui.TouchEventType.ended then
            audio.playSound("sound/button.mp3")
            local type = sender == btn_three and ui.SILVER_MONTH_CARD or ui.GOLD_MONTH_CARD
            local state = ui.state[type]
            if state == ui.STATE_GET_GOLD then
                local instActivityObj = ui.getMonthCardData(type)
                if instActivityObj then
                    sendNetData(instActivityObj.int["1"], type)
                end
            elseif state == ui.STATE_RECHARGE then
                utils.checkGOLD(1)
            elseif state == ui.STATE_RECHARGING then
                UIManager.showToast("请稍候，延时到账")
            end
        end
    end

    btn_three:addTouchEventListener(btnEvent)
    btn_five:addTouchEventListener(btnEvent)
end

function ui.setup()
    sendNetEquipData()

    local names = { "three", "five" }
    local types = { ui.SILVER_MONTH_CARD, ui.GOLD_MONTH_CARD }

    for i = 1, #types do
        local type = types[i]
        local name = names[i]

        local btn_recharge = ccui.Helper:seekNodeByName(ui.Widget, "btn_" .. name)
        local text_hint = ccui.Helper:seekNodeByName(ui.Widget, string.format("image_%s_hint", name)):getChildByName("text_hint")
        ccui.Helper:seekNodeByName(ui.Widget, "text_" .. name):setString(DictRecharge[ui.CHARGE_IDS[type]].rmb * 10)

        repeat
            local instActivityObj = ui.getMonthCardData(type)
            if instActivityObj then
                if instActivityObj.string["4"] == "" then
                    ui.state[type] = ui.STATE_RECHARGING
                    text_hint:setString("持续赠送30天")
                    btn_recharge:setTitleText("请稍候")
                    btn_recharge:setEnabled(true)
                    utils.GrayWidget(btn_recharge, false)
                    break
                elseif instActivityObj.string["4"] ~= "" and UIActivityPanel.isEndActivityByEndTime(instActivityObj.string["4"]) then
                    ui.state[type] = ui.STATE_RECHARGE
                    text_hint:setString("持续赠送30天")
                    btn_recharge:setTitleText("前往充值")
                    btn_recharge:setEnabled(true)
                    utils.GrayWidget(btn_recharge, false)
                    break
                end

                local recentGetTime = instActivityObj.string["7"]
                local endDay = utils.changeTimeFormat(instActivityObj.string["4"])[3]
                local getDay = nil
                if recentGetTime ~= nil and recentGetTime ~= "" then
                    getDay = utils.changeTimeFormat(recentGetTime)[3]
                end
                local remainDay = 0
                local _curTime = utils.getCurrentTime()
                local _date = os.date("*t", _curTime)
                local function isleapyear(y)
                    return(y % 4 == 0 and y % 100 or y % 400 == 0)
                end
                local md = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }
                if isleapyear(_date.year) then
                    md[2] = 29
                end
                if endDay - dp.loginDay > 0 then
                    remainDay = endDay - dp.loginDay
                elseif endDay - dp.loginDay == 0 then
                    remainDay = 30
                else
                    remainDay = md[_date.month] - dp.loginDay + endDay
                end

                if getDay and tonumber(getDay) == tonumber(dp.loginDay) then
                    text_hint:setString("剩余领取天数：" ..(remainDay - 1))
                    btn_recharge:setTitleText("已领取")
                    btn_recharge:setEnabled(false)
                    utils.GrayWidget(btn_recharge, true)
                else
                    ui.state[type] = ui.STATE_GET_GOLD
                    text_hint:setString("剩余领取天数：" .. remainDay)
                    btn_recharge:setTitleText("领取")
                    btn_recharge:setEnabled(true)
                    utils.GrayWidget(btn_recharge, false)
                end
            else
                ui.state[type] = ui.STATE_RECHARGE
                text_hint:setString("持续赠送30天")
                btn_recharge:setTitleText("前往充值")
                btn_recharge:setEnabled(true)
                utils.GrayWidget(btn_recharge, false)
            end
        until true
    end
end