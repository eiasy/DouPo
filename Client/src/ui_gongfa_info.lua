UIGongfaInfo = { }

local ui_scrollView = nil
local ui_svItem = nil

local _instMagicId = nil
local _dictMagicId = nil
local _isView = nil
local _isPvp = nil
local _btnDischargeX = 0
local _btnIntensifyX = 0

local function netCallbackFunc(data)
    UIManager.popScene()
    UIManager.flushWidget(UILineup)
end

local function setScrollView(item, data)
    local dictCardData = DictCard[tostring(data.cardId)]
    local ui_cardFrame = item:getChildByName("image_frame_card")
    local ui_cardIcon = ui_cardFrame:getChildByName("image_card")
    local ui_cardName = ccui.Helper:seekNodeByName(item, "text_name")
    local ui_luckName = ccui.Helper:seekNodeByName(item, "text_luck")
    local ui_luckDesc = ccui.Helper:seekNodeByName(item, "text_property")
    ui_cardFrame:loadTexture(utils.getQualityImage(dp.Quality.card, dictCardData.qualityId, dp.QualityImageType.small))
    ui_cardIcon:loadTexture("image/" .. DictUI[tostring(dictCardData.smallUiId)].fileName)
    ui_cardName:setString(dictCardData.name)
    ui_luckName:setString(data.name)
    ui_luckDesc:setString(data.description)
end

local function setBottomBtnVisible(isShow)
    local btn_change = ccui.Helper:seekNodeByName(UIGongfaInfo.Widget, "btn_exit")
    -- 更换
    local btn_discharge = ccui.Helper:seekNodeByName(UIGongfaInfo.Widget, "btn_onekey")
    -- 卸下
    local btn_intensify = ccui.Helper:seekNodeByName(UIGongfaInfo.Widget, "btn_lineup")
    -- 强化
    btn_change:setVisible(isShow)
    btn_discharge:setVisible(isShow)
    btn_intensify:setVisible(isShow)
end

function UIGongfaInfo.init()
    local btn_close = ccui.Helper:seekNodeByName(UIGongfaInfo.Widget, "btn_close")
    -- 关闭
    local btn_change = ccui.Helper:seekNodeByName(UIGongfaInfo.Widget, "btn_exit")
    -- 更换
    local btn_discharge = ccui.Helper:seekNodeByName(UIGongfaInfo.Widget, "btn_onekey")
    -- 卸下
    local btn_intensify = ccui.Helper:seekNodeByName(UIGongfaInfo.Widget, "btn_lineup")
    -- 强化
    _btnDischargeX = btn_discharge:getPositionX()
    _btnIntensifyX = btn_intensify:getPositionX()
    btn_close:setPressedActionEnabled(true)
    btn_change:setPressedActionEnabled(true)
    btn_discharge:setPressedActionEnabled(true)
    btn_intensify:setPressedActionEnabled(true)
    local function onButtonEvent(sender, eventType)
        if eventType == ccui.TouchEventType.ended then
            AudioEngine.playEffect("sound/button.mp3")
            if sender == btn_close then
                UIManager.popScene()
            elseif sender == btn_change then
                local instMagicData = net.InstPlayerMagic[tostring(_instMagicId)]
                local instCardId = instMagicData.int["8"]
                if instCardId > 0 then
                    local magicType = instMagicData.int["4"]
                    local sendData = {
                        header = StaticMsgRule.putOn,
                        msgdata =
                        {
                            int =
                            {
                                instPlayerMagicId = 0,
                                instPlayerCardId = instCardId,
                                type = magicType,
                            }
                        }
                    }
                    if magicType == dp.MagicType.treasure then
                        UIBagGongFaList.setOperateType(UIBagGongFaList.OperateType.fabaoEquip, sendData)
                    elseif magicType == dp.MagicType.gongfa then
                        UIBagGongFaList.setOperateType(UIBagGongFaList.OperateType.gongfaEquip, sendData)
                    end
                    UIManager.pushScene("ui_bag_gongfa_list")
                end
            elseif sender == btn_discharge then
                local instMagicData = net.InstPlayerMagic[tostring(_instMagicId)]
                local instCardId = instMagicData.int["8"]
                if instCardId > 0 then
                    UIManager.showLoading()
                    netSendPackage( {
                        header = StaticMsgRule.putOff,
                        msgdata =
                        {
                            int =
                            {
                                instPlayerMagicId = _instMagicId,
                            }
                        }
                    } , netCallbackFunc)
                end
            elseif sender == btn_intensify then
                if _isView and _dictMagicId then
                    UIManager.popScene()
                else
                    UIGongfaIntensify.setInstMagicId(_instMagicId)
                    UIManager.pushScene("ui_gongfa_intensify")
                    -- UIManager.replaceScene("ui_gongfa_intensify")
                end
            end
        end
    end
    btn_close:addTouchEventListener(onButtonEvent)
    btn_change:addTouchEventListener(onButtonEvent)
    btn_discharge:addTouchEventListener(onButtonEvent)
    btn_intensify:addTouchEventListener(onButtonEvent)

    ui_scrollView = ccui.Helper:seekNodeByName(UIGongfaInfo.Widget, "view_luck")
    ui_svItem = ui_scrollView:getChildByName("image_base_di"):clone()
    if ui_svItem:getReferenceCount() == 1 then
        ui_svItem:retain()
    end
