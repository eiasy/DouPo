UIActivityPanel = {
    -- 累充礼包, 超值月卡, 开服基金, VIP礼包
    rechargeActivity = { "addRecharge", "monthCard", "fund", "vip" },
    privateActivity =
    {
        ["100000"] = { int = { ["1"] = 100000, ["3"] = 16016, ["6"] = 1, ["7"] = 1, ["8"] = 1, ["11"] = 100000, ["12"] = 0, ["13"] = 0 }, string = { ["2"] = "VIP礼包", ["4"] = "", ["5"] = "", ["9"] = "vip", ["10"] = "永久" } }
    }
}

local panelItem = nil
local scrollView = nil
local ui_choose_frame = nil
local selectedActivityId = nil
local ActivityThings = nil
local _rechargeActivity = nil
local first = nil
local RECHARGE_TAG = 999
local jumpName = nil --- 从其他界面切换过来的名字
UIActivityPanel.imageHintTag = 100 -- 用作各个模块红点的Tag

local function replaceWidget(aWidgetName)
    local ui_widget = WidgetManager.create(aWidgetName)
    if ui_widget then
        local prev_uiWidget = UIManager.uiLayer:getChildByTag(ui_widget:getTag())
        if prev_uiWidget then
            local class = WidgetManager.getClass(prev_uiWidget:getName())
            if class and class.free then
                class.free()
            end
            UIManager.uiLayer:removeChild(prev_uiWidget, false)
        end
        UIManager.uiLayer:addChild(ui_widget)
        --    UIManager.uiLayer:visit()
    end
end

local function setScrollViewFocus()
    if jumpName then
        scrollView:jumpToLeft()
        local childs = scrollView:getChildren()
        for key, obj in pairs(childs) do
            local _acticity = net.SysActivity[tostring(obj:getTag())]
            if _acticity and _acticity.string["9"] == jumpName then
                local contaniner = scrollView:getInnerContainer()
                local w =(contaniner:getContentSize().width - scrollView:getContentSize().width)
                local dt = 0
                if w > 0 then
                    local mid = obj:getPositionX() + obj:getContentSize().width - scrollView:getContentSize().width
                    if mid > 0 then
                        dt = mid / w
                    end
                end
                scrollView:scrollToPercentHorizontal(dt * 100, 0.5, true)
            end
        end
    end
end
local function activityVipCallBack(pack) --zy vip经验更新问题
    if pack.msgdata.int and pack.msgdata.int["1"] then
        dp.rechargeGold = pack.msgdata.int["1"]
    else
        return
    end
        replaceWidget("ui_activity_vip")
end
local function showActivityWidget(instActivityId)
    local sname = net.SysActivity[tostring(instActivityId)].string["9"]
    if sname == "auctionShop" then
        replaceWidget("ui_activity_miteer")
    elseif sname == "hJYStore" then
        local instActivityObj = nil
        if net.InstActivity then
            for key, obj in pairs(net.InstActivity) do
                if instActivityId == obj.int["3"] then
                    instActivityObj = obj
                end
            end
        end
        UIActivityHJY.setData(instActivityObj)
        replaceWidget("ui_activity_heijiaoyu")
    elseif sname == "wash" then
        replaceWidget("ui_activity_bath")
        -- cc.UserDefault:getInstance():setBoolForKey("wash",false)
    elseif sname == "achievement" then
        UIActivitySuccess.setOperateType(UIActivitySuccess.operateType.success)
        if UIActivitySuccess.Widget and UIActivitySuccess.Widget:getParent() then
            UIActivitySuccess.setup()
        else
            replaceWidget("ui_activity_success")
        end
    elseif sname == "mail" then
        -- UIActivitySuccess.setOperateType(UIActivitySuccess.operateType.mail)
        if UIActivityEmail.Widget and UIActivityEmail.Widget:getParent() then
            UIActivityEmail.setup()
        else
            replaceWidget("ui_activity_email")
        end
    elseif sname == "fund" then
        replaceWidget("ui_activity_foundation")
    elseif sname == "addRecharge" then
        --        local function chargeCallBack(pack)
        --          if pack.msgdata.int and pack.msgdata.int["1"] then
        --            UIActivityRecharge.rechargeGold = pack.msgdata.int["1"]
        --          else
        --            return
        --          end
        replaceWidget("ui_activity_recharge")
        --        end
        --        utils.checkGOLD(3,chargeCallBack)
    elseif sname == "flashSale" then
        replaceWidget("ui_activity_buy")
    elseif sname == "monthCard" then
        replaceWidget("ui_activity_card")
    elseif sname == "rank" then
        replaceWidget("ui_activity_ranking")
    elseif sname == "LimitTimeExchange" then
        replaceWidget("ui_activity_time_exchange")
    elseif sname == "SaveConsume" then
        cclog("ui_activity_cost_all")
        replaceWidget("ui_activity_cost_all")
    elseif sname == "lucky" then
        replaceWidget("ui_activity_luckdial")
    elseif sname == "vip" then
        utils.checkGOLD(0, activityVipCallBack)
    end
