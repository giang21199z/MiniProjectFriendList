package com.miniproject.model;

import java.net.URI;
import java.util.ArrayList;

import com.miniproject.friendlist.R;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendAdapter extends ArrayAdapter<Friend> {
	Activity context = null;
	ArrayList<Friend> myArray = null;
	int layoutId;

	public FriendAdapter(Activity context, int layoutId, ArrayList<Friend> arr) {
		super(context, layoutId, arr);
		this.context = context;
		this.layoutId = layoutId;
		this.myArray = arr;
	}

	public ArrayList<Friend> getMyArray() {
		return myArray;
	}

	public void setMyArray(ArrayList<Friend> myArray) {
		this.myArray = myArray;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		convertView = inflater.inflate(layoutId, null);
		if (myArray.size() > 0 && position >= 0) {
			final TextView txtName = (TextView) convertView
					.findViewById(R.id.txtName);
			final Friend friend = myArray.get(position);
			txtName.setText(friend.getName());
//			final ImageView avatar = (ImageView) convertView
//					.findViewById(R.id.imgAvatar);
//			if (friend.getAvatar() == null || friend.getAddress() == "") {
//				avatar.setImageResource(R.drawable.abc_ic_voice_search);
//			} else {
//				avatar.setImageURI(new URI(friend.getAvatar()));
//			}
			final TextView txtPhone = (TextView) convertView
					.findViewById(R.id.txtPhone);
			txtPhone.setText(friend.getPhone());
		}

		return convertView;// trả về View này, tức là trả luôn

	}
}