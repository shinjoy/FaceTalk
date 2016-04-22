package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import kr.nomad.mars.dto.BannerAd;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class BannerAdDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper banneradMapper = new RowMapper() {
		public BannerAd mapRow(ResultSet rs, int rowNum) throws SQLException {
			BannerAd bannerad = new BannerAd();
			bannerad.setBannerSeq(rs.getInt("banner_seq"));
			bannerad.setTitle(rs.getString("title"));
			bannerad.setLinkUrl(rs.getString("link_url"));
			bannerad.setSido(rs.getString("sido"));
			bannerad.setGugun(rs.getString("gugun"));
			bannerad.setContentsHtml(rs.getString("contents_html"));
			bannerad.setContentsText(rs.getString("contents_text"));
			bannerad.setStartDate(rs.getString("start_date"));
			bannerad.setBackgroundColor(rs.getString("background_color"));
			bannerad.setEndDate(rs.getString("end_date"));
			bannerad.setVisible(rs.getInt("visible"));
			bannerad.setCountPush(rs.getInt("count_push"));
			bannerad.setCountView(rs.getInt("count_view"));
			bannerad.setCountClick(rs.getInt("count_click"));
			bannerad.setRegDate(rs.getString("reg_date"));
			bannerad.setSite(rs.getString("site"));
			return bannerad;
		}
	};
	
	private RowMapper VbanneradMapper = new RowMapper() {
		public BannerAd mapRow(ResultSet rs, int rowNum) throws SQLException {
			BannerAd bannerad = new BannerAd();
			bannerad.setBannerSeq(rs.getInt("banner_seq"));
			bannerad.setTitle(rs.getString("title"));
			bannerad.setLinkUrl(rs.getString("link_url"));
			bannerad.setSido(rs.getString("sido"));
			bannerad.setGugun(rs.getString("gugun"));
			bannerad.setContentsHtml(rs.getString("contents_html"));
			bannerad.setContentsText(rs.getString("contents_text"));
			bannerad.setStartDate(rs.getString("start_date"));
			bannerad.setBackgroundColor(rs.getString("background_color"));
			bannerad.setEndDate(rs.getString("end_date"));
			bannerad.setVisible(rs.getInt("visible"));
			bannerad.setCountPush(rs.getInt("count_push"));
			bannerad.setCountView(rs.getInt("count_view"));
			bannerad.setCountClick(rs.getInt("count_click"));
			bannerad.setRegDate(rs.getString("reg_date"));
			bannerad.setSite(rs.getString("site"));
			bannerad.setSiteName(rs.getString("site_name"));
			return bannerad;
		}
	};


	public void addBannerAd(final BannerAd bannerad) {
		String query = "" +
				"INSERT INTO T_NF_BANNER_AD " +
				"	(title, link_url, sido,"
				+ " gugun, contents_html, contents_text,"
				+ " start_date, end_date,background_color,"
				+ " visible, count_push, count_view,"
				+ " count_click, reg_date,site) " +
				"VALUES " +
				"	(?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, getDate(),?) ";
		this.jdbcTemplate.update(query, new Object[] {
			bannerad.getTitle(),
			bannerad.getLinkUrl(),
			bannerad.getSido(),
			bannerad.getGugun(),
			bannerad.getContentsHtml(),
			bannerad.getContentsText(),
			bannerad.getStartDate(),
			bannerad.getEndDate(),
			bannerad.getBackgroundColor(),
			bannerad.getVisible(),
			bannerad.getCountPush(),
			bannerad.getCountView(),
			bannerad.getCountClick(),
			bannerad.getSite()
		});
	}

	public void deleteBannerAd(int banner_seq) {
		String query = "DELETE FROM T_NF_BANNER_AD WHERE banner_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { banner_seq });
	}

	public void updateBannerAd(BannerAd bannerad) { 
		String query = "" + 
				"UPDATE T_NF_BANNER_AD SET " +
				"	title = ?, " +
				"	link_url = ?, " +
				"	sido = ?, " +
				"	gugun = ?, " +
				"	contents_html = ?, " +
				"	contents_text = ?, " +
				"	start_date = ?, " +
				"	end_date = ?, "+ 
				" 	background_color =?," +
				"	visible = ?, " +
				"	count_push = ?, " +
				"	count_view = ?, " +
				"	count_click = ?, " +
				"	site = ?, " +
				"	reg_date = getDate() " +
				"	WHERE banner_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			bannerad.getTitle(),
			bannerad.getLinkUrl(),
			bannerad.getSido(),
			bannerad.getGugun(),
			bannerad.getContentsHtml(),
			bannerad.getContentsText(),
			bannerad.getStartDate(),
			bannerad.getEndDate(),
			bannerad.getBackgroundColor(),
			bannerad.getVisible(),
			bannerad.getCountPush(),
			bannerad.getCountView(),
			bannerad.getCountClick(),
			bannerad.getSite(),
			bannerad.getBannerSeq()
		});
	}
	public void updateBannerAdView(int bannerSeq) { 
		String query = "" + 
				"UPDATE T_NF_BANNER_AD SET " +
				"	count_view = count_view + 1 " +
				"	WHERE banner_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { bannerSeq });
	}
	public void updateBannerAdClick(int bannerSeq) { 
		String query = "" + 
				"UPDATE T_NF_BANNER_AD SET " +
				"	count_click = count_click + 1 " +
				"	WHERE banner_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { bannerSeq });
	}

	public BannerAd getBannerAd(int banner_seq) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_BANNER_AD " +
				"WHERE banner_seq = ? ";
		try {
			return (BannerAd)this.jdbcTemplate.queryForObject(query, new Object[] { banner_seq }, this.banneradMapper);
		} catch (Exception e) {
			return new BannerAd();
		}
	}

	public BannerAd getBannerAdRandomOne(String site) {
		String con=" ";
		if(!site.equals("")){
			con += " AND site = '"+ site +"' ";
		}
		String query = ""
				+ "	SELECT top 1 *  "
				+ "	FROM T_NF_BANNER_AD "
				+ "	WHERE CONVERT(VARCHAR, GETDATE(), 112) >= start_date AND CONVERT(VARCHAR, GETDATE(), 112) <= end_date"
				+ " "+con
				+ "	ORDER BY NEWID() ";
		try {
			return (BannerAd)this.jdbcTemplate.queryForObject(query, this.banneradMapper);
		} catch (Exception e) {
			return null;
		}
	}

	public BannerAd getBannerAdRandomOne(String sido, String gugun) {
		String query = ""
				+ "	SELECT top 1 * "
				+ "	FROM T_NF_BANNER_AD "
				+ " WHERE sido = ? AND gugun = ? "
				+ "	ORDER BY NEWID() ";
		try {
			return (BannerAd)this.jdbcTemplate.queryForObject(query, new Object[] { sido, gugun}, this.banneradMapper);
		} catch (Exception e) {
			return null;
		}
	}

	public List<BannerAd> getBannerAdList() {
		String query = "" +
				"SELECT banner_seq, title, link_url, sido, gugun, contents_html, contents_text, start_date, end_date,background_color, visible, count_push, count_view, count_click, reg_date " +
				"FROM T_NF_BANNER_AD " +
				"ORDER BY banner_seq DESC";
		return (List<BannerAd>)this.jdbcTemplate.query(query, this.banneradMapper);
	}

	public int getLastSeq() {
		String query = " SELECT MAX(banner_seq) FROM T_NF_BANNER_AD ";
		return this.jdbcTemplate.queryForInt(query);
	}
	
	
	public List<BannerAd> getBannerAdMainList(String site,int userType,int page, int itemCountPerPage) {
		
		List<BannerAd> result = null;
		String condition=" where 1=1 ";
		if(userType!=1){
				condition += " AND site = '"+ site +"' ";
		}
		String query = ""
				+ "	SELECT * FROM ( "
				+ "	SELECT "
				+ "		ROW_NUMBER() OVER(ORDER BY banner_seq desc ) AS row_seq, "
				+ "		* "
				+ "	FROM V_NF_BANNER_AD " +condition
				+ ") AS row WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") + 1 AND "+page+" * "+itemCountPerPage+" ";
				
		result = (List<BannerAd>)this.jdbcTemplate.query(query, this.VbanneradMapper);
		
		return result;
	}
	
	public int getBannerAdMainCount(String site,int userType) {
		String condition=" where 1=1 ";
		if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}
		int result = 0;		
		String query = "SELECT COUNT(*) AS cnt FROM V_NF_BANNER_AD "+condition;
		result = this.jdbcTemplate.queryForInt(query);
				
		return result;
	}

}
