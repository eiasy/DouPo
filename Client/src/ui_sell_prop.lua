UISellProp = {}

local ui_selectedNum = nil --需要卖出的数量
local ui_predictMoney = nil --预计获得金钱数
local _uiItem = nil
local _thingInstData = nil --物品实例数据
local _haveNum = nil --拥有数量
local _unitPrice = nil --单价
local _callbackFunc = nil

local function netCallbackFunc(data)
	AudioEngine.playEffect("sound/gold.mp3")
	local code = tonumber(data.header)
    if code == StaticMsgRule.fightSoulBuySilver then
        UIManager.showToast("购买成功")
        if _uiItem == UISoulGet then
            UIManager.flushWidget(UISoulGet)
		end
	elseif code == StaticMsgRule.sell then
		UIManager.showToast("恭喜你获得" .. ui_predictMoney:getString() .."银币")
		if _uiItem == UIBag then
			UIManager.flushWidget(UIBag)
			UIManager.flushWidget(UITeamInfo)
		elseif _uiItem == UIBagEquipmentSell then
			UIManager.flushWidget(UITeamInfo)
			UIManager.flushWidget(UIBagEquipmentSell)
        elseif _uiItem == UIBagWingSell then
            UIManager.flushWidget( UIBagWingSell )
            UIManager.flushWidget( UIBagWing )
        elseif _uiItem == UISoulGet then
            UIManager.flushWidget(UISoulGet)
		end
		UIManager.flushWidget(UISellProp)
	elseif code == StaticMsgRule.deletePillRecipe then
		UIManager.flushWidget(UIDanFang)
		UIManager.showToast("恭喜你获得" .. ui_predictMoney:getString() .."银币")
	elseif code == StaticMsgRule.deletePill or code == StaticMsgRule.deletePillThing then
		UIManager.flushWidget(UIDanYao)
		UIManager.showToast("恭喜你获得" .. ui_predictMoney:getString() .."银币")
	elseif code  == StaticMsgRule.buy then
        if _callbackFunc then
            _callbackFunc()
        else
		    if  DictThing[tostring(_thingInstData.int["thingId"])].bagTypeId == 1  then
			    UIShop.isFlush = true
			    UIShop.getShopList(1)
		    elseif DictThing[tostring(_thingInstData.int["thingId"])].bagTypeId == 2 then
			    UIManager.flushWidget(UIShop)
		    end
		    UIManager.showToast("购买成功,请到背包中查看")
            if DictThing[tostring(_thingInstData.int["thingId"])].name == "至尊宝盒" then --加统计消耗
                cclog("统计至尊宝盒")
                local item = { "至尊宝盒" , tonumber(ui_selectedNum:getString()) , tonumber( DictThing[tostring(_thingInstData.int["thingId"])].buyGold ) }
                SDK.tdDoOnPurchase( item )
            end
        end
	elseif code == StaticMsgRule.thingUse then 
		local _dictThingId = _thingInstData.int["3"]
		local usePrompt = ""
		if StaticThing.energyPill == _dictThingId then 
		  usePrompt = "+" .. ui_predictMoney:getString() .. " 体力"
		elseif StaticThing.vigorPill == _dictThingId then 
		  usePrompt = "+" .. ui_predictMoney:getString() .. " 耐力"
		elseif StaticThing.silverNote5000 == _dictThingId or StaticThing.silverNote10000 == _dictThingId then 
		  usePrompt = "+" .. ui_predictMoney:getString() .. " 银币"
		end
		UIManager.showToast(usePrompt)
		UIManager.flushWidget(UIBag)
		UIManager.flushWidget(UITeamInfo)
	elseif code == StaticMsgRule.arenaConvert then
		UIManager.showToast("兑换成功！")
		UIArena.refreshExchangeList()
    elseif code == StaticMsgRule.store then
        UIManager.showToast("兑换成功！")
        UIManager.flushWidget(UITowerShop)
    elseif code == StaticMsgRule.exchangeBossShop then
        if _callbackFunc then
            _callbackFunc(tonumber(ui_selectedNum:getString()))
        end
    elseif code == StaticMsgRule.buyGroupBox then
        if _callbackFunc then
            _callbackFunc()
        end
	end
	UIManager.popScene()
