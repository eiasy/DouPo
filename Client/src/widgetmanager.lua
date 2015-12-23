require "test_login"
require "ui_login"
require "ui_login_choose"
require "ui_activation"
require "ui_loading"
require "ui_notice"
require "ui_menu"
require "ui_team_info"
require "ui_homepage"
require "ui_lineup"
require "ui_lineup_dialog"
require "ui_card_change"
require "ui_card_info"
require "ui_card_upgrade"
require "ui_card_advance"
require "ui_card_realm"
require "ui_bag_equipment_sell"
require "ui_equipment_info"
require "ui_equipment_intensify"
require "ui_equipment_clean"
-- require "ui_fire_get"
-- require "ui_fire"
-- require "ui_fire_spallation"
-- require "ui_fire_upgrade"
-- require "ui_fire_inherit"
-- require "ui_fire_bage"
-- require "ui_fire_info"
-- require "ui_fire_list"
require "ui_resolve"
require "ui_resolve_list"
require "ui_medicine"
require "ui_medicine_alchemy"
require "ui_liandan"
require "ui_danfang"
require "ui_danyao"
require "ui_bag"
require "ui_sell_prop"
require "ui_shop"
require "ui_gem_inlay"
require "ui_gem_switch"
require "ui_gem_upgrade"
require "ui_bag_equipment"
require "ui_activity_tower"
require "ui_tower_test"
require "ui_tower_challenge"
require "ui_tower_preview"
require "ui_tower_win"
require "ui_gem_list"
require "ui_shop_recruit_jewel"
require "ui_shop_recruit_preview"
require "ui_shop_recruit_ten"
require "ui_fight"
require "ui_fight_win"
require "ui_fight_fail"
require "ui_bag_card_sell"
require "ui_fight_preview"
require "ui_fight_task"
require "ui_fight_task_choose"
require "ui_fight_get_accident"
require "ui_fight_clearing"
require "ui_fight_upgrade"
require "ui_fight_activity_choose"
require "ui_bag_card"
require "ui_bag_gongfa"
require "ui_lineup_embattle"
require "ui_loot_choose"
require "ui_loot_fight"
require "ui_loot"
require "ui_loot_hint"
require "ui_friend"
-- require "ui_bag_skill"
-- require "ui_bag_skill_choose"
-- require "ui_bag_skill_hint"
-- require "ui_bag_skill_upgrade"
require "ui_activity_miteer"
require "ui_activity_panel"
require "ui_activity_heijiaoyu"
require "ui_guide_info"
require "ui_fight_main"
require "ui_acticity_miteer_hint"
require "ui_award_gift"
require "ui_award_online"
require "ui_award_sign"
require "ui_arena"
require "ui_arena_check"
require "ui_arena_history"
require "ui_guide_people"
require "ui_guide_system"
require "ui_award_get"
require "ui_bag_gongfa_list"
require "ui_gongfa_info"
require "ui_gongfa_intensify"
require "ui_activity_bath"
require "ui_boss"
require "ui_boss_preview"
require "ui_boss_ranking"
require "ui_task_day"
require "ui_team"
require "ui_name"
require "ui_activity_success"
require "ui_box_use"
require "ui_box_get"
require "ui_gift_vip"
require "ui_settings"
require "ui_beauty"
require "ui_activity_gift"
require "ui_gift_recharge"
require "ui_beauty_info"
require "ui_settings_hint"
require "ui_guide_fight"
require "ui_activity_foundation"
require "ui_activity_recharge"
require "ui_activity_buy"
require "ui_activity_card"
require "ui_activity_ranking"
require "ui_good_info"
require "ui_activity_time"
require "ui_hint_buy"
require "ui_activity_exchange"
require "ui_activity_trial"
require "ui_card_jingjie"
require "ui_tower_shop"
require "ui_alliance"
require "ui_alliance_rank"
require "ui_alliance_info"
require "ui_alliance_build"
require "ui_alliance_apply"
require "ui_alliance_hint"
require "ui_alliance_shop"
require "ui_activity_email"
require "ui_talk"
require "ui_alliance_talk"
require "ui_alliance_hint_shop"
require "ui_alliance_help"
require "ui_activity_cost_all"
require "ui_activity_limitShopping"
require "ui_activity_dailyDeals"
require "ui_activity_grabTheHour"
require "ui_activity_privateSale"
require "ui_activity_LimitTimeHero"
require "ui_activity_StrongHero"
require "ui_activity_LimitTimeHero_preview"
require "ui_activity_StrongHero_preview"
require "ui_activity_time_exchange"
require "ui_lineup_recommend"
require "ui_activity_luckdial"
require "ui_activity_luckdial_preview"
require "ui_tower_win_small"
require "ui_equipment_new"
require "ui_activity_rechargeDouble"
require "ui_activity_MakeMoney"
require "ui_activity_vip"
require "ui_pilltower"
require "ui_pilltower_embattle"
require "ui_pilltower_preview"
require "ui_pilltower_rank"
require "ui_pilltower_blood"
require "ui_pilltower_get"
require "ui_activity_hint"
require "ui_ore"
require "ui_ore_email"
require "ui_ore_hint"
require "ui_ore_info"
require "ui_loot_clearing"
require "ui_poster"
require "ui_fire"
require "ui_soul_choose"
require "ui_soul_bag"
require "ui_soul_get"
require "ui_soul_info"
require "ui_soul_list"
require "ui_soul_upgrade"
require "ui_soul_install"
require "ui_tower_strong"
require "ui_fire_add"
require "ui_bag_wing"
require "ui_bag_wing_sell"
require "ui_wing_advance"
require "ui_wing_change"
require "ui_wing_common"
require "ui_wing_info"
require "ui_wing_intensify"
require "ui_buy_slive"
require "ui_boss_award"
require "ui_boss_hint"
require "ui_boss_shop"
require "ui_equipment_advance"
require "ui_star"
require "ui_star_lighten"
require "ui_star_reward"
require "ui_gongfa_refining"
require "ui_wing_info_all"
require "ui_activity_Christmas"
require "ui_activity_normalExchange"
require "ui_activity_purchase_manager"
require "ui_activity_purchase_trade"
require "ui_activity_purchase_rank"
require "ui_activity_purchase_gift"
require "ui_war"
require "ui_war_ambush"
require "ui_war_info"
require "ui_war_list"
require "ui_war_rank"
require "ui_war_schedule"

