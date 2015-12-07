package mmo.tools.script.vm;

public class HostAPIFunction extends HostAPI {

	public HostAPIFunction(ScriptVM v) {
		super(v);
		funcNames = new String[] { "SETLOCATION", // 设置飞船坐标
		        "REPAINT", // 重绘屏幕实现
		        "SLEEP", // 延迟实现
		        "PRINTSTRING", // 打印
		        "ISSHUTTER", //
		        "OPENSHUTTER", //
		};
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * HAPI_PrintString ()
	 * <p/>
	 * This is a simple host API function that scripts can call to print strings a specified number of times, as well as
	 * receive an arbitrary return value.
	 */

	private void HAPI_PrintString(int iThreadIndex) {
		String pstrString = vm.XS_GetParamAsString(iThreadIndex, 0);
		System.out.println("\nfunc(HAPI_PrintString):\t " + pstrString);
		vm.XS_ReturnFromHost(iThreadIndex, 1);
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * HAPI_PrintString ()
	 * <p/>
	 * Prints a newline to the console.
	 */

	public void HAPI_PrintNewline(int iThreadIndex) {
		// Print the newline

		System.out.print("\n");

		// Return to the XVM
		vm.XS_ReturnFromHost(iThreadIndex, 0);
	}

	// public void HAPI_isShutter(int iThreadIndex) {
	//
	// boolean flg = cgame.isShutter();
	// // System.out.println("ScriptVM$HostAPIFunc.HAPI_isShutter  :" + flg);
	// // Return to the XVM
	// vm.XS_ReturnIntFromHost(iThreadIndex, 0, flg ? 1 : 0);
	// }

	// public void HAPI_openShutter(int iThreadIndex) {
	// int flg = vm.XS_GetParamAsInt(iThreadIndex, 0);
	// cgame.openShutter((flg == 0) ? false : true);
	// // Return to the XVM
	// vm.XS_ReturnFromHost(iThreadIndex, 1);
	// }

	/**
	 * ***************************************************************************************
	 * <p/>
	 * HAPI_PrintTab ()
	 * <p/>
	 * Prints a tab to the console.
	 */

	public void HAPI_PrintTab(int iThreadIndex) {
		// Print the tab

		System.out.print("\t");

		// Return to the XVM

		vm.XS_ReturnFromHost(iThreadIndex, 0);
	}

	public boolean call(String funcName, int script) {

		int scriptIndex = script;
		int funcIndex = getFuncId(funcName);
		if (funcIndex < 0) {
			return false;
		}
		switch (funcIndex) {
			// case 0:
			// int x = vm.XS_GetParamAsInt(scriptIndex, 0);
			// int y = vm.XS_GetParamAsInt(scriptIndex, 1);
			// // 判断是哪个脚本线程调用的该方法
			// if (cgame.ship_thread1 == scriptIndex)
			// cgame.ship1.setXY(x, y);
			// else if (cgame.ship_thread2 == scriptIndex)
			// cgame.ship2.setXY(x, y);
			// vm.XS_ReturnIntFromHost(scriptIndex, 2, 0);
			// break;
			// case 1:
			// cgame.repaint();
			// cgame.serviceRepaints();
			// break;
			case 2:
				int time = vm.XS_GetParamAsInt(scriptIndex, 0);
				vm.XS_PauseScript(scriptIndex, time);
				vm.XS_ReturnIntFromHost(scriptIndex, 1, 0);
				break;
			case 3:
				HAPI_PrintString(scriptIndex);
				break;
			// case 4:
			// HAPI_isShutter(scriptIndex);
			// break;
			// case 5:
			// HAPI_openShutter(scriptIndex);
			// break;
		}
		return true;
	}
}
