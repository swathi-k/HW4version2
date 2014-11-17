package cs175.hw4.snakes;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

/**
 * Snake: is a simple game. It has three level.Player need clear each level to
 * move next level. Any player can get highest 4 point.
 */
public class Snake extends Activity {
	private SQLiteDatabase db;
	/**
	 * Constants for desired direction of moving the snake
	 */
	public static int MOVE_LEFT = 0;
	public static int MOVE_UP = 1;
	public static int MOVE_DOWN = 2;
	public static int MOVE_RIGHT = 3;

	private static String ICICLE_KEY = "snake-view";

	private SnakeView mSnakeView;

	/**
	 * Called when Activity is first created. Turns off the title bar, sets up
	 * the content views, and fires up the SnakeView.
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.snake_layout);

		mSnakeView = (SnakeView) findViewById(R.id.snake);
		mSnakeView.setDependentViews((TextView) findViewById(R.id.scores),
				(TextView) findViewById(R.id.lives),
				(TextView) findViewById(R.id.level),
				(TextView) findViewById(R.id.text),
				findViewById(R.id.arrowContainer),
				findViewById(R.id.background),
				(TextView) findViewById(R.id.HigestScore));

		if (savedInstanceState == null) {
			// set up a new game

			mSnakeView.setMode(SnakeView.READY);

		} else {
			// We are being restored
			Bundle map = savedInstanceState.getBundle(ICICLE_KEY);
			if (map != null) {
				mSnakeView.restoreState(map);
			} else {
				mSnakeView.setMode(SnakeView.PAUSE);
			}
		}
		mSnakeView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (mSnakeView.getGameState() == SnakeView.RUNNING) {
					// Normalize x,y between 0 and 1
					// float x = event.getX() / v.getWidth();
					// float y = event.getY() / v.getHeight();

					// Direction will be [0,1,2,3] depending on quadrant
					int direction = 0;
					// direction = (x > y) ? 1 : 0;
					// direction |= (x > 1 - y) ? 2 : 0;

					float x = event.getX();
					float y = event.getY();

					float vy = v.getHeight() / 2;
					float vx = v.getWidth() / 2;
					// if(y < vy)
					// {
					if (x < vx)
						direction = 0;
					else
						direction = 3;
					// }
					// else
					// direction = 1;

					// Direction is same as the quadrant which was clicked
					Log.i("snakemoved1 " + direction, "snakemoved1 "
							+ direction);
					mSnakeView.moveSnake(direction);

					mSnakeView.updateLabels();

				} else {
					// If the game is not running then on touching any part of
					// the screen
					// we start the game by sending MOVE_UP signal to SnakeView
					if (mSnakeView.getGameState() == SnakeView.GAMEOVER) {
						try {
							Thread.sleep(3000);
							Log.i("mscore Sleeping done",
									"mscore Sleeping done");
						} catch (InterruptedException ex) {
							Log.i("mscore Sleeping",
									"YourApplicationName.toString()");
						}

						mSnakeView.setMode(SnakeView.READY);
					} else {
						mSnakeView.moveSnake(MOVE_UP);
					}
				}
				return false;
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		// Pause the game along with the activity
		mSnakeView.setMode(SnakeView.PAUSE);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Store the game state
		outState.putBundle(ICICLE_KEY, mSnakeView.saveState());
	}

	/**
	 * Handles key events in the game. Update the direction our snake is
	 * traveling based on the DPAD.
	 * 
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent msg) {

		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_UP:
			mSnakeView.moveSnake(MOVE_UP);
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			mSnakeView.moveSnake(MOVE_RIGHT);
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			mSnakeView.moveSnake(MOVE_DOWN);
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			mSnakeView.moveSnake(MOVE_LEFT);
			break;
		}

		return super.onKeyDown(keyCode, msg);
	}

	private void loadDBData() {
		// TODO Auto-generated method stub
		int highscore = 0;
		String result;
		TextView displayScore = (TextView) findViewById(R.id.HigestScore);
		MyDb sankeScore = new MyDb(this);
		db = sankeScore.getReadableDatabase();

		/****** Debug line 163 *****/

		Cursor cursor = db.query("hw4", null, null, null, null, null, null);
		if (cursor != null) {
			highscore = cursor.getInt(cursor.getColumnIndex("HIGH_SCORE"));
			result = Integer.toString(highscore);
			displayScore.setText(result);

			Log.i("what is result", "" + result);

		} else {
			result = "0";
			displayScore.setText(result);
			Log.i("what is result", " is it here" + result);
		}

		cursor.close();

	}

}