function ccui.Layout:loadTexture(str)
    local name = self:getName() .. "_old"
    local parent = self:getParent()
    if (parent ~= nil) then
        local children = parent:getChildren()
        for i, child in ipairs(children) do
            if (child:getName() == name) then
                child:loadTexture(str)
                return
            end
        end
    end

    local children = self:getChildren()
    for i, child in ipairs(children) do
        if (child:getName() == name) then
            child:loadTexture(str)
            return
        end
    end
end

WidgetManager = { }

local uiMapClass = {
    test_login = TestLogin,
    ui_shop_recruit_jewel = UIShopRecruitJewel,
    ui_shop_recruit_preview = UIShopRecruitPreView,
    ui_shop_recruit_ten = UIShopRecruitTen,
    ui_login = UILogin,
    ui_login_choose = UILoginChoose,
    ui_activation = UIActivation,
    ui_loading = UILoading,
    ui_notice = UINotice,
    ui_menu = UIMenu,
    ui_team_info = UITeamInfo,
    ui_homepage = UIHomePage,
    ui_lineup = UILineup,
    ui_lineup_dialog = UILineupDialog,
    ui_card_change = UICardChange,
    ui_card_info = UICardInfo,
    ui_card_upgrade = UICardUpgrade,
    ui_card_advance = UICardAdvance,
    ui_card_realm = UICardRealm,
    ui_bag_equipment_sell = UIBagEquipmentSell,
    ui_equipment_info = UIEquipmentInfo,
    ui_equipment_intensify = UIEquipmentIntensify,
    ui_equipment_clean = UIEquipmentClean,
    -- ui_fire_get = UIFireGet,
    -- ui_fire = UIFire,
    -- ui_fire_spallation = UIFireSpallation,
    -- ui_fire_upgrade = UIFireUpgrade,
    -- ui_fire_inherit = UIFireInherit,
    -- ui_fire_bage = UIFireBage,
    -- ui_fire_info = UIFireInfo,
    -- ui_fire_list = UIFireList,
    ui_resolve = UIResolve,
    ui_resolve_list = UIResolve_list,
    ui_medicine = UIMedicine,
    ui_medicine_alchemy = UIMedicineAlchemy,
    ui_liandan = UILianDan,
    ui_danfang = UIDanFang,
    ui_danyao = UIDanYao,
    ui_bag = UIBag,
    ui_gem_upgrade = UIGemUpGrade,
    ui_sell_prop = UISellProp,
    ui_shop = UIShop,
    ui_gem_inlay = UIGemInlay,
    ui_gem_switch = UIGemSwitch,
    ui_bag_equipment = UIBagEquipment,
    ui_activity_tower = UIActivityTower,
    ui_tower_test = UITowerTest,
    ui_tower_challenge = UITowerChallenge,
    ui_gem_list = UIGemList,
    ui_tower_preview = UITowerPreview,
    ui_tower_win = UITowerWin,
    ui_fight = UIFight,
    ui_fight_win = UIFightWin,
    ui_fight_fail = UIFightFail,
    ui_bag_card_sell = UIBagCardSell,
    ui_fight_preview = UIFightPreView,
    ui_fight_task = UIFightTask,
    ui_fight_task_choose = UIFightTaskChoose,
    ui_fight_get_accident = UIFightGetAccident,
    ui_fight_clearing = UIFightClearing,
    ui_fight_upgrade = UIFightUpgrade,
    ui_fight_activity_choose = UIFightActivityChoose,
    ui_bag_card = UIBagCard,
    ui_bag_gongfa = UIBagGongFa,
    ui_lineup_embattle = UILineupEmbattle,
    ui_loot_choose = UILootChoose,
    ui_loot_fight = UILootFight,
    ui_loot = UILoot,
    ui_loot_hint = UILootHint,
    ui_loot_clearing = UILootClearing,
    ui_friend = UIFriend,
    -- ui_bag_skill = UIBagSkill,
    -- ui_bag_skill_choose = UIBagSkillChoose,
    -- ui_bag_skill_hint = UIBagSkillHint,
    -- ui_bag_skill_upgrade = UIBagSkillUpgrade,
    ui_activity_miteer = UIActivityMiteer,
    ui_activity_panel = UIActivityPanel,
    ui_activity_heijiaoyu = UIActivityHJY,
    ui_guide_info = UIGuideInfo,
    ui_fight_main = UIFightMain,
    ui_acticity_miteer_hint = UIActivityMiteerHint,
    ui_award_gift = UIAwardGift,
    ui_award_online = UIAwardOnLine,
    ui_award_sign = UIAwardSign,
    ui_arena = UIArena,
    ui_arena_check = UIArenaCheck,
    ui_arena_history = UIArenaHistory,
    ui_guide_system = UIGuideSystem,
    ui_award_get = UIAwardGet,
    ui_bag_gongfa_list = UIBagGongFaList,
    ui_gongfa_info = UIGongfaInfo,
    ui_gongfa_intensify = UIGongfaIntensify,
    ui_activity_bath = UIActivityBath,
    ui_boss = UIBoss,
    ui_boss_preview = UIBossPreview,
    ui_boss_ranking = UIBossRanking,
    ui_task_day = UITaskDay,
    ui_team = UITeam,
    ui_activity_success = UIActivitySuccess,
    ui_name = UIName,
    ui_box_use = UIBoxUse,
    ui_box_get = UIBoxGet,
    ui_gift_vip = UIGiftVip,
    ui_settings = UISettings,
    ui_beauty = UIBeauty,
    ui_activity_gift = UIActivityGift,
    ui_gift_recharge = UIGiftRecharge,
    ui_beauty_info = UIBeautyInfo,
    ui_settings_hint = UISettingsHint,
    ui_guide_fight = UIGuideFight,
    ui_activity_foundation = UIActivityFoundation,
    ui_activity_recharge = UIActivityRecharge,
    ui_activity_buy = UIActivityBuy,
    ui_activity_card = UIActivityCard,
    ui_activity_ranking = UIActivityRanking,
    ui_good_info = UIGoodInfo,
    ui_activity_time = UIActivityTime,
    ui_hint_buy = UIHintBuy,
    ui_activity_exchange = UIActivityExchange,
    ui_activity_trial = UIActivityTrial,
    ui_card_jingjie = UICardJingJie,
    ui_tower_shop = UITowerShop,
    ui_alliance = UIAlliance,
    ui_alliance_rank = UIAllianceRank,
    ui_alliance_info = UIAllianceInfo,
    ui_alliance_build = UIAllianceBuild,
    ui_alliance_apply = UIAllianceApply,
    ui_alliance_hint = UIAllianceHint,
    ui_alliance_shop = UIAllianceShop,
    ui_talk = UITalk,
    ui_alliance_talk = UIAllianceTalk,
    ui_activity_email = UIActivityEmail,
    ui_alliance_hint_shop = UIAllianceHintShop,
    ui_alliance_help = UIAllianceHelp,
    ui_activity_cost_all = UIActivityCostAll,
    ui_activity_limitShopping = UIActivityLimitShopping,
    ui_activity_dailyDeals = UIActivityDailyDeals,
    ui_activity_grabTheHour = UIActivityGrabTheHour,
    ui_activity_privateSale = UIActivityPrivateSale,
    ui_activity_LimitTimeHero = UIAactivityLimitTimeHero,
    ui_activity_StrongHero = UIActivityStrongHero,
    ui_activity_LimitTimeHero_preview = UIActivityLimitTimeHeroPreview,
    ui_activity_StrongHero_preview = UIActivityStrongHeroPreview,
    ui_activity_time_exchange = UIActivityTimeExchange,
    ui_lineup_recommend = UILineupRecommend,
    ui_activity_luckdial = UIActivityLuckdial,
    ui_activity_luckdial_preview = UIActivityLuckdialPreview,
    ui_activity_vip = UIActivityVip,
    ui_tower_win_small = UITowerWinSmall,
    ui_equipment_new = UIEquipmentNew,
    ui_activity_rechargeDouble = UIActivityRechargeDouble,
    ui_activity_MakeMoney = UIActivityMakeMoney,
    ui_pilltower = UIPilltower,
    ui_pilltower_embattle = UIPilltowerEmbattle,
    ui_pilltower_preview = UIPilltowerPreview,
    ui_pilltower_rank = UIPilltowerRank,
    ui_pilltower_blood = UIPilltowerBlood,
    ui_pilltower_get = UIPilltowerGet,
    ui_activity_hint = UIActivityHint,
    ui_ore = UIOre,
    ui_ore_email = UIOreEmail,
    ui_ore_hint = UIOreHint,
    ui_ore_info = UIOreInfo,
    ui_poster = UIPoster,
    ui_fire = UIFire ,
    ui_soul_bag = UISoulBag ,
    ui_soul_choose = UISoulChoose ,
    ui_soul_get = UISoulGet , 
    ui_soul_info = UISoulInfo ,
    ui_soul_list = UISoulList ,
    ui_soul_upgrade = UISoulUpgrade ,
    ui_soul_install = UISoulInstall ,
    ui_tower_strong = UITowerStrong,
    ui_fire_add = UIFireAdd,
    ui_bag_wing = UIBagWing,
    ui_bag_wing_sell = UIBagWingSell,
    ui_wing_advance = UIWingAdvance,
    ui_wing_change = UIWingChange,
    ui_wing_common = UIWingCommon,
    ui_wing_info = UIWingInfo,
    ui_wing_intensify = UIWingIntensify,
    ui_buy_slive =UIBuySlive,
    ui_boss_award = UIBossAward,
    ui_boss_hint = UIBossHint,
    ui_boss_shop = UIBossShop,
	ui_equipment_advance = UIEquipmentAdvance ,
	ui_star = UIStar ,
    ui_star_lighten = UIStarLighten ,
    ui_star_reward = UIStarReward ,
    ui_gongfa_refining = UIGongfaRefining ,
    ui_wing_info_all = UIWingInfoAll ,
    ui_activity_Christmas = UIActivityChristmas,
    ui_activity_normalExchange = UIActivityNormalExchange,
    ui_activity_purchase_manager = UIActivityPurchaseManager,
    ui_activity_purchase_trade = UIActivityPurchaseTrade,
    ui_activity_purchase_rank = UIActivityPurchaseRank,
    ui_activity_purchase_gift = UIActivityPurchaseGift,
    ui_war = UIWar,
    ui_war_ambush = UIWarAmbush,
    ui_war_info = UIWarInfo,
    ui_war_list = UIWarList,
    ui_war_rank = UIWarRank,
    ui_war_schedule = UIWarSchedule}

