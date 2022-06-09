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
<title><fmt:message key="page.insert.title" /></title>
<link href="css/commons.css" rel="stylesheet">

</head>
<body>

	<div class="header">
		<h1 class="site_logo">
			<a href="menu"><fmt:message key="site_logo" /></a>
		</h1>
		<div class="user">
			<p class="user_name">${user.name}さん、こんにちは</p>
			<form class="logout_form" action="logout" method="get">
				<button class="logout_btn" type="submit">
					<img src="images/ログアウト.png">ログアウト
				</button>
			</form>
		</div>
	</div>

	<hr>

	<div class="insert">
		<div class="discription">
			<p>
				登録情報を入力してください（<span class="required"></span>は必須です）
			</p>
		</div>

		<div class="form_body">
			<p class="error">
				<c:if test="${not empty insertErrMsg}">
					<span>${fn:escapeXml(insertErrMsg)}</span>
				</c:if>
			</p>

			<form:form action="insert" method="post" modelAttribute="insert">
				<fieldset class="label-130">
					<div>
						<label class="required"><fmt:message
								key="form.lbl.japanese" /></label>
						<form:input type="text" path="japanese" class="base-text"
							value="${japanese}" />
						<span class="error"><form:errors path="japanese"
								cssStyle="color: red" /> </span>
					</div>
					<div>
						<label class="required"><fmt:message
								key="form.lbl.english" /></label>
						<form:input type="text" path="english" class="base-text"
							value="${english}" />
						<span class="error"><form:errors path="english"
								cssStyle="color: red" /></span>
					</div>
					<div class="select_block">
						<label class="required"><fmt:message
								key="form.lbl.difficulty"/></label>
						<select name="difficulty">
							<option value="1">レベル1</option>
							<option value="2">レベル2</option>
							<option value="3">レベル3</option>
						</select>
					</div>
				</fieldset>
				<div class="btns">
					<button type="button" onclick="openModal()" name="insert"
						class="basic_btn">
						<fmt:message key="btn.insert" />
					</button>
					<input type="button" onclick="location.href='back'" value="戻る"
						class="cancel_btn">
				</div>
				<div id="modal">
					<p class="modal_message">
						<fmt:message key="modal.message.insert" />
					</p>
					<div class="btns">
						<form:button type="submit" name="insert" class="basic_btn">
							<fmt:message key="btn.insert" />
						</form:button>
						<button type="button" name="cancel" onclick="closeModal()"
							class="cancel_btn">
							<fmt:message key="btn.cancel" />
						</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js"></script>