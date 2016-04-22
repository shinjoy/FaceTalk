package kr.nomad.mars.dto;

public class Point {
	int pointSeq = 0;
	String eventName = "";
	int point = 0;
	int money = 0;
	int period = 0;
	int times = 0;
	String commend = "";
	String pointCode = "";
	String periodtxt ="";
	String timestxt ="";
	String site="";
	
	
	
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public int getPointSeq() {
		return pointSeq;
	}
	public void setPointSeq(int pointSeq) {
		this.pointSeq = pointSeq;
	}
	public String getPointCode() {
		return pointCode;
	}
	public void setPointCode(String pointCode) {
		this.pointCode = pointCode;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public String getCommend() {
		return commend;
	}
	public void setCommend(String commend) {
		this.commend = commend;
	}
	public String getPeriodtxt() {
		String str ="" ;
		if(this.period==4){
			str = "1일";
		}else if(this.period==2){
			str ="매번";
		}else if(this.period==1){
			str ="최초 1회";
		}
		return str;
	}
	public void setPeriodtxt(String periodtxt) {
		this.periodtxt = periodtxt;
	}
	
	
	
	
	 

}