function WidgetManager.create(jsonFileName)
    if uiMapClass[jsonFileName] and uiMapClass[jsonFileName].Widget then
        if uiMapClass[jsonFileName].setup then
            uiMapClass[jsonFileName].setup()
        end
        return uiMapClass[jsonFileName].Widget
    end

    local node = cc.CSLoader:createNode("ui/" .. jsonFileName .. ".csb")
    cclog("ui/" .. jsonFileName .. ".csb")
    if node == nil then
        cclog("-------------->>>  [" .. jsonFileName .. ".csb] load failed !!!!!")
        return
    end
    node:setContentSize(display.size)
    ccui.Helper:doLayout(node)
    local ui_widget = node:getChildren()[1]
    ui_widget:retain()
    ui_widget:removeSelf()
    ui_widget:setName(jsonFileName)
    uiMapClass[jsonFileName].Widget = ui_widget
    if uiMapClass[jsonFileName].init then
        uiMapClass[jsonFileName].init()
    end
    if uiMapClass[jsonFileName].setup then
        uiMapClass[jsonFileName].setup()
    end
    return ui_widget
end

function WidgetManager.getClass(jsonFileName)
    if uiMapClass[jsonFileName] and uiMapClass[jsonFileName].Widget then
        return uiMapClass[jsonFileName]
    end
end

function WidgetManager.getWidgetName(uiItem)
    for key, obj in pairs(uiMapClass) do
        if obj == uiItem then
            return key
        end
    end
end

function WidgetManager.getAllWidgetClass()
    return uiMapClass
end

function WidgetManager.delete(class)
    if not tolua.isnull(class.Widget) and class.Widget:getReferenceCount() == 1 then
        class.Widget:removeAllChildren()
        class.Widget:release()
        class.Widget = nil
    end
end