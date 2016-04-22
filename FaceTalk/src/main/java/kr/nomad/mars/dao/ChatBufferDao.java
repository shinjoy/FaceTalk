package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.ChatBuffer;

public class ChatBufferDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper chatbufferMapper = new RowMapper() {
		public ChatBuffer mapRow(ResultSet rs, int rowNum) throws SQLException {
			ChatBuffer chatbuffer = new ChatBuffer();
			chatbuffer.setChatBufferSeq(rs.getInt("chat_buffer_seq"));
			chatbuffer.setServerId(rs.getString("server_id"));
			chatbuffer.setDestId(rs.getString("dest_id"));
			chatbuffer.setFromServerId(rs.getString("from_server_id"));
			chatbuffer.setBufferKey(rs.getInt("buffer_key"));
			chatbuffer.setContents(rs.getString("contents"));
			chatbuffer.setPt(rs.getInt("pt"));
			chatbuffer.setSt(rs.getInt("st"));
			return chatbuffer;
		}
	};

	public void addChatBuffer(final ChatBuffer chatbuffer) {
		String query = "" +
				"INSERT INTO T_NF_CHAT_BUFFER " +
				"	(chat_buffer_seq, server_id, dest_id, from_server_id, buffer_key, contents, pt, st) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			chatbuffer.getChatBufferSeq(),
			chatbuffer.getServerId(),
			chatbuffer.getDestId(),
			chatbuffer.getFromServerId(),
			chatbuffer.getBufferKey(),
			chatbuffer.getContents(),
			chatbuffer.getPt(),
			chatbuffer.getSt()
		});
	}

	public void deleteChatBuffer(int chat_buffer_seq) {
		String query = "DELETE FROM T_NF_CHAT_BUFFER WHERE chat_buffer_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { chat_buffer_seq });
	}

	public void updateChatBuffer(ChatBuffer chatbuffer) { 
		String query = "" + 
				"UPDATE T_NF_CHAT_BUFFER SET " +
				"	chat_buffer_seq = ?, " +
				"	server_id = ?, " +
				"	dest_id = ?, " +
				"	from_server_id = ?, " +
				"	buffer_key = ?, " +
				"	contents = ?, " +
				"	pt = ?, " +
				"	st = ? " +
				"WHERE chat_buffer_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			chatbuffer.getChatBufferSeq(),
			chatbuffer.getServerId(),
			chatbuffer.getDestId(),
			chatbuffer.getFromServerId(),
			chatbuffer.getBufferKey(),
			chatbuffer.getContents(),
			chatbuffer.getPt(),
			chatbuffer.getSt()
		});
	}

	public ChatBuffer getChatBuffer(int chat_buffer_seq) {
		String query = "" + 
				"SELECT chat_buffer_seq, server_id, dest_id, from_server_id, buffer_key, contents, pt, st " +
				"FROM T_NF_CHAT_BUFFER " +
				"WHERE chat_buffer_seq = ? ";
		return (ChatBuffer)this.jdbcTemplate.queryForObject(query, new Object[] { chat_buffer_seq }, this.chatbufferMapper);
	}

	public List<ChatBuffer> getChatBufferList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" chat_buffer_seq, server_id, dest_id, from_server_id, buffer_key, contents, pt, st " +
				"FROM T_NF_CHAT_BUFFER " +
				"WHERE chat_buffer_seq <= ( " +
				"	SELECT MIN(chat_buffer_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" chat_buffer_seq " +
				"		FROM T_NF_CHAT_BUFFER " +
				"		ORDER BY chat_buffer_seq DESC " +
				"	) AS A) " +
				"ORDER BY chat_buffer_seq DESC";
		return (List<ChatBuffer>)this.jdbcTemplate.query(query, this.chatbufferMapper);
	}
}
