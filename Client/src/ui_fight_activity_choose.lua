UIFightActivityChoose={}
local barrierLevel = 1
local chapter ={}
local activityBarrierTimes =nil
local scrollView= nil
local thingItem= nil
local dropThing={}
local _dropThings = nil
local ui_text_left_number = nil
local  flag = nil
local isFirstAccess = true
UIFightActivityChoose.wingTo = false
local function setLocalOrder(_tag)
    local image_card_difficulty = {}
    for i =1,3 do  
        image_card_difficulty[i] = ccui.Helper:seekNodeByName(UIFightActivityChoose.Widget, "image_card_difficulty" .. i)
        local image_bg = ccui.Helper:seekNodeByName(UIFightActivityChoose.Widget, "image_base_difficulty" .. i)
        if _tag == i then 
            image_bg:setLocalZOrder(1)
        else 
            image_bg:setLocalZOrder(0)
        end
    end
    for key,obj in pairs(DictBarrier) do
        if chapter.chapterId == obj.chapterId and barrierLevel ==  obj.type  then
            chapter.barrierId = obj.id
            chapter.openLevel = obj.openLevel
            local cardId = obj.cardId
            local bigUiId = DictCard[tostring(cardId)].bigUiId
            local imageName = DictUI[tostring(bigUiId)].fileName
            image_card_difficulty[barrierLevel]:loadTexture("image/" .. imageName)
        elseif chapter.chapterId == obj.chapterId and barrierLevel ~=  obj.type  then
            local cardId = obj.cardId
            local bigUiId = DictCard[tostring(cardId)].bigUiId
            local imageName = DictUI[tostring(bigUiId)].fileName
            image_card_difficulty[obj.type]:loadTexture("image/" .. imageName)
        end
    end
end

local function setScrollViewItem(_item,objTable)
    local thingIcon = _item:getChildByName("image_good")
    local thingName = thingIcon:getChildByName("text_good_name")
    local tableTypeId,tableFieldId, thingNum = objTable[1], objTable[2], objTable[3]
    local name,Icon = utils.getDropThing(tableTypeId,tableFieldId)
    thingName:setString(name)
    thingIcon:loadTexture(Icon)
    utils.addBorderImage(tableTypeId,tableFieldId,_item)
end
local function scrollviewUpdate()
    for key, obj in pairs(dropThing) do
       local Item = thingItem:clone()
       setScrollViewItem(Item, obj)
       scrollView:addChild(Item)
    end
end
function UIFightActivityChoose.init()
    local btn_close = ccui.Helper:seekNodeByName(UIFightActivityChoose.Widget, "btn_close")
    local btn_fight = ccui.Helper:seekNodeByName(UIFightActivityChoose.Widget, "btn_fight")
    btn_close:setPressedActionEnabled(true)
    btn_fight:setPressedActionEnabled(true)
    local image_card_difficulty ={}
    local btn_add = ccui.Helper:seekNodeByName(UIFightActivityChoose.Widget, "btn_add")
    ui_text_left_number = ccui.Helper:seekNodeByName( UIFightActivityChoose.Widget , "text_left_number")
    btn_add:setEnabled(false)
    btn_add:setVisible( false )
    local function TouchEvent(sender,eventType)
        if eventType == ccui.TouchEventType.ended then
            if sender== btn_close then 
                AudioEngine.playEffect("sound/button.mp3")
                UIManager.popScene()
                if UIFightActivityChoose.wingTo then
                    
                    UIManager.showWidget("ui_notice", "ui_lineup")
                    UIManager.showWidget("ui_menu")
                    UILineup.toWingInfo()
