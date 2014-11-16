package cs175.hw4.snakes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * A SQLiteOpenhelper to create "snake.db" and table hw4
 * 
 */
public class MyDb extends SQLiteOpenHelper {

	/**
	 * constructor
	 * 
	 * @param context
	 */
	public MyDb(Context context) {
		super(context, "snake.db", null, 3);
		// activity, database name, , version
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// create table at first time
		String sql = "create table if not exists hw4 (name varchar(50), speed integer, HIGH_SCORE integer, score intger)";
		db.execSQL(sql);

		Cursor rawQuery = db.rawQuery("select * from hw4", null);
		if (rawQuery.getCount() == 0) {
			db.execSQL("insert into hw4 (name, HIGH_SCORE) values('player 1', 0)");
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// drop and create table
		db.execSQL("drop table if exists hw4");
		onCreate(db);
	}

}
