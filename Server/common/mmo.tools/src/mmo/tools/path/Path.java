package mmo.tools.path;

/**
 * 
 * @author 段链
 * 
 */
public class Path {
	private static final int TILE_SIZE            = 32;
	private static final int TILE_SIZE_HALF       = 16;
	/**
	 * 预分配的寻路点个数
	 */
	public static final int  POINT_LENGTH         = 50;
	/**
	 * 预分配的路径点个数
	 */
	public static final int  PATH_LENGTH          = 10;
	/**
	 * 表示寻路点是阻挡的组成部分
	 */
	public static int        PHY_MASK             = 0x1;
	/**
	 * 表示寻路点是阻挡层边缘的组成部分
	 */
	public static int        EDGE_MASK            = 0x4;
	/**
	 * 表示寻路点在开启列表中
	 */
	public static int        OPENED_MASK          = 0x8;
	/**
	 * 表示寻路点在关闭列表中
	 */
	public static int        CLOSED_MASK          = 0x10;
	/**
	 * 表示寻路点是临时阻挡层的组成部分
	 */
	public static int        TEMP_IMPASSABLE_MASK = 0x20;
	/**
	 * 表示寻路点是临时阻挡层边缘的组成部分
	 */
	public static int        TEMP_EDGE_MASK       = 0x40;
	/**
	 * 表示寻路点无法通过
	 */
	public static final int  IMPASSABLE_MASK      = PHY_MASK | TEMP_IMPASSABLE_MASK;

	static short[][]         phy;
	static long[]            points;                                                // [i, j, g, f]
	static int[]             fatherIDs;
	static int[]             openList;

	static int               openListCount;
	static int               pointCount;
	static int               pathPointCount;

	static int               starti;
	static int               startj;
	static int               desti;
	static int               destj;

	static int               breakPointRow;
	static int               breakPointCol;
	static int               breakPointG;

	static int               destPoint;

	static boolean           notFound;

	static {
		points = new long[Path.POINT_LENGTH];
		fatherIDs = new int[Path.POINT_LENGTH];
		openList = new int[Path.POINT_LENGTH];
		openListCount = 0;
		pointCount = 0;
		notFound = true;
	}

	/**
	 * 服务器寻路
	 * 
	 * @param sj
	 *            起点tileX
	 * @param si
	 *            起点tileY
	 * @param dj
	 *            终点tileX
	 * @param di
	 *            终点tileY
	 * @return 路径=[tileX, tileY, ... ..., tileX, tileY]
	 */
	public static final int[] findPath(short[][] phy, int sj, int si, int dj, int di) {
		Path.phy = phy;
		starti = si;
		startj = sj;
		desti = di;
		destj = dj;
		findPath();
		formatPath();
		int[] path = buildPath();
		reset();
		clear();
		return path;
	}

	private static final void findPath() {
		int p;

		p = adjustPointIfImpassable(desti, destj);
		desti = Lib.getX(p);
		destj = Lib.getY(p);

		p = adjustPointIfImpassable(starti, startj);
		starti = Lib.getX(p);
		startj = Lib.getY(p);

		p = getPoint(starti, startj, 0, calculateH(starti, startj), -1);
		add(p);
		setFlag(starti, startj, Path.OPENED_MASK);

		int i;
		int j;
		int flag;
		while (openListCount > 0 && notFound) {
			p = poll();
			i = getI(p);
			j = getJ(p);
			setFlag(i, j, Path.CLOSED_MASK);

			flag = whetherTwoPointsConnected(i, j, desti, destj);
			if (flag > 0) {
				notFound = false;
				destPoint = getPoint(desti, destj, breakPointG + getG(p), 0, p);
			} else if (flag == 0 && !hasFlag(breakPointRow, breakPointCol, Path.OPENED_MASK | Path.CLOSED_MASK)
			        && canExpandFrom(breakPointRow, breakPointCol)) {
				add(getPoint(breakPointRow, breakPointCol, breakPointG + getG(p), calculateH(breakPointRow, breakPointCol), p));
				setFlag(breakPointRow, breakPointCol, Path.OPENED_MASK);
				if (isEdgePoint(i, j)) {
					expandFrom(p);
				}
			} else {
				expandFrom(p);
			}
		}
	}

