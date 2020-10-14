<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户注册</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>

<script type="text/javascript">

	function checkUserExit(user_name) {
		alert("aaaa")
		$.ajax({
			url:"${pageContext.request.contextPath}/user/checkUserExit",
			type:"post",
			data:{username:user_name},
			success:function(result){
				$("#checkUser").text(result.msg);
			}
		})
		
	}
</script>
</head>

<body>
	<div class="wrap">
		<form action="${pageContext.request.contextPath}/user/register"
			method="post">
			<input type="hidden" name="action" value="doRegister" />
			<div class="zclf login logns">
				<h1 class="blue">用户注册</h1>
				<dl>
					<dd>
						<label> <small>*</small>用户名
						</label> 
						    <input name="username" type="text" value="${registerBean.username }" onblur="checkUserExit(this.value);" class="inputh lf"/> 
							<span class="red">${username}</span>
							<span id="checkUser"></span>
						<div class="lf red laba">用户名要求3~6个字符</div>
					</dd>
					<dd>
						<label> <small>*</small>密码
						</label> <input name="userpassword" type="text" value="${registerBean.userpassword}" class="inputh lf"/> 
							<span class="red">${userpassword}</span>
						<div class="lf red laba">密码要求不低于6个字符</div>
					</dd>
					<dd>
						<label> <small>*</small>身份证号
						</label> <input name="usercardno" type="text" value="${registerBean.usercardno }" class="inputh lf"/> 
							<span class="red">${usercardno}</span>
						<div class="lf red laba">身份证号18位数字</div>
					</dd>
					<dd>
						<label> <small>*</small>电话
						</label> <input name="usertel" type="text" class="inputh lf"
							value="${registerUser.usertel}" /> <span class="red">${usertel}</span>
						<div class="lf red laba">电话号码必须是11位数字</div>
					</dd>
					<dd>
						<label> <small>*</small>住址
						</label> <input name="useraddress" type="text" class="inputh lf"
							value="${registerUser.useraddress}" />
					</dd>
					<dd>
						<label> <small>*</small>邮政编码
						</label> <input name="userpostnumber" type="text" class="inputh lf"
							value="${registerUser.userpostnumber}" />
					</dd>
					<!-- 
					<dd class="hegas">
						<label> <small>*</small>验证码
						</label> <input name="inputCode" type="text" class="inputh inputs lf"
							value="" /> <span class="wordp lf"><img id="validateCode"
							src="Number.jsp" width="96" height="27" alt="" /></span> <span
							class="blues lf"><a id="changeCode"
							href="javascript:void(0);" title="">看不清</a></span>
					</dd> -->
					<dd class="hegas">
						<label>&nbsp;</label> <input type="submit" value="立即注册"
							class="spbg buttombg buttombgs f14 lf" />
					</dd>
				</dl>
			</div>
		</form>
		<!-- main end-->
		<!-- footer begin-->
	</div>
	<!--footer end-->
</body>
</html>
