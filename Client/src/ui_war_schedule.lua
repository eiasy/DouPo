UIWarSchedule = { }

local ui = UIWarSchedule

function ui.init()
    local btn_close = ccui.Helper:seekNodeByName(ui.Widget, "btn_close")
    local btn_info = ccui.Helper:seekNodeByName(ui.Widget, "btn_info")
    local btn_reward = ccui.Helper:seekNodeByName(ui.Widget, "btn_info_0")

    local function onButtonEvent(sender, eventType)
        if eventType == ccui.TouchEventType.ended then
            audio.playSound("sound/button.mp3")
            if sender == btn_info then
                UIWarList.showReport()
            elseif sender == btn_reward then
                UIWarList.showReward()
            elseif sender == btn_close then
                UIManager.popScene()
            end
        end
    end

    btn_close:addTouchEventListener(onButtonEvent)
    btn_info:addTouchEventListener(onButtonEvent)
    btn_reward:addTouchEventListener(onButtonEvent)

    local image_hint = ccui.Helper:seekNodeByName(ui.Widget, "image_hint")
    local image_win = image_hint:getChildByName("image_win")
    image_win:setLocalZOrder(4)
    local x, y = image_win:getPosition()

    for i = 1, 8 do
        local image_alliance = image_hint:getChildByName("image_alliance" .. i)
        image_alliance:setLocalZOrder(3)
    end

    local image_alliance1 = image_hint:getChildByName("image_alliance1")
    local image_alliance5 = image_hint:getChildByName("image_alliance5")

    local left = image_alliance1:getPositionX() + image_alliance1:getContentSize().width / 2 - 5 - x
    local right = image_alliance5:getPositionX() - image_alliance5:getContentSize().width / 2 + 5 - x
    local width = 22
    local config = { left = left, right = right, center = 0, width = width }

    for i = 1, 4 do
        config[i] = image_hint:getChildByName("image_alliance" .. i):getPositionY() - y
    end
    config[1] = math.ceil(config[1])
    config[4] = math.floor(config[4])
    config[5] =(config[1] + config[2]) / 2
    config[6] =(config[3] + config[4]) / 2
    config[7] =(config[5] + config[6]) / 2
    ui.config = config

    local redDraw = cc.DrawNode:create()
    redDraw:setName("redDraw")
    redDraw:setLocalZOrder(2)
    redDraw:setPosition(x, y)
    image_hint:addChild(redDraw)

    local grayDraw = cc.DrawNode:create()
    grayDraw:setName("grayDraw")
    grayDraw:setLocalZOrder(1)
    grayDraw:setPosition(x, y)
    image_hint:addChild(grayDraw)

    local grayColor = cc.c4f(150 / 255, 150 / 255, 150 / 255, 1.0)

    grayDraw:clear()
    for i = 1, 2 do
        local startX = i == 1 and left or right
        local offsetX = i == 1 and width or - width

        for k = 1, 4, 2 do
            grayDraw:drawPoly( { cc.p(startX, config[k]), cc.p(startX + offsetX, config[k]), cc.p(startX + offsetX, config[k + 1]), cc.p(startX, config[k + 1]) }, 4, false, grayColor)
        end

        startX = startX + offsetX
        grayDraw:drawPoly( { cc.p(startX, config[5]), cc.p(startX + offsetX, config[5]), cc.p(startX + offsetX, config[6]), cc.p(startX, config[6]) }, 4, false, grayColor)

        grayDraw:drawLine(cc.p(startX + offsetX, config[7]), cc.p(0, config[7]), grayColor)
    end
end

