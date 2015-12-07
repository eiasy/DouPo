require "ui_talk_fly"
UIHomePage = {
	btn_time_RedPoint = true --btn_time的红点
}
local button_Pos = {}
local button_Pos_Fuli = {}
local temp_Pos = {}
UIHomePage.ScheduleId  = nil
UIHomePage.countDownTime = nil
UIHomePage.flag = false  --这个标志位为了游戏注销后复位用
UIHomePage.zOrder = { BUTTON = 4,CLOUD = 3, PARTICLE = 2,OTHER = 1}
UIHomePage.btnTimeHintFlag = false
local moreTag = 0       --标记更多按钮的状态，0为收起，1为展开
local isAction = false  --标记更多按钮是否正在动画中
local isShowFuli = true --标记是否显示福利弹框，避免福利弹框内的按钮都隐藏时显示空弹框
UIHomePage.accessCount = 0
UIHomePage.limitHeroFlag = true
UIHomePage.luckyFlag = false
UIHomePage.qmflFlag = false
UIHomePage.costAllFlag = false
---------------------zy 月卡显示红点提示-------------------------------
function UIHomePage.getMonthCardData(type)
    local instActivityObj = nil
    if net.InstActivity then
        for key, obj in pairs(net.InstActivity) do
            local activity = net.SysActivity[tostring(obj.int["3"])]
            if activity.string["9"] == "monthCard" and obj.int["6"] == type then
                instActivityObj = obj
                return instActivityObj
            end
        end
    end
    return nil
end
function UIHomePage.isShowMonthCardHint()
    local isShowHint = false
    local types = { UIActivityCard.SILVER_MONTH_CARD, UIActivityCard.GOLD_MONTH_CARD }
    for i = 1, #types do  
        local instActivityObj = UIHomePage.getMonthCardData(types[ i ])
        if instActivityObj then
            if instActivityObj.string["4"] == "" then             
                isShowHint = false
                return
            elseif instActivityObj.string["4"] ~= "" and UIActivityPanel.isEndActivityByEndTime(instActivityObj.string["4"]) then
                isShowHint = false
                return
            end

            local recentGetTime = instActivityObj.string["7"]
            local endDay = utils.changeTimeFormat(instActivityObj.string["4"])[3]
            local getDay = nil
            if recentGetTime ~= nil and recentGetTime ~= "" then
                getDay = utils.changeTimeFormat(recentGetTime)[3]
            end
            local remainDay = 0
            local _curTime = utils.getCurrentTime()
            local _date = os.date("*t", _curTime)
            local function isleapyear(y)
                return(y % 4 == 0 and y % 100 or y % 400 == 0)
            end
            local md = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }
            if isleapyear(_date.year) then
                md[2] = 29
            end
            if endDay - dp.loginDay > 0 then
                remainDay = endDay - dp.loginDay
            elseif endDay - dp.loginDay == 0 then
                remainDay = 30
            else
                remainDay = md[_date.month] - dp.loginDay + endDay
            end

            if getDay and tonumber(getDay) == tonumber(dp.loginDay) then
                isShowHint = false
            else
                isShowHint = true
            end
        else
            isShowHint = false
        end
	    if isShowHint then
            break
	    end
    end
    return isShowHint
end
---------------------------------------
function UIHomePage.stopSchedule()
	if  UIHomePage.ScheduleId then 
       cc.Director:getInstance():getScheduler():unscheduleScriptEntry(UIHomePage.ScheduleId)
       UIHomePage.ScheduleId= nil
       UIHomePage.countDownTime = nil;
       local btn_award = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_award")
       btn_award:getChildByName("image_hint"):setVisible(true)
       btn_award:getChildByName("image_base_time"):setVisible(false)
       if UIAwardOnLine.Widget ~= nil then 
  	  	local btn_prize = ccui.Helper:seekNodeByName(UIAwardOnLine.Widget, "btn_prize")
  	  	utils.GrayWidget(btn_prize,false)
  	  	btn_prize:setEnabled(true)
  	  end
    end
end
function UIHomePage.updateTime()
	  if UIHomePage.countDownTime ~= 0 then 
	      UIHomePage.countDownTime  = UIHomePage.countDownTime -1
	      local hour= math.floor(UIHomePage.countDownTime/3600)
	      local min= math.floor(UIHomePage.countDownTime%3600/60)
	      local sec= UIHomePage.countDownTime%60
	      local btn_award = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_award")
	      local base_time = btn_award:getChildByName("image_base_time")
	      btn_award:getChildByName("image_hint"):setVisible(false)
          base_time:setVisible(true)
	      base_time:getChildByName("text_time"):setString(string.format("%02d:%02d:%02d",hour,min,sec))
	  	  if UIAwardOnLine.Widget ~= nil then 
	  	  	local text_countdown = ccui.Helper:seekNodeByName(UIAwardOnLine.Widget, "text_countdown")
	  	  	text_countdown:setString(string.format("距离下一次领取奖励还有：%02d:%02d:%02d",hour,min,sec))
	  	  	
	  	  end
	  else
	     UIHomePage.stopSchedule()
	  end
end

function UIHomePage.hideMore(  )
    local btn_more = ccui.Helper:seekNodeByName(UIHomePage.Widget, "image_more")--更多按钮
	local panel_main = ccui.Helper:seekNodeByName(UIHomePage.Widget, "panel")	--弹框
    if btn_more and panel_main then
        if moreTag == 1 then
		    local action = cc.RotateBy:create(0.3,-180)
		    local btn_more_arrow = btn_more:getChildByName("image_more_arrow")
		    btn_more_arrow:runAction(action)
		    panel_main:setVisible(false)
            panel_main:getChildByName("image_more_info"):setVisible(false)
		    moreTag = 0
        elseif panel_main:getChildByName("image_gift"):isVisible() then
            panel_main:setVisible(false)
            panel_main:getChildByName("image_gift"):setVisible(false)
	    end
    end
end

