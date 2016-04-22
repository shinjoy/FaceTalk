package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.ChatTran;

public class ChatTranDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper chattranMapper = new RowMapper() {
		public ChatTran mapRow(ResultSet rs, int rowNum) throws SQLException {
			ChatTran chattran = new ChatTran();
			chattran.setChatTranSeq(rs.getInt("chat_tran_seq"));
			chattran.setChatRoomSeq(rs.getInt("chat_room_seq"));
			chattran.setChatMsgKey(rs.getString("chat_msg_key"));
			chattran.setSndId(rs.getString("snd_id"));
			chattran.setRcvId(rs.getString("rcv_id"));
			chattran.setStatus(rs.getInt("status"));
			chattran.setRegDate(rs.getString("reg_date"));
			return chattran;
		}
	};

	public void addChatTran(final ChatTran chattran) {
		String query = "" +
				"INSERT INTO T_NF_CHAT_TRAN " +
				"	(chat_tran_seq, chat_room_seq, chat_msg_key, snd_id, rcv_id, status, reg_date) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			chattran.getChatTranSeq(),
			chattran.getChatRoomSeq(),
			chattran.getChatMsgKey(),
			chattran.getSndId(),
			chattran.getRcvId(),
			chattran.getStatus(),
			chattran.getRegDate()
		});
	}

	public void deleteChatTran(int chat_tran_seq) {
		String query = "DELETE FROM T_NF_CHAT_TRAN WHERE chat_tran_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { chat_tran_seq });
	}

	public void updateChatTran(ChatTran chattran) { 
		String query = "" + 
				"UPDATE T_NF_CHAT_TRAN SET " +
				"	chat_tran_seq = ?, " +
				"	chat_room_seq = ?, " +
				"	chat_msg_key = ?, " +
				"	snd_id = ?, " +
				"	rcv_id = ?, " +
				"	status = ?, " +
				"	reg_date = ? " +
				"WHERE chat_tran_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			chattran.getChatTranSeq(),
			chattran.getChatRoomSeq(),
			chattran.getChatMsgKey(),
			chattran.getSndId(),
			chattran.getRcvId(),
			chattran.getStatus(),
			chattran.getRegDate()
		});
	}

	public ChatTran getChatTran(int chat_tran_seq) {
		String query = "" + 
				"SELECT chat_tran_seq, chat_room_seq, chat_msg_key, snd_id, rcv_id, status, reg_date " +
				"FROM T_NF_CHAT_TRAN " +
				"WHERE chat_tran_seq = ? ";
		return (ChatTran)this.jdbcTemplate.queryForObject(query, new Object[] { chat_tran_seq }, this.chattranMapper);
	}

	public List<ChatTran> getChatTranList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" chat_tran_seq, chat_room_seq, chat_msg_key, snd_id, rcv_id, status, reg_date " +
				"FROM T_NF_CHAT_TRAN " +
				"WHERE chat_tran_seq <= ( " +
				"	SELECT MIN(chat_tran_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" chat_tran_seq " +
				"		FROM T_NF_CHAT_TRAN " +
				"		ORDER BY chat_tran_seq DESC " +
				"	) AS A) " +
				"ORDER BY chat_tran_seq DESC";
		return (List<ChatTran>)this.jdbcTemplate.query(query, this.chattranMapper);
	}
}
