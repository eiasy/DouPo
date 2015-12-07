package mmo.common.bean.passDuplicate;

public class PassDuplicateLayer {
	private int    id;
	private String name;
//	private int    cost;
	private int    index;
	private int    mapId;
	private int    nextMapId;
	private int    tileX;
	private int    tileY;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public int getCost() {
//		return cost;
//	}
//
//	public void setCost(int cost) {
//		this.cost = cost;
//	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public int getNextMapId() {
		return nextMapId;
	}

	public void setNextMapId(int nextMapId) {
		this.nextMapId = nextMapId;
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

}
