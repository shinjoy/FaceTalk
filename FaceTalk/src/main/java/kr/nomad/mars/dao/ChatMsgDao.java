package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.ChatMsg;

public class ChatMsgDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper chatmsgMapper = new RowMapper() {
		public ChatMsg mapRow(ResultSet rs, int rowNum) throws SQLException {
			ChatMsg chatmsg = new ChatMsg();
			chatmsg.setChatMsgKey(rs.getString("chat_msg_key"));
			chatmsg.setChatRoomSeq(rs.getInt("chat_room_seq"));
			chatmsg.setSndId(rs.getString("snd_id"));
			chatmsg.setmType(rs.getInt("m_type"));
			chatmsg.setcType(rs.getInt("c_type"));
			chatmsg.setContents(rs.getString("contents"));
			chatmsg.setFileName(rs.getString("file_name"));
			chatmsg.setRegDate(rs.getString("reg_date"));
			chatmsg.setStatus(rs.getInt("status"));
			chatmsg.setOption1(rs.getString("option1"));
			chatmsg.setOption2(rs.getString("option2"));
			chatmsg.setOption3(rs.getString("option3"));
			return chatmsg;
		}
	};

	public void addChatMsg(final ChatMsg chatmsg) {
		String query = "" +
				"INSERT INTO T_NF_CHAT_MSG " +
				"	(chat_msg_key, chat_room_seq, snd_id, m_type, c_type, contents, file_name, reg_date, status, option1, option2, option3) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			chatmsg.getChatMsgKey(),
			chatmsg.getChatRoomSeq(),
			chatmsg.getSndId(),
			chatmsg.getmType(),
			chatmsg.getcType(),
			chatmsg.getContents(),
			chatmsg.getFileName(),
			chatmsg.getRegDate(),
			chatmsg.getStatus(),
			chatmsg.getOption1(),
			chatmsg.getOption2(),
			chatmsg.getOption3()
		});
	}

	public void deleteChatMsg(int chat_room_seq) {
		String query = "DELETE FROM T_NF_CHAT_MSG WHERE chat_room_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { chat_room_seq });
	}

	public void updateChatMsg(ChatMsg chatmsg) { 
		String query = "" + 
				"UPDATE T_NF_CHAT_MSG SET " +
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
			chatmsg.getChatMsgKey(),
			chatmsg.getChatRoomSeq(),
			chatmsg.getSndId(),
			chatmsg.getmType(),
			chatmsg.getcType(),
			chatmsg.getContents(),
			chatmsg.getFileName(),
			chatmsg.getRegDate(),
			chatmsg.getStatus(),
			chatmsg.getOption1(),
			chatmsg.getOption2(),
			chatmsg.getOption3()
		});
	}

	public ChatMsg getChatMsg(int chat_room_seq) {
		String query = "" + 
				"SELECT chat_msg_key, chat_room_seq, snd_id, m_type, c_type, contents, file_name, reg_date, status, option1, option2, option3 " +
				"FROM T_NF_CHAT_MSG " +
				"WHERE chat_room_seq = ? ";
		return (ChatMsg)this.jdbcTemplate.queryForObject(query, new Object[] { chat_room_seq }, this.chatmsgMapper);
	}

	public List<ChatMsg> getChatMsgList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" chat_msg_key, chat_room_seq, snd_id, m_type, c_type, contents, file_name, reg_date, status, option1, option2, option3 " +
				"FROM T_NF_CHAT_MSG " +
				"WHERE chat_room_seq <= ( " +
				"	SELECT MIN(chat_room_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" chat_room_seq " +
				"		FROM T_NF_CHAT_MSG " +
				"		ORDER BY chat_room_seq DESC " +
				"	) AS A) " +
				"ORDER BY chat_room_seq DESC";
		return (List<ChatMsg>)this.jdbcTemplate.query(query, this.chatmsgMapper);
	}
}
