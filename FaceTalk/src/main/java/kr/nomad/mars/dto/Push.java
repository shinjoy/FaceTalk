package kr.nomad.mars.dto;

public class Push {
	int seq = 0;
	String os = "";
	String pushKey = "";
	String msg = "";
	String msgType = "";
	String msgKey = "";
	int pushType = 0;
	String userid = "";
	int badge = 0;
	int status = 0;
	String regDate = "";
	String serviceId = "";
	String sender = "";
	
	public static String MSG_TYPE_SEND_NOTICE = "1";	// 공지사항
	public static String MSG_TYPE_REQUEST_FRIEND = "2";	// 친구요청
	public static String MSG_TYPE_FRIEND_ACCEPT = "3";	// 친구수락
	public static String MSG_TYPE_REQUEST_CHAT = "4";	// 대화요청
	public static String MSG_TYPE_CHAT_ACCEPT = "5";	// 대화수락
	public static String MSG_TYPE_START_CHAT = "6";		// 대화하기
	public static String MSG_TYPE_BBS = "7";			// 댓글등록
	
	public static String MSG_TYPE_GUESTBOOK = "8"; //방명록
	public static String MSG_TYPE_FRIEND_REJECT = "9";//친구거절
	public static String MSG_TYPE_CHAT_REJECT = "10";//채팅거절
	public static String MSG_TYPE_SEND_POINT = "11"; //포인트선물
	public static String MSG_TYPE_ADMIN_COMMENT = "12";			// 관리자 1:1 문의 댓글등록
	public static String MSG_TYPE_DO_FRIEND = "13";	// 친구요청(내가)
	public static String MSG_TYPE_DO_CHAT = "14";	// 대화요청(내가)
	public static String MSG_TYPE_GOOD = "15"; //좋아요등록
	public static String MSG_TYPE_SHARE = "16"; //공유등록
	public static String MSG_TYPE_BBS_COMMENT = "17";			// 댓글의 댓글등록
	public static String MSG_TYPE_BBS_BLINED = "18";			// 신고시
	public static String MSG_TYPE_POINT_ADMIN= "19";  //관리자 포인트변경 
	public static String MSG_TYPE_DELETE= "20";//푸시삭제
	public static String MSG_TYPE_LOGOUT= "21";//관리자에서 로그아웃 처리.
	public static String MSG_TYPE_EVENT= "22";//이벤트 푸시.
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getPushKey() {
		return pushKey;
	}
	public void setPushKey(String pushKey) {
		this.pushKey = pushKey;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgKey() {
		return msgKey;
	}
	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}
	public int getPushType() {
		return pushType;
	}
	public void setPushType(int pushType) {
		this.pushType = pushType;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getBadge() {
		return badge;
	}
	public void setBadge(int badge) {
		this.badge = badge;
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
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	
}
