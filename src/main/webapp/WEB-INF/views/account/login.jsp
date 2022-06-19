<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h2>Đăng nhập</h2>
<h4 class="error">${message }</h4>
<div class="panel panel-default">
	<div class="panel-body">
		<form action="/account/login" method="post">
			<div class="form-group">
				<label>Email</label> <input name="id" class="form-control"
					value="${uid }" id="id">

			</div>
			<div class="form-group">
				<label>Mật khẩu</label> <input name="pw" class="form-control"
					value="${pwd}" type="password" id="password">
			</div>
			
			<div class="form-group">
				<button class="btn btn-default">Đăng nhập</button>
			</div>
		</form>
	</div>
</div>