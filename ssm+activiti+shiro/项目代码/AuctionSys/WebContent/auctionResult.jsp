<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>竞拍结果</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="wrap">
		<!-- main begin-->
		<div class="sale">
			<h1 class="lf">拍卖结束的商品</h1>
			<div class="right rulse">
				当前用户是：<span class="blue strong"><a href="#" title="张三">${sessionScope.user.username}</a></span>
			</div>
			<div class="cl"></div>
		</div>
		<div class="items">
			<ul class="rows even strong">
				<li>名称</li>
				<li>开始时间</li>
				<li>结束时间</li>
				<li>起拍价</li>
				<li class="list-wd">成交价</li>
				<li class="borderno">买家</li>
			</ul>
			<c:forEach items="${auctionList }" var="auctionCustom" varStatus="state">
				<ul <c:if test="${state.index%2==1 }">class="rows even"</c:if>
					<c:if test="${state.index%2==0 }">class="rows"</c:if>>
					<li><a href="国书" title="">${auctionCustom.auctionname }</a></li>
					<li><fmt:formatDate value="${auctionCustom.auctionstarttime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></li>
					<li><fmt:formatDate value="${auctionCustom.auctionendtime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></li>
					<li>${auctionCustom.auctionstartprice }</li>
					<li class="list-wd">${auctionCustom.auctionPrice }</li>
					<li class="borderno red"><a href="#">${auctionCustom.userName }</a></li>
				</ul>
			</c:forEach>

		</div>
		<h1>拍卖中的商品</h1>
		<div class="items records">
			<ul class="rows even strong rowh">
				<li>名称</li>
				<li>开始时间</li>
				<li>结束时间</li>
				<li>起拍价</li>
				<li class="borderno record">出价记录</li>
				<div class="cl"></div>
			</ul>
			<c:forEach items="${auctionList }" var="auctionCustom" varStatus="state">
				<ul <c:if test="${state.index%2==1 }">class="rows even"</c:if>
					<c:if test="${state.index%2==0 }">class="rows"</c:if>>
					<li><a href="国书" title="">${auctionCustom.auctionname }</a></li>
					<li><fmt:formatDate value="${auctionCustom.auctionstarttime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></li>
					<li><fmt:formatDate value="${auctionCustom.auctionendtime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></li>
					<li>${auctionCustom.auctionstartprice }</li>
					

					<c:forEach items="${auction.auctionRecordList }" var="record">
						<li class=" blue record">
							<p>${record.auctionUser.username }${record.auctionprice }</p>
						</li>
					</c:forEach>
					
				</ul>
			</c:forEach>

		</div>
		<!-- main end-->
		
		 <br/>
	 <input type="button" value="返回列表" class="spbg buttombg f14" 
    		onclick="location='${pageContext.request.contextPath}/auction/queryAuctions'" />
	</div>
	</div>
</body>
</html>