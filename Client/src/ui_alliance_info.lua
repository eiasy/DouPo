UIAllianceInfo = {}

local BUTTON_TEXT_APPOINT = "任命"
local BUTTON_TEXT_KICKOUT = "踢出"
local BUTTON_TEXT_IMPEACH = "弹劾"
local BUTTON_TEXT_CHECK = "查看"
local BUTTON_TEXT_EXIT = "退出"

local userData = nil

local ui_scrollView = nil
local ui_svItem = nil

local _applyBtnPosX = nil
local _curCommand = nil
local _currentMemberCount = 0

local netCallbackFunc = nil

local function cleanScrollView()
	if ui_svItem:getReferenceCount() == 1 then
  	ui_svItem:retain()
  end
  ui_scrollView:removeAllChildren()
end

local function getDynamicItem()
	local layout = ccui.Layout:create()
	layout:setContentSize(cc.size(UIManager.screenSize.width, 90))
	local ui_timeLabel = ccui.Text:create()
	ui_timeLabel:setName("ui_timeLabel")
	ui_timeLabel:setFontName(dp.FONT)
	ui_timeLabel:setFontSize(23)
	ui_timeLabel:setTextColor(cc.c3b(255, 255, 255))
	ui_timeLabel:setPosition(cc.p(82, 59))
	layout:addChild(ui_timeLabel)
	local ui_richText = ccui.RichText:create()
	ui_richText:setName("ui_richText")
	ui_richText:setPosition(cc.p(400, 40))
	ui_richText:ignoreContentAdaptWithSize(false)
	ui_richText:setContentSize(cc.size(440, 70))
	layout:addChild(ui_richText)
	return layout
end

local function layoutScrollView(_listData, _initItemFunc, _isDynamic)
	local SCROLLVIEW_ITEM_SPACE = 0
	cleanScrollView()
	ui_scrollView:jumpToTop()
	local _innerHeight = 0
	for key, obj in pairs(_listData) do
		local scrollViewItem = _isDynamic and getDynamicItem() or ui_svItem:clone()
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
		local apX, apY = childs[i]:getAnchorPoint().x, childs[i]:getAnchorPoint().y
		if i == 1 then
			if apX == 0.5 and apY == 0.5 then
				childs[i]:setPosition(ui_scrollView:getContentSize().width / 2, ui_scrollView:getInnerContainerSize().height - childs[i]:getContentSize().height / 2 - SCROLLVIEW_ITEM_SPACE)
			elseif apX == 0 and apY == 0 then
				childs[i]:setPosition((ui_scrollView:getContentSize().width - childs[i]:getContentSize().width) / 2, ui_scrollView:getInnerContainerSize().height - childs[i]:getContentSize().height - SCROLLVIEW_ITEM_SPACE)
			end
		else
			if apX == 0.5 and apY == 0.5 then
				childs[i]:setPosition(ui_scrollView:getContentSize().width / 2, prevChild:getBottomBoundary() - childs[i]:getContentSize().height / 2 - SCROLLVIEW_ITEM_SPACE)
			elseif apX == 0 and apY == 0 then
				childs[i]:setPosition((ui_scrollView:getContentSize().width - childs[i]:getContentSize().width) / 2, prevChild:getBottomBoundary() - childs[i]:getContentSize().height - SCROLLVIEW_ITEM_SPACE)
			end
		end
		prevChild = childs[i]
	end
	ActionManager.ScrollView_SplashAction(ui_scrollView)
end

