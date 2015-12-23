package com.huayi.doupo.logic.util.luafight;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.ResourceFinder;
import org.luaj.vm2.lib.jse.JsePlatform;

import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.util.unionwar.UnionWarUtil;

public class LuaFightEngine {

	private static String httpDir = "http://192.168.1.251/src/";
	private static int TIMEOUT_CONNECT = 1000 * 30; // 连接超时
	private static int TIMEOUT_RECEIVE = 1000 * 30; // 接收超时
	private static byte[] fightbuffer = null;
	private static byte[] fightcalc = null;
	private static byte[] fightercalc = null;
	private static byte[] fightpyros = null;
	private static byte[] fightyokes = null;
	private static byte[] skillmanager = null;

	private Globals globals = null;

	public static void init() {
		try {
			if (fightbuffer == null) {
				fightbuffer = loadLuaFile("fightbuffer.lua");
			}
			if (fightcalc == null) {
				fightcalc = loadLuaFile("fightcalc.lua");
			}
			if (fightercalc == null) {
				fightercalc = loadLuaFile("fightercalc.lua");
			}
			if (fightpyros == null) {
				fightpyros = loadLuaFile("fightpyros.lua");
			}
			if (fightyokes == null) {
				fightyokes = loadLuaFile("fightyokes.lua");
			}
			if (skillmanager == null) {
				skillmanager = loadLuaFile("skillmanager.lua");
			}
		} catch (Exception e) {
			LogUtil.error("UnionWar LuaFightEngine初始化失败");
			UnionWarUtil.setAllowOpen(false);
		}
	}

	private final static byte[] loadLuaFile(String fileName) throws MalformedURLException, IOException {
		File file = new File("script/" + fileName);
		if (file.exists()) {
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			return buffer;
		}
		HttpURLConnection httpConnection = null;
		InputStream is = null;
		try {
			httpConnection = (HttpURLConnection) new URL(httpDir + fileName).openConnection();
			httpConnection.setConnectTimeout(TIMEOUT_CONNECT);
			httpConnection.setReadTimeout(TIMEOUT_RECEIVE);
			int length = httpConnection.getContentLength();
			is = httpConnection.getInputStream();
			int pos = 0;
			int siz = 0;
			byte[] buffer = new byte[length];
			while ((siz = is.read(buffer, pos, length - pos)) > 0) {
				pos += siz;
			}
			if (pos == length) {
				return buffer;
			}
		} finally {
			if (is != null) {
				is.close();
			}
			if (httpConnection != null) {
				httpConnection.disconnect();
			}
		}
		return null;
	}

	public LuaFightEngine() {
		try {
			globals = JsePlatform.standardGlobals();
			globals.finder = new ResourceFinder() {
				public InputStream findResource(String filename) {
					if (filename.equals("skillmanager.lua")) {
						return new ByteArrayInputStream(skillmanager);
					}
					if (filename.equals("fightyokes.lua")) {
						return new ByteArrayInputStream(fightyokes);
					}
					if (filename.equals("fightpyros.lua")) {
						return new ByteArrayInputStream(fightpyros);
					}
					if (filename.equals("fightercalc.lua")) {
						return new ByteArrayInputStream(fightercalc);
					}
					if (filename.equals("fightbuffer.lua")) {
						return new ByteArrayInputStream(fightbuffer);
					}
					if (filename.equals("fightcalc.lua")) {
						return new ByteArrayInputStream(fightcalc);
					}
					return null;
				}
			};
			globals.set("FLAG_FOR_SERVER", LuaValue.valueOf(true));
			globals.set("Fight", globals.loadfile("fightcalc.lua").call());
		} catch (Exception e) {
			LogUtil.error("UnionWar LuaFightEngine构造失败");
			UnionWarUtil.setAllowOpen(false);
		}
	}

	protected static void getLuaCodeFromLuaTableSks(StringBuilder sb, LuaValue sks) {
		sb.append("sks={");
		for (int i = 1; i <= 10; ++i) { // ！！！ 扫描10个，标准上限3
			LuaValue sk = sks.get(i);
			if (sk == LuaValue.NIL) {
				break;
			}
			sb.append("{id=").append(sk.get("id")).append(",lv=").append(sk.get("lv")).append("},");
		}
		sb.append("},");
	}

