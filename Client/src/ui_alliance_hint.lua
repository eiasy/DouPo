UIAllianceHint = {}

local userData = nil

local function netCallbackFunc(msgData)
	UIManager.popScene()
	if userData.callbackFunc then
		userData.callbackFunc(msgData)
	end
end

function UIAllianceHint.init()
end

function UIAllianceHint.setup()
	local panel = UIAllianceHint.Widget:getChildByName("image_hint")
	local ui_titleText = panel:getChildByName("text_title")
	local ui_textBg = panel:getChildByName("image_hint")
	local ui_textLabel = ui_textBg:getChildByName("text_hint")
	local btn_cancel = panel:getChildByName("btn_cancel")
	local btn_ok = panel:getChildByName("btn_ok")
	btn_cancel:setPressedActionEnabled(true)
	btn_ok:setPressedActionEnabled(true)
	local function onButtonEvent(sender, eventType)
		if eventType == ccui.TouchEventType.ended then
			if sender == btn_cancel then
				UIManager.popScene()
			elseif sender == btn_ok then
				UIManager.showLoading()
				netSendPackage({header = StaticMsgRule.writeUnion, 
					msgdata = {
						int = {instUnionMemberId = net.InstUnionMember.int["1"], type = userData.title=="公告" and 1 or 2}, --1-公告 2-宣言
						string = {detail = ui_textLabel:getString()}
					}}, netCallbackFunc)
			end
		end
	end
	btn_cancel:addTouchEventListener(onButtonEvent)
	btn_ok:addTouchEventListener(onButtonEvent)
	ui_titleText:setString("联盟" .. userData.title)

	local ui_editBox = cc.EditBox:create(ui_textLabel:getContentSize(), cc.Scale9Sprite:create())
  ui_editBox:setAnchorPoint(ui_textLabel:getAnchorPoint())
  ui_editBox:setPosition(ui_textLabel:getPosition())
  ui_editBox:setFont(ui_textLabel:getFontName(), ui_textLabel:getFontSize())
  ui_editBox:setFontColor(ui_textLabel:getColor())
  ui_editBox:setPlaceHolder("联盟" .. userData.title)
  ui_editBox:setMaxLength(20)
  ui_textLabel:getParent():addChild(ui_editBox, -1)
  if userData.desc then
  	ui_editBox:setText(userData.desc)
  	ui_textLabel:setString(userData.desc)
	end
  ui_editBox:registerScriptEditBoxHandler(function(eventType, sender)
		if eventType == "return" then
			ui_textLabel:setString(ui_editBox:getText())
		end
	end)
end

function UIAllianceHint.free()
	userData = nil
end

function UIAllianceHint.show(_tableParams)
	userData = _tableParams
	UIManager.pushScene("ui_alliance_hint")
end