package mmo.common.bean.buf;

import java.util.Map;

import mmo.common.bean.role.Role;
import mmo.common.bean.skill.ASkill;

public interface IBuf {

	/**
	 * 获取触发过滤器的角色
	 * 
	 * @return 触发过滤器的角色
	 */
	public Role getFilterTrigger();

	/**
	 * 获取刷新间隔
	 * 
	 * @return
	 */
	public int getRefreshOffset();

	/**
	 * 设置触发过滤器的角色
	 */
	public void setFilterTrigger(Role trigger);

	/**
	 * BUF对应的技能编号
	 * 
	 * @return 技能编号
	 */
	public int getSkillid();

	/**
	 * BUF名称
	 * 
	 * @return 名称
	 */
	public String getName();

	/**
	 * 是否结束
	 * 
	 * @return true结束，false未结束
	 */
	public boolean isFinish();

	/**
	 * 是否要持久化
	 * 
	 * @return
	 */
	public boolean isPersist();

	/**
	 * 刷新
	 * 
	 * @return true刷新，false未刷新
	 */
	public boolean refresh();

	/**
	 * 通知角色移除该BUF
	 * 
	 * @param role
	 *            角色对象
	 */
	public void clearBuf(Role role);

	/**
	 * 通知角色创建BUF
	 * 
	 * @param role
	 *            角色对象
	 */
	public void createBuf(Role role);

	/**
	 * 清除BUF
	 */
	public void clearBuf();

	/**
	 * 执行BUF
	 * 
	 * @return
	 */
	public boolean playSkill();

	/***
	 * 重置BUF
	 */
	public void reset();

	/**
	 * 获取剩余时间
	 * 
	 * @return 剩余时间
	 */
	public int getRemainTime();

	/**
	 * 是否为减益性BUF
	 * 
	 * @return true减益
	 */
	public boolean isMinus();

	/**
	 * 更新角色的指定buf的计时器时间（毫秒）
	 * 
	 * @param value
	 *            计时器变化量（value>0代表加长，value<0代表缩短）
	 * @return 当前的计时器时间（毫秒），-1代表计时器结束或角色没有该buf
	 */
	public int updateRemainTime(int value);

	/**
	 * 获取附加类别
	 * 
	 * @return 附加类别值
	 */
	public short getExtraCate();

	/**
	 * 添加HP的变化量
	 * 
	 * @param changeHp
	 *            HP变化量
	 */
	public void addParameterHp(int changeHp);

	/**
	 * 添加HP的变化量
	 * 
	 * @param changeMp
	 *            MP变化量
	 */
	public void addParameterMp(int changeMp);

	/**
	 * 添加经验的变化量
	 * 
	 * @param changeExp
	 *            经验变化量
	 */
	public void addParameterExp(int changeExp);

	/**
	 * 获得HP的变化量
	 * 
	 * @return HP的变化量
	 */
	public int getHpChangeValue();

	/**
	 * 获得MP的变化量
	 * 
	 * @return MP的变化量
	 */
	public int getMpChangeValue();

	/**
	 * 获得经验的变化量
	 * 
	 * @return 经验的变化量
	 */
	public int getExpChangeValue();

	/**
	 * 通知客户端创建BUF
	 */
	public void createBuf();

	/**
	 * buf依赖的宿主对象
	 * 
	 * @return
	 */
	public Role getHost();

//	/**
//	 * 获取BUF的依附对象
//	 * 
//	 * @return
//	 */
//	public Role getOperateTarget();

	/**
	 * 设置参数
	 * 
	 * @param parameters
	 *            BUF执行逻辑需要的参数
	 */
	public void setParameters(Map<Integer, Integer> parameters);

	/**
	 * 初始化状态区域
	 * 
	 * @param parent
	 *            创建状态区域的技能
	 * @param continueTime
	 *            持续时间（ms）
	 * @param refreshOffset
	 *            刷新间隔（ms）
	 * @param centerX
	 *            中心坐标像素位置-X
	 * @param centerY
	 *            中心坐标像素位置-Y
	 * @param radiusX
	 *            像素半径-X
	 * @param radiusY
	 *            像素半径-Y
	 * @param actId
	 *            动作ID
	 */
	public void initStateZone(ASkill parent, int continueTime, int refreshOffset, short centerX, short centerY, short radiusX, short radiusY,
	        int actId);

	/**
	 * 初始化状态区域
	 * 
	 * @param parent
	 *            创建状态区域的技能
	 * @param centerX
	 *            中心坐标像素位置-X
	 * @param centerY
	 *            中心坐标像素位置-Y
	 * @param radiusX
	 *            像素半径-X
	 * @param radiusY
	 *            像素半径-Y
	 */
	public void initStateZone(ASkill parent, int centerX, int centerY, int radiusX, int radiusY);

	/**
	 * 获取BUF本身的叠加层数上限
	 * 
	 * @return
	 */
	public int getMaxNumber();

	/**
	 * 获取角色身上该BUF的当前叠加层数
	 * 
	 * @return
	 */
	public int getCurrentNumber();

	/**
	 * 修改角色身上该BUF当前叠加层数
	 * 
	 * @param currentNumber
	 *            层数
	 */
	public void setCurrentNumber(int currentNumber);

	/**
	 * 清除该BUF的层数
	 */
	public void clearCurrentNumber();

	/**
	 * 获取技能效果五行属性
	 * 
	 * @return
	 */
	public short getFiveElements();

	/**
	 * 获取BUF的释放者
	 * 
	 * @return
	 */
	public Role getFirer();
}