	protected static void getLuaCodeFromLuaTablePyros(StringBuilder sb, LuaValue pyros) {
		sb.append("pyros={");
		for (int i = 1; i <= 12; ++i) { // ！！！ 扫描12个，标准上限4
			LuaValue pyro = pyros.get(i);
			if (pyro == LuaValue.NIL) {
				break;
			}
			sb.append("{id=").append(pyro.get("id")).append(",lv=").append(pyro.get("lv")).append("},");
		}
		sb.append("},");
	}

	protected static void getLuaCodeFromLuaTableFst(StringBuilder sb, LuaValue fst) {
		sb.append("{");
		{
			sb.append("name=\"").append(fst.get("name")).append("\",");
			sb.append("scale=").append(fst.get("scale")).append(",");
			sb.append("isBoss=").append(fst.get("isBoss")).append(",");
			sb.append("showBanner=").append(fst.get("showBanner")).append(",");
			sb.append("cardID=").append(fst.get("cardID")).append(",");
			sb.append("frameID=").append(fst.get("frameID")).append(",");
			sb.append("hp=").append(fst.get("hp")).append(",");
			sb.append("hit=").append(fst.get("hit")).append(",");
			sb.append("dodge=").append(fst.get("dodge")).append(",");
			sb.append("hitRatio=").append(fst.get("hitRatio")).append(",");
			sb.append("dodgeRatio=").append(fst.get("dodgeRatio")).append(",");
			sb.append("crit=").append(fst.get("crit")).append(",");
			sb.append("renxing=").append(fst.get("renxing")).append(",");
			sb.append("critRatio=").append(fst.get("critRatio")).append(",");
			sb.append("renxingRatio=").append(fst.get("renxingRatio")).append(",");
			sb.append("critRatioDHAdd=").append(fst.get("critRatioDHAdd")).append(",");
			sb.append("critRatioDHSub=").append(fst.get("critRatioDHSub")).append(",");
			sb.append("critPercentAdd=").append(fst.get("critPercentAdd")).append(",");
			sb.append("critPercentSub=").append(fst.get("critPercentSub")).append(",");
			sb.append("bufBurnReduction=").append(fst.get("bufBurnReduction")).append(",");
			sb.append("bufPoisonReduction=").append(fst.get("bufPoisonReduction")).append(",");
			sb.append("bufCurseReduction=").append(fst.get("bufCurseReduction")).append(",");
			sb.append("attPhsc=").append(fst.get("attPhsc")).append(",");
			sb.append("attMana=").append(fst.get("attMana")).append(",");
			sb.append("defPhsc=").append(fst.get("defPhsc")).append(",");
			sb.append("defMana=").append(fst.get("defMana")).append(",");
			sb.append("attPhscRatio=").append(fst.get("attPhscRatio")).append(",");
			sb.append("attManaRatio=").append(fst.get("attManaRatio")).append(",");
			sb.append("defPhscRatio=").append(fst.get("defPhscRatio")).append(",");
			sb.append("defManaRatio=").append(fst.get("defManaRatio")).append(",");
			sb.append("shuxingzengzhi=").append(fst.get("shuxingzengzhi")).append(",");
			sb.append("damageIncrease=").append(fst.get("damageIncrease")).append(",");
			sb.append("jjCur=").append(fst.get("jjCur")).append(",");
			sb.append("jjMax=").append(fst.get("jjMax")).append(",");
			{
				getLuaCodeFromLuaTableSks(sb, fst.get("sks"));
			}
			{
				getLuaCodeFromLuaTablePyros(sb, fst.get("pyros"));
			}
		}
		sb.append("},");
	}

	protected static void getLuaCodeFromLuaTableFighters(StringBuilder sb, LuaValue data) {
		sb.append("mainForce={");
		{
			LuaValue mainForce = data.get("mainForce");
			for (int i = 1; i <= 6; ++i) { // ！！！标准上限 6
				LuaValue fst = mainForce.get(i);
				if (fst == LuaValue.NIL) {
					sb.append("nil,");
				} else {
					getLuaCodeFromLuaTableFst(sb, fst);
				}
			}
		}
		sb.append("},");
		sb.append("substitute={");
		{
			LuaValue substitute = data.get("substitute");
			for (int i = 1; i <= 3; ++i) { // ！！！标准上限3
				LuaValue fst = substitute.get(i);
				if (fst == LuaValue.NIL) {
					sb.append("nil,");
					break;
				} else {
					getLuaCodeFromLuaTableFst(sb, fst);
				}
			}
		}
		sb.append("},");
		sb.append("power=").append(data.get("power")).append(",");
	}

