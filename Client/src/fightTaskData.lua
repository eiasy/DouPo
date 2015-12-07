                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         FightTaskData ={}
FightTaskData.FightData = {}
FightTaskData.FightData[1] = {}
FightTaskData.FightData[2] = {}
FightTaskData.Fight_INIT_DATA = {
    isPVE = true,
    isSelfFirst = true,
    skipEmbattle = true,
    allowSkipFight = true,
    bgImage0 = "heijiaoyu_0.png",
    bgImage1 = "heijiaoyu_1.png",
 myData = { 
        mainForce = {              
        	nil,
          	{name="萧炎",scale=nil,isBoss=false,showBanner=true ,cardID=88,frameID=3,hp=26000,hit=105,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=2500,attMana=1800,defPhsc=1000,defMana=1000,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=274,lv=1},{id=353,lv=1}}},--萧炎首场战斗
        	nil,
        	nil,
            nil,
            nil,
            },
             substitute = {
                   {name="美杜莎",scale=nil,isBoss=false,showBanner=false ,cardID=144,frameID=4,hp=5000,hit=500,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=3100,attMana=2300,defPhsc=1000,defMana=1000,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1009,lv=1},{id=1098,lv=1},{id=1097,lv=1}}},
				},
    },
    otherData = {
        {
            mainForce = {
             {name="云雷",scale=nil,isBoss=false,showBanner=true ,cardID=11,frameID=11,hp=4000,hit=200,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=950,attMana=1250,defPhsc=100,defMana=600,attPhscRatio=5,attManaRatio=5,defPhscRatio=4,defManaRatio=5,sks={{id=1022,lv=1},{id=1023,lv=1}}},--竖排连锁
             nil,
             {name="云盛",scale=nil,isBoss=false,showBanner=true ,cardID=55,frameID=4,hp=4000,hit=200,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=950,attMana=1250,defPhsc=10,defMana=100,attPhscRatio=5,attManaRatio=5,defPhscRatio=4,defManaRatio=5,sks={{id=1022,lv=1},{id=1023,lv=1}}},--竖排连锁
             {name="云棱",scale=nil,isBoss=false,showBanner=true ,cardID=27,frameID=4,hp=4000,hit=200,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=1200,attMana=1200,defPhsc=400,defMana=400,attPhscRatio=5,attManaRatio=5,defPhscRatio=4,defManaRatio=4,sks={{id=1015,lv=1},{id=1023,lv=1},{id=1017,lv=1}}},--云棱
			 {name="纳兰嫣然",scale=nil,isBoss=false,showBanner=true ,cardID=95,frameID=4,hp=7000,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=2000,attMana=1500,defPhsc=500,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1012,lv=1},{id=1013,lv=1},{id=1014,lv=1},{id=1099,lv=1}}},--纳兰嫣然
			 {name="葛叶",scale=nil,isBoss=false,showBanner=true ,cardID=8,frameID=4,hp=4000,hit=200,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=1200,attMana=1200,defPhsc=500,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=4,sks={{id=1015,lv=1},{id=1023,lv=1},{id=1017,lv=1}}},--葛叶
                        },
        },
    
    },
    script = {

      	{fight = 1,round = 1,talk = {dir=0,name="萧炎",imageName="poster_char_xiaoyan.png",dialog = "三年之约，我如约而至，今日，解决掉以往的恩怨吧！"}},
        {fight = 1,round = 1,talk = {dir=1,name="纳兰嫣然",imageName="poster_char_nalanyanran.png",dialog = "我自己的婚事，自己会做主。"}},
		{fight = 1,round = 1,talk = {dir=0,name="萧炎",imageName="poster_char_xiaoyan.png",dialog = "当年你给我萧家的耻辱，是时候还回来！"}},
        {fight = 1,round = 1,talk = {dir=1,name="纳兰嫣然",imageName="poster_char_nalanyanran.png",dialog = "即使如今已过三年，可我却并不认为当年我做错了，我有权利选择自己的命运。"}},
        {fight = 1,round = 1,talk = {dir=0,name="海波东",imageName="poster_char_haibodong.png",dialog = "小子什么时候变得这么墨迹了？"}},
        {fight = 1,round = 1,enter= {position = 4,data = {name="海波东",showBanner=true ,cardID=94,frameID=4,hp=8000,hit=200,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=2100,attMana=1600,defPhsc=700,defMana=700,attPhscRatio=5,attManaRatio=5,defPhscRatio=9,defManaRatio=9,sks={{id=1003,lv=1},{id=1004,lv=1}}}}},--海波东
        {fight = 1,round = 1,intro= {id = 2}},
        {fight = 1,round = 1,enter= {position = 6,data = {name="凌影",showBanner=true ,cardID=47,frameID=4,hp=4000,hit=200,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=950,attMana=1250,defPhsc=450,defMana=600,attPhscRatio=5,attManaRatio=5,defPhscRatio=7,defManaRatio=7,sks={{id=1006,lv=1},{id=1007,lv=1}}}}},--凌影
        {fight = 1,round = 1,intro= {id = 3}},
        {fight = 1,round = 11,intro= {id = 1}},
        {fight = 1,round = 12,talk = {dir=1,name="纳兰嫣然",imageName="poster_char_nalanyanran.png",dialog = "萧炎，你赢了……按照当年的约定，为奴为婢，我任你处置。"}},
        {fight = 1,round = 12,talk = {dir=0,name="萧炎",imageName="poster_char_xiaoyan.png",dialog = "纳兰嫣然，三年之约已经结束，日后的我们，不会再有任何纠葛！"}},
        {fight = 1,round = 12,talk = {dir=1,name="萧炎",imageName="poster_char_xiaoyan.png",dialog = "此间事情已了，我们走吧!"}},
        {fight = 1,round = 12,talk = {dir=1,name="云山",imageName="poster_char_yunshan.png",dialog = "萧家小辈，给我留下！"}},
        {fight = 1,round = 12,enter= {position = 8,data = {name="云山",showBanner=true ,cardID=69,frameID=4,hp=50000,hit=1000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=1500,attMana=2000,defPhsc=750,defMana=750,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1018,lv=1},{id=1019,lv=1}}}}},
        {fight = 1,round = 12,talk = {dir=1,name="云山",imageName="poster_char_yunshan.png",dialog = "区区几个斗皇……哼哼……"}},
        {fight = 1,round = 12,talk = {dir=0,name="海波东",imageName="poster_char_haibodong.png",dialog = "云山，难道你已经进阶……"}},
        {fight = 1,round = 12,intro= {id = 4}},
       
      
    },
    record = nil,
}
  	--退婚
    FightTaskData.FightData[1][1] = {
    isPVE = true,
    isSelfFirst = true,
    skipEmbattle = true,
    bgImagePath0 ="image/backgroundWar/heijiaoyu02.png",
    bgImagePath1 ="image/backgroundWar/heijiaoyu01.png",
     myData = { --todo rename renxing
        mainForce = {
            nil,
            nil,
            nil,
            nil,
            {name="萧炎",scale=nil,isBoss=false,showBanner=true ,cardID=88,frameID=2,hp=20000,hit=30,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=50,attMana=1800,defPhsc=1000,defMana=1000,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=274,lv=1},{id=353,lv=1}}},--萧炎 已修改 
            nil,
            },
       },
    otherData = {
        {
        mainForce = {
        {name="葛叶",scale=nil,isBoss=false,showBanner=true ,cardID=8,frameID=3,hp=600,hit=200,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=1200,attMana=1200,defPhsc=500,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=4,sks={{id=700,lv=1},{id=704,lv=1},{id=711,lv=1}}},--葛叶
        {name="纳兰嫣然",scale=nil,isBoss=false,showBanner=true ,cardID=95,frameID=4,hp=2500,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=2000,attMana=1500,defPhsc=500,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=334,lv=1},{id=375,lv=1},{id=181,lv=1}}},--纳兰嫣然
		{name="云岚宗长老",scale=nil,isBoss=false,showBanner=true ,cardID=54,frameID=3,hp=300,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=800,attMana=1000,defPhsc=500,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=702,lv=1},{id=706,lv=1},{id=800,lv=1}}},--墨承
        {name="云岚宗长老",scale=nil,isBoss=false,showBanner=true ,cardID=11,frameID=3,hp=800,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=800,attMana=1000,defPhsc=500,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=601,lv=1},{id=605,lv=1},{id=803,lv=1}}},--冰符
        nil,
        {name="云岚宗长老",scale=nil,isBoss=false,showBanner=true ,cardID=76,frameID=3,hp=600,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=800,attMana=1000,defPhsc=500,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=701,lv=1},{id=706,lv=1},{id=802,lv=1}}},--费天
             },
        },
    },
   
     script = {
    		{fight = 1,round = 1,talk = {dir=0,name="纳兰嫣然",imageName="poster_char_nalanyanran.png",dialog = "我纳兰嫣然，从未受过这等侮辱。"}},
       		{fight = 1,round = 1,talk = {dir=1,name="萧炎",imageName="poster_char_xiaoyan.png",dialog = "休的就是你！"}},
         	{fight = 1,round = 1,talk = {dir=0,name="纳兰嫣然",imageName="poster_char_nalanyanran.png",dialog = "你！你……找死！"}},
          	{fight = 1,round = 1,enter= {position = 5,data = {name="萧战",showBanner=true ,cardID=36,frameID=3,hp=4000,hit=5000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=2800,attMana=2800,defPhsc=800,defMana=800,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=600,lv=1},{id=1200,lv=1},{id=800,lv=1}}}}},--萧战
           	{fight = 1,round = 1,enter= {position = 4,data = {name="萧鼎",showBanner=true ,cardID=37,frameID=3,hp=4000,hit=5000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=2800,attMana=2800,defPhsc=800,defMana=800,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=601,lv=1},{id=1201,lv=1},{id=803,lv=1}}}}},
           	{fight = 1,round = 1,enter= {position = 6,data = {name="萧厉",showBanner=true ,cardID=48,frameID=3,hp=4000,hit=5000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=2800,attMana=2800,defPhsc=800,defMana=800,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=602,lv=1},{id=1202,lv=1},{id=805,lv=1}}}}},
           	{fight = 1,round = 1,talk = {dir=1,name="萧战",imageName="poster_char_xiaozhan.png",dialog = "大胆，在我萧家岂容你们放肆！"}},     
             
        },
    record = nil,
}
	--英雄救美
  
	 FightTaskData.FightData[1][2] = {
    isPVE = true,
    isSelfFirst = true,
    skipEmbattle = true,
    bgImagePath0 = "image/backgroundWar/shanmaibianyuan02.png",
    bgImagePath1 = "image/backgroundWar/shanmaibianyuan01.png",
     myData = { 
        mainForce = {
            nil,
            {name="谷尼",scale=nil,isBoss=false,showBanner=true ,cardID=546,frameID=3,hp=1800,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=250,attMana=250,defPhsc=10,defMana=10,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1503,lv=1},{id=1518,lv=1}}},--谷尼
            nil,
			nil,
		    {name="雅妃",scale=nil,isBoss=false,showBanner=true ,cardID=51,frameID=4,hp=800,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=200,attMana=200,defPhsc=10,defMana=10,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=96,lv=1},{id=396,lv=1},{id=89,lv=1}}},--雅妃
			nil,
            },
    },
    otherData = {
        {
        mainForce = {
       {name="蛇怪",scale=nil,isBoss=false,showBanner=true ,cardID=550,frameID=1,hp=600,hit=10,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=100,attMana=100,defPhsc=10,defMana=10,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1013,lv=1},{id=803,lv=1}}},
	   {name="蛇怪",scale=nil,isBoss=false,showBanner=true ,cardID=550,frameID=1,hp=600,hit=10,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=100,attMana=100,defPhsc=10,defMana=10,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1013,lv=1},{id=803,lv=1}}},
       {name="蛇怪",scale=nil,isBoss=false,showBanner=true ,cardID=550,frameID=1,hp=600,hit=10,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=100,attMana=100,defPhsc=10,defMana=10,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1013,lv=1},{id=803,lv=1}}},
       nil,
       {name="藤毒蛇王",scale=1.4,isBoss=false,showBanner=true ,cardID=502,frameID=3,hp=900,hit=5000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=2100,attMana=1000,defPhsc=10,defMana=10,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1513,lv=1},{id=1013,lv=1}}},
       nil,
        },
        },
    },
   
    script = {
         
        {fight = 1,round = 1,talk = {dir=1,name="谷尼",imageName="poster_char_boss6.png",dialog = "没想到在这里竟然能碰到藤毒蛇王！"}},
        {fight = 1,round = 1,talk = {dir=0,name="雅妃",imageName="poster_char_yafei.png",dialog = "这株黄连精我们势在必得，大师！你可有把握？"}},
        {fight = 1,round = 1,talk = {dir=1,name="谷尼",imageName="poster_char_boss6.png",dialog = "毕竟是二阶魔兽，不过现在也只能试试了。待会儿一旦形势不对，小姐你就先行离开。"}},
        {fight = 1,round = 7,talk = {dir=1,name="萧炎",imageName="poster_char_xiaoyan.png",dialog = "畜生！放开那个姐姐！"}},
        {fight = 1,round = 7,enter= {position = 4,data ={name="萧炎",showBanner=true ,cardID=88,frameID=2,hp=20000,hit=30,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=50,attMana=1800,defPhsc=1000,defMana=1000,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=274,lv=1},{id=353,lv=1}}}}},--萧炎 已修改 
        {fight = 1,round = 7,enter= {position = 6,data = {name="萧薰儿",showBanner=true ,cardID=136,frameID=4,hp=20000,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=1200,attMana=1200,defPhsc=500,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=36,lv=1},{id=1501,lv=1},{id=1500,lv=1}}}}},
		{fight = 1,round = 7,talk = {dir=1,name="萧薰儿",imageName="poster_char_xiaoxuner.png",dialog = "这藤毒蛇王毒性极强，萧炎哥哥小心啊！"}},
     
        },
    record = nil,
}
   --加列奥-药老
    FightTaskData.FightData[1][3] = {
    isPVE = true,
    isSelfFirst = true,
    skipEmbattle = true,
    bgImagePath0 ="image/backgroundWar/wutancheng02.png",
    bgImagePath1 ="image/backgroundWar/wutancheng01.png",
   myData = {
        mainForce = {
        	nil,
            nil,
        	nil,
			nil,
            {name="萧炎",scale=nil,isBoss=false,showBanner=true ,cardID=88,frameID=2,hp=26000,hit=5,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=50,attMana=180,defPhsc=1000,defMana=1000,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=274,lv=1},{id=0353,lv=1}}},--萧炎
           {name="琥嘉",scale=nil,isBoss=false,showBanner=true ,cardID=43,frameID=3,hp=20000,hit=30,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=50,attMana=1800,defPhsc=1000,defMana=1000,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=105,lv=1},{id=81,lv=1}}},--萧炎 已修改 
             },
    },
    otherData = {
        {
        mainForce = {
       {name="佣兵",scale=nil,isBoss=false,showBanner=true ,cardID=508,frameID=1,hp=800,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=800,attMana=1000,defPhsc=500,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1503,lv=1},{id=1518,lv=1}}},
       {name="加列奥",scale=nil,isBoss=false,showBanner=true ,cardID=2,frameID=2,hp=5100,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=800,attMana=1000,defPhsc=100,defMana=100,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1503,lv=1},{id=1518,lv=1}}},
       {name="佣兵",scale=nil,isBoss=false,showBanner=true ,cardID=508,frameID=1,hp=800,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=800,attMana=1000,defPhsc=500,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1503,lv=1},{id=1518,lv=1}}},
       {name="佣兵",scale=nil,isBoss=false,showBanner=true ,cardID=508,frameID=1,hp=800,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=800,attMana=1000,defPhsc=500,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1503,lv=1},{id=1518,lv=1}}},
       {name="佣兵",scale=nil,isBoss=false,showBanner=true ,cardID=508,frameID=1,hp=800,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=800,attMana=1000,defPhsc=500,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1503,lv=1},{id=1518,lv=1}}},
       {name="佣兵",scale=nil,isBoss=false,showBanner=true ,cardID=508,frameID=1,hp=800,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=800,attMana=1000,defPhsc=500,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1503,lv=1},{id=1518,lv=1}}},
        },
        },
       
    },

    
    script = {
         {fight = 1,round = 2,enter= {position = 5,data = {name="药老",showBanner=true ,cardID=135,frameID=4,hp=4000,hit=5000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=4000,attMana=4000,defPhsc=4000,defMana=4000,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1502,lv=1},{id=1503,lv=1}}}}},--{{id=1500,lv=1},{id=1501,lv=1},{id=1502,lv=1}
		 {fight = 1,round = 2,intro= {id = 0}},
         {fight = 1,round = 2,talk = {dir=1,name="药老",imageName="poster_char_yaolao.png",dialog = "老夫看上的人可不是你们能欺负的！"}},    
         {fight = 1,round = 2,talk = {dir=0,name="加列奥",imageName="poster_char_jialieao.png",dialog = "哪来的糟老头子！本大爷的事也是你能掺合的！小心我打的你生活不能自理！"}},    
         {fight = 1,round = 2,talk = {dir=1,name="药老",imageName="poster_char_yaolao.png",dialog = "呀呵，现在的小孩口气都挺冲啊！信不信老头我分分钟躺地上！"}},  
         {fight = 1,round = 2,talk = {dir=0,name="加列奥",imageName="poster_char_jialieao.png",dialog = "啊啊啊，大爷我最讨厌碰瓷的老表砸了！给我往死里打！"}},    
         {fight = 1,round = 2,talk = {dir=1,name="药老",imageName="poster_char_yaolao.png",dialog = "唉，现在的年轻人啊，一点都不懂得尊老爱幼！本来不想出手的！就让你见识见识爷爷的厉害！"}},  
         
         },
    record = nil,
}
	--萧战-加勒毕
	FightTaskData.FightData[1][4] = {
    isPVE = true,
    isSelfFirst = true,
    skipEmbattle = true,
    bgImagePath0 ="image/backgroundWar/wutancheng02.png",
    bgImagePath1 ="image/backgroundWar/wutancheng01.png",
    myData = { 
        mainForce = {
           nil,
           nil,
           nil,
           nil,
           {name="萧炎",scale=nil,isBoss=false,showBanner=true ,cardID=88,frameID=2,hp=20000,hit=30,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=50,attMana=1800,defPhsc=1000,defMana=1000,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=274,lv=1},{id=353,lv=1}}},--萧炎 已修改 
           {name="琥嘉",scale=nil,isBoss=false,showBanner=true ,cardID=43,frameID=3,hp=20000,hit=30,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=50,attMana=1800,defPhsc=1000,defMana=1000,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=105,lv=1},{id=81,lv=1}}},           
		  
            },
    },
    otherData = {
        {
        mainForce = {
       {name="佣兵",scale=nil,isBoss=false,showBanner=true ,cardID=508,frameID=1,hp=800,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=800,attMana=1000,defPhsc=500,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1503,lv=1},{id=1518,lv=1}}},
       {name="加列毕",scale=nil,isBoss=false,showBanner=true ,cardID=512,frameID=1,hp=3800,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=800,attMana=1000,defPhsc=100,defMana=100,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1503,lv=1},{id=1518,lv=1}}},
       {name="加列奥",scale=nil,isBoss=false,showBanner=true ,cardID=2,frameID=1,hp=3800,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=800,attMana=1000,defPhsc=100,defMana=100,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1503,lv=1},{id=1518,lv=1}}},
       {name="佣兵",scale=nil,isBoss=false,showBanner=true ,cardID=508,frameID=1,hp=800,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=800,attMana=1000,defPhsc=500,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1503,lv=1},{id=1518,lv=1}}},
       {name="佣兵",scale=nil,isBoss=false,showBanner=true ,cardID=508,frameID=1,hp=800,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=800,attMana=1000,defPhsc=500,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1503,lv=1},{id=1518,lv=1}}},
       {name="佣兵",scale=nil,isBoss=false,showBanner=true ,cardID=508,frameID=1,hp=400,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=95,critRatio=0,renxingRatio=0,attPhsc=800,attMana=1000,defPhsc=500,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=1503,lv=1},{id=1518,lv=1}}},
           },
        },
       
    
    },
    script = {    
         {fight = 1,round = 2,enter= {position = 5,data = {name="萧战",showBanner=true ,cardID=36,frameID=3,hp=4000,hit=5000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=6000,attMana=6000,defPhsc=13000,defMana=13000,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=600,lv=1},{id=610,lv=1},{id=800,lv=1}}}}},
         {fight = 1,round = 2,talk = {dir=1,name="萧战",imageName="poster_char_xiaozhan.png",dialog = "妈的，加列老狗，我萧战的儿子，什么时候轮到你个杂种来教训了？"}},     
        
                    
    },
    record = nil,
}
	--若琳导师 1-8
	FightTaskData.FightData[1][8] = {
    isPVE = true,
    isSelfFirst = true,
    skipEmbattle = true,
    bgImagePath0 ="image/backgroundWar/heijiaoyu02.png",
    bgImagePath1 ="image/backgroundWar/heijiaoyu01.png",
    myData = { 
        mainForce = {
           nil,
           nil,
           nil,
          {name="吴昊",scale=nil,isBoss=false,showBanner=true ,cardID=65,frameID=3,hp=6000,hit=3000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=50,critRatio=0,renxingRatio=0,attPhsc=2000,attMana=2000,defPhsc=5,defMana=5,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=324,lv=1},{id=384,lv=1},{id=377,lv=1}}},           
          {name="萧炎",scale=nil,isBoss=false,showBanner=true ,cardID=88,frameID=3,hp=6000,hit=3000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=50,critRatio=0,renxingRatio=0,attPhsc=2000,attMana=2000,defPhsc=50,defMana=50,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=274,lv=1},{id=353,lv=1}}},--萧炎 已修改 
          {name="琥嘉",scale=nil,isBoss=false,showBanner=true ,cardID=43,frameID=3,hp=3000,hit=3000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=50,critRatio=0,renxingRatio=0,attPhsc=2000,attMana=2000,defPhsc=5,defMana=5,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=105,lv=1},{id=81,lv=1}}},           
           			 },
    		},
    otherData = {
        {
        mainForce = {
        nil,
        nil,
        nil,
        nil,
        {name="若琳导师",scale=nil,isBoss=true, showBanner=true ,cardID=52,frameID=4,hp=23000,hit=1000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=50,critRatio=0,renxingRatio=0,attPhsc=1600,attMana=1600,defPhsc=5,defMana=5,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=137,lv=1},{id=7,lv=1},{id=80,lv=1}}},
        nil,
           },
        },
       
    
    },
    script = { 
      
    	 
   		 	{fight = 1,round = 1,talk = {dir=0,name="若琳导师",imageName="poster_char_ruolin.png",dialog = "要是这么容易就让你请假一年，老娘以后还怎么混！"}},    
   		 	{fight = 1,round = 1,talk = {dir=1,name="萧炎",imageName="poster_char_xiaoyan.png",dialog = "导师！我准备好了，尽管招呼吧！"}},
   		 	
   		 	{fight = 1,round = 1,talk = {dir=0,name="若琳导师",imageName="poster_char_ruolin.png",dialog = "大召唤术！班干部的威力！！！！"}},   
   		 	
   		 	{fight = 1,round = 1,enter= {position = 7,data = {name="林修崖",showBanner=true ,cardID=92 ,frameID=4,hp=35000,hit=2000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=50,critRatio=0,renxingRatio=0,attPhsc=3500,attMana=3000,defPhsc=5,defMana=5,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=0291,lv=1},{id=0313,lv=1},{id=0184,lv=1}}}}},--林修崖
            {fight = 1,round = 1,music= {mp3 = "sound/bg_jianan.mp3"}},
            {fight = 1,round = 1,enter= {position = 9,data = {name="林焱",showBanner=true ,cardID=93 ,frameID=4,hp=26000,hit=2000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=50,critRatio=0,renxingRatio=0,attPhsc=3500,attMana=3000,defPhsc=5,defMana=5,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=0309,lv=1},{id=0386,lv=1},{id=0203,lv=1}}}}},--林焱
  			{fight = 1,round = 1,enter= {position = 12,data = {name="柳擎",showBanner=true ,cardID=66 ,frameID=4,hp=32000,hit=2000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=50,critRatio=0,renxingRatio=0,attPhsc=3500,attMana=3000,defPhsc=5,defMana=5,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=0361,lv=1},{id=0196,lv=1},{id=0318,lv=1}}}}},--柳擎
			{fight = 1,round = 1,enter= {position = 10,data = {name="韩月",showBanner=true ,cardID=28 ,frameID=4,hp=26000,hit=2000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=50,critRatio=0,renxingRatio=0,attPhsc=3500,attMana=3000,defPhsc=5,defMana=5,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=0173,lv=1},{id=0077,lv=1},{id=0143,lv=1}}}}},--韩月
			{fight = 1,round = 1,talk = {dir=1,name="琥嘉",imageName="poster_char_hujia.png",dialog = "竟然是迦南学院小四代！！！我感觉要晕倒了！"}},
			{fight = 1,round = 1,talk = {dir=1,name="萧炎",imageName="poster_char_xiaoyan.png",dialog = "喂喂！先别花痴！小四代是什么鬼？"}},
			{fight = 1,round = 1,talk = {dir=1,name="琥嘉",imageName="poster_char_hujia.png",dialog = "你连迦南小四代都不知道，你还考什么迦南学院！"}},  
			{fight = 1,round = 1,talk = {dir=0,name="林修崖",imageName="poster_char_linxiuya.png",dialog = "学弟，论美貌你是比不过我的！"}},  
 		    {fight = 1,round = 1,talk = {dir=0,name="柳擎",imageName="poster_char_liuqing.png",dialog = "来！打一架吧！！！"}},       
   		    {fight = 1,round = 1,talk = {dir=0,name="林焱",imageName="poster_char_linyan.png",dialog = "挑战本天才，你是猪么？！！"}},       
   		    {fight = 1,round = 1,talk = {dir=0,name="韩月",imageName="poster_char_hanyue.png",dialog = "学弟，你的名牌是我的！！"}},       
 			{fight = 1,round = 1,talk = {dir=0,name="若琳导师",imageName="poster_char_ruolin.png",dialog = "萧炎！你觉悟吧！"}},    
 			--第一回合 
 			{fight = 1,round = 9,talk = {dir=1,name="琥嘉",imageName="poster_char_hujia.png",dialog = "果然是迦南小四代！！实力也相差太多了！！"}},
    		{fight = 1,round = 9,talk = {dir=1,name="萧炎",imageName="poster_char_xiaoyan.png",dialog = "老头！！你就看着我挨打啊！说好的支持呢！"}},
    		{fight = 1,round = 9,talk = {dir=0,name="药老",imageName="poster_char_yaolao.png",dialog = "不过是几个毛头小子！你可以的，可以的！"}},
    		{fight = 1,round = 9,talk = {dir=1,name="萧炎",imageName="poster_char_xiaoyan.png",dialog = "老头，你没本事就直说，我不会怪你的！"}},
    		{fight = 1,round = 9,talk = {dir=0,name="药老",imageName="poster_char_yaolao.png",dialog = "哼！就这点阵仗都不用我出手，我随便扔几个魂魄助你！"}},
    		{fight = 1,round = 9,enter= {position = 4,data = {name="妖暝",showBanner=true ,cardID=142 ,frameID=4,hp=40000,hit=15000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=20000,attMana=20000,defPhsc=5,defMana=5,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=50,lv=1},{id=22,lv=1},{id=240,lv=1}}}}},--火云老祖
  			{fight = 1,round = 9,enter= {position = 5,data = {name="北龙王",showBanner=true ,cardID=125 ,frameID=4,hp=40000,hit=15000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=20000,attMana=20000,defPhsc=5,defMana=5,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=317,lv=1},{id=376,lv=1},{id=318,lv=1}}}}},--北龙王
			{fight = 1,round = 9,enter= {position = 6,data = {name="古烈",showBanner=true ,cardID=128 ,frameID=4,hp=40000,hit=15000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=20000,attMana=20000,defPhsc=5,defMana=5,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=287,lv=1},{id=254,lv=1},{id=304,lv=1}}}}},--古烈
			{fight = 1,round = 9,talk = {dir=0,name="药老",imageName="poster_char_yaolao.png",dialog = "看我的中州传奇！！"}},
         	{fight = 1,round = -1,music= {mp3 = "sound/commonfight2.mp3"}},
        
                    
    },
    record = nil,
}

	--穆力 2-15
	FightTaskData.FightData[2][15] = {
    isPVE = true,
    isSelfFirst = true,
    skipEmbattle = true,
    bgImagePath0 ="image/backgroundWar/shanmaineibu02.png",
    bgImagePath1 ="image/backgroundWar/shanmaineibu01.png",
    myData = { --todo rename renxing
        mainForce = {
          	{name="吴昊",scale=nil,isBoss=false,showBanner=true ,cardID=65,frameID=3,hp=3600,hit=5000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=50,critRatio=0,renxingRatio=0,attPhsc=3000,attMana=3000,defPhsc=50,defMana=50,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=324,lv=1},{id=384,lv=1}}},  --{id=377,lv=1}         
            {name="萧炎",scale=nil,isBoss=false,showBanner=true ,cardID=88,frameID=3,hp=38000,hit=5000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=50,critRatio=0,renxingRatio=0,attPhsc=3000,attMana=3000,defPhsc=500,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=274,lv=1},{id=353,lv=1}}},--萧炎 护盾 普攻 被动反攻  打后排 {id=371,lv=1}
			{name="琥嘉",scale=nil,isBoss=false,showBanner=true ,cardID=43,frameID=3,hp=3600,hit=5000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=50,critRatio=0,renxingRatio=0,attPhsc=3000,attMana=3000,defPhsc=50,defMana=50,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=105,lv=1},{id=81,lv=1}}},           
			nil,
          	{name="小医仙",scale=nil,isBoss=false,showBanner=true ,cardID=89,frameID=4,hp=3800,hit=5000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=50,critRatio=0,renxingRatio=0,attPhsc=3000,attMana=3000,defPhsc=50,defMana=50,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=265,lv=1},{id=401,lv=1}}},--小医仙解锁 3008 消灭前排       
          	nil,
           },
    },
    otherData = {
        {
        mainForce = {                                     
      				{name="佣兵",scale=nil,isBoss=false,showBanner=true ,cardID=509 ,frameID=1,hp=1000,hit=50,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=5,critRatio=0,renxingRatio=0,attPhsc=200,attMana=200,defPhsc=5,defMana=10,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=600,lv=1},{id=603,lv=1},{id=612,lv=1}}},--佣兵
					{name="佣兵",scale=nil,isBoss=false,showBanner=true ,cardID=509 ,frameID=1,hp=1000,hit=50,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=5,critRatio=0,renxingRatio=0,attPhsc=200,attMana=200,defPhsc=5,defMana=10,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=600,lv=1},{id=603,lv=1},{id=612,lv=1}}},--佣兵
					{name="佣兵",scale=nil,isBoss=false,showBanner=true ,cardID=509 ,frameID=1,hp=1000,hit=50,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=5,critRatio=0,renxingRatio=0,attPhsc=200,attMana=200,defPhsc=5,defMana=10,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=600,lv=1},{id=603,lv=1},{id=612,lv=1}}},--佣兵
					nil,
					{name="头领",scale=nil,isBoss=false,showBanner=true ,cardID=82 ,frameID=2,hp=1000,hit=50,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=5,critRatio=0,renxingRatio=0,attPhsc=200,attMana=200,defPhsc=5,defMana=10,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=600,lv=1},{id=604,lv=1},{id=611,lv=1}}},--佣兵
					nil,
  					},
      			
        },
        		{
        mainForce = {
      			  	{name="佣兵",scale=nil,isBoss=false,showBanner=true ,cardID=508 ,frameID=1,hp=700,hit=50,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=9,critRatio=0,renxingRatio=0,attPhsc=200,attMana=200,defPhsc=5,defMana=10,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=600,lv=1},{id=603,lv=1},{id=612,lv=1}}},--佣兵
					{name="佣兵",scale=nil,isBoss=false,showBanner=true ,cardID=508 ,frameID=1,hp=700,hit=50,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=9,critRatio=0,renxingRatio=0,attPhsc=200,attMana=200,defPhsc=5,defMana=10,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=600,lv=1},{id=603,lv=1},{id=612,lv=1}}},--佣兵
					{name="佣兵",scale=nil,isBoss=false,showBanner=true ,cardID=508 ,frameID=1,hp=700,hit=50,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=9,critRatio=0,renxingRatio=0,attPhsc=200,attMana=200,defPhsc=5,defMana=10,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=600,lv=1},{id=603,lv=1},{id=612,lv=1}}},--佣兵
					{name="头领",scale=nil,isBoss=false,showBanner=true ,cardID=29 ,frameID=2,hp=2000,hit=50,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=9,critRatio=0,renxingRatio=0,attPhsc=200,attMana=200,defPhsc=5,defMana=10,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=702,lv=1},{id=705,lv=1},{id=711,lv=1}}},--炎利
					nil,
					{name="头领",scale=nil,isBoss=false,showBanner=true ,cardID=67 ,frameID=2,hp=2000,hit=50,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=9,critRatio=0,renxingRatio=0,attPhsc=200,attMana=200,defPhsc=5,defMana=10,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=327,lv=1},{id=373,lv=1},{id=199,lv=1}}},--熊战
				
				  },
        },
 		{
        mainForce = {
       				nil,
					nil,
					nil,
					nil,	
					{name="穆力",scale=nil,isBoss= true, showBanner=true ,cardID=4,frameID=2,hp=10000,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=25,critRatio=0,renxingRatio=0,attPhsc=800,attMana=500,defPhsc=500,defMana=100,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=600,lv=1},{id=604,lv=1},{id=612,lv=1}}},--佣兵
  					nil,      			  						
      				},
      			 substitute = {

		      		{name="穆力",scale=nil,isBoss= true, showBanner=true ,cardID=4,frameID=2,hp=10000,hit=100,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=25,critRatio=0,renxingRatio=0,attPhsc=800,attMana=500,defPhsc=500,defMana=100,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=600,lv=1},{id=604,lv=1},{id=612,lv=1}}},--佣兵
  					
					},
        },
       
       
    
    },
    script = {    
   			{fight = 1,round = 1,talk = {dir=0,name="头领",imageName="poster_char_hongtianxiao.png",dialog = "萧炎！快快投降，少团长还能放你一条生路！"}},    
   			{fight = 1,round = 1,talk = {dir=1,name="萧炎",imageName="poster_char_xiaoyan.png",dialog = "找死！！"}},    
   			{fight = 1,round = 1,music= {mp3 = "sound/bg_2_7_1.mp3"}},

   		 	{fight = 2,round = 1,talk = {dir=0,name="头领",imageName="poster_char_xiongzhan.png",dialog = "击杀萧炎，重重有赏！！！"}}, 
   		 	{fight = 2,round = 1,talk = {dir=0,name="头领",imageName="poster_char_yanli.png",dialog = "兄弟们！给我上！！！"}}, 
   		          	
          	{fight = 3,round = 1,talk = {dir=0,name="穆力",imageName="poster_char_muli.png",dialog = "萧炎！你现在后悔还来的及！"}},    
			{fight = 3,round = 1,talk = {dir=1,name="萧炎",imageName="poster_char_xiaoyan.png",dialog = "你的手下都被我们干掉了，你还拿什么抓我们？！！"}},    
   			{fight = 3,round = 1,music= {mp3 = "sound/bg_2_7_2.mp3"}},
   			{fight = 3,round = 1,enter= {position = 11,data = {name="穆力",showBanner=true ,cardID=4 ,frameID=2,hp=8000,hit=50,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=2100,attMana=1600,defPhsc=50,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=3006,lv=1},{id=803,lv=1}}}}},--穆力释放技能
			{fight = 3,round = 1,talk = {dir=0,name="穆力",imageName="poster_char_muli.png",dialog = "哈哈哈哈！狼头佣兵之魂！现身吧！"}}, 
   			{fight = 3,round = 1,enter= {position = 8,data = {name="佣兵魂",isBoss=true,showBanner=true ,cardID=555 ,frameID=3,hp=27000,hit=15000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=24000,attMana=24000,defPhsc=50,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=3001,lv=1},{id=3002,lv=1},{id=3003,lv=1}}}}},--穆力释放技能
			{fight = 3,round = 1,order= {11,}}, --复活佣兵
			 
			{fight = 3,round = 2,talk = {dir=0,name="佣兵魂",imageName="poster_char_touling.png",dialog = "为了佣兵的荣耀！！"}},  
			{fight = 3,round = 2,exit = {position = 11}},
			{fight = 3,round = 2,order= {reset=true,}}, 
   			{fight = 3,round = 2,order= {4,5,6,2,8,}}, 
   		
       		{fight = 3,round = 7,order= {reset=true,}}, 
       		{fight = 3,round = 7,music= {mp3 = "sound/bg_2_7_2_1.mp3"}},
       		{fight = 3,round = 7,talk = {dir=1,name="萧炎",imageName="poster_char_xiaoyan.png",dialog = "老头！你就干看着啊？！"}},    
       
            {fight = 3,round = 7,talk = {dir=0,name="佣兵魂",imageName="poster_char_touling.png",dialog = "你叫破喉咙！也没人管你的！！"}}, 
            {fight = 3,round = 7,talk = {dir=1,name="药老",imageName="poster_char_yaolao.png",dialog = "徒儿莫怕！！"}},  
			{fight = 3,round = 7,enter= {position = 1,data = {name="火云老祖",showBanner=true ,cardID=134 ,frameID=4,hp=50000,hit=2000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=6000,attMana=5000,defPhsc=50,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=379,lv=1},{id=399,lv=1},{id=322,lv=1}}}}},--炎烬
            {fight = 3,round = 7,enter= {position = 2,data = {name="药老",showBanner=true ,cardID=135 ,frameID=4,hp=80800,hit=50,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=6000,attMana=5000,defPhsc=50,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=3005,lv=1},{id=266,lv=1},{id=207,lv=1},{id=178,lv=1}}}}},--药老
      		{fight = 3,round = 7,enter= {position = 3,data = {name="炎烬",showBanner=true ,cardID=147,frameID=4,hp=27000,hit=2000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=6000,attMana=5000,defPhsc=50,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=47,lv=1},{id=101,lv=1},{id=78,lv=1}}}}},--火云老祖
			{fight = 3,round = 7,enter= {position = 4,data = {name="天雷子",showBanner=true ,cardID=117,frameID=4,hp=26000,hit=2000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=6000,attMana=5000,defPhsc=50,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=275,lv=1},{id=362,lv=1},{id=160,lv=1}}}}},--天雷子
			{fight = 3,round = 7,enter= {position = 6,data = {name="黑擎",showBanner=true ,cardID=122 ,frameID=4,hp=26000,hit=2000,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=6000,attMana=5000,defPhsc=50,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=332,lv=1},{id=345,lv=1},{id=146,lv=1}}}}},--黑擎
			 
		    {fight = 3,round = 7,order= {2,1,3,8,4,5,6,}}, 
		    
 			{fight = 3,round = 11,order= {reset=true,}}, 
 			{fight = 3,round = 11,order= {2,1,3,8,4,5,6,}}, 
      		{fight = 3,round = 17,music= {mp3 = "sound/bg_2_7_3.mp3"}},
       		{fight = 3,round = 17,enter= {position = 8,data = {name="穆力",showBanner=true ,cardID=4 ,frameID=2,hp=8000,hit=50,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=9000,attMana=9000,defPhsc=50,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=3000,lv=1},{id=803,lv=1}}}}},--穆力释放技能
	  		{fight = 3,round = 17,order= {8,}}, 
			{fight = 3,round = 18,enter= {position = 8,data = {name="穆力",scale=1.8,isBoss=true,showBanner=true ,cardID=554,frameID=4,hp=18000,hit=50,dodge=0,hitRatio=0,dodgeRatio=0,crit=0,renxing=90,critRatio=0,renxingRatio=0,attPhsc=58000,attMana=58000,defPhsc=50,defMana=500,attPhscRatio=5,attManaRatio=5,defPhscRatio=5,defManaRatio=5,sks={{id=3011,lv=1},{id=3012,lv=1},{id=3013,lv=1}}}}},--穆力释放技能 全屏幕变身 3001
			{fight = 3,round = 19,order= {reset=true,}}, 
			{fight = 3,round = 19,music= {mp3 = "sound/bg_2_7_4.mp3"}},	
			{fight = 3,round = 19,order= {8,}}, 
			{fight = 3,round = 20,bg  = {bgImagePath0 = "image/backgroundWar/star.png",bgImagePath1 = "image/backgroundWar/star.png"}},
			{fight = 3,round = 20,order= {reset=true,}},  
			{fight = 3,round = 20,order= {1,2,3,4,5,6,}}, 
			{fight = 3,round = 25,order= {reset=true,}},  
   			     		
            {fight = 3,round = -1,talk = {dir=0,name="穆力",imageName="poster_char_muli.png",dialog = "萧炎！你给我等着！！"}},    
       		{fight = 3,round = -1,music= {mp3 = "sound/commonfight2.mp3"}},
                    
    		},
    record = nil,
   }