--                    UIManager.pushScene("ui_wing_info")
--                    local btn_medicine = ccui.Helper:seekNodeByName(UIWingInfo.Widget, "btn_intensify")
--                    btn_medicine:releaseUpEvent()
                end
            elseif sender == btn_add then 
                AudioEngine.playEffect("sound/button.mp3")
                if activityBarrierTimes > 0 then 
                    UIManager.showToast("当前剩余次数未用完，无法购买")
                else
                    local buyNum =0
                    local haveBarrierNum =0 
                    local instPlayerChapterId = nil
                    for key,ActivityObj in pairs(net.InstPlayerChapter) do
                        if ActivityObj.int["3"] == chapter.chapterId then
                            haveBarrierNum = ActivityObj.int["4"]
                            if ActivityObj.int["8"] ~= nil then 
                               buyNum = ActivityObj.int["8"]
                            end
                            instPlayerChapterId = ActivityObj.int["1"]
                        end
                    end
                    local VipNum = net.InstPlayer.int["19"]
                    local baseMoney = DictSysConfig[tostring(StaticSysConfig.activityChapterInitGold)].value
                    local oneAddMoney  = DictSysConfig[tostring(StaticSysConfig.activityChapterInitGoldAdd)].value
                    local BuyBarrierTimeMoney  = baseMoney + buyNum*oneAddMoney
                    local VipTime = 0
                    if chapter.chapterId == DictSysConfig[tostring(StaticSysConfig.slbz)].value then 
                        VipTime= DictVIP[tostring(VipNum+1)].silverActivityChapterBuyTimes
                    elseif chapter.chapterId == DictSysConfig[tostring(StaticSysConfig.tsxc)].value then 
                        VipTime= DictVIP[tostring(VipNum+1)].talentActivityChapterBuyTimes
                    elseif chapter.chapterId == DictSysConfig[tostring(StaticSysConfig.yhgt)].value then 
                        VipTime= DictVIP[tostring(VipNum+1)].expActivityChapterBuyTimes
                    elseif chapter.chapterId == DictSysConfig[tostring(StaticSysConfig.wysm)].value then 
                        VipTime= DictVIP[tostring(VipNum+1)].soulActivityChapterBuyTimes
                    end
                    local prompt = "是否花费" .. BuyBarrierTimeMoney .."元宝购买一次活动副本攻打次数？\n 您今日已经购买" .. buyNum .. "次   \n您是Vip" .. VipNum .. "玩家,今日可购买" .. VipTime .."次"
                    if instPlayerChapterId~= nil then
                        if VipNum == 0 then
                            UIManager.showToast("您还不是Vip,无法购买")
                            return
                        end
                        if buyNum < VipTime then 
                            utils.PromptDialog(UIFight.sendActivityBarrierTimeRequest,prompt,instPlayerChapterId)
                        else
                            UIManager.showToast("Vip购买次数已到")
                        end
                    else
                        UIManager.showToast("请求失败")
                    end
                end
            elseif sender == image_card_difficulty[1] then
                barrierLevel =1
                UIFightActivityChoose.setup()
            elseif sender == image_card_difficulty[2] then
                barrierLevel =2 
                UIFightActivityChoose.setup()
            elseif sender == image_card_difficulty[3] then
                barrierLevel =3
                UIFightActivityChoose.setup()
            elseif sender == btn_fight then
                AudioEngine.playEffect("sound/fight.mp3")
                if activityBarrierTimes > 0 then
                    local nowLevel = net.InstPlayer.int["4"]
                    if nowLevel >= chapter.openLevel then 
                        for key,obj in pairs(DictBarrierLevel) do
                            if obj.barrierId == chapter.barrierId and obj.level == barrierLevel then 
                                chapter.barrierLevelId = obj.id
                            end
                        end
                        UIManager.popScene()
                        utils.sendFightData(chapter,dp.FightType.FIGHT_TASK.ACTIVITY)
                        UIFightMain.loading()
                        cc.UserDefault:getInstance():setIntegerForKey("UIFightActivityChooseBarrierLevel",barrierLevel)
                    else
                        UIManager.showToast(string.format("%d级开启",chapter.openLevel))
                    end
                else
                    UIManager.showToast("今日挑战次数已用完")
                end
            end
        end
    end
    for i=1,3 do
        image_card_difficulty[i] =ccui.Helper:seekNodeByName(UIFightActivityChoose.Widget, "image_card_difficulty" .. i)
        image_card_difficulty[i]:addTouchEventListener(TouchEvent)
        image_card_difficulty[i]:setEnabled(true)
        image_card_difficulty[i]:setTouchEnabled(true)
    end
    btn_close:addTouchEventListener(TouchEvent)
    btn_fight:addTouchEventListener(TouchEvent)
   -- btn_add:addTouchEventListener(TouchEvent)
    scrollView = ccui.Helper:seekNodeByName(UIFightActivityChoose.Widget, "view_get")
    thingItem = scrollView:getChildByName("image_frame_good"):clone()
    if thingItem:getReferenceCount() == 1 then
       thingItem:retain()
    end
