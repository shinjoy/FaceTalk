package kr.nomad.mars.dto;

import kr.nomad.util.T;

public class Message {
	int messageSeq = 0;
	String userId = "";
	String messageType = "";
	String keySeq = "";
	String contents = "";
	String regDate = "";
	int viewCheck = 0;
	String writeId ="";
	String friendName = "";
	String friendId = "";
	String friendArea = "";
	int friendGender = 1;
	String friendPhoto = "";
	int friendLevel = 0;
	int friendBirthyear = 0;
	String message = "";
	String agentId="";
	int friendAge = 0;
	String friendGendertxt="";
	
	
	
	
	
	
	public String getFriendGendertxt() {
		
		
		if (this.friendGender == 1) {
			return "남자";
		} else if (this.friendGender == 2) {
			return "여자";
		} else {
			return "";
		}
		
	}
	
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getWriteId() {
		return writeId;
	}
	public void setWriteId(String writeId) {
		this.writeId = writeId;
	}
	public int getFriendAge() {
		int thisYear = Integer.parseInt(T.getTodayYear());
		if (friendBirthyear == 0) {
			return 0;
		} else {
			return thisYear - this.friendBirthyear+1;
		}
	}
	public int getMessageSeq() {
		return messageSeq;
	}
	public void setMessageSeq(int messageSeq) {
		this.messageSeq = messageSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getKeySeq() {
		return keySeq;
	}
	public void setKeySeq(String keySeq) {
		this.keySeq = keySeq;
	}
	public int getViewCheck() {
		return viewCheck;
	}
	public void setViewCheck(int viewCheck) {
		this.viewCheck = viewCheck;
	}
	public String getFriendName() {
		return friendName;
	}
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public String getFriendArea() {
		return friendArea;
	}
	public void setFriendArea(String friendArea) {
		this.friendArea = friendArea;
	}
	public int getFriendGender() {
		return friendGender;
	}
	public void setFriendGender(int friendGender) {
		this.friendGender = friendGender;
	}
	public String getFriendPhoto() {
		return friendPhoto;
	}
	public void setFriendPhoto(String friendPhoto) {
		this.friendPhoto = friendPhoto;
	}
	public int getFriendLevel() {
		return friendLevel;
	}
	public void setFriendLevel(int friendLevel) {
		this.friendLevel = friendLevel;
	}
	public int getFriendBirthyear() {
		return friendBirthyear;
	}
	public void setFriendBirthyear(int friendBirthyear) {
		this.friendBirthyear = friendBirthyear;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
