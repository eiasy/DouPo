package mmo.tools.script.vm;

public abstract class HostAPI {

		protected ScriptVM vm       = null;
		protected String[] funcNames = null;

		public HostAPI(ScriptVM svm) {
				this.vm = svm;
		}

		protected int getFuncId(String funcName) {
				if (funcNames == null) {
						return -1;
				}
				for (int i = 0; i < funcNames.length; i++) {
						if (funcName.equals(funcNames[i])) {
								return i;
						}
				}
				return -1;
		}

		public abstract boolean call(String funcName, int scriptIndex);
}