local function netCallbackfunc(pack)
    local msgdata = pack.msgdata

    local alliancelist = { }

    if msgdata.message and msgdata.message["0"] then
        for i = 0, 6 do
            local teamAB = msgdata.message[tostring(i)]

            local state = teamAB.int.state or UIWar.STATE_FINISH
            local isAWin = teamAB.int.isAWin == 1

            local teamA
            if i <= 3 then
                teamA = { }
                table.insert(teamA, tostring(teamAB.int.aId or 0))
                table.insert(teamA, teamAB.string and teamAB.string.aName or "")
                table.insert(alliancelist, teamA)
            elseif state == UIWar.STATE_FINISH and teamAB.int.aId then
                local id = tostring(teamAB.int.aId)
                for k, alliance in ipairs(alliancelist) do
                    if id == alliance[1] then
                        teamA = alliance
                        break
                    end
                end
            end

            if state == UIWar.STATE_FINISH and teamA then
                if not isAWin then
                    teamAB.string.aMvpName = ""
                end
                table.insert(teamA, teamAB.string.aMvpName or "")
                table.insert(teamA, teamAB.int.aScore1 or 0)
                table.insert(teamA, teamAB.int.aScore2 or 0)
                table.insert(teamA, teamAB.int.aScore3 or 0)
                table.insert(teamA, teamAB.int.aScore4 or 0)
            end

            local teamB
            if i <= 3 then
                teamB = { }
                table.insert(teamB, tostring(teamAB.int.bId or 0))
                table.insert(teamB, teamAB.string and teamAB.string.bName or "")
                table.insert(alliancelist, teamB)
            elseif state == UIWar.STATE_FINISH and teamAB.int.bId then
                local id = tostring(teamAB.int.bId)
                for k, alliance in ipairs(alliancelist) do
                    if id == alliance[1] then
                        teamB = alliance
                        break
                    end
                end
            end

            if state == UIWar.STATE_FINISH and teamB then
                if isAWin then
                    teamAB.string.bMvpName = ""
                end
                table.insert(teamB, teamAB.string.bMvpName or "")
                table.insert(teamB, teamAB.int.bScore1 or 0)
                table.insert(teamB, teamAB.int.bScore2 or 0)
                table.insert(teamB, teamAB.int.bScore3 or 0)
                table.insert(teamB, teamAB.int.bScore4 or 0)
            end
        end
    else
        for i = 1, 8 do
            table.insert(alliancelist, { 0, "" })
        end
    end

    local image_hint = ccui.Helper:seekNodeByName(ui.Widget, "image_hint")
    local redDraw = image_hint:getChildByName("redDraw")
    redDraw:clear()

    local image_win = image_hint:getChildByName("image_win")
    local x, y = image_win:getPosition()

    local redColor = cc.c4f(1.0, 7 / 255, 7 / 255, 1.0)

    local eighthfinal = { }
    local quarterfinal = { }
    local final = { }

    for i, data in ipairs(alliancelist) do
        local image_alliance = image_hint:getChildByName("image_alliance" .. i)
        local text_name = image_alliance:getChildByName("text_name")
        local image_state = image_alliance:getChildByName("image_state")

        local unionId = tonumber(data[1] or 0)
        local allianceName = data[2] or ""

        if unionId == 0 then
            text_name:hide()
            image_state:hide()
        else
            local config = ui.config
            local startX = i < 5 and config.left or config.right
            local offsetX = i < 5 and config.width or - config.width
            local cfgIdx = i < 5 and i or(i - 4)

            local index = 1
            local isWin = false
            for k = 3, #data, 5 do
                isWin =(data[k] or "") ~= ""

                local e = {
                    allianceName = allianceName,
                    mvp = data[k] or "",
                    points = { }
                }

                for m = 1, 4 do
                    e.points[m] = tonumber(data[k + m] or 0)
                end

                if index == 1 then
                    eighthfinal[i] = e
                    if isWin then
                        redDraw:drawPoly( { cc.p(startX +(index - 1) * offsetX, config[cfgIdx]), cc.p(startX + index * offsetX, config[cfgIdx]), cc.p(startX + index * offsetX, config[(cfgIdx <= 2 and 1 or 2) + 4]) }, 3, false, redColor)
                    end
                elseif index == 2 then
                    quarterfinal[math.ceil(i / 2)] = e
                    if isWin then
                        redDraw:drawPoly( { cc.p(startX +(index - 1) * offsetX, config[(cfgIdx <= 2 and 1 or 2) + 4]), cc.p(startX + index * offsetX, config[(cfgIdx <= 2 and 1 or 2) + 4]), cc.p(startX + index * offsetX, config[7]) }, 3, false, redColor)
                    end
                else
                    final[math.ceil(i / 4)] = e
                    if isWin then
                        redDraw:drawLine(cc.p(startX +(index - 1) * offsetX, config[7]), cc.p(config.center, config[7]), redColor)
                    end
                end

                index = index + 1
                if not isWin then break end
            end

            image_alliance:loadTexture(unionId == net.InstUnionMember.int["2"] and "ui/lm_zbsc_me.png" or "ui/lm_zdsc_other.png")
            utils.GrayWidget(image_alliance, not isWin and #data > 2)
            text_name:show():setString(allianceName)

            if #data <= 2 then
                image_state:hide()
                local color = unionId == net.InstUnionMember.int["2"] and cc.c3b(12, 247, 255) or cc.c3b(239, 255, 60)
                text_name:setTextColor(color)
            else
                image_state:show():loadTexture(isWin and "ui/lm_zb_shengli.png" or "ui/lm_zb_shibai.png")
                if isWin then
                    text_name:setTextColor(unionId == net.InstUnionMember.int["2"] and cc.c3b(12, 247, 255) or cc.c3b(239, 255, 60))
                else
                    local color = unionId == net.InstUnionMember.int["2"] and cc.c3b(12, 247, 255) or cc.c3b(239, 255, 60)
                    local gray = math.floor((color.r + color.g + color.b) / 3)
                    text_name:setTextColor(cc.c3b(gray, gray, gray))
                end
            end
        end
    end

    local emptyAlliance = {
        allianceName = "",
        mvp = nil,
        points = { 0, 0, 0, 0 }
    }
    local _eighthfinal = { }
    for i = 1, 8, 2 do
        if eighthfinal[i] or eighthfinal[i + 1] then
            table.insert(_eighthfinal, { eighthfinal[i] or emptyAlliance, eighthfinal[i + 1] or emptyAlliance })
        end
    end
    local _quarterfinal = { }
    for i = 1, 4, 2 do
        if quarterfinal[i] or quarterfinal[i + 1] then
            table.insert(_quarterfinal, { quarterfinal[i] or emptyAlliance, quarterfinal[i + 1] or emptyAlliance })
        end
    end
    local _final = { }
    for i = 1, 2, 2 do
        if final[i] or final[i + 1] then
            table.insert(_final, { final[i] or emptyAlliance, final[i + 1] or emptyAlliance })
        end
    end
    UIWarList.report = { _eighthfinal, _quarterfinal, _final }
    UIWarList.reward = { utils.stringSplit(msgdata.string.reward8 or "", "/"), utils.stringSplit(msgdata.string.reward4 or "", "/"), utils.stringSplit(msgdata.string.reward2 or "", "/") }
end

function ui.setup()
    UIManager.showLoading()
    netSendPackage( { header = StaticMsgRule.unionSchedule, msgdata = { } }, netCallbackfunc, netCallbackfunc)
end