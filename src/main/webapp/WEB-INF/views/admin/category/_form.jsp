<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<h2>Danh mục</h2>

<div class="panel panel-default">
	<div class="panel-body">
		<form:form action="${base }/index" modelAttribute="entity">
			<div class="form-group">
				<label>Mã loại</label>
				<form:input path="id" class="form-control" readonly="true"
					placeholder="Auto number" />
				<form:errors path="id" cssClass="error"></form:errors>
			</div>
			<div class="form-group">
				<label>Tên tiếng Anh</label>
				<p style="line-height: 2;" id="nameWarning"></p>
				<form:input path="name" class="form-control" id="name" />
				<form:errors path="name" cssClass="error"></form:errors>
			</div>
			<div class="form-group">
				<label>Tên Việt Nam</label>
				<p style="line-height: 2;" id="phoneWarning"></p>
				<form:input path="nameVN" class="form-control" id="nameVN" />
				<form:errors path="nameVN" cssClass="error"></form:errors>
			</div>
			<div class="form-group">
				<button onClick="changeInformation()" class="btn btn-primary"
					formaction="${base }/create">Thêm</button>
				<button class="btn btn-warning" formaction="${base }/update">Cập nhật</button>
				<a class="btn btn-default" href="${base }/index">Xóa rỗng</a>
			</div>
		</form:form>
	</div>
</div>
