package kr.nomad.mars.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.LevelHistory;
import kr.nomad.util.T;

public class LevelHistoryDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper levelhistoryMapper = new RowMapper() {
		public LevelHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
			LevelHistory levelhistory = new LevelHistory();
			levelhistory.setLvSeq(rs.getInt("lv_seq"));
			levelhistory.setUserId(rs.getString("user_id"));
			levelhistory.setLevelMoney(rs.getInt("level_money"));
			levelhistory.setInMoney(rs.getInt("in_money"));
			levelhistory.setOutMoney(rs.getInt("out_money"));
			levelhistory.setCurrentLevel(rs.getInt("current_level"));
			levelhistory.setMaxLevel(rs.getInt("max_level"));
			levelhistory.setRegDate(rs.getString("reg_date"));
			return levelhistory;
		}
	};

	public void addLevelHistory(final LevelHistory levelhistory) {
		String query = "" +
				"INSERT INTO T_NF_LEVEL_HISTORY " +
				"	(user_id, level_money, in_money, out_money, current_level, max_level, reg_date) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?, getDate()) ";
		this.jdbcTemplate.update(query, new Object[] {
			levelhistory.getUserId(),
			levelhistory.getLevelMoney(),
			levelhistory.getInMoney(),
			levelhistory.getOutMoney(),
			levelhistory.getCurrentLevel(),
			levelhistory.getMaxLevel()
			
		});
	}
	//탈퇴시 삭제
	public void deleteLevelHistory(String userId) {
		String query = "DELETE FROM T_NF_LEVEL_HISTORY WHERE user_Id = ? ";
		this.jdbcTemplate.update(query, new Object[] { userId });
	}
	//레벨 업데이트
	public void updateLevelHistory(String userId,int level,int maxlevel) { 
		String query = "" + 
				"UPDATE T_NF_LEVEL_HISTORY SET " +
				"	current_level = ?, " +
				"	max_level = ? " +
				"WHERE lv_seq = (select top 1 lv_seq from T_NF_LEVEL_HISTORY where user_Id='"+userId+"'"
				+ "order by reg_date desc)";
		this.jdbcTemplate.update(query, new Object[] {
	
			level,maxlevel
		});
	}
	//레벨 계산전 가져옴.
	public LevelHistory getLevelHistory(String userId) {
		String query = "" + 
				"SELECT top 1 *" +
				"FROM T_NF_LEVEL_HISTORY " +
				"WHERE user_id = ? order by reg_date desc";
		return (LevelHistory)this.jdbcTemplate.queryForObject(query, new Object[] { userId }, this.levelhistoryMapper);
	}

	public List<LevelHistory> getLevelHistoryList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" lv_seq, user_id, level_money, in_money, out_money, current_level, max_level, reg_date " +
				"FROM T_NF_LEVEL_HISTORY " +
				"WHERE lv_seq <= ( " +
				"	SELECT MIN(lv_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" lv_seq " +
				"		FROM T_NF_LEVEL_HISTORY " +
				"		ORDER BY lv_seq DESC " +
				"	) AS A) " +
				"ORDER BY lv_seq DESC";
		return (List<LevelHistory>)this.jdbcTemplate.query(query, this.levelhistoryMapper);
	}

}
