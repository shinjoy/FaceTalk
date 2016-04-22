package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Item;
import kr.nomad.mars.dto.LevelItem;

public class ItemDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper itemMapper = new RowMapper() {
		public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
			Item item = new Item();
			item.setSeq(rs.getInt("seq"));
			item.setItemName(rs.getString("item_name"));
			item.setItemCode(rs.getInt("item_code"));
			item.setPeriod(rs.getInt("period"));
			item.setPoint(rs.getInt("point"));
			item.setLimitCount(rs.getInt("limit_count"));
			item.setSite(rs.getString("site"));
			return item;
		}
	};

	public void addItem(final Item item) {
		String query = "" +
				"INSERT INTO T_NF_ITEM " +
				"	(item_name, item_code, period, point, limit_count,site) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			item.getItemName(),
			item.getItemCode(),
			item.getPeriod(),
			item.getPoint(),
			item.getLimitCount(),
			item.getSite()
		});
	}

	public void deleteItem(int seq) {
		String query = "DELETE FROM T_NF_ITEM WHERE seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { seq });
	}

	public void updateItem(Item item) { 
		String query = "" + 
				"UPDATE T_NF_ITEM SET " +
				"	item_name = ?, " +
				"	item_code = ?, " +
				"	period = ?, " +
				"	point = ?, " +
				"	limit_count = ?, " +
				"	site = ? " +
				"WHERE seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			
			item.getItemName(),
			item.getItemCode(),
			item.getPeriod(),
			item.getPoint(),
			item.getLimitCount(),
			item.getSite(),
			item.getSeq()
		
		});
	}
	

	public Item getItem(int seq) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_ITEM " +
				"WHERE seq = ? ";
		return (Item)this.jdbcTemplate.queryForObject(query, new Object[] { seq }, this.itemMapper);
	}

	//아이템 리스트 
	public List<Item> getItemList() {
		String query = "" +
				"select * from t_nf_item ";
		
		try{
			return (List<Item>)this.jdbcTemplate.query(query, this.itemMapper);
		}catch(Exception e){
			return new ArrayList();
		}
	}
	
	// 관리자 아이템 리스트 
	
	public List<Item> getItemList(String site) {
		
		List<Item> result = null;

		String query = ""
				+ "	SELECT * FROM  "
				
				+ "		 t_nf_item where site = ? ";
				
		result = (List<Item>)this.jdbcTemplate.query(query,new Object[] { site }, this.itemMapper);
		
		return result;
	}
	

}
