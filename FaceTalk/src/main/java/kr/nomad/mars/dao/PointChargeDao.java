package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Point;
import kr.nomad.mars.dto.PointCharge;

public class PointChargeDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper pointchargeMapper = new RowMapper() {
		public PointCharge mapRow(ResultSet rs, int rowNum) throws SQLException {
			PointCharge pointcharge = new PointCharge();
			pointcharge.setChargeSeq(rs.getInt("charge_seq"));
			pointcharge.setChargeMoney(rs.getInt("charge_money"));
			pointcharge.setChargePoint(rs.getInt("charge_point"));
			pointcharge.setPoint(rs.getInt("point"));
			pointcharge.setComment(rs.getString("comment"));
			pointcharge.setSite(rs.getString("site"));
			pointcharge.setCode(rs.getString("code"));
			return pointcharge;
		}
	};

	public void addPointCharge(final PointCharge pointcharge) {
		String query = "" +
				"INSERT INTO T_NF_SET_POINT_CHARGE " +
				"	(charge_money, charge_point, point, comment,site,code) " +
				"VALUES " +
				"	( ?, ?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			pointcharge.getChargeMoney(),
			pointcharge.getChargePoint(),
			pointcharge.getPoint(),
			pointcharge.getComment(),
			pointcharge.getSite(),
			pointcharge.getCode()
		});
	}

	public void deletePointCharge(int charge_seq) {
		String query = "DELETE FROM T_NF_SET_POINT_CHARGE WHERE charge_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { charge_seq });
	}

	public void updatePointCharge(PointCharge pointcharge) { 
		String query = "" + 
				"UPDATE T_NF_SET_POINT_CHARGE SET " +
				"	charge_money = ?, " +
				"	charge_point = ?, " +
				"	point = ?, " +
				"	site = ?, " +
				"	code = ?, " +
				"	comment = ? " +
				"WHERE charge_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			pointcharge.getChargeMoney(),
			pointcharge.getChargePoint(),
			pointcharge.getPoint(),
			pointcharge.getSite(),
			pointcharge.getCode(),
			pointcharge.getComment(),
			pointcharge.getChargeSeq()
		});
	}

	public PointCharge getPointCharge(int charge_seq) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_SET_POINT_CHARGE " +
				"WHERE charge_seq = ? ";
		return (PointCharge)this.jdbcTemplate.queryForObject(query, new Object[] { charge_seq }, this.pointchargeMapper);
	}

	public PointCharge getPointCharge(String code) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_SET_POINT_CHARGE " +
				"WHERE code = ? ";
		return (PointCharge)this.jdbcTemplate.queryForObject(query, new Object[] { code }, this.pointchargeMapper);
	}
	
	// 포인트 리스트 
	
	public List<PointCharge> getPointChargeList(String site) {
		
		List<PointCharge> result = null;

		String query = ""
				+ "	SELECT * FROM  "
				
				+ "	 T_NF_SET_POINT_CHARGE where site = ?";
				
		result = (List<PointCharge>)this.jdbcTemplate.query(query, new Object[] { site },this.pointchargeMapper);
		
		return result;
	}
	
	
	public int getCount() {
		int result = 0;		
		String query = "SELECT COUNT(*) AS cnt FROM T_NF_SET_POINT_CHARGE ";
		result = this.jdbcTemplate.queryForInt(query);
				
		return result;
	}
	
	// 포인트 리스트 
	
	public List<PointCharge> getPointChargeList() {
			
			String query = ""
					+ "	SELECT "
					+ "		* "
					+ "	FROM T_NF_SET_POINT_CHARGE order by charge_money ";
					
		return (List<PointCharge>)this.jdbcTemplate.query(query, this.pointchargeMapper);
			
	}
	
}
