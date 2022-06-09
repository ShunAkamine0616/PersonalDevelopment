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
<title>タイトルページ</title>
</head>
<body>
	<h1>英単語タイピングゲームへようこそ</h1>

	<form action="login">
		<%-- 	<form:button value="login">ログイン</form:button> --%>
		<%-- 	<form:button value="register">新規登録</form:button> --%>
		<input type="button" onclick="location.href='./loginForm'"
			value="ログイン" class="basic_btn" name="btn">
		<input type="button" onclick="location.href='./register'"
			value="新規登録" class="basic_btn" name="btn">
	</form>

</body>
</html>