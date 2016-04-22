package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Area;

public class AreaDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper areaMapper = new RowMapper() {
		public Area mapRow(ResultSet rs, int rowNum) throws SQLException {
			Area area = new Area();
			area.setAreaSeq(rs.getInt("area_seq"));
			area.setAreaSido(rs.getString("area_sido"));
			area.setAreaGugun(rs.getString("area_gugun"));
			return area;
		}
	};

	public void addArea(final Area area) {
		String query = "" +
				"INSERT INTO T_NF_AREA " +
				"	(area_seq, area_sido, area_gugun) " +
				"VALUES " +
				"	(?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			area.getAreaSeq(),
			area.getAreaSido(),
			area.getAreaGugun()
		});
	}

	public void deleteArea(int area_seq) {
		String query = "DELETE FROM T_NF_AREA WHERE area_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { area_seq });
	}

	public void updateArea(Area area) { 
		String query = "" + 
				"UPDATE T_NF_AREA SET " +
				"	area_seq = ?, " +
				"	area_sido = ?, " +
				"	area_gugun = ? " +
				"WHERE area_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			area.getAreaSeq(),
			area.getAreaSido(),
			area.getAreaGugun()
		});
	}

	public Area getArea(int area_seq) {
		String query = "" + 
				"SELECT area_seq, area_sido, area_gugun " +
				"FROM T_NF_AREA " +
				"WHERE area_seq = ? ";
		return (Area)this.jdbcTemplate.queryForObject(query, new Object[] { area_seq }, this.areaMapper);
	}

	public List<Area> getAreaList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" area_seq, area_sido, area_gugun " +
				"FROM T_NF_AREA " +
				"WHERE area_seq <= ( " +
				"	SELECT MIN(area_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" area_seq " +
				"		FROM T_NF_AREA " +
				"		ORDER BY area_seq DESC " +
				"	) AS A) " +
				"ORDER BY area_seq DESC";
		return (List<Area>)this.jdbcTemplate.query(query, this.areaMapper);
	}
		
	
	public List getSidoList() {
		String query = "SELECT DISTINCT area_sido,sort_id FROM T_NF_AREA order by sort_id ";
		return this.jdbcTemplate.queryForList(query);
	}
	
	
}
