package mmo.tools.util;

import java.io.DataInput;
import java.io.IOException;

public class MapUtil {

	public  short[][] phy;

	public  int       mapWidth;
	public  int       mapHeight;
	public  boolean   largeTileID;

	public  int       tileWidth;
	public  int       tileHeight;
	public  int       halfTileWidth;
	public  int       halfTileHeight;

	 String[]         tileImages;
	 byte[][]         tileImageIDs;

	public  final boolean isImpassableTile(int i, int j) {
		return isOutMap(i, j) || (phy[i][j] & 0xF) != 0;
	}

	public  final boolean isOutMap(int i, int j) {
		return i < 0 || i >= mapHeight || j < 0 || j >= mapWidth;
	}

	public void load(DataInput dis) throws IOException {
		mapHeight = dis.readByte() & 0xFF;
		mapWidth = dis.readByte() & 0xFF;
		largeTileID = dis.readBoolean();
		loadPhy(dis);
		loadTileImage(dis);

		tileHeight = 16;
		tileWidth = 16;
		halfTileHeight = 8;
		halfTileWidth = 8;
	}

	private  final void loadPhy(DataInput dis) throws IOException {
		phy = new short[mapHeight][mapWidth];
		for (int i = 0; i < phy.length; i++) {
			for (int j = 0; j < phy[i].length; j++) {
				phy[i][j] = dis.readByte();
			}
		}
	}

	private  final void loadTileImage(DataInput dis) throws IOException {
		int imageLength = dis.readByte() & 0xFF;
		tileImages = new String[imageLength];
		tileImageIDs = new byte[imageLength][];
		for (int i = 0; i < tileImages.length; i++) {
			tileImages[i] = dis.readUTF();
			tileImageIDs[i] = new byte[dis.readByte() & 0xFF];
			dis.readFully(tileImageIDs[i]);
		}
	}

}