	private static final int adjustPointIfImpassable(int i, int j) {
		if (!hasFlag(i, j, Path.IMPASSABLE_MASK)) {
			return Lib.getPoint(i, j);
		}

		int centerPoint = getPoint(i, j, 0, 0, -1);
		add(centerPoint);
		setFlag(i, j, Path.OPENED_MASK);

		int p;
		int g;
		while (openListCount > 0) {
			centerPoint = poll();
			i = getI(centerPoint);
			j = getJ(centerPoint);
			setFlag(i, j, Path.CLOSED_MASK);

			if (!hasFlag(i, j, Path.IMPASSABLE_MASK)) {
				reset();
				return Lib.getPoint(i, j);
			}

			for (int rowOffset = -1; rowOffset < 2; rowOffset++) {
				i = getI(centerPoint) + rowOffset;
				for (int colOffset = -1; colOffset < 2; colOffset++) {
					j = getJ(centerPoint) + colOffset;
					if ((rowOffset == 0 && colOffset == 0) || isOutFlag(i, j) || hasFlag(i, j, Path.CLOSED_MASK)) {
						continue;
					}

					g = calculateG(rowOffset, colOffset) + getG(centerPoint);
					if (hasFlag(i, j, Path.OPENED_MASK)) {
						p = search(i, j);
						if (getG(p) > g) {
							setG(p, g);
							fatherIDs[p] = centerPoint;
						}
					} else {
						p = getPoint(i, j, g, 0, centerPoint);
						add(p);
						setFlag(i, j, Path.OPENED_MASK);
					}
				}
			}
		}
		reset();

		return -1;
	}

	private static final int search(int i, int j) {
		int point;
		for (int p = 1; p <= openListCount; p++) {
			point = openList[p];
			if (getI(point) == i && getJ(point) == j) {
				return point;
			}
		}

		return -1;
	}

	private static final int whetherTwoPointsConnected(int startRow, int startCol, int destRow, int destCol) {
		Line iter = Lib.iter;
		iter.setLine(startRow, startCol, destRow, destCol);
		while (iter.hasNext()) {
			if (hasFlag(iter.x, iter.y, Path.IMPASSABLE_MASK)) {
				iter.back();
				break;
			}
			iter.next();
		}

		if (iter.hasNext()) {
			if (iter.x == iter.startX && iter.y == iter.startY) {
				return -1;
			} else {
				breakPointRow = iter.x;
				breakPointCol = iter.y;
				breakPointG = getManhattanDistance(startRow, startCol, breakPointRow, breakPointCol);
				return 0;
			}
		} else {
			breakPointG = getManhattanDistance(startRow, startCol, destRow, destCol);
			return 1;
		}
	}

	private static final boolean canExpandFrom(int centeri, int centerj) {
		int i;
		int j;
		for (int rowOffset = -1; rowOffset < 2; rowOffset++) {
			i = centeri + rowOffset;
			for (int colOffset = -1; colOffset < 2; colOffset++) {
				j = centerj + colOffset;
				if ((rowOffset != 0 || colOffset != 0) && isEdgePoint(i, j)) {
					return true;
				}
			}
		}

		return false;
	}

