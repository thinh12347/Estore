<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h2>Đổi mật khẩu</h2>
<h4 class="error">${message }</h4>
<form:form action="/account/change" method="post" modelAttribute="form">
	<div class="form-group">
		<label>Email</label>
		<form:input path="id" class="form-control" readonly="true" />
	</div>
	<div class="form-group">
		<label>Mật khẩu hiện tại</label> <input name="pw" class="form-control" required="required"
			type="password">
		<form:errors path="password" cssClass="error"></form:errors>
	</div>
	<div class="form-group">
		<label>Mật khẩu mới</label> <input name="pw1" class="form-control"
			type="password" pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,64})"
			title="Nhập password từ 6-64 ký tự, ít nhất một chữ hoa, một chữ thường và một số"
			required="required">
	</div>
	<div class="form-group">
		<label>Xác nhận mật khẩu mới</label> <input name="pw2"
			class="form-control" type="password" required="required">

	</div>
	<div class="form-group">
		<form:hidden path="fullname" />
		<form:hidden path="photo" />
	</div>
	<div class="form-group">
		<button class="btn btn-default">Đổi</button>
	</div>
</form:form>