function UIHomePage.init()
    UITalkFly.create()
	---------------TEST 快速升级---------------
	if not dp.RELEASE then
		local quickUpgrade = ccui.Text:create()
	  quickUpgrade:setString("· 快速升级 ·")
	  quickUpgrade:setFontName(dp.FONT)
	  quickUpgrade:setFontSize(30)
	  quickUpgrade:setTextColor(cc.c3b(255, 255, 0))
	  quickUpgrade:setPosition(cc.p(UIManager.screenSize.width / 2, UIManager.screenSize.height / 2))
	  UIHomePage.Widget:addChild(quickUpgrade, 10000)
	  quickUpgrade:setTouchEnabled(true)
	  quickUpgrade:addTouchEventListener(function(sender, eventType)
	  	if eventType == ccui.TouchEventType.ended then
				UIManager.showLoading()
				local function flush()
					UIManager.flushWidget(UITeamInfo)
					UIGuidePeople.levelGuideTrigger()
				end
				netSendPackage({header = StaticMsgRule.quickUpgrade, msgdata = {}}, flush)
	  	end
	  end)
	end
  ---------------TEST 快速升级---------------

	local btn_recharge = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_recharge") 		--充值按钮
	local btn_task = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_task") 				--每日任务按钮
	local btn_sign = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_sign") 				--签到礼包按钮
	local btn_activity = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_activity") 		--活动按钮
	local btn_lv = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_lv") 					--等级礼包按钮
	local btn_gift = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_gift") 				--开服礼包按钮
	local btn_award = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_award") 			--在线奖励按钮
	local btn_prize = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_prize") 			--领奖中心按钮
	local btn_equipment = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_equipment") 	--装备背包按钮
	local btn_card = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_card") 				--卡牌按钮
	local btn_gongfa = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_gongfa") 			--功法按钮
    local btn_soul = ccui.Helper:seekNodeByName(UIHomePage.Widget , "btn_soul")             --斗魂
    local btn_wing = ccui.Helper:seekNodeByName(UIHomePage.Widget , "btn_wing" )            --神羽
    btn_wing:getChildByName("image_hint"):setVisible(false)
	local btn_set = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_set") 				--设置按钮
	local btn_email = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_email") 			--邮件
	local btn_ranking = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_ranking")		--排行榜
	local btn_trial = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_trial") 			--试炼日
	local btn_talk = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_talk") 				--聊天
	local btn_resolve = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_resolve")		--分解
	local btn_more = ccui.Helper:seekNodeByName(UIHomePage.Widget, "image_more")			--更多按钮
	local btn_time = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_time")              --限时特惠
	local btn_change = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_change")          --无敌兑换
    local btn_fuli = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_fuli")              --福利
    local btn_welfare_recharge = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_welfare_recharge")  --充值福利

	local panel_main = ccui.Helper:seekNodeByName(UIHomePage.Widget, "panel")				--首页弹框
	local panel_resolve = ccui.Helper:seekNodeByName(UIHomePage.Widget,"panel_resolve") 	--异火
	local panel_relic = ccui.Helper:seekNodeByName(UIHomePage.Widget,"panel_relic") 		--远古遗迹
	local panel_tower = ccui.Helper:seekNodeByName(UIHomePage.Widget,"panel_tower") 		--天焚炼气塔
	local panel_jjc = ccui.Helper:seekNodeByName(UIHomePage.Widget,"panel_jjc") 			--竞技场
	local panel_alliance = ccui.Helper:seekNodeByName(UIHomePage.Widget,"panel_alliance") 	--联盟

	btn_recharge:setPressedActionEnabled(true)
	btn_task:setPressedActionEnabled(true)
	btn_sign:setPressedActionEnabled(true)
	btn_activity:setPressedActionEnabled(true)
	btn_lv:setPressedActionEnabled(true)
	btn_gift:setPressedActionEnabled(true)
	btn_award:setPressedActionEnabled(true)
	btn_prize:setPressedActionEnabled(true)
	btn_email:setPressedActionEnabled(true)
	btn_ranking:setPressedActionEnabled(true)
	btn_trial:setPressedActionEnabled(true)
	btn_welfare_recharge:setPressedActionEnabled(true)
	btn_equipment:setPressedActionEnabled(true)
	btn_card:setPressedActionEnabled(true)
	btn_gongfa:setPressedActionEnabled(true)
    btn_soul:setPressedActionEnabled( true )
    btn_wing:setPressedActionEnabled( true )
	btn_set:setPressedActionEnabled(true)
	btn_talk:setPressedActionEnabled(true)
	btn_resolve:setPressedActionEnabled(true)
	btn_more:setTouchEnabled(true)
    btn_time:setPressedActionEnabled(true)
    btn_change:setPressedActionEnabled(true)
    btn_fuli:setPressedActionEnabled(true)

	btn_recharge:setLocalZOrder(UIHomePage.zOrder.BUTTON)
	btn_task:setLocalZOrder(UIHomePage.zOrder.BUTTON)
	btn_sign:setLocalZOrder(UIHomePage.zOrder.BUTTON)
	btn_activity:setLocalZOrder(UIHomePage.zOrder.BUTTON)
	btn_lv:setLocalZOrder(UIHomePage.zOrder.BUTTON)
	btn_gift:setLocalZOrder(UIHomePage.zOrder.BUTTON)
	btn_award:setLocalZOrder(UIHomePage.zOrder.BUTTON)
	btn_prize:setLocalZOrder(UIHomePage.zOrder.BUTTON)
	btn_email:setLocalZOrder(UIHomePage.zOrder.BUTTON)
	btn_ranking:setLocalZOrder(UIHomePage.zOrder.BUTTON)
	btn_trial:setLocalZOrder(UIHomePage.zOrder.BUTTON)
	btn_welfare_recharge:setLocalZOrder(UIHomePage.zOrder.BUTTON)
	btn_equipment:setLocalZOrder(UIHomePage.zOrder.BUTTON)
	btn_card:setLocalZOrder(UIHomePage.zOrder.BUTTON)
	btn_gongfa:setLocalZOrder(UIHomePage.zOrder.BUTTON)
    btn_soul:setLocalZOrder( UIHomePage.zOrder.BUTTON )
    btn_wing:setLocalZOrder( UIHomePage.zOrder.BUTTON )
	btn_set:setLocalZOrder(UIHomePage.zOrder.BUTTON)
	btn_talk:setLocalZOrder(UIHomePage.zOrder.BUTTON)
	btn_resolve:setLocalZOrder(UIHomePage.zOrder.BUTTON)
	btn_more:setLocalZOrder(UIHomePage.zOrder.BUTTON)
    btn_time:setLocalZOrder(UIHomePage.zOrder.BUTTON)
    btn_change:setLocalZOrder(UIHomePage.zOrder.BUTTON)
    btn_fuli:setLocalZOrder(UIHomePage.zOrder.BUTTON)

	panel_resolve:setTouchEnabled(true)
	panel_relic:setTouchEnabled(true)
	panel_tower:setTouchEnabled(true)
	panel_jjc:setTouchEnabled(true)
	panel_alliance:setTouchEnabled(true)
	panel_main:setTouchEnabled(true)

	panel_resolve:setLocalZOrder(UIHomePage.zOrder.OTHER)
	panel_relic:setLocalZOrder(UIHomePage.zOrder.OTHER)
	panel_tower:setLocalZOrder(UIHomePage.zOrder.OTHER)
	panel_jjc:setLocalZOrder(UIHomePage.zOrder.OTHER)
	panel_alliance:setLocalZOrder(UIHomePage.zOrder.OTHER)
	panel_main:setLocalZOrder(UIHomePage.zOrder.BUTTON)

	--创建粒子
    local particleTrial = cc.ParticleSystemQuad:create("particle/shouye_action_effect_slstar.plist")
    particleTrial:setPosition(cc.p(btn_trial:getContentSize().width / 2, btn_trial:getContentSize().height * 0.4))
    particleTrial:setScale(0.8)
    btn_trial:addChild(particleTrial)

	local particleResolve = cc.ParticleSystemQuad:create("particle/shouye_action_effect_fire_1.plist")
	particleResolve:setPosition(cc.p(75,65))
	particleResolve:setScale(0.6)
	panel_resolve:addChild(particleResolve,UIHomePage.zOrder.PARTICLE)

	local particleTower = cc.ParticleSystemQuad:create("particle/shouye_action_effect_star_1.plist")
	particleTower:setPosition(cc.p(130,95))
	particleTower:setScale(0.5)
	panel_tower:addChild(particleTower,UIHomePage.zOrder.PARTICLE)

	local particleAlliance = cc.ParticleSystemQuad:create("particle/shouye_action_effect_taohua_1.plist")
	particleAlliance:setPosition(cc.p(195,210))
	particleAlliance:setScale(0.9)
	panel_alliance:addChild(particleAlliance,UIHomePage.zOrder.PARTICLE)

	local particleAllianceTwo = cc.ParticleSystemQuad:create("particle/shouye_action_effect_taohua_1.plist")
	particleAllianceTwo:setPosition(cc.p(20,130))
	panel_alliance:addChild(particleAllianceTwo,UIHomePage.zOrder.PARTICLE)

    local rechargePosX,rechargePosY = btn_recharge:getPosition() 
    local rechargePox = cc.p(rechargePosX,rechargePosY)

    local particleRecharge = cc.ParticleSystemQuad:create("particle/shouye_action_effect_star_2.plist")
	particleRecharge:setPosition(rechargePox)
	UIHomePage.Widget:addChild(particleRecharge,UIHomePage.zOrder.BUTTON+1)

    local rechargeBg = cc.Sprite:create("image/shouye_recharge_bg.png")
    rechargeBg:setPosition(rechargePox)
    UIHomePage.Widget:addChild(rechargeBg,UIHomePage.zOrder.BUTTON-1)
    rechargeBg:runAction(cc.RepeatForever:create(cc.RotateBy:create(2.6,360)))
	--创建天焚炼气塔的动画
	local animPath = "ani/ui_anim_shou/"
	ccs.ArmatureDataManager:getInstance():removeArmatureFileInfo(animPath .. "ui_anim_shou.ExportJson")
	ccs.ArmatureDataManager:getInstance():addArmatureFileInfo(animPath .. "ui_anim_shou.ExportJson")
	local animation = ccs.Armature:create("ui_anim_shou")
	animation:getAnimation():play("shou01")
	local aniX,aniY=panel_tower:getPosition()
	animation:setPosition(cc.p(aniX+123,aniY+116))
	UIHomePage.Widget:addChild(animation, UIHomePage.zOrder.PARTICLE)

    local function createPaoPao( )--zy
        local node = cc.Node:create()
        local image = ccui.ImageView:create("ui/ui_zhaoren.png")
        node:addChild(image)
        node:setName("paopao")
        node:setPosition(cc.p(250, 190))
        panel_alliance:addChild(node,UIHomePage.zOrder.BUTTON)
    end

	--创建首页云朵
	local function createCloud(  )
		local cloudW = 500
		local cloudH = 328
		local maxY = UIManager.screenSize.height - cloudH/2.0
		local minY = cloudH/2.0
		local function getParam( )
			local y = math.random(minY,maxY)
			local scale = (UIManager.screenSize.height-y)/UIManager.screenSize.height*1.5
			if scale < 0.5 then
				scale = 0.5
			elseif scale > 1.5 then
				scale = 1.5
			end
			local actiontime = (UIManager.screenSize.height-y)/UIManager.screenSize.height*50
			if actiontime <20 then
				actiontime = 20
			elseif actiontime > 40 then
				actiontime = 40
			end
			return y,scale,actiontime
		end
		local function callBack(actionBody)
			actionBody:stopAllActions()
			local callBackY,callBackScale,callBackTime = getParam()
			actionBody:setPosition(cc.p(-cloudW/2,callBackY))
			actionBody:setScale(callBackScale)
			actionBody:setOpacity(255)
			local action = cc.Sequence:create(cc.DelayTime:create(math.random(1,3)),cc.Spawn:create(cc.MoveBy:create(callBackTime,cc.p(UIManager.screenSize.width, 0)),
			cc.Sequence:create(cc.DelayTime:create(callBackTime/3*2.0),cc.FadeTo:create(callBackTime/3.0,0),cc.CallFunc:create(callBack))),cc.CallFunc:create(callBack))
			actionBody:runAction(action)
		end
		local function createAction(delayTime,time)
			local action = cc.Sequence:create(cc.DelayTime:create(delayTime),cc.Spawn:create(cc.MoveBy:create(time,cc.p(UIManager.screenSize.width, 0)),
			cc.Sequence:create(cc.DelayTime:create(time/3*2.0),cc.FadeTo:create(time/3.0,0),cc.CallFunc:create(callBack))),cc.CallFunc:create(callBack))
			return action
		end
		local cloudTable = {cloud1=nil,cloud2=nil,cloud3=nil}
		cloudTable.cloud1 = cc.Sprite:create("image/ui_home_cloud.png")
		cloudTable.cloud2 = cc.Sprite:create("image/ui_home_cloud.png")
		cloudTable.cloud3 = cc.Sprite:create("image/ui_home_cloud.png")
		local cloudY1,scale1,time1 = getParam()
		cloudTable.cloud1:setPosition(cc.p(-cloudW/2+100,cloudY1))
		cloudTable.cloud1:setScale(scale1)
		cloudTable.cloud1:runAction(createAction(0,time1))
		UIHomePage.Widget:addChild(cloudTable.cloud1, UIHomePage.zOrder.CLOUD)
		local cloudY2,scale2,time2 = getParam()
		cloudTable.cloud2:setPosition(cc.p(-cloudW/2+50,cloudY2))
		cloudTable.cloud2:setScale(scale2)
		cloudTable.cloud2:runAction(createAction(1,time2))
		UIHomePage.Widget:addChild(cloudTable.cloud2, UIHomePage.zOrder.CLOUD)
		local cloudY3,scale3,time3 = getParam()
		cloudTable.cloud3:setPosition(cc.p(-cloudW/2+50,cloudY3))
		cloudTable.cloud3:setScale(scale3)
		cloudTable.cloud3:runAction(createAction(2,time3))
		UIHomePage.Widget:addChild(cloudTable.cloud3, UIHomePage.zOrder.CLOUD)
	end
	--创建云朵
	createCloud()	
    createPaoPao()
	local function btnTouchEvent(sender, eventType)
		if eventType == ccui.TouchEventType.ended then
			AudioEngine.playEffect("sound/button.mp3")
			if sender == btn_recharge then
				utils.checkGOLD(1)
			elseif sender == btn_more then
                if not isAction then
                    cclog("isAction=true   moreTag="..moreTag)
                    isAction = true
                    if moreTag == 0 then
					    local action = cc.Sequence:create(cc.RotateBy:create(0.3,180),cc.CallFunc:create(function (  )
						    panel_main:setVisible(true)
                            panel_main:getChildByName("image_more_info"):setVisible(true)
                            isAction = false
					    end))
					    local btn_more_arrow = btn_more:getChildByName("image_more_arrow")
					    btn_more_arrow:runAction(action)
					    moreTag = 1
				    elseif moreTag == 1 then
                        local action = cc.Sequence:create(cc.RotateBy:create(0.3,-180),cc.CallFunc:create(function (  )
                            isAction = false
					    end))
					    local btn_more_arrow = btn_more:getChildByName("image_more_arrow")
					    btn_more_arrow:runAction(action)
					    panel_main:setVisible(false)
                        panel_main:getChildByName("image_more_info"):setVisible(false)
					    moreTag = 0
				    end
                end
            elseif sender == btn_fuli then
                if isShowFuli then
                    panel_main:setVisible(true)
                    panel_main:getChildByName("image_gift"):setVisible(true)
                end
                if UIGuidePeople.guideStep == "7B1" then
                    UIGuidePeople.isGuide(btn_lv,UIHomePage)
                elseif UIGuidePeople.guideStep == "25B1" then
                    UIGuidePeople.isGuide(btn_sign,UIHomePage)
                elseif UIGuidePeople.levelStep == "10_1" then
                    UIGuidePeople.isGuide(btn_lv,UIHomePage)
                end
                
			elseif sender == btn_lv then 
				UIAwardGift.setOperateType(UIAwardGift.OperateType.lv)
			elseif sender == btn_gift then 
				UIAwardGift.setOperateType(UIAwardGift.OperateType.gift)
			elseif sender == btn_prize then 
				UIAwardGift.setOperateType(UIAwardGift.OperateType.prize)
			elseif  sender == btn_sign then 
				UIManager.pushScene("ui_award_sign")
			elseif sender == btn_award then
				UIManager.pushScene("ui_award_online")
            elseif sender == btn_time then
				UIManager.hideWidget("ui_team_info")
				UIManager.showWidget("ui_activity_time")
			elseif  sender == btn_task then 
				UIManager.pushScene("ui_task_day")
			-- elseif sender == btn_beruty then
			-- 	UIManager.hideWidget("ui_team_info")
			-- 	UIManager.showWidget("ui_beauty")
			elseif  sender == btn_set then
				UIManager.pushScene("ui_settings")
			elseif sender == btn_equipment then
				local openLv = DictFunctionOpen[tostring(StaticFunctionOpen.equipment)].level
				if net.InstPlayer.int["4"] < openLv then 
					UIManager.showToast("战队等级达到"..openLv.."级开启！")
					return 
				end
				UIManager.showWidget("ui_team_info", "ui_bag_equipment")
			elseif  sender == btn_card then
				UIManager.showWidget("ui_team_info", "ui_bag_card")
            elseif sender == btn_soul then
                local openLv = DictFunctionOpen[tostring(StaticFunctionOpen.fight)].level
                local lootOpen = false
					if net.InstPlayerBarrier then
				      for key,obj in pairs(net.InstPlayerBarrier) do
				          if obj.int["3"] == openLv then  
				          	lootOpen = true
				          	break;
				          end
				      end
				    end
				    if lootOpen then 
				    	UIManager.hideWidget("ui_team_info")
                        UIManager.showWidget("ui_soul_get")
					else
						UIManager.showToast("需要通关关卡"..DictBarrier[ tostring( openLv ) ].name)
						return  
					end
            elseif sender == btn_wing then
                if net.InstPlayer.int["4"] < DictFunctionOpen[tostring(StaticFunctionOpen.wing)].level then
                    UIManager.showToast("战队等级达到"..DictFunctionOpen[tostring(StaticFunctionOpen.wing)].level.."级开启！")
                else
                    UIManager.showWidget("ui_team_info", "ui_bag_wing")
                end
			elseif  sender == btn_gongfa then
				UIManager.showWidget("ui_team_info", "ui_bag_gongfa")               
			elseif sender == btn_activity then
				UIManager.showWidget("ui_activity_panel")
			elseif sender == btn_email then
				UIActivityPanel.scrollByName("mail","mail")
				UIManager.showWidget("ui_activity_panel")
                if btn_email:getChildByName("image_hint"):isVisible() then
                     btn_email:getChildByName("image_hint"):setVisible(false)
                end
			elseif sender == btn_ranking then
				UIActivityPanel.scrollByName("rank","rank")
				UIManager.showWidget("ui_activity_panel")
			elseif sender == btn_trial then
                UIActivityTrial.show()
            elseif sender == btn_welfare_recharge then
                UIActivityPanel.setRechargeActivity(UIActivityPanel.rechargeActivity)
                UIManager.showWidget("ui_activity_panel")
            elseif sender == btn_change then
				UIManager.hideWidget("ui_team_info")
				UIManager.showWidget("ui_activity_exchange")
			elseif sender == btn_talk then
				UIManager.pushScene("ui_talk")
			elseif sender == panel_jjc then
				local openLv = DictFunctionOpen[tostring(StaticFunctionOpen.area)].level
				if net.InstPlayer.int["4"] < openLv then
					UIManager.showToast("需要战队等级" .. openLv .. "级开启！")
					return
				end
				UIArena.comFromMain = true
				UIManager.hideWidget("ui_team_info")
				UIManager.showWidget("ui_arena")
				UIArena.isFromMain = true
			elseif sender == panel_relic then
				local lootOpen = false
					if net.InstPlayerBarrier then
				      for key,obj in pairs(net.InstPlayerBarrier) do
				          if obj.int["3"] == 20 then  --17关开启
				          	lootOpen = true
				          	break;
				          end
				      end
				    end
				    if lootOpen then 
				    	UIManager.hideWidget("ui_team_info")
				    	UILoot.isFromMain = true
						UILoot.show(1,1)
					else
						UIManager.showToast("需要通关关卡魔兽山脉边缘的“亡命追杀”")
						return  
					end
			elseif sender == btn_resolve then
				local openLv = DictFunctionOpen[tostring(StaticFunctionOpen.resolve)].level
				if net.InstPlayer.int["4"] < openLv then 
					UIManager.showToast("战队等级达到"..openLv.."级开启！")
					return 
				end
				UIManager.hideWidget("ui_team_info")
				UIManager.showWidget("ui_resolve")
			elseif sender == panel_resolve then
				UIFire.show()
			elseif sender == panel_tower then
                local openLv = DictFunctionOpen[tostring(StaticFunctionOpen.tower)].level
                if net.InstPlayer.int["4"] < openLv then 
                    UIManager.showToast("战队等级达到"..openLv.."级开启！")
				    return
				end
                UIManager.hideWidget("ui_team_info")
                UIManager.showWidget("ui_tower_test")
			elseif  sender == panel_alliance then
                local openLv = DictSysConfig[tostring(StaticSysConfig.unionPlayerLevel)].value
                if net.InstPlayer.int["4"] < openLv then 
					UIManager.showToast("战队等级达到"..openLv.."级开启！")
					return
				end
				if net.InstUnionMember and net.InstUnionMember.int["2"] ~= 0 then
					UIManager.hideWidget("ui_notice")
					UIManager.hideWidget("ui_team_info")
					UIManager.showWidget("ui_alliance")
				else
					UIManager.pushScene("ui_alliance_rank")
				end
			elseif sender == panel_main then
                if panel_main:getChildByName("image_more_info"):isVisible() then
                    if not isAction then
                        isAction = true
                        local action = cc.Sequence:create(cc.RotateBy:create(0.3,-180),cc.CallFunc:create(function (  )
                            isAction = false
					    end))
				        local btn_more_arrow = btn_more:getChildByName("image_more_arrow")
				        btn_more_arrow:runAction(action)
				        panel_main:setVisible(false)
                        panel_main:getChildByName("image_more_info"):setVisible(false)
				        moreTag = 0
                    end
                elseif panel_main:getChildByName("image_gift"):isVisible() then
                    panel_main:setVisible(false)
                    panel_main:getChildByName("image_gift"):setVisible(false)
                    local signFlag = btn_sign:isVisible() and btn_sign:getChildByName("image_hint"):isVisible()
                    local lvFlag = btn_lv:isVisible() and btn_lv:getChildByName("image_hint"):isVisible()
                    local giftFlag = btn_gift:isVisible() and btn_gift:getChildByName("image_hint"):isVisible()
                    local awardFlag = btn_award:isVisible() and btn_award:getChildByName("image_hint"):isVisible()
                    local changeFlag = btn_change:isVisible() and btn_change:getChildByName("image_hint"):isVisible()
                    if signFlag or lvFlag or giftFlag or awardFlag or changeFlag then
                        btn_fuli:getChildByName("image_hint"):setVisible(true)
                    else
                        btn_fuli:getChildByName("image_hint"):setVisible(false)
                    end
                end
			end
		end
	end
	
	btn_email:addTouchEventListener(btnTouchEvent)
	btn_trial:addTouchEventListener(btnTouchEvent)
    btn_welfare_recharge:addTouchEventListener(btnTouchEvent)
	btn_ranking:addTouchEventListener(btnTouchEvent)
	btn_recharge:addTouchEventListener(btnTouchEvent)
	btn_task:addTouchEventListener(btnTouchEvent)
	btn_sign:addTouchEventListener(btnTouchEvent)
	btn_activity:addTouchEventListener(btnTouchEvent)
	btn_lv:addTouchEventListener(btnTouchEvent)
	btn_gift:addTouchEventListener(btnTouchEvent)
	btn_award:addTouchEventListener(btnTouchEvent)
	btn_prize:addTouchEventListener(btnTouchEvent)
	btn_equipment:addTouchEventListener(btnTouchEvent)
	btn_card:addTouchEventListener(btnTouchEvent)
	btn_gongfa:addTouchEventListener(btnTouchEvent)
    btn_soul:addTouchEventListener( btnTouchEvent )
    btn_wing:addTouchEventListener( btnTouchEvent )
	btn_set:addTouchEventListener(btnTouchEvent)
	btn_talk:addTouchEventListener(btnTouchEvent)
	btn_resolve:addTouchEventListener(btnTouchEvent)
	btn_more:addTouchEventListener(btnTouchEvent)
    btn_time:addTouchEventListener(btnTouchEvent)
    btn_change:addTouchEventListener(btnTouchEvent)
    btn_fuli:addTouchEventListener(btnTouchEvent)

	panel_resolve:addTouchEventListener(btnTouchEvent)
	panel_relic:addTouchEventListener(btnTouchEvent)
	panel_tower:addTouchEventListener(btnTouchEvent)
	panel_jjc:addTouchEventListener(btnTouchEvent)
	panel_alliance:addTouchEventListener(btnTouchEvent)
	panel_main:addTouchEventListener(btnTouchEvent)

	local _button = {}
	table.insert(_button,btn_activity)
    table.insert(_button,btn_fuli)
	table.insert(_button,btn_time)
    table.insert(_button,btn_welfare_recharge)
	table.insert(_button,btn_trial)

	for key,obj in pairs(_button) do
		button_Pos[key] = {} 
		button_Pos[key].x,button_Pos[key].y= obj:getPosition()
	end

    local _fuliBtn = {}
    table.insert(_fuliBtn,btn_sign)
    table.insert(_fuliBtn,btn_lv)
    table.insert(_fuliBtn,btn_gift)
    table.insert(_fuliBtn,btn_award)
    table.insert(_fuliBtn,btn_change)
    for key,obj in pairs(_fuliBtn) do
		button_Pos_Fuli[key] = {} 
		button_Pos_Fuli[key].x,button_Pos_Fuli[key].y= obj:getPosition()
	end

	temp_Pos.leftX,temp_Pos.leftY = btn_prize:getPosition()
	temp_Pos.rightX,temp_Pos.rightY = btn_task:getPosition()
