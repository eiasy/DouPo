UIBossAward = {}

local userData = nil

function UIBossAward.init()
    local image_basemap = UIBossAward.Widget:getChildByName("image_basemap")
    local btn_closed = image_basemap:getChildByName("btn_closed")
    local btn_sure = image_basemap:getChildByName("btn_sure")
    btn_closed:setPressedActionEnabled(true)
    btn_sure:setPressedActionEnabled(true)
    local function onButtonEvent(sender, eventType)
        if eventType == ccui.TouchEventType.ended then
            UIManager.popScene()
        end
    end
    btn_sure:addTouchEventListener(onButtonEvent)
    btn_closed:addTouchEventListener(onButtonEvent)
end

function UIBossAward.setup()
    if userData then
        local image_basemap = UIBossAward.Widget:getChildByName("image_basemap")
        local image_di = image_basemap:getChildByName("image_di")
        local text_hint = image_basemap:getChildByName("text_hint")
        local text_rank = ccui.Helper:seekNodeByName(image_di:getChildByName("image_base_di"), "text_rank")
        local _flag = false
        if userData.isLastHit == 1 then --最后一击
            text_hint:setString("大侠果然英武不凡，对BOSS造成致命一击，\n获得巨额击杀奖励")
        elseif userData.bossIsDead ~= 0 then --BOOS死亡
            text_hint:setString("大侠们众志成城，成功击杀了世界BOSS，\n获得全额排名奖励")
        else
            text_hint:setString("击杀世界BOSS任务没有完成，排名奖励减半")
            _flag = true
        end
        text_rank:setString(tostring(userData.bossRank))
        if userData.things then
            local data = utils.stringSplit(userData.things, ";")
            for key, obj in pairs(data) do
                local thingItem = image_di:getChildByName("image_frame_good" .. key)
                if thingItem then
                    local ui_icon = thingItem:getChildByName("image_good")
                    local ui_name = ui_icon:getChildByName("text_name")
                    local ui_count = ccui.Helper:seekNodeByName(thingItem, "text_number")
                    local itemProps = utils.getItemProp(obj)
                    if itemProps.frameIcon then
                        ui_icon:loadTexture(itemProps.frameIcon)
                    end
                    if itemProps.smallIcon then
                        ui_icon:loadTexture(itemProps.smallIcon)
                    end
                    if itemProps.name then
                        ui_name:setString(itemProps.name)
                    end
                    if itemProps.count then
                        ui_count:setString(_flag and (itemProps.count / 2) or itemProps.count)
                    end
                end
            end
        end
    end
end

function UIBossAward.free()
    userData = nil
end

function UIBossAward.show(_tableParams)
    userData = _tableParams
    if userData then
        userData.things = userData.msgData.msgdata.string.things
        userData.bossIsDead = userData.msgData.msgdata.int.bossIsDead --0-未被击杀  非0-被击杀
        userData.isLastHit = userData.msgData.msgdata.int.isLastHit --0-不是  1-是
        userData.bossRank = userData.msgData.msgdata.int.bossRank --伤害排行
    end
    UIManager.pushScene("ui_boss_award")
end