	public FightOutput calculate(FightData a, FightData b) {
		LuaValue fight = globals.get("Fight");

		fight.get("doInit").call(new FightInput(a, b).toLuaValue());

		FightOutput fr = new FightOutput();
		StringBuilder sb = new StringBuilder();
		LuaValue initData = fight.get("initData");
		sb.append("local v={");
		{
			sb.append("allowSpeed3=").append(initData.get("allowSpeed3")).append(",");
			sb.append("isPVE=").append(initData.get("isPVE")).append(",");
			sb.append("isSelfFirst=").append(initData.get("isSelfFirst")).append(",");
			sb.append("isBoss=").append(initData.get("isBoss")).append(",");
			sb.append("allowSkipFight=").append(initData.get("allowSkipFight")).append(",");
			sb.append("skipEmbattle=").append(initData.get("skipEmbattle")).append(",");
			sb.append("bgImagePath0=\"").append(initData.get("bgImagePath0")).append("\",");
			sb.append("bgImagePath1=\"").append(initData.get("bgImagePath1")).append("\",");
			sb.append("myData={");
			{
				getLuaCodeFromLuaTableFighters(sb, initData.get("myData"));
			}
			sb.append("},");
			sb.append("otherData={");
			{
				LuaValue otherData = initData.get("otherData");
				for (int i = 1; i <= 3; ++i) { // TODO 应该只有一个
					LuaValue data = otherData.get(i);
					if (data == LuaValue.NIL) {
						break;
					}
					sb.append("{");
					getLuaCodeFromLuaTableFighters(sb, data);
					sb.append("},");
				}
			}
			sb.append("},");
			sb.append("script={"); // TODO 禁止脚本
			sb.append("},");
			sb.append("record={");
			{
				LuaValue record = initData.get("record");
				sb.append("randomNum={");
				{
					LuaValue randomNum = record.get("randomNum");
					int len = randomNum.length();
					for (int i = 1; i <= len; ++i) {
						LuaValue rn = randomNum.get(i);
						sb.append("{").append(rn.get(1)).append(",").append(rn.get(2)).append(",").append(rn.get(3)).append(",").append(rn.get(4)).append("},");
					}
				}
				sb.append("},");
				sb.append("manualAct={},mmmmmmAct={},swapPos={{},{},{}},");
			}
			sb.append("},");
			sb.append("result={");
			{
				LuaValue result = initData.get("result");
				fr.setWin(result.get("isWin").toboolean());
				sb.append("isWin=").append(result.get("isWin")).append(",");
				sb.append("fightIndex=").append(result.get("fightIndex")).append(",");
				sb.append("bigRound=").append(result.get("bigRound")).append(",");
				sb.append("myDeaths=").append(result.get("myDeaths")).append(",");
				sb.append("hpPercent=").append(result.get("hpPercent")).append(",");
				sb.append("bossDamage=").append(result.get("bossDamage")).append(",");
				sb.append("hash={");
				{
					LuaValue hash = result.get("hash");
					sb.append("isWin=\"").append(hash.get("isWin")).append("\",");
					sb.append("fightIndex=\"").append(hash.get("fightIndex")).append("\",");
					sb.append("bigRound=\"").append(hash.get("bigRound")).append("\",");
					sb.append("myDeaths=\"").append(hash.get("myDeaths")).append("\",");
					sb.append("hpPercent=\"").append(hash.get("hpPercent")).append("\",");
					sb.append("bossDamage=\"").append(hash.get("bossDamage")).append("\",");
				}
				sb.append("},");
				// sb.append("fightersHP = {");
				// {
				// LuaValue fightersHP = result2.get("fightersHP");
				// LuaValue k = LuaValue.NIL;
				// Varargs vs;
				// while ((vs = fightersHP.next(k)) != LuaValue.NIL) {
				// if ((k = vs.arg1()) == LuaValue.NIL) {
				// break;
				// }
				// sb.append(k).append("=").append(vs.arg(2)).append(",");
				// }
				// }
				// sb.append("},");
			}
			sb.append("},");
		}
		sb.append("}");

		sb.append("local h={}");
		LuaValue fightersHP = initData.get("result").get("fightersHP");
		LuaValue k = LuaValue.NIL;
		Varargs vs;
		while ((vs = fightersHP.next(k)) != LuaValue.NIL) {
			if ((k = vs.arg1()) == LuaValue.NIL) {
				break;
			}
			sb.append("h[").append(k).append("]=").append(vs.arg(2)).append("\n");
		}
		sb.append("v.result.fightersHP=h\n");
		sb.append("return v");
		fight.get("doFree").call();
		fr.setVideoLua(sb.toString());
		return fr;
	}

}
