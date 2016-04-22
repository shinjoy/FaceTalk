package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import kr.nomad.mars.dto.Bbs;
import kr.nomad.mars.dto.User;
import kr.nomad.util.T;
import kr.nomad.util.push.PushKey;
import kr.nomad.util.push.PushMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	private RowMapper userMapper = new RowMapper() {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserSeq(rs.getInt("user_seq"));
			user.setAgentId(rs.getString("agent_id"));
			user.setUserId(rs.getString("user_id"));
			user.setPassword(rs.getString("password"));
			user.setUserType(rs.getInt("user_type"));
			user.setUserName(rs.getString("user_name"));
			user.setEmail(rs.getString("email"));
			user.setNickName(rs.getString("nick_name"));
			user.setPhoneNumber(rs.getString("phone_number"));
			user.setIntro(rs.getString("intro"));
			user.setAddress(rs.getString("address"));
			user.setLatitude(rs.getDouble("latitude"));
			user.setLongitude(rs.getDouble("longitude"));
			user.setBirthYear(rs.getInt("birth_year"));
			user.setGender(rs.getInt("gender"));
			user.setArea(rs.getString("area"));
			user.setRegDate(rs.getString("reg_date"));
			user.setLastLogindate(rs.getString("last_logindate"));
			user.setLoginFacebook(rs.getInt("login_facebook"));
			user.setLoginKakao(rs.getInt("login_kakao"));
			user.setOsType(rs.getString("os_type"));
			user.setOsVersion(rs.getString("os_version"));
			user.setAppVersion(rs.getString("app_version"));
			user.setDeviceName(rs.getString("device_name"));
			user.setDeviceId(rs.getString("device_id"));
			user.setPushkey(rs.getString("pushkey"));
			user.setUsePushservice(rs.getInt("use_pushservice"));
			user.setStatus(rs.getInt("status"));
			user.setPoint(rs.getInt("point"));
			user.setIncome(rs.getInt("income"));
			user.setPhoto(rs.getString("photo"));
			user.setUserLevel(rs.getInt("user_level"));
			user.setLevelPoint(rs.getInt("level_point"));
			user.setComment(rs.getString("comment"));
			user.setPhotoRegDate(rs.getString("photo_reg_date"));
			user.setSite(rs.getString("site"));
			user.setDropReason(rs.getString("drop_reason"));
			user.setPhotoStatus(rs.getInt("photo_status"));
			user.setFirstBbs(rs.getInt("first_bbs"));
			
			return user;
		}
	};
	private RowMapper userMapper2 = new RowMapper() {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserSeq(rs.getInt("user_seq"));
			user.setAgentId(rs.getString("agent_id"));
			user.setUserId(rs.getString("user_id"));
			user.setPassword(rs.getString("password"));
			user.setUserType(rs.getInt("user_type"));
			user.setUserName(rs.getString("user_name"));
			user.setEmail(rs.getString("email"));
			user.setNickName(rs.getString("nick_name"));
			user.setPhoneNumber(rs.getString("phone_number"));
			user.setIntro(rs.getString("intro"));
			user.setAddress(rs.getString("address"));
			user.setLatitude(rs.getDouble("latitude"));
			user.setLongitude(rs.getDouble("longitude"));
			user.setBirthYear(rs.getInt("birth_year"));
			user.setGender(rs.getInt("gender"));
			user.setArea(rs.getString("area"));
			user.setRegDate(rs.getString("reg_date"));
			user.setLastLogindate(rs.getString("last_logindate"));
			user.setLoginFacebook(rs.getInt("login_facebook"));
			user.setLoginKakao(rs.getInt("login_kakao"));
			user.setOsType(rs.getString("os_type"));
			user.setOsVersion(rs.getString("os_version"));
			user.setAppVersion(rs.getString("app_version"));
			user.setDeviceName(rs.getString("device_name"));
			user.setDeviceId(rs.getString("device_id"));
			user.setPushkey(rs.getString("pushkey"));
			user.setUsePushservice(rs.getInt("use_pushservice"));
			user.setStatus(rs.getInt("status"));
			user.setPoint(rs.getInt("point"));
			user.setIncome(rs.getInt("income"));
			user.setPhoto(rs.getString("photo"));
			user.setUserLevel(rs.getInt("user_level"));
			user.setLevelPoint(rs.getInt("level_point"));
			user.setComment(rs.getString("comment"));
			user.setSite(rs.getString("site"));
			user.setSiteName(rs.getString("site_name"));
			user.setLoginStatus(rs.getInt("login_status"));
			user.setDropReason(rs.getString("drop_reason"));
			return user;
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

	private RowMapper chatmemMapper = new RowMapper() {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			
			user.setUserId(rs.getString("user_id"));
			user.setUserName(rs.getString("user_name"));
			user.setBirthYear(rs.getInt("birth_year"));
			user.setGender(rs.getInt("gender"));
			user.setRegDate(rs.getString("reg_date"));
			user.setUserLevel(rs.getInt("user_level"));
			user.setOwner(rs.getInt("owner"));
			user.setChatRoomSeq(rs.getInt("chat_room_seq"));
		
		
			return user;
		}
	};
	private RowMapper chatmemMapper2 = new RowMapper() {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			
			user.setUserId(rs.getString("user_id"));
			user.setUserName(rs.getString("user_name"));
			user.setBirthYear(rs.getInt("birth_year"));
			user.setGender(rs.getInt("gender"));
			user.setRegDate(rs.getString("reg_date"));
			user.setUserLevel(rs.getInt("user_level"));
			user.setOwner(rs.getInt("owner"));
			user.setChatRoomSeq(rs.getInt("chat_room_seq"));
			user.setPhoto(rs.getString("photo"));
		
			return user;
		}
	};
	public void addUser(final User user) {
		String query = "" +
				"INSERT INTO T_NF_USER " +
				"	(user_id, password, agent_id, comment "
				+ " ,user_type, user_name, email, "
				+ "   nick_name, phone_number, intro, "
				+ "    address, latitude, longitude,"
				+ "    birth_year, gender, area, "
				+ "    reg_date, last_logindate, login_facebook,"
				+ "    login_kakao, os_type, os_version, "
				+ "    app_version, device_name, device_id, "
				+ "    pushkey, use_pushservice, status,"
				+ "    point, income, photo,"
				+ "    user_level, level_point, site,"
				+ "    photo_reg_date, drop_reason,photo_status,first_bbs) " +
				"VALUES " +
				"	(?, ?, ?, ?, "
				+ "  ?, ?, ?, "
				+ "  ?, ?, ?, "
				+ "  ?, ?, ?, "
				+ "  ?, ?, ?, "
				+ "  getDate(), ?, ?, "
				+ "  ?, ?, ?, "
				+ "  ?, ?, ?, "
				+ "  ?, ?, ?, "
				+ "  ?, ?, ?, "
				+ "  ?, ?, ?, "
				+ "  getDate(), ?, ?,?) ";
		this.jdbcTemplate.update(query, new Object[] {
			user.getUserId(),
			user.getPassword(),
			user.getAgentId(),
			user.getComment(),
			user.getUserType(),
			user.getUserName(),
			user.getEmail(),
			user.getNickName(),
			user.getPhoneNumber(),
			user.getIntro(),
			user.getAddress(),
			user.getLatitude(),
			user.getLongitude(),
			user.getBirthYear(),
			user.getGender(),
			user.getArea(),
			user.getLastLogindate(),
			user.getLoginFacebook(),
			user.getLoginKakao(),
			user.getOsType(),
			user.getOsVersion(),
			user.getAppVersion(),
			user.getDeviceName(),
			user.getDeviceId(),
			user.getPushkey(),
			user.getUsePushservice(),
			user.getStatus(),
			user.getPoint(),
			user.getIncome(),
			user.getPhoto(),
			user.getUserLevel(),
			user.getLevelPoint(),
			user.getSite(),
		
			user.getDropReason(),
			user.getPhotoStatus(),
			user.getFirstBbs()
		});
	}
	
	/*//다시
	public void addUser1(final User user) {
		String query = "" +
				"INSERT INTO T_NF_USER " +
				"	(user_id, password, comment ,user_type, user_name, email, nick_name, phone_number, intro, address, latitude, longitude, birth_year, gender, area, reg_date, last_logindate, login_facebook, login_kakao, os_type, os_version, app_version, device_name, device_id, pushkey, use_pushservice, status, point, income, photo, user_level, level_point, site, photo_reg_date) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, getDate(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, getDate()) ";
		this.jdbcTemplate.update(query, new Object[] {
			user.getUserId(),
			user.getPassword(),
			user.getComment(),
			user.getUserType(),
			user.getUserName(),
			user.getEmail(),
			user.getNickName(),
			user.getPhoneNumber(),
			user.getIntro(),
			user.getAddress(),
			user.getLatitude(),
			user.getLongitude(),
			user.getBirthYear(),
			user.getGender(),
			user.getArea(),
			user.getLastLogindate(),
			user.getLoginFacebook(),
			user.getLoginKakao(),
			user.getOsType(),
			user.getOsVersion(),
			user.getAppVersion(),
			user.getDeviceName(),
			user.getDeviceId(),
			user.getPushkey(),
			user.getUsePushservice(),
			user.getStatus(),
			user.getPoint(),
			user.getIncome(),
			user.getPhoto(),
			user.getUserLevel(),
			user.getLevelPoint(),
			user.getSite()
		});
	}*/
	

	public void deleteUser(String user_id) {
		String query = "DELETE FROM T_NF_USER WHERE  user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { user_id });
	}

	public void updateManager(User user) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				
				"	agent_id = ?, " +
				"	user_type = ?, " +
				"	user_name = ?, " +
				"   comment = ?, "+	
				"	phone_number = ?, " +
				"	intro = ?, " +
				"	address = ?, " +
				"	latitude = ?, " +
				"	longitude = ?, " +
				"	birth_year = ?, " +
				"	gender = ?, " +
				"	area = ?, " +
				"	point = ?, " +
				"	income = ?, " +
				"	photo = ?, " +
				"	user_level = ?, " +
				"	level_point = ?, " +
				"	photo_reg_date = getDate(), " +
				"	site = ? " +
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			
			user.getAgentId(),
			user.getUserType(),
			user.getUserName(),
			user.getComment(),
			user.getPhoneNumber(),
			user.getIntro(),
			user.getAddress(),
			user.getLatitude(),
			user.getLongitude(),
			user.getBirthYear(),
			user.getGender(),
			user.getArea(),
			user.getPoint(),
			user.getIncome(),
			user.getPhoto(),
			user.getUserLevel(),
			user.getLevelPoint(),
			user.getSite(),
			user.getUserId()
		});
	}
	public void updateUser(User user) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				
				"	agent_id = ?, " +
				"	user_type = ?, " +
				"	user_name = ?, " +
				"	email = ?, " +
				"   comment = ?, "+	
				"	nick_name = ?, " +
				"	phone_number = ?, " +
				"	intro = ?, " +
				"	address = ?, " +
				"	latitude = ?, " +
				"	longitude = ?, " +
				"	birth_year = ?, " +
				"	gender = ?, " +
				"	area = ?, " +
				"	reg_date = ?, " +
				"	last_logindate = ?, " +
				"	login_facebook = ?, " +
				"	login_kakao = ?, " +
				"	os_type = ?, " +
				"	os_version = ?, " +
				"	app_version = ?, " +
				"	device_name = ?, " +
				"	device_id = ?, " +
				"	pushkey = ?, " +
				"	use_pushservice = ?, " +
				"	status = ?, " +
				"	point = ?, " +
				"	income = ?, " +
				"	photo = ?, " +
				"	user_level = ?, " +
				"	level_point = ?, " +
				"	photo_reg_date = getDate(), " +
				"	site = ? " +
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			
			user.getAgentId(),
			user.getUserType(),
			user.getUserName(),
			user.getEmail(),
			user.getComment(),
			user.getNickName(),
			user.getPhoneNumber(),
			user.getIntro(),
			user.getAddress(),
			user.getLatitude(),
			user.getLongitude(),
			user.getBirthYear(),
			user.getGender(),
			user.getArea(),
			user.getRegDate(),
			user.getLastLogindate(),
			user.getLoginFacebook(),
			user.getLoginKakao(),
			user.getOsType(),
			user.getOsVersion(),
			user.getAppVersion(),
			user.getDeviceName(),
			user.getDeviceId(),
			user.getPushkey(),
			user.getUsePushservice(),
			user.getStatus(),
			user.getPoint(),
			user.getIncome(),
			user.getPhoto(),
			user.getUserLevel(),
			user.getLevelPoint(),
			user.getSite(),
			user.getUserId()
		});
	}
	
	public void updateUser(String userId,int usermoney,int userpoint,int saveMoney ,int collevel) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				"	point = ?, " +
				"	income = ?, " +
				"	user_level = ?, " +
				"	level_point = ? " +
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
				userpoint,
				usermoney,
				collevel,
				saveMoney,
				userId
		});
	}
	
	public void updatePointUser(String userId,int point) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				"	point = ? " +
			
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
				point,userId
		});
	}
	public void updateUser(String userId,int usermoney,int userpoint) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				"	point = ?, " +
				"	income = ? " +
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
				userpoint,
				usermoney,
				userId
		});
	}
	
	public void outUser(int type,String userid,String dropReason) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				"	status = ? , " +
				"	photo = '' ," +
				"  drop_reason = ?, " +
				"  device_id = '', " +
				"  pushkey = '' " +
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
				type,dropReason,userid
		});
	}

	public void updatedUser(int type,String userid,String dropReason) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				"	status = ? , " +
				"	photo = '' ," +
				"	phone_number ='',  " +
				"  drop_reason = ?, " +
				"  device_id = '', " +
				"  pushkey = '' " +
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
				type,dropReason,userid
		});
	}
	
	public void updatedUserBBS(String userid) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				"  first_bbs = 1 " +
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query,new Object[] {userid});
	}

	public User getUser(String user_id) {
		String query = "" + 
				"SELECT * " +
				"FROM V_NF_USER " +
				"WHERE user_id = ? ";
		try {
			return (User)this.jdbcTemplate.queryForObject(query, new Object[] { user_id }, this.userMapper2);
			
		} catch (Exception e) {
			return new User();
		}
		

	}
	
	public List<User> getAgentUser(String user_id) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_USER " +
				"WHERE agent_id = ? ";
		try {
			return (List<User>)this.jdbcTemplate.query(query, new Object[] { user_id }, this.userMapper);
			
		} catch (Exception e) {
			return new ArrayList<>();
		}
		

	}
	
	public User getUsers(String user_id) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_USER " +
				"WHERE user_id = ? ";
		try {
			return (User)this.jdbcTemplate.queryForObject(query, new Object[] { user_id }, this.userMapper);
			
		} catch (Exception e) {
			return new User();
		}
		

	}



	public List<User> getUserList(int page, int itemCountPerPage) {
		String query = "" 
			 	+ "	SELECT * FROM ( "
	            + "		SELECT "
	            + "			ROW_NUMBER() OVER(ORDER BY user_name ASC) as row_seq, "
	            + "			* "
	            + "		FROM V_NF_USER "
	            + "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		return (List<User>)this.jdbcTemplate.query(query, this.userMapper2);
	}
	public int getCount() {
		String query = " SELECT COUNT(*) FROM T_NF_USER ";
		return this.jdbcTemplate.queryForInt(query);
	}
	public List<User> getUserList(String site,String keyword, int page, int itemCountPerPage) {
		String con=" where 1=1 ";
		if(!keyword.equals("")){
			con+=" and (user_id like '%"+keyword+"%' OR user_name like '%"+keyword+"%') ";
		}
	/*	if(!site.equals("")){
			con+=" and site = '"+site+"' ";
		}*/
		
		String query = "" 
			 	+ "	SELECT * FROM ( "
	            + "		SELECT "
	            + "			ROW_NUMBER() OVER(ORDER BY user_name ASC) as row_seq, "
	            + "			* "
	            + "		FROM V_NF_USER "
	            + "		 "+con
	            + "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		return (List<User>)this.jdbcTemplate.query(query, this.userMapper2);
	}
	public int getUserCount(String site,String keyword) {
		String con=" where 1=1 ";
		if(!keyword.equals("")){
			con+=" and (user_id like '%"+keyword+"%' OR user_name like '%"+keyword+"%') ";
		}
		/*if(!site.equals("")){
			con+=" and site = '"+site+"' ";
		}*/
		String query = " SELECT COUNT(*) FROM T_NF_USER "+con;
		return this.jdbcTemplate.queryForInt(query);
	}
	
	/**
	 * 아이디 중복 확인
	 * @param userId
	 * @return
	 */
	public boolean correctId(String userId) {
		String query = "SELECT COUNT(*) FROM T_NF_USER WHERE user_id = ? ";
		return (this.jdbcTemplate.queryForInt(query, new Object[] { userId }) == 1);
	}
	
	public boolean correctIdSite(String userId,String site) {
		String query = "SELECT COUNT(*) FROM T_NF_USER WHERE user_id = ? and site = ? ";
		return (this.jdbcTemplate.queryForInt(query, new Object[] { userId,site }) == 1);
	}
	public boolean correctAgentId(String userId) {
		String query = "SELECT COUNT(*) FROM T_NF_USER WHERE user_id = ? AND user_type = 3 ";
		return (this.jdbcTemplate.queryForInt(query, new Object[] { userId }) == 1);
	}
	
	//탈퇴하지않은 회원 아이디 조회
	public boolean correctActiveId(String userId) {
		String query = "SELECT COUNT(*) FROM T_NF_USER WHERE user_id = ? and status = 1 ";
		return (this.jdbcTemplate.queryForInt(query, new Object[] { userId }) == 1);
	}
	
	
	/**
	 * 비밀번호찾기
	 * @param userId
	 * @return
	 */
	public boolean correctIdphone(String userId,String phone) {
		String query = "SELECT COUNT(*) FROM T_NF_USER WHERE user_id = ? and phone_number= ? and status = 1";
		return (this.jdbcTemplate.queryForInt(query, new Object[] { userId,phone }) == 1);
	}
	
	/**
	 * 아이디 패스워드 일치 확인
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean correctPw(String userId, String password) {		
	    String query = "SELECT count(*) AS id_cnt FROM T_NF_USER WHERE user_id = ? and password = ?";
	    try {
		    return this.jdbcTemplate.queryForInt(query, new Object[] { userId, password}) > 0;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 닉네임 중복 확인
	 * @param userId
	 * @return
	 */
	public boolean correctNick(String userName) {
		String query = "SELECT COUNT(*) FROM T_NF_USER WHERE user_name = ? ";
		return (this.jdbcTemplate.queryForInt(query, new Object[] { userName }) == 1);
	}
	
	/**
	 * 전화번호 중복 확인
	 * @param phoneNumber
	 * @return
	 */
	public boolean correctPhone(String phoneNumber,String site) {
		String query = "SELECT COUNT(*) FROM T_NF_USER WHERE phone_number = ? and site = ? ";
		return (this.jdbcTemplate.queryForInt(query, new Object[] { phoneNumber,site }) == 1);
	}
	
	/**
	 * 아이디 찾기시
	 * @param phoneNumber
	 * @return
	 */
	public boolean correctPhonesearch(String phoneNumber) {
		String query = "SELECT COUNT(*) FROM T_NF_USER WHERE phone_number = ? and status = 1 ";
		return (this.jdbcTemplate.queryForInt(query, new Object[] { phoneNumber }) == 1);
	}



	/**
	 * 회원 푸시키 저장
	 * @param userId
	 * @param pushKey
	 */
	public void updateUserPushKey(String userId, String pushKey) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				"	pushkey = ? " +
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { pushKey, userId });
	}
	public void updateUserLogin(User user) { 
		String query = ""
				+ "	UPDATE T_NF_USER SET "
				+ "		pushkey = ?, "
				+ "		latitude = ?, "
				+ "		longitude = ?, "
				+ "		login_status = 1, "
				+ "		last_logindate = getDate() "
				+ "	WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
				user.getPushkey(),
				user.getLatitude(),
				user.getLongitude(),
				user.getUserId()
		});
	}

	
	public void updateMobileUserLogin(User user) { 
		String query = ""
				+ "	UPDATE T_NF_USER SET "
				+ "		pushkey = ?, "
				+ "		latitude = ?, "
				+ "		longitude = ?, "
				+ "		login_status = 1, "
				+ "		last_logindate = getDate(), "				
				+ "		os_type = ?, "
				+ "		device_id = ? "				
				
				+ "	WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
				user.getPushkey(),
				user.getLatitude(),
				user.getLongitude(),				
				user.getOsType(),
				user.getDeviceId(),								
			
				user.getUserId()
		});
	}
	
	
	
	public void updatePcUserLogin(User user) { 
		String query = ""
				+ "	UPDATE T_NF_USER SET "
				+ "		pushkey = ?, "
				+ "		latitude = ?, "
				+ "		longitude = ?, "
				+ "		login_status = 1, "
				+ "		last_logindate = getDate(), "				
				+ "		os_type = ?, "
				+ "		device_id_windows = ? "								
				+ "	WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
				user.getPushkey(),
				user.getLatitude(),
				user.getLongitude(),				
				user.getOsType(),
				user.getDeviceId(),								
			
				user.getUserId()
				
		});
	}
	
	//로그아웃
	
	public void updateoutlogin(String userId) { 
		String query = ""
				+ "	UPDATE T_NF_USER SET "
				+ "		pushkey = '', "
				+ "		device_id = '', "
				+ "		login_status = 0 "
				+ "	WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { userId });
	}
	
	/**
	 * 프로필 사진 등록
	 * @param user
	 */
	public void updateProfileImgAdd(User user) {
		String query = "" +
				"UPDATE T_NF_USER SET " +
				" photo = ?, photo_reg_date = getDate() " +
				"WHERE user_id = ?" ;
			this.jdbcTemplate.update(query,new Object[]{
				user.getPhoto(),
				user.getUserId()
			});
	}
	
	/**
	 * 프로필 사진 삭제
	 * @param userId
	 */
	public void updateProfileImgDel(String userId) {
		String query = "" +
				"UPDATE T_NF_USER SET " +
				" photo = '' " +
				"WHERE user_id = ?" ;
			this.jdbcTemplate.update(query,new Object[]{
				userId
			});
	}
	
	
	
	/**
	 * 아이디 찾기
	 * @param phoneNumber
	 * @return
	 */
	public User getUserByPhone(String phoneNumber) {
		String query = "" + 
				"SELECT * " +
				"FROM V_NF_USER " +
				"WHERE phone_number = ? ";
		return (User)this.jdbcTemplate.queryForObject(query, new Object[] { phoneNumber }, this.userMapper2);
		
	}
	
	
	/**
	 * 비밀번호 변경
	 * @param userId
	 * @param userPassword
	 */
	public boolean updateUserPassword(String userId, String password) { 
		boolean res= false;
		String query = "" + 
				"	UPDATE T_NF_USER  " +
				"	SET password = ? " +
				"	WHERE user_id = ? ";
		try {
			this.jdbcTemplate.update(query, new Object[] { password, userId });
			res=true;
			return res;
		} catch (Exception e) {
			// TODO: handle exception
			return res;
		}
		
	}

	
	/** 일반회원 검색 목록 **/
	public List<User> getUserList(int siteSeq, int gen, int gender, int age, String keyword, String areaSido, int page, int itemCountPerPage) {
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = " WHERE 1=1 and user_type = 4 and (status=1) ";

		if (siteSeq != 0) {
			condition += " AND site = "+ siteSeq +" ";
		}
		
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
		
		if (gen != 0) {
			condition += " AND gender = "+ gen +" ";
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
			condition += " AND (user_id like '%"+ keyword +"%' OR user_name like '%"+ keyword +"%') ";
		}
		if (areaSido.equals("") == false) {
			condition += " AND area like '%"+ areaSido +"%'";
		}
		
		String query = ""
	            + "    SELECT * FROM ( "
	            + "        SELECT "
	            + "            ROW_NUMBER() OVER(ORDER BY user_id desc) as row_seq, "
	            + "            * "
	            + "        FROM V_NF_USER "
	            + "  		"+condition+" "
	            + "    ) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
	    return (List<User>)this.jdbcTemplate.query(query, this.userMapper2);
	}
	
	
	
	public List<User> getUserList(int status,String site,int userType,String siteName, int gen, int gender, int age, String keyword, String areaSido, String colName, String sort, int page, int pageItem) {
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = " WHERE 1=1 and user_type = 4  ";

		if(status!=0){
			condition+=" and status ="+status;
		}
		if (!siteName.equals("")) {
			condition += " AND site = '"+ siteName +"' ";
		}
		
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
		
		if (gen != 0) {
			condition += " AND gender = "+ gen +" ";
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
		/*if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}*/
		
		if (keyword.equals("") == false) {
			condition += " AND (user_id like '%"+ keyword +"%' OR user_name like '%"+ keyword +"%' OR phone_number like '%"+ keyword +"%' ) ";
		}
		
		if (areaSido.equals("") == false) {
			condition += " AND area like '%"+ areaSido +"%'";
		}
		
		String query = ""
	            + "    SELECT * FROM ( "
	            + "        SELECT "
	            + "            ROW_NUMBER() OVER(ORDER BY "+colName +" "+ sort +") as row_seq, "
	            + "            * "
	            + "        FROM V_NF_USER "
	            +condition 
	            + "    ) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+pageItem+") +1 AND "+page+" * "+pageItem+" ";
	    return (List<User>)this.jdbcTemplate.query(query, this.userMapper2);
	}
	
	
	/** 일반회원 검색결과 갯수 **/
	public int getCount(int status,String site,int userType,String siteName,  int gen, int gender, int age, String keyword, String areaSido) {
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = " WHERE 1=1 and user_type = 4 ";
		if(status!=0){
			condition+=" and status ="+status;
		}
		if (!siteName.equals("")) {
			condition += " AND site = '"+ siteName +"' ";
		}
		
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
		if (gen != 0) {
			condition += " AND gender = "+ gen +" ";
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
			condition += " AND (user_id like '%"+ keyword +"%' OR user_name like '%"+ keyword +"%' OR phone_number like '%"+ keyword +"%'  ) ";
		}
		if (areaSido.equals("") == false) {
			condition += " AND area like '%"+ areaSido +"%'";
		}
		/*if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}*/
	    String query = " SELECT COUNT(*) FROM V_NF_USER "+ condition;
	    return this.jdbcTemplate.queryForInt(query);
	}

	
	/** 접속중 검색 목록 **/
	public List<User> getUserAccessList(String site,int userType,String siteName, int gen, int gender, int age, String keyword, String areaSido,String colName, String sort, int page, int itemCountPerPage) {
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = " WHERE 1=1 and user_type = 4 and login_status =1 AND status=1";
		/*if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}*/
		if (!siteName.equals("")) {
			condition += " AND site = '"+ siteName +"' ";
		}
		
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
		
		if (gen != 0) {
			condition += " AND gender = "+ gen +" ";
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
			condition += " AND (user_id like '%"+ keyword +"%' OR user_name like '%"+ keyword +"%' OR phone_number like '%"+ keyword +"%') ";
		}
		if (areaSido.equals("") == false) {
			condition += " AND area like '%"+ areaSido +"%'";
		}
		
		String query = ""
	            + "    SELECT * FROM ( "
	            + "        SELECT "
	            + "            ROW_NUMBER() OVER(ORDER BY "+colName +" " +sort+") as row_seq, "
	            + "            * "
	            + "        FROM V_NF_USER "
	            + "  		"+condition+" "
	            + "    ) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
	    return (List<User>)this.jdbcTemplate.query(query, this.userMapper2);
	}
	
	
	/** 접속중 검색결과 갯수 **/
	public int getAccessCount(String site,int userType,String siteName, int gen, int gender, int age, String keyword, String areaSido) {
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = " WHERE 1=1 and user_type = 4 and login_status = 1 AND status=1 ";

		/*if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}*/
		if (!siteName.equals("")) {
			condition += " AND site = '"+ siteName +"' ";
		}
		
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
		if (gen != 0) {
			condition += " AND gender = "+ gen +" ";
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
			condition += " AND (user_id like '%"+ keyword +"%' OR user_name like '%"+ keyword +"%' OR phone_number like '%"+ keyword +"%') ";
		}
		if (areaSido.equals("") == false) {
			condition += " AND area like '%"+ areaSido +"%'";
		}
		
	    String query = " SELECT COUNT(*) FROM V_NF_USER "+ condition;
	    return this.jdbcTemplate.queryForInt(query);
	}
	
	
	
	
	
	
	//** 탈퇴회원 검색 목록 **//*
	public List<User> getUserDropList(String site,int userType,String siteName, int gen, int gender, int age, String keyword, String areaSido,String colName ,String sort, int page, int itemCountPerPage) {
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = " WHERE 1=1 and user_type = 4 and (status=2 or status=4) ";

		/*if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}*/
		if (!siteName.equals("")) {
			condition += " AND site = '"+ siteName +"' ";
		}
		
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
		
		if (gen != 0) {
			condition += " AND gender = "+ gen +" ";
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
			condition += " AND (user_id like '%"+ keyword +"%' OR user_name like '%"+ keyword +"%' OR phone_number like '%"+ keyword +"%') ";
		}
		if (areaSido.equals("") == false) {
			condition += " AND area like '%"+ areaSido +"%'";
		}
		
		String query = ""
	            + "    SELECT * FROM ( "
	            + "        SELECT "
	            + "            ROW_NUMBER() OVER(ORDER BY "+colName +" " +sort+") as row_seq, "
	            + "            * "
	            + "        FROM V_NF_USER "
	            + "  		"+condition+" "
	            + "    ) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
	    return (List<User>)this.jdbcTemplate.query(query, this.userMapper2);
	}
	
	
	//** 탈퇴회원 검색결과 갯수 **//*
	public int getDropCount(String site,int userType,String siteName, int gen, int gender, int age, String keyword, String areaSido) {
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = " WHERE 1=1 and user_type = 4 and (status=2 or status=4)";

		/*if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}*/
		if (!siteName.equals("")) {
			condition += " AND site = '"+ siteName +"' ";
		}
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
		if (gen != 0) {
			condition += " AND gender = "+ gen +" ";
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
			condition += " AND (user_id like '%"+ keyword +"%' OR user_name like '%"+ keyword +"%' OR phone_number like '%"+ keyword +"%') ";
		}
		if (areaSido.equals("") == false) {
			condition += " AND area like '%"+ areaSido +"%'";
		}
		
	    String query = " SELECT COUNT(*) FROM V_NF_USER "+ condition;
	    return this.jdbcTemplate.queryForInt(query);
	}
	
	
	// 일반회원 엑셀다운로드
	public List<User> getUser() {
		String query = ""
				+ "SELECT *  "
				+ "FROM V_NF_USER WHERE user_type = 4 AND (status=1 or status=3) ORDER BY user_id ";
		return (List<User>) this.jdbcTemplate.query(query, this.userMapper2);
	}
	
	
	/** 오름차순 내림차순 
	 * @return **/
	public String getSorting(String item, boolean itemValue) {
		
		String query = "";
		
		if(itemValue == true){
			query= " SELECT * FROM V_NF_USER ORDER BY site_name DESC ";
		}else{
			query = " SELECT * FROM V_NF_USER ORDER BY site_name ASC ";
		}
		
	    return query;
	}
	
	
	
	// 관리자 검색목록
	public List<User> getAdminList(String site,int luserType,String siteName, String keyword ,String colName, String sort, int userType, int page, int itemCountPerPage) {
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = " WHERE 1=1 and user_type in (1,2,3)  ";

		/*if(luserType!=1){
			condition += " AND site = '"+ site +"' ";
		}*/
		if (!siteName.equals("")) {
			condition += " AND site = '"+ siteName +"' ";
		}
		
		if (keyword.equals("") == false) {
			condition += " AND (user_id like '%"+ keyword +"%' OR user_name like '%"+ keyword +"%' OR phone_number like '%"+ keyword +"%') ";
		}
		

		if (userType != 0) {
			condition += " AND user_type = "+ userType +" ";
		}
		
		String query = ""
	            + "    SELECT * FROM ( "
	            + "        SELECT "
	            + "            ROW_NUMBER() OVER(ORDER BY "+colName +" "+ sort +") as row_seq, "
	            + "            * "
	            + "        FROM V_NF_USER "
	            +condition 
	            + "    ) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
	    return (List<User>)this.jdbcTemplate.query(query, this.userMapper2);
	}
	
	
	/** 관리자 검색결과 갯수 **/
	public int getAdminCount(String site,int luserType,String siteName, String keyword, int userType) {
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = " WHERE 1=1 and user_type in (1,2,3) ";
/*
		if(luserType!=1){
			condition += " AND site = '"+ site +"' ";
		}*/
		if (!siteName.equals("")) {
			condition += " AND site = '"+ siteName +"' ";
		}
		
		if (keyword.equals("") == false) {
			condition += " AND (user_id like '%"+ keyword +"%' OR user_name like '%"+ keyword +"%' OR phone_number like '%"+ keyword +"%') ";
		}
		
		if (userType != 0) {
			condition += " AND user_type = "+ userType +" ";
		}
		
	    String query = " SELECT COUNT(*) FROM V_NF_USER "+ condition;
	    return this.jdbcTemplate.queryForInt(query);
	}
	
	
	
	
	/** 관리자 검색 목록 **/
	public List<User> getAdminList(int siteSeq, String keyword,int page, int itemCountPerPage) {
		
		String condition = " WHERE 1=1 and user_type in (1,2,3) ";

		if (siteSeq != 0) {
			condition += " AND site = "+ siteSeq +" ";
		}

		if (keyword.equals("") == false) {
			condition += " AND (user_id like '%"+ keyword +"%' OR user_name like '%"+ keyword +"%' OR phone_number like '%"+ keyword +"%') ";
		}
//		if (areaSido.equals("") == false) {
//			condition += " AND area like '%"+ areaSido +"%'";
//		}

		
		String query = ""
	            + "    SELECT * FROM ( "
	            + "        SELECT "
	            + "            ROW_NUMBER() OVER(ORDER BY user_id desc) as row_seq, "
	            + "            * "
	            + "        FROM V_NF_USER "
	            + "  		"+condition+" "
	            + "    ) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
	    return (List<User>)this.jdbcTemplate.query(query, this.userMapper2);
	}
	
	
