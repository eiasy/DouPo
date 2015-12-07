UIHintBuy = {
	MONEY_TYPE_GOLD = 1, 			--元宝
	MONEY_TYPE_SILVER = 2,		--银币
	MONEY_TYPE_STAR = 3,			--星星
	MONEY_TYPE_MONTHCARD = 4, --月卡
	MONEY_TYPE_VIP = 5,				--VIP
	MONEY_TYPE_LEVEL = 6,			--等级
    MONEY_TYPE_RECHARGE = 7,   --去充值
}

local _moneyType = 1

function UIHintBuy.init()
end

function UIHintBuy.setup()
	local image_base_di = UIHintBuy.Widget:getChildByName("image_base_di")
	local btn_close = image_base_di:getChildByName("btn_close")
    local btn_no = image_base_di:getChildByName("btn_no")
    local btn_go = image_base_di:getChildByName("btn_go")
    local btn_yes = image_base_di:getChildByName("btn_yes")

    local function touchEvent(sender, eventType)
	    if eventType == ccui.TouchEventType.ended then
            audio.playSound("sound/button.mp3")
            if sender == btn_go then
                if _moneyType == UIHintBuy.MONEY_TYPE_GOLD or _moneyType == UIHintBuy.MONEY_TYPE_VIP then
			        UIManager.popScene()
			        utils.checkGOLD(1)
		        elseif _moneyType == UIHintBuy.MONEY_TYPE_SILVER then
			        UIMenu.onShop()
		        elseif _moneyType == UIHintBuy.MONEY_TYPE_STAR or _moneyType == UIHintBuy.MONEY_TYPE_LEVEL then
			        UIMenu.onFight(2)
		        elseif _moneyType == UIHintBuy.MONEY_TYPE_MONTHCARD then
			        UIActivityPanel.scrollByName("monthCard", "monthCard")
			        UIManager.showWidget("ui_activity_panel")
		        end
            elseif sender == btn_no or sender == btn_close then
                UIManager.popScene()
            elseif sender == btn_yes then
                UIManager.popScene()
			    utils.checkGOLD(1)
            end
	    end
	end

    btn_close:addTouchEventListener(touchEvent)
	if _moneyType == UIHintBuy.MONEY_TYPE_RECHARGE then
        btn_no:setVisible(true)
        btn_yes:setVisible(true)
        btn_go:setVisible(false)
        image_base_di:getChildByName("image_shop"):setVisible(false)
        btn_no:addTouchEventListener(touchEvent)
        btn_yes:addTouchEventListener(touchEvent)
        image_base_di:getChildByName("text_info"):setString( UIHintBuy.params and table.concat(UIHintBuy.params, "\n", 1, #UIHintBuy.params) or "")
    else
        btn_no:setVisible(false)
        btn_yes:setVisible(false)
        btn_go:setVisible(true)
        btn_go:addTouchEventListener(touchEvent)

        local text_info, text_shop, image_shop
	    if _moneyType == UIHintBuy.MONEY_TYPE_GOLD then
		    text_info, text_shop, image_shop = "您的元宝不足啦！", "充值", "ui/cz_cz.png"
	    elseif _moneyType == UIHintBuy.MONEY_TYPE_SILVER then
		    text_info, text_shop, image_shop = "您的银币不足啦！", "商城", "ui/db_shop.png"
	    elseif _moneyType == UIHintBuy.MONEY_TYPE_STAR then
		    text_info, text_shop, image_shop = "您的星星不足啦！", "副本", "ui/db_02.png"
	    elseif _moneyType == UIHintBuy.MONEY_TYPE_MONTHCARD then
		    text_info, text_shop, image_shop = "您不是月卡用户！", "月卡", "ui/ui_activity_card.png"
	    elseif _moneyType == UIHintBuy.MONEY_TYPE_VIP then
		    text_info, text_shop, image_shop = "您的VIP等级不足！", "充值", "ui/cz_cz.png"
	    elseif _moneyType == UIHintBuy.MONEY_TYPE_LEVEL then
		    text_info, text_shop, image_shop = "您的等级不足！", "副本", "ui/db_02.png"
	    end

        if text_info then
		    image_base_di:getChildByName("text_info"):setString("主人！" .. text_info .. string.format("\n可以前往 %s 购买！", text_shop))
	    end
	    if image_shop then
		    image_base_di:getChildByName("image_shop"):loadTexture(image_shop)
	    end
    end
end

function UIHintBuy.show(type, params)
	_moneyType = type
    UIHintBuy.params = params
	UIManager.pushScene("ui_hint_buy")
end

function UIHintBuy.free()
	_moneyType = 1
    UIHintBuy.params = nil
end