package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.GuestBook;

public class GuestBookDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper guestbookMapper = new RowMapper() {
		public GuestBook mapRow(ResultSet rs, int rowNum) throws SQLException {
			GuestBook guestbook = new GuestBook();
			guestbook.setBookSeq(rs.getInt("book_seq"));
			guestbook.setUserId(rs.getString("user_id"));
			guestbook.setGuestId(rs.getString("guest_id"));
			guestbook.setContents(rs.getString("contents"));
			guestbook.setRegDate(rs.getString("reg_date"));
			guestbook.setUserName(rs.getString("user_name"));
			guestbook.setPhoto(rs.getString("photo"));
			guestbook.setGender(rs.getInt("gender"));
			return guestbook;
		}
	};
	private RowMapper guestbookMapper2 = new RowMapper() {
		public GuestBook mapRow(ResultSet rs, int rowNum) throws SQLException {
			GuestBook guestbook = new GuestBook();
			guestbook.setBookSeq(rs.getInt("book_seq"));
			guestbook.setUserId(rs.getString("user_id"));
			guestbook.setGuestId(rs.getString("guest_id"));
			guestbook.setContents(rs.getString("contents"));
			guestbook.setRegDate(rs.getString("reg_date"));
			
			return guestbook;
		}
	};

	public int addGuestBook(final GuestBook guestbook) {
		String query = "" +
				"INSERT INTO T_NF_GUESTBOOK " +
				"	( user_id, guest_id, contents, reg_date) " +
				"VALUES " +
				"	(?, ?, ?, getDate()) "
			  +"	SELECT SCOPE_IDENTITY() AS book_seq ";
		
		return this.jdbcTemplate.queryForInt(query, new Object[] {
			
			guestbook.getUserId(),
			guestbook.getGuestId(),
			guestbook.getContents()
		});
	}

	public void deleteGuestBook(int book_seq) {
		String query = "DELETE FROM T_NF_GUESTBOOK WHERE book_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { book_seq });
	}
	
	//탈퇴시 데이터삭제
	public void deleteGuestBook(String userId) {
		String query = "DELETE FROM T_NF_GUESTBOOK WHERE user_id = ? or guest_id = ?";
		try{
			this.jdbcTemplate.update(query, new Object[] { userId,userId });
		}catch(Exception e){
			
		}
	}

	public void updateGuestBook(GuestBook guestbook) { 
		String query = "" + 
				"UPDATE T_NF_GUESTBOOK SET " +
				"	contents = ? " +
				"WHERE book_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			guestbook.getContents(),
			guestbook.getBookSeq()
		});
	}

	public GuestBook getGuestBook(int book_seq) {
		String query = "" + 
				"SELECT book_seq, user_id, guest_id, contents, reg_date " +
				"FROM V_NF_GESTBOOK " +
				"WHERE book_seq = ? ";
		try{
			return (GuestBook)this.jdbcTemplate.queryForObject(query, new Object[] { book_seq }, this.guestbookMapper2);
		}catch(Exception e){
			return null;
		}
	}

	public List<GuestBook> getGuestBookList(String userId, int page, int itemCountPerPage) {
		String query = ""
	            + "	SELECT * FROM ( "
	            + "		SELECT "
	            + "			ROW_NUMBER() OVER(ORDER BY reg_date DESC) as row_seq, "
	            + "			* "
	            + "		FROM V_NF_GESTBOOK "
	            + "		WHERE user_id = ? "
	    		+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		try {
			return (List<GuestBook>)this.jdbcTemplate.query(query, new Object[] {userId}, this.guestbookMapper);
		} catch (Exception e) {
			return null;
		}
		
	}


	public int getCount(String userId) {
		int result = 0;
		String query = "SELECT COUNT(*) AS cnt FROM V_NF_GESTBOOK WHERE user_id = ?";
		result = this.jdbcTemplate.queryForInt(query, new Object[] {userId});
		return result;
	}








}
