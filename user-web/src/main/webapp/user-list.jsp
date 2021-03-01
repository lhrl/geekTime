<head>
<jsp:directive.include
	file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
<title>My Home Page</title>
</head>
<body>
	<div class="container-lg">
		<h3 style="color: #7abaff">用户列表：</h3>
		<c:forEach items="${userList}" var="keyword" >
			 ${keyword}<br>
		</c:forEach>
	</div>
</body>