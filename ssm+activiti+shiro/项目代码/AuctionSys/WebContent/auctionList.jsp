<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/WebCalendar.js"></script>
</head>
<body>
<div class="wrap">
<!-- main begin-->
  <div class="sale">
    <h1 class="lf">在线拍卖系统</h1>
    <div class="logout right"><a href="${pageContext.request.contextPath}/user/layout" title="注销">注销</a></div>
  </div>
  <div class="forms">
  <form id="form_query" action="${pageContext.request.contextPath}/auction/queryAuctions" method="post">
  <input name="pageNum" id="page" type="hidden" value="1"/>
    <label for="name">名称</label>
    <input name="auctionname" type="text" value="${condition.auctionname}" class="nwinput" id="name"/>
    <label for="names">描述</label>
    <input name="auctiondesc" type="text" value="${condition.auctiondesc}" id="names" class="nwinput"/>
    <label for="time">开始时间</label>
   <input name="auctionstarttime" 
            value="<fmt:formatDate value="${condition.auctionstarttime}" pattern="yyyy-MM-dd HH:mm:ss" />"
        	type="text" id="time" class="nwinput" readonly="readonly" onclick="selectDate(this,'yyyy-MM-dd hh:mm:ss')"/>
        
    <label for="end-time">结束时间</label>
    <input name="auctionendtime"
        	value="<fmt:formatDate value="${condition.auctionendtime}" pattern="yyyy-MM-dd HH:mm:ss" />"
        	type="text" id="end-time" class="nwinput" readonly="readonly" onclick="selectDate(this,'yyyy-MM-dd hh:mm:ss')"/>
    
    <label for="price">起拍价</label>
    <input name="auctionstartprice" type="text" value="${condition.auctionstartprice}" id="price" class="nwinput" />
    <input name="" type="submit"  value="查询" class="spbg buttombg f14  sale-buttom"/>
    <c:if test="${sessionScope.user.userisadmin eq 0}">
    <a href="${pageContext.request.contextPath}/auction/findCompleteResult"><input type="button"  value="竞拍结果" class="spbg buttombg f14  sale-buttom buttomb"/></a>
    </c:if>
    <c:if test="${sessionScope.user.userisadmin eq 1}">
    <input type="button"  value="发布" class="spbg buttombg f14  sale-buttom buttomb"/>
    </c:if>
  </form>
  </div>
  
  <div class="items">
      <ul class="rows even strong">
        <li>名称</li>
        <li class="list-wd">描述</li>
        <li>开始时间</li>
        <li>结束时间</li>
        <li>起拍价</li>
        <li class="borderno">操作</li>
      </ul>
     <c:forEach items="${auctionList }" var="auction" varStatus="state">
      <ul 
      <c:if test="${state.index%2==1 }">class="rows even"</c:if>
      <c:if test="${state.index%2==0 }">class="rows"</c:if>
      >
        <li><a href="国书" title="">${auction.auctionname}</a></li>
        <li class="list-wd">${auction.auctiondesc}</li>
        <li>
        	<fmt:formatDate value="${auction.auctionstarttime }" pattern="yyyy-MM-dd hh:mm:ss"/>
        </li>
        <li>
        	<fmt:formatDate value="${auction.auctionendtime }" pattern="yyyy-MM-dd hh:mm:ss"/>
        </li>
        <li>${auction.auctionpic}</li>
        <li class="borderno red">
          <c:if test="${sessionScope.user.userisadmin eq 0 }">
          <a href="${pageContext.request.contextPath }/auction/completePrice/${auction.auctionid}" >竞拍</a>
          </c:if>
          <c:if test="${sessionScope.user.userisadmin eq 1 }">
           <a href="${pageContext.request.contextPath }/auction/toDetail/${auction.auctionid}" title="修改">修改</a>
          <a href="#" title="竞拍" onclick="abc();">删除</a>
          </c:if>
        </li>
      </ul>
     </c:forEach>
      <div class="page">
               【当前第${pageInfo.pageNum}页，总${pageInfo.pages }共页，总共${pageInfo.total}条记录】
        <a href="javascript:jumpPage(1)" >首页</a>
        <a href="javascript:jumpPage(${pageInfo.prePage})" >上一页</a>
        <a href="javascript:jumpPage(${pageInfo.nextPage})">下一页</a>
        <a href="javascript:jumpPage(${pageInfo.pages})">尾页</a> 
      </div>
  </div>
  <script>
  function jumpPage(page_no) {
	  document.getElementById("page").value=page_no;
	  document.getElementById("form_query").submit();
	
}
  </script>
<!-- main end-->
</div>
</body>
</html>
