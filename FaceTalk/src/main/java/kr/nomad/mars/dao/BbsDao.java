package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Album;
import kr.nomad.mars.dto.Bbs;
import kr.nomad.mars.dto.BbsFiles;
import kr.nomad.util.T;

public class BbsDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper bbsMapperorg = new RowMapper() {
		public Bbs mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bbs bbs = new Bbs();
			bbs.setBbsSeq(rs.getInt("bbs_seq"));
			bbs.setBbsCategory(rs.getString("bbs_category"));
			bbs.setBbsHeader(rs.getString("bbs_header"));
			bbs.setUserId(rs.getString("user_id"));
			bbs.setBbsTitle(rs.getString("bbs_title"));
			bbs.setBbsContents(rs.getString("bbs_contents"));
			bbs.setBbsContentsText(rs.getString("bbs_contents_text"));
			bbs.setFiles(rs.getString("files"));
			bbs.setLinkUrl(rs.getString("link_url"));
			bbs.setExtraContents(rs.getString("extra_contents"));
			bbs.setViewCount(rs.getInt("view_count"));
			bbs.setLikeCount(rs.getInt("like_count"));
			bbs.setDislikeCount(rs.getInt("dislike_count"));
			bbs.setReportCount(rs.getInt("report_count"));
			bbs.setFileCount(rs.getInt("file_count"));
			bbs.setCommentCount(rs.getInt("comment_count"));
			bbs.setBlindCount(rs.getInt("blind_count"));
			bbs.setRegDate(rs.getString("reg_date"));
			bbs.setShareBbsSeq(rs.getInt("share_bbs_seq"));
			bbs.setShareWriteId(rs.getString("share_write_id"));
			bbs.setShareRegDate(rs.getString("share_reg_date"));
			
			
			return bbs;
		}
	};

	private RowMapper bbsMapper = new RowMapper() {
		public Bbs mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bbs bbs = new Bbs();
			bbs.setBbsSeq(rs.getInt("bbs_seq"));
			bbs.setBbsCategory(rs.getString("bbs_category"));
			bbs.setBbsHeader(rs.getString("bbs_header"));
			bbs.setUserId(rs.getString("user_id"));
			bbs.setBbsTitle(rs.getString("bbs_title"));
			bbs.setBbsContents(rs.getString("bbs_contents"));
			bbs.setBbsContentsText(rs.getString("bbs_contents_text"));
			bbs.setFiles(rs.getString("files"));
			bbs.setLinkUrl(rs.getString("link_url"));
			bbs.setExtraContents(rs.getString("extra_contents"));
			bbs.setViewCount(rs.getInt("view_count"));
			bbs.setLikeCount(rs.getInt("like_count"));
			bbs.setDislikeCount(rs.getInt("dislike_count"));
			bbs.setReportCount(rs.getInt("report_count"));
			bbs.setFileCount(rs.getInt("file_count"));
			bbs.setCommentCount(rs.getInt("comment_count"));
			bbs.setBlindCount(rs.getInt("blind_count"));
			bbs.setRegDate(rs.getString("reg_date"));
			bbs.setGender(rs.getInt("gender"));
			bbs.setBirthYear(rs.getInt("birth_year"));
			bbs.setArea(rs.getString("area"));
			bbs.setUserName(rs.getString("user_name"));
			bbs.setPhoto(rs.getString("photo"));
			bbs.setUserLevel(rs.getInt("user_level"));
			return bbs;
		}
	};
	private RowMapper VbbsMapper = new RowMapper() {
		public Bbs mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bbs bbs = new Bbs();
			bbs.setBbsSeq(rs.getInt("bbs_seq"));
			bbs.setBbsCategory(rs.getString("bbs_category"));
			bbs.setBbsHeader(rs.getString("bbs_header"));
			bbs.setUserId(rs.getString("user_id"));
			bbs.setBbsTitle(rs.getString("bbs_title"));
			bbs.setBbsContents(rs.getString("bbs_contents"));
			bbs.setBbsContentsText(rs.getString("bbs_contents_text"));
			bbs.setFiles(rs.getString("files"));
			bbs.setLinkUrl(rs.getString("link_url"));
			bbs.setExtraContents(rs.getString("extra_contents"));
			bbs.setViewCount(rs.getInt("view_count"));
			bbs.setLikeCount(rs.getInt("like_count"));
			bbs.setDislikeCount(rs.getInt("dislike_count"));
			bbs.setReportCount(rs.getInt("report_count"));
			bbs.setFileCount(rs.getInt("file_count"));
			bbs.setCommentCount(rs.getInt("bbscom_count"));
			bbs.setBlindCount(rs.getInt("blind_count"));
			bbs.setRegDate(rs.getString("reg_date"));
			bbs.setGender(rs.getInt("gender"));
			bbs.setBirthYear(rs.getInt("birth_year"));
			bbs.setArea(rs.getString("area"));
			bbs.setUserName(rs.getString("user_name"));
			bbs.setPhoto(rs.getString("photo"));
			bbs.setUserLevel(rs.getInt("user_level"));
			return bbs;
		}
	};
	
	private RowMapper bbsMapper2 = new RowMapper() {
		public Bbs mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bbs bbs = new Bbs();
			bbs.setBbsSeq(rs.getInt("bbs_seq"));
			bbs.setBbsCategory(rs.getString("bbs_category"));
			bbs.setBbsHeader(rs.getString("bbs_header"));
			bbs.setUserId(rs.getString("user_id"));
			bbs.setBbsTitle(rs.getString("bbs_title"));
			bbs.setBbsContents(rs.getString("bbs_contents"));
			bbs.setBbsContentsText(rs.getString("bbs_contents_text"));
			bbs.setFiles(rs.getString("files"));
			bbs.setLinkUrl(rs.getString("link_url"));
			bbs.setExtraContents(rs.getString("extra_contents"));
			bbs.setViewCount(rs.getInt("view_count"));
			bbs.setLikeCount(rs.getInt("like_count"));
			bbs.setDislikeCount(rs.getInt("dislike_count"));
			bbs.setReportCount(rs.getInt("report_count"));
			bbs.setFileCount(rs.getInt("file_count"));
			bbs.setCommentCount(rs.getInt("comment_count"));
			bbs.setBlindCount(rs.getInt("blind_count"));
			bbs.setRegDate(rs.getString("reg_date"));
			bbs.setBirthYear(rs.getInt("birth_year"));
			bbs.setGender(rs.getInt("gender"));
			bbs.setArea(rs.getString("area"));
			bbs.setUserName(rs.getString("user_name"));
			bbs.setPhoto(rs.getString("photo"));
			bbs.setUserLevel(rs.getInt("user_level"));
			bbs.setCategoryName(rs.getString("category_name"));
			return bbs;
		}
	};
	

	

	private RowMapper<Bbs> bbsMapperListIdx = new RowMapper<Bbs>() {
		public Bbs mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bbs bbs = new Bbs();
			bbs.setBbsSeq(rs.getInt("bbs_seq"));
			bbs.setBbsCategory(rs.getString("bbs_category"));
			bbs.setBbsHeader(rs.getString("bbs_header"));
			bbs.setUserId(rs.getString("user_id"));
			bbs.setBbsTitle(rs.getString("bbs_title"));
			bbs.setBbsContents(rs.getString("bbs_contents"));
			bbs.setBbsContentsText(rs.getString("bbs_contents_text"));
			bbs.setFiles(rs.getString("files"));
			bbs.setLinkUrl(rs.getString("link_url"));
			bbs.setExtraContents(rs.getString("extra_contents"));
			bbs.setViewCount(rs.getInt("view_count"));
			bbs.setLikeCount(rs.getInt("like_count"));
			bbs.setDislikeCount(rs.getInt("dislike_count"));
			bbs.setReportCount(rs.getInt("report_count"));
			bbs.setFileCount(rs.getInt("file_count"));
			bbs.setCommentCount(rs.getInt("comment_count"));
			bbs.setRegDate(rs.getString("reg_date"));			
			bbs.setUserName(rs.getString("user_name"));
			bbs.setUserLevel(rs.getInt("user_level"));
			bbs.setFiles(rs.getString("files"));
			return bbs;
		}
	};
	private RowMapper bbsMapper3 = new RowMapper() {
		public Bbs mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bbs bbs = new Bbs();
			bbs.setBbsSeq(rs.getInt("count"));
			bbs.setUserId(rs.getString("user_id"));
			bbs.setRegDate(rs.getString("reg_date"));
			bbs.setBirthYear(rs.getInt("birth_year"));
			bbs.setGender(rs.getInt("gender"));
			bbs.setArea(rs.getString("area"));
			bbs.setUserName(rs.getString("user_name"));
			bbs.setPhoto(rs.getString("photo"));
			bbs.setUserLevel(rs.getInt("user_level"));
			return bbs;
		}
	};
	private RowMapper bbsMapper4 = new RowMapper() {
		public Bbs mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bbs bbs = new Bbs();
			bbs.setBbsSeq(rs.getInt("bbs_seq"));
			bbs.setBbsCategory(rs.getString("bbs_category"));
			bbs.setBbsHeader(rs.getString("bbs_header"));
			bbs.setUserId(rs.getString("user_id"));
			bbs.setBbsTitle(rs.getString("bbs_title"));
			bbs.setBbsContents(rs.getString("bbs_contents"));
			bbs.setBbsContentsText(rs.getString("bbs_contents_text"));
			bbs.setFiles(rs.getString("files"));
			bbs.setLinkUrl(rs.getString("link_url"));
			bbs.setExtraContents(rs.getString("extra_contents"));
			bbs.setViewCount(rs.getInt("view_count"));
			bbs.setLikeCount(rs.getInt("like_count"));
			bbs.setDislikeCount(rs.getInt("dislike_count"));
			bbs.setReportCount(rs.getInt("report_count"));
			bbs.setFileCount(rs.getInt("file_count"));
			bbs.setCommentCount(rs.getInt("comment_count"));
			bbs.setBlindCount(rs.getInt("blind_count"));
			bbs.setRegDate(rs.getString("reg_date"));
			bbs.setCateName(rs.getString("category_name"));
			bbs.setUserName(rs.getString("user_name"));
			bbs.setPhoto(rs.getString("photo"));
			return bbs;
		}
	};
	
	private RowMapper bbsMapper5 = new RowMapper() {
		public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
			Album album = new Album();
			album.setBbsSeq(rs.getInt("bbs_seq"));
			album.setBbsCategory(rs.getString("bbs_category"));
			album.setBbsHeader(rs.getString("bbs_header"));
			album.setUserId(rs.getString("user_id"));
			album.setBbsTitle(rs.getString("bbs_title"));
			album.setBbsContents(rs.getString("bbs_contents"));
			album.setBbsContentsText(rs.getString("bbs_contents_text"));
			album.setFiles(rs.getString("files"));
			album.setLinkUrl(rs.getString("link_url"));
			album.setExtraContents(rs.getString("extra_contents"));
			album.setViewCount(rs.getInt("view_count"));
			album.setLikeCount(rs.getInt("like_count"));
			album.setDislikeCount(rs.getInt("dislike_count"));
			album.setReportCount(rs.getInt("report_count"));
			album.setFileCount(rs.getInt("file_count"));
			album.setCommentCount(rs.getInt("comment_count"));
			album.setBlindCount(rs.getInt("blind_count"));
			album.setRegDate(rs.getString("reg_date"));
			return album;
		}
	};
	
	
	private RowMapper bbsMapper6 = new RowMapper() {
		public Bbs mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bbs bbs = new Bbs();
			bbs.setBbsSeq(rs.getInt("bbs_seq"));
			bbs.setBbsCategory(rs.getString("bbs_category"));
			bbs.setBbsHeader(rs.getString("bbs_header"));
			bbs.setUserId(rs.getString("user_id"));
			bbs.setBbsTitle(rs.getString("bbs_title"));
			bbs.setBbsContents(rs.getString("bbs_contents"));
			bbs.setBbsContentsText(rs.getString("bbs_contents_text"));
			bbs.setFiles(rs.getString("files"));
			bbs.setLinkUrl(rs.getString("link_url"));
			bbs.setExtraContents(rs.getString("extra_contents"));
			bbs.setViewCount(rs.getInt("view_count"));
			bbs.setLikeCount(rs.getInt("like_count"));
			bbs.setDislikeCount(rs.getInt("dislike_count"));
			bbs.setReportCount(rs.getInt("report_count"));
			bbs.setFileCount(rs.getInt("file_count"));
			bbs.setCommentCount(rs.getInt("comment_count"));
			bbs.setBlindCount(rs.getInt("blind_count"));
			bbs.setRegDate(rs.getString("reg_date"));
			bbs.setBirthYear(rs.getInt("birth_year"));
			bbs.setGender(rs.getInt("gender"));
			bbs.setArea(rs.getString("area"));
			bbs.setUserName(rs.getString("user_name"));
			bbs.setPhoto(rs.getString("photo"));
			bbs.setUserLevel(rs.getInt("user_level"));
			bbs.setCategoryName(rs.getString("category_name"));
			bbs.setBbscomCount(rs.getInt("bbscom_count"));
			return bbs;
		}
	};
	
	private RowMapper bbsMapper7 = new RowMapper() {
		public Bbs mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bbs bbs = new Bbs();
			bbs.setBbsSeq(rs.getInt("bbs_seq"));
			bbs.setBbsCategory(rs.getString("bbs_category"));
			bbs.setBbsHeader(rs.getString("bbs_header"));
			bbs.setUserId(rs.getString("user_id"));
			bbs.setBbsTitle(rs.getString("bbs_title"));
			bbs.setBbsContents(rs.getString("bbs_contents"));
			bbs.setBbsContentsText(rs.getString("bbs_contents_text"));
			bbs.setFiles(rs.getString("files"));
			bbs.setLinkUrl(rs.getString("link_url"));
			bbs.setExtraContents(rs.getString("extra_contents"));
			bbs.setViewCount(rs.getInt("view_count"));
			bbs.setLikeCount(rs.getInt("like_count"));
			bbs.setDislikeCount(rs.getInt("dislike_count"));
			bbs.setReportCount(rs.getInt("report_count"));
			bbs.setFileCount(rs.getInt("file_count"));
			bbs.setCommentCount(rs.getInt("comment_count"));
			bbs.setBlindCount(rs.getInt("blind_count"));
			bbs.setRegDate(rs.getString("reg_date"));
			bbs.setCateName(rs.getString("category_name"));
			bbs.setUserName(rs.getString("user_name"));
			bbs.setPhoto(rs.getString("photo"));
			bbs.setBirthYear(rs.getInt("birth_year"));
			bbs.setGender(rs.getInt("gender"));
			bbs.setArea(rs.getString("area"));
			bbs.setBbscomCount(rs.getInt("bbscom_count"));
			return bbs;
		}
	};
	
	private RowMapper BbsnCommentMapper= new RowMapper() {
		public Bbs mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bbs bbs = new Bbs();
			bbs.setBbsSeq(rs.getInt("bbs_seq"));
			bbs.setUserId(rs.getString("user_id"));
		    bbs.setPushkey(rs.getString("pushkey"));
		    bbs.setPushType(rs.getInt("use_pushservice"));
		    bbs.setOs(rs.getString("os_type"));
			return bbs;
		}
	};
	public int addBbs(final Bbs bbs) {
		String query = ""
				+ "	INSERT INTO T_NF_BBS "
				+ "		(bbs_category, bbs_header, user_id, "
				+ "      bbs_title, bbs_contents, bbs_contents_text, "
				+ "      files, link_url, extra_contents, "
				+ "		 view_count, like_count, dislike_count, "
				+ "      report_count, file_count, comment_count, "
				+ "      blind_count, reg_date,"
				+ "      share_bbs_seq, share_write_id, share_reg_date ) "
				+ "	VALUES "
				+ "		(?, ?, ?, "
				+ "		 ?, ?, ?, "
				+ "		 ?, ?, ?, "
				+ "      ?, ?, ?, "
				+ "      ?, ?, ?, "
				+ "      ?, getDate(),"
				+ "      ?, ?, getDate()) "
				+ "	SELECT SCOPE_IDENTITY() AS bbs_seq ";
		return this.jdbcTemplate.queryForInt(query, new Object[] {
				bbs.getBbsCategory(),
				bbs.getBbsHeader(),
				bbs.getUserId(),
				bbs.getBbsTitle(),
				bbs.getBbsContents(),
				bbs.getBbsContentsText(),
				bbs.getFiles(),
				bbs.getLinkUrl(),
				bbs.getExtraContents(),
				bbs.getViewCount(),
				bbs.getLikeCount(),
				bbs.getDislikeCount(),
				bbs.getReportCount(),
				bbs.getFileCount(),
				bbs.getCommentCount(),
				bbs.getBlindCount(),
				bbs.getShareBbsSeq(),
				bbs.getShareWriteId()
		});
	}
	public void deleteBbs(int bbs_seq) {
		String query = "DELETE FROM T_NF_BBS WHERE bbs_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { bbs_seq });
	}
	
	//공유된글 지우고
	public void deleteShareBbs(int sharebbsseq) {
		String query = "DELETE FROM T_NF_BBS WHERE share_bbs_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { sharebbsseq });
	}

	public boolean deleteBbss(int bbs_seq) {
		boolean result = false;
		try {
			String query = "DELETE FROM T_NF_BBS WHERE bbs_seq = ? ";
			int qResult = this.jdbcTemplate.update(query, new Object[] { bbs_seq});	
			
			if(qResult > 0) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	public void updateBbs(Bbs bbs) { 
		String query = "" + 
				"UPDATE T_NF_BBS SET " +
				"	bbs_category = ?, " +
				"	bbs_header = ?, " +
				"	user_id = ?, " +
				"	bbs_title = ?, " +
				"	bbs_contents = ?, " +
				"	bbs_contents_text = ?, " +
				"	files = ?, " +
				"	link_url = ?, " +
				"	extra_contents = ?, " +
				"	view_count = ?, " +
				"	like_count = ?, " +
				"	dislike_count = ?, " +
				"	report_count = ?, " +
				"	file_count = ?, " +
				"	comment_count = ?, " +
				"	blind_count = ?, " +
				"	reg_date = ? " +
				"WHERE bbs_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			bbs.getBbsCategory(),
			bbs.getBbsHeader(),
			bbs.getUserId(),
			bbs.getBbsTitle(),
			bbs.getBbsContents(),
			bbs.getBbsContentsText(),
			bbs.getFiles(),
			bbs.getLinkUrl(),
			bbs.getExtraContents(),
			bbs.getViewCount(),
			bbs.getLikeCount(),
			bbs.getDislikeCount(),
			bbs.getReportCount(),
			bbs.getFileCount(),
			bbs.getCommentCount(),
			bbs.getBlindCount(),
			bbs.getRegDate(),
			bbs.getBbsSeq()
		});
	}
	

	public boolean updateBbsContents(Bbs bbs) { 
		boolean result = false;
		try {
			String query = "" + 
					"UPDATE T_NF_BBS SET " +
					"	bbs_category = ?, " +
					"	bbs_header = ?, " +
					"	user_id = ?, " +
					"	bbs_title = ?, " +
					"	bbs_contents = ?, " +
					"	bbs_contents_text = ?, " +
					"	files = ?, " +
					"	link_url = ?, " +
					"	extra_contents = ?, " +
					"	view_count = ?, " +
					"	like_count = ?, " +
					"	dislike_count = ?, " +
					"	report_count = ?, " +
					"	file_count = ? " +
					
					"WHERE bbs_seq = ? ";
			int querResult = this.jdbcTemplate.update(query, new Object[] {
				bbs.getBbsCategory(),
				bbs.getBbsHeader(),
				bbs.getUserId(),
				bbs.getBbsTitle(),
				bbs.getBbsContents(),
				bbs.getBbsContentsText(),
				bbs.getFiles(),
				bbs.getLinkUrl(),
				bbs.getExtraContents(),
				bbs.getViewCount(),
				bbs.getLikeCount(),
				bbs.getDislikeCount(),
				bbs.getReportCount(),
				bbs.getFileCount(),
				
				bbs.getBbsSeq()
			});
			
			if(querResult > 0) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	//친구 토크목록
	public List<Bbs> selectf(String userId,int page,int itemCountPerPage,String keyword) {
		String con="";
		if(!keyword.equals("")){
			con = " and bbs_contents like '%"+keyword+"%'";
		}
		
		String query = ""
			+ "SELECT * FROM ("
			+ "	select "
			+ "		ROW_NUMBER() OVER(ORDER BY bbs_seq DESC) as row_seq, "
			+ "		 * "
			+ " from V_nf_bbs "
			+ " where user_id in (select friend_id from t_nf_friend where user_id = ?) "+ con
			+ ") AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+"";
	
		return (List<Bbs>)this.jdbcTemplate.query(query, new Object[] {userId},this.bbsMapper2);
	
	}
	//친구토크 갯수
	public int selectf(String userId,String keyword) { 
		String con="";
		if(!keyword.equals("")){
			con = " and bbs_contents like '%"+keyword+"%'";
		}
		
		String query = ""
			+ " select count(*) "
			+ " select count(*) "
			+ " from V_nf_bbs "
			+ " where user_id in (select friend_id from t_nf_friend where user_id = ?) ";
			
	
		return this.jdbcTemplate.queryForInt(query, new Object[] {userId});
	
	}

	public boolean updateCommentCount(int bbsSeq, int value) {
		boolean result = false;
		try {
			String query = "update T_NF_BBS set comment_count = ? where bbs_seq = ? ";
			int qResult = this.jdbcTemplate.update(query, new Object[] { value, bbsSeq});				
			if(qResult > 0) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;		
	}
	
	public boolean updateViewCount(int bbsSeq) {
		boolean result = false;
		try {
			String query = "update T_NF_BBS set view_count = view_count + 1 where bbs_seq = ? ";
			int qResult = this.jdbcTemplate.update(query, new Object[] { bbsSeq});				
			if(qResult > 0) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;		
	}
	
	public boolean updateFileCount(int bbsSeq, int value) {
		boolean result = false;
		try {
			String query = "update T_NF_BBS set file_count = ? where bbs_seq = ? ";
			int qResult = this.jdbcTemplate.update(query, new Object[] { value, bbsSeq});				
			if(qResult > 0) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;		
	}
	
	
	public boolean updateReportCount(int bbsSeq, int value) {
		boolean result = false;
		try {
			String query = "update T_NF_BBS set report_count = ? where bbs_seq = ? ";
			int qResult = this.jdbcTemplate.update(query, new Object[] { value, bbsSeq});				
			if(qResult > 0) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;		
	}
	
	public boolean updateDislikeCount(int bbsSeq, int value) {
		boolean result = false;
		try {
			String query = "update T_NF_BBS set dislike_count = ? where bbs_seq = ? ";
			int qResult = this.jdbcTemplate.update(query, new Object[] { value, bbsSeq});				
			if(qResult > 0) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;		
	}
	
	public boolean updateLikeCount(int bbsSeq, int value) {
		boolean result = false;
		try {
			String query = "update T_NF_BBS set like_count = ? where bbs_seq = ? ";
			int qResult = this.jdbcTemplate.update(query, new Object[] { value, bbsSeq});				
			if(qResult > 0) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;		
	}
	
	public boolean updateCate(int bbsSeq, int value) {
		boolean result = false;
		try {
			String query = "update T_NF_BBS set bbs_category = ? where bbs_seq = ? ";
			int qResult = this.jdbcTemplate.update(query, new Object[] { value, bbsSeq});				
			if(qResult > 0) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;		
	}
	
	public boolean updateLikeCountAndGloryCheck(int bbsSeq, int value) {
		boolean result = false;
		try {
			String query = "update T_NF_BBS set like_count = ?, bbs_glory = 1 where bbs_seq = ? ";
			int qResult = this.jdbcTemplate.update(query, new Object[] { value, bbsSeq});				
			if(qResult > 0) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;		
	}
	
	
	public Bbs getBbs(int bbs_seq) {
		String query = "" + 
				"SELECT * " +
				"FROM V_NF_BBS " +
				"WHERE bbs_seq = ? and kind!=3";
		try{
			return (Bbs)this.jdbcTemplate.queryForObject(query, new Object[] { bbs_seq }, this.VbbsMapper);
		}catch(Exception e){
			return new Bbs();
		}
	}
	
	public Bbs getBbs2(int bbs_seq) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_BBS " +
				"WHERE bbs_seq = ? ";
		try{
			return (Bbs)this.jdbcTemplate.queryForObject(query, new Object[] { bbs_seq }, this.bbsMapperorg);
		}catch(Exception e){
			return null;
		}
	}
	
	
	// 공유한 해당 글 리스트
	public List<Bbs> getBbsShareList(int share_bbs_seq) {
		String query = "" + 
				"SELECT * " +
				"FROM V_NF_BBS " +
				"WHERE share_bbs_seq = ? ";
		try{
			return (List<Bbs>)this.jdbcTemplate.query(query, new Object[] { share_bbs_seq }, this.bbsMapper);
		}catch(Exception e){
			return new ArrayList();
		}
	}
	
	

	
	//중복체크
	public int getBbsSharechk(String userId,int bbs_seq) {
		String query = "" + 
				"SELECT count(*) " +
				"FROM T_NF_BBS " +
				"WHERE share_bbs_seq = ? and user_id = ? ";
		try{
			return this.jdbcTemplate.queryForInt(query, new Object[] { bbs_seq,userId });
		}catch(Exception e){
			return 0;
		}
	}
	public List<Bbs> getBbspushList(int bbs_seq) {
		String query = "" + 
				"SELECT * " +
				"FROM V_BBSNCOMMENT " +
				"WHERE bbs_seq = ? ";
		try{
			return (List<Bbs>)this.jdbcTemplate.query(query, new Object[] { bbs_seq }, this.BbsnCommentMapper);
		}catch(Exception e){
			return new ArrayList();
		}
	}
	
	public int getcommentCount(int bbs_seq) {
		String query = "" + 
				"SELECT comment_count " +
				"FROM T_NF_BBS " +
				"WHERE bbs_seq = ? ";
		try{
			return this.jdbcTemplate.queryForInt(query, new Object[] { bbs_seq });
		}catch(Exception e){
			return 0;
		}
	}
	
	public Bbs getupdateBbs(int bbs_seq) {
		
		
		String query = "" + 
				" SELECT * " +
				" FROM V_NF_BBSLIST " +
				" WHERE bbs_seq = ? ";
		try {
			return (Bbs)this.jdbcTemplate.queryForObject(query, new Object[] { bbs_seq }, this.bbsMapper7);
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public Bbs getuBbs(int bbs_seq) {
		
		
		String query = "" + 
				" SELECT * " +
				" FROM V_NF_BBSLIST " +
				" WHERE bbs_seq = ? ";
		try {
			return (Bbs)this.jdbcTemplate.queryForObject(query, new Object[] { bbs_seq }, this.bbsMapper7);
		} catch (Exception e) {
			return new Bbs();
		}
		
	}
	
	
	/** 파일 리스트로 가지고 오기 **/
	public List<Bbs> getBbsFileList(int bbs_seq) {
		String query = "" + 
				" SELECT * " +
				" FROM V_NF_BBSLIST " +
				" WHERE bbs_seq = ? ";
	    return (List<Bbs>)this.jdbcTemplate.query(query, new Object[] { bbs_seq }, this.bbsMapper2);
	}
	
	
	

	/** 토크 검색 목록 **/
	public List<Bbs> getBbsList(String site,String userId, int page, int itemCountPerPage) {
		String query = ""
	            + "	SELECT * FROM ( "
	            + "		SELECT "
	            + "			ROW_NUMBER() OVER(ORDER BY reg_date desc) as row_seq, "
	            + "			* "
	            + "		FROM V_NF_BBS "
	            //+ "		WHERE user_id = ? and blind_count = 0 and status =1 and writer_status = 1"
	            + " where user_id = ? and (writer_id != ? ) and blind_count = 0 and status =1 and writer_status = 1 and site=? "
	            + "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
	    return (List<Bbs>)this.jdbcTemplate.query(query, new Object[] { userId , userId,site}, this.bbsMapperVisited2);
	}
	
	//토크검색목록 카운트
	public int getBbsListCount(String site,String userId) {
		String query = ""
	        
	            + "		SELECT "
	       
	            + "			count(*) "
	            + "		FROM V_NF_BBS "
	            + " where site=? and user_id = ? and (writer_id != ? ) and blind_count = 0 and status =1 and writer_status = 1 ";
	       
	    return this.jdbcTemplate.queryForInt(query, new Object[] {site, userId,userId });
	}
	
	//탈퇴시 내가쓴글 조회 일단
	public List<Bbs> getBbsList(String userId) {
		String query = ""
	            + "	SELECT * FROM  "
	            + "		T_NF_BBS "
	            + "		WHERE user_id = ?";
	   try{
		   return (List<Bbs>)this.jdbcTemplate.query(query, new Object[] { userId }, this.bbsMapperorg);
	   }catch(Exception e){
		   return null;
	   }
	  
	}

	// 탈퇴시 공유된 글 목록
	public List<Bbs> getshareBbsList(int shareBbsSeq) {
		String query = "" + 
				"	SELECT * FROM  " + 
				"		T_NF_BBS " + 
				"		WHERE share_bbs_seq = ? ";
		try {
			return (List<Bbs>) this.jdbcTemplate.query(query, new Object[] { shareBbsSeq }, this.bbsMapperorg);
		} catch (Exception e) {
			return null;
		}

	}
	
	//탈퇴시 내가쓴글 삭제
	
	public void deleteBbs(String userId) {
		String query = "DELETE FROM T_NF_BBS WHERE user_id = ? ";
		try{
			this.jdbcTemplate.update(query, new Object[] { userId });
		}catch(Exception e){
			
		}
		
	}

	// 해당 토크 파일 리스트 가지고 오기 
	public List<Bbs> getFileList(int bbs_seq) {
		String query = ""
	            + "	SELECT * FROM  "
	            + "		 V_NF_BBS "
	            + "		WHERE bbs_seq = ? ";
	    try{
	    	return (List<Bbs>)this.jdbcTemplate.query(query, new Object[] { bbs_seq }, this.bbsMapper2);
	    }catch(Exception e){
	    	return null;
	    }
		
	}
	
	// 해당 토크 파일 리스트 지우기
	public void deleteBbsFiles(int bbs_seq) {
		String query = "DELETE FROM T_NF_BBS WHERE bbs_seq = ? ";
		try{
			this.jdbcTemplate.update(query, new Object[] { bbs_seq });
		}catch(Exception e){
			
		}
	}
	
	
	
	/** 토크 검색 목록 **/
	public List<Bbs> getBbsList(int gender, int age, String keyword, String areaSido, boolean reportCount, int page, int itemCountPerPage) {
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = " WHERE 1=1 AND dislike_count = 0 ";
		
		if (gender != 0) {
			condition += " AND u.gender = "+ gender +" ";
		}
		if (age == 1) {
			condition += " AND (CAST(u.birth_year AS INT) between "+(year-19)+" and "+(year-10)+")";
		} else if (age == 2) {
			condition += " AND (CAST(u.birth_year AS INT) between "+(year-29)+" and "+(year-20)+" )";
		} else if (age == 3) {
			condition += " AND (CAST(u.birth_year AS INT) between "+(year-39)+"  and "+(year-30)+" )";
		} else if (age == 4) {
			condition += " AND (CAST(u.birth_year AS INT) between "+(year-49)+" and "+(year-40)+" )";
		} else if (age == 5) {
			condition += " AND (CAST(u.birth_year AS INT) between "+(year-89)+" and "+(year-50)+"  )";
		}
		
		if (keyword.equals("") == false) {
			condition += " AND u.nick_name like '%"+ keyword +"%'";
		}
		if (areaSido.equals("") == false) {
			condition += " AND u.area like '%"+ areaSido +"%'";
		}
		
		if (reportCount == true) {
			condition += " AND b.report_count > 0";
		}
		
		String query = ""
	            + "    SELECT * FROM ( "
	            + "        SELECT "
	            + "            ROW_NUMBER() OVER(ORDER BY bbs_seq desc) as row_seq, "
	            + "            b.* ,"
	            + "				u.gender, u.birth_year, u.area, u.user_name "
	            + "        FROM T_NF_BBS AS b LEFT OUTER JOIN"
	            + "			T_NF_USER AS u ON u.user_id = b.user_id"
	            + "			WHERE b.user_id IN (SELECT user_id FROM T_NF_USER "+condition+" ) OR bbs_contents like '%"+ keyword +"%'"
	            + "    ) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
	    return (List<Bbs>)this.jdbcTemplate.query(query, this.bbsMapper2);
	}
	
	

	
	
	
	// 신고토크 리스트
	public List<Bbs> getBbsResiList(int gender, int age, String keyword, String areaSido, String startDate,String endDate,int page, int itemCountPerPage) {
		
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = "WHERE 1=1  AND report_count > 0 ";
		
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
		
		if (age == 1) {
			condition += " AND (CAST(birth_year AS INT) between "+(year-19)+" and "+(year-10)+")";
		} else if (age == 2) {
			condition += " AND (CAST(birth_year AS INT) between "+(year-29)+" and "+(year-20)+" )";
		} else if (age == 3) {
			condition += " AND (CAST(birth_year AS INT) between "+(year-39)+"  and "+(year-30)+" )";
		} else if (age == 4) {
			condition += " AND (CAST(birth_year AS INT) between "+(year-49)+" and "+(year-40)+" )";
		} else if (age == 5) {
			condition += " AND (CAST(birth_year AS INT) between "+(year-89)+" and "+(year-50)+"  )";
		}
		
		if (keyword.equals("") == false) {
			condition += " AND user_name like '%"+ keyword +"%'";
		}
		if (areaSido.equals("") == false) {
			condition += " AND area like '%"+ areaSido +"%'";
		}
		
		
		if(keyword.equals("") == false){
			
			condition+="OR bbs_contents like '%"+ keyword +"%'";
		}
		
		if(!startDate.equals("")&&!endDate.equals("")){
			
			condition+=" AND reg_date between '"+ startDate+ " 00:00:00' and  '"+endDate+" 23:59:59'" ;
		}
		
		String query = ""
	            + "	SELECT * FROM ( "
	            + "		SELECT "
	            + "			ROW_NUMBER() OVER(ORDER BY bbs_seq desc) as row_seq, "
	            + "			* "
	            + "		FROM V_NF_TALKLIST "
				+ "	 "+condition+" " 
	            + "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";

		return (List<Bbs>)this.jdbcTemplate.query(query,this.bbsMapper2);
	}
	
	
	
	/** 관리자 신고토크 검색결과 갯수 **/
	public int getResiCount(int gender, int age, String keyword, String areaSido, String startDate,String endDate) {
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = "WHERE 1=1  AND report_count > 0 ";
		
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
		
		if (age == 1) {
			condition += " AND (CAST(birth_year AS INT) between "+(year-19)+" and "+(year-10)+")";
		} else if (age == 2) {
			condition += " AND (CAST(birth_year AS INT) between "+(year-29)+" and "+(year-20)+" )";
		} else if (age == 3) {
			condition += " AND (CAST(birth_year AS INT) between "+(year-39)+"  and "+(year-30)+" )";
		} else if (age == 4) {
			condition += " AND (CAST(birth_year AS INT) between "+(year-49)+" and "+(year-40)+" )";
		} else if (age == 5) {
			condition += " AND (CAST(birth_year AS INT) between "+(year-89)+" and "+(year-50)+"  )";
		}
		
		if (keyword.equals("") == false) {
			condition += " AND user_name like '%"+ keyword +"%'";
		}
		if (areaSido.equals("") == false) {
			condition += " AND area like '%"+ areaSido +"%'";
		}
		
		if(keyword.equals("") == false){
			
			condition+="OR bbs_contents like '%"+ keyword +"%'";
		}
		
		if(!startDate.equals("")&&!endDate.equals("")){
			
			condition+=" AND reg_date between '"+ startDate+ " 00:00:00' and  '"+endDate+" 23:59:59'" ;
		}
		
	    String query = " SELECT COUNT(*) FROM V_NF_TALKLIST "+ "	 "+condition+" " ;
	    return this.jdbcTemplate.queryForInt(query);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/********************************************************************************
	/* 러브다이아 소스
	********************************************************************************/
	private RowMapper<Bbs> bbsMapperVisited = new RowMapper<Bbs>() {
		public Bbs mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bbs bbs = new Bbs();
			bbs.setBbsSeq(rs.getInt("bbs_seq"));
			bbs.setBbsCategory(rs.getString("bbs_category"));
			bbs.setBbsHeader(rs.getString("bbs_header"));
			bbs.setUserId(rs.getString("user_id"));
			bbs.setBbsTitle(rs.getString("bbs_title"));
			bbs.setBbsContents(rs.getString("bbs_contents"));
			bbs.setBbsContentsText(rs.getString("bbs_contents_text"));
			bbs.setFiles(rs.getString("files"));
			bbs.setLinkUrl(rs.getString("link_url"));
			bbs.setExtraContents(rs.getString("extra_contents"));
			bbs.setViewCount(rs.getInt("view_count"));
			bbs.setLikeCount(rs.getInt("like_count"));
			bbs.setDislikeCount(rs.getInt("dislike_count"));
			bbs.setReportCount(rs.getInt("report_count"));
			bbs.setFileCount(rs.getInt("file_count"));
			bbs.setCommentCount(rs.getInt("comment_count"));
			bbs.setRegDate(rs.getString("reg_date"));			
			bbs.setUserName(rs.getString("user_name"));
			bbs.setUserLevel(rs.getInt("user_level"));
			bbs.setDistance(rs.getInt("distance"));
			bbs.setPhoto(rs.getString("photo"));
			bbs.setArea(rs.getString("area"));
			bbs.setGender(rs.getInt("gender"));
			bbs.setBirthYear(rs.getInt("birth_year"));
			bbs.setShareSeq(rs.getInt("share_seq"));
			bbs.setWriterId(rs.getString("writer_id"));
			bbs.setWriterName(rs.getString("writer_name"));
			bbs.setKind(rs.getInt("kind"));
			bbs.setWriterPhoto(rs.getString("writer_photo"));
			bbs.setWriterArea(rs.getString("writer_area"));
			bbs.setWriterGender(rs.getInt("writer_gender"));
			bbs.setWriterComment(rs.getString("writer_comment"));
			bbs.setWriterBirth(rs.getInt("writer_birth"));
			bbs.setWriterFiles(rs.getString("writer_files"));
			bbs.setWriterStatus(rs.getInt("writer_status"));
			bbs.setStatus(rs.getInt("status"));
			bbs.setWriterCommnetCount(rs.getInt("writer_comment_count"));
			bbs.setWriterLikeCount(rs.getInt("writer_like_count"));
			bbs.setWrite_blind_count(rs.getInt("writer_blind_count"));
			bbs.setSite(rs.getString("site"));
			
			return bbs;
		}
	};
	private RowMapper<Bbs> bbsMapperVisited2 = new RowMapper<Bbs>() {
		public Bbs mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bbs bbs = new Bbs();
			bbs.setBbsSeq(rs.getInt("bbs_seq"));
			bbs.setBbsCategory(rs.getString("bbs_category"));
			bbs.setBbsHeader(rs.getString("bbs_header"));
			bbs.setUserId(rs.getString("user_id"));
			bbs.setBbsTitle(rs.getString("bbs_title"));
			bbs.setBbsContents(rs.getString("bbs_contents"));
			bbs.setBbsContentsText(rs.getString("bbs_contents_text"));
			bbs.setFiles(rs.getString("files"));
			bbs.setLinkUrl(rs.getString("link_url"));
			bbs.setExtraContents(rs.getString("extra_contents"));
			bbs.setViewCount(rs.getInt("view_count"));
			bbs.setLikeCount(rs.getInt("like_count"));
			bbs.setDislikeCount(rs.getInt("dislike_count"));
			bbs.setReportCount(rs.getInt("report_count"));
			bbs.setBlindCount(rs.getInt("blind_count"));
			bbs.setFileCount(rs.getInt("file_count"));
			bbs.setCommentCount(rs.getInt("comment_count"));
			bbs.setRegDate(rs.getString("reg_date"));			
			bbs.setUserName(rs.getString("user_name"));
			bbs.setUserLevel(rs.getInt("user_level"));
			//bbs.setDistance(rs.getInt("distance"));
			bbs.setPhoto(rs.getString("photo"));
			bbs.setArea(rs.getString("area"));
			bbs.setGender(rs.getInt("gender"));
			bbs.setBirthYear(rs.getInt("birth_year"));
			bbs.setShareSeq(rs.getInt("share_seq"));
			bbs.setWriterId(rs.getString("writer_id"));
			bbs.setWriterName(rs.getString("writer_name"));
			bbs.setKind(rs.getInt("kind"));
			bbs.setWriterPhoto(rs.getString("writer_photo"));
			bbs.setWriterArea(rs.getString("writer_area"));
			bbs.setWriterGender(rs.getInt("writer_gender"));
			bbs.setWriterComment(rs.getString("writer_comment"));
			bbs.setWriterBirth(rs.getInt("writer_birth"));
			bbs.setWriterFiles(rs.getString("writer_files"));
			bbs.setWriterStatus(rs.getInt("writer_status"));
			bbs.setStatus(rs.getInt("status"));
			bbs.setWriterCommnetCount(rs.getInt("writer_comment_count"));
			bbs.setWriterLikeCount(rs.getInt("writer_like_count"));
			bbs.setWrite_blind_count(rs.getInt("writer_blind_count"));
			bbs.setCategoryName(rs.getString("category_name"));
			bbs.setSite(rs.getString("site"));
			return bbs;
		}
	};
	//주연 
	public List<Bbs> getBbsList(String site,int page, int itemCountPerPage, String userId, int category, String keyword, int gender, String area, int sort, Double latitude, Double longitude,String kind) {
		String condition = " WHERE blind_count = 0 and writer_blind_count = 0 and A.latitude>0 and A.longitude>0 and status=1 and writer_status = 1";
		
		if (category > 1) {
			condition += " AND bbs_category = "+category;
		}
		if (keyword.equals("") == false) {
			condition += " AND (bbs_contents like '%"+ keyword +"%' or user_name like '%"+ keyword +"%')";
		}
		if (gender > 0) {
			condition += " AND gender = "+ gender;
		}
		if (area.equals("") == false) {
			condition += " AND area = '"+ area +"'";
		}
		if(kind.equals("")){
			condition += " and kind in (1,2) ";
					
		}
		if(kind.equals("1")){
			condition += " and user_id in (select friend_id from t_nf_friend where user_id = '"+userId+"') ";
					
		}
		if(kind.equals("2")){
			condition += " and user_id  = '"+userId+"'";
					
		}
	/*	if(!site.equals("")){
			condition += " and site  = '"+site+"'";
		}*/
		String order = "ORDER BY bbs_seq DESC";
		if(sort ==0){
			order = " order by reg_date desc";
		}else if(sort == 1) {
			order = "ORDER BY (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) ASC, reg_date DESC";
		}else if(sort == -1) {
			order = "ORDER BY (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) deSC, reg_date DESC";
		}else{
			
			condition += " and (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) between 0 and "+sort;
			order = "ORDER BY (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) ASC, reg_date DESC";
		}
		
		String query = ""
			+ "	SELECT * FROM ( "
			+ "		SELECT "
			+ "			ROW_NUMBER() OVER("+order+") as row_seq, "
			+ "			A.* "
			+ " , (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) as distance"
			+ "		FROM V_NF_BBS AS A "
			+ "	"+ condition 
			+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		
		return (List<Bbs>)this.jdbcTemplate.query(query, this.bbsMapperVisited);
		 
	}
	
	public Bbs getBbs(int bbsSeq, String userId, Double latitude, Double longitude) {
		String query = ""
			+ "	SELECT "
			+ "		A.* "
			+ "		, CASE WHEN (SELECT COUNT(*) FROM dbo.T_NF_BBS_VISIT WHERE bbs_seq = A.bbs_seq AND user_id = ?) = 0 THEN 0 ELSE 1 end AS visited "
            + "		, (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) as distance"
			+ "	FROM V_NF_BBS AS A "
			+ "	WHERE A.bbs_seq = ? ";
		return (Bbs)this.jdbcTemplate.queryForObject(query, new Object[] {userId, bbsSeq}, this.bbsMapperVisited);
	}
	//주연 
	public Bbs getBbsView(int bbsSeq,String userId) {
		String query = ""
			+ "	SELECT "
			+ "	* "
			+ "	FROM V_NF_BBS "
			+ "	WHERE bbs_seq = ? and user_id = ? and status=1 and writer_status = 1 and kind in (1,2) ";
//		try{
			return (Bbs)this.jdbcTemplate.queryForObject(query, new Object[] {bbsSeq,userId}, this.bbsMapperVisited2);
	/*	}catch(Exception e){
			return null;
		}*/
	}
	
	/*//푸시전송할 리스트 댓글의댓글 
	public List<Bbs> getBbsViewList(int bbsSeq,String userId) {
			String query = ""
				+ "	SELECT "
				+ "	* "
				+ "	FROM V_NF_BBS "
				+ "	WHERE bbs_seq = ? and user_id = ? ";
			return (Bbs)this.jdbcTemplate.queryForObject(query, new Object[] {bbsSeq,userId}, this.bbsMapperVisited2);
	}
*/
	public int getCount(String site,int category, String keyword, int gender, String area,int sort,Double latitude,Double longitude,String kind,String userId) {
		String condition = " WHERE blind_count = 0 and writer_blind_count = 0 and A.latitude>0 and A.longitude>0  and status=1 and writer_status = 1";
		
		if (category > 0) {
			condition += " AND bbs_category = "+category;
		}
		if (keyword.equals("") == false) {
			condition += " AND (bbs_contents like '%"+ keyword +"%' or user_name like '%"+ keyword +"%')";
		}
		if (gender > 0) {
			condition += " AND gender = "+ gender;
		}
		if (area.equals("") == false) {
			condition += " AND area = '"+ area +"'";
		}
		if(kind.equals("")){
			condition += " and kind in (1,2) ";
		}
		if(kind.equals("1")){
			condition += " and user_id in (select friend_id from t_nf_friend where user_id = '"+userId+"') ";
					
		}
		if(kind.equals("2")){
			condition += " and user_id  = '"+userId+"'";
					
		}
		/*if(!site.equals("")){
			condition += " and site  = '"+site+"'";
					
		}*/
		if (sort >1) {
			condition += " and (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) between 0 and "+sort;
		}
		String query = ""
			+ "	SELECT count(*) "
			+ "	FROM V_NF_BBS AS A "
			+ "	"+ condition;
		return this.jdbcTemplate.queryForInt(query);
	}
	//주연
	public List<Bbs> getABbsList(int bbsseq,String userId) {
		String query = ""
			+"select * from V_NF_BBS where bbs_seq= ? and user_id = ? ";
		return (List<Bbs>)this.jdbcTemplate.query(query,new Object[]{bbsseq,userId}, this.bbsMapperVisited2);
	}
	
	public List<Bbs> getBbsList(int page, int itemCountPerPage, int bbsGlory, String userId) {
		
		List<Bbs> result = null;
		String query = ""
			+ "	SELECT * FROM ( "
			+ "		SELECT "
			+ "			ROW_NUMBER() OVER(ORDER BY bbs_header DESC, reg_date DESC) as row_seq, "
			+ "			* "
			+ "			, CASE WHEN (SELECT COUNT(*) FROM dbo.T_NF_BBS_VISIT WHERE bbs_seq = A.bbs_seq AND user_id = ?) = 0 THEN 0 ELSE 1 end AS visited "
			+ "		FROM V_NF_BBS AS A "
			+ "		WHERE(bbs_glory = ? OR bbs_header = 1) "
			+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		result = (List<Bbs>)this.jdbcTemplate.query(query,new Object[]{ userId, bbsGlory }, this.bbsMapperVisited);
		return result;
	}
	public int getCount(int bbsGlory) {
		int result = 0;		
		if (bbsGlory == 1) {
			String query = "SELECT COUNT(*) AS cnt FROM V_NF_BBS WHERE (bbs_glory = ? ) ";
			result = this.jdbcTemplate.queryForInt(query, new Object[] { bbsGlory });
		} else {
			String query = "SELECT COUNT(*) AS cnt FROM V_NF_BBS";
			result = this.jdbcTemplate.queryForInt(query, new Object[] {});
		}
		return result;
	}

	public List<Bbs> getBbsListByCategory(int page, int itemCountPerPage, int bbsCategory, String loginId) {
		
		List<Bbs> result = null;
		if (bbsCategory == 0) {
			String query = ""
				+ "	SELECT * FROM ( "
				+ "		SELECT "
				+ "			ROW_NUMBER() OVER(ORDER BY bbs_header DESC, reg_date DESC) as row_seq, "
				+ "			* "
				+ "			, CASE WHEN (SELECT COUNT(*) FROM dbo.T_NF_BBS_VISIT WHERE bbs_seq = A.bbs_seq AND user_id = ?) = 0 THEN 0 ELSE 1 end AS visited "
				+ "		FROM V_NF_BBS AS A "
				+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
			result = (List<Bbs>)this.jdbcTemplate.query(query, new Object[]{ loginId }, this.bbsMapperVisited);
		} else {
			String query = ""
				+ "	SELECT * FROM ( "
				+ "		SELECT "
				+ "			ROW_NUMBER() OVER(ORDER BY bbs_header DESC, reg_date DESC) as row_seq, "
				+ "			* "
				+ "			, CASE WHEN (SELECT COUNT(*) FROM dbo.T_NF_BBS_VISIT WHERE bbs_seq = A.bbs_seq AND user_id = ?) = 0 THEN 0 ELSE 1 end AS visited "
				+ "		FROM V_NF_BBS AS A "
				+ "		WHERE(bbs_category = ? ) "
				+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
			result = (List<Bbs>)this.jdbcTemplate.query(query,new Object[]{ loginId, bbsCategory }, this.bbsMapperVisited);
		}
		
		return result;
	}
	

/*	public List<Bbs> getMyBbsList(int page, int itemCountPerPage, String userId,String keyword) {
		
		
		List<Bbs> result = null;
		String query = ""
			+ "	SELECT * FROM ( "
			+ "		SELECT "
			+ "			ROW_NUMBER() OVER(ORDER BY reg_date DESC) as row_seq, "
			+ "			* "
			+ "		FROM V_NF_BBS "
			+ "		WHERE user_id = ? "
			+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		result = (List<Bbs>)this.jdbcTemplate.query(query,new Object[]{ userId }, this.bbsMapper2);
		return result;
	}
	
	public int getMyBbsCount(String userId) {
		int result = 0;		
		String query = "SELECT COUNT(*) AS cnt FROM V_NF_BBS WHERE user_id = ? ";
		result = this.jdbcTemplate.queryForInt(query, new Object[] { userId });
		return result;
	}*/


	public List<Bbs> getMyBbsList(String site,int page, int itemCountPerPage, String userId, String keyword) {
		String con ="";
		if(!keyword.equals("")){
			con = " and bbs_contents like '%"+keyword+"%'";
		}
		
		List<Bbs> result = null;
		String query = ""
			+ "	SELECT * FROM ( "
			+ "		SELECT "
			+ "			ROW_NUMBER() OVER(ORDER BY reg_date DESC) as row_seq, "
			+ "			* "
			+ "		FROM V_NF_BBS "
			+ "		WHERE  writer_id <> ? and user_id = ? and kind <> 3 and status=1 and writer_status = 1 "+ con
			+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		result = (List<Bbs>)this.jdbcTemplate.query(query,new Object[]{ userId,userId}, this.bbsMapperVisited2);
		return result;
	}
	
	public int getMyBbsCount(String userId, String keyword) {
		
		String con ="";
		if(!keyword.equals("")){
			con = " and bbs_contents like '%"+keyword+"%'";
		}
		
		int result = 0;		
		String query = "SELECT COUNT(*) AS cnt FROM V_NF_BBS WHERE writer_id <> ? and user_id = ?  and kind <> 3  and status=1 and writer_status = 1"+con;
		result = this.jdbcTemplate.queryForInt(query, new Object[] { userId , userId});
		return result;
	}
	
	public List<Bbs> getsearchList(int page, int itemCountPerPage, String keyword) {
		
		List<Bbs> result = null;
		String query = ""
			+ "	SELECT * FROM ( "
			+ "		SELECT "
			+ "			ROW_NUMBER() OVER(ORDER BY reg_date DESC) as row_seq, "
			+ "			* "
			+ "		FROM V_NF_BBS "
			+ "		WHERE user_name like ? or bbs_contents like ? and blind_count = 0 "
			+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		result = (List<Bbs>)this.jdbcTemplate.query(query,new Object[]{ "%"+keyword+"%", "%"+keyword+"%" }, this.bbsMapper);
		return result;
	}
	
	
	
	
/*
	public int getsearchCount(String keyword) {
		int result = 0;		
		String query = "SELECT COUNT(*) AS cnt FROM V_NF_BBS WHERE user_name like ? or bbs_contents like ? ";
		result = this.jdbcTemplate.queryForInt(query, new Object[] { "%"+keyword+"%", "%"+keyword+"%" });
		return result;
	}
	*/
	
	
	

	// 내가쓴 댓글 리스트 2
	public List<Bbs> getMyBbsCommentListWithIdx(int page, int itemCountPerPage, String userId) {
		
		List<Bbs> result = null;
		String query = ""
			+ "	SELECT * FROM ( \n"
			+ "		SELECT \n"
			+ "			ROW_NUMBER() OVER(ORDER BY B.reg_date DESC) as row_seq, \n"
			+ "			--A.max_common_idx, \n"
			+ "			B.*, \n"
			+ "			( \n"
			+ "				SELECT row_seq \n"
			+ "				FROM ( \n"
			+ "					SELECT \n"
			+ "						ROW_NUMBER() OVER(ORDER BY reg_date DESC) as row_seq \n"
			+ "						,* \n"
			+ "					FROM T_NF_BBS_COMMENT \n"
			+ "					WHERE bbs_seq = B.bbs_seq \n"
			+ "				) AS C \n"
			+ "				WHERE C.bbs_comment_seq = A.max_common_idx \n"
			+ "			) AS list_idx \n"
			+ "		FROM \n"
			+ "			( \n"
			+ "				SELECT user_id, bbs_seq, MAX(bbs_comment_seq) as max_common_idx \n"
			+ "				FROM T_NF_BBS_COMMENT \n"
			+ "				WHERE user_id = ? \n"
			+ "				GROUP BY user_id, bbs_seq \n"
			+ "			) AS A LEFT OUTER JOIN \n"
			+ "			V_NF_BBS AS B ON B.bbs_seq = A.bbs_seq \n"
			+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		result = (List<Bbs>)this.jdbcTemplate.query(query,new Object[]{ userId }, this.bbsMapperListIdx);
		return result;
	}
	
	public int getMyBbsCommentCount(String userId) {
		int result = 0;		
		String query = "SELECT COUNT(*) AS cnt FROM V_NF_BBS WHERE bbs_seq in (SELECT bbs_seq FROM T_NF_BBS_COMMENT WHERE user_id = ?) ";
		result = this.jdbcTemplate.queryForInt(query, new Object[] { userId });
		return result;
	}
	
	public int getsearchCount(String keyword) {
		int result = 0;		
		String query = "SELECT COUNT(*) WHERE user_name like ? or bbs_contents like ? ";
		result = this.jdbcTemplate.queryForInt(query, new Object[] {  "%"+keyword+"%", "%"+keyword+"%" });
		return result;
	}
	
	//검색 리스트 
	public List<Bbs> getsearchList(int page, int itemCountPerPage, String userId, int category,int age, String keyword, int gender, String area, int sort, Double latitude, Double longitude) {
		String condition = " WHERE user_type = 4 and status=1 ";

		if (gender > 0) {
			condition += " AND gender = '"+ gender+"'";
		}
		if (area.equals("") == false) {
			condition += " AND area = '"+ area +"'";
		}
		if (age>0) {
			condition += " AND birth_year between '"+ (age-9) +"'and '"+(age)+"'" ;
		}
		if(keyword!=""){
			condition += " AND user_name like +'%"+keyword+"%'";
		}
		String order = "ORDER BY reg_date DESC";
		if(sort ==0){
			order =order;
		}else if (sort == 1) {
			order = "ORDER BY (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) ASC, birth_year DESC";
		}else if(sort ==-1){
			order = "ORDER BY (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) DESC, birth_year DESC";
		}else{
			condition += " and (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) between 0 and "+sort;
			order = "ORDER BY (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) ASC, birth_year DESC";
		}
		if (category==1){//인기도
			order = "ORDER BY count DESC, reg_date DESC";
		}
		if (category==2){//레벨별
			order =  "ORDER BY user_level DESC, reg_date DESC";
		}
		List<Bbs> result = null;
		String query = ""
			+ "	SELECT * FROM ( "
			+ "		SELECT "
			+ "			ROW_NUMBER() OVER("+order+") as row_seq, "
			+ "			A.*, "
            + "			 (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) as distance"
			+ "		FROM V_NF_USERF AS A "
			+ "	"+ condition
			+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		result = (List<Bbs>)this.jdbcTemplate.query(query, this.bbsMapper3);
		return result;
	}
	
	public int getCount(int category,int gender, String area,int age,String keyword,Double latitude, Double longitude,int sort) {
		String condition = " WHERE user_type = 4 and status=1  ";
		
		if (gender > 0) {
			condition += " AND gender = '"+ gender+"'";
		}
		if (area.equals("") == false) {
			condition += " AND area = '"+ area +"'";
		}
		if (age>0) {
			condition += " AND birth_year between '"+ (age-9) +"'and '"+(age)+"'" ;
		}
		if(keyword!=""){
			condition += " AND user_name like +'%"+keyword+"%'";
		}
		
		if (sort >1) {
			condition += " and (select dbo.Distance (latitude, longitude, "+latitude+", "+longitude+")) between 0 and "+sort;
		}
		
		String query = ""
			+ "	SELECT count(*) "
			+ "	FROM V_NF_USERF  "
			+ "	"+ condition;
		return this.jdbcTemplate.queryForInt(query);
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////
	
	// 관리자 토크 리스트 
	
	
		public List<Bbs> getVBbsList(String siteName,String site,int userType,int categorySeq,int gender, int age, String keyword, String areaSido, boolean reportCount, boolean blindCount, String startDate,String endDate, String colName,String sort,int page, int itemCountPerPage) {
			int year = Integer.parseInt(T.getTodayYear());
			String condition = " WHERE 1=1 AND dislike_count = 0 and kind in (1,2) ";
			
			/*if(userType!=1){
				condition += " AND site = '"+ site +"' ";
			}
			if(userType==1){
				
			}*/
			if(!siteName.equals("")){
				condition += " AND site = '"+ siteName +"' ";
			}
			if (gender != 0) {
				condition += " AND gender = "+ gender +" ";
			}
			if(categorySeq!=0){
				condition += " AND bbs_category = "+ categorySeq +" ";
			}
			if (age == 1) {
				condition += " AND (CAST(birth_year AS INT) between "+(year-19)+" and "+(year-10)+")";
			} else if (age == 2) {
				condition += " AND (CAST(birth_year AS INT) between "+(year-29)+" and "+(year-20)+" )";
			} else if (age == 3) {
				condition += " AND (CAST(birth_year AS INT) between "+(year-39)+"  and "+(year-30)+" )";
			} else if (age == 4) {
				condition += " AND (CAST(birth_year AS INT) between "+(year-49)+" and "+(year-40)+" )";
			} else if (age == 5) {
				condition += " AND (CAST(birth_year AS INT) between "+(year-89)+" and "+(year-50)+"  )";
			}
			
			if (keyword.equals("") == false) {
				condition += " AND user_name like '%"+ keyword +"%'";
			}
			if (areaSido.equals("") == false) {
				condition += " AND area like '%"+ areaSido +"%'";
			}
			
			if (reportCount != false) {
				condition += " AND report_count > 0";
			}
			
			if (blindCount != false) {
				condition += " AND blind_count > 0";
			}
			
			if(keyword.equals("") == false){
				
				condition+="OR bbs_contents like '%"+ keyword +"%'";
			}
			
			if(!startDate.equals("")&&!endDate.equals("")){
				
				condition+=" AND reg_date between '"+ startDate+ " 00:00:00' and  '"+endDate+" 23:59:59'" ;
			}
			String order= " reg_date ";
			String order2= " desc ";
		
			if(!colName.equals("")){
				order=" "+colName;
			}
			if(!sort.equals("")){
				order2=" "+sort; 
			}
			System.out.println(order2);		
			String query = ""
				+ "	SELECT * FROM ( "
				+ "		SELECT "
				+ "			ROW_NUMBER() OVER(order by "+order+order2+") as row_seq, "
				+ "			A.* "
				+ "		FROM V_NF_BBS AS A "
				+ "	"+ condition 
				+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
			
			return (List<Bbs>)this.jdbcTemplate.query(query, this.bbsMapperVisited2);
			
		
		}
		
		
		/** 관리자 토크 검색결과 갯수 **/
		public int getCount(String siteName,String site,int userType,int categorySeq,int gender, int age, String keyword, String areaSido, boolean reportCount, boolean blindCount, String startDate,String endDate) {
			
			int year = Integer.parseInt(T.getTodayYear());
			
			String condition = " WHERE 1=1 AND dislike_count = 0 and kind in (1,2) ";
		/*	if(userType!=1){
				condition += " AND site = '"+ site +"' ";
			}
			if(userType==1){
			
			}*/
			if(!siteName.equals("")){
				condition += " AND site = '"+ siteName +"' ";
			}
			if (gender != 0) {
				condition += " AND gender = "+ gender +" ";
			}
			if(categorySeq!=0){
				condition += " AND bbs_category = "+ categorySeq +" ";
			}
			if (age == 1) {
				condition += " AND (CAST(birth_year AS INT) between "+(year-19)+" and "+(year-10)+")";
			} else if (age == 2) {
				condition += " AND (CAST(birth_year AS INT) between "+(year-29)+" and "+(year-20)+" )";
			} else if (age == 3) {
				condition += " AND (CAST(birth_year AS INT) between "+(year-39)+"  and "+(year-30)+" )";
			} else if (age == 4) {
				condition += " AND (CAST(birth_year AS INT) between "+(year-49)+" and "+(year-40)+" )";
			} else if (age == 5) {
				condition += " AND (CAST(birth_year AS INT) between "+(year-89)+" and "+(year-50)+"  )";
			}
			
			if (keyword.equals("") == false) {
				condition += " AND user_name like '%"+ keyword +"%'";
			}
			if (areaSido.equals("") == false) {
				condition += " AND area like '%"+ areaSido +"%'";
			}
			
			if (reportCount != false) {
				condition += " AND report_count > 0";
			}
			
			if(keyword.equals("") == false){
				
				condition+="OR bbs_contents like '%"+ keyword +"%'";
			}
			
			if(!startDate.equals("")&&!endDate.equals("")){
				
				condition+=" AND reg_date between '"+ startDate+ " 00:00:00' and  '"+endDate+" 23:59:59'" ;
			}
			
		    String query = " SELECT COUNT(*) FROM V_NF_BBS "
		    		+ "	 "+condition+" " ;
		    return this.jdbcTemplate.queryForInt(query);
		}
		
		
		
		
		/** 토크 검색결과 갯수 **/
		public int getCount(int gender, int age, String keyword, String areaSido, boolean reportCount) {
			
			int year = Integer.parseInt(T.getTodayYear());
			
			String condition = " AND 1=1 AND dislike_count = 0 ";
			
			if (gender != 0) {
				condition += " AND u.gender = "+ gender +" ";
			}
			
			if (age == 1) {
				condition += " AND (CAST(u.birth_year AS INT) between "+(year-19)+" and "+(year-10)+")";
			} else if (age == 2) {
				condition += " AND (CAST(u.birth_year AS INT) between "+(year-29)+" and "+(year-20)+" )";
			} else if (age == 3) {
				condition += " AND (CAST(u.birth_year AS INT) between "+(year-39)+"  and "+(year-30)+" )";
			} else if (age == 4) {
				condition += " AND (CAST(u.birth_year AS INT) between "+(year-49)+" and "+(year-40)+" )";
			} else if (age == 5) {
				condition += " AND (CAST(u.birth_year AS INT) between "+(year-89)+" and "+(year-50)+"  )";
			}
			
			if (keyword.equals("") == false) {
				condition += " AND u.nick_name like '%"+ keyword +"%'";
			}
			if (areaSido.equals("") == false) {
				condition += " AND u.area like '%"+ areaSido +"%'";
			}
			
			if (reportCount == true) {
				condition += " AND b.report_count > 0";
			}
			
			if(keyword.equals("") == false){
				
				condition+="OR bbs_contents like '%"+ keyword +"%'";
			}
			
		    String query = " SELECT COUNT(*) FROM T_NF_BBS AS b LEFT OUTER JOIN "
		    		+ "		T_NF_USER AS u ON u.user_id = b.user_id "
		    		+ "		WHERE b.user_id IN (SELECT user_id FROM T_NF_USER "+condition+" )";
		    return this.jdbcTemplate.queryForInt(query);
		}
		
		
	//일반회원 토크 상세보기
	
	public List<Bbs> getGuestBookList(String userId, int page, int itemCountPerPage) {
			String query = ""
		            + "	SELECT * FROM ( "
		            + "		SELECT "
		            + "			ROW_NUMBER() OVER(ORDER BY bbs_seq DESC) as row_seq, "
		            + "			* "
		            + "		FROM V_NF_TALKLIST "
		            + "		WHERE user_id = ? "
		    		+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
			try {
				return (List<Bbs>)this.jdbcTemplate.query(query, new Object[] {userId}, this.bbsMapper2);
			} catch (Exception e) {
				return null;
			}
			
		}


		public int getCount(String userId) {
			int result = 0;
			String query = "SELECT COUNT(*) AS cnt FROM V_NF_TALKLIST WHERE user_id = ?";
			result = this.jdbcTemplate.queryForInt(query, new Object[] {userId});
			return result;
		}
		

		
		
	
	
	/********************************************************************************
	/* 러브다이아 소스
	********************************************************************************/
	




	

	
	public int getsearchCount(String userId, String keyword) {
		int result = 0;		
		String query = "SELECT COUNT(*) AS cnt FROM V_NF_BBS WHERE user_id like ? or bbs_contents like ? ";
		result = this.jdbcTemplate.queryForInt(query, new Object[] { "%"+keyword+"%", "%"+keyword+"%" });
		return result;
	}
	
	//메인화면 최근 토크  출력

	public List<Bbs> getBbsList(int topCount,String site,int userType) {
		String con=" where 1=1 ";
		if(userType!=1){
			con+=" and site = '"+site+"' ";
		}
		
		
		List<Bbs> result = null;
		String query = ""
				+ "	SELECT top "+topCount+" * "
				+ "	FROM V_NF_BBS  "+con
				+ " ORDER BY bbs_seq desc ";
		result = (List<Bbs>)this.jdbcTemplate.query(query, this.bbsMapper2);
		return result;
	}
	
////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
}
