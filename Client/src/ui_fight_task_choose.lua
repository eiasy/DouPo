UIFightTaskChoose={
	reset = false,
}
local objData = nil
local RequstBarrierLevelId = nil  ---当前请求的战斗
local TableBarrierLevel = nil  --该关卡的所有等级
local btnOne ={}
local btnTen ={}
local image_base_easy =nil
local image_base_common = nil
local image_base_difficulty = nil
local ui_panel = nil
local barrierLevelIds={}
local barrierAllTimes = nil ---战斗总次数
local barrierTimes = 0  --- 已挑战次数
local InstbarrierId = nil
local btn_image_gold={}
local coldMoney = 0  --冷却所花的钱
local ResetBarrierTimes = 0  --副本重置次数
local ResetBarrierMoney = 0  --重置挑战次数所花的钱
local scheduleID =nil
local ui_image_basemap =nil
local image_base_easy_x,image_base_easy_y = nil,nil
local image_base_common_x ,image_base_common_y = nil ,nil
local image_base_difficulty_x,image_base_difficulty_y = nil,nil
local ui_image_basemap_height = nil
local ui_image_basemap_x,ui_image_basemap_y = nil,nil
local maxChapterTypeObj = nil
local energy =0
local coldTime = 0
local buyEnergyNum = 0
local energyPillPrice = 0
local OPENAKEYFIGHTLEVEL = DictFunctionOpen[tostring( StaticFunctionOpen.continuFight ) ].level -- 一键战斗开启等级

local TIP_COLOR = cc.c3b(255, 255, 0)

local function stopSchedule()
	if scheduleID then
		cc.Director:getInstance():getScheduler():unscheduleScriptEntry(scheduleID)
		scheduleID = nil
		if coldTime ~= 0 then
			coldTime = 0
		end
	end
end

local function updateTime()
	stopSchedule()
	for i=1,3 do
		btn_image_gold[i]:setVisible(false)
		-- btnTen[i]:setTitleText("")
	end
	--[[

    if maxChapterTypeObj ~= nil and coldTime ~= 0 then

        coldTime =coldTime-1

        local hour= math.floor(coldTime/3600)

        local min= math.floor(coldTime%3600/60)

        local sec= coldTime%60

        for i=1,3 do

            btnTen[i]:setTitleText(string.format("%02d:%02d",min,sec))

            btnTen[i]:setTitleColor(cc.c3b(255,255,255))

            btn_image_gold[i]:setVisible(true)

            btn_image_gold[i]:getChildByName("image_gold"):getChildByName("text_number"):setString("×" .. coldMoney)

        end

    else

        stopSchedule()

        for i=1,3 do

            btn_image_gold[i]:setVisible(false)

            btnTen[i]:setTitleText("")

        end

    end

  --]]
end

local function netCallbackFunc(pack)
	if tonumber(pack.header) == StaticMsgRule.aKeyCommonWar then
		UITeam.checkRecoverState()
		UIManager.flushWidget(UIFightTask)
		UIManager.flushWidget(UIFightTaskChoose)
		local param = {}
		table.insert(param,RequstBarrierLevelId)
		table.insert(param,pack.msgdata)
		UIFightClearing.setParam(param)
		UIManager.pushScene("ui_fight_clearing")
		UIFightTaskChoose.reset = false
	elseif tonumber(pack.header) == StaticMsgRule.clearColdTime then
		coldTime =0
		UIFightTaskChoose.reset = false
		UIFightTaskChoose.setup()

	elseif tonumber(pack.header) == StaticMsgRule.resetFightNum then
		UIFightTaskChoose.setup()
	elseif tonumber(pack.header) == StaticMsgRule.thingUse or tonumber(pack.header) == StaticMsgRule.goldEnergyOrVigor  then
        if tonumber(pack.header) == StaticMsgRule.thingUse then
    		UIManager.showToast("获得" .. DictSysConfig[tostring(StaticSysConfig.energyPillEnergy)].value .. "点体力")
        else
            local widget = UIFightTaskChoose.BuyEneryDialog.Widget
            if widget then
                local text_energypill = ccui.Helper:seekNodeByName(widget, "text_energypill")
                local sprite = cc.Sprite:create("image/+1.png")
                local size = text_energypill:getContentSize()
                sprite:setPosition(size.width / 2, size.height / 2)
                sprite:setScale(20 / sprite:getContentSize().height)
                sprite:setOpacity(150)
                text_energypill:addChild(sprite)

                local rightHint = ccui.Helper:seekNodeByName(widget, "rightHint")
                rightHint:runAction(cc.Sequence:create(cc.ScaleTo:create(0.1, 1.2), cc.ScaleTo:create(0.1, 1)))

                local scaleAction = cc.ScaleTo:create(1 / 6, 1.0)
                local alphaAction = cc.Sequence:create(cc.FadeIn:create(5 / 60), cc.DelayTime:create(1 / 6), cc.FadeOut:create(15 / 60))
                local moveAction = cc.EaseCubicActionInOut:create(cc.MoveBy:create(30 / 60, cc.p(0, 127)))
                moveAction = cc.Sequence:create(moveAction, cc.RemoveSelf:create())
                sprite:runAction(cc.Spawn:create(scaleAction, alphaAction, moveAction))
            end 
        end

		local bar_strength = ccui.Helper:seekNodeByName(UIFightTask.Widget,"bar_strength")
		bar_strength:setPercent(utils.getPercent(net.InstPlayer.int["8"], net.InstPlayer.int["9"]))
		bar_strength:getChildByName("text_strength"):setString(net.InstPlayer.int["8"].."/"..net.InstPlayer.int["9"])
		if tonumber(pack.header) == StaticMsgRule.goldEnergyOrVigor then
			UIShop.getShopList(1,nil)
		end
        UIFightTaskChoose.checkPlayerEnergy()
	end
end

local function sendFightTenRequest(_barrierId,_barrierLevelId,_fightNum)
	local  sendData = {
		header = StaticMsgRule.aKeyCommonWar,
		msgdata = {
			int = {
				instPlayerBarrierId = _barrierId,
				barrierLevelId  =_barrierLevelId ,
				fightNum  = _fightNum
			},
			string = {
				coredata = utils.fightVerifyData() --此处不可以欲计算
			}
		}
	}
	RequstBarrierLevelId = _barrierLevelId
	UIManager.showLoading()
	netSendPackage(sendData, netCallbackFunc)
end

