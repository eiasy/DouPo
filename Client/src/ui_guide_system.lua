UIGuideSystem = { }

local function JumpGuide()
    local function jump(flag)
        if flag == "sy" then
            if UIHomePage.Widget ~= UIManager.uiLayer:getChildByTag(1002) then
                UIManager.showScreen("ui_notice", "ui_team_info", "ui_homepage", "ui_menu")
            end
        elseif flag == "bagcard" then
            UIBagCard.setFlag(2)
            UIManager.showScreen("ui_notice", "ui_team_info", "ui_bag_card", "ui_menu")
        end
        AudioEngine.playMusic("sound/bg_music.mp3", true)
    end
    if UIGuidePeople.guideStep then
        if UIGuidePeople.guideStep == guideInfo["4B1"].step then
            jump("sy")
            UIGuidePeople.addGuideUI(UIMenu, ccui.Helper:seekNodeByName(UIMenu.Widget, "btn_troops"), guideInfo["4B1"].step)
        elseif UIGuidePeople.guideStep == guideInfo["5B1"].step then
            jump("sy")
            UIGuidePeople.addGuideUI(UIMenu, ccui.Helper:seekNodeByName(UIMenu.Widget, "btn_shop"), guideInfo["5B1"].step)
        elseif UIGuidePeople.guideStep == guideInfo["7B5"].step then
            jump("sy")
            UIGuidePeople.addGuideUI(UIHomePage, ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_skill"), guideInfo["7B6"].step)
        elseif UIGuidePeople.guideStep == guideInfo["7B1"].step then
            jump("sy")
            UIGuidePeople.addGuideUI(UIHomePage, ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_fuli"), guideInfo["7B1"].step)
        elseif UIGuidePeople.guideStep == guideInfo["8B1"].step then
            UIGuidePeople.addGuideUI(UIFightTask, ccui.Helper:seekNodeByName(UIFightTask.Widget, "btn_back"), guideInfo["8B1"].step)
        elseif UIGuidePeople.guideStep == guideInfo["18B1"].step then
            jump("sy")
            UIGuidePeople.addGuideUI(UIMenu, ccui.Helper:seekNodeByName(UIMenu.Widget, "btn_troops"), guideInfo["18B1"].step)
        elseif UIGuidePeople.guideStep == guideInfo["20B1"].step then
            jump("sy")
            UIGuidePeople.addGuideUI(UIMenu, ccui.Helper:seekNodeByName(UIMenu.Widget, "btn_dogfight"), guideInfo["20B1"].step)
        elseif UIGuidePeople.guideStep == guideInfo["25B1"].step then
            jump("sy")
            UIGuidePeople.addGuideUI(UIHomePage, ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_fuli"), guideInfo["25B1"].step)
        elseif UIGuidePeople.guideStep == guideInfo["25B4"].step then
            UIGuidePeople.addGuideUI(UIHomePage, ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_task"), guideInfo["25B5"].step)
        elseif UIGuidePeople.guideStep == guideInfo["45B1"].step then
            jump("sy")
            UIGuidePeople.addGuideUI(UIHomePage, ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_soul"), guideInfo["45B1"].step)
        end
    elseif UIGuidePeople.levelStep then
        if UIGuidePeople.levelStep == guideInfo["7_1"].step then
            jump("bagcard")
        elseif UIGuidePeople.levelStep == guideInfo["8_1"].step then
            jump("sy")
            UIGuidePeople.addGuideUI(UIMenu, ccui.Helper:seekNodeByName(UIMenu.Widget, "btn_troops"), guideInfo["8_1"].step)
        elseif UIGuidePeople.levelStep == guideInfo["10_1"].step then
            jump("sy")
            UIGuidePeople.addGuideUI(UIHomePage, ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_fuli"), guideInfo["10_1"].step)
        elseif UIGuidePeople.levelStep == guideInfo["14_1"].step then
            UIFight.setFlag(1)
            UIManager.showScreen("ui_notice", "ui_team_info", "ui_fight", "ui_menu")
        elseif UIGuidePeople.levelStep == guideInfo["11_1"].step or UIGuidePeople.levelStep == guideInfo["28_1"].step
         or UIGuidePeople.levelStep == guideInfo["32_1"].step or UIGuidePeople.levelStep == guideInfo["22_1"].step then
            jump("sy")
            UIGuidePeople.addGuideUI(UIMenu, ccui.Helper:seekNodeByName(UIMenu.Widget, "btn_dogfight"), UIGuidePeople.levelStep)
        elseif UIGuidePeople.levelStep == guideInfo["12_1"].step then
            jump("sy")
            UIGuidePeople.addGuideUI(UIHomePage, ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_resolve"), guideInfo["12_1"].step)
        elseif UIGuidePeople.levelStep == guideInfo["18_1"].step then
            jump("sy")
            UIGuidePeople.addGuideUI(UIMenu, ccui.Helper:seekNodeByName(UIMenu.Widget, "btn_troops"), guideInfo["18_1"].step)
        elseif UIGuidePeople.levelStep == guideInfo["20_1"].step then
            jump("sy")
            UIGuidePeople.addGuideUI(UIMenu, ccui.Helper:seekNodeByName(UIMenu.Widget, "btn_troops"), guideInfo["20_1"].step)
        elseif UIGuidePeople.levelStep == guideInfo["26_1"].step then
            jump("sy")
            UIGuidePeople.addGuideUI(UIMenu, ccui.Helper:seekNodeByName(UIMenu.Widget, "btn_troops"), guideInfo["26_1"].step)
        elseif UIGuidePeople.levelStep == guideInfo["28_1"].step or UIGuidePeople.levelStep == guideInfo["16_1"].step then
            if next(net.InstPlayerEquip) then
                jump("sy")
                UIGuidePeople.addGuideUI(UIHomePage, ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_equipment"), UIGuidePeople.levelStep)
            end
        end
    end
end

function UIGuideSystem.init(...)
    local btn_sure = ccui.Helper:seekNodeByName(UIGuideSystem.Widget, "btn_sure")
    local btn_close = ccui.Helper:seekNodeByName(UIGuideSystem.Widget, "btn_close")
    btn_sure:setPressedActionEnabled(true)
    btn_close:setPressedActionEnabled(true)
    local function btnTouchEvent(sender, eventType)
        if eventType == ccui.TouchEventType.ended then
            AudioEngine.playEffect("sound/button.mp3")
            if sender == btn_sure then
                UIManager.popScene()
                JumpGuide()
            elseif sender == btn_close then
                UIManager.popScene()
            end
        end
    end
    btn_sure:addTouchEventListener(btnTouchEvent)
    btn_close:addTouchEventListener(btnTouchEvent)
end

function UIGuideSystem.setup(...)
    UIGuideSystem.Widget:setEnabled(true)
    UIFightTask.setBasemapPercent(nil)
    local image_system = ccui.Helper:seekNodeByName(UIGuideSystem.Widget, "image_system")
    local text_system_open = ccui.Helper:seekNodeByName(UIGuideSystem.Widget, "text_system_open")
    local btn_close = ccui.Helper:seekNodeByName(UIGuideSystem.Widget, "btn_close")
    if UIGuidePeople.guideStep then
        cclog("关卡引导" .. UIGuidePeople.guideStep)
        btn_close:setVisible(false)
        if UIGuidePeople.guideStep == guideInfo["5B1"].step then
            image_system:loadTexture("ui/db_shop_l.png")
            text_system_open:setString("商城")
        elseif UIGuidePeople.guideStep == guideInfo["4B1"].step then
            image_system:loadTexture("ui/jinjie.png")
            text_system_open:setString("卡牌进阶")
        elseif UIGuidePeople.guideStep == guideInfo["7B5"].step then
            image_system:loadTexture("ui/home_douji.png")
            text_system_open:setString("装备斗技")
        elseif UIGuidePeople.guideStep == guideInfo["7B1"].step then
            image_system:loadTexture("ui/home_dengjilibao.png")
            text_system_open:setString("等级礼包")
        elseif UIGuidePeople.guideStep == guideInfo["8B1"].step then
            image_system:loadTexture("ui/db_02.png")
            text_system_open:setString("青山镇")
        elseif UIGuidePeople.guideStep == guideInfo["18B1"].step then
            image_system:loadTexture("ui/home_zhuangbei.png")
            text_system_open:setString("装备")
        elseif UIGuidePeople.guideStep == guideInfo["20B1"].step then
            image_system:loadTexture("ui/db_duel.png")
            text_system_open:setString("远古遗迹")
        elseif UIGuidePeople.guideStep == guideInfo["25B1"].step then
            image_system:loadTexture("ui/home_qiandaolibao.png")
            text_system_open:setString("签到礼包")
        elseif UIGuidePeople.guideStep == guideInfo["25B4"].step then
            image_system:loadTexture("ui/home_meirirenwu.png")
            text_system_open:setString("每日任务")
         elseif UIGuidePeople.guideStep == guideInfo["45B1"].step then
            image_system:loadTexture("ui/home_soul.png")
            text_system_open:setString("斗魂")
        end
    elseif UIGuidePeople.levelStep then
        cclog("等级引导" .. UIGuidePeople.levelStep)
        btn_close:setVisible(false)
        if UIGuidePeople.levelStep == guideInfo["7_1"].step then
            image_system:loadTexture("ui/db_lineUp.png")
            text_system_open:setString("替补")
        elseif UIGuidePeople.levelStep == guideInfo["8_1"].step then
            image_system:loadTexture("ui/jingjie.png")
            text_system_open:setString("境界")
        elseif UIGuidePeople.levelStep == guideInfo["10_1"].step then
            image_system:loadTexture("ui/db_lineUp.png")
            text_system_open:setString("阵容+1")
        elseif UIGuidePeople.levelStep == guideInfo["14_1"].step then
            image_system:loadTexture("ui/db_02.png")
            text_system_open:setString("精英副本")
        elseif UIGuidePeople.levelStep == guideInfo["11_1"].step then
            image_system:loadTexture("ui/db_duel.png")
            text_system_open:setString("竞技场")
        elseif UIGuidePeople.levelStep == guideInfo["12_1"].step then
            image_system:loadTexture("ui/db_duel.png")
            text_system_open:setString("分解/神秘商店")
        elseif UIGuidePeople.levelStep == guideInfo["18_1"].step then
            image_system:loadTexture("ui/minggong.png")
            text_system_open:setString("命宫")
        elseif UIGuidePeople.levelStep == guideInfo["20_1"].step then
            image_system:loadTexture("ui/xiulian.png")
            text_system_open:setString("修炼")
        elseif UIGuidePeople.levelStep == guideInfo["26_1"].step then
            image_system:loadTexture("ui/xhb.png")
            text_system_open:setString("缘分")
            -- elseif UIGuidePeople.levelStep == guideInfo["28_1"].step then
            -- 	image_system:loadTexture("ui/home_zhuangbei.png")
            -- 	text_system_open:setString("装备洗练")
        elseif UIGuidePeople.levelStep == guideInfo["16_1"].step then
            image_system:loadTexture("ui/home_zhuangbei.png")
            text_system_open:setString("装备镶嵌")
        elseif UIGuidePeople.levelStep == guideInfo["28_1"].step then
            image_system:loadTexture("ui/db_duel.png")
            text_system_open:setString("天焚炼气塔")
        elseif UIGuidePeople.levelStep == guideInfo["32_1"].step then
            image_system:loadTexture("ui/db_duel.png")
            text_system_open:setString("丹塔")
        elseif UIGuidePeople.levelStep == guideInfo["22_1"].step then
            image_system:loadTexture("ui/db_duel.png")
            text_system_open:setString("资源矿")
        end
    end
end

