<%@ page language="java" contentType="text/html"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Student"%>
<%@ page import="domain.Group"%>


<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Студенты</title>
<head>
<meta charset="UTF-8">
<title>Students</title>
</head>
<body>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<div id="main">
<aside class="leftAside">
<h3>Список студентов</h3>
<table>
<thead>
<tr>
<th>Код</th>
<th>Фамилия</th>
<th>Имя</th>
<th>Отчество</th>
<th>Группа</th>
<th>Дата рождения</th>
<th>Телефон</th>
<th>Эл. почта</th>
</tr>
</thead>
<tbody>
<c:forEach var="student" items="${students}">
<tr>
<td>${student.getId()}</td>
<td>${student.getLastname()}</td>
<td>${student.getName()}</td>
<td>${student.getFname()}</td>
<td>${student.getGroup()}</td>
<td>${student.getBdate()}</td>
<td>${student.getPhone()}</td>
<td>${student.getEmail()}</td>
</tr>
</c:forEach>
</tbody>
</table>
</aside>
<section>
<article>
<h3>Данные по студенту</h3>
<div class="text-article">
<form method="POST" action="">
<p>
<label for="lastname">Фамилия</label>
<input type="text" name="lastname" />
</p>
<p>
<label for="name">Имя</label>
<input type="text" name="name" />
</p>
<p>
<label for="fname">Отчество</label>
<input type="text" name="fname" />
</p>
<p>
<label for="group">Группа</label>
<select name="group">
<option disabled>Выберите группу</option>
<c:forEach var="group" items="${groups}">
<option value="${group}">
<c:out value="${group.getName()}"></c:out>
</option>
</c:forEach>
</select>
</p>
<p>
<label for="bdate">Дата рождения</label>
<input type="text" name="bdate" />
</p>
<p>
<label for="phone">Телефон</label>
<input type="text" name="phone" />
</p>
<p>
<label for="email">Эл. почта </label>
<input type="text" name="email" />
</p>
<p>
<button type="submit">Добавить</button>
</p>
</form>

</div>
</article>
</section>
</div>
<jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>
