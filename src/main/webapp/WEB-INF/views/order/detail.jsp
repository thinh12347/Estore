<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<h2>THÔNG TIN ĐƠN HÀNG</h2>

<form:form action="/order/checkout" modelAttribute="order">
	<div class="form-group">
		<label>Mã đơn hàng</label>
		<form:input path="id" class="form-control" readonly="true" />
	</div>
	<div class="form-group">
		<label>Email</label>
		<form:input path="customer.id" class="form-control" readonly="true" />
	</div>
	<div class="form-group">
		<label>Ngày đặt hàng</label>
		<form:input path="orderDate" class="form-control" readonly="true" />
	</div>
	<div class="form-group">
		<label>Địa chỉ giao hàng</label>
		<form:input path="customer.Diachi" class="form-control"
			 />
	</div>
	<div class="form-group">
		<label>Tổng cộng</label>
		<form:input path="amount" class="form-control" readonly="true" />
	</div>
	<div class="form-group">
		<label>Mô tả</label>
		<form:textarea path="description" rows="3" class="form-control"
			/>
	</div>
	<div class="form-group">
		<button class="btn btn-warning" formaction="/order/update">Cập nhật</button>

	</div>
</form:form>
<h2>CHI TIẾT ĐẶT HÀNG</h2>
<table class="table">
	<thead>
		<tr>
			<th>Mã sản phẩm</th>
			<th>Tên sản phẩm</th>
			<th>Giá</th>
			<th>Giảm giá</th>
			<th>Số lượng</th>
			<th>Tổng cộng</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="d" items="${details }">
			<tr>
				<td>${d.product.id }</td>
				<td>${d.product.name }</td>
				<td>${d.unitPrice }</td>
				<td>${d.discount }</td>
				<td>${d.quantity }</td>
				<td>${d.quantity * d.unitPrice * (1 - d.discount) }</td>

			</tr>
		</c:forEach>
	</tbody>
</table>