---重置冷却时间
local function sendClearColdTimeRequest(_instPlayerChapterTypeId)
	local  sendData = {
		header = StaticMsgRule.clearColdTime,
		msgdata = {
			int = {
				instPlayerChapterTypeId = _instPlayerChapterTypeId,
			}
		}
	}
	UIManager.showLoading()
	netSendPackage(sendData, netCallbackFunc)
end

---重置关卡挑战次数
local function sendResetBarrierTimeRequest()
	local  sendData = {
		header = StaticMsgRule.resetFightNum,
		msgdata = {
			int = {
				instPlayerBarrierId = objData.int["1"],
			}
		}
	}
	UIManager.showLoading()
	netSendPackage(sendData, netCallbackFunc)
end

UIFightTaskChoose.BuyEneryDialog = {}

local function showBuyEnergyDialog()
    UIFightTaskChoose.BuyEneryDialog.init()
    UIFightTaskChoose.BuyEneryDialog.setup()
end

function UIFightTaskChoose.BuyEneryDialog.init()
    if UIFightTaskChoose.BuyEneryDialog.Widget then return end
    local vipNum = net.InstPlayer.int["19"]

    local ui_middle = ccui.Layout:create()
    ui_middle:setContentSize(display.size)
    ui_middle:setTouchEnabled(true)
    ui_middle:retain()

	local bg_image = cc.Scale9Sprite:create("ui/dialog_bg.png")
	ui_middle:addChild(bg_image)
	bg_image:setAnchorPoint(cc.p(0.5, 0.5))
	bg_image:setPreferredSize(cc.size(500, 500))
	bg_image:setPosition(display.size.width / 2, display.size.height / 2)
	local bgSize = bg_image:getPreferredSize()

	local title = ccui.Text:create()
	title:setString("· 提示 ·")
	title:setFontName(dp.FONT)
	title:setFontSize(35)
	title:setTextColor(cc.c4b(255, 255, 0, 255))
	title:setPosition(cc.p(bgSize.width / 2, bgSize.height- title:getContentSize().height-15))
	bg_image:addChild(title,3)

	local msgLabel = ccui.Text:create()
	msgLabel:setString("您的体力不足\n使用体力丹可以恢复体力")
	msgLabel:setTextAreaSize(cc.size(425,500))
	msgLabel:setFontName(dp.FONT)
	msgLabel:setTextHorizontalAlignment(cc.TEXT_ALIGNMENT_CENTER)
	msgLabel:setTextVerticalAlignment(cc.TEXT_ALIGNMENT_CENTER)
	msgLabel:setFontSize(26)
	msgLabel:setPosition(cc.p(bgSize.width / 2, bgSize.height- title:getContentSize().height * 3.5))
	bg_image:addChild(msgLabel,3)

	local node = cc.Node:create()
	local image_di = ccui.ImageView:create("ui/quality_small_blue.png")
	local image = ccui.ImageView:create("image/poster_item_small_tilidan.png")
	local description = ccui.Text:create()
    description:setName("text_energypill")
	description:setFontSize(20)
	description:setFontName(dp.FONT)
	description:setAnchorPoint(cc.p(0.5,1))
    description:setTextColor(TIP_COLOR)
	image:setPosition(cc.p(image_di:getContentSize().width/2,image_di:getContentSize().height/2))
	image_di:addChild(image)
	image_di:setPosition(cc.p(0,0))
	description:setPosition(cc.p(0,-image_di:getContentSize().height/2-5))
	node:addChild(image_di)
	node:addChild(description)
	description:setString("体力+" ..DictSysConfig[tostring(StaticSysConfig.energyPillEnergy)].value)
	node:setPosition(cc.p(bgSize.width / 2,msgLabel:getPositionY()-95))
	bg_image:addChild(node,3)

	local closeBtn = ccui.Button:create("ui/btn_x.png", "ui/btn_x.png")
	closeBtn:setPressedActionEnabled(true)
	closeBtn:setPosition(cc.p(bgSize.width - closeBtn:getContentSize().width/2, bgSize.height - closeBtn:getContentSize().height/2))
	bg_image:addChild(closeBtn,3)

    closeBtn:addTouchEventListener(function(sender, eventType)
        if sender == closeBtn then
            bg_image:runAction(cc.Sequence:create(cc.ScaleTo:create(0.2, 0.1), cc.CallFunc:create(function()
                UIManager.uiLayer:removeChild(ui_middle, true)
		        cc.release(ui_middle)
                UIFightTaskChoose.BuyEneryDialog.Widget = nil
            end)))  
        end
    end
    )

	local sureBtn = ccui.Button:create("ui/yh_sq_btn01.png", "ui/yh_sq_btn01.png")
    sureBtn:setName("sureBtn")
	sureBtn:setPressedActionEnabled(true)
    local withscale = ccui.RichText:create()
    withscale:setName("withscale")
    withscale:pushBackElement(ccui.RichElementText:create(1, display.COLOR_WHITE, 255, "购买", dp.FONT, 25))
    withscale:pushBackElement(ccui.RichElementImage:create(2, display.COLOR_WHITE, 255, "ui/jin.png"))
    withscale:pushBackElement(ccui.RichElementText:create(3, display.COLOR_WHITE, 255, "×10", dp.FONT, 25))
    withscale:setPosition(sureBtn:getContentSize().width / 2, sureBtn:getContentSize().height / 2)
    sureBtn:addChild(withscale)
	sureBtn:setPosition(cc.p(bgSize.width / 4, bgSize.height * 0.2))
	bg_image:addChild(sureBtn,3)

	local leftHint = ccui.RichText:create()
    leftHint:setName("leftHint")
    leftHint:pushBackElement(ccui.RichElementText:create(1, display.COLOR_WHITE, 255, "还可购买：", dp.FONT, 20))
    leftHint:pushBackElement(ccui.RichElementText:create(2, TIP_COLOR, 255, "0", dp.FONT, 20))
    leftHint:pushBackElement(ccui.RichElementText:create(3, display.COLOR_WHITE, 255, "次", dp.FONT, 20))
	leftHint:setPosition(cc.p(bgSize.width / 4, bgSize.height * 0.1))
    bg_image:addChild(leftHint,3)

	local useBtn = ccui.Button:create("ui/tk_btn01.png", "ui/tk_btn01.png")
    useBtn:setName("useBtn")
	useBtn:setTitleText("使用")
	useBtn:setTitleFontName(dp.FONT)
	useBtn:setTitleFontSize(25)
	useBtn:setPressedActionEnabled(true)
	useBtn:setPosition(cc.p(bgSize.width / 4 * 3, bgSize.height * 0.2))
	bg_image:addChild(useBtn,3)

    local rightHint = ccui.RichText:create()
    rightHint:setName("rightHint")
	rightHint:pushBackElement(ccui.RichElementText:create(1, display.COLOR_WHITE, 255, "当前拥有：", dp.FONT, 20))
    rightHint:pushBackElement(ccui.RichElementText:create(2, TIP_COLOR, 255, "0", dp.FONT, 20))
    rightHint:pushBackElement(ccui.RichElementText:create(3, display.COLOR_WHITE, 255, "个", dp.FONT, 20))
	rightHint:setPosition(cc.p(bgSize.width / 4*3, bgSize.height * 0.1))
	bg_image:addChild(rightHint,3)

    UIManager.uiLayer:addChild(ui_middle, 20000)
    ActionManager.PopUpWindow_SplashAction(bg_image)
    UIFightTaskChoose.BuyEneryDialog.Widget = ui_middle
