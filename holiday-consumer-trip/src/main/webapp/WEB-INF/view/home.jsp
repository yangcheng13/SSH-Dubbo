<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.UUID" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD//XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
    <title>holiday-cunsumer Home</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/jscript"></script>
</head>
<body>
  <br/>
    <div class="box-123">
      <table class="table table-bordered">
         <tr>
             <th>城市编号</th>
             <th>城市名称</th>
             <th>旅程價格</th>
             <th>添加時間</th>
          </tr>
        <tbody id="cityCols">
		<c:forEach items="${cityData}" var="ct">
			<tr id ='${ct.appId}'>
				<td>${ct.appId}</td>
				<td>${ct.city}</td>
				<td>${ct.price}</td>
				<td>${ct.addTime}</td>
			</tr>
		</c:forEach>
	   </tbody>        
     </table>
   </div>
</body>
</html>