UIEquipmentAdvance={}

local _equipInstId = nil
local MAX_STAR_LEVEL = 5 --最大星级
local useStoneNums = 0 --使用淬炼石个数
local _curTime = 0
local _schedulerId = nil
local curPro = 0--当前的淬炼度
local _maxStoneValue = 0
local maxExp = 0
local STAR_ANIM_TAG = -11111
local _equipIconPath = nil
local _curEquipStarLevel = nil

local function upgrade(value)  
    maxExp = _maxStoneValue
    if curPro >= maxExp then
		return false
	end
	curPro = curPro + value
    cclog("cuPro "..curPro)
	--改变进度条
    local bar_cuilian = ccui.Helper:seekNodeByName(UIEquipmentAdvance.Widget,"bar_cuilian")
    bar_cuilian:setPercent(curPro * 100 / maxExp )
    local text_cuilian = ccui.Helper:seekNodeByName(UIEquipmentAdvance.Widget,"text_cuilian")--淬炼值
    text_cuilian:setString(curPro .. "/" .. maxExp)
	return true
end

local function refreshStoneNum( index , value )
    local image_frame_stone , text_number = nil , nil
    if index == 1 then
        image_frame_stone = ccui.Helper:seekNodeByName(UIEquipmentAdvance.Widget, "image_frame_stone1")
    elseif index == 2 then
        image_frame_stone = ccui.Helper:seekNodeByName(UIEquipmentAdvance.Widget, "image_frame_stone2")
    elseif index == 3 then
        image_frame_stone = ccui.Helper:seekNodeByName(UIEquipmentAdvance.Widget, "image_frame_stone3")
    elseif index == 4 then
        image_frame_stone = ccui.Helper:seekNodeByName(UIEquipmentAdvance.Widget, "image_frame_stone4")
    end
    text_number = image_frame_stone:getChildByName("text_number")
    local count = 0
    if index == 1 then
        count = utils.getThingCount(StaticThing.thing121)
    elseif index == 2 then
        count = utils.getThingCount(StaticThing.thing122)
    elseif index == 3 then
        count = utils.getThingCount(StaticThing.thing123)
    elseif index == 4 then
        count = utils.getThingCount(StaticThing.thing124)
    end
    text_number:setString( "x"..( count - value ) )
end

local function netCallback( pack ) 
    if curPro >= _maxStoneValue then
        local animCallbackFunc = function()
            if UIEquipmentAdvance.Widget:getChildByTag(STAR_ANIM_TAG) then
                UIEquipmentAdvance.Widget:getChildByTag(STAR_ANIM_TAG):removeFromParent()
            end
            UIEquipmentAdvance.setup()
	        UIManager.flushWidget(UIEquipmentInfo)
            UIManager.flushWidget(UIEquipmentNew)
	        UIManager.flushWidget(UILineup)
	        UIManager.flushWidget(UIBagEquipment)
        end
        local _animId = 41
	    if _animId then
            local animation = ActionManager.getUIAnimation(_animId)
            animation:getBone("Layer1"):addDisplay(ccs.Skin:create(_equipIconPath), 0)
            animation:setPosition(cc.p(UIManager.screenSize.width / 2, UIManager.screenSize.height / 2 +70))
	        UIManager.uiLayer:addChild(animation, 1000)
            local function onFrameEvent(bone, evt, originFrameIndex, currentFrameIndex)
		        if evt == "starAnim" then
                    local _curStarPosition = nil
                    local image_basemap = UIEquipmentAdvance.Widget:getChildByName("image_basemap")
	                local panel = ccui.Helper:seekNodeByName(image_basemap, "image_di_r")
	                local ui_equipQualityBg = panel:getChildByName("image_di_name")

                    cclog("_curEquipStarLevel :".._curEquipStarLevel)
                    for i = 1, MAX_STAR_LEVEL do
                        if _curEquipStarLevel + 1 == i then
                            cclog("_curEquipStarLevel ::".._curEquipStarLevel)
		                    local ui_starImg = ui_equipQualityBg:getChildByName("image_star" .. i)
                            local point = ui_starImg:getParent():convertToWorldSpace(cc.p(ui_starImg:getPositionX(), ui_starImg:getPositionY()))
                            _curStarPosition = cc.p(point.x, point.y)
                            break
                        end
                    end
                    _curEquipStarLevel = 0
                    local animStar = ccui.ImageView:create("ui/star01.png")
                    animStar:setPosition(cc.p(UIManager.screenSize.width / 2, UIManager.screenSize.height * 0.75))
                    animStar:setScale(8)
                    UIEquipmentAdvance.Widget:addChild(animStar, 1000, STAR_ANIM_TAG)
                    animStar:runAction(cc.Sequence:create(cc.Spawn:create(cc.ScaleTo:create(0.15, 1), cc.MoveTo:create(0.15, _curStarPosition)), cc.CallFunc:create(animCallbackFunc)))
                end
            end
            animation:getAnimation():setFrameEventCallFunc(onFrameEvent)
        end
    else
        UIManager.showToast("淬炼成功！")
        UIEquipmentAdvance.setup()
    end
