package kr.nomad.mars.dto;

public class Level {
	int levelSeq = 0;
	int level = 0;
	int minEx = 0;
	int maxEx = 0;

	public int getLevelSeq() {
		return levelSeq;
	}

	public void setLevelSeq(int levelSeq) {
		this.levelSeq = levelSeq;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMinEx() {
		return minEx;
	}

	public void setMinEx(int minEx) {
		this.minEx = minEx;
	}

	public int getMaxEx() {
		return maxEx;
	}

	public void setMaxEx(int maxEx) {
		this.maxEx = maxEx;
	}

}
