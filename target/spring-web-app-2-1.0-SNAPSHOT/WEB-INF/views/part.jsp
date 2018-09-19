
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PARTS</title>
</head>
<body>
    <h3>PARTS(компьютерные комплектующие)</h3>

    <form:form method="POST" action="/add" modelAttribute="part">
        <table>
            <c:if test="${!empty part.name}">
                <tr>
                    <td>
                        <form:label path="id">
                            <spring:message text="ID"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="id" readonly="true" size="8"  disabled="true" />
                        <form:hidden path="id" />
                    </td>
                </tr>
            </c:if>
            <tr>
                <td>
                    <form:label path="name">
                        <spring:message text="Наименование"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="name" />
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="need">
                        <spring:message text="Необходимость"/>
                    </form:label>
                </td>
                <td>
                    <form:checkbox path="need" />
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="count">
                        <spring:message text="Количество"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="count" type="number"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:if test="${!empty part.name}">
                        <input type="submit"
                               value="<spring:message text="Изменить"/>" />
                    </c:if>
                    <c:if test="${empty part.name}">
                        <input type="submit"
                               value="<spring:message text="Добавить"/>" />
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>

</body>
</html>