package kr.nomad.mars.dto;

public class PointMoney {
	
	int pmSeq = 0;
	String userId = "";
	String type = "";
	int inPoint = 0;
	int outPoint = 0;
	int inMoney = 0;
	int outMoney = 0;
	int remainPoint = 0;
	int remainMoney = 0;
	String regDate = "";
	String comment ="";
	String userName ="";
	int Point = 0;
	int Income=0;
	String typeTxt ="";
	int seqId =0;
	//뷰
	int priceSum =0;
	int totalFee =0;
	int pay_point=0;
	int tType = 0;
	int kind =0;
	String site="";
	String siteName="";
	public static String JOIN_JOIN = "JOIN";
	public static String JOIN_CHAT = "CHAT"; //대화하기
	public static String JOIN_APCHAT = "APCHAT"; //채팅 수락
	public static String JOIN_TALK = "TALK"; //토크쓰기
	public static String JOIN_COMMENT = "COMMENT"; //댓글쓰기
	public static String JOIN_LOGIN = "LOGIN"; //출석
	public static String JOIN_7LOGIN = "7LOGIN"; //7일출석
	public static String JOIN_CHANGE ="CHANGE"; //환전
	public static String JOIN_NOCHANGE ="NOCHANGE";
	public static String JOIN_REGIFT ="REGIFT";//선물받기
	public static String JOIN_SEGIFT ="SEGIFT";//선물보내기
	public static String JOIN_ADMIN ="ADMIN";
	public static String JOIN_BAD ="BAD";//신고하기
	public static String JOIN_LEVELUP="LEVELUP";
	public static String JOIN_FREE="FREE";//무료충전
	public static String JOIN_PHOTO="PHOTO";//프사등록
	public static String JOIN_ITEM="ITEM";//아이템구매
	public static String JOIN_CHANGE_NAME="NAME";
	public static String JOIN_ITEM_USE="USEITEM";//아이템사용
	public static String JOIN_ONE_CHAT="ONECHAT";//1:1채팅방 생성
	public static String JOIN_NONAME_CHAT="NONAMECHAT"; //익명 채팅
//	public static String JOIN_ONLYSEX_CHAT="ONLYSEXCHAT"; //이성 채팅
	public static String JOIN_ANYONE_CHAT="ANYONECHAT"; //1:N 채팅
	public static String JOIN_LEVELITEM="LEVELITEM";//무료지급
	public static String JOIN_TALK_DELETE="TALKDELETE";//토크삭제
	public static String JOIN_COMMENT_DELETE="COMMENTDELETE";//댓글삭제
	
	
	
	public String getSiteName() {
		return siteName;
	}


	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}


	public String getSite() {
		return site;
	}


	public void setSite(String site) {
		this.site = site;
	}


	public int getKind() {
		return kind;
	}


	public void setKind(int kind) {
		this.kind = kind;
	}


	public int getSeqId() {
		return seqId;
	}
	

	public int getPay_point() {
		return pay_point;
	}


	public void setPay_point(int pay_point) {
		this.pay_point = pay_point;
	}


	public void setSeqId(int seqId) {
		this.seqId = seqId;
	}
	

	public String getTypeTxt() {
		return typeTxt;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPoint() {
		return Point;
	}
	public void setPoint(int point) {
		Point = point;
	}
	public int getIncome() {
		return Income;
	}
	public void setIncome(int income) {
		Income = income;
	}
	public int getPmSeq() {
		return pmSeq;
	}
	public void setPmSeq(int pmSeq) {
		this.pmSeq = pmSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getInPoint() {
		return inPoint;
	}
	public void setInPoint(int inPoint) {
		this.inPoint = inPoint;
	}
	public int getOutPoint() {
		return outPoint;
	}
	public void setOutPoint(int outPoint) {
		this.outPoint = outPoint;
	}
	public int getInMoney() {
		return inMoney;
	}
	public void setInMoney(int inMoney) {
		this.inMoney = inMoney;
	}
	public int getOutMoney() {
		return outMoney;
	}
	public void setOutMoney(int outMoney) {
		this.outMoney = outMoney;
	}
	public int getRemainPoint() {
		return remainPoint;
	}
	public void setRemainPoint(int remainPoint) {
		this.remainPoint = remainPoint;
	}
	public int getRemainMoney() {
		return remainMoney;
	}
	public void setRemainMoney(int remainMoney) {
		this.remainMoney = remainMoney;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getPriceSum() {
		return priceSum;
	}
	public void setPriceSum(int priceSum) {
		this.priceSum = priceSum;
	}
	public int getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}
	public int gettType() {
		return tType;
	}
	public void settType(int tType) {
		this.tType = tType;
	}
	public void setTypeTxt(String typeTxt) {
		this.typeTxt = typeTxt;
	}
	
	
	

}
