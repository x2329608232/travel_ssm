<%--
  Created by IntelliJ IDEA.
  User: AnYu
  Date: 2020/10/28
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <c:forEach items="${list}" var="pro">
            主键    ${pro.id}<br>
            编号    ${pro.productNum}<br>
            名称    ${pro.productName}<br>
            出发城市   ${pro.cityName}<br>
            出发时间  ${pro.departureTime}
                ${pro.departureTimeStr}<br>
            产品价格  ${pro.productPrice}<br>
            产品描述   ${pro.productDesc}<br>
            状态  ${pro.productStatus}<br>
            ${pro.productStatusStr}<br>
        </c:forEach>

</body>
</html>