local function initAllianceInfo(_msgData)
	local image_info = UIAllianceInfo.Widget:getChildByName("image_basemap"):getChildByName("image_info")
	local ui_allianceName = ccui.Helper:seekNodeByName(image_info:getChildByName("image_title_l"), "text_name")
	local ui_allianceBg = image_info:getChildByName("image_di_alliance")
	local ui_allianceLeaderName = ccui.Helper:seekNodeByName(ui_allianceBg, "text_name")
	local ui_allianceMember = ccui.Helper:seekNodeByName(ui_allianceBg, "text_member")
	local ui_allianceLevel = ccui.Helper:seekNodeByName(ui_allianceBg, "text_alliance_lv")
	local ui_allianceExpBar = ccui.Helper:seekNodeByName(ui_allianceBg, "bar_exp")
	local ui_allianceExp = ui_allianceExpBar:getChildByName("text_exp")
	local ui_allianceNoticeBg = image_info:getChildByName("image_notice")
	local ui_allianceNotice = ui_allianceNoticeBg:getChildByName("text_notice")
	local ui_allianceOfferBg = image_info:getChildByName("image_manifesto")
	local ui_allianceOffer = ui_allianceOfferBg:getChildByName("text_manifesto")
	local btn_apply = image_info:getChildByName("btn_apply")
	local btn_disband = image_info:getChildByName("btn_disband")

	ui_allianceName:setString(_msgData.string["2"])
	ui_allianceLevel:setString("等级：" .. _msgData.int["4"])
	ui_allianceLeaderName:setString("盟主：" .. userData.leaderName)
    _currentMemberCount = _msgData.int["7"]
	ui_allianceMember:setString(string.format("成员：%d/%d", _msgData.int["7"], _msgData.int["6"]))
	local upgradeExp = DictUnionLevelPriv[tostring(_msgData.int["4"])].exp
	ui_allianceExp:setString(_msgData.int["3"].." / "..upgradeExp)
	ui_allianceExpBar:setPercent(utils.getPercent(_msgData.int["3"], upgradeExp))
	ui_allianceNotice:setString(_msgData.string["10"])
	ui_allianceOffer:setString(_msgData.string["11"])

	if not _applyBtnPosX then
		_applyBtnPosX = btn_apply:getPositionX()
	end
	local selfGradeId = net.InstUnionMember.int["4"]
	if selfGradeId == 1 or selfGradeId == 2 then
		ui_allianceNoticeBg:setTouchEnabled(true)
		ui_allianceOfferBg:setTouchEnabled(true)
		btn_apply:setVisible(true)
		if selfGradeId == 2 then
			btn_disband:setVisible(false)
			btn_apply:setPositionX(btn_apply:getParent():getContentSize().width / 2)
		else
			btn_disband:setVisible(true)
			btn_apply:setPositionX(_applyBtnPosX)
		end
		btn_apply:setPressedActionEnabled(true)
		btn_disband:setPressedActionEnabled(true)
		local function onButtonEvent(sender, eventType)
			if eventType == ccui.TouchEventType.ended then
				if sender == btn_apply then
					UIAllianceApply.show({currentCount=_currentMemberCount,totalCount=_msgData.int["6"]})
				elseif sender == btn_disband then
					if _msgData.int["7"] >= 5 then
						UIManager.showToast("5人及以上联盟不允许解散")
					else
						utils.showDialog("确定要解散联盟吗？", function()
							UIManager.showLoading()
							netSendPackage({header = StaticMsgRule.dissolveUnion, msgdata = {int={instUnionMemberId=net.InstUnionMember.int["1"]}}}, netCallbackFunc)
						end)
					end
				elseif sender == ui_allianceNoticeBg then
					UIAllianceHint.show({title="公告",desc=ui_allianceNotice:getString(),callbackFunc=netCallbackFunc})
				elseif sender == ui_allianceOfferBg then
					UIAllianceHint.show({title="宣言",desc=ui_allianceOffer:getString(),callbackFunc=netCallbackFunc})
				end
			end
		end
		btn_apply:addTouchEventListener(onButtonEvent)
		btn_disband:addTouchEventListener(onButtonEvent)
		ui_allianceNoticeBg:addTouchEventListener(onButtonEvent)
		ui_allianceOfferBg:addTouchEventListener(onButtonEvent)
	else
		btn_apply:setVisible(false)
		btn_disband:setVisible(false)
		ui_allianceNoticeBg:setTouchEnabled(false)
		ui_allianceOfferBg:setTouchEnabled(false)
	end
end