end

function UIFightTaskChoose.BuyEneryDialog.setup()
    if not UIFightTaskChoose.BuyEneryDialog.Widget then return end

    local widget = UIFightTaskChoose.BuyEneryDialog.Widget

    local number  = 0
	local instThingId = nil
	if net.InstPlayerThing then
		for key,obj in pairs(net.InstPlayerThing) do
			if StaticThing.energyPill == obj.int["3"] then
				number = obj.int["5"]
				instThingId = obj.int["1"]
			end
		end
	end

    local withscale = ccui.Helper:seekNodeByName(widget, "withscale")
    local leftHint = ccui.Helper:seekNodeByName(widget, "leftHint")
    local rightHint = ccui.Helper:seekNodeByName(widget, "rightHint")
    local sureBtn = ccui.Helper:seekNodeByName(widget, "sureBtn")
    local useBtn = ccui.Helper:seekNodeByName(widget, "useBtn")

    withscale:removeElement(2)
    withscale:pushBackElement(ccui.RichElementText:create(3, display.COLOR_WHITE, 255, "×" .. energyPillPrice, dp.FONT, 25))
    leftHint:removeElement(1)
    leftHint:insertElement(ccui.RichElementText:create(2, TIP_COLOR, 255, tostring(math.max(0, buyEnergyNum)), dp.FONT, 20), 1)
    rightHint:removeElement(1)
    rightHint:insertElement(ccui.RichElementText:create(2, TIP_COLOR, 255, tostring(number), dp.FONT, 20), 1)
	
    local function sendUseData(_instPlayerThingId)
		local  sendData = {
			header = StaticMsgRule.thingUse,
			msgdata = {
				int = {
					instPlayerThingId   = _instPlayerThingId ,
					num = 1,
				}
			}
		}
		UIManager.showLoading()
		netSendPackage(sendData, netCallbackFunc)
	end
	local function sendGoldData()
		local  sendData = {
			header = StaticMsgRule.goldEnergyOrVigor,
			msgdata = {
				int = {
					type  = 1,
				}
			}
		}
		UIManager.showLoading()
		netSendPackage(sendData, netCallbackFunc)
	end

	local function btnEvent(sender, eventType)
		if eventType == ccui.TouchEventType.ended then
			if sender == sureBtn then
				if 0 < buyEnergyNum then
					sendGoldData()
				end
			elseif sender == useBtn then
				if number > 0 then
					sendUseData(instThingId)
				end             
			end
		end
	end
	sureBtn:addTouchEventListener(btnEvent)
	useBtn:addTouchEventListener(btnEvent)
	if number <= 0 then
		utils.GrayWidget(useBtn,true)
		useBtn:setEnabled(false)
    else
        utils.GrayWidget(useBtn,false)
		useBtn:setEnabled(true)
	end
	if energyPillPrice > net.InstPlayer.int["5"] or buyEnergyNum <= 0 then
		utils.GrayWidget(sureBtn,true)
		sureBtn:setEnabled(false)
    else
        utils.GrayWidget(sureBtn,false)
		sureBtn:setEnabled(true)
	end
end

---清楚冷却时间
local function resetFightTenFunc()
	local info  = "花费" .. coldMoney .. "元宝可以清楚当前冷却时间，确认清除？"
	local InstPlayerChapterTypeId = nil
	for key,obj in pairs(net.InstPlayerChapterType) do
		if obj.int["3"] == 1 then
			InstPlayerChapterTypeId = obj.int["1"]
		end
	end
	if InstPlayerChapterTypeId~= nil then
		utils.PromptDialog(sendClearColdTimeRequest,info,InstPlayerChapterTypeId)
	else
		UIManager.showToast("数据异常！")
	end
end

