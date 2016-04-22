package kr.nomad.mars.dto;

import kr.nomad.util.T;

public class Friend {
	int friendSeq = 0;
	String userId = "";
	String friendId = "";
	String regDate = "";

	int gender = 0;
	int birthYear = 0;
	String area = "";
	String userName = "";
	
	String genderText = "";
	int userAge = 0;
	
	public static int MAN = 1;
	public static int WOMAN = 2;

	public String getGenderText() {
		if (this.gender == MAN) {
			return "남자";
		} else if (this.gender == WOMAN) {
			return "여자";
		} else {
			return "";
		}
	}
	public int getUserAge() {
		int thisYear = Integer.parseInt(T.getTodayYear());
		if (birthYear == 0) {
			return 0;
		} else {
			return thisYear - this.birthYear +1;
		}
	}

	public int getFriendSeq() {
		return friendSeq;
	}
	public void setFriendSeq(int friendSeq) {
		this.friendSeq = friendSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
