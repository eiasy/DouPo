UIActivityTrial = {
    ACTIVITY_DAY_COUNT = 7 --活动显示总天数
}

local DAY_COUNT = 5 --总天数
local SCROLLVIEW_ITEM_SPACE = 10

local ui_scrollView = nil
local ui_svItemGood, ui_svItemFight = nil, nil

local _curShowDayIndex = 1
local _curShowDayOfItemIndex = nil
local _curShowTitleIndex = nil
local _countdownTime = 0
local _curDayData = nil
local _curAwards = nil
local _totalRechargeNums = 0

local netCallbackFunc = nil
local getProgress = nil

UIActivityTrial.hintFlag = false

local function cleanScrollView()
  if ui_svItemGood:getReferenceCount() == 1 then
  	ui_svItemGood:retain()
  end
  if ui_svItemFight:getReferenceCount() == 1 then
  	ui_svItemFight:retain()
  end
  ui_scrollView:removeAllChildren()
end

local function countDowun()
    _countdownTime = _countdownTime - 1
    if _countdownTime < 0 then
        _countdownTime = 0
    end
    
    if UIActivityTrial.Widget and UIActivityTrial.Widget:getParent() then
        local image_basemap = UIActivityTrial.Widget:getChildByName("image_basemap")
        if image_basemap then
            local _tempTime = utils.stringSplit(net.InstPlayer.registerTime, " ")
            local _registerTime = utils.GetTimeByDate(_tempTime[1] .. " 00:00:00")
            local _curTime = utils.getCurrentTime()
            local _taskDayNum = 0
            for i = 1, DAY_COUNT do
		        local dayButton = image_basemap:getChildByName("btn_day" .. i)
                local dayImage = dayButton:getChildByName("image_day")
                local dayLock = dayButton:getChildByName("image_suo")
                if _curTime >= _registerTime + ((i-1)*24*60*60) then
                    dayButton:setTouchEnabled(true)
                    utils.GrayWidget(dayImage, false)
                    dayLock:setVisible(false)
                    if i > _taskDayNum then
                        _taskDayNum = i
                    end
                else
                    dayButton:setTouchEnabled(false)
                    utils.GrayWidget(dayImage, true)
                    dayLock:setVisible(true)
                    dayButton:getChildByName("image_hint"):setVisible(false)
                end
            end
            local image_frame_good = image_basemap:getChildByName("image_frame_good")
            local ui_textTime = image_frame_good:getChildByName("text_time")
            if _curTime > _registerTime + (DAY_COUNT*24*60*60) then
--                local day = math.floor(_countdownTime / 3600 / 24) --天
--	            local hour = math.floor(_countdownTime / 3600 % 24) --小时
                local hour = math.floor(_countdownTime / 3600 % (24 * (UIActivityTrial.ACTIVITY_DAY_COUNT - DAY_COUNT))) --小时
	            local minute = math.floor(_countdownTime / 60 % 60) --分
	            local second = math.floor(_countdownTime % 60) --秒
                ui_textTime:setString(string.format("距离试炼期结束还有：%02d:%02d:%02d", hour, minute, second))
            else
                local _tableTime = os.date("*t", _curTime)
                local _endTime = utils.GetTimeByDate(_tableTime.year .. "-" .. _tableTime.month .. "-" .. _tableTime.day .. " 23:59:59")
                local _tempCountdown = _endTime - _curTime
                if _tempCountdown < 0 then
                    _tempCountdown = 0
                end
                local _hour = math.floor(_tempCountdown / 3600 % 24) --小时
	            local _minute = math.floor(_tempCountdown / 60 % 60) --分
	            local _second = math.floor(_tempCountdown % 60) --秒
                ui_textTime:setString(string.format("距离第%d日结束还有：%02d:%02d:%02d", _taskDayNum, _hour, _minute, _second))
            end
        end
    end
end

local function layoutScrollView(_listData, _initItemFunc)
	cleanScrollView()
	ui_scrollView:jumpToTop()
	local _innerHeight = 0
	for key, obj in pairs(_listData) do
		local scrollViewItem = (obj.type == 0) and ui_svItemFight:clone() or ui_svItemGood:clone()
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

local function isGetAward(_id)
    if net.InstPlayerTryToPractice then
        local getTryIds = utils.stringSplit(net.InstPlayerTryToPractice.string["3"], ";")
        for key, obj in pairs(getTryIds) do
            if _id == tonumber(obj) then
                return true
            end
        end
    end
    return false
end

local function refreshMoney()
	local image_bian = UIActivityTrial.Widget:getChildByName("image_basemap"):getChildByName("image_title")
	ccui.Helper:seekNodeByName(image_bian, "text_gold_number"):setString(tostring(net.InstPlayer.int["5"]))
	ccui.Helper:seekNodeByName(image_bian, "text_silver_number"):setString(net.InstPlayer.string["6"])
end

local function getDayProgress()
    local _progress = 0
    if net.InstPlayerTryToPractice then
        local getTryIds = utils.stringSplit(net.InstPlayerTryToPractice.string["3"], ";")
        for key, obj in pairs(getTryIds) do
            if DictTryToPractice[obj] then
                local tryPracticeTypeData = DictTryToPracticeType[tostring(DictTryToPractice[obj].tryToPracticeTypeId)]
                if tryPracticeTypeData then
                    if tryPracticeTypeData.day == _curShowDayIndex and not string.find(tryPracticeTypeData.sname, "hotShopping") then
                        _progress = _progress + 1
                    end
                end
            end
        end
    end
    return _progress
end

