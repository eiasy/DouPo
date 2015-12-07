package mmo.common.module.service.charge;

import mmo.http.ICallback;
import mmo.tools.java.IReloadClasses;

public interface IChargeSDKCallback extends ICallback, IReloadClasses {

	public Class getClass(String className);
}
