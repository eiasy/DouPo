UIActivityEmail = { }
local tag = 1
local btn_all = nil
local btn_fight = nil
local btn_friend = nil
local btn_system = nil
local scrollView = nil
local mailItem = nil
local mailTable = { }
local fightTable = { }
local friendTable = { }
local systemTable = { }

local function compareMail(value1, value2)
    -- if (value1.int["4"] == 3 and value2.int["4"] == 3) then
    local iTime1 = utils.GetTimeByDate(value1.string["9"])
    local iTime2 = utils.GetTimeByDate(value2.string["9"])
    return iTime1 < iTime2
    -- end
end

function refreshTable()
    mailTable = { }
    for key, obj in pairs(net.InstPlayerMail) do
        table.insert(mailTable, obj)
    end
    utils.quickSort(mailTable, compareMail)
    fightTable = { }
    friendTable = { }
    systemTable = { }
    UIOreEmail.mail = { }
    for key, value in pairs(mailTable) do
        if value.int["4"] == 1 or value.int["4"] == 2 or value.int["4"] == 5 then
            table.insert(fightTable, value)
        elseif value.int["4"] == 3 then
            table.insert(friendTable, value)
        elseif value.int["4"] == 4 then
            table.insert(systemTable, value)
        end

        if value.int["5"] == 5 then
            table.insert(UIOreEmail.mail, value)
        end
    end
end