local function fightPromptDialog(bagType)
	local visibleSize = cc.Director:getInstance():getVisibleSize()
	local bg_image = cc.Scale9Sprite:create("ui/dialog_bg.png")
	bg_image:setAnchorPoint(cc.p(0.5, 0.5))
	bg_image:setPreferredSize(cc.size(600, 300))
	bg_image:setPosition(cc.p(visibleSize.width / 2, visibleSize.height / 2))
	local bgSize = bg_image:getPreferredSize()
	bg_image:retain()
	local title = ccui.Text:create()
	title:setString("· 提示 ·")
	title:setFontName(dp.FONT)
	title:setFontSize(35)
	title:setTextColor(cc.c4b(255, 255,  0,255))
	title:setPosition(cc.p(bgSize.width / 2, bgSize.height * 0.85))
	bg_image:addChild(title,3)
	local msgLabel = cc.Label:create()
	msgLabel:setSystemFontName(dp.FONT)
	local hint = nil
	if bagType == StaticBag_Type.card then
		hint = "您的卡牌背包已达上限，可以出售、升级或者扩充您的卡牌携带上限"
	elseif bagType == StaticBag_Type.equip then
		hint = "您的装备背包已达上限，可以出售、分解或者扩充您的装备携带上限"
	end
	msgLabel:setString(hint)
	msgLabel:setWidth(bgSize.width * 0.85)
	msgLabel:setHorizontalAlignment(cc.TEXT_ALIGNMENT_CENTER)
	msgLabel:setVerticalAlignment(cc.TEXT_ALIGNMENT_CENTER)
	msgLabel:setSystemFontSize(26)
	msgLabel:setTextColor(cc.c4b(255, 255, 255, 255))
	msgLabel:setPosition(cc.p(bgSize.width / 2, bgSize.height * 0.6))
	bg_image:addChild(msgLabel,3)

	local closeBtn = ccui.Button:create("ui/btn_x.png", "ui/btn_x.png")
	closeBtn:setPressedActionEnabled(true)
	closeBtn:setTouchEnabled(true)
	closeBtn:setPosition(cc.p(bgSize.width - closeBtn:getContentSize().width * 0.3, bgSize.height - closeBtn:getContentSize().height * 0.3))
	bg_image:addChild(closeBtn,3)
	local leftBtn = ccui.Button:create("ui/tk_btn01.png", "ui/tk_btn01.png")
	leftBtn:setTitleText("扩充")
	leftBtn:setTitleFontName(dp.FONT)
	leftBtn:setTitleFontSize(25)
	leftBtn:setPressedActionEnabled(true)
	leftBtn:setTouchEnabled(true)
	leftBtn:setPosition(cc.p(bgSize.width / 4-20, bgSize.height * 0.25))
	bg_image:addChild(leftBtn,3)
	local openLv = DictFunctionOpen[tostring(StaticFunctionOpen.resolve)].level
	local middleBtn = nil
	if bagType == StaticBag_Type.card then
		middleBtn = ccui.Button:create("ui/tk_btn01.png", "ui/tk_btn01.png")
		middleBtn:setTitleText("升级")
		middleBtn:setTitleFontName(dp.FONT)
		middleBtn:setTitleFontSize(25)
		middleBtn:setPressedActionEnabled(true)
		middleBtn:setTouchEnabled(true)
		middleBtn:setPosition(cc.p(bgSize.width / 2, bgSize.height * 0.25))
		bg_image:addChild(middleBtn,3)
	elseif bagType == StaticBag_Type.equip and net.InstPlayer.int["4"] >= openLv then
		middleBtn = ccui.Button:create("ui/tk_btn01.png", "ui/tk_btn01.png")
		middleBtn:setTitleText("分解")
		middleBtn:setTitleFontName(dp.FONT)
		middleBtn:setTitleFontSize(25)
		middleBtn:setPressedActionEnabled(true)
		middleBtn:setTouchEnabled(true)
		middleBtn:setPosition(cc.p(bgSize.width / 2, bgSize.height * 0.25))
		bg_image:addChild(middleBtn,3)
	end
	local rightBtn = ccui.Button:create("ui/tk_btn01.png", "ui/tk_btn01.png")
	rightBtn:setTitleText("出售")
	rightBtn:setTitleFontName(dp.FONT)
	rightBtn:setTitleFontSize(25)
	rightBtn:setPressedActionEnabled(true)
	rightBtn:setTouchEnabled(true)
	rightBtn:setPosition(cc.p(bgSize.width / 4 * 3+20, bgSize.height * 0.25))
	bg_image:addChild(rightBtn,3)
	local childs = UIManager.uiLayer:getChildren()
	local function btnEvent(sender, eventType)
		if eventType == ccui.TouchEventType.ended then
			UIManager.uiLayer:removeChild(bg_image, true)
			cc.release(bg_image)
			if sender == leftBtn or sender == middleBtn then
				UIFightTask.setBasemapPercent(nil)
				if bagType == StaticBag_Type.card then
					UIBagCard.setFlag(1)
					UIManager.showScreen("ui_notice","ui_team_info", "ui_bag_card","ui_menu")
				elseif bagType == StaticBag_Type.equip then
					if sender == leftBtn then
						UIBagEquipment.setFlag(1)
						UIManager.showScreen("ui_notice","ui_team_info", "ui_bag_equipment","ui_menu")
					elseif sender == middleBtn then
						UIManager.showScreen("ui_notice","ui_resolve","ui_menu")
					end
				end
			elseif sender == rightBtn then
				if bagType == StaticBag_Type.card then
					UIBagCardSell.setOperateType(UIBagCardSell.OperateType.CardSell)
					UIManager.pushScene("ui_bag_card_sell")
				elseif bagType == StaticBag_Type.equip then
					UIBagEquipmentSell.setOperateType(UIBagEquipmentSell.OperateType.SellEquip)
					UIManager.pushScene("ui_bag_equipment_sell")
				end
			end
			for i = 1, #childs do
				if not tolua.isnull(childs[i]) then
					childs[i]:setEnabled(true)
				end
			end
		end
	end
	closeBtn:addTouchEventListener(btnEvent)
	leftBtn:addTouchEventListener(btnEvent)
	if bagType == StaticBag_Type.card or (bagType == StaticBag_Type.equip and net.InstPlayer.int["4"] >= openLv) then
		middleBtn:addTouchEventListener(btnEvent)
	end
	rightBtn:addTouchEventListener(btnEvent)
	UIManager.uiLayer:addChild(bg_image, 10000)
	for i = 1, #childs do
		if childs[i] ~= bg_image then
			childs[i]:setEnabled(false)
		end
	end
end

local function checkBag()
	----判断卡牌背包------------
	local cardGrid = DictBagType[tostring(StaticBag_Type.card)].bagUpLimit
	if net.InstPlayerBagExpand then
		for key,obj in pairs(net.InstPlayerBagExpand) do
			if obj.int["3"] == StaticBag_Type.card  then
				cardGrid = obj.int["4"] + DictBagType[tostring(obj.int["3"])].bagUpLimit
			end
		end
	end
	local cardNumber = utils.getDictTableNum(net.InstPlayerCard)
	----判断装备背包------------
	local equipGrid = DictBagType[tostring(StaticBag_Type.equip)].bagUpLimit
	if net.InstPlayerBagExpand then
		for key,obj in pairs(net.InstPlayerBagExpand) do
			if obj.int["3"] == StaticBag_Type.equip then
				equipGrid = obj.int["4"] + DictBagType[tostring(obj.int["3"])].bagUpLimit
			end
		end
	end
	local equipNumber = utils.getDictTableNum(net.InstPlayerEquip)
	if cardNumber >= cardGrid then
		UIManager.popScene()
		fightPromptDialog(StaticBag_Type.card)
		return true
	elseif equipNumber >= equipGrid then
		UIManager.popScene()
		fightPromptDialog(StaticBag_Type.equip)
		return true
	end
end

