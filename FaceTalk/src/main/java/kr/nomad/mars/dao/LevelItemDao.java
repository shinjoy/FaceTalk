package kr.nomad.mars.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import kr.nomad.mars.dto.LevelItem;
import kr.nomad.mars.dto.Point;
import kr.nomad.util.T;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class LevelItemDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper levelitemMapper = new RowMapper() {
		public LevelItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			LevelItem levelitem = new LevelItem();
			levelitem.setItemSeq(rs.getInt("item_seq"));
			levelitem.setLevelNum(rs.getInt("level_num"));
			levelitem.setBenefit(rs.getString("benefit"));
			levelitem.setPeriod(rs.getInt("period"));
			levelitem.setBenefitValue(rs.getString("benefit_value"));
			levelitem.setLimitCount(rs.getInt("limit_count"));
			return levelitem;
		}
	};
	
	private RowMapper levelnitemMapper = new RowMapper() {
		public LevelItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			LevelItem levelitem = new LevelItem();
			levelitem.setItemSeq(rs.getInt("item_seq"));
			levelitem.setLevelNum(rs.getInt("level_num"));
			levelitem.setBenefit(rs.getString("benefit"));
			levelitem.setPeriod(rs.getInt("period"));
			levelitem.setBenefitValue(rs.getString("benefit_value"));
			levelitem.setItemCode(rs.getInt("item_code"));
			levelitem.setLimitCount(rs.getInt("limit_count"));
			return levelitem;
		}
	};
	
	private RowMapper levelitemMapper2 = new RowMapper() {
		public LevelItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			LevelItem levelitem = new LevelItem();
		
			
			levelitem.setBenefit(rs.getString("benefit"));
		
			return levelitem;
		}
	};
	
	//이벤트 리스트
	
	public List<LevelItem> getLevelItemList() {
		String query = "" +
				"select distinct(benefit) from t_nf_level_item";
		return (List<LevelItem>)this.jdbcTemplate.query(query, this.levelitemMapper2);
	}
	
	public List<LevelItem> getLevelList(String benefit) {
		String query = "" +
				"select * from t_nf_level_item where benefit = ? ";
		
		return (List<LevelItem>)this.jdbcTemplate.query(query, new Object[] {benefit}, this.levelitemMapper);
	}

	public void addLevelItem(final LevelItem levelitem) {
		String query = "" +
				"INSERT INTO T_NF_Level_item " +
				"	(level_num, benefit, period,limit_count) " +
				"VALUES " +
				"	(?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			levelitem.getLevelNum(),
			levelitem.getBenefit(),
			levelitem.getPeriod(),
			levelitem.getLimitCount()
		});
	}

	public void deleteLevelItem(int item_seq) {
		String query = "DELETE FROM T_NF_Level_item WHERE item_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { item_seq });
	}

	public void updateLevelItem(LevelItem levelitem) { 
		String query = "" + 
				"UPDATE T_NF_Level_item SET " +
				"	level_num = ?, " +
				"	benefit = ?, " +
				"	period = ? ," +
				"	limit_count = ? " +
				"WHERE item_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			levelitem.getLevelNum(),
			levelitem.getBenefit(),
			levelitem.getPeriod(),
			levelitem.getLimitCount(),
			levelitem.getItemSeq()
		});
	}

	/*public LevelItem getLevelItem(int item_seq) {
		String query = "" + 
				"SELECT item_seq, level_num, benefit, period " +
				"FROM T_NF_Level_item " +
				"WHERE item_seq = ? ";
		return (LevelItem)this.jdbcTemplate.queryForObject(query, new Object[] { item_seq }, this.levelitemMapper);
	}*/
	
	// 포인트 리스트 
	
	public List<LevelItem> getLevelItemList(String site) {
		
		List<LevelItem> result = null;

		String query = ""
				+ "	SELECT * FROM  "
			
				+ "		 T_NF_Level_item where site=? ";
				
		result = (List<LevelItem>)this.jdbcTemplate.query(query,new Object[] { site }, this.levelitemMapper);
		
		return result;
	}

	// 레벨값
	public LevelItem getLevelItem(int level) {
		String query = ""
				+ "SELECT * "
				+ "FROM T_NF_Level_item where level_num = ? ";
		try {
			return (LevelItem) this.jdbcTemplate.queryForObject(query, new Object[] { level }, this.levelitemMapper);
		} catch (Exception e) {
			return null;
		}
	}
	//레벨값
	public List<LevelItem> getLevelItemList(int level) {
		String query = "" + 
				"SELECT l.item_seq,l.level_num,l.benefit,l.period,l.benefit_value,i.item_code,l.limit_count " +
				"FROM T_NF_Level_item as l " +
				"left outer join T_nf_item as i on l.benefit_value=i.item_name where l.level_num = ? ";
		try{
			return (List<LevelItem>)this.jdbcTemplate.query(query, new Object[] { level }, this.levelnitemMapper);
		}catch(Exception e){
			return null;
		}
	}
	
	
	
	
	
	public int getCount() {
		int result = 0;		
		String query = "SELECT COUNT(*) AS cnt FROM T_NF_Level_item ";
		result = this.jdbcTemplate.queryForInt(query);
				
		return result;
	}
	
	

}
