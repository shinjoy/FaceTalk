package kr.nomad.mars.dto;

import java.util.ArrayList;
import java.util.List;

import kr.nomad.util.T;

public class ChatRoom {
	int chatRoomSeq = 0;
	int chatRoomType = 0;
	String ownerId = "";
	String regDate = "";
	String lastMsgSeq = "";
	String title = "";
	String replyId = "";
	int fMatchingSeq = 0;
	int itemCount = 0;
	String age = "";
	String area = "";
	int memberLimit = 0;
	int memberCount = 0;
	int isOneone = 0;
	int isAnonym = 0;
	int onlyOppositeSex = 0;
	String useMegaphoneDate = "";
	int useSpeaker = 0;
	int useFreeTicket = 0;
	int gender = 1;
	int distance = 0;
	public static int MAN = 1;
	public static int WOMAN = 2;
	String genderText = "";
	
	String userName = "";
	String nickName = "";
	String photo = "";
	Double latitude = 0.0;
	Double longitude = 0.0;
	
	///추가
	String reqKey = "";
	String roomIdx = "";
	String roomType = "";
	String ownerID = "";
	String memberInfo ="";
	String result = "false";
	String phoneNumber = "";
	int useMega = 0;
	
	String agentId = "";
	String agentName = "";
	double distancetxt=0.0;
	int photoStatus = 0;
	String memphoto= "";
	int memgender =0;
	
	String otherAgentUser = "";
	String site="";
	String siteName="";
	public static String[] AGE_LIST = {"10대","20대","30대","40대","50대","60대","70대 이상"};	// 연령
	public static int[] MEMBER_LIMIT_LIST = {2,4,10,20,50};	// 인원 제한
	public static int IS_ONEONE_TRUE = 1;			// 1:1채팅방
	public static int IS_ONEONE_FALSE = 0;			// 1:1채팅방 아님
	public static int IS_ANONYM_TRUE = 1;			// 익명채팅방
	public static int IS_ANONYM_FALSE = 0;			// 익명방 아님
	public static int ONLY_OPPOSIT_SEX_FALSE = 0;	// 이성방 아님
	public static int ONLY_OPPOSIT_SEX_MALE = 1;	// 이성방 : 남성 이성
	public static int ONLY_OPPOSIT_SEX_FEMALE = 2;	// 이성방 : 여성 이성
	public static int USE_SPEAKER_LIMIT = 3;		// 확성기 사용 일수
	public static int USE_SPEAKER_TRUE = 1;			// 확성기 사용
	public static int USE_SPEAKER_FALSE = 0;		// 확성기 사용 안함
	public static int USE_FREE_TICKET_TRUE = 1;		// 자유이용권 사용
	public static int USE_FREE_TICKET_FALSE = 2;	// 자유이용권 사용 아님
	public static int GENDER_MALE = 1;				// 남성
	public static int GENDER_FEMALE = 2;				// 여성
	
	
	
	List memberList = new ArrayList();
	
	
	

	


	public String getSite() {
		return site;
	}


	public void setSite(String site) {
		this.site = site;
	}


