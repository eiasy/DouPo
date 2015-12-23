UIBagWing={}
local _flag = nil
local scrollView = nil
local _item = nil
local _itemPiece = nil
local _objThing = nil
local function selectedBtnChange(flag) 
    local btn_equipment = ccui.Helper:seekNodeByName(UIBagWing.Widget,"btn_equipment")
    local btn_chip = ccui.Helper:seekNodeByName(UIBagWing.Widget,"btn_chip")
    if flag == 1 then 
        btn_chip:loadTextureNormal("ui/yh_btn01.png")
        btn_chip:getChildByName("text_chip"):setTextColor(cc.c4b(255,255,255,255))
        btn_equipment:loadTextureNormal("ui/yh_btn02.png")
        btn_equipment:getChildByName("text_equipment"):setTextColor(cc.c4b(51,25,4,255))
    elseif  flag ==  2 then
        btn_equipment:loadTextureNormal("ui/yh_btn01.png")
        btn_equipment:getChildByName("text_equipment"):setTextColor(cc.c4b(255,255,255,255))
        btn_chip:loadTextureNormal("ui/yh_btn02.png")
        btn_chip:getChildByName("text_chip"):setTextColor(cc.c4b(51,25,4,255))
    end
end
function UIBagWing.freshViewItem( obj )
    item = scrollView:getChildByTag( 10000 + obj.int["1"] )
    if _flag == 1 then
        local btn_intensify = ccui.Helper:seekNodeByName( item , "btn_intensify" )
        local btn_advance = ccui.Helper:seekNodeByName( item , "btn_advance" )
        local btn_change = ccui.Helper:seekNodeByName( item , "btn_change" )
        local function onEvent( sender , eventType )
            if eventType == ccui.TouchEventType.ended then
                if sender == btn_intensify then
                    UIWingIntensify.setData( obj )
                    UIManager.pushScene( "ui_wing_intensify" )
                elseif sender == btn_advance then
                    UIWingAdvance.setData( obj )
                    UIManager.pushScene( "ui_wing_advance" )
                elseif sender == btn_change then
                    if obj.int["3"] >= 5 then
                        UIManager.showToast("全属性翅膀不需要转换")
                    else
                        UIWingChange.setData( obj )
                        UIManager.pushScene( "ui_wing_change" )
                    end
                end
            end
        end
        btn_intensify:setPressedActionEnabled( true )
        btn_intensify:addTouchEventListener( onEvent )
        btn_advance:setPressedActionEnabled( true )
        btn_advance:addTouchEventListener( onEvent )
        btn_change:setPressedActionEnabled( true )
        btn_change:addTouchEventListener( onEvent )

        local image_frame_wing = ccui.Helper:seekNodeByName( item , "image_frame_wing" )

        local image_wing_0 = image_frame_wing:getChildByName( "image_wing_0" )
        image_wing_0:loadTexture( "ui/wing_"..DictWing[ tostring( obj.int["3"] ) ].sname..".png" )
        
        local text_name_wing = ccui.Helper:seekNodeByName( item , "text_name_wing" )
        text_name_wing:setString( DictWing[ tostring( obj.int["3"] ) ].name )
        local text_lv = ccui.Helper:seekNodeByName( item , "text_lv" )
        if obj.int["5"] == 1 then
            text_lv:setString( "一阶" )
        elseif obj.int["5"] == 2 then
            text_lv:setString( "二阶" )
        elseif obj.int["5"] == 3 then
            text_lv:setString( "三阶" )
        end
        
        local text_wing_for = ccui.Helper:seekNodeByName( item , "text_wing_for" )
        if obj.int["6"] ~= 0 then
            text_wing_for:setVisible( true )
            local cardName = DictCard[tostring(net.InstPlayerCard[tostring(obj.int["6"])].int["3"])].name
            text_wing_for:setString("装备于"..cardName)
        else
            text_wing_for:setVisible( false )
        end

        local image_di = ccui.Helper:seekNodeByName( item , "image_di" )
        local strengthenData , advanceData , proShow = utils.getWingInfo( obj.int["3"] , obj.int["4"] , obj.int["5"] , image_di ) 

        local image_wing = image_frame_wing:getChildByName( "image_wing" )
        local smallImage= DictUI[tostring(advanceData.smallUiId)].fileName
        image_wing:loadTexture( "image/"..smallImage )

    elseif _flag == 2 then
        local btn_lineup = ccui.Helper:seekNodeByName( item , "btn_lineup" )
        local function onEventPiece( sender , eventType )
            if eventType == ccui.TouchEventType.ended then
                if sender == btn_lineup then
                    local title = btn_lineup:getTitleText()
                    if title == "合成" then
                        UIWingCommon.setData( obj )
                        UIManager.pushScene( "ui_wing_common" )
                    elseif title == "去进阶" then
                        UIManager.pushScene( "ui_wing_info" )
                    end
                end
            end
        end
        btn_lineup:setPressedActionEnabled( true )
        btn_lineup:addTouchEventListener( onEventPiece )

        local tableFieldId = obj.int["3"]
        local name_text=DictThing[tostring(tableFieldId)].name
        local smallUiId = DictThing[tostring(tableFieldId)].smallUiId
        local smallImage= DictUI[tostring(smallUiId)].fileName
        local description_text =DictThing[tostring(tableFieldId)].description
        btn_lineup:setBright( true )
        btn_lineup:setEnabled( true )
       -- cclog("---------------")
        if tableFieldId == StaticThing.thing306 then
            local condition = utils.stringSplit( DictWingAdvance["1"].nextStarNumConds , "_" )
          --  cclog( "condition[3] :"..condition[3])
            if obj.int["5"] >= tonumber( condition[3] ) then
                btn_lineup:setTitleText( "合成" )
            else
                btn_lineup:setBright( false )
                btn_lineup:setTitleText( "未集全" )
            end
        else
            local condition = utils.stringSplit( DictWingAdvance[tostring(tableFieldId-StaticThing.thing306 + 1)].nextStarNumConds , "_" )
            --cclog( "condition[3] :"..condition[3])
            if obj.int["5"] >= tonumber( condition[3] ) then
                btn_lineup:setTitleText( "去进阶" )
            else
                btn_lineup:setTitleText( "去进阶" )
                btn_lineup:setEnabled( false )
                btn_lineup:setBright( false )
            end

        end
       -- cclog("---------------")
        local image_frame_chip = ccui.Helper:seekNodeByName( item , "image_frame_chip" )
        utils.addBorderImage( obj.int["6"] , obj.int["3"] , image_frame_chip )
        local image_chip = image_frame_chip:getChildByName( "image_chip" )
        image_chip:loadTexture( "image/"..smallImage )
        local text_chip_name = ccui.Helper:seekNodeByName( item , "text_chip_name" )
        text_chip_name:setString( name_text )
        local text_number = ccui.Helper:seekNodeByName( item , "text_number" )
        text_number:setString( "数量："..obj.int["5"] )
        local text_gem_describe = ccui.Helper:seekNodeByName( item , "text_gem_describe" )
        text_gem_describe:setString(description_text)
    end
