UIAllianceShop = {}

local userData = nil

local ui_scrollView = nil
local ui_svItem = nil

local _storeData = nil
local _instUnionStoreData = nil

local netCallbackFunc = nil

local function cleanScrollView(_isRelease)
	if _isRelease then
		if ui_svItem and ui_svItem:getReferenceCount() >= 1 then
	  	ui_svItem:release()
	  	ui_svItem = nil
	  end
	  if ui_scrollView then
	  	ui_scrollView:removeAllChildren()
	  	ui_scrollView = nil
		end
	else
		if ui_svItem:getReferenceCount() == 1 then
	  	ui_svItem:retain()
	  end
	  ui_scrollView:removeAllChildren()
	end
end

local function layoutScrollView(_listData, _initItemFunc)
    local SCROLLVIEW_ITEM_SPACE = 0
	cleanScrollView()
	ui_scrollView:jumpToTop()
	local _innerHeight = 0
    if not _listData then _listData = {} end
	for key, obj in pairs(_listData) do
		local scrollViewItem = ui_svItem:clone()
		_initItemFunc(scrollViewItem, obj, key)
		ui_scrollView:addChild(scrollViewItem)
		_innerHeight = _innerHeight + scrollViewItem:getContentSize().height + SCROLLVIEW_ITEM_SPACE
	end
	_innerHeight = _innerHeight + SCROLLVIEW_ITEM_SPACE
	if _innerHeight < ui_scrollView:getContentSize().height then
		_innerHeight = ui_scrollView:getContentSize().height
	end
	ui_scrollView:setInnerContainerSize(cc.size(ui_scrollView:getContentSize().width, _innerHeight))
	local childs = ui_scrollView:getChildren()
	local prevChild = nil
	for i = 1, #childs do
		if i == 1 then
			childs[i]:setPosition(ui_scrollView:getContentSize().width / 2, ui_scrollView:getInnerContainerSize().height - childs[i]:getContentSize().height / 2 - SCROLLVIEW_ITEM_SPACE)
		else
			childs[i]:setPosition(ui_scrollView:getContentSize().width / 2, prevChild:getBottomBoundary() - childs[i]:getContentSize().height / 2 - SCROLLVIEW_ITEM_SPACE)
		end
		prevChild = childs[i]
	end
	ActionManager.ScrollView_SplashAction(ui_scrollView)
end

local function getBuyData(_data)
    if _instUnionStoreData and _instUnionStoreData.string then
        if _data.type == 1 then
            local storeData1 = utils.stringSplit(_instUnionStoreData.string["4"], ";")
            for key, obj in pairs(storeData1) do
                local _tempData = utils.stringSplit(obj, "_") --[1]:id [2]:nums
                if _data.id == tonumber(_tempData[1]) then
                    local _isBuy = nil
                    if tonumber(_tempData[2]) - _data.sellValue == 0 then
                        _isBuy = true
                    end
                    return {isBuy = _isBuy, buyNum = tonumber(_tempData[2])}
                end
            end
            local storeData2 = utils.stringSplit(_instUnionStoreData.string["7"], ";")
            for key, obj in pairs(storeData2) do
                local _tempData = utils.stringSplit(obj, "_") --[1]:id [2]:nums
                if _data.id == tonumber(_tempData[1]) then
                    local _isBuy = nil
                    if tonumber(_tempData[2]) - _data.sellValue == 0 then
                        _isBuy = true
                    end
                    return {isBuy = _isBuy, buyNum = tonumber(_tempData[2])}
                end
            end
        elseif _data.type == 3 then
            local storeData = utils.stringSplit(_instUnionStoreData.string["6"], ";")
            for key, _dictId in pairs(storeData) do
                if _data.id == tonumber(_dictId) then
                    return {isBuy = true, buyNum = _data.sellValue}
                end
            end
        end
    end
    return {}
end

