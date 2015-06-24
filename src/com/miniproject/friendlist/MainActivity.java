package com.miniproject.friendlist;

import java.util.ArrayList;

import com.miniproject.friendlist.DAO.FriendSQLHelper;
import com.miniproject.model.Friend;
import com.miniproject.model.FriendAdapter;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements
		OnItemLongClickListener, OnItemClickListener {

	Button btnAdd;
	ListView listFriend;
	FriendSQLHelper friendDAO;
	FriendAdapter friendAdapter;
	ArrayList<Friend> arrFriend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnAdd = (Button) findViewById(R.id.btnAdd);
		listFriend = (ListView) findViewById(R.id.lvFriendList);
		friendDAO = new FriendSQLHelper(this);
		arrFriend = friendDAO.getFriends();

		Log.d("Nhào zô = ", arrFriend.size() + "");
		friendAdapter = new FriendAdapter(this, R.layout.custom_listview,
				arrFriend);
		listFriend.setAdapter(friendAdapter);
		listFriend.setOnItemLongClickListener(this);
		listFriend.setOnItemClickListener(this);

		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(),
						AddFriendActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		friendDAO = new FriendSQLHelper(this);
		arrFriend = friendDAO.getFriends();

		Log.d("Nhào zô = ", arrFriend.size() + "");
		friendAdapter = new FriendAdapter(this, R.layout.custom_listview,
				arrFriend);
		listFriend.setAdapter(friendAdapter);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		final int pos = position;
		new AlertDialog.Builder(this)
				.setTitle("Delete a friend")
				.setMessage("Do you really want to delete this friend?")
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int whichButton) {
								Log.d("SIZE = ", arrFriend.size() + "");
								friendDAO.removeFriend(arrFriend.get(pos)
										.getId());
								arrFriend.remove(pos);
								friendAdapter.setMyArray(arrFriend);
								listFriend.setAdapter(friendAdapter);
							}
						}).setNegativeButton(android.R.string.no, null).show();
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		Friend friend = arrFriend.get(position);
		Intent intent = new Intent(this, DetailFriendActivity.class);
		intent.putExtra("name", friend.getName());
		intent.putExtra("avatar", friend.getAvatar());
		intent.putExtra("address", friend.getAddress());
		intent.putExtra("phone", friend.getPhone());
		startActivity(intent);
	}

}
