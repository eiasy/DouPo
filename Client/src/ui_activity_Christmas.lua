UIActivityChristmas = {}
local _countF = nil
local _countS = nil
local _type = nil
function getAnimation( uiAnimId , uiAnimName )
    local animPath = "ani/ui_anim/ui_anim" .. uiAnimId .. "/"
    ccs.ArmatureDataManager:getInstance():removeArmatureFileInfo(animPath .. "ui_anim" .. uiAnimId .. ".ExportJson")
    ccs.ArmatureDataManager:getInstance():addArmatureFileInfo(animPath .. "ui_anim" .. uiAnimId .. ".ExportJson")
    local animation = ccs.Armature:create("ui_anim" .. uiAnimId)
    animation:getAnimation():play( "ui_anim" .. uiAnimId .. "_"..uiAnimName )
    return animation
end

function freshData()
    local text_hand = ccui.Helper:seekNodeByName( UIActivityChristmas.Widget , "text_hand" )
    text_hand:setString("今日可领 ".._countF.."/2 次")
    local panel_hand = ccui.Helper:seekNodeByName( UIActivityChristmas.Widget , "panel_hand" )
    if _countF > 0 then
        utils.addFrameParticle( panel_hand , true , 1.4 , false , 40 , 0 )
    else
        utils.addFrameParticle( panel_hand , false )
    end

    local text_box = ccui.Helper:seekNodeByName( UIActivityChristmas.Widget , "text_box" )   
    local panel_box = ccui.Helper:seekNodeByName( UIActivityChristmas.Widget , "panel_box" )
    if _countS > 0 then
        text_box:setString("可领取")
        utils.addFrameParticle( panel_box , true , 1.4 )
    else
        text_box:setString("已领取")
        utils.addFrameParticle( panel_box , false )
    end
end
local function getEffect( goodS )
    local goods = utils.stringSplit( goodS , ";" )
    UIAwardGet.setOperateType(UIAwardGet.operateType.award, goods)
   	UIManager.pushScene("ui_award_get")
end
local function netCallBack( pack )
    _countF = pack.msgdata.int.first
    _countS = pack.msgdata.int.second
    cclog("_countF ".._countF .. "  ".._countS)
    freshData()
    if _type == 1 or _type == 2 then
        local goodStr = pack.msgdata.string["1"]
        getEffect( goodStr )
    end
end
--int.type = 圣诞活动类型 (0.进入活动界面  1.开启拼手气礼盒  2.开启圣诞礼盒)

local function netSendData()
    cclog("_type :".._type)
    UIManager.showLoading()
    local sendData = {
        header = StaticMsgRule.christmasDay ,
        msgdata = {
            int = {
                type = _type
            }
        }
    }
    netSendPackage(sendData , netCallBack)
end
local function openEffect()      
    netSendData()
end


function UIActivityChristmas.init()
    local panel_hand = ccui.Helper:seekNodeByName( UIActivityChristmas.Widget , "panel_hand" )
    local panel_box = ccui.Helper:seekNodeByName( UIActivityChristmas.Widget , "panel_box" )
    local anim_hand = getAnimation( 62 , 1 )
    anim_hand:setPosition( cc.p( panel_hand:getContentSize().width / 2 , panel_hand:getContentSize().height / 2 ) )
    panel_hand:addChild( anim_hand )
    local anim_box = getAnimation( 62 , 2 )
    anim_box:setPosition( cc.p( panel_box:getContentSize().width / 2 , panel_box:getContentSize().height / 2 ) )
    panel_box:addChild( anim_box )
    local function onEvent( sender , eventType )
        if eventType == ccui.TouchEventType.ended then
            if sender == panel_hand then
                --cclog("抓手气")
                _type = 1
               openEffect()
            elseif sender == panel_box then
                --cclog("宝箱")
                _type = 2
                openEffect()
            end
        end
    end
    panel_hand:setTouchEnabled( true )
    panel_hand:addTouchEventListener( onEvent )
    panel_box:setTouchEnabled( true )
    panel_box:addTouchEventListener( onEvent )

    local particle1 = cc.ParticleSystemQuad:create("particle/snow/ui_anim_snow_1.plist" )
        --particle1:setPositionType(cc.POSITION_TYPE_RELATIVE)
    particle1:setPosition( cc.p( UIManager.screenSize.width / 2 , UIManager.screenSize.height ) )
    UIActivityChristmas.Widget:addChild(particle1 , 10 )
end
function UIActivityChristmas.setup()
    _type = 0
    netSendData()
    AudioEngine.playMusic("sound/christmas.mp3", true)
end
function UIActivityChristmas.free()
    _countF = nil
    _countS = nil
    _type = nil
    AudioEngine.playMusic("sound/bg_music.mp3", true)
end

function UIActivityChristmas.onActivity( params )

end