end

local function netSendData( _thingId )
    cclog("  ---> ".._equipInstId .."  ".._thingId.."  "..useStoneNums)
    local thingInstId = nil
    for key , value in pairs( net.InstPlayerThing ) do
        if value.int["3"] == _thingId then
            thingInstId = value.int["1"]
            break
        end
    end
    if thingInstId then
        cclog("道具实例Id:"..thingInstId)
        cclog("cuPro -------------------"..curPro.."   maxExp:".._maxStoneValue)
        local tempIsAc = 0
        if curPro >= _maxStoneValue then
            tempIsAc = 1 
        else
            tempIsAc = 0
        end
        cclog("tempIsAc:"..tempIsAc)
	    local sendData = {
		    header = StaticMsgRule.useAdvanceThing,
		    msgdata = {
			    int = {
				    instPlayerEquipId = _equipInstId,
				    thingid = thingInstId ,
				    count = useStoneNums ,
                    isAc = tempIsAc 
			    }
		    }
	    }
	    UIManager.showLoading()
	    netSendPackage(sendData, netCallback)
    else
        cclog("道具物品有问题")
    end
end 		

function UIEquipmentAdvance.init() 
	local image_basemap = UIEquipmentAdvance.Widget:getChildByName("image_basemap")
	local panel = ccui.Helper:seekNodeByName(image_basemap, "image_di_r")
    ui_equipIcon = ccui.Helper:seekNodeByName(UIEquipmentAdvance.Widget, "image_equipment")
    local ui_equipQualityBg = panel:getChildByName("image_di_name")
	local ui_equipName = ui_equipQualityBg:getChildByName("text_name")
	local image_frame_stone1 = ccui.Helper:seekNodeByName(UIEquipmentAdvance.Widget, "image_frame_stone1")
	ui_cuilianIcon1 = image_frame_stone1:getChildByName("image_stone")
    local image_frame_stone2 = ccui.Helper:seekNodeByName(UIEquipmentAdvance.Widget, "image_frame_stone2")
	ui_cuilianIcon2 = image_frame_stone2:getChildByName("image_stone")
    local image_frame_stone3 = ccui.Helper:seekNodeByName(UIEquipmentAdvance.Widget, "image_frame_stone3")
	ui_cuilianIcon3 = image_frame_stone3:getChildByName("image_stone")
    local image_frame_stone4 = ccui.Helper:seekNodeByName(UIEquipmentAdvance.Widget, "image_frame_stone4")
	ui_cuilianIcon4 = image_frame_stone4:getChildByName("image_stone")
    local btn_close = ccui.Helper:seekNodeByName( UIEquipmentAdvance.Widget , "btn_close" )

    local btn_preview = ccui.Helper:seekNodeByName(UIEquipmentAdvance.Widget , "btn_preview")
    btn_preview:setVisible(false)
    local function onEvent( sender , eventType )         
        if eventType == ccui.TouchEventType.ended then
            if sender == btn_close then              
                UIManager.popScene()            
            end          
        end
    end
    btn_close:setPressedActionEnabled( true )
    btn_close:addTouchEventListener( onEvent )

    local pillNum = 0  --物品数量
    local perNum = 0
    local curChooseImage = 0      		
	local function addExp(dt)
		if os.time() - _curTime >= 1 and pillNum > 0 then							
			--num:setString(tostring(pillNum - _usePillNums)) --道具数量减少
			if upgrade(perNum) then
                useStoneNums = useStoneNums + 1
                refreshStoneNum( curChooseImage , useStoneNums )
				if useStoneNums == pillNum then
					if _schedulerId then
						cc.Director:getInstance():getScheduler():unscheduleScriptEntry(_schedulerId)
						_schedulerId = nil
					end
					UIManager.showToast("道具已用完！")
				end
			else
				if _schedulerId then
					cc.Director:getInstance():getScheduler():unscheduleScriptEntry(_schedulerId)
					_schedulerId = nil
				end
				UIManager.showToast("淬炼值已满！")
			end
		end
	end

	local function itemEvent(sender, eventType)  
        local isCanUpgrade = true    
        local dictThingData1 = DictThing[tostring(StaticThing.thing121)]
        local dictThingData2 = DictThing[tostring(StaticThing.thing122)]
        local dictThingData3 = DictThing[tostring(StaticThing.thing123)]
        local dictThingData4 = DictThing[tostring(StaticThing.thing124)] 

        local instEquipData = net.InstPlayerEquip[tostring(_equipInstId)]
	    local equipAdvanceId = instEquipData.int["8"] --装备进阶字典ID
	    local dictEquipAdvanceData = DictEquipAdvance[tostring(equipAdvanceId)] --装备进阶字典表
        local isMax = false
        if dictEquipAdvanceData and dictEquipAdvanceData.equipQualityId == StaticEquip_Quality.golden and dictEquipAdvanceData.starLevel == MAX_STAR_LEVEL then  
            isMax = true
        end
		if eventType == ccui.TouchEventType.began then                      
                    if sender == image_frame_stone1 then  
                        pillNum = utils.getThingCount(StaticThing.thing121)
                        perNum = dictThingData1.level
                        curChooseImage = 1                
                    elseif sender == image_frame_stone2 then 
                        pillNum = utils.getThingCount(StaticThing.thing122)
                        perNum = dictThingData2.level
                        curChooseImage = 2
                    elseif sender == image_frame_stone3 then
                        pillNum = utils.getThingCount(StaticThing.thing123)
                        perNum = dictThingData3.level
                        curChooseImage = 3
                    elseif sender == image_frame_stone4 then 
                        pillNum = utils.getThingCount(StaticThing.thing124)
                        perNum = dictThingData4.level
                        curChooseImage = 4
                    end
            if pillNum <= 0 then
            elseif isMax then

			elseif isCanUpgrade then
				useStoneNums = 0
				_curTime = os.time()
				if _schedulerId then
					cc.Director:getInstance():getScheduler():unscheduleScriptEntry(_schedulerId)
					_schedulerId = nil
				end
				_schedulerId = cc.Director:getInstance():getScheduler():scheduleScriptFunc(addExp, 0, false)
			else
				UIManager.showToast("道具已满足进阶")
			end
		elseif eventType == ccui.TouchEventType.ended or eventType == ccui.TouchEventType.canceled then
            if pillNum <= 0 then
                if curChooseImage == 1 then
                    utils.storyDropOutDialog(dictThingData1 ,3 )
                elseif curChooseImage == 2 then
                    utils.storyDropOutDialog(dictThingData2 ,4 )
                elseif curChooseImage == 3 then
                    utils.storyDropOutDialog(dictThingData3 ,5)
                elseif curChooseImage == 4 then
                    UIManager.showToast("暂无产出！")
                end
            elseif isMax then
                UIManager.showToast("已进阶到顶级")
			elseif isCanUpgrade then
				if _schedulerId then
					cc.Director:getInstance():getScheduler():unscheduleScriptEntry(_schedulerId)
					_schedulerId = nil
				end
				if os.time() - _curTime > 0 then

				else
					_curTime = 0
					addExp()
				end
                local thingId = StaticThing.thing121
                if curChooseImage == 2 then
                    thingId = StaticThing.thing122
                elseif curChooseImage == 3 then
                    thingId = StaticThing.thing123
                elseif curChooseImage == 4 then
                    thingId = StaticThing.thing124
                end
                netSendData( thingId )           
			end
		end
	end
    image_frame_stone1:addTouchEventListener( itemEvent )
    image_frame_stone2:addTouchEventListener( itemEvent )
    image_frame_stone3:addTouchEventListener( itemEvent )
    image_frame_stone4:addTouchEventListener( itemEvent )