local function getShopFunc(pack)
	local propThing =pack.msgdata.message
	if propThing then
		for key,obj in pairs(propThing) do
			local tableFieldId = obj.int["thingId"]
			if tableFieldId == StaticThing.energyPill then
				-- energyPillPrice = obj.int["price"]
				-- buyEnergyNum = obj.int["canBuyNum"]

				buyEnergyNum = obj.int["canBuyNum"]
				local _todayBuyPrice = 0
				--   buyVigorNum = _obj.int["todayBuyNum"]
				local _todayBuyNum = obj.int["todayBuyNum"] + 1
				local _extend = utils.stringSplit(DictThingExtend[tostring(tableFieldId)].extend, ";")
				for _k, _o in pairs(_extend) do
					local _tempO = utils.stringSplit(_o, "_")
					if _todayBuyNum >= tonumber(_tempO[1]) and _todayBuyNum <= tonumber(_tempO[2]) then
						energyPillPrice = math.round( tonumber( _tempO[3]) * UIShop.disCount )
						break
					end
				end

				break
			end
		end
	end
	showBuyEnergyDialog()
end

function UIFightTaskChoose.checkPlayerEnergy()
	UIManager.showLoading()
	local data ={
		header = StaticMsgRule.getStoreData,
		msgdata={
			int={
				type = 1,
			},
		}
	}
	netSendPackage(data,getShopFunc)
end

local function getBlankNode(width)
    local node = cc.Node:create()
    node:setContentSize(width, 5)
    return node
end

