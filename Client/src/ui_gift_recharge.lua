require "net"
require "SDK"

UIGiftRecharge = {
    retList = nil,-- retList格式为  字典id_0;字典id_1;....最后没有分号 字典id后边的0和1：//0-未充过钱  1-充过钱
}
local scrollView = nil
local listItem = nil
local retListTab = nil

local function giftVipCallBack(pack)
    if pack.msgdata.int and pack.msgdata.int["1"] then
        dp.rechargeGold = pack.msgdata.int["1"]
    else
        return
    end
    UIManager.replaceScene("ui_gift_vip")
end

function UIGiftRecharge.init(...)
    local btn_close = ccui.Helper:seekNodeByName(UIGiftRecharge.Widget, "btn_close")
    local btn_sure = ccui.Helper:seekNodeByName(UIGiftRecharge.Widget, "btn_sure")
    local function TouchEvent(sender, eventType)
        if eventType == ccui.TouchEventType.ended then
            AudioEngine.playEffect("sound/button.mp3")
            if sender == btn_close then
                UIManager.popScene()
            elseif sender == btn_sure then
                utils.checkGOLD(0, giftVipCallBack)
            end
        end
    end
    btn_close:setPressedActionEnabled(true)
    btn_sure:setPressedActionEnabled(true)
    btn_close:addTouchEventListener(TouchEvent)
    btn_sure:addTouchEventListener(TouchEvent)
    scrollView = ccui.Helper:seekNodeByName(UIGiftRecharge.Widget, "view")
    listItem = scrollView:getChildByName("image_base_di")
end

-- FOR IOS
local EC_SUCCEED = 0
local EC_RESTORED = 1
local EC_USERCANCELLED = 2
local EC_DISALLOWED = 3
local EC_REQUESTERROR = 4
local EC_FAILED = 5

local function savePay(productID, userDatas, base64Receipt)
    local payStr = cc.UserDefault:getInstance():getStringForKey("iapPayData")
    if payStr ~= "" then
        payStr = payStr .. "^" .. productID .. "^" .. userDatas .. "^" .. base64Receipt
    else
        payStr = productID .. "^" .. userDatas .. "^" .. base64Receipt
    end
    cc.UserDefault:getInstance():setStringForKey("iapPayData", payStr)
end

