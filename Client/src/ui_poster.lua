UIPoster = {}

local function doPay()
    local obj = DictRecharge[tostring(1)]
--    if device.platform == "ios" then
        local instActivityObj = UIActivityPanel.getInstThingByName("monthCard")
        if instActivityObj then
            if instActivityObj.string["4"] == "" then
                UIManager.showToast("您已购买月卡！")
            else
                if UIActivityPanel.isEndActivityByEndTime(instActivityObj.string["4"]) then
                    UIGiftRecharge.doGetOrderID(obj)
                else
                    UIManager.showToast("您上次购买的月卡还未过期！")
                end
            end
        else
            UIGiftRecharge.doGetOrderID(obj)
        end
--    else
--        UIGiftRecharge.doGetOrderID(obj)
        -- UIManager.showToast("暂未开放!")
--    end
end

function UIPoster.init()
    local image_hint = UIPoster.Widget:getChildByName("image_hint")
    local btn_closed = image_hint:getChildByName("btn_closed")
    local btn_quit = image_hint:getChildByName("btn_quit")
    local btn_gas = image_hint:getChildByName("btn_gas")
    btn_closed:setPressedActionEnabled(true)
    btn_quit:setPressedActionEnabled(true)
    btn_gas:setPressedActionEnabled(true)
    local function onButtonEvent(sender, eventType)
        if eventType == ccui.TouchEventType.ended then
            if sender == btn_closed or sender == btn_quit then
                UIManager.popScene()
            elseif sender == btn_gas then
                doPay()
            end
        end
    end
    btn_closed:addTouchEventListener(onButtonEvent)
    btn_quit:addTouchEventListener(onButtonEvent)
    btn_gas:addTouchEventListener(onButtonEvent)
end

function UIPoster.setup()
    local uiDatas = {
        {tableTypeId = 7, tableFieldId = 52},
        {tableTypeId = 6, tableFieldId = 7},
        {tableTypeId = 6, tableFieldId = 71},
        {tableTypeId = 6, tableFieldId = 46},
        {tableTypeId = 6, tableFieldId = 92}
    }
    local ui_buttons = {}
    local image_hint = UIPoster.Widget:getChildByName("image_hint")
    ui_buttons[1] = image_hint:getChildByName("panel_ruolin") --若琳导师
    ui_buttons[2] = image_hint:getChildByName("panel_jian") --万魂剑
    ui_buttons[3] = image_hint:getChildByName("panel_lingyu") --万魂灵玉
    ui_buttons[4] = image_hint:getChildByName("panel_mingjia") --万魂冥甲
    ui_buttons[5] = image_hint:getChildByName("panel_guan") --万魂冠
    
    for key, uiItem in pairs(ui_buttons) do
        utils.showThingsInfo(uiItem, uiDatas[key].tableTypeId, uiDatas[key].tableFieldId)
    end
end

function UIPoster.free()

end

function UIPoster.show()
    UIManager.pushScene("ui_poster")
end