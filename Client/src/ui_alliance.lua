UIAlliance = { }

local SHOW_MAX_COUNT = 50 -- 最大显示总数
local DEFAULT_SHOW_COUNT = 7 -- 默认显示个数

local ui_scrollView = nil
local ui_svItem = nil

local _allianceLevel = nil
local _leaderName = nil
local _isWriteUnion = nil
local _allianceGradeType = nil

local netCallbackFunc = nil

local function cleanScrollView()
    if ui_svItem:getReferenceCount() == 1 then
        ui_svItem:retain()
    end
    ui_scrollView:removeAllChildren()
end

local function layoutScrollView(_listData, _initItemFunc)
    local SCROLLVIEW_ITEM_SPACE = 0
    cleanScrollView()
    ui_scrollView:jumpToTop()
    local _innerHeight = 0
    if not _listData then _listData = { } end
    if #_listData < DEFAULT_SHOW_COUNT then
        for i = #_listData + 1, DEFAULT_SHOW_COUNT do
            _listData[i] = 0
        end
    end
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
    -- ActionManager.ScrollView_SplashAction(ui_scrollView)
end

function UIAlliance.refreshOffer()
    local image_basemap = UIAlliance.Widget:getChildByName("image_basemap")
    local image_title_di = image_basemap:getChildByName("image_title_di")
    local ui_allianceOffer = ccui.Helper:seekNodeByName(image_title_di, "text_alliance_number")
    ui_allianceOffer:setString(tostring(net.InstUnionMember.int["5"]))
end

local function initAllianceInfo(_msgData)
    local image_basemap = UIAlliance.Widget:getChildByName("image_basemap")
    local image_title_di = image_basemap:getChildByName("image_title_di")
    _allianceGradeType = _msgData.int["5"]
    local ui_playerName = ccui.Helper:seekNodeByName(image_title_di, "text_name")
    ui_playerName:setString(net.InstPlayer.string["3"])
    local ui_palyerLevel = ccui.Helper:seekNodeByName(image_title_di, "text_lv")
    ui_palyerLevel:setString("LV " .. net.InstPlayer.int["4"])
    local ui_allianceOffer = ccui.Helper:seekNodeByName(image_title_di, "text_alliance_number")
    ui_allianceOffer:setString(tostring(net.InstUnionMember.int["5"]))
    local ui_allianceName = ccui.Helper:seekNodeByName(image_title_di, "text_alliance_name")
    ui_allianceName:setString(_msgData.string["2"])
    local ui_allianceLevel = ccui.Helper:seekNodeByName(image_title_di, "text_alliance_lv")
    ui_allianceLevel:setString(tonumber(_msgData.int["4"]))
    _allianceLevel = _msgData.int["4"]
    local ui_allianceNotice = ccui.Helper:seekNodeByName(image_title_di, "Label_66")
    ui_allianceNotice:setString(_msgData.string["10"])
    local ui_allianceExpBar = ccui.Helper:seekNodeByName(image_title_di, "bar_exp")
    local ui_allianceExp = ui_allianceExpBar:getChildByName("text_exp")
    local upgradeExp = DictUnionLevelPriv[tostring(_msgData.int["4"])].exp
    ui_allianceExp:setString(_msgData.int["3"] .. " / " .. upgradeExp)
    ui_allianceExpBar:setPercent(utils.getPercent(_msgData.int["3"], upgradeExp))
    local selfGradeId = net.InstUnionMember.int["4"]
    local ui_ui_allianceNoticeBg = ui_allianceNotice:getParent():getChildByName("image_base_notice")
    if selfGradeId == 1 or selfGradeId == 2 then
        ui_ui_allianceNoticeBg:setTouchEnabled(true)
        ui_ui_allianceNoticeBg:addTouchEventListener( function(sender, eventType)
            if eventType == ccui.TouchEventType.ended then
                UIAllianceHint.show( { title = "公告", desc = ui_allianceNotice:getString(), callbackFunc = netCallbackFunc })
            end
        end )
    else
        ui_ui_allianceNoticeBg:setTouchEnabled(false)
    end
end