local function currentDayAward(_data)
	local image_basemap = UIActivityTrial.Widget:getChildByName("image_basemap")
	local image_frame_good = image_basemap:getChildByName("image_frame_good")
	local image_good = image_frame_good:getChildByName("image_good")
	local btn_get = image_frame_good:getChildByName("btn_get")
    local text_title = image_frame_good:getChildByName("text_title")
	local text_hint = image_frame_good:getChildByName("text_hint")
	local text_number = image_frame_good:getChildByName("text_number")
    local image_ygq = image_frame_good:getChildByName("image_ygq")
    local image_loading = image_frame_good:getChildByName("image_loading")
	btn_get:setPressedActionEnabled(true)
    local _isGetAward = false
    if net.InstPlayerTryToPractice and net.InstPlayerTryToPractice.string["5"] then
        local getDayIds = utils.stringSplit(net.InstPlayerTryToPractice.string["5"], ";")
        for key, obj in pairs(getDayIds) do
            if _curShowDayIndex == tonumber(obj) then
                _isGetAward = true
                break
            end
        end
    end
    if _isGetAward then
        image_ygq:setVisible(false)
        text_hint:setVisible(false)
        text_title:setVisible(true)
        image_loading:setVisible(false)
        btn_get:setTouchEnabled(false)
        btn_get:setBright(false)
        btn_get:setVisible(true)
        btn_get:setTitleText("已领取")
    else
        local _tempTime = utils.stringSplit(net.InstPlayer.registerTime, " ")
        local _registerTime = utils.GetTimeByDate(_tempTime[1] .. " 00:00:00")
        local _curTime = utils.getCurrentTime()
        if _curTime > _registerTime + (_curShowDayIndex*24*60*60) then
            text_title:setVisible(false)
            btn_get:setVisible(false)
            text_hint:setVisible(false)
            image_loading:setVisible(false)
            image_ygq:setVisible(true)
        else
            image_ygq:setVisible(false)
            text_title:setVisible(true)
            btn_get:setTouchEnabled(true)
            btn_get:setBright(true)
            btn_get:setTitleText("领取")
            local _progress = getDayProgress()
            if _progress >= _data.count then
                btn_get:setVisible(true)
	            text_hint:setVisible(false)
                image_loading:setVisible(false)
            else
                btn_get:setVisible(false)
                image_loading:setVisible(true)
	            text_hint:setVisible(true)
                text_hint:setString(string.format("任务完成数%d/%d", _progress, _data.count))
                image_loading:getChildByName("bar_loading"):setPercent(utils.getPercent(_progress, _data.count))
            end
        end
    end
	local _things = ""
	if _data then
		_things = _data.rewards
	else
		--******************XXXXXXXXXXXXXX
	end
    local itemProps = utils.getItemProp(_data.rewards)
	image_frame_good:loadTexture(itemProps.frameIcon)
	image_good:loadTexture(itemProps.smallIcon)
	utils.showThingsInfo(image_good, itemProps.tableTypeId, itemProps.tableFieldId)
	if itemProps.count > 1 then
		text_number:setVisible(true)
		text_number:setString("×" .. itemProps.count)
	else
		text_number:setVisible(false)
	end
    if btn_get:isVisible() then
        btn_get:addTouchEventListener(function(sender, eventType)
            if eventType == ccui.TouchEventType.ended then
                if _countdownTime <= 0 then
                    return UIManager.showToast("活动已结束！")
                end
--              UIManager.showToast("完成当天任务，领取")
                UIManager.showLoading()
                _curDayData = _data
	            netSendPackage({header = StaticMsgRule.tryToPracticeAward, msgdata = 
                    {int={id=_data.id,instPlayerTryToPracticeId=net.InstPlayerTryToPractice and net.InstPlayerTryToPractice.int["1"] or 0}}}, netCallbackFunc)
            end
        end)
    end
end