local function appointDialog(sender,params)
	local dialog = ccui.Layout:create()
	dialog:setContentSize(UIManager.screenSize)
	dialog:setBackGroundColorType(ccui.LayoutBackGroundColorType.solid)
	-- dialog:setBackGroundColor(cc.c3b(0, 0, 0))
	dialog:setBackGroundColorOpacity(0)
	dialog:setTouchEnabled(true)
	dialog:retain()
	local bg_image = cc.Scale9Sprite:create("ui/dialog_bg.png")
  bg_image:setAnchorPoint(cc.p(0.5, 0.5))
  bg_image:setPreferredSize(cc.size(410, 262))
  -- bg_image:setPosition(cc.p(UIManager.screenSize.width / 2, UIManager.screenSize.height / 2))
  dialog:addChild(bg_image)
  local bgSize = bg_image:getPreferredSize()
  local arrow = ccui.ImageView:create("ui/tk_j_jiantou02.png")
  dialog:addChild(arrow, 1)
  local pos = sender:getWorldPosition()
  if pos.y > bgSize.height then
  	arrow:setRotation(-90)
  	bg_image:setPosition(cc.p(pos.x, pos.y - (sender:getContentSize().height + bgSize.height) / 2))
  	arrow:setPosition(cc.p(bg_image:getPositionX(), bg_image:getPositionY() + bgSize.height / 2))
	else
		arrow:setRotation(90)
		bg_image:setPosition(cc.p(pos.x, pos.y + (sender:getContentSize().height + bgSize.height) / 2))
		arrow:setPosition(cc.p(bg_image:getPositionX(), bg_image:getPositionY() - bgSize.height / 2))
	end
	local buttonSpace = 10
  local upBtn = ccui.Button:create("ui/tk_btn_big_yellow.png", "ui/tk_btn_big_yellow.png")
  upBtn:setTitleText("移交盟主")
  upBtn:setTitleFontName(dp.FONT)
  upBtn:setTitleColor(cc.c3b(255, 255, 255))
  upBtn:setTitleFontSize(30)
  upBtn:setPressedActionEnabled(true)
  upBtn:setTouchEnabled(true)
  upBtn:setPosition(cc.p(bgSize.width / 2, (bgSize.height + upBtn:getContentSize().height + buttonSpace) / 2))
  bg_image:addChild(upBtn)
  local downBtn = ccui.Button:create("ui/tk_btn_big_yellow.png", "ui/tk_btn_big_yellow.png")
	downBtn:setTitleText(params.gradeId == 2 and "罢免副盟主" or "任命副盟主")
  downBtn:setTitleFontName(dp.FONT)
  downBtn:setTitleColor(cc.c3b(255, 255, 255))
  downBtn:setTitleFontSize(30)
  downBtn:setPressedActionEnabled(true)
  downBtn:setTouchEnabled(true)
  downBtn:setPosition(cc.p(bgSize.width / 2, (bgSize.height - downBtn:getContentSize().height - buttonSpace) / 2))
  bg_image:addChild(downBtn)
  
  local function btnEvent(sender, eventType)
		if eventType == ccui.TouchEventType.ended then
			AudioEngine.playEffect("sound/button.mp3")
			UIManager.uiLayer:removeChild(dialog, true)
			cc.release(dialog)
			local sendPackage = nil
			if sender == upBtn then
				utils.showDialog("确定任命\n"..params.name.."\n为新盟主吗？", function()
					sendPackage(1)
				end)
			elseif sender == downBtn then
				if params.gradeId == 2 then
					utils.showDialog("确定罢免\n"..params.name.."\n的副盟主之位吗？", function()
						sendPackage(3)
					end)
				else
					utils.showDialog("确定任命\n"..params.name.."\n为副盟主吗？", function()
						sendPackage(2)
					end)
				end
			end
			sendPackage = function(_gradeId)
				UIManager.showLoading()
				netSendPackage({header = StaticMsgRule.appointUnion, msgdata = 
					{int={instUnionMemberId=params.instUnionMemberId,unionGradeId=_gradeId}}}, netCallbackFunc)
			end
		end
	end
	upBtn:addTouchEventListener(btnEvent)
	downBtn:addTouchEventListener(btnEvent)
	dialog:addTouchEventListener(btnEvent)
	UIManager.uiLayer:addChild(dialog, 10000)
