<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello, ${username}! <a href="ShoppingListServlet?action=logout">Logout</a></p>
        
        <h2>List</h2>
        <form action="ShoppingListServlet" method="post">
            
            Add Item: <input type="text" name="addItem">
            <input type="submit" value="Add">
            
            <input type="hidden" name="action" value="add">
        </form>
        
        <!--show list of items -->
        <form action="ShoppingListServlet" method="post">
            <c:forEach items="${itemList}" var="item">
                <!--<p>${item}</p>-->
                <label><input type="radio" name="deleteItem" value="${item}">${item}<br /></label>
            </c:forEach>
            <c:if test="${itemList.size() > 0}">
                <input type="submit" value="delete">
                <input type="hidden" name="action" value="delete">
            </c:if>
        </form>
    </body>
</html>