end

local function addImageHint(panelItem, flag)
    utils.addImageHint(flag, panelItem, UIActivityPanel.imageHintTag, 15, 15)
end

local function setScrollViewItem(_panelItem, obj)
    local ui_image = _panelItem:getChildByName("image_warrior")
    local ActivityId = obj.int["1"]
    local smallUiId = net.SysActivity[tostring(ActivityId)].int["3"]
    if smallUiId ~= 0 then
        local smallImage = DictUI[tostring(smallUiId)].fileName
        ui_image:loadTexture("image/" .. smallImage)
    end
    local sname = net.SysActivity[tostring(obj.int["1"])].string["9"]
    local function TouchEvent(sender, eventType)
        if eventType == ccui.TouchEventType.ended then
            if selectedActivityId == sender:getTag() then
                return
            end
            if sname == "hJYStore" and net.InstPlayer.int["4"] < DictFunctionOpen[tostring(StaticFunctionOpen.hJYStoreLevel)].level then
                UIManager.showToast("神秘商店开启等级:" .. DictFunctionOpen[tostring(StaticFunctionOpen.hJYStoreLevel)].level)
                return
            end
            selectedActivityId = sender:getTag()
            ui_choose_frame:removeFromParent()
            ui_choose_frame:setPosition(cc.p(_panelItem:getContentSize().width / 2, _panelItem:getContentSize().height / 2))
            sender:addChild(ui_choose_frame)
            showActivityWidget(selectedActivityId)
        end
    end
    _panelItem:addTouchEventListener(TouchEvent)
    _panelItem:setEnabled(true)
    if sname == "wash" then
        addImageHint(_panelItem, UIActivityBath.checkImageHint())
    elseif sname == "fund" then
        UIActivityFoundation.checkImageHint(_panelItem, false)
    elseif sname == "achievement" then
        addImageHint(_panelItem, UIActivitySuccess.checkImageHint())
    elseif sname == "flashSale" then
        addImageHint(_panelItem, UIActivityBuy.checkImageHint())
        --    elseif sname == "mail" then
        --        addImageHint(_panelItem,UIHomePage.yj)
    elseif sname == "hJYStore" then
        addImageHint(_panelItem, UIActivityHJY.checkImageHint())
    elseif sname == "lucky" then
        addImageHint(_panelItem, UIHomePage.luckyFlag)
    elseif sname == "SaveConsume" then
        UIActivityCostAll.checkImageHint(_panelItem, false)
    elseif sname == "monthCard" then
        -- zy  月卡红点提示
        addImageHint(_panelItem, UIHomePage.isShowMonthCardHint())
    end
end

function UIActivityPanel.addImageHint(flag, name)
    local scrollView = ccui.Helper:seekNodeByName(UIActivityPanel.Widget, "view_warrior")
    if not scrollView then
        return
    end
    local parent = nil
    if name then
        parent = scrollView:getChildByName(name)
    else
        -- 首冲
        parent = scrollView:getChildByTag(RECHARGE_TAG)
    end
    utils.addImageHint(flag, parent, UIActivityPanel.imageHintTag, 15, 15)
end

local function setRechargeItem(_panelItem)
    local ui_image = _panelItem:getChildByName("image_warrior")
    ui_image:loadTexture("ui/sc_icon.png")

    local function chargeCallBack(pack)
        if pack.msgdata.int and pack.msgdata.int["1"] then
            dp.rechargeGold = pack.msgdata.int["1"]
        else
            return
        end
        selectedActivityId = _panelItem:getTag()
        ui_choose_frame:removeFromParent()
        ui_choose_frame:setPosition(cc.p(_panelItem:getContentSize().width / 2, _panelItem:getContentSize().height / 2))
        _panelItem:addChild(ui_choose_frame)
        replaceWidget("ui_activity_gift")
    end
    local function TouchEvent(sender, eventType)
        if eventType == ccui.TouchEventType.ended then
            if selectedActivityId == sender:getTag() then
                return
            end
            utils.checkGOLD(0, chargeCallBack)
        end
    end
    _panelItem:addTouchEventListener(TouchEvent)
    _panelItem:setEnabled(true)
    addImageHint(_panelItem, UIActivityGift.checkImageHint())
end

