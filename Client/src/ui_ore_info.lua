UIOreInfo = {
    mine = nil,
    warParam = nil
}

local ui = UIOreInfo

local function netErrorCallbackFunc(pack)
    local code = tonumber(pack.header)
    local msgdata = pack.msgdata
    if code == StaticMsgRule.mineFightWin then
        UIOre.showFightResult(ui, -1, msgdata)
        return
    end
    if msgdata.int.activityStartTime and msgdata.int.activityEndTime and msgdata.int.activityStartTime and msgdata.int.activityEndTime then
        UIOre.activityTimes = { msgdata.int.activityStartTime, msgdata.int.activityEndTime, msgdata.int.activityStartTime2, msgdata.int.activityEndTime2 }
        UIManager.pushScene("ui_ore_hint")
    end
    UIOre.refreshCurPageMine(pack)
end

local function netCallbackFunc(pack)
    UIOre.refreshCurPageMine(pack)
    local code = tonumber(pack.header)
    local msgdata = pack.msgdata
    if code == StaticMsgRule.expelAssistant then
        UIManager.showToast("该玩家已被驱逐")
    elseif code == StaticMsgRule.occupyMine then
        if msgdata.int.fightType == 0 or msgdata.int.fightType == 1 then
            local uiOreFree = UIOre.free
            local uiOreInfoFree = UIOreInfo.free
            UIOre.free = nil
            UIOreInfo.free = nil
            pvp.loadGameData(pack)
            ui.warParam = { msgdata.int.fightType, msgdata.int.playerId, ui.mine.mineId }
            utils.sendFightData(nil, dp.FightType.FIGHT_MINE, function(isWin)
                if isWin then
                    UIManager.showLoading()
                    netSendPackage( {
                        header = StaticMsgRule.mineFightWin,
                        msgdata =
                        {
                            int = { fightType = UIOreInfo.warParam[1], id = UIOreInfo.warParam[2], mineId = UIOreInfo.warParam[3] },
                            string = { coredata = GlobalLastFightCheckData }
                        }
                    } , netCallbackFunc, netErrorCallbackFunc)
                else
                    UIOre.showFightResult(ui, 0)
                end
            end )
            if not UIFightMain.Widget or not UIFightMain.Widget:getParent() then
                UIFightMain.loading()
            else
                UIFightMain.setup()
            end
            UIOre.free = uiOreFree
            UIOreInfo.free = uiOreInfoFree
        end
    elseif code == StaticMsgRule.mineFightWin then
        UIOre.showFightResult(ui, 1, msgdata)
    end
end

local function sendPacket(pack)
    UIManager.showLoading()
    netSendPackage(pack, netCallbackFunc, netErrorCallbackFunc)
end

function ui.setMine(aMine)
    ui.mine = aMine
end

function ui.free()
    ui.mine = nil
    ui.warParam = nil
end

