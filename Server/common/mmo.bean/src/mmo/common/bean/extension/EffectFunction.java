package mmo.common.bean.extension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 效果
 * 
 * @author 肖琼
 * 
 */
public class EffectFunction {
	private int            effectId;         // 效果ID
	private String         effectName = null; // 效果名称
	private List<Function> conditions = null; // 激活效果的条件
	private int            functionId;       // 效果绑定的作用ID,用作套装属性时，表示激活所需的最小装备个数

	public EffectFunction(int effectId) {
		this.effectId = effectId;
		conditions = new ArrayList<Function>();
	}

	public void release() {
		if (conditions != null) {
			conditions.clear();
		}
		conditions = null;
	}

	public void addCondition(Function fc) {
		if (conditions == null) {
			conditions = new ArrayList<Function>();
		}
		conditions.add(fc);
	}

	public List<Function> getConditions() {
		if (conditions == null) {
			Collections.emptyList();
		}
		return conditions;
	}

	public int getEffectId() {
		return effectId;
	}

	public void setEffectId(int effectId) {
		this.effectId = effectId;
	}

	public String getEffectName() {
		return effectName;
	}

	public void setEffectName(String effectName) {
		this.effectName = effectName;
	}

	public int getFunctionId() {
		return functionId;
	}

	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}

}
