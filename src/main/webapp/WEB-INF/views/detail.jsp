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
<title>商品詳細</title>
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

	<div class="update">
		<div class="form_body">
			<div class="img_block">
				<img src="images/マッキー.png" class="product_img"><br>
			</div>
			<form action="delete?productId=${product.getProductId()}"
				method="get">
				<fieldset class="label-130 product_block">
					<p class="error">
						<c:if test="${not empty deleteErrMsg}">
							<span>${fn:escapeXml(deleteErrMsg)}</span>
						</c:if>
					</p>
					<div>
						<label><fmt:message
								key="form.lbl.productId" /></label> <input type="text" name="productId"
							value="${product.productId}" readonly class="base-text">
					</div>
					<div>
						<label><fmt:message
								key="form.lbl.productName" /></label> <input type="text" name="productName"
							value="${product.name}" readonly class="base-text">
					</div>
					<div>
						<label><fmt:message
								key="form.lbl.price" /></label> <input type="text" name="price"
							value="${product.price}" readonly class="base-text">
					</div>
					<div>
						<label><fmt:message
								key="form.lbl.category" /></label> <input type="text" name="category"
							value="${product.category.categoryName}" readonly class="base-text">
					</div>
					<div>
						<label><fmt:message
								key="form.lbl.description" /></label>
						<textarea name="description" readonly class="base-text"
							style="background-color: rgb(209, 209, 209);">
            ${product.getDescription()}
            </textarea>
					</div>
				</fieldset>
				<div>
					<div class="btns">
						<!--             <input type="submit" onclick="openModal()" value="削除" class="basic_btn" name="btn"> -->
						<!--             <input type="submit" onclick="location.href='./updateInput.html'" value="編集" class="basic_btn" name="btn"> -->
						<!--             <input type="submit" onclick="location.href='./menu.html'" value="戻る" class="cancel_btn" name="btn"> -->
						<c:if test="${user.role == 1}">
							<input type="button" onclick="openModal()" value="削除"
								class="basic_btn" name="btn">
							<input type="button"
								onclick="location.href='./updateInput'"
								value="編集" class="basic_btn" name="btn">
						</c:if>
						<input type="button" onclick="location.href='back'"
						value="戻る" class="cancel_btn">
					</div>
					<div id="modal">
						<p class="modal_message">削除しますか？</p>
						<div class="btns">
							<button type="submit" class="basic_btn"><fmt:message key="btn.delete" /></button>
							<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js"></script>