function showREmailInfo(name)
    local dialog = ccui.Layout:create()
    dialog:setContentSize(UIManager.screenSize)
    dialog:setBackGroundColorType(ccui.LayoutBackGroundColorType.solid)
    dialog:setBackGroundColor(cc.c3b(0, 0, 0))
    dialog:setBackGroundColorOpacity(130)
    dialog:setTouchEnabled(true)

    local bg_image = cc.Scale9Sprite:create("ui/dialog_bg.png")
    bg_image:setAnchorPoint(cc.p(0.5, 0.5))
    bg_image:setPreferredSize(cc.size(450, 300))
    bg_image:setPosition(cc.p(UIManager.screenSize.width / 2, UIManager.screenSize.height / 2))
    dialog:addChild(bg_image)
    local bgSize = bg_image:getPreferredSize()

    local title = ccui.Text:create()
    title:setAnchorPoint(0, 0.5)
    title:setString("我对")
    title:setFontName(dp.FONT)
    title:setFontSize(23)
    title:setTextColor(cc.c4b(255, 255, 255, 255))
    title:setPosition(cc.p(bg_image:getPositionX() - bgSize.width / 2 - 50, bgSize.height * 0.83))
    bg_image:addChild(title)

    local title1 = ccui.Text:create()
    title1:setAnchorPoint(0, 0.5)
    title1:setString(name)
    title1:setFontName(dp.FONT)
    title1:setFontSize(23)
    title1:setTextColor(cc.c4b(0, 255, 0, 255))
    title1:setPosition(title:getPositionX() + title:getContentSize().width, title:getPositionY())
    bg_image:addChild(title1)

    local title2 = ccui.Text:create()
    title2:setAnchorPoint(0, 0.5)
    title2:setString("说：")
    title2:setFontName(dp.FONT)
    title2:setFontSize(23)
    title2:setTextColor(cc.c4b(255, 255, 255, 255))
    title2:setPosition(title1:getPositionX() + title1:getContentSize().width, title1:getPositionY())
    bg_image:addChild(title2)


    local text = ccui.RichText:create();
    local re1 = ccui.RichElementText:create(1, cc.c3b(0, 0, 0), 255, "最多输入40字", dp.FONT, 23)
    text:pushBackElement(re1)
    local imgBg = ccui.Scale9Sprite:create("ui/tk_di02.png")
    imgBg:setPreferredSize(cc.size(400, 120))
    local msgBox = cc.EditBox:create(cc.size(400, 120), ccui.Scale9Sprite:create())
    -- msgBox:setPlaceHolder("最多输入40字")
    msgBox:setPlaceholderFontSize(23)
    local textContent = ""
    local function editboxEventHandler(eventType)
        --        if eventType == "began" then
        --            -- 当编辑框获得焦点并且键盘弹出的时候被调用
        --            msgBox:setText(textContent)
        --        elseif eventType == "ended" then
        --            -- 当编辑框失去焦点并且键盘消失的时候被调用
        --            textContent = msgBox:getText()
        --            text:removeElement( re1 )
        --            re1 = ccui.RichElementText:create( 1, cc.c3b(0, 0, 0), 255, textContent  , dp.FONT , 23 )
        --            --msgBox:
        --            text:pushBackElement(re1)
        --            if textContent ~= "" then
        --                msgBox:setText("   ")
        --            end
        --        elseif eventType == "changed" then
        --            -- 当编辑框的文本被修改的时候被调用
        --            text:removeElement( re1 )
        --            re1 = ccui.RichElementText:create( 1, cc.c3b(0, 0, 0), 255, msgBox:getText() , dp.FONT , 23 )
        --            --msgBox:
        --            text:pushBackElement(re1)
        --        elseif eventType == "return" then
        --            text:removeElement( re1 )
        --            re1 = ccui.RichElementText:create( 1, cc.c3b(0, 0, 0), 255, textContent , dp.FONT , 23 )
        --            --msgBox:
        --            text:pushBackElement(re1)
        --            -- 当用户点击编辑框的键盘以外的区域，或者键盘的Return按钮被点击时所调用
        --        end

        local isIOS = device.platform == "ios"
        if eventType == "return" then
            text:removeElement(re1)
            textContent = msgBox:getText()
            if msgBox:getText() == "" then
                msgBox:setZOrder(2)
                re1 = ccui.RichElementText:create(1, cc.c3b(0, 0, 0), 255, "最多输入40字", dp.FONT, 23)
            else
                re1 = ccui.RichElementText:create(1, cc.c3b(0, 0, 0), 255, msgBox:getText(), dp.FONT, 23)
            end

            text:pushBackElement(re1)
        elseif eventType == "began" then
            msgBox:setZOrder(0)
            if isIOS then text:setVisible(false) msgBox:setZOrder(2) end
        elseif eventType == "ended" then
            if isIOS then text:setVisible(true) msgBox:setZOrder(0) end
        end
    end
    -- msgLabel:setString(msg)
    msgBox:setFontName(dp.FONT)
    msgBox:setFontSize(23)
    msgBox:setFontColor(cc.c3b(0, 0, 0))
    msgBox:setMaxLength(40)
    msgBox:setPosition(cc.p(bgSize.width / 2, bgSize.height / 2 + 10))
    msgBox:registerScriptEditBoxHandler(editboxEventHandler)
    bg_image:addChild(msgBox, 2)

    imgBg:setPosition(cc.p(bgSize.width / 2, bgSize.height / 2 + 10))
    bg_image:addChild(imgBg, 1)

    text:setContentSize(cc.size(msgBox:getContentSize().width - 20, msgBox:getContentSize().height - 20))
    text:ignoreContentAdaptWithSize(false)
    --  text:setTextHorizontalAlignment(cc.TEXT_ALIGNMENT_CENTER)
    --  text:setTextVerticalAlignment(cc.TEXT_ALIGNMENT_CENTER)
    -- text:setAnchorPoint( cc.p( 0 , 1 ) )
    text:setPosition(cc.p(msgBox:getPositionX(), msgBox:getPositionY()))
    bg_image:addChild(text, 2)



    local sureBtn = ccui.Button:create("ui/tk_btn_big_blue.png", "ui/tk_btn_big_blue.png")
    sureBtn:setScale(0.8)
    sureBtn:setTitleText("取消")

    sureBtn:setTitleFontName(dp.FONT)
    sureBtn:setTitleColor(cc.c3b(255, 255, 255))
    sureBtn:setTitleFontSize(30)
    sureBtn:setPressedActionEnabled(true)
    sureBtn:setTouchEnabled(true)
    sureBtn:setPosition(cc.p(bgSize.width * 0.25, bgSize.height * 0.2))
    bg_image:addChild(sureBtn)
    local backBtn = ccui.Button:create("ui/tk_btn_big_red.png", "ui/tk_btn_big_red.png")

    backBtn:setTitleText("发送")
    backBtn:setScale(0.8)
    backBtn:setTitleFontName(dp.FONT)
    backBtn:setTitleColor(cc.c3b(255, 255, 255))
    backBtn:setTitleFontSize(30)
    backBtn:setPressedActionEnabled(true)
    backBtn:setTouchEnabled(true)
    backBtn:setPosition(cc.p(bgSize.width * 0.75, bgSize.height * 0.2))
    bg_image:addChild(backBtn)

    local function btnEvent(sender, eventType)
        if eventType == ccui.TouchEventType.ended then
            AudioEngine.playEffect("sound/button.mp3")
            if sender == backBtn then
                cclog("send Email")
                if textContent and textContent ~= "" then
                    netSendPackage(
                    {
                        header = StaticMsgRule.sendMail,
                        msgdata = { string = { oppoName = name, oppoContent = textContent } }
                    }
                    )
                    dialog:removeFromParent()
                else
                    UIManager.showToast("请输入内容")
                end
            elseif sender == sureBtn then
                dialog:removeFromParent()
            end
        end
    end
    sureBtn:addTouchEventListener(btnEvent)
    backBtn:addTouchEventListener(btnEvent)
    bg_image:setScale(0.1)
    UIManager.uiLayer:addChild(dialog, 10000)
    bg_image:runAction(cc.Sequence:create(cc.ScaleTo:create(0.2, 1.1), cc.ScaleTo:create(0.06, 1)))