end
local function setScrollViewItem( item , obj )
    item:setTag( 10000 + obj.int["1"] )
    if _flag == 1 then
        local btn_intensify = ccui.Helper:seekNodeByName( item , "btn_intensify" )
        local btn_advance = ccui.Helper:seekNodeByName( item , "btn_advance" )
        local btn_change = ccui.Helper:seekNodeByName( item , "btn_change" )
        local function onEvent( sender , eventType )
            if eventType == ccui.TouchEventType.ended then              
                if sender == btn_intensify then
                    if obj.int["6"] == 0 then
                        UIWingIntensify.setData( obj )
                    else
                        UIWingIntensify.setData( obj , net.InstPlayerCard[tostring(obj.int["6"])].int["3"] )
                    end
                    UIManager.pushScene( "ui_wing_intensify" )
                elseif sender == btn_advance then
                    if obj.int["6"] == 0 then
                        UIWingAdvance.setData( obj )
                    else
                        UIWingAdvance.setData( obj , net.InstPlayerCard[tostring(obj.int["6"])].int["3"] )
                    end
                    UIManager.pushScene( "ui_wing_advance" )
                elseif sender == btn_change then
                    if obj.int["3"] >= 5 then
                        UIManager.showToast("全属性翅膀不需要转换")
                    else
                        if obj.int["6"] == 0 then
                            UIWingChange.setData( obj )
                        else
                            UIWingChange.setData( obj , net.InstPlayerCard[tostring(obj.int["6"])].int["3"] )
                        end
                        UIManager.pushScene( "ui_wing_change" )
                    end
                end
            end
        end
        btn_intensify:setPressedActionEnabled( true )
        btn_intensify:addTouchEventListener( onEvent )
        btn_advance:setPressedActionEnabled( true )
        btn_advance:addTouchEventListener( onEvent )
        btn_change:setPressedActionEnabled( true )
        btn_change:addTouchEventListener( onEvent )

        

        local image_frame_wing = ccui.Helper:seekNodeByName( item , "image_frame_wing" )

        local image_wing_0 = image_frame_wing:getChildByName( "image_wing_0" )
        if obj.int["3"] >= 5 then
            image_wing_0:loadTexture( "ui/wing_all.png" )
        else
            image_wing_0:loadTexture( "ui/wing_"..DictWing[ tostring( obj.int["3"] ) ].sname..".png" )
        end
        local text_name_wing = ccui.Helper:seekNodeByName( item , "text_name_wing" )
        text_name_wing:setString( DictWing[ tostring( obj.int["3"] ) ].name )
        local text_lv = ccui.Helper:seekNodeByName( item , "text_lv" )
        if obj.int["5"] == 1 then
            text_lv:setString( "一阶" )
        elseif obj.int["5"] == 2 then
            text_lv:setString( "二阶" )
        elseif obj.int["5"] == 3 then
            text_lv:setString( "三阶" )
        end
        
        local text_wing_for = ccui.Helper:seekNodeByName( item , "text_wing_for" )
        if obj.int["6"] ~= 0 then
            text_wing_for:setVisible( true )
            local cardName = DictCard[tostring(net.InstPlayerCard[tostring(obj.int["6"])].int["3"])].name
            text_wing_for:setString("装备于"..cardName)
        else
            text_wing_for:setVisible( false )
        end
        local image_di = ccui.Helper:seekNodeByName( item , "image_di" )
        local strengthenData , advanceData , proShow = utils.getWingInfo( obj.int["3"] , obj.int["4"] , obj.int["5"] , image_di ) 

        local image_wing = image_frame_wing:getChildByName( "image_wing" )
        local smallImage= DictUI[tostring(advanceData.smallUiId)].fileName
        image_wing:loadTexture( "image/"..smallImage )

    elseif _flag == 2 then
        local btn_lineup = ccui.Helper:seekNodeByName( item , "btn_lineup" )
        local function onEventPiece( sender , eventType )
            if eventType == ccui.TouchEventType.ended then
                if sender == btn_lineup then
                    local title = btn_lineup:getTitleText()
                    if title == "合成" then
                        UIManager.pushScene( "ui_wing_common" )
                    elseif title == "去进阶" then
                        UIManager.pushScene( "ui_wing_info" )
                    end
                end
            end
        end
        btn_lineup:setPressedActionEnabled( true )
        btn_lineup:addTouchEventListener( onEventPiece )

        local tableFieldId = obj.int["3"]
        local name_text=DictThing[tostring(tableFieldId)].name
        local smallUiId = DictThing[tostring(tableFieldId)].smallUiId
        local smallImage= DictUI[tostring(smallUiId)].fileName
        local description_text =DictThing[tostring(tableFieldId)].description
        btn_lineup:setEnabled( true )
        btn_lineup:setBright( true )
        if tableFieldId == StaticThing.thing306 then
            local condition = utils.stringSplit( DictWingAdvance["1"].nextStarNumConds , "_" )
            cclog( "condition[3] :"..condition[3])
            if obj.int["5"] >= tonumber( condition[3] ) then
                btn_lineup:setTitleText( "合成" )
            else
                btn_lineup:setBright( false )
                btn_lineup:setTitleText( "未集全" )
            end
        else
            local condition = utils.stringSplit( DictWingAdvance[tostring(tableFieldId-StaticThing.thing306 + 1)].nextStarNumConds , "_" )
            cclog( "condition[3] :"..condition[3])
            if obj.int["5"] >= tonumber( condition[3] ) then
                btn_lineup:setTitleText( "去进阶" )
            else
                btn_lineup:setTitleText( "去进阶" )
                btn_lineup:setEnabled( false )
                btn_lineup:setBright( false )
            end

        end

        local image_frame_chip = ccui.Helper:seekNodeByName( item , "image_frame_chip" )
        utils.addBorderImage( obj.int["6"] , obj.int["3"] , image_frame_chip )
        local image_chip = image_frame_chip:getChildByName( "image_chip" )
        image_chip:loadTexture( "image/"..smallImage )
        local text_chip_name = ccui.Helper:seekNodeByName( item , "text_chip_name" )
        text_chip_name:setString( name_text )
        local text_number = ccui.Helper:seekNodeByName( item , "text_number" )
        text_number:setString( "数量："..obj.int["5"] )
        local text_gem_describe = ccui.Helper:seekNodeByName( item , "text_gem_describe" )
        text_gem_describe:setString(description_text)
    end