end

local function sendData()
	local sendData = nil
	if _uiItem == UIDanFang then
		sendData = {
			header = StaticMsgRule.deletePillRecipe,
			msgdata = {
				int = {
					instPlayerPillRecipeId = _thingInstData.int["1"],
					num = tonumber(ui_selectedNum:getString())
				}
			}
		}
	elseif _uiItem == UIDanYao then
		if _uiItem.getShowType() == _uiItem.ShowType.ShowDanYao then
			sendData = {
				header = StaticMsgRule.deletePill,
				msgdata = {
					int = {
						instPlayerPillId = _thingInstData.int["1"],
						num = tonumber(ui_selectedNum:getString())
					}
				}
			}
		elseif _uiItem.getShowType() == _uiItem.ShowType.ShowYaoCai then
			sendData = {
				header = StaticMsgRule.deletePillThing,
				msgdata = {
					int = {
						instPlayerPillThingId = _thingInstData.int["1"],
						num = tonumber(ui_selectedNum:getString())
					}
				}
			}
		end
	elseif _uiItem == UIBag then
		sendData = {
			header = StaticMsgRule.sell,
			msgdata = {
				int = {
					buyNum = tonumber(ui_selectedNum:getString()),
					type = 1,
				},
				string = {
					sellIds  = _thingInstData.int["1"],
				}
			}
		}
    elseif _uiItem == UIBagWingSell then
        sendData = {
			header = StaticMsgRule.sell,
			msgdata = {
				int = {
					buyNum = tonumber(ui_selectedNum:getString()),
					type = 1,
				},
				string = {
					sellIds  = _thingInstData.int["1"],
				}
			}
		}
	elseif _uiItem == "UIBagUse" then
		sendData = {
	      header = StaticMsgRule.thingUse,
	      msgdata = {
	        int = {
	          instPlayerThingId   = _thingInstData.int["1"] ,
	          num                 = tonumber(ui_selectedNum:getString()),
	        }
	      }
	    }
	elseif _uiItem == UIBagEquipmentSell then
		sendData = {
			header = StaticMsgRule.sell,
			msgdata = {
				int = {
					buyNum = tonumber(ui_selectedNum:getString()),
					type = 3,
				},
				string={
					sellIds  = _thingInstData.int["1"],
				}
			}
		}
	elseif _uiItem == UIShop then
		sendData = {
			header = StaticMsgRule.buy,
			msgdata = {
				int = {
					thingId = _thingInstData.int["thingId"],
					type = DictThing[tostring(_thingInstData.int["thingId"])].bagTypeId,
					num = tonumber(ui_selectedNum:getString()),
				}
			}
		}
	elseif _uiItem == UIArena then
		sendData = {
			header = StaticMsgRule.arenaConvert, 
				msgdata = {
					int = {
						instPlayerArenaId = net.InstPlayerArena.int["1"], --玩家竞技场实例Id
						arenaConvertId = _thingInstData.id, --竞技场兑换字典Id
						convertNum = tonumber(ui_selectedNum:getString()) --兑换次数
					}
				}
			}
    elseif _uiItem == UITowerShop then
        sendData = {
            header = StaticMsgRule.store ,
            msgdata = {
                int = {
				    instPlayerPagodaId = net.InstPlayerPagoda.int["1"],
				    dictPagodaStoreId = _thingInstData.id ,
                    num = tonumber(ui_selectedNum:getString()) --兑换次数
			    }
            }
        }
    elseif _uiItem == UISoulGet then
        if _thingInstData.type == 1 then
            sendData = {
                header = StaticMsgRule.fightSoulBuySilver ,
                msgdata = {
                    int = {				        
                        num = tonumber(ui_selectedNum:getString()) --兑换次数
			        }
                }
            }
        elseif _thingInstData.type == 2 then
            sendData = {
			    header = StaticMsgRule.sell,
			    msgdata = {
				    int = {
					    buyNum = tonumber(ui_selectedNum:getString()),
					    type = 1,
				    },
				    string = {
					    sellIds  = _thingInstData.thingsId,
				    }
			    }
		    }
        end
    elseif _uiItem == UIBossShop then
        sendData = {
            header = StaticMsgRule.exchangeBossShop ,
            msgdata = {
                int = {
				    bossShopId = _thingInstData.id ,
                    num = tonumber(ui_selectedNum:getString()) --兑换次数
			    }
            }
        }
    elseif _uiItem == UIActivityPurchaseTrade then
        sendData = {
            header = StaticMsgRule.buyGroupBox ,
            msgdata = {
                int = {
                    num = tonumber(ui_selectedNum:getString()) --购买次数
			    }
            }
        }
	end
	UIManager.showLoading()
	netSendPackage(sendData, netCallbackFunc)
