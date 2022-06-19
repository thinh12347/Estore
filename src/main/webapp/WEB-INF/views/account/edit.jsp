<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h2>Chỉnh sửa hồ sơ</h2>
<h4 class="error">${message }</h4>
<form:form action="/account/edit" modelAttribute="form"
	enctype="multipart/form-data">
	<div class="form-group">
		<label>Email</label>
		<form:input path="id" class="form-control" readonly="true" />

	</div>
	<div class="form-group">
		<label>Tên</label>
		<form:input path="fullname" class="form-control" />
		<form:errors path="fullname" cssClass="error"></form:errors>
	</div>
	<div class="form-group">
		<img alt="" src="/static/images/customers/${form.photo }"
			style="width: 80px; height: 90px;"> <input type="file"
			name="photo_file">
		<form:hidden path="photo" class="form-control" />
	</div>
	<div class="form-group">
		<label>Địa chỉ</label>
		<form:input path="Diachi" class="form-control" />
		<form:errors path="Diachi" cssClass="error"></form:errors>
	</div>
	<div class="form-group">
		<form:hidden path="password" />
		<form:hidden path="activated" />
		<form:hidden path="admin" />
	</div>
	<button class="btn btn-default">Cập nhật</button>
</form:form>