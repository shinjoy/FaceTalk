package kr.nomad.mars.dto;

public class PointCharge {
	int chargeSeq = 0;
	int chargeMoney = 0;
	int chargePoint = 0;
	int point = 0;
	String comment = "";
	String site="";
	String code ="";
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public int getChargeSeq() {
		return chargeSeq;
	}
	public void setChargeSeq(int chargeSeq) {
		this.chargeSeq = chargeSeq;
	}
	public int getChargeMoney() {
		return chargeMoney;
	}
	public void setChargeMoney(int chargeMoney) {
		this.chargeMoney = chargeMoney;
	}
	public int getChargePoint() {
		return chargePoint;
	}
	public void setChargePoint(int chargePoint) {
		this.chargePoint = chargePoint;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

}
