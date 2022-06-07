<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:if test="${empty user}">
	<meta http-equiv="Refresh" content="0;URL=index">
</c:if>
<title>TOP画面</title>
<link href="css/commons.css" rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="/css/index.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
</head>
<body>

	<div class="user">
		<p class="user_name">${user.name}さん、こんにちは</p>
		<form class="logout_form" action="logout" method="get">
			<button class="logout_btn" type="submit">
				<img src="images/ドアアイコン.png">
				<fmt:message key="form.lbl.logout" />
			</button>
		</form>
	</div>


	<c:if test="${user.role == 1}">
		<div class="btn">
			<form action="searchInput" method="get">
				<button class="basic_btn regist" name="wordSearch">
					単語検索
				</button>
			</form>
		</div>
	</c:if>
	
<!-- 	ここから追加 -->
	<div id="content">
		<p id="timer"></p>

		<p>
			<button type="button" id="timerstart" name="timerstart"
				class="btn btn-primary">スタート</button>
		</p>
		<p id="japanese"></p>
		<p id="target"></p>
		<p id="count"></p>
	</div>
	<script src="/js/countdown.js"></script>
</body>
</html>
