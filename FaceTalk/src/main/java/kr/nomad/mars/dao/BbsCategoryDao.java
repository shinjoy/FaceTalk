package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.BbsCategory;

public class BbsCategoryDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper bbscategoryMapper = new RowMapper() {
		public BbsCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
			BbsCategory bbscategory = new BbsCategory();
			bbscategory.setCategorySeq(rs.getInt("category_seq"));
			bbscategory.setCategoryName(rs.getString("category_name"));
			bbscategory.setCategorySort(rs.getInt("category_sort"));
			return bbscategory;
		}
	};

	public int getLastSeq() {
		String query = " SELECT MAX(category_sort) FROM T_NF_BBS_CATEGORY ";
		return this.jdbcTemplate.queryForInt(query);
	}
	
	public void addBbsCategory(final BbsCategory bbscategory) {
		String query = "" +
				"INSERT INTO T_NF_BBS_CATEGORY " +
				"	(category_name, category_sort) " +
				"VALUES " +
				"	(?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			bbscategory.getCategoryName(),
			bbscategory.getCategorySort()
		});
	}

	public void deleteBbsCategory(int category_seq) {
		String query = "DELETE FROM T_NF_BBS_CATEGORY WHERE category_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { category_seq });
	}

	public void updateBbsCategory(BbsCategory bbscategory) { 
		String query = "" + 
				"UPDATE T_NF_BBS_CATEGORY SET " +
				"	category_name = ?, " +
				"	category_sort = ? " +
				"WHERE category_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			bbscategory.getCategoryName(),
			bbscategory.getCategorySort(),
			bbscategory.getCategorySeq()
		});
	}

	public BbsCategory getBbsCategory(int category_seq) {
		String query = "" + 
				"SELECT *" +
				"FROM T_NF_BBS_CATEGORY " +
				"WHERE category_seq = ? ";
		return (BbsCategory)this.jdbcTemplate.queryForObject(query, new Object[] { category_seq }, this.bbscategoryMapper);
	}

	public List<BbsCategory> getBbsCategoryList() {
		String query = "" +
				"SELECT * " +
				"FROM T_NF_BBS_CATEGORY " +
				"ORDER BY category_sort ASC ";
		return (List<BbsCategory>)this.jdbcTemplate.query(query, this.bbscategoryMapper);
	}
	
	public List<BbsCategory> getadminBbsCategoryList() {
		String query = "" +
				"SELECT * " +
				"FROM T_NF_BBS_CATEGORY where category_seq > 0 " +
				"ORDER BY category_sort ASC ";
		return (List<BbsCategory>)this.jdbcTemplate.query(query, this.bbscategoryMapper);
	}
}