/*	/** 관리자 검색결과 갯수 
	public int getAdminCount(int siteSeq, String keyword) {
		
		String condition = " WHERE 1=1 and user_type between 1 and 3";

		if (siteSeq != 0) {
			condition += " AND site = "+ siteSeq +" ";
		}

		if (keyword.equals("") == false) {
			condition += " AND (user_id like '%"+ keyword +"%' OR user_name like '%"+ keyword +"%') ";
		}
//		if (areaSido.equals("") == false) {
//			condition += " AND area like '%"+ areaSido +"%'";
//		}
		
	    String query = " SELECT COUNT(*) FROM T_NF_USER "+ condition;
	    return this.jdbcTemplate.queryForInt(query);
	}
*/	
	
	// 관리자 엑셀다운로드
	public List<User> getAdmin(String site,int userType) {
		String condition="";
	/*	if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}*/
		String query = ""
				+ "SELECT *  "
				+ "FROM V_NF_USER WHERE user_type BETWEEN 1 AND 3 "
				+ " "+condition
				+ "ORDER BY user_id ";
		return (List<User>) this.jdbcTemplate.query(query, this.userMapper2);
	}

	public List<User> getChatUser(String site,int userType) {
		String con="";
		if(userType!=1){
			con+=" and site='"+site+"'";
		}
		String query = ""
				+ "SELECT *  "
				+ "FROM V_NF_USER WHERE user_type = 3 "
				+ " "+con
				+ "	ORDER BY user_id ";
		return (List<User>) this.jdbcTemplate.query(query, this.userMapper2);
	}

	/**
	 * 푸시정보
	 * @return
	 */
	public List<PushKey> getPushList(String userId) {
		try {
			String query = ""
					+ "SELECT user_id, pushkey, os_type, use_pushservice "
					+ "FROM t_nf_user "
					+ "WHERE NOT(pushkey is null OR pushkey = '') AND NOT(os_type is null OR os_type = '') AND user_id = ? ";	
			return (List<PushKey>)this.jdbcTemplate.query(query, new Object[] {userId}, PushMapper.pushMapper );
		} catch (Exception e) {
			// TODO: handle exception
			return null;			
		}		
	}
	
	
	//메인화면 최근 등록 회원 출력

	public List<User> getUserList(int topCount,String site,int userType) {
		String con=" ";
		if(userType!=1){
			con+=" and site = '"+site+"' ";
		}
		List<User> result = null;
		String query = ""
				+ "	SELECT top "+topCount+" * "
				+ "	FROM V_NF_USER where status=1 and user_type=4 "+con
				+ " ORDER BY reg_date desc ";
		result = (List<User>)this.jdbcTemplate.query(query, this.userMapper2);
		return result;
	}
	
	// 푸시키 사이트 리스트 값 가지고 오기
	public List<User> getUserList(String site) {
			String query = "" + 
					" SELECT * " +
					" FROM T_NF_USER WHERE site='"+site+"'" ;

			return (List<User>)this.jdbcTemplate.query(query, this.userMapper);
	 
	}
	
	// 푸시키 리스트 값 가지고 오기
	public List<User> getUserList() {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_USER WHERE pushkey <> '' and pushkey IS NOT NULL and use_pushservice = 1 " ;

		return (List<User>)this.jdbcTemplate.query(query, this.userMapper);
 
	}
	
	
	/** 남자 회원수 **/
	public int getUserManCount(int userType,String site) {
		String con="";
		if(userType!=1){
			con+=" and site = '"+site+"' ";
		}
	    String query = " SELECT COUNT(*) FROM T_NF_USER WHERE gender = 1 and user_type=4 and status =1 "+con;
	    return this.jdbcTemplate.queryForInt(query);
	}
	
	/** 여자 회원수 **/
	public int getUserWomanCount(int userType,String site) {
		String con="";
		if(userType!=1){
			con+=" and site = '"+site+"' ";
		}
	    String query = " SELECT COUNT(*) FROM T_NF_USER WHERE gender = 2 and user_type=4 and status =1 "+con ;
	    return this.jdbcTemplate.queryForInt(query);
	}
	
	/** 접속중 회원수 **/
	public int getUserAccessCount(int userType,String site) {
		String con="";
		if(userType!=1){
			con+=" and site = '"+site+"' ";
		}
	    String query = " SELECT COUNT(*) FROM T_NF_USER WHERE user_type=4 and login_status =1 AND status=1"+con ;
	    return this.jdbcTemplate.queryForInt(query);
	}
	
	public List<User> getfsearchList(int page, int itemCountPerPage, 
			String userId, int category,
			int age, String keyword,
			int gender, String area,
			int sort, Double latitude, Double longitude) {
		String condition = " and A.latitude>0 and A.longitude>0 ";
		
		if(keyword!=""){
			condition += " AND user_name like +'%"+keyword+"%'";
		}
		if (gender > 0) {
			condition += " AND gender = '"+ gender+"'";
		}
		if (area.equals("") == false) {
			condition += " AND area = '"+ area +"'";
		}
		if (age>0) {
			condition += " AND birth_year between '"+ (age-9) +"'and '"+(age)+"'" ;
		}
		String order = "ORDER BY reg_date DESC";
		if(sort ==0){
			order =order;
		}else if (sort == 1) {
			order = "ORDER BY (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) ASC, reg_date DESC";
		}else if(sort ==-1){
			order = "ORDER BY (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) DESC, reg_date DESC";
		}else{
			
			condition += " and (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) between 0 and "+sort;
			order = "ORDER BY (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) ASC, reg_date DESC";
		}
		List<User> result = null;
		String query = ""
			+ "	SELECT * FROM ( "
			+ "		SELECT "
			+ "			ROW_NUMBER() OVER("+order+") as row_seq, "
			+ "			A.*, " 
            + "			 (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) as distance"
			+ "			 from V_NF_USERF AS A "
			+ "			where user_id in (select friend_id from T_NF_FRIEND where user_id = ?) "
			+ condition		  
	        + "      )AS a"
	        + "	WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		result = (List<User>)this.jdbcTemplate.query(query,new Object[] { userId}, this.bbsMapper3);
		return result;
	}
	
	public int getCount(String userId,String keyword) {
		String condition = "";
		
		if(keyword!=""){
			condition += " AND user_name like +'%"+keyword+"%'";
		}
		
	
		String query = ""
				+ "		SELECT "
				+ "			count(*) "
				+ "			 from t_nf_user "
				+ "			where user_id in (select friend_id from T_NF_FRIEND where user_id = ?)"
				+ "" +condition;		  
		   
		return this.jdbcTemplate.queryForInt(query,new Object[] { userId });
	}
	
	
	public int getfreindCount(String userId,String keyword,int age, int gender,String area,int category,int sort,Double latitude,Double longitude) {
		String condition = " and latitude>0 and longitude>0  ";
		
		if(keyword!=""){
			condition += " AND user_name like +'%"+keyword+"%'";
		}
		if (gender > 0) {
			condition += " AND gender = '"+ gender+"'";
		}
		if (area.equals("") == false) {
			condition += " AND area = '"+ area +"'";
		}
		if (age>0) {
			condition += " AND birth_year between '"+ (age-9) +"'and '"+(age)+"'" ;
		}
		if (sort >1) {
			condition += " and (select dbo.Distance (latitude, longitude, "+latitude+", "+longitude+")) between 0 and "+sort;
		}
	
		String query = ""
				+ "		SELECT "
				+ "			count(*) "
				+ "			 from t_nf_user "
				+ "			where user_id in (select friend_id from T_NF_FRIEND where user_id = ?)"
				+ "" +condition;		  
		   
		return this.jdbcTemplate.queryForInt(query,new Object[] { userId });
	}
	
	//전체리스트
	public List<Bbs> getsearchList(String site,int page, int itemCountPerPage, String userId, int category,int age, String keyword, int gender, String area, int sort, Double latitude, Double longitude) {
		String condition = " WHERE user_type in (4,5) and status=1 and A.latitude>0 and A.longitude>0 ";
		String con ="";
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
			condition += " AND user_name like +'%"+keyword+"%' ";
		}
		/*if (!site.equals("")){
			condition += " AND site ='"+site+"' ";
		}*/
		String order = "ORDER BY reg_date DESC ";
		if(sort ==0){
			order =order;
		}else if (sort == 1) {
			order = "ORDER BY (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) ASC, reg_date DESC";
		}else if(sort ==-1){
			order = "ORDER BY (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) DESC, reg_date DESC";
		}else{
			
			condition += " and (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) between 0 and "+sort;
			order = "ORDER BY (select dbo.Distance (A.latitude, A.longitude, "+latitude+", "+longitude+")) ASC, reg_date DESC";
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
			+ "	"+ condition + " and user_id not in (select friend_id from t_nf_friend where user_id = '"+userId+"')"
			+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		result = (List<Bbs>)this.jdbcTemplate.query(query, this.bbsMapper3);
		return result;
	}
	
	public int getUserSiteCount(String site,String userId,int category,int gender, String area,int age,String keyword, int sort, Double latitude, Double longitude) {
		String condition = " WHERE user_type in (4,5) and status=1 and latitude>0 and longitude>0 ";
		
		if(keyword!=""){
			condition += " AND user_name like +'%"+keyword+"%'";
		}
		if (gender > 0) {
			condition += " AND gender = "+ gender;
		}
		if (area.equals("") == false) {
			condition += " AND area = '"+ area +"'";
		}
		if (age>0){
			condition += " AND birth_year between '"+ (age-10) +"'and '"+(age)+"'" ;
		}


		
		if (sort >1) {
			condition += " and (select dbo.Distance (latitude, longitude, "+latitude+", "+longitude+")) between 0 and "+sort;
		}

		/*if (!site.equals("")) {
			condition += " and site='"+site+"' ";
		}*/
		String query = ""
			+ "	SELECT count(*) "
			+ "	FROM V_NF_USERF   "
			+ "	"+ condition + " and user_id not in (select friend_id from t_nf_friend where user_id = '"+userId+"')";
		return this.jdbcTemplate.queryForInt(query);
	}
	

	

	public void updatefUser(User user) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				"	user_name = ?, " +
				"   comment = ?," +
				"	area = ? " +
				" WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			user.getUserName(),
			user.getComment(),
			user.getArea(),
			user.getUserId()
		});
	}

	
	
	
	public void updateUserphoto(User user) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				"   photo_status = ?, " +
				"	photo = ?, " +
				"	photo_reg_date = getDate()  "+
				" WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
		
			user.getPhotoStatus(),
			user.getPhoto(),
			user.getUserId()
		});
	}

	
	
	public List<User> getUserListexcel(String site,int userType,String siteName, int gen, int gender, int age, String keyword, String areaSido) {
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = " WHERE 1=1 and user_type = 4 and (status=1) ";
	/*	if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}*/
		if (!siteName.equals("")) {
			condition += " AND site = '"+ siteName +"' ";
		}
		
		if (gender != 0) {
			condition += " AND gender = "+ gender +" ";
		}
		
		if (gen != 0) {
			condition += " AND gender = "+ gen +" ";
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
			condition += " AND (user_id like '%"+ keyword +"%' OR user_name like '%"+ keyword +"%') ";
		}
		if (areaSido.equals("") == false) {
			condition += " AND area like '%"+ areaSido +"%'";
		}
		
		String query = ""
	            + "    SELECT * FROM  "
	            + "        V_NF_USER "
	            + "  		"+condition+" ";
	       
	    return (List<User>)this.jdbcTemplate.query(query, this.userMapper2);
	}
	
	/** 가상회원 검색 목록 **/
	public List<User> getUserImagineList(String site,int userType,String siteName, int gender, int age, String keyword, String areaSido,String colName ,String sort, int page, int itemCountPerPage) {
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = " WHERE 1=1 and user_type = 5 and (status=1) ";
		/*if (userType!=1) {
			condition += " AND site = '"+ site +"' ";
		}
		*/
		
		if (!siteName.equals("")) {
			condition += " AND site = '"+ siteName +"' ";
		}
		
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
			condition += " AND (user_id like '%"+ keyword +"%' OR user_name like '%"+ keyword +"%' OR phone_number like '%"+ keyword +"%') ";
		}
		if (areaSido.equals("") == false) {
			condition += " AND area like '%"+ areaSido +"%'";
		}
		
		String query = ""
	            + "    SELECT * FROM ( "
	            + "        SELECT "
	            + "            ROW_NUMBER() OVER(ORDER BY "+colName +" " +sort+") as row_seq, "
	            + "            * "
	            + "        FROM V_NF_USER "
	            + "  		"+condition+" "
	            + "    ) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
	    return (List<User>)this.jdbcTemplate.query(query, this.userMapper2);
	}

	/** 가상회원 검색 목록 **/
	public List<User> getUserImagineList(String agentId, int gender, int age, String keyword, String areaSido,String colName ,String sort, int page, int itemCountPerPage) {
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = " WHERE 1=1 and user_type = 5 and (status=1) ";

		if (agentId.equals("")) {
			condition += " AND 1=2 ";
		} else {
			condition += " AND agent_id = '"+ agentId +"' ";
		}
		
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
			condition += " AND (user_id like '%"+ keyword +"%' OR user_name like '%"+ keyword +"%' OR phone_number like '%"+ keyword +"%') ";
		}
		if (areaSido.equals("") == false) {
			condition += " AND area like '%"+ areaSido +"%'";
		}
		
		String query = ""
	            + "    SELECT * FROM ( "
	            + "        SELECT "
	            + "            ROW_NUMBER() OVER(ORDER BY "+colName +" " +sort+") as row_seq, "
	            + "            * "
	            + "        FROM V_NF_USER "
	            + "  		"+condition+" "
	            + "    ) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
	    return (List<User>)this.jdbcTemplate.query(query, this.userMapper2);
	}

	
	/** 가상회원 검색결과 갯수 **/
	public int getImagineCount(String site,int userType,String siteName, int gender, int age, String keyword, String areaSido) {
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = " WHERE 1=1 and user_type = 5 and (status=1) ";

		/*if(userType!=1){
			condition += " AND site = '"+ site +"' ";
		}*/
		if (!siteName.equals("")) {
			condition += " AND site = '"+ siteName +"' ";
		}
		
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
			condition += " AND (user_id like '%"+ keyword +"%' OR user_name like '%"+ keyword +"%' OR phone_number like '%"+ keyword +"%') ";
		}
		if (areaSido.equals("") == false) {
			condition += " AND area like '%"+ areaSido +"%'";
		}
		
	    String query = " SELECT COUNT(*) FROM V_NF_USER "+ condition;
	    return this.jdbcTemplate.queryForInt(query);
	}

	/** 가상회원 검색결과 갯수 **/
	public int getImagineCount(String agentId, int gender, int age, String keyword, String areaSido) {
		
		int year = Integer.parseInt(T.getTodayYear());
		
		String condition = " WHERE 1=1 and user_type = 5 and (status=1) ";

		if (agentId.equals("")) {
			condition += " AND 1=2 ";
		} else {
			condition += " AND agent_id = '"+ agentId +"' ";
		}
		
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
			condition += " AND (user_id like '%"+ keyword +"%' OR user_name like '%"+ keyword +"%' OR phone_number like '%"+ keyword +"%') ";
		}
		if (areaSido.equals("") == false) {
			condition += " AND area like '%"+ areaSido +"%'";
		}
		
	    String query = " SELECT COUNT(*) FROM V_NF_USER "+ condition;
	    return this.jdbcTemplate.queryForInt(query);
	}

	
	public User getUserSeq(int user_seq) {
		String query = "" + 
				"SELECT * " +
				"FROM V_NF_USER " +
				"WHERE user_seq = ? ";
		try {
			return (User)this.jdbcTemplate.queryForObject(query, new Object[] { user_seq }, this.userMapper2);
			
		} catch (Exception e) {
			return new User();
		}
		

	}
	
	
	
	
	//채팅 참여 리스트
	
	public List<User> getchatuserList(int chatRoomSeq) {
		String query = ""  
	
		 /* + "    SELECT * FROM ( "
		  + "        SELECT "
		  + "            ROW_NUMBER() OVER(ORDER BY owner desc , reg_date asc) as row_seq, "
		  + "            * "
		  + "        FROM V_NF_Chatmem where chat_room_seq = ? "
		  + "    ) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";*/
			+"   SELECT * FROM "
			+"   V_NF_Chatmem where chat_room_seq = ? ";
				
		try {
			return (List<User>)this.jdbcTemplate.query(query, new Object[] { chatRoomSeq }, this.chatmemMapper2);
			
		} catch (Exception e) {
			return new ArrayList();
		}
	}
		//채팅 참여 리스트 갯수
		
	public int getchatuserCount(int chatRoomSeq) {
		String query = ""
                + " select count(*) from "
				+ "  V_NF_Chatmem where chat_room_seq = ? ";

		try {
			return this.jdbcTemplate.queryForInt(query, new Object[] { chatRoomSeq });

		} catch (Exception e) {
			return 0;
		}
		

	}
	
	//채팅 참여 리스트
	
	public List<User> getadchatuserList(int chatRoomSeq,int page,int itemCountPerPage) {
		String query = ""  
	
		  + "    SELECT * FROM ( "
		  + "        SELECT "
		  + "            ROW_NUMBER() OVER(ORDER BY owner desc , reg_date asc) as row_seq, "
		  + "            * "
		  + "        FROM V_NF_Chatmem where chat_room_seq = ? "
		  + "    ) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		
				
		try {
			return (List<User>)this.jdbcTemplate.query(query, new Object[] { chatRoomSeq }, this.chatmemMapper2);
			
		} catch (Exception e) {
			return new ArrayList();
		}
		
	}
	
	
	public List<User> getUserAgentImagineList(String agentId) {
		
		String query = ""
	            + "    SELECT user_id FROM  "
	          
	            + "         V_NF_USER "
	            + "  		where agent_id = ?  ";
	         //  + "    ) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
	   try{
		   return (List<User>)this.jdbcTemplate.query(query,new Object[] { agentId }, this.userMapper2);
	   }catch(Exception e){
		   return new ArrayList<>();
	   }
	}

}