	public String getSiteName() {
		return siteName;
	}


	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}


	public List getMemberList() {
		return memberList;
	}


	public void setMemberList(List memberList) {
		this.memberList = memberList;
	}


	public String getMemphoto() {
		return memphoto;
	}


	public void setMemphoto(String memphoto) {
		this.memphoto = memphoto;
	}


	public int getMemgender() {
		return memgender;
	}


	public void setMemgender(int memgender) {
		this.memgender = memgender;
	}


	public int getPhotoStatus() {
		return photoStatus;
	}


	public void setPhotoStatus(int photoStatus) {
		this.photoStatus = photoStatus;
	}


	public int getUseMega() {
		return useMega;
	}


	public void setUseMega(int useMega) {
		this.useMega = useMega;
	}


	
	public Double getDistancetxt() {
		Double num =this.distancetxt/1000;
		String str = String.format("%.1f" , num);
		
		return Double.parseDouble(str);
		//return (double)(this.distance/1000);
	}


	

	public void setDistancetxt(double distancetxt) {
		this.distancetxt = distancetxt;
	}


	public String getGenderText() {

		if (this.gender == MAN) {
			return "남자";
		} else if (this.gender == WOMAN) {
			return "여자";
		} else {
			return "";
		}
	}
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getReqKey() {
		return reqKey;
	}
	public void setReqKey(String reqKey) {
		this.reqKey = reqKey;
	}
	public String getRoomIdx() {
		return Integer.toString(this.chatRoomSeq);
	}
	public void setRoomIdx(String roomIdx) {
		this.roomIdx = roomIdx;
	}
	public String getRoomType() {
		return Integer.toString(this.chatRoomType);
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getOwnerID() {
		return this.ownerId;
	}
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}
	public String getMemberInfo() {
		return memberInfo;
	}
	public void setMemberInfo(String memberInfo) {
		this.memberInfo = memberInfo;
	}
	public int getChatRoomSeq() {
		return chatRoomSeq;
	}
	public void setChatRoomSeq(int chatRoomSeq) {
		this.chatRoomSeq = chatRoomSeq;
	}
	public int getChatRoomType() {
		return chatRoomType;
	}
	public void setChatRoomType(int chatRoomType) {
		this.chatRoomType = chatRoomType;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getLastMsgSeq() {
		return lastMsgSeq;
	}
	public void setLastMsgSeq(String lastMsgSeq) {
		this.lastMsgSeq = lastMsgSeq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReplyId() {
		return replyId;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}
	public int getfMatchingSeq() {
		return fMatchingSeq;
	}
	public void setfMatchingSeq(int fMatchingSeq) {
		this.fMatchingSeq = fMatchingSeq;
	}
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getMemberLimit() {
		return memberLimit;
	}
	public void setMemberLimit(int memberLimit) {
		this.memberLimit = memberLimit;
	}
	public int getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	public int getIsOneone() {
		return isOneone;
	}
	public void setIsOneone(int isOneone) {
		this.isOneone = isOneone;
	}
	public int getIsAnonym() {
		return isAnonym;
	}
	public void setIsAnonym(int isAnonym) {
		this.isAnonym = isAnonym;
	}
	public int getOnlyOppositeSex() {
		return onlyOppositeSex;
	}
	public void setOnlyOppositeSex(int onlyOppositeSex) {
		this.onlyOppositeSex = onlyOppositeSex;
	}
	public String getUseMegaphoneDate() {
		return useMegaphoneDate;
	}
	public void setUseMegaphoneDate(String useMegaphoneDate) {
		this.useMegaphoneDate = useMegaphoneDate;
	}
	public int getUseSpeaker() {
		if (useMegaphoneDate == null || useMegaphoneDate.equals("")) {
			return USE_SPEAKER_FALSE;
		} else {
			int dateDiff = T.getDateDiffCnt(this.useMegaphoneDate, T.getToday());
			if (dateDiff <=3 ) {
				return USE_SPEAKER_TRUE;
			} else {
				return USE_SPEAKER_FALSE;
			}
		}
	}
	public void setUseSpeaker(int useSpeaker) {
		this.useSpeaker = useSpeaker;
	}
	public int getUseFreeTicket() {
		return useFreeTicket;
	}
	public void setUseFreeTicket(int useFreeTicket) {
		this.useFreeTicket = useFreeTicket;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	public String getAgentId() {
		return agentId;
	}


	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}


	public String getAgentName() {
		return agentName;
	}


	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}


	public String getOtherAgentUser() {
		return otherAgentUser;
	}


	public void setOtherAgentUser(String otherAgentUser) {
		this.otherAgentUser = otherAgentUser;
	}
	
	

}
