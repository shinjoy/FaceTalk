package kr.nomad.mars.dto;

import kr.nomad.util.T;

public class User {
	int userSeq = 0;
	int rowSeq = 0;
	String agentId = "";
	String userId = "";
	String password = "";
	int userType = 0;
	String userName = "";
	String email = "";
	String comment = "";
	String phoneNumber = "";
	String intro = "";
	String address = "";
	double latitude = 0;
	double longitude = 0;
	int birthYear = 0;
	int gender = 0;
	String area = "";
	String regDate = "";
	String lastLogindate = "";
	String nickName = "";
	int loginFacebook = 0;
	int loginKakao = 0;
	String osType = "";
	String osVersion = "";
	String appVersion = "";
	String deviceName = "";
	String deviceId = "";
	String pushkey = "";
	int usePushservice = 1;
	int status = 0;
	int point = 0;
	int income = 0;
	String photo = "";
	int userLevel = 0;
	int levelPoint = 0;
	String photoRegDate = "";
	int photoStatus =0;
	String site = "";
	String siteName = "";
	int loginStatus = 0;
	String userTypeText = "";
	String genderText = "";
	int userAge = 0;
	int count = 0;
	String thumphoto = "";
	String dropReason = "";
	public static int MAN = 1;
	public static int WOMAN = 2;

	public static int USERTYPE_ADMIN = 1; // 최고관리자
	public static int USERTYPE_NORMAL = 2; // 일반관리자
	public static int USERTYPE_INQUIRY = 3; // 채팅관리자
	public static int USERTYPE_USER = 4; // 일반
	public static int USERTYPE_IMG_USER = 5; // 가상회원
	//채팅멤버 조회
	int chatRoomSeq =0;
	int owner =0;
	int userFncSeq =0;
	String contents ="";
	String blockId="";
	String fncReason="";
	String fncFiles ="";
	String blockName="";
	
	String fncResontxt="";
	int userStatus=0;
	String statustxt="";
	int firstBbs =0;
	
	
	
	
	public int getFirstBbs() {
		return firstBbs;
	}

	public void setFirstBbs(int firstBbs) {
		this.firstBbs = firstBbs;
	}

	public String getStatustxt() {
		String str="";
		if(this.userStatus==1||this.status==1){
			str="사용중";
		}else if(this.userStatus==2||this.status==2){
			str="탈퇴";
		}else if(this.userStatus==3||this.status==3){
			str="사용중지";
		}else if(this.userStatus==4||this.status==4){
			str="강제탈퇴";
		}
		return str;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public String getFncReasontxt() {
		String str="";
		if(this.fncReason.equals("1")){
			str="음란성 게시물";
			
		}else if(this.fncReason.equals("2")){
			str="욕설";
			
		}else if(this.fncReason.equals("3")){
			str="타인도용/사기";
			
		}else if(this.fncReason.equals("4")){
			str="기타";
			
		}
		
		return str;
	}
	
	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public int getUserFncSeq() {
		return userFncSeq;
	}

	public void setUserFncSeq(int userFncSeq) {
		this.userFncSeq = userFncSeq;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public String getFncReason() {
		return fncReason;
	}

	public void setFncReason(String fncReason) {
		this.fncReason = fncReason;
	}

	public String getFncFiles() {
		return fncFiles;
	}

	public void setFncFiles(String fncFiles) {
		this.fncFiles = fncFiles;
	}

	public int getChatRoomSeq() {
		return chatRoomSeq;
	}

	public void setChatRoomSeq(int chatRoomSeq) {
		this.chatRoomSeq = chatRoomSeq;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public int getPhotoStatus() {
		return photoStatus;
	}

	public void setPhotoStatus(int photoStatus) {
		this.photoStatus = photoStatus;
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

	public String getUserTypeText() {
		if (this.userType == USERTYPE_ADMIN) {
			return "최고관리자";
		} else if (this.userType == USERTYPE_NORMAL) {
			return "일반관리자";
		} else if (this.userType == USERTYPE_INQUIRY) {
			return "채팅관리자";
		} else if (this.userType == USERTYPE_USER) {
			return "일반회원";
		} else if (this.userType == USERTYPE_IMG_USER) {
			return "가상회원";
		} else {
			return "";
		}
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public int getRowSeq() {
		return rowSeq;
	}

	public void setRowSeq(int rowSeq) {
		this.rowSeq = rowSeq;
	}

	public String getDropReason() {
		return dropReason;
	}

	public void setDropReason(String dropReason) {
		this.dropReason = dropReason;
	}

	public int getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getUserAge() {
		int thisYear = Integer.parseInt(T.getTodayYear());
		if (this.birthYear == 0) {
			return 0;
		} else {
			return thisYear - this.birthYear +1 ;
		}
	}

	public int getUserYear(int age) {
		int thisYear = Integer.parseInt(T.getTodayYear());
		if (age == 0) {
			return 0;
		} else {
			return thisYear - age +1;
		}
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude =  longitude;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhotoRegDate() {
		return photoRegDate;
	}

	public void setPhotoRegDate(String photoRegDate) {
		this.photoRegDate = photoRegDate;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getRegDate() {

		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getLastLogindate() {

		return lastLogindate;
	}

	public void setLastLogindate(String lastLogindate) {
		this.lastLogindate = lastLogindate;
	}

	public int getLoginFacebook() {
		return loginFacebook;
	}

	public void setLoginFacebook(int loginFacebook) {
		this.loginFacebook = loginFacebook;
	}

	public int getLoginKakao() {
		return loginKakao;
	}

	public void setLoginKakao(int loginKakao) {
		this.loginKakao = loginKakao;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getPushkey() {
		return pushkey;
	}

	public void setPushkey(String pushkey) {
		this.pushkey = pushkey;
	}

	public int getUsePushservice() {
		return usePushservice;
	}

	public void setUsePushservice(int usePushservice) {
		this.usePushservice = usePushservice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public int getLevelPoint() {
		return levelPoint;
	}

	public void setLevelPoint(int levelPoint) {
		this.levelPoint = levelPoint;
	}



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

	public String getThumphoto() {
		String str = "";
		if (!this.photo.equals("")) {

			String fileName = this.photo;
			// 썸네일
			String thumpath = fileName.substring(0, fileName.lastIndexOf("/"))
					+ "/thumb"; // 경로
			String tumbName = fileName.substring(fileName.lastIndexOf("/")); // 사진이름
			str = thumpath + tumbName;

		}
		return str;
	}

}
