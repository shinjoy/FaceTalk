package kr.nomad.mars.dto;

public class ChatBuffer {
	int chatBufferSeq = 0;
	String serverId = "";
	String destId = "";
	String fromServerId = "";
	int bufferKey = 0;
	String contents = "";
	int pt = 0;
	int st = 0;
	public int getChatBufferSeq() {
		return chatBufferSeq;
	}
	public void setChatBufferSeq(int chatBufferSeq) {
		this.chatBufferSeq = chatBufferSeq;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public String getDestId() {
		return destId;
	}
	public void setDestId(String destId) {
		this.destId = destId;
	}
	public String getFromServerId() {
		return fromServerId;
	}
	public void setFromServerId(String fromServerId) {
		this.fromServerId = fromServerId;
	}
	public int getBufferKey() {
		return bufferKey;
	}
	public void setBufferKey(int bufferKey) {
		this.bufferKey = bufferKey;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getPt() {
		return pt;
	}
	public void setPt(int pt) {
		this.pt = pt;
	}
	public int getSt() {
		return st;
	}
	public void setSt(int st) {
		this.st = st;
	}

}
