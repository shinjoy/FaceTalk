package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Event;
import kr.nomad.mars.dto.Faq;

public class FaqDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper faqMapper = new RowMapper() {
		public Faq mapRow(ResultSet rs, int rowNum) throws SQLException {
			Faq faq = new Faq();
			faq.setNoticeSeq(rs.getInt("notice_seq"));
			faq.setUserId(rs.getString("user_id"));
			faq.setNotiType(rs.getString("noti_type"));
			faq.setSendPush(rs.getInt("send_push"));
			faq.setTitle(rs.getString("title"));
			faq.setContentsHtml(rs.getString("contents_html"));
			faq.setContentsText(rs.getString("contents_text"));
			faq.setLinkUrl(rs.getString("link_url"));
			faq.setStartDate(rs.getString("start_date"));
			faq.setEndDate(rs.getString("end_date"));
			faq.setVisible(rs.getInt("visible"));
			faq.setFiles(rs.getString("files"));
			faq.setViewCount(rs.getInt("view_count"));
			faq.setRegDate(rs.getString("reg_date"));
			faq.setSite(rs.getString("site"));
			return faq;
		}
	};
	private RowMapper VfaqMapper = new RowMapper() {
		public Faq mapRow(ResultSet rs, int rowNum) throws SQLException {
			Faq faq = new Faq();
			faq.setNoticeSeq(rs.getInt("notice_seq"));
			faq.setUserId(rs.getString("user_id"));
			faq.setNotiType(rs.getString("noti_type"));
			faq.setSendPush(rs.getInt("send_push"));
			faq.setTitle(rs.getString("title"));
			faq.setContentsHtml(rs.getString("contents_html"));
			faq.setContentsText(rs.getString("contents_text"));
			faq.setLinkUrl(rs.getString("link_url"));
			faq.setStartDate(rs.getString("start_date"));
			faq.setEndDate(rs.getString("end_date"));
			faq.setVisible(rs.getInt("visible"));
			faq.setFiles(rs.getString("files"));
			faq.setViewCount(rs.getInt("view_count"));
			faq.setRegDate(rs.getString("reg_date"));
			faq.setSite(rs.getString("site"));
			faq.setSiteName(rs.getString("site_name"));
			return faq;
		}
	};
	public void addFaq(final Faq faq) {
		String query = "" +
				"INSERT INTO T_NF_FAQ " +
				"	( user_id, noti_type, send_push,"
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
			faq.getUserId(),
			faq.getNotiType(),
			faq.getSendPush(),
			faq.getTitle(),
			faq.getContentsHtml(),
			faq.getContentsText(),
			faq.getLinkUrl(),
			faq.getStartDate(),
			faq.getEndDate(),
			faq.getVisible(),
			faq.getFiles(),
			faq.getViewCount(),
			faq.getSite()
		});
	}

	public void deleteFaq(int notice_seq) {
		String query = "DELETE FROM T_NF_FAQ WHERE notice_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { notice_seq });
	}

	public void updateFaq(Faq faq) { 
		String query = "" + 
				"UPDATE T_NF_FAQ SET " +
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
			faq.getUserId(),
			faq.getNotiType(),
			faq.getSendPush(),
			faq.getTitle(),
			faq.getContentsHtml(),
			faq.getContentsText(),
			faq.getLinkUrl(),
			faq.getStartDate(),
			faq.getEndDate(),
			faq.getVisible(),
			faq.getFiles(),
			faq.getViewCount(),
			faq.getSite(),
			faq.getRegDate(),
			faq.getNoticeSeq()
		});
	}

	public Faq getFaq(int notice_seq) {
		String query = "" + 
				"SELECT * " +
				"FROM V_NF_FAQ " +
				"WHERE notice_seq = ? ";
		try {
			return (Faq)this.jdbcTemplate.queryForObject(query, new Object[] { notice_seq }, this.VfaqMapper);
		} catch (Exception e) {
			return new Faq();
		}
		
	}
	// 공지사항 리스트 
	
	public List<Faq> getFaqList(int page, int itemCountPerPage) {
		
		List<Faq> result = null;

		String query = ""
				+ "	SELECT * FROM ( "
				+ "	SELECT "
				+ "		ROW_NUMBER() OVER(ORDER BY notice_seq desc ) AS row_seq, "
				+ "		* "
				+ "	FROM T_NF_FAQ "
				+ ") AS row WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") + 1 AND "+page+" * "+itemCountPerPage+" ";
				
		result = (List<Faq>)this.jdbcTemplate.query(query, this.faqMapper);
		
		return result;
	}
	
	
	public int getCount() {
		int result = 0;		
		String query = "SELECT COUNT(*) AS cnt FROM T_NF_FAQ ";
		result = this.jdbcTemplate.queryForInt(query);
				
		return result;
	}
	
	// 공지사항 키워드 검색
	
	public List<Faq> getFaqList(String site,int userType,String keyword, int page, int itemCountPerPage) {
		String con=" where 1=1 ";
		if(userType!=1){
			con += " AND site = '"+ site +"' ";
		}
		if(!keyword.equals("")){
			con+=" and (title like '%"+keyword+"%' OR contents_text like '%"+keyword+"%')";
		}
		String query = ""
				+ "    SELECT * FROM ( "
				+ "        SELECT "
				+ "            ROW_NUMBER() OVER(ORDER BY notice_seq DESC) as row_seq, "
				+ "            * "
				+ "        FROM V_NF_FAQ "
				+ "     "+con
				+ "    ) AS a WHERE row_seq BETWEEN ((" + page + " - 1) * "
				+ itemCountPerPage + ") +1 AND " + page + " * "
				+ itemCountPerPage + " ";
		return (List<Faq>) this.jdbcTemplate.query(query, this.VfaqMapper);
	}

	public int getCount(String site,int userType,String keyword) {
		String con=" where 1=1 ";
		if(userType!=1){
			con += " AND site = '"+ site +"' ";
		}
		if(!keyword.equals("")){
			con+=" and (title like '%"+keyword+"%' OR contents_text like '%"+keyword+"%')";
		}
		String query = " SELECT COUNT(*) FROM V_NF_FAQ  "+con;
		return this.jdbcTemplate.queryForInt(query);
	}
	
	
	public int getLastSeq() {
		String query = " SELECT MAX(notice_seq) FROM T_NF_FAQ ";
		return this.jdbcTemplate.queryForInt(query);
	}
}
