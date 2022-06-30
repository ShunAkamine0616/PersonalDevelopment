<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="page.index.title" /></title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body class="login_body">
	<div class="header">
		<h1 class="site_logo">
			<fmt:message key="site_logo" />
		</h1>
	</div>

	<div class="login_form">
		<img src="./images/logo.png" class="login_logo">
		<p class="error">
			<c:if test="${not empty loginErrMsg}">
				<span>${fn:escapeXml(loginErrMsg)}</span>
			</c:if>
		</p>
		<form:form action="login" modelAttribute="index" method="post">
			<fieldset>
				<div class="cp_iptxt">
					<form:input class="base_input" path="loginId" placeholder="ID" />
					<i class="fa fa-user fa-lg fa-fw" aria-hidden="true"></i>
					<div class="error">
						<form:errors path="loginId" cssStyle="color: red" />
					</div>
				</div>

				<div>
					<form:input class="base_input" type="password" path="password"
						placeholder="PASS" />
					<div class="error">
						<form:errors path="password" cssStyle="color: red" />
					</div>
				</div>
			</fieldset>
			<form:button name="login" class="btn-square-shadow">
				<fmt:message key="form.lbl.login" />
			</form:button>
			<%-- 			<form:button name="login"><fmt:message key="form.lbl.signup"/></form:button> --%>
		</form:form>
		<form action="registerInput" method="get">
			<button class="basic_btn regist" name="register">
				<fmt:message key="form.lbl.signup" />
			</button>
		</form>
	</div>
</body>
</html>
