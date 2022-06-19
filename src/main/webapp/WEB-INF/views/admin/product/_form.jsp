<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<h2>Sản Phẩm</h2>

<div class="panel panel-default">
	<div class="panel-body">
		<form:form action="${base }/index" modelAttribute="entity"
			enctype="multipart/form-data">
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Mã sản phẩm</label>
					<form:input path="id" class="form-control" readonly="true"
						placeholder="Auto number" />
				</div>
				<div class="form-group col-sm-6">
					<label>Tên sản phẩm</label>
					<form:input path="name" class="form-control" />
					<form:errors path="name" cssClass="error"></form:errors>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Giá</label>
					<form:input path="unitPrice" class="form-control" type="number" />
					<form:errors path="unitPrice" cssClass="error"></form:errors>
				</div>
				<div class="form-group col-sm-6">
					<label>Danh mục</label>
					<form:select path="category.id" class="form-control">
						<form:options items="${cates}" itemLabel="nameVN" itemValue="id"></form:options>
					</form:select>
				</div>

				<form:hidden path="quantity" class="form-control" />

			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Giảm giá</label>
					<form:input path="discount" class="form-control" type="number"
						min="0" max="1" step="0.1" value="0" />
					<form:errors path="discount" cssClass="error"></form:errors>
				</div>
				<div class="form-group col-sm-6">
					<label>Ngày sản xuất</label>
					<form:input path="productDate" class="form-control" type="date" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Đặc biệt</label>
					<div class="form-control">
						<form:radiobutton path="special" value="true"/><span class="m-l">Có</span>
						<form:radiobutton path="special" value="false"
							checked="checked" /><span class="m-l">Không</span>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-sm-12">
					<img alt="" src="/static/images/product/${entity.image }"
						style="width: 80px; height: 90px;"> <input type="file"
						name="image_file" class="form-control">
					<form:hidden path="image" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-12">
					<label>Mô tả</label>
					<form:textarea path="description" rows="3" class="form-control" />
				</div>
				<div class="form-group col-sm-12 ">
					<button class="btn btn-primary" formaction="${base }/create">Thêm</button>
					<button class="btn btn-warning" formaction="${base }/update">Cập nhật</button>
					<a class="btn btn-default" href="${base }/index">Xóa rỗng</a>
				</div>
			</div>
			<form:hidden path="viewCount" />
		</form:form>
	</div>
</div>
<script type="text/javascript">
	bkLib.onDomLoaded(function() {
		nicEditors.allTextAreas()
	});
</script>