end

local function netErrorCallbackFunc(pack)
    local protocol = tonumber(pack.header)
    local msgdata = pack.msgdata
    if protocol == StaticMsgRule.mineFightWin then
        UIOre.showFightResult(UIActivityEmail, -1, msgdata)
    elseif protocol == StaticMsgRule.mineFail then
        UIOre.showFightResult(UIActivityEmail, 0)
    end
end

local function netCallbackFunc(pack)
    local protocol = tonumber(pack.header)
    local msgdata = pack.msgdata
    if protocol == StaticMsgRule.mineBeatBack then
        if msgdata.int.fightType == 0 or msgdata.int.fightType == 1 then
            pvp.loadGameData(pack)
            UIOreInfo.warParam = { msgdata.int.fightType, msgdata.int.playerId, msgdata.int.mineId }
            utils.sendFightData(nil, dp.FightType.FIGHT_MINE, function(isWin)
                if isWin then
                    netSendPackage( {
                        header = StaticMsgRule.mineFightWin,
                        msgdata =
                        {
                            int = { fightType = UIOreInfo.warParam[1], id = UIOreInfo.warParam[2], mineId = UIOreInfo.warParam[3] },
                            string = { coredata = GlobalLastFightCheckData }
                        }
                    } , netCallbackFunc, netErrorCallbackFunc)
                else
                    netSendPackage( { header = StaticMsgRule.mineFail, msgdata = { int = { mineId = UIOreInfo.warParam[3] } } }, netCallbackFunc, netErrorCallbackFunc)
                end
            end )
            if not UIFightMain.Widget or not UIFightMain.Widget:getParent() then
                UIFightMain.loading()
            else
                UIFightMain.setup()
            end
        end
    elseif protocol == StaticMsgRule.mineFightWin then
        UIOre.showFightResult(UIActivityEmail, 1, msgdata)
    elseif protocol == StaticMsgRule.mineFail then
        UIOre.showFightResult(UIActivityEmail, 0)
    end
end

