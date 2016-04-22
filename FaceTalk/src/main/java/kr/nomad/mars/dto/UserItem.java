package kr.nomad.mars.dto;

public class UserItem {

	int itemSeq = 0;
	String userId = "";
	String stardDate = "";
	String finishDate = "";
	String itemName = "";
	int itemCode = 0;
	int status = 0;
	int useCount =0;
	int limitCount =0;
	int period =0;
	
	
	
	
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getLimitCount() {
		return limitCount;
	}
	public void setLimitCount(int limitCount) {
		this.limitCount = limitCount;
	}
	public int getUseCount() {
		return useCount;
	}
	public void setUseCount(int useCount) {
		this.useCount = useCount;
	}
	public int getItemSeq() {
		return itemSeq;
	}
	public void setItemSeq(int itemSeq) {
		this.itemSeq = itemSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStardDate() {
		return stardDate;
	}
	public void setStardDate(String stardDate) {
		this.stardDate = stardDate;
	}
	
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemCode() {
		return itemCode;
	}
	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getItemtxt() {
		String str ="";
		if(this.itemCode==Item.CODE_FREETICHET){
			str = "자유이용권";
		}else{
			str = "확성기";
		}
		return str;
	}

	
}
