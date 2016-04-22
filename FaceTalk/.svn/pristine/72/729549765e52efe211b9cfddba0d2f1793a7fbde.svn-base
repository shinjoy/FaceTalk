package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Event;
import kr.nomad.mars.dto.Notice;

public class EventDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper eventMapper = new RowMapper() {
		public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
			Event event = new Event();
			event.setNoticeSeq(rs.getInt("notice_seq"));
			event.setUserId(rs.getString("user_id"));
			event.setNotiType(rs.getString("noti_type"));
			event.setSendPush(rs.getInt("send_push"));
			event.setTitle(rs.getString("title"));
			event.setContentsHtml(rs.getString("contents_html"));
			event.setContentsText(rs.getString("contents_text"));
			event.setLinkUrl(rs.getString("link_url"));
			event.setStartDate(rs.getString("start_date"));
			event.setEndDate(rs.getString("end_date"));
			event.setVisible(rs.getInt("visible"));
			event.setFiles(rs.getString("files"));
			event.setViewCount(rs.getInt("view_count"));
			event.setRegDate(rs.getString("reg_date"));
			event.setSite(rs.getString("site"));
			return event;
		}
	};
	
	private RowMapper VeventMapper = new RowMapper() {
		public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
			Event event = new Event();
			event.setNoticeSeq(rs.getInt("notice_seq"));
			event.setUserId(rs.getString("user_id"));
			event.setNotiType(rs.getString("noti_type"));
			event.setSendPush(rs.getInt("send_push"));
			event.setTitle(rs.getString("title"));
			event.setContentsHtml(rs.getString("contents_html"));
			event.setContentsText(rs.getString("contents_text"));
			event.setLinkUrl(rs.getString("link_url"));
			event.setStartDate(rs.getString("start_date"));
			event.setEndDate(rs.getString("end_date"));
			event.setVisible(rs.getInt("visible"));
			event.setFiles(rs.getString("files"));
			event.setViewCount(rs.getInt("view_count"));
			event.setRegDate(rs.getString("reg_date"));
			event.setSite(rs.getString("site"));
			event.setSiteName(rs.getString("site_name"));
			return event;
		}
	};

	public void addEvent(final Event event) {
		String query = "" +
				"INSERT INTO T_NF_EVENT " +
				"	(user_id, noti_type, send_push,"
				+ " title, contents_html, contents_text,"
				+ " link_url, start_date, end_date,"
				+ " visible, files, view_count,"
				+ " reg_date,site) " +
				"VALUES " +
				"	(?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " getDate(),?) ";
		this.jdbcTemplate.update(query, new Object[] {
			event.getUserId(),
			event.getNotiType(),
			event.getSendPush(),
			event.getTitle(),
			event.getContentsHtml(),
			event.getContentsText(),
			event.getLinkUrl(),
			event.getStartDate(),
			event.getEndDate(),
			event.getVisible(),
			event.getFiles(),
			event.getViewCount(),
			event.getSite()

		});
	}

	public void deleteEvent(int notice_seq) {
		String query = "DELETE FROM T_NF_EVENT WHERE notice_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { notice_seq });
	}

	public void updateEvent(Event event) { 
		String query = "" + 
				"UPDATE T_NF_EVENT SET " +
				"	user_id = ?, " +
				"	noti_type = ?, " +
				"	send_push = ?, " +
				"	title = ?, " +
				"	contents_html = ?, " +
				"	contents_text = ?, " +
				"	link_url = ?, " +
				"	start_date = ?, " +
				"	end_date = ?, " +
				"	visible = ?, " +
				"	files = ?, " +
				"	view_count = ?, " +
				"	site = ?, " +
				"	reg_date = ? " +
				"WHERE notice_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			event.getUserId(),
			event.getNotiType(),
			event.getSendPush(),
			event.getTitle(),
			event.getContentsHtml(),
			event.getContentsText(),
			event.getLinkUrl(),
			event.getStartDate(),
			event.getEndDate(),
			event.getVisible(),
			event.getFiles(),
			event.getViewCount(),
			event.getSite(),
			event.getRegDate(),
			event.getNoticeSeq()
		});
	}

	public Event getEvent(int notice_seq) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_EVENT " +
				"WHERE notice_seq = ? ";
		try {
			return (Event)this.jdbcTemplate.queryForObject(query, new Object[] { notice_seq }, this.eventMapper);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new Event();
		}
		
	}

	// 공지사항 리스트 
	
	public List<Event> getEventList(String site,int userType,int page, int itemCountPerPage) {
		
		List<Event> result = null;

		String con=" where 1=1 ";
		if(userType!=1){
			con += " AND site = '"+ site +"' ";
		}
		String query = ""
				+ "	SELECT * FROM ( "
				+ "	SELECT "
				+ "		ROW_NUMBER() OVER(ORDER BY type asc,start_date desc) AS row_seq, "
				+ "		* "
				+ "	FROM V_NF_EVENT "+con
				+ ") AS row WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") + 1 AND "+page+" * "+itemCountPerPage+" ";
				
		result = (List<Event>)this.jdbcTemplate.query(query, this.VeventMapper);
		
		return result;
	}
	
	public List<Event> getEventListMobile(String site,int page, int itemCountPerPage) {
		String con=" ";
		List<Event> result = null;
		if(!site.equals("")){
			con += " AND site = '"+ site +"' ";
		}
		String query = ""
				+ "	SELECT * FROM ( "
				+ "	SELECT "
				+ "		ROW_NUMBER() OVER(ORDER BY start_date desc ) AS row_seq, "
				+ "		* "
				+ "	FROM T_NF_EVENT "
				+ "  where  0 <= DATEDIFF(DD,start_date , getDate())   and "
				+ "		DATEDIFF(DD,end_date , getDate()) <= 0 "+con
				+ ") AS row WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") + 1 AND "+page+" * "+itemCountPerPage+" ";
		
		result = (List<Event>)this.jdbcTemplate.query(query, this.eventMapper);
		
		return result;
	}
	
	
	
	public int getCount() {
		int result = 0;		
		String query = "SELECT COUNT(*) AS cnt FROM V_NF_EVENT ";
		result = this.jdbcTemplate.queryForInt(query);
				
		return result;
	}
	
	public int getMobileCount(String site) {
		String con=" ";
		if(!site.equals("")){
			con += " AND site = '"+ site +"' ";
		}
		int result = 0;		
		String query = "SELECT COUNT(*) AS cnt FROM T_NF_EVENT "
				+" where (0 <= DATEDIFF(DD, start_date, getDate())) and "
				+ "		(DATEDIFF(DD,end_date , getDate()) <= 0) "+con;
		result = this.jdbcTemplate.queryForInt(query);
				
		return result;
	}
	
	// 공지사항 키워드 검색
	
	public List<Event> getEventList(String site,int userType,String keyword, int page, int itemCountPerPage) {
		String condition=" where 1=1 ";
		if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}
		if(!keyword.equals("")){
			condition += " AND title like '%"+ keyword +"%' OR contents_text like '%" + keyword + "%' ";
		}
		String query = ""
				+ "    SELECT * FROM ( "
				+ "        SELECT "
				+ "            ROW_NUMBER() OVER(order by reg_date desc) as row_seq, "
				+ "            * "
				+ "        FROM V_NF_EVENT "
				+ "     "+condition
				+ "    ) AS a WHERE row_seq BETWEEN ((" + page + " - 1) * "
				+ itemCountPerPage + ") +1 AND " + page + " * "
				+ itemCountPerPage + " ";
		return (List<Event>) this.jdbcTemplate.query(query, this.VeventMapper);
	}

	public int getCount(String site,int userType,String keyword) {
		String condition=" where 1=1 ";
		if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}
		if(!keyword.equals("")){
			condition +=" AND (title like '%"+ keyword +"%' OR contents_text like '%" + keyword + "%') ";
		}
		String query = " SELECT COUNT(*) FROM V_NF_EVENT  "+condition;
		return this.jdbcTemplate.queryForInt(query);
	}
	
	
	public int getLastSeq() {
		String query = " SELECT MAX(notice_seq) FROM T_NF_EVENT ";
		return this.jdbcTemplate.queryForInt(query);
	}
	
	
	//모바일웹 
	
	
	
}