	private static final void expandFrom(int centerPoint) {
		int i;
		int j;
		int g;
		int p;
		int father = fatherIDs[centerPoint];
		for (int rowOffset = -1; rowOffset < 2; rowOffset++) {
			i = getI(centerPoint) + rowOffset;
			for (int colOffset = -1; colOffset < 2; colOffset++) {
				j = getJ(centerPoint) + colOffset;
				if ((rowOffset != 0 || colOffset != 0) && isEdgePoint(i, j)) {
					g = calculateG(rowOffset, colOffset) + getG(centerPoint);

					if (father < 0 || whetherTwoPointsAdjacent(centerPoint, father)
					        || whetherTwoPointsConnected(getI(father), getJ(father), i, j) <= 0) {
						p = getPoint(i, j, g, calculateH(i, j), centerPoint);
					} else {
						p = getPoint(i, j, breakPointG + getG(father), calculateH(i, j), father);
					}

					add(p);
					setFlag(i, j, Path.OPENED_MASK);

					if (i == desti && j == destj) {
						destPoint = p;
						notFound = false;
						return;
					}
				}
			}
		}
	}

	public static final boolean isEdgePoint(int i, int j) {
		if (isOutFlag(i, j)) {
			return false;
		}
		if (hasFlag(i, j, Path.OPENED_MASK | Path.CLOSED_MASK)) {
			return false;
		}
		if (hasFlag(i, j, Path.TEMP_EDGE_MASK)) {
			return true;
		} else if (hasFlag(i, j, Path.EDGE_MASK) && !hasFlag(i, j, Path.TEMP_IMPASSABLE_MASK)) {
			return true;
		}

		return false;
	}

	private static final boolean whetherTwoPointsAdjacent(int p1, int p2) {
		return Math.abs(getI(p1) - getI(p2)) + Math.abs(getJ(p1) - Math.abs(getJ(p2))) <= 2;
	}

	private static final void formatPath() {
		int end1 = destPoint;
		int end2;
		pathPointCount = 1;
		while (end1 >= 0) {
			end2 = fatherIDs[end1];
			if (end2 >= 0) {
				end2 = fatherIDs[end2];
			} else {
				break;
			}
			while (end2 >= 0) {
				if (whetherTwoPointsConnected(getI(end2), getJ(end2), getI(end1), getJ(end1)) > 0) {
					fatherIDs[end1] = end2;
				}
				end2 = fatherIDs[end2];
			}
			end1 = fatherIDs[end1];
			pathPointCount++;
		}
	}

	private static int[] buildPath() {
		int[] path = new int[pathPointCount];
		int p = destPoint;
		for (int i = pathPointCount - 1; i >= 0; i--) {
			path[i] = (getJ(p) * TILE_SIZE + TILE_SIZE_HALF) << 16 | (getI(p) * TILE_SIZE + TILE_SIZE_HALF);
			p = fatherIDs[p];
		}
		return path;
	}

	private static final void reset() {
		int mask = Path.OPENED_MASK | Path.CLOSED_MASK;
		for (int i = 0; i < pointCount; i++) {
			removeFlag(getI(i), getJ(i), mask);
		}
		openListCount = 0;
		pointCount = 0;
		notFound = true;
	}

	public static final void clearTempPhy(short[][] tempPhy) {
		Path.phy = tempPhy;
		int mask = Path.TEMP_IMPASSABLE_MASK | Path.TEMP_EDGE_MASK;
		for (int i = 0; i < phy.length; i++) {
			for (int j = 0; j < phy[i].length; j++) {
				phy[i][j] &= ~mask;
			}
		}
	}

	private static final void clear() {
		int mask = Path.OPENED_MASK | Path.CLOSED_MASK;
		for (int i = 0; i < phy.length; i++) {
			for (int j = 0; j < phy[i].length; j++) {
				phy[i][j] &= ~mask;
			}
		}
	}

	private static final int getPoint(int i, int j, int g, int h, int fatherID) {
		points = Lib.ensureCapacity(points, pointCount + 1);
		fatherIDs = Lib.ensureCapacity(fatherIDs, pointCount + 1);

		points[pointCount] = (((long) i) << 48) + (((long) j) << 32) + (((long) g) << 16) + (((long) (g + h)) << 0);
		fatherIDs[pointCount] = fatherID;

		return pointCount++;
	}

	private static final void add(int p) {
		openList = Lib.ensureCapacity(openList, openListCount + 2);
		bubbleUp(++openListCount, p);
	}