local function setScrollViewItem(_item, _data, _index)
    _item:setName(_data.type .. "&" .. _index)
    local itemProps = utils.getItemProp(_data.tableTypeId, _data.tableFieldId)
	local ui_itemFrame = _item:getChildByName("image_frame_chip")
	local ui_itemIcon = ui_itemFrame:getChildByName("image_chip")
	local ui_itemValue = ui_itemFrame:getChildByName("text_number")
	local ui_itemFlag = ui_itemFrame:getChildByName("image_sui")
	local ui_itemName = _item:getChildByName("text_chip_name")
	local ui_itemHint = _item:getChildByName("text_hint")
	local ui_itemCount = _item:getChildByName("text_number")
	local ui_offerNums = _item:getChildByName("image_fire"):getChildByName("text_number")
    local ui_goldImg = _item:getChildByName("image_gold")
    local ui_goldNums = ui_goldImg:getChildByName("text_number")

	ui_itemFrame:loadTexture(itemProps.frameIcon)
	ui_itemName:setString(itemProps.name)
	ui_itemIcon:loadTexture(itemProps.smallIcon)

    --zy 联盟商店查看详细
    utils.showThingsInfo(ui_itemIcon, _data.tableTypeId, _data.tableFieldId)

	if itemProps.flagIcon then
		ui_itemFlag:loadTexture(itemProps.flagIcon)
		ui_itemFlag:setVisible(true)
	else
		ui_itemFlag:setVisible(false)
	end
    ui_itemCount:setString("")
	if itemProps.flagIcon and _data.tableTypeId == StaticTableType.DictThing then
        local dictData = DictThing[tostring(_data.tableFieldId)]
        if dictData and dictData.id >= 200 and dictData.id < 300 then
            local tempData = DictEquipment[tostring(dictData.equipmentId)]
            if tempData then
                local itemCountDesc = "(" .. utils.getThingCount(dictData.id) .. "/" .. DictEquipQuality[tostring(tempData.equipQualityId)].thingNum .. ")"
		        ui_itemCount:setString(itemCountDesc)
            end
        end
	end
    ui_itemValue:setString("×" .. _data.value)
    local _buyData = getBuyData(_data)
    if userData.allianceLevel >= _data.unionLevel then
        if _data.sellType == 2 then
            ui_itemHint:setString(string.format("今日可购买%d次", _buyData.buyNum and _data.sellValue - _buyData.buyNum or _data.sellValue))
        else
            ui_itemHint:setString(string.format("可购买%d次", _buyData.buyNum and _data.sellValue - _buyData.buyNum or _data.sellValue))
        end
    else
	    ui_itemHint:setString(string.format("联盟达到%d级", _data.unionLevel))
    end
    ui_offerNums:setString("×".._data.buyOffer)
    if _data.buyGold > 0 then
        ui_goldNums:setString("×".._data.buyGold)
        ui_goldImg:setVisible(true)
    else
        ui_goldImg:setVisible(false)
    end

    local _itemBtn = _item:getChildByName("btn_lineup")
    _itemBtn:setTitleText(_buyData.isBuy and "已购买" or "购买")
	_itemBtn:setPressedActionEnabled(true)
    if _buyData.isBuy then
        _itemBtn:setBright(false)
        _itemBtn:setTouchEnabled(false)
    else
        _itemBtn:setTouchEnabled(true)
        if userData.allianceLevel >= _data.unionLevel then
            _itemBtn:setBright(true)
        else
            _itemBtn:setBright(false)
        end
    end
	_itemBtn:addTouchEventListener(function(_sender, _eventType)
		if _eventType == ccui.TouchEventType.ended then
            if userData.allianceLevel >= _data.unionLevel then
                if net.InstUnionMember.int["5"] >= _data.buyOffer then
                    if net.InstPlayer.int["5"] < _data.buyGold then
                        UIManager.showToast("元宝不足！")
                        return
                    end
                    local function buyThings()
                        UIManager.showLoading()
	                    netSendPackage({header = StaticMsgRule.buyUnionStore, msgdata = {int={
                            instUnionMemberId=net.InstUnionMember.int["1"],instUnionStoreId=_instUnionStoreData and _instUnionStoreData.int["1"] or 0,unionStoreId=_data.id
                         }}}, netCallbackFunc)
                    end
                    UIAllianceHintShop.show({buyOffer = _data.buyOffer, buyGold = _data.buyGold, itemName = itemProps.name, itemFrame = itemProps.frameIcon, itemIcon = itemProps.smallIcon, itemCount = _data.value, callbackFunc = buyThings})
                else
                    UIManager.showToast("联盟贡献不足！")
                end
            else
                UIManager.showToast("联盟等级不足！")
            end
        end
    end)
