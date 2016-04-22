package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.BbsVisit;

public class BbsVisitDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper bbsvisitMapper = new RowMapper() {
		public BbsVisit mapRow(ResultSet rs, int rowNum) throws SQLException {
			BbsVisit bbsvisit = new BbsVisit();
			bbsvisit.setSeq(rs.getInt("seq"));
			bbsvisit.setBbsSeq(rs.getInt("bbs_seq"));
			bbsvisit.setUserId(rs.getString("user_id"));
			return bbsvisit;
		}
	};

	public void addBbsVisit(final BbsVisit bbsvisit) {
		String query = "" +
				"INSERT INTO T_NF_BBS_VISIT " +
				"	(bbs_seq, user_id) " +
				"VALUES " +
				"	(?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			bbsvisit.getBbsSeq(),
			bbsvisit.getUserId()
		});
	}

	public void deleteBbsVisit(int seq) {
		String query = "DELETE FROM T_NF_BBS_VISIT WHERE seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { seq });
	}
	//탈퇴시 데이터삭제
	
	public void deleteBbsVisit(String userId) {
		String query = "DELETE FROM T_NF_BBS_VISIT WHERE user_id = ? ";
		try{
			this.jdbcTemplate.update(query, new Object[] { userId });
		}catch(Exception e){
			
		}
	}

	public void updateBbsVisit(BbsVisit bbsvisit) { 
		String query = "" + 
				"UPDATE T_NF_BBS_VISIT SET " +
				"	seq = ?, " +
				"	bbs_seq = ?, " +
				"	user_id = ? " +
				"WHERE seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			bbsvisit.getSeq(),
			bbsvisit.getBbsSeq(),
			bbsvisit.getUserId()
		});
	}

	public BbsVisit getBbsVisit(int seq) {
		String query = "" + 
				"SELECT seq, bbs_seq, user_id " +
				"FROM T_NF_BBS_VISIT " +
				"WHERE seq = ? ";
		return (BbsVisit)this.jdbcTemplate.queryForObject(query, new Object[] { seq }, this.bbsvisitMapper);
	}

	public int getBbsVisitCount(int bbsSeq, String userId) {
		String query = "" + 
				"SELECT COUNT(*) " +
				"FROM T_NF_BBS_VISIT " +
				"WHERE bbs_seq = ? AND user_id = ? ";
		return this.jdbcTemplate.queryForInt(query, new Object[] { bbsSeq, userId });
	}
}
