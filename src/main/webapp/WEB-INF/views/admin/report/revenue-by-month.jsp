<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<h2 class="alert alert-success">Doanh thu theo tháng</h2>
<table class="table table-hover">
	<thead>
		<tr>
			<th>Tháng</th>
			<th>Số lượng</th>
			<th>Doang thu</th>
			<th>Nhỏ nhất</th>
			<th>Lớn nhất</th>
			<th>Trung bình</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="e" items="${data }">
			<tr>
				<td>${e[0] }</td>
				<td>${e[1] }</td>
				<td><f:formatNumber value="${e[2] }" pattern="#,###.000"></f:formatNumber>VNĐ</td>
				<td><f:formatNumber value="${e[3] }" pattern="#,###.000"></f:formatNumber>VNĐ</td>
				<td><f:formatNumber value="${e[4]}" pattern="#,###.000"></f:formatNumber>VNĐ</td>
				<td><f:formatNumber value="${e[5] }" pattern="#,###.000"></f:formatNumber>VNĐ</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
