package kr.nomad.mars.dto;

public class Distance {

	int distanceSeq = 0;
	int distanceValue = 0;
	int sort = 0;
	String distanceName = "";

	public String getDistanceName() {
		return distanceName;
	}

	public void setDistanceName(String distanceName) {
		this.distanceName = distanceName;
	}

	public int getDistanceSeq() {
		return distanceSeq;
	}

	public void setDistanceSeq(int distanceSeq) {
		this.distanceSeq = distanceSeq;
	}

	public int getDistanceValue() {
		return distanceValue;
	}

	public void setDistanceValue(int distanceValue) {
		this.distanceValue = distanceValue;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
