UIActivityNormalExchange = {}

local ui_scrollView = nil
local ui_svItem = nil
local DictActivity = nil

local function cleanScrollView()
    if ui_svItem:getReferenceCount() == 1 then
        ui_svItem:retain()
    end
    ui_scrollView:removeAllChildren()
end

local function layoutScrollView(_listData, _initItemFunc)
	cleanScrollView()
	ui_scrollView:jumpToTop()
	local _innerHeight = 0
    local SCROLLVIEW_ITEM_SPACE = 10
    if _listData == nil then
        _listData = {}
    end
	for key, obj in pairs(_listData) do
		local scrollViewItem = ui_svItem:clone()
		_initItemFunc(scrollViewItem, obj)
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

local function setScrollViewItem(_item, _data)
    if _data == nil then
        return cclog("LUA ERROR: 服务发送来的数据错误！！！")
    end
    local _tempData = utils.stringSplit(_data, "|") --id|costItem|getItem|countLimit
    local itemData = nil
    if _tempData and #_tempData >= 4 then
        itemData = {
            id = tonumber(_tempData[1]),
            costItem = _tempData[2],
            getItem = _tempData[3],
            countLimit = tonumber(_tempData[4]),
            exchangeCount = tonumber(_tempData[5])
        }
    end
    _tempData = nil
    if itemData then
        local text_time = _item:getChildByName("image_base_hint"):getChildByName("text_time")
        if DictActivity and DictActivity.string["4"] ~= "" and DictActivity.string["5"] ~= "" then
            local _startTime = utils.changeTimeFormat(DictActivity.string["4"])
		    local _endTime = utils.changeTimeFormat(DictActivity.string["5"])
            text_time:setString(string.format("兑换时间 %02d.%02d %02d:%02d-%02d.%02d %02d:%02d", 
            _startTime[2], _startTime[3], _startTime[5], _startTime[6], _endTime[2], _endTime[3], _endTime[5], _endTime[6]))
        else
            text_time:setString("兑换时间 00.00 00:00-00.00 00:00")
        end
        local thingItems = {}
        for i = 1, 4 do
--            thingItems[i] = _item:getChildByName("image_frame_good" .. i)
            thingItems[4 + 1 - i] = _item:getChildByName("image_frame_good" .. i)
        end
        local thingsData = utils.stringSplit(itemData.costItem, ";")
        for i, thingItem in pairs(thingItems) do
            local _thingName = thingItem:getChildByName("text_name")
            local _thingIcon = thingItem:getChildByName("image_good")
            if thingsData[i] then
                
                local itemProps = utils.getItemProp(thingsData[i])
                if itemProps.name then
                    _thingName:setString(itemProps.name .. "\n×" .. itemProps.count)
                end
                if itemProps.smallIcon then
                    _thingIcon:loadTexture(itemProps.smallIcon)
                    utils.showThingsInfo(_thingIcon, itemProps.tableTypeId, itemProps.tableFieldId)
                end
                if itemProps.frameIcon then
                   thingItem:loadTexture(itemProps.frameIcon)
                end
                thingItem:setVisible(true)
            else
                _thingIcon:loadTexture("ui/mg_suo.png")
                _thingName:setVisible(false)
--                thingItem:setVisible(false)
            end
        end
        local giftItem = _item:getChildByName("image_frame_gift")
        local text_hint = giftItem:getChildByName("text_hint")
        local btn_exchange = giftItem:getChildByName("btn_exchange")
        local _giftName = giftItem:getChildByName("text_name")
        local _giftIcon = giftItem:getChildByName("image_gift")
        local itemProps = utils.getItemProp(itemData.getItem)
        if itemProps.name then
            _giftName:setString(itemProps.name .. "\n×" .. itemProps.count)
        end
        if itemProps.smallIcon then
            _giftIcon:loadTexture(itemProps.smallIcon)
            utils.showThingsInfo(_giftIcon, itemProps.tableTypeId, itemProps.tableFieldId)
        end
        if itemProps.frameIcon then
            giftItem:loadTexture(itemProps.frameIcon)
        end
        text_hint:setString("剩余兑换次数：" .. itemData.countLimit - itemData.exchangeCount)
        btn_exchange:setPressedActionEnabled(true)
        btn_exchange:addTouchEventListener(function(sender, eventType)
            if eventType == ccui.TouchEventType.ended then
                if itemData.countLimit - itemData.exchangeCount > 0 then
                    UIManager.showLoading()
                    netSendPackage({ header = StaticMsgRule.overflowExchange, msgdata = { int = {id = itemData.id} }}, function(_msgData)
                        UIManager.showToast("兑换成功！")
                        UIActivityNormalExchange.setup()
                    end)
                else
                    UIManager.showToast("兑换次数不足！")
                end
            end
        end)

--        if #thingsData < #thingItems then
--            local _thingPosX, _giftOffsetX = 0, 0
--            if #thingsData == 1 then
--                _thingPosX = thingItems[4]:getPositionX() - thingItems[1]:getPositionX()
--            elseif #thingsData == 2 then
--                _thingPosX = thingItems[3]:getPositionX() - thingItems[1]:getPositionX()
--            elseif #thingsData == 3 then
--                _thingPosX = thingItems[2]:getPositionX() - thingItems[1]:getPositionX()
--            end
--            for key = 1, #thingsData do
--                thingItems[key]:setPositionX(thingItems[key]:getPositionX() + _thingPosX)
--            end
--        end

    end
end

function UIActivityNormalExchange.init()
    local image_basemap = UIActivityNormalExchange.Widget:getChildByName("image_basemap")
    ui_scrollView = image_basemap:getChildByName("view_info")
    ui_svItem = ui_scrollView:getChildByName("image_base_gift"):clone()
end

function UIActivityNormalExchange.onActivity(_params)
    DictActivity = _params
end

function UIActivityNormalExchange.setup()
    cleanScrollView()
    UIManager.showLoading()
    netSendPackage({ header = StaticMsgRule.intoOverflowExchange, msgdata = { }}, function(_msgData)
        local msg = _msgData.msgdata.string["1"]
        if msg then
            layoutScrollView(utils.stringSplit(msg, "/"), setScrollViewItem)
        end
    end)
end

function UIActivityNormalExchange.free()
    DictActivity = nil
    cleanScrollView()
end