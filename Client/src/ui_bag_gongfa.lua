UIBagGongFa ={}
local scrollView = nil
local gongFaItem = nil
local expandNum = 0 --背包扩展的总数
local expandPrice = 0
local bagFlag = nil -- 1 功法 2 法宝
local function compareMagic(value1,value2)
    if DictMagic[tostring(value1.int["3"])].value1 == "3" and DictMagic[tostring(value2.int["3"])].value1 ~= "3" then 
      return true
    elseif DictMagic[tostring(value1.int["3"])].value1 ~= "3" and DictMagic[tostring(value2.int["3"])].value1 == "3" then 
      return false
    else 
      if DictMagic[tostring(value1.int["3"])].magicQualityId > DictMagic[tostring(value2.int["3"])].magicQualityId then 
          return true
      else
          return false
      end
    end
end
local function netCallbackFunc(pack)
  if tonumber(pack.header) == StaticMsgRule.bagExpand then
    UIManager.showToast("扩充成功")
    UIManager.flushWidget(UIBagGongFa)
    UIManager.flushWidget(UITeamInfo)
  end
end

local function ExpandCallBack()  
    utils.sendExpandData(bagFlag == 1 and StaticBag_Type.kungFu or StaticBag_Type.magic,netCallbackFunc)
end

local function selectedBtnChange(flag)
  local btn_gongfa = ccui.Helper:seekNodeByName(UIBagGongFa.Widget, "btn_gongfa")
  local btn_treasured = ccui.Helper:seekNodeByName(UIBagGongFa.Widget, "btn_treasured")
  if flag == 1 then 
        btn_gongfa:loadTextureNormal("ui/yh_btn02.png")
        btn_gongfa:getChildByName("text_gongfa"):setTextColor(cc.c4b(51,25,4,255))
        btn_treasured:loadTextureNormal("ui/yh_btn01.png")
        btn_treasured:getChildByName("text_gongfa"):setTextColor(cc.c4b(255,255,255,255))
    elseif  flag ==  2 then
        btn_gongfa:loadTextureNormal("ui/yh_btn01.png")
        btn_gongfa:getChildByName("text_gongfa"):setTextColor(cc.c4b(255,255,255,255))
        btn_treasured:loadTextureNormal("ui/yh_btn02.png")
        btn_treasured:getChildByName("text_gongfa"):setTextColor(cc.c4b(51,25,4,255))
    end
end

local function setScrollViewItem(flag,_Item, _obj)
    local value = {}
    local ui_property ={}
    local ui_frame = ccui.Helper:seekNodeByName(_Item, "image_frame_equipment")
    local ui_MagicIcon = ui_frame:getChildByName("image_equipment")
    local ui_MagicName = ccui.Helper:seekNodeByName(_Item, "text_name_equipment")
    local ui_MagicType = ccui.Helper:seekNodeByName(_Item, "text_gongfa_lv")
    local ui_MagicLevel = _Item:getChildByName("text_lv")
    local ui_text_equipment_card = ccui.Helper:seekNodeByName(_Item, "text_equipment_card")
    local ui_qualityLevel = ccui.Helper:seekNodeByName(_Item, "label_pz")
    local btn_intensify = _Item:getChildByName("btn_intensify")
    ui_property[2] =  ccui.Helper:seekNodeByName(_Item,"text_limit")
    ui_property[3] =  ccui.Helper:seekNodeByName(_Item,"text_gongfa_number")
    ui_property[1] =  ccui.Helper:seekNodeByName(_Item,"text_laterality")
    local instPlayerCardId = _obj.int["8"] --是否被使用  0-未使用 1-使用
    if instPlayerCardId == 0 then
        ui_text_equipment_card:setVisible(false)
    else
        local cardId = net.InstPlayerCard[tostring(instPlayerCardId)].int["3"]
        local cardName = DictCard[tostring(cardId)].name
        ui_text_equipment_card:setVisible(true)
        ui_text_equipment_card:setString("装备于" ..cardName)
    end
    local dictId = _obj.int["3"] --功法或法宝id
    local dicData = DictMagic[tostring(dictId)] --功法字典表
    local dictUiData = DictUI[tostring(dicData.smallUiId)] --资源字典表
    local Level = DictMagicLevel[tostring(_obj.int["6"])].level
    local qualityValue = _obj.int["5"]
    local qualityLevel = dicData.grade
    value[1] = dicData.value1 
    value[2] = dicData.value2 
    value[3] = dicData.value3 
    
    local borderImage = utils.getQualityImage(dp.Quality.gongFa, qualityValue, dp.QualityImageType.small)
    utils.changeNameColor(ui_MagicName,qualityValue,dp.Quality.gongFa)
    ui_frame:loadTexture(borderImage)
    ui_MagicIcon:loadTexture("image/" .. dictUiData.fileName)
    ui_MagicName:setString(dicData.name)
    ui_MagicType:setString(DictMagicQuality[tostring(qualityValue)].name)
    ui_MagicLevel:setString(string.format("%d级",Level))
    ui_qualityLevel:setString(qualityLevel)
    for i=1,3 do 
      if value[i] ~= "" then
        local dictValue = utils.stringSplit(value[i], "_")
        if tonumber(dictValue[1]) == 3 then
          ui_property[i]:setString(string.format("%s+%d%s","经验",dicData.exp,""))
        else
          local dictFightPropId = dictValue[2]
          local value = dictValue[3]+dictValue[4]*(Level-1)
          local name = DictFightProp[tostring(dictFightPropId)].name
          ui_property[i]:setString(string.format("%s+%d%s",name,value,tonumber(dictValue[1]) == 1 and "%" or ""))
        end
      else
        ui_property[i]:setVisible(false)
      end
    end
    btn_intensify:setPressedActionEnabled(true)
    local function btnTouchEvent(sender, eventType)
      if eventType == ccui.TouchEventType.ended then
          if sender == btn_intensify then 
            UIGongfaIntensify.setInstMagicId(_obj.int["1"])
            UIManager.pushScene("ui_gongfa_intensify")
          elseif sender == ui_MagicIcon then 
            if value[1] ~= "3" then 
              UIGongfaInfo.setInstMagicId(_obj.int["1"], true)
              UIManager.pushScene("ui_gongfa_info")
            end
          end
          
      end
    end
    ui_MagicIcon:setEnabled(true)
    ui_MagicIcon:setTouchEnabled(true)
    ui_MagicIcon:addTouchEventListener(btnTouchEvent)
    btn_intensify:addTouchEventListener(btnTouchEvent)
    if value[1] == "3" then 
      btn_intensify:setVisible(false)
    else 
      btn_intensify:setVisible(true)
    end
