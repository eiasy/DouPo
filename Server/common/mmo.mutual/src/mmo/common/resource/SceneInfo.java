package mmo.common.resource;

import java.util.Arrays;

public class SceneInfo {
	private String  sceneName;
	private short[] resourceIndex;

	public void release() {
		sceneName = null;
		resourceIndex = null;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public short[] getResourceIndex() {
		return resourceIndex;
	}

	public void setResourceIndex(short[] resourceIndex) {
		this.resourceIndex = resourceIndex;
	}

	@Override
	public String toString() {
		return "SceneResource [sceneName=" + sceneName + ", resourceIndex=" + Arrays.toString(resourceIndex) + "]";
	}
}
