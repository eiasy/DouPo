package mmo.tools.path;

/**
 * 
 * @author 段链
 * 
 */
public class Line {
	public int startX;
	public int startY;
	public int endX;
	public int endY;

	public int x;
	public int y;

	public int xOffset;
	public int yOffset;

	public int fillx;
	public int filly;

	public int e;
	public int eAdd;
	public int eSubtract;

	public int notIteratedCount;

	public boolean horizontal;
	public boolean updateX;
	public boolean updateY;
	public boolean isBack;

	public final void setLine(int startX, int startY, int endX, int endY) {
		this.startX = startX;
		this.startY = startY;
		x = startX;
		y = startY;
		this.endX = endX;
		this.endY = endY;

		int distX = endX - startX;
		int distY = endY - startY;

		xOffset = distX > 0 ? 1 : -1;
		yOffset = distY > 0 ? 1 : -1;

		distX = Math.abs(distX) << 1;
		distY = Math.abs(distY) << 1;

		horizontal = distX > distY;

		if (horizontal) {
			notIteratedCount = distX >> 1;
			eAdd = distY;
			eSubtract = distX;
			updateX = true;
			updateY = false;
		} else {
			notIteratedCount = distY >> 1;
			eAdd = distX;
			eSubtract = distY;
			updateX = false;
			updateY = true;
		}
		e = -notIteratedCount;
		isBack = false;
	}

	public final boolean hasNext() {
		return notIteratedCount > 0;
	}

	public final void next() {
		if (horizontal) {
			x += xOffset;
			e += eAdd;
			updateY = e > 0;
			if (updateY) {
				y += yOffset;
				e -= eSubtract;
			}
		} else {
			y += yOffset;
			e += eAdd;
			updateX = e > 0;
			if (updateX) {
				x += xOffset;
				e -= eSubtract;
			}
		}
		notIteratedCount--;
		isBack = true;
	}

	public final void back() {
		if (isBack) {
			if (horizontal) {
				x -= xOffset;
				e -= eAdd;
				if (updateY) {
					y -= yOffset;
					e += eSubtract;
				}
			} else {
				y -= yOffset;
				e -= eAdd;
				if (updateX) {
					x -= xOffset;
					e += eSubtract;
				}
			}
			notIteratedCount++;
			isBack = false;
		}
	}

	public final boolean getFillPoint(boolean from, int endY) {
		if (notIteratedCount <= 0 || y == endY) {
			return false;
		}
		fillx = x;
		filly = y;
		if (horizontal) {
			while (notIteratedCount > 0 && e + eAdd <= 0) {
				x += xOffset;
				e += eAdd;
				updateY = e > 0;
				notIteratedCount--;
				isBack = true;
			}
			if (from ^ (xOffset > 0)) {
				fillx = x;
				filly = y;
			}
		}
		if (notIteratedCount > 0) {
			next();
		}

		return true;
	}

}