local function setScrollViewItem(_item, _data)
    if _data.type == 0 then
	else
        local ui_title = _item:getChildByName("text_title")
        ui_title:setString(_data.name)
        ui_title:enableOutline(cc.c4b(51, 25, 4, 255), 2)
        local thingItem = _item:getChildByName("image_frame_good1")
        local itemProps = utils.getItemProp(_data.rewards)
        thingItem:loadTexture(itemProps.frameIcon)
        thingItem:getChildByName("image_good"):loadTexture(itemProps.smallIcon)
        thingItem:getChildByName("text_number"):setString("×" .. itemProps.count)
        utils.showThingsInfo(thingItem:getChildByName("image_good"), itemProps.tableTypeId, itemProps.tableFieldId)
        _item:getChildByName("text_info"):setString(string.format("奖励：%s×%d", itemProps.name, itemProps.count))
        local btn_exchange = _item:getChildByName("btn_exchange")
        local _progress = getProgress(_data)
        if _progress >= _data.progress then
            _item:getChildByName("text_schedule"):setString("")
        else
            _item:getChildByName("text_schedule"):setString(_progress .. "/" .. _data.progress)
        end
        if _data.type == 1 then
            _item:getChildByName("image_wdc"):setVisible(false)
            btn_exchange:setVisible(true)
            btn_exchange:setTitleText((_progress >= _data.progress) and "交任务" or "战斗")
        else
            btn_exchange:setVisible((_progress >= _data.progress) and true or false)
            _item:getChildByName("image_wdc"):setVisible(not btn_exchange:isVisible())
            btn_exchange:setTitleText("交任务")
        end
        if isGetAward(_data.id) then
            _item:getChildByName("image_wdc"):setVisible(false)
            btn_exchange:setTitleText("已完成")
            _item:getChildByName("text_schedule"):setString("")
            btn_exchange:setTouchEnabled(false)
            btn_exchange:setVisible(true)
            btn_exchange:setBright(false)
        end
        if btn_exchange:isVisible() then
            btn_exchange:setPressedActionEnabled(true)
            btn_exchange:addTouchEventListener(function(sender, eventType)
                if eventType == ccui.TouchEventType.ended then
                    if _countdownTime <= 0 then
                        return UIManager.showToast("活动已结束！")
                    end
                    if sender:getTitleText() == "交任务" then
--                        UIManager.showToast("达标领取！")
                        UIManager.showLoading()
                        _curShowDayOfItemIndex = _data.day
                        _curAwards = _data.rewards
	                    netSendPackage({header = StaticMsgRule.tryToPractice, 
		                    msgdata = {int={id=_data.id,instPlayerTryToPracticeId=net.InstPlayerTryToPractice and net.InstPlayerTryToPractice.int["1"] or 0}}}, netCallbackFunc)
                    else
--                        UIManager.showToast("去战斗啊！~")
                        utils.sendFightData({tryId=_data.id,index=_curShowDayIndex.."_".._data.day,fightId=_data.conditions}, dp.FightType.FIGHT_TRY_PRACTICE, function(_isWin, _param)
                            local animationId = 11
                            if _isWin == 1 then
--                                UIManager.showToast("战斗胜利！~")
                                animationId = 11
                            else
--                                UIManager.showToast("战斗失败！~")
                                animationId = 12
                            end
                            local animation = ActionManager.getUIAnimation(animationId, function(armature)
                                if _param then
                                    local tParams = utils.stringSplit(_param.index, "_")
                                    _curShowDayIndex = tonumber(tParams[1])
                                    _curShowDayOfItemIndex = tonumber(tParams[2])
                                end
                                UIManager.gameLayer:runAction(cc.Sequence:create(cc.DelayTime:create(0.1), cc.CallFunc:create(function()
                                    if armature and armature:getParent() then armature:removeFromParent() end
                                    UIManager.showWidget("ui_notice", "ui_menu", "ui_activity_trial")
                                end)))
                            end)
                            animation:setPosition(cc.p(UIManager.screenSize.width / 2, UIManager.screenSize.height * 0.75))
                            UIManager.gameLayer:addChild(animation, 1000000)
                        end)
				        UIFightMain.loading()
                    end
                end
            end)
        end
	end
end

local function showHotShoppingUI(_data, _index)
    cleanScrollView()
    local hotShoppingData = {}
    for key, obj in pairs(DictTryToPractice) do
        if obj.tryToPracticeTypeId == _data.id then
            local _tempObj = utils.stringSplit(obj.conditions, "_")
            hotShoppingData[tonumber(_tempObj[1])] = obj
            _tempObj = nil
        end
    end
    local image_basemap = UIActivityTrial.Widget:getChildByName("image_basemap")
    for key, obj in pairs(hotShoppingData) do
        local _item = image_basemap:getChildByName("image_base_good"..key)
        if _item then
            _item:setVisible(true)
            ccui.Helper:seekNodeByName(_item, "text_title"):setString(obj.name)
            local _tempObj = utils.stringSplit(obj.rewards, ";")
            for _k, _o in pairs(_tempObj) do
                local itemProps = utils.getItemProp(_o)
                local uiFrame = _item:getChildByName("image_frame_good".._k)
                uiFrame:loadTexture(itemProps.frameIcon)
                uiFrame:getChildByName("text_good"):setString(itemProps.name)
                uiFrame:getChildByName("image_good"):loadTexture(itemProps.smallIcon)
                ccui.Helper:seekNodeByName(uiFrame, "text_number"):setString(tostring(itemProps.count))
                utils.showThingsInfo(uiFrame:getChildByName("image_good"), itemProps.tableTypeId, itemProps.tableFieldId)
            end
            local btn_exchange = _item:getChildByName("btn_exchange")
            local _btnEnabled = true
            local _isGetAward = isGetAward(obj.id)
            if key == 1 or key == 2 then
                if _isGetAward then
                    btn_exchange:setTitleText("已领取")
                    _btnEnabled = false
                    if _item:getChildByName("text_schedule") then
                        _item:getChildByName("text_schedule"):setString("")
                    end
                else
                    btn_exchange:setTitleText("领取")
                end
                if key == 2 then
                    if _totalRechargeNums >= obj.progress then
                        _item:getChildByName("text_schedule"):setString("")
                        _item:getChildByName("image_wdc"):setVisible(false)
                        btn_exchange:setVisible(true)
                    else
                        _item:getChildByName("text_schedule"):setString(_totalRechargeNums .. "/" .. obj.progress)
                        _item:getChildByName("image_wdc"):setVisible(true)
                        btn_exchange:setVisible(false)
                    end
                end
            elseif key == 3 then
                local oldPrice = _item:getChildByName("image_gold_yuan"):getChildByName("text_gold_number")
                local nowPrice = _item:getChildByName("image_gold_xian"):getChildByName("text_gold_number")
                oldPrice:setString(utils.stringSplit(obj.conditions, "_")[2])
                nowPrice:setString(tostring(obj.progress))
                if _isGetAward then
                    btn_exchange:setTitleText("已购买")
                    _btnEnabled = false
                else
                    btn_exchange:setTitleText("抢购")
                end
            end
            btn_exchange:setTouchEnabled(_btnEnabled)
            btn_exchange:setBright(_btnEnabled)
            btn_exchange:setPressedActionEnabled(true)
            btn_exchange:addTouchEventListener(function(sender, eventType)
                if eventType == ccui.TouchEventType.ended then
                    if _countdownTime <= 0 then
                        return UIManager.showToast("活动已结束！")
                    end
                    if key == 1 or key == 2 then
                        UIManager.showLoading()
                        _curShowDayOfItemIndex = _index
                        _curAwards = obj.rewards
	                    netSendPackage({header = StaticMsgRule.tryToPractice, 
		                    msgdata = {int={id=obj.id,instPlayerTryToPracticeId=net.InstPlayerTryToPractice and net.InstPlayerTryToPractice.int["1"] or 0}}}, netCallbackFunc)
                    elseif key == 3 then
                        if net.InstPlayer.int["5"] >= obj.progress then
                            UIManager.showLoading()
                            _curShowDayOfItemIndex = _index
                            _curAwards = obj.rewards
	                        netSendPackage({header = StaticMsgRule.tryToPracticeBuy, msgdata = 
                                {int={id=obj.id,instPlayerTryToPracticeId=net.InstPlayerTryToPractice and net.InstPlayerTryToPractice.int["1"] or 0}}}, netCallbackFunc)
                        else
                            UIManager.showToast("元宝不足！")
                        end
                    end
                end
            end)
        end
    end
