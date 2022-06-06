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
<title>更新</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<div class="header">
		<h1 class="site_logo">
			<a href="menu.jsp">商品管理システム</a>
		</h1>
		<div class="user">
			<p class="user_name">${user.name}さん、こんにちは</p>
			<form class="logout_form" action="logout" method="get">
				<button class="logout_btn" type="submit">
					<img src="images/ドアアイコン.png">ログアウト
				</button>
			</form>
		</div>
	</div>

	<hr>

	<div class="insert">
		<div class="form_body">
			<p class="error">
				<c:if test="${not empty updateErrMsg}">
					<span>${fn:escapeXml(updateErrMsg)}</span>
				</c:if>
			</p>

			<form:form action="update" method="get" modelAttribute="update">
				<fieldset class="label-130">
					<div>
						<label><fmt:message key="form.lbl.productId" /></label>
						<form:input type="text" path="productId" class="base-text"
							value="${product.productId}" />
						<span class="error"><form:errors path="productId"
								cssStyle="color: red" /></span>
					</div>
					<div>
						<label><fmt:message key="form.lbl.productName" /></label>
						<form:input type="text" path="productName" class="base-text"
							value="${product.productName}" />
						<span class="error"> <form:errors path="productName"
								cssStyle="color: red" />
						</span>
					</div>
					<div>
						<label><fmt:message key="form.lbl.price" /></label>
						<form:input type="text" path="price" class="base-text"
							value="${product.price}" />
						<span class="error"><form:errors path="price" cssStyle="color: red" />
						</span>
					</div>
					<div>
						<label><fmt:message key="form.lbl.category" /></label>
						<form:select path="categoryId" class="base-text">
							<form:options items="${categoryList}" itemLabel="categoryName"
								itemValue="categoryId" />
						</form:select>
					</div>
					<div>
						<label><fmt:message key="form.lbl.description" /></label>
						<form:textarea path="description" class="base-text"
							value="${product.description}"></form:textarea>
					</div>
					<div>
						<label>画像</label> <form:input type="file" path="imagePath"/> <span
							class="error">エラーメッセージ</span>
					</div>
				</fieldset>
				<div class="btns">
					<button type="button" onclick="openModal()" class="basic_btn">更新</button>
					<input type="button" onclick="location.href='./back'"
						value="メニューに戻る" class="cancel_btn">
				</div>
				<div id="modal">
					<p class="modal_message">更新しますか？</p>
					<div class="btns">
						<button type="submit" class="basic_btn">更新</button>
						<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js"></script>