function ui.init()
    local image_basemap = ui.Widget:getChildByName("image_basemap")
    local text_title = image_basemap:getChildByName("image_title"):getChildByName("text_title")
    local text_number = ccui.Helper:seekNodeByName(ui.Widget, "text_number")
    local text_hint = image_basemap:getChildByName("image_di_hint"):getChildByName("text_hint")
    local btn_closed = image_basemap:getChildByName("btn_closed")
    local btn_middle = image_basemap:getChildByName("btn_middle")
    local btn_left = image_basemap:getChildByName("btn_left")
    local btn_right = image_basemap:getChildByName("btn_right")
    local btn_away2 = ccui.Helper:seekNodeByName(ui.Widget, "btn_away2")
    local text_master_get = ccui.Helper:seekNodeByName(ui.Widget, "text_master_get")
    local text_guest_get = ccui.Helper:seekNodeByName(ui.Widget, "text_guest_get")

    text_master_get:setString(string.format("第1位持续占领%d小时者获得", math.floor(UIOre.specialRewardOccupyTime / 3600)))
    text_guest_get:setString(string.format("第1位持续协助%d小时者获得", math.floor(UIOre.specialRewardAssistTime / 3600)))

    if ui.mine then
        text_title:setString(UIOre.MINE_NAMES[ui.mine.mineType + 1])
        local produceSpeed = UIOre.produceSpeed[ui.mine.mineType + 1]
        text_number:setString(string.format("银币%d/小时      进阶石%d/小时", produceSpeed[1], produceSpeed[2]))

        local curTime = utils.getCurrentTime()
        local t = os.date("*t", curTime)
        curTime = t.hour * 3600 + t.min * 60 + t.sec

        local function checkTime(startTime, endTime)
            if startTime <= endTime then
                if curTime >= startTime and curTime <= endTime then
                    return endTime - startTime
                end
            else
                if curTime >= startTime and curTime <= 24 * 3600 then
                    return 24 * 3600 - startTime + endTime
                elseif curTime <= endTime then
                    return 24 * 3600 - startTime + endTime
                end
            end

            return nil
        end

        local costTime = checkTime(UIOre.activityTimes[1], UIOre.activityTimes[2]) or checkTime(UIOre.activityTimes[3], UIOre.activityTimes[4]) or 0

        if ui.mine.mineType == 0 then
            text_hint:setString(string.format("占领矿产消耗%d%s", produceSpeed[3], produceSpeed[4] == 0 and "点耐力" or "元宝"))
        else
            text_hint:setString(string.format("占领矿产消耗%d%s    协助矿产消耗%d%s", produceSpeed[3], produceSpeed[4] == 0 and "点耐力" or "元宝", produceSpeed[5], produceSpeed[6] == 0 and "点耐力" or "元宝"))
        end

        local function touchevent(sender, eventType)
            if eventType == ccui.TouchEventType.ended then
                audio.playSound("sound/button.mp3")
                local mine = ui.mine
                if sender == btn_closed then
                    UIManager.popScene()
                elseif sender == btn_left then
                    if net.InstPlayer.int[produceSpeed[4] == 0 and "10" or "5"] < produceSpeed[3] then
                        if produceSpeed[4] == 0 then
                            utils.checkPlayerVigor()
                        else
                            UIManager.showToast(string.format("%s不足，不可%s该矿", produceSpeed[4] == 0 and "耐力" or "元宝", sender:getTitleText()))
                        end
                        return
                    end
                    if UIOre.mineId and UIOre.mineId ~= 0 then
                        utils.PromptDialog( function()
                            UIManager.showLoading()
                            local packet = { header = UIOre.mineOp == UIOre.MINE_OP_OCCUPY and StaticMsgRule.giveUpOccupy or StaticMsgRule.giveUpAssist, msgdata = { int = { mineId = UIOre.mineId } } }

                            local function giveUpCallback(pack)
                                UIManager.hideLoading()
                                sendPacket { header = StaticMsgRule.occupyMine, msgdata = { int = { mineId = ui.mine.mineId, minerId = ui.mine.minerId } } }
                            end

                            local function giveUpErrorCallback(pack)
                                UIManager.hideLoading()
                                local msgdata = pack.msgdata
                                if msgdata.int.activityStartTime and msgdata.int.activityEndTime and msgdata.int.activityStartTime and msgdata.int.activityEndTime then
                                    UIOre.activityTimes = { msgdata.int.activityStartTime, msgdata.int.activityEndTime, msgdata.int.activityStartTime2, msgdata.int.activityEndTime2 }
                                    UIManager.pushScene("ui_ore_hint")
                                    return
                                end
                                UIOre.refreshCurPageMine(pack)
                                sendPacket { header = StaticMsgRule.occupyMine, msgdata = { int = { mineId = ui.mine.mineId, minerId = ui.mine.minerId } } }
                            end

                            netSendPackage(packet, giveUpCallback, giveUpErrorCallback)
                        end , string.format("您已经%s一座资源矿，是否放弃%s资源矿并%s新矿？", UIOre.mineOp == UIOre.MINE_OP_OCCUPY and "拥有" or "协助了", UIOre.mineOp == UIOre.MINE_OP_OCCUPY and "" or "协助", mine.minerId == 0 and "占领" or "抢夺"))
                    else
                        sendPacket { header = StaticMsgRule.occupyMine, msgdata = { int = { mineId = mine.mineId, minerId = mine.minerId } } }
                    end
                elseif sender == btn_right then
                    if mine.minerId == 0 then
                        UIManager.showToast("该矿无人占领，不可协助")
                        return
                    end
                    if mine.assistant.id ~= 0 then
                        UIManager.showToast("协助者已满")
                        return
                    end
                    if net.InstPlayer.int[produceSpeed[6] == 0 and "10" or "5"] < produceSpeed[5] then
                        if produceSpeed[6] == 0 then
                            utils.checkPlayerVigor()
                        else
                            UIManager.showToast(string.format("%s不足，不可协助该矿", produceSpeed[6] == 0 and "耐力" or "元宝"))
                        end
                        return
                    end
                    if UIOre.mineId and UIOre.mineId ~= 0 then
                        utils.PromptDialog( function()
                            UIManager.showLoading()
                            local packet = { header = UIOre.mineOp == UIOre.MINE_OP_OCCUPY and StaticMsgRule.giveUpOccupy or StaticMsgRule.giveUpAssist, msgdata = { int = { mineId = UIOre.mineId } } }
                            local function giveUpCallback(pack)
                                UIManager.hideLoading()
                                sendPacket { header = StaticMsgRule.assistMiner, msgdata = { int = { mineId = ui.mine.mineId } } }
                            end
                            local function giveUpErrorCallback(pack)
                                UIManager.hideLoading()
                                local msgdata = pack.msgdata
                                if msgdata.int.activityStartTime and msgdata.int.activityEndTime and msgdata.int.activityStartTime and msgdata.int.activityEndTime then
                                    UIOre.activityTimes = { msgdata.int.activityStartTime, msgdata.int.activityEndTime, msgdata.int.activityStartTime2, msgdata.int.activityEndTime2 }
                                    UIManager.pushScene("ui_ore_hint")
                                    return
                                end
                                UIOre.refreshCurPageMine(pack)
                                sendPacket { header = StaticMsgRule.assistMiner, msgdata = { int = { mineId = ui.mine.mineId } } }
                            end
                            netSendPackage(packet, giveUpCallback, giveUpErrorCallback)
                        end , string.format("您已经%s一座资源矿，是否放弃%s资源矿并%s新矿？", UIOre.mineOp == UIOre.MINE_OP_OCCUPY and "拥有" or "协助了", UIOre.mineOp == UIOre.MINE_OP_OCCUPY and "" or "协助", "协助"))
                    else
                        sendPacket { header = StaticMsgRule.assistMiner, msgdata = { int = { mineId = mine.mineId } } }
                    end
                elseif sender == btn_middle then
                    if mine.mineId == UIOre.mineId then
                        if UIOre.mineOp == UIOre.MINE_OP_OCCUPY then
                            -- 放弃占领
                            utils.PromptDialog( function()
                                sendPacket { header = StaticMsgRule.giveUpOccupy, msgdata = { int = { mineId = mine.mineId } } }
                            end , "确认放弃该矿吗？\n放弃后将结算收益")
                        elseif UIOre.mineOp == UIOre.MINE_OP_ASSIST then
                            -- 放弃协助
                            utils.PromptDialog( function()
                                sendPacket { header = StaticMsgRule.giveUpAssist, msgdata = { int = { mineId = mine.mineId } } }
                            end , "确认放弃该矿吗？\n放弃后将结算收益")
                        end
                    end
                elseif sender == btn_away2 then
                    if btn_away2:getTitleText() == "驱逐" then
                        if mine.mineId == UIOre.mineId and UIOre.mineOp == UIOre.MINE_OP_OCCUPY then
                            sendPacket { header = StaticMsgRule.expelAssistant, msgdata = { int = { mineId = mine.mineId, assistId = mine.assistant.id } } }
                        else
                            UIManager.showToast("您不是矿主，无权驱逐")
                        end
                    else
                        UIManager.pushScene("ui_talk")
                        if UITalk.Widget then
                            local btn_unio = ccui.Helper:seekNodeByName(UITalk.Widget, "btn_alliance")
                            btn_unio:releaseUpEvent()
                            local uiText = ccui.Helper:seekNodeByName(UITalk.Widget, "image_basemap"):getChildByName("uiText")
                            uiText:setText(string.format("unionAssist:%d|%s|%d|%d", mine.mineType, mine.assistant.reward, mine.mineId, mine.minerId))
                            local btn_go = ccui.Helper:seekNodeByName(UITalk.Widget, "btn_go")
                            btn_go:releaseUpEvent()
                        end

                        btn_away2:setBright(false)
                        btn_away2:setEnabled(false)
                        UIOre.inviteScheduleId = cc.Director:getInstance():getScheduler():scheduleScriptFunc( function(dt)
                            local btn_away2 = nil
                            if ui.Widget and ui.Widget:getParent() and ui.mine.minerId == net.InstPlayer.int["1"] and ui.mine.assistant.id == 0 then
                                btn_away2 = ccui.Helper:seekNodeByName(ui.Widget, "btn_away2")
                                btn_away2:setTitleText(string.format("%02d秒", UITalk.time[UITalk.TAG_UNION]))
                            end
                            if UITalk.time[UITalk.TAG_UNION] <= 0 then
                                if UIOre.inviteScheduleId then
                                    cc.Director:getInstance():getScheduler():unscheduleScriptEntry(UIOre.inviteScheduleId)
                                    UIOre.inviteScheduleId = nil
                                end
                                if btn_away2 then
                                    btn_away2:setTitleText("联盟邀请")
                                    btn_away2:setBright(true)
                                    btn_away2:setEnabled(true)
                                end
                            end
                        end , 1, false)
                    end
                end
            end
        end

        btn_closed:addTouchEventListener(touchevent)
        btn_left:addTouchEventListener(touchevent)
        btn_right:addTouchEventListener(touchevent)
        btn_middle:addTouchEventListener(touchevent)
        btn_away2:addTouchEventListener(touchevent)
    end
