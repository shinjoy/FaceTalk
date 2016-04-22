package kr.nomad.mars.dto;

public class ChatBlock {
	int cbSeq = 0;
	String cbUserId = "";
	String blUserId = "";
	String regDate = "";
	String userName ="";
	String photo = "";
	int gender = 0;
	
	
	
	
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getCbSeq() {
		return cbSeq;
	}
	public void setCbSeq(int cbSeq) {
		this.cbSeq = cbSeq;
	}
	public String getCbUserId() {
		return cbUserId;
	}
	public void setCbUserId(String cbUserId) {
		this.cbUserId = cbUserId;
	}
	public String getBlUserId() {
		return blUserId;
	}
	public void setBlUserId(String blUserId) {
		this.blUserId = blUserId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