end

function UIHomePage.setup()
    if UIHomePage.accessCount <= 1 then
        UIHomePage.accessCount = UIHomePage.accessCount + 1
    end
    if not UITalkFly.layer then
        UITalkFly.create()
    end
      
	UIGuidePeople.isGuide(nil,UIHomePage)
	local btn_recharge = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_recharge") 		--充值按钮
	local btn_task = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_task") 				--每日任务按钮
	local btn_sign = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_sign") 				--签到礼包按钮
	local btn_activity = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_activity") 		--活动按钮
	local btn_lv = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_lv") 					--等级礼包按钮
	local btn_gift = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_gift") 				--开服礼包按钮
	local btn_award = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_award") 			--在线奖励按钮
	local btn_prize = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_prize") 			--领奖中心按钮
	local btn_equipment = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_equipment") 	--装备背包按钮
	local btn_card = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_card") 				--卡牌按钮
	local btn_gongfa = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_gongfa") 			--功法按钮
    local btn_soul = ccui.Helper:seekNodeByName( UIHomePage.Widget , "btn_soul" )           --斗魂
	local btn_set = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_set") 				--设置按钮
	local btn_email = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_email") 			--邮件
	local btn_ranking = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_ranking")		--排行榜
	local btn_trial = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_trial") 			--试炼日
	local btn_talk = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_talk") 				--聊天
	local btn_resolve = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_resolve")		--分解
	local btn_more = ccui.Helper:seekNodeByName(UIHomePage.Widget, "image_more")			--更多
	local image_more = ccui.Helper:seekNodeByName(UIHomePage.Widget, "image_more_info")		--更多弹框
    local btn_time = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_time")              --限时特惠
	local btn_change = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_change")          --无敌兑换
    local btn_fuli = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_fuli")              --福利
    local panel_main = ccui.Helper:seekNodeByName(UIHomePage.Widget, "panel")				--首页弹框
    local btn_welfare_recharge = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_welfare_recharge") --充值福利
    local panel_alliance = ccui.Helper:seekNodeByName(UIHomePage.Widget,"panel_alliance") 	--联盟

    btn_resolve:getChildByName("image_hint"):setVisible(false)

    if net.InstPlayer.int["4"] >= 25 then --zy 联盟招人
        if net.InstUnionMember and net.InstUnionMember.int["2"] ~= 0 then
             panel_alliance:getChildByName("paopao"):setVisible(false)
        else
             panel_alliance:getChildByName("paopao"):setVisible(true)
        end
    else
        panel_alliance:getChildByName("paopao"):setVisible(false)
    end	

	local _button = {}
    table.insert(_button,btn_activity)
    table.insert(_button,btn_fuli)
	table.insert(_button,btn_time)
    table.insert(_button,btn_welfare_recharge)
	table.insert(_button,btn_trial)
    local _fuliBtn = {}
    table.insert(_fuliBtn,btn_sign)
	table.insert(_fuliBtn,btn_lv)
    table.insert(_fuliBtn,btn_gift)
    table.insert(_fuliBtn,btn_award)
    table.insert(_fuliBtn,btn_change)
    -------------s----------------------------
    --print("isShowMonthCardHint 月卡===== ")
    --print( isShowMonthCardHint())
    if UIHomePage.isShowMonthCardHint() then 
        btn_welfare_recharge:getChildByName("image_hint"):setVisible(true) 
    else
        btn_welfare_recharge:getChildByName("image_hint"):setVisible(false)
    end
	-------------充值--------------------------------
