package kr.nomad.mars.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import kr.nomad.mars.dto.Gift;
import kr.nomad.util.T;
import kr.nomad.util.push.PushKey;
import kr.nomad.util.push.PushMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class GiftDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper giftMapper = new RowMapper() {
		public Gift mapRow(ResultSet rs, int rowNum) throws SQLException {
			Gift gift = new Gift();
			gift.setGiftSeq(rs.getInt("gift_seq"));
			gift.setReceiveId(rs.getString("receive_id"));
			gift.setSendId(rs.getString("send_id"));
			gift.setGiftPoint(rs.getInt("gift_point"));
			gift.setRegDate(rs.getString("reg_date"));
			return gift;
		}
	};
	
	//선물로그
	public void addGIFT(final Gift gift) {
		String query = "" +
				"INSERT INTO T_NF_GIFT " +
				"	(receive_id, send_id, gift_point, reg_date) " +
				"VALUES " +
				"	(?, ?, ?, getDate()) ";
		this.jdbcTemplate.update(query, new Object[] {
			
			gift.getReceiveId(),
			gift.getSendId(),
			gift.getGiftPoint()
			
		});
	}
	//탈퇴시 삭제
	public void deletedropGIFT(String userId) {
		String query = "DELETE FROM T_NF_GIFT WHERE  receive_id = ?  or send_id = ? " ;
		this.jdbcTemplate.update(query, new Object[] { userId,userId });
	}


	public void deleteGIFT(String gift_seq) {
		String query = "DELETE FROM T_NF_GIFT WHERE gift_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { gift_seq });
	}

	public void updateGIFT(Gift gift) { 
		String query = "" + 
				"UPDATE T_NF_GIFT SET " +
				"	gift_seq = ?, " +
				"	receive_id = ?, " +
				"	send_id = ?, " +
				"	gift_point = ?, " +
				"	reg_date = ? " +
				"WHERE gift_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			gift.getGiftSeq(),
			gift.getReceiveId(),
			gift.getSendId(),
			gift.getGiftPoint(),
			gift.getRegDate()
		});
	}

	public Gift getGIFT(String gift_seq) {
		String query = "" + 
				"SELECT gift_seq, receive_id, send_id, gift_point, reg_date " +
				"FROM T_NF_GIFT " +
				"WHERE gift_seq = ? ";
		return (Gift)this.jdbcTemplate.queryForObject(query, new Object[] { gift_seq }, this.giftMapper);
	}

	public List<Gift> getGIFTList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" gift_seq, receive_id, send_id, gift_point, reg_date " +
				"FROM T_NF_GIFT " +
				"WHERE gift_seq <= ( " +
				"	SELECT MIN(gift_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" gift_seq " +
				"		FROM T_NF_GIFT " +
				"		ORDER BY gift_seq DESC " +
				"	) AS A) " +
				"ORDER BY gift_seq DESC";
		return (List<Gift>)this.jdbcTemplate.query(query, this.giftMapper);
	}

}
