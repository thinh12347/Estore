<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<h2>Khách hàng</h2>

<div class="panel panel-default">
	<div class="panel-body">
		<form:form action="${base }/index" modelAttribute="entity"
			enctype="multipart/form-data">
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Email</label>
					<form:input path="id" class="form-control"
						readonly="${!empty entity.id }" />
				</div>
				<div class="form-group col-sm-6">
					<label>Mật khẩu</label>
					<form:input path="password" class="form-control" type="password" readonly="true"/>
					<form:errors path="password" cssClass="error"></form:errors>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Tên</label>
					<form:input path="fullname" class="form-control"  readonly="true"/>
					<form:errors path="fullname" cssClass="error"></form:errors>
				</div>
				<div class="form-group col-sm-6">
					<label>Kích hoạt</label>
					<div class="form-control">
						<form:radiobutton path="activated" value="true" />
						<span class="m-l">Có</span>
						<form:radiobutton path="activated" value="false" />
						<span class="m-l">Không</span>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<form:hidden path="admin" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-12">
					<img alt="" src="/static/images/customers/${entity.photo }"
						style="width: 80px; height: 90px;"> <input type="file"
						name="photo_file" class="form-control" >
					<form:hidden path="photo" />
				</div>
				<div class="form-group col-sm-12 ">

					<button class="btn btn-warning" formaction="${base }/update">Cập
						nhật</button>
				</div>
			</div>
		</form:form>

	</div>
</div>
