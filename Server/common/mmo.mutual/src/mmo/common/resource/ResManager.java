package mmo.common.resource;

public class ResManager {
	private static String sceneDir = null;

	public static String getSceneDir() {
		return sceneDir;
	}

	public static void setSceneDir(String sceneDir) {
		ResManager.sceneDir = sceneDir;
	}

}