end

local function setScrollViewItem(_item, _data)
	local selfGradeId = net.InstUnionMember.int["4"]
	local image_info = _item:getChildByName("image_di_info")
	local ui_frameImg = image_info:getChildByName("image_frame_title")
	local ui_icon = ui_frameImg:getChildByName("image_title")
	local ui_vipFlag = ui_frameImg:getChildByName("image_vip")
	local ui_name = image_info:getChildByName("text_name")
	local ui_onlineTime = image_info:getChildByName("text_time")
	local ui_level = image_info:getChildByName("text_lv")
	local ui_fight = image_info:getChildByName("text_fight")
	local ui_todayOffer = image_info:getChildByName("text_congratulate_tody")
	local ui_totalOffer = image_info:getChildByName("text_congratulate_all")
	local ui_memberFlag = _item:getChildByName("image_job")
	ui_name:setString(_data.string["10"])
	local _onlineState = _data.long["12"] --在线状态 0:在线
	if _onlineState == 0 then
		ui_onlineTime:setString("在线")
	else
		ui_onlineTime:setString(UIAlliance.getOnlineState(_onlineState / 1000))
	end
	local dictCard = DictCard[tostring(_data.int["13"])]
	if dictCard then
		ui_icon:loadTexture("image/" .. DictUI[tostring(dictCard.smallUiId)].fileName)
	end
	if _data.int["14"] > 0 then
		ui_vipFlag:setVisible(true)
	else
		ui_vipFlag:setVisible(false)
	end
	ui_level:setString("等 级：".._data.int["11"])
	ui_fight:setString("战斗力：".._data.int["15"])
	ui_todayOffer:setString("今日贡献：" .. _data.int["6"])
	ui_totalOffer:setString("累计贡献：" .. _data.int["5"])
	local gradeId = _data.int["4"]
	local grades = UIAlliance.getAllianceGrade(gradeId)
	if grades then
		ui_memberFlag:loadTexture(grades.icon)
	end
	local btn_left = _item:getChildByName("btn_on")
	local btn_middle = _item:getChildByName("btn_out")
	local btn_right = _item:getChildByName("btn_look")
	btn_left:setPressedActionEnabled(true)
	btn_middle:setPressedActionEnabled(true)
	btn_right:setPressedActionEnabled(true)
	if net.InstPlayer.int["1"] == _data.int["3"] then
		_item:loadTexture("ui/jjc_di01.png")
		btn_left:setVisible(false)
		btn_middle:setVisible(false)
		btn_right:setVisible(true)
		btn_right:setTitleText(BUTTON_TEXT_EXIT)
        ui_onlineTime:setTextColor(cc.c3b(255, 255, 0))
	else
        ui_onlineTime:setTextColor(cc.c3b(139, 69, 19))
		if selfGradeId == 1 then --盟主
			btn_left:setVisible(true)
			btn_left:setTitleText(BUTTON_TEXT_APPOINT)
			btn_middle:setVisible(true)
			btn_middle:setTitleText(BUTTON_TEXT_KICKOUT)
			btn_right:setVisible(true)
			btn_right:setTitleText(BUTTON_TEXT_CHECK)
		elseif selfGradeId == 2 then --副盟主
			btn_left:setVisible(false)
            if gradeId == 2 then --副盟主遇到副盟主
                btn_middle:setVisible(false)
            else
                btn_middle:setVisible(true)
            end
			btn_middle:setTitleText(BUTTON_TEXT_KICKOUT)
			btn_right:setVisible(true)
			btn_right:setTitleText(BUTTON_TEXT_CHECK)
		elseif selfGradeId == 3 then --成员
			btn_left:setVisible(false)
			btn_middle:setVisible(false)
			btn_right:setVisible(true)
			btn_right:setTitleText(BUTTON_TEXT_CHECK)
		end
        if gradeId == 1 then --zy 修改弹劾之后联盟信息中盟主名字没有更新的问题
			--userData.leaderName = _data.string["10"]
			btn_middle:setVisible(true)
			btn_middle:setTitleText(BUTTON_TEXT_IMPEACH)
		end
	end
    	if gradeId == 1 then
			userData.leaderName = _data.string["10"]		
		end
    ui_icon:setTouchEnabled(true)
    ui_icon:addTouchEventListener(function(sender, eventType)
        if eventType == ccui.TouchEventType.ended then
            local image_info = UIAllianceInfo.Widget:getChildByName("image_basemap"):getChildByName("image_info")
	        local ui_allianceName = ccui.Helper:seekNodeByName(image_info:getChildByName("image_title_l"), "text_name")
            UIAllianceTalk.show({playerId=_data.int["3"],userName=_data.string["10"],userLvl=_data.int["11"],userFight=_data.int["15"],userUnio=ui_allianceName:getString(),headId=_data.int["13"],vip=_data.int["14"]})
        end
    end)
	local function onButtonEvent(sender, eventType)
		if eventType == ccui.TouchEventType.ended then
			if sender == btn_left then
				if sender:getTitleText() == BUTTON_TEXT_APPOINT then
					-- UIManager.showToast("任命！~" .. BUTTON_TEXT_APPOINT)
					appointDialog(sender,{gradeId=gradeId,instUnionMemberId=_data.int["1"],name=_data.string["10"]})
				end
			elseif sender == btn_middle then
				if sender:getTitleText() == BUTTON_TEXT_KICKOUT then
					-- UIManager.showToast("踢出！~" .. BUTTON_TEXT_KICKOUT)
					utils.showDialog("确定要将\n" .. _data.string["10"] .. "\n踢出联盟么？", function()
						UIManager.showLoading()
						_curCommand = BUTTON_TEXT_KICKOUT
						netSendPackage({header = StaticMsgRule.exitUnion, 
							msgdata = {int={instUnionMemberId=_data.int["1"],type=-1}}}, netCallbackFunc)
					end)
				elseif sender:getTitleText() == BUTTON_TEXT_IMPEACH then  --zy弹劾						
						userData.leaderUnioId = _data.int["1"]
                       -- cclog( "----------------->leaderId :".._data.int["1"] .." playerId : ".. userData.selfUnioId )
                        UIManager.showLoading()
						netSendPackage({header = StaticMsgRule.isWishUnionAccCon, 
                            msgdata = {int={leaderUnionMemId=_data.int["1"],accuseUnionMemId = userData.selfUnioId }}}, netCallbackFunc)						                    
				end
			elseif sender == btn_right then
				if sender:getTitleText() == BUTTON_TEXT_CHECK then
					UIManager.showLoading()
					netSendPackage({header = StaticMsgRule.enemyPlayerInfo, msgdata = {int={playerId=_data.int["3"]}}}, netCallbackFunc)
				elseif sender:getTitleText() == BUTTON_TEXT_EXIT then
					-- UIManager.showToast("退出！~" .. BUTTON_TEXT_EXIT)
					if gradeId == 1 then
						UIManager.showToast("请先将盟主之位传于他人")
					else
						utils.showDialog("确定要退出联盟么？", function()
							UIManager.showLoading()
							_curCommand = BUTTON_TEXT_EXIT
							netSendPackage({header = StaticMsgRule.exitUnion, 
								msgdata = {int={instUnionMemberId=_data.int["1"],type=-2}}}, netCallbackFunc)
						end)
					end
				end
			end
		end
	end
	btn_left:addTouchEventListener(onButtonEvent)
	btn_middle:addTouchEventListener(onButtonEvent)
	btn_right:addTouchEventListener(onButtonEvent)
