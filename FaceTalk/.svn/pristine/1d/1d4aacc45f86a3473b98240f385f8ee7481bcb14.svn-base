package kr.nomad.mars.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import kr.nomad.mars.dto.Expense;
import kr.nomad.util.T;
import kr.nomad.util.push.PushKey;
import kr.nomad.util.push.PushMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ExpenseDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper expenseMapper = new RowMapper() {
		public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
			Expense expense = new Expense();
			expense.setExpenseSeq(rs.getInt("expense_seq"));
			expense.setUserId(rs.getString("user_id"));
			expense.setPoint(rs.getInt("point"));
			expense.setPayPoint(rs.getInt("pay_point"));
			expense.setRequestDate(rs.getString("request_date"));
			expense.setFinishDate(rs.getString("finish_date"));
			expense.setType(rs.getString("type"));
			expense.setBank(rs.getString("bank"));
			expense.setBankCount(rs.getString("bank_count"));
			expense.setOwnerName(rs.getString("owner_name"));
			expense.setComment(rs.getString("comment"));
			return expense;
		}
	};
	
	private RowMapper expenseMapper2 = new RowMapper() {
		public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
			Expense expense = new Expense();
			expense.setExpenseSeq(rs.getInt("expense_seq"));
			expense.setUserId(rs.getString("user_id"));
			expense.setPoint(rs.getInt("point"));
			expense.setPayPoint(rs.getInt("pay_point"));
			expense.setRequestDate(rs.getString("request_date"));
			expense.setFinishDate(rs.getString("finish_date"));
			expense.setType(rs.getString("type"));
			expense.setUserName(rs.getString("user_name"));
			expense.setStatus(rs.getInt("status"));
			expense.setIncome(rs.getInt("income"));
			expense.setUpoint(rs.getInt("upoint"));
			expense.setBank(rs.getString("bank"));
			expense.setBankCount(rs.getString("bank_count"));
			expense.setOwnerName(rs.getString("owner_name"));
			expense.setComment(rs.getString("comment"));
			expense.setSite(rs.getString("site"));
			expense.setSiteName(rs.getString("site_name"));
			return expense;
		}
	};
	//환급 신청
	public void addExpense(final Expense expense) {
		String query = "" +
				"INSERT INTO T_NF_POINT_EXPENSE " +
				"	(user_id, point, pay_point, request_date, "
				+ "  finish_date, type, status,"
				+ "   bank, bank_count, "
				+ "  owner_name,comment) " +
				"VALUES " +
				"	( ?, ?, ?,getDate(), "
				+ "   ?, ?, ?,"
				+ "   ?, ?, "
				+ "   ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			
			expense.getUserId(),
			expense.getPoint(),
			expense.getPayPoint(),
			expense.getFinishDate(),
			expense.getType(),
			expense.getStatus(),
			expense.getBank(),
			expense.getBankCount(),
			expense.getOwnerName(),
			expense.getComment()
			
		});
	}
	

	public void deleteExpense(String userId) {
		String query = "DELETE FROM T_NF_POINT_EXPENSE WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { userId });
	}
	//완료시 업데이트
	public void updateExpense(int seq,int status,String comment) { 
		String query = "" + 
				"UPDATE T_NF_POINT_EXPENSE SET " +
				"	status = ?, " +
				"  finish_date = getDate(), "+
				" comment = ? "+
				"WHERE expense_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
				status,comment,seq
		});
	}

