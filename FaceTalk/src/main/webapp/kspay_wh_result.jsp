<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import = "java.sql.*" %>
<%@ page import="java.net.URLDecoder"%> 
<script type="text/javascript" src="/lib/jquery/jquery-1.10.2.min.js"></script>

<%
	String rcid		= request.getParameter("reCommConId"		);
	String rctype	= request.getParameter("reCommType"		);
	String rhash	= request.getParameter("reHash"			);

	String	authyn =  "";
	String	trno   =  "";
	String	trddt  =  "";
	String	trdtm  =  "";
	String	amt    =  "";
	String	authno =  "";
	String	msg1   =  "";
	String	msg2   =  "";
	String	ordno  =  "";
	String	isscd  =  "";
	String	aqucd  =  "";
	String	result =  "";

	String	resultcd =  "";
	int payType =0;
	//업체에서 추가하신 인자값을 받는 부분입니다
	String point = URLDecoder.decode(request.getParameter("ECHA"), "UTF-8"); 
	
	String	price =  URLDecoder.decode(request.getParameter("ECHB"), "UTF-8");
	String	mobile =  URLDecoder.decode(request.getParameter("ECHC"), "UTF-8");
	String	userpoint =   URLDecoder.decode(request.getParameter("ECHD"), "UTF-8");
	String	sndOrdernumber =   URLDecoder.decode(request.getParameter("ECHF"), "UTF-8");
	String	comment =  URLDecoder.decode(request.getParameter("ECHG"), "UTF-8"); 
	String	userId =   URLDecoder.decode(request.getParameter("ECHH"), "UTF-8"); 

	ksnet.kspay.KSPayWebHostBean ipg = new ksnet.kspay.KSPayWebHostBean(rcid);
	if (ipg.send_msg("1"))		//KSNET 결제결과 중 아래에 나타나지 않은 항목이 필요한 경우 Null 대신 필요한 항목명을 설정할 수 있습니다.
	{
		authyn	= ipg.getValue("authyn");
		trno	 = ipg.getValue("trno"  );
		trddt	 = ipg.getValue("trddt" );
		trdtm	 = ipg.getValue("trdtm" );
		amt		 = ipg.getValue("amt"   );
		authno = ipg.getValue("authno");
		msg1	 = ipg.getValue("msg1"  );
		msg2	 = ipg.getValue("msg2"  );
		ordno	 = ipg.getValue("ordno" );
		isscd	 = ipg.getValue("isscd" );
		aqucd	 = ipg.getValue("aqucd" );
		//temp_v	 = ipg.getValue("temp_v");
		result	 = ipg.getValue("result");
	
		
		if (null != authyn && 1 == authyn.length())
		{
			if (authyn.equals("O"))
			{
				resultcd = "0000";
				char re =result.charAt(0);
				 if(re=='1'||re=='I'){
					payType =1;
				}else{
					payType =2;
				}
			
				// DB연결
        		String url = "jdbc:jtds:sqlserver://218.234.17.164:1433;tds=8.0;lastupdatecount=true;DatabaseName=FaceTalk";
	        	String id = "promise";                                                    // 사용자 계정
	        	String pw = "facepro$$$";                                                // 사용자 계정의 패스워드

	        	Connection conn = null;                                        // null로 초기화 한다.
	        	PreparedStatement pstmt = null;

	        	Class.forName("net.sourceforge.jtds.jdbc.Driver");		// 데이터베이스와 연동하기 위해 DriverManager에 등록한다.
	        	conn=DriverManager.getConnection(url,id,pw);            // DriverManager 객체로부터 Connection 객체를 얻어온다.
	        	
	        	String sql = "INSERT INTO T_NF_ORDER " 
	    				+"(user_id, order_title, price_sum, "
	    				+ "total_fee, payment_type, "
	    				+ "card_name, card_number, "
	    				+ "order_date, "
	    				+ "comment,"
	    				+ "mobile) " +
	    				"VALUES " +
	    				"	(?, ?, ?,"
	    				+ "  ?, ?, "
	    				+ "  ?, ?,"
	    				+ "  getDate(), ?, "
	    				+ "  ?) ";        // sql 쿼리
	        	pstmt = conn.prepareStatement(sql);            // prepareStatement에서 해당 sql을 미리 컴파일한다.
	        	
	        	pstmt.setString(1,userId); //
	        	pstmt.setString(2,trno);
	        	pstmt.setInt(3,Integer.parseInt(point));
	        	pstmt.setInt(4,Integer.parseInt(price));
	        	pstmt.setInt(5,payType);
	        	pstmt.setString(6,isscd);//발급사코드
	        	pstmt.setInt(7,0);
	        	pstmt.setString(8,"충전");
	        	pstmt.setString(9,mobile);
	        	
	        	pstmt.executeUpdate(); 
	        	
	        	///유저테이블 업데이트
	        	
	        	sql = "update t_nf_user set " 
	    			  +" point = ? where user_id = ? ";       // sql 쿼리
	        	pstmt = conn.prepareStatement(sql);            // prepareStatement에서 해당 sql을 미리 컴파일한다.
	        	
	        	pstmt.setInt(1,Integer.parseInt(point)+Integer.parseInt(userpoint)); //
	        	pstmt.setString(2,userId);
	        
	        	pstmt.executeUpdate();  
	        	
			}else
			{
				resultcd = authno.trim();
			}

			ipg.send_msg("3");	// 정상처리가 완료되었을 경우 호출합니다.(이 과정이 없으면 일시적으로 kspay_send_msg("1")을 호출하여 거래내역 조회가 가능합니다.)
		}
	}
