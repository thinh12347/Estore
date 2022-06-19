<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h2>Tạo tài khoản</h2>
<h4 class="error">${message}</h4>
<form:form action="/account/register" modelAttribute="form"
	enctype="multipart/form-data">
	<div class="form-group">
		<label>Email</label>
		<form:input path="id" class="form-control" name="id" />
		<form:errors path="id" cssClass="error" ></form:errors>
	</div>
	<div class="form-group">
		<label> Mật khẩu</label>
		<form:input path="password" class="form-control" type="password" />
		<form:errors path="password" cssClass="error"></form:errors>
	</div>
	<div class="form-group">
		<label>Tên</label>
		<form:input path="fullname" class="form-control"/>
		<form:errors path="fullname" cssClass="error"></form:errors>
	</div>
	<div class="form-group">
		<label>Địa chỉ</label>
		<form:input path="Diachi" class="form-control"/>
		<form:errors path="Diachi" cssClass="error"></form:errors>
	</div>
	<div class="form-group">
		<label>Avarta</label> <input type="file" name="photo_file">
		<form:hidden path="photo" class="form-control"/>
	</div>
	<div class="form-group">
		<button class="btn btn-default">Đăng kí</button>
	</div>
</form:form>