local function scrollviewUpdate()
    --- 判断是否领取礼包 领取了就不显示了----
    local isGetFirstRechargeGift = net.InstPlayer.int["35"]
    if not _rechargeActivity then
        isGetFirstRechargeGift = 1
    end
    --     if device.platform == "ios" then
    --        isGetFirstRechargeGift = 1
    --     end
    if isGetFirstRechargeGift == 0 then
        local _panelItem = panelItem:clone()
        setRechargeItem(_panelItem)
        _panelItem:setTag(RECHARGE_TAG)
        -----默认第一项选中---------
        if first and not jumpName then
            first = false
            ui_choose_frame:setPosition(cc.p(_panelItem:getContentSize().width / 2, _panelItem:getContentSize().height / 2))
            _panelItem:addChild(ui_choose_frame)
            selectedActivityId = RECHARGE_TAG
            local function chargeCallBack(pack)
                if pack.msgdata.int and pack.msgdata.int["1"] then
                    dp.rechargeGold = pack.msgdata.int["1"]
                end
                UIManager.showWidget("ui_activity_gift")
            end
            utils.checkGOLD(0, chargeCallBack)
        end
        -------------------------
        scrollView:addChild(_panelItem)
    end

    for key, obj in pairs(ActivityThings) do
        local _panelItem = panelItem:clone()
        setScrollViewItem(_panelItem, obj)
        -----默认第一项选中---------
        if first and not jumpName then
            first = false
            ui_choose_frame:setPosition(cc.p(_panelItem:getContentSize().width / 2, _panelItem:getContentSize().height / 2))
            _panelItem:addChild(ui_choose_frame)
            selectedActivityId = ActivityThings[1].int["1"]
            showActivityWidget(selectedActivityId)
        end
        if jumpName and obj.string["9"] == jumpName then
            ui_choose_frame:setPosition(cc.p(_panelItem:getContentSize().width / 2, _panelItem:getContentSize().height / 2))
            _panelItem:addChild(ui_choose_frame)
            selectedActivityId = obj.int["1"]
            showActivityWidget(selectedActivityId)
        end
        -------------------------
        _panelItem:setTag(obj.int["1"])
        _panelItem:setName(obj.string["9"])
        scrollView:addChild(_panelItem)
    end
end

function UIActivityPanel.init()
    ui_choose_frame = ccui.ImageView:create("ui/frame_fg.png")
    ui_choose_frame:setEnabled(false)
    ui_choose_frame:retain()
    scrollView = ccui.Helper:seekNodeByName(UIActivityPanel.Widget, "view_warrior")
    panelItem = scrollView:getChildByName("btn_base_warrior"):clone()
    if panelItem:getReferenceCount() == 1 then
        panelItem:retain()
    end
end

function UIActivityPanel.setup()
    table.merge(net.SysActivity, UIActivityPanel.privateActivity)
    AudioEngine.playMusic("sound/activity.mp3", true)
    first = true
    ActivityThings = UIActivityPanel.getActivityThing()
    scrollView:removeAllChildren()
    if ActivityThings then
        local function compareFunc(obj1, obj2)
            if obj1.int["11"] < obj2.int["11"] then
                return false
            else
                return true
            end
        end
        utils.quickSort(ActivityThings, compareFunc)
        scrollviewUpdate()
        local innerWidth, space = 0, 15

        local childs = scrollView:getChildren()
        innerWidth =(panelItem:getContentSize().width + space) * #childs
        if innerWidth < scrollView:getContentSize().width then
            innerWidth = scrollView:getContentSize().width
        end
        scrollView:setInnerContainerSize(cc.size(innerWidth, scrollView:getContentSize().height))
        local x, y = 0, scrollView:getContentSize().height / 2
        for i = 1, #childs do
            x = panelItem:getContentSize().width / 2 + space / 2 +(i - 1) *(panelItem:getContentSize().width + space)
            childs[i]:setPosition(cc.p(x, y))
            local sname = nil
            local tag = childs[i]:getTag()
            if RECHARGE_TAG ~= tag then
                sname = net.SysActivity[tostring(tag)].string["9"]
            end
            if sname and sname == "hJYStore" then
                if UIGuidePeople.levelStep then
                    jumpName = sname
                    setScrollViewFocus()
                end
                UIActivityPanel.Widget:runAction(cc.Sequence:create(cc.DelayTime:create(0.5), cc.CallFunc:create( function()
                    UIGuidePeople.isGuide(childs[i], UIActivityPanel)
                end )))
            end
        end
    end
    if not UIGuidePeople.levelStep then
        setScrollViewFocus()
    end
end

function UIActivityPanel.free()
    scrollView:removeAllChildren()
    selectedActivityId = nil
    ActivityThings = nil
    _rechargeActivity = nil
    first = nil
    jumpName = nil
    AudioEngine.playMusic("sound/bg_music.mp3", true)
end