local function setMailScrollViewItem(_Item, _obj)
    local btn_go = _Item:getChildByName("btn_go")
    local ui_name = _Item:getChildByName("text_title")
    local ui_time = _Item:getChildByName("text_time")
    local ui_description = ccui.Helper:seekNodeByName(_Item, "text_info")
    local _type = _obj.int["4"]
    -- cclog( "_type : " .. _type )
    btn_go:setPressedActionEnabled(true)
    local function btnTouchEvent(sender, eventType)
        if eventType == ccui.TouchEventType.ended then
            sender:retain()
            if _type == 1 then
                UIManager.hideWidget("ui_activity_panel")
                UILoot.show(1, 1)
            elseif _type == 2 then
                UIManager.hideWidget("ui_activity_panel")
                UIManager.showWidget("ui_arena")
            elseif _type == 3 then
                if dp.getUserData().roleLevel < 22 then
                    UIManager.showToast("需要等级达到22级")
                else
                    showREmailInfo(_obj.string["3"])
                end
            elseif _type == 4 then
            elseif _type == 5 then
                if _obj.int["6"] == 4 then
                    UIManager.showLoading()
                    netSendPackage( { header = StaticMsgRule.mineBeatBack, msgdata = { int = { minerId = _obj.int["5"] } } }, netCallbackFunc)
                else
                    UIManager.hideWidget("ui_activity_panel")
                    UIManager.hideWidget("ui_menu")
                    UIManager.showWidget("ui_ore")
                end
            end
            cc.release(sender)
        end
    end
    btn_go:addTouchEventListener(btnTouchEvent)
    local serverTime = utils.GetTimeByDate(_obj.string["9"])
    local currentTime = utils.getCurrentTime()
    local subTime = currentTime - serverTime
    local timeText = nil

    if math.floor(subTime /(3600 * 24)) > 0 then
        timeText = math.floor(subTime /(3600 * 24)) .. "天前"
    elseif math.floor(subTime / 3600) > 0 then
        timeText = math.floor(subTime / 3600) .. "小时前"
    elseif math.floor(subTime / 60) > 0 then
        timeText = math.floor(subTime / 60) .. "分钟前"
    elseif math.floor(subTime % 60) > 0 then
        timeText = math.floor(subTime % 60) .. "秒前"
    end
    ui_time:setString(timeText)
    if _type == 1 then
        btn_go:setTitleText("去抢夺")
        ui_name:setString("抢碎片防守失败")
        local chipId = _obj.int["5"]
        local chipName = DictChip[tostring(chipId)].name
        ui_description:setString(string.format("%s抢走了您的%s", _obj.string["3"], chipName))
    elseif _type == 2 then
        if _obj.int["6"] == 1 then
            ui_name:setString("竞技场防守成功")
            btn_go:setTitleText("去竞技")
            ui_description:setString(string.format("【%s】挑战您失败，您捍卫了您的名次", _obj.string["3"]))
        else
            ui_name:setString("竞技场防守失败")
            btn_go:setTitleText("去竞技")
            ui_description:setString(string.format("【%s】击败了您，您的名次滑落到%d名", _obj.string["3"], _obj.int["5"]))
        end
    elseif _type == 3 then
        ui_name:setString("发送者：" .. _obj.string["3"])
        ui_description:setString(_obj.string["7"])
        --        btn_go:setScale( 0.8 )
        btn_go:loadTextureNormal("ui/tk_btn01.png")
        btn_go:loadTexturePressed("ui/tk_btn01.png")
        btn_go:setTitleText("回复")
    elseif _type == 4 then
        ui_name:setString(_obj.string["3"])
        ui_description:setString(_obj.string["7"])
        btn_go:setVisible(false)
    elseif _type == 5 then
        local name, description = UIOreEmail.getEmailInfo(_obj)
        ui_name:setString(name)
        ui_description:setString(description)
        btn_go:setTitleText(_obj.int["6"] == 4 and "去反击" or "去占矿")
    end
end

function refreshList(index)
    -- refreshTable()
    local tempTable = nil
    if index == 1 then
        tempTable = mailTable
    elseif index == 2 then
        tempTable = fightTable
    elseif index == 3 then
        tempTable = friendTable
    elseif index == 4 then
        tempTable = systemTable
    end
    scrollView:removeAllChildren()
    if next(tempTable) and mailItem then
        utils.updateView(UIActivityEmail, scrollView, mailItem, tempTable, setMailScrollViewItem)
    end
    tempTable = nil
