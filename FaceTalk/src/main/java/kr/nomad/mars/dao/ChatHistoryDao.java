package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.ChatHistory;

public class ChatHistoryDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper chathistoryMapper = new RowMapper() {
		public ChatHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
			ChatHistory chathistory = new ChatHistory();
			chathistory.setChatMsgKey(rs.getString("chat_msg_key"));
			chathistory.setChatRoomSeq(rs.getInt("chat_room_seq"));
			chathistory.setSndId(rs.getString("snd_id"));
			chathistory.setmType(rs.getInt("m_type"));
			chathistory.setcType(rs.getInt("c_type"));
			chathistory.setContents(rs.getString("contents"));
			chathistory.setFileName(rs.getString("file_name"));
			chathistory.setRegDate(rs.getString("reg_date"));
			chathistory.setStatus(rs.getInt("status"));
			chathistory.setOption1(rs.getString("option1"));
			chathistory.setOption2(rs.getString("option2"));
			chathistory.setOption3(rs.getString("option3"));
			return chathistory;
		}
	};

	public void addChatHistory(final ChatHistory chathistory) {
		String query = "" +
				"INSERT INTO T_NF_CHAT_HISTORY " +
				"	(chat_msg_key, chat_room_seq, snd_id, m_type, c_type, contents, file_name, reg_date, status, option1, option2, option3) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			chathistory.getChatMsgKey(),
			chathistory.getChatRoomSeq(),
			chathistory.getSndId(),
			chathistory.getmType(),
			chathistory.getcType(),
			chathistory.getContents(),
			chathistory.getFileName(),
			chathistory.getRegDate(),
			chathistory.getStatus(),
			chathistory.getOption1(),
			chathistory.getOption2(),
			chathistory.getOption3()
		});
	}

	public void deleteChatHistory(int chat_room_seq) {
		String query = "DELETE FROM T_NF_CHAT_HISTORY WHERE chat_room_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { chat_room_seq });
	}

	public void updateChatHistory(ChatHistory chathistory) { 
		String query = "" + 
				"UPDATE T_NF_CHAT_HISTORY SET " +
				"	chat_msg_key = ?, " +
				"	chat_room_seq = ?, " +
				"	snd_id = ?, " +
				"	m_type = ?, " +
				"	c_type = ?, " +
				"	contents = ?, " +
				"	file_name = ?, " +
				"	reg_date = ?, " +
				"	status = ?, " +
				"	option1 = ?, " +
				"	option2 = ?, " +
				"	option3 = ? " +
				"WHERE chat_room_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			chathistory.getChatMsgKey(),
			chathistory.getChatRoomSeq(),
			chathistory.getSndId(),
			chathistory.getmType(),
			chathistory.getcType(),
			chathistory.getContents(),
			chathistory.getFileName(),
			chathistory.getRegDate(),
			chathistory.getStatus(),
			chathistory.getOption1(),
			chathistory.getOption2(),
			chathistory.getOption3()
		});
	}

	public ChatHistory getChatHistory(int chat_room_seq) {
		String query = "" + 
				"SELECT chat_msg_key, chat_room_seq, snd_id, m_type, c_type, contents, file_name, reg_date, status, option1, option2, option3 " +
				"FROM T_NF_CHAT_HISTORY " +
				"WHERE chat_room_seq = ? ";
		return (ChatHistory)this.jdbcTemplate.queryForObject(query, new Object[] { chat_room_seq }, this.chathistoryMapper);
	}

	public List<ChatHistory> getChatHistoryList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" chat_msg_key, chat_room_seq, snd_id, m_type, c_type, contents, file_name, reg_date, status, option1, option2, option3 " +
				"FROM T_NF_CHAT_HISTORY " +
				"WHERE chat_room_seq <= ( " +
				"	SELECT MIN(chat_room_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" chat_room_seq " +
				"		FROM T_NF_CHAT_HISTORY " +
				"		ORDER BY chat_room_seq DESC " +
				"	) AS A) " +
				"ORDER BY chat_room_seq DESC";
		return (List<ChatHistory>)this.jdbcTemplate.query(query, this.chathistoryMapper);
	}
}