local function doVerifyPay(productID, userDatas, base64Receipt)
    local ud = dp.getUserData()
    if (not ud) or(not SDK.getUserId()) then return end
    local http = cc.XMLHttpRequest:new()
    http.responseType = cc.XMLHTTPREQUEST_RESPONSE_STRING
    http:registerScriptHandler( function()
        if http.status == 200 then
            local payStr = cc.UserDefault:getInstance():getStringForKey("iapPayData")
            if payStr ~= "" then
                local newPay = { }
                local pay = utils.stringSplit(payStr, "%^")
                for i = 1, #pay, 3 do
                    if pay[i + 2] ~= base64Receipt then
                        table.insert(newPay, pay[i])
                        table.insert(newPay, pay[i + 1])
                        table.insert(newPay, pay[i + 2])
                    end
                end
                payStr = table.concat(newPay, "^", 1, #newPay)
                cc.UserDefault:getInstance():setStringForKey("iapPayData", payStr)
                UIGiftRecharge.checkPay()
            end
        elseif netIsConnected() then
            UIGiftRecharge.checkPay()
        end
    end )
    http:open("POST", SDK.getNotifyUri())
    http:setRequestHeader("Content-Type", "application/octet-stream")
    local di = SDK.getDeviceInfo()
    http:send(
    "receipt=" .. utils.encodeURI(base64Receipt) ..
    "&product_id=3" ..
    "&server_id=" .. ud.serverId ..
    "&user_id=" .. SDK.getUserId() ..
    "&channel_id=yiyou" ..
    "&role_id=" .. ud.roleId ..
    "&role_name=" .. ud.roleName ..
    "&role_level=" .. ud.roleLevel ..
    "&device_os=" .. di.systemName ..
    "&device_mac=" .. di.macAddr ..
    "&idfa=" .. di.idfa ..
    "&app_product_id=" .. productID ..
    "&orderform=" .. userDatas
    )
end

local function onPay(params)
    if SDK.getChannel() == "iosy2game" then
        cclog("ErrorCode: " .. params.errorCode)
        if params.errorCode == EC_SUCCEED or params.errorCode == EC_RESTORED then
            cclog("ProductID: " .. params.productID)
            cclog("UserDatas: " .. params.userDatas)
            savePay(params.productID, params.userDatas, params.base64Receipt)
            doVerifyPay(params.productID, params.userDatas, params.base64Receipt)
        elseif params.errorCode == EC_USERCANCELLED then
        elseif params.errorCode == EC_DISALLOWED then
        elseif params.errorCode == EC_REQUESTERROR then
        elseif params.errorCode == EC_FAILED then
        end
        UIManager.hideLoading()
    else
        -- todo 不作处理
    end
end

UIGiftRecharge.onPay = onPay

-- 检测是否有未处理完的交易
function UIGiftRecharge.checkPay()
    if device.platform ~= "ios" then return end

    local payStr = cc.UserDefault:getInstance():getStringForKey("iapPayData")
    if payStr ~= "" then
        local pay = utils.stringSplit(payStr, "%^")
        local productID = pay[1]
        local userDatas = pay[2]
        local base64Receipt = pay[3]
        doVerifyPay(productID, userDatas, base64Receipt)
    end
end

local requestingProduct -- 参见DictRecharge.lua

local function onGetOrderID(pack)
    -- todo check requestingProduct?
    local orderID = pack.msgdata.string["1"]
    local productID = requestingProduct.id
    local priceYUAN = requestingProduct.rmb
    local di = SDK.getDeviceInfo()

    local productNames = { "月卡", "100元宝", "300元宝", "500元宝", "1000元宝", "3000元宝", "5000元宝", "10000元宝", "黄金月卡" }

    if SDK.getChannel() == "dev" then
        -- SDK.doPay(string,onPay)
    elseif SDK.getChannel() == "uc" or SDK.getChannel() == "baidu" then
        local productName = productNames[productID] or "";
        local info = { productName, orderID, tostring(productID), tostring(priceYUAN) }
        SDK.doPay(info, onPay)
    elseif SDK.getChannel() == "yijie" then
        if di.packageName == "com.doupo.ewan" then
            local productName = "";
            if productID == 1 then
                productName = "月卡";
            else
                productName = "元宝";
            end
            local info = { productName, orderID, tostring(1), tostring(priceYUAN) }
            SDK.doPay(info, onPay)
        elseif di.packageName == "com.doupo.mz" then
            local productName = "";
            if productID == 1 then
                productName = "元宝+月卡";
            else
                productName = "元宝";
            end
            local info = { productName, orderID, tostring(1), tostring(priceYUAN) }
            SDK.doPay(info, onPay)
        else
            local productName = productNames[productID] or "";
            local info = { productName, orderID, tostring(1), tostring(priceYUAN) }
            SDK.doPay(info, onPay)
        end
    elseif SDK.getChannel() == "y2game" then
        local productName = productNames[productID] or "";
        local info = { productName, orderID, requestingProduct.firstAmtDes, tostring(priceYUAN) }
        SDK.doPay(info, onPay)
    elseif SDK.getChannel() == "lianxiang" then
        local wareSids = { "4316", "4309", "4310", "4311", "4312", "4313", "4314", "4315", "4311" }
        local wareSid = wareSids[productID] or "";
        local info = { wareSid, orderID, tostring(productID), tostring(priceYUAN) }
        SDK.doPay(info, onPay)
    elseif SDK.getChannel() == "xiaomi" then
        local role = dp.getUserData()
        local info = { orderID, tostring(productID), tostring(priceYUAN), tostring(role.vipLevel), tostring(role.roleLevel), role.roleName, tostring(role.roleId), role.serverName }
        SDK.doPay(info, onPay)
    elseif SDK.getChannel() == "qq" then
        local role = dp.getUserData()
        cc.JNIUtils:logAndroid("qq doPay ---------------------------------")
        local info = { tostring(role.serverId), tostring(priceYUAN), orderID }
        SDK.doPay(info)
    elseif SDK.getChannel() == "360" then
        local role = dp.getUserData()
        local info = {
            tostring(priceYUAN),"元宝",tostring(productID),"斗破苍穹OL",
            role.roleName,tostring(role.roleId),orderID
        }
        SDK.doPay(info, onPay)
    elseif SDK.getChannel() == "huawei" then
        local role = dp.getUserData()
        local info = { tostring(priceYUAN), "元宝", "充值元宝", orderID, tostring(role.roleId) }
        SDK.doPay(info, onPay)
    elseif SDK.getChannel() == "oppo" then
        local info = { orderID, tostring(priceYUAN), "元宝", "充值元宝" }
        SDK.doPay(info, onPay)
    elseif SDK.getChannel() == "vivo" then
        local channelData = pack.msgdata.string["2"]
        local info = { "元宝", "充值元宝", channelData }
        SDK.doPay(info, onPay)
    elseif SDK.getChannel() == "jinli" then
        local channelData = pack.msgdata.string["2"]
        local info = { channelData }
        SDK.doPay(info, onPay)
    elseif SDK.getChannel() == "kupai" then
        local channelData = pack.msgdata.string["2"]
        local info = { channelData }
        SDK.doPay(info, onPay)
    elseif SDK.getChannel() == "iosy2game" then
        UIManager.showLoading()
        SDK.doPay( { productID = requestingProduct.description, userDatas = orderID }, onPay)
    elseif SDK.getChannel() == "anysdk" then
        local role = dp.getUserData()
        local productName = productNames[productID] or "";
        SDK.doPay( { productID = requestingProduct.description, userDatas = orderID, product_Name = productName, productPrice = tostring(priceYUAN), product_Count = "1", roleId = tostring(role.roleId), roleName = tostring(role.roleName), roleGrade = tostring(role.roleLevel), server_Id = tostring(role.serverId) }, onPay)
    end


    local productName = "";
    if device.platform == "ios" then
        if SDK.getChannel() == "anysdk" then
            productName = productNames[productID] or productName;
        else
            local productNames = { "月卡", "60元宝", "300元宝", "680元宝", "980元宝", "1980元宝", "3280元宝", "6480元宝", "黄金月卡" }
            productName = productNames[productID] or productName;
        end
    else
        productName = productNames[productID] or productName;
    end
    local info = { productName, orderID, tostring(priceYUAN) }
    SDK.doPayTD(info)
    requestingProduct = nil
end

function UIGiftRecharge.doGetOrderID(product)
    requestingProduct = product
    if device.platform == "windows" then
        cclog("模拟器不支持支付")
    elseif device.platform == "android" or device.platform == "ios" then
        UIManager.showLoading()
        local pack = {
            header = StaticMsgRule.getOrder,
            msgdata =
            {
                int =
                {
                    rechargeId = product.id
                }
            }
        }
        netSendPackage(pack, onGetOrderID)
    end
end

local function setScrollViewItem(Item, obj)
    local ui_image = ccui.Helper:seekNodeByName(Item, "image_good")
    local ui_number = ccui.Helper:seekNodeByName(Item, "text_number")
    local ui_price = ccui.Helper:seekNodeByName(Item, "text_price")
    local ui_text_info = ccui.Helper:seekNodeByName(Item, "text_info")
    local ui_text_xiangou = ccui.Helper:seekNodeByName(Item, "text_xiangou")
    local imageName = DictUI[tostring(obj.uiId)].fileName
    ui_image:loadTexture("image/" .. imageName)
    ui_price:setString(obj.rmb .. "元")
    if SDK.getChannel() == "iosy2game" then
        if obj.firstAmt == 0 then
            ui_number:setString(obj.rmb .. "元月卡")
        else
            ui_number:setString(obj.rmb * 10 .. "元宝")
        end
    else
        ui_number:setString(obj.rmb * 10 .. "元宝")
    end
    if tonumber(retListTab[obj.id]) ~= 0 then
        ui_text_info:setString(obj.noFirstAmtDes)
        ui_text_xiangou:setVisible(false)
    else
        ui_text_info:setString(obj.firstAmtDes)
        if obj.id == 1 or obj.id == 9 then
            ui_text_xiangou:setVisible(false)
        else
            ui_text_xiangou:setVisible(true)
        end
    end
    -- if SDK.getChannel() == "iosy2game" then
    -- 	ui_text_xiangou:setVisible(false)
    -- end
    Item:setEnabled(true)
    Item:setTouchEnabled(true)
    local function chargeEvent(sender, eventType)
        if eventType == ccui.TouchEventType.ended then
            if obj.id == 1 or obj.id == 9 then
                -- if device.platform == "ios" then
                local type = obj.id == 1 and UIActivityCard.SILVER_MONTH_CARD or UIActivityCard.GOLD_MONTH_CARD
                local name = obj.id == 1 and "白银" or "黄金"
                local instActivityObj = UIActivityCard.getMonthCardData(type)
                if instActivityObj then
                    if instActivityObj.string["4"] == "" then
                        UIManager.showToast("您已购买" .. name .. "月卡！")
                    else
                        if UIActivityPanel.isEndActivityByEndTime(instActivityObj.string["4"]) then
                            UIGiftRecharge.doGetOrderID(obj)
                        else
                            UIManager.showToast("您上次购买的" .. name .. "月卡还未过期！")
                        end
                    end
                else
                    UIGiftRecharge.doGetOrderID(obj)
                end
                --  else
                --      UIGiftRecharge.doGetOrderID(obj)
                --      -- UIManager.showToast("暂未开放!")
                --  end
            else
                UIGiftRecharge.doGetOrderID(obj)
            end
        end
    end
    Item:addTouchEventListener(chargeEvent)
end

function UIGiftRecharge.setup(...)
    scrollView:removeAllChildren()
    -- if UIGiftVip.getState() then
    -- 	ccui.Helper:seekNodeByName(UIGiftRecharge.Widget, "image_hint"):setVisible(true)
    -- else
    ccui.Helper:seekNodeByName(UIGiftRecharge.Widget, "image_hint"):setVisible(false)
    -- end
    local currentVipNum = net.InstPlayer.int["19"]
    local nextVipNum = currentVipNum + 1
    local limit = nil
    if DictVIP[tostring(nextVipNum + 1)] then
        limit = DictVIP[tostring(nextVipNum + 1)].limit
    end
    local ui_label_vip = ccui.Helper:seekNodeByName(UIGiftRecharge.Widget, "text_vip")
    local ui_text_loading = ccui.Helper:seekNodeByName(UIGiftRecharge.Widget, "text_loading")
    local ui_loading = ccui.Helper:seekNodeByName(UIGiftRecharge.Widget, "bar_loading")
    local ui_image_gold = ccui.Helper:seekNodeByName(UIGiftRecharge.Widget, "image_gold")
    local ui_text_vip = ui_image_gold:getChildByName("text_vip")
    local ui_text_recharge = ui_image_gold:getChildByName("text_recharge")
    local ui_text_hint = ccui.Helper:seekNodeByName(UIGiftRecharge.Widget, "text_hint")
    ui_label_vip:setString(currentVipNum)
    if limit then
        ui_image_gold:setVisible(true)
        ui_text_hint:setVisible(false)
        ui_text_loading:setString(string.format("%d/%d", dp.rechargeGold, limit * 10))
        local number = dp.rechargeGold /(limit * 10) * 100
        if number > 100 then
            ui_loading:setPercent(100)
        else
            ui_loading:setPercent(number)
        end
        ui_text_vip:setString(string.format("既可成为 VIP%d", nextVipNum))
        ui_text_recharge:setString(string.format("再充值 %d", limit * 10 - dp.rechargeGold))
    else
        ui_text_loading:setString("MAX")
        ui_loading:setPercent(100)
        ui_image_gold:setVisible(false)
        ui_text_hint:setVisible(true)
    end
    if UIGiftRecharge.retList then
        retListTab = { }
        local _retListTab = utils.stringSplit(UIGiftRecharge.retList, ";")
        for key, obj in pairs(_retListTab) do
            local strTab = utils.stringSplit(obj, "_")
            table.insert(retListTab, strTab[1], strTab[2])
        end
    end
    local rechargeThing = { }

    for key, obj in pairs(DictRecharge) do
        table.insert(rechargeThing, obj)
    end
    utils.quickSort(rechargeThing, function(obj1, obj2)
        if obj1.id == 9 then
            return obj2.id <= 1
        elseif obj2.id == 9 then
            return obj1.id > 1
        end

        return obj1.id > obj2.id
    end )
    utils.updateView(UIGiftRecharge, scrollView, listItem, rechargeThing, setScrollViewItem)
end

function UIGiftRecharge.free()
    scrollView:removeAllChildren()
    retListTab = nil
    UIGiftRecharge.retList = nil
end
