<%@ page language="java" contentType="text/html"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Student"%>
<%@ page import="domain.Group"%>
<%
Group r1 = new Group(1l, "ИСТ-321", "КТиИБ", "2", "очная");
Group r2 = new Group(2l, "ИСТ-331", "КТиИБ", "3", "очная");
Group r3= new Group(3l, "ИСТ-332", "КТиИБ", "3", "очная");
Group r4 = new Group(4l, "ЭК-525Z", "ЭиФ", "2", "заочная");
Group[] groups = new Group[]{r1, r2, r3, r4};
pageContext.setAttribute("groups", groups);
Student p1 = new Student(1l, "Иванов", "Иван", "Иванов", "15.01.2003", "+7(961)-289-55-24", "ivanov@mail.ru", 1l, r1);
Student p2 = new Student(2l, "Блохин", "Антон", "Владимирович", "19.04.2002", "+7(800)-555-35-35", "ant@gmail.com", 2l, r2);
Student p3= new Student(3l, "Сидор", "Сидоров", "-", "08.02.2002", "+7(961)-289-33-57", "sidorov@mail.ru", 3l, r3);
Student p4 = new Student(4l, "Петров", "Петр", "Петрович", "24.03.2002", "+7(961)-289-44-39", "petrov@mail.ru", 4l, r4);
Student[] students = new Student[]{p1, p2, p3, p4};
pageContext.setAttribute("students", students);
%>

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
<select>
<option disabled>Выберите группу</option>
<c:forEach var="group" items="${groups}">
<option value="${group}">
${group.getName()}
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
