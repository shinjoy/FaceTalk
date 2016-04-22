package kr.nomad.mars.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.ChatOut;
import kr.nomad.mars.dto.ChatRequest;
import kr.nomad.mars.dto.FriendRequest;

public class ChatOutDao {

	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper chatoutMapper = new RowMapper() {
		public ChatOut mapRow(ResultSet rs, int rowNum) throws SQLException {
			ChatOut chatout = new ChatOut();
			chatout.setChatRoomSeq(rs.getInt("chat_room_seq"));
			chatout.setBlockId(rs.getString("block_id"));
			chatout.setRegDate(rs.getString("reg_date"));
			chatout.setOutSeq(rs.getInt("out_seq"));
			return chatout;
		}
	};

	public void addChatOut(final ChatOut chatout) {
		String query = "" +
				"INSERT INTO T_NF_CHAT_OUT " +
				"	(chat_room_seq, block_id, reg_date, out_seq) " +
				"VALUES " +
				"	(?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			chatout.getChatRoomSeq(),
			chatout.getBlockId(),
			chatout.getRegDate(),
			chatout.getOutSeq()
		});
	}

	public void deleteChatOut(String userId) {
		String query = "DELETE FROM T_NF_CHAT_OUT WHERE block_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { userId });
	}

	public void updateChatOut(ChatOut chatout) { 
		String query = "" + 
				"UPDATE T_NF_CHAT_OUT SET " +
				"	chat_room_seq = ?, " +
				"	block_id = ?, " +
				"	reg_date = ?, " +
				"	out_seq = ? " +
				"WHERE out_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			chatout.getChatRoomSeq(),
			chatout.getBlockId(),
			chatout.getRegDate(),
			chatout.getOutSeq()
		});
	}

	//강퇴당햇는지 판별 
	
	
	public int getChatOut(int chatroomseq,String userId) {
		String query = "" + 
				"SELECT  count(*) " +
				"FROM T_NF_CHAT_OUT " +
				"WHERE chat_room_seq = ? and block_id = ?  ";
		try{
			return this.jdbcTemplate.queryForInt(query, new Object[] { chatroomseq , userId});
		}catch(Exception e){
			return 0;
		}
	}

	
	public ChatOut getChatOut(String out_seq) {
		String query = "" + 
				"SELECT chat_room_seq, block_id, reg_date, out_seq " +
				"FROM T_NF_CHAT_OUT " +
				"WHERE out_seq = ? ";
		return (ChatOut)this.jdbcTemplate.queryForObject(query, new Object[] { out_seq }, this.chatoutMapper);
	}

	public List<ChatOut> getChatOutList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" chat_room_seq, block_id, reg_date, out_seq " +
				"FROM T_NF_CHAT_OUT " +
				"WHERE out_seq <= ( " +
				"	SELECT MIN(out_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" out_seq " +
				"		FROM T_NF_CHAT_OUT " +
				"		ORDER BY out_seq DESC " +
				"	) AS A) " +
				"ORDER BY out_seq DESC";
		return (List<ChatOut>)this.jdbcTemplate.query(query, this.chatoutMapper);
	}
	
}
