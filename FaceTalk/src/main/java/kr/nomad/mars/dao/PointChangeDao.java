package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.PointChange;
import kr.nomad.mars.dto.PointCharge;

public class PointChangeDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper pointchangeMapper = new RowMapper() {
		public PointChange mapRow(ResultSet rs, int rowNum) throws SQLException {
			PointChange pointchange = new PointChange();
			pointchange.setChangeSeq(rs.getInt("change_seq"));
			pointchange.setPoint(rs.getInt("point"));
			pointchange.setMoney(rs.getInt("money"));
			pointchange.setCash(rs.getInt("cash"));
			pointchange.setStatus(rs.getInt("status"));
			pointchange.setSite(rs.getString("site"));
			return pointchange;
		}
	};

	public void addPointChange(final PointChange pointchange) {
		String query = "" +
				"INSERT INTO T_NF_SET_POINT_CHANGE " +
				"	(point, money, cash, status,site) " +
				"VALUES " +
				"	(?, ?, ?, ?,?) ";
		this.jdbcTemplate.update(query, new Object[] {
			pointchange.getPoint(),
			pointchange.getMoney(),
			pointchange.getCash(),
			pointchange.getStatus(),
			pointchange.getSite()
		});
	}

	public void deletePointChange(int change_seq) {
		String query = "DELETE FROM T_NF_SET_POINT_CHANGE WHERE change_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { change_seq });
	}

	public void updatePointChange(PointChange pointchange) { 
		String query = "" + 
				"UPDATE T_NF_SET_POINT_CHANGE SET " +
				"	point = ?, " +
				"	money = ?, " +
				"	cash = ?, " +
				"	site = ?, " +
				"	status = ? " +
				"WHERE change_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			pointchange.getPoint(),
			pointchange.getMoney(),
			pointchange.getCash(),
			pointchange.getSite(),
			pointchange.getStatus(),
			pointchange.getChangeSeq()
		});
	}

	public PointChange getPointChange(int change_seq) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_SET_POINT_CHANGE " +
				"WHERE change_seq = ? ";
		return (PointChange)this.jdbcTemplate.queryForObject(query, new Object[] { change_seq }, this.pointchangeMapper);
	}

	
	// 포인트 리스트 
	
	public List<PointChange> getPointChangeList(String site) {
		
		List<PointChange> result = null;

		String query = ""
				+ "	SELECT * FROM  "
			
				+ "	 T_NF_SET_POINT_CHANGE  where site=?";
				
		result = (List<PointChange>)this.jdbcTemplate.query(query,  new Object[] { site },this.pointchangeMapper);
		
		return result;
	}
	
	// 포인트 리스트 
	
	public List<PointChange> getPointChangeListpaging(String site,int page,int itemCountPerPage) {
		
		List<PointChange> result = null;

		String query = ""
				+ "	SELECT * FROM ( "
			    + "		SELECT "
		        + "			ROW_NUMBER() OVER(ORDER BY change_seq asc) as row_seq, "
		        + "			* "
		        + " FROM	 T_NF_SET_POINT_CHANGE  where site= ? "
				+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		result = (List<PointChange>)this.jdbcTemplate.query(query,  new Object[] { site },this.pointchangeMapper);
		
		return result;
	}
	public int getCount(String site) {
		int result = 0;		
		String query = "SELECT COUNT(*)  FROM T_NF_SET_POINT_CHANGE where site= ? ";
		result = this.jdbcTemplate.queryForInt(query,new Object[] { site });
				
		return result;
	}
	//포인트 리스트 주연
	public List<PointChange> getPointChangeList() {
		
		List<PointChange> result = null;

		String query = ""
				+ "	SELECT * FROM  "
				+ "	T_NF_SET_POINT_CHANGE ";
				
		return (List<PointChange>)this.jdbcTemplate.query(query, this.pointchangeMapper);
	}
}
