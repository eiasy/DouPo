UIFriend = {}

local FLAG_MAIN = 1 --主力标识位
local FLAG_BENCH = 2 --替补标识位
local POINTS = {{x=-205,y=199},{x=0,y=199},{x=205,y=199},{x=-205,y=27},{x=0,y=27},{x=205,y=27},{x=-205,y=-199},{x=0,y=-199},{x=205,y=-199}}

local rootPanel = nil
local uiPanel = nil
local ui_scrollView = nil
local ui_svItem = nil

local uiCardItem = nil
local ui_friends = {}
local ui_activateLuckNum = nil

local _activateLuckNum = 0 --激活缘分个数
local _btnAllLcuk_FLAG = false
local btnAllLuck_Text1 = "全部缘分"
local btnAllLuck_Text2 = "返回上层"

local _InstPlayerPartner = nil --小伙伴实例数据

--@flag : 标志位(true:AllLuck，false:CardLuck)
local function showAllLuckOrCardLuck(flag)
	_btnAllLcuk_FLAG = flag
	rootPanel:getChildByName("image_base_name_up"):setVisible(not flag)
	uiPanel:setVisible(not flag)
	rootPanel:getChildByName("image_base_name_bench"):setVisible(not flag)
	ui_scrollView:setVisible(flag)
	ui_scrollView:removeAllChildren()
	if flag then
		local innerHieght, space = 0, 20
		
		local formation1, formation2 = {}, {}
		for key, obj in pairs(net.InstPlayerFormation) do
			if obj.int["4"] == 1 then	--主力
				formation1[#formation1 + 1] = obj
			elseif obj.int["4"] == 2 then --替补
				formation2[#formation2 + 1] = obj
			end
		end
		local function compareFunc(obj1, obj2)
			if obj1.int["1"] > obj2.int["1"] then
				return true
			end
			return false
		end
		utils.quickSort(formation1, compareFunc)
		utils.quickSort(formation2, compareFunc)
		local formationData = {}
		for key, obj in pairs(formation1) do
			formationData[#formationData + 1] = obj
		end
		for key, obj in pairs(formation2) do
			formationData[#formationData + 1] = obj
		end
		for key, obj in pairs(formationData) do
			local svItem = ui_svItem:clone()
			ui_scrollView:addChild(svItem)
			innerHieght = innerHieght + svItem:getContentSize().height + space
			
			local ui_cardFrame = ccui.Helper:seekNodeByName(svItem, "image_frame_card")
			local ui_cardName = ccui.Helper:seekNodeByName(svItem, "text_name")
			local ui_cardIcon = ui_cardFrame:getChildByName("image_card")
			local ui_bench = ccui.Helper:seekNodeByName(svItem, "image_bench")
			local ui_lusckNames, ui_lucks = {}, {}
			for i = 1, 6 do
				ui_lusckNames[i] = ccui.Helper:seekNodeByName(svItem, "text_luck" .. i .. "_name")
				ui_lucks[i] = ccui.Helper:seekNodeByName(svItem, "text_luck" .. i)
				ui_lusckNames[i]:setString("")
				ui_lucks[i]:setString("")
			end
			
			local instFormationId = obj.int["1"] --阵型实例ID
			local instCardId = obj.int["3"] --卡牌实例ID
			local type = obj.int["4"] --阵型类型 1:主力,2:替补
			local dictCardId = obj.int["6"] --卡牌字典ID
			local instCardData = net.InstPlayerCard[tostring(instCardId)] --卡牌实例数据
			local dictCardData = DictCard[tostring(dictCardId)] --卡牌字典数据
			local qualityId = instCardData.int["4"] --品阶ID
			
			ui_cardFrame:loadTexture(utils.getQualityImage(dp.Quality.card, qualityId, dp.QualityImageType.small))
			ui_cardName:setString(dictCardData.name)
			ui_cardIcon:loadTexture("image/" .. DictUI[tostring(dictCardData.smallUiId)].fileName)
			if type == 2 then
				ui_bench:loadTexture("ui/bz_zi02.png")
			else
				ui_bench:loadTexture("ui/bz_zi01.png")
			end
			local cardLucks = {}
			for k, objDcl in pairs(DictCardLuck) do
				if objDcl.cardId == dictCardId then
					cardLucks[#cardLucks + 1] = objDcl
				end
			end
			utils.quickSort(cardLucks, function(obj1, obj2) if obj1.id > obj2.id then return true end end)
			for key, dictLuck in pairs(cardLucks) do
				ui_lusckNames[key]:setString(dictLuck.name .. "：")
				ui_lucks[key]:setString(dictLuck.description)
				if utils.isCardLuck(dictLuck, instFormationId) then
					ui_lusckNames[key]:setTextColor(cc.c4b(0, 68, 255, 255))
					ui_lucks[key]:setTextColor(cc.c4b(0, 68, 255, 255))
				else
					ui_lusckNames[key]:setTextColor(cc.c4b(51, 25, 4, 255))
					ui_lucks[key]:setTextColor(cc.c4b(51, 25, 4, 255))
				end
			end
		end
		
		innerHieght = innerHieght + space
		if innerHieght < ui_scrollView:getContentSize().height then
			innerHieght = ui_scrollView:getContentSize().height
		end
		ui_scrollView:setInnerContainerSize(cc.size(ui_scrollView:getContentSize().width, innerHieght))
		local childs = ui_scrollView:getChildren()
		local prevChild = nil
		for i = 1, #childs do
			if i == 1 then
				childs[i]:setPosition(cc.p((ui_scrollView:getContentSize().width - childs[i]:getContentSize().width) / 2, ui_scrollView:getInnerContainerSize().height - childs[i]:getContentSize().height - space))
			else
				childs[i]:setPosition(cc.p((ui_scrollView:getContentSize().width - childs[i]:getContentSize().width) / 2, prevChild:getBottomBoundary() - childs[i]:getContentSize().height - space))
			end
			prevChild = childs[i]
		end
	end
	
	local btn_luck_all = rootPanel:getChildByName("btn_luck_all")
	if _btnAllLcuk_FLAG then
		btn_luck_all:setTitleText(btnAllLuck_Text2)
	else
		btn_luck_all:setTitleText(btnAllLuck_Text1)
	end
end

function UIFriend.init()
	rootPanel = ccui.Helper:seekNodeByName(UIFriend.Widget, "image_basemap")
	local btn_close = rootPanel:getChildByName("btn_close")
	local btn_exit = rootPanel:getChildByName("btn_exit")
	local btn_luck_all = rootPanel:getChildByName("btn_luck_all")
	btn_close:setPressedActionEnabled(true)
	btn_exit:setPressedActionEnabled(true)
	btn_luck_all:setPressedActionEnabled(true)
	
	uiPanel = rootPanel:getChildByName("image_base_card_up")
	uiCardItem = uiPanel:getChildByName("image_base_card"):clone()
	if uiCardItem:getReferenceCount() == 1 then
		uiCardItem:retain()
	end
	
	ui_scrollView = rootPanel:getChildByName("view_luck_info")
	ui_svItem = ui_scrollView:getChildByName("panel_all"):clone()
	if ui_svItem:getReferenceCount() == 1 then
		ui_svItem:retain()
	end
	
	local function btnEvent(sender, eventType)
		if eventType == ccui.TouchEventType.ended then
			if sender == btn_close or sender == btn_exit then
				AudioEngine.playEffect("sound/button.mp3")
				UIManager.popScene()
				_btnAllLcuk_FLAG = false
			elseif sender == btn_luck_all then
				showAllLuckOrCardLuck(not _btnAllLcuk_FLAG)
			end
		end
	end
	btn_close:addTouchEventListener(btnEvent)
	btn_exit:addTouchEventListener(btnEvent)
	btn_luck_all:addTouchEventListener(btnEvent)
	
	local ui_friendPanel = rootPanel:getChildByName("image_base_friend_info")
	for i = 1, 8 do
		ui_friends[i] = ui_friendPanel:getChildByName("image_frame_friend" .. i)
		ui_friends[i]:loadTexture("ui/card_small_white.png")
	end
	ui_activateLuckNum = rootPanel:getChildByName("image_luck_all"):getChildByName("label_all_number")
end

function UIFriend.setup()
	uiPanel:removeAllChildren()
	showAllLuckOrCardLuck(_btnAllLcuk_FLAG)
	_activateLuckNum = 0
	if net.InstPlayerFormation then
		local panelSize = uiPanel:getContentSize()
		for key, obj in pairs(net.InstPlayerFormation) do
			local instCardId = obj.int["3"] --卡牌实例ID
			local type = obj.int["4"] --1:主力,2:替补
			local position = obj.int["5"] --站位
			local dictCardId = net.InstPlayerCard[tostring(instCardId)].int["3"] --卡牌字典ID
			local instCardData = net.InstPlayerCard[tostring(instCardId)] --卡牌实例数据
			local cardItem = uiCardItem:clone()
			local cardFrame = cardItem:getChildByName("image_frame_card")
			local cardIcon = cardFrame:getChildByName("image_card")
			local cardLuckNum = ccui.Helper:seekNodeByName(cardItem, "label_luck")
			cardFrame:setTag(instCardId)
			cardFrame:loadTexture(utils.getQualityImage(dp.Quality.card, instCardData.int["4"], dp.QualityImageType.middle))
			cardIcon:loadTexture("image/" .. DictUI[tostring(DictCard[tostring(dictCardId)].bigUiId)].fileName)
			
			local cardLucks = {}
			for k, objLuck in pairs(DictCardLuck) do
				if objLuck.cardId == dictCardId then
					cardLucks[#cardLucks + 1] = objLuck
				end
			end
			local luckNum = 0
			for k, objLuck in pairs(cardLucks) do
				if utils.isCardLuck(objLuck, obj.int["1"]) then
					luckNum = luckNum + 1
				end
			end
			cardLuckNum:setString(tostring(luckNum))
			_activateLuckNum = _activateLuckNum + luckNum
			
			if type == FLAG_BENCH then
				position = position + 6
			end
			cardItem:setPosition(cc.p(POINTS[position].x + panelSize.width / 2, POINTS[position].y + panelSize.height / 2))
			uiPanel:addChild(cardItem, 0, position)
		end
	end
	
	_InstPlayerPartner = {}
	if net.InstPlayerPartner then
		for key, obj in pairs(net.InstPlayerPartner) do
			_InstPlayerPartner[obj.int["5"]] = obj
		end
	end
	
	local playerLevel = net.InstPlayer.int["4"] --玩家等级
	for i, item in pairs(ui_friends) do
		local tempData = {}
		for key, obj in pairs(DictLevelProp) do
			if i == obj.partnerCard then
				tempData[#tempData + 1] = obj
			end
		end
		utils.quickSort(tempData, function(obj1, obj2) if obj1.id > obj2.id then return true end return false end)
		local ui_cardIcon = item:getChildByName("image_friend")
		local ui_lock = item:getChildByName("image_level")
		if tempData[1] and playerLevel >= tempData[1].id then
			ui_cardIcon:setVisible(true)
			ui_lock:setVisible(false)
			item:setTouchEnabled(true)
			local ui_cardName = ccui.Helper:seekNodeByName(ui_cardIcon, "text_friend_name")
			local ui_luckImg = ui_cardIcon:getChildByName("image_luck")
			if _InstPlayerPartner[i] then
				--ui_luckImg:setVisible(true)
				local instCardId = _InstPlayerPartner[i].int["3"] --卡牌实例ID
				local dictCardId = _InstPlayerPartner[i].int["4"] --卡牌字典ID
				local instCardData = net.InstPlayerCard[tostring(instCardId)] --卡牌实例数据
				local dictCardData = DictCard[tostring(dictCardId)] --卡牌字典数据
				item:loadTexture(utils.getQualityImage(dp.Quality.card, instCardData.int["4"], dp.QualityImageType.small))
				ui_cardIcon:loadTexture("image/" .. DictUI[tostring(dictCardData.smallUiId)].fileName)
				ui_cardName:setString(dictCardData.name)
				
				local cardLucks = {}
				for k, objLuck in pairs(DictCardLuck) do
					if objLuck.cardId == dictCardId then
						cardLucks[#cardLucks + 1] = objLuck
					end
				end
				local luckNum = 0
				for k, objLuck in pairs(cardLucks) do
					if utils.isCardLuck(objLuck, nil, true) then
						luckNum = luckNum + 1
					end
				end
				--ui_luckImg:getChildByName("label_luck"):setString(tostring(luckNum))
--				_activateLuckNum = _activateLuckNum + luckNum
				
			else
				item:loadTexture("ui/card_small_white.png")
				ui_cardIcon:loadTexture("ui/frame_tianjia.png")
				--ui_luckImg:setVisible(false)
				ui_cardName:setString("")
			end
            ui_luckImg:setVisible(false)
			local function itemEvent(sender, eventType)
				if eventType == ccui.TouchEventType.ended then
					if _InstPlayerPartner[i] then
						UICardInfo.setUIParam(UIFriend, {_InstPlayerPartner[i].int["3"], i, _InstPlayerPartner[i].int["1"]})
						UIManager.pushScene("ui_card_info")
					else
						UICardChange.setUIParam(UICardChange.OperateType.Friend, i)
						UIManager.pushScene("ui_card_change")
					end
				end
			end
			item:addTouchEventListener(itemEvent)
		else
			ui_cardIcon:setVisible(false)
			ui_lock:setVisible(true)
			if tempData[1] then
				item:setTouchEnabled(false)
				ui_lock:getChildByName("label_level"):setString(tostring(tempData[1].id))
			else
				ui_lock:setVisible(false)
				item:setTouchEnabled(true)
				item:addTouchEventListener(function(sender, eventType)
					if eventType == ccui.TouchEventType.ended then
						UIManager.showToast("暂未开放！")
					end
				end)
			end
		end
	end
	ui_activateLuckNum:setString(tostring(_activateLuckNum))
end

function UIFriend.free()
	if uiCardItem and uiCardItem:getReferenceCount() >= 1 then
		uiCardItem:release()
		uiCardItem = nil
	end
	if ui_svItem and ui_svItem:getReferenceCount() >= 1 then
		ui_svItem:release()
		ui_svItem = nil
	end
	uiPanel:removeAllChildren()
	ui_scrollView:removeAllChildren()
end