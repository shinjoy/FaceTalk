package kr.nomad.mars.dto;

import java.io.File;
import java.util.ArrayList;

import kr.nomad.util.T;

public class Bbs {

	int bbsSeq = 0;
	String bbsCategory = "";
	String bbsHeader = "";
	String userId = "";
	String bbsTitle = "";
	String bbsContents = "";
	String bbsContentsText = "";
	String files = "";
	String linkUrl = "";
	String extraContents = "";
	int viewCount = 0;
	int likeCount = 0;
	int dislikeCount = 0;
	int reportCount = 0;
	int fileCount = 0;
	
	int blindCount = 0;
	String regDate = "";
	String cateName = "";
	String thumfile ="";
	
	String area = "";
	int gender = 0;
	int birthYear = 0;
	String nickName = "";
	String userName = "";
	String photo = "";
	int userLevel = 0;
	String pushkey = "";
	int pushType = 1;
	String userTypeText = "";
	String genderText = "";
	int userAge = 0;
	String os ="";
	int distance = 0;

	int bbscomCount = 0;

	String categoryName = "";
	String thumphoto ="";
	
	//뷰
	int shareSeq = 0;
	String writerId = "";
	String writerName = "";
	int friendStatus =0;
	int goodStatus =0;
	int kind =0;
	String writerPhoto="";
	String writerArea="";
	int writerGender=0;
	String writerComment="";
	int writerBirth=0;
	int commentCount = 0;
	int writerAge=0;
	String writergendertxt ="";
	String writerFiles="";
	String writerThumfiles="";
	String writerThumphoto="";
	int status =0;
	int writerStatus =0;
	int writerLikeCount =0;
	int writerCommnetCount =0;
	///공유
	int shareBbsSeq=0;
	String shareWriteId = "";
	String shareRegDate = "";
	int write_blind_count = 0 ;
	ArrayList fileList = new ArrayList();
	ArrayList fileList2 = new ArrayList();
	public static int MAN = 1;
	public static int WOMAN = 2;
	String site = "";

	
	
	
	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public ArrayList getFileList2() {
		return fileList2;
	}

	public void setFileList2(ArrayList fileList2) {
		this.fileList2 = fileList2;
	}

	public ArrayList getFileList() {
		return fileList;
	}

	public void setFileList(ArrayList fileList) {
		this.fileList = fileList;
	}

	public int getWrite_blind_count() {
		return write_blind_count;
	}

	public void setWrite_blind_count(int write_blind_count) {
		this.write_blind_count = write_blind_count;
	}

	public int getWriterLikeCount() {
		return writerLikeCount;
	}

	public void setWriterLikeCount(int writerLikeCount) {
		this.writerLikeCount = writerLikeCount;
	}

	public int getWriterCommnetCount() {
		return writerCommnetCount;
	}

