<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<fmt:setLocale value="${pageContext.request.locale.language}" />
<fmt:setBundle basename="com.internousdev.JissenKadai4.property.glopass"
	var="lang" />
</head>
<body>

	<s:form action="LoginAction">
		<s:textfield label="ID" name="id" />
		<!-- getTextでpropetiesに記述したプロパティ名を書く -->
		<s:textfield label="%{getText('lang.login.password')}" name="password" />
		<s:submit value="%{getText('lang.login.accept')}" />
	</s:form>
</body>
</html>