/*	public void updateExpense(Expense expense) { 
		String query = "" + 
				"UPDATE T_NF_POINT_EXPENSE SET " +
				"	expense_seq = ?, " +
				"	user_id = ?, " +
				"	point = ?, " +
				"	pay_point = ?, " +
				"	request_date = ?, " +
				"	finish_date = ?, " +
				"	type = ? " +
				"WHERE expense_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			expense.getExpenseSeq(),
			expense.getUserId(),
			expense.getPoint(),
			expense.getPayPoint(),
			expense.getRequestDate(),
			expense.getFinishDate(),
			expense.getType()
		});
	}*/
	//유저정보
	public Expense getExpense(int expenseSeq) {
		String query = "" + 
				"SELECT * " +
				"FROM V_NF_POINT " +
				"WHERE expense_seq = ? ";
		return (Expense)this.jdbcTemplate.queryForObject(query, new Object[] { expenseSeq }, this.expenseMapper2);
	}

	/*public List<Expense> getExpenseList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" expense_seq, user_id, point, pay_point, request_date, finish_date, type " +
				"FROM T_NF_POINT_EXPENSE " +
				"WHERE expense_seq <= ( " +
				"	SELECT MIN(expense_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" expense_seq " +
				"		FROM T_NF_POINT_EXPENSE " +
				"		ORDER BY expense_seq DESC " +
				"	) AS A) " +
				"ORDER BY expense_seq DESC";
		return (List<Expense>)this.jdbcTemplate.query(query, this.expenseMapper);
	}*/
	
	//환급 
	public List<Expense> getExpenseList(String site,int userType,String colName,String sort,String type,String keyword, int status,String bankName, int page, int itemCountPerPage) {
		String con="   where type = '"+type +"' and status = "+status;
		
		if(userType!=1){
			con += " AND site = '"+ site +"' ";
		}
		
		if(!keyword.equals("")){
			con+=" and user_id like '%"+keyword+"%'" ;
		}
		if(!bankName.equals("")){
			con+=" and bank like '%"+bankName+"%'";
			
		}
		
		String query = "" 
				+ "    SELECT * FROM ( "
			    + "        SELECT "
			    + "            ROW_NUMBER() OVER(ORDER BY "+colName +" " +sort+") as row_seq, "
			    + "            * "
			    + "        FROM V_NF_POINT "
			    + "  "+con
			    + "     ) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		return (List<Expense>)this.jdbcTemplate.query(query, this.expenseMapper2);
	}
	
	public int getExpenseCnt(String site,int userType,String type,String keyword,int status,String bankName){
		
		String con="   where type = '"+type +"' and status = "+status;
		if(userType!=1){
			con += " AND site = '"+ site +"' ";
		}
		if(!keyword.equals("")){
			con+=" and user_id like '%"+keyword+"%'" ;
		}
		if(!bankName.equals("")){
			con+=" and bank like '%"+bankName+"%'";
			
		}
		String query = "" +
				"SELECT count(*) " +
				"FROM V_NF_POINT "+con ;
				
		return this.jdbcTemplate.queryForInt(query);		
		
	}
	
	//환급완료 
	public List<Expense> getFinishList(String site,int userType,String colName,String sort,String keyword,int status,String startDate,String endDate, int page, int itemCountPerPage) {
		String con="   where status = "+status ;
		if(keyword!=""){
			con+=" and user_id like '%"+keyword+"%' " ;
		}
		
		if(!startDate.equals("")&&!endDate.equals("")){
			
			con+=" and request_date between '"+ startDate+ " 00:00:00' and  '"+endDate+" 23:59:59'" ;
		}
		/*if(userType!=1){
			con += " AND site = '"+ site +"' ";
		}*/
		String query = "" 
				+ "    SELECT * FROM ( "
			    + "        SELECT "
			    + "            ROW_NUMBER() OVER(ORDER BY "+colName +" " +sort+") as row_seq, "
			    + "            * "
			    + "        FROM V_NF_POINT "
			    + "  "+con
			    + "     ) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		return (List<Expense>)this.jdbcTemplate.query(query, this.expenseMapper2);
	}
	
	public int getFinishCnt(String site,int userType,String keyword,int status,String startDate,String endDate){
		
		String con="   where status = "+status ;
		
		if(keyword!=""){
			con+=" and user_id like '%"+keyword+"%'" ;
		}
		/*if(userType!=1){
			con += " AND site = '"+ site +"' ";
		}*/
		
		if(!startDate.equals("")&&!endDate.equals("")){
			
			con+=" and request_date between '"+ startDate+ " 00:00:00' and  '"+endDate+" 23:59:59'" ;
		}
		
		String query = "" +
				"SELECT count(*) " +
				"FROM V_NF_POINT "+con ;
				
		return this.jdbcTemplate.queryForInt(query);		
		
	}
	

	//엑셀
	public List<Expense> getExpenseexcelList(String type,String keyword, int status,String bankName,String startDate,String endDate,String col,String sort) {
		String con="   where status = "+status;
		if(!type.equals("")){
			con+=" and  type = '"+type +"'" ;
		}
		if(!keyword.equals("")){
			con+=" and user_id like '%"+keyword+"%'" ;
		}
		if(!bankName.equals("")){
			con+=" and bank like '%"+bankName+"%'";
		}
		if(!startDate.equals("")&&!endDate.equals("")){
			
			con+=" and request_date between '"+ startDate+ " 00:00:00' and  '"+endDate+" 23:59:59'" ;
		}
		String order = " order by "+col+" "+sort;
		
		String query = "" 
				+ "    SELECT * FROM  "
			 
			    + "       V_NF_POINT "
			    
			    + "  "+con 
			    +order;
			
		return (List<Expense>)this.jdbcTemplate.query(query, this.expenseMapper2);
	}
	

}
