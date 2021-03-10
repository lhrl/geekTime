<head>
<jsp:directive.include file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
	<title>My Home Page</title>
    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
</head>
<body>
	<div class="container">
		<c:if test="${not empty message}">
			<div id="message" style="color: red;text-align: center" class="success"><c:out value="${message}"/></div>
		</c:if>
		<form class="form-signin" method="post" action="register">
			<h1 class="h3 mb-3 font-weight-normal">用户注册</h1>
			<label for="name" n class="sr-only">姓名</label> <input
				type="text" name="name" id="name" value="${registerName}" class="form-control"
				placeholder="请输入姓名" required autofocus><br/>
			<label for="inputEmail" class="sr-only">电子邮件</label> <input
				type="email" name="email" id="inputEmail" value="${registerEmail}" class="form-control"
				placeholder="请输入电子邮件" required autofocus>
			<label for="phoneNumber" class="sr-only">手机号</label><br/> <input
				type="text"  name="phoneNumber" id="phoneNumber" value="${registerPhoneNumber}" class="form-control"
				placeholder="请输入手机号" required>
			<label for="inputPassword" class="sr-only">密码</label><br/> <input
				type="password"  name="password" id="inputPassword" class="form-control"
				placeholder="请输入密码" required><br/>
			<button class="btn btn-lg btn-primary btn-block" type="submit">立即注册</button>
			<p class="mt-5 mb-3 text-muted">&copy; 2017-2021</p>
		</form>
	</div>
</body>