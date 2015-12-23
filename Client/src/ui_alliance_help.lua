UIAllianceHelp = {
    TYPES = {"info", "integral", "hero", "dial", "money", "pilltower", "ore", "realm" , "soul", "fire" , "wing", "hero_1" , "star", "war1", "war2", "war3", "purchase" }
}

local userData = nil

function UIAllianceHelp.init()
    UIAllianceHelp.Widget:addTouchEventListener(function(sender, eventType)
        if eventType == ccui.TouchEventType.ended then
            UIManager.popScene()
        end
    end)
end

function UIAllianceHelp.setup()
    if userData.titleName then
        ccui.Helper:seekNodeByName(UIAllianceHelp.Widget, "text_title"):setString(userData.titleName)
    end
    local view = ccui.Helper:seekNodeByName(UIAllianceHelp.Widget, "view")
    local children = view:getChildren()
    local name = "text_" .. UIAllianceHelp.TYPES[userData.type + 1]
    local helpText = view:getChildByName(name)
    helpText:setVisible(true)
    local _height = helpText:getContentSize().height
    for i, child in ipairs(children) do
        if child ~= helpText then
            child:setVisible(false)
        end
    end

    local ui_scrollView = ccui.Helper:seekNodeByName(UIAllianceHelp.Widget, "view")
    if _height < ui_scrollView:getContentSize().height then
        _height = ui_scrollView:getContentSize().height
    end
    ui_scrollView:setInnerContainerSize(cc.size(ui_scrollView:getContentSize().width, _height))
    helpText:setPositionY(ui_scrollView:getInnerContainerSize().height - 5)
end

function UIAllianceHelp.free()
    userData = nil
end

function UIAllianceHelp.show(_tableParams)
    userData = _tableParams
    UIManager.pushScene("ui_alliance_help")
end