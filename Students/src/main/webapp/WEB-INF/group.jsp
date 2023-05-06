<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Group"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Группы</title>
<head>
<meta charset="UTF-8">
<title>Groups</title>
</head>
<body>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<div id="main">
<section>
<aside class="leftAside">
<h3>Список групп</h3>
<table>
<thead>
<tr>
<th scope="col">Код</th>
<th scope="col">Название</th>
<th scope="col">Факультет</th>
<th scope="col">Курс</th>
<th scope="col">ФО</th>
</tr>
</thead>
<tbody>
<c:forEach var="group" items="${groups}">
<tr>
<td>${group.getId()}</td>
<td>${group.getName()}</td>
<td>${group.getFaculty()}</td>
<td>${group.getYear()}</td>
<td>${group.getType()}</td>
</tr>
</c:forEach>
</tbody>
</table>
</aside>
</section>
<section>
<article>
<h3>Добавление группы</h3>
<div class="text-article">
<form method="POST" action="">
<p>
<label for="name">Название
</label> <input type="text" name="name" />
</p>
<p>
<label for="faculty">Факультет
</label> <input type="text" name="faculty" />
</p>
<p>
<label for="year">Курс
</label> <input type="text" name="year" />
</p>
<p>
<label for="type">Форма обучение
</label> <input type="text" name="type" />
</p>
</form>
<p>
<button type="submit">Добавить</button>
</p>
</div>
</article>
</section>
</div>
<jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>