end

function UISellProp.init()
	local btn_close = ccui.Helper:seekNodeByName(UISellProp.Widget, "btn_close")
	local ui_image_base_sell = ccui.Helper:seekNodeByName(UISellProp.Widget, "image_base_sell")
	local ui_sub10 = ccui.Helper:seekNodeByName(ui_image_base_sell, "btn_cut_ten")
	local ui_sub = ccui.Helper:seekNodeByName(ui_image_base_sell, "btn_cut")
	local ui_add10 = ccui.Helper:seekNodeByName(ui_image_base_sell, "btn_add_ten")
	local ui_add = ccui.Helper:seekNodeByName(ui_image_base_sell, "btn_add")
	ui_selectedNum = ccui.Helper:seekNodeByName(ui_image_base_sell, "text_number")
	local ui_image_base_earnings = ccui.Helper:seekNodeByName(UISellProp.Widget, "image_base_earnings_info")
	ui_predictMoney = ccui.Helper:seekNodeByName(ui_image_base_earnings, "text_number_predict")
	local btn_sure = ccui.Helper:seekNodeByName(UISellProp.Widget, "btn_sure")
	local btn_undo = ccui.Helper:seekNodeByName(UISellProp.Widget, "btn_undo")

	btn_close:setPressedActionEnabled(true)
	btn_sure:setPressedActionEnabled(true)
	btn_undo:setPressedActionEnabled(true)
	ui_sub10:setPressedActionEnabled(true)
	ui_sub:setPressedActionEnabled(true)
	ui_add10:setPressedActionEnabled(true)
	ui_add:setPressedActionEnabled(true)
	local function btnTouchEvent(sender, eventType)
		if eventType == ccui.TouchEventType.ended then
			AudioEngine.playEffect("sound/button.mp3")
			if sender == btn_close or sender == btn_undo then
				UIManager.popScene()
			elseif sender == btn_sure then
				if tonumber(ui_selectedNum:getString()) > 0 then
					if _uiItem == UIShop or _uiItem == UIActivityPurchaseTrade then
						if net.InstPlayer.int["5"] >=	tonumber(ui_predictMoney:getString()) then
							sendData()
						else
							UIManager.showToast("元宝不足！")
						end
					elseif _uiItem == UIArena then
						if net.InstPlayerArena.int["7"] >=	tonumber(ui_predictMoney:getString()) then
							sendData()
						else
							UIManager.showToast("威望不足！")
						end
                    elseif _uiItem == UITowerShop then
                        if net.InstPlayer.int["21"] >= tonumber(ui_selectedNum:getString()) * _unitPrice then 
                            sendData()
                        else
                            UIManager.showToast("火能石不足！")
                        end
                    elseif _uiItem == UIBossShop then
                        if _thingInstData.bossIntergral >= tonumber(ui_selectedNum:getString()) * _unitPrice then 
                            sendData()
                        else
                            UIManager.showToast("屠魔积分不足！")
                        end
					else
						sendData()
					end
				else
					if _uiItem == UIShop or _uiItem == UIActivityPurchaseTrade then
						UIManager.showToast("请选择购买的数量！")
					elseif _uiItem == UIArena then
						UIManager.showToast("请选择兑换的次数！")
					elseif _uiItem == "UIBagUse" then
						UIManager.showToast("请选择使用的数量！")
                    elseif _uiItem == UITowerShop then
                        UIManager.showToast("请选择兑换的数量！")
                    elseif _uiItem == UISoulGet then
                        if _thingInstData.type == 1 then
                            UIManager.showToast("请选择购买的数量！")
                        else
                            UIManager.showToast("请选择使用的数量！")
                        end
                    elseif _uiItem == UIBossShop then
                        UIManager.showToast("请选择兑换的数量！")
					else
						UIManager.showToast("请选择出售的数量！")
					end
				end
			end
		end
	end
	btn_close:addTouchEventListener(btnTouchEvent)
	btn_sure:addTouchEventListener(btnTouchEvent)
	btn_undo:addTouchEventListener(btnTouchEvent)

	local function addOrcutEvent(sender, eventType)
		if eventType == ccui.TouchEventType.ended then
			local number = tonumber(ui_selectedNum:getString())
			local canBuyNum = math.floor(net.InstPlayer.int["5"]/_unitPrice)
            if _uiItem == UIShop or _uiItem == UIActivityPurchaseTrade then
                canBuyNum = math.floor(net.InstPlayer.int["5"]/math.round( _unitPrice * UIShop.disCount ))
            end
			if sender == ui_sub10 then
				if number >= 10 then
					number = number - 10
				elseif number > 1 and number < 10 then 
					number = 1 
				end
			elseif sender == ui_sub then
				if number > 0 then
					number = number - 1
				end
			elseif sender == ui_add10 then
				if _uiItem == UIShop or _uiItem == UIActivityPurchaseTrade then
					if _thingInstData.int and _thingInstData.int["canBuyNum"] ~= nil  and  _thingInstData.int["canBuyNum"] ~= -1 then
						if number +10  <= _thingInstData.int["canBuyNum"] then
							number = number + 10
						elseif number > _thingInstData.int["canBuyNum"] - 10 and number < _thingInstData.int["canBuyNum"] then
                            number = _thingInstData.int["canBuyNum"]
                        else
							UIManager.showToast("今日只能购买" .. _thingInstData.int["canBuyNum"] .. "个")
						end
					else
						if number + 10 <= canBuyNum then 
							number = number + 10
						elseif number > canBuyNum - 10 and number < canBuyNum then 
							number = canBuyNum 
						else 
							UIManager.showToast("元宝不足！")
						end
					end
				elseif _uiItem == UIArena then
					if number + 10  <= _thingInstData.convertNum then
						number = number + 10
                    elseif number > _thingInstData.convertNum - 10 and number < _thingInstData.convertNum then
                        number = _thingInstData.convertNum
					else
						UIManager.showToast("今日只能兑换" .. _thingInstData.convertNum .. "次")
					end
                elseif _uiItem == UITowerShop then
                    if number <= math.floor( net.InstPlayer.int["21"]/_unitPrice ) - 10 then
                        number = number + 10
                    elseif number < math.floor( net.InstPlayer.int["21"]/_unitPrice ) then
                        number = math.floor(net.InstPlayer.int["21"]/_unitPrice)
                    else
                        UIManager.showToast("火能石不足！")
                    end
                elseif _uiItem == UISoulGet then
                    if _thingInstData.type == 1 then
                        if number < _thingInstData.num - 10 then
                            number = number + 10
                        elseif number < _thingInstData.num then
                            number = _thingInstData.num     
                        else
							UIManager.showToast("VIP购买次数已到")                 
                        end
                    elseif _thingInstData.type == 2 then
                        if number < _thingInstData.num - 10 then
                            number = number + 10
                        elseif number < _thingInstData.num then
                            number = _thingInstData.num
                        end
                    end
                elseif _uiItem == UIBossShop then
                    if number <= math.floor( _thingInstData.bossIntergral / _unitPrice ) - 10 then
                        number = number + 10
                    elseif number < math.floor( _thingInstData.bossIntergral / _unitPrice ) then
                        number = math.floor(_thingInstData.bossIntergral / _unitPrice)
                    else
                        UIManager.showToast("屠魔积分不足！")
                    end
				else
					if number <= _haveNum - 10 then
						number = number + 10
					elseif number > _haveNum - 10 and number < _haveNum then 
						number = _haveNum
					else
						UIManager.showToast("数量已达上限！")
					end
				end
			elseif sender == ui_add then
				if _uiItem == UIShop or _uiItem == UIActivityPurchaseTrade then
					if _thingInstData.int and _thingInstData.int["canBuyNum"] ~= nil  and  _thingInstData.int["canBuyNum"] ~= -1 then
						if number < _thingInstData.int["canBuyNum"] then
							number = number + 1
						else
							UIManager.showToast("今日只能购买" .. _thingInstData.int["canBuyNum"] .. "个")
						end
					else
						if number < canBuyNum then 
							number = number + 1
						else 
							UIManager.showToast("元宝不足！")
						end
					end
				elseif _uiItem == UIArena then
					if number  < _thingInstData.convertNum then
						number = number + 1
					else
						UIManager.showToast("今日只能兑换" .. _thingInstData.convertNum .. "次")
					end
                elseif _uiItem == UITowerShop then
                    if number < math.floor( net.InstPlayer.int["21"]/_unitPrice ) then
                        number = number + 1
                    else
                        UIManager.showToast("火能石不足！")
                    end
                elseif _uiItem == UISoulGet then
                    if _thingInstData.type == 1 then
                        if number < _thingInstData.num then
                            number = number + 1
                        else
							UIManager.showToast("VIP购买次数已到")     
                        end
                    elseif _thingInstData.type == 2 then
                        if number < _thingInstData.num then
                            number = number + 1
                        end
                    end
                elseif _uiItem == UIBossShop then
                    if number < math.floor( _thingInstData.bossIntergral / _unitPrice ) then
                        number = number + 1
                    else
                        UIManager.showToast("屠魔积分不足！")
                    end
				else
					if number < _haveNum then
						number = number + 1
					else
						UIManager.showToast("数量已达上限！")
					end
				end
			end
			ui_selectedNum:setString(tostring(number))
			if _uiItem == UIShop and (_thingInstData.int["thingId"] == StaticThing.vigorPill or _thingInstData.int["thingId"] == StaticThing.energyPill) then
			    local _buyPrice = 0
                local _todayBuyNum = _thingInstData.int["todayBuyNum"] + number
                local _extend = utils.stringSplit(DictThingExtend[tostring(_thingInstData.int["thingId"])].extend, ";")
                for _i = _thingInstData.int["todayBuyNum"] + 1, _todayBuyNum do
	                for _k, _o in pairs(_extend) do
	                  local _tempO = utils.stringSplit(_o, "_")
	                  if _i >= tonumber(_tempO[1]) and _i <= tonumber(_tempO[2]) then
	                    _buyPrice = _buyPrice + tonumber(_tempO[3])
	                    break
	                  end
	                end
      	        end
                ui_predictMoney:setString(tostring(math.round( _buyPrice * UIShop.disCount ) ))
            elseif _uiItem == UISoulGet and _thingInstData.type == 1 then
                local ui_textHint = ccui.Helper:seekNodeByName(UISellProp.Widget, "text_hint")
                ui_textHint:setString("购买"..number*DictSysConfig[tostring(StaticSysConfig.silverNoteToCopper)].value.."银币")
                ui_predictMoney:setString(tostring(number * _unitPrice))
            elseif _uiItem == UIShop then
                ui_predictMoney:setString(tostring( math.round( number * _unitPrice * UIShop.disCount )))
            else
				ui_predictMoney:setString(tostring(number * _unitPrice))
			end
		end
	end
	ui_sub10:addTouchEventListener(addOrcutEvent)
	ui_sub:addTouchEventListener(addOrcutEvent)
	ui_add10:addTouchEventListener(addOrcutEvent)
	ui_add:addTouchEventListener(addOrcutEvent)
