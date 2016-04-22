package kr.nomad.mars.dto;

import kr.nomad.util.T;

public class BbsFiles {
	
	int fileSeq = 0;
	int bbsSeq = 0;
	String userId = "";
	String fileName = "";
	String filePath = "";
	String regDate = "";
	
	String area = "";
	int gender = 0;
	int birthYear = 0;
	String nickName = "";
	String userName = "";
	
	String userTypeText = "";
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
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getFileSeq() {
		return fileSeq;
	}
	public void setFileSeq(int fileSeq) {
		this.fileSeq = fileSeq;
	}
	public int getBbsSeq() {
		return bbsSeq;
	}
	public void setBbsSeq(int bbsSeq) {
		this.bbsSeq = bbsSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	
	
	

}
