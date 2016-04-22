package kr.nomad.mars;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.nomad.mars.dao.AreaDao;
import kr.nomad.mars.dao.BbsCommentDao;
import kr.nomad.mars.dao.BbsDao;
import kr.nomad.mars.dao.BbsFilesDao;
import kr.nomad.mars.dao.EventDao;
import kr.nomad.mars.dao.FaqDao;
import kr.nomad.mars.dao.GuestBookDao;
import kr.nomad.mars.dao.LevelDao;
import kr.nomad.mars.dao.NoticeDao;
import kr.nomad.mars.dao.PointChargeDao;
import kr.nomad.mars.dao.PointDao;
import kr.nomad.mars.dao.QnaDao;
import kr.nomad.mars.dao.UserDao;
import kr.nomad.mars.dto.Album;
import kr.nomad.mars.dto.Area;
import kr.nomad.mars.dto.Bbs;
import kr.nomad.mars.dto.BbsComment;
import kr.nomad.mars.dto.BbsFiles;
import kr.nomad.mars.dto.Event;
import kr.nomad.mars.dto.Faq;
import kr.nomad.mars.dto.GuestBook;
import kr.nomad.mars.dto.Level;
import kr.nomad.mars.dto.Notice;
import kr.nomad.mars.dto.Point;
import kr.nomad.mars.dto.PointCharge;
import kr.nomad.mars.dto.Qna;
import kr.nomad.mars.dto.User;
import kr.nomad.util.F;
import kr.nomad.util.Paging;
import kr.nomad.util.Response;
import kr.nomad.util.T;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import encrypt.Sha256Util;

@Controller
public class MallController {
	
	@Autowired
	UserDao userDao;
	
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

	// 쇼핑몰 메인
	@RequestMapping("/mall/index.go")
	public String mallIndexController(HttpSession session, Model model) {


		return "/mall/index";
	}

	// 쇼핑몰 메인
	@RequestMapping("/mall/company.go")
	public String mallCompanyController(HttpSession session, Model model) {


		return "/mall/company";
	}

	// 쇼핑몰 메인
	@RequestMapping("/mall/customer.go")
	public String mallCustomerController(HttpSession session, Model model) {


		return "/mall/customer";
	}

	// 쇼핑몰 메인
	@RequestMapping("/mall/introduce.go")
	public String mallIntroduceController(HttpSession session, Model model) {


		return "/mall/introduce";
	}
}