%>
<script type="text/javascript">
	$(document).ready(function() {
		document.location.href="/m/result.go"
	});
</script>
	


<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>호스트방식(APP) 결제샘플</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
	<style type="text/css">
	BODY{font-size:9pt; line-height:100%}
	TD{font-size:9pt; line-height:100%}
	A {color:blue;line-height:100%; background-color:#E0EFFE}
	INPUT{font-size:9pt;}
	SELECT{font-size:9pt;}
</style>
</head>
<body bgcolor=#ffffff onload="">
<CENTER><B><font size=4 color="blue">성공페이지 내역.</font></B></CENTER>
<br>
<TABLE  width=100% border="1" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="center" colspan=4>
			<br>
			이페이지는 <font color = "red">승인성공시</font>에 업체측으로 리턴되는 결과 값들을 나타내고 있읍니다. 
			<br>
			아래와 같은 리턴 항목들중에서 귀사에서 필요하신 부분만 받으셔서 사용하시면 됩니다.
			<br>
			<br>
		</td>
	</tr>
<TR>
	<TD><B>항목명</B></TD>
	<TD><B>변수명</B></TD>
	<TD><B>결과값</B></TD>
	<TD><B>비고</B></TD>
</TR>
<TR>
	<TD>승인구분</TD>
	<TD>authyn</TD>
	<TD><%if(authyn.equals("O")) out.print("승인"); else out.print("거절");%></TD>
	<TD>승인요청에 대하여 승인이 허락되던지 <br>거절되던지 리턴값의 항목은 같읍니다.</TD>
</TR>
<TR>
	<TD>거래번호</TD>
	<TD>trno</TD>
	<TD><%=trno%></TD>
	<TD>거래번호는 중요합니다. <br>결재정보중 유니크키로 사용하는값으로 사후 승인취소등의 처리시 꼭 필요합니다.</TD>
</TR>
<TR>
	<TD>거래일자</TD>
	<TD>trddt</TD>
	<TD><%=trddt%></TD>
	<TD>&nbsp;</TD>
</TR>
<TR>
	<TD>거래시간</TD>
	<TD>trdtm</TD>
	<TD><%=trdtm%></TD>
	<TD>&nbsp;</TD>
</TR>
<TR>
	<TD>카드사 승인번호/은행 코드번호</TD>
	<TD>authno</TD>
	<TD><%=authno%></TD>
	<TD>승인번호는 카드사에서 발급하는 것으로 유니크하지 않을수도 있음에 주의하십시요.</TD>
</TR>
<TR>
	<TD>발급사코드/가상계좌번호/계좌이체번호</TD>
	<TD>isscd</TD>
	<TD><%=isscd%></TD>
	<TD></TD>
</TR>
<TR>
	<TD>매입사코드</TD>
	<TD>aqucd</TD>
	<TD><%=aqucd%></TD>
	<TD></TD>
</TR>
<TR>
	<TD>주문번호</TD>
	<TD>ordno</TD>
	<TD><%=ordno%></TD>
	<TD>주문번호는 업체측에서 넘겨주신 값을 그대로 리턴해드립니다.</TD>
</TR>
<TR>
	<TD>금액</TD>
	<TD>amt</TD>
	<TD><%=amt%></TD>
	<TD>&nbsp;</TD>
</TR>
<TR>
	<TD>메세지1</TD>
	<TD>msg1</TD>
	<TD><%=msg1%></TD>
	<TD>메세지는 카드사에서 보내는 것을 그대로 리턴해 드리는것으로<br> KSNET에서 생성된 내용은 아닙니다.</TD>
</TR>
<TR>
	<TD>메세지2</TD>
	<TD>msg2</TD>
	<TD><%=msg2%></TD>
	<TD>승인성공시 이부분엔 OK와 승인번호가 표시됩니다.</TD>
</TR>
<TR>
    <TD>결제수단</TD>
    <TD>result</TD>
    <TD><%=result%></TD>
    <TD>결제수단이 표시됩니다.</TD>
</TR>
<TR>
    <TD>결제수단</TD>
    <TD>result</TD>
    <TD><%=result%></TD>
    <TD>결제수단이 표시됩니다.</TD>
</TR>
<TR>
    <TD>결제수단</TD>
    <TD>result</TD>
    <TD><%=result%></TD>
    <TD>결제수단이 표시됩니다.</TD>
</TR>
<TR>
    <TD>결제수단</TD>
    <TD>result</TD>
    <TD><%=result%></TD>
    <TD>결제수단이 표시됩니다.</TD>
</TR>
<TR>
    <TD>결제수단</TD>
    <TD>price</TD>
    <TD><%=price%></TD>
    <TD>결제수단이 표시됩니다.</TD>
</TR>
<TR>
    <TD>여기여기</TD>
    <TD>point</TD>
    <TD><%=point%></TD>
    <TD>결제수단이 표시됩니다.</TD>
</TR>
	<tr>
		<td align="center" colspan=4>
			<br>
			<br>
			<br>
		</td>
	</tr>
</TABLE>
</body>
</html> --%>