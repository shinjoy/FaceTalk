package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Bbs;
import kr.nomad.mars.dto.Order;
import kr.nomad.util.T;



public class OrderDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper orderMapper = new RowMapper() {
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order = new Order();
			order.setOrderSeq(rs.getInt("order_seq"));
			order.setUserId(rs.getString("user_id"));
			order.setOrderTitle(rs.getString("order_title"));
			order.setPriceSum(rs.getInt("price_sum"));
			order.setDiscountSum(rs.getInt("discount_sum"));
			order.setDeliveryFee(rs.getInt("delivery_fee"));
			order.setTotalFee(rs.getInt("total_fee"));
			order.setPaymentFee(rs.getInt("payment_fee"));
			order.setPaymentType(rs.getInt("payment_type"));
			order.setBankName(rs.getString("bank_name"));
			order.setAccountName(rs.getString("account_name"));
			order.setCardName(rs.getString("card_name"));
			order.setCardNumber(rs.getString("card_number"));
			order.setStatus(rs.getString("status"));
			order.setOrderDate(rs.getString("order_date"));
			order.setPayDate(rs.getString("pay_date"));
			order.setDeliveryDate(rs.getString("delivery_date"));
			order.setComment(rs.getString("comment"));
			order.setBuyerName(rs.getString("buyer_name"));
			order.setPostcode(rs.getString("postcode"));
			order.setAddress1(rs.getString("address1"));
			order.setAddress2(rs.getString("address2"));
			order.setPhone(rs.getString("phone"));
			order.setMobile(rs.getString("mobile"));
			return order;
		}
	};
	
	private RowMapper VorderMapper = new RowMapper() {
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order = new Order();
			order.setOrderSeq(rs.getInt("order_seq"));
			order.setUserId(rs.getString("user_id"));
			order.setOrderTitle(rs.getString("order_title"));
			order.setPriceSum(rs.getInt("price_sum"));
			order.setDiscountSum(rs.getInt("discount_sum"));
			order.setDeliveryFee(rs.getInt("delivery_fee"));
			order.setTotalFee(rs.getInt("total_fee"));
			order.setPaymentFee(rs.getInt("payment_fee"));
			order.setPaymentType(rs.getInt("payment_type"));
			order.setBankName(rs.getString("bank_name"));
			order.setAccountName(rs.getString("account_name"));
			order.setCardName(rs.getString("card_name"));
			order.setCardNumber(rs.getString("card_number"));
			order.setStatus(rs.getString("status"));
			order.setOrderDate(rs.getString("order_date"));
			order.setPayDate(rs.getString("pay_date"));
			order.setDeliveryDate(rs.getString("delivery_date"));
			order.setComment(rs.getString("comment"));
			order.setBuyerName(rs.getString("buyer_name"));
			order.setPostcode(rs.getString("postcode"));
			order.setAddress1(rs.getString("address1"));
			order.setAddress2(rs.getString("address2"));
			order.setPhone(rs.getString("phone"));
			order.setMobile(rs.getString("mobile"));
			order.setSite(rs.getString("site"));
			order.setSiteName(rs.getString("site_name"));
			return order;
		}
	};

	public void addOrder(final Order order) {
		String query = "" +
				"INSERT INTO T_NF_ORDER " +
				"	(user_id, order_title, price_sum, discount_sum, "
				+ "delivery_fee, total_fee, payment_fee, payment_type, "
				+ "bank_name, account_name, card_name, card_number, "
				+ "status, order_date, pay_date, delivery_date, "
				+ "comment, buyer_name, postcode, address1, "
				+ "address2, phone, mobile) " +
				"VALUES " +
				"	(?, ?, ?, ?,"
				+ "  ?, ?, ?, ?, "
				+ "  ?, ?, ?, ?,"
				+ "  ?, getDate(), ?, ?, "
				+ "  ?, ?, ?, ?, "
				+ "  ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			
			order.getUserId(),
			order.getOrderTitle(),
			order.getPriceSum(),
			order.getDiscountSum(),
			order.getDeliveryFee(),
			order.getTotalFee(),
			order.getPaymentFee(),
			order.getPaymentType(),
			order.getBankName(),
			order.getAccountName(),
			order.getCardName(),
			order.getCardNumber(),
			order.getStatus(),
			order.getPayDate(),
			order.getDeliveryDate(),
			order.getComment(),
			order.getBuyerName(),
			order.getPostcode(),
			order.getAddress1(),
			order.getAddress2(),
			order.getPhone(),
			order.getMobile()
		});
	}

	public void deleteOrder(int order_seq) {
		String query = "DELETE FROM T_NF_ORDER WHERE order_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { order_seq });
	}

	public void updateOrder(Order order) { 
		String query = "" + 
				"UPDATE T_NF_ORDER SET " +
				"	user_id = ?, " +
				"	order_title = ?, " +
				"	price_sum = ?, " +
				"	discount_sum = ?, " +
				"	delivery_fee = ?, " +
				"	total_fee = ?, " +
				"	payment_fee = ?, " +
				"	payment_type = ?, " +
				"	bank_name = ?, " +
				"	account_name = ?, " +
				"	card_name = ?, " +
				"	card_number = ?, " +
				"	status = ?, " +
				"	order_date = ?, " +
				"	pay_date = ?, " +
				"	delivery_date = ?, " +
				"	comment = ?, " +
				"	buyer_name = ?, " +
				"	postcode = ?, " +
				"	address1 = ?, " +
				"	address2 = ?, " +
				"	phone = ?, " +
				"	mobile = ? " +
				"WHERE order_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			order.getUserId(),
			order.getOrderTitle(),
			order.getPriceSum(),
			order.getDiscountSum(),
			order.getDeliveryFee(),
			order.getTotalFee(),
			order.getPaymentFee(),
			order.getPaymentType(),
			order.getBankName(),
			order.getAccountName(),
			order.getCardName(),
			order.getCardNumber(),
			order.getStatus(),
			order.getOrderDate(),
			order.getPayDate(),
			order.getDeliveryDate(),
			order.getComment(),
			order.getBuyerName(),
			order.getPostcode(),
			order.getAddress1(),
			order.getAddress2(),
			order.getPhone(),
			order.getMobile(),
			order.getOrderSeq()
		});
	}

	public Order getOrder(String order_seq) {
		String query = "" + 
				"SELECT order_seq, user_id, order_title, price_sum, discount_sum, delivery_fee, total_fee, payment_fee, payment_type, bank_name, account_name, card_name, card_number, status, order_date, pay_date, delivery_date, comment, buyer_name, postcode, address1, address2, phone, mobile " +
				"FROM T_NF_ORDER " +
				"WHERE order_seq = ? ";
		return (Order)this.jdbcTemplate.queryForObject(query, new Object[] { order_seq }, this.orderMapper);
	}

	
	// 관리자 결제 내역 리스트 
	
	
	public List<Order> getOrderList(String site,int userType,String colName,String sort,String keyword,String type, String startDate,String endDate,int page, int itemCountPerPage) {
		
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = " WHERE 1=1";
		
		if (keyword.equals("") == false) {
			condition += " AND user_id like '%"+ keyword +"%'";
		}
		/*if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}*/
		if(!startDate.equals("")&&!endDate.equals("")){
			
			condition+=" AND order_date between '"+ startDate+ " 00:00:00' and  '"+endDate+" 23:59:59'" ;
		}
		if (type.equals("") == false) {
			condition += " AND payment_type ="+type;
		}
		
		String query = ""
	            + "	SELECT * FROM ( "
	            + "		SELECT "
			    + "            ROW_NUMBER() OVER(ORDER BY "+colName +" " +sort+") as row_seq, "
	            + "			* "
	            + "		FROM V_NF_ORDER "
				+ "	 "+condition+" "
				 + "     ) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";

		return (List<Order>)this.jdbcTemplate.query(query,this.VorderMapper);
	}
	
	
	/** 관리자 결제 내역 검색결과 갯수 **/
	public int getCount(String site,int userType,String keyword,String type, String startDate,String endDate) {
		
		String condition = " WHERE 1=1";
		
		if (keyword.equals("") == false) {
			condition += " AND user_id like '%"+ keyword +"%'";
		}
		/*if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}*/
		if(!startDate.equals("")&&!endDate.equals("")){
			
			condition+=" AND order_date between '"+ startDate+ " 00:00:00' and  '"+endDate+" 23:59:59'" ;
		}
		if (type.equals("") == false) {
			condition += " AND payment_type ="+type;
		}
		
	    String query = " SELECT COUNT(*) FROM V_NF_ORDER "
	    		+ "	 "+condition+" " ;
	    return this.jdbcTemplate.queryForInt(query);
	}
	
	//충전 엑셀
	public List<Order> getOrderexcelList(String keyword,String type, String startDate,String endDate,String col,String sort) {
		
		
		String condition = " WHERE 1=1";
		
		if (keyword.equals("") == false) {
			condition += " AND user_id like '%"+ keyword +"%'";
		}
		if (type.equals("") == false) {
			condition += " AND payment_type ="+type;
		}
		
		if(!startDate.equals("")&&!endDate.equals("")){
			
			condition+=" AND order_date between '"+ startDate+ " 00:00:00' and  '"+endDate+" 23:59:59'" ;
		}
		
		String order =" order by " + col +" "+sort; 
		String query = ""
	            + "	SELECT * FROM  "
	            +" T_NF_ORDER "
				+ "	 "+condition+" "+order;

		return (List<Order>)this.jdbcTemplate.query(query,this.orderMapper);
	}
	
	

}
