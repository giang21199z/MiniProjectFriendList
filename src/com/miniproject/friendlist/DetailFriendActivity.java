package com.miniproject.friendlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class DetailFriendActivity extends Activity implements OnClickListener {
	ImageView avatar;
	Button back;
	EditText edit_name;
	EditText edit_phone;
	EditText edit_address;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_friend);
		avatar = (ImageView) findViewById(R.id.avatar_detail);
		back = (Button) findViewById(R.id.btnReturn);
		edit_name = (EditText) findViewById(R.id.editName_detail);
		edit_phone = (EditText) findViewById(R.id.editPhone_detail);
		edit_address = (EditText) findViewById(R.id.editAddress_detail);

		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		String phone = intent.getStringExtra("phone");
		String address = intent.getStringExtra("address");
		String avatar = intent.getStringExtra("avatar");

		edit_name.setText(name);
		edit_phone.setText(phone);
		edit_address.setText(address);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == back) {
			this.finish();
		}
	}
}