--	local state = UIGiftVip.getState()
--	if state then 
--		btn_recharge:getChildByName("image_hint"):setVisible(true)
--	else 
		btn_recharge:getChildByName("image_hint"):setVisible(false)
--	end
	----精彩活动-----------------------------------
	local ActivityThings = UIActivityPanel.getActivityThing()
    if #ActivityThings == 0 then 
    	btn_activity:setVisible(false)
    else
        btn_activity:setVisible(true)
        local result = UIActivityBath.checkImageHint()  or UIActivitySuccess.checkImageHint() or UIActivityFoundation.checkImageHint(btn_activity,true)
        local flashSaleFlag = false
        for key,obj in pairs(ActivityThings) do
            if obj.string["9"] == "lucky" then
                if UIHomePage.accessCount == 1 then
                    UIHomePage.luckyFlag = true
                end
            elseif obj.string["9"] == "SaveConsume" then
                if not result then --如果不需要判断累计消耗就不去判断避免联网
                    UIActivityCostAll.checkImageHint(btn_activity,true)
                end
            elseif obj.string["9"] == "flashSale" then
                flashSaleFlag = UIActivityBuy.checkImageHint()
            elseif obj.string["9"] == "hJYStore" then
                flashSaleFlag = UIActivityHJY.checkImageHint()
            end
        end
        if result or UIHomePage.luckyFlag or flashSaleFlag or UIHomePage.costAllFlag then
            btn_activity:getChildByName("image_hint"):setVisible(true)
        else
            btn_activity:getChildByName("image_hint"):setVisible(false)
        end
    end
    ------每日任务------------------------------
    local signInOpen = false
	if net.InstPlayerBarrier then
      	for key,obj in pairs(net.InstPlayerBarrier) do
        	if obj.int["5"] == 3 and obj.int["3"] == 25 then ---第三章节最后一个关卡打完才开启
        		signInOpen = true
        	end
      	end
    end
    if net.InstPlayerDailyTask and signInOpen then
    	btn_task:setVisible(true)
       local level = net.InstPlayer.int["4"]
       local flag = false
       for key, obj in pairs(net.InstPlayerDailyTask) do
            if obj.int["3"] < 1000 then
       		    local dictObj = DictDailyTask[tostring(obj.int["3"])]
       		    local taskLevel = DictFunctionOpen[tostring(dictObj.functionOpenId)].level
       		    local totalTimes = dictObj.times
			    local rewardTimes = obj.int["4"]
       		    if level >= taskLevel and obj.int["5"] == 0 and rewardTimes >= totalTimes then 
				    flag = true
			    end
            end
       end
       if flag then 
       	  btn_task:getChildByName("image_hint"):setVisible(true)
       else 
       	  btn_task:getChildByName("image_hint"):setVisible(false)
       end
    else 
    	btn_task:setVisible(false)
    end
    --------------------在线奖励---------------------------------------
	if net.InstActivityOnlineRewards and UIHomePage.flag == false then
		UIHomePage.flag = true
		local InstData = nil 
		for key,obj in pairs(net.InstActivityOnlineRewards) do 
			InstData = obj
		end
		if InstData.int["4"] ~= 0 then 
			if UIHomePage.ScheduleId ~= nil then
		        cc.Director:getInstance():getScheduler():unscheduleScriptEntry(UIHomePage.ScheduleId)
		    end
			btn_award:setVisible(true)
			btn_award:getChildByName("image_hint"):setVisible(false)
			UIHomePage.countDownTime = math.ceil(InstData.int["4"]/1000)
			local ui_timeText = btn_award:getChildByName("image_base_time"):getChildByName("text_time")
			local hour= math.floor(UIHomePage.countDownTime/3600)
            local min= math.floor(UIHomePage.countDownTime%3600/60)
            local sec= UIHomePage.countDownTime%60
            ui_timeText:setString(string.format("%02d:%02d:%02d",hour,min,sec))
            UIHomePage.ScheduleId = cc.Director:getInstance():getScheduler():scheduleScriptFunc(UIHomePage.updateTime,1,false)
		else 
			btn_award:setVisible(false)
		end
	elseif  net.InstActivityOnlineRewards == nil and UIHomePage.flag == false then 
	 	btn_award:setVisible(false)
	end
	---开服礼包 ------------
	if net.InstActivityOpenServiceBag then 
		local InstData = nil 
		for key,obj in pairs(net.InstActivityOpenServiceBag) do 
			InstData = obj
		end
		if InstData.int["3"] == 0 and InstData.string["4"] == "" then 
			btn_gift:setVisible(false)
        elseif InstData.int["3"] == 7 and InstData.string["4"] == "" then
            btn_gift:setVisible(false)
		else 
            btn_gift:setVisible(true)
			if InstData.string["4"] ~= "" then 
				btn_gift:getChildByName("image_hint"):setVisible(true)
			else 
				btn_gift:getChildByName("image_hint"):setVisible(false)
			end
		end
	else 
		btn_gift:setVisible(false)
	end
	--------等级礼包 --------------------
	local InstLevel = net.InstPlayer.int["4"]
	local number = 0 ---达到等级可以领取的礼包数
    local totalNumber = 0 --礼包总数
	for key,obj in pairs(DictActivityLevelBag) do 
        totalNumber = totalNumber + 1
		if InstLevel >= obj.id  then 
			number = number +1
		end
	end
	if net.InstActivityLevelBag then 
		local InstData = nil 
		for key,obj in pairs(net.InstActivityLevelBag) do 
			InstData = obj
		end
		if InstData.string["3"] ~= "" then 
			local GetDatas =utils.stringSplit(InstData.string["3"], ";") --- 已经领取的
			if #GetDatas ~= totalNumber then
                btn_lv:setVisible(true) 
				local num = number -#GetDatas
				if num ~= 0 then 
					btn_lv:getChildByName("image_hint"):setVisible(true)
				else 
					btn_lv:getChildByName("image_hint"):setVisible(false)
				end
			else 
				btn_lv:setVisible(false)
			end
		else 
			if number == 0 then 
				btn_lv:getChildByName("image_hint"):setVisible(false)
			else 
				btn_lv:getChildByName("image_hint"):setVisible(true)
			end
		end
	else
		if number == 0 then 
			btn_lv:getChildByName("image_hint"):setVisible(false)
		else 
			btn_lv:getChildByName("image_hint"):setVisible(true)
		end
	end
    --------------试炼日---------------
    if net.InstPlayer.registerTime and utils.getCurrentTime() > utils.GetTimeByDate(net.InstPlayer.registerTime) + (UIActivityTrial.ACTIVITY_DAY_COUNT*24*60*60) then
        btn_trial:setVisible(false)
    end
    if btn_trial:isVisible() then
        UIActivityTrial.hintFlag = false
        btn_trial:getChildByName("image_hint"):setVisible( UIActivityTrial.checkImageHint(0,true))
    end
    --------------试炼日---------------
    ------------------英雄--------------------------
    utils.addImageHint(UIBagCard.checkImageHint(),btn_card,100,0,0)
    ------------------------------------------------
    --------------------装备----------------------------
    utils.addImageHint(UIBagEquipment.checkImageHint(),btn_equipment,100,0,0)
    ------------------------------------------------
    --------------------神羽------------------------
    local btn_wing = ccui.Helper:seekNodeByName(UIHomePage.Widget , "btn_wing" )            --神羽
    utils.addImageHint(UIBagWing.checkImageHint(),btn_wing,100,0,0)
	--------------签到礼包--------------------------
	if signInOpen then 
		btn_sign:setVisible(true)
		for key,obj in pairs(net.SysActivity) do
            if obj.string["9"] == "signIn" then
                if obj.int["7"] == 0 and obj.string["4"] == "" and obj.string["5"] == "" then
	                btn_sign:setVisible(false)
	            else 
	                if obj.string["4"] ~= "" and obj.string["5"] ~= "" then 
	                    local startTime =utils.GetTimeByDate(obj.string["4"])
	                    local endTime = utils.GetTimeByDate(obj.string["5"])
	                    local currentTime = utils.getCurrentTime()
	                    if startTime > currentTime and currentTime > endTime then 
	            	        btn_sign:setVisible(false)
	                    end
	                end
	            end
                break
            end
	    end
		if net.InstActivitySignIn then 
		  local instData = nil 
		  for key,obj in pairs(net.InstActivitySignIn) do 
		   	instData = obj
		  end
		  local tableTime =utils.changeTimeFormat(instData.string["8"]) -- updateTime
	      if tonumber(tableTime[3]) == dp.loginDay then 
	      	btn_sign:getChildByName("image_hint"):setVisible(false)
	      else 
	      	btn_sign:getChildByName("image_hint"):setVisible(true)
	      end
		end
	else 
		btn_sign:setVisible(false)
	end
    ------------领奖中心------
    local awardNumber = 0
    if net.InstPlayerAward then 
    	for key,obj  in pairs(net.InstPlayerAward) do 
    		awardNumber = awardNumber + 1
    	end
    	btn_prize:setVisible(true)
    end
    if awardNumber == 0  then 
    	btn_prize:setVisible(false)
    end

    -------------聊天----------------
    btn_talk:getChildByName("image_hint"):setVisible(false)

	----------首冲礼包--------
	-- local isGetFirstRechargeGift = 1
 --    if isGetFirstRechargeGift == 0 then 
 --    	btn_shouchong:setVisible(true)
 --    	if net.InstPlayer.int["19"] > 0 then 
 --    		btn_shouchong:getChildByName("image_hint"):setVisible(true)
 --    	else 
 --    		btn_shouchong:getChildByName("image_hint"):setVisible(false)
 --    	end
 --    else 
 --    	btn_shouchong:setVisible(false)
 --    end
	----------邮件---------------
		 btn_email:setVisible(true)

        if UIHomePage.yj then 
            btn_email:getChildByName("image_hint"):setVisible(true)
        else
            btn_email:getChildByName("image_hint"):setVisible(false)
        end
        if UIHomePage.tk then
            btn_talk:getChildByName("image_hint"):setVisible( true )
        else
            btn_talk:getChildByName("image_hint"):setVisible( false )
        end
    -------------更多--------------
       if btn_talk:getChildByName("image_hint"):isVisible() or btn_email:getChildByName("image_hint"):isVisible() then
            btn_more:getChildByName("image_hint"):setVisible(true)
       else
            btn_more:getChildByName("image_hint"):setVisible(false)
       end 

	---------限时抢购---------------
