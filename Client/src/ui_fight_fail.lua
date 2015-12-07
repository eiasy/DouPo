UIFightFail = {}
local _fightType = nil --战斗类型
local _customParam = nil --自定义参数
local screenHeight = cc.Director:getInstance():getVisibleSize().height
local animalHeight = 0 
if screenHeight == 1136 then 
  animalHeight = 950 
elseif screenHeight == 960 then 
  animalHeight = 845 
end
function UIFightFail.init()
	local ui_image_base_di = ccui.Helper:seekNodeByName(UIFightFail.Widget, "image_base_di")
	local ui_describe = ccui.Helper:seekNodeByName(ui_image_base_di, "text_describe") --描述
	local ui_cardTrain = ccui.Helper:seekNodeByName(ui_image_base_di, "image_recruit") --英雄培养
	local ui_lineup = ccui.Helper:seekNodeByName(ui_image_base_di, "image_lineup") --调整阵容
	local ui_card = ccui.Helper:seekNodeByName(ui_image_base_di, "image_card") --卡牌升级
	local ui_equipment = ccui.Helper:seekNodeByName(ui_image_base_di, "image_equipment") --装备强化
	local btnSure = ccui.Helper:seekNodeByName(UIFightFail.Widget, "btn_sure") --确定按钮
	local btn_again = ccui.Helper:seekNodeByName(UIFightFail.Widget, "btn_again") --再战按钮
	local function btnTouchEvent(sender, eventType)
		if eventType == ccui.TouchEventType.ended then
			if sender == btnSure then
				if  _fightType == dp.FightType.FIGHT_TASK.COMMON then
     				UIManager.showScreen("ui_fight_task")
                    UIFightTask.showPosterDialog()
     			elseif  _fightType == dp.FightType.FIGHT_TASK.ELITE then
     				UIFight.setFlag(1)
         			UIManager.showScreen("ui_notice", "ui_team_info", "ui_fight", "ui_menu")
         		elseif  _fightType == dp.FightType.FIGHT_TASK.ACTIVITY then
     				UIFight.setFlag(3)
         			UIManager.showScreen("ui_notice", "ui_team_info", "ui_fight", "ui_menu")
                    if UIFightActivityChoose.wingTo then                   
                        UIManager.showWidget("ui_notice", "ui_lineup")
                        UIManager.showWidget("ui_menu")
                        UILineup.toWingInfo()
                    end
                    if UIFightPreView.wingTo then
                        UIManager.showWidget("ui_notice", "ui_lineup")
                        UIManager.showWidget("ui_menu")
                        UILineup.toWingInfo()
                    end
				end 
			elseif sender == btn_again then
				UIManager.popScene()
				utils.sendFightData(_customParam,_fightType)
                UIFightMain.setup()
			elseif sender == ui_cardTrain then
                if UIFightActivityChoose.wingTo then
                    UIFightActivityChoose.wingTo = false
                end
                if UIFightPreView.wingTo then
                    UIFightPreView.wingTo = false
                end
				local openLv = DictFunctionOpen[tostring(StaticFunctionOpen.state)].level
				if net.InstPlayer.int["4"] < openLv then 
					UIManager.showToast("战队等级达到"..openLv.."级开启！")
				else
					UIManager.showScreen("ui_notice","ui_lineup", "ui_menu")
				end
			elseif sender == ui_lineup then
                if UIFightActivityChoose.wingTo then
                    UIFightActivityChoose.wingTo = false
                end
                if UIFightPreView.wingTo then
                    UIFightPreView.wingTo = false
                end
			   UIManager.pushScene("ui_lineup_embattle")
			elseif sender == ui_card then
                if UIFightActivityChoose.wingTo then
                    UIFightActivityChoose.wingTo = false
                end
                if UIFightPreView.wingTo then
                    UIFightPreView.wingTo = false
                end
			   UIManager.showScreen("ui_notice", "ui_team_info", "ui_bag_card", "ui_menu")
			elseif sender == ui_equipment then
                if UIFightActivityChoose.wingTo then
                    UIFightActivityChoose.wingTo = false
                end
                if UIFightPreView.wingTo then
                    UIFightPreView.wingTo = false
                end
			   UIManager.showScreen("ui_notice", "ui_team_info", "ui_bag_equipment", "ui_menu")
			end
		end
	end
	btn_again:setPressedActionEnabled(true)
	btnSure:setPressedActionEnabled(true)
	btnSure:addTouchEventListener(btnTouchEvent)
	btn_again:addTouchEventListener(btnTouchEvent)
	ui_cardTrain:addTouchEventListener(btnTouchEvent)
	ui_lineup:addTouchEventListener(btnTouchEvent)
	ui_card:addTouchEventListener(btnTouchEvent)
	ui_equipment:addTouchEventListener(btnTouchEvent)
    utils.GrayWidget(ccui.Helper:seekNodeByName(UIFightFail.Widget, "image_di"), true)