end
function UIFightActivityChoose.setup()
    
    scrollView:removeAllChildren()
    if isFirstAccess then
        local tempLevel = cc.UserDefault:getInstance():getIntegerForKey("UIFightActivityChooseBarrierLevel",1)
        isFirstAccess = false
        barrierLevel = tempLevel
    end
    setLocalOrder(barrierLevel)
    dropThing = {}
    
    if chapter.barrierId ~= nil then
        local things = DictBarrier[tostring(chapter.barrierId)].things
        if chapter.chapterId == DictSysConfig[tostring(StaticSysConfig.wysm)].value then
            things = _dropThings[barrierLevel]
        end
        local thingsTable = utils.stringSplit(things,";")
        for key,obj in pairs(thingsTable) do
             dropThing[#dropThing+1] = utils.stringSplit(obj,"_")
        end
    end
    if next(dropThing) then
        scrollviewUpdate()
        local innerHieght, space, _col = 0, 15, 4
        local childs = scrollView:getChildren()
        if #childs < _col then
          innerHieght = thingItem:getContentSize().height + thingItem:getChildByName("image_good"):getChildByName("text_good_name"):getContentSize().height + space
        elseif #childs % _col == 0 then
          innerHieght = (#childs / _col) * (thingItem:getContentSize().height + thingItem:getChildByName("image_good"):getChildByName("text_good_name"):getContentSize().height + space) + space
        else
          innerHieght = math.ceil(#childs / _col) * (thingItem:getContentSize().height + thingItem:getChildByName("image_good"):getChildByName("text_good_name"):getContentSize().height + space) + space
        end
        if innerHieght < scrollView:getContentSize().height then
          innerHieght = scrollView:getContentSize().height
        end
        scrollView:setInnerContainerSize(cc.size(scrollView:getContentSize().width, innerHieght))
        
        local prevChild = nil
        local _tempI, x, y = 1, 0, 0
        for i = 1, #childs do
          x = _tempI * (scrollView:getContentSize().width / _col) - (scrollView:getContentSize().width / _col) / 2
          _tempI = _tempI + 1
          if i < _col then
            y = scrollView:getInnerContainerSize().height - childs[i]:getContentSize().height / 2 - space
            prevChild = childs[i]
            childs[i]:setPosition(cc.p(x, y))
          elseif i % _col == 0 then
            childs[i]:setPosition(cc.p(x, y))
            y = prevChild:getBottomBoundary() - prevChild:getChildByName("image_good"):getChildByName("text_good_name"):getContentSize().height - childs[i]:getContentSize().height / 2 - space
            _tempI = 1
            prevChild = childs[i]
          else
            y = prevChild:getBottomBoundary() - prevChild:getChildByName("image_good"):getChildByName("text_good_name"):getContentSize().height - childs[i]:getContentSize().height / 2 - space
            childs[i]:setPosition(cc.p(x, y))
          end
        end
    end
    local DictChapterObj = DictChapter[tostring(chapter.chapterId)]
    local haveBarrierNum =0
    if net.InstPlayerChapter then
        for key,ActivityObj in pairs(net.InstPlayerChapter) do
            if ActivityObj.int["3"] == DictChapterObj.id then
                haveBarrierNum = ActivityObj.int["4"]
            end
        end
    end
    
    
    activityBarrierTimes = DictChapterObj.fightNum - haveBarrierNum
    if flag and flag == 3 then
        local VipNum = net.InstPlayer.int["19"] 
        if VipNum >= 0 then
            local VipTime1 = 0
            if chapter.chapterId == DictSysConfig[tostring(StaticSysConfig.slbz)].value then 
                VipTime1= DictVIP[tostring(VipNum+1)].silverActivityChapterBuyTimes
            elseif chapter.chapterId == DictSysConfig[tostring(StaticSysConfig.tsxc)].value then 
                VipTime1= DictVIP[tostring(VipNum+1)].talentActivityChapterBuyTimes
            elseif chapter.chapterId == DictSysConfig[tostring(StaticSysConfig.yhgt)].value then 
                VipTime1= DictVIP[tostring(VipNum+1)].expActivityChapterBuyTimes
            elseif chapter.chapterId == DictSysConfig[tostring(StaticSysConfig.wysm)].value then 
                VipTime1= DictVIP[tostring(VipNum+1)].soulActivityChapterBuyTimes
            elseif chapter.chapterId == DictSysConfig[tostring(StaticSysConfig.shcx)].value then 
                VipTime1= DictVIP[tostring(VipNum+1)].wingChapterNum
            end
           activityBarrierTimes = activityBarrierTimes + VipTime1
        end
        
    end
     
    ui_text_left_number:setString(string.format("剩余次数：%d次",activityBarrierTimes))
end
function UIFightActivityChoose.setChapter(_chapterId , _flag , _things)
    chapter.chapterId =_chapterId
    barrierLevel =1
    flag = _flag
    if chapter.chapterId == DictSysConfig[tostring(StaticSysConfig.wysm)].value then
        _dropThings = _things
    end
end

function UIFightActivityChoose.free()
    isFirstAccess = true
    if thingItem and thingItem:getReferenceCount() >= 1 then
       thingItem:release()
       thingItem = nil
    end
    if scrollView then
        scrollView:removeAllChildren()
        scrollView = nil
    end
    flag = nil
    _dropThings = nil
end