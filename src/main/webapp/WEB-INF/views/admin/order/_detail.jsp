<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<table class="table table-hover">
	<thead>
		<tr>
			<th>Mã sản phẩm</th>
			<th>Tên</th>
			<th>Giá</th>
			<th>Giảm giá</th>
			<th>Số lượng</th>
			<th>Tổng số</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="e" items="${details }">
			<tr>
				<td>${e.product.id }</td>
				<td>${e.product.name }</td>
				<td>${e.unitPrice }</td>
				<td>${e.discount }</td>
				<td>${e.quantity }</td>
				<td>${e.unitPrice*e.quantity*(1-e.discount) }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>