package kr.nomad.mars;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.annotation.LazySingletonAspectInstanceFactoryDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.enums.LetterCodedLabeledEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import encrypt.Sha256Util;
import kr.nomad.mars.dao.AreaDao;
import kr.nomad.mars.dao.BannerAdDao;
import kr.nomad.mars.dao.BbsCategoryDao;
import kr.nomad.mars.dao.BbsCommentDao;
import kr.nomad.mars.dao.BbsDao;
import kr.nomad.mars.dao.BbsFilesDao;
import kr.nomad.mars.dao.BbsFncDao;
import kr.nomad.mars.dao.BbsShareDao;
import kr.nomad.mars.dao.BbsVisitDao;
import kr.nomad.mars.dao.ChatBlockDao;
import kr.nomad.mars.dao.ChatBufferDao;
import kr.nomad.mars.dao.ChatHistoryDao;
import kr.nomad.mars.dao.ChatMemberDao;
import kr.nomad.mars.dao.ChatMsgDao;
import kr.nomad.mars.dao.ChatOutDao;
import kr.nomad.mars.dao.ChatReqDao;
import kr.nomad.mars.dao.ChatRequestDao;
import kr.nomad.mars.dao.ChatRoomDao;
import kr.nomad.mars.dao.ChatTranDao;
import kr.nomad.mars.dao.DistanceDao;
import kr.nomad.mars.dao.EventCommentDao;
import kr.nomad.mars.dao.EventDao;
import kr.nomad.mars.dao.ExpenseDao;
import kr.nomad.mars.dao.FaqDao;
import kr.nomad.mars.dao.FriendDao;
import kr.nomad.mars.dao.FriendRequestDao;
import kr.nomad.mars.dao.GiftDao;
import kr.nomad.mars.dao.GuestBookDao;
import kr.nomad.mars.dao.ItemDao;
import kr.nomad.mars.dao.LevelDao;
import kr.nomad.mars.dao.LevelHistoryDao;
import kr.nomad.mars.dao.LevelItemDao;
import kr.nomad.mars.dao.LoginlogDao;
import kr.nomad.mars.dao.ManagerDao;
import kr.nomad.mars.dao.MessageDao;
import kr.nomad.mars.dao.NoticeDao;
import kr.nomad.mars.dao.OrderDao;
import kr.nomad.mars.dao.PointChangeDao;
import kr.nomad.mars.dao.PointChargeDao;
import kr.nomad.mars.dao.PointDao;
import kr.nomad.mars.dao.PointMoneyDao;
import kr.nomad.mars.dao.PushDao;
import kr.nomad.mars.dao.QnaDao;
import kr.nomad.mars.dao.SidoDao;
import kr.nomad.mars.dao.SiteDao;
import kr.nomad.mars.dao.SmsAuthDao;
import kr.nomad.mars.dao.UserDao;
import kr.nomad.mars.dao.UserFncDao;
import kr.nomad.mars.dao.UserItemDao;
import kr.nomad.mars.dto.Album;
import kr.nomad.mars.dto.Bbs;
import kr.nomad.mars.dto.BbsComment;
import kr.nomad.mars.dto.BbsFiles;
import kr.nomad.mars.dto.BbsFnc;
import kr.nomad.mars.dto.BbsShare;
import kr.nomad.mars.dto.BbsVisit;
import kr.nomad.mars.dto.ChatBlock;
import kr.nomad.mars.dto.ChatMember;
import kr.nomad.mars.dto.ChatOut;
import kr.nomad.mars.dto.ChatReq;
import kr.nomad.mars.dto.ChatRequest;
import kr.nomad.mars.dto.ChatRoom;
import kr.nomad.mars.dto.Expense;
import kr.nomad.mars.dto.Friend;
import kr.nomad.mars.dto.FriendRequest;
import kr.nomad.mars.dto.Gift;
import kr.nomad.mars.dto.GuestBook;
import kr.nomad.mars.dto.Item;
import kr.nomad.mars.dto.Level;
import kr.nomad.mars.dto.LevelCal;
import kr.nomad.mars.dto.LevelHistory;
import kr.nomad.mars.dto.LevelItem;
import kr.nomad.mars.dto.Loginlog;
import kr.nomad.mars.dto.Message;
import kr.nomad.mars.dto.Order;
import kr.nomad.mars.dto.Notice;
import kr.nomad.mars.dto.Point;
import kr.nomad.mars.dto.PointChange;
import kr.nomad.mars.dto.PointCharge;
import kr.nomad.mars.dto.PointMoney;
import kr.nomad.mars.dto.Push;
import kr.nomad.mars.dto.Qna;
import kr.nomad.mars.dto.Sido;
import kr.nomad.mars.dto.Site;
import kr.nomad.mars.dto.User;
import kr.nomad.mars.dto.UserFnc;
import kr.nomad.mars.dto.UserItem;
import kr.nomad.util.F;
import kr.nomad.util.FileMime;
import kr.nomad.util.ImageUtil;
import kr.nomad.util.Paging;
import kr.nomad.util.Response;
import kr.nomad.util.T;
import kr.nomad.util.encrypt.CryptoNew;
import kr.nomad.util.file.MovieConverter;
import kr.nomad.util.file.UniqFileRenamePolicy;
import kr.nomad.util.push.PushKey;
import net.sf.json.JSONObject;
import sun.misc.PostVMInitHook;
import sun.org.mozilla.javascript.internal.regexp.SubString;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class ServerController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PushDao pushDao;
	
	@Autowired
	private AreaDao areaDao;
	
	@Autowired
	private BbsCategoryDao bbsCategoryDao;
	
	@Autowired
	private BbsCommentDao bbsCommentDao;
	
	@Autowired
	private BbsDao bbsDao;
	
	@Autowired
	private BbsFncDao bbsFncDao;
	
	@Autowired
	private BbsFilesDao bbsFilesDao;

	@Autowired
	private BbsVisitDao bbsVisitDao;
	
	@Autowired
	private BbsShareDao bbsShareDao;

	@Autowired
	private ChatBlockDao chatBlockDao;
	
	@Autowired
	private ChatBufferDao chatBufferDao;
	
	@Autowired
	private ChatHistoryDao chatHistoryDao;
	
	@Autowired
	private ChatMemberDao chatMemberDao;
	
	@Autowired
	private ChatMsgDao chatMsgDao;
	
	@Autowired
	private ChatRequestDao chatRequestDao;
	
	@Autowired
	private ChatRoomDao chatRoomDao;
	
	@Autowired
	private ChatTranDao chatTranDao;
	
	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private FaqDao faqDao;
	
	@Autowired
	private FriendDao friendDao;
	
	@Autowired
	private FriendRequestDao friendRequestDao;
	
	@Autowired
	private GuestBookDao guestBookDao;
	
	@Autowired
	private LevelDao levelDao;
	
	@Autowired
	private ManagerDao managerDao;
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private PointChangeDao pointChangeDao;
	
	@Autowired
	private PointChargeDao pointChargeDao;
	
	@Autowired
	private PointDao pointDao;
	
	@Autowired
	private QnaDao qnaDao;
	
	@Autowired
	private SmsAuthDao smsAuthDao;
	
	@Autowired
	private SidoDao sidoDao;
	
	@Autowired 
	private PointMoneyDao pointmoneyDao;
	
	@Autowired LevelHistoryDao levelhistoryDao;
	
	@Autowired LevelCal levelService;
	
	@Autowired LoginlogDao loginlogDao;
	
	@Autowired GiftDao giftDao;
	
	@Autowired DistanceDao distanceDao;
	
	@Autowired ExpenseDao expenseDao;
	
	@Autowired LevelItemDao levelItemDao;
	
	@Autowired OrderDao orderDao;
	
	@Autowired UserItemDao useritemDao;
	
	@Autowired ItemDao itemDao;
	
	@Autowired BannerAdDao bannerAdDao;
	
	@Autowired UserFncDao userFncDao;
	
	@Autowired ChatReqDao chatReqDao;
	
	@Autowired ChatOutDao chatOutDao;
	
	@Autowired
	EventCommentDao eventCommentDao;
	
	@Autowired SiteDao siteDao;
	
	// 페이지당 아이템 갯수
	@Value("#{config['page.itemCountPerPage']}")
	int ITEM_COUNT_PER_PAGE;

	// 페이징당 페이지 갯수
	@Value("#{config['page.pageCountPerPaging']}")
	int PAGE_COUNT_PER_PAGING;

	// 파일 루트
	@Value("#{config['file.root']}")
	String FILE_ROOT;

	String FILE_PATH = "";
	String FILE_LOCAL_PATH = "";

	// 파일 최대크기(Mb)
	@Value("#{config['file.maxSize']}")
	int FILE_MAX_SIZE;
		

	@RequestMapping("/api_view.go")
	public String wUserMenuController(
	        Model model
	    ) {
	    return "/api_view";
	}
	
	//첫글 판별
	@RequestMapping("/m/first_bbs.go")
	public String firstBbsController(
		@RequestParam(value="userId", required=false, defaultValue="") String userId, 
		HttpServletRequest req, HttpServletResponse res){
	
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		User user = userDao.getUsers(userId);
		map.put("result", true);
		map.put("firstBbs", user.getFirstBbs());
		
	
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, map);
		return null;

	}
	
	
	

	/**
	 * 모바일 로그인
	 * @param loginId
	 * @param loginPw
	 * @param res
	 * @return
	 */
	@RequestMapping("/m/login.go")
	public String loginController(
		@RequestParam(value="userId", required=false, defaultValue="") String userId, 
		@RequestParam(value="password", required=false, defaultValue="") String password, 
		@RequestParam(value="pushKey", required=false, defaultValue="") String pushKey, 
		@RequestParam(value="latitude", required=false, defaultValue="") String latitude, 
		@RequestParam(value="longitude", required=false, defaultValue="") String longitude, 
		@RequestParam(value="site", required=false, defaultValue="FACETALK") String site, 

		// 0 : 모바일, 1 : PC
		@RequestParam(value = "deviceType", required = false, defaultValue = "0") int deviceType,

		// 추가
		@RequestParam(value = "deviceId", required = false, defaultValue = "") String deviceId,
		@RequestParam(value = "osType", required = false, defaultValue = "") String osType,
				
		HttpServletRequest req, HttpServletResponse res
	) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		//String enPw = "";
		try {
			boolean userCheck = false;
			if (deviceType == 0) {
				userCheck = userDao.correctIdSite(userId,site);
			} else {
				userCheck = userDao.correctAgentId(userId);
			}
			if (userCheck == true) {
	
				
				boolean loginCheck = userDao.correctPw(userId, password);
	
				if (loginCheck == true) {
	
					User user = userDao.getUser(userId);
					if(user.getStatus()==1){ 
						HttpSession session = req.getSession();
						session.setAttribute("USER_ID", user.getUserId());
						session.setAttribute("USER_NAME", user.getUserName());
						
						String today = T.getToday();
						String before =T.getDateAdd(today, -6);
						user.setPushkey(pushKey);
						if((latitude.equals("")&&longitude.equals(""))||
								(latitude.equals("0")&&longitude.equals("0"))){
							Sido sido =sidoDao.getSidoArea(user.getArea());
							if(sido!=null){
								user.setLatitude(sido.getLatitude());
								user.setLongitude(sido.getLongitude());
								
							}else{
								user.setLatitude(1);
								user.setLongitude(1);
							}
						}else{
							user.setLatitude(Double.parseDouble(latitude));
							user.setLongitude(Double.parseDouble(longitude));
							
						}
						System.out.println(user.getLongitude());
						
						
						user.setOsType(osType);
						user.setDeviceId(deviceId);
						
						if (deviceType == 0) {
							userDao.updateMobileUserLogin(user);
						} else {
							userDao.updatePcUserLogin(user);
						}
						
						
						
						Loginlog ll = loginlogDao.getLoginlog(userId,today);
						if(ll!=null){//오늘 로그인 한적이 있으면
							
							int chkcnt =ll.getLogCount();
							int nchkcnt = chkcnt+1;
							loginlogDao.updateLoginlog(nchkcnt,userId,today);
						}else{
							
							ll= new Loginlog();
							ll.setUserId(userId);
							ll.setLogDate(today);
							ll.setLogCount(1);
							loginlogDao.addLoginlog(ll);
							String kind ="";
							String kname ="";
							int cnt = loginlogDao.getLoginlog(userId, today , before);
							int check = pointmoneyDao.getcnt(userId, PointMoney.JOIN_7LOGIN, today, before);
							if(check==1){ //7일이내 7일 출석 받은적 있음
								kind =PointMoney.JOIN_LOGIN;
								kname="로그인";
							}else{
								//7일 연속인지
								if(cnt ==7){ //7일연속 출석
									kind=PointMoney.JOIN_7LOGIN;
									kname = PointMoney.JOIN_7LOGIN;
									
								}else{
									kind =PointMoney.JOIN_LOGIN;
									kname=PointMoney.JOIN_LOGIN;
								}
							}
							
							Point pp =pointDao.getSitePoint(kind,user.getSite());
							if(pp!=null){
								int point = pp.getPoint(); //포인트
								int money = pp.getMoney(); //머니
								levelService.inserDb(userId, kname, money, point,"출석체크");
							}
							
						}
						
						
						map.put("message", "로그인이 성공되었습니다.");
						map.put("result", true);
						map.put("user", user);
						map.put("phone", user.getPhoneNumber());
						map.put("photo", user.getPhoto());
						map.put("userType", user.getUserType());
						map.put("area", user.getArea());
						map.put("photo", user.getPhoto());
						map.put("user", user);
					}else if(user.getStatus()==2){
						map.put("message", "탈퇴한 사용자입니다.");
						map.put("result", false);
						map.put("userType", user.getStatus());
						
					}else if(user.getStatus()==3){
						map.put("message", "사용중지 된 회원입니다 관리자에게 문의해주세요.");
						map.put("result", false);
						map.put("userType", user.getStatus());
						
					}else if(user.getStatus()==4){
						map.put("message", "관리자에의해 강제탈퇴 되었습니다.");
						map.put("result", false);
						map.put("userType", user.getStatus());
					}
		
				} else {
					map.put("message", "패스워드가 일치하지 않습니다.");
					map.put("result", false);
					map.put("userType", 0);
				}
	
			} else {
				map.put("message", "해당 ID가 존재하지 않거나 ID가 일치하지 않습니다.");
				map.put("result", false);
				map.put("userType", 0);
			}
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
			map.put("userType", 0);
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, map);
		return null;

	}
	
	/**
	 * 이미지 등록
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 */

	// 사진 업로드
	@RequestMapping("/m/photo_upload.go")
	public String photoUploadController(HttpServletRequest req, HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = false;
		

		String FILE_PATH = "/files/temp/";
		String FILE_LOCAL_PATH = FILE_ROOT + FILE_PATH;
		//String userId = "";
		String photo = "";
		String fileName = "";
		String photoPre = "";
		String path = "";
		String repath ="";
		String org = "";
		
		int fileSize = FILE_MAX_SIZE * 1024 * 1024;

		try {
			req.setCharacterEncoding("utf-8");

			File file = null;
			try {
				MultipartRequest multi = new MultipartRequest(req, FILE_LOCAL_PATH, fileSize, "UTF-8", new UniqFileRenamePolicy());

				// 폼에서 입력한 값을 가져옴
				String userId="";
				path = F.nullCheck(multi.getParameter("path"), "");
				if(path.equals("profile")){
					userId = F.nullCheck(multi.getParameter("userId"), "");
				}
				Enumeration files = multi.getFileNames();
				while (files.hasMoreElements()) {
					String elementName = (String) files.nextElement();
					file = multi.getFile(elementName);
					if (file != null) {
						org = file.getName();
						photo =org;
						if(path.equals("profile")){
							String hwak = photo.substring(photo.lastIndexOf("."));
							photo = userId+hwak;
						}
					}
				}
				
				fileName = photo;
				String fullpath ="";
				
				if(path.equals("profile")){
					fullpath=FILE_ROOT + "/files/"+ path ;
					repath =  "/files/"+ path+"/" ;
				}else{
					String yearMonth = T.getTodayYear()+T.getTodayMonth();
					
					photoPre = yearMonth+"/";
					fullpath =FILE_ROOT + "/files/"+ path +"/"+photoPre;
					repath = "/files/"+ path +"/"+photoPre;
				}
				
			
				if(!path.equals("profile")){

					File copyFolder = new File(fullpath);
					if (!copyFolder.exists()) {
						copyFolder.mkdirs();
					}
				}
				

				// 파일 복사
				FileInputStream fis = new FileInputStream(FILE_LOCAL_PATH + org);
				FileOutputStream fos = new FileOutputStream(fullpath+"/"+fileName);
				int data = 0;
				while((data=fis.read())!=-1) {
					fos.write(data);
				}
				fis.close();
				fos.close();
				   
				// 축소이미지 저장
				File newFile = new File(FILE_LOCAL_PATH + org);
				File thumbFile = new File(fullpath+"/thumb/"+fileName);
				if (!thumbFile.exists()) {
					thumbFile.mkdirs();
				}
				try {
					ImageUtil.resize(newFile, thumbFile, 500, 0, 0);
					result = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				//복사한뒤 원본파일을 삭제함
				file.delete();
			} catch (Exception e) {
				e.getMessage();
			}
		
			
			map.put("photo", fileName);
			map.put("path", repath);
			map.put("result", true);
			map.put("message", "사진이 등록되었습니다.");
			
		} catch (Exception e) {
			map.put("result", false);
			map.put("message", "사진 등록에 실패하였습니다.\n"+e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map);

		Gson gson = new Gson();
		String outputstr = gson.toJson(jsonObject);
		Response.responseWrite(res, outputstr);

		return null;
	}
	
		
	/**
	 * 아이디 중복 체크	
	 * @param userId	
	 */
	@RequestMapping("/m/dup_check_id.go")
	public String checkIdController(
		@RequestParam(value="userId", required=false, defaultValue="") String userId,
		HttpServletRequest req, HttpServletResponse res	) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		boolean userCheck = userDao.correctId(userId);
		if (userCheck == true) {
			map.put("result", false);
			map.put("message", "사용중인 아이디 입니다.");
		} else {
			map.put("result", true);
			map.put("message", "사용가능한 아이디 입니다.");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
		
	/**
	 * 닉네임 중복 체크	
	 * @param userId	
	 */
	@RequestMapping("/m/dup_check_nick.go")
	public String checkNickController(
		@RequestParam(value="userName", required=false, defaultValue="") String userName,
		HttpServletRequest req, HttpServletResponse res	) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		boolean userCheck = userDao.correctNick(userName);
		if (userCheck == true) {
			map.put("result", false);
			map.put("message", "사용중인 닉네임 입니다.");
		} else {
			map.put("result", true);
			map.put("message", "사용가능한 닉네임 입니다.");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	
		
	/**
	 * 전화번호 중복 체크	
	 * @param userId	
	 */
	@RequestMapping("/m/dup_check_phone.go")
	public String checkPhoneController(
		@RequestParam(value="phoneNumber", required=false, defaultValue="") String phoneNumber,
		@RequestParam(value="site", required=false, defaultValue="FACETALK") String site,
		HttpServletRequest req, HttpServletResponse res	) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		boolean userCheck = userDao.correctPhone(phoneNumber,site);
		if (userCheck == true) {
			map.put("result", false);
			map.put("message", "이미 등록된 전화번호 입니다.");
		} else {
			map.put("result", true);
			map.put("message", "등록가능한 전화번호 입니다.");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
				
	/**
	 * 회원가입
	 * @param userId
	 * @param userName
	 * @param userType
	 * @param password
	 * @param phoneNumber
	 * @param userAge
	 * @param gender
	 * @param area
	 * @param latitude
	 * @param longitude
	 * @param osType
	 * @param osVersion
	 * @param deviceId
	 * @param pushKey
	 * @param res
	 * @return
	 */
	@RequestMapping("/m/join.go")
	public String joinController(
		@RequestParam(value="userId", required=false, defaultValue="") String userId, 
		@RequestParam(value="password", required=false, defaultValue="") String password,
		@RequestParam(value="userType", required=false, defaultValue="") String userType,
		@RequestParam(value="userName", required=false, defaultValue="") String userName,
		@RequestParam(value="phoneNumber", required=false, defaultValue="") String phoneNumber, 
		@RequestParam(value="userAge", required=false, defaultValue="0") int userAge, 
		@RequestParam(value="gender", required=false, defaultValue="1") int gender,
		@RequestParam(value="area", required=false, defaultValue="") String area,
		@RequestParam(value="photo", required=false, defaultValue="") String photo,
		@RequestParam(value="latitude", required=false, defaultValue="0") double latitude,
		@RequestParam(value="longitude", required=false, defaultValue="0") double longitude,
		@RequestParam(value="osType", required=false, defaultValue="") String osType,
		@RequestParam(value="osVersion", required=false, defaultValue="") String osVersion,
		@RequestParam(value="deviceId", required=false, defaultValue="") String deviceId,
		@RequestParam(value="pushKey", required=false, defaultValue="") String pushKey,
		@RequestParam(value="site", required=false, defaultValue="FACETALK") String site,
		HttpServletRequest req, HttpServletResponse res
		) {

		Map<String, Object> map = new HashMap<String, Object>();
		int birthYear = 0;
		try {
			boolean userCheck = userDao.correctId(userId);
			boolean chk = userDao.correctPhone(phoneNumber,site);
			if (userCheck) {
				map.put("result", false);
				map.put("message", "존재하는 아이디 입니다.");
			}else if(chk){
				map.put("result", false);
				map.put("message", "사용중인 전화번호입니다.");
				
			}else if(phoneNumber.equals("")||userId.equals("")||area.equals("")){
				map.put("result", false);
				map.put("message", "필수 입력 사항이 기입되지 않았습니다.");
			}else {
				
				/*String enPw = Sha256Util.encryptPassword(password);*/
				Calendar calendar = Calendar.getInstance();
				int todayYear = calendar.get(Calendar.YEAR);
				birthYear = todayYear - userAge + 1;
				String regDate = "";
				User user = new User();
				user.setUserId(userId);
				user.setUserName(userName);
				user.setUserType(4);
				user.setPassword(password);
				user.setPhoneNumber(phoneNumber);
				user.setBirthYear(birthYear);
				user.setGender(gender);
				user.setArea(area);
				user.setPhoto(photo);
				if(latitude==0||longitude==0){
					Sido sido =sidoDao.getSidoArea(area);
					if(sido!=null){
						latitude=sido.getLatitude();
						longitude=sido.getLongitude();
					}else{
						latitude=1;
						longitude=1;
					}
					
					
				}
				user.setLatitude(latitude);
				user.setLongitude(longitude);
				user.setOsType(osType);
				user.setOsVersion(osVersion);
				user.setDeviceId(deviceId);
				user.setPushkey(pushKey);
				user.setStatus(1);
				user.setLevelPoint(1);
				user.setPoint(0);
				user.setSite(site);
				if(!photo.equals("")){
					user.setPhotoStatus(1);
				}
				userDao.addUser(user);
			
				
				Point pp =pointDao.getSitePoint(PointMoney.JOIN_JOIN,site);
				if(pp!=null){
					int point = pp.getPoint(); //포인트
					int money = pp.getMoney(); //머니
					//레벨 계산 메서드
					levelService.inserDb(userId, PointMoney.JOIN_JOIN, money, point,"회원가입");
				}
				
				if(!photo.equals("")){//회원가입시 프로필 사진등록
					pp =pointDao.getSitePoint(PointMoney.JOIN_PHOTO,site);
					if(pp!=null){
						int point = pp.getPoint(); //포인트
						int money = pp.getMoney(); //머니
						levelService.inserDb(userId, PointMoney.JOIN_PHOTO, money, point,"프사등록");
					}
					
				}
				
				map.put("result", true);
				map.put("message", "회원가입되었습니다.");
			}
		} catch (Exception e) {
			map.put("result", false);
			map.put("message", "회원가입에 실패하였습니다.\n"+e.getMessage());
		}
		 
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}


	/**
	 * 프로필 이미지 삭제
	 * @param userId
	 * @param photo
	 * @param res
	 * @return
	 */
	@RequestMapping("/m/profile_photo_delete.go")
	public String profileIMGDeleteController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		HttpServletResponse res
	) {
		
		Map<String, Object> map = new HashMap<String, Object>();
	

		try {
			// DB 파일명 삭제
			
			User user = userDao.getUser(userId);
			String photo = user.getPhoto();
			if (user != null) {
				//파일삭제
				filedelete(user.getPhoto());
				
				userDao.updateProfileImgDel(userId);
	
				map.put("message", "프로필 이미지가 삭제되었습니다.");
				map.put("result", true);
			} else {
				map.put("message", "존재하지 않는 ID 입니다.");
				map.put("result", false);
			}
		} catch (Exception e) {
	
			map.put("message", "프로필 이미지가 삭제되지 않았습니다.\n"+e.getMessage());
			map.put("result", false);
		}

		Response.responseWrite(res, map);
		JSONObject jsonObject = JSONObject.fromObject(map);
		return null;
	}
	
	/**
	 * 아이디 찾기
	 * @param phoneNumber
	 * @param res
	 * @return
	 */
	@RequestMapping("/m/find_id.go")
	public String findIdController(
		@RequestParam(value = "phoneNumber", required = false, defaultValue = "") String phoneNumber,
		HttpServletResponse res) {
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			boolean userCheck = userDao.correctPhonesearch(phoneNumber);
		
			if(userCheck == true){
				User user = userDao.getUserByPhone(phoneNumber);
				map.put("result", true);
				map.put("userId", user.getUserId());
			} else {
				map.put("result", false);
				map.put("message", "해당 전화번호로 등록된 계정이 없거나 탈퇴한 사용자입니다.");
			}
			
		} catch (Exception e) {
			map.put("message", e.getMessage());
		}
								
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	/**
	 * 비밀번호 찾기
	 * @param userId
	 * @param res
	 * @return
	 */
	@RequestMapping("/m/find_pw.go")
	public String findPwController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		@RequestParam(value = "phone", required = false, defaultValue = "") String phone,
		HttpServletResponse res) {
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			boolean userCheck = userDao.correctIdphone(userId,phone);
		
			if(userCheck == true){
				User user = userDao.getUser(userId);
				
				map.put("result", true);
				map.put("password", user.getPassword());
			} else {
				map.put("result", false);
				map.put("message", "해당 전화번호로 등록된 계정이 없거나 탈퇴한 사용자입니다.");
			}
			
		} catch (Exception e) {
			map.put("message", e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	//재설정
	
	@RequestMapping("/m/npw.go")
	public String chPwController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		@RequestParam(value = "password", required = false, defaultValue = "") String password,
		HttpServletResponse res) {
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
		
				boolean change = userDao.updateUserPassword(userId, password);
				
				if(change){
					map.put("result", true);
					map.put("password", "변경이 완료되었습니다");
				}else{
					map.put("result", false);
					map.put("password", "변경에 실패하였습니다");
				}
		} catch (Exception e) {
			map.put("message", e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	
	/**
	 * 비밀번호 변경
	 * @param userId
	 * @param res
	 * @return
	 */
	@RequestMapping("/m/change_pw.go")
	public String chPwController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		@RequestParam(value = "password", required = false, defaultValue = "") String password,
		@RequestParam(value = "npassword", required = false, defaultValue = "") String npassword,
		HttpServletResponse res) {
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			boolean userCheck = userDao.correctPw(userId, password);
		
			if(userCheck == true){
				boolean change = userDao.updateUserPassword(userId, npassword);
				
				if(change){
					map.put("result", true);
					map.put("password", "비밀번호가 변경되었습니다. 다시 로그인 해 주세요.");
				}else{
					map.put("result", false);
					map.put("password", "변경에 실패하였습니다");
				}
				
			} else {
				map.put("result", false);
				map.put("message", "비밀번호가 다릅니다.");
			}
			
		} catch (Exception e) {
			map.put("message", e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	/**
	 * 회원 토크 목록
	 * @param userId
	 * @param req
	 * @param res
	 * @return
	 */
		
	@RequestMapping("/m/user_talk_list.go")
	public String talkListController(
			@RequestParam(value="userId", required=false, defaultValue="") String userId,
			@RequestParam(value="myId", required=false, defaultValue="") String myId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpServletRequest req, HttpServletResponse res){
		
		Map<String, Object> map = new HashMap<String, Object>();

		List<Bbs> list = null;

		try {
			User user = userDao.getUsers(userId);
			list = bbsDao.getBbsList(user.getSite(),userId, page, ITEM_COUNT_PER_PAGE );
			if(userId.equals(myId)){
				list = bbsDao.getMyBbsList(user.getSite(),page, ITEM_COUNT_PER_PAGE, userId, "");
			}
			for(int i=0;i<list.size();i++){
				Bbs bbs= list.get(i);
				int chk= bbsFncDao.getBBsCount(bbs.getBbsSeq(),myId,BbsFnc.FNC_TYPE_LIKE,BbsFnc.FNC_TYPE_SHARE_LIKE);
				if(chk>0){//좋아요 상태면
					bbs.setGoodStatus(1);
				}
				chk = friendDao.getFrcorrect(userId, myId);
				if(chk>0){ //친구면
					bbs.setFriendStatus(1);
				}
				list.set(i, bbs);
				
			}
			
			int bbsCount =bbsDao.getBbsListCount(user.getSite(),userId);
			if(userId.equals(myId)){
				bbsCount=bbsDao.getMyBbsCount(userId, "");
			}
			map.put("currentPage", page);
			map.put("itemCount", bbsCount);
			map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
			map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);

			map.put("list", list);
			map.put("result", true);
		} catch (Exception e) {
			map.put("result", false);
			map.put("message", e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	
	/**
	 * 사용자 프로필
	 * @param userId
	 * @param req
	 * @param res
	 * @return
	 */
		
	@RequestMapping("/m/user_profile.go")
	public String userProfileController(
			@RequestParam(value="userId", required=false, defaultValue="") String userId, //로그인유저아이디
			@RequestParam(value="dId", required=false, defaultValue="") String dId, //타인아이디 나면 같이보냄
			HttpServletRequest req, HttpServletResponse res){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			if(userId.equals(dId)){//내가 내 프로필 보기
		
				
				int pStatus =0;
				User user = userDao.getUsers(userId);
				if(user.getPhotoStatus()==0){//최초등록자
					pStatus=1;
					Point pp= pointDao.getSitePoint(PointMoney.JOIN_PHOTO,user.getSite());
					if(pp!=null){
						map.put("message", "최초 1회 프로필 사진등록시 "+pp.getPoint()+"포인트를 지급합니다.");
					}
					
				}
					
				
				map.put("pStatus", pStatus);
				map.put("user", user);
				map.put("result", true);
			}else{//타인 프로필 보기 
				//친구인지 아닌지 판별 하고
				int status =0;
				User user = userDao.getUsers(dId);
				if(user.getStatus()==1){
					if(friendDao.getFr(userId, dId)!=null){ //친구이면
						status = 1;
						
					}
					map.put("status", status);
					//내 프로필 사진이 있는지 확인
				
					if((userDao.getUser(userId).getPhoto()).equals("")){  //없으면
						
							
							map.put("user", user);
							map.put("result", true);
							map.put("uphoto", 0);//내 프로필 사진이 있는지 확인
							map.put("message", "본인 프로필 사진을 입력해야 상대방의 프로필 사진을 볼 수있습니다.");
					
					}else{//있으면
						
							map.put("uphoto", 1);
							map.put("user", user);
							map.put("result", true);
						
					}
				}else{
					map.put("result", false);
					map.put("message", "조회할 수 없는 회원입니다. ");
				}
			}
		
		} catch (Exception e) {
			map.put("result", false);
			map.put("message", e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	
	/**
	 * 회원 앨범 목록
	 * @param userId
	 * @param req
	 * @param res
	 * @return
	 */
		
	@RequestMapping("/m/user_album_list.go")
	public String albumListController(
			@RequestParam(value="userId", required=false, defaultValue="") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpServletRequest req, HttpServletResponse res){
		
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			String prophoto = userDao.getUser(userId).getPhoto(); //프사  
			List<Album> list = bbsFilesDao.getAlbumList(userId, page, ITEM_COUNT_PER_PAGE );
			
			for(int i=0;i<list.size();i++){
				Album aa = (Album)list.get(i);
				List<Bbs> list2= bbsDao.getABbsList((list.get(i)).getBbsSeq(),aa.getUserId()); 
				aa.setSublist(list2);
				list.set(i, aa);
			}
			map.put("prophoto", prophoto); //프사
			map.put("list", list);
			map.put("result", true);
		} catch (Exception e) {
			map.put("result", false);
			map.put("message", e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	/**
	 * 회원 방명록 목록
	 * @param userId
	 * @param req
	 * @param res
	 * @return
	 */
		
	@RequestMapping("/m/user_guestbook_list.go")
	public String guestListController(
			@RequestParam(value="userId", required=false, defaultValue="") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpServletRequest req, HttpServletResponse res){
		
		Map<String, Object> map = new HashMap<String, Object>();

		List<GuestBook> list = null;

		try {
			list = guestBookDao.getGuestBookList(userId, page , ITEM_COUNT_PER_PAGE );
			map.put("list", list);
			map.put("result", true);
		} catch (Exception e) {
			map.put("result", false);
			map.put("message", e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	
	@RequestMapping("/m/user_guestbook_edit.go")
	public String guestEditController(
			@RequestParam(value="bookSeq", required=false, defaultValue="0") int bookSeq,
			@RequestParam(value="userId", required=false, defaultValue="") String userId,
			@RequestParam(value="guestId", required=false, defaultValue="") String guestId,
			@RequestParam(value="contents", required=false, defaultValue="") String contents,
			HttpServletRequest req, HttpServletResponse res){
		
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (bookSeq == 0) {
				
				User uu = userDao.getUser(userId);
				User gg = userDao.getUser(guestId);
				//guestId 나 userId 상대방
				int chkmecnt = chatBlockDao.getBlockmechk(guestId,userId );
				int chkothercnt = chatBlockDao.getBlockotherchk(guestId,userId);
				if(chkmecnt==0){//차단한 친구가 아니면
					if(chkothercnt==0){
						GuestBook book = new GuestBook();
						book.setUserId(userId);
						book.setGuestId(guestId);
						book.setContents(contents);
						bookSeq = guestBookDao.addGuestBook(book);
						String pushmessage = gg.getUserName() +" 님이 방명록을 등록하셨습니다.";
						
						
						//푸시알림발송
						push(userId, bookSeq, pushmessage, Push.MSG_TYPE_GUESTBOOK,guestId);
						
						map.put("result", true);
						map.put("message", "방명록이 등록되었습니다.");
					}else{//내가 차단당하면
						map.put("result", false);
						map.put("message", "당신은 차단되었습니다.");
						//map.put("message", uu.getUserName()+"님이 당신을 차단하였습니다.");
					}
						
				}else{//차단한 친구면
					map.put("result", false);
					map.put("message", "차단해제 후 이용해주세요.");
				}
				
			} else {
				
				GuestBook book = guestBookDao.getGuestBook(bookSeq);
				if (userId.equals(book.getUserId()) || userId.equals(book.getGuestId())) {
					book.setContents(contents);
					guestBookDao.updateGuestBook(book);
					map.put("result", true);
					map.put("message", "방명록이 수정되었습니다.");
				} else {
					map.put("result", false);
					map.put("message", "본인만 수정할 수 있습니다.");
				}
			}
		} catch (Exception e) {
			map.put("result", false);
			map.put("message", e.getMessage());
		}
	
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
		
		
	
	/**
	 * 방명록 삭제
	 * @param userId
	 * @param bookSeq
	 * @param res
	 * @return
	 */
		@RequestMapping("/m/user_guestbook_delete.go")
		public String guestBookDeleteController(
			@RequestParam(value="userId", required=false, defaultValue="") String userId,
			@RequestParam(value = "bookSeq", required = false, defaultValue = "0") int bookSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			GuestBook book = guestBookDao.getGuestBook(bookSeq);
			if (userId.equals(book.getUserId()) || userId.equals(book.getGuestId())) {
				// 메뉴 삭제
				guestBookDao.deleteGuestBook(bookSeq);
				map.put("result", true);
				map.put("message", "방명록이 삭제되었습니다.");
				
				messageDao.deletebbsMessage(Push.MSG_TYPE_GUESTBOOK, bookSeq);
				
				pushdelete(book.getUserId(), bookSeq,"삭제", Push.MSG_TYPE_DELETE, userId);
				
			} else {
				map.put("result", false);
				map.put("message", "본인만 삭제할 수 있습니다.");
			}
		} catch (Exception e) {
			map.put("message", "방명록이 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
		}
		
		/**
		 * 내 친구 목록
		 * @param userId
		 * @param keyword
		 * @param page
		 * @param req
		 * @param res
		 * @return
		 */
		@RequestMapping("/m/friend_list.go")
		public String friendListController(
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				@RequestParam(value="keyword", required=false, defaultValue="") String keyword,
				@RequestParam(value="page", required=false, defaultValue="1") int page,
				HttpServletRequest req, HttpServletResponse res
			){
			
			Map<String, Object> map = new HashMap<String, Object>();

			List<Friend> list = null;
			int count = 0;
			try {
				if (keyword.equals("")) {
					list = friendDao.getFriendList(userId, page, ITEM_COUNT_PER_PAGE);
					count = friendDao.getCount(userId);
				} else {
					list = friendDao.getFriendList(userId, keyword, page, ITEM_COUNT_PER_PAGE);
					count = friendDao.getCount(userId, keyword);
				}
				map.put("list", list);
				map.put("count", count);
				map.put("currentPage", page);
				map.put("result", true);
			} catch (Exception e) {
				map.put("result", false);
				map.put("message", e.getMessage());
			}
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;

		}
		
		/**
		 * 전체회원 목록
		 * @param keyword
		 * @param page
		 * @param req
		 * @param res
		 * @return
		 */
		
		@RequestMapping("/m/user_list.go")
		public String userListController(
				@RequestParam(value="page", required=false, defaultValue="1") int page,
				@RequestParam(value="userId", required=false, defaultValue="1") String userId,
				@RequestParam(value="keyword", required=false, defaultValue="") String keyword,
				HttpServletRequest req, HttpServletResponse res
			){
			
			Map<String, Object> map = new HashMap<String, Object>();

			List<User> list = null;
			int count = 0;
			try {
				User uu= userDao.getUsers(userId);
				
					list = userDao.getUserList(uu.getSite(),keyword,page,ITEM_COUNT_PER_PAGE);
					count = userDao.getUserCount(uu.getSite(),keyword);
				
				map.put("list", list);
				map.put("count", count);
				map.put("result", true);
			} catch (Exception e) {
				map.put("result", false);
				map.put("message", e.getMessage());
			}
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;

		}
		
		
		/**
		 * 친구 신청
		 * @param userId
		 * @param friendId
		 * @param message
		 * @param req
		 * @param res
		 * @return
		 */
		@RequestMapping("/m/request_friend.go")
		public String requestFriendController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "friendId", required = false, defaultValue = "") String friendId,
			@RequestParam(value = "message", required = false, defaultValue = "") String message,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//아이디 존재 여부
			boolean userCheck = userDao.correctActiveId(userId);
			boolean userFriendCheck = userDao.correctActiveId(friendId);
			if(!userId.equals(friendId)){
				if (userCheck == false) {
					map.put("result", false);
					map.put("message", "조회 불가능한 회원입니다.");
				} else if (userFriendCheck == false) {
					map.put("result", false);
					map.put("message", "조회 불가능한 회원입니다.");
				} else {
					//차단여부
					//int chk = chatBlockDao.getBlockchk(userId, friendId);
					int chkmecnt = chatBlockDao.getBlockmechk(userId, friendId);
					int chkothercnt = chatBlockDao.getBlockotherchk(userId, friendId);
					User user = userDao.getUser(userId);//나
					User user2 = userDao.getUser(friendId);//친구
					if(chkmecnt==0){
						// 친구 등록 여부
						if(chkothercnt==0){
							Friend friend = friendDao.getFriend(userId, friendId);
							
							if(friend == null){
								
								// 친구 신청 여부
								FriendRequest fr = friendRequestDao.getFriendRequest0(userId, friendId);
								if (fr == null) {
									fr = new FriendRequest();
									fr.setUserId(userId);
									fr.setFriendId(friendId);
									fr.setMessage(message);
									fr.setStatus(0);
									int seq = friendRequestDao.addFriendRequest(fr);
									
									
									
									String pushMessage = user.getUserName()+"님이 친구신청을 하였습니다.";
									//String pushMessage2 = user2.getUserName()+"님에게 친구신청을 하였습니다.";
									push(friendId, seq, pushMessage, Push.MSG_TYPE_REQUEST_FRIEND,userId);
									//push(userId, seq, pushMessage2, Push.MSG_TYPE_DO_FRIEND, friendId);
									
									
									map.put("result", true);
									map.put("message", "친구 요청되었습니다.");
									
								} else {
								
									map.put("result", false);
									map.put("message", "이미 친구 요청을 하였으며 응답을 기다리는 중입니다.");
								}
							} else {
								map.put("result", false);
								map.put("message", "이미 등록된 친구입니다.");
							}
						}else{
							map.put("result", false);
							map.put("message", "당신은 차단되었습니다.");
							//map.put("message", user2.getUserName()+"님이 당신을 차단하였습니다.");
						}
					}else{
						map.put("result", false);
						map.put("message", "차단 해제 후 이용해주세요.");
					}
					
					
				}
			}else{
				map.put("result", false);
				map.put("message", "본인에게는 친구신청 할 수 없습니다.");
			}
		} catch (Exception e) {
			map.put("result", false);
			map.put("message", e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
		
		/**
		 * 대화요청
		 * @param userId
		 * @param friendId
		 * @param message
		 * @param req
		 * @param res
		 * @return
		 */
		
		@RequestMapping("/m/request_chat.go")
		public String requestChatController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "friendId", required = false, defaultValue = "") String friendId,
			@RequestParam(value = "message", required = false, defaultValue = "") String message,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//아이디 존재 여부
			boolean userCheck = userDao.correctActiveId(userId);
			boolean userFriendCheck = userDao.correctActiveId(friendId);
			if(!userId.equals(friendId)){
				if (userCheck == false) {
					map.put("result", false);
					map.put("message", "존재하지 않는 회원 입니다.");
				} else if (userFriendCheck == false) {
					map.put("result", false);
					map.put("message", "존재하지 않는 친구 입니다.");
				} else {
					
					User user = userDao.getUsers(userId);
					Point point = pointDao.getSitePoint(PointMoney.JOIN_CHAT,user.getSite());
					if(point==null){
						point=new Point();
					}
					int requirePoint = point.getPoint();
					int requireMoney = point.getMoney();
					int remainPoint = user.getPoint();
					if (remainPoint < Math.abs(requirePoint)) {
						map.put("result", false);
						map.put("message", "포인트가 부족합니다.");
					}else{
					
					
						int chkmecnt = chatBlockDao.getBlockmechk(userId, friendId);
						int chkothercnt = chatBlockDao.getBlockotherchk(userId, friendId);
						User user2 = userDao.getUsers(friendId);
							if(chkmecnt==0){
								if(chkothercnt==0){
									
										// 대화 신청 여부
										ChatRequest cr = chatRequestDao.getChatRequest2(userId, friendId);
										if (cr == null) {
											cr = new ChatRequest();
											cr.setUserId(userId);
											cr.setFriendId(friendId);
											cr.setMessage(message);
											cr.setStatus(0);
											int seq = chatRequestDao.addChatRequest(cr);
											
											String pushMessage = user.getUserName()+"님이 대화를 신청하였습니다.";
										
											push(friendId, seq, pushMessage, Push.MSG_TYPE_REQUEST_CHAT,userId);
											levelService.minusDb(userId, PointMoney.JOIN_CHAT, requireMoney, requirePoint,"채팅신청");
											map.put("result", true);
											map.put("message", "대화 신청되었습니다.");
										} else {
											if (cr.getStatus() == 0) {
												map.put("result", false);
												map.put("message", "이미 대화 신청을 하였으며 응답을 기다리는 중입니다.");
											}
											
										}
									
								}else{
									map.put("result", false);
									map.put("message", "당신은 차단되었습니다.");
									//map.put("message", user2.getUserName()+"님이 당신을 차단하였습니다.");
								}
							}else{
								map.put("result", false);
								map.put("message", "차단 해제 후 이용해주세요.");
							}
					}
				}
			}else{
				map.put("result", false);
				map.put("message", "본인에게는 대화신청을 할 수 없습니다.");
			}

		} catch (Exception e) {
			map.put("result", false);
			map.put("message", e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
		}
		
	

		/**
		 * 친구 신청 수락
		 * @param seq
		 * @param answer
		 * @param userId
		 * @param req
		 * @param res
		 * @return
		 */
		@RequestMapping("/m/request_friend_answer.go")
		public String friendAnswerController(
			@RequestParam(value="seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value="answer", required = false, defaultValue = "0") int answer,
			HttpServletRequest req, HttpServletResponse res){
		
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				FriendRequest fr = friendRequestDao.getFriendRequest(seq);
				int chkmecnt = chatBlockDao.getBlockmechk(fr.getFriendId(), fr.getUserId());
				int chkothercnt = chatBlockDao.getBlockotherchk(fr.getFriendId(), fr.getUserId());
				User user = userDao.getUsers(fr.getFriendId());
				if(fr!=null){
					if (fr.getStatus()==1) {
						map.put("result", false);
						map.put("message", "이미 친구 수락을 했습니다.");
					} else if (fr.getStatus()==2) {
						map.put("result", false);
						map.put("message", "이미 친구 요청을 거절 했습니다.");
					} else if (chkmecnt!=0) {
						map.put("result", false);
						map.put("message", "이미 차단된 친구입니다.");
					}else if(chkothercnt!=0){
						map.put("result", false);
						map.put("message", user.getUserName()+"님이 당신을 차단했습니다.");
						
					}else if(user.getStatus()!=1){
						map.put("result", false);
						map.put("message", "조회 할 수 없는 회원입니다.");
						
					}else{
						
						if(answer == 1){
								//친구신청한사람 fr.getUserId() //나 fr.getFriendId()
								friendRequestDao.updateFriendRequestStatus(seq, FriendRequest.STATUS_ACCEPT);
								
								Friend friend1 = new Friend();
								friend1.setUserId(fr.getUserId());
								friend1.setFriendId(fr.getFriendId());
								friendDao.addFriend(friend1);
								
								Friend friend2 = new Friend();
								friend2.setUserId(fr.getFriendId());
								friend2.setFriendId(fr.getUserId());
								friendDao.addFriend(friend2);
		
								
								String message = user.getUserName()+"님이 친구 신청이 수락했습니다.";
								
								push(fr.getUserId(), seq, message, Push.MSG_TYPE_FRIEND_ACCEPT,fr.getFriendId());
								
								
								map.put("result", true);
								map.put("message", "친구신청을 수락하였습니다.");
						}else{
							    String message=user.getUserName()+"님이 친구 신청을 거절했습니다.";
							    
								friendRequestDao.updateFriendRequestStatus(seq, FriendRequest.STATUS_REFUSE);
								push(fr.getUserId(), seq, message, Push.MSG_TYPE_FRIEND_REJECT,fr.getFriendId());
								map.put("result", true);
								map.put("message", "친구신청을 거절하였습니다.");
								
									
						}
					}
				}else{
						map.put("result", false);
						map.put("message", "삭제된 요청입니다.");
				}
					
					
			} catch (Exception e) {
				map.put("result", false);
				map.put("message", e.getMessage());
			}
			JSONObject jsonObject = JSONObject.fromObject(map);	
			Response.responseWrite(res, map);
			return null;

		}
		
		/**
		 * 대화 요청 수락
		 * @param userId
		 * @param friendId
		 * @param message
		 * @param answer
		 * @param req
		 * @param res
		 * @return
		 */
		
		@RequestMapping("/m/request_chat_answer.go")
		public String chatAnswerController(
				@RequestParam(value="seq", required = false, defaultValue = "0") int seq,
				@RequestParam(value="answer", required = false, defaultValue = "0") int answer,
				//@RequestParam(value="userId", required = false, defaultValue = "0") String userId,
				HttpServletRequest req, HttpServletResponse res
			){
		
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				ChatRequest cr = chatRequestDao.getChatRequest(seq);
				//FriendRequest fr = friendRequestDao.FriendRequest(cr.getUserId(), cr.getFriendId());
				 User user = userDao.getUsers(cr.getUserId());
				if (cr.getStatus()==1) {
						map.put("result", false);
						map.put("message", "이미 요청을 수락했습니다.");
				} else if (cr.getStatus()==2) {
						map.put("result", false);
						map.put("message", "이미 요청을 거절했습니다.");
				}else if(user.getStatus()!=1){
					map.put("result", false);
					map.put("message", "조회 불가능한 회원입니다.");
					
				}else {
						
					if(answer == 1){
						chatRequestDao.updateChatRequestStatus(seq, ChatRequest.STATUS_ACCEPT);
						
						
						User fuser = userDao.getUser(cr.getFriendId());
						String pushmessage = fuser.getUserName()+"님과 대화요청이 수락 되었습니다.";
						push(cr.getUserId(), seq, pushmessage, Push.MSG_TYPE_CHAT_ACCEPT,cr.getFriendId());
						User imguser = userDao.getUsers(cr.getFriendId());
						User imgfuser = userDao.getUser(cr.getUserId());
						map.put("userId", imguser.getUserId());
						map.put("userName", imguser.getUserName());
						map.put("userfre", imgfuser.getUserId());
						map.put("userfreName", imgfuser.getUserName());
						//User user2 = userDao.getUser(cr.getFriendId());
						if(fuser.getGender()==User.WOMAN){
							Point pp = pointDao.getSitePoint(PointMoney.JOIN_APCHAT,imguser.getSite());
							if(pp!=null){
								int point =pp.getPoint();
								int money = pp.getMoney();
								levelService.inserDb(cr.getFriendId(), PointMoney.JOIN_APCHAT, money, point,"채팅수락");
							}
						}
						
							map.put("result", true);
							map.put("message", user.getUserName()+"님의 대화요청을 수락하였습니다.");
					}else{
							//friendRequestDao.updateFriendRequestStatus(cr.getReqSeq(), FriendRequest.STATUS_REFUSE);
							chatRequestDao.updateChatRequestStatus(seq, ChatRequest.STATUS_REFUSE);
							
							//ChatRequest cr = chatRequestDao.getChatRequest(seq);
							User fuser = userDao.getUser(cr.getFriendId());
							
							String message = fuser.getUserName()+"님이 대화 요청을 거절했습니다.";
							push(cr.getUserId(), seq, message, Push.MSG_TYPE_CHAT_REJECT,cr.getFriendId());
							map.put("result", false);
							map.put("message", user.getUserName()+"님의 대화요청을 거절하였습니다.");
						}
					}
				
				
			} catch (Exception e) {
				map.put("result", false);
				map.put("message", e.getMessage());
			}
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;

		}
		

		
		/**
		 * 내 토크 목록 
		 * @param userId
		 * @param keyword
		 * @param page
		 * @param req
		 * @param res
		 * @return
		 */
				
		@RequestMapping("/m/bbs_list_my.go")
		public String myTalkListController(
			@RequestParam(value="userId", required=false, defaultValue="") String userId,
			@RequestParam(value="keyword", required=false, defaultValue="") String keyword,
			@RequestParam(value="page", required=false, defaultValue="1") int page,
			HttpServletRequest req, HttpServletResponse res){
			
			Map<String, Object> map = new HashMap<String, Object>();

			List<Bbs> list = null;
			int count = 0;
			try {
					User user= userDao.getUsers(userId);
					list = bbsDao.getMyBbsList(user.getSite(),page, ITEM_COUNT_PER_PAGE, userId, keyword);
					count = bbsDao.getMyBbsCount(userId, keyword);
					for(int i=0;i<list.size();i++){
						Bbs bbs =list.get(i);
						int chk= bbsFncDao.getBBsCount(bbs.getBbsSeq(),userId,BbsFnc.FNC_TYPE_LIKE,BbsFnc.FNC_TYPE_SHARE_LIKE);
						if(chk>0){//좋아요 상태면
							bbs.setGoodStatus(1);
						}
						list.set(i, bbs);
					}
					
				
				map.put("list", list);
				map.put("count", count);
				map.put("result", true);
			} catch (Exception e) {
				map.put("result", false);
				map.put("message", e.getMessage());
			}
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;

		}
		


		/**
		 * 게시판 목록<br>
		 * @param page
		 * @param bbsGlory : 1-명예의 전당, 0-자유게시판
		 * @param category : 0-전체검색, 1~ - 카테고리별 검색
		 * @param keyword : 사용안함
		 */
		
		@RequestMapping("/m/bbs_list.go")
		public String bbsListController(
				@RequestParam(value="page", required=false, defaultValue="1") int page,
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				@RequestParam(value="category", required=false, defaultValue="0") int category,
				@RequestParam(value="gender", required=false, defaultValue="0") int gender,
				@RequestParam(value="area", required=false, defaultValue="") String area,
				@RequestParam(value="kind", required=false, defaultValue="") String kind,//친구검색인지 아닌지
				@RequestParam(value="sort", required=false, defaultValue="0") int sort,
				@RequestParam(value="keyword", required=false, defaultValue="") String keyword,
				@RequestParam(value="site", required=false, defaultValue="FACETALK") String site,
				@RequestParam(value="latitude", required=false, defaultValue="0") Double latitude,
				@RequestParam(value="longitude", required=false, defaultValue="0") Double longitude,
				HttpServletResponse res
			) {
			
			List bbsList = null;
			double bbsCount = 0;
			Map<String, Object> map = new HashMap<String, Object>();
			try{
				int chk=0;
				String did = "";
				User uu=userDao.getUsers(userId);
				//String site="";
				if(!userId.equals("")){
					site=uu.getSite();
				}
				bbsList = bbsDao.getBbsList(site,page, ITEM_COUNT_PER_PAGE, userId, category, keyword, gender, area, sort, latitude, longitude,kind);
				for(int i=0; i<bbsList.size();i++){
					Bbs bbs = (Bbs)bbsList.get(i);
					did =bbs.getUserId();
					if(bbs.getKind()==2){//공유글이면
						did = bbs.getWriterId();
					}
					chk = friendDao.getFrcorrect(userId, did);
					if(chk>0){ //친구면
						bbs.setFriendStatus(1);
					}
					chk= bbsFncDao.getBBsCount(bbs.getBbsSeq(),userId,BbsFnc.FNC_TYPE_LIKE,BbsFnc.FNC_TYPE_SHARE_LIKE);
					if(chk>0){//좋아요 상태면
						bbs.setGoodStatus(1);
					}
					bbsList.set(i, bbs);
					
				}
				bbsCount = bbsDao.getCount(site,category, keyword, gender, area,sort,latitude,longitude,kind,userId);
				map.put("result", true);
				map.put("list", bbsList);
				map.put("currentPage", page);
				map.put("itemCount", bbsCount);
				map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
				map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);
				
			}catch(Exception e){
				map.put("result", false);
				map.put("message", e.getMessage());
			}
		
			
		
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;

		}
		//상세보기 본글
		@RequestMapping("/m/bbs_view.go")
		public String bbsViewController(
				@RequestParam(value="bbsSeq", required=false, defaultValue="1") int bbsSeq,
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				/*@RequestParam(value="latitude", required=false, defaultValue="0") Double latitude,
				@RequestParam(value="longitude", required=false, defaultValue="0") Double longitude,*/
				HttpServletResponse res
			) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			try{
				Bbs bbs = bbsDao.getBbs2(bbsSeq);
				if(bbs!=null){
					String userid =bbs.getUserId();
					bbs = bbsDao.getBbsView(bbsSeq,userid);
				}
				
				if(bbs!=null){
					if(bbs.getBlindCount()==1){
					
						bbs.setBbsContents("블라인드 처리 된 게시글 입니다.");
						bbs.setFiles("");
						bbs.setThumfile("");
						bbs.setFileCount(0);
						
					}
					if(bbs.getWrite_blind_count()==1){
						bbs.setWriterComment("블라인드 처리 된 게시글 입니다.");
						bbs.setWriterFiles("");
						
						bbs.setWriterThumfiles("");
						
					}
					map.put("bbs", bbs);
					map.put("result", true);
					
					
					
				}else{
					map.put("message", "삭제되었거나 조회할 수 없는 글입니다.");
					map.put("result", true);
				}
				
			}catch(Exception e){
				map.put( "message", e.getMessage());
				map.put("result", false);
			}
			
			
			
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;

		}
		//상세보기 댓글
		@RequestMapping("/m/bbs_comment_view.go")
		public String bbsCommentViewController(
				@RequestParam(value="bbsSeq", required=false, defaultValue="1") int bbsCommentSeq,
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				/*@RequestParam(value="latitude", required=false, defaultValue="0") Double latitude,
				@RequestParam(value="longitude", required=false, defaultValue="0") Double longitude,*/
				HttpServletResponse res
			) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			try{
				BbsComment bbs = bbsCommentDao.getBbsComment(bbsCommentSeq);
				//Bbs bbs = bbsDao.getBbsView(bbsSeq,userid);
				if(bbs!=null){
					map.put("bbs", bbs);
					map.put("result", true);
				}else{
					map.put("message", "삭제되었거나 조회할 수 없는 글입니다.");
					map.put("result", false);
				}
				
			}catch(Exception e){
				map.put( "message", e.getMessage());
				map.put("result", false);
			}
			
			
			
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;

		}
		/**
		 * 내가 쓴 글 리스트<br>
		 */
		@RequestMapping("/m/my_bbs_list.go")
		public String myBbsListController(
				@RequestParam(value="keyword", required=false, defaultValue="") String keyword,
				@RequestParam(value="page", required=false, defaultValue="1") int page,
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				HttpServletResponse res
			) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			try{
				
				List bbsList = null;
				double bbsCount = 0;
				User user=userDao.getUsers(userId);
				bbsList = bbsDao.getMyBbsList(user.getSite(),page, ITEM_COUNT_PER_PAGE, userId,keyword);
				bbsCount = bbsDao.getMyBbsCount(userId,keyword);
				
				
				
				
				map.put("result", true);
				map.put("bbsList", bbsList);
				map.put("currentPage", page);
				map.put("itemCount", bbsCount);
				map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
				map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);
				
			}catch(Exception e){
				map.put("result", false);
			}
			
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;

		}
		

		

		/**
		 * 내 댓글 리스트<br>
		 */

		@RequestMapping("/m/my_comment_list.go")
		public String myCommentListController(
				@RequestParam(value="page", required=false, defaultValue="1") int page,
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				HttpServletResponse res
			) {
			
			List bbsList = null;
			double bbsCount = 0;

			//bbsList = bbsDao.getMyBbsCommentListWithIdx(page, ITEM_COUNT_PER_PAGE, userId);
			bbsList = bbsCommentDao.getMyBbsCommentList(userId, page, ITEM_COUNT_PER_PAGE);
			bbsCount = bbsCommentDao.getMyCount(userId);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bbsList", bbsList);
			map.put("currentPage", page);
			map.put("itemCount", bbsCount);
			map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
			map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);
			
			Response.responseWrite(res, map);
			return null;

		}
		
		@RequestMapping("/m/my_comment_list2.go")
		public String myCommentList2Controller(
				@RequestParam(value="page", required=false, defaultValue="1") int page,
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				HttpServletResponse res
			) {
			
			List bbsList = null;
			double bbsCount = 0;

			bbsList = bbsDao.getMyBbsCommentListWithIdx(page, ITEM_COUNT_PER_PAGE, userId);
			bbsCount = bbsDao.getMyBbsCommentCount(userId);
			
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bbsList", bbsList);
			map.put("currentPage", page);
			map.put("itemCount", bbsCount);
			map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
			map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);
			
			Response.responseWrite(res, map);
			return null;

		}
		
		@RequestMapping("/m/my_comment_list1.go")
		public String myCommentList1Controller(
				@RequestParam(value="page", required=false, defaultValue="1") int page,
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				HttpServletResponse res
			) {
			
			List bbsList = null;
			double bbsCount = 0;

			bbsList = bbsDao.getMyBbsCommentListWithIdx(page, ITEM_COUNT_PER_PAGE, userId);
			bbsCount = bbsDao.getMyBbsCommentCount(userId);
			
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bbsList", bbsList);
			map.put("currentPage", page);
			map.put("itemCount", bbsCount);
			map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
			map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);
			
			Response.responseWrite(res, map);
			return null;

		}
		
		
		
		/**
		 * 게시판 
<br>
		 */
		@RequestMapping("/m/bbs_comment_list.go")
		public String bbsCommentListController(
				HttpSession httpSession,
				@RequestParam(value="bbsSeq", required=false, defaultValue="0") int bbsSeq,
				@RequestParam(value="loginId", required=false, defaultValue="") String loginId,
				@RequestParam(value="page", required=false, defaultValue="1") int page,
				
				HttpServletResponse res
			) {
			List <BbsComment>commentList = bbsCommentDao.getBbsCommentList(bbsSeq, page, ITEM_COUNT_PER_PAGE);
			int bbsCount = bbsCommentDao.getCount(bbsSeq);
			int count = bbsDao.getcommentCount(bbsSeq);
			for(int i=0;i<commentList.size();i++){
				BbsComment bc = commentList.get(i);
				if(bc.getDislikeCount()==1){
					bc.setBbsContents("블라인드 처리 된 글입니다.");
					bc.setFiles("");
				}
				commentList.set(i,bc);
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("commentList", commentList);
			map.put("itemCount", bbsCount);
			map.put("count", count);
			map.put("currentPage", page);
			map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
			map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);
			
			Response.responseWrite(res, map);
			return null;
		}	
		
		/**
		 * 게시판 댓글의 댓글 리스트<br>
		 */
		@RequestMapping("/m/bbs_comment_reply_list.go")
		public String bbsCommentReplyListController(
				HttpSession httpSession,
				@RequestParam(value="bbsSeq", required=false, defaultValue="0") int bbsSeq,
				@RequestParam(value="commentSeq", required=false, defaultValue="0") int commentSeq,
				@RequestParam(value="page", required=false, defaultValue="1") int page,
				
				HttpServletResponse res
			) {
			List<BbsComment> commentList = bbsCommentDao.getBbsCommentReplyList(bbsSeq, page, ITEM_COUNT_PER_PAGE, commentSeq);
			int bbsCount = bbsCommentDao.getReplyCount(bbsSeq, commentSeq);
			int count = bbsCommentDao.getReplyCommentCount(commentSeq);
			//bbsDao.updateViewCount(bbsSeq);
			for(int i=0;i<commentList.size();i++){
				BbsComment bc = commentList.get(i);
				if(bc.getDislikeCount()==1){
					bc.setBbsContents("블라인드 처리 된 글입니다.");
					bc.setFiles("");
				}
				commentList.set(i,bc);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("commentList", commentList);
			map.put("itemCount", bbsCount);
			map.put("count", count);
			map.put("currentPage", page);
			map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
			map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);
			
			Response.responseWrite(res, map);
			return null;
		}	

		/**
		 * 게시판 글 등록 수정<br>
		 * <br>
		 * 등록 하는 경우 bbsSeq 값을 -1 로 입력함.<br>
		 */
		@RequestMapping("/m/bbs_edit_do.go")
		public String bbsEditDoController(
				HttpSession httpSession,
				@RequestParam(value="bbsSeq", required=false, defaultValue="-1") int bbsSeq,
				@RequestParam(value="groupSeq", required=false, defaultValue="0") int groupSeq,
				@RequestParam(value="category", required=false, defaultValue="") String category,
				@RequestParam(value="bbsHeader", required=false, defaultValue="") String bbsHeader,
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				@RequestParam(value="userPw", required=false, defaultValue="") String userPw,
				@RequestParam(value="bbsTitle", required=false, defaultValue="") String bbsTitle,
				@RequestParam(value="contents", required=false, defaultValue="") String contents,
				@RequestParam(value="photo", required=false, defaultValue="") String photo,
				@RequestParam(value="linkUrl", required=false, defaultValue="1") String linkUrl,
				@RequestParam(value="delphoto", required=false, defaultValue="") String delphoto,
				
				HttpServletResponse res
			) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			int viewCount = 0;
			int likeCount = 0;
			int dislikeCount = 0;
			int reportCount = 0;
			int commentCount = 0;
			int latitude = 0;
			int longitude = 0;
			String regDate = "";
			String userName = "";
			
			
			int fileCount = 0;
			if (photo.equals("") == false) {
				fileCount = photo.split(",").length;
			}
			
			
			if(bbsSeq == -1) {
				Bbs bbs = new Bbs();
				bbs.setBbsCategory(category);
				bbs.setBbsContents(contents);
				bbs.setBbsHeader(bbsHeader);
				bbs.setBbsSeq(bbsSeq);
				bbs.setBbsTitle(bbsTitle);
				bbs.setCommentCount(commentCount);
				bbs.setDislikeCount(dislikeCount);
				bbs.setFileCount(fileCount);
				bbs.setFiles(photo);
				
				bbs.setLikeCount(dislikeCount);
				bbs.setLinkUrl(linkUrl);
				
				bbs.setRegDate(regDate);
				bbs.setReportCount(reportCount);
				bbs.setUserId(userId);
				bbs.setUserName(userName);
				bbs.setViewCount(viewCount);
				//등록
				bbsSeq = bbsDao.addBbs(bbs);
				
				String[] fileArr = photo.split(",");
				for (int i=0; i<fileArr.length; i++) {
					String fileName = fileArr[i];
					BbsFiles bbsFile = new BbsFiles();
					bbsFile.setBbsSeq(bbsSeq);
					bbsFile.setUserId(userId);
					bbsFile.setFileName(fileName);
					bbsFilesDao.addBbsFiles(bbsFile);
				}
				
				
				User user=userDao.getUsers(userId);
				Point pp =pointDao.getSitePoint(PointMoney.JOIN_TALK,user.getSite());
				if(pp!=null){
					int point = pp.getPoint(); //포인트
					int money = pp.getMoney();
					if(pp.getPeriod()==4){
						String today = T.getToday();
						int cnt =pointmoneyDao.getcnt(userId,PointMoney.JOIN_TALK,today,today);
						if(cnt<pp.getTimes()){//정해진것 다안받으면
							levelService.inserDb(userId, PointMoney.JOIN_TALK, money, point,"토크쓰기" );
						}
					}else{
						levelService.inserDb(userId, PointMoney.JOIN_TALK, money, point,"토크쓰기" );
						
					}
				}
				
				userDao.updatedUserBBS(userId);
				map.put("bbsSeq", bbsSeq);
				map.put("result", true);
				map.put("message", "게시물이 등록되었습니다.");

				
			} else { //수정
				Bbs bbs = bbsDao.getBbs2(bbsSeq);
				bbsFilesDao.deleteBbsFile(bbs.getBbsSeq());//일단 다지우고
				if (photo.equals("") == false) {
					fileCount = photo.split(",").length;
				
					String[] fileArr = photo.split(",");
					for (int i=0; i<fileArr.length; i++) {
						String fileName = fileArr[i];
						BbsFiles bbsFile = new BbsFiles();
						bbsFile.setBbsSeq(bbsSeq);
						bbsFile.setUserId(userId);
						bbsFile.setFileName(fileName);
						bbsFilesDao.addBbsFiles(bbsFile); //다시등록
					}
				}
				if (delphoto.equals("") == false) {
					fileCount = delphoto.split(",").length;
				
					String[] fileArr = delphoto.split(",");
					for (int i=0; i<fileArr.length; i++) {
						String fileName = fileArr[i];
						filedelete(fileName);
					}
				}
				bbs.setBbsCategory(category);
				bbs.setBbsContents(contents);
				bbs.setBbsHeader(bbsHeader);
				bbs.setBbsTitle(bbsTitle);
				bbs.setCommentCount(commentCount);
				bbs.setDislikeCount(dislikeCount);
				bbs.setFileCount(fileCount);
				bbs.setFiles(photo);
				
				bbs.setLikeCount(dislikeCount);
				bbs.setLinkUrl(linkUrl);
				
				bbs.setRegDate(regDate);
				bbs.setReportCount(reportCount);
				bbs.setUserId(userId);
				bbs.setUserName(userName);
				bbs.setViewCount(viewCount);
				//수정
				boolean result = bbsDao.updateBbsContents(bbs);
				
				if(result) {
					bbs = bbsDao.getBbsView(bbsSeq,userId);
					map.put("bbs", bbs);
					map.put("bbsSeq", bbsSeq);
					map.put("result", true);
					map.put("message", "게시물이 수정되었습니다.");
				} else {
					map.put("result", false);
					map.put("bbsSeq", bbsSeq);
					map.put("message", "게시물 수정에 실패 하였습니다.");
				}
			}
			
			Response.responseWrite(res, map);
			return null;
		}	
		


		/**
		 * 영상파일업로드<br>
		 * 영상파일 압축, 썸네일 추출<br>
		 * <br>
		 * @param userId
		 * @param userPw
		 * @param fileName
		 * <br>
		 * 테스트<br>
		 * http://localhost/m_bbs_move_file_upload.go?userId=admin&userPw=jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=&fileName=abc.jpg<br>
		 * <br>
		 * 파일경로 : /data/bbs/<br>
		 * return<br>
		 * {"result":false,"filePath":"/data/bbs/","fileName":"","orgFileName":""}
		 */
		@RequestMapping("/m/bbs_move_file_upload.go")
		public String bbsMoveFileUploadDoController(
				HttpServletRequest req,
				HttpServletResponse res
			) throws Exception {

			req.setCharacterEncoding("utf-8");
			
			int fileMaxBiteSize = 999999999;//FILE_MAX_SIZE * 1024 * 1024;
			File file = null;
			
			FILE_PATH = "temp/";
			String DEST_PATH = "/data/bbs/";
			
			FILE_LOCAL_PATH = FILE_ROOT + DEST_PATH + FILE_PATH;
			String DEST_LOCAL_PATH = FILE_ROOT + DEST_PATH;
			
			
			boolean result = false;
			String message = "";
			String fileName = "";
			String fileNameOrg = "";
			
			int bbsSeq = 0;
			try {
				//파일 업로드. 
				//폼에서 가져온 인자값을 얻기 위해 request 객체 전달, 업로드 경로, 파일 최대 크기, 한글처리, 파일 중복처리
				MultipartRequest multi = new MultipartRequest(req, FILE_LOCAL_PATH, fileMaxBiteSize, "UTF-8", new DefaultFileRenamePolicy()); //
				 
				String userId = F.nullCheck(multi.getParameter("userId"),"");
				String userPw = F.nullCheck(multi.getParameter("userPw"),"");
				fileNameOrg = F.nullCheck(multi.getParameter("fileName"),"");
				
				Enumeration files = multi.getFileNames();
				while (files.hasMoreElements()) {
					String elementName = (String)files.nextElement();
					file = multi.getFile(elementName);
					
					if (file != null) {
						
						String tmpFileName = file.getName();
						File tmpFile = new File(FILE_LOCAL_PATH + tmpFileName);		
						
						String mimeType = FileMime.getMimeType(tmpFile);
						if (mimeType != "" && mimeType.split("[/]")[0].equals("video")) {
		
							//컨버팅..
							MovieConverter converter  = new MovieConverter(tmpFileName
									, DEST_LOCAL_PATH, FILE_LOCAL_PATH);
							
							fileName = converter.convert();
							result = true;
						}
									
						tmpFile.delete();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				
				message = e.getMessage();
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", result);
			map.put("fileName", fileName);
			map.put("filePath", DEST_PATH);
			map.put("orgFileName", fileNameOrg);
			
			Response.responseWrite(res, map);
			return null;
		}
		
		/**
		 * 게시판 삭제 처리<br>
		 * @param userId
		 * @param userPw
		 * @param bbsSeq
		 */
		@RequestMapping("/m/bbs_delete_do.go")
		public String bbsDeleteDoController(
				HttpSession httpSession,
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				@RequestParam(value="talkId", required=false, defaultValue="") String talkId,
				@RequestParam(value="bbsSeq", required=false, defaultValue="0") int bbsSeq,
				HttpServletResponse res
			) {
			boolean result = false;
			String message = "";
			
		    // 게시물 삭제
			try {
				
			
					Bbs bbs = bbsDao.getBbs2(bbsSeq);
					if(bbs.getFiles()!=""){
						filedelete(bbs.getFiles());
					}
					
					//일단 공유한 글 리스트가져오고
					List<Bbs>sharelist = bbsDao.getshareBbsList(bbsSeq);
					for(int i=0;i<sharelist.size();i++){
						Bbs sharebbs = sharelist.get(i);
					    deleteComment(sharebbs.getBbsSeq());//공유한글의 글 Seq로 댓글,댓글의 댓글지우고
					    List<Message>list = messageDao.getdeleteMessageList(Push.MSG_TYPE_BBS, sharebbs.getBbsSeq());
					    for(int k=0;k<list.size();k++){
					    	Message ms = list.get(k);
					    	//공유된 글에 엮인 사람들한테 푸시 리셋
					    	pushdelete(ms.getUserId(), sharebbs.getBbsSeq(),"삭제", Push.MSG_TYPE_DELETE, ms.getWriteId());
					    }
					    messageDao.deletebbsMessage(Push.MSG_TYPE_BBS, sharebbs.getBbsSeq());//공유한 글의 알림
					   
					    
					    bbsFncDao.deleteBbsGood(sharebbs.getBbsSeq());//그글에 엮인 좋아요,신고 지우고
					    //공유한 사람한테 리셋
					    pushdelete(sharebbs.getUserId(), sharebbs.getBbsSeq(),"삭제", Push.MSG_TYPE_DELETE, sharebbs.getUserId());
					    
					    bbsFilesDao.deleteshareFiles(sharebbs.getBbsSeq()); //파일리스트 삭제
					    
					}
					
					bbsDao.deleteShareBbs(bbsSeq);//이글에 공유되있는 글지우기
					bbsFncDao.deleteBbsGood(bbsSeq);//내글과 연관된 좋아요 지우기
					boolean delresult = bbsDao.deleteBbss(bbsSeq);
					if(delresult){
						deleteComment(bbsSeq);
					}
					
					bbsFilesDao.deleteBbsFile(bbsSeq);
					//bbsShareDao.deleteBbsShare2(bbsSeq);
					
					messageDao.deletebbsMessage(Push.MSG_TYPE_BBS, bbsSeq);//게시글 댓글알림
				
					messageDao.deletebbsMessage(Push.MSG_TYPE_GOOD, bbsSeq);//좋아요했던알림
					messageDao.deletebbsMessage(Push.MSG_TYPE_SHARE, bbsSeq);//공유했던 알림
					User user=userDao.getUsers(userId);
					//내글 지웟으니 포인트 차감
					Point pp = pointDao.getSitePoint(PointMoney.JOIN_TALK,user.getSite());
					if(pp!=null){
						String today = T.getToday();
						//오늘 삭제해 차감된 횟수
						int usergettime = pointmoneyDao.getcnt(userId, PointMoney.JOIN_TALK_DELETE, today, today);
						if(usergettime<pp.getTimes()){
							levelService.minusDb(userId, PointMoney.JOIN_TALK_DELETE, -pp.getMoney(), -pp.getPoint(), "토크삭제");
						}
					}
					
					//내 푸시 리셋요청
					pushdelete(bbs.getUserId(), bbs.getBbsSeq(),"삭제", Push.MSG_TYPE_DELETE, bbs.getUserId());
					result = true;
					message ="삭제되었습니다";
				
			} catch (Exception e) {
				e.printStackTrace();
				result = false;
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", result);
			map.put("message", message);
			
			
			Response.responseWrite(res, map);
			return null;
		}
		
		/**
		 * 게시판 댓글 삭제 처리<br>
		 * @param userId
		 * @param userPw
		 * @param bbsSeq
		 * @param bbsCommentSeq
		 */
		@RequestMapping("/m/bbs_Comment_delete_do.go")
		public String bbsCommentDeleteDoController(
				HttpSession httpSession,
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				@RequestParam(value="bbsSeq", required=false, defaultValue="0") int bbsSeq,
				@RequestParam(value="bbsCommentSeq", required=false, defaultValue="0") int bbsCommentSeq,
				@RequestParam(value="pbbsCommentSeq", required=false, defaultValue="0") int pbbsCommentSeq,
				HttpServletResponse res
			) {
			boolean result = false;
			String message = "";
			
			Map<String, Object> map = new HashMap<String, Object>();
			// 게시물 삭제
			try {
				BbsComment bc = bbsCommentDao.getBbsComment(bbsCommentSeq);
				//댓글
				if(pbbsCommentSeq==0){
					
					if(bc!=null){
						
							if(bc.getFiles()!=""){
								filedelete(bc.getFiles());
							}
							boolean delresult = bbsCommentDao.deleteBbsComment(bbsCommentSeq);
							
							if(delresult) {
								List<BbsComment>list = bbsCommentDao.getRBbsComment(bbsCommentSeq);
								if(list!=null){
									Iterator<BbsComment>it = list.iterator();
									while(it.hasNext()){
										BbsComment rbc = it.next();
										if(rbc.getFiles()!=""){
											filedelete(rbc.getFiles());
										}
										//댓글에답글 신고내역삭제
										bbsFncDao.deleteBbsCommentGood(rbc.getBbsCommentSeq());
									}
								}
								bbsCommentDao.deleteBbsReplyCommentAll(bbsCommentSeq);
								List<Message>list2 =messageDao.getdeleteMessageList(Push.MSG_TYPE_BBS_COMMENT, bbsCommentSeq);
								for(int i =0;i<list2.size();i++){
									Message ms = list2.get(i);
									pushdelete(ms.getUserId(), bbsCommentSeq, "", Push.MSG_TYPE_DELETE, ms.getUserId());
								}
								messageDao.deletebbsMessage(Push.MSG_TYPE_BBS_COMMENT, bbsCommentSeq);
								int rCount = bbsCommentDao.getCount(bbsSeq);										
								bbsDao.updateCommentCount(bbsSeq, rCount);	
								bbsFncDao.deleteBbsGood(bbsCommentSeq);//신고내역삭제
							}
							
							pushdelete(userId, bbsCommentSeq, "", Push.MSG_TYPE_DELETE, userId);
												
							result = true;
							message ="삭제되었습니다.";
					}
				}else{ //댓글에 댓글
					 if(bc.getFiles()!=""){
							filedelete(bc.getFiles());
					 }
						boolean delresult = bbsCommentDao.deleteBbsComment(bbsCommentSeq);
						
						// 댓글의 댓글들 삭제
						if(delresult) {
							List<BbsComment>list = bbsCommentDao.getRBbsComment(bbsCommentSeq);
							if(list!=null){
								Iterator<BbsComment>it = list.iterator();
								while(it.hasNext()){
									BbsComment rbc = it.next();
									if(rbc.getFiles()!=""){
										filedelete(rbc.getFiles());
									}
									bbsFncDao.deleteBbsCommentGood(rbc.getBbsCommentSeq());
									
								}
							}
							
							bbsCommentDao.deleteBbsReplyCommentAll(bbsCommentSeq);	
							
							int rCount = bbsCommentDao.getCommentCount(bbsSeq, pbbsCommentSeq);										
							bbsCommentDao.updateCommentCount(pbbsCommentSeq, rCount);
							rCount = bbsCommentDao.getCount(bbsSeq);										
							bbsDao.updateCommentCount(bbsSeq, rCount);	
							bbsFncDao.deleteBbsGood(pbbsCommentSeq);//신고내역삭제
							
						}
						result = true;
						message ="삭제되었습니다.";
				}
				User user= userDao.getUsers(userId);
				Point pp = pointDao.getSitePoint(PointMoney.JOIN_COMMENT,user.getSite());
				if(pp!=null){
					String today = T.getToday();
					//오늘 삭제해 차감된 횟수
					int usergettime = pointmoneyDao.getcnt(userId, PointMoney.JOIN_COMMENT_DELETE, today, today);
					if(usergettime<pp.getTimes()){
						levelService.minusDb(userId, PointMoney.JOIN_COMMENT_DELETE, -pp.getMoney(), -pp.getPoint(), "댓글삭제");
					}
				}
			} catch (Exception e) {
				result = false;
				message =e.getMessage();
			}

		
			map.put("result", result);
			map.put("message", message);
			
			
			Response.responseWrite(res, map);
			return null;
		}
		
		
		
		/**
		 * 게시판 댓글 등록 수정<br>	  
		 * 등록 : bbsCommentSeq : -1<br>
		 * 수정 : bbsCommentSeq : 댓글 시퀀스<br>
		 */
		@RequestMapping("/m/bbs_comment_edit_do.go")
		public String bbsCommentEditDoController(
				HttpSession httpSession,
				@RequestParam(value="bbsCommentSeq", required=false, defaultValue="-1") int bbsCommentSeq,
				@RequestParam(value="bbsSeq", required=false, defaultValue="-1") int bbsSeq,
				@RequestParam(value="category", required=false, defaultValue="") String category,
				@RequestParam(value="bbsHeader", required=false, defaultValue="") String bbsHeader,
				@RequestParam(value="userId", required=true, defaultValue="") String userId,
				@RequestParam(value="userPw", required=true, defaultValue="") String userPw,
				@RequestParam(value="bbsTitle", required=false, defaultValue="") String bbsTitle,
				@RequestParam(value="bbsContents", required=false, defaultValue="") String bbsContents,
				@RequestParam(value="bbsContentsText", required=false, defaultValue="") String bbsContentsText,
				@RequestParam(value="files", required=false, defaultValue="") String files,
				@RequestParam(value="linkUrl", required=false, defaultValue="1") String linkUrl,
				@RequestParam(value="extraContents", required=false, defaultValue="1") String extraContents,
				@RequestParam(value="rLevel", required=false, defaultValue="0") int rLevel,
				@RequestParam(value="rCommentSeq", required=false, defaultValue="-1") int rCommentSeq,
				
				HttpServletResponse res
			) {
			Map<String, Object> map = new HashMap<String, Object>();
			boolean result = false;
			String message = "";
			
			int viewCount = 0;
			int likeCount = 0;
			int dislikeCount = 0;
			int reportCount = 0;
			int fileCount = 0;
			int commentCount = 0;
			int latitude = 0;
			int longitude = 0;
			String regDate = "";
			String userName = "";
			
			if(rLevel==0){//댓글이다
				map.put("bbsCommentSeq",bbsCommentSeq);
			}else{
				map.put("bbsCommentSeq",rCommentSeq);
			}
			if (bbsContentsText.equals("")) {
				bbsContentsText = bbsContents;
			}
			
			
			BbsComment bbs = new BbsComment();
			
			if (files.equals("") == false) {
				fileCount = files.split(",").length;
			}
			bbs.setBbsCommentSeq(bbsCommentSeq);
			bbs.setBbsCategory(category);
			bbs.setBbsContents(bbsContentsText);
			bbs.setBbsHeader(bbsHeader);
			bbs.setBbsSeq(bbsSeq);
			bbs.setBbsTitle(bbsTitle);
			bbs.setCommentCount(commentCount);
			bbs.setDislikeCount(dislikeCount);
			bbs.setExtraContents(extraContents);
			bbs.setFileCount(fileCount);
			bbs.setFiles(files);
			
			
			bbs.setLikeCount(dislikeCount);
			bbs.setLinkUrl(linkUrl);
			
			bbs.setRegDate(regDate);
			bbs.setReportCount(reportCount);
			bbs.setUserId(userId);
			bbs.setViewCount(viewCount);
			bbs.setrLevel(rLevel);
			bbs.setrCommentSeq(rCommentSeq);
			try{
				Bbs bbschk = bbsDao.getuBbs(bbsSeq);
				int chkmecnt = chatBlockDao.getBlockmechk(userId, bbschk.getUserId());
				int chkothercnt = chatBlockDao.getBlockotherchk(userId, bbschk.getUserId());
				if(bbsCommentSeq > -1) {
					//수정.
					
					if(chkmecnt==0){
						if(chkothercnt==0){
							
								result  = bbsCommentDao.updateBbsCommentContents(bbs);
								message = "댓글이 수정 되었습니다.";	
						}else{
							message = bbschk.getUserName()+"님이 당신을 차단하여 삭제만 가능합니다.";
						}
					}else{
						message = "차단 해제 후 이용해주세요.";
					}
				} else {
					//등록
					if(bbschk!=null){
					
						if(chkmecnt==0){
							if(chkothercnt==0){
								result  = bbsCommentDao.addBbsComment(bbs);				
								if(result) {					 
									
										int rCount = bbsCommentDao.getCommentCount(bbsSeq, rCommentSeq);										
										bbsCommentDao.updateCommentCount(rCommentSeq, rCount);					
										rCount = bbsCommentDao.getCount(bbsSeq);										
										bbsDao.updateCommentCount(bbsSeq, rCount);												
									
									User user=userDao.getUsers(userId);
									Point pp =pointDao.getSitePoint(PointMoney.JOIN_COMMENT,user.getSite());
									if(pp!=null){
										int point = pp.getPoint(); //포인트
										int money = pp.getMoney();
										if(pp.getPeriod()==4){
											String today = T.getToday();
											int cnt =pointmoneyDao.getcnt(userId,PointMoney.JOIN_COMMENT,today,today);
											
											if(cnt<pp.getTimes()){//정해진것 다안받으면
												levelService.inserDb(userId, PointMoney.JOIN_COMMENT, money, point,"댓글등록" );
											}
										}else{
											levelService.inserDb(userId, PointMoney.JOIN_COMMENT, money, point,"댓글등록" );
											
										}
									}
									
									//user = userDao.getUser(userId);
									Bbs bbss = bbsDao.getBbs2(bbsSeq);
									String txt =txtLength(bbss.getBbsContents());
									String pushMessage = user.getUserName() +"님이 댓글을 등록하셨습니다.:" + txt;
									List<Bbs> list = bbsDao.getBbspushList(bbsSeq);//글에 속해있는 유저리스트	
									//List Imglist= userDao.getUserAgentImagineList(user.getAgentId());
									
									for(int i=0;i<list.size();i++){
										Bbs bb = list.get(i);
										
										if(!bb.getUserId().equals(userId)){
											if(rLevel==0){//댓글이다
												//if(!Imglist.contains(bb.getUserId())){
													push(bb.getUserId(), bbsSeq, pushMessage, Push.MSG_TYPE_BBS,userId);
												//}
												
												
											}else{//댓글의 댓글이다 
												BbsComment bc = bbsCommentDao.getBbsCommentone(bbsSeq,rCommentSeq);
												txt = txtLength(bc.getBbsContents());
												pushMessage = user.getUserName() +"님이 댓글에 답글을 등록하셨습니다.:" + txt;
												//if(!Imglist.contains(bb.getUserId())){
													push(bb.getUserId(), rCommentSeq, pushMessage, Push.MSG_TYPE_BBS_COMMENT,userId);
												//}
												
											}
											
										}
											
									}
									result =true;
									message = "댓글이 등록 되었습니다.";
								
								}else{
									message = "댓글 등록에 실패 하였습니다.";
								}
							}else{
								message = bbschk.getUserName()+"님이 당신을 차단하였습니다.";
							}
						}else{
							message = "차단 해제 후 이용해주세요.";
						}
					} 
				}
			}catch(Exception e){
				message = e.getMessage();
			}
			
			
			
		
			map.put("result", result);
			map.put("message", message);
			
			Response.responseWrite(res, map);
			return null;
		}
		
		
		
	

		/**
		 * 게시물 좋아요 등록<br>
		
		 * <br>
		 * @param bbsSeq : -1이 아니면 게시물에 좋아요 등록
		 * @param bbsSeqComment : bbsSeq가 -1이고 bbsSeqComment가 -1이 아니면 댓글에 좋아요 등록
		 * @param userId
		 * @param res
		 */
		@RequestMapping("/m/bbs_like.go")
		public String bbsLikeController(
				@RequestParam(value="bbsSeq", required=false, defaultValue="-1") int bbsSeq,
				@RequestParam(value="bbsSeqComment", required=false, defaultValue="-1") int bbsSeqComment,
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				HttpServletResponse res
			) {
			boolean result = false;
			String message = "";
			Map<String, Object> map = new HashMap<String, Object>();
		
			int type = BbsFnc.FNC_TYPE_LIKE;
			
			
			Bbs bbs = bbsDao.getBbs2(bbsSeq);
			if(bbs.getShareBbsSeq()>0){//공유글이면
				type = BbsFnc.FNC_TYPE_SHARE_LIKE;
			}
			int goodCount = bbsFncDao.getBBsCount(bbsSeq, userId,type);
			
			if (goodCount>0) { //이미 좋아요 눌렀으면->좋아요 취소
				bbsFncDao.deleteBbsFncGood(bbsSeq, userId, type);
				int value = bbsFncDao.getSum(BbsFnc.FNC_TYPE_LIKE,BbsFnc.FNC_TYPE_SHARE_LIKE, bbsSeq, bbsSeqComment);
				bbsDao.updateLikeCount(bbsSeq, value);
				
				if(!bbs.getUserId().equals(userId)){ //내글 좋아요가 아니면.
					pushdelete(userId, bbsSeq, "", Push.MSG_TYPE_DELETE, userId);
					messageDao.deletegoodMessage(userId,Push.MSG_TYPE_GOOD,bbsSeq);
				}
				
				map.put("goodCount", value);
				map.put("goodStatus", 0);
				
				result = true;
				message = "좋아요가 해제되었습니다.";
			} else {
				BbsFnc bbsFnc = new BbsFnc();
				bbsFnc.setFncType(type);
				bbsFnc.setBbsSeq(bbsSeq);
				bbsFnc.setBbsCommentSeq(bbsSeqComment);
				bbsFnc.setFncValue(2);
				bbsFnc.setUserId(userId);
				result = bbsFncDao.addBbsFnc(bbsFnc);
				User user = userDao.getUsers(userId);
				if(result) {
					
					if(bbsSeq > -1) {
						int value = bbsFncDao.getSum(BbsFnc.FNC_TYPE_LIKE,BbsFnc.FNC_TYPE_SHARE_LIKE, bbsSeq, bbsSeqComment);
						
						bbsDao.updateLikeCount(bbsSeq, value);
						map.put("goodCount", value);
						map.put("goodStatus", 1);
					} else if (bbsSeqComment > -1) {
						int value = bbsFncDao.getSum(BbsFnc.FNC_TYPE_LIKE,BbsFnc.FNC_TYPE_SHARE_LIKE, bbsSeq, bbsSeqComment);
						map.put("goodCount", value);
						bbsCommentDao.updateLikeCount(bbsSeqComment, value);					
					}
					String txt = txtLength(bbs.getBbsContents());
					String pushmsg = user.getUserName()+"님이 회원님의 토크를 좋아합니다.:"+txt;
					//List<User>Imglist= userDao.getUserAgentImagineList(user.getAgentId());
					if(!bbs.getUserId().equals(userId)){ //내글 좋아요가 아니면.
					//	if(!Imglist.contains(bbs.getUserId())){	
							push(bbs.getUserId(), bbsSeq,pushmsg, Push.MSG_TYPE_GOOD, userId);
						//}
					}
					
					message = "좋아요가 등록 되었습니다.";
				} else {
					message = "좋아요 등록에 실패 하였습니다.";
				}			
			}
			
			map.put("result", result);
			map.put("message", message);
			
			Response.responseWrite(res, map);
			return null;
		}	
	
		
		
		
		/**
		 * 게시물 신고 등록<br>
		 * <br>
		 * @param bbsSeq : -1이 아니면 게시물에 신고 등록
		 * @param bbsSeqComment : bbsSeq가 -1이고 bbsSeqComment가 -1이 아니면 댓글에 신고 등록
		 * @param userId
		 */
		@RequestMapping("/m/bbs_report.go")
		public String bbsReportController(
				@RequestParam(value="talkSeq", required=false, defaultValue="-1") int bbsSeq,
				@RequestParam(value="bbsSeqComment", required=false, defaultValue="-1") int bbsSeqComment,
				@RequestParam(value="contents", required=false, defaultValue="") String contents,
				@RequestParam(value="photo", required=false, defaultValue="") String photo,
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				@RequestParam(value="blockId", required=false, defaultValue="") String blockId,
				@RequestParam(value="fncReason", required=false, defaultValue="") int fncReason,
				HttpServletResponse res
			) {
			boolean result = false;
			String message = "";
			int kind  =0;
			int fncCount =0;
			if(bbsSeq>0){ //본글신고
				fncCount = bbsFncDao.getBBsCount(bbsSeq, userId ,BbsFnc.FNC_TYPE_REPORT);
			}else{
				fncCount = bbsFncDao.getBBsCommentCount(bbsSeqComment, userId, BbsFnc.FNC_TYPE_REPORT);
			}
			
			
			//String blockId = (bbsDao.getBbs2(bbsSeq)).getUserId(); //글쓴이 아이디
			Friend ff = friendDao.getFr(userId, blockId);
			
			if(ff==null){ //친구가 아니면
				
				if (fncCount>0) {
					result = false;
					message = "이미 신고한 토크입니다.";
				}else{
					if(bbsSeq==-1){ //댓글이면*/
						kind =1;
					}else{
						kind =2;
					}
					
						BbsFnc bbsFnc = new BbsFnc();
						bbsFnc.setBbsSeq(bbsSeq);//글번호
						bbsFnc.setFncType(BbsFnc.FNC_TYPE_REPORT); //타입 신고인지 좋아요인지 이런컬럼
						bbsFnc.setBbsCommentSeq(bbsSeqComment); //댓글번호
						bbsFnc.setFncValue(kind); //댓글인지본글인지 
						bbsFnc.setUserId(userId);//신고자
						bbsFnc.setContents(contents);//자세한내용신고
						bbsFnc.setPhoto(photo);//사진
						bbsFnc.setFncReason(fncReason); //신고사유 숫자로들어옴
						result = bbsFncDao.addBbsFnc(bbsFnc);
						
					
				}
					//String did = "";
					if(result) {
						if(bbsSeq > -1) { //본글이면
							int value = bbsFncDao.getCount(bbsSeq, bbsSeqComment); //해당글의 신고건수 카운트
							bbsDao.updateReportCount(bbsSeq, value); //업데이트
							
						} else if (bbsSeqComment > -1) {
							int value = bbsFncDao.getCount(bbsSeq, bbsSeqComment);
							bbsCommentDao.updateReportCount(bbsSeqComment, value);
						
						}
						
						
						
						message = "신고가 등록되었습니다.";
						result = true;
					} 
			}else{
				message = "친구의 글은 신고할 수 없습니다.";
				result = false;
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", result);
			map.put("message", message);
			
			Response.responseWrite(res, map);
			return null;
		}	
		
		
		
		/**
		 * 게시물 조회수 추가<br>
		 * <br>
		 * @param bbsSeq
		 * @param userId
		 */
		@RequestMapping("/m/bbs_view_count.go")
		public String bbsViewCountController(
				@RequestParam(value="bbsSeq", required=false, defaultValue="-1") int bbsSeq,		
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				HttpServletResponse res
			) {
			boolean result = true;
			String message = "";
			
			int count = bbsVisitDao.getBbsVisitCount(bbsSeq, userId);
			if (count == 0) {
				BbsVisit visit = new BbsVisit();
				visit.setBbsSeq(bbsSeq);
				visit.setUserId(userId);
				bbsVisitDao.addBbsVisit(visit);
				result = bbsDao.updateViewCount(bbsSeq);
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", result);
			map.put("message", message);
			
			Response.responseWrite(res, map);
			return null;
		}
		


	

		/**
		 * 시도<br>
		 */
		@RequestMapping("/m/sido.go")
		public String sidoController(
				HttpSession httpSession,
				HttpServletResponse res
			) {
			Map<String, Object> map = new HashMap<String, Object>();
		
			try {
				List list = sidoDao.getSidoList();
				map.put("result", true);
				map.put("list", list);
			} catch (Exception e) {
				map.put("result", false);
				map.put("message", e.getMessage());
			}
			
			Response.responseWrite(res, map);
			return null;
		}

		//공유
		@RequestMapping("/m/bbs_share.go")
		public String bbsShareController(
				@RequestParam(value="bbsSeq", required=true, defaultValue="0") int bbsSeq,
				@RequestParam(value="userId", required=true, defaultValue="") String userId,
				HttpServletRequest req,
				HttpServletResponse res
		){
			Map<String, Object> map = new HashMap<String, Object>();
			
			try {
				Bbs bbs = bbsDao.getBbs2(bbsSeq);//bbsSeq 로 가져옴
				int chk = bbsDao.getBbsSharechk(userId,bbsSeq);//중복체크
				if(chk==0){
					if(bbs!=null){
						User user = userDao.getUsers(userId);//유저 아이디
						
						Bbs sharebbs = new Bbs(); //공유한글을 내글로
						sharebbs.setBbsCategory(bbs.getBbsCategory());
						sharebbs.setBbsHeader(bbs.getBbsHeader());
						sharebbs.setUserId(userId);
						sharebbs.setBbsTitle(bbs.getBbsTitle());
						sharebbs.setShareBbsSeq(bbsSeq);
						sharebbs.setShareWriteId(bbs.getUserId());
						int seq = bbsDao.addBbs(sharebbs); 
						
						String txt = txtLength(bbs.getBbsContents()) ;
						
						String pushmsg = user.getUserName()+"님이 회원님의 토크를 공유하였습니다.:"+txt;
						push(bbs.getUserId(), bbsSeq, pushmsg, Push.MSG_TYPE_SHARE, userId);
						map.put("result", true);
						map.put("message", "공유하였습니다.");
					}
				}else{
					map.put("result", false);
					map.put("message", "이미 공유하였습니다.");
				}
			} catch (Exception e) {
				map.put("result", false);
				map.put("message", e.getMessage());
			}
			
			Response.responseWrite(res, map);
			return null;
		}
		
	
		/**
		 * 주제<br>
		 */
		@RequestMapping("/m/category.go")
		public String categoryController(
				HttpSession httpSession,
				HttpServletResponse res
			) {
			Map<String, Object> map = new HashMap<String, Object>();
		
			// 게시물 삭제
			try {
				List list = bbsCategoryDao.getBbsCategoryList();
				map.put("result", true);
				map.put("list", list);
			} catch (Exception e) {
				map.put("result", false);
				map.put("message", e.getMessage());
			}
			JSONObject jsonObject = JSONObject.fromObject(map);	
			Response.responseWrite(res, map);
			return null;
		}
		
		/**
		 * 거리<br>
		 */
		@RequestMapping("/m/distance.go")
		public String distanceController(
				HttpSession httpSession,
				HttpServletResponse res
			) {
			Map<String, Object> map = new HashMap<String, Object>();
		
			
			try {
				List list = distanceDao.getDistanceList();
				map.put("result", true);
				map.put("list", list);
			} catch (Exception e) {
				map.put("result", false);
				map.put("message", e.getMessage());
			}
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;
		}
	
		
		/**
		 * 회원검색<br>
		 * @param page
		 * @param bbsGlory : 1-명예의 전당, 0-자유게시판
		 * @param category : 0-전체검색, 1~ - 카테고리별 검색
		 * @param keyword : 사용안함
		 * @kind 친구 -> 친구목록 전체 ->전체목록
		 */
		
		@RequestMapping("/m/search_mem.go")
		public String searchmemController(
				@RequestParam(value="kind", required=false, defaultValue="") String kind,
				@RequestParam(value="page", required=false, defaultValue="1") int page,
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				@RequestParam(value="category", required=false, defaultValue="0") int category,//1인기도,2레벨,0전체
				@RequestParam(value="age", required=false, defaultValue="0") int age,//10대 :10 20대: 20
				@RequestParam(value="gender", required=false, defaultValue="0") int gender,
				@RequestParam(value="area", required=false, defaultValue="") String area,
				@RequestParam(value="sort", required=false, defaultValue="0") int sort, //거리순 5km ->3 10:4 15:5
				@RequestParam(value="keyword", required=false, defaultValue="") String keyword,//검색어
				@RequestParam(value="latitude", required=false, defaultValue="0") Double latitude,
				@RequestParam(value="longitude", required=false, defaultValue="0") Double longitude,
				HttpServletResponse res
			) {
			
			List bbsList = null;
			double bbsCount = 0;
			User user = new User();
			Map<String, Object> map = new HashMap<String, Object>();
			try{
				int sage =  user.getUserYear(age);
				if(kind.equals("친구")){
					
					bbsList = userDao.getfsearchList(page, ITEM_COUNT_PER_PAGE, 
							userId, category, 
							sage, keyword, 
							gender, area, 
							sort, latitude, longitude);
					
					bbsCount = userDao.getfreindCount(userId,keyword,sage,gender,area,category,sort, latitude, longitude);
					map.put("result", true);
				}
				if(kind.equals("전체")){
					user = userDao.getUsers(userId);
					
					bbsList = userDao.getsearchList(user.getSite(),page, ITEM_COUNT_PER_PAGE, userId, category, sage, keyword, gender, area, sort, latitude, longitude);
					bbsCount = userDao.getUserSiteCount(user.getSite(),userId,category, gender, area ,sage,keyword, sort, latitude, longitude);
					map.put("result", true);
				}
			}catch(Exception e){
				map.put("result", false);
				map.put("message", e.getMessage());
			}
		
			
			
			map.put("bbsList", bbsList);
			map.put("currentPage", page);
			map.put("itemCount", bbsCount);
			map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
			map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);
			
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;

		}
		
		
		
		
		//프로필수정
		@RequestMapping("m/user_profile_edit.go")
		public String profileController(
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				@RequestParam(value="userName", required=false, defaultValue="") String userName,
				@RequestParam(value="comment", required=false, defaultValue="") String comment,//한줄소개
				@RequestParam(value="photo", required=false, defaultValue="") String photo, //전에 파일이름
				@RequestParam(value="newphoto", required=false, defaultValue="") String newphoto,//업로드할 파일이름
			    @RequestParam(value="area", required=false, defaultValue="") String area,
				
				HttpServletResponse res
			) {
			boolean result = true;
			String message ="";
			User uu = userDao.getUsers(userId);
			String beforeName=uu.getUserName();
			Map<String, Object> map = new HashMap<String, Object>();
			
			try{	
				
				if(!comment.equals(uu.getComment())){
					uu.setComment(comment);
					userDao.updatefUser(uu);
					message="변경되었습니다.";
				}
				if(!area.equals("")){
					uu.setArea(area);
					userDao.updatefUser(uu);
					message="변경되었습니다.";
				}
				if(!newphoto.equals("")){
					
					
					uu = userDao.getUsers(userId);
					if(uu.getPhotoStatus()==0){//프로필 최초 등록자야
					
						uu.setPhotoStatus(1);
						Point pp =pointDao.getSitePoint(PointMoney.JOIN_PHOTO,uu.getSite());
						
						if(pp!=null){
							int point = pp.getPoint(); //포인트
							int money = pp.getMoney(); //머니
							levelService.inserDb(userId, PointMoney.JOIN_PHOTO, money, point,"프사등록");
						}
					}
					uu.setPhoto(newphoto);
					message="변경되었습니다.";
					userDao.updateUserphoto(uu);
				}
				if(!userName.equals("")){//닉네임변경
					if(!beforeName.equals(userName)){
						Point pp =pointDao.getSitePoint(PointMoney.JOIN_CHANGE_NAME,uu.getSite());
						
							if(pp!=null){
								int point = pp.getPoint(); //포인트
								int money = pp.getMoney(); //머니
								if(uu.getPoint()<-point){
									result = false;
									message="포인트가 부족하여 닉네임을 변경할 수 없습니다.";
								}else{
									levelService.inserDb(userId, PointMoney.JOIN_CHANGE_NAME, money, point, "닉네임변경");
									uu.setUserName(userName);
									message="변경되었습니다.";
								}
								
							}else{
								uu.setUserName(userName);
								message="변경되었습니다.";
							}
					}
					userDao.updatefUser(uu);
					
				}
				map.put("result", result);
				map.put("message", message);
			
			}catch(Exception e){
				map.put("result", false);
				map.put("message", "실패하였습니다.");
			
			}
			
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;

		}
		
		//친구삭제
		@RequestMapping("/m/user_fdelete.go")
		public String ufdeleteController(
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				@RequestParam(value="friendId", required=false, defaultValue="") String friendId,
				
				
				HttpServletResponse res
			) {
			boolean result = false;
			String message ="";
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			try{
				friendDao.deleteFriend(friendId, userId);
				result = true;
				message = "삭제되었습니다.";
			}catch(Exception e){
				message = "실패하였습니다.";
			}
			
			
			
			map.put("result", result);
			map.put("message", message);
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;

		}
		
		//차단리스트
		@RequestMapping("/m/user_block.go")
		public String ublockController(
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				@RequestParam(value="page", required=false, defaultValue="") int page,
			
				HttpServletResponse res
			) {
			boolean result = false;
			String message ="";
			List<ChatBlock>list = null;
			Map<String, Object> map = new HashMap<String, Object>();
			
			
			list = chatBlockDao.getBlockList(page, ITEM_COUNT_PER_PAGE,userId);
			int count = chatBlockDao.getBlockCount(userId);	
			
			map.put("list", list);
			map.put("currentPage", page);
			map.put("itemCount", count);
			map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
			map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);
		
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;

		}
		
		//차단해제
		@RequestMapping("/m/user_rblock.go")
		public String rblockController(
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				@RequestParam(value="bId", required=false, defaultValue="") String bId,
				HttpServletResponse res
			) {
			boolean result = false;
			String message ="";
			List<ChatBlock>list = null;
			Map<String, Object> map = new HashMap<String, Object>();
			
			try{
				chatBlockDao.deleteBlock(userId, bId);
				message = "해제되었습니다.";
				result = true;
			}catch(Exception e){
				message = "실패하였습니다.";
			}
				
			map.put("result", result);
			map.put("message", message);
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;

		}
		
		//탈퇴
		@RequestMapping("/m/drop_user.go")
		public String dropController(
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				@RequestParam(value="dropReason", required=false, defaultValue="") String dropReason,
				HttpServletResponse res
			) {
			boolean result = false;
			String message ="";
			List<ChatBlock>list = null;
			Map<String, Object> map = new HashMap<String, Object>();
			
			try{
				String photoname =(userDao.getUser(userId)).getPhoto();
				
				if(!photoname.equals("")){
					filedelete(photoname);
				}
				
				userDao.updatedUser(2,userId,dropReason);//유저테이블 회원 상태 2
				
				friendDao.deleteFriend(userId);//친구 상태 삭제
				friendRequestDao.deleteFriendRequest(userId);//친구요청 삭제
				chatRequestDao.deleteChatRequest(userId);//채팅 요청 삭제
				pointmoneyDao.deletePointMoney(userId);//포인트 기록 삭제
				levelhistoryDao.deleteLevelHistory(userId);//레벨 히스토리 삭제 
				
				loginlogDao.deleteLoginlog(userId);//로그인 로그 삭제 
				//토크지우기
				List<Bbs> bblist =null;
				bblist =bbsDao.getBbsList(userId); //일단 내 토크목록가져온뒤에
				if(bblist!=null){
					Iterator<Bbs> it = bblist.iterator();
					while(it.hasNext()){
						Bbs bbs = it.next();
						int bbsSeq = bbs.getBbsSeq(); //내 글의 seq 로
						if(!bbs.getFiles().equals("")){
							filedelete(bbs.getFiles());
						}
						//일단 공유한 글 리스트가져오고
						List<Bbs>sharelist = bbsDao.getshareBbsList(bbsSeq);
						for(int i=0;i<sharelist.size();i++){
							Bbs sharebbs = sharelist.get(i);
						    deleteComment(sharebbs.getBbsSeq());//공유한글의 글 Seq로 댓글,댓글의 댓글지우고
						    List<Message>list2 = messageDao.getdeleteMessageList(Push.MSG_TYPE_BBS, sharebbs.getBbsSeq());
						    for(int k=0;k<list2.size();k++){
						    	Message ms = list2.get(k);
						    	//공유된 글에 엮인 사람들한테 푸시 리셋
						    	pushdelete(ms.getUserId(), sharebbs.getBbsSeq(),"삭제", Push.MSG_TYPE_DELETE, ms.getWriteId());
						    }
						    messageDao.deletebbsMessage(Push.MSG_TYPE_BBS, sharebbs.getBbsSeq());
						    bbsFncDao.deleteBbsGood(sharebbs.getBbsSeq());//그글에 엮인 좋아요,신고 지우고
						    bbsFilesDao.deleteshareFiles(sharebbs.getBbsSeq()); //파일리스트 삭제
						    bbsDao.deleteShareBbs(bbsSeq);//이글에 공유되있는 글지우기
							
						}
						
						
						//bbsShareDao.deleteBbsShare2(bbsSeq);
						bbsFncDao.deleteBbsGood(bbsSeq);//내글과 연관된 좋아요 지우기
						boolean delresult = bbsDao.deleteBbss(bbsSeq);
						if(delresult){
							deleteComment(bbsSeq);
						}
					}
				}
				bbsDao.deleteBbs(userId); //내글과 내가 공유한글 삭제
				//bbsCommentDao.deleteBbsComment(userId);//내 댓글 삭제..?
				bbsVisitDao.deleteBbsVisit(userId);//토크 방문기록 삭제
				guestBookDao.deleteGuestBook(userId);//방명록삭제
				bbsShareDao.deleteuserShare(userId); //공유기록 삭제
				chatBlockDao.deleteBlock(userId); //차단기록 삭제
				List<Message>userlist=messageDao.getdeleteuserMessageList(userId);
				for(int i=0;i<userlist.size();i++){
					Message ms = userlist.get(i);
					pushdelete(ms.getUserId(), 0, "", Push.MSG_TYPE_DELETE, userId);
				}
				messageDao.deleteMessage(userId);//알림 내역 삭제
				useritemDao.deleteUserItem(userId);//아이템 내역 삭제
			    bbsFncDao.deleteBbsFnc(userId);//신고내역을 삭제해야할까
			    giftDao.deletedropGIFT(userId);//선물내역삭제
				
			    System.out.println("dddddd");
				
				List<BbsFiles> filelist = bbsFilesDao.getAlbumList(userId);
				if(filelist!=null){
					Iterator<BbsFiles> it2 = filelist.iterator();
					while(it2.hasNext()){
						String filename = (it2.next()).getFileName();
						if(!filename.equals("")){
							filedelete(filename);
						}
						
					}
					
					bbsFilesDao.deleteFiles(userId); //파일리스트 삭제
				}
				  
				  eventCommentDao.deleteEventComment(userId); //이벤트 댓글삭제
				    List<Qna>qanlist = qnaDao.getQnaList(userId);
				    for(int i=0;i<qanlist.size();i++){
				    	Qna qq = qanlist.get(i);
				    	int seq =qq.getNoticeSeq();
				    	
						qnaDao.deleteQna(seq);
						qnaDao.deleteQnaComment(seq);
				    }
				
				
					userFncDao.deleteUserFnc(userId); //유저 신고당한 내역 한내역 삭제
					chatOutDao.deleteChatOut(userId); //채팅 강퇴내역 삭제
				
				//채팅에 관련한 요청들.
				ChatReq cReq = new ChatReq();
				cReq.setMemberID(userId);
				cReq.setReqTYPE("10");
				chatReqDao.addChatReq(cReq);
				System.out.println("ddd");
				message = "탈퇴되었습니다.";
				result = true;
			}catch(Exception e){
				message = "실패하였습니다.";
				System.out.println(e.getMessage());
			}
			
			map.put("result", result);
			map.put("message", message);
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;

		}
	
		
		//더보기-앨범
		@RequestMapping("/m/album_list.go")
		public String albumController(
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				@RequestParam(value="page", required=false, defaultValue="1") int page,
				@RequestParam(value="gender", required=false, defaultValue="0") int gender, //성별
				@RequestParam(value="area", required=false, defaultValue="") String area, //지역	
				@RequestParam(value="age", required=false, defaultValue="0") int age, //연령
				@RequestParam(value="sort", required=false, defaultValue="0") int sort, //거리 
				@RequestParam(value="keyword", required=false, defaultValue="") String keyword,//검색어
				@RequestParam(value="latitude", required=false, defaultValue="0") Double latitude, 
				@RequestParam(value="longitude", required=false, defaultValue="0") Double longitude,
				HttpServletRequest req, HttpServletResponse res
			){
			
			List bbsList = null;
			double bbsCount = 0;
			User user = new User();
			
			
			int sage =  user.getUserYear(age);
			user=userDao.getUsers(userId);
			bbsList = bbsFilesDao.getalbumList(user.getSite(),page, ITEM_COUNT_PER_PAGE, userId,sage, keyword, gender, area, sort, latitude, longitude);
			bbsCount = bbsFilesDao.getalbumCount(user.getSite(),gender, area ,sage,keyword,sort,latitude,longitude);
			int uphoto =0;	
			System.out.println(user.getPhoto());
			if((user.getPhoto()).equals("")){ //프로필 사진등록자가 아니면 
				uphoto =0;
			}else{
				uphoto =1;
			}
			
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uphoto", uphoto);//프로필 사진 등록자인지 아닌지 판별값.(미등록자:0,등록자:1)
			map.put("bbsList", bbsList);
			map.put("currentPage", page);
			map.put("itemCount", bbsCount);
			map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
			map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;


		}
		

		//알림내역
		@RequestMapping("/m/message_list.go")
		public String messageListController(
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				@RequestParam(value="page", required=false, defaultValue="1") int page,
				HttpServletRequest req, HttpServletResponse res
		){
			Map<String, Object> map = new HashMap<String, Object>();
			
			List<Message> list = messageDao.getMessageList(userId, page, ITEM_COUNT_PER_PAGE);
			int count = messageDao.getCount(userId);
			
			String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
					PAGE_COUNT_PER_PAGING);

			
		
			map.put("currentPage", page);
			map.put("paging", paging);
			
			map.put("list", list);
			map.put("count", count);
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;
		}
		
		//알림판별 (대화요청,친구요청)
		@RequestMapping("/m/message_chk.go")
		public String messageChkController(
			
				@RequestParam(value="seq", required=false, defaultValue="0") int seq,
				@RequestParam(value="type", required=false, defaultValue="") String type,
				HttpServletRequest req, HttpServletResponse res
		){
			Map<String, Object> map = new HashMap<String, Object>();
			
			try{
				if(type.equals(Push.MSG_TYPE_REQUEST_FRIEND)){//친구신청이면
					FriendRequest fr =friendRequestDao.getFriendRequest(seq);
					if(fr!=null){
						if(fr.getStatus()==1){
							map.put("result", false);
							map.put("message", "이미 수락된 요청입니다.");
						}else if(fr.getStatus()==2){
							map.put("result", false);
							map.put("message", "이미 거절된 요청입니다.");
						}else{
							map.put("result", true);
						}
					}else{
						map.put("result", false);
						map.put("message", "조회 불가능한 요청입니다.");
					}
				}
				if(type.equals(Push.MSG_TYPE_REQUEST_CHAT)){//대화신청이면
					
					ChatRequest cr = chatRequestDao.getChatRequest(seq);
					if(cr!=null){
						if(cr.getStatus()==1){
							map.put("result", false);
							map.put("message", "이미 수락된 요청입니다.");
						}else if(cr.getStatus()==2){
							map.put("result", false);
							map.put("message", "이미 거절된 요청입니다.");
						}else{
							map.put("result", true);
						}
					}else{
						map.put("result", false);
						map.put("message", "조회 불가능한 요청입니다.");
					}
				}
			}catch(Exception e){
				map.put("result", false);
				map.put("message", e.getMessage());
			}
			
			
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;
		}


		//알림내역
		@RequestMapping("/m/message_visited.go")
		public String messageListController(
				@RequestParam(value="messageSeq", required=false, defaultValue="0") int messageSeq,
				HttpServletRequest req, HttpServletResponse res
		){
			Map<String, Object> map = new HashMap<String, Object>();

			try {
				messageDao.updateMessageView(messageSeq);
				map.put("result", true);
				map.put("message", "알림이 읽음 처리되었습니다.");
			} catch (Exception e) {
				map.put("result", false);
				map.put("message", e.getMessage());
			}
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;
		}

		//알림내역
		@RequestMapping("/m/message_visited_all.go")
		public String messageListAllController(
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				HttpServletRequest req, HttpServletResponse res
		){
			Map<String, Object> map = new HashMap<String, Object>();

			try {
				messageDao.updateMessageView(userId);
				map.put("result", true);
				map.put("message", "모든 알림이 읽음 처리되었습니다.");
			} catch (Exception e) {
				map.put("result", false);
				map.put("message", e.getMessage());
			}
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;
		}
		
		//안읽은 알림
		@RequestMapping("/m/message_noread.go")
		public String messageNoreadController(
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				HttpServletRequest req, HttpServletResponse res
		){
			Map<String, Object> map = new HashMap<String, Object>();

			try {
				int count = messageDao.getnoreadCount(userId);
				map.put("result", true);
				map.put("count", count);
			} catch (Exception e) {
				map.put("result", false);
				map.put("message", e.getMessage());
			}
			
			Response.responseWrite(res, map);
			return null;
		}
		
	// 알림내역삭제
	@RequestMapping("/m/message_delete.go")
	public String messageDeleteController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "type", required = false, defaultValue = "") String type,
			@RequestParam(value = "messageSeq", required = false, defaultValue = "0") String messageSeq,
			HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		
	try{
		
			if(!messageSeq.equals("0")){
				if(type.equals("pc")){
					List<Message> list = messageDao.getMessageListAll(userId);
					for(int i =0;i<list.size();i++){
						Message ms = messageDao.getMessage(list.get(i).getMessageSeq());
						if(ms.getMessageType().equals(Push.MSG_TYPE_REQUEST_FRIEND)){//메세지 타입이 친구요청이면
							friendRequestDao.deleteFriendRequestal(Integer.parseInt(ms.getKeySeq()));//친구요청삭제
							
						}else if(ms.getMessageType().equals(Push.MSG_TYPE_REQUEST_CHAT)){//메세지타입이 채팅요청이면
							//friendRequestDao.deleteFriendRequestal(Integer.parseInt(ms.getKeySeq()));//친구요청 지우고
							chatRequestDao.deleteChatRequest(Integer.parseInt(ms.getKeySeq()));//채팅요청 지우고
						}
						messageDao.deleteMessage(list.get(i).getMessageSeq());
					}
					
					
				}else{
					String [] arr = messageSeq.split(",");
					for(int i =0;i<arr.length;i++){
						Message ms = messageDao.getMessage(Integer.parseInt(arr[i]));
						if(ms.getMessageType().equals(Push.MSG_TYPE_REQUEST_FRIEND)){//메세지 타입이 친구요청이면
							friendRequestDao.deleteFriendRequestal(Integer.parseInt(ms.getKeySeq()));//친구요청삭제
							
						}else if(ms.getMessageType().equals(Push.MSG_TYPE_REQUEST_CHAT)){//메세지타입이 채팅요청이면
							//friendRequestDao.deleteFriendRequestal(Integer.parseInt(ms.getKeySeq()));//친구요청 지우고
							chatRequestDao.deleteChatRequest(Integer.parseInt(ms.getKeySeq()));//채팅요청 지우고
						}
						messageDao.deleteMessage(Integer.parseInt(arr[i]));
					}
				}
			}
			map.put("result", true);
			map.put("message", "삭제되었습니다.");
		}catch(Exception e){
			map.put("result", false);
			map.put("message", e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, map);
		return null;
	}
		
		
		//팝업
		@RequestMapping("/m/popup_notice.go")
		public String popupNoticeController(
				@RequestParam(value = "site", required = false, defaultValue = "FACETALK") String site,
				HttpServletRequest req,
				HttpServletResponse res
		){
			Map<String, Object> map = new HashMap<String, Object>();
			
			int notiType = 10;
			
			List<Notice> list = noticeDao.getPopupTopList(site);
			//Notice notice = (Notice) noticeDao.getPopupTopList(site);
			map.put("list", list);
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;
		}
		
		
		//광고
		@RequestMapping("/m/adshow.go")
		public String adController(
				HttpServletRequest req,
				HttpServletResponse res
		){
			Map<String, Object> map = new HashMap<String, Object>();
			
			
			
			map.put("result", false);
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;
		}
		

		//무통장 입금 확인 페이지
		@RequestMapping("/m/income_verify.go")
		public String incomeVerifyController(
				HttpServletRequest req,
				HttpServletResponse res
		){
			Map<String, Object> map = new HashMap<String, Object>();
			
			
			
			
			
			Response.responseWrite(res, map);
			return null;
		}
		
		/**
		 *  SMS보내기
		 * @param loginId
		 * @param loginPw
		 * @param res
		 * @return
		 */
		@RequestMapping("/m/faceTalk_send_sms.go")
		public String aimmedSendSmsController(
				@RequestParam(value="message", required = false, defaultValue = "") String message,
				@RequestParam(value="site", required = false, defaultValue = "FACETALK") String site,
				@RequestParam(value="msgType", required = false, defaultValue = "0") int msgType,//1비번 2아이디
				@RequestParam(value="userId", required = false, defaultValue = "") String userId,
				@RequestParam(value="to", required = false, defaultValue = "") String to,
				@RequestParam(value="from", required = false, defaultValue = "0") String from,
				
				HttpServletRequest req, HttpServletResponse res
			) {
			
			Map<String, Object> map = new HashMap<String, Object>();

			try {
				boolean chk=true;
				if(msgType==1){//비밀번호 찾기임.
					boolean userCheck = userDao.correctIdphone(userId,to);
					if(userCheck==false){
						chk=false;
					}
				}
				if(msgType==2){//아이디 찾기임.
					boolean userCheck = userDao.correctPhone(to, site);
					if(userCheck==false){
						chk=false;
					}
				}
				if(chk){
					// 끌어모았어 get 요청 URL 문자열 사용 URLEncoder.encode 대한 특별한 및 안 보이는 문자 인코딩 진행하다
					String getURL = "http://cpsms.skysms.co.kr/cpsms/cp_sms_send.php";
					String msgorg="안녕하세요 ";
					site = siteDao.getSiteServiceId(site).getSiteName();
					msgorg +=site;
					String msg1 =msgorg+ "입니다. 인증번호는["+message+"]입니다.";
					//getURL += "?body="+URLEncoder.encode(message, "UTF-8") ;
					String curl =URLEncoder.encode(msg1, "EUC-KR");
					getURL += "?cpuserid="+"sword82" ;
					getURL += "&passwd="+"!vmfhaltm1" ;
					getURL += "&body="+ curl ;
					getURL += "&destination="+ to;
					getURL += "&callback="+ from;
				
					
					//String getURL = GET_URL + "?name=" + URLEncoder.encode("zhangshan", "utf-8");
					URL getUrl = new URL(getURL);
					// 끌어모았어 URL을 열 따라 연결할 URL 형식은 따라 할 URL.openConnection 함수, 
					// 다시 다른 URLConnection 하위 클래스 대상, 여기 URL 한 http 때문에 실제 복귀 것은 HttpURLConnection
					HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
					// 을 연결 하지만 실제로는 get request 반xp드시 다음 구의 connection.getInputStream () 함수 중 비로소 진정한 발 까지 서버
					connection.connect();
					// 확실한 입력 및 사용 Reader 읽기
					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 인코딩 설정 을 함께 했 다., 그렇지 않으면, 중국어
					System.out.println("=============================");
					System.out.println("Contents of get request");
					System.out.println("=============================");
					String lines;
					String msg = "";
					while ((lines = reader.readLine()) != null) {
						String str = new String(lines.getBytes());
						msg += URLDecoder.decode(str, "EUC-KR");
						System.out.println(lines);
					}
					reader.close();
					// 연결 끊기
					connection.disconnect();
					
					//JSONObject jsonObject = JSONObject.fromObject(msg);
					//String statusCode = jsonObject.getString("result_code");
					String [] arr = msg.split(" | ");
					
					if(arr[0].equals("99")){
						map.put("result", true);
						map.put("message", "인증번호가 발송되었습니다.");
					}else if (arr[0].equals("8")) {
						map.put("result", false);
						map.put("message", "잔액이 부족합니다.");
					} else if (arr[0].equals("5")) {
						map.put("message", "전달내용이 빠져있습니다.");
						map.put("result", false);
					} else if (arr[0].equals("55")) {
						map.put("message", "인증된 발신번호가 아닙니다.");
						map.put("result", false);
					} else if (arr[0].equals("6")) {
						map.put("message", "받는사람 전화번호가 빠져있습니다.");
						map.put("result", false);
					} else if (arr[0].equals("4")) {
						map.put("message", "받는 사람전화번호가 잘못되어 있습니다.");
						map.put("result", false);
					} else if (arr[0].equals("3")) {
						map.put("message", "받는 사람전화번호가 빠져있습니다.");
						map.put("result", false);
					} else if (arr[0].equals("9")) {
						map.put("message", "올바른 발신번호가 아닙니다.");
						map.put("result", false);
					}
				}else{
					map.put("message", "가입하신 전화번호와 ID가 일치하지 않습니다.");
					map.put("result", false);
				}
			} catch (Exception e) {
				map.put("message", "");
				map.put("result", false);
			}
			 
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, jsonObject);
			
			return null;
		
		}
		
		//포인트 선물하기 전 
		@RequestMapping("/m/readygiftpoint.go")
		public String readygiftController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpServletRequest req, HttpServletResponse res) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			try{
				User uu = userDao.getUsers(userId);
			
				int userpoint = uu.getPoint();
				
				map.put("userpoint", userpoint); //내포인트
				map.put("result", true);
				
				
			
			}catch(Exception e){
				map.put("result", false);
				map.put("message", e.getMessage());
			}
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, jsonObject);
			
			return null;
		
		}
		
		//포인트 선물하기
		@RequestMapping("/m/giftpoint.go")
		public String giftController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "friendId", required = false, defaultValue = "") String friendId,
		
			@RequestParam(value = "point", required = false, defaultValue = "0") int point,
			HttpServletRequest req, HttpServletResponse res) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			try{
				User uu = userDao.getUsers(userId);
				User fuser = userDao.getUsers(friendId);
				int chkmecnt = chatBlockDao.getBlockmechk(userId, friendId);
				int chkothercnt = chatBlockDao.getBlockotherchk(userId, friendId);
				int userpoint = uu.getPoint();
				if(userpoint<point){
					map.put("result", false);
					map.put("message", "유저의 포인트가 선물포인트보다 작습니다.");
				}else if(fuser.getStatus()!=1){
					map.put("result", false);
					map.put("message", "조회가 불가능한 회원입니다.");
					
				}else if(chkmecnt!=0){
					map.put("result", false);
					map.put("message", "차단해제 후 사용해주세요.");
					
				}/*else if(chkothercnt!=0){
					map.put("result", false);
					map.put("message", fuser.getUserName()+"님이 당신을 차단하였습니다.");
					
				}*/else{
					
					
					levelService.minusDb(userId, PointMoney.JOIN_SEGIFT, 0, -point,friendId+"님께 선물");
					levelService.inserDb(friendId, PointMoney.JOIN_REGIFT, point, 0,userId+"의 선물");
					Gift gf = new Gift();
					gf.setReceiveId(friendId);
					gf.setSendId(userId);
					gf.setGiftPoint(point);
					giftDao.addGIFT(gf);
					
					
					String pushmessage = uu.getUserName() +"님이 CASH "+point+"을 선물하셨습니다.";
					
					push(friendId, 0, pushmessage, Push.MSG_TYPE_SEND_POINT,userId);
				
					map.put("result", true);
					map.put("message", "선물이 완료되었습니다.");
				}
			
			}catch(Exception e){
				map.put("result", false);
				map.put("message", e.getMessage());
			}
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, jsonObject);
			
			return null;
		
		}
		
	// 환급 리스트
	@RequestMapping("/m/changeList.go")
	public String changeListController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		@RequestParam(value = "page", required = false, defaultValue = "1") int page,
	HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = false;
		String message = "";
		try {

			User uu = userDao.getUsers(userId);
			List<PointChange>list = pointChangeDao.getPointChangeListpaging(uu.getSite(),page, ITEM_COUNT_PER_PAGE);
			int count = pointChangeDao.getCount(uu.getSite());
			map.put("user", uu);
			map.put("list", list);
			map.put("count", count);
			result = true;
			
		} catch (Exception e) {
			result = false;
			message = e.getMessage();
		}
		map.put("result", result);
		map.put("message", message);
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;

	}
		
	// 포인트 환전신청
	@RequestMapping("/m/changepoint.go")
	public String changeController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		@RequestParam(value = "fmoney", required = false, defaultValue = "0") int fmoney,
		@RequestParam(value = "point", required = false, defaultValue = "0") int point,
		@RequestParam(value = "money", required = false, defaultValue = "0") int money,
		@RequestParam(value = "ownerName", required = false, defaultValue = "") String ownerName,
		@RequestParam(value = "bank", required = false, defaultValue = "") String bank,
		@RequestParam(value = "bankCount", required = false, defaultValue = "") String bankCount,
		HttpServletRequest req,HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = false;
		String message ="";
		try {
			
			User uu = userDao.getUsers(userId);
			
			if(uu.getIncome()<fmoney){
				result = false;
				message = "소지 CASH보다 큽니다.";
			}else{
				
				//fmoney 를 포인트로
				if(money==0){
					
					levelService.inserDb(userId, PointMoney.JOIN_CHANGE, fmoney, point,"CASH → \n point");
					result = true;
					message = "처리되었습니다.";
				}else{//fmoney 를 현금으로
					
					
					Expense ex = new Expense();
					ex.setUserId(userId);
					ex.setPayPoint(money);
					ex.setStatus(0);
					ex.setBank(bank);
					ex.setBankCount(bankCount);
					ex.setType("CASH");
					ex.setPoint(fmoney);
					ex.setOwnerName(ownerName);
					
					expenseDao.addExpense(ex);
					levelService.inserDb(userId, PointMoney.JOIN_CHANGE, fmoney, point,"CASH → \n 현금");
					result = true;
					message = "신청되었습니다.";
				}

			}
		} catch (Exception e) {
			result = false;
			message =e.getMessage();
		}
		map.put("result", result);
		map.put("message", message);
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;

	}
	
	//레벨안내
	@RequestMapping("/m/level_info.go")
	public String levelInfoController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			User uu = userDao.getUser(userId);
			int userlevel = uu.getUserLevel();//유저레벨
			Level ll = levelDao.getremainex(userlevel);
			int totalex = ll.getMaxEx()-ll.getMinEx();
			int remainex = ll.getMaxEx()-uu.getLevelPoint()+1;//남은경험치
			List<Point> list = pointDao.getPointList(uu.getSite());
			List benelist = new ArrayList();
			List<LevelItem>benefitlist = levelItemDao.getLevelItemList();//혜택리스트
			for(int i = 0; i< benefitlist.size();i++){
				String str = "";
				String benefit = benefitlist.get(i).getBenefit();
				
				List<LevelItem> levellist =levelItemDao.getLevelList(benefit);//그 혜택에 리스트가져오고
				str +=(i+1)+". ";
				for(int j =0; j<levellist.size();j++){
					
					if(j==(levellist.size()-1)){
						str += levellist.get(j).getLevelNum()+"레벨 ";
					}else{
						str += levellist.get(j).getLevelNum()+",";
					}
					
				}
				int period = levellist.get(i).getPeriod();
				str+=benefit +" "+period+"일 지급 " ;
				benelist.add(str);
			}
			int p =0;
			List exlist = new ArrayList<>();
			for(int i =0;i<list.size();i++){
				Point pp= list.get(i);
				if(pp.getMoney()!=0){
					p++;
					String str =p+". "+ pp.getEventName() +": "+String.format("%,d", pp.getMoney())+"C";
					if(pp.getPeriod()==4){
						str +=" ("+pp.getPeriodtxt()+" "+pp.getTimes()+"회)";  
					}else{
						str +=" ("+pp.getPeriodtxt()+")";
					}
					exlist.add(str);
				}
			
			}
			
			
			map.put("result", true);
			map.put("userlevel", userlevel);
			map.put("userPhoto", uu.getPhoto());
			map.put("userlevelpoint", uu.getLevelPoint());
			map.put("totalex", totalex);
			map.put("remainex", remainex);
			map.put("list", exlist);
			map.put("benelist", benelist);
			
		}catch(Exception e){
			map.put("result", false);
			map.put("message", e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		
		return null;
	
	}
	
	//충전리스트
	@RequestMapping("/m/charge_list.go")
	public String chargeListController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,	
		HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			
			User uu = userDao.getUsers(userId);
			List<PointCharge> list =pointChargeDao.getPointChargeList(uu.getSite());
			int userpoint = uu.getPoint();
			
			map.put("userpoint", userpoint); //내포인트
			map.put("result", true);
			map.put("list", list);
		}catch(Exception e){
			map.put("result", false);
			map.put("message", e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		
		return null;
	
	}

	 //f머니 전환 내역 
	@RequestMapping("/m/my_changeList.go")
	public String MyChangeListController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,	
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,	
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			
			List<PointMoney>list = pointmoneyDao.getVchangeList(userId, page, ITEM_COUNT_PER_PAGE);
			int count = pointmoneyDao.getVChangeCount(userId);
			
			map.put("count", count);
			map.put("currentPage", page);
			map.put("list", list);
			map.put("result", true);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	 //구매내역 
	@RequestMapping("/m/my_paylist.go")
	public String MypayListController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,	
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,	
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			
			List<PointMoney>list = pointmoneyDao.getVmyList(userId,page,ITEM_COUNT_PER_PAGE);
			int count = pointmoneyDao.getVmyCount(userId);
			
			map.put("count", count);
			map.put("currentPage", page);
			map.put("list", list);
			map.put("result", true);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	//결제
	@RequestMapping("/order_kspay.go")
	public String orderKspayController(
			//@RequestParam(value="sndPaymethod", required=false, defaultValue="") String sndPaymethod,
			//@RequestParam(value="sndOrdernumber", required=false, defaultValue="") String sndOrdernumber,
			@RequestParam(value="sndGoodname", required=false, defaultValue="") String sndGoodname,
			@RequestParam(value="sndAmount", required=false, defaultValue="") String sndAmount,
			@RequestParam(value="sndOrdername", required=false, defaultValue="") String sndOrdername,
			@RequestParam(value="sndEmail", required=false, defaultValue="") String sndEmail,
			@RequestParam(value="sndMobile", required=false, defaultValue="") String sndMobile,
			@RequestParam(value="comment", required=false, defaultValue="") String comment,
			@RequestParam(value="userpoint", required=false, defaultValue="") int userpoint,
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat fomat = new SimpleDateFormat("yyyyMMddhhmmss");
		String random = fomat.format(cal.getTime());
		
		String sndOrdernumber = sndOrdername+"_"+random;
		System.out.println(sndOrdernumber);
		
		model.addAttribute("sndStoreid", "2062100214");
		model.addAttribute("userpoint", userpoint);
		model.addAttribute("sndOrdernumber", sndOrdernumber);
		model.addAttribute("sndGoodname", sndGoodname);
		model.addAttribute("sndAmount", sndAmount);
		model.addAttribute("sndOrdername", sndOrdername);
		model.addAttribute("sndEmail", sndEmail);
		model.addAttribute("sndMobile", sndMobile);
		model.addAttribute("comment", "충전");
	    return "/kspay_wh_order";
	}
	 //무료충전소
	@RequestMapping("/m/free_point.go")
	public String FreePointController(
			@RequestParam(value = "partner", required = false, defaultValue = "") String partner,
			@RequestParam(value = "cust_id", required = false, defaultValue = "") String cust_id,
			@RequestParam(value = "ad_no", required = false, defaultValue = "") String ad_no,
			@RequestParam(value = "point", required = false, defaultValue = "") String point,
			@RequestParam(value = "ad_title", required = false, defaultValue = "") String ad_title,
			@RequestParam(value = "date", required = false, defaultValue = "") String date,
			@RequestParam(value = "seq_id", required = false, defaultValue = "") String seq_id,
			@RequestParam(value = "vaild_key", required = false, defaultValue = "") String vaild_key,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			User uu= userDao.getUsers(cust_id);
			int chk = pointmoneyDao.getUserFreeChk(Integer.parseInt(seq_id));
			if(chk==0){//중복지급아님
				PointMoney pm = new PointMoney();
				pm.setInPoint(Integer.parseInt(point));
				pm.setComment(ad_title+":"+point);
				pm.setUserId(cust_id);
				pm.setRemainPoint(uu.getPoint()+Integer.parseInt(point));
				pm.setRemainMoney(uu.getIncome());
				pm.setType(PointMoney.JOIN_FREE);
				pm.setSeqId(Integer.parseInt(seq_id));
				pointmoneyDao.addPointMoney(pm);
				userDao.updateUser(cust_id, uu.getIncome(), pm.getRemainPoint());
				map.put("ResultMsg", "충전이 완료되었습니다.");
				map.put("Result", true);
				map.put("ResultCode", 1);
			}else{
				map.put("ResultMsg", "이미 받으셨습니다.");
				map.put("Result", false);
				map.put("ResultCode", 3);
			}
			
			
			
			
		} catch (Exception e) {
			map.put("ResultMsg", e.getMessage());
			map.put("ResultCode", 2);
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	
	/* 버전확인 */
	@RequestMapping("/m/version.go")
	public String VersionController(
			@RequestParam(value = "osType", required = false, defaultValue = "") String osType,	
			@RequestParam(value = "site", required = false, defaultValue = "FACETALK") String site,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			Notice nn =new Notice();
			if(osType.equals("ANDROID")){
				nn = noticeDao.getUpdateV(Notice.OS_ANDROID,site);
			}else{
				nn = noticeDao.getUpdateV(Notice.OS_IOS,site);
			}
			
			map.put("version", nn.getTitle());
			map.put("url", nn.getLinkUrl());
			map.put("result", true);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	//푸시테스트
	@RequestMapping("/m/push_test.go")
	public String PushtestController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			User uu = userDao.getUsers(userId);
			Push push = new Push();
			push.setBadge(1);
			push.setOs(uu.getOsType());
			push.setPushKey(uu.getPushkey());
			push.setMsgType(Push.MSG_TYPE_GUESTBOOK);
			push.setUserid(userId);
			push.setStatus(0);
			push.setServiceId(uu.getSite());
			push.setPushType(1);							
			push.setMsg("테스트입니다");
			push.setMsgKey(String.valueOf(0));
			pushDao.addPush(push);
			
			map.put("result", true);
			map.put("message", "전송되었습니다.");
		}catch(Exception e){
			map.put("result", false);
			map.put("message", e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		
		return null;
	
	}
	//완료
	@RequestMapping("/m/result.go")
	public String resultController(
		
		HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("ddd");
		
		return "/m/result";
	
	}
	
	//로그아웃
	@RequestMapping("/m/logout.go")
	public String logoutController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			userDao.updateoutlogin(userId);
			map.put("result", true);
			map.put("message", "로그아웃 되었습니다.");
		}catch(Exception e){
			map.put("result", false);
			map.put("message", e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		
		return null;
	
	}
	
	
	// 채팅방 목록
	@RequestMapping("/m/chat_list.go")
	public String chatListController(
		@RequestParam(value = "page", required = false, defaultValue = "1") int page ,
		@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		@RequestParam(value = "agentId", required = false, defaultValue = "") String agentId,
		@RequestParam(value = "age", required = false, defaultValue = "20") int age,
		@RequestParam(value = "gender", required = false, defaultValue = "1") int gender,
		@RequestParam(value = "area", required = false, defaultValue = "") String area,
		@RequestParam(value = "distance", required = false, defaultValue = "") int distance,
		@RequestParam(value = "latitude", required = false, defaultValue = "") Double latitude,
		@RequestParam(value = "longitude", required = false, defaultValue = "") Double longitude,
		@RequestParam(value = "admin", required = false, defaultValue = "") String admin,
		HttpServletRequest req, HttpServletResponse res,Model model,HttpSession session ) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List <ChatRoom> list =new ArrayList();
		int count =0;
		String str=null;
		try{
			
			if(admin.equals("1")){
				// 페이징
				User uu =userDao.getUsers((String)session.getAttribute("USER_ID"));
				int userType=(int)session.getAttribute("USER_TYPE");
				list= chatRoomDao.getChatRoomAdminList(uu.getSite(),userType,page, ITEM_COUNT_PER_PAGE, keyword, age, gender, area, distance, latitude, longitude);
				count=chatRoomDao.getChatRoomAdminCount(uu.getSite(),userType, keyword, age, gender, area, distance, latitude, longitude);
				for(int i=0;i<list.size();i++){
					
					ChatRoom cr = list.get(i);
					List<User>userlist = userDao.getchatuserList(cr.getChatRoomSeq());
					cr.setMemberList(userlist);
					list.set(i, cr);
				}
				 
				String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
						PAGE_COUNT_PER_PAGING);

				model.addAttribute("list", list);
				model.addAttribute("paging", paging);
				model.addAttribute("currentPage", page);
				str="/admin/chat/chat_list";
			}else if(admin.equals("2")){
				String id="";
				if(userId.equals("")){
					id=agentId;
				}else{
					id=userId;
				}
				User uu = userDao.getUsers(id);
				if(uu!=null){
					if(latitude==0.0||longitude==0.0||longitude==0||latitude==0){
						latitude=uu.getLatitude();
						longitude=uu.getLongitude();
					}
				}
				
				list= chatRoomDao.getChatRoomNormalList(uu.getSite(),page, ITEM_COUNT_PER_PAGE, keyword, age, gender, area, distance, latitude, longitude);
			
				count= chatRoomDao.getChatRoomNormalCount(uu.getSite(),keyword, age, gender, area, distance, latitude, longitude);
	
				String  paging = Paging.getPaging2(page, count, ITEM_COUNT_PER_PAGE, 5);
				
				 
				for (int i=0; i<list.size(); i++) {
					User user = userDao.getUser(userId);
					ChatRoom room = (ChatRoom)list.get(i);
					ChatMember member = chatMemberDao.getChatMember(room.getChatRoomSeq(), user.getAgentId());
					room.setOtherAgentUser(member.getUserId());
					list.set(i, room);
				}
				
				map.put("result", true);
				map.put("list",list);
				map.put("count",count);
				map.put("paging",paging);
				
			}else{
				
				User uu = userDao.getUsers(userId);
				if(uu!=null){
					if(latitude==0.0||longitude==0.0||longitude==0||latitude==0){
						latitude=uu.getLatitude();
						longitude=uu.getLongitude();
					}
				}
				
				list= chatRoomDao.getChatRoomNormalList(uu.getSite(),page, ITEM_COUNT_PER_PAGE, keyword, age, gender, area, distance, latitude, longitude);
			
				count= chatRoomDao.getChatRoomNormalCount(uu.getSite(),keyword, age, gender, area, distance, latitude, longitude);
	
				String  paging = Paging.getPaging2(page, count, ITEM_COUNT_PER_PAGE, 5);
				
				for (int i=0; i<list.size(); i++) {
					User user = userDao.getUser(userId);
					ChatRoom room = (ChatRoom)list.get(i);
					ChatMember member = chatMemberDao.getChatMember(room.getChatRoomSeq(), user.getAgentId());
					room.setOtherAgentUser(member.getUserId());
				}
				
				map.put("result", true);
				map.put("list",list);
				map.put("count",count);
				map.put("paging",paging);
				
			}
		}catch(Exception e){
			map.put("result", false);
			map.put("message", e.getMessage());
		}
		
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		
		return str;
	}
	
	//아이템 구매리스트(채팅용)
	@RequestMapping("/m/buy_chatitem_list.go")
	public String buychatItemListController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			
			List<UserItem>list = useritemDao.getMyuseChatItemList(userId);
			map.put("list", list);
			
			map.put("result",true);
			
		}catch(Exception e){
			map.put("result",false);
			map.put("message",e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		
		return null;
	}
	
	// 채팅방 만들기
	@RequestMapping("/m/chat_add.go")
	public String chatAddController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		@RequestParam(value = "title", required = false, defaultValue = "") String title,
		@RequestParam(value = "area", required = false, defaultValue = "") String area,
		@RequestParam(value = "itemSeq", required = false, defaultValue = "0") int itemSeq,
		@RequestParam(value = "memberLimit", required = false, defaultValue = "2") int memberLimit,
		@RequestParam(value = "useMegaphone", required = false, defaultValue = "0") int useMegaphone,
		@RequestParam(value = "useFreeTicket", required = false, defaultValue = "0") int useFreeTicket,
		
		@RequestParam(value = "isAnonym", required = false, defaultValue = "0") int isAnonym,
	
		HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			
			// 아이디 존재여부 체크
			
			User user = userDao.getUsers(userId);
			String agentId = user.getAgentId();
			boolean isAgent = ((agentId == null || agentId.equals("")) == false);
			int userAge = user.getUserAge();
			String age = "";
			int type =0;
			if (userAge < 20) {
				age = "10대";
			} else if (userAge >= 20 && userAge < 30) {
				age = "20대";
			} else if (userAge >= 30 && userAge < 40) {
				age = "30대";
			} else if (userAge >= 40 && userAge < 50) {
				age = "40대";
			} else if (userAge >= 50 && userAge < 60) {
				age = "50대";
			} else if (userAge >= 60 && userAge < 70) {
				age = "60대";
			} else if (userAge >= 70) {
				age = "70대 이상";
			}
			
			
			
			// useFreeTicket(자유이용권)을 사용할 경우 확성기 사용 불가
			// 자유이용권을 사용할 경우, 아이템에서 차감 및 사용로그 기록
			// 자유이용권으로 방을 5개 까지 만들 수 있음
			// 자유이용권은 1:1채팅, 익명채팅, 이성채팅을 무료로 사용
			// 자유이용권은 구매 후 1주일간 사용가능
			// 자유이용권이 아닌 경우 포인트 차감
			// - 확성기 1000p
			// - 1:1채팅 1000p
			// - 익명채팅 1000p
			// 확성기를 사용할 경우, 아이템에서 차감 및 사용로그 기록. 오늘 날짜를 use_speaker_date에 기록
			
			String useMegaphoneDate = "";
			boolean chk = false;
			String kind ="";
			String msg ="";
			if(isAnonym ==1){ //익명채팅
				kind = PointMoney.JOIN_NONAME_CHAT;
				msg = "익명채팅";
				type = 3;
			}else if(memberLimit>2){//1:N
				kind=PointMoney.JOIN_ANYONE_CHAT;
				msg = "채팅";
				type = 1;
			}else{ 
				kind = PointMoney.JOIN_ONE_CHAT;
				msg = "1:1채팅";
				type = 2;
			}
			
		
			
			// 채팅관리자 일 경우, 아이템 및 포인트 적용 안함
			if (isAgent) {
				if (useMegaphone == 1) { //확성기 사용.
					useMegaphoneDate = T.getNowFomat();
				}
				chk =true;
			} else {
				Point pp = pointDao.getSitePoint(kind,user.getSite());//세팅되있는 포인트
				if(pp!=null){
					if(user.getPoint()<-pp.getPoint()){
						chk=false;
						map.put("result", false);
						map.put("message", "대화방생성시 필요한 포인트가 부족합니다.");
					}else{
						chk =true;
					}
				}else{
					chk =true;
				}
				if(chk){			
					if (useMegaphone == 1) { //확성기 사용.
						
						useMegaphoneDate = T.getNowFomat();
								
						UserItem ui = useritemDao.getUserItem(itemSeq);
						int useCount = ui.getUseCount()+1;
						useritemDao.updateMyItemCount(useCount,itemSeq);//확성기 사용 카운트 증가
						ui = useritemDao.getUserItem(itemSeq);
						if(ui.getUseCount()==ui.getLimitCount()){
								useritemDao.updateUserItem(itemSeq, 0);
						}//////카운트
						PointMoney pm = new PointMoney();
								
						pm.setOutMoney(0);
						pm.setOutPoint(0);
						pm.setRemainMoney(user.getIncome());
						pm.setRemainPoint(user.getPoint());
						pm.setUserId(userId);
						pm.setType(PointMoney.JOIN_ITEM_USE);
						pm.setComment(Item.NAME_MEGAPHONE);
						pointmoneyDao.addPointMoney(pm); //사용로그 기록 
								
					}/////////확성기사용
					if(pp!=null){
							levelService.inserDb(userId,kind, pp.getMoney(), pp.getPoint(), msg);
							chk =true;
					}
				}//////////////포인트 부족하지않음
				
				/*if(pp!=null){
					if(user.getPoint()<-pp.getPoint()){
						chk=false;
						map.put("result", false);
						map.put("message", "대화방생성시 필요한 포인트가 부족합니다.");
					}else{
							
							if (useMegaphone == 1) { //확성기 사용.
						
								useMegaphoneDate = T.getNowFomat();
								
								UserItem ui = useritemDao.getUserItem(itemSeq);
								int useCount = ui.getUseCount()+1;
								useritemDao.updateMyItemCount(useCount,itemSeq);//확성기 사용 카운트 증가
								ui = useritemDao.getUserItem(itemSeq);
								if(ui.getUseCount()==ui.getLimitCount()){
									useritemDao.updateUserItem(itemSeq, 0);
								}//////카운트
								PointMoney pm = new PointMoney();
								
								pm.setOutMoney(0);
								pm.setOutPoint(0);
								pm.setRemainMoney(user.getIncome());
								pm.setRemainPoint(user.getPoint());
								pm.setUserId(userId);
								pm.setType(PointMoney.JOIN_ITEM_USE);
								pm.setComment(Item.NAME_MEGAPHONE);
								pointmoneyDao.addPointMoney(pm); //사용로그 기록 
								
							}/////////확성기사용
							levelService.inserDb(userId,kind, pp.getMoney(), pp.getPoint(), msg);
							chk =true;
					}//////////////포인트 부족하지않음
				}//////////if(pp)
*/			}
				if(memberLimit==0){
					map.put("result", false);
					map.put("message", "제한 인원을 다시 설정해주세요.");
					chk=false;
				}
			
				if(chk==true){
					ChatRoom room = new ChatRoom();
					room.setChatRoomType(type);
					room.setOwnerId(userId);
					room.setTitle(title);
					room.setAge(age);
					room.setArea(area);
					room.setMemberLimit(memberLimit);
				
					room.setIsAnonym(isAnonym);
				
					room.setUseMegaphoneDate(useMegaphoneDate);
					room.setUseFreeTicket(useFreeTicket);
					
					int seq = chatRoomDao.addChatRoom(room);
					ChatMember cm = new ChatMember();
					cm.setChatRoomSeq(seq);
					cm.setUserId(userId);
					cm.setEncUserName("익명0");
					cm.setOrgUserId(userId);
					chatMemberDao.addChatMember(cm);
					room = chatRoomDao.getChatRoom(seq);
					room.setResult("true");
					room.setMemberInfo(userId);
					map.put("room", room);
					map.put("result", true);
					map.put("message", "대화방이 생성되었습니다.");
				}//if
		}catch(Exception e){
			map.put("result", false);
			map.put("message", e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		
		return null;
	}

	// 채팅방 입장
	@RequestMapping("/m/chat_join.go")
	public String chatJoinController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId, 
			@RequestParam(value = "chatRoomSeq", required = false, defaultValue = "0") int chatRoomSeq,
			HttpServletRequest req,HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ChatRoom cr = chatRoomDao.getChatRoom(chatRoomSeq);
			boolean result =false;
			String message ="";
			//cr.getIsAnonym() 익명
			//cr.getIsOneone() 1:1
			//cr.getOnlyOppositeSex() 이성만
			
			//일단 정원마감 확인 해야함.
			//내가 여기 있는지 없는지 판별 : 0이면 내가 참여하지 않은 대화방
			
			int cnt = chatMemberDao.getChatMemberCnt(chatRoomSeq,userId);
			int type =3;
			
			if(cr!=null){
				
			
				if(cnt==0){
					if(cr.getMemberLimit()>cr.getMemberCount()){ ///정원이 마감 안됬으면,
						//이미 여기서 1:1 인원 파악 판별을 끝낸 상태.
						//그러므로 이성방인지 판별
						//방장에의해 차단된 사용자인지 판별
						int chk = chatOutDao.getChatOut(chatRoomSeq,userId);
					/*	int chk = chatBlockDao.getBlockmechk(cr.getOwnerId(), userId);
						int chk2 = chatBlockDao.getBlockotherchk(cr.getOwnerId(), userId);*/
						if(chk==0){
							
								if(cr.getMemberLimit()==2){ //두명방(이성만)
									User user = userDao.getUsers(userId);
									if(user.getGender()!=cr.getGender()){ //근데 입장하려는 사람이랑 성별이 같지 않으면
										result =true;
										type=1;
										message = "입장하였습니다.";
										
									}else{//같으면
										message = "이성만 입장 가능합니다.";
									}
								}else{ 
									result =true;
									 type=1;
									message = "입장하였습니다.";
								}
								
							
						 }else{
							 message = "방장에 의해 차단 되었습니다.";
							
						 }
					}else{
						
						message = "입장가능 인원을 초과하였습니다.";
					}
				}else{
					result =true;
					message = "이미 참여 중인 채팅방입니다.";
					type=0;
				}
				
				
				map.put("result", result);
				map.put("message", message);
				map.put("type", type);
				
			}else{
				map.put("result", false);
				map.put("message", "조회 할 수 없는 방입니다.");
			}
			
		} catch (Exception e) {
			map.put("result", false);
			map.put("message", e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}
	 //채팅방 정보
	@RequestMapping("/m/chat_room_info.go")
	public String chatRoomInfoController(
	@RequestParam(value = "chatRoomSeq", required = false, defaultValue = "0") int chatRoomSeq,
	//@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
	HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			ChatRoom cr = chatRoomDao.getChatRoom(chatRoomSeq);
			//ChatMember cm = chatMemberDao.getChatMemberPush(chatRoomSeq, userId);
			//map.put("notification", cm.getNotification());
			map.put("result", true);
			map.put("date", cr);
			
		}catch(Exception e){
			map.put("result", false);
			map.put("message", e.getMessage());
		}
		
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}
	 //채팅방 확성기적용
	@RequestMapping("/m/chat_room_mega.go")
	public String chatRoomMegaController(
	@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
	@RequestParam(value = "itemSeq", required = false, defaultValue = "0") int itemSeq,
	@RequestParam(value = "chatRoomSeq", required = false, defaultValue = "0") int chatRoomSeq,
	HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			ChatRoom cr = chatRoomDao.getChatRoom(chatRoomSeq);
			String useMegaphoneDate = "";
			if(cr.getOwnerId().equals(userId)){
				
				if(cr.getUseFreeTicket()==1){//자유이용권 쓴방이면
					map.put("result", false);
					map.put("message", "자유이용권과 중복사용 불가합니다.");
				}else{
					useMegaphoneDate = T.getNowFomat();
					chatRoomDao.updateChatRoom(chatRoomSeq,useMegaphoneDate);
					UserItem ui = useritemDao.getUserItem(itemSeq);
					int useCount = ui.getUseCount()+1;
					useritemDao.updateMyItemCount(useCount,itemSeq);//확성기 사용 카운트 증가
					ui = useritemDao.getUserItem(itemSeq);
					if(ui.getUseCount()==ui.getLimitCount()){
						useritemDao.updateUserItem(itemSeq, 0);
					}
					map.put("result", true);
					map.put("message", "적용완료 되었습니다.");
					
				}
			}else{
				map.put("result", false);
				map.put("message", "방장만 사용할 수 있습니다.");
			}
			
			
			
		}catch(Exception e){
			map.put("result", false);
			map.put("message", e.getMessage());
		}
		
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}
	
	 //채팅방 참여인원리스트
	@RequestMapping("/m/chat_memberList.go")
	public String chatmemberListController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		@RequestParam(value = "chatRoomSeq", required = false, defaultValue = "0") int chatRoomSeq,
		//@RequestParam(value = "page", required = false, defaultValue = "1") int page,
		HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try{
			ChatMember cm = chatMemberDao.getChatMemberPush(chatRoomSeq, userId);
			List<User>list = userDao.getchatuserList(chatRoomSeq);
			int count = userDao.getchatuserCount(chatRoomSeq);
			
			map.put("result", true);
			map.put("list", list);
			map.put("status", cm.getNotification());
			/*map.put("currentPage", page);
			map.put("itemCount", count);
			map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
			map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);*/
			
		}catch(Exception e){
			map.put("result", false);
			map.put("message", e.getMessage());
		}
		
		
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		 
		return null;
	}
	
	// 채팅방 신고하기
		@RequestMapping("/m/chat_fnc.go")
		public String chatFncController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId, 
			@RequestParam(value = "blockId", required = false, defaultValue = "") String blockId, 
			@RequestParam(value = "fncReason", required = false, defaultValue = "") String fncReason,
			@RequestParam(value = "fncFiles", required = false, defaultValue = "") String fncFiles, 
			@RequestParam(value = "contents", required = false, defaultValue = "") String contents, 
			@RequestParam(value = "chatRoomSeq", required = false, defaultValue = "0") int chatRoomSeq,
				HttpServletRequest req,HttpServletResponse res) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			try{
				int chk = userFncDao.getUserFncCount(userId,blockId,chatRoomSeq);
				if(chk==0){
					UserFnc uf = new UserFnc();
					uf.setBlockId(blockId);
					uf.setUserId(userId);
					uf.setChatRoomSeq(chatRoomSeq);
					uf.setFncReason(fncReason);
					uf.setFncFiles(fncFiles);
					uf.setStatus(0);
					uf.setContents(contents);
					userFncDao.addUserFnc(uf);
					
					map.put("result",true);
					map.put("message","신고되었습니다.");
				}else{
					map.put("result",false);
					map.put("message","이미 신고하셨습니다.");
					
				}
			}catch(Exception e){
				map.put("result",false);
				map.put("message",e.getMessage());
			}
			
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, jsonObject);
			
			return null;
		}
		//채팅방 푸시설정
		@RequestMapping("/m/chat_push.go")
		public String chatPushController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "status", required = false, defaultValue = "0") int status,
			@RequestParam(value = "chatRoomSeq", required = false, defaultValue = "0") int chatRoomSeq,
			HttpServletRequest req, HttpServletResponse res) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			try{
				
				chatMemberDao.updateChatMemberPush(chatRoomSeq,status,userId);
				map.put("result",true);
				
			}catch(Exception e){
				map.put("result",false);
				map.put("message",e.getMessage());
			}
			
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, jsonObject);
			
			return null;
	}
		

		
	// 나의 채팅 목록
	@RequestMapping("/m/my_chat_list.go")
	public String mychatListController(
			@RequestParam(value = "chatRoomSeq", required = false, defaultValue = "") String chatRoomSeq,
			
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {

			List<ChatRoom>list = chatRoomDao.getmyChatRoomList(chatRoomSeq);
			
			for(ChatRoom cr:list){
				if(cr.getMemberCount()<3){
					List<User>memberList = userDao.getchatuserList(cr.getChatRoomSeq());
					cr.setMemberList(memberList);
				}
			}
			map.put("result", true);
			map.put("list", list);
		} catch (Exception e) {
			map.put("result", false);
			map.put("message", e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

/*	// 강퇴하기
	@RequestMapping("/m/chat_drop.go")
	public String chatFncController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		@RequestParam(value = "blockId", required = false, defaultValue = "") String blockId,
		@RequestParam(value = "chatRoomSeq", required = false, defaultValue = "0") int chatRoomSeq,
		HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			ChatRoom cr = chatRoomDao.getChatRoom(chatRoomSeq);
			if(cr.getOwnerId().equals(userId)){
				chatMemberDao.outChatMember(chatRoomSeq,blockId); //강퇴
				
				//강퇴시 방장에 차단친구로
				ChatBlock cb = new ChatBlock();
				cb.setCbUserId(userId);
				cb.setBlUserId(blockId);
				chatBlockDao.addChatBlock(cb);
				
				map.put("result",true);
				map.put("message","강퇴 되었습니다.");
			}else{
				map.put("result",false);
				map.put("message","방장만 강퇴할 수 있습니다 .");
			}
		}catch(Exception e){
			map.put("result",false);
			map.put("message",e.getMessage());
		}
		
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		
		
		return null;
	}

	    //채팅방 나가기
		@RequestMapping("/m/chat_out.go")
		public String chatOutController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		@RequestParam(value = "chatRoomSeq", required = false, defaultValue = "0") int chatRoomSeq,
		HttpServletRequest req, HttpServletResponse res) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			try{
				ChatRoom cr = chatRoomDao.getChatRoom(chatRoomSeq);//방정보를 가져와서
				if(cr.getOwnerId().equals(userId)){//내가 방장
					if(cr.getChatRoomType()==1){ //1:N 채팅이면
						chatMemberDao.outChatMember(chatRoomSeq,userId);//나가기 처리한 후
						ChatMember cm = chatMemberDao.getChatMemberTop(chatRoomSeq);//제일 오래된 사람아이디로
						chatRoomDao.updateChatRoom(cm.getUserId(),chatRoomSeq);//방장 업데이트
					}else{
						chatMemberDao.deleteRoomMember(chatRoomSeq);//방장 나갓으니 다 내보내고
						chatRoomDao.deleteChatRoom(chatRoomSeq);//방지움.
					}
				}else{//나 방장 아님.
					chatMemberDao.outChatMember(chatRoomSeq,userId);//나가기 처리
				}
				
				map.put("result",true);
				map.put("message","채팅방을 나오셨습니다.");
				
			}catch(Exception e){
				map.put("result",false);
				map.put("message",e.getMessage());
			}
			
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, jsonObject);
			
			return null;
		}*/
	
	
	//아이템 구매리스트
	@RequestMapping("/m/buy_item_list.go")
	public String buyItemListController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			User user = userDao.getUser(userId);
			List<Item>list = itemDao.getItemList(user.getSite());
			List<UserItem>mylist = useritemDao.getMyuseItemList(userId);
			map.put("list", list);
			map.put("mylist", mylist);
			map.put("result",true);
			
		}catch(Exception e){
			map.put("result",false);
			map.put("message",e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		
		return null;
	}
	
	
	//확성기 자유이용권 아이템 구매 
	@RequestMapping("/m/buy_item.go")
	public String buyItemController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
		
		HttpServletRequest req, HttpServletResponse res) {
		
		Item it = itemDao.getItem(seq);
		UserItem ui = useritemDao.getMyItem(userId,it.getItemCode());
		
		
		String today = "";
		String finish = "";
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			User user =userDao.getUsers(userId);
			
			if(user.getPoint()>-it.getPoint()){
				String comment =levelService.item(userId, it.getItemName(), it.getItemCode(), it.getPeriod(), it.getLimitCount());
				
				
				levelService.minusDb(userId, PointMoney.JOIN_ITEM, 0, it.getPoint(),comment);
				map.put("result", true);
				map.put("message", "구매완료되었습니다.");
			}else{
				map.put("result", false);
				map.put("message", "포인트가 부족합니다.");
			}
			
			
			
		}catch(Exception e){
			map.put("result", false);
			map.put("message", e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		
		return null;
	
	}
	
	//내아이템 구매내역리스트
	@RequestMapping("/m/buy_myitem_list.go")
	public String buyMyItemListController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,	
		HttpServletRequest req, HttpServletResponse res) {
			
		Map<String, Object> map = new HashMap<String, Object>();
			try{
				List<UserItem>list = useritemDao.getMyuseItemList(userId);
				
				map.put("list", list);
				map.put("result",true);
				
			}catch(Exception e){
				map.put("result",false);
				map.put("message",e.getMessage());
			}
			
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, jsonObject);
			
			return null;
	}
	
	// 차단하기
	@RequestMapping("/m/block_user.go")
	public String blockController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "blockId", required = false, defaultValue = "") String blockId,
			HttpServletRequest req,HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Friend fr = friendDao.getFr(userId, blockId);
			if(!userId.equals(blockId)){
				if(fr==null){
					int chk = chatBlockDao.getBlockmechk(userId, blockId);
					if(chk==0){
						ChatBlock cb = new ChatBlock();
						cb.setCbUserId(userId);
						cb.setBlUserId(blockId);
						chatBlockDao.addChatBlock(cb);
						map.put("result", true);
						map.put("message", "차단되었습니다.");
					}else{
						map.put("result", false);
						map.put("message", "이미 차단하였습니다.");
					}
				
				}else{
					map.put("result", false);
					map.put("message", "친구는 차단할 수 없습니다.친구를 삭제 후 차단해주세요.");
				}
				
				
			}else{
				map.put("result", false);
				map.put("message", "본인은 차단할 수 없습니다.");
			}
			

		} catch (Exception e) {
			map.put("result", false);
			map.put("message", e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}
	
	//포인트 세팅 값 
	@RequestMapping("/m/point_value.go")
	public String pointValueController(
		@RequestParam(value = "codeName", required = false, defaultValue = "") String codeName,	
		@RequestParam(value = "site", required = false, defaultValue = "FACETALK") String site,	
		HttpServletRequest req, HttpServletResponse res) {
			
		Map<String, Object> map = new HashMap<String, Object>();
			try{
				Point pp =pointDao.getSitePoint(codeName,site);
				int point =0;
				if(pp.getPointCode().equals(PointMoney.JOIN_PHOTO)){
				
					point = Math.abs(pp.getPoint());
					
				}else{
					point = Math.abs(pp.getPoint());
				}
				
				
				map.put("point",point);
				map.put("result",true);
				
			}catch(Exception e){
				map.put("result",false);
				map.put("message",e.getMessage());
			}
			
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, jsonObject);
			
			return null;
	}

	// 파일삭제
	@RequestMapping("/m/file_delete.go")
	public String file_deleteController(
			@RequestParam(value = "file", required = false, defaultValue = "") String file,
			Model model, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		filedelete(file);
		
		map.put("result", true);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		
		return null;
	}
	
	
		//파일삭제
		public void filedelete(String files){
			String [] arr = files.split(",");
			for(int i=0; i<arr.length ; i++){
				String fileName = arr[i];
				//본파일
				FILE_LOCAL_PATH = FILE_ROOT + fileName ;
				File file = new File(FILE_LOCAL_PATH);
				file.delete();
				//썸네일
				String  thumpath = fileName.substring(0,fileName.lastIndexOf("/"))+"/thumb"; //경로
				String tumbName = fileName.substring(fileName.lastIndexOf("/")); //사진이름
				String thumb = FILE_ROOT +thumpath + tumbName;
				File file2 = new File(thumb);
				file2.delete();
				
			}
			
		}
		//푸시키알림전송
		public void push(String userId,int seq,String pushmsg,String type,String writeId){
			//boolean res = false;
			
			User uu = userDao.getUsers(userId);
			
			if (uu.getUsePushservice()==1&&!uu.getPushkey().equals("")&&uu.getStatus()==1) {
			
				Push push = new Push();
				push.setBadge(1);
				push.setOs(uu.getOsType());
				push.setPushKey(uu.getPushkey());
				push.setMsgType(type);
				push.setUserid(userId);
				push.setStatus(0);
				push.setServiceId(uu.getSite());
				push.setPushType(1);							
				push.setMsg(pushmsg);
				push.setMsgKey(String.valueOf(seq));
				pushDao.addPush(push);
			}
			
			Message msg = new Message();
			msg.setUserId(userId);
			msg.setMessageType(type);
			msg.setKeySeq(String.valueOf(seq));
			msg.setContents(pushmsg);
			msg.setWriteId(writeId);
			messageDao.addMessage(msg);
			
			//return res;
		}
		
	// 푸시키알림전송
	public void pushdelete(String userId, int seq, String pushmsg, String type, String writeId) {
		// boolean res = false;

		User uu = userDao.getUsers(userId);

		if (uu.getUsePushservice() == 1 && !uu.getPushkey().equals("") && uu.getStatus() == 1) {

			Push push = new Push();
			push.setBadge(1);
			push.setOs(uu.getOsType());
			push.setPushKey(uu.getPushkey());
			push.setMsgType(type);
			push.setUserid(userId);
			push.setStatus(0);
			push.setServiceId(uu.getSite());
			push.setPushType(1);
			push.setMsg(pushmsg);
			push.setMsgKey(String.valueOf(seq));
			pushDao.addPush(push);
		}


		// return res;
	}



	// 1:1 문의 내용
	@RequestMapping("/m/qna_view.go")
	public String qnaReadController(
			@RequestParam(value = "qnaSeq", required = false, defaultValue = "0") int qnaSeq,
			HttpSession session, HttpServletResponse res, Model model)
		{
		
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		
		Qna qna = qnaDao.getQna(qnaSeq);

		map.put("qna", qna);
		map.put("result", true);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}	
	

	// 1:1 문의 등록/수정
	@RequestMapping("/m/qna_edit_do.go")
	public String adEditDoController(
			@RequestParam(value = "qnaSeq", required = false, defaultValue = "0") int qnaSeq,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "contents", required = false, defaultValue = "") String contents,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			HttpSession session, HttpServletResponse res, Model model)
		{
		
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		
		
		if (qnaSeq == 0) {

			Qna qna = new Qna();
			qna.setUserId(userId);
			qna.setTitle(title);
			qna.setContentsText(contents);
			qna.setContentsHtml(contents);
			User user = userDao.getUsers(userId);
			qna.setSite(user.getSite());
			qnaDao.addQna(qna);
			
			result = true;
			message = "등록되었습니다.";
			

		} else {

			Qna qna = qnaDao.getQna(qnaSeq);
			qna.setTitle(title);
			qna.setContentsText(contents);
			qna.setContentsHtml(contents);
			qnaDao.updateCommentQna(qna);
			
			result = true;
			message = "수정되었습니다.";
			
		}

		 map.put("qnaSeq", qnaSeq);
		 map.put("result", result);
		 map.put("message", message);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}	
	
	// 1:1 문의 삭제
	@RequestMapping("/m/qna_delete.go")
	public String qnadeleteController(
			@RequestParam(value = "qnaSeq", required = false, defaultValue = "0") int qnaSeq,
			HttpSession session,HttpServletResponse res, Model model) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		 try{
			 qnaDao.deleteQna(qnaSeq);
			 map.put("result", true);
			 map.put("message", "삭제되었습니다.");
		 }catch(Exception e){
			 map.put("result", false);
			 map.put("message", "실패하였습니다.");
		 }
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	//내용 자르기
	public String txtLength(String contents){
		
		
		if(contents.length()>8){
			contents = contents.substring(0, 8)+"...";
		}
		return contents;
	}
	//댓글,댓글의 댓글지우기
	public boolean deleteComment(int Seq){
		
		boolean res = false;
			
				List<BbsComment> list1 = bbsCommentDao.getBbsCommentFileList(Seq);
				//댓글을 지우고
				if(list1!=null){
					Iterator<BbsComment>it1 = list1.iterator();
					while(it1.hasNext()){
						BbsComment bc = it1.next();
						if(!bc.getFiles().equals("")){
							filedelete(bc.getFiles());
						}
						//댓글의 댓글삭제
						List<BbsComment>list2 = bbsCommentDao.getRBbsComment(bc.getBbsCommentSeq());
						if(list2!=null){
							Iterator<BbsComment>it2 = list2.iterator();
							while(it2.hasNext()){
								BbsComment rbc = it2.next();
								if(!rbc.getFiles().equals("")){
									filedelete(rbc.getFiles());
								}
								bbsFncDao.deleteBbsCommentGood(rbc.getBbsCommentSeq());//댓글의 댓글신고내역지우기
							}
							
							
						}
						
						bbsCommentDao.deleteBbsReplyCommentAll(bc.getBbsCommentSeq());//댓글의 댓글지우기
						bbsFncDao.deleteBbsCommentGood(bc.getBbsCommentSeq());//댓글의 신고내역 지우기
						messageDao.deletebbsMessage(Push.MSG_TYPE_BBS_COMMENT, bc.getBbsCommentSeq());//댓글에 답글 알림
						List<Message>list3 = messageDao.getdeleteMessageList(Push.MSG_TYPE_BBS_COMMENT, bc.getBbsCommentSeq());
						for(int i =0;i<list3.size();i++){
							Message ms = list3.get(i);
							pushdelete(ms.getUserId(),  bc.getBbsCommentSeq(), "", Push.MSG_TYPE_DELETE, ms.getUserId());
						}
						
					}
					
				}
				
				bbsCommentDao.deleteBbsCommentAll(Seq);//댓글지우기
				res =true;
			
			
		return res;
	}
		
	//배너 클릭
	@RequestMapping("/m/banner_click.go")
	public String bannerClickController(
			@RequestParam(value="bannerSeq", required=false, defaultValue="0") int bannerSeq, 
			HttpServletRequest req, HttpServletResponse res
		) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		
		bannerAdDao.updateBannerAdClick(bannerSeq);

		map.put("result", true);
		 
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	//사진
	
	private int imageExifOrientation(String filePath) {
		HashMap hm = new HashMap();
		File file = new File(filePath);
	
		Metadata metadata;
		int retStr = 0;
		try {
			metadata = JpegMetadataReader.readMetadata(file);
			for (Directory directory : metadata.getDirectories()) {
			    for (Tag tag : directory.getTags()) {
			        String strTagName = tag.getTagName();
			        if (strTagName.equals("Orientation")) {
			        	String strDesc = tag.getDescription();
			        	if (strDesc.contains("90")) {
			        		retStr=90;
			        	}else if(strDesc.contains("180")){
			        		retStr=180;
			        	}else if(strDesc.contains("270")){
			        		retStr=270;
			        	}
			        }
			    }
			}
		} catch (JpegProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retStr;
 	}
	
	
	//인앱결제
	@RequestMapping("/m/inApp_insert.go")
	public String inAppInsertController(
			@RequestParam(value="packageName", required=true, defaultValue="") String packageName,
			@RequestParam(value="productId", required=true, defaultValue="") String productId,
			@RequestParam(value="purchaseToken", required=true, defaultValue="") String purchaseToken,
			@RequestParam(value="accessToken", required=true, defaultValue="") String accessToken,
			@RequestParam(value="userId", required=true, defaultValue="") String userId,
			
			HttpServletRequest req,
			HttpServletResponse res
	){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {			
			
			final String urlStr =
			" https://www.googleapis.com/androidpublisher/v2/applications/"
			+packageName+"/purchases/products/"+productId+"/tokens/"+purchaseToken;
			//+ "?access_token="+accessToken;			
	
			HttpURLConnection conn;
			BufferedReader rd;
			String line;
			String result = "";
			
			try {
				URL url = new URL(urlStr);
			    conn = (HttpURLConnection) url.openConnection();
			    conn.setRequestMethod("GET");
			    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			    while ((line = rd.readLine()) != null) {
			    	result += line;
			    }
			    rd.close();
			 
			    JSONObject jsonObj = JSONObject.fromObject(result);
				String purchaseState=jsonObj.get("purchaseState").toString();
				String consumptionState	=jsonObj.get("consumptionState").toString();
				if(purchaseState.equals("false") || purchaseState.equals("")
				 ||consumptionState.equals("false") || consumptionState.equals("")){
					map.put("result", false);
					map.put("message", "인증 에러 입니다.");
				}else{
					
					//DB INSERT.
					User user = userDao.getUsers(userId);
					PointCharge pc = pointChargeDao.getPointCharge(productId);
					Order order = new Order();
					order.setUserId(userId);
					order.setOrderTitle(/*고유코드*/"");
					order.setPriceSum(pc.getPoint());
					order.setTotalFee(pc.getChargeMoney());
					order.setComment("충전");
					orderDao.addOrder(order);
					int point = user.getPoint()+pc.getPoint();
					userDao.updatePointUser(userId,point);
					
					
					map.put("result", true);
				}
			  
			} catch (Exception e) {
				
				map.put("result", false);
				map.put("message", e.getMessage());
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			map.put("result", false);
			map.put("message", e.getMessage());
		}
		
		Response.responseWrite(res, map);
		return null;
	}
}