end

function ui.setup()
    if not ui.mine then
        UIManager.popScene()
        return
    end

    local found = false
    for i, mine in ipairs(UIOre.mines) do
        if mine.mineId == ui.mine.mineId then
            ui.mine = mine
            found = true
            break
        end
    end

    if not found then
        UIManager.popScene()
        return
    end

    local mine = ui.mine
    local text_name = ccui.Helper:seekNodeByName(ui.Widget, "text_name")
    local text_lv = ccui.Helper:seekNodeByName(ui.Widget, "text_lv")
    local text_miner_hint = text_lv:getParent():getChildByName("text_hint")
    local image_di_helper = ccui.Helper:seekNodeByName(ui.Widget, "image_di_helper")
    local text_hint = ccui.Helper:seekNodeByName(image_di_helper, "text_hint")
    local btn_middle = ccui.Helper:seekNodeByName(ui.Widget, "btn_middle")
    local btn_left = ccui.Helper:seekNodeByName(ui.Widget, "btn_left")
    local btn_right = ccui.Helper:seekNodeByName(ui.Widget, "btn_right")
    local image_di_income = ccui.Helper:seekNodeByName(ui.Widget, "image_di_income")
    local text_reward_hint = image_di_income:getChildByName("text_hint")

    local isMiner = UIOre.mineId == mine.mineId and UIOre.mineOp == UIOre.MINE_OP_OCCUPY
    local isAssistant = UIOre.mineId == mine.mineId and UIOre.mineOp == UIOre.MINE_OP_ASSIST

    if mine.mineType == 0 then
        local children = image_di_income:getChildren()
        for i, child in ipairs(children) do
            if child ~= text_reward_hint and child:getName() ~= "image_title" then
                child:hide()
            end
        end
        text_reward_hint:show()
    else
        local children = image_di_income:getChildren()
        for i, child in ipairs(children) do
            if child ~= text_reward_hint and child:getName() ~= "image_title" then
                child:show()
            end
        end
        text_reward_hint:hide()
        local items = { image_di_income:getChildByName("image_master_good"), image_di_income:getChildByName("image_guest_good") }
        local itemProps = { utils.getItemProp(mine.reward), utils.getItemProp(mine.assistant.reward) }
        for i, item in ipairs(items) do
            local itemProp = itemProps[i]
            local image_good = item:getChildByName("image_good")
            local image_base_number = item:getChildByName("image_base_number")
            utils.addBorderImage(itemProp.tableTypeId, itemProp.tableFieldId, item)
            image_good:loadTexture(itemProp.smallIcon)
            if itemProp.count > 1 then
                image_base_number:show():getChildByName("text_number"):setString(tostring(itemProp.count))
            else
                image_base_number:hide()
            end
            utils.showThingsInfo(item, itemProp.tableTypeId, itemProp.tableFieldId)
        end
        local text_master_time = ccui.Helper:seekNodeByName(ui.Widget, "text_master_time")
        local text_guest_time = ccui.Helper:seekNodeByName(ui.Widget, "text_guest_time")

        function masterUpdate(dt)
            local mine = UIOreInfo.mine

            if UIOre.getRewardType == 1 then
                text_master_time:unscheduleUpdate()
                text_master_time:show():setString("您已领取过本次活动奖励")
                return false
            elseif mine.rewardPlayerId ~= 0 or(mine.startTime ~= 0 and mine.startTime + UIOre.specialRewardOccupyTime - utils.getCurrentTime() <= 0) then
                text_master_time:unscheduleUpdate()
                text_master_time:show():setString(mine.minerId == net.InstPlayer.int["1"] and "结算时发放奖励" or "本次活动奖励已被领完")
                return false
            elseif mine.minerId ~= 0 and mine.startTime ~= 0 then
                local countdown = math.min(UIOre.specialRewardOccupyTime, mine.startTime + UIOre.specialRewardOccupyTime - utils.getCurrentTime())
                if countdown <= 0 then
                    text_master_time:unscheduleUpdate()
                    text_master_time:show():setString("00:00:00")
                    UIOre.needRefreshMine = true
                    return false
                else
                    countdown = string.format("%02d:%02d:%02d", math.floor(countdown / 3600), math.floor(countdown / 60) % 60, countdown % 60)
                    text_master_time:show():setString(countdown)
                    return true
                end
            else
                text_master_time:unscheduleUpdate()
                text_master_time:hide()
                return false
            end
        end

        function guestUpdate(dt)
            local mine = UIOreInfo.mine
            if UIOre.getRewardType == 2 then
                text_guest_time:unscheduleUpdate()
                text_guest_time:show():setString("您已领取过本次活动奖励")
                return false
            elseif mine.assistant.rewardPlayerId ~= 0 or(mine.assistant.startTime ~= 0 and mine.assistant.startTime + UIOre.specialRewardAssistTime - utils.getCurrentTime() <= 0) then
                text_guest_time:unscheduleUpdate()
                text_guest_time:show():setString(mine.assistant.id == net.InstPlayer.int["1"] and "结算时发放奖励" or "本次活动奖励已被领完")
                return false
            elseif mine.assistant.id ~= 0 and mine.assistant.startTime ~= 0 then
                local countdown = math.min(UIOre.specialRewardAssistTime, mine.assistant.startTime + UIOre.specialRewardAssistTime - utils.getCurrentTime())
                if countdown <= 0 then
                    text_guest_time:unscheduleUpdate()
                    text_guest_time:show():setString("00:00:00")
                    UIOre.needRefreshMine = true
                    return false
                else
                    countdown = string.format("%02d:%02d:%02d", math.floor(countdown / 3600), math.floor(countdown / 60) % 60, countdown % 60)
                    text_guest_time:show():setString(countdown)
                    return true
                end
            else
                text_guest_time:unscheduleUpdate()
                text_guest_time:hide()
                return false
            end
        end

        local needScheduleUpdate = masterUpdate()
        if needScheduleUpdate then
            text_master_time:scheduleUpdate(masterUpdate)
        end
        needScheduleUpdate = guestUpdate()
        if needScheduleUpdate then
            text_guest_time:scheduleUpdate(guestUpdate)
        end
    end

    if mine.mineType == 0 or mine.minerId == 0 then
        if mine.mineType == 0 then
            text_hint:show():setString("当前矿不可协助")
        else
            text_hint:show():setString("当前矿无人占领不可协助")
        end
        local children = image_di_helper:getChildren()
        for i, child in ipairs(children) do
            if child ~= text_hint and child:getName() ~= "image_title" then
                child:hide()
            end
        end
    elseif mine.minerId ~= 0 then
        local btn_away = image_di_helper:getChildByName("btn_away2")
        local text_name = image_di_helper:getChildByName("text_name2")
        local text_lv = image_di_helper:getChildByName("text_lv2")

        local assistant = mine.assistant
        if assistant.id == 0 then
            text_name:show():setString("当前可协助")
            if mine.minerId == net.InstPlayer.int["1"] and mine.alliance ~= "" then
                if UITalk.time[UITalk.TAG_UNION] and UITalk.time[UITalk.TAG_UNION] > 0 then
                    btn_away:setTitleText(string.format("%02d秒", UITalk.time[UITalk.TAG_UNION]))
                    btn_away:setBright(false)
                    btn_away:setEnabled(false)
                else
                    btn_away:setTitleText("联盟邀请")
                    btn_away:setBright(true)
                    btn_away:setEnabled(true)
                end
            else
                btn_away:setTitleText("联盟邀请")
                btn_away:setBright(false)
                btn_away:setEnabled(false)
            end

            text_lv:hide()
        else
            btn_away:hide()
            text_name:show():setString(assistant.name)
            text_lv:show():setString("LV." .. assistant.level)
            -- btn_away:setTitleText("驱逐")
            -- btn_away:show():setEnabled(isMiner)
            -- btn_away:setBright(isMiner)
        end
        text_hint:hide()
    end

    if mine.minerId ~= 0 then
        text_name:show():setString(mine.name)
        text_lv:show():setString("LV." .. mine.level)
        text_miner_hint:hide()
    else
        text_name:hide()
        text_lv:hide()
        text_miner_hint:show()
    end

    if isMiner or isAssistant then
        btn_left:hide()
        btn_right:hide()
        btn_middle:show():setTitleText(isMiner and "放弃占领" or "放弃协助")
    else
        btn_middle:hide()
        if mine.mineType == 0 then
            btn_right:hide()
            btn_left:show():setPosition(btn_middle:getPosition())
        else
            btn_left:show():setPosition(148, 53)
            btn_right:show()
            btn_right:setEnabled(mine.minerId ~= 0)
            btn_right:setBright(mine.minerId ~= 0)
            btn_left:setTitleText(mine.minerId ~= 0 and "抢夺" or "占领")
        end
    end
end