end

function UIFightFail.setup()
	AudioEngine.playEffect("sound/fail.mp3")
    local image_basemap = UIFightFail.Widget:getChildByName("image_basemap")
	local ui_fightName = ccui.Helper:seekNodeByName(image_basemap, "text_fight_name")
	local ui_curFight = ccui.Helper:seekNodeByName(image_basemap, "label_zhan")
	ui_curFight:setString(utils.getFightValue())
	local armature = ActionManager.getUIAnimation(12)
	armature:setPosition(cc.p(320,animalHeight))
    UIFightFail.Widget:addChild(armature,100,100)
    local text_hint1 = image_basemap:getChildByName("text_hint1")
    local text_hint2 = image_basemap:getChildByName("text_hint2")
	local ui_difficulty = ccui.Helper:seekNodeByName(UIFightFail.Widget, "image_fight_difficulty") 
    if _fightType == dp.FightType.FIGHT_TASK.COMMON or _fightType == dp.FightType.FIGHT_TASK.ELITE or _fightType == dp.FightType.FIGHT_TASK.ACTIVITY then  --_customParam[1]  普通副本传  DictBarrierLevelId  其他传chapterId
      if  _fightType == dp.FightType.FIGHT_TASK.COMMON then
           local DictBarrierLevelId = _customParam.barrierLevelId
           local barrierLevel = DictBarrierLevel[tostring(DictBarrierLevelId)].level
           local barrierId = DictBarrierLevel[tostring(DictBarrierLevelId)].barrierId
           local fightName = DictBarrier[tostring(barrierId)].name
           local image_star={} 
           image_star[1] = ui_difficulty:getChildByName("image_star1")
           image_star[2] = ui_difficulty:getChildByName("image_star2")
           image_star[3] = ui_difficulty:getChildByName("image_star3")
           for i=1,3 do
              if i <= barrierLevel then
                  image_star[i]:loadTexture("ui/fb_xing.png")
              else
                  image_star[i]:loadTexture("ui/fb_xing1.png")
              end
          end
           if barrierLevel ==1 then 
               ui_difficulty:loadTexture("ui/zd_jd.png")
           elseif barrierLevel ==2 then 
               ui_difficulty:loadTexture("ui/zd_pt.png")
           elseif barrierLevel ==3 then 
               ui_difficulty:loadTexture("ui/zd_kn.png")
           end
           ui_fightName:setString(fightName)
           text_hint1:setVisible(false)
           text_hint2:setVisible(false)
      else
          local chapterId = _customParam.chapterId
          local name = DictChapter[tostring(chapterId)].name
          ui_fightName:setString(name)
          ui_difficulty:setVisible(false)
          text_hint1:setVisible(true)
          text_hint2:setVisible(true)
      end 
	end
end

function UIFightFail.free()
	UIFightFail.Widget:removeChildByTag(100)
	_customParam = nil
end

--@fightType : 战斗类型
--@param : 自定义参数(格式由调用方自己定义)
function UIFightFail.setParam(fightType, param)
	_fightType = fightType
	_customParam = param
end