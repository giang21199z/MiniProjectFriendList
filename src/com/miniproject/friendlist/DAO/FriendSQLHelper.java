package com.miniproject.friendlist.DAO;

import java.util.ArrayList;

import com.miniproject.model.Friend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class FriendSQLHelper extends SQLiteOpenHelper {

	public final static String DB_NAME = "friendlist";
	public final static int DB_VERSION = 1;

	public final static String DB_TABLE = "friend";
	public final static String ID = "id";
	public final static String NAME = "name";
	public final static String PHONE = "phone";
	public final static String ADDRESS = "address";
	public final static String AVATAR = "avatar";

	SQLiteDatabase mSQLiteDB;

	public FriendSQLHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		mSQLiteDB = this.getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE IF NOT EXISTS " + DB_TABLE + " (" + ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, "
				+ ADDRESS + " TEXT, " + PHONE + " TEXT, " + AVATAR + " TEXT);";
		Log.d("SQL = ", sql);

		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		String sql = "DROP TABLE IF EXISTS " + DB_TABLE;
		db.execSQL(sql);
		onCreate(db);
	}

	public void addFriend(Friend friend) {
		ContentValues contenValues = new ContentValues();
		contenValues.put(FriendSQLHelper.NAME, friend.getName());
		contenValues.put(FriendSQLHelper.PHONE, friend.getPhone());
		contenValues.put(FriendSQLHelper.AVATAR, friend.getAvatar());
		contenValues.put(FriendSQLHelper.ADDRESS, friend.getAddress());
		mSQLiteDB = this.getWritableDatabase();
		mSQLiteDB.insert(FriendSQLHelper.DB_TABLE, FriendSQLHelper.NAME,
				contenValues);
	}

	public ArrayList<Friend> getFriends() {
		ArrayList<Friend> arrFriend = new ArrayList<Friend>();
		Cursor cursor = null;
		mSQLiteDB = this.getWritableDatabase();
		cursor = mSQLiteDB.query(true, this.DB_TABLE, null, null, null, null,
				null, null, null, null);

		while (cursor.moveToNext()) {
			int id = cursor.getInt(0);
			String name = cursor.getString(1);
			String address = cursor.getString(2);
			String phone = cursor.getString(3);
			String avatar = cursor.getString(4);

			Log.d("DEBUG = ID=", id + "____NAME=" + name + "___PHONE=" + phone
					+ "___AVATAR=" + avatar + "___ADDRESS" + address);
			arrFriend.add(new Friend(id, name, phone, avatar, address));
		}
		return arrFriend;
	}

	public void removeFriend(int id) {
		String sql = "id = ?";
		String[] args = new String[] { id + "" };
		mSQLiteDB = this.getWritableDatabase();
		mSQLiteDB.delete(this.DB_TABLE, sql, args);
	}

}
