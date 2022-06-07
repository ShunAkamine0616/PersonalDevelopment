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
<title>メニュー</title>
<link href="css/commons.css" rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<body>
	<div id="app">

		<div class="header">
			<h1 class="site_logo">
				<a href="menu.jsp"><fmt:message key="site_logo" /></a>
			</h1>
			<div class="user">
				<p class="user_name">${user.name}さん、こんにちは</p>
				<form class="logout_form" action="logout" method="get">
					<button class="logout_btn" type="submit">
						<img src="images/ドアアイコン.png">
						<fmt:message key="form.lbl.logout" />
					</button>
				</form>
			</div>
		</div>

		<hr>
		<c:if test="${user.role == 1}">
			<div class="btn">
				<form action="InsertController" method="get">
					<button class="basic_btn regist" name="insert">単語追加</button>
				</form>
			</div>
		</c:if>
		
		<p>
			<c:if test="${not empty successMsg}">
				<span>${fn:escapeXml(successMsg)}</span>
			</c:if>
		</p>
		<form method="get" action="search" class="search_container">
			<input type="text" size="25" placeholder="キーワード検索" name="keyword"
				id="keyword" value="${keyword}"> <input type="submit"
				value="&#xf002">
		</form>

		<table>
			<div class="caption">
				<p>検索結果：${wordList.size()}件</p>
			</div>
			<div class="order">
				<select class="base-text" name="sort"
					onChange="location.href='searchByKeyword?sort=' + value + '&keyword=' + document.getElementById('keyword').value">
					<option>並び替え</option>
					<option value="id">ID順</option>
					<option value="japanese">日本語順</option>
					<option value="english">英単語順</option>
					<option value="difficulty">難易度: 低い順</option>
					<option value="difficulty DESC">難易度: 高い順</option>
					<option value="created_at">登録日：古い順</option>
					<option value="created_at DESC">登録日：新しい順</option>
					<option value="updated_at">更新日：古い順</option>
					<option value="updated_at DESC">更新日：新しい順</option>
				</select>
			</div>
			<thead>
				<tr>
					<th>ID</th>
					<th>日本語訳</th>
					<th>英単語</th>
					<th>難易度</th>
					<th>詳細</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="word" items="${wordList}">
					<tr>
						<td>${word.getId()}</td>
						<td>${word.getJapanese()}</td>
						<td>${word.getEnglish()}</td>
						<td>${word.getDifficulty()}</td>
						<td><a class="detail_btn" href="detail?id=${word.getId()}">詳細</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<footer></footer>
	<script>
    window.addEventListener('pageshow', () => {
    	let element = document.getElementById('sort');
    	element.value = getParam('sort');
    });
	</script>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
</body>
</html>
