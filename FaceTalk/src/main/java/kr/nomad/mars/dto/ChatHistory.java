package kr.nomad.mars.dto;

public class ChatHistory {
	String chatMsgKey = "";
	int chatRoomSeq = 0;
	String sndId = "";
	int mType = 0;
	int cType = 0;
	String contents = "";
	String fileName = "";
	String regDate = "";
	int status = 0;
	String option1 = "";
	String option2 = "";
	String option3 = "";
	public String getChatMsgKey() {
		return chatMsgKey;
	}
	public void setChatMsgKey(String chatMsgKey) {
		this.chatMsgKey = chatMsgKey;
	}
	public int getChatRoomSeq() {
		return chatRoomSeq;
	}
	public void setChatRoomSeq(int chatRoomSeq) {
		this.chatRoomSeq = chatRoomSeq;
	}
	public String getSndId() {
		return sndId;
	}
	public void setSndId(String sndId) {
		this.sndId = sndId;
	}
	public int getmType() {
		return mType;
	}
	public void setmType(int mType) {
		this.mType = mType;
	}
	public int getcType() {
		return cType;
	}
	public void setcType(int cType) {
		this.cType = cType;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}

}
