package cs175.hw4.snakes;

/**
 * Walls class keep track of walls when snake is moving.
 * 
 * **/
public class Walls {

	private int[][][] mywallArray;

	public Walls(int xmax, int ymax) {
		mywallArray = new int[xmax][ymax][1];
		reset();
	}

	public boolean addWall(int x, int y) {
		mywallArray[x][y][0] = 1;
		return true;
	}

	public boolean getWall(int x, int y) {
		if (x < 0 || y < 0)
			return true;

		if (x >= mywallArray.length || y >= mywallArray[0].length)
			return true;

		if (mywallArray[x][y][0] == 1)
			return true;
		else
			return false;
	}

	public void reset() {
		for (int i = 0; i < mywallArray.length; i++) {
			for (int j = 0; j < mywallArray[i].length; j++) {
				mywallArray[i][j][0] = 0;
			}
		}
	}

}
