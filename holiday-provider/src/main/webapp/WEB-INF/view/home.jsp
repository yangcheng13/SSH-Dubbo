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
    <title>holiday-provider Home</title>
    <link href="css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/jscript"></script>
    <script type="text/javascript">
      function modifyCity(id){
    	  var cityNameTd = $("tr#"+id+" td:eq(1)");
    	  var cityPriceTd = $("tr#"+id+" td:eq(2)");
    	  var opButton = $("tr#"+id+" td:eq(4)");
    	  var name = cityNameTd.text();
    	  cityNameTd.empty();
    	  cityNameTd.append("<input id='newName' value='"+name+"'>")
    	  var price = cityPriceTd.text();
    	  cityPriceTd.empty();
    	  cityPriceTd.append("<input id='newPrice' value='"+price+"'>")
    	  opButton.empty();
    	  opButton.append("<button id = 'sure' onclick='postModify(\""+id+"\");'>确认</button><button id = 'cancel' onclick='cancel();' >取消</button>")
      };
      function postModify(appId){
    	  var name = $("#newName").val();
          var price = $("#newPrice").val();
    	  $.ajax({
    		  url : "/holiday-provider/editCity",
    		  type : "POST",
    		  contentType : "application/json;charset=utf-8",
    		  data : JSON.stringify({"appId":appId,"price":price,"city":name}),	 
    		  success : function(result) {
    		      if (result.success == true) {
    		         location.reload();
    		      } 
    		  },
    		  error:function(msg){
    		      alert('Error!');
    		  }
    	  });
      };
      function cancel(){
    	  location.reload();
      };
      function del(appId){
    	  $.get("/holiday-provider/delCity?appId="+appId+"",
                  function(data,status){
                      location.reload();
                  });
      };
      function formatTime(){
    	  var timeTds = $("td#timeTd");
    	  var round = Math.round;
    	  for ( var td in timeTds) {
			var timeL = $(timeTds[td]).text();
			var timeF = Format(new Date(round(timeL)),"yyyy-MM-dd hh:mm:ss");
			$(timeTds[td]).text(timeF);
		}
    	  
      };
      function Format(now,mask)
      {
          var d = now;
          var zeroize = function (value, length)
          {
              if (!length) length = 2;
              value = String(value);
              for (var i = 0, zeros = ''; i < (length - value.length); i++)
              {
                  zeros += '0';
              }
              return zeros + value;
          };
       
          return mask.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g, function ($0)
          {
              switch ($0)
              {
                  case 'd': return d.getDate();
                  case 'dd': return zeroize(d.getDate());
                  case 'ddd': return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][d.getDay()];
                  case 'dddd': return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][d.getDay()];
                  case 'M': return d.getMonth() + 1;
                  case 'MM': return zeroize(d.getMonth() + 1);
                  case 'MMM': return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][d.getMonth()];
                  case 'MMMM': return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][d.getMonth()];
                  case 'yy': return String(d.getFullYear()).substr(2);
                  case 'yyyy': return d.getFullYear();
                  case 'h': return d.getHours() % 12 || 12;
                  case 'hh': return zeroize(d.getHours() % 12 || 12);
                  case 'H': return d.getHours();
                  case 'HH': return zeroize(d.getHours());
                  case 'm': return d.getMinutes();
                  case 'mm': return zeroize(d.getMinutes());
                  case 's': return d.getSeconds();
                  case 'ss': return zeroize(d.getSeconds());
                  case 'l': return zeroize(d.getMilliseconds(), 3);
                  case 'L': var m = d.getMilliseconds();
                      if (m > 99) m = Math.round(m / 10);
                      return zeroize(m);
                  case 'tt': return d.getHours() < 12 ? 'am' : 'pm';
                  case 'TT': return d.getHours() < 12 ? 'AM' : 'PM';
                  case 'Z': return d.toUTCString().match(/[A-Z]+$/);
                  // Return quoted strings with the surrounding quotes removed
                  default: return $0.substr(1, $0.length - 2);
              }
          });
      };
    </script>
</head>
<body>
  <br/>
    <div class="box-123">
      <a id = "add" style='border-left: left' href='/holiday-provider/addCity'>添加</a>
      <table class="table table-bordered">
         <tr>
             <th>城市编号</th>
             <th>城市名称</th>
             <th>旅程價格</th>
             <th>添加時間</th>
             <th>管理操作</th>
          </tr>
        <tbody id="cityCols">
		<c:forEach items="${cityData}" var="ct">
			<tr id ='${ct.appId}'>
				<td>${ct.appId}</td>
				<td>${ct.city}</td>
				<td>${ct.price}</td>
				<td id='timeTd'>${ct.addTime}</td>
				<td>
				<button id = "edit" onclick='modifyCity("${ct.appId}");' name='${ct.appId}'>修改</button>
				<button id = "del"  onclick='del("${ct.appId}");' name='${ct.appId}'>删除</button>
				</td>
			</tr>
		</c:forEach>
	   </tbody>        
     </table>
   </div>
</body>
</html>