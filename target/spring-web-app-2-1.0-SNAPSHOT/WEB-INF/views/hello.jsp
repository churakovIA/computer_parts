
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>PARTS</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
        .errorBlock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
        .aa {color: #0038FF; padding: 3px; text-decoration: none;}
    </style>
</head>
<body>
    <h3>PARTS(компьютерные комплектующие)</h3>
    <a href="/addPart" class="aa">Добавить комплектующую</a>
    <p/>
    <form:form  method="POST" action="/list?filter=${filter}"  modelAttribute="part">
        <form:errors path="*" cssClass="errorBlock" element="div" />
        <table>
            <tr>
                <td>
                    <form:label path="name">
                        <spring:message text="Наименование содержит:"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="name" />
                </td>
                <td>
                    <input type="submit"
                           value="<spring:message text="Найти"/>" />
                </td>
            </tr>
        </table>
    </form:form>

    <c:set var="fname" value="${empty name ? '' : '&name='}${name}"/>

    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <p><b>Показать:</b>
        <a href="${contextPath}/list?filter=all${fname}" class="aa">все</a>
        <a href="${contextPath}/list?filter=need${fname}" class="aa">необходимые</a>
        <a href="${contextPath}/list?filter=additional${fname}" class="aa">дополнительные</a>
    </p>
    <div class="pagination">
        <b>Страницы:</b>
        <c:forEach begin="${startpage}" end="${endpage}" var="p">
            <a href="<c:url value="/list"><c:param name="filter" value="${filter}"/><c:param name="page" value="${p}"/>${p}</c:url>${fname}" class="aa">${p}</a>
        </c:forEach>
    </div>
    <table class="tg">
        <tr>
            <th width="120">Наименование</th>
            <th width="120">Необходимость</th>
            <th width="120">Количество</th>
            <th width="60">Редактировать</th>
            <th width="60">Удалить</th>
        </tr>
        <c:if test="${!empty listParts}">
            <c:forEach items="${listParts}" var="part">
                <tr>
                    <td>${part.name}</td>
                    <td align = center>${part.need}</td>
                    <td align = center>${part.count}</td>
                    <td><a href="<c:url value='/edit/${part.id}' />"  class="aa">Редактировать</a></td>
                    <td><a href="<c:url value='/remove/${part.id}' />"  class="aa">Удалить</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <p/>
    <table class="tg">
        <tr>
            <th width="240">Можно собрать</th>
            <th width="120">${computersCount}</th>
            <th width="120">компьютеров</th>
        </tr>
    </table>


</body>
</html>