--	if not UIActivityBuy.isActivityEnd() then 
--	 	btn_buy:setVisible(true)
--	else 
--	 	btn_buy:setVisible(false)
--	end
	----------排列按钮位置------------------------
	local buttons = {}
	for key,obj in pairs(_button) do 
		if obj:isVisible() then 
			table.insert(buttons,obj)
		end
	end
	for key,obj  in pairs(buttons) do 
		obj:setPosition(cc.p(button_Pos[key].x,button_Pos[key].y))
	end
    local fuliButtons = {}
    local btnCount = 0
    local btnWidth = 0
    local contentSizeWidth = 564
    local contentSizeHeight = 185
    --计算因为隐藏按钮而需要减少的宽度
    for key,obj in pairs(_fuliBtn) do
        if obj:isVisible() then 
			table.insert(fuliButtons,obj)
        else
            btnCount = btnCount + 1
            local before = _fuliBtn[key-1]
            if before then
                btnWidth = btnWidth + button_Pos_Fuli[key].x-button_Pos_Fuli[key-1].x
            else
                local after = _fuliBtn[key+1]
                if after then
                    btnWidth = button_Pos_Fuli[key+1].x-button_Pos_Fuli[key].x
                end
            end
		end
    end
    for key,obj  in pairs(fuliButtons) do 
		obj:setPosition(cc.p(button_Pos_Fuli[key].x,button_Pos_Fuli[key].y))
	end
    local fuliBox = panel_main:getChildByName("image_gift")
    fuliBox:setContentSize(contentSizeWidth-btnWidth,contentSizeHeight)
    if btnCount >= 5 then
        isShowFuli = false
    else
        isShowFuli = true
    end
	if not btn_task:isVisible() then
		btn_prize:setPosition(cc.p(temp_Pos.rightX,temp_Pos.rightY))
		btn_task:setPosition(cc.p(temp_Pos.leftX,temp_Pos.rightY))
	else
		btn_prize:setPosition(cc.p(temp_Pos.leftX,temp_Pos.rightY))
		btn_task:setPosition(cc.p(temp_Pos.rightX,temp_Pos.rightY))
	end

	-----------------美人系统-------------------
	local btn_beruty = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_beruty") --美人系统按钮
	if btn_beruty:isVisible() then
		btn_beruty:getChildByName("image_hint"):setVisible(UIBeauty.isShowHint())
	end
	-----------------美人系统-------------------

	-----------------限时特惠-------------------
    local timeActivity = UIActivityTime.getActivityThing()
    local timeResult = false
    for key, obj in pairs(timeActivity) do
        if obj.sname == "grabTheHour" then
            timeResult = timeResult or UIActivityGrabTheHour.checkImageHint()
        elseif obj.sname == "LimitTimeHero" then
            if not timeResult then
                timeResult = timeResult or UIAactivityLimitTimeHero.checkImageHint()
            end
        end
    end
    btn_time:getChildByName("image_hint"):setVisible(timeResult)

	-----------------限时特惠-------------------

	-----------------无敌兑换-------------------
    if UIActivityExchange.checkImageHint() then
        btn_change:getChildByName("image_hint"):setVisible(true)
    else
        btn_change:getChildByName("image_hint"):setVisible(false)
    end
	-----------------无敌兑换-------------------

    ----------------福利----------------------
    local signFlag = btn_sign:isVisible() and btn_sign:getChildByName("image_hint"):isVisible()
    local lvFlag = btn_lv:isVisible() and btn_lv:getChildByName("image_hint"):isVisible()
    local giftFlag = btn_gift:isVisible() and btn_gift:getChildByName("image_hint"):isVisible()
    local awardFlag = btn_award:isVisible() and btn_award:getChildByName("image_hint"):isVisible()
    local changeFlag = btn_change:isVisible() and btn_change:getChildByName("image_hint"):isVisible()
    if signFlag or lvFlag or giftFlag or awardFlag or changeFlag then
        btn_fuli:getChildByName("image_hint"):setVisible(true)
    else
        btn_fuli:getChildByName("image_hint"):setVisible(false)
    end
    ---------------福利-----------------------
end

--function UIHomePage.setBtnTimePoint(_visible)
--	-----------------限时特惠-------------------
--	if UIHomePage.Widget and UIHomePage.Widget:getParent() then
--		local btn_time = ccui.Helper:seekNodeByName(UIHomePage.Widget, "btn_time")
--		btn_time:getChildByName("image_hint"):setVisible(_visible)
--	end
--	UIHomePage.btn_time_RedPoint = _visible
--	-----------------限时特惠-------------------
--end