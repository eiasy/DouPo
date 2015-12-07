package mmo.common.bean.scene;

import mmo.common.config.scene.SceneCate;
import mmo.tools.path.Path;
import mmo.tools.util.MathUtil;
import mmo.tools.util.StringUtil;

/**
 * GameMap entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class GameScene implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/** 持久化字段 */
	protected int             id;
	protected String          name;
	protected String          datafile;
	protected byte            pk;
	protected byte            cate;
	protected String          mark;
	protected String          note;
	protected short           interspace;
	protected int             region;
	protected short           mapWidth;
	protected short           mapHeight;
	protected byte            weather;
	protected short           musicid;
	protected short           dhx;
	protected short           dhy;
	protected int             reliveOffset;
	protected short           reliveX;
	protected short           reliveY;
	protected byte            state;
	protected short           levelSub;             // 等级下线
	protected short           levelSup;             // 等级上限
	protected short           roleLevel;            // 限制等级
	protected int             focusScene;           // 聚焦场景
	protected boolean         isSectScene;
	protected String          script;               // 场景脚本
	/** 副本模式 */
	protected byte            mode;
	/** 音乐文件名 */
	protected String[]        musicFile;

	public byte getMode() {
		return mode;
	}

	public void setMode(byte mode) {
		this.mode = mode;
	}

	public String getMusicFile() {
		String music = "";
		if (musicFile != null && musicFile.length > 0) {
			music = musicFile[MathUtil.getRandom(0, musicFile.length - 1)];
		}
		return music;
	}

	public void setMusicFile(String[] musicFile) {
		this.musicFile = musicFile;
	}

	public boolean isSectScene() {
		return isSectScene;
	}

	protected short[][] phy;

	// Constructors

	/** default constructor */
	public GameScene() {
	}

	// Property accessors

	public byte getCate() {
		return cate;
	}

	public String getDatafile() {
		return datafile;
	}

	// public byte[] getDatafileData() {
	// return datafileData;
	// }

	public int getFocusScene() {
		return focusScene;
	}

	public void setFocusScene(int focusScene) {
		this.focusScene = focusScene;
	}

	public short getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(short roleLevel) {
		this.roleLevel = roleLevel;
	}

	public short getDhx() {
		return this.dhx;
	}

	public short getDhy() {
		return this.dhy;
	}

	public int getId() {
		return this.id;
	}

	public short getInterspace() {
		return this.interspace;
	}

	public short getLevelSub() {
		return levelSub;
	}

	public short getLevelSup() {
		return levelSup;
	}

	public String getMark() {
		return this.mark;
	}

	public short getMusicid() {
		return this.musicid;
	}

	public String getName() {
		return this.name;
	}

	public String getNote() {
		return this.note;
	}

	public byte getPk() {
		return this.pk;
	}

	public int getRegion() {
		return region;
	}

	public int getReliveOffset() {
		return this.reliveOffset;
	}

	public short getReliveX() {
		return this.reliveX;
	}

	public short getReliveY() {
		return this.reliveY;
	}

	public String getSceneInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("场景ID：");
		if (id == 0) {
			sb.append("******");
		} else {
			sb.append(id);
		}
		sb.append(StringUtil.BR).append("名  称：").append(getName()).append(StringUtil.BR).append("描  述：").append(StringUtil.BR).append("    ")
		        .append(getNote());
		return sb.toString();
	}

	public byte getState() {
		return this.state;
	}

	public short getTileHeight() {
		return this.mapHeight;
	}

	public short getTileWidth() {
		return this.mapWidth;
	}

	public byte getWeather() {
		return this.weather;
	}

	public void setCate(byte cate) {
		this.cate = cate;
		this.isSectScene = getCate() == SceneCate.SECT;
	}

	public void setDatafile(String datafile) {
		this.datafile = datafile;
	}

	// public void setDatafileData(byte[] datafileData) {
	// this.datafileData = datafileData;
	// }

	public void setDhx(short dhx) {
		this.dhx = dhx;
	}

	public void setDhy(short dhy) {
		this.dhy = dhy;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setInterspace(short interspace) {
		this.interspace = interspace;
	}

	public void setLevelSub(short levelSub) {
		this.levelSub = levelSub;
	}

	public void setLevelSup(short levelSup) {
		this.levelSup = levelSup;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public void setMusicid(short musicid) {
		this.musicid = musicid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setPk(byte pk) {
		this.pk = pk;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public void setReliveOffset(int reliveOffset) {
		this.reliveOffset = reliveOffset;
	}

	public void setReliveX(short relivex) {
		this.reliveX = relivex;
	}

	public void setReliveY(short relivey) {
		this.reliveY = relivey;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public void setTileH(short tileh) {
		this.mapHeight = tileh;
	}

	public void setTileW(short tilew) {
		this.mapWidth = tilew;
	}

	public void setWeather(byte weather) {
		this.weather = weather;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String toString() {
		return "【" + id + ": " + name + "】";
	}

	public short[][] getPhy() {
		return phy;
	}

	public boolean isPhy(int cx, int cy) {
		return isOutMap(cx, cy) || (phy[cy][cx] & Path.PHY_MASK) != 0;
	}

	public final boolean isOutMap(int i, int j) {
		return i < 0 || i >= mapWidth || j < 0 || j >= mapHeight;
	}
}