end
function UIBagWing.init()
    local btn_sell = ccui.Helper:seekNodeByName( UIBagWing.Widget , "btn_sell" )
    local btn_chip = ccui.Helper:seekNodeByName( UIBagWing.Widget , "btn_chip" )
    local btn_equipment = ccui.Helper:seekNodeByName(UIBagWing.Widget,"btn_equipment")
    local image_hint = ccui.Helper:seekNodeByName(UIBagWing.Widget , "image_hint")
    local btn_go = image_hint:getChildByName("btn_go")
    local btn_help = ccui.Helper:seekNodeByName( UIBagWing.Widget , "btn_help" )
    local function onEvent( sender , eventType )
        if eventType == ccui.TouchEventType.ended then
            if sender == btn_sell then
                if _flag == 1 then
                    UIBagWingSell.setType( UIBagWingSell.type.SELL )
                else
                    UIBagWingSell.setType( UIBagWingSell.type.SELL_PIECE )
                end                
                UIManager.pushScene("ui_bag_wing_sell")
            elseif sender == btn_chip then
                if _flag == 2 then
                    return 
                end
                _flag = 2
                UIBagWing.setup()
            elseif sender == btn_equipment then
                if _flag == 1 then
                    return
                end
                _flag = 1
                UIBagWing.setup()
            elseif sender == btn_go then
                UIFight.setFlag(1,2)
                 UIManager.showScreen("ui_notice", "ui_team_info", "ui_fight", "ui_menu")
                 UIFightPreView.setChapterId(DictChapter["300"].id)
                 UIManager.pushScene("ui_fight_preview")
            elseif sender == btn_help then
                UIAllianceHelp.show( { type = 10 , titleName = "神羽帮助" } )
            end
        end
    end
    btn_sell:setPressedActionEnabled( true )
    btn_sell:addTouchEventListener( onEvent )
    btn_chip:setPressedActionEnabled( true )
    btn_chip:addTouchEventListener( onEvent )
    btn_equipment:setPressedActionEnabled( true )
    btn_equipment:addTouchEventListener( onEvent )
    btn_go:setPressedActionEnabled( true )
    btn_go:addTouchEventListener(onEvent)
    btn_help:setPressedActionEnabled( true )
    btn_help:addTouchEventListener(onEvent)

    scrollView = ccui.Helper:seekNodeByName( UIBagWing.Widget , "view_list_equipment")
    _item = scrollView:getChildByName("image_base_wing")
    _item:retain()
    _itemPiece = scrollView:getChildByName("image_base_chip")
    _itemPiece:retain()