end

netCallbackFunc = function(msgData)
	local code = tonumber(msgData.header)
	if code == StaticMsgRule.unionStore then
        if msgData.msgdata.int and msgData.msgdata.int["1"] then
            _instUnionStoreData = msgData.msgdata
        else
            _instUnionStoreData = nil
        end
        local childs = ui_scrollView:getChildren()
        if childs and childs[1] and #utils.stringSplit(childs[1]:getName(), "&") > 1 then
            local _type = tonumber(utils.stringSplit(childs[1]:getName(), "&")[1])
            local _temp1, _temp2 = {}, {}
            for key, obj in pairs(_storeData[_type]) do
                if getBuyData(obj).isBuy then
                    _temp2[#_temp2 + 1] = obj
                else
                    _temp1[#_temp1 + 1] = obj
                end
            end
            _storeData[_type] = {}
            for i = 1, (#_temp1 + #_temp2) do
                local _tempOjb = nil
                if _temp1[i] then
                    _tempOjb = _temp1[i]
                else
                    _tempOjb = _temp2[i - #_temp1]
                end
                _storeData[_type][i] = _tempOjb
            end
            _temp1 = nil
            _temp2 = nil
            for key, obj in pairs(childs) do
                local _temp = utils.stringSplit(obj:getName(), "&")
                if _temp and #_temp > 1 then
                    setScrollViewItem(obj, _storeData[tonumber(_temp[1])][tonumber(_temp[2])], tonumber(_temp[2]))
                end
            end
        end
    elseif code == StaticMsgRule.buyUnionStore then
        UIAlliance.refreshOffer()
        UIManager.showToast("购买成功！")
        UIAllianceShop.setup()
    end
end

function UIAllianceShop.init()
    local image_basemap = UIAllianceShop.Widget:getChildByName("image_basemap")
	local btn_close = image_basemap:getChildByName("btn_close")
	btn_close:setPressedActionEnabled(true)
	btn_close:addTouchEventListener(function(sender, eventType)
		if eventType == ccui.TouchEventType.ended then
			UIManager.popScene()
		end
	end)
	ui_scrollView = image_basemap:getChildByName("view_award_lv")
	ui_svItem = ui_scrollView:getChildByName("image_base_good"):clone()

    _storeData = {} -- 1-道具 2-限时 3-奖励
    for key, obj in pairs(DictUnionStore) do
        if not _storeData[obj.type] then
            _storeData[obj.type] = {}
        end
        _storeData[obj.type][#_storeData[obj.type] + 1] = obj
    end
    for i = 1, 3 do
        if _storeData[i] then
            utils.quickSort(_storeData[i], function(obj1, obj2) if obj1.rank > obj2.rank then return true end end)
        end
    end
end

function UIAllianceShop.setup()
    local image_basemap = UIAllianceShop.Widget:getChildByName("image_basemap")
	local offerCount = image_basemap:getChildByName("image_fire_all"):getChildByName("text_number")
	offerCount:setString(tostring(net.InstUnionMember.int["5"]))

    local _prevUIBtn = nil
	local btn_purple = image_basemap:getChildByName("btn_purple")
	local btn_orange = image_basemap:getChildByName("btn_orange")
	local btn_reward = image_basemap:getChildByName("btn_reward")
    -----------------------去掉限时项---------------------------
    btn_orange:setVisible(false)
    btn_reward:setPosition(btn_orange:getPosition())
    -----------------------去掉限时项---------------------------
	local function onButtonEvent(sender, eventType)
        if eventType == ccui.TouchEventType.ended then
            if _prevUIBtn == sender then
				return
			end
			_prevUIBtn = sender
            local _index = 1
            sender:loadTextures("ui/yh_btn02.png", "ui/yh_btn02.png")
			if sender == btn_purple then
                _index = 1
                sender:getChildByName("text_purple"):setTextColor(cc.c4b(51, 25, 4, 255))
				btn_orange:getChildByName("text_orange"):setTextColor(cc.c4b(255, 255, 255, 255))
				btn_orange:loadTextures("ui/yh_btn01.png", "ui/yh_btn02.png")
				btn_reward:getChildByName("text_reward"):setTextColor(cc.c4b(255, 255, 255, 255))
				btn_reward:loadTextures("ui/yh_btn01.png", "ui/yh_btn02.png")
                UIManager.showLoading()
	            netSendPackage({header = StaticMsgRule.unionStore, 
		            msgdata = {int={instUnionStoreId=_instUnionStoreData and _instUnionStoreData.int["1"] or 0}}}, netCallbackFunc)
            elseif sender == btn_orange then
                _index = 2
                sender:getChildByName("text_orange"):setTextColor(cc.c4b(51, 25, 4, 255))
				btn_purple:getChildByName("text_purple"):setTextColor(cc.c4b(255, 255, 255, 255))
				btn_purple:loadTextures("ui/yh_btn01.png", "ui/yh_btn02.png")
				btn_reward:getChildByName("text_reward"):setTextColor(cc.c4b(255, 255, 255, 255))
				btn_reward:loadTextures("ui/yh_btn01.png", "ui/yh_btn02.png")
            elseif sender == btn_reward then
                _index = 3
                sender:getChildByName("text_reward"):setTextColor(cc.c4b(51, 25, 4, 255))
				btn_purple:getChildByName("text_purple"):setTextColor(cc.c4b(255, 255, 255, 255))
				btn_purple:loadTextures("ui/yh_btn01.png", "ui/yh_btn02.png")
				btn_orange:getChildByName("text_orange"):setTextColor(cc.c4b(255, 255, 255, 255))
				btn_orange:loadTextures("ui/yh_btn01.png", "ui/yh_btn02.png")
                UIManager.showLoading()
	            netSendPackage({header = StaticMsgRule.unionStore, 
		            msgdata = {int={instUnionStoreId=_instUnionStoreData and _instUnionStoreData.int["1"] or 0}}}, netCallbackFunc)
            end
            layoutScrollView(_storeData[_index], setScrollViewItem)
        end
    end
    btn_purple:addTouchEventListener(onButtonEvent)
	btn_orange:addTouchEventListener(onButtonEvent)
	btn_reward:addTouchEventListener(onButtonEvent)
    local _tempItem = ui_scrollView:getChildren()[1]
    if _tempItem and #utils.stringSplit(_tempItem:getName(), "&") > 1 then
        local _type = utils.stringSplit(_tempItem:getName(), "&")[1]
        if tonumber(_type) == 1 then
            btn_purple:releaseUpEvent()
        elseif tonumber(_type) == 2 then
            btn_orange:releaseUpEvent()
        elseif tonumber(_type) == 3 then
            btn_reward:releaseUpEvent()
        end
    else
	    btn_purple:releaseUpEvent()
    end
end

function UIAllianceShop.free()
    cleanScrollView(true)
    userData = nil
    _storeData = nil
    if _instUnionStoreData and _instUnionStoreData.string then
        _instUnionStoreData.string = nil
    end
end

function UIAllianceShop.show(_tableParams)
    userData = _tableParams
    UIManager.pushScene("ui_alliance_shop")
end