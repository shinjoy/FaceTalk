package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import kr.nomad.mars.dto.Site;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class SiteDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper siteMapperorg = new RowMapper() {
		public Site mapRow(ResultSet rs, int rowNum) throws SQLException {
			Site site = new Site();
			site.setSiteSeq(rs.getInt("site_seq"));
			site.setSiteName(rs.getString("site_name"));
		
			site.setServiceId(rs.getString("service_id"));
			site.setPackageName(rs.getString("package_name"));
			return site;
		}
	};
	private RowMapper siteMapper = new RowMapper() {
		public Site mapRow(ResultSet rs, int rowNum) throws SQLException {
			Site site = new Site();
			site.setSiteSeq(rs.getInt("site_seq"));
			site.setSiteName(rs.getString("site_name"));
			site.setSiteUrl(rs.getString("site_url"));
			site.setCompanyName(rs.getString("company_name"));
			site.setCompanyPhone(rs.getString("company_phone"));
			site.setManagerName(rs.getString("manager_name"));
			site.setRegDate(rs.getString("reg_date"));
			site.setUserCount(rs.getInt("user_count"));
			site.setManagerCount(rs.getInt("manager_count"));
			site.setServiceId(rs.getString("service_id"));
			site.setPackageName(rs.getString("package_name"));
			return site;
		}
	};

	public void addSite(final Site site) {
		String query = "" +
				"INSERT INTO T_NF_SITE " +
				"	(site_name, site_url, company_name,"
				+ " company_phone, manager_name, reg_date,"
				+ " service_id,package_name) " +
				"VALUES " +
				"	(?, ?, ?,"
				+ " ?, ?, getDate(),"
				+ " ?,?) ";
		this.jdbcTemplate.update(query, new Object[] {
			site.getSiteName(),
			site.getSiteUrl(),
			site.getCompanyName(),
			site.getCompanyPhone(),
			site.getManagerName(),
			site.getServiceId(),
			site.getPackageName()
		});
	}

	public void deleteSite(int site_seq) {
		String query = "DELETE FROM T_NF_SITE WHERE site_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { site_seq });
	}

	public void updateSite(Site site) { 
		String query = "" + 
				"UPDATE T_NF_SITE SET " +
				"	site_name = ?, " +
				"	site_url = ?, " +
				"	company_name = ?, " +
				"	company_phone = ?, " +
				"	manager_name = ?, " +
				"	service_id = ?, " +
				"	package_name = ? " +
				"WHERE site_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			site.getSiteName(),
			site.getSiteUrl(),
			site.getCompanyName(),
			site.getCompanyPhone(),
			site.getManagerName(),
			site.getServiceId(),
			site.getPackageName(),
			site.getSiteSeq()
		});
	}

	public Site getSite(int site_seq) {
		String query = "" + 
				"SELECT * " +
				"FROM V_NF_SITE " +
				"WHERE site_seq = ? ";
		try {
			return (Site)this.jdbcTemplate.queryForObject(query, new Object[] { site_seq }, this.siteMapper);
		} catch (Exception e) {
			return new Site();
		}
	}
	
	public Site getSiteServiceId(String  serviceId) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_SITE " +
				"WHERE service_id = ? ";
		try {
			return (Site)this.jdbcTemplate.queryForObject(query, new Object[] { serviceId }, this.siteMapperorg);
		} catch (Exception e) {
			return new Site();
		}
	}

	public List<Site> getSiteList() {
		String query = ""
				+ "	SELECT * "
				+ "	FROM V_NF_SITE "
				+ "	ORDER BY site_seq ASC";
		return (List<Site>)this.jdbcTemplate.query(query, this.siteMapper);
	}
}