end

local function setDynamicListItem(_item, _data)
	local ui_timeLabel = _item:getChildByName("ui_timeLabel")
	local _times = utils.changeTimeFormat(_data.time)
	ui_timeLabel:setString(string.format("[%02d-%02d %02d:%02d]", _times[2], _times[3], _times[5], _times[6]))
	local ui_richText = _item:getChildByName("ui_richText")
	for key, obj in pairs(_data.richText) do
		ui_richText:pushBackElement(ccui.RichElementText:create(key, obj.color, 255, obj.text, ui_timeLabel:getFontName(), ui_timeLabel:getFontSize()))
	end
end

local function netCallback(data)--zy 弹劾成功回调
	local image_basemap = UIAllianceInfo.Widget:getChildByName("image_basemap")
	local image_title_di = image_basemap:getChildByName("image_title_di")
	local btn_info = image_title_di:getChildByName("btn_info")
	local btn_job = image_title_di:getChildByName("btn_job")
	local btn_lv = image_title_di:getChildByName("btn_lv")
	local image_info = image_basemap:getChildByName("image_info")
       utils.showSureDialog("盟主已被弹劾，恭喜你成为新任盟主", function()
            btn_info:loadTextures("ui/yh_btn02.png", "ui/yh_btn02.png")
			btn_job:loadTextures("ui/yh_btn02.png", "ui/yh_btn02.png")
            btn_job:setTitleColor(cc.c3b(69, 26, 0))
			btn_info:loadTextures("ui/yh_btn01.png", "ui/yh_btn02.png")
            btn_info:setTitleColor(cc.c3b(255, 255, 0))
			btn_lv:loadTextures("ui/yh_btn01.png", "ui/yh_btn02.png")
            btn_lv:setTitleColor(cc.c3b(255, 255, 0))
			image_info:setVisible(false)
			ui_scrollView:setVisible(true)
			cleanScrollView()
			UIManager.showLoading()
            --cclog("leaderUnionMemId---------------->=="..net.InstUnionMember.int["1"])
			netSendPackage({header = StaticMsgRule.unionMember, 
				  msgdata = {int={instUnionMemberId=net.InstUnionMember.int["1"]}}}, netCallbackFunc)
    end)