end

function UIEquipmentAdvance.setup()  
    local instEquipData = net.InstPlayerEquip[tostring(_equipInstId)]
	local dictEquipId = instEquipData.int["4"] --装备字典ID
    local equipTypeId = instEquipData.int["3"] --装备类型ID
	local equipLv = instEquipData.int["5"] --装备等级
	local equipAdvanceId = instEquipData.int["8"] --装备进阶字典ID
    cclog("equipAdvanceId =============="..equipAdvanceId)
	local dictEquipData = DictEquipment[tostring(dictEquipId)] --装备字典表
	local dictEquipAdvanceData = DictEquipAdvance[tostring(equipAdvanceId)] --装备进阶字典表
 
    local dictThingData1 = DictThing[tostring(StaticThing.thing121)]
    local dictThingData2 = DictThing[tostring(StaticThing.thing122)]
    local dictThingData3 = DictThing[tostring(StaticThing.thing123)]
    local dictThingData4 = DictThing[tostring(StaticThing.thing124)]
	local image_basemap = UIEquipmentAdvance.Widget:getChildByName("image_basemap")
	local panel = ccui.Helper:seekNodeByName(image_basemap, "image_di_r")
    local ui_equipQualityBg = panel:getChildByName("image_di_name")
	local ui_equipName = ui_equipQualityBg:getChildByName("text_name")   
	local image_arrow1 = panel:getChildByName("image_arrow1")
	local image_arrow2 = panel:getChildByName("image_arrow2")    
    local image_frame_stone1 = ccui.Helper:seekNodeByName(UIEquipmentAdvance.Widget, "image_frame_stone1")
	ui_cuilianNum1 = image_frame_stone1:getChildByName("text_add")
    text_number1 = image_frame_stone1:getChildByName("text_number")
    local image_frame_stone2 = ccui.Helper:seekNodeByName(UIEquipmentAdvance.Widget, "image_frame_stone2")
	ui_cuilianNum2 = image_frame_stone2:getChildByName("text_add")
    text_number2 = image_frame_stone2:getChildByName("text_number")
    local image_frame_stone3 = ccui.Helper:seekNodeByName(UIEquipmentAdvance.Widget, "image_frame_stone3")
	ui_cuilianNum3 = image_frame_stone3:getChildByName("text_add")
    text_number3 = image_frame_stone3:getChildByName("text_number")
    local image_frame_stone4 = ccui.Helper:seekNodeByName(UIEquipmentAdvance.Widget, "image_frame_stone4")
	ui_cuilianNum4 = image_frame_stone4:getChildByName("text_add")
    text_number4 = image_frame_stone4:getChildByName("text_number")  

	ui_cuilianIcon1:loadTexture("image/" .. DictUI[tostring(dictThingData1.smallUiId)].fileName)
    ui_cuilianIcon2:loadTexture("image/" .. DictUI[tostring(dictThingData2.smallUiId)].fileName)
    ui_cuilianIcon3:loadTexture("image/" .. DictUI[tostring(dictThingData3.smallUiId)].fileName)
    ui_cuilianIcon4:loadTexture("image/" .. DictUI[tostring(dictThingData4.smallUiId)].fileName)   
    _equipIconPath = "image/" .. DictUI[tostring(dictEquipData.bigUiId)].fileName
	ui_equipIcon:loadTexture( _equipIconPath )
	ui_equipName:setString(dictEquipData.name)
	ui_equipQualityBg:loadTexture(utils.getQualityImage(dp.Quality.equip, dictEquipAdvanceData.equipQualityId, dp.QualityImageType.middle, true))
    local curStar = dictEquipAdvanceData.starLevel
    if dictEquipAdvanceData.equipQualityId == StaticEquip_Quality.purple and dictEquipAdvanceData.starLevel == MAX_STAR_LEVEL then
        curStar = 0
    end  
	for key , value in pairs( DictEquipAdvancered ) do       
		if value.equipId == tonumber(dictEquipId) and value.starLevel == curStar then
            cclog("condition : "..value.contions)
			_maxStoneValue = tonumber(value.contions)
			break
		end
	end
    _curEquipStarLevel = curStar
    cclog("_curEquipStarLevel ::".._curEquipStarLevel.."  "..curStar)
    curPro = 0
    cclog("_equipInstId : ".._equipInstId)
    if net.InstPlayerRedEquip then
        for key ,value in pairs( net.InstPlayerRedEquip ) do
          -- cclog("curPro111 : ------------------- "..value.int["4"].."   "..value.int["3"])
            if value.int["3"] == _equipInstId then
                curPro = value.int["4"]
                cclog("总吃过curPro : ------------------- "..curPro)
                break
            end
        end
        if dictEquipAdvanceData.equipQualityId == StaticEquip_Quality.golden then
            for key , value in pairs( DictEquipAdvancered ) do       
		        if value.equipId == tonumber(dictEquipId) and value.starLevel == 0 then           
			        curPro = curPro - tonumber(value.contions)
                    break
		        end
	        end
        end
        for key , value in pairs( DictEquipAdvancered ) do       
		    if value.equipId == tonumber(dictEquipId) and value.starLevel < curStar then           
			    curPro = curPro - tonumber(value.contions)
                cclog("实际curPro : ------------------- "..curPro)
		    end
	    end
    end
	cclog("_maxStoneValue : ".._maxStoneValue .. " curPro:"..curPro)

	local bar_cuilian = ccui.Helper:seekNodeByName(UIEquipmentAdvance.Widget,"bar_cuilian")
	bar_cuilian:setPercent(curPro * 100 / _maxStoneValue)
	local text_cuilian = ccui.Helper:seekNodeByName(UIEquipmentAdvance.Widget,"text_cuilian")
	text_cuilian:setString( curPro.."/".._maxStoneValue )

	for i = 1, MAX_STAR_LEVEL do
		local ui_starImg = ui_equipQualityBg:getChildByName("image_star" .. i)
		if equipAdvanceId ~= 0 and dictEquipAdvanceData.starLevel >= i then
			ui_starImg:loadTexture("ui/star01.png")
		else
			ui_starImg:loadTexture("ui/star02.png")
		end
		if i > 3 and dictEquipData.equipQualityId == StaticEquip_Quality.blue then
			ui_starImg:setVisible(false)
		else
			ui_starImg:setVisible(true)
		end
	end

	--显示属性
    local equipAdvanceData = {}
	for key, obj in pairs(DictEquipAdvance) do
		if obj.equipQualityId == StaticEquip_Quality.golden then
			if equipTypeId == obj.equipTypeId and dictEquipData.equipQualityId == obj.equipQualityId - 1 then
				equipAdvanceData[#equipAdvanceData + 1] = obj
			end
		end
		if equipTypeId == obj.equipTypeId and dictEquipData.equipQualityId == obj.equipQualityId then
			equipAdvanceData[#equipAdvanceData + 1] = obj
		end
	end
	utils.quickSort(equipAdvanceData,function(obj1, obj2) if obj1.id > obj2.id then return true end end)
	if equipAdvanceId == 0 and (not dictEquipAdvanceData) then
		dictEquipAdvanceData = equipAdvanceData[1]
	end
    local nextEquipAdvanceData = nil
    local attAddValue = 0
    for key, obj in pairs(equipAdvanceData) do
	    if dictEquipAdvanceData.id == obj.id then
		    nextEquipAdvanceData = (equipAdvanceId == 0) and obj or equipAdvanceData[key + 1]
	    end
	    if equipAdvanceId >= obj.id then
		    attAddValue = attAddValue + obj.propAndAdd
	    else
		    break
	    end
    end
    local equipPropData = {}
    local propData = utils.stringSplit(dictEquipData.propAndAdd, ";")
    for key, obj in pairs(propData) do
	    equipPropData[key] = utils.stringSplit(obj, "_") --[1]:fightPropId, [2]:initValue, [3]:addValue
    end
    if #equipPropData > 1 then
	    image_arrow2:setVisible(true)
    else
	    image_arrow2:setVisible(false)
    end
    for key, obj in pairs(equipPropData) do
	    local _item = panel:getChildByName("image_arrow" .. key)
	    local fightPropId, initValue, addValue = tonumber(obj[1]), tonumber(obj[2]), tonumber(obj[3])
	    _item:getChildByName("text_title"):setString(DictFightProp[tostring(fightPropId)].name .. "：")
	    _item:getChildByName("text_blood_before"):setString(addValue + attAddValue)
	    if nextEquipAdvanceData then
		    _item:getChildByName("text_blood_after"):setString(addValue + attAddValue + nextEquipAdvanceData.propAndAdd)
	    else
		    _item:getChildByName("text_blood_after"):setString("暂未开放")
	    end
    end

    ui_cuilianNum1:setString("淬炼值+"..dictThingData1.level)
    ui_cuilianNum2:setString("淬炼值+"..dictThingData2.level)
    ui_cuilianNum3:setString("淬炼值+"..dictThingData3.level)
    ui_cuilianNum4:setString("淬炼值+"..dictThingData4.level)
    refreshStoneNum( 1 , 0 )
    refreshStoneNum( 2 , 0 )
    refreshStoneNum( 3 , 0 )
    refreshStoneNum( 4 , 0 )
end
function UIEquipmentAdvance.free()
    _equipInstId = nil
    useStoneNums = nil
    _schedulerId = nil
    _equipIconPath = nil
    _curEquipStarLevel = nil
end
function UIEquipmentAdvance.setEquipInstId(equipInstId)
	_equipInstId = equipInstId
end