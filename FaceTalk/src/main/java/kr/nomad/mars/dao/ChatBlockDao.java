package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.ChatBlock;

public class ChatBlockDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper chatblockMapper = new RowMapper() {
		public ChatBlock mapRow(ResultSet rs, int rowNum) throws SQLException {
			ChatBlock chatblock = new ChatBlock();
			chatblock.setCbSeq(rs.getInt("cb_seq"));
			chatblock.setCbUserId(rs.getString("cb_user_id"));
			chatblock.setBlUserId(rs.getString("bl_user_id"));
			chatblock.setRegDate(rs.getString("reg_date"));
			return chatblock;
		}
	};
	private RowMapper VchatblockMapper = new RowMapper() {
		public ChatBlock mapRow(ResultSet rs, int rowNum) throws SQLException {
			ChatBlock chatblock = new ChatBlock();
			chatblock.setCbSeq(rs.getInt("cb_seq"));
			chatblock.setCbUserId(rs.getString("cb_user_id"));
			chatblock.setBlUserId(rs.getString("bl_user_id"));
			chatblock.setRegDate(rs.getString("reg_date"));
			chatblock.setUserName(rs.getString("user_name"));
			chatblock.setPhoto(rs.getString("photo"));
			chatblock.setGender(rs.getInt("gender"));
			return chatblock;
		}
	};
	//차단추가 
	public void addChatBlock(final ChatBlock chatblock) {
		String query = "" +
				"INSERT INTO T_NF_CHAT_BLOCK " +
				"	(cb_user_id, bl_user_id, reg_date) " +
				"VALUES " +
				"	(?, ?, getDate()) ";
		this.jdbcTemplate.update(query, new Object[] {
			
			chatblock.getCbUserId(),
			chatblock.getBlUserId()
			
		});
	}

	public void deleteChatBlock(int cb_seq) {
		String query = "DELETE FROM T_NF_CHAT_BLOCK WHERE cb_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { cb_seq });
	}

	public void updateChatBlock(ChatBlock chatblock) { 
		String query = "" + 
				"UPDATE T_NF_CHAT_BLOCK SET " +
				"	cb_seq = ?, " +
				"	cb_user_id = ?, " +
				"	bl_user_id = ?, " +
				"	reg_date = ? " +
				"WHERE cb_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			chatblock.getCbSeq(),
			chatblock.getCbUserId(),
			chatblock.getBlUserId(),
			chatblock.getRegDate()
		});
	}

	public ChatBlock getChatBlock(int cb_seq) {
		String query = "" + 
				"SELECT cb_seq, cb_user_id, bl_user_id, reg_date " +
				"FROM T_NF_CHAT_BLOCK " +
				"WHERE cb_seq = ? ";
		return (ChatBlock)this.jdbcTemplate.queryForObject(query, new Object[] { cb_seq }, this.chatblockMapper);
	}

	public List<ChatBlock> getChatBlockList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" cb_seq, cb_user_id, bl_user_id, reg_date " +
				"FROM T_NF_CHAT_BLOCK " +
				"WHERE cb_seq <= ( " +
				"	SELECT MIN(cb_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" cb_seq " +
				"		FROM T_NF_CHAT_BLOCK " +
				"		ORDER BY cb_seq DESC " +
				"	) AS A) " +
				"ORDER BY cb_seq DESC";
		return (List<ChatBlock>)this.jdbcTemplate.query(query, this.chatblockMapper);
	}
	//차단리스트 
	public List<ChatBlock> getBlockList(int page, int itemCountPerPage,String id) {
		String query = ""
				+ "	SELECT * FROM ( "
				+ "		SELECT "
				+ "			ROW_NUMBER() OVER(ORDER BY reg_date DESC) as row_seq ,* "
				+ "			 from V_NF_CHAT_BLOCK "
				+ "			where cb_user_id = ?"
				+ "      )AS A"
		        + "	WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
				
		try{
			return (List<ChatBlock>)this.jdbcTemplate.query(query, new Object[] { id },this.VchatblockMapper);
		}catch(Exception e){
			return null;
		}
	}
	
	//차단리스트 갯수
	public int getBlockCount(String id) {
		String query = ""
				+ "	SELECT count(*) FROM  "
			
				+ "			 V_NF_CHAT_BLOCK "
				+ "			where cb_user_id = ?";
			
		      
		try{
			return this.jdbcTemplate.queryForInt(query, new Object[] { id });
		}catch(Exception e){
			return 0;
		}
	}
	
	//차단 삭제
	public void deleteBlock(String id,String bid) {
		String query = "DELETE FROM T_NF_CHAT_BLOCK WHERE cb_user_id = ? and bl_user_id =? ";
		this.jdbcTemplate.update(query, new Object[] { id,bid });
	}
	
	//탈퇴시 차단기록 삭제
	public void deleteBlock(String id) {
		String query = "DELETE FROM T_NF_CHAT_BLOCK WHERE cb_user_id = ? or bl_user_id =? ";
		this.jdbcTemplate.update(query, new Object[] { id,id });
	}
	
	//내가차단리스트 
	public int getBlockmechk(String userId ,String visitId) {
		String query = ""
				+ "		SELECT "
				+ "		  count(*) "
				+ "		from T_NF_CHAT_BLOCK "
				+ "			where cb_user_id = ? and bl_user_id = ?";
				
		try{
			return this.jdbcTemplate.queryForInt(query, new Object[] { userId,visitId });
		}catch(Exception e){
			return 0;
		}
	}
	
	//남차단리스트 
	public int getBlockotherchk(String userId ,String visitId) {
		String query = ""
				+ "		SELECT "
				+ "		  count(*) "
				+ "		from T_NF_CHAT_BLOCK "
				+ "			where cb_user_id = ? and bl_user_id = ? ";
				
		try{
			return this.jdbcTemplate.queryForInt(query, new Object[] {visitId,userId });
		}catch(Exception e){
			return 0;
		}
	}
}