end

function UIGongfaInfo.setup()
    ui_scrollView:removeAllChildren()
    local btn_change = ccui.Helper:seekNodeByName(UIGongfaInfo.Widget, "btn_exit") -- 更换
    local btn_discharge = ccui.Helper:seekNodeByName(UIGongfaInfo.Widget, "btn_onekey")-- 卸下
    local btn_intensify = ccui.Helper:seekNodeByName(UIGongfaInfo.Widget, "btn_lineup")-- 强化
    btn_intensify:setTitleText("强化")
    if _isView then
        btn_change:setVisible(false)
        btn_discharge:setVisible(false)
        btn_intensify:setPositionX(_btnDischargeX)
        if _dictMagicId then
            btn_intensify:setTitleText("关闭")
        end
    else
        btn_change:setVisible(true)
        btn_discharge:setVisible(true)
        btn_intensify:setPositionX(_btnIntensifyX)
    end
    if _instMagicId then
        local ui_titleText = ccui.Helper:seekNodeByName(UIGongfaInfo.Widget, "text_gongfa_up")
        local ui_propPanel = ccui.Helper:seekNodeByName(UIGongfaInfo.Widget, "image_basecolour")
        local ui_magicIcon = ccui.Helper:seekNodeByName(ui_propPanel, "image_gongfa")
        local ui_magicQualityBg = ccui.Helper:seekNodeByName(ui_propPanel, "image_base_name")
        local ui_magicName = ui_magicQualityBg:getChildByName("text_name")
        local ui_magicLv = ui_magicQualityBg:getChildByName("text_lv")
        local ui_magicQualityName = ui_magicQualityBg:getChildByName("text_type")
        local ui_magicQuality = ccui.Helper:seekNodeByName(ui_propPanel, "text_number_quality")
        local ui_propBg = ccui.Helper:seekNodeByName(ui_propPanel, "image_base_property")

        local dictMagicId, magicType, magicQualityId, magicLevleId
        if _dictMagicId then
            dictMagicId = _dictMagicId
            local dictMagicData = DictMagic[tostring(dictMagicId)]
            magicType = dictMagicData.type
            magicQualityId = dictMagicData.magicQualityId
            magicLevleId = dictMagicData.magicLevelId
        else
            local instMagicData = nil
            if _isPvp then
                instMagicData = pvp.InstPlayerMagic[tostring(_instMagicId)]
                setBottomBtnVisible(false)
            else
                instMagicData = net.InstPlayerMagic[tostring(_instMagicId)]
                setBottomBtnVisible(true)
            end
            dictMagicId = instMagicData.int["3"]
            magicType = instMagicData.int["4"]
            magicQualityId = instMagicData.int["5"]
            magicLevleId = instMagicData.int["6"]
        end
        local dictMagicData = DictMagic[tostring(dictMagicId)]
        local magicLv = DictMagicLevel[tostring(magicLevleId)].level

        ui_titleText:setString((magicType == dp.MagicType.gongfa and "功法" or "法宝") .. "属性")
        ui_magicIcon:loadTexture("image/" .. DictUI[tostring(dictMagicData.bigUiId)].fileName)
        ui_magicQualityBg:loadTexture(utils.getQualityImage(dp.Quality.gongFa, magicQualityId, dp.QualityImageType.small, true))
        ui_magicName:setString(dictMagicData.name)
        ui_magicLv:setString("LV " .. magicLv)
        ui_magicQualityName:setString(DictMagicQuality[tostring(magicQualityId)].name ..(magicType == dp.MagicType.gongfa and "功法" or "法宝"))
        ui_magicQuality:setString(tostring(dictMagicData.grade))

        for i = 1, 6 do
            local ui_propText = ui_propBg:getChildByName("text_prop" .. i)
            local _tValues = utils.stringSplit(dictMagicData["value" .. i], "_")
            local textColor = cc.c4b(255, 255, 255, 255)
            if string.len(dictMagicData["value" .. i]) > 0 and _tValues and #_tValues > 0 then
                if i <= 3 then
                    ui_propText:setString(DictFightProp[_tValues[2]].name .. " +" .. formula.getMagicValue1(magicLv, tonumber(_tValues[3]), tonumber(_tValues[4]))
                    ..(tonumber(_tValues[1]) == 1 and "%" or ""))
                else
                    local _textLv = ""
                    if i == 4 then
                        if magicLv >= 10 then
                            _textLv = "（已解锁）"
                            textColor = cc.c4b(255, 255, 0, 255)
                        else
                            _textLv = "（10级解锁）"
                            textColor = cc.c4b(255, 255, 255, 255)
                        end
                    elseif i == 5 then
                         if magicLv >= 20 then
                            _textLv = "（已解锁）"
                            textColor = cc.c4b(255, 255, 0, 255)
                        else
                            _textLv = "（20级解锁）"
                            textColor = cc.c4b(255, 255, 255, 255)
                        end
                    elseif i == 6 then
                         if magicLv >= 40 then
                            _textLv = "（已解锁）"
                            textColor = cc.c4b(255, 255, 0, 255)
                        else
                            _textLv = "（40级解锁）"
                            textColor = cc.c4b(255, 255, 255, 255)
                        end
                    end
                    ui_propText:setTextColor(textColor)
                    ui_propText:setString(DictFightProp[_tValues[1]].name .. " +" .. _tValues[2] .. "%" .. _textLv)
                end
            else
                ui_propText:setString("")
            end
        end

        local magicCardLuck = { }
        local innerHieght, space = 0, 10
        for key, obj in pairs(DictCardLuck) do
            local lucks = utils.stringSplit(obj.lucks, "_")
            local tableTypeId, tableFieldId = tonumber(lucks[1]), tonumber(lucks[2])
            if tableTypeId == StaticTableType.DictMagic and tableFieldId == dictMagicId then
                magicCardLuck[#magicCardLuck + 1] = obj
            end
        end
        utils.quickSort(magicCardLuck, function(obj1, obj2)
            if DictCard[tostring(obj1.cardId)].qualityId < DictCard[tostring(obj2.cardId)].qualityId then
                return true
            end
        end )
        for key, obj in pairs(magicCardLuck) do
            local scrollViewItem = ui_svItem:clone()
            setScrollView(scrollViewItem, obj)
            ui_scrollView:addChild(scrollViewItem)
            innerHieght = innerHieght + scrollViewItem:getContentSize().height + space
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
                childs[i]:setPosition(cc.p(ui_scrollView:getContentSize().width / 2, ui_scrollView:getInnerContainerSize().height - childs[i]:getContentSize().height / 2 - space))
            else
                childs[i]:setPosition(cc.p(ui_scrollView:getContentSize().width / 2, prevChild:getBottomBoundary() - childs[i]:getContentSize().height / 2 - space))
            end
            prevChild = childs[i]
        end
    end
end

function UIGongfaInfo.setInstMagicId(instMagicId, isView, isPvp)
    _instMagicId = instMagicId
    _isView = isView
    _isPvp = isPvp
end

function UIGongfaInfo.setDictMagicId(dictMagicId)
    _dictMagicId = dictMagicId
    _instMagicId = 0
    _isView = true
end

function UIGongfaInfo.free()
    _instMagicId = nil
    _dictMagicId = nil
    _isView = nil
    if ui_svItem and ui_svItem:getReferenceCount() >= 1 then
        ui_svItem:release()
        ui_svItem = nil
    end
    ui_scrollView:removeAllChildren()
end