local function showResetBarrierTimesDialog()
	local VipNum = net.InstPlayer.int["19"]
	local resetEnabled = DictVIP[tostring(VipNum+1)].isResetGenerBarrier
	if resetEnabled == 0 then
		UIManager.showToast("您的挑战次数已用完,升级VIP可以重置挑战次数")
		return
	end
    local chapterResetCount = DictVIP[tostring(VipNum+1)].chapterResetCount
    if ResetBarrierTimes >= chapterResetCount then
        local params = {"今日重置次数已达上限！"}

        local vipLevel = nil
        for i = VipNum + 1, math.huge do
            local vip = DictVIP[tostring(i)]
            if not vip then break end
            if vip.chapterResetCount > chapterResetCount then
                vipLevel = i
                break
            end
        end

        if vipLevel then
            params[#params + 1] = string.format("VIP达到 %d 级\n副本重置次数为 %d 次", vipLevel - 1, DictVIP[tostring(vipLevel)].chapterResetCount)
        end
                        
        UIHintBuy.show(UIHintBuy.MONEY_TYPE_RECHARGE, params)
    else
        local ui_middle = ccui.Layout:create()
        ui_middle:setContentSize(display.size)
        ui_middle:setTouchEnabled(true)
        ui_middle:retain()

        local bg_image = cc.Scale9Sprite:create("ui/dialog_bg.png")
	    ui_middle:addChild(bg_image)
	    bg_image:setAnchorPoint(cc.p(0.5, 0.5))
	    bg_image:setPreferredSize(cc.size(500, 350))
	    bg_image:setPosition(display.size.width / 2, display.size.height / 2)
	    local bgSize = bg_image:getPreferredSize()

	    local title = ccui.Text:create()
	    title:setString("· 提示 ·")
	    title:setFontSize(35)
	    title:setFontName(dp.FONT)
	    title:setTextColor(cc.c4b(255, 255, 0, 255))
	    title:setPosition(cc.p(bgSize.width / 2, bgSize.height - 45))
	    bg_image:addChild(title)

	    local msgRichText = ccui.RichText:create()
        msgRichText:setContentSize(cc.size(425,300))
        msgRichText:pushBackElement(ccui.RichElementText:create(1, display.COLOR_WHITE, 255, "是否花费", dp.FONT, 26))
        msgRichText:pushBackElement(ccui.RichElementCustomNode:create(2, display.COLOR_WHITE, 255, getBlankNode(5)))
        msgRichText:pushBackElement(ccui.RichElementImage:create(3, display.COLOR_WHITE, 255, "ui/jin.png"))
        msgRichText:pushBackElement(ccui.RichElementText:create(4, cc.c3b(255, 255, 0), 255, "×" .. ResetBarrierMoney, dp.FONT, 26))
        msgRichText:pushBackElement(ccui.RichElementText:create(5, display.COLOR_WHITE, 255, "重置副本？", dp.FONT, 26))
	    msgRichText:setPosition(cc.p(bgSize.width / 2, bgSize.height - 120))
	    bg_image:addChild(msgRichText)

        local msgLabel = ccui.Text:create()
	    msgLabel:setString(string.format("今日还可重置 %d 次", chapterResetCount - ResetBarrierTimes))
	    msgLabel:setFontName(dp.FONT)
	    msgLabel:setTextHorizontalAlignment(cc.TEXT_ALIGNMENT_CENTER)
	    msgLabel:setTextVerticalAlignment(cc.TEXT_ALIGNMENT_CENTER)
	    msgLabel:setFontSize(26)
	    msgLabel:setTextColor(TIP_COLOR)
	    msgLabel:setPosition(cc.p(bgSize.width / 2, 75 + 75))
	    bg_image:addChild(msgLabel)

        local sureBtn = ccui.Button:create("ui/tk_btn01.png", "ui/tk_btn01.png")
	    sureBtn:setTitleText("确定")
	    sureBtn:setTitleFontName(dp.FONT)
	    sureBtn:setTitleColor(cc.c3b(255,255,255))
	    sureBtn:setTitleFontSize(25)
	    sureBtn:setPressedActionEnabled(true)
	    sureBtn:setTouchEnabled(true)
	    sureBtn:setPosition(bgSize.width / 4 * 3, 75)
	    bg_image:addChild(sureBtn)

        local closeBtn = ccui.Button:create("ui/btn_x.png", "ui/btn_x.png")
	    closeBtn:setPressedActionEnabled(true)
	    closeBtn:setTouchEnabled(true)
	    closeBtn:setPosition(cc.p(bgSize.width - closeBtn:getContentSize().width * 0.5, bgSize.height - closeBtn:getContentSize().height * 0.5))
	    bg_image:addChild(closeBtn,2)

        local cancelBtn = ccui.Button:create("ui/tk_btn_purple.png", "ui/tk_btn_purple.png")
	    cancelBtn:setTitleText("取消")
	    cancelBtn:setTitleFontName(dp.FONT)
	    cancelBtn:setTitleColor(cc.c3b(255,255,255))
	    cancelBtn:setTitleFontSize(25)
	    cancelBtn:setPressedActionEnabled(true)
	    cancelBtn:setTouchEnabled(true)
	    cancelBtn:setPosition(cc.p(bgSize.width / 4, 75))
	    bg_image:addChild(cancelBtn)

        local function closeDialog()
            UIManager.uiLayer:removeChild(ui_middle, true)
			cc.release(ui_middle)
        end
	    local function btnEvent(sender, eventType)
		    if eventType == ccui.TouchEventType.ended then
			    AudioEngine.playEffect("sound/button.mp3")
			    if sender == sureBtn then
				    sendResetBarrierTimeRequest()
			    end

                bg_image:runAction(cc.Sequence:create(cc.ScaleTo:create(0.2, 0.1), cc.CallFunc:create(closeDialog)))
		    end
	    end

        sureBtn:addTouchEventListener(btnEvent)
        closeBtn:addTouchEventListener(btnEvent)
	    cancelBtn:addTouchEventListener(btnEvent)

	    UIManager.uiLayer:addChild(ui_middle, 20000)
        ActionManager.PopUpWindow_SplashAction(bg_image)
    end
end

function UIFightTaskChoose.init()
	local btn_close = ccui.Helper:seekNodeByName(UIFightTaskChoose.Widget, "btn_close")
	local btn_embattle = ccui.Helper:seekNodeByName(UIFightTaskChoose.Widget, "btn_embattle")
	btn_close:setPressedActionEnabled(true)
	btn_embattle:setPressedActionEnabled(true)
	image_base_easy = ccui.Helper:seekNodeByName(UIFightTaskChoose.Widget, "image_base_easy")
	image_base_common = ccui.Helper:seekNodeByName(UIFightTaskChoose.Widget, "image_base_common")
	image_base_difficulty = ccui.Helper:seekNodeByName(UIFightTaskChoose.Widget, "image_base_difficulty")
	ui_image_basemap = ccui.Helper:seekNodeByName(UIFightTaskChoose.Widget, "image_basemap")
	ui_panel = ui_image_basemap:getChildByName("panel")
	---获取原来的坐标
	image_base_easy_x,image_base_easy_y = image_base_easy:getPosition()
	image_base_common_x,image_base_common_y = image_base_common:getPosition()
	image_base_difficulty_x,image_base_difficulty_y = image_base_difficulty:getPosition()
	ui_panel_x,ui_panel_y = ui_panel:getPosition()
	---------------
	ui_image_basemap_height = ui_image_basemap:getContentSize().height
	ui_image_basemap_x,ui_image_basemap_y = ui_image_basemap:getPosition()
	btnOne[1] = image_base_easy:getChildByName("btn_fight")
	btnTen[1] = image_base_easy:getChildByName("btn_fight_ten")
	btnOne[2] = image_base_common:getChildByName("btn_fight")
	btnTen[2] = image_base_common:getChildByName("btn_fight_ten")
	btnOne[3] = image_base_difficulty:getChildByName("btn_fight")
	btnTen[3] = image_base_difficulty:getChildByName("btn_fight_ten")
	btnOne[1]:setPressedActionEnabled(true)
	btnOne[2]:setPressedActionEnabled(true)
	btnOne[3]:setPressedActionEnabled(true)
	btnTen[1]:setPressedActionEnabled(true)
	btnTen[2]:setPressedActionEnabled(true)
	btnTen[3]:setPressedActionEnabled(true)
	btn_image_gold[1] = ccui.Helper:seekNodeByName(image_base_easy, "image_shadow")
	btn_image_gold[2] = ccui.Helper:seekNodeByName(image_base_common, "image_shadow")
	btn_image_gold[3] = ccui.Helper:seekNodeByName(image_base_difficulty, "image_shadow")
	local function TouchEvent(sender,eventType)
		if eventType == ccui.TouchEventType.ended then
			if sender == btn_close then
				AudioEngine.playEffect("sound/button.mp3")
				UIManager.popScene()
			elseif sender == btn_embattle then
				AudioEngine.playEffect("sound/button.mp3")
				UIManager.pushScene("ui_lineup_embattle")
			elseif sender == btnOne[1] or sender == btnOne[2] or sender == btnOne[3] then
				AudioEngine.playEffect("sound/fight.mp3")
				utils.LevelUpgrade =false
				local barrierLevelId = nil
				if sender == btnOne[1] then
					barrierLevelId = barrierLevelIds[1]
				elseif sender == btnOne[2] then
					barrierLevelId = barrierLevelIds[2]
				elseif sender == btnOne[3] then
					barrierLevelId = barrierLevelIds[3]
				end
				if barrierTimes >= barrierAllTimes then
                    showResetBarrierTimesDialog()
				else
					if checkBag() then
						return
					end

					if net.InstPlayer.int["8"] < energy then
						UIFightTaskChoose.checkPlayerEnergy()
						return
					end
					-----进入战斗---------------
					UIManager.popScene()
					local barrierId = nil
					local chapterId = nil
					if objData.name then
						barrierId = objData.id
						chapterId = objData.chapterId
					else
						barrierId = objData.int["3"]
						chapterId = DictBarrier[tostring(objData.int["3"])].chapterId
					end
					local param = {}
					param.barrierLevelId = barrierLevelId
					param.chapterId = chapterId
					param.barrierId = barrierId
					if objData.name and FightTaskData.FightData[chapterId] and FightTaskData.FightData[chapterId][barrierId] then
						UIFightMain.setData(FightTaskData.FightData[chapterId][barrierId],param,dp.FightType.FIGHT_TASK.COMMON)
						UIFightMain.loading()
					else
						utils.sendFightData(param,dp.FightType.FIGHT_TASK.COMMON)
						UIFightMain.loading()
                        if barrierId == 35 then
                            UIFightTask.setShowPoster(true, barrierId)
                        end
					end
				end
			else
				AudioEngine.playEffect("sound/fight.mp3")
				utils.LevelUpgrade =false
				local barrierLevelId = nil
				local levelFlag = 0
				if sender == btnTen[1] then
					levelFlag = 1
					barrierLevelId = barrierLevelIds[1]
				elseif sender == btnTen[2] then
					levelFlag = 2
					barrierLevelId = barrierLevelIds[2]
				elseif sender == btnTen[3] then
					levelFlag = 3
					barrierLevelId = barrierLevelIds[3]
				end
				if levelFlag > objData.int["6"] then
					UIManager.showToast("请先通关该难度副本！")
					return
				end
				local VipNum = net.InstPlayer.int["19"]
				local fightEnabled= DictVIP[tostring(VipNum+1)].isContinuFight
				if net.InstPlayer.int["4"] < OPENAKEYFIGHTLEVEL and fightEnabled == 0 then
					UIManager.showToast("等级达到" .. OPENAKEYFIGHTLEVEL .. "级才能开启")
					return
				end
				-- if  coldTime == 0  then
				if checkBag() then
					return
				end
				if barrierTimes < barrierAllTimes then
					---10次战斗  不足十次按不足算
					if barrierAllTimes - barrierTimes >= 10 then
						if net.InstPlayer.int["8"] < energy*10 then
							UIFightTaskChoose.checkPlayerEnergy()
							return
						end
						sendFightTenRequest(InstbarrierId,barrierLevelId,10)
					else
						if net.InstPlayer.int["8"] < energy*(barrierAllTimes - barrierTimes) then
							UIFightTaskChoose.checkPlayerEnergy()
							return
						end
						sendFightTenRequest(InstbarrierId,barrierLevelId,barrierAllTimes - barrierTimes)
					end
				else
					showResetBarrierTimesDialog()
				end
				-- else  ---购买冷却
				-- resetFightTenFunc()
				-- end
			end
		end
	end
	for i=1,3 do
		btnOne[i]:addTouchEventListener(TouchEvent)
		btnTen[i]:addTouchEventListener(TouchEvent)
	end
	btn_close:addTouchEventListener(TouchEvent)
	btn_embattle:addTouchEventListener(TouchEvent)
end

function UIFightTaskChoose.setup()
	if scheduleID then
		cc.Director:getInstance():getScheduler():unscheduleScriptEntry(scheduleID)
		scheduleID = nil
	end
	UIFightTaskChoose.Widget:setEnabled(true)

	if net.InstPlayerChapterType then
		for key,obj in pairs(net.InstPlayerChapterType) do
			if obj.int["3"] == 1 then
				maxChapterTypeObj  =obj
				if not UIFightTaskChoose.reset then
					if maxChapterTypeObj.string ~= nil and maxChapterTypeObj.string["5"] ~= nil and maxChapterTypeObj.string["5"] ~= "0" then
						local aKeyTime  =utils.GetTimeByDate(maxChapterTypeObj.string["5"])
						local currentTime = utils.getCurrentTime()
						if currentTime - aKeyTime >= DictSysConfig[tostring(StaticSysConfig.chapterAKeyTime)].value*3600 then
							coldTime = 0
						else
							coldTime = DictSysConfig[tostring(StaticSysConfig.chapterAKeyTime)].value*3600 -(currentTime - aKeyTime)
						end
					end
				end
			end
		end
	end
	if maxChapterTypeObj ~= nil then
		local coldNum = 0
		if maxChapterTypeObj.int["6"] ~= nil  then
			coldNum  =maxChapterTypeObj.int["6"]
		end
		local baseMoney = DictSysConfig[tostring(StaticSysConfig.chapterAKeyGold)].value
		local oneAddMoney  = DictSysConfig[tostring(StaticSysConfig.chapterAKeyGoldAdd)].value
		coldMoney  = baseMoney + coldNum*oneAddMoney
		-----元宝封顶----
		if coldMoney > DictSysConfig[tostring(StaticSysConfig.chapterBuyMaxGold)].value then
			coldMoney  = DictSysConfig[tostring(StaticSysConfig.chapterBuyMaxGold)].value
		end
	end
	updateTime()
	local image_task_star={}

	local ui_name = ccui.Helper:seekNodeByName(UIFightTaskChoose.Widget,"text_name")
	local ui_image = ui_panel:getChildByName("image_boss")
	local ui_text_challenge_number = ui_panel:getChildByName("text_challenge_number")
	local ui_text_cost_power = ui_panel:getChildByName("text_cost_power")
	local ui_image_di_good = ui_panel:getChildByName("image_di_good")
	image_task_star[1] = ui_panel:getChildByName("image_task_star1")
	image_task_star[2] = ui_panel:getChildByName("image_task_star2")
	image_task_star[3] = ui_panel:getChildByName("image_task_star3")

	local barrierId = nil
	local barrierLevel  =nil
	barrierTimes = nil
	if objData.name ~= nil  then  ---最后一条实例数据
		barrierId = objData.id
		barrierLevel =0
		barrierTimes = 0
		btnTen[1]:setVisible(false)
		btnTen[2]:setVisible(false)
		btnTen[3]:setVisible(false)
	else
		btnTen[1]:setVisible(true)
		btnTen[2]:setVisible(true)
		btnTen[3]:setVisible(true)
		barrierId = objData.int["3"]
		barrierLevel  = objData.int["6"]
		if barrierLevel == 1 then
			btnTen[1]:setBright(true)
			btnTen[2]:setBright(false)
			btnTen[3]:setBright(false)
		elseif barrierLevel == 2 then
			btnTen[1]:setBright(true)
			btnTen[2]:setBright(true)
			btnTen[3]:setBright(false)
		elseif barrierLevel == 3 then
			btnTen[1]:setBright(true)
			btnTen[2]:setBright(true)
			btnTen[3]:setBright(true)
		end
		barrierTimes = objData.int["4"]
		InstbarrierId = objData.int["1"]
		local resetTime = 0
		if objData.int["7"] ~= nil  then
			resetTime  = objData.int["7"]
		end
        ResetBarrierTimes = resetTime
		local baseMoney = DictSysConfig[tostring(StaticSysConfig.chapterAKeyGold)].value
		local oneAddMoney  = DictSysConfig[tostring(StaticSysConfig.chapterAKeyGoldAdd)].value
		ResetBarrierMoney  = baseMoney + resetTime*oneAddMoney
		-----元宝封顶----
		if ResetBarrierMoney > DictSysConfig[tostring(StaticSysConfig.chapterBuyMaxGold)].value then
			ResetBarrierMoney  = DictSysConfig[tostring(StaticSysConfig.chapterBuyMaxGold)].value
		end
	end
	energy = DictBarrier[tostring(barrierId)].energy
	barrierAllTimes = DictBarrier[tostring(barrierId)].fightNum
	local cardId = DictBarrier[tostring(barrierId)].cardId
	local bigUiId = DictCard[tostring(cardId)].bigUiId
	local imageName = DictUI[tostring(bigUiId)].fileName
	local name = DictBarrier[tostring(barrierId)].name
	ui_name:setString(name)
	ui_image:loadTexture("image/" .. imageName)
	ui_text_cost_power:setString("消耗体力：" .. energy)
	ui_text_challenge_number:setString("今日挑战次数：" .. barrierTimes .. "/" .. barrierAllTimes)
	local TableBarrierLevel={}
	for key, obj in pairs(DictBarrierLevel) do
		if obj.barrierId == barrierId then
			table.insert(TableBarrierLevel,obj)
		end
	end
	for i=1,3 do
		if i <= #TableBarrierLevel then
			image_task_star[i]:setVisible(true)
		else
			image_task_star[i]:setVisible(false)
		end
		utils.GrayWidget(image_task_star[i],i > barrierLevel)
	end

	if coldTime == 0 then
		btn_image_gold[1]:setVisible(false)
		btn_image_gold[2]:setVisible(false)
		btn_image_gold[3]:setVisible(false)
	end
	for i=1,3 do
		--      if maxChapterTypeObj == nil or coldTime == 0 then
		if barrierAllTimes - barrierTimes < 10 then
            if barrierAllTimes -barrierTimes > 0 then
			    btnTen[i]:setTitleText(string.format("战%d次",barrierAllTimes -barrierTimes))
            else
                btnTen[i]:setTitleText("重置")
            end
		else
			btnTen[i]:setTitleText(string.format("战%d次",10))
		end
		--      end
	end
	if maxChapterTypeObj~= nil and coldTime ~= 0 then
		scheduleID = cc.Director:getInstance():getScheduler():scheduleScriptFunc(updateTime,1,false)
	end
	TableBarrierLevel ={}
	barrierLevelIds ={}
	for key, obj in pairs(DictBarrierLevel) do
		if obj.barrierId == barrierId then
			table.insert(TableBarrierLevel,obj)
		end
	end
	local exp = DictLevelProp[tostring(net.InstPlayer.int["4"])].oneWarExp
	for key,obj in pairs(TableBarrierLevel) do
		if obj.level == 1 then
			local text_money = image_base_easy:getChildByName("text_money")
			local text_exp = image_base_easy:getChildByName("text_cultivation")
			text_money:setString(obj.copper)
			text_exp:setString(exp)
			barrierLevelIds[1] = obj.id
		elseif obj.level == 2 then
			local text_money = image_base_common:getChildByName("text_money")
			local text_exp = image_base_common:getChildByName("text_cultivation")
			text_money:setString(obj.copper)
			text_exp:setString(exp)
			barrierLevelIds[2] = obj.id
		elseif obj.level == 3 then
			local text_money = image_base_difficulty:getChildByName("text_money")
			local text_exp = image_base_difficulty:getChildByName("text_cultivation")
			text_money:setString(obj.copper)
			text_exp:setString(exp)
			barrierLevelIds[3] = obj.id
		end
	end

	if #TableBarrierLevel == 1 then
		image_base_easy:setVisible(true)
		image_base_common:setVisible(false)
		image_base_difficulty:setVisible(false)
		ui_image_basemap:setPosition(ui_image_basemap_x,ui_image_basemap_y-ui_image_basemap_height/8)
		ui_image_basemap:setContentSize(cc.size(608,600))
		image_base_easy:setPosition(cc.p(image_base_easy_x,image_base_easy_y-(ui_image_basemap_height-600)))
		ui_panel:setPosition(cc.p(ui_panel_x,ui_panel_y-(ui_image_basemap_height-600)))
	elseif #TableBarrierLevel == 2 then
		image_base_easy:setVisible(true)
		image_base_common:setVisible(true)
		image_base_difficulty:setVisible(false)
		ui_image_basemap:setPosition(ui_image_basemap_x,ui_image_basemap_y-60)
		ui_image_basemap:setContentSize(cc.size(608,750))
		image_base_easy:setPosition(cc.p(image_base_easy_x,image_base_easy_y-(ui_image_basemap_height-750)))
		image_base_common:setPosition(cc.p(image_base_common_x,image_base_common_y-(ui_image_basemap_height-750)))
		ui_panel:setPosition(cc.p(ui_panel_x,ui_panel_y-(ui_image_basemap_height-750)))
	elseif #TableBarrierLevel == 3 then
		image_base_easy:setVisible(true)
		image_base_common:setVisible(true)
		image_base_difficulty:setVisible(true)
		ui_image_basemap:setPosition(ui_image_basemap_x,ui_image_basemap_y)
		ui_image_basemap:setContentSize(cc.size(608,840))
		image_base_easy:setPosition(cc.p(image_base_easy_x,image_base_easy_y))
		image_base_common:setPosition(cc.p(image_base_common_x,image_base_common_y))
		image_base_difficulty:setPosition(cc.p(image_base_difficulty_x,image_base_difficulty_y))
		ui_panel:setPosition(cc.p(ui_panel_x,ui_panel_y))
	end
	----------------掉落物品显示------------
	local dropThing ={}
	local things = DictBarrier[tostring(barrierId)].things
	local thingsTable = utils.stringSplit(things,";")
	for key,obj in pairs(thingsTable) do
		dropThing[#dropThing+1] = utils.stringSplit(obj,"_")
	end
	for i=1,6 do
		local ui_image_good = ui_image_di_good:getChildByName("image_frame_good" .. i)
		if i > #dropThing then
			ui_image_good:setVisible(false)
		else
			ui_image_good:setVisible(true)
			local ui_image = ui_image_good:getChildByName("image_good")
			local ui_name = ui_image:getChildByName("text_good_name")
			local tableTypeId,tableFieldId, thingNum = dropThing[i][1], dropThing[i][2], dropThing[i][3]
			local name,Icon = utils.getDropThing(tableTypeId,tableFieldId)
			ui_name:setString(name)
			ui_image:loadTexture(Icon)
			utils.addBorderImage(tableTypeId,tableFieldId,ui_image_good)
		end
	end
	UIGuidePeople.isGuide(nil,UIFightTaskChoose)
end

function UIFightTaskChoose.setData(_data)
	objData =_data
end

-----到达24点复位-----------
function UIFightTaskChoose.ResetData()
	if objData ~= nil and objData.name == nil then
		objData.int["4"] =0
		objData.int["7"] =0
	end
	UIFightTaskChoose.reset =true
	stopSchedule()
	if maxChapterTypeObj ~= nil then
		maxChapterTypeObj.int["6"] = 0
	end
end

function UIFightTaskChoose.setTimeInterval(intervalTime)
	if maxChapterTypeObj ~= nil and coldTime ~= 0 then

		local countDownTime = coldTime - intervalTime
		if countDownTime >= 0  then
			coldTime =countDownTime
		else
			coldTime = 0
		end
		UIFightTaskChoose.setup()
	end
end

function UIFightTaskChoose.free()
	stopSchedule()
end