local function setScrollViewItem(_item, _data, _index)
    local ui_name = _item:getChildByName("text_name")
    local ui_memberFlag = _item:getChildByName("image_job")
    local ui_level = _item:getChildByName("text_lv")
    local ui_online = _item:getChildByName("text_congratulation")
    if _index % 2 == 0 then
        _item:loadTexture("ui/lm_s.png")
    end
    local _isShowContent = true
    if type(_data) == "number" and _data == 0 then
        _isShowContent = false
    else
        local grades = UIAlliance.getAllianceGrade(_data.int["4"])
        if grades then
            ui_memberFlag:loadTexture(grades.icon)
        end
        local _name = _data.string["10"]
        local _level = _data.int["11"]
        local _onlineState = _data.long["12"]
        -- 在线状态 0:在线
        ui_name:setString(_name)
        ui_level:setString(_level)
        if _data.int["4"] == 1 then
            _leaderName = _name
        end
        if _onlineState == 0 then
            ui_online:setString("在线")
        else
            ui_online:setString(UIAlliance.getOnlineState(_onlineState / 1000))
        end
    end
    ui_name:setVisible(_isShowContent)
    ui_memberFlag:setVisible(_isShowContent)
    ui_level:setVisible(_isShowContent)
    ui_online:setVisible(_isShowContent)
end

