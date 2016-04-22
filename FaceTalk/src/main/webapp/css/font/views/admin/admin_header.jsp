<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
	$(document).ready(function() {
// 		user.Usercount();
	});
</script>
<section class="main-cover">
	<header id="top-header">
		<aside>
			<div class="top-logo">
				<a href="/admin/main.go"><img src="/images/img_logo.png"></a>
			</div>
		</aside>
		<section>
			<div class="head">
				<div class="info-block">
					<ul>
						<li>
							<dl>
								<dt><img src="/images/img_user.png"></dt>
								<dd>
									회원수<br>
									<span id="user-count">0</span>명
								</dd>
							</dl>
						</li>
						<li>
							<dl>
								<dt><img src="/images/img_analysis.png"></dt>
								<dd>
									분석가수<br>
									<span id="analyst-count">0</span>명
								</dd>
							</dl>
						</li>
						<li>
							<dl>
								<dt><img src="/images/img_today_analysis.png"></dt>
								<dd>
									금일 분석요청 수<br>
									<span id="analysis-count">0</span>건
								</dd>
							</dl>
						</li>
					</ul>
				</div>

				<div class="user-block">
					<div class="head-tool" onclick="user.togleCombo();">
						<dl>
							<dt class="user-info">
								<p>${USER_NAME}<br>${USER_ID}</p>
							</dt>
							<dd>
								<div class="arrow">▼</div>
							</dd>
						</dl>
					</div>
					<ul class="user-tool-combo">
						<li><button type="button" onclick="user.changePw();"><span>비밀번호 변경</span></button></li>
						<li><button type="button" onclick="user.logout('admin');"><span>로그아웃</span></button></li>
					</ul>
				</div>
			</div>
		</section>
	</header>
</section>