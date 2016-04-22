package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import kr.nomad.mars.dto.BbsShare;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class BbsShareDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper bbsshareMapper = new RowMapper() {
		public BbsShare mapRow(ResultSet rs, int rowNum) throws SQLException {
			BbsShare bbsshare = new BbsShare();
			bbsshare.setShareSeq(rs.getInt("share_seq"));
			bbsshare.setBbsSeq(rs.getInt("bbs_seq"));
			bbsshare.setUserId(rs.getString("user_id"));
			bbsshare.setRegDate(rs.getString("reg_date"));
			return bbsshare;
		}
	};

	public void addBbsShare(final BbsShare bbsshare) {
		String query = "" +
				"INSERT INTO T_NF_BBS_SHARE " +
				"	(bbs_seq, user_id, reg_date) " +
				"VALUES " +
				"	(?, ?, getDate()) ";
		this.jdbcTemplate.update(query, new Object[] {
			bbsshare.getBbsSeq(),
			bbsshare.getUserId()
		});
	}

	public void deleteBbsShare(int share_seq) {
		String query = "DELETE FROM T_NF_BBS_SHARE WHERE share_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { share_seq });
	}
	//탈퇴시 공유기록 삭제
	public void deleteuserShare(String userId) {
		String query = "DELETE FROM T_NF_BBS_SHARE WHERE user_id = ?  ";
		this.jdbcTemplate.update(query, new Object[] { userId });
	}
	//본글 삭제시 공유기록삭제
	public void deleteBbsShare2(int bbsSeq) {
		String query = "DELETE FROM T_NF_BBS_SHARE WHERE bbs_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { bbsSeq });
	}
	
	//공유하기 중복체크
	
	public int getBbsSharechk(String user_id,int bbsSeq) {
		String query = "" + 
				"SELECT count(*) " +
				"FROM T_NF_BBS_SHARE " +
				"WHERE bbs_seq = ? and user_id = ? ";
		return this.jdbcTemplate.queryForInt(query, new Object[] { bbsSeq,user_id });
	}

	public BbsShare getBbsShare(String share_seq) {
		String query = "" + 
				"SELECT share_seq, bbs_seq, user_id, reg_date " +
				"FROM T_NF_BBS_SHARE " +
				"WHERE share_seq = ? ";
		return (BbsShare)this.jdbcTemplate.queryForObject(query, new Object[] { share_seq }, this.bbsshareMapper);
	}
}
