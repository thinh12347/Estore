<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h2>Quên mật khẩu</h2>
<h4 class="error">${message }</h4>
<form action="/account/forgot" method="post">
	<div class="form-group">
		<label>Email</label> <input name="id" class="form-control">
		<form:errors path="id" cssClass="error"></form:errors>
	</div>
	<div class="form-group">
		<button class="btn btn-default">Lấy mật khẩu</button>
	</div>
</form>