end

function UISellProp.setup()
	UISellProp.Widget:setEnabled(true)
	local ui_imageIcon =ccui.Helper:seekNodeByName(UISellProp.Widget, "image_get")
	local ui_moneyTotalText= ccui.Helper:seekNodeByName(UISellProp.Widget, "text_get_predict")
	local ui_image_hint =  ccui.Helper:seekNodeByName(UISellProp.Widget, "image_base_hint_price")
	local ui_title = ccui.Helper:seekNodeByName(UISellProp.Widget, "text_sell")
	local ui_haveNum = ccui.Helper:seekNodeByName(UISellProp.Widget, "text_have_number")
	local ui_textHint = ccui.Helper:seekNodeByName(UISellProp.Widget, "text_hint")
	ui_selectedNum:setString("1")
	if _thingInstData then
		if _uiItem == UIDanFang or _uiItem == UIDanYao then
			local dictData = nil
			if _uiItem == UIDanFang then
				ui_title:setString("出售丹方")
				dictData = DictPillRecipe[tostring(_thingInstData.int["3"])]
			elseif _uiItem == UIDanYao then
				if _uiItem.getShowType() == _uiItem.ShowType.ShowDanYao then
					ui_title:setString("出售丹药")
					dictData = DictPill[tostring(_thingInstData.int["3"])]
				elseif _uiItem.getShowType() == _uiItem.ShowType.ShowYaoCai then
					ui_title:setString("出售药材")
					dictData = DictPillThing[tostring(_thingInstData.int["3"])]
				end
			end
			ui_imageIcon:loadTexture("ui/yin.png")
			_haveNum = _thingInstData.int["4"]
			ui_haveNum:setVisible(true)
			ui_haveNum:setString("拥有量： " .. _haveNum)
			ui_moneyTotalText:setString("预计获得：")
			ui_textHint:setString(string.format("请选择出售的%s数量", dictData.name))
			_unitPrice = dictData.sellCopper
			ui_image_hint:setVisible(false)
		elseif _uiItem == UIBag or _uiItem == UIBagEquipmentSell  then
			if _uiItem == UIBag then
				ui_title:setString("出售道具")
			else
				ui_title:setString("出售装备碎片")
			end
			ui_imageIcon:loadTexture("ui/yin.png")
			_haveNum = _thingInstData.int["5"]
			if _thingInstData.int["3"] == StaticThing.goldBox then
		      _haveNum = _haveNum + _thingInstData.int["4"]
		    end
			ui_haveNum:setVisible(true)
			ui_haveNum:setString("拥有量： " .. _haveNum)
			ui_moneyTotalText:setString("预计获得：")
			ui_textHint:setString(string.format("请选择出售的%s数量", DictThing[tostring(_thingInstData.int["3"])].name))
			_unitPrice = DictThing[tostring(_thingInstData.int["3"])].sellCopper
			ui_image_hint:setVisible(false)
        elseif _uiItem == UIBagWingSell then
            ui_title:setString("出售神羽碎片")
            ui_imageIcon:loadTexture("ui/yin.png")
            _haveNum = _thingInstData.int["5"]
            ui_haveNum:setVisible(true)
			ui_haveNum:setString("拥有量： " .. _haveNum)
			ui_moneyTotalText:setString("预计获得：")
			ui_textHint:setString(string.format("请选择出售的%s数量", DictThing[tostring(_thingInstData.int["3"])].name))
			_unitPrice = DictThing[tostring(_thingInstData.int["3"])].sellCopper
		elseif _uiItem == "UIBagUse" then ---背包物品的使用
			ui_title:setString("使用道具")
			local _dictThingId = _thingInstData.int["3"]
			if StaticThing.energyPill == _dictThingId then 
              _unitPrice = DictSysConfig[tostring(StaticSysConfig.energyPillEnergy)].value
              ui_imageIcon:loadTexture("ui/zd_tili.png")
            elseif StaticThing.vigorPill == _dictThingId then 
              _unitPrice = DictSysConfig[tostring(StaticSysConfig.vigorPillVigor)].value
              ui_imageIcon:loadTexture("ui/zd_naili.png")
            elseif StaticThing.silverNote10000 == _dictThingId then 
              _unitPrice = DictThing[tostring(_dictThingId)].sellCopper
              ui_imageIcon:loadTexture("ui/yin.png")            
            elseif StaticThing.silverNote5000 == _dictThingId then 
              _unitPrice = DictThing[tostring(_dictThingId)].sellCopper
              ui_imageIcon:loadTexture("ui/yin.png")
            end
			_haveNum = _thingInstData.int["5"]
			ui_haveNum:setVisible(true)
			ui_haveNum:setString("拥有量： " .. _haveNum)
			ui_moneyTotalText:setString("预计获得：")
			ui_textHint:setString(string.format("请选择使用的%s数量", DictThing[tostring(_dictThingId)].name))
			ui_image_hint:setVisible(false)
		elseif _uiItem == UIShop then
			ui_haveNum:setVisible(false)
			ui_moneyTotalText:setString("总价：")
			if _thingInstData.int["thingId"] == StaticThing.vigorPill or _thingInstData.int["thingId"] == StaticThing.energyPill then
                local _todayBuyPrice = 0
                local _todayBuyNum = _thingInstData.int["todayBuyNum"] + 1
                local _extend = utils.stringSplit(DictThingExtend[tostring(_thingInstData.int["thingId"])].extend, ";")
                for _k, _o in pairs(_extend) do
                  local _tempO = utils.stringSplit(_o, "_")
                  if _todayBuyNum >= tonumber(_tempO[1]) and _todayBuyNum <= tonumber(_tempO[2]) then
                    _todayBuyPrice = tonumber(_tempO[3])
                    break
                  end
                end
                _unitPrice = _todayBuyPrice
            else
				_unitPrice = _thingInstData.int["price"]
			end
			ui_imageIcon:loadTexture("ui/jin.png")
			ui_textHint:setString(string.format("请选择购买%s的数量", DictThing[tostring(_thingInstData.int["thingId"])].name))
			if  DictThing[tostring(_thingInstData.int["thingId"])].bagTypeId == 1 and DictThing[tostring(_thingInstData.int["thingId"])].thingTypeId ~= 3 then
				ui_title:setString("购买道具")
                if _thingInstData.int["thingId"] == StaticThing.energyPill or _thingInstData.int["thingId"] == StaticThing.vigorPill then
				    ui_image_hint:setVisible(true)
                end
			elseif DictThing[tostring(_thingInstData.int["thingId"])].bagTypeId == 2 and DictThing[tostring(_thingInstData.int["thingId"])].thingTypeId ~= 3 then
				ui_title:setString("购买魔核")
				ui_image_hint:setVisible(false)
			end
		elseif _uiItem == UIArena then
			local itemName = ""
			if _thingInstData.tableTypeId == StaticTableType.DictPill then
				local dictPill = DictPill[tostring(_thingInstData.tableFieldId)]
				itemName = dictPill.name
			elseif _thingInstData.tableTypeId == StaticTableType.DictThing then
				local dictThing = DictThing[tostring(_thingInstData.tableFieldId)]
				itemName = dictThing.name
			elseif _thingInstData.tableTypeId == StaticTableType.DictEquipment then
				local dictEquipment = DictEquipment[tostring(_thingInstData.tableFieldId)]
				itemName = dictEquipment.name
			elseif _thingInstData.tableTypeId == StaticTableType.DictCard then
				local dictCard = DictCard[tostring(_thingInstData.tableFieldId)]
				itemName = dictCard.name
			elseif _thingInstData.tableTypeId == StaticTableType.DictCardSoul then
				local dictCardSoul = DictCardSoul[tostring(_thingInstData.tableFieldId)]
				itemName = dictCardSoul.name
			elseif _thingInstData.tableTypeId == StaticTableType.DictChip then
				local dictChip = DictChip[tostring(_thingInstData.tableFieldId)]
				itemName = dictChip.name
			end
			_haveNum = 0
			if net.InstPlayerArena then
				_haveNum = net.InstPlayerArena.int["7"]
			end
			ui_title:setString("兑换道具")
			ui_imageIcon:loadTexture("ui/weiwang.png")
			ui_haveNum:setVisible(false)
			ui_moneyTotalText:setString("需要威望：")
			ui_textHint:setString(string.format("请选择兑换%s的次数", itemName))
			_unitPrice = _thingInstData.prestige
        elseif _uiItem == UITowerShop then
            local itemThing = utils.getItemProp(_thingInstData.tableTypeId, _thingInstData.tableFieldId)
            ui_title:setString("兑换道具")
			ui_imageIcon:loadTexture("ui/small_xiuwei.png")
			ui_haveNum:setVisible(false)
			ui_moneyTotalText:setString("需要火能：")
			ui_textHint:setString(string.format("请选择兑换%s的个数", itemThing.name))
			_unitPrice = _thingInstData.culture
        elseif _uiItem == UISoulGet then
            local image_hint =  ccui.Helper:seekNodeByName(UISellProp.Widget, "image_base_hint")
            image_hint:setPosition( cc.p( image_hint:getPositionX() + 110 , image_hint:getPositionY() ) )
            ui_textHint:setAnchorPoint( cc.p( 0.5 , 0.5 ) )
            ui_textHint:setPosition( cc.p( ui_textHint:getPositionX() + 165 , ui_textHint:getPositionY() ) )
            if _thingInstData.type == 1 then
                ui_title:setString("购买银币")
                ui_textHint:setString("购买"..tonumber(ui_selectedNum:getString())*DictSysConfig[tostring(StaticSysConfig.silverNoteToCopper)].value.."银币")
                ui_imageIcon:loadTexture("ui/jin.png")
			    ui_haveNum:setVisible(false)
			    ui_moneyTotalText:setString("总价：")
                _unitPrice = math.round( DictThing[tostring(StaticThing.silverNote10000)].buyGold * UIShop.disCount )
            elseif _thingInstData.type == 2 then
                ui_title:setString("使用银票")
                ui_textHint:setString("请选择使用银票数量")
                ui_imageIcon:loadTexture("ui/yin.png")
			    ui_haveNum:setVisible(false)
			    ui_moneyTotalText:setString("可获得银币：")
                _unitPrice = DictSysConfig[tostring(StaticSysConfig.silverNoteToCopper)].value
            end               				
		elseif _uiItem == UIBossShop then
            local _thingsData = utils.stringSplit(_thingInstData.things, "_")
            local itemThing = utils.getItemProp(tonumber(_thingsData[1]), tonumber(_thingsData[2]))
            ui_title:setString("兑换道具")
			ui_imageIcon:loadTexture("ui/boss_integral.png")
			ui_haveNum:setVisible(false)
			ui_moneyTotalText:setString("需要屠魔积分：")
			ui_textHint:setString(string.format("请选择兑换%s的个数", itemThing.name))
			_unitPrice = _thingInstData.needbossIntegral
        elseif _uiItem == UIActivityPurchaseTrade then
            ui_title:setString("购买道具")
            ui_haveNum:setVisible(false)
			ui_moneyTotalText:setString("总价：")
            _unitPrice = _thingInstData.price
            ui_imageIcon:loadTexture("ui/jin.png")
			ui_textHint:setString(string.format("请选择购买%s的数量", _thingInstData.thingData.name))
		end
	end
    if _uiItem == UIShop then
        ui_predictMoney:setString(math.round( tonumber(ui_selectedNum:getString())*_unitPrice * UIShop.disCount ))
    else
	    ui_predictMoney:setString(tonumber(ui_selectedNum:getString())*_unitPrice)
    end
end

function UISellProp.setData(thingInstData, uiItem, callbackFunc)
	_thingInstData = thingInstData
	_uiItem = uiItem
    _callbackFunc = callbackFunc
end

function UISellProp.free()
	_uiItem = nil
	_thingInstData = nil
	_haveNum = nil
	_unitPrice = nil
    _callbackFunc = nil
end