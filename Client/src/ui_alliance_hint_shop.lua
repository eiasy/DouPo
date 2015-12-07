UIAllianceHintShop = {}

local userData = nil

function UIAllianceHintShop.init()
end

function UIAllianceHintShop.setup()
    local btn_cancel = ccui.Helper:seekNodeByName(UIAllianceHintShop.Widget, "btn_cancel")
    local btn_ok = ccui.Helper:seekNodeByName(UIAllianceHintShop.Widget, "btn_ok")
    btn_cancel:setPressedActionEnabled(true)
    btn_ok:setPressedActionEnabled(true)
    local function onButtonEvent(sender, eventType)
        if eventType == ccui.TouchEventType.ended then
            if sender == btn_ok and userData.callbackFunc then
                userData.callbackFunc()
            end
            UIManager.popScene()
        end
    end
    btn_cancel:addTouchEventListener(onButtonEvent)
    btn_ok:addTouchEventListener(onButtonEvent)
    local text_hint = ccui.Helper:seekNodeByName(UIAllianceHintShop.Widget, "text_hint")
    if userData.buyGold > 0 then
        text_hint:setString(string.format("是否确认花费 %d联盟贡献和%d元宝 购买以下物品么？", userData.buyOffer, userData.buyGold))
    else
        text_hint:setString(string.format("是否确认花费 %d联盟贡献 购买以下物品么？", userData.buyOffer))
    end
    local ui_frame = ccui.Helper:seekNodeByName(UIAllianceHintShop.Widget, "image_frame_good")
    local ui_icon = ui_frame:getChildByName("image_good")
    local ui_name = ui_frame:getChildByName("text_name")
    ui_frame:setTexture(userData.itemFrame)
    ui_icon:setTexture(userData.itemIcon)
    ui_name:setString(userData.itemName .. "×" .. userData.itemCount)
end

function UIAllianceHintShop.free()
    userData = nil
end

function UIAllianceHintShop.show(_talbeParams)
    userData = _talbeParams
    UIManager.pushScene("ui_alliance_hint_shop")
end