end


function UIBagGongFa.init()
    local btn_gongfa = ccui.Helper:seekNodeByName(UIBagGongFa.Widget, "btn_gongfa")
    local btn_treasured = ccui.Helper:seekNodeByName(UIBagGongFa.Widget, "btn_treasured")
    local btn_expansion = ccui.Helper:seekNodeByName(UIBagGongFa.Widget, "btn_expansion")
    local btn_sell = ccui.Helper:seekNodeByName(UIBagGongFa.Widget, "btn_sell")
    local function btnTouchEvent(sender, eventType)
       if eventType == ccui.TouchEventType.ended then
          AudioEngine.playEffect("sound/button.mp3")
          if sender == btn_expansion then
            expandPrice = DictSysConfig[tostring(StaticSysConfig.expandInitGold)].value + expandNum*DictSysConfig[tostring(StaticSysConfig.bagExpandGoldGrow)].value
            local hint = string.format("购买5个%s背包上限，需要花费%d个元宝",bagFlag == 1 and "功法" or "法宝",expandPrice)
            utils.PromptDialog(ExpandCallBack,hint)
          elseif sender == btn_sell then
                UIBagGongFaList.setOperateType(bagFlag == 1  and UIBagGongFaList.OperateType.gongfaSell or UIBagGongFaList.OperateType.fabaoSell )
                UIManager.pushScene("ui_bag_gongfa_list")
          elseif sender == btn_gongfa then 
            if bagFlag == 1 then 
                  return
            end
            bagFlag = 1
            UIBagGongFa.setup()
          elseif sender == btn_treasured then 
            if bagFlag == 2 then 
                  return
            end
            bagFlag = 2
            UIBagGongFa.setup()
          end
       end
     end
      btn_gongfa:setPressedActionEnabled(true)
      btn_treasured:setPressedActionEnabled(true)
      btn_expansion:setPressedActionEnabled(true)
      btn_sell:setPressedActionEnabled(true)
      btn_gongfa:addTouchEventListener(btnTouchEvent)
      btn_expansion:addTouchEventListener(btnTouchEvent)
      btn_treasured:addTouchEventListener(btnTouchEvent)
      btn_sell:addTouchEventListener(btnTouchEvent)
      scrollView = ccui.Helper:seekNodeByName(UIBagGongFa.Widget, "view_list_gongfa")
      gongFaItem = scrollView:getChildByName("image_base_gongfa"):clone()
end
function UIBagGongFa.setup()
    if not bagFlag then 
      bagFlag = 1 
    end
    local grid = 0
    if gongFaItem:getReferenceCount() == 1 then
      gongFaItem:retain()
    end
    scrollView:removeAllChildren()
    if net.InstPlayerBagExpand then 
        for key,obj in pairs(net.InstPlayerBagExpand) do
            if obj.int["3"] == StaticBag_Type.kungFu and bagFlag == 1 then 
                grid = obj.int["4"] + DictBagType[tostring(obj.int["3"])].bagUpLimit
                expandNum = obj.int["6"]
            elseif  obj.int["3"] == StaticBag_Type.magic and bagFlag == 2 then 
                grid = obj.int["4"] + DictBagType[tostring(obj.int["3"])].bagUpLimit
                expandNum = obj.int["6"]
            end
        end
    end
    if grid == 0 then 
        grid =DictBagType[tostring(bagFlag == 1 and StaticBag_Type.kungFu or StaticBag_Type.magic)].bagUpLimit
    end
    local gongFaThing={}
    if net.InstPlayerMagic then
      for key, obj in pairs(net.InstPlayerMagic) do
        if bagFlag == 1 and obj.int["4"] == 2 or bagFlag == 2 and obj.int["4"] == 1 then 
          table.insert(gongFaThing,obj)
        end
      end
      utils.quickSort(gongFaThing,compareMagic)
    end
    selectedBtnChange(bagFlag)
    if next(gongFaThing) then
        utils.updateView(UIBagGongFa,scrollView,gongFaItem,gongFaThing,setScrollViewItem,bagFlag)
    end
    local text_ceiling =  ccui.Helper:seekNodeByName(UIBagGongFa.Widget, "text_ceiling")
    text_ceiling:setString(string.format("背包上限%d/%d",#gongFaThing,grid))
end

function UIBagGongFa.free( ... )
  scrollView:removeAllChildren()
  bagFlag = nil
  expandNum = nil
end