package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Friend;

public class FriendDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper friendMapper = new RowMapper() {
		public Friend mapRow(ResultSet rs, int rowNum) throws SQLException {
			Friend friend = new Friend();
			friend.setFriendSeq(rs.getInt("friend_seq"));
			friend.setUserId(rs.getString("user_id"));
			friend.setFriendId(rs.getString("friend_id"));
			friend.setRegDate(rs.getString("reg_date"));
			friend.setGender(rs.getInt("gender"));
			friend.setBirthYear(rs.getInt("birth_year"));
			friend.setArea(rs.getString("area"));
			friend.setUserName(rs.getString("user_name"));
			return friend;
		}
	};
	
	private RowMapper friendMapper2 = new RowMapper() {
		public Friend mapRow(ResultSet rs, int rowNum) throws SQLException {
			Friend friend = new Friend();
		
			friend.setUserId(rs.getString("user_id"));
			friend.setFriendId(rs.getString("friend_id"));
		
			return friend;
		}
	};

	//친구삭제
	public void deleteFriend(String friendId,String userId) {
		String query = "DELETE FROM T_NF_FRIEND WHERE( friend_id = ? and user_id = ? ) or ( friend_id = ? and user_id = ? )";
		this.jdbcTemplate.update(query, new Object[] { friendId,userId,userId,friendId });
	}
	
	//탈퇴시 정보 삭제
	public void deleteFriend(String userId) {
		String query = "DELETE FROM T_NF_FRIEND WHERE friend_id = ? or user_id = ?";
		try{
			this.jdbcTemplate.update(query, new Object[] { userId,userId});
		}catch(Exception e){
			
		}
	}
	
	public void addFriend(final Friend friend) {
		String query = "" +
				"INSERT INTO T_NF_FRIEND " +
				"	(user_id, friend_id, reg_date) " +
				"VALUES " +
				"	(?, ?, getDate()) ";
		this.jdbcTemplate.update(query, new Object[] {
			friend.getUserId(),
			friend.getFriendId()
		});
	}


	public void updateFriend(Friend friend) { 
		String query = "" + 
				"UPDATE T_NF_FRIEND SET " +
				"	friend_seq = ?, " +
				"	user_id = ?, " +
				"	friend_id = ?, " +
				"	reg_date = ? " +
				"WHERE friend_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			friend.getFriendSeq(),
			friend.getUserId(),
			friend.getFriendId(),
			friend.getRegDate()
		});
	}

	public Friend getFriend(String userId, String keyword) {
		String query = "" + 
				"SELECT * " +
				"FROM V_NF_FRIEND " +
				"WHERE user_id = ? AND friend_id = ? ";
		try {
			return (Friend)this.jdbcTemplate.queryForObject(query, new Object[] { userId, keyword }, this.friendMapper);
		} catch (Exception e) {
			return null;
		}
	}
	
	public Friend getFr(String userId, String dId) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_FRIEND " +
				"WHERE user_id = ? AND friend_id = ?";
		try{
			return (Friend)this.jdbcTemplate.queryForObject(query, new Object[] {userId,dId}, this.friendMapper2);
		}catch(Exception e){
			return null;
		}
	}
	
	public int getFrcorrect(String userId, String dId) {
		String query = "" + 
				"SELECT count(*) " +
				"FROM T_NF_FRIEND " +
				"WHERE user_id = ? AND friend_id = ?";
		try{
			return this.jdbcTemplate.queryForInt(query,new Object[] {userId,dId});
		}catch(Exception e){
			return 0;
		}
	}

	public List<Friend> getFriendList(String userId, int page, int itemCountPerPage) {
		String query = "" 
			 	+ "	SELECT * FROM ( "
	            + "		SELECT "
	            + "			ROW_NUMBER() OVER(ORDER BY user_id DESC) as row_seq, "
	            + "			* "
	            + "		FROM V_NF_FRIEND "
	            + "		WHERE user_id = ? "
	            + "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		return (List<Friend>)this.jdbcTemplate.query(query, new Object[] { userId }, this.friendMapper);
	}
	public int getCount(String userId) {
		String query = " SELECT COUNT(*) FROM T_NF_FRIEND WHERE user_id = ? ";
		return this.jdbcTemplate.queryForInt(query, new Object[] { userId });
	}
	
	//친구검색 키워드 
	public List<Friend> getFriendList(String userId, String keyword, int page, int itemCountPerPage) {
		String query = "" 
			 	+ "	SELECT * FROM ( "
	            + "		SELECT "
	            + "			ROW_NUMBER() OVER(ORDER BY user_id DESC) as row_seq, "
	            + "			* "
	            + "		FROM V_NF_FRIEND "
	            + "		WHERE user_id = ? AND friend_id like ? "
	            + "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		return (List<Friend>)this.jdbcTemplate.query(query, new Object[] { userId, "%"+keyword+"%" }, this.friendMapper);
	}
	
/*	public List<Friend> getFriendList(String userId) {
		String query = "" 
			 	+ "	SELECT * "
	            + "	FROM V_NF_FRIEND "
	            + "		WHERE user_id = ? ";
		return (List<Friend>)this.jdbcTemplate.query(query, new Object[] { userId}, this.friendMapper);
	}
	*/
	public int getCount(String userId, String keyword) {
		String query = " SELECT COUNT(*) FROM T_NF_FRIEND WHERE user_id = ? AND friend_id like ? ";
		return this.jdbcTemplate.queryForInt(query, new Object[] { userId, "%"+keyword+"%" });
	}

	
	/**
	 * 친구 목록 카운트
	 * @param keyword
	 * @return
	 */
	public int getFriendCount(String userId ,String keyword) {
	    String query = " SELECT COUNT(*) FROM T_NF_Friend WHERE user_id = ? AND friend_id like ? ";
	    return this.jdbcTemplate.queryForInt(query, new Object[] { userId ,"%"+keyword+"%"});
	}
}