end
function UIBagWing.setup()
    if not _flag then
        _flag = 1
    end
    selectedBtnChange( _flag )
    _objThing = {}
    local item = nil
    local image_hint = ccui.Helper:seekNodeByName(UIBagWing.Widget , "image_hint")
    local text_hint = image_hint:getChildByName("text_hint")
    local btn_go = image_hint:getChildByName("btn_go")
    if _flag == 1 then
      --  _objThing = { 1 , 2 , 3 , 4 }
        if net.InstPlayerWing then
            for key , value in pairs( net.InstPlayerWing ) do
                table.insert( _objThing , value )
            end
        end
        utils.quickSort( _objThing , function ( obj1 , obj2 )
            if obj1.int["6"] == 0 and obj2.int["6"] ~= 0 then
                return true
            elseif obj2.int["6"] == 0 and obj1.int["6"] ~= 0 then
                return false
            elseif obj1.int["5"] < obj2.int["5"] then
                return true
            elseif obj1.int["5"] > obj2.int["5"] then
                return false
            elseif obj1.int["4"] < obj2.int["4"] then
                return true
            else
                return false
            end
        end)
        if #_objThing > 0 then
            image_hint:setVisible( false )
        else
            image_hint:setVisible( true )
            text_hint:setVisible( true )
            text_hint:setString("暂未获得神羽，使用一阶神羽碎片可合成神羽")
            btn_go:setVisible( false )
        end
        item = _item
    elseif _flag == 2 then
       -- _objThing = { 1 , 2 , 3 }
        item = _itemPiece
        if net.InstPlayerThing then
            for key, obj in pairs(net.InstPlayerThing) do
                if obj.int["7"] == StaticBag_Type.wing then 
                     table.insert(_objThing,obj)
                end
            end
        end
        utils.quickSort( _objThing , function ( obj1 , obj2 )
            if obj1.int["3"] < obj2.int["3"] then
                return true
            else
                return false
            end
        end)
        if #_objThing > 0 then
            image_hint:setVisible( false )
        else
            image_hint:setVisible( true )
            text_hint:setString("未获得碎片")
            text_hint:setVisible( true )
            btn_go:setVisible( true )
        end
    end
    
    scrollView:removeAllChildren()
    utils.updateScrollView( UIBagWingSell , scrollView , item , _objThing , setScrollViewItem )
    local btn_chip = ccui.Helper:seekNodeByName( UIBagWing.Widget , "btn_chip" )
    utils.addImageHint(UIBagWing.checkImageHint(),btn_chip,100,18,10)
end
function UIBagWing.free()
    _flag = nil
    _objThing = nil
end

function UIBagWing.checkImageHint()
    local objThing={}
    if net.InstPlayerThing then
        for key, obj in pairs(net.InstPlayerThing) do
             if obj.int["7"] == StaticBag_Type.wing then 
                 table.insert(objThing,obj)
             end
        end
    end
    local result = false
    for key, obj in pairs(objThing) do
        local tableFieldId = obj.int["3"]       
        if tableFieldId == StaticThing.thing306 then
            local condition = utils.stringSplit( DictWingAdvance["1"].nextStarNumConds , "_" )
            if obj.int["5"] >= tonumber( condition[3] ) then
                result = true
                break
            end
        end
    end
    return result
end