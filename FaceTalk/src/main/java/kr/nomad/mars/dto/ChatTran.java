package kr.nomad.mars.dto;

public class ChatTran {
	int chatTranSeq = 0;
	int chatRoomSeq = 0;
	String chatMsgKey = "";
	String sndId = "";
	String rcvId = "";
	int status = 0;
	String regDate = "";
	public int getChatTranSeq() {
		return chatTranSeq;
	}
	public void setChatTranSeq(int chatTranSeq) {
		this.chatTranSeq = chatTranSeq;
	}
	public int getChatRoomSeq() {
		return chatRoomSeq;
	}
	public void setChatRoomSeq(int chatRoomSeq) {
		this.chatRoomSeq = chatRoomSeq;
	}
	public String getChatMsgKey() {
		return chatMsgKey;
	}
	public void setChatMsgKey(String chatMsgKey) {
		this.chatMsgKey = chatMsgKey;
	}
	public String getSndId() {
		return sndId;
	}
	public void setSndId(String sndId) {
		this.sndId = sndId;
	}
	public String getRcvId() {
		return rcvId;
	}
	public void setRcvId(String rcvId) {
		this.rcvId = rcvId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