	public void setWriterCommnetCount(int writerCommnetCount) {
		this.writerCommnetCount = writerCommnetCount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getWriterStatus() {
		return writerStatus;
	}

	public void setWriterStatus(int writerStatus) {
		this.writerStatus = writerStatus;
	}

	public String getWriterFiles() {
		return writerFiles;
	}

	public void setWriterFiles(String writerFiles) {
		this.writerFiles = writerFiles;
	}

	public int getGoodStatus() {
		return goodStatus;
	}

	public void setGoodStatus(int goodStatus) {
		this.goodStatus = goodStatus;
	}


	public String getWriterPhoto() {
		return writerPhoto;
	}

	public void setWriterPhoto(String writerPhoto) {
		this.writerPhoto = writerPhoto;
	}

	public String getWriterArea() {
		return writerArea;
	}

	public void setWriterArea(String writerArea) {
		this.writerArea = writerArea;
	}

	public int getWriterGender() {
		return writerGender;
	}

	
	public String getWriterComment() {
		return writerComment;
	}

	public void setWriterComment(String writerComment) {
		this.writerComment = writerComment;
	}

	public void setWriterGender(int writerGender) {
		this.writerGender = writerGender;
	}

	
	public int getWriterBirth() {
		return writerBirth;
	}

	public void setWriterBirth(int writerBirth) {
		this.writerBirth = writerBirth;
	}

	public int getShareBbsSeq() {
		return shareBbsSeq;
	}

	public void setShareBbsSeq(int shareBbsSeq) {
		this.shareBbsSeq = shareBbsSeq;
	}

	public String getShareWriteId() {
		return shareWriteId;
	}

	public void setShareWriteId(String shareWriteId) {
		this.shareWriteId = shareWriteId;
	}

	public String getShareRegDate() {
		return shareRegDate;
	}

	public void setShareRegDate(String shareRegDate) {
		this.shareRegDate = shareRegDate;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
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

	
	public String getWritergendertxt() {
		if (this.writerGender == MAN) {
			return "남자";
		} else if (this.writerGender == WOMAN) {
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
	
	public int getWriterAge() {
		int thisYear = Integer.parseInt(T.getTodayYear());
		if (this.writerBirth == 0) {
			return 0;
		} else {
			return thisYear - this.writerBirth +1;
		}
	}

	
	

	public String getPushkey() {
		return pushkey;
	}

	public void setPushkey(String pushkey) {
		this.pushkey = pushkey;
	}

	

	public int getPushType() {
		return pushType;
	}

	public void setPushType(int pushType) {
		this.pushType = pushType;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public int getFriendStatus() {
		return friendStatus;
	}

	public void setFriendStatus(int friendStatus) {
		this.friendStatus = friendStatus;
	}

	public int getBbscomCount() {
		return bbscomCount;
	}

	public void setBbscomCount(int bbscomCount) {
		this.bbscomCount = bbscomCount;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public int getBlindCount() {
		return blindCount;
	}

	public void setBlindCount(int blindCount) {
		this.blindCount = blindCount;
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

	public int getBbsSeq() {
		return bbsSeq;
	}

	public void setBbsSeq(int bbsSeq) {
		this.bbsSeq = bbsSeq;
	}

	public String getBbsCategory() {
		return bbsCategory;
	}

	public void setBbsCategory(String bbsCategory) {
		this.bbsCategory = bbsCategory;
	}

	public String getBbsHeader() {
		return bbsHeader;
	}

	public void setBbsHeader(String bbsHeader) {
		this.bbsHeader = bbsHeader;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBbsTitle() {
		return bbsTitle;
	}

	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}

	public String getBbsContents() {
		//String txt=bbsContents;
		/*if(this.blindCount==1||this.write_blind_count==1){
			txt="블라인드 처리 된 게시글 입니다.";
		}*/
		return bbsContents;
	}

	public void setBbsContents(String bbsContents) {
		this.bbsContents = bbsContents;
	}

	public String getBbsContentsText() {
		return bbsContentsText;
	}

	public void setBbsContentsText(String bbsContentsText) {
		this.bbsContentsText = bbsContentsText;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getExtraContents() {
		return extraContents;
	}

	public void setExtraContents(String extraContents) {
		this.extraContents = extraContents;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getDislikeCount() {
		return dislikeCount;
	}

	public void setDislikeCount(int dislikeCount) {
		this.dislikeCount = dislikeCount;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getShareSeq() {
		return shareSeq;
	}

	public void setShareSeq(int shareSeq) {
		this.shareSeq = shareSeq;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getThumfile() {
		String str ="";
		if(!this.files.equals("")){
			String [] arr = this.files.split(",");
			for(int i=0; i<arr.length ; i++){
				String fileName = arr[i];
				//썸네일
				String thumpath = fileName.substring(0,fileName.lastIndexOf("/"))+"/thumb"; //경로
				String tumbName = fileName.substring(fileName.lastIndexOf("/")); //사진이름
				if(arr.length-1==i){
					str+=thumpath+tumbName;
				}else{
					str+=thumpath+tumbName+",";
				}
				
				//str+=thumpath+tumbName+",";
				
			}
		}
		
		
		return str;
	}
	
	
	
	public String getWriterThumfiles() {
		String str ="";
		if(!this.writerFiles.equals("")){
			String [] arr = this.writerFiles.split(",");
			for(int i=0; i<arr.length ; i++){
				String fileName = arr[i];
				//썸네일
				String thumpath = fileName.substring(0,fileName.lastIndexOf("/"))+"/thumb"; //경로
				String tumbName = fileName.substring(fileName.lastIndexOf("/")); //사진이름
				if(arr.length-1==i){
					str+=thumpath+tumbName;
				}else{
					str+=thumpath+tumbName+",";
				}
				
				//str+=thumpath+tumbName+",";
				
			}
		}
		
		
		return str;
	
	}

	
	public String getWriterThumphoto() {
		String str ="";
		if(!this.writerPhoto.equals("")){
			
				String fileName = this.writerPhoto;
				//썸네일
				String thumpath = fileName.substring(0,fileName.lastIndexOf("/"))+"/thumb"; //경로
				String tumbName = fileName.substring(fileName.lastIndexOf("/")); //사진이름
				str=thumpath+tumbName;
				
			}
		return str;
	}

	public String getThumphoto() {
		String str ="";
		if(!this.photo.equals("")&&this.photo!=null&&!this.photo.equals(null)){
			
				String fileName = this.photo;
				//썸네일
				String thumpath = fileName.substring(0,fileName.lastIndexOf("/"))+"/thumb"; //경로
				String tumbName = fileName.substring(fileName.lastIndexOf("/")); //사진이름
				str=thumpath+tumbName;
				
			}
		return str;
	}

	public void setThumfile(String thumfile) {
		this.thumfile = thumfile;
	}

	public void setWriterThumfiles(String writerThumfiles) {
		this.writerThumfiles = writerThumfiles;
	}
	
	

}