	private static final int poll() {
		int first = openList[1];
		pushDown(1, openList[openListCount--]);
		return first;
	}

	private static final void bubbleUp(int pos, int p) {
		openList[pos] = p;
		int fatherPos;

		while (pos > 1) {
			fatherPos = pos >> 1;
			if (compare(openList[fatherPos], openList[pos]) > 0) {
				swap(fatherPos, pos);
				pos = fatherPos;
			} else {
				return;
			}
		}
	}

	private static final void pushDown(int pos, int p) {
		openList[pos] = p;
		int childPos;

		while ((childPos = pos << 1) <= openListCount) {
			if (childPos <= openListCount - 1 && compare(openList[childPos], openList[childPos + 1]) > 0) {
				childPos++;
			}
			if (compare(openList[pos], openList[childPos]) > 0) {
				swap(pos, childPos);
				pos = childPos;
			} else {
				return;
			}
		}
	}

	private static final int compare(int p1, int p2) {
		int f1 = getF(p1);
		int f2 = getF(p2);

		return f1 > f2 ? 1 : (f1 < f2 ? -1 : 0);
	}

	private static final void swap(int i, int j) {
		int temp = openList[i];
		openList[i] = openList[j];
		openList[j] = temp;
	}

	private static final int calculateG(int rowOffset, int colOffset) {
		return (rowOffset == 0 || colOffset == 0) ? 10 : 14;
	}

	private static final int calculateH(int i, int j) {
		return getManhattanDistance(i, j, desti, destj);
	}

	private static final int getManhattanDistance(int sRow, int sCol, int dRow, int dCol) {
		return (Math.abs(sRow - dRow) + Math.abs(sCol - dCol)) * 10;
	}

	private static final int getI(int index) {
		return ((int) (points[index] >>> 48)) & 0xFFFF;
	}

	private static final int getJ(int index) {
		return ((int) (points[index] >>> 32)) & 0xFFFF;
	}

	private static final int getG(int index) {
		return ((int) (points[index] >>> 16)) & 0xFFFF;
	}

	private static final void setG(int index, int g) {
		int h = getF(index) - getG(index);
		points[index] &= 0xFFFFFFFF0000FFFFL;
		points[index] |= (g << 16);
		setF(index, g + h);
	}

	private static final int getF(int index) {
		return ((int) (points[index])) & 0xFFFF;
	}

	private static final void setF(int index, int f) {
		points[index] &= 0xFFFFFFFFFFFF0000L;
		points[index] |= f;
	}

	/**
	 * 设置临时阻挡层
	 * 
	 * @param i
	 * @param j
	 */
	public static final void setTempImpassableFlag(short[][] tempPhy, int i, int j) {
		Path.phy = tempPhy;
		if (isOutFlag(i, j)) {
			return;
		}
		phy[i][j] |= TEMP_IMPASSABLE_MASK;
		phy[i][j] &= ~TEMP_EDGE_MASK;

		setTempEdgeFlag(i, j - 1);
		setTempEdgeFlag(i, j + 1);
		setTempEdgeFlag(i - 1, j);
		setTempEdgeFlag(i + 1, j);
	}

	private static final void setTempEdgeFlag(int i, int j) {
		if (!hasFlag(i, j, IMPASSABLE_MASK)) {
			setFlag(i, j, TEMP_EDGE_MASK);
		}
	}

	private static final void setFlag(int i, int j, int mask) {
		if (isOutFlag(i, j)) {
			return;
		}
		phy[i][j] |= mask;
	}

	public static final boolean hasFlag(int i, int j, int mask) {
		if (isOutFlag(i, j)) {
			return true;
		}
		return (phy[i][j] & mask) != 0;
	}

	private static final void removeFlag(int i, int j, int mask) {
		if (isOutFlag(i, j)) {
			return;
		}
		phy[i][j] &= ~mask;
	}

	private static final boolean isOutFlag(int i, int j) {
		return i < 0 || i >= phy.length || j < 0 || j >= phy[i].length;
	}
}
