package kr.nomad.mars;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.filefilter.DelegateFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
import kr.nomad.mars.dao.ChatOutDao;
import kr.nomad.mars.dao.ChatReqDao;
import kr.nomad.mars.dao.ChatRequestDao;
import kr.nomad.mars.dao.ChatRoomDao;
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
import kr.nomad.mars.dao.TypeDao;
import kr.nomad.mars.dao.UserDao;
import kr.nomad.mars.dao.UserFncDao;
import kr.nomad.mars.dao.UserItemDao;
import kr.nomad.mars.dto.Album;
import kr.nomad.mars.dto.BannerAd;
import kr.nomad.mars.dto.Bbs;
import kr.nomad.mars.dto.BbsCategory;
import kr.nomad.mars.dto.BbsComment;
import kr.nomad.mars.dto.BbsFiles;
import kr.nomad.mars.dto.BbsFnc;
import kr.nomad.mars.dto.ChatBlock;
import kr.nomad.mars.dto.ChatReq;
import kr.nomad.mars.dto.ChatRoom;
import kr.nomad.mars.dto.Distance;
import kr.nomad.mars.dto.Event;
import kr.nomad.mars.dto.EventComment;
import kr.nomad.mars.dto.Expense;
import kr.nomad.mars.dto.Faq;
import kr.nomad.mars.dto.GuestBook;
import kr.nomad.mars.dto.Item;
import kr.nomad.mars.dto.Level;
import kr.nomad.mars.dto.LevelCal;
import kr.nomad.mars.dto.LevelItem;
import kr.nomad.mars.dto.Message;
import kr.nomad.mars.dto.Notice;
import kr.nomad.mars.dto.Order;
import kr.nomad.mars.dto.Point;
import kr.nomad.mars.dto.PointChange;
import kr.nomad.mars.dto.PointCharge;
import kr.nomad.mars.dto.PointMoney;
import kr.nomad.mars.dto.Push;
import kr.nomad.mars.dto.Qna;
import kr.nomad.mars.dto.Site;
import kr.nomad.mars.dto.Type;
import kr.nomad.mars.dto.User;
import kr.nomad.util.F;
import kr.nomad.util.ImageUtil;
import kr.nomad.util.Paging;
import kr.nomad.util.Response;
import kr.nomad.util.T;
import kr.nomad.util.XlsxWriter;
import kr.nomad.util.encrypt.CryptoNew;
import kr.nomad.util.encrypt.CryptoSeedData;
import kr.nomad.util.file.UniqFileRenamePolicy;
import net.sf.json.JSONObject;

@Controller
public class AdminController {

	@Autowired
	NoticeDao noticeDao;

	@Autowired
	UserDao userDao;

	@Autowired
	AreaDao areaDao;

	@Autowired
	EventDao eventDao;

	@Autowired
	FaqDao faqDao;

	@Autowired
	GuestBookDao guestbookDao;

	@Autowired
	QnaDao qnaDao;

	@Autowired
	PointDao pointDao;

	@Autowired
	PointChargeDao pointChargeDao;

	@Autowired
	BbsDao bbsDao;

	@Autowired
	LevelDao levelDao;

	@Autowired
	BbsCommentDao bbsCommentDao;

	@Autowired
	BbsFilesDao bbsFilesDao;

	@Autowired
	PointChangeDao pointChangeDao;

	@Autowired
	BbsCategoryDao bbsCategoryDao;

	@Autowired
	FriendDao friendDao;

	@Autowired
	FriendRequestDao friendRequestDao;

	@Autowired
	ChatRequestDao chatRequestDao;

	@Autowired
	BbsVisitDao bbsVisitDao;

	@Autowired
	GuestBookDao guestBookDao;

	@Autowired
	EventCommentDao eventCommentDao;

	@Autowired
	SiteDao siteDao;

	@Autowired
	PushDao pushDao;

	@Autowired
	private SidoDao sidoDao;

	@Autowired
	PointMoneyDao pointmoneyDao;

	@Autowired
	LevelHistoryDao levelhistoryDao;

	@Autowired
	LevelCal levelService;

	@Autowired
	ExpenseDao expenseDao;

	@Autowired
	DistanceDao distanceDao;

	@Autowired
	OrderDao orderDao;

	@Autowired
	LevelItemDao levelItemDao;

	@Autowired
	TypeDao typeDao;

	@Autowired
	UserItemDao useritemDao;

	@Autowired
	private BbsShareDao bbsShareDao;

	@Autowired
	private ChatBlockDao chatBlockDao;

	@Autowired
	LoginlogDao loginlogDao;

	@Autowired
	MessageDao messageDao;

	@Autowired
	ItemDao itemDao;

	@Autowired
	BannerAdDao bannerAdDao;

	@Autowired
	GiftDao giftDao;

	@Autowired
	private BbsFncDao bbsFncDao;
	
	@Autowired ChatOutDao chatOutDao;
	
	@Autowired UserFncDao userFncDao;
	
	@Autowired ChatReqDao chatReqDao;
	@Autowired ChatRoomDao chatRoomDao; 

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

	// 관리자 헤더 count
	@RequestMapping("/admin/count.go")
	public String adminCountController(HttpSession session, Model model,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		
		int manCount = userDao.getUserManCount(userType,user.getSite());
		int womanCount = userDao.getUserWomanCount(userType,user.getSite());
		int accessCount = userDao.getUserAccessCount(userType,user.getSite());
		int chatroomCount = chatRoomDao.getChatRoomcount(userType,user.getSite());
		
		map.put("manCount", manCount);
		map.put("womanCount", womanCount);
		map.put("accessCount", accessCount);
		map.put("chatroomCount", chatroomCount);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;

	}

	// 관리자 메인
	@RequestMapping("/admin/main.go")
	public String adminMainController(HttpSession session, Model model) {

		List<User> user = null;
		List<Bbs> list = null;

		int topCount = 5;
		User uu =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		
		user = userDao.getUserList(topCount,uu.getSite(),userType);
		list = bbsDao.getBbsList(topCount,uu.getSite(),userType);

		for(int i=0;i<list.size();i++){
			Bbs bbs = list.get(i);
			String file = bbs.getFiles();
			if(!file.equals("")){
				String [] arr = file.split(",");
				ArrayList<String>fileList = new ArrayList<>();
				for(int k=0;k<arr.length;k++){
				
				
					fileList.add(arr[k]);
					
				}
				bbs.setFileList(fileList);
				list.set(i, bbs);
			}
		}
		model.addAttribute("user", user);
		model.addAttribute("bbs", list);

		return "/admin/main";
	}

