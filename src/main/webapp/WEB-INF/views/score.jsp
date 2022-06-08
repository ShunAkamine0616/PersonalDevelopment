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
<title>スコア表</title>
<link href="css/commons.css" rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<body>
	<div id="app">

		<div class="header">
			<h1 class="site_logo">
				<a href="menu"><fmt:message key="site_logo" /></a>
			</h1>
			<div class="user">
				<p class="user_name">${user.name}さん、こんにちは</p>
				<form class="logout_form" action="logout" method="get">
					<button class="logout_btn" type="submit">
						<img src="images/ログアウト.png">
						<fmt:message key="form.lbl.logout" />
					</button>
				</form>
			</div>
		</div>

		<hr>
		<!-- 		<p> -->
		<%-- 			<c:if test="${not empty successMsg}"> --%>
		<%-- 				<span>${fn:escapeXml(successMsg)}</span> --%>
		<%-- 			</c:if> --%>
		<!-- 		</p> -->
		<%-- 		<form method="get" action="search" class="search_container"> --%>
		<!-- 			<input type="text" size="25" placeholder="キーワード検索" name="keyscore" -->
		<%-- 				id="keyscore" value="${keyscore}"> <input type="submit" --%>
		<!-- 				value="&#xf002"> -->
		<%-- 		</form> --%>
		<input type="button" onclick="location.href='back'" value="タイピング画面へ戻る"
			class="cancel_btn">
		<table border="1">
			<div class="caption">
				<p>スコア表：${scoreList.size()}件</p>
			</div>
			<!-- 			<div class="order"> -->
			<!-- 				<select class="base-text" name="sort" -->
			<!-- 					onChange="location.href='searchByKeyscore?sort=' + value + '&keyscore=' + document.getElementById('keyscore').value"> -->
			<!-- 					<option>並び替え</option> -->
			<!-- 					<option value="id">ID順</option> -->
			<!-- 					<option value="japanese">日本語順</option> -->
			<!-- 					<option value="english">英単語順</option> -->
			<!-- 					<option value="difficulty">難易度: 低い順</option> -->
			<!-- 					<option value="difficulty DESC">難易度: 高い順</option> -->
			<!-- 					<option value="created_at">登録日：古い順</option> -->
			<!-- 					<option value="created_at DESC">登録日：新しい順</option> -->
			<!-- 					<option value="updated_at">更新日：古い順</option> -->
			<!-- 					<option value="updated_at DESC">更新日：新しい順</option> -->
			<!-- 				</select> -->
			<!-- 			</div> -->
			<thead>
				<tr>
					<th>ユーザーID</th>
					<th>スコア</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="score" items="${scoreList}">
					<tr>
						<td>${score.loginId}</td>
						<td>${score.score}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<footer></footer>
	<script>
    window.addEventListener('pageshow', () => {
    	let element = document.getElementById('sort');
//     	element.value = getParam('sort');
    });
	</script>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
</body>
</html>
