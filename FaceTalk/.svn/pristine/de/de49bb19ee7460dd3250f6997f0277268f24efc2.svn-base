package kr.nomad.mars.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import kr.nomad.mars.dto.Type;
import kr.nomad.util.T;

import kr.nomad.util.push.PushMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class TypeDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper typeMapper = new RowMapper() {
		public Type mapRow(ResultSet rs, int rowNum) throws SQLException {
			Type type = new Type();
			type.setSeq(rs.getInt("seq"));
			type.setSort(rs.getInt("sort"));
			type.setTypeCode(rs.getString("type_code"));
			type.setTypeName(rs.getString("type_name"));
			return type;
		}
	};

	public void addType(final Type type) {
		String query = "" +
				"INSERT INTO T_NF_TYPE " +
				"	(seq, sort, type_code, type_name) " +
				"VALUES " +
				"	(?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			type.getSeq(),
			type.getSort(),
			type.getTypeCode(),
			type.getTypeName()
		});
	}

	public void deleteType(String seq) {
		String query = "DELETE FROM T_NF_TYPE WHERE seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { seq });
	}

	public void updateType(Type type) { 
		String query = "" + 
				"UPDATE T_NF_TYPE SET " +
				"	seq = ?, " +
				"	sort = ?, " +
				"	type_code = ?, " +
				"	type_name = ? " +
				"WHERE seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			type.getSeq(),
			type.getSort(),
			type.getTypeCode(),
			type.getTypeName()
		});
	}

	public Type getType(String seq) {
		String query = "" + 
				"SELECT seq, sort, type_code, type_name " +
				"FROM T_NF_TYPE " +
				"WHERE seq = ? ";
		return (Type)this.jdbcTemplate.queryForObject(query, new Object[] { seq }, this.typeMapper);
	}

	public List<Type> getTypeList() {
		
		String query = "" +
				"SELECT * " +
				"FROM T_NF_TYPE " ;
			
		return (List<Type>)this.jdbcTemplate.query(query, this.typeMapper);
	}

}
