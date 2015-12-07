UIBoxGet = {}

local ui_scrollView = nil
local ui_svItem = nil
local _thingData = nil

local function cleanScrollView()
	-- if ui_svItem:getReferenceCount() == 1 then
	-- 	ui_svItem:retain()
	-- end
	ui_scrollView:removeAllChildren()
end

local function setScrollViewItem(item, data)
	local ui_itemIcon = item:getChildByName("image_good")
    local ui_itemNum = item:getChildByName("num")
	local ui_itemType = ui_itemIcon:getChildByName("image_type")
	local ui_itemName = ccui.Helper:seekNodeByName(item, "text_name")
    local itemProps = utils.getItemProp(data.tableTypeId, data.tableFieldId)
    if itemProps.frameIcon then
        item:loadTexture(itemProps.frameIcon)
    end
    if itemProps.smallIcon then
        ui_itemIcon:loadTexture(itemProps.smallIcon)
    end
    if itemProps.name then
        ui_itemName:setString(itemProps.name)
    end
    if itemProps.flagIcon then
        ui_itemType:loadTexture(itemProps.flagIcon)
        ui_itemType:setVisible(true)
    else
        ui_itemType:setVisible(false)
    end
    if data.value > 1 then
        ui_itemNum:setVisible(true)
        ui_itemNum:setFontName(dp.FONT)
        ui_itemNum:setString("x"..data.value)
    else
        ui_itemNum:setVisible(false)
    end
end

function UIBoxGet.init()
	local btn_close = ccui.Helper:seekNodeByName(UIBoxGet.Widget, "btn_close")
	local btn_sure = ccui.Helper:seekNodeByName(UIBoxGet.Widget, "btn_sure")
	btn_close:setPressedActionEnabled(true)
	btn_sure:setPressedActionEnabled(true)
	local function onBtnEvent(sender, eventType)
		if eventType == ccui.TouchEventType.ended then
			AudioEngine.playEffect("sound/button.mp3")
			if sender == btn_close or sender == btn_sure then
				UIManager.popScene()
			end
		end
	end
	btn_close:addTouchEventListener(onBtnEvent)
	btn_sure:addTouchEventListener(onBtnEvent)

	ui_scrollView = ccui.Helper:seekNodeByName(UIBoxGet.Widget, "view_good")
	ui_svItem = ui_scrollView:getChildByName("image_frame_good")
end

function UIBoxGet.setup()
	cleanScrollView()

    ccui.Helper:seekNodeByName(UIBoxGet.Widget, "text_get"):setString("恭喜您获得如下奖励")

	if _thingData and #_thingData > 0 then
		for key, obj in pairs(_thingData) do
			local thingItem = ui_svItem:clone()
			setScrollViewItem(thingItem, obj)
			ui_scrollView:addChild(thingItem)
		end

		local innerHieght, space, row = 0, 10, 4
		local svItemHeight = ui_svItem:getContentSize().height + ui_svItem:getChildByName("image_di_name"):getContentSize().height
		local childs = ui_scrollView:getChildren()
		if #childs < row then
			innerHieght = svItemHeight + space
		elseif #childs % row == 0 then
			innerHieght = (#childs / row) * (svItemHeight + space) + space
		else
			innerHieght = math.ceil(#childs / row) * (svItemHeight + space) + space
		end
		innerHieght = innerHieght + space
		if innerHieght < ui_scrollView:getContentSize().height then
			innerHieght = ui_scrollView:getContentSize().height
		end
		ui_scrollView:setInnerContainerSize(cc.size(ui_scrollView:getContentSize().width, innerHieght))

		local prevChild = nil
		local _tempI, x, y = 1, 0, 0
		for i = 1, #childs do
			x = _tempI * (ui_scrollView:getContentSize().width / row) - (ui_scrollView:getContentSize().width / row) / 2
			_tempI = _tempI + 1
			if i < row then
				y = ui_scrollView:getInnerContainerSize().height - childs[i]:getContentSize().height / 2 - space
				prevChild = childs[i]
				childs[i]:setPosition(cc.p(x, y))
			elseif i % row == 0 then
				childs[i]:setPosition(cc.p(x, y))
				y = prevChild:getBottomBoundary() - childs[i]:getContentSize().height / 2 - childs[i]:getChildByName("image_di_name"):getContentSize().height - space
				_tempI = 1
				prevChild = childs[i]
			else
				y = prevChild:getBottomBoundary() - childs[i]:getContentSize().height / 2 - childs[i]:getChildByName("image_di_name"):getContentSize().height - space
				childs[i]:setPosition(cc.p(x, y))
			end
		end
	end
end

function UIBoxGet.setData(data)
	_thingData = data
end

function UIBoxGet.free()
	cleanScrollView()
	_thingData = nil
end