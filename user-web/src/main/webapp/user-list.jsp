<head>
<jsp:directive.include
	file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
<title>My Home Page</title>
</head>
<body>
	<div class="container-lg">
		<c:if test="${empty userList}">
			<div id="message" style="color: red;text-align: center" class="h3 mb-3 font-weight-normal">当前没有注册用户</div>
		</c:if>
		<c:if test="${not empty userList}">
		<div class="row">
			<h2>用户列表</h2>

			<table class="table table-striped table-bordered table-condensed">
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>手机号</th>
					<th>邮箱</th>
				</tr>
				<c:forEach items="${userList}" var="user" >
					<tr>
						<td>${user.id}</td>
						<td>${user.name}</td>
						<td>${user.phoneNumber}</td>
						<td>${user.email}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		</c:if>
	</div>
</body>