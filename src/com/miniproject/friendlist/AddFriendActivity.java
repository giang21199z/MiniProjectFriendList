package com.miniproject.friendlist;

import com.miniproject.friendlist.DAO.FriendSQLHelper;
import com.miniproject.model.Friend;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddFriendActivity extends Activity implements OnClickListener {
	Button btnAddFriend;
	Button btnChoose;
	Button btnBack;
	EditText editName;
	EditText editPhone;
	EditText editAddress;
	ImageView add_image;
	public static final int RESULT_GALLERY = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_friend);

		btnAddFriend = (Button) findViewById(R.id.btnSave);
		btnChoose = (Button) findViewById(R.id.btnBrowserAvatar);
		btnBack = (Button) findViewById(R.id.btnBack);

		editName = (EditText) findViewById(R.id.editName);
		editPhone = (EditText) findViewById(R.id.editPhone);
		editAddress = (EditText) findViewById(R.id.editAddress);

		add_image = (ImageView) findViewById(R.id.add_avatar);

		btnBack.setOnClickListener(this);
		btnAddFriend.setOnClickListener(this);
		btnChoose.setOnClickListener(this);
	}

	public void addNewFriend() {
		String name = editName.getText().toString();
		String phone = editPhone.getText().toString();
		String address = editAddress.getText().toString();
		Friend friend = new Friend(name, phone, "avatar.jpg", address);
		FriendSQLHelper friendDAO = new FriendSQLHelper(this);
		friendDAO.addFriend(friend);
		Toast.makeText(this, "Add new Friend Success!", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void onClick(View v) {
		if (v == btnAddFriend) {
			addNewFriend();
		} else if (v == btnBack) {
			this.finish();
		} else if (v == btnChoose) {
			Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
			Intent galleryIntent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(galleryIntent, RESULT_GALLERY);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_GALLERY:
			if (null != data) {
				Uri uri = data.getData();
				add_image.setImageURI(uri);
			}
			break;
		default:
			break;
		}
	}
}