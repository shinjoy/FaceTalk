package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Notice;

public class NoticeDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper noticeMapper = new RowMapper() {
		public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
			Notice notice = new Notice();
			notice.setNoticeSeq(rs.getInt("notice_seq"));
			notice.setUserId(rs.getString("user_id"));
			notice.setNotiType(rs.getInt("noti_type"));
			notice.setSendPush(rs.getInt("send_push"));
			notice.setTitle(rs.getString("title"));
			notice.setContentsHtml(rs.getString("contents_html"));
			notice.setContentsText(rs.getString("contents_text"));
			notice.setLinkUrl(rs.getString("link_url"));
			notice.setStartDate(rs.getString("start_date"));
			notice.setEndDate(rs.getString("end_date"));
			notice.setVisible(rs.getInt("visible"));
			notice.setFiles(rs.getString("files"));
			notice.setViewCount(rs.getInt("view_count"));
			notice.setRegDate(rs.getString("reg_date"));
			notice.setSort(rs.getInt("sort"));
			notice.setSite(rs.getString("site"));
			return notice;
		}
	};
	private RowMapper VnoticeMapper = new RowMapper() {
		public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
			Notice notice = new Notice();
			notice.setNoticeSeq(rs.getInt("notice_seq"));
			notice.setUserId(rs.getString("user_id"));
			notice.setNotiType(rs.getInt("noti_type"));
			notice.setSendPush(rs.getInt("send_push"));
			notice.setTitle(rs.getString("title"));
			notice.setContentsHtml(rs.getString("contents_html"));
			notice.setContentsText(rs.getString("contents_text"));
			notice.setLinkUrl(rs.getString("link_url"));
			notice.setStartDate(rs.getString("start_date"));
			notice.setEndDate(rs.getString("end_date"));
			notice.setVisible(rs.getInt("visible"));
			notice.setFiles(rs.getString("files"));
			notice.setViewCount(rs.getInt("view_count"));
			notice.setRegDate(rs.getString("reg_date"));
			notice.setSort(rs.getInt("sort"));
			notice.setType(rs.getInt("type"));
			notice.setSite(rs.getString("site"));
			notice.setSiteName(rs.getString("site_name"));
			return notice;
		}
	};

	public void addNotice(final Notice notice) {
		String query = "" +
				"INSERT INTO T_NF_NOTICE " +
				"	(user_id, noti_type, send_push,"
				+ " title, contents_html, contents_text,"
				+ " link_url, start_date, end_date,"
				+ " visible, files, view_count,"
				+ " reg_date,sort,site) " +
				"VALUES " +
				"	(?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " getDate(),?,?) ";
		this.jdbcTemplate.update(query, new Object[] {
			notice.getUserId(),
			notice.getNotiType(),
			notice.getSendPush(),
			notice.getTitle(),
			notice.getContentsHtml(),
			notice.getContentsText(),
			notice.getLinkUrl(),
			notice.getStartDate(),
			notice.getEndDate(),
			notice.getVisible(),
			notice.getFiles(),
			notice.getViewCount(),
			notice.getSort(),
			notice.getSite()
		});
	}

	public void deleteNotice(int notice_seq) {
		String query = "DELETE FROM T_NF_NOTICE WHERE notice_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { notice_seq });
	}

	public void updateNotice(Notice notice) { 
		String query = "" + 
				"UPDATE T_NF_NOTICE SET " +
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
				"	reg_date = ?, " +
				"	site = ? " +
				"WHERE notice_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			notice.getUserId(),
			notice.getNotiType(),
			notice.getSendPush(),
			notice.getTitle(),
			notice.getContentsHtml(),
			notice.getContentsText(),
			notice.getLinkUrl(),
			notice.getStartDate(),
			notice.getEndDate(),
			notice.getVisible(),
			notice.getFiles(),
			notice.getViewCount(),
			notice.getRegDate(),
			notice.getSite(),
			notice.getNoticeSeq()
		
		});
	}

	public Notice getNotice(int notice_seq) {
		String query = "" + 
				" SELECT * " +
				" FROM T_NF_NOTICE " +
				" WHERE notice_seq = ? ";
		try {
			return (Notice)this.jdbcTemplate.queryForObject(query, new Object[] { notice_seq }, this.noticeMapper);
			
		} catch (Exception e) {
			return new Notice();
		}
		
	}

	
	// 공지사항 리스트 
	
	public List<Notice> getNoticeMobileList(String site,int userType,int page, int itemCountPerPage) {
		List<Notice> result = null;
		String condition=" ";
		if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}

		String query = ""
				+ "    SELECT * FROM ( "
				+ "        SELECT "
				+ "            ROW_NUMBER() OVER(ORDER BY noti_type desc,reg_date desc) as row_seq, "
				+ "            * "
				+ "        FROM T_NF_NOTICE "
				+ "        WHERE (noti_type = 1 OR noti_type= 0) "
				+ "        AND "
				+ "		0 <= DATEDIFF(DD,start_date , getDate())   and "
				+ "		DATEDIFF(DD,end_date , getDate()) <= 0 "
				+ "       "+condition
				+ "    ) AS a WHERE row_seq BETWEEN ((" + page + " - 1) * "
				+ itemCountPerPage + ") +1 AND " + page + " * "
				+ itemCountPerPage + " ";
				
		result = (List<Notice>)this.jdbcTemplate.query(query, this.noticeMapper);
		
		return result;
	}
	
	
	public int getNoticeMobileCount(String site,int userType) {
		String condition=" ";
		if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}
		int result = 0;		
		String query = "SELECT COUNT(*) AS cnt FROM T_NF_NOTICE  "
				+ "WHERE noti_type = 0 OR noti_type = 1"
				+ "        AND "
				+ "		0 <= DATEDIFF(DD,start_date , getDate())   and "
				+ "		DATEDIFF(DD,end_date , getDate()) <= 0 "+condition;
		result = this.jdbcTemplate.queryForInt(query);
				
		return result;
	}
	
	// 공지사항 키워드 검색
	
	public List<Notice> getNoticeMainList(String site,int userType,String keyword, int page, int itemCountPerPage) {
		String con ="";
		
		if(!keyword.equals("")){
			con += " and title like '%"+keyword+"%' OR contents_text like '%"+keyword+"%' ";
		}
		if(userType!=1){
			con += " AND site = '"+ site +"' ";
		}
		String query = ""
				+ "    SELECT * FROM ( "
				+ "        SELECT "
				+ "            ROW_NUMBER() OVER(ORDER BY type asc,reg_date desc) as row_seq, "
				+ "            * "
				+ "        FROM V_NF_NOTICE "
				+ "        WHERE (noti_type = 1 OR noti_type= 0) "
				+ con 
				+ "    ) AS a WHERE row_seq BETWEEN ((" + page + " - 1) * "
				+ itemCountPerPage + ") +1 AND " + page + " * "
				+ itemCountPerPage + " ";
		return (List<Notice>) this.jdbcTemplate.query(query, this.VnoticeMapper);
	}

	public int getNoticeMainCount(String site,int userType,String keyword) {
		String con ="";
		
		if(!keyword.equals("")){
			con += " and title like '%"+keyword+"%' OR contents_text like '%"+keyword+"%' ";
		}
		if(userType!=1){
			con += " AND site = '"+ site +"' ";
		}
		String query = " SELECT COUNT(*) FROM V_NF_NOTICE WHERE"
				+ " (noti_type = 1 OR noti_type=0 ) "
				+con;
		return this.jdbcTemplate.queryForInt(query);
	}
	
	
	
	// 메인 팝업공지사항
	public List<Notice> getNoticeTopList() {
		
		List<Notice> result = null;
		String query = ""
				+ "	SELECT top  * "
				+ "	FROM T_NF_NOTICE WHERE noti_type = 10 "
				+ " ORDER BY notice_seq desc ";
		result = (List<Notice>)this.jdbcTemplate.query(query, this.noticeMapper);
		return result;
	}

	// 메인 팝업공지사항
	public List<Notice> getPopupTopList(String site) {
		
		List<Notice> result = null;
		String con=" ";
		if(!site.equals("")){
			con +=" AND site = '"+ site +"' ";
		}
		String query = ""
				+ "	SELECT * "
				+ "	FROM T_NF_NOTICE "
				+ " WHERE "
				+ "		noti_type = 10 AND visible = 1 "
				+ "		AND convert(nvarchar(10), start_date, 120) <= convert(nvarchar(10), getDate(), 120) "
				+ "		AND convert(nvarchar(10), end_date, 120) >= convert(nvarchar(10), getDate(), 120) "
				+ " "+con
				+ " ORDER BY notice_seq desc ";
		result = (List<Notice>)this.jdbcTemplate.query(query, this.noticeMapper);
		return result;
	}
	
	// 메인 이용안내사항
	public List<Notice> getGuideTopList(String site) {
		
		String con=" ";
		if(!site.equals("")){
			con+=" and site= '"+site+"' ";
		}
		
		List<Notice> result = null;
		String query = ""
				+ "	SELECT * "
				+ "	FROM T_NF_NOTICE "
				+ " WHERE "
				+ "		noti_type = 30 AND visible = 1 "
				+ "		AND convert(nvarchar(10), start_date, 120) <= convert(nvarchar(10), getDate(), 120) "
				+ "		AND convert(nvarchar(10), end_date, 120) >= convert(nvarchar(10), getDate(), 120)"
				+ " "+con
				+ " ORDER BY sort desc ";
		result = (List<Notice>)this.jdbcTemplate.query(query, this.noticeMapper);
		return result;
	}

	
	
	
	// 공지사항 리스트 
	
	public List<Notice> getNoticePopUpList(String site,int userType,int page, int itemCountPerPage,int notiType) {
		String condition=" ";
		List<Notice> result = null;
		if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}
		String query = ""
				+ "	SELECT * FROM ( "
				+ "	SELECT "
				+ "		ROW_NUMBER() OVER(ORDER BY notice_seq asc) AS row_seq, "
				+ "		* "
				+ "	FROM V_NF_NOTICE WHERE noti_type = ? "+condition
				+ " ) AS row WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") + 1 AND "+page+" * "+itemCountPerPage+" ";
				
		result = (List<Notice>)this.jdbcTemplate.query(query,new Object[] {notiType}, this.VnoticeMapper);
		
		return result;
	}
	

	public List<Notice> getNoticeMainListsort(String site,int userType,int page, int itemCountPerPage,int notiType) {
		String condition=" ";
		List<Notice> result = null;
		if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}
		String query = ""
				+ "	SELECT * FROM ( "
				+ "	SELECT "
				+ "		ROW_NUMBER() OVER(ORDER BY site asc,sort asc) AS row_seq, "
				+ "		* "
				+ "	FROM V_NF_NOTICE WHERE noti_type = ? "+condition
				+ ") AS row WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") + 1 AND "+page+" * "+itemCountPerPage+" ";
				
		result = (List<Notice>)this.jdbcTemplate.query(query,new Object[] {notiType}, this.VnoticeMapper);
		
		return result;
	}
	
	
	public int getNoticePopUpCount(String site,int userType,int notiType) {
		String condition=" ";
		if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}
		int result = 0;		
		String query = "SELECT COUNT(*) AS cnt FROM V_NF_NOTICE WHERE noti_type = ? "+condition;
		result = this.jdbcTemplate.queryForInt(query,new Object[] {notiType});
				
		return result;
	}

	
	
	// 공지사항 키워드 검색
	
	public List<Notice> getNoticeMainList(String site,int userType,String keyword, int page, int itemCountPerPage,int notiType) {
		String condition=" where 1=1 ";
		if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}
		if(notiType!=0){
			condition += " AND noti_type = "+ notiType +" ";
		}
		if(!keyword.equals("")){
			condition += " AND title like '%"+keyword+"%' OR contents_text like '%"+keyword+"%' ";
		}
		String query = ""
				+ "    SELECT * FROM ( "
				+ "        SELECT "
				+ "            ROW_NUMBER() OVER(ORDER BY notice_seq DESC) as row_seq, "
				+ "            * "
				+ "        FROM V_NF_NOTICE "
				+ "      "+condition
				+ "    ) AS a WHERE row_seq BETWEEN ((" + page + " - 1) * "
				+ itemCountPerPage + ") +1 AND " + page + " * "
				+ itemCountPerPage + " ";
		return (List<Notice>) this.jdbcTemplate.query(query, this.VnoticeMapper);
	}

	public int getNoticeMainCount(String site,int userType,String keyword,int notiType) {
		String condition=" where 1=1 ";
		if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}
		if(notiType!=0){
			condition += " AND noti_type = "+ notiType +" ";
		}
		if(!keyword.equals("")){
			condition += " AND title like '%"+keyword+"%' OR contents_text like '%"+keyword+"%' ";
		}
		String query = " SELECT COUNT(*) FROM V_NF_NOTICE  " +condition;
		return this.jdbcTemplate.queryForInt(query);
	}
	
	
	
	public Notice getUpdateV(int type,String site) {
		String query = "" + 
				" SELECT top 1* " +
				" FROM T_NF_NOTICE " +
				" WHERE send_push = ? and site= ? order by reg_date desc ";
		try {
			return (Notice)this.jdbcTemplate.queryForObject(query, new Object[] { type ,site}, this.noticeMapper);
			
		} catch (Exception e) {
			return new Notice();
		}
		
	}
	
	
	
	
	
	public int getLastSeq() {
		String query = " SELECT MAX(notice_seq) FROM T_NF_NOTICE ";
		return this.jdbcTemplate.queryForInt(query);
	}
	
	public int getLastSort() {
		String query = " SELECT MAX(sort) FROM T_NF_NOTICE ";
		return this.jdbcTemplate.queryForInt(query);
	}
	
	//순서 업데이트
	public void updatesort(int daySeq,int sort) { 
		String query = "" + 
				"UPDATE T_NF_NOTICE SET " +
				
				"	sort = ? " +
			
				"WHERE notice_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {sort,daySeq});
	}
	
	
	
}
