package cs175.hw4.snakes;

import android.util.Log;

public class Walls {
	
	private int[][][] mywallArray;
	
	public Walls(int xmax, int ymax) {
		mywallArray = new int[xmax][ymax][1];
		reset();
	}
	
	public boolean addWall(int x, int y) {
		Log.i("snakemoved " + x + " y is " + y, "snakemoved " + x + " y is " + y);
		mywallArray[x][y][0] = 1;
		return true;
	}
	
	public boolean getWall(int x, int y) {
		if(mywallArray[x][y][0] == 1)
			return true;
		else
			return false;
	}
	
	public void reset() {
		for(int i = 0; i < mywallArray.length; i++) {
			for(int j = 0; j < mywallArray[i].length; j++) {
				Log.i("snakemoved " + i + " j is " + j, "snakemoved " + i + " j is " + j);
				mywallArray[i][j][0] = 0;
			}
		}
	}

}
