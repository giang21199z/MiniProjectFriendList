package com.miniproject.model;

public class Friend {
	int id;
	String name;
	String phone;
	String avatar;
	String address;
	int type;

	public Friend() {
		// TODO Auto-generated constructor stub
	}

	public Friend(String name, String phone, String avatar, String address) {
		super();
		this.name = name;
		this.phone = phone;
		this.avatar = avatar;
		this.address = address;
	}

	public Friend(int id, String name, String phone, String avatar,
			String address) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.avatar = avatar;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
