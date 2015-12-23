package mmo.common.module.sdk.token;

import mmo.http.ICallback;
import mmo.tools.java.IReloadClasses;

public interface ILoginSDKCallback extends IReloadClasses, ICallback {

	public void validateToken(AClientData clientData);

}