netCallbackFunc = function(msgData)
    local code = tonumber(msgData.header)
    if code == StaticMsgRule.unionDetail then
        local unionDetail = msgData.msgdata.message.unionDetail
        initAllianceInfo(unionDetail)
        if not _isWriteUnion then
            UIManager.showLoading()
            netSendPackage( {
                header = StaticMsgRule.unionMember,
                msgdata = { int = { instUnionMemberId = net.InstUnionMember.int["1"] } }
            } , netCallbackFunc)
        end
        _isWriteUnion = nil
    elseif code == StaticMsgRule.unionMember then
        local unionMember = msgData.msgdata.message.unionMember
        local memberList = { }
        for key, obj in pairs(unionMember.message) do
            memberList[#memberList + 1] = obj
        end
        utils.quickSort(memberList, function(obj1, obj2)
            if obj1.int["4"] > obj2.int["4"] then
                return true
            elseif obj1.int["4"] == obj2.int["4"] then
                if obj1.int["5"] > obj2.int["5"] then
                    return true
                end
            end
        end )
        layoutScrollView(memberList, setScrollViewItem)
    elseif code == StaticMsgRule.writeUnion then
        _isWriteUnion = true
        UIManager.showLoading()
        netSendPackage( {
            header = StaticMsgRule.unionDetail,
            msgdata = { int = { instUnionMemberId = net.InstUnionMember.int["1"] } }
        } , netCallbackFunc)
    end
end

function UIAlliance.init()
    local image_basemap = UIAlliance.Widget:getChildByName("image_basemap")
    local image_di_system = image_basemap:getChildByName("image_di_system")
    local btn_back = ccui.Helper:seekNodeByName(image_basemap, "btn_back")
    local btn_rank = ccui.Helper:seekNodeByName(image_basemap, "btn_rank")
    local btn_help = image_basemap:getChildByName("btn_help")
    -- 议事大厅
    local btn_hall = ccui.Helper:seekNodeByName(image_di_system, "btn_hall")
    -- 联盟商店
    local btn_shop = ccui.Helper:seekNodeByName(image_di_system, "btn_shop")
    -- 联盟建设
    local btn_build = ccui.Helper:seekNodeByName(image_di_system, "btn_build")
    local btn_wait = ccui.Helper:seekNodeByName(image_di_system, "btn_wait")

    btn_back:setPressedActionEnabled(true)
    btn_rank:setPressedActionEnabled(true)
    btn_help:setPressedActionEnabled(true)
    btn_hall:setPressedActionEnabled(true)
    btn_shop:setPressedActionEnabled(true)
    btn_build:setPressedActionEnabled(true)
    btn_wait:setPressedActionEnabled(true)

    local function onButtonEvent(sender, eventType)
        if eventType == ccui.TouchEventType.ended then
            if sender == btn_back then
                UIMenu.onHomepage()
            elseif sender == btn_rank then
                UIManager.pushScene("ui_alliance_rank")
            elseif sender == btn_help then
                UIAllianceHelp.show( { titleName = "联盟帮助", type = 0 })
            elseif sender == btn_hall then
                UIAllianceInfo.show( { leaderName = _leaderName })
            elseif sender == btn_shop then
                UIAllianceShop.show( { allianceLevel = _allianceLevel })
            elseif sender == btn_build then
                UIManager.showWidget("ui_alliance_build")
            elseif sender == btn_wait then
                if true then
                    UIManager.showToast("即将上线，敬请期待")
                    return
                end
                UIManager.showWidget("ui_war")
            end
        end
    end

    btn_back:addTouchEventListener(onButtonEvent)
    btn_rank:addTouchEventListener(onButtonEvent)
    btn_help:addTouchEventListener(onButtonEvent)
    btn_hall:addTouchEventListener(onButtonEvent)
    btn_shop:addTouchEventListener(onButtonEvent)
    btn_build:addTouchEventListener(onButtonEvent)
    btn_wait:addTouchEventListener(onButtonEvent)

    ui_scrollView = image_basemap:getChildByName("view_info")
    ui_svItem = ui_scrollView:getChildByName("image_member_info"):clone()
end

function UIAlliance.setup()
    UIManager.showLoading()
    netSendPackage( {
        header = StaticMsgRule.unionDetail,
        msgdata = { int = { instUnionMemberId = net.InstUnionMember.int["1"] } }
    } , netCallbackFunc)
    layoutScrollView(nil, setScrollViewItem)
    --[[
	ui_scrollView:addEventListener(function(sender, eventType)
		if eventType == ccui.ScrollviewEventType.scrollToTop then
		elseif eventType == ccui.ScrollviewEventType.scrollToBottom then
		elseif eventType == ccui.ScrollviewEventType.scrolling then
		else
			cclog("-------->>>  eventType = " .. eventType)
		end
	end)
	--]]

    local image_basemap = UIAlliance.Widget:getChildByName("image_basemap")
    local image_di_system = image_basemap:getChildByName("image_di_system")
    local btn_hall = ccui.Helper:seekNodeByName(image_di_system, "btn_hall")
    -- 议事大厅
    local image_hint = btn_hall:getChildByName("image_hint")
    image_hint:setVisible(false)
    local selfGradeId = net.InstUnionMember.int["4"]
    if selfGradeId == 1 or selfGradeId == 2 then
        netSendPackage( { header = StaticMsgRule.obtainApply, msgdata = { int = { instUnionMemberId = net.InstUnionMember.int["1"] } } }, function(_msgData)
            local unionApply = _msgData.msgdata.message.unionApply
            if unionApply and unionApply.message then
                image_hint:setVisible(true)
            end
        end )
    end
end

function UIAlliance.free()
    cleanScrollView()
    _leaderName = nil
    _isWriteUnion = nil
    _allianceLevel = nil
end

-- 按照一个月30天计算
local function getTime(_secsNums)
    -- 年
    local _year = math.floor(_secsNums / 3600 / 24 / 30 / 12 % 12)
    -- 月
    local _month = math.floor(_secsNums / 3600 / 24 / 30 % 30)
    -- 周
    local _week = math.floor(_secsNums / 3600 / 24 / 7 % 7)
    -- 天
    local _day = math.floor(_secsNums / 3600 / 24 % 24)
    -- 小时
    local _hour = math.floor(_secsNums / 3600 % 24)
    -- 分
    local _minute = math.floor(_secsNums / 60 % 60)
    -- 秒
    local _second = math.floor(_secsNums % 60)
    return { year = _year, month = _month, day = _day, week = _week, hour = _hour, min = _minute, sec = _second }
end

function UIAlliance.getOnlineState(_second)
    local _onlineState = "离线"
    local onlineTime = getTime(_second)
    if onlineTime.year > 0 then
        return _onlineState .. onlineTime.year .. "年以上"
    elseif onlineTime.month > 0 then
        return _onlineState .. onlineTime.month .. "月以上"
    elseif onlineTime.week > 0 then
        return _onlineState .. onlineTime.week .. "周以上"
    elseif onlineTime.day > 0 then
        return _onlineState .. onlineTime.day .. "天以上"
    elseif onlineTime.hour > 0 then
        return _onlineState .. onlineTime.hour .. "小时以上"
    elseif onlineTime.min > 0 then
        return _onlineState .. onlineTime.min .. "分钟以上"
    else
        return _onlineState
    end
end

--- 获取联盟的称号(职位)信息
function UIAlliance.getAllianceGrade(_gradeId)
    if net.InstUnionMember then
        for key, obj in pairs(DictUnionGrade) do
            if _allianceGradeType == obj.type and _gradeId == obj.gradeId then
                local flagImg = ""
                if _gradeId == 1 then
                    flagImg = "ui/lm_mz.png"
                elseif _gradeId == 2 then
                    flagImg = "ui/lm_fmz.png"
                elseif _gradeId == 3 then
                    flagImg = "ui/lm_cy.png"
                end
                return { name = obj.name, icon = flagImg }
            end
        end
    end
end