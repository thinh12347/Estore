<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<h2>Order Form</h2>

<div class="panel panel-default">
	<div class="panel-body">
		<form:form action="${base }/index" modelAttribute="entity">
			
			<div class="form-group">
				<label>Mã đơn hàng</label>
				<form:input path="id" class="form-control" readonly="${!empty entity.id }" />
			</div>
			<div class="form-group">
				<label>Email</label>
				<form:input path="customer.id" readonly="${!empty entity.customer.id }" class="form-control" />
			</div>
			<div class="form-group">
				<label>Tên</label>
				<form:input path="customer.fullname" readonly="${!empty entity.customer.fullname }"  class="form-control" />
			
			</div>
			<div class="form-group">
				<label>Ngày đặt hàng</label>
				<form:input path="orderDate" readonly="${!empty entity.orderDate }" class="form-control" />
			</div>
			
			<div class="form-group">
				<label>Tổng cộng</label>
				<form:input path="amount" readonly="${!empty entity.amount }" class="form-control" />
			</div>
			<div class="form-group">
				<label>Mô tả</label>
				<form:textarea path="description" rows="3" class="form-control" />
			</div>
			<div class="form-group">
				<button class="btn btn-warning" formaction="${base }/update">Cập nhật</button>
				
			</div>
		</form:form>
		<c:if test="${!empty details }">
				<jsp:include page="_detail.jsp"></jsp:include>
		
		</c:if>
	</div>
</div>