end

function freshButton(index1, tagIndex)
    if index1 ~= tagIndex then
        tag = tagIndex
        if index1 == 1 and btn_all then
            btn_all:loadTextureNormal("ui/yh_btn01.png")
            btn_all:setTitleColor(cc.c4b(255, 255, 255, 255))
        elseif index1 == 2 and btn_fight then
            btn_fight:loadTextureNormal("ui/yh_btn01.png")
            btn_fight:setTitleColor(cc.c4b(255, 255, 255, 255))
        elseif index1 == 3 and btn_friend then
            btn_friend:loadTextureNormal("ui/yh_btn01.png")
            btn_friend:setTitleColor(cc.c4b(255, 255, 255, 255))
        elseif index1 == 4 and btn_system then
            btn_system:loadTextureNormal("ui/yh_btn01.png")
            btn_system:setTitleColor(cc.c4b(255, 255, 255, 255))
        end
        if tag == 1 and btn_all then
            btn_all:loadTextureNormal("ui/yh_btn02.png")
            btn_all:setTitleColor(cc.c4b(51, 25, 4, 255))
            refreshList(1)
        elseif tag == 2 and btn_fight then
            btn_fight:loadTextureNormal("ui/yh_btn02.png")
            btn_fight:setTitleColor(cc.c4b(51, 25, 4, 255))
            refreshList(2)
        elseif tag == 3 and btn_friend then
            btn_friend:loadTextureNormal("ui/yh_btn02.png")
            btn_friend:setTitleColor(cc.c4b(51, 25, 4, 255))
            refreshList(3)
        elseif tag == 4 and btn_system then
            btn_system:loadTextureNormal("ui/yh_btn02.png")
            btn_system:setTitleColor(cc.c4b(51, 25, 4, 255))
            refreshList(4)
        end
    end
end

function UIActivityEmail.init()
    scrollView = ccui.Helper:seekNodeByName(UIActivityEmail.Widget, "view_success")
    mailItem = scrollView:getChildByName("image_base_email"):clone()
    mailItem:retain()
    btn_all = ccui.Helper:seekNodeByName(UIActivityEmail.Widget, "btn_all")
    btn_fight = ccui.Helper:seekNodeByName(UIActivityEmail.Widget, "btn_fight")
    btn_friend = ccui.Helper:seekNodeByName(UIActivityEmail.Widget, "btn_friend")
    btn_system = ccui.Helper:seekNodeByName(UIActivityEmail.Widget, "btn_set")
    local function onBtnEvent(sender, eventType)
        if eventType == ccui.TouchEventType.ended then
            if sender == btn_all then
                if tag ~= 1 then
                    freshButton(tag, 1)
                end
            elseif sender == btn_fight then
                if tag ~= 2 then
                    freshButton(tag, 2)
                end
            elseif sender == btn_friend then
                if tag ~= 3 then
                    freshButton(tag, 3)
                end
            elseif sender == btn_system then
                if tag ~= 4 then
                    freshButton(tag, 4)
                end
            end
        end
    end
    btn_all:addTouchEventListener(onBtnEvent)
    btn_fight:addTouchEventListener(onBtnEvent)
    btn_friend:addTouchEventListener(onBtnEvent)
    btn_system:addTouchEventListener(onBtnEvent)
end

function UIActivityEmail.setup()
    scrollView:removeAllChildren()
    if net.InstPlayerMail then
        UIHomePage.yj = nil
        UIOre.yj = nil
        refreshTable()
    end
    if next(mailTable) and mailItem and tag == 1 then
        utils.updateView(UIActivityEmail, scrollView, mailItem, mailTable, setMailScrollViewItem)
    end
    freshButton(tag, 1)
    --     UIActivityPanel.addImageHint(false,"email")
end

function UIActivityEmail.free()
    -- mailItem:release()
end