end

local function showDayList(_data, _index)
    _curShowTitleIndex = _index
	local image_basemap = UIActivityTrial.Widget:getChildByName("image_basemap")
	local _listData = {}
	if _data then
		for i = 1, DAY_COUNT do
			local btnTrial = image_basemap:getChildByName("btn_trial" .. i)
			if btnTrial:isVisible() then
                local ui_dayName = btnTrial:getChildByName("text_day")
				if _index == i then
                    if ui_dayName then
					    ui_dayName:setTextColor(cc.c4b(51, 25, 4, 255))
                    end
					btnTrial:loadTextures("ui/yh_btn02.png", "ui/yh_btn02.png")
				else
                    if ui_dayName then
					    ui_dayName:setTextColor(cc.c4b(255, 255, 255, 255))
                    end
					btnTrial:loadTextures("ui/yh_btn01.png", "ui/yh_btn02.png")
				end
			end
		end
        if string.find(_data.sname, "fight") then
            _listData[1] = {id = 0, type = 0}
        end
        for key, obj in pairs(DictTryToPractice) do
            if obj.tryToPracticeTypeId == _data.id then
                _listData[#_listData + 1] = obj
                _listData[#_listData].day = _index
                if _listData[1].type == 0 and #_listData > 1 then
                    _listData[#_listData].type = 1
                end
            end
        end
        utils.quickSort(_listData, function(obj1, obj2) if obj1.id > obj2.id then return true end end)
	end
    if _index == 1 then
        showHotShoppingUI(_data, _index)
    else
        for i = 1, 3 do
            image_basemap:getChildByName("image_base_good"..i):setVisible(false)
        end
        local _tempObj, _awardObj = {}, {}
        for key, obj in pairs(_listData) do
            if isGetAward(obj.id) then
                _awardObj[#_awardObj + 1] = obj
            else
                _tempObj[#_tempObj + 1] = obj
            end
        end
        _listData = {}
        for i = 1, #_tempObj + #_awardObj do
            if _tempObj[i] then
                _listData[#_listData + 1] = _tempObj[i]
            elseif _awardObj[i - #_tempObj] then
                _listData[#_listData + 1] = _awardObj[i - #_tempObj]
            end
        end
        _tempObj = nil
        _awardObj = nil
	    layoutScrollView(_listData, setScrollViewItem)
    end
end

local function showDayUI(_dayIndex)
	local image_basemap = UIActivityTrial.Widget:getChildByName("image_basemap")
    for i = 1, DAY_COUNT do
        local dayButton = image_basemap:getChildByName("btn_day" .. i)
        if i == _dayIndex then
            dayButton:loadTextureNormal("ui/sl_j.png")
        else
	        dayButton:loadTextureNormal("ui/sl_h.png")
        end
    end
	_curShowDayIndex = _dayIndex
	local _dayData = {}
	for key, obj in pairs(DictTryToPracticeType) do
		if obj.day == _dayIndex then
            if string.find(obj.sname, "hotShopping") then
                if _dayData[1] then
                    _dayData[#_dayData + 1] = _dayData[1]
                end
                _dayData[1] = obj
            else
			    _dayData[#_dayData + 1] = obj
            end
		end
	end
	utils.quickSort(_dayData, function(obj1, obj2) if obj1.id > obj2.id then return true end end)
	for i = 1, DAY_COUNT do
		local titleButton = image_basemap:getChildByName("btn_trial" .. i)
		if _dayData[i] then
			local obj = _dayData[i]
			if i == 1 then
				currentDayAward(obj)
			end
			titleButton:setVisible(true)
            local ui_dayName = titleButton:getChildByName("text_day")
            if ui_dayName then
			    ui_dayName:setString(obj.name)
            end
			titleButton:addTouchEventListener(function(sender, eventType)
				if eventType == ccui.TouchEventType.ended and _curShowTitleIndex ~= i then
					showDayList(obj, i)
				end
			end)
		else
			titleButton:setVisible(false)
		end
	end
    if _curShowDayOfItemIndex then
        showDayList(_dayData[_curShowDayOfItemIndex], _curShowDayOfItemIndex)
        _curShowDayOfItemIndex = nil
    else
	    showDayList(_dayData[1], 1)
    end
end

netCallbackFunc = function(_msgData)
    local code = tonumber(_msgData.header)
    if code == StaticMsgRule.tryToPractice then
        if _curAwards then
            utils.showGetThings(_curAwards)
            _curAwards = nil
        end
        UIActivityTrial.setup(true)
    elseif code == StaticMsgRule.tryToPracticeBuy then
        if _curAwards then
            utils.showGetThings(_curAwards)
            _curAwards = nil
        end
        UIActivityTrial.setup(true)
    elseif code == StaticMsgRule.tryToPracticeAward then
        if _curDayData then
            utils.showGetThings(_curDayData.rewards)
            currentDayAward(_curDayData)
            _curDayData = nil
        end
    end
end

---@flag : 0表示左下,1表示右上
local function myPathFun(controlX, controlY, w, time, flag)
--	local time = 0.5
	if flag == 0 then
		local bezier1 = {
			cc.p(-controlX, 0),
			cc.p(-controlX, controlY),
			cc.p(0, controlY),
		}
		local bezierBy1 = cc.BezierBy:create(time, bezier1)
		local move1 = cc.MoveBy:create(time, cc.p(w, 0))
		local bezier2 = {
			cc.p(controlX, 0),
			cc.p(controlX, -controlY),
			cc.p(0, -controlY),
		}
		local bezierBy2 = cc.BezierBy:create(time, bezier2)
		local move2 = cc.MoveBy:create(time, cc.p(-w, 0))
		local path = cc.RepeatForever:create(cc.Sequence:create(bezierBy1, move1, bezierBy2, move2))
		if w == 0 then
			path = cc.RepeatForever:create(cc.Sequence:create(bezierBy1, bezierBy2))
		end
		return path
	elseif flag == 1 then
		local bezier1 = {
			cc.p(controlX, 0),
			cc.p(controlX, -controlY),
			cc.p(0, -controlY),
		}
		local bezierBy1 = cc.BezierBy:create(time, bezier1)
		local move1 = cc.MoveBy:create(time, cc.p(-w, 0))
		local bezier2 = {
			cc.p(-controlX, 0),
			cc.p(-controlX, controlY),
			cc.p(0, controlY),
		}
		local bezierBy2 = cc.BezierBy:create(time, bezier2)
		local move2 = cc.MoveBy:create(time, cc.p(w, 0))
		local path = cc.RepeatForever:create(cc.Sequence:create(bezierBy1, move1, bezierBy2, move2))
		if w == 0 then
			path = cc.RepeatForever:create(cc.Sequence:create(bezierBy1, bezierBy2))
		end
		return path
	end
end
local function addParticleEffect(node)
	for _i = 1, 2 do
		local effect = cc.ParticleSystemQuad:create("particle/ui_anim8_effect.plist")
		node:addChild(effect)
		effect:setPositionType(cc.POSITION_TYPE_RELATIVE)
		if _i == 1 then
			effect:setPosition(cc.p(50, 0))
			effect:runAction(myPathFun(node:getContentSize().width - 40, node:getContentSize().height, 0, 0.5, 0))
		else
			effect:setPosition(cc.p(node:getContentSize().width - 50, node:getContentSize().height))
			effect:runAction(myPathFun(node:getContentSize().width - 40, node:getContentSize().height, 0, 0.5, 1))
		end
	end
end

function UIActivityTrial.init()
	local image_basemap = UIActivityTrial.Widget:getChildByName("image_basemap")
	ui_scrollView = image_basemap:getChildByName("view_good")
    ui_scrollView:setVisible(true)
	ui_svItemGood = ui_scrollView:getChildByName("image_base_good"):clone()
	ui_svItemFight = ui_scrollView:getChildByName("image_base_fight"):clone()
	for i = 1, DAY_COUNT do
		local dayButton = image_basemap:getChildByName("btn_day" .. i)
		dayButton:setPressedActionEnabled(true)
		dayButton:addTouchEventListener(function(sender, eventType)
			if eventType == ccui.TouchEventType.ended and _curShowDayIndex ~= i then
				showDayUI(i)
                UIActivityTrial.checkImageHint(i)
			end
		end)
	end

	local image_frame_good = image_basemap:getChildByName("image_frame_good")
    local image_good = image_frame_good:getChildByName("image_good")
    image_good:setLocalZOrder(1)
    addParticleEffect(image_frame_good)
--    local particle = cc.ParticleSystemQuad:create("particle/shouye_action_effect_slstar.plist")
--	particle:setPosition(cc.p(image_frame_good:getContentSize().width / 2,image_frame_good:getContentSize().height * 0.4))
--	particle:setScale(0.8)
--	image_frame_good:addChild(particle, 0)
end

function UIActivityTrial.setup(tag)
    local _endTime = utils.GetTimeByDate(net.InstPlayer.registerTime) + (UIActivityTrial.ACTIVITY_DAY_COUNT*24*60*60)
    _countdownTime = _endTime - utils.getCurrentTime()
    dp.addTimerListener(countDowun)
	local image_bian = UIActivityTrial.Widget:getChildByName("image_basemap"):getChildByName("image_title")
	ccui.Helper:seekNodeByName(image_bian, "label_fight"):setString(tostring(utils.getFightValue()))
	refreshMoney()
    showDayUI(_curShowDayIndex)
    UIActivityTrial.hintFlag = true
    if not tag then
        UIActivityTrial.checkImageHint(_curShowDayIndex,true)
    else
        UIActivityTrial.checkImageHint(_curShowDayIndex)
    end
end

function UIActivityTrial.free()
	cleanScrollView()
    _curShowDayIndex = 1
    _curShowDayOfItemIndex = nil
    _curShowTitleIndex = nil
    _curDayData = nil
    _curAwards = nil
    --_totalRechargeNums = 0
    dp.removeTimerListener(countDowun)
end

function UIActivityTrial.show(_flag)
    UIManager.showLoading()
	netSendPackage({header = StaticMsgRule.lookSaveAmt, msgdata = {int={type = 5}}}, function(_msgData)
        if _msgData then
            _totalRechargeNums = _msgData.msgdata.int["1"]
            local _tempTime = utils.stringSplit(net.InstPlayer.registerTime, " ")
            local _registerTime = utils.GetTimeByDate(_tempTime[1] .. " 00:00:00")
            local _curTime = utils.getCurrentTime()
            local _taskDayNum = 0
            for i = 1, DAY_COUNT do
                if _curTime >= _registerTime + ((i-1)*24*60*60) then
                    if i > _taskDayNum then
                        _taskDayNum = i
                    end
                end
            end
            if _taskDayNum >= DAY_COUNT then
                _curShowDayIndex = DAY_COUNT
            else
                _curShowDayIndex = _taskDayNum
            end
            if _flag then
                UIManager.showScreen("ui_notice", "ui_activity_trial", "ui_menu")
            else
                UIManager.hideWidget("ui_team_info")
	            UIManager.showWidget("ui_activity_trial")
            end
        end
    end)
end

getProgress = function(_data)
    if _data then
        local tryData = DictTryToPracticeType[tostring(_data.tryToPracticeTypeId)]
        if tryData then
            if tryData.id == StaticTryToPracticeType.chapter then --主线副本
                local chapterId = tonumber(_data.conditions)
                if net.InstPlayerChapter then
                    for ipbKey, ipbObj in pairs(net.InstPlayerChapter) do
			            if tonumber(chapterId) == ipbObj.int["3"] and ipbObj.int["6"] == 1 then
				            return _data.progress
			            end
		            end
                end
            elseif tryData.id == StaticTryToPracticeType.equip then --装备强化
                if net.InstPlayerLineup then
                    local ipfData = {}
                    for key, obj in pairs(net.InstPlayerLineup) do
                        local instEquipData = net.InstPlayerEquip[tostring(obj.int["5"])]
                        if instEquipData.int["5"] >= tonumber(_data.conditions) then
                            if not ipfData[obj.int["3"]] then
                                ipfData[obj.int["3"]] = {}
                            end
                            ipfData[obj.int["3"]][#ipfData[obj.int["3"]] + 1] = obj
                        end
                    end
                    local progress = 0
                    for key, obj in pairs(ipfData) do
                        if #obj == 4 then
                            progress = progress + 1
                        end
                    end
                    ipfData = nil
                    return progress
                end
            elseif tryData.id == StaticTryToPracticeType.advance then --萧炎进阶
                if net.InstPlayerCard then
                    local _conditions = utils.stringSplit(_data.conditions, "_")
                    local dictCardId, qualityId, starLevelId = tonumber(_conditions[1]), tonumber(_conditions[2]), tonumber(_conditions[3])
                    for key, obj in pairs(net.InstPlayerCard) do
                        if obj.int["3"] == dictCardId and obj.int["4"] >= qualityId then
                            if (obj.int["4"] == qualityId and obj.int["5"] >= starLevelId) or (obj.int["4"] > qualityId) then
                            return _data.progress
                            end
                        end
                    end
                end
            elseif tryData.id == StaticTryToPracticeType.magic then --功法 法宝
                if net.InstPlayerMagic then
                    local ipmData = {}
                    for key, obj in pairs(net.InstPlayerMagic) do
                        if obj.int["8"] > 0 and DictMagicLevel[tostring(obj.int["6"])].level >= tonumber(_data.conditions) then
                            if not ipmData[obj.int["8"]] then
                                ipmData[obj.int["8"]] = {}
                            end
                            ipmData[obj.int["8"]][#ipmData[obj.int["8"]] + 1] = obj
                        end
                    end
                    local progress = 0
                    for key, obj in pairs(ipmData) do
                        if #obj == 2 then
                            progress = progress + 1
                        end
                    end
                    ipmData = nil
                    return progress
                end
            elseif tryData.id == StaticTryToPracticeType.arena then --竞技场
                if net.InstPlayerArena then
                    if net.InstPlayerArena.int["4"] <= tonumber(_data.conditions) then
                        return _data.progress
                    end
                end
            elseif tryData.id == StaticTryToPracticeType.title then --境界
                if net.InstPlayerCard then
                    local progress = 0
                    local _conditions = utils.stringSplit(_data.conditions, "_")
                    local dictCardId, titleDetailId = tonumber(_conditions[1]), tonumber(_conditions[2])
                    for key, obj in pairs(net.InstPlayerCard) do
                        if dictCardId == 0 then
                            if obj.int["10"] == 1 and obj.int["6"] >= titleDetailId then
                                progress = progress + 1
                            end
                        else
                            if obj.int["3"] == dictCardId and obj.int["6"] == titleDetailId then
                                return _data.progress
                            end
                        end
                    end
                    _conditions = nil
                    return progress
                end
            elseif tryData.id == StaticTryToPracticeType.constell then --命宫
                if net.InstPlayerCard then
                    local progress = 0
                    for key, obj in pairs(net.InstPlayerCard) do
                        if obj.int["10"] == 1 and obj.string["13"] then
                            local _instConstellIds = utils.stringSplit(obj.string["13"], ";")
                            for _k, _id in pairs(_instConstellIds) do
                                if _k >= tonumber(_data.conditions) then
                                    local instConstellData = net.InstPlayerConstell[tostring(_id)]
                                    local isUse = instConstellData.string["5"]
                                    local _isUses = utils.stringSplit(isUse, ";")
						            local _open = ""
						            for i = 1, #_isUses do
							            if i == #_isUses then
								            _open = _open .. "1"
							            else
								            _open = _open .. "1;"
							            end
						            end
						            if isUse == _open then
                                        progress = progress + 1
						            end
                                    break
                                end
                            end
                        end
                    end
                    return progress
                end
            elseif tryData.id == StaticTryToPracticeType.fight1 then --热血试炼
                if net.InstPlayerTryToPractice then
                    local unGetTryIds = utils.stringSplit(net.InstPlayerTryToPractice.string["4"], ";")
                    for key, obj in pairs(unGetTryIds) do
                        if _data.id == tonumber(obj) then
                            return _data.progress
                        end
                    end
                end
            elseif tryData.id == StaticTryToPracticeType.inlay then --宝石镶嵌
                if net.InstPlayerLineup then
                    local progress = 0
                    for key, obj in pairs(net.InstPlayerLineup) do
                        local instEquipData = net.InstPlayerEquip[tostring(obj.int["5"])]
                        if net.InstEquipGem then
                            local _conditions = utils.stringSplit(_data.conditions, "_")
                            local _type, _gemLevel = tonumber(_conditions[1]), tonumber(_conditions[2])
			                for key, obj in pairs(net.InstEquipGem) do
				                if instEquipData.int["1"] == obj.int["3"] then
                                    if _type == 1 then --打孔个数
                                        progress = progress + 1
                                    else --镶嵌个数
                                        if obj.int["4"] > 0 and DictThing[tostring(obj.int["4"])].level >= _gemLevel then
                                            progress = progress + 1
                                        end
                                    end
				                end
			                end
		                end
                    end
                    return progress
                end
            elseif tryData.id == StaticTryToPracticeType.pagoda then --炼气塔
                if net.InstPlayerPagoda then
                    local _type = tonumber(_data.conditions)
                    if _type == 1 then --通关层数
--                        if net.InstPlayerPagoda.int["7"] >= _data.progress then
                            return net.InstPlayerPagoda.int["7"]
--                        end
                    else --重置次数
--                        if net.InstPlayerPagoda.int["5"] >= _data.progress then
                            return net.InstPlayerPagoda.int["5"]
--                        end
                    end
                end
            elseif tryData.id == StaticTryToPracticeType.hJYStore then --神秘商店
                if net.InstPlayerAchievementValue then
                    for key, obj in pairs(net.InstPlayerAchievementValue) do
                        if DictAchievementType[tostring(obj.int["3"])] and DictAchievementType[tostring(obj.int["3"])].sname == "hJYStore" then
                            return obj.int["4"]
                        end
                    end
                end
            elseif tryData.id == StaticTryToPracticeType.fight2 then --严酷试炼
                if net.InstPlayerTryToPractice then
                    local unGetTryIds = utils.stringSplit(net.InstPlayerTryToPractice.string["4"], ";")
                    for key, obj in pairs(unGetTryIds) do
                        if _data.id == tonumber(obj) then
                            return _data.progress
                        end
                    end
                end
            elseif tryData.id == StaticTryToPracticeType.luck then --缘分
                if net.InstPlayerFormation then
                    local progress, _cardLuckData = 0, {}
                    local _conditions = utils.stringSplit(_data.conditions, "_")
                    local _type, _luckNum = tonumber(_conditions[1]), tonumber(_conditions[2])
		            for key, obj in pairs(net.InstPlayerFormation) do
                        local dictCardId = net.InstPlayerCard[tostring(obj.int["3"])].int["3"]
			            for k, objLuck in pairs(DictCardLuck) do
				            if objLuck.cardId == dictCardId and utils.isCardLuck(objLuck, obj.int["1"]) then
                                if _type == 1 then
                                    if not _cardLuckData[dictCardId] then
                                        _cardLuckData[dictCardId] = {}
                                    end
                                    _cardLuckData[dictCardId][#_cardLuckData[dictCardId] + 1] = objLuck
                                else
					                progress = progress + 1
                                end
				            end
			            end
                    end
                    for key, obj in pairs(_cardLuckData) do
                        if #obj >= _luckNum then
                            progress = progress + 1
                        end
                    end
                    _cardLuckData = nil
                    return progress
                end
            elseif tryData.id == StaticTryToPracticeType.equipAdvance then --装备进阶
                if net.InstPlayerLineup then
                    local progress = 0
                    for key, obj in pairs(net.InstPlayerLineup) do
                        local instEquipData = net.InstPlayerEquip[tostring(obj.int["5"])]
                        local dictEquipAdvanceData = DictEquipAdvance[tostring(instEquipData.int["8"])]
                        if dictEquipAdvanceData and dictEquipAdvanceData.starLevel >= tonumber(_data.conditions) then
                            progress = progress + 1
                        end
                    end
                    return progress
                end
            elseif tryData.id == StaticTryToPracticeType.fight3 then --地狱试炼
                if net.InstPlayerTryToPractice then
                    local unGetTryIds = utils.stringSplit(net.InstPlayerTryToPractice.string["4"], ";")
                    for key, obj in pairs(unGetTryIds) do
                        if _data.id == tonumber(obj) then
                            return _data.progress
                        end
                    end
                end
            end
        else
            cclog("=============数据出错了===============")
        end
    else
        cclog("=============数据出错了===============")
    end
    return 0
end

local function checkFinalAward(_data)
    local result = false
    if net.InstPlayerTryToPractice and net.InstPlayerTryToPractice.string["5"] then
        local getDayIds = utils.stringSplit(net.InstPlayerTryToPractice.string["5"], ";")
        for key, obj in pairs(getDayIds) do
            if _curShowDayIndex == tonumber(obj) then
                _isGetAward = true
                break
            end
        end
    end
     if not _isGetAward then
        local _tempTime = utils.stringSplit(net.InstPlayer.registerTime, " ")
        local _registerTime = utils.GetTimeByDate(_tempTime[1] .. " 00:00:00")
        local _curTime = utils.getCurrentTime()
        if _curTime > _registerTime + (_curShowDayIndex*24*60*60) then
            result = false
        else
            local _progress = getDayProgress()
            if _progress >= _data.count then
                result = true
            else
                result = false
            end
        end
    end
    return result
end

local function checkHotShopping(_data,_index)
    local result = false
    local awardFlag = false
    local chargeFlag = 0  --0为充值够，1--充值够了未领取，2--已经领取
    local saleFlag = false
    local hotShoppingData = {}
    for key, obj in pairs(DictTryToPractice) do
        if obj.tryToPracticeTypeId == _data.id then
            local _tempObj = utils.stringSplit(obj.conditions, "_")
            hotShoppingData[tonumber(_tempObj[1])] = obj
            _tempObj = nil
        end
    end
    for key, obj in pairs(hotShoppingData) do
        local _isGetAward = isGetAward(obj.id)
        if _isGetAward then
            result = false or result    
        else
            result = true or result
        end
        if key == 1 and not _isGetAward then
            awardFlag = true
        end
        if key == 2 then
            if _isGetAward then
                chargeFlag = 2
            else
                if _totalRechargeNums < obj.progress then
                    chargeFlag = 0
                else
                    chargeFlag = 1
                end
            end
        end
        if key == 3 and not _isGetAward then
            saleFlag = true
        end
    end
    return result,awardFlag,chargeFlag,saleFlag
end

local function checkOther(_tempdata,_index)
    local result = false
    local _data = {}
	if _tempdata then
        if string.find(_tempdata.sname, "fight") then
            _data[1] = {id = 0, type = 0}
        end
        for key, obj in pairs(DictTryToPractice) do
            if obj.tryToPracticeTypeId == _tempdata.id then
                _data[#_data + 1] = obj
                _data[#_data].day = _index
                if _data[1].type == 0 and #_data > 1 then
                    _data[#_data].type = 1
                end
            end
        end
        utils.quickSort(_data, function(obj1, obj2) if obj1.id > obj2.id then return true end end)
	end
    for key, obj in pairs(_data) do
        if obj.type == 0 then
	    else
            local _progress = getProgress(obj)
            if _progress >= obj.progress and not isGetAward(obj.id) then
                result = result or true
            else
                result = result or false
            end
        end
    end
    return result
end

local function checkDayAward(_dayIndex,_index)
    local _dayData = {}
	for key, obj in pairs(DictTryToPracticeType) do
		if obj.day == _dayIndex then
            if string.find(obj.sname, "hotShopping") then
                if _dayData[1] then
                    _dayData[#_dayData + 1] = _dayData[1]
                end
                _dayData[1] = obj
            else
			    _dayData[#_dayData + 1] = obj
            end
		end
	end
	utils.quickSort(_dayData, function(obj1, obj2) if obj1.id > obj2.id then return true end end)
    local resultFirst = false
    local resultOther = false
    local resultThree = false
    local awardFlag = false
    local chargeFlag = 0
    local saleFlag = false
    for key, obj in pairs(_dayData) do
        local parent = nil
        if UIActivityTrial.hintFlag then
            parent = UIActivityTrial.Widget:getChildByName("image_basemap"):getChildByName("btn_trial"..key)
        end
        if key == 1 then
            resultFirst,awardFlag,chargeFlag,saleFlag = checkHotShopping(_dayData[key],key)
            if parent and UIActivityTrial.hintFlag and _dayIndex == _index then
                local tempResult = awardFlag or (chargeFlag < 2) or saleFlag
                parent:getChildByName("image_hint"):setVisible(tempResult)
            end
            resultThree = checkFinalAward(_dayData[key])
        else
            local resultTempOther = checkOther(_dayData[key],key)
            if parent and UIActivityTrial.hintFlag and _dayIndex == _index then
                parent:getChildByName("image_hint"):setVisible(resultTempOther)
            end
            resultOther = resultOther or resultTempOther
        end
    end
    return resultFirst,resultOther,resultThree,awardFlag,chargeFlag,saleFlag
end


function UIActivityTrial.checkImageHint(_index,tag)
    local result = false
    if tag then
        local _tempTime = utils.stringSplit(net.InstPlayer.registerTime, " ")
        local _registerTime = utils.GetTimeByDate(_tempTime[1] .. " 00:00:00")
        local _curTime = utils.getCurrentTime()
        for i = 1, DAY_COUNT do
            if _curTime >= _registerTime + ((i-1)*24*60*60) then
                local resultFirst,resultOther,resultThree,awardFlag,chargeFlag,saleFlag = checkDayAward(i,_index)
                local parent = nil
                if UIActivityTrial.hintFlag then
                    parent = UIActivityTrial.Widget:getChildByName("image_basemap"):getChildByName("btn_day"..i)
                end
                if parent and UIActivityTrial.hintFlag then
                    if resultOther or resultThree then
                        parent:getChildByName("image_hint"):setVisible(true)
                    elseif resultFirst then
                        if awardFlag or chargeFlag == 1 then
                            parent:getChildByName("image_hint"):setVisible(true)
                        else
                            parent:getChildByName("image_hint"):setVisible(false)
                        end
                    else
                        parent:getChildByName("image_hint"):setVisible(false)
                    end
                elseif not UIActivityTrial.hintFlag then
                    if resultOther or resultThree then
                        result = result or true
                    elseif resultFirst then
                        if awardFlag or chargeFlag == 1 then
                            result = result or true
                        end
                    end
                end
            end
        end
    else
        local i = _index
        local resultFirst,resultOther,resultThree,awardFlag,chargeFlag,saleFlag = checkDayAward(i,_index)
        local parent = UIActivityTrial.Widget:getChildByName("image_basemap"):getChildByName("btn_day"..i)
        if parent then
            if resultOther or resultThree then
                parent:getChildByName("image_hint"):setVisible(true)
            elseif resultFirst then
                if awardFlag or chargeFlag == 1 then
                     parent:getChildByName("image_hint"):setVisible(true)
                else
                     parent:getChildByName("image_hint"):setVisible(false)
                end
            else
                 parent:getChildByName("image_hint"):setVisible(false)
            end
        end
    end
    return result
end