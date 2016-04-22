package kr.nomad.mars.dto;

public class Expense {
	
	int expenseSeq = 0;
	String userId = "";
	int point = 0;
	int payPoint = 0;
	String requestDate = "";
	String finishDate = "";
	String type = "";
	int status =0;
	String typeTxt = "";
	String statusTxt="";
	String bank="";
	String bankCount = "";
	String ownerName ="";
	String comment ="";
	
	String userName ="";
	int income =0 ;
	int upoint =0;
	String site="";
	String siteName="";
	
	
	
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankCount() {
		return bankCount;
	}

	public void setBankCount(String bankCount) {
		this.bankCount = bankCount;
	}

	public String getStatusTxt() {
		String str ="";
		
		if(this.status==1){
			str= "완료";	
		}else if(this.status==2){
			str= "불가";
		}
		return str;
	}
	
	public String getTypeTxt(){
		String str ="";
		
		if(this.type.equals("point")){
			str= "포인트";	
		}else if(this.type.equals("fmoney")){
			str= "FMONEY";
		}
		return str;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public int getUpoint() {
		return upoint;
	}
	public void setUpoint(int upoint) {
		this.upoint = upoint;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getExpenseSeq() {
		return expenseSeq;
	}
	public void setExpenseSeq(int expenseSeq) {
		this.expenseSeq = expenseSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getPayPoint() {
		return payPoint;
	}
	public void setPayPoint(int payPoint) {
		this.payPoint = payPoint;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	
	

}