end
netCallbackFunc = function(msgData)
	local code = tonumber(msgData.header)
	if code == StaticMsgRule.unionDetail then
		local unionDetail = msgData.msgdata.message.unionDetail
		initAllianceInfo(unionDetail)
    elseif code == StaticMsgRule.isWishUnionAccCon then --zy弹劾 
        utils.showDialog("是否花费200元宝弹劾盟主？", function()
        	UIManager.showLoading()
			netSendPackage({header = StaticMsgRule.accuseLeader, 
				msgdata = {int={leaderUnionMemId=userData.leaderUnioId,accuseUnionMemId = userData.selfUnioId}}}, netCallback)
        end)
	elseif code == StaticMsgRule.unionMember then
		local unionMember = msgData.msgdata.message.unionMember
		local memberList = {}
		for key, obj in pairs(unionMember.message) do
			memberList[#memberList + 1] = obj
            if obj.int["3"] == net.InstPlayer.int["1"] then
                userData.selfUnioId = obj.int["1"]
            end
		end
		utils.quickSort(memberList, function(obj1, obj2)
			if obj1.int["4"] > obj2.int["4"] then
				return true
			elseif obj1.int["4"] == obj2.int["4"] then
				if obj1.int["5"] > obj2.int["5"] then
					return true
				end
			end
		end)
		layoutScrollView(memberList, setScrollViewItem)
	elseif code == StaticMsgRule.writeUnion then
		UIAllianceInfo.setup()
	elseif code == StaticMsgRule.exitUnion then
		if _curCommand == BUTTON_TEXT_EXIT then
			UIManager.showToast("退出成功！")
			UIMenu.onHomepage()
		elseif _curCommand == BUTTON_TEXT_KICKOUT then
			UIManager.showToast("踢出成功！")
			local image_basemap = UIAllianceInfo.Widget:getChildByName("image_basemap")
			local image_title_di = image_basemap:getChildByName("image_title_di")
			local btn_job = image_title_di:getChildByName("btn_job")
			btn_job:releaseUpEvent()
		end
	elseif code == StaticMsgRule.enemyPlayerInfo then
		pvp.loadGameData(msgData)
		UIManager.pushScene("ui_arena_check")
	elseif code == StaticMsgRule.appointUnion then
		local image_basemap = UIAllianceInfo.Widget:getChildByName("image_basemap")
		local image_title_di = image_basemap:getChildByName("image_title_di")
		local btn_job = image_title_di:getChildByName("btn_job")
		btn_job:releaseUpEvent()
	elseif code == StaticMsgRule.dissolveUnion then
		UIManager.showToast("解散成功！")
		UIMenu.onHomepage()
	elseif code == StaticMsgRule.unionDynamic then
		local _messageList = {}
		if msgData.msgdata.message.unionDynamic and msgData.msgdata.message.unionDynamic.message then
			for key, obj in pairs(msgData.msgdata.message.unionDynamic.message) do
				local _msgContent = ""
				if obj.int["4"] == 0 then --添加
					_msgContent = "<color=255,217,0>恭喜</color><color=0,255,0>"..obj.string["3"].."</color><color=255,217,0>加入联盟，成为我们的一员。让我们携手打天下！</color>"
				elseif obj.int["4"] == -1 then --踢出
					_msgContent = "<color=255,217,0>成员</color><color=0,255,0>"..obj.string["3"].."</color><color=255,217,0>玩忽职守被踢出联盟，联盟生涯相聚不易，且行且珍惜！</color>"
				elseif obj.int["4"] == -2 then --退出
					_msgContent = "<color=255,217,0>成员</color><color=0,255,0>"..obj.string["3"].."</color><color=255,217,0>离开联盟，希望他找到更好的归宿，我们仍然欢迎他再次回归！</color>"
				else
					local unionBuildData = DictUnionBuild[tostring(obj.int["4"])]
					_msgContent = "<color=255,217,0>成员</color><color=0,255,0>"..obj.string["3"].."</color><color=255,217,0>"..unionBuildData.description.."，增加</color><color=87,172,255>"..unionBuildData.plan.."点</color><color=255,217,0>进度，增加</color><color=87,172,255>"..unionBuildData.exp.."点</color><color=255,217,0>联盟经验</color>"
				end
				_messageList[#_messageList + 1] = { time = obj.string["6"], richText = utils.richTextFormat(_msgContent) }
			end
			utils.quickSort(_messageList, function(obj1, obj2) if utils.GetTimeByDate(obj1.time) < utils.GetTimeByDate(obj2.time) then return true end end)
		end
		layoutScrollView(_messageList, setDynamicListItem, getDynamicItem())
	end
end

function UIAllianceInfo.init()
	local image_basemap = UIAllianceInfo.Widget:getChildByName("image_basemap")
	local image_title_di = image_basemap:getChildByName("image_title_di")
	local btn_back = image_title_di:getChildByName("btn_back")
	local btn_info = image_title_di:getChildByName("btn_info")
	local btn_job = image_title_di:getChildByName("btn_job")
	local btn_lv = image_title_di:getChildByName("btn_lv")

	local image_info = image_basemap:getChildByName("image_info")

	ui_scrollView = image_basemap:getChildByName("view_member")
	ui_svItem = ui_scrollView:getChildByName("image_di_menber"):clone()
    ui_svItem:getChildByName("image_di_info"):getChildByName("text_name"):setTextColor(cc.c3b(0, 128, 0))

	btn_back:setPressedActionEnabled(true)

	local function onButtonEvent(sender, eventType)
		if eventType == ccui.TouchEventType.ended then
			if sender == btn_back then
				UIManager.hideWidget("ui_team_info")
				UIManager.showWidget("ui_alliance")
			elseif sender == btn_info then
				btn_info:loadTextures("ui/yh_btn02.png", "ui/yh_btn02.png")
                btn_info:setTitleColor(cc.c3b(69, 26, 0))
				btn_job:loadTextures("ui/yh_btn01.png", "ui/yh_btn02.png")
                btn_job:setTitleColor(cc.c3b(255, 255, 0))
				btn_lv:loadTextures("ui/yh_btn01.png", "ui/yh_btn02.png")
                btn_lv:setTitleColor(cc.c3b(255, 255, 0))
				ui_scrollView:setVisible(false)
				cleanScrollView()
				image_info:setVisible(true)
				UIManager.showLoading()
				netSendPackage({header = StaticMsgRule.unionDetail, 
					msgdata = {int={instUnionMemberId=net.InstUnionMember.int["1"]}}}, netCallbackFunc)
			elseif sender == btn_job then
				btn_job:loadTextures("ui/yh_btn02.png", "ui/yh_btn02.png")
                btn_job:setTitleColor(cc.c3b(69, 26, 0))
				btn_info:loadTextures("ui/yh_btn01.png", "ui/yh_btn02.png")
                btn_info:setTitleColor(cc.c3b(255, 255, 0))
				btn_lv:loadTextures("ui/yh_btn01.png", "ui/yh_btn02.png")
                btn_lv:setTitleColor(cc.c3b(255, 255, 0))
				image_info:setVisible(false)
				ui_scrollView:setVisible(true)
				cleanScrollView()
				UIManager.showLoading()
				netSendPackage({header = StaticMsgRule.unionMember, 
				msgdata = {int={instUnionMemberId=net.InstUnionMember.int["1"]}}}, netCallbackFunc)
			elseif sender == btn_lv then
				btn_lv:loadTextures("ui/yh_btn02.png", "ui/yh_btn02.png")
                btn_lv:setTitleColor(cc.c3b(69, 26, 0))
				btn_info:loadTextures("ui/yh_btn01.png", "ui/yh_btn02.png")
                btn_info:setTitleColor(cc.c3b(255, 255, 0))
				btn_job:loadTextures("ui/yh_btn01.png", "ui/yh_btn02.png")
                btn_job:setTitleColor(cc.c3b(255, 255, 0))
				ui_scrollView:setVisible(true)
				cleanScrollView()
				image_info:setVisible(false)
				UIManager.showLoading()
				netSendPackage({header = StaticMsgRule.unionDynamic, 
				msgdata = {int={instUnionMemberId=net.InstUnionMember.int["1"],type=2}}}, netCallbackFunc)
			end
		end
	end

	btn_back:addTouchEventListener(onButtonEvent)
	btn_info:addTouchEventListener(onButtonEvent)
	btn_job:addTouchEventListener(onButtonEvent)
	btn_lv:addTouchEventListener(onButtonEvent)
end

function UIAllianceInfo.setup()
	local image_basemap = UIAllianceInfo.Widget:getChildByName("image_basemap")
	local image_title_di = image_basemap:getChildByName("image_title_di")
	local ui_playerName = ccui.Helper:seekNodeByName(image_title_di, "text_name")
	ui_playerName:setString(net.InstPlayer.string["3"])
	local ui_palyerLevel = ccui.Helper:seekNodeByName(image_title_di, "text_lv")
	ui_palyerLevel:setString("LV "..net.InstPlayer.int["4"])
	local ui_allianceOffer = ccui.Helper:seekNodeByName(image_title_di, "text_alliance_number")
	ui_allianceOffer:setString(tostring(net.InstUnionMember.int["5"]))
	local btn_info = image_title_di:getChildByName("btn_info")
	btn_info:releaseUpEvent()
end

function UIAllianceInfo.refreshMemberCount(_params)
    _currentMemberCount = _params.currentCount
	local image_info = UIAllianceInfo.Widget:getChildByName("image_basemap"):getChildByName("image_info")
	local ui_allianceBg = image_info:getChildByName("image_di_alliance")
	local ui_allianceMember = ccui.Helper:seekNodeByName(ui_allianceBg, "text_member")
	ui_allianceMember:setString(string.format("成员：%d/%d", _params.currentCount, _params.totalCount))
end

function UIAllianceInfo.free()
	userData = nil
	_curCommand = nil
    _currentMemberCount = 0
	cleanScrollView()
end

function UIAllianceInfo.show(_tableParams)
	userData = _tableParams
	UIManager.showWidget("ui_alliance_info")
end