	// 관리자등록
	@RequestMapping("/admin/admin_insert.go")
	public String admininsertController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			HttpSession session, Model model) {

		List site = siteDao.getSiteList();

		model.addAttribute("site", site);
		model.addAttribute("seq", seq);
		return "/admin/user/admin_edit";
	}

	// 회원 관리 > 일반회원
	@RequestMapping("/admin/user/user.go")
	public String userController(
			@RequestParam(value = "gen", required = false, defaultValue = "0") int gen,
			HttpSession session, Model model) {

		List location = sidoDao.getSidoList();
		List site = siteDao.getSiteList();

		model.addAttribute("gen", gen);
		model.addAttribute("site", site);
		model.addAttribute("location", location);
		return "/admin/user/user";
	}

	// 회원 관리 > 일반회원 리스트
	@RequestMapping("/admin/user/user_list.go")
	public String userListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			@RequestParam(value = "gen", required = false, defaultValue = "0") int gen,
			@RequestParam(value = "gender", required = false, defaultValue = "0") int gender,
			@RequestParam(value = "age", required = false, defaultValue = "0") int age,
			@RequestParam(value = "pageItem", required = false, defaultValue = "0") int pageItem,
			@RequestParam(value = "areaSido", required = false, defaultValue = "") String areaSido,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "colname", required = false, defaultValue = "") String colName,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort,
			HttpSession session, Model model) {

		List<User> list = null;
		int count = 0;
		if(colName.equals("birth_year")){
			if(sort.equals("asc")){
				sort="desc";
			}
			else if(sort.equals("desc")){
				sort="asc";
			}
		}
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		list = userDao.getUserList(1,user.getSite(),userType,site, gen, gender, age, keyword,
				areaSido, colName, sort, page, pageItem);
		count = userDao.getCount(1,user.getSite(),userType,site, gen, gender, age, keyword, areaSido);

		// 페이징
		String paging = Paging.getPaging(page, count, pageItem,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/user/user_list";
	}

	// 회원 관리 > 접속중 회원
	@RequestMapping("/admin/user/user_access.go")
	public String accessController(
			@RequestParam(value = "gen", required = false, defaultValue = "0") int gen,
			HttpSession session, Model model) {
		
		List location = sidoDao.getSidoList();
		List site = siteDao.getSiteList();

		model.addAttribute("gen", gen);
		model.addAttribute("site", site);
		model.addAttribute("location", location);
		return "/admin/user/user_access";
	}

	// 회원 관리 > 접속중 회원
	@RequestMapping("/admin/user/user_access_list.go")
	public String accessListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			@RequestParam(value = "gen", required = false, defaultValue = "0") int gen,
			@RequestParam(value = "gender", required = false, defaultValue = "0") int gender,
			@RequestParam(value = "age", required = false, defaultValue = "0") int age,
			@RequestParam(value = "areaSido", required = false, defaultValue = "") String areaSido,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "colname", required = false, defaultValue = "") String colName,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort,
			HttpSession session, Model model) {

		List<User> list = null;
		int count = 0;
		
		if(colName.equals("birth_year")){
			if(sort.equals("asc")){
				sort="desc";
			}
			else if(sort.equals("desc")){
				sort="asc";
			}
		}
		User uu =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		list = userDao.getUserAccessList(uu.getSite(),userType,site, gen, gender, age, keyword,
				areaSido, colName, sort, page, ITEM_COUNT_PER_PAGE);
		count = userDao.getAccessCount(uu.getSite(),userType,site, gen, gender, age, keyword,
				areaSido);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/user/user_access_list";
	}

	// 회원 관리 > 정지회원
	@RequestMapping("/admin/user/user_stop.go")
	public String stopController(
			@RequestParam(value = "gen", required = false, defaultValue = "0") int gen,
			HttpSession session, Model model) {

		List location = sidoDao.getSidoList();
		List site = siteDao.getSiteList();

		model.addAttribute("gen", gen);
		model.addAttribute("site", site);
		model.addAttribute("location", location);
		return "/admin/user/user_stop";
	}

	// 회원 관리 > 정지회원 리스트
	@RequestMapping("/admin/user/user_stop_list.go")
	public String stopListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			@RequestParam(value = "gen", required = false, defaultValue = "0") int gen,
			@RequestParam(value = "gender", required = false, defaultValue = "0") int gender,
			@RequestParam(value = "age", required = false, defaultValue = "0") int age,
			@RequestParam(value = "areaSido", required = false, defaultValue = "") String areaSido,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "colname", required = false, defaultValue = "") String colName,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort,
			HttpSession session, Model model) {

		List<User> list = null;
		int count = 0;
		if(colName.equals("birth_year")){
			if(sort.equals("asc")){
				sort="desc";
			}
			else if(sort.equals("desc")){
				sort="asc";
			}
		}
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		
		list = userDao.getUserList(3,user.getSite(),userType,site, gen, gender, age, keyword,
				areaSido, colName, sort, page, ITEM_COUNT_PER_PAGE);
		count = userDao.getCount(3,user.getSite(),userType,site, gen, gender, age, keyword, areaSido);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/user/user_stop_list";
	}

	// 회원 관리 > 탈퇴회원
	@RequestMapping("/admin/user/user_drop.go")
	public String dropController(
			@RequestParam(value = "gen", required = false, defaultValue = "0") int gen,
			HttpSession session, Model model) {

		List location = sidoDao.getSidoList();
		List site = siteDao.getSiteList();

		model.addAttribute("gen", gen);
		model.addAttribute("site", site);
		model.addAttribute("location", location);
		return "/admin/user/user_drop";
	}

	// 회원 관리 > 탈퇴회원 리스트
	@RequestMapping("/admin/user/user_drop_list.go")
	public String dropListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			@RequestParam(value = "gen", required = false, defaultValue = "0") int gen,
			@RequestParam(value = "gender", required = false, defaultValue = "0") int gender,
			@RequestParam(value = "age", required = false, defaultValue = "0") int age,
			@RequestParam(value = "areaSido", required = false, defaultValue = "") String areaSido,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "colname", required = false, defaultValue = "") String colName,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort,
			HttpSession session, Model model) {

		List<User> list = null;
		int count = 0;
		if(colName.equals("birth_year")){
			if(sort.equals("asc")){
				sort="desc";
			}
			else if(sort.equals("desc")){
				sort="asc";
			}
		}
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		
		list = userDao.getUserDropList(user.getSite(),userType,site, gen, gender, age, keyword,
				areaSido, colName, sort, page, ITEM_COUNT_PER_PAGE);
		count = userDao.getDropCount(user.getSite(),userType,site, gen, gender, age, keyword, areaSido);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/user/user_drop_list";
	}

	// 오름차순 내림차순 정렬
	@RequestMapping("/admin/user/siteValue.go")
	public String siteValueDoController(
			@RequestParam(value = "item", required = false, defaultValue = "") String item,
			@RequestParam(value = "itemValue", required = false) boolean itemValue,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 오름차순 내림차순 정렬
			userDao.getSorting(item, itemValue);

			// map.put("message", "방명록이 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("e.getMessage()", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 일반회원 엑셀 다운로드
	@RequestMapping("/admin/user/use_list_excel.go")
	public ModelAndView productdListExcelController(
			Model model,

			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			@RequestParam(value = "gen", required = false, defaultValue = "0") int gen,
			@RequestParam(value = "gender", required = false, defaultValue = "0") int gender,
			@RequestParam(value = "age", required = false, defaultValue = "0") int age,
			@RequestParam(value = "areaSido", required = false, defaultValue = "") String areaSido,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "colname", required = false, defaultValue = "") String colName,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort,
			HttpSession session) {
		
		User uu =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		
		List list = userDao.getUserListexcel(uu.getSite(),userType,site, gen, gender, age,
				keyword, areaSido);

		SimpleDateFormat formatdate = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "code_" + formatdate.format(new Date());
		XlsxWriter writer = new XlsxWriter(FILE_ROOT + "/files/excel/"
				+ fileName + ".xls");

		List title = new ArrayList();
		List contents = new ArrayList();

		title.add("운영사이트");
		title.add("아이디");
		title.add("이름");
		title.add("휴대폰번호");
		title.add("나이");
		title.add("성별");
		title.add("지역");
		title.add("포인트(P)");
		title.add("F-MONEY");
		title.add("등록 일자");
		title.add("최근 접속일");

		for (int i = 0; i < list.size(); i++) {
			User user = (User) list.get(i);

			List dataList = new ArrayList();
			dataList.add(user.getSiteName());
			dataList.add(user.getUserId());
			dataList.add(user.getUserName());
			dataList.add(user.getPhoneNumber());
			dataList.add(user.getUserAge());
			dataList.add(user.getGenderText());
			dataList.add(user.getArea());
			dataList.add(user.getPoint());
			dataList.add(user.getIncome());
			dataList.add(user.getRegDate());
			dataList.add(user.getLastLogindate());

			contents.add(dataList);
		}

		writer.writeFile(title, contents);

		File file = new File(FILE_ROOT + "/files/excel/" + fileName + ".xls");

		return new ModelAndView("fileDownloadView", "file", file);
	}

	// 일반회원 상세보기
	@RequestMapping("/admin/user/user_view.go")
	public String userViewController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpSession session, Model model) {

		List<GuestBook> list = null;
		int count = 0;

		User user = null;

		if (userId.equals("")) {
			user = new User();
		} else {
			user = userDao.getUser(userId);
		}

		list = guestbookDao.getGuestBookList(userId, page, ITEM_COUNT_PER_PAGE);
		count = guestbookDao.getCount(userId);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);
		model.addAttribute("user", user);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);
		return "admin/user/user_view";
	}

	// 일반회원 방명록 삭제
	@RequestMapping("/admin/user/book_delete_do.go")
	public String bookDeleteDoController(@RequestParam int bookSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 게시물 삭제
			guestbookDao.deleteGuestBook(bookSeq);

			map.put("message", "방명록이 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "방명록이 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 일반회원 토크 상세보기
	@RequestMapping("/admin/user/talk_view.go")
	public String userTalkViewController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpSession session, Model model) {

		List<Bbs> list = null;
		int count = 0;

		User user = null;

		if (userId.equals("")) {
			user = new User();
		} else {
			user = userDao.getUser(userId);
		}

		list = bbsDao.getGuestBookList(userId, page, ITEM_COUNT_PER_PAGE);
		for(int i=0;i<list.size();i++){
			Bbs bbs = list.get(i);
			String file = bbs.getFiles();
		
			if(!file.equals("")){
				String [] arr = file.split(",");
				ArrayList<String>fileList = new ArrayList<>();
				for(int k=0;k<arr.length;k++){
				
				
					fileList.add(arr[k]);
					
				}
				bbs.setFileList(fileList);
				list.set(i, bbs);
			}
		}
		count = bbsDao.getCount(userId);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("user", user);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);
		return "admin/user/talk_view";
	}

	// 일반회원 앨범 상세보기
	@RequestMapping("/admin/user/album_view.go")
	public String userAlbumViewController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpSession session, Model model) {

		List<BbsFiles> list = null;
		int count = 0;

		User user = null;

		if (userId.equals("")) {
			user = new User();
		} else {
			user = userDao.getUser(userId);
		}

		list = bbsFilesDao.getGuestBookList(userId, page, ITEM_COUNT_PER_PAGE);
		count = bbsFilesDao.getCount(userId);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("user", user);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);
		return "admin/user/album_view";
	}

	// 일반회원 수정 삭제
	@RequestMapping("/admin/user/user_edit.go")
	public String userListController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpSession session, Model model) {

		User user = null;
		List location = sidoDao.getSidoList();

		if (userId.equals("")) {
			user = new User();
		} else {
			user = userDao.getUser(userId);
		}

		model.addAttribute("user", user);
		model.addAttribute("location", location);
		return "/admin/user/user_edit";
	}

	// 일반회원 등록 수정 처리
	@RequestMapping("/admin/user/user_edit_do.go")
	public String userEditDoController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "use", required = false, defaultValue = "0") int use,
			@RequestParam(value = "point", required = false, defaultValue = "0") int point,
			@RequestParam(value = "money", required = false, defaultValue = "0") int money,
			@RequestParam(value = "userName", required = false, defaultValue = "") String userName,
			@RequestParam(value = "gender", required = false, defaultValue = "0") int gender,
			@RequestParam(value = "phoneNumber", required = false, defaultValue = "") String phoneNumber,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "area", required = false, defaultValue = "") String area,
			@RequestParam(value = "userLevel", required = false, defaultValue = "0") int userLevel,
			@RequestParam(value = "userAge", required = false, defaultValue = "0") int userAge,
			HttpServletResponse res, Model model,HttpSession ss) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			boolean userCheck = userDao.correctNick(userName);

			User user = userDao.getUser(userId);
			if(user.getPoint()!=point || user.getIncome()!=money){
				levelService.inserDb(userId, PointMoney.JOIN_ADMIN, money, point,"관리자변경");
				String pushmsg = "관리자에 의해 ";
				
				if(user.getPoint()!=point){
					int sum = point - user.getPoint(); //추가 
					if(sum>0){ //추가
						pushmsg += sum+"포인트 추가 ";
					}else{//차감
						pushmsg += Math.abs(sum)+"포인트 차감 "; 
					}
					
				}
				if(user.getIncome()!=money){
					int sum = money - user.getIncome(); //추가 
					if(sum>0){ //추가
						pushmsg += sum+"CASH 추가 ";
					}else{//차감
						pushmsg += Math.abs(sum)+"CASH 차감 "; 
					}
					
				}
				pushmsg +="되었습니다.";
				String writeId = (String)ss.getAttribute("USER_ID");
				
				push(userId, 0, pushmsg, Push.MSG_TYPE_POINT_ADMIN, writeId);
			}
			

			
			int thisYear = Integer.parseInt(T.getTodayYear());
			int birthYear = thisYear - userAge +1;

			

			user.setStatus(use);
			user.setPoint(point);
			user.setIncome(money);

			if (!userName.equals("")) {

				user.setUserName(userName);

			} else if (userCheck) {
				result = false;
				message = "존재하는 닉네임 입니다.";

			}
			user.setGender(gender);
			user.setBirthYear(birthYear);
			user.setPhoneNumber(phoneNumber);
			user.setComment(comment);
			user.setUserLevel(userLevel);

			Level level = levelDao.getremainex(userLevel);

			if (userLevel == level.getLevel()) {
				user.setLevelPoint(level.getMinEx());
			}

			user.setArea(area);
			

			result = true;
			message = "수정되었습니다.";
			
			if(use ==3){ //사용중지.
			
				pushdelete(userId, 0, "LOGOUT", Push.MSG_TYPE_LOGOUT, "ADMIN");
				user.setDeviceId("");
				user.setPushkey("");
				userDao.updateUser(user);
				//채팅에 관련한 요청들.
				ChatReq cReq = new ChatReq();
				cReq.setMemberID(userId);
				cReq.setReqTYPE("10");
				chatReqDao.addChatReq(cReq);
				
				
			}else if (use == 4) {
				pushdelete(userId, 0, "LOGOUT", Push.MSG_TYPE_LOGOUT, "ADMIN");
				userDao.updateUser(user);
				String photoname = (userDao.getUser(userId)).getPhoto();

				if (!photoname.equals("")) {
					filedelete(photoname);
				}

				userDao.outUser(4, userId, "관리자탈퇴");// 유저테이블 회원 상태 4

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
				
			    eventCommentDao.deleteEventComment(userId); //이벤트 댓글삭제
			    List<Qna>list = qnaDao.getQnaList(userId);
			    for(int i=0;i<list.size();i++){
			    	Qna qq = list.get(i);
			    	int seq =qq.getNoticeSeq();
			    	
					qnaDao.deleteQna(seq);
					qnaDao.deleteQnaComment(seq);
			    }
			   
			
				userFncDao.deleteUserFnc(userId); //유저 신고당한 내역 한내역 삭제
				chatOutDao.deleteChatOut(userId); //채팅 강퇴내역 삭제
				
				
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
				
				//채팅에 관련한 요청들.
				ChatReq cReq = new ChatReq();
				cReq.setMemberID(userId);
				cReq.setReqTYPE("10");
				chatReqDao.addChatReq(cReq);
				
				message = "탈퇴되었습니다.";
			}else{
				userDao.updateUser(user);
			}

		} catch (Exception e) {
			message = e.getMessage();
		}

		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 일반회원 삭제
	@RequestMapping("/admin/user/user_delete_do.go")
	public String companyDeleteDoController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpServletResponse res) {

		boolean result = false;
		String message = "";
		List<ChatBlock> list = null;
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			String photoname = (userDao.getUser(userId)).getPhoto();

			if (!photoname.equals("")) {
				filedelete(photoname);
			}

			userDao.updatedUser(4, userId, "관리자탈퇴");// 유저테이블 회원 상태 4

			friendDao.deleteFriend(userId);// 친구 상태 삭제
			friendRequestDao.deleteFriendRequest(userId);// 친구요청 삭제
			chatRequestDao.deleteChatRequest(userId);// 채팅 요청 삭제
			pointmoneyDao.deletePointMoney(userId);// 포인트 기록 삭제
			levelhistoryDao.deleteLevelHistory(userId);// 레벨 히스토리 삭제

			loginlogDao.deleteLoginlog(userId);// 로그인 로그 삭제
			// 토크지우기
			List<Bbs> bblist = null;
			bblist = bbsDao.getBbsList(userId); // 일단 내 토크목록가져온뒤에
			if (bblist != null) {
				Iterator<Bbs> it = bblist.iterator();
				while (it.hasNext()) {
					Bbs bbs = it.next();
					int bbsSeq = bbs.getBbsSeq(); // 내 글의 seq 로
					if (!bbs.getFiles().equals("")) {
						filedelete(bbs.getFiles());
					}
					// 일단 공유한 글 리스트가져오고
					List<Bbs> sharelist = bbsDao.getshareBbsList(bbsSeq);
					for (int i = 0; i < sharelist.size(); i++) {
						Bbs sharebbs = sharelist.get(i);
						deleteComment(sharebbs.getBbsSeq());// 공유한글의 글 Seq로
															// 댓글,댓글의 댓글지우고
						messageDao.deletebbsMessage(Push.MSG_TYPE_BBS,
								sharebbs.getBbsSeq());
						bbsFncDao.deleteBbsGood(sharebbs.getBbsSeq());// 그글에 엮인
																		// 좋아요,신고
																		// 지우고
						bbsFilesDao.deleteshareFiles(sharebbs.getBbsSeq()); // 파일리스트
																			// 삭제
						bbsDao.deleteShareBbs(bbsSeq);// 이글에 공유되있는 글지우기

					}

					// bbsShareDao.deleteBbsShare2(bbsSeq);
					bbsFncDao.deleteBbsGood(bbsSeq);// 내글과 연관된 좋아요 지우기
					boolean delresult = bbsDao.deleteBbss(bbsSeq);
					if (delresult) {
						deleteComment(bbsSeq);
					}
				}
			}
			bbsDao.deleteBbs(userId); // 내글과 내가 공유한글 삭제
			// bbsCommentDao.deleteBbsComment(userId);//내 댓글 삭제..?
			bbsVisitDao.deleteBbsVisit(userId);// 토크 방문기록 삭제
			guestBookDao.deleteGuestBook(userId);// 방명록삭제
			bbsShareDao.deleteuserShare(userId); // 공유기록 삭제
			chatBlockDao.deleteBlock(userId); // 차단기록 삭제
			messageDao.deleteMessage(userId);// 알림 내역 삭제
			useritemDao.deleteUserItem(userId);// 아이템 내역 삭제
			bbsFncDao.deleteBbsFnc(userId);// 신고내역을 삭제해야할까
			giftDao.deletedropGIFT(userId);// 선물내역삭제
			// 생성된 채팅룸 지워야됨
			// 참여한 방 나가기 처리해야함.

			// 환급 신청 내역을 삭제해야할가?

			List<BbsFiles> filelist = bbsFilesDao.getAlbumList(userId);
			if (filelist != null) {
				Iterator<BbsFiles> it2 = filelist.iterator();
				while (it2.hasNext()) {
					String filename = (it2.next()).getFileName();
					if (!filename.equals("")) {
						filedelete(filename);
					}

				}

				bbsFilesDao.deleteFiles(userId); // 파일리스트 삭제
			}
			//채팅에 관련한 요청들.
			ChatReq cReq = new ChatReq();
			cReq.setMemberID(userId);
			cReq.setReqTYPE("10");
			chatReqDao.addChatReq(cReq);
			
			message = "탈퇴되었습니다.";
			result = true;
		} catch (Exception e) {
			message = "실패하였습니다.";
		}

		map.put("result", result);
		map.put("message", message);
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, map);
		return null;
	}

	// 회원 관리 > 환급처리
	@RequestMapping("/admin/user/point.go")
	public String ccController(
			HttpSession session,
			Model model,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "point", required = false, defaultValue = "1") int point,
			@RequestParam(value = "fmoney", required = false, defaultValue = "1") int fmoney,
			@RequestParam(value = "kind", required = false, defaultValue = "") String kind,// p,f
			HttpServletResponse res) {

		List<PointChange> list = pointChangeDao.getPointChangeList();

		model.addAttribute("pointset", list);
		model.addAttribute("userId", userId);
		model.addAttribute("point", point);
		model.addAttribute("kind", kind);
		model.addAttribute("fmoney", fmoney);

		return "/admin/user/user_point";
	}

	// 회원 관리 > 환급처리 뷰
	@RequestMapping("/admin/user/point_v.go")
	public String point_vController(HttpSession session, Model model,
			HttpServletResponse res) {

		List<PointChange> list = pointChangeDao.getPointChangeList();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		map.put("pointv", list);
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// // 회원 관리 > 환급처리 ㄱ
	// @RequestMapping("/admin/user/user_point_do.go")
	// public String vccController(HttpSession session, Model model,
	// @RequestParam(value = "userId", required = false, defaultValue = "")
	// String userId,
	// @RequestParam(value = "point", required = false, defaultValue = "0") int
	// point,//환급포인트
	// @RequestParam(value = "upoint", required = false, defaultValue = "0") int
	// upoint,//유저포인트
	// @RequestParam(value = "fmoney", required = false, defaultValue = "0") int
	// fmoney,//환급포인트
	// @RequestParam(value = "ufmoney", required = false, defaultValue = "0")
	// int ufmoney,//유저포인트
	// @RequestParam(value = "kind", required = false, defaultValue = "") String
	// kind,//p,f,
	// @RequestParam(value = "seckind", required = false, defaultValue = "")
	// String seckind,//p,w
	// HttpServletResponse res
	// ) {
	//
	//
	// if(kind.equals("p")){ //포인트를 현금으로
	//
	// levelService.inserDb(userId, Point.JOIN_CHANGE, 0 , point);
	// }else{ //f머니를 포인트나 현금으로
	// if(seckind.equals("p")){ //포인트로 전환한다면
	// levelService.inserDb(userId, Point.JOIN_CHANGE, fmoney , point);
	// }else{
	// levelService.inserDb(userId, Point.JOIN_CHANGE, fmoney , 0);
	// }
	// }
	//
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("result", true);
	//
	// JSONObject jsonObject = JSONObject.fromObject(map);
	// Response.responseWrite(res, jsonObject);
	//
	//
	//
	// return null;
	// }

	// 회원 관리 > 관리사이트
	@RequestMapping("/admin/user/site.go")
	public String siteController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<Site> list = siteDao.getSiteList();

		model.addAttribute("list", list);
		return "/admin/user/site";
	}

	// 회원 관리 > 운영사이트 > 등록/수정
	@RequestMapping("/admin/user/site_edit.go")
	public String siteEditController(
			@RequestParam(value = "siteSeq", required = false, defaultValue = "0") int siteSeq,
			HttpSession session, Model model) {

		Site site = siteDao.getSite(siteSeq);

		model.addAttribute("site", site);
		return "/admin/user/site_edit";
	}

	// 회원 관리 > 운영사이트 > 등록/수정
	@RequestMapping("/admin/user/site_edit_do.go")
	public String siteEditDoController(
			@RequestParam(value = "siteSeq", required = false, defaultValue = "0") int siteSeq,
			@RequestParam(value = "siteName", required = false, defaultValue = "") String siteName,
			@RequestParam(value = "siteUrl", required = false, defaultValue = "") String siteUrl,
			@RequestParam(value = "companyName", required = false, defaultValue = "") String companyName,
			@RequestParam(value = "packageName", required = false, defaultValue = "") String packageName,
			@RequestParam(value = "serviceId", required = false, defaultValue = "") String serviceId,
			@RequestParam(value = "companyPhone", required = false, defaultValue = "") String companyPhone,
			@RequestParam(value = "managerName", required = false, defaultValue = "") String managerName,
			HttpServletResponse res, HttpSession session, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			if (siteSeq == 0) {
				Site site = new Site();
				site.setSiteName(siteName);
				site.setSiteUrl(siteUrl);
				site.setCompanyName(companyName);
				site.setCompanyPhone(companyPhone);
				site.setManagerName(managerName);
				site.setServiceId(serviceId);
				site.setPackageName(packageName);
				siteDao.addSite(site);

				result = true;
				message = "운영사이트가 등록되었습니다.";
			} else {
				Site site = siteDao.getSite(siteSeq);
				site.setSiteName(siteName);
				site.setSiteUrl(siteUrl);
				site.setCompanyName(companyName);
				site.setCompanyPhone(companyPhone);
				site.setManagerName(managerName);
				site.setServiceId(serviceId);
				site.setPackageName(packageName);
				siteDao.updateSite(site);

				result = true;
				message = "운영사이트가 정보가 수정되었습니다.";
			}
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

	// 회원 관리 > 운영사이트 > 삭제
	@RequestMapping("/admin/user/site_delete_do.go")
	public String siteDeleteDoController(
			@RequestParam(value = "siteSeq", required = false, defaultValue = "0") int siteSeq,
			HttpServletResponse res, HttpSession session, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			siteDao.deleteSite(siteSeq);
			map.put("result", true);
			map.put("message", "운영사이트가 삭제되었습니다.");
		} catch (Exception e) {
			map.put("result", false);
			map.put("message", e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 회원 관리 > 관리자회원
	@RequestMapping("/admin/user/admin.go")
	public String adminController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {
		List site = siteDao.getSiteList();

		model.addAttribute("site", site);
		return "/admin/user/admin";
	}

	// 회원 관리 > 관리자회원 리스트
	@RequestMapping("/admin/user/admin_list.go")
	public String adminListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			@RequestParam(value = "userType", required = false, defaultValue = "0") int userType,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "colname", required = false, defaultValue = "") String colName,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort,
			HttpSession session, Model model) {

		List<User> list = null;
		int count = 0;
		User uu =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int loginuserType=(int)session.getAttribute("USER_TYPE");
		
		list = userDao.getAdminList(uu.getSite(),loginuserType,site, keyword, colName, sort, userType,
				page, ITEM_COUNT_PER_PAGE);
		count = userDao.getAdminCount(uu.getSite(),loginuserType,site, keyword, userType);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);

		return "admin/user/admin_list";
	}

	// 관리자 수정
	@RequestMapping("/admin/user/admin_edit.go")
	public String adminEditController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "seq", required = false, defaultValue = "") int seq,
			HttpSession session, Model model) {

		User user = null;
		if (userId.equals("")) {
			user = new User();
		} else {
			user = userDao.getUser(userId);
		}
		List site = siteDao.getSiteList();
		User uu =userDao.getUsers((String)session.getAttribute("USER_ID"));
		model.addAttribute("adminSite", uu.getSite());
		model.addAttribute("site", site);
		model.addAttribute("user", user);
		model.addAttribute("seq", seq);
		return "/admin/user/admin_edit";
	}

	// 관리자 등록 수정 처리
	@RequestMapping("/admin/user/admin_edit_do.go")
	public String adminEditDoController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			
			@RequestParam(value = "userName", required = false, defaultValue = "") String userName,
			@RequestParam(value = "password", required = true, defaultValue = "") String password,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "userAdmin", required = true, defaultValue = "0") int userAdmin,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		String enPw = "";

		try {

			if (seq == 0) {
				boolean userCheck = userDao.correctId(userId);
				if (userCheck) {
					result = false;
					message = "중복되는 아이디가 있습니다.";
				} else {

					User user = new User();
					// enPw = Sha256Util.encryptPassword(password);
					enPw = CryptoNew.encrypt(password);
					user.setUserId(userId);
					user.setUserName(userName);
					user.setPassword(enPw);
					user.setComment(comment);
					user.setUserType(userAdmin);
					user.setSite(site);
					user.setStatus(1);
					userDao.addUser(user);
					result = true;
					message = "관리자가 등록되었습니다.";

				}
			} else {

				User user = userDao.getUser(userId);
			
				user.setUserName(userName);
				user.setPassword(enPw);
				user.setUserType(userAdmin);
				user.setComment(comment);
				// user.setArea(areaSido);
				user.setSite(site);
				userDao.updateUser(user);
				result = true;
				message = "관리자가 수정되었습니다.";

			}
		} catch (Exception e) {
			message = e.getMessage();
		}

		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;

	}

	// 관리자 삭제
	@RequestMapping("/admin/user/admin_delete_do.go")
	public String adminDeleteDoController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 관리자 삭제
			userDao.deleteUser(userId);

			map.put("message", "관리자가 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "관리자가 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 관리자 엑셀 다운로드
	@RequestMapping("/admin/user/admin_list_excel.go")
	public ModelAndView adminListExcelController(Model model,HttpSession session) {

		
		User uu =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		List list = userDao.getAdmin(uu.getSite(),userType);
		SimpleDateFormat formatdate = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "code_" + formatdate.format(new Date());
		XlsxWriter writer = new XlsxWriter(FILE_ROOT + "/files/excel/"
				+ fileName + ".xls");

		List title = new ArrayList();
		List contents = new ArrayList();

		title.add("운영사이트");
		title.add("관리레벨");
		title.add("관리자명");
		title.add("아이디");
		title.add("등록 일자");

		for (int i = 0; i < list.size(); i++) {
			User user = (User) list.get(i);

			List dataList = new ArrayList();
			dataList.add(user.getSiteName());
			dataList.add(user.getUserTypeText());
			dataList.add(user.getUserName());
			dataList.add(user.getUserId());
			dataList.add(user.getRegDate());

			contents.add(dataList);
		}

		writer.writeFile(title, contents);

		File file = new File(FILE_ROOT + "/files/excel/" + fileName + ".xls");

		return new ModelAndView("fileDownloadView", "file", file);
	}

	// 회원 관리 > 포인트 설정
	@RequestMapping("/admin/user/set_point.go")
	public String pointController(
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			HttpSession session, Model model) {

		List<Point> list = null;
		int count = 0;
		List<Site> sitelist= siteDao.getSiteList();
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		
		if(site.equals("")){
			if(userType==1){
				if(sitelist.size()>0){
					site=sitelist.get(0).getServiceId();
				}
			}else{
				site=user.getSite();
			}
		}
		list = pointDao.getPointList(site);
		
		

	
		model.addAttribute("sitelist", sitelist);
		model.addAttribute("list", list);
		model.addAttribute("site", site);
		System.out.println(site);
		return "/admin/user/set_point";
	}
	
	// 포인트 등록, 수정의 처리
	@RequestMapping("/admin/user/point_edit_do.go")
	public String pointEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "pointSeq", required = false, defaultValue = "0") int pointSeq,
			@RequestParam(value = "eventName", required = false, defaultValue = "") String eventName,
			@RequestParam(value = "point", required = false, defaultValue = "0") int point,
			@RequestParam(value = "money", required = false, defaultValue = "0") int money,
			@RequestParam(value = "period", required = false, defaultValue = "0") int period,
			@RequestParam(value = "times", required = false, defaultValue = "0") int times,
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			@RequestParam(value = "pointCode", required = false, defaultValue = "") String pointCode,
			@RequestParam(value = "commend", required = false, defaultValue = "") String commend,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			Point pp=null;
			if(!pointCode.equals(PointMoney.JOIN_LEVELUP)){
				pp =pointDao.getSitePoint(pointCode,site);
			}
			
			
		
			
			if (pointSeq == 0) {
				if(pp==null){
					Point pointinput = new Point();
					pointinput.setEventName(eventName);
					pointinput.setPoint(point);
					pointinput.setMoney(money);
					pointinput.setPeriod(period);
					pointinput.setTimes(times);
					pointinput.setCommend(commend);
					pointinput.setSite(site);
					pointinput.setPointCode(pointCode);
					pointDao.addPoint(pointinput);
					// pointSeq = pointDao.getLastSeq();
					result = true;
					message = "등록되었습니다.";
				}else{
					result = false;
					message = "등록된 정책입니다.";
				}

			} else {
				Point pointinput = pointDao.getPoint(pointSeq);
				pointinput.setEventName(eventName);
				pointinput.setPoint(point);
				pointinput.setMoney(money);
				pointinput.setPeriod(period);
				pointinput.setTimes(times);
				pointinput.setCommend(commend);
				if(pp!=null&&!pointinput.getPointCode().equals(pointCode)&&pp.getPointSeq()!=pointSeq){
						result = false;
						message = "등록된 정책입니다.";
				}else{
					pointinput.setPointCode(pointCode);
					pointDao.updatePoint(pointinput);
					result = true;
					message = "수정되었습니다.";
				}
				
			}
		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("pointSeq", pointSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 포인트 설정 삭제
	@RequestMapping("/admin/user/point_delete_do.go")
	public String pointDeleteDoController(@RequestParam int pointSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 포인트 설정 삭제
			pointDao.deletePoint(pointSeq);

			map.put("message", "삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 회원 관리 > 포인트 충전 설정
	@RequestMapping("/admin/user/set_point_charge.go")
	public String chargeController(
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			HttpSession session, Model model) {

		List<PointCharge> list = null;
		int count = 0;

		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		List <Site>sitelist= siteDao.getSiteList();
		if(site.equals("")){
			if(userType==1){
				if(sitelist.size()>0){
					site=sitelist.get(0).getServiceId();
				}
			}else{
				site=user.getSite();
			}
		}
		list = pointChargeDao.getPointChargeList(site);
		
		

	
		model.addAttribute("sitelist", sitelist);
		model.addAttribute("site", site);
		model.addAttribute("list", list);
	

		return "/admin/user/set_point_charge";
	}

	// 포인트 충전 등록, 수정의 처리
	@RequestMapping("/admin/user/charge_edit_do.go")
	public String chargeEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "chargeSeq", required = false, defaultValue = "0") int chargeSeq,
			@RequestParam(value = "chargeMoney", required = false, defaultValue = "0") int chargeMoney,
			@RequestParam(value = "chargePoint", required = false, defaultValue = "0") int chargePoint,
			@RequestParam(value = "point", required = false, defaultValue = "0") int point,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			@RequestParam(value = "code", required = false, defaultValue = "") String code,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			if (chargeSeq == 0) {
				PointCharge pointCharge = new PointCharge();
				pointCharge.setChargeMoney(chargeMoney);
				pointCharge.setChargePoint(chargePoint);
				pointCharge.setPoint(point);
				pointCharge.setComment(comment);
				pointCharge.setSite(site);
				pointCharge.setCode(code);
				pointChargeDao.addPointCharge(pointCharge);
				// pointSeq = pointDao.getLastSeq();
				result = true;
				message = "등록되었습니다.";

			} else {
				PointCharge pointCharge = pointChargeDao
						.getPointCharge(chargeSeq);
				pointCharge.setChargeMoney(chargeMoney);
				pointCharge.setChargePoint(chargePoint);
				pointCharge.setPoint(point);
				pointCharge.setSite(site);
				pointCharge.setComment(comment);
				pointCharge.setCode(code);
				pointChargeDao.updatePointCharge(pointCharge);
				result = true;
				message = "수정되었습니다.";
			}
		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("chargeSeq", chargeSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 포인트 충전 설정 삭제
	@RequestMapping("/admin/user/charge_delete_do.go")
	public String chargeDeleteDoController(@RequestParam int chargeSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 포인트 충전 설정 삭제
			pointChargeDao.deletePointCharge(chargeSeq);

			map.put("message", "삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 회원 관리 > 포인트/money 전환 설정
	@RequestMapping("/admin/user/set_point_money.go")
	public String moneyController(
			HttpSession session,
			Model model,
			@RequestParam(value = "site", required = false, defaultValue = "") String site) {

		List<PointChange> list = null;
		int count = 0;

		List <Site>sitelist= siteDao.getSiteList();
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
			int userType=(int)session.getAttribute("USER_TYPE");
			
			if(site.equals("")){
				if(userType==1){
					if(sitelist.size()>0){
						site=sitelist.get(0).getServiceId();
					}
				}else{
					site=user.getSite();
				}
			}
		
		list = pointChangeDao.getPointChangeList(site);
		
	
		model.addAttribute("sitelist", sitelist);
		model.addAttribute("list", list);
		model.addAttribute("site", site);

		return "/admin/user/set_point_money";
	}

	// 회원 관리 > 포인트/money 전환 설정 처리
	@RequestMapping("/admin/user/change_edit_do.go")
	public String changeEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "changeSeq", required = false, defaultValue = "0") int changeSeq,
			@RequestParam(value = "money", required = false, defaultValue = "0") int money,
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			@RequestParam(value = "cash", required = false, defaultValue = "0") int cash,
			@RequestParam(value = "point", required = false, defaultValue = "0") int point,
			// @RequestParam(value = "status", required = false, defaultValue =
			// "0") int status,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			if (changeSeq == 0) {
				PointChange pointChange = new PointChange();
				pointChange.setCash(cash);
				pointChange.setMoney(money);
				pointChange.setPoint(point);
				pointChange.setSite(site);
				pointChangeDao.addPointChange(pointChange);
				// pointSeq = pointDao.getLastSeq();
				result = true;
				message = "등록되었습니다.";

			} else {
				PointChange pointChange = pointChangeDao
						.getPointChange(changeSeq);
				pointChange.setCash(cash);
				pointChange.setMoney(money);
				pointChange.setPoint(point);
				pointChange.setSite(site);
				pointChangeDao.updatePointChange(pointChange);
				result = true;
				message = "수정되었습니다.";
			}
		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("changeSeq", changeSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 포인트 충전 설정 삭제
	@RequestMapping("/admin/user/change_delete_do.go")
	public String changeDeleteDoController(@RequestParam int changeSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 포인트 충전 설정 삭제
			pointChangeDao.deletePointChange(changeSeq);

			map.put("message", "삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 회원 관리 > 레벨 정책 설정
	@RequestMapping("/admin/user/set_level.go")
	public String levelController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpSession session, Model model) {

		List<Level> list = null;
		int count = 0;

		list = levelDao.getLevelList(page, ITEM_COUNT_PER_PAGE);
		count = levelDao.getCount();

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);

		return "/admin/user/set_level";
	}

	// 레벨 정책 설정 등록, 수정 처리
	@RequestMapping("/admin/user/level_edit_do.go")
	public String levelEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "levelSeq", required = false, defaultValue = "0") int levelSeq,
			@RequestParam(value = "level", required = false, defaultValue = "0") int level,
			@RequestParam(value = "minEx", required = false, defaultValue = "0") int minEx,
			@RequestParam(value = "maxEx", required = false, defaultValue = "0") int maxEx,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			if (levelSeq == 0) {
				Level levelinput = new Level();
				levelinput.setLevel(level);
				levelinput.setMinEx(minEx);
				levelinput.setMaxEx(maxEx);
				levelDao.addLevel(levelinput);
				// pointSeq = pointDao.getLastSeq();
				result = true;
				message = "등록되었습니다.";

			} else {
				Level levelinput = levelDao.getLevel(levelSeq);
				levelinput.setLevel(level);
				levelinput.setMinEx(minEx);
				levelinput.setMaxEx(maxEx);
				levelDao.updateLevel(levelinput);
				result = true;
				message = "수정되었습니다.";
			}
		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("levelSeq", levelSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 레벨 정책 삭제
	@RequestMapping("/admin/user/level_delete_do.go")
	public String levelDeleteDoController(@RequestParam int levelSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 레벨 정책 삭제
			levelDao.deleteLevel(levelSeq);

			map.put("message", "삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 회원 관리 > 레벨 아이템
	@RequestMapping("/admin/user/level_item.go")
	public String itemController(
			@RequestParam(value = "", required = false, defaultValue = "") String site,
			HttpSession session, Model model) {

		List<LevelItem> list = null;
		int count = 0;
		List <Site>sitelist= siteDao.getSiteList();
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
			int userType=(int)session.getAttribute("USER_TYPE");
			
			if(site.equals("")){
				if(userType==1){
					if(sitelist.size()>0){
						site=sitelist.get(0).getServiceId();
					}
				}else{
					site=user.getSite();
				}
			}
		list = levelItemDao.getLevelItemList(site);
		

		model.addAttribute("sitelist", sitelist);
		model.addAttribute("list", list);
		model.addAttribute("site", site);


		return "/admin/user/level_item";
	}

	// 레벨 아이템 등록, 수정의 처리
	@RequestMapping("/admin/user/item_edit_do.go")
	public String itemEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "itemSeq", required = false, defaultValue = "0") int itemSeq,
			@RequestParam(value = "levelNum", required = false, defaultValue = "0") int levelNum,
			@RequestParam(value = "benefit", required = false, defaultValue = "") String benefit,
			@RequestParam(value = "period", required = false, defaultValue = "0") int period,
			@RequestParam(value = "limitCount", required = false, defaultValue = "0") int limitCount,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			if (itemSeq == 0) {
				LevelItem levelItem = new LevelItem();
				levelItem.setLevelNum(levelNum);
				levelItem.setBenefit(benefit);
				levelItem.setPeriod(period);
				levelItem.setLimitCount(limitCount);
				levelItemDao.addLevelItem(levelItem);

				// pointSeq = pointDao.getLastSeq();
				result = true;
				message = "등록되었습니다.";

			} else {
				LevelItem levelItem = new LevelItem();
				levelItem.setItemSeq(itemSeq);
				levelItem.setLevelNum(levelNum);
				levelItem.setBenefit(benefit);
				levelItem.setPeriod(period);
				levelItem.setLimitCount(limitCount);
				levelItemDao.updateLevelItem(levelItem);
				result = true;
				message = "수정되었습니다.";
			}
		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("itemSeq", itemSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 레벨 아이템 설정 삭제
	@RequestMapping("/admin/user/item_delete_do.go")
	public String itemChaDeleteDoController(@RequestParam int itemSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 포인트 설정 삭제
			levelItemDao.deleteLevelItem(itemSeq);

			map.put("message", "삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 회원 관리 > 구매 아이템
	@RequestMapping("/admin/user/charge_item.go")
	public String chargeItemController(
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			HttpSession session, Model model) {

		List<Item> list = null;
		int count = 0;

		
		List <Site>sitelist= siteDao.getSiteList();
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
			int userType=(int)session.getAttribute("USER_TYPE");
			
			if(site.equals("")){
				if(userType==1){
					if(sitelist.size()>0){
						site=sitelist.get(0).getServiceId();
					}
				}else{
					site=user.getSite();
				}
			}
		
			list = itemDao.getItemList(site);
			

		
		model.addAttribute("sitelist", sitelist);
		model.addAttribute("list", list);
		model.addAttribute("site", site);

		

		return "/admin/user/charge_item";
	}

	// 구매 아이템 등록, 수정의 처리
	@RequestMapping("/admin/user/itemcharge_edit_do.go")
	public String itemChargeEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "itemName", required = false, defaultValue = "") String itemName,
			@RequestParam(value = "point", required = false, defaultValue = "") int point,
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			@RequestParam(value = "limitCount", required = false, defaultValue = "0") int limitCount,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			if (seq == 0) {

				Item ltem = new Item();
				ltem.setItemName(itemName);
				if (itemName.equals(Item.NAME_FREETICHET)) {
					ltem.setItemCode(ltem.CODE_FREETICHET);
				} else if (itemName.equals(Item.NAME_MEGAPHONE)) {
					ltem.setItemCode(ltem.CODE_MEGAPHONE);
				}
				
				ltem.setPoint(point);
				ltem.setSite(site);
				ltem.setLimitCount(limitCount);
				itemDao.addItem(ltem);

				// pointSeq = pointDao.getLastSeq();
				result = true;
				message = "등록되었습니다.";

			} else {
				Item ltem = new Item();
				ltem.setSeq(seq);
				if (itemName.equals(Item.NAME_FREETICHET)) {
					ltem.setItemCode(ltem.CODE_FREETICHET);
				} else if (itemName.equals(Item.NAME_MEGAPHONE)) {
					ltem.setItemCode(ltem.CODE_MEGAPHONE);
				}
				ltem.setItemName(itemName);
				ltem.setPoint(point);
				ltem.setSite(site);
				ltem.setLimitCount(limitCount);
				itemDao.updateItem(ltem);
				result = true;
				message = "수정되었습니다.";
			}
		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("seq", seq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 구매 아이템 설정 삭제
	@RequestMapping("/admin/user/itemcharge_delete_do.go")
	public String itemChargeDeleteDoController(@RequestParam int seq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 포인트 설정 삭제
			itemDao.deleteItem(seq);

			map.put("message", "삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 회원관리 > 약관 관리
	@RequestMapping("/admin/doc/doc_list.go")
	public String docController(
			HttpSession session,
			Model model,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "point", required = false, defaultValue = "1") int point,
			HttpServletResponse res) {

		return "admin/doc/doc";
	}

	// 회원관리 > 거리 관리
	@RequestMapping("/admin/distance/distance.go")
	public String categoryController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		List<Distance> distance = null;

		distance = distanceDao.getDistanceList();

		model.addAttribute("distance", distance);

		return "/admin/distance/distance";
	}

	// 거리 등록,수정의 처리
	@RequestMapping("/admin/distance/distance_edit_do.go")
	public String distanceEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "distanceSeq", required = false, defaultValue = "0") int distanceSeq,
			@RequestParam(value = "distanceValue", required = false, defaultValue = "0") int distanceValue,
			@RequestParam(value = "distanceName", required = false, defaultValue = "") String distanceName,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			if (distanceSeq == 0) {
				Distance distance = new Distance();
				distance.setDistanceValue(distanceValue);
				distance.setDistanceName(distanceName);
				int sort = distanceDao.getLastSeq();
				distance.setSort(sort + 1);
				distanceDao.addDistance(distance);
				result = true;
				message = "추가되었습니다.";
			} else {
				Distance distance = distanceDao.getDistance(distanceSeq);
				distance.setDistanceValue(distanceValue);
				distance.setDistanceName(distanceName);
				distanceDao.updateDistance(distance);
				result = true;
				message = "수정되었습니다.";
			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 거리 삭제
	@RequestMapping("/admin/distance/distance_delete_do.go")
	public String categoryDeleteDoController(@RequestParam int distanceSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 거리 삭제
			distanceDao.deleteDistance(distanceSeq);

			map.put("message", "거리가 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "거리가 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 토크 관리 > 토크목록
	@RequestMapping("/admin/talk/talk.go")
	public String talkController(HttpSession session, Model model) {

		List location = sidoDao.getSidoList();
		model.addAttribute("location", location);
		List<Site>sitelist =siteDao.getSiteList();
		List<BbsCategory>cateList = bbsCategoryDao.getadminBbsCategoryList();
		model.addAttribute("cateList", cateList);
		model.addAttribute("sitelist", sitelist);
		return "/admin/talk/talk";
	}

	// 토크 관리 > 토크목록 리스트
	@RequestMapping("/admin/talk/talk_list.go")
	public String talkListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "gender", required = false, defaultValue = "0") int gender,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			@RequestParam(value = "age", required = false, defaultValue = "0") int age,
			@RequestParam(value = "categorySeq", required = false, defaultValue = "0") int categorySeq,
			@RequestParam(value = "areaSido", required = false, defaultValue = "") String areaSido,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "colName", required = false, defaultValue = "reg_date") String colName,
			@RequestParam(value = "sort", required = false, defaultValue = "desc") String sort,
			@RequestParam(value = "reportCount", required = false, defaultValue = "") boolean reportCount,
			@RequestParam(value = "blindCount", required = false, defaultValue = "") boolean blindCount,
			HttpSession session, Model model) {

		List<Bbs> list = null;
		int count = 0;
		User uu =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		
		list = bbsDao.getVBbsList(site,uu.getSite(),userType,categorySeq,gender, age, keyword, areaSido, reportCount,blindCount,startDate, endDate, colName, sort, page, ITEM_COUNT_PER_PAGE);
		count = bbsDao.getCount(site,uu.getSite(),userType,categorySeq,gender, age, keyword, areaSido, reportCount,blindCount,startDate, endDate);

		for(int i=0;i<list.size();i++){
			Bbs bbs = list.get(i);
			String file = bbs.getFiles();
			if(!file.equals("")){
				String [] arr = file.split(",");
				ArrayList<String>fileList = new ArrayList<>();
				for(int k=0;k<arr.length;k++){
				
					fileList.add(arr[k]);
				}
				bbs.setFileList(fileList);
				list.set(i, bbs);
			}
		}
		
		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,PAGE_COUNT_PER_PAGING);
		
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/talk/talk_list";
	}

	// 토크 신고 해체처리, 블라인드 처리
	@RequestMapping("/admin/talk/talk_edit_do.go")
	public String talkEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "bbsSeq", required = false, defaultValue = "0") int bbsSeq,
			@RequestParam(value = "bbsCommentSeq", required = false, defaultValue = "0") int bbsCommentSeq,
			@RequestParam(value = "blindCount", required = false, defaultValue = "0") int blindCount,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			String userId="";
			if(bbsSeq>0){

				Bbs bbs = bbsDao.getBbs2(bbsSeq);
				// bbs.setReportCount(reportCount);
				bbs.setBlindCount(blindCount);
				bbsDao.updateBbs(bbs);
				System.out.println(blindCount);
				
				userId=bbs.getUserId();
				List<Bbs> list = bbsDao.getBbsShareList(bbsSeq);

				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						Bbs bbsShare = (Bbs) list.get(i);
						bbsShare.setBlindCount(blindCount);
						bbsDao.updateBbs(bbsShare);
					}
				}

				result = true;
				message = "처리 되었습니다.";
			}else{
				BbsComment bc = bbsCommentDao.getBbsComment(bbsCommentSeq);
				bc.setDislikeCount(blindCount);
				bbsCommentDao.updateblindBbsComment(bc);
				userId=bc.getUserId();
				result = true;
				message = "처리 되었습니다.";
			}
			
			if (blindCount == 1) {
				User user=userDao.getUsers(userId);
				Point pp =pointDao.getSitePoint(PointMoney.JOIN_BAD,user.getSite());
				
				if(pp!=null){
					int point = pp.getPoint(); //포인트
					int money = pp.getMoney(); //머니
					levelService.minusDb(userId, PointMoney.JOIN_BAD, money, point,"");
				}
				
				Message msg = new Message();
				msg.setUserId(userId);
				msg.setMessageType(Push.MSG_TYPE_BBS_BLINED);
				msg.setKeySeq(String.valueOf(bbsSeq));
				msg.setContents("블라인드 처리되어 경험치가 차감되었습니다.");
				msg.setWriteId("admin");
				messageDao.addMessage(msg);
			}


		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("bbsSeq", bbsSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 토크 상세보기
	@RequestMapping("/admin/talk/talk_view.go")
	public String talkViewController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "bbsSeq", required = false, defaultValue = "1") int bbsSeq,
			HttpSession session, Model model) {

		List<BbsComment> list = null;
		List<BbsCategory> catelist = null;
		int count = 0;
		//List commentList = new ArrayList(); 
		List fileList = new ArrayList();
		List fileList2 = new ArrayList();
		Bbs bbs = null;
	

		if (bbsSeq == 0) {
			bbs = new Bbs();
		} else {
			
			bbs = bbsDao.getBbs(bbsSeq);
			
			String file = bbs.getFiles();
			if(!file.equals("")){
				String [] arr = file.split(",");
				for(int k=0;k<arr.length;k++){
					fileList.add(arr[k]);
				}
			}
			
			if(bbs!=null){
				String userid =bbs.getUserId();
				bbs = bbsDao.getBbsView(bbsSeq,userid);
			}
			
			if(bbs!=null){
				if(bbs.getShareSeq()>0){
					Bbs sbbs = bbsDao.getBbs(bbs.getShareSeq());
					file = sbbs.getFiles();
					if(!file.equals("")){
						String [] arr = file.split(",");
						for(int k=0;k<arr.length;k++){
							fileList2.add(arr[k]);
						}
					}
					
				}
			}
		}	
		catelist = bbsCategoryDao.getBbsCategoryList();
		list = bbsCommentDao.getCommentList(bbsSeq, page, ITEM_COUNT_PER_PAGE);
		count = bbsCommentDao.getCommentCount(bbsSeq);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);
		model.addAttribute("bbs", bbs);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);
		model.addAttribute("cate", catelist);
		model.addAttribute("fileLists", fileList);
		model.addAttribute("fileList2", fileList2);
		return "admin/talk/talk_view";
	}


	// 토크 관리 > 신고목록
	@RequestMapping("/admin/talk/register.go")
	public String registerController(HttpSession session, Model model) {

		List location = sidoDao.getSidoList();

		model.addAttribute("location", location);

		return "/admin/talk/register";
	}

	@RequestMapping("/admin/talk/register_list.go")
	public String registerListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "fncReason", required = false, defaultValue = "0") int fncReason,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "sort", required = false, defaultValue = "desc") String sort,
			@RequestParam(value = "colname", required = false, defaultValue = "") String colname,
			@RequestParam(value = "startDate", required = false, defaultValue = "desc") String startDate,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			HttpSession session, Model model) {

		List<BbsFnc> list = null;
		int count = 0;
		User uu =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		
		list = bbsFncDao.getadBbsFncList(uu.getSite(),userType,startDate,endDate,fncReason,keyword,sort,colname,page, ITEM_COUNT_PER_PAGE);
		count = bbsFncDao.getadCount(uu.getSite(),userType,startDate,endDate,fncReason, keyword);
		
		for(int i=0;i<list.size();i++){
			BbsFnc bf= list.get(i);
			if(bf.getType()==1){//본글신고면
				Bbs bbs =bbsDao.getupdateBbs(bf.getBbsSeq());
				String file = bbs.getFiles();
				if(!file.equals("")){
					String [] arr = file.split(",");
					ArrayList<String>fileList = new ArrayList<>();
					for(int k=0;k<arr.length;k++){
					
						fileList.add(arr[k]);
					}
					bbs.setFileList(fileList);
				
				}
				bf.setBbs(bbs);
			}else{
				BbsComment bc = bbsCommentDao.getBbsComment(bf.getBbsCommentSeq());
				bf.setBbsComment(bc);
			}
			list.set(i, bf);
		}
		
		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "/admin/talk/register_list";
	}
	
	// 채팅 관리 > 신고목록
		@RequestMapping("/admin/chat/chat_register.go")
		public String chatregisterController(HttpSession session, Model model) {

			List location = sidoDao.getSidoList();

			model.addAttribute("location", location);

			return "/admin/chat/chat_register";
		}

		@RequestMapping("/admin/chat/chat_register_list.go")
		public String chatregisterListController(
				@RequestParam(value = "page", required = false, defaultValue = "1") int page,
				@RequestParam(value = "fncReason", required = false, defaultValue = "0") int fncReason,
				@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
				@RequestParam(value = "sort", required = false, defaultValue = "desc") String sort,
				@RequestParam(value = "colname", required = false, defaultValue = "") String colname,
				@RequestParam(value = "startDate", required = false, defaultValue = "desc") String startDate,
				@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
				HttpSession session, Model model) {

			List<BbsFnc> list = null;
			int count = 0;
			User uu =userDao.getUsers((String)session.getAttribute("USER_ID"));
			int userType=(int)session.getAttribute("USER_TYPE");
			
			list = userFncDao.getUserFncList(uu.getSite(),userType,startDate,endDate,fncReason,keyword,sort,colname,page, ITEM_COUNT_PER_PAGE);
			count = userFncDao.getUserFncCount(uu.getSite(),userType,startDate,endDate,fncReason, keyword);
			
			
			
			// 페이징
			String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
					PAGE_COUNT_PER_PAGING);

			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("keyword", keyword);
			model.addAttribute("currentPage", page);
			return "/admin/chat/chat_register_list";
		}
	
	// 토크 관리 > 토크목록
	@RequestMapping("/admin/talk/subject.go")
	public String subController(
			@RequestParam(value = "categorySeq", required = false, defaultValue = "0") int categorySeq,
			HttpSession session, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		List<BbsCategory> general = null;

		general = bbsCategoryDao.getadminBbsCategoryList();

		model.addAttribute("general", general);

		return "/admin/talk/subject";
	}

	// 토크 관리 > 카테고리 이동
	@RequestMapping("/admin/talk/cate.go")
	public String cateController(
			@RequestParam(value = "cate", required = false, defaultValue = "0") int cate,
			@RequestParam(value = "bbsSeq", required = false, defaultValue = "0") int bbsSeq,
			HttpSession session, Model model, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			boolean chk = bbsDao.updateCate(bbsSeq, cate); // 본글 카테고리이동

			List<BbsComment> list = null;
			list = bbsCommentDao.getCommentList(bbsSeq); // 본글에 대한 댓글리스트를 가져와서

			if (list.size() > 0) {
				Iterator<BbsComment> it = list.iterator();
				while (it.hasNext()) {
					int bbscseq = (it.next()).getBbsCommentSeq();// 그댓글의 카테고리도변환
					bbsCommentDao.updateComment(bbscseq, Integer.toString(cate));

				}
			}
			if (chk) {
				map.put("result", true);
				map.put("message", "수정되었습니다");
			} else {
				map.put("result", false);
				map.put("message", "실패하였습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 토크 주제 등록,수정의 처리
	@RequestMapping("/admin/talk/subject_edit_do.go")
	public String categoryEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "categorySeq", required = false, defaultValue = "0") int categorySeq,
			@RequestParam(value = "categoryName", required = false, defaultValue = "0") String categoryName,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			if (categorySeq == 0) {

				BbsCategory bbsCategory = new BbsCategory();
				bbsCategory.setCategoryName(categoryName);
				int categorySort = bbsCategoryDao.getLastSeq();
				bbsCategory.setCategorySort(categorySort + 1);
				bbsCategoryDao.addBbsCategory(bbsCategory);

				result = true;
				message = "추가되었습니다.";

			} else {
				BbsCategory bbsCategory = bbsCategoryDao
						.getBbsCategory(categorySeq);
				bbsCategory.setCategoryName(categoryName);
				bbsCategoryDao.updateBbsCategory(bbsCategory);
				result = true;
				message = "수정되었습니다.";
			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 토크 주제토크 주제 삭제
	@RequestMapping("/admin/talk/subject_delete_do.go")
	public String subjectDeleteDoController(@RequestParam int categorySeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 토크 주제 삭제
			bbsCategoryDao.deleteBbsCategory(categorySeq);
			;

			map.put("message", "토크주제가 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "토크주제가 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 관리 > 앨범목록
	@RequestMapping("/admin/album/album.go")
	public String albumController(HttpSession session, Model model) {
		List location = sidoDao.getSidoList();

		model.addAttribute("location", location);
		return "/admin/album/album";
	}

	// 토크 관리 > 앨범목록 리스트
	@RequestMapping("/admin/album/album_list.go")
	public String albumListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,

			@RequestParam(value = "gender", required = false, defaultValue = "0") int gender,
			@RequestParam(value = "age", required = false, defaultValue = "0") int age,
			@RequestParam(value = "areaSido", required = false, defaultValue = "") String areaSido,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<Album> list = null;
		int count = 0;

		User uu =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		
		list = bbsFilesDao.getAlbumList(uu.getSite(),userType,gender, age, keyword, areaSido, page,
				ITEM_COUNT_PER_PAGE);
		count = bbsFilesDao.getAlbumCount(uu.getSite(),userType,gender, age, keyword, areaSido);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);

		return "admin/album/album_list";
	}

	// 채팅 목록
	@RequestMapping("/admin/chat/chat.go")
	public String chatController(
			HttpSession session, Model model) {

		List areaList = sidoDao.getSidoList();
		model.addAttribute("areaList", areaList);
		return "/admin/chat/chat";
	}

	// 채팅 목록 리스트

	@RequestMapping("/admin/chat/chat_list.go")
	public String chatListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "age", required = false, defaultValue = "") String age,
			@RequestParam(value = "gender", required = false, defaultValue = "") String gender,
			@RequestParam(value = "area", required = false, defaultValue = "") String area,
			HttpSession session, Model model) {

		List<ChatRoom> list = null;
		int count = 0;

		// if (keyword.equals("")) {
		//
		// list = noticeDao.getNoticeMainList(page, ITEM_COUNT_PER_PAGE);
		// count = noticeDao.getNoticeMainCount();
		//
		// } else {
		//
		// list = noticeDao.getNoticeMainList(keyword, page,
		// ITEM_COUNT_PER_PAGE);
		// count = noticeDao.getNoticeMainCount(keyword);
		// }
		//
		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/chat/chat_list";
	}

	// 채팅방 개설
	@RequestMapping("/admin/chat/advice.go")
	public String adviceController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {
		int count = 0;

		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("paging", paging);

		return "/admin/chat/advice";
	}

	// 채팅방 개설 리스트

	@RequestMapping("/admin/chat/advice_list.go")
	public String adviceListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<Notice> list = null;
		int count = 0;
		int notiType = 0;

		// if (keyword.equals("")) {
		//
		// list = noticeDao.getNoticeMainList(page, ITEM_COUNT_PER_PAGE);
		// count = noticeDao.getNoticeMainCount();
		//
		// } else {
		//
		// list = noticeDao.getNoticeMainList(keyword, page,
		// ITEM_COUNT_PER_PAGE);
		// count = noticeDao.getNoticeMainCount(keyword);
		// }
		//
		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/chat/advice_list";
	}
	
	// 채팅방 지우기
	@RequestMapping("/admin/chat_room_delete.go")
		public String chatDeleteController(
				@RequestParam(value = "chatRoomSeq", required = false, defaultValue = "0") int chatRoomSeq,
				HttpSession session, Model model,HttpServletResponse res) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			try{
				ChatReq cReq = new ChatReq();
				cReq.setMemberID("");
				cReq.setReqTYPE("12");
				cReq.setRoomIDX(chatRoomSeq);
				chatReqDao.addChatReq(cReq);
				
				map.put("result", true);
				map.put("message", "삭제되었습니다.");
			}catch(Exception e){
				map.put("result", false);
				map.put("message", e.getMessage());
			}
			
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, jsonObject);
			return null;
	}
	// 공지사항
	@RequestMapping("/admin/notice/notice.go")
	public String noticeController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {
		
		return "/admin/notice/notice";
	}

	// 공지사항 리스트

	@RequestMapping("/admin/notice/notice_list.go")
	public String noticeListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<Notice> list = null;
		int count = 0;
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		
		list = noticeDao.getNoticeMainList(user.getSite(),userType,keyword, page,ITEM_COUNT_PER_PAGE);
		count = noticeDao.getNoticeMainCount(user.getSite(),userType,keyword);
		

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/notice/notice_list";
	}

	// 공지사항 등록/수정
	@RequestMapping("/admin/notice/notice_edit.go")
	public String noticeEditController(
			@RequestParam(value = "noticeSeq", required = false, defaultValue = "0") int noticeSeq,
			Model model,HttpSession session) {

		Notice notice = null;
		if (noticeSeq == 0) {
			notice = new Notice();
		} else {
			notice = noticeDao.getNotice(noticeSeq);
		}
		List<Site>list =siteDao.getSiteList();
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		model.addAttribute("user", user);
		model.addAttribute("notice", notice);
		model.addAttribute("list", list);
		return "admin/notice/notice_edit";
	}

	// 공지사항 수정의 처리
	@RequestMapping("/admin/notice/notice_edit_do.go")
	public String noticeEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "noticeSeq", required = false, defaultValue = "0") int noticeSeq,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			@RequestParam(value = "notiType", required = false, defaultValue = "0") int notiType,
			@RequestParam(value = "sendPush", required = false, defaultValue = "0") int sendPush,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "ir1", required = false, defaultValue = "") String contentsHtml,
			@RequestParam(value = "ir1_text", required = false, defaultValue = "") String contentsText,
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		List<User> list = null;
		String pushMessage ="";
		try {
			if (noticeSeq == 0) {
				Notice notice = new Notice();
				notice.setUserId(userId);
				notice.setTitle(title);
				notice.setStartDate(startDate);
				notice.setEndDate(endDate);
				notice.setNotiType(notiType);
				notice.setSendPush(sendPush);
				notice.setContentsHtml(contentsHtml);
				notice.setContentsText(contentsText);
				notice.setSite(site);
				noticeDao.addNotice(notice);
				noticeSeq = noticeDao.getLastSeq();

				pushMessage = "공지사항 글이 등록되었습니다.";
				

				result = true;
				message = "등록되었습니다.";
				
				list = userDao.getUserList(site);
				
				for (int i = 0; i < list.size(); i++) {
					User uu = list.get(i);
					if(sendPush==1){
						push(uu.getUserId(), noticeSeq, pushMessage, Push.MSG_TYPE_SEND_NOTICE, userId);
					}
				}
			
			} else {
				Notice notice = noticeDao.getNotice(noticeSeq);

				notice.setUserId(userId);
				notice.setTitle(title);
				notice.setStartDate(startDate);
				notice.setEndDate(endDate);
				notice.setNotiType(notiType);
				notice.setSendPush(sendPush);
				notice.setContentsHtml(contentsHtml);
				notice.setContentsText(contentsText);
				notice.setSite(site);
				noticeDao.updateNotice(notice);
				result = true;
				message = "수정되었습니다.";
				pushMessage = "공지사항이 수정되었습니다.";
			}
			
			
		
			
			
		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("noticeSeq", noticeSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 공지사항 삭제
	@RequestMapping("/admin/notice/notice_delete_do.go")
	public String noticeDeleteDoController(@RequestParam int noticeSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 게시물 삭제
			noticeDao.deleteNotice(noticeSeq);

			map.put("message", "게시물이 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "게시물이 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 팝업 공지
	@RequestMapping("/admin/popup/popup.go")
	public String popup(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/popup/popup";
	}

	// 팝업 공지 리스트
	@RequestMapping("/admin/popup/popup_list.go")
	public String popupListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			// @RequestParam(value = "keyword", required = false, defaultValue =
			// "") String keyword,
			HttpSession session, Model model) {

		List<Notice> list = null;
		int count = 0;
		int notiType = 10;
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		
		list = noticeDao.getNoticePopUpList(user.getSite(),userType,page, ITEM_COUNT_PER_PAGE, notiType);
		count = noticeDao.getNoticePopUpCount(user.getSite(),userType,notiType);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		// model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/popup/popup_list";
	}

	// // 팝업공지 등록/수정
	@RequestMapping("/admin/popup/popup_edit.go")
	public String popupEditController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			Model model,HttpSession session) {

		Notice notice = null;
		if (seq == 0) {
			notice = new Notice();
		} else {
			notice = noticeDao.getNotice(seq);
		}
		List<Site>list=siteDao.getSiteList();
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		model.addAttribute("user", user);
		model.addAttribute("notice", notice);
		model.addAttribute("list", list);
		return "admin/popup/popup_edit";
	}

	// 팝업광고 수정의 처리
	@RequestMapping("/admin/popup/popup_edit_do.go")
	public String popupEditDoController(HttpServletRequest req,
			HttpServletResponse res, Model model) throws Exception {

		req.setCharacterEncoding("utf-8");

		int fileMaxBiteSize = FILE_MAX_SIZE * 1024 * 1024;
		File file = null;

		FILE_PATH = "/files/popup/";
		FILE_LOCAL_PATH = FILE_ROOT + FILE_PATH;

		try {
			// 파일 업로드.
			// 폼에서 가져온 인자값을 얻기 위해 request 객체 전달, 업로드 경로, 파일 최대 크기, 한글처리, 파일 중복처리
			MultipartRequest multi = new MultipartRequest(req, FILE_LOCAL_PATH, fileMaxBiteSize, "UTF-8", new DefaultFileRenamePolicy());

			// 폼에서 입력한 값을 가져옴
			int seq = Integer.parseInt(F.nullCheck(multi.getParameter("seq"),"0"));
			String linkUrl = F.nullCheck(multi.getParameter("linkUrl"), "");
			int visible = Integer.parseInt(F.nullCheck(multi.getParameter("visible"), "0"));
			String startDate = F.nullCheck(multi.getParameter("startDate"), "");
			String endDate = F.nullCheck(multi.getParameter("endDate"), "");
			String backgroundColor = F.nullCheck(multi.getParameter("backgroundColor"), "");
			String sido = F.nullCheck(multi.getParameter("areaSido"), "");
			String gugun = F.nullCheck(multi.getParameter("areaGugun"), "");
			String site = F.nullCheck(multi.getParameter("site"), "");
			String delfile =F.nullCheck(multi.getParameter("delfile"),"");
			String MAIN_IMG = "";
			String THUMB_IMG = "";
			if(!delfile.equals("")){
				filedelete(delfile);
			}
			
			// 업로드한 파일들을 Enumeration 타입으로 반환.
			// Enumeration형은 데이터를 뽑아올때 유용한 인터페이스
			Enumeration files = multi.getFileNames();
			
			String sName = "";

			while (files.hasMoreElements()) {
				String elementName = (String) files.nextElement();

				file = multi.getFile(elementName);
				int i = 0;
				if (file != null) {
					if (elementName.equals("imgFile")) {
						String sPath = file.getParent() + "/";
						sName = file.getName();
					}
				}
			}

			boolean result = true;
			String message = "";

			if (seq == 0) {
				Notice notice = new Notice();
				// notice.setLinkUrl(linkUrl);
				// notice.setBackgroundColor(backgroundColor);
				notice.setStartDate(startDate);
				notice.setEndDate(endDate);
				notice.setVisible(visible);
				notice.setSite(site);
				notice.setNotiType(10);
				notice.setContentsHtml(FILE_PATH + sName);
				noticeDao.addNotice(notice);
				seq = noticeDao.getLastSeq();
				result = true;
				message = "등록되었습니다.";

			} else {
				Notice notice = noticeDao.getNotice(seq);
				if (sName.equals("") == false) {
					notice.setContentsHtml(FILE_PATH + sName);
				}
				// notice.setLinkUrl(linkUrl);
				// notice.setBackgroundColor(backgroundColor);
				notice.setStartDate(startDate);
				notice.setEndDate(endDate);
				notice.setSite(site);
				notice.setVisible(visible);
				notice.setNotiType(10);
				noticeDao.updateNotice(notice);
				result = true;
				message = "수정되었습니다.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// return "redirect:/admin/notice_manager.go";
		return "redirect:/admin/popup/popup.go";
	}

	// 팝업광고 이미지 삭제
	@RequestMapping("/admin/popup/popup_file_delete_do.go")
	public String popupFileDeleteDoController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "imgFile", required = false, defaultValue = "") String imgFile,
			HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		FILE_PATH = "/files/popup/";
		FILE_LOCAL_PATH = FILE_ROOT + FILE_PATH;

		File file = new File(FILE_LOCAL_PATH + imgFile);
		file.delete();

		Notice notice = noticeDao.getNotice(seq);
		notice.setContentsHtml("");
		noticeDao.updateNotice(notice);

		map.put("result", result);
		map.put("message", "삭제되었습니다.");

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 팝업광고 삭제
	@RequestMapping("/admin/popup/popup_delete_do.go")
	public String popupDeleteDoController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "imgFile", required = false, defaultValue = "") String imgFile,
			HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		FILE_PATH = "/files/popup/";
		FILE_LOCAL_PATH = FILE_ROOT + FILE_PATH;

		File file = new File(FILE_LOCAL_PATH + imgFile);
		file.delete();

		Notice notice = noticeDao.getNotice(seq);
		notice.setContentsHtml("");
		noticeDao.deleteNotice(seq);

		map.put("result", result);
		map.put("message", "삭제되었습니다.");

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 업그레이드 공지사항
	@RequestMapping("/admin/update/update.go")
	public String update(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/update/update";
	}

	// 업그레이드 공지사항 리스트

	@RequestMapping("/admin/update/update_list.go")
	public String updateListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<Notice> list = null;
		int count = 0;
		int notiType = 20;

		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
			list = noticeDao.getNoticeMainList(user.getSite(),userType,keyword, page,ITEM_COUNT_PER_PAGE, notiType);
			count = noticeDao.getNoticeMainCount(user.getSite(),userType,keyword, notiType);

		
		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/update/update_list";
	}

	// 업그레이드 등록/수정
	@RequestMapping("/admin/update/update_edit.go")
	public String updateEditController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			Model model,HttpSession session) {

		Notice notice = null;
		if (seq == 0) {
			notice = new Notice();
		} else {
			notice = noticeDao.getNotice(seq);
		}
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		List list= siteDao.getSiteList();
		model.addAttribute("user", user);
		model.addAttribute("notice", notice);
		model.addAttribute("list", list);
		return "admin/update/update_edit";
	}

	// 업그레이드 수정의 처리
	@RequestMapping("/admin/update/update_edit_do.go")
	public String updateEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "notiType", required = false, defaultValue = "0") int notiType,
			@RequestParam(value = "sendPush", required = false, defaultValue = "0") int sendPush,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			@RequestParam(value = "linkUrl", required = false, defaultValue = "") String linkUrl,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			if (seq == 0) {
				Notice notice = new Notice();
				notice.setUserId(userId);
				notice.setTitle(title);
				// notice.setStartDate(startDate);
				notice.setSite(site);
				notice.setNotiType(20);
				notice.setSendPush(sendPush);
				notice.setLinkUrl(linkUrl);
				// notice.setSendPush(sendPush);
				// notice.setContentsHtml(contentsHtml);
				// notice.setContentsText(contentsText);
				noticeDao.addNotice(notice);
				seq = noticeDao.getLastSeq();

				result = true;
				message = "등록되었습니다.";
			} else {
				Notice notice = noticeDao.getNotice(seq);

				notice.setUserId(userId);
				notice.setTitle(title);
				// notice.setStartDate(startDate);
				notice.setSite(site);
				notice.setNotiType(20);
				notice.setSendPush(sendPush);
				notice.setLinkUrl(linkUrl);
				// notice.setContentsHtml(contentsHtml);
				// notice.setContentsText(contentsText);
				noticeDao.updateNotice(notice);
				result = true;
				message = "수정되었습니다.";
			}
		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("seq", seq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 업그레이드 삭제
	@RequestMapping("/admin/update/update_delete_do.go")
	public String updateDeleteDoController(@RequestParam int seq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 게시물 삭제
			noticeDao.deleteNotice(seq);

			map.put("message", "업그레이드 공지가 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "업그레이드 공지가 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 이벤트 관리
	@RequestMapping("/admin/event/event.go")
	public String eventController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/event/event";
	}

	// 이벤트 리스트

	@RequestMapping("/admin/event/event_list.go")
	public String eventListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<Event> list = null;
		int count = 0;
		int notiType = 0;

		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
		String todayData = today.format(date);

		
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		list = eventDao.getEventList(user.getSite(),userType,keyword, page, ITEM_COUNT_PER_PAGE);
		count = eventDao.getCount(user.getSite(),userType,keyword);


		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);
		// 댓글리스트

		model.addAttribute("todayData", todayData);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/event/event_list";
	}

	// 이벤트 관리 등록/수정
	@RequestMapping("/admin/event/event_edit.go")
	public String eventEditController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			Model model,HttpSession session ) {

		Event event = null;
		EventComment eventComment = null;

		if (seq == 0) {
			event = new Event();
		} else {
			event = eventDao.getEvent(seq);
			eventComment = eventCommentDao.getEvent(seq);
		}

		List commentList = eventCommentDao.getEventCommentList(seq, page,
				ITEM_COUNT_PER_PAGE);
		int bbsCount = eventCommentDao.getCount(seq);

		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		List list= siteDao.getSiteList();
		model.addAttribute("user", user);
		model.addAttribute("list", list);
		
		model.addAttribute("eventComment", eventComment);
		model.addAttribute("commentList", commentList);
		model.addAttribute("event", event);

		return "admin/event/event_edit";
	}

	// 이벤트 관리 수정의 처리
	@RequestMapping("/admin/event/event_edit_do.go")
	public String eventEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			@RequestParam(value = "notiType", required = false, defaultValue = "0") int notiType,
			@RequestParam(value = "sendPush", required = false, defaultValue = "0") int sendPush,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "ir1", required = false, defaultValue = "") String contentsHtml,
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			@RequestParam(value = "ir1_text", required = false, defaultValue = "") String contentsText,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			if (seq == 0) {
				Event event = new Event();
				event.setUserId(userId);
				event.setTitle(title);
				event.setStartDate(startDate);
				event.setEndDate(endDate);
				// event.setNotiType(notiType);
				event.setSendPush(sendPush);
				event.setContentsHtml(contentsHtml);
				event.setContentsText(contentsText);
				event.setSite(site);
				eventDao.addEvent(event);
				seq = eventDao.getLastSeq();

				result = true;
				message = "등록되었습니다.";
				
				List<User> list = userDao.getUserList(site);
				if(sendPush==1){
					for (int i = 0; i < list.size(); i++) {
						User uu = list.get(i);
						
						push(uu.getUserId(), seq, "새로운 이벤트가 등록되었습니다.", Push.MSG_TYPE_EVENT, userId);
						/*Push push = new Push();
						push.setBadge(1);
						push.setOs(uu.getOsType());
						push.setPushKey(uu.getPushkey());
						push.setMsgType(Push.MSG_TYPE_EVENT);
						push.setUserid(uu.getUserId());
						push.setStatus(0);
						push.setServiceId(site);
						push.setPushType(1); // keyword.getKeyType()
						push.setMsg();
						push.setMsgKey(String.valueOf(seq));
						pushDao.addPush(push);*/

					}
				}
			} else {
				Event event = eventDao.getEvent(seq);

				event.setUserId(userId);
				event.setTitle(title);
				event.setStartDate(startDate);
				event.setEndDate(endDate);
				// event.setNotiType(notiType);
				event.setSendPush(sendPush);
				event.setContentsHtml(contentsHtml);
				event.setContentsText(contentsText);
				event.setSite(site);
				eventDao.updateEvent(event);
				result = true;
				message = "수정되었습니다.";
			}
		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("seq", seq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 이벤트 댓글 등록
	@RequestMapping("/admin/event/event_comment_edit.go")
	public String QnaEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "bbsCommentSeq", required = false, defaultValue = "0") int bbsCommentSeq,
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			if (bbsCommentSeq == 0) {

				EventComment eventComment = new EventComment();
				eventComment.setUserId(userId);
				eventComment.setBbsSeq(seq);
				eventComment.setBbsContents(comment);
				// eventComment.setrCommentSeq(1);
				eventCommentDao.addBbsComment(eventComment);
				// seq = eventCommentDao.getLastSeq();

				result = true;
				message = "등록되었습니다.";

			} else {

				EventComment eventComment = eventCommentDao.getEventComment(bbsCommentSeq);
				eventComment.setUserId(userId);
				eventComment.setBbsSeq(seq);
				eventComment.setBbsContents(comment);

				// 수정

				eventCommentDao.updateEventCommentContents(eventComment);

				result = true;
				message = "댓글이 수정 되었습니다.";
			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("seq", seq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 이벤트 관리 삭제
	@RequestMapping("/admin/event/event_delete_do.go")
	public String eventDeleteDoController(@RequestParam int seq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			// 게시물 삭제
			eventDao.deleteEvent(seq);

			// 게시물에 속한 댓글 삭제
			eventCommentDao.deleteEventCommentAll(seq);

			map.put("message", "이벤트가 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "이벤트가 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 이벤트 댓글 삭제
	@RequestMapping("/admin/event/comment_delete_do.go")
	public String commentDeleteDoController(@RequestParam int bbsCommentSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			// 게시물에 속한 댓글 삭제
			eventCommentDao.deleteEventComment(bbsCommentSeq);

			map.put("message", "댓글이 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "댓글이 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 1:1문의 관리
	@RequestMapping("/admin/inquiry/inquiry.go")
	public String inquiryController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/inquiry/inquiry";
	}

	// 1:1문의 관리 리스트
	@RequestMapping("/admin/inquiry/inquiry_list.go")
	public String inquiryListController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<Qna> list = null;
		int count = 0;
		int noticeseq = 0;

		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		
		list = qnaDao.getQnaList(user.getSite(),userType,keyword, page, ITEM_COUNT_PER_PAGE);
		count = qnaDao.getCount(keyword);
		

		// 페이징
		String paging = Paging.getPaging2(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		// 접수, 답변완료 seq 값 가지고 오기.
		if (list != null) {
			Iterator<Qna> it = list.iterator();
			while (it.hasNext()) {
				noticeseq = (it.next()).getNoticeSeq();
			}
		}

		// 댓글리스트
		List commentList = qnaDao.getQnaList(noticeseq, page,
				ITEM_COUNT_PER_PAGE);

		model.addAttribute("commentList", commentList);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/inquiry/inquiry_list";
	}

	// 1:1문의 관리 등록/수정
	@RequestMapping("/admin/inquiry/inquiry_edit.go")
	public String inquiryEditController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			Model model) {

		Qna qna = null;
		if (seq == 0) {
			qna = new Qna();
		} else {
			qna = qnaDao.getQna(seq);
		}

		// 댓글리스트
		List commentList = qnaDao.getQnaList(seq, page, ITEM_COUNT_PER_PAGE);
		// int bbsCount = bbsCommentDao.getCount(seq);

		model.addAttribute("commentList", commentList);
		model.addAttribute("qna", qna);

		return "admin/inquiry/inquiry_edit";
	}

	// 1:1문의 수정의 처리
	@RequestMapping("/admin/inquiry/inquiry_edit_do.go")
	public String inquiryEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "notiType", required = false, defaultValue = "0") int notiType,
			@RequestParam(value = "answer", required = false, defaultValue = "0") int answer,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "ir1", required = false, defaultValue = "") String contentsHtml,
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			@RequestParam(value = "ir1_text", required = false, defaultValue = "") String contentsText,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			HttpServletResponse res, Model model,HttpSession ss) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			if (seq == 0) {
				Qna qna = new Qna();
				qna.setUserId(userId);
				qna.setTitle(title);
				qna.setContentsHtml(contentsHtml);
				qna.setContentsText(contentsText);
				qna.setAnswer(answer);
				qna.setSite(site);
				qnaDao.addQna(qna);
				seq = qnaDao.getLastSeq();

				result = true;
				message = "등록되었습니다.";
			} else {
				Qna qna = qnaDao.getQna(seq);

				qna.setUserId(userId);
				qna.setTitle(title);
				qna.setContentsHtml(contentsHtml);
				qna.setContentsText(contentsText);
				qna.setAnswer(answer);
				qnaDao.updateCommentQna(qna);
				result = true;
				message = "수정되었습니다.";
				
				if(!comment.equals("")){
				
					qna = new Qna();
					qna.setrCommentSeq(seq);
					qna.setTitle(comment);
					qna.setContentsHtml(comment);
					qna.setUserId((String)ss.getAttribute("USER_ID"));
					qna.setrLevel(1);
					qnaDao.addQna(qna);
				} 	
					Message messageInput = new Message();
					messageInput.setUserId(userId);
					messageInput.setMessageType("12");
					messageInput.setKeySeq(String.valueOf(seq));
					messageInput.setContents("문의하신 질문에 대한 답변이 등록되었습니다");
					messageInput.setWriteId((String)ss.getAttribute("USER_ID"));
					messageDao.addMessage(messageInput);
	
					User user = userDao.getUser(userId);
					Push push = new Push();
					push.setBadge(1);
					push.setOs(user.getOsType());
					push.setPushKey(user.getPushkey());
					push.setMsgType(Push.MSG_TYPE_ADMIN_COMMENT);
					push.setUserid((String)ss.getAttribute("USER_ID"));
					push.setStatus(0);
					push.setServiceId(site);
					push.setPushType(1);
					push.setMsg("문의하신 질문에 대한 답변이 등록되었습니다");
					push.setMsgKey(String.valueOf(0));
					pushDao.addPush(push);
				
			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("seq", seq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	/**
	 * 1:1문의 댓글 등록
	 */
	@RequestMapping("/admin/inquiry/inquiry_comment_edit_do.go")
	public String bbsCommentEditDoController(
			HttpSession httpSession,
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "userId", required = true, defaultValue = "") String userId,
			@RequestParam(value = "comment", required = true, defaultValue = "") String comment,
			@RequestParam(value = "userWrite", required = true, defaultValue = "") String userWrite,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			Qna qna = new Qna();
			qna.setrCommentSeq(seq);
			qna.setTitle(comment);
			qna.setContentsHtml(comment);
			qna.setUserId(userId);
			qna.setrLevel(1);
			qnaDao.addQna(qna);

			Message messageInput = new Message();
			messageInput.setUserId(userWrite);
			messageInput.setMessageType("12");
			messageInput.setKeySeq(String.valueOf(seq));
			messageInput.setContents("문의하신 질문에 대한 답변이 등록되었습니다");
			messageInput.setWriteId(userId);
			messageDao.addMessage(messageInput);

			User user = userDao.getUser(userWrite);
			Push push = new Push();
			push.setBadge(1);
			push.setOs(user.getOsType());
			push.setPushKey(user.getPushkey());
			push.setMsgType(Push.MSG_TYPE_ADMIN_COMMENT);
			push.setUserid(userWrite);
			push.setStatus(0);
			push.setServiceId(user.getSite());
			push.setPushType(1);
			push.setMsg("문의하신 질문에 대한 답변이 등록되었습니다");
			push.setMsgKey(String.valueOf(0));
			pushDao.addPush(push);

			result = true;
			message = "등록되었습니다.";

		} catch (Exception e) {
			// TODO: handle exception
			message = e.getMessage();
		}

		map.put("seq", seq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;

		

	}

	/**
	 * 1:1문의 댓글 등록
	 */
	@RequestMapping("/admin/inquiry/inquiry_modify_edit_do.go")
	public String bbsCommentmodifyEditDoController(
			HttpSession httpSession,
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "userId", required = true, defaultValue = "") String userId,
			@RequestParam(value = "comment", required = true, defaultValue = "") String comment,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			Qna qna = qnaDao.getQna(seq);
			qna.setrCommentSeq(seq);
			qna.setTitle(comment);
			qna.setContentsHtml(comment);
			qna.setUserId(userId);
			qna.setrLevel(1);
			qnaDao.updateQna(comment, seq);
			result = true;
			message = "등록되었습니다.";

		} catch (Exception e) {
			// TODO: handle exception
			message = e.getMessage();
		}

		map.put("seq", seq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;

	

	}

	// 1:1문의 관리 삭제
	@RequestMapping("/admin/inquiry/inquiry_delete_do.go")
	public String inquiryDeleteDoController(@RequestParam int seq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			// 게시물 삭제
			qnaDao.deleteQna(seq);

			// 댓글 삭제
			qnaDao.deleteQnaComment(seq);

			map.put("message", "게시물이 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "게시물이 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 1:1 댓글 삭제
	@RequestMapping("/admin/inquiry/comment_delete_do.go")
	public String inquirycommentDeleteDoController(@RequestParam int seq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			// 게시물에 속한 댓글 삭제
			qnaDao.deleteQna(seq);

			map.put("message", "댓글이 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "댓글이 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// FAQ관리
	@RequestMapping("/admin/faq/faq.go")
	public String faqController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/faq/faq";
	}

	// FAQ관리 리스트

	@RequestMapping("/admin/faq/faq_list.go")
	public String faqListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<Faq> list = null;
		int count = 0;
		int notiType = 0;

		/*if (keyword.equals("")) {

			list = faqDao.getFaqList(page, ITEM_COUNT_PER_PAGE);
			count = faqDao.getCount();

		} else {*/
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		
		list = faqDao.getFaqList(user.getSite(),userType,keyword, page, ITEM_COUNT_PER_PAGE);
		count = faqDao.getCount(user.getSite(),userType,keyword);

		//}

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/faq/faq_list";
	}

	// FAQ관리 등록/수정
	@RequestMapping("/admin/faq/faq_edit.go")
	public String faqEditController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			Model model,HttpSession session) {

		Faq faq = null;
		if (seq == 0) {
			faq = new Faq();
		} else {
			faq = faqDao.getFaq(seq);
		}
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		List list= siteDao.getSiteList();
		model.addAttribute("user", user);
		model.addAttribute("list", list);
		model.addAttribute("faq", faq);

		return "admin/faq/faq_edit";
	}

	// FAQ관리 수정의 처리
	@RequestMapping("/admin/faq/faq_edit_do.go")
	public String faqEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			@RequestParam(value = "ir1", required = false, defaultValue = "") String contentsHtml,
			@RequestParam(value = "ir1_text", required = false, defaultValue = "") String contentsText,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			if (seq == 0) {
				Faq faq = new Faq();
				faq.setUserId(userId);
				faq.setTitle(title);
				// faq.setNotiType(notiType);
				faq.setContentsHtml(contentsHtml);
				faq.setContentsText(contentsText);
				faq.setSite(site);
				faqDao.addFaq(faq);
				seq = faqDao.getLastSeq();

				result = true;
				message = "등록되었습니다.";
			} else {
				Faq faq = faqDao.getFaq(seq);

				faq.setUserId(userId);
				faq.setTitle(title);
				// faq.setNotiType(notiType);
				faq.setContentsHtml(contentsHtml);
				faq.setContentsText(contentsText);
				faq.setSite(site);
				faqDao.updateFaq(faq);
				result = true;
				message = "수정되었습니다.";
			}
		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("seq", seq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// FAQ관리 관리 삭제
	@RequestMapping("/admin/faq/faq_delete_do.go")
	public String faqDeleteDoController(@RequestParam int seq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 게시물 삭제
			faqDao.deleteFaq(seq);

			map.put("message", "게시물이 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "게시물이 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 포인트 환전신청관리
	@RequestMapping("/admin/point/prequire.go")
	public String pointpRequireController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/point/require";
	}

	// F-MONEY 환전신청관리
	@RequestMapping("/admin/point/frequire.go")
	public String pointfRequireController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		String startDate = "";
		String endDate = T.getToday();

		startDate = T.getDateAdd(endDate, -7);

		List location = sidoDao.getSidoList();

		model.addAttribute("location", location);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);

		return "/admin/point/require2";
	}

	// 환전신청관리리스트
	@RequestMapping("/admin/point/require_list.go")
	public String pointRequireListController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "page", required = false, defaultValue = "") int page,
			@RequestParam(value = "type", required = false, defaultValue = "") String type,
			@RequestParam(value = "colName", required = false, defaultValue = "") String colName,
			@RequestParam(value = "bankName", required = false, defaultValue = "") String bankName,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort,
			HttpSession session, Model model) {
		System.out.println(bankName);
		
		User uu =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		
		List<Expense> list = expenseDao.getExpenseList(uu.getSite(),userType,colName, sort, type,
				keyword, 0, bankName, page, ITEM_COUNT_PER_PAGE);
		int cnt = expenseDao.getExpenseCnt(uu.getSite(),userType,type, keyword, 0, bankName);
		String paging = Paging.getPaging(page, cnt, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		return "/admin/point/require_list";
	}

	
	// 팝업창
	@RequestMapping("/admin/fmoney/popup.go")
	public String pointpopUpController(
			@RequestParam(value = "noarrSeq", required = false, defaultValue = "") String[] arrSeq,
			HttpSession session, Model model, HttpServletResponse res) {

		model.addAttribute("list", arrSeq);

		return "admin/point/popup";
	}

	// 환전 불가
	@RequestMapping("/admin/point/changeNo.go")
	public String pointChangeNoController(
			@RequestParam(value = "arrSeq", required = false, defaultValue = "") String[] arrSeq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			HttpSession session, Model model, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		int Count = arrSeq.length;
		List list = new ArrayList();
		String message = "";
		boolean result = false;
		try {

			if (arrSeq != null || !(arrSeq.equals(""))) {

				for (int i = 0; i < Count; i++) {
					int expenseSeq = Integer.parseInt(arrSeq[i]);
					Expense ep = expenseDao.getExpense(expenseSeq);
					String userId = ep.getUserId();
					int point = ep.getPoint();// 신청 포인트
					expenseDao.updateExpense(expenseSeq, 2, comment);// 불가처리주고
					levelService.inserDb(userId, PointMoney.JOIN_NOCHANGE,
							+point, 0, comment);

				}
			}

			result = true;
			message = "완료되었습니다";
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

	// fmoney환전
	@RequestMapping("/admin/fmoney/changedo.go")
	public String fmoneyChangeController(
			@RequestParam(value = "arrSeq", required = false, defaultValue = "") String[] arrSeq,
			HttpSession session, Model model, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		int Count = arrSeq.length;
		List list = new ArrayList();
		String message = "";
		boolean result = false;
		try {
			
			
			if (arrSeq != null || !(arrSeq.equals(""))) {

				for (int i = 0; i < Count; i++) {
					int expenseSeq = Integer.parseInt(arrSeq[i]);
					Expense ep = expenseDao.getExpense(expenseSeq);
					String userId = ep.getUserId();
					int point = ep.getPoint();// 신청 f머니
					
					expenseDao.updateExpense(expenseSeq, 1, "환전완료");
				}

			}

			result = true;
			message = "완료되었습니다";
		} catch (Exception e) {
			result = false;
			message = e.getMessage();
		}
		map.put("faillist", list);
		map.put("result", result);
		map.put("message", message);
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 환전완료관리
	@RequestMapping("/admin/point/finish.go")
	public String pointFinishController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		String startDate = "";
		String endDate = T.getToday();

		startDate = T.getDateAdd(endDate, -7);

		List location = sidoDao.getSidoList();

		model.addAttribute("location", location);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);

		return "/admin/point/finish";
	}

	// 환전완료,불가 리스트
	@RequestMapping("/admin/point/finish_list.go")
	public String pointFinishListController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "page", required = false, defaultValue = "") int page,

			@RequestParam(value = "colName", required = false, defaultValue = "") String colName,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort,
			@RequestParam(value = "status", required = false, defaultValue = "0") int status,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			HttpSession session, Model model) {

		User uu =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		
		List<Expense> list = expenseDao.getFinishList(uu.getSite(),userType,colName, sort, keyword,
				status, startDate, endDate, page, ITEM_COUNT_PER_PAGE);
		int cnt = expenseDao.getFinishCnt(uu.getSite(),userType,keyword, status, startDate, endDate);
		String paging = Paging.getPaging(page, cnt, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);

		String str = "/admin/point/error_list";
		if (status == 1) {// 완료
			str = "/admin/point/finish_list";
		}
		return str;
	}

	@RequestMapping("/admin/point/error.go")
	public String pointerrorListController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,

			HttpSession session, Model model) {

		return "/admin/point/error";

	}

	// 포인트히스토리
	@RequestMapping("/admin/point/history.go")
	public String HistoryController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<Type> list = typeDao.getTypeList();
		model.addAttribute("typelist", list);

		return "/admin/point/history";
	}

	// 히스토리
	@RequestMapping("/admin/point/history_list.go")
	public String HistoryListController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "page", required = false, defaultValue = "") int page,
			@RequestParam(value = "type", required = false, defaultValue = "") String type,
			@RequestParam(value = "colName", required = false, defaultValue = "") String colName,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			HttpSession session, Model model) {
		
		User uu =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		
		List<PointMoney> list = pointmoneyDao.getPointMoneyList(uu.getSite(),userType,colName, sort,
				type, keyword, startDate, endDate, page, ITEM_COUNT_PER_PAGE);
		int cnt = pointmoneyDao
				.getHistoryCnt(uu.getSite(),userType,type, keyword, startDate, endDate);
		String paging = Paging.getPaging(page, cnt, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);

		return "/admin/point/history_list";
	}

	// 충전내역
	@RequestMapping("/admin/point/charge.go")
	public String ChargeController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/point/charge";
	}

	// 충전내역
	@RequestMapping("/admin/point/charge_list.go")
	public String ChargeListController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "page", required = false, defaultValue = "") int page,
			@RequestParam(value = "type", required = false, defaultValue = "") String type,
			@RequestParam(value = "colName", required = false, defaultValue = "") String colName,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			HttpSession session, Model model) {
		User uu =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		
		List list = orderDao.getOrderList(uu.getSite(),userType,colName, sort, keyword, type,
				startDate, endDate, page, ITEM_COUNT_PER_PAGE);
		int count = orderDao.getCount(uu.getSite(),userType,keyword, type, startDate, endDate);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "/admin/point/charge_list";
	}

	// fmoney 환전신청 엑셀
	@RequestMapping("/admin/point/frequire_list_excel.go")
	public ModelAndView frequireListExcelController(
			Model model,
			@RequestParam(value = "colname", required = false, defaultValue = "") String colname,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "type", required = false, defaultValue = "") String type,
			@RequestParam(value = "bankName", required = false, defaultValue = "") String bankName,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate) {

		List<Expense> list = expenseDao.getExpenseexcelList(type, keyword, 0,
				bankName, startDate, endDate, colname, sort);

		SimpleDateFormat formatdate = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "code_" + formatdate.format(new Date());
		XlsxWriter writer = new XlsxWriter(FILE_ROOT + "/files/excel/"
				+ fileName + ".xls");

		List title = new ArrayList();
		List contents = new ArrayList();
		title.add("요청번호");
		title.add("아이디");
		title.add("이름");
		title.add("Fmoney(F)");
		title.add("환전금액");
		title.add("은행");
		title.add("계좌번호");
		title.add("계좌주");
		title.add("요청 일자");

		for (int i = 0; i < list.size(); i++) {
			Expense ep = (Expense) list.get(i);

			List dataList = new ArrayList();
			dataList.add(ep.getExpenseSeq());
			dataList.add(ep.getUserId());
			dataList.add(ep.getUserName());
			dataList.add(ep.getPoint());
			dataList.add(ep.getPayPoint());
			dataList.add(ep.getBank());
			dataList.add(ep.getBankCount());
			dataList.add(ep.getOwnerName());
			dataList.add(ep.getRequestDate());

			contents.add(dataList);
		}

		writer.writeFile(title, contents);

		File file = new File(FILE_ROOT + "/files/excel/" + fileName + ".xls");

		return new ModelAndView("fileDownloadView", "file", file);
	}

	// 환전 완료
	@RequestMapping("/admin/point/point_list_excel.go")
	public ModelAndView ListExcelController(
			Model model,
			@RequestParam(value = "colname", required = false, defaultValue = "") String colname,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "bankName", required = false, defaultValue = "") String bankName,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			@RequestParam(value = "type", required = false, defaultValue = "") String type) {

		List list = expenseDao.getExpenseexcelList(type, keyword, 1, bankName,
				startDate, endDate, colname, sort);

		SimpleDateFormat formatdate = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "code_" + formatdate.format(new Date());
		XlsxWriter writer = new XlsxWriter(FILE_ROOT + "/files/excel/"
				+ fileName + ".xls");

		List title = new ArrayList();
		List contents = new ArrayList();

		title.add("유형");
		title.add("아이디");
		title.add("이름");
		title.add("머니");
		title.add("환전금액");
		title.add("요청 일자");
		title.add("환급 일자");

		for (int i = 0; i < list.size(); i++) {
			Expense ep = (Expense) list.get(i);

			List dataList = new ArrayList();
			dataList.add(ep.getTypeTxt());
			dataList.add(ep.getUserId());
			dataList.add(ep.getUserName());
			dataList.add(ep.getPoint());
			dataList.add(ep.getPayPoint());
			dataList.add(ep.getRequestDate());
			dataList.add(ep.getFinishDate());

			contents.add(dataList);
		}

		writer.writeFile(title, contents);

		File file = new File(FILE_ROOT + "/files/excel/" + fileName + ".xls");

		return new ModelAndView("fileDownloadView", "file", file);
	}

	// 환전 불가
	@RequestMapping("/admin/point/error_list_excel.go")
	public ModelAndView errorListExcelController(
			Model model,
			@RequestParam(value = "colname", required = false, defaultValue = "") String colname,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "bankName", required = false, defaultValue = "") String bankName,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			@RequestParam(value = "type", required = false, defaultValue = "") String type) {

		List list = expenseDao.getExpenseexcelList(type, keyword, 2, bankName,
				startDate, endDate, colname, sort);

		SimpleDateFormat formatdate = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "code_" + formatdate.format(new Date());
		XlsxWriter writer = new XlsxWriter(FILE_ROOT + "/files/excel/"
				+ fileName + ".xls");

		List title = new ArrayList();
		List contents = new ArrayList();

		title.add("유형");
		title.add("아이디");
		title.add("이름");
		title.add("머니");
		title.add("환전금액");
		title.add("은행");
		title.add("계좌번호");
		title.add("비고");
		title.add("요청일자");
		title.add("처리일자");

		for (int i = 0; i < list.size(); i++) {
			Expense ep = (Expense) list.get(i);

			List dataList = new ArrayList();
			dataList.add(ep.getTypeTxt());
			dataList.add(ep.getUserId());
			dataList.add(ep.getUserName());
			dataList.add(ep.getPoint());
			dataList.add(ep.getPayPoint());
			dataList.add(ep.getBank());
			dataList.add(ep.getBankCount());
			dataList.add(ep.getComment());
			dataList.add(ep.getRequestDate());
			dataList.add(ep.getFinishDate());

			contents.add(dataList);
		}

		writer.writeFile(title, contents);

		File file = new File(FILE_ROOT + "/files/excel/" + fileName + ".xls");

		return new ModelAndView("fileDownloadView", "file", file);
	}

	// 히스토리
	@RequestMapping("/admin/point/history_list_excel.go")
	public ModelAndView historyExcelController(
			Model model,
			@RequestParam(value = "colname", required = false, defaultValue = "") String colname,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "type", required = false, defaultValue = "") String type,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate) {

		List list = pointmoneyDao.getPointMoneyexcelList(type, keyword,
				startDate, endDate, colname, sort);

		SimpleDateFormat formatdate = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "code_" + formatdate.format(new Date());
		XlsxWriter writer = new XlsxWriter(FILE_ROOT + "/files/excel/"
				+ fileName + ".xls");

		List title = new ArrayList();
		List contents = new ArrayList();

		title.add("유형");
		title.add("아이디");
		title.add("이름");
		title.add("수입포인트(P)");
		title.add("수입fmoney(f)");
		title.add("지출포인트(P)");
		title.add("지출fmoney(f)");
		title.add("차후포인트(P)");
		title.add("차후fmoney(f)");
		title.add("일자");

		for (int i = 0; i < list.size(); i++) {
			PointMoney pm = (PointMoney) list.get(i);
			List dataList = new ArrayList();
			dataList.add(pm.getTypeTxt());
			dataList.add(pm.getUserId());
			dataList.add(pm.getUserName());
			dataList.add(pm.getInPoint());
			dataList.add(pm.getInMoney());
			dataList.add(pm.getOutPoint());
			dataList.add(pm.getOutMoney());
			dataList.add(pm.getRemainPoint());
			dataList.add(pm.getRemainMoney());
			dataList.add(pm.getRegDate());
			contents.add(dataList);
		}

		writer.writeFile(title, contents);

		File file = new File(FILE_ROOT + "/files/excel/" + fileName + ".xls");

		return new ModelAndView("fileDownloadView", "file", file);
	}

	// 결제내역 엑셀
	@RequestMapping("/admin/point/order_list_excel.go")
	public ModelAndView orderExcelController(
			Model model,

			@RequestParam(value = "colname", required = false, defaultValue = "") String colname,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "type", required = false, defaultValue = "") String type,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate) {

		List list = orderDao.getOrderexcelList(keyword, type, startDate,
				endDate, colname, sort);

		SimpleDateFormat formatdate = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "code_" + formatdate.format(new Date());
		XlsxWriter writer = new XlsxWriter(FILE_ROOT + "/files/excel/"
				+ fileName + ".xls");

		List title = new ArrayList();
		List contents = new ArrayList();
		title.add("번호");
		title.add("유형");
		title.add("고유코드");
		title.add("아이디");
		title.add("휴대폰번호");
		title.add("충전포인트(P)");
		title.add("결제금액(원)");
		title.add("결제일자");

		for (int i = 0; i < list.size(); i++) {
			Order or = (Order) list.get(i);
			List dataList = new ArrayList();
			dataList.add(or.getOrderSeq());
			dataList.add(or.getPaytypeText());
			dataList.add(or.getOrderTitle());
			dataList.add(or.getUserId());
			dataList.add(or.getMobile());
			dataList.add(or.getPriceSum());
			dataList.add(or.getTotalFee());
			dataList.add(or.getOrderDate());

			contents.add(dataList);
		}

		writer.writeFile(title, contents);

		File file = new File(FILE_ROOT + "/files/excel/" + fileName + ".xls");

		return new ModelAndView("fileDownloadView", "file", file);
	}

	// 이용안내 공지
	@RequestMapping("/admin/guide/guide.go")
	public String guide(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/guide/guide";
	}

	// 이용안내 공지 리스트
	@RequestMapping("/admin/guide/guide_list.go")
	public String guideListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			
			HttpSession session, Model model) {

		List<Notice> list = null;
		int count = 0;
		int notiType = 30;
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		list = noticeDao.getNoticeMainListsort(user.getSite(),userType,page, ITEM_COUNT_PER_PAGE, notiType);
		count = noticeDao.getNoticeMainCount(user.getSite(),userType,"",notiType);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		// model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "/admin/guide/guide_list";
	}

	// 이용안내공지 등록/수정
	@RequestMapping("/admin/guide/guide_edit.go")
	public String guideEditController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			Model model,HttpSession session) {

		Notice notice = null;
		if (seq == 0) {
			notice = new Notice();
		} else {
			notice = noticeDao.getNotice(seq);
		}


		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		List list= siteDao.getSiteList();
		model.addAttribute("user", user);
		model.addAttribute("list", list);
		model.addAttribute("notice", notice);

		return "/admin/guide/guide_edit";
	}

	// 이용안내 광고 수정의 처리
	@RequestMapping("/admin/guide/guide_edit_do.go")
	public String guideEditDoController(HttpServletRequest req,
			HttpServletResponse res, Model model) throws Exception {

		req.setCharacterEncoding("utf-8");

		int fileMaxBiteSize = FILE_MAX_SIZE * 1024 * 1024;
		File file = null;

		FILE_PATH = "/files/guide/";
		FILE_LOCAL_PATH = FILE_ROOT + FILE_PATH;

		try {
			// 파일 업로드.
			// 폼에서 가져온 인자값을 얻기 위해 request 객체 전달, 업로드 경로, 파일 최대 크기, 한글처리, 파일 중복처리
			MultipartRequest multi = new MultipartRequest(req, FILE_LOCAL_PATH,
					fileMaxBiteSize, "UTF-8", new DefaultFileRenamePolicy());

			// 폼에서 입력한 값을 가져옴
			int seq = Integer.parseInt(F.nullCheck(multi.getParameter("seq"),
					"0"));
			String linkUrl = F.nullCheck(multi.getParameter("linkUrl"), "");
			int visible = Integer.parseInt(F.nullCheck(
					multi.getParameter("visible"), "0"));
			String startDate = F.nullCheck(multi.getParameter("startDate"), "");
			String endDate = F.nullCheck(multi.getParameter("endDate"), "");
			String backgroundColor = F.nullCheck(
					multi.getParameter("backgroundColor"), "");
			String sido = F.nullCheck(multi.getParameter("areaSido"), "");
			String site = F.nullCheck(multi.getParameter("site"), "");
			String gugun = F.nullCheck(multi.getParameter("areaGugun"), "");
			// int price =
			// Integer.parseInt(F.nullCheck(multi.getParameter("price"),"0"));
			String MAIN_IMG = "";
			String THUMB_IMG = "";

			// 업로드한 파일들을 Enumeration 타입으로 반환.
			// Enumeration형은 데이터를 뽑아올때 유용한 인터페이스
			Enumeration files = multi.getFileNames();

			String sName = "";

			while (files.hasMoreElements()) {
				String elementName = (String) files.nextElement();

				file = multi.getFile(elementName);
				int i = 0;
				if (file != null) {
					if (elementName.equals("imgFile")) {
						String sPath = file.getParent() + "/";
						sName = file.getName();
					}
				}
			}

			boolean result = true;
			String message = "";

			if (seq == 0) {
				Notice notice = new Notice();
				// notice.setLinkUrl(linkUrl);
				// notice.setBackgroundColor(backgroundColor);
				notice.setStartDate(startDate);
				notice.setEndDate(endDate);
				notice.setVisible(visible);
				notice.setSort(noticeDao.getLastSort()+1);
				notice.setNotiType(30);
				notice.setContentsHtml(FILE_PATH + sName);
				notice.setSite(site);
				noticeDao.addNotice(notice);
				seq = noticeDao.getLastSeq();
				result = true;
				message = "등록되었습니다.";

			} else {
				Notice notice = noticeDao.getNotice(seq);
				if (sName.equals("") == false) {
					notice.setContentsHtml(FILE_PATH + sName);
				}
				// notice.setLinkUrl(linkUrl);
				notice.setSite(site);
				notice.setStartDate(startDate);
				notice.setEndDate(endDate);
				// notice.setSido(sido);
				// notice.setGugun(gugun);
				notice.setVisible(visible);
				notice.setNotiType(30);
				
				noticeDao.updateNotice(notice);
				result = true;
				message = "수정되었습니다.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// return "redirect:/admin/notice_manager.go";
		return "redirect:/admin/guide/guide.go";
	}

	// 이용안내 광고 이미지 삭제
	@RequestMapping("/admin/guide/guide_file_delete_do.go")
	public String guideFileDeleteDoController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "imgFile", required = false, defaultValue = "") String imgFile,
			HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		FILE_PATH = "/files/guide/";
		FILE_LOCAL_PATH = FILE_ROOT + FILE_PATH;

		File file = new File(FILE_LOCAL_PATH + imgFile);
		file.delete();

		Notice notice = noticeDao.getNotice(seq);
		notice.setContentsHtml("");
		noticeDao.updateNotice(notice);

		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 이용안내 광고 삭제
	@RequestMapping("/admin/guide/guide_delete_do.go")
	public String guideDeleteDoController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "imgFile", required = false, defaultValue = "") String imgFile,
			HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		FILE_PATH = "/files/guide/";
		FILE_LOCAL_PATH = FILE_ROOT + FILE_PATH;

		File file = new File(FILE_LOCAL_PATH + imgFile);
		file.delete();

		Notice notice = noticeDao.getNotice(seq);
		notice.setContentsHtml("");
		noticeDao.deleteNotice(seq);

		map.put("result", result);
		map.put("message", "삭제되었습니다.");

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// /////////////////////////////////////////////////////////////////////////////

	// 회원 관리 > 가상회원
	@RequestMapping("/admin/user/user_imagine.go")
	public String imagineController(HttpSession session, Model model) {

		List location = sidoDao.getSidoList();
		List site = siteDao.getSiteList();

		model.addAttribute("site", site);
		model.addAttribute("location", location);
		return "/admin/user/user_imagine";
	}

	// 회원 관리 > 가상회원 리스트
	@RequestMapping("/admin/user/user_imagine_list.go")
	public String imagineListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "site", required = false, defaultValue = "") String site,
			@RequestParam(value = "gender", required = false, defaultValue = "0") int gender,
			@RequestParam(value = "age", required = false, defaultValue = "0") int age,
			@RequestParam(value = "areaSido", required = false, defaultValue = "") String areaSido,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "colname", required = false, defaultValue = "") String colName,
			@RequestParam(value = "sort", required = false, defaultValue = "") String sort,
			HttpSession session, Model model) {

		List<User> list = null;
		int count = 0;
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");

		
		list = userDao.getUserImagineList(user.getSite(),userType,site, gender, age, keyword,areaSido, colName, sort, page, ITEM_COUNT_PER_PAGE);
		count = userDao.getImagineCount(user.getSite(),userType,site, gender, age, keyword, areaSido);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/user/user_imagine_list";
	}

	// 가상회원 등록/수정
	@RequestMapping("/admin/user/user_imagine_edit.go")
	public String userImagineEditController(
			@RequestParam(value = "userSeq", required = false, defaultValue = "0") int userSeq,
			@RequestParam(value = "userId", required = false, defaultValue = "0") String userId,
			Model model,HttpSession session) {

		User user = null;
		List location = sidoDao.getSidoList();

		if (userSeq == 0) {
			user = new User();
		} else {
			user = userDao.getUser(userId);
		}
		User uu =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");
		List site = siteDao.getSiteList();
		List chatUserList = userDao.getChatUser(uu.getSite(),userType);

		model.addAttribute("location", location);
		model.addAttribute("site", site);
		model.addAttribute("user", user);
		model.addAttribute("userSeq", userSeq);
		model.addAttribute("userId", userId);
		model.addAttribute("chatUserList", chatUserList);

		return "/admin/user/user_imagine_edit";
	}

	// 가상회원 등록,수정의 처리
	@RequestMapping("/admin/user/user_imagine_edit_do.go")
	public String imagineEditDoController(HttpServletRequest req,
			HttpServletResponse res, Model model) throws Exception {

		req.setCharacterEncoding("utf-8");

		req.setCharacterEncoding("utf-8");

		int fileMaxBiteSize = FILE_MAX_SIZE * 1024 * 1024;
		File file = null;

		FILE_PATH = "/files/profile/";
		FILE_LOCAL_PATH = FILE_ROOT + FILE_PATH;
		Map<String, Object> map = new HashMap<String, Object>();
		String agentId = "";
		String editFrom = "";
		try {
			// 파일 업로드.
			// 폼에서 가져온 인자값을 얻기 위해 request 객체 전달, 업로드 경로, 파일 최대 크기, 한글처리, 파일 중복처리
			MultipartRequest multi = new MultipartRequest(req, FILE_LOCAL_PATH,fileMaxBiteSize, "UTF-8", new UniqFileRenamePolicy());

			// 폼에서 입력한 값을 가져옴
			int userSeq = Integer.parseInt(F.nullCheck(multi.getParameter("userSeq"), "0"));
			agentId = F.nullCheck(multi.getParameter("agentId"), "");
			String userId = F.nullCheck(multi.getParameter("userId"), "");
			String password = F.nullCheck(multi.getParameter("password"), "");
			String userName = F.nullCheck(multi.getParameter("userName"), "");
			String phoneNumber = F.nullCheck(multi.getParameter("phoneNumber"),"");
			int birthYear = Integer.parseInt(F.nullCheck(multi.getParameter("birthYear"), "0"));
			int gender = Integer.parseInt(F.nullCheck(multi.getParameter("gender"), "0"));
			String sido = F.nullCheck(multi.getParameter("areaSido"), "");
			String latitude = F.nullCheck(multi.getParameter("latitude"), "0");
			String longitude = F.nullCheck(multi.getParameter("longitude"), "0");
			//String site = F.nullCheck(multi.getParameter("site"), "");
			int pointUser = Integer.parseInt(F.nullCheck(multi.getParameter("point"), "0"));
			int userLevel = Integer.parseInt(F.nullCheck(multi.getParameter("userLevel"), "0"));
			int moneyUser = Integer.parseInt(F.nullCheck(multi.getParameter("fmoney"), "0"));
			String comment = F.nullCheck(multi.getParameter("comment"), "");
			editFrom = F.nullCheck(multi.getParameter("editFrom"), "");
			String MAIN_IMG = "";
			String THUMB_IMG = "";


			Enumeration files = multi.getFileNames();
			User user=userDao.getUsers(agentId);
			String site=user.getSite();
			String sName ="";
			String imgPhoto ="";
			String fullpath ="";
			String repath = "";
			boolean result = true;
			String message = "";
			while (files.hasMoreElements()) {
				String elementName = (String) files.nextElement();
				file = multi.getFile(elementName);
				if (file != null) {
					if(elementName.equals("photo")){
						
						sName = file.getName();
						if(!sName.equals("")){
							String jpg = sName.substring(sName.lastIndexOf("."));
							imgPhoto = userId+jpg;
							fullpath=FILE_ROOT + "/files/profile" ;
							repath =  "/files/profile/" ;
						}
						
					}
				}
			}
			if(!imgPhoto.equals("")){
				FileInputStream fis = new FileInputStream(FILE_LOCAL_PATH + sName);
				FileOutputStream fos = new FileOutputStream(fullpath+"/"+imgPhoto);
				int data = 0;
				while((data=fis.read())!=-1) {
					fos.write(data);
				}
				fis.close();
				fos.close();
				   
				// 축소이미지 저장
				File newFile = new File(FILE_LOCAL_PATH + sName);
				File thumbFile = new File(fullpath+"/thumb/"+imgPhoto);
				if (!thumbFile.exists()) {
					thumbFile.mkdirs();
				}
				try {
					ImageUtil.resize(newFile, thumbFile, 500, 0,0);
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			}
			

			if (userSeq == 0) {
				
				boolean userCheck = userDao.correctId(userId);
				boolean phoneCheck = userDao.correctPhone(phoneNumber,site);
				boolean nickCheck = userDao.correctNick(userName);
				
				if (userCheck) {
					map.put("result", false);
					map.put("message", "존재하는 아이디 입니다.");
				}else if(phoneCheck) {
					map.put("result", false);
					map.put("message", "존재하는 전화번호 입니다.");
					
				}else if ((nickCheck==true)||userName.equals("")) {
					map.put("result", false);
					map.put("message", "존재하는 닉네임 입니다.");
					
					
				}else {

					// String enPw = Sha256Util.encryptPassword(password);
					String enPw = CryptoNew.encrypt(password);
					Calendar calendar = Calendar.getInstance();
					String regDate = "";
					user = new User();
					user.setAgentId(agentId);
					user.setUserId(userId);
					user.setUserType(5);
						user.setPassword(enPw);
						user.setPhoneNumber(phoneNumber);
						user.setBirthYear(birthYear);
						
						user.setGender(gender);
						user.setArea(sido);
						if(!imgPhoto.equals("")){
							user.setPhoto(repath + imgPhoto);
						}
						user.setUserName(userName);
						user.setLatitude(Double.parseDouble(latitude));
						user.setLongitude(Double.parseDouble(longitude));
						user.setStatus(1);
						user.setUserLevel(userLevel);
						user.setPoint(0);
						Level level = levelDao.getremainex(userLevel);
						
						if (userLevel == level.getLevel()) {
							user.setLevelPoint(level.getMinEx());
						}
						user.setComment(comment);
						user.setSite(site);
						
	
						Point pp = pointDao.getSitePoint(PointMoney.JOIN_JOIN,site);
						if (pp != null) {
							int point = pp.getPoint(); // 포인트
							int money = pp.getMoney(); // 머니
							// 레벨 계산 메서드
							levelService.inserDb(userId, PointMoney.JOIN_JOIN,money, point, "회원가입");
						}
						userDao.addUser(user);
						
						map.put("result", true);
						map.put("message", "등록되었습니다.");
					

						
					}
			} else {

				// String enPw = Sha256Util.encryptPassword(password);
				String enPw = CryptoNew.encrypt(password);

				user = userDao.getUserSeq(userSeq);
				
				if (imgPhoto.equals("") == false) {
					user.setPhoto(FILE_PATH + imgPhoto);
				}
				
				user.setAgentId(agentId);
				user.setUserId(userId);
			
				boolean phoneCheck =false;
				boolean nickCheck =false;
				if(!user.getPhoneNumber().equals(phoneNumber)){
					phoneCheck = userDao.correctPhone(phoneNumber,site);
				}
				if(!user.getUserName().equals(userName)){
					nickCheck = userDao.correctNick(userName);
				}
				if(phoneCheck) {
				
					map.put("result", false);
					map.put("message", "존재하는 전화번호 입니다.");
				}else if ((nickCheck==true)||userName.equals("")) {
					map.put("result", false);
					map.put("message", "존재하는 닉네임 입니다.");
				
				}else {

						user.setUserName(userName);
						user.setUserType(5);
						user.setPassword(enPw);
						user.setPhoneNumber(phoneNumber);
						user.setBirthYear(birthYear);
						user.setGender(gender);
						user.setArea(sido);
						// user.setPhoto(photo);
						user.setLatitude(Double.parseDouble(latitude));
						user.setLongitude(Double.parseDouble(longitude));
						user.setStatus(1);
						user.setPoint(pointUser);
						user.setIncome(moneyUser);
						user.setUserLevel(userLevel);
						user.setComment(comment);
		
						Level level = levelDao.getremainex(userLevel);
		
						if (userLevel == level.getLevel()) {
							user.setLevelPoint(level.getMinEx());
						}
		
						user.setSite(site);
						userDao.updateManager(user);
						map.put("result", true);
						map.put("message", "수정되었습니다.");
						

				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		map.put("agentId", agentId);
	
			
	
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
			
		return null;
		
	
	}
	
	
	// 가상회원 이미지 삭제
	@RequestMapping("/admin/user/imagine_file_delete_do.go")
	public String imagineFileDeleteDoController(
			@RequestParam(value = "userId", required = false, defaultValue = "0") String userId,
			@RequestParam(value = "photo", required = false, defaultValue = "") String photo,
			HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		FILE_PATH = "/files/profile/";
		FILE_LOCAL_PATH = FILE_ROOT + FILE_PATH;

		File file = new File(FILE_ROOT + photo);
		file.delete();

		userDao.updateProfileImgDel(userId);

		result = true;
		message = "이미지가 삭제되었습니다.";

		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 배너광고
	@RequestMapping("/admin/banner/banner.go")
	public String bannerController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/banner/banner";
	}

	// 유저 데이터 전체 삭제
	@RequestMapping("/admin/user/user_delete_all.go")
	public String userDeleteAllController(
			@RequestParam(value = "arrSeq", required = false, defaultValue = "") String [] arrSeq,
			HttpSession session, Model model,HttpServletResponse res) {
		
		Map<String,Object>map = new HashMap<>();
		try{
			if(arrSeq !=null && !arrSeq.equals("")){
				
				for(int i=0;i<arrSeq.length;i++){
					
					String userId = arrSeq[i];
					expenseDao.deleteExpense(userId);//현금 환전 신청내역
					userDao.deleteUser(userId);
					
				}
			}
			
			map.put("result", true);
			map.put("message", "삭제되었습니다.");
		}catch(Exception e){

			map.put("result", false);
			map.put("message", e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	// 배너광고 리스트
	@RequestMapping("/admin/banner/banner_list.go")
	public String bannerListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<BannerAd> list = null;
		int count = 0;
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		int userType=(int)session.getAttribute("USER_TYPE");

		list = bannerAdDao.getBannerAdMainList(user.getSite(),userType,page, ITEM_COUNT_PER_PAGE);
		count = bannerAdDao.getBannerAdMainCount(user.getSite(),userType);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);

		return "admin/banner/banner_list";
	}

	// 배너광고 등록/수정
	@RequestMapping("/admin/banner/banner_edit.go")
	public String bannerEditController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			Model model,HttpSession session) {

		List location = null;
		BannerAd bannerAd = null;

		if (seq == 0) {
			bannerAd = new BannerAd();
			location = areaDao.getSidoList();
		} else {
			bannerAd = bannerAdDao.getBannerAd(seq);
			location = areaDao.getSidoList();
		}
		User user =userDao.getUsers((String)session.getAttribute("USER_ID"));
		List list= siteDao.getSiteList();
		model.addAttribute("user", user);
		model.addAttribute("list", list);
		model.addAttribute("bannerAd", bannerAd);
		model.addAttribute("location", location);

		return "admin/banner/banner_edit";
	}

	// 배너광고 수정의 처리
	@RequestMapping("/admin/banner/banner_edit_do.go")
	public String BannereditBoardItemDoController(HttpServletRequest req,
			HttpServletResponse res, Model model) throws Exception {

		req.setCharacterEncoding("utf-8");

		int fileMaxBiteSize = FILE_MAX_SIZE * 1024 * 1024;
		File file = null;

		FILE_PATH = "/files/banner/";
		FILE_LOCAL_PATH = FILE_ROOT + FILE_PATH;

		try {
			// 파일 업로드.
			// 폼에서 가져온 인자값을 얻기 위해 request 객체 전달, 업로드 경로, 파일 최대 크기, 한글처리, 파일 중복처리
			MultipartRequest multi = new MultipartRequest(req, FILE_LOCAL_PATH,fileMaxBiteSize, "UTF-8", new DefaultFileRenamePolicy());

			// 폼에서 입력한 값을 가져옴
			int seq = Integer.parseInt(F.nullCheck(multi.getParameter("seq"),
					"0"));
			String linkUrl = F.nullCheck(multi.getParameter("linkUrl"), "");
			String startDate = F.nullCheck(multi.getParameter("startDate"), "");
			String endDate = F.nullCheck(multi.getParameter("endDate"), "");
			// String backgroundColor =
			// F.nullCheck(multi.getParameter("backgroundColor"), "");
			// String sido = F.nullCheck(multi.getParameter("areaSido"), "");
			String site = F.nullCheck(multi.getParameter("site"), "");
			String MAIN_IMG = "";
			String THUMB_IMG = "";

			// 업로드한 파일들을 Enumeration 타입으로 반환.
			// Enumeration형은 데이터를 뽑아올때 유용한 인터페이스
			Enumeration files = multi.getFileNames();

			String sName = "";

			while (files.hasMoreElements()) {
				String elementName = (String) files.nextElement();

				file = multi.getFile(elementName);
				int i = 0;
				if (file != null) {
					if (elementName.equals("imgFile")) {
						String sPath = file.getParent() + "/";
						sName = file.getName();
					}
				}
			}

			boolean result = true;
			String message = "";

			if (seq == 0) {
				BannerAd bannerAd = new BannerAd();
				bannerAd.setLinkUrl(linkUrl);
				// bannerAd.setBackgroundColor(backgroundColor);
				bannerAd.setStartDate(startDate);
				bannerAd.setEndDate(endDate);
				// bannerAd.setSido(sido);
				bannerAd.setSite(site);
				bannerAd.setContentsHtml(FILE_PATH + sName);
				bannerAdDao.addBannerAd(bannerAd);
				seq = bannerAdDao.getLastSeq();
				result = true;
				message = "등록되었습니다.";

			} else {
				BannerAd bannerAd = bannerAdDao.getBannerAd(seq);
				if (sName.equals("") == false) {
					bannerAd.setContentsHtml(FILE_PATH + sName);
				}
				bannerAd.setLinkUrl(linkUrl);
				// bannerAd.setBackgroundColor(backgroundColor);
				bannerAd.setStartDate(startDate);
				bannerAd.setEndDate(endDate);
				// bannerAd.setSido(sido);
				bannerAd.setSite(site);
				bannerAdDao.updateBannerAd(bannerAd);
				result = true;
				message = "수정되었습니다.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/admin/banner/banner.go";
	}

	// 배너광고 이미지 삭제
	@RequestMapping("/admin/banner/banner_file_delete_do.go")
	public String bannerFileDeleteDoController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "imgFile", required = false, defaultValue = "") String imgFile,
			HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		FILE_PATH = "/files/banner/";
		FILE_LOCAL_PATH = FILE_ROOT + FILE_PATH;

		File file = new File(FILE_ROOT + imgFile);
		file.delete();

		BannerAd bannerAd = bannerAdDao.getBannerAd(seq);
		bannerAd.setContentsHtml("");
		bannerAdDao.updateBannerAd(bannerAd);

		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 배너광고 삭제
	@RequestMapping("/admin/banner/banner_delete_do.go")
	public String bannerDeleteDoController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "imgFile", required = false, defaultValue = "") String imgFile,
			HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		FILE_PATH = "/files/banner/";
		FILE_LOCAL_PATH = FILE_ROOT + FILE_PATH;

		File file = new File(FILE_ROOT + imgFile);
		file.delete();

		BannerAd bannerAd = bannerAdDao.getBannerAd(seq);
		bannerAd.setContentsHtml("");
		bannerAdDao.deleteBannerAd(seq);

		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	public void filedelete(String files) {

		String[] arr = files.split(",");
		for (int i = 0; i < arr.length; i++) {
			String fileName = arr[i];
			// 본파일
			FILE_LOCAL_PATH = FILE_ROOT + fileName;
			File file = new File(FILE_LOCAL_PATH);
			file.delete();
			// 썸네일
			String thumpath = fileName.substring(0, fileName.lastIndexOf("/"))
					+ "/thumb"; // 경로
			String tumbName = fileName.substring(fileName.lastIndexOf("/")); // 사진이름
			String thumb = FILE_ROOT + thumpath + tumbName;
			File file2 = new File(thumb);
			file2.delete();

		}

	}

	// 댓글,댓글의 댓글지우기
	public boolean deleteComment(int Seq) {

		boolean res = false;
		try {
			List<BbsComment> list1 = bbsCommentDao.getBbsCommentFileList(Seq);
			// 댓글을 지우고
			if (list1 != null) {
				Iterator<BbsComment> it1 = list1.iterator();
				while (it1.hasNext()) {
					BbsComment bc = it1.next();
					if (!bc.getFiles().equals("")) {
						filedelete(bc.getFiles());
					}
					// 댓글의 댓글삭제
					List<BbsComment> list2 = bbsCommentDao.getRBbsComment(bc
							.getBbsCommentSeq());
					if (list2 != null) {
						Iterator<BbsComment> it2 = list2.iterator();
						while (it2.hasNext()) {
							BbsComment rbc = it2.next();
							if (!rbc.getFiles().equals("")) {
								filedelete(rbc.getFiles());
							}
						}
					}

					bbsCommentDao.deleteBbsReplyCommentAll(bc
							.getBbsCommentSeq());// 댓글의 댓글지우기
				}

			}
			bbsCommentDao.deleteBbsCommentAll(Seq);// 댓글지우기
			res = true;
		} catch (Exception e) {

		}

		return res;
	}

	

	// 1:1문의 관리 등록/수정
	@RequestMapping("/admin/chat/room_list.go")
	public String roomListController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			Model model) {

		Qna qna = null;
		if (seq == 0) {
			qna = new Qna();
		} else {
			qna = qnaDao.getQna(seq);
		}

		// 댓글리스트
		List commentList = qnaDao.getQnaList(seq, page, ITEM_COUNT_PER_PAGE);
		// int bbsCount = bbsCommentDao.getCount(seq);

		model.addAttribute("commentList", commentList);
		model.addAttribute("qna", qna);

		return "admin/chat/room_list";
	}

	// 1:1문의 관리 등록/수정
	@RequestMapping("/admin/chat/room_view.go")
	public String roomViewController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			Model model) {

		Qna qna = null;
		if (seq == 0) {
			qna = new Qna();
		} else {
			qna = qnaDao.getQna(seq);
		}

		// 댓글리스트
		List commentList = qnaDao.getQnaList(seq, page, ITEM_COUNT_PER_PAGE);
		// int bbsCount = bbsCommentDao.getCount(seq);

		model.addAttribute("commentList", commentList);
		model.addAttribute("qna", qna);

		return "admin/chat/room_view";
	}
	
	// 정렬
	@RequestMapping("/admin/guide/sort.go")
	public String daysortEditController(
			@RequestParam(value = "arrSeq", required = false, defaultValue = "") String[] arrSeq,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			int Count = arrSeq.length;

			if (arrSeq != null || !(arrSeq.equals(""))) {

				for (int i = 0; i < Count; i++) {

					int Seq = Integer.parseInt(arrSeq[i]);

					noticeDao.updatesort(Seq, i + 1);

					result = true;
					message = "수정되었습니다.";
				}

			}

		} catch (Exception e) {
			message = e.getMessage();
		}

		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	// 채팅방 참여리스트
	@RequestMapping("/admin/chat/user_list.go")
	public String chatuser_listController(
			@RequestParam(value = "chatRoomSeq", required = false, defaultValue = "0") int chatRoomSeq,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			List<User>list = userDao.getadchatuserList(chatRoomSeq,page,ITEM_COUNT_PER_PAGE);
			
			int count = userDao.getchatuserCount(chatRoomSeq);
			String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
					PAGE_COUNT_PER_PAGING);

			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("currentPage", page);
		} catch (Exception e) {
			message = e.getMessage();
		}

		
		return "/admin/chat/chat_user_list";
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

			if ( !uu.getPushkey().equals("") && uu.getStatus() == 1) {

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
	
}