function UIActivityPanel.scrollByName(sname, _jumpName)
    if _jumpName then
        jumpName = _jumpName
        return
    end
    local childs = scrollView:getChildren()
    for i = 1, #childs do
        local _acticity = net.SysActivity[tostring(childs[i]:getTag())]
        if _acticity and _acticity.string["9"] == sname then
            if sname == "hJYStore" and net.InstPlayer.int["4"] < DictFunctionOpen[tostring(StaticFunctionOpen.hJYStoreLevel)].level then
                UIManager.showToast("神秘商店开启等级:" .. DictFunctionOpen[tostring(StaticFunctionOpen.hJYStoreLevel)].level)
                return
            end
            selectedActivityId = childs[i]:getTag()
            ui_choose_frame:removeFromParent()
            ui_choose_frame:setPosition(cc.p(childs[i]:getContentSize().width / 2, childs[i]:getContentSize().height / 2))
            childs[i]:addChild(ui_choose_frame)
            showActivityWidget(selectedActivityId)
            break
        elseif sname == "recharge" and childs[i]:getTag() == RECHARGE_TAG then
            local function chargeCallBack(pack)
                if pack.msgdata.int and pack.msgdata.int["1"] then
                    dp.rechargeGold = pack.msgdata.int["1"]
                else
                    return
                end
                selectedActivityId = RECHARGE_TAG
                ui_choose_frame:removeFromParent()
                ui_choose_frame:setPosition(cc.p(childs[i]:getContentSize().width / 2, childs[i]:getContentSize().height / 2))
                childs[i]:addChild(ui_choose_frame)
                replaceWidget("ui_activity_gift")
            end
            utils.checkGOLD(0, chargeCallBack)
        end
    end
end

function UIActivityPanel.setRechargeActivity(_activitys)
    _rechargeActivity = _activitys
end

function UIActivityPanel.getActivityThing()
    --[[
  local activityThings ={}
  for key,obj in pairs(net.SysActivity) do
    if obj.int["8"] == 1 then
      if obj.int["7"] == 1  then
        if obj.string["9"] == "flashSale" and not UIActivityBuy.isActivityEnd() then ---限时抢购
          table.insert(activityThings,obj)
        elseif obj.string["9"] == "fund" and not UIActivityFoundation.isActivityEnd() then ---开服基金
          table.insert(activityThings,obj)
        elseif obj.string["9"] ~= "flashSale" and obj.string["9"] ~= "fund" then
          table.insert(activityThings,obj)
        end
      else
        if obj.string["4"] ~= "" and obj.string[5] ~= "" then
          local startTime =utils.GetTimeByDate(obj.string["4"])
          local endTime = utils.GetTimeByDate(obj.string["5"])
          local currentTime = utils.getCurrentTime()
          if startTime < currentTime and currentTime < endTime then
            table.insert(activityThings,obj)
          end
        end
      end
    end
  end
  return activityThings
  --]]
    local activityIsView = function(_activitySname)
        for key, obj in pairs(UIActivityPanel.rechargeActivity) do
            if obj == _activitySname then
                if _rechargeActivity then
                    return true
                else
                    return false
                end
            end
        end
        if not _rechargeActivity then
            return true
        end
    end
    local activityThings = { }
    for key, obj in pairs(net.SysActivity) do
        if (0 < obj.int["1"] and obj.int["1"] <= 100) or UIActivityPanel.privateActivity[key] then
            -- 精彩活动的活动范围
            local isView = obj.int["8"]
            -- 0-不显示,1-显示
            if isView == 1 and activityIsView(obj.string["9"]) then
                if obj.string["9"] == "flashSale" then
                    if net.InstActivity then
                        for _k, _obj in pairs(net.InstActivity) do
                            if _obj.int["3"] == obj.int["1"] and _obj.int["6"] - utils.getCurrentTime() > 0 then
                                table.insert(activityThings, obj)
                                break
                            end
                        end
                    end
                else
                    local startTime = obj.string["4"]
                    local endTime = obj.string["5"]
                    local currentTime = utils.getCurrentTime()
                    if obj.string["9"] == "addRecharge" or(startTime == "" and endTime == "") or(utils.GetTimeByDate(startTime) < currentTime and currentTime < utils.GetTimeByDate(endTime)) then
                        table.insert(activityThings, obj)
                    end
                end
            end
        end
    end
    return activityThings
end

function UIActivityPanel.getInstThingByName(sname)
    local instActivityObj = nil
    if net.InstActivity then
        for key, obj in pairs(net.InstActivity) do
            if net.SysActivity[tostring(obj.int["3"])].string["9"] == sname then
                instActivityObj = obj
                return instActivityObj
            end
        end
    end
end

function UIActivityPanel.isEndActivityByEndTime(_time)
    if utils.GetTimeByDate(_time) <= utils.getCurrentTime() then
        return true
    end
end