package com.huayi.doupo.base.model.logic;

/**
 * 占星  上方星星对象
 * @author mp
 * @date 2015-12-7 下午3:57:37
 */
public class UpStar {
	
	/**
	 * 位置
	 */
	private int position;
	
	/**
	 * 星星Id
	 */
	private int starId;
	
	/**
	 * 状态0-未点亮 1-点亮
	 */
	private int state;

	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getStarId() {
		return starId;
	}

	public void setStarId(int starId) {
		this.starId = starId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
}
