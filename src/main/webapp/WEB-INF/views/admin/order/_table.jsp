<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<table class="table table-hover">
	<thead>
		<tr>
			<th>Mã đơn hàng</th>
			<th>Email</th>
			<th>Ngày đặc hàng</th>
			<th>Tổng cộng</th>
			<th></th>
		</tr>
	</thead>
	<tbody id="tbody">

	</tbody>
</table>
<ul class="pager">
	<li><a href="#"><i class="glyphicon glyphicon-hand-up"></i></a></li>
	<li><a href="#"><i class="glyphicon glyphicon-hand-left"></i></a></li>
	<li><a href="#"><span id="info"></span></a></li>
	<li><a href="#"><i class="glyphicon glyphicon-hand-right"></i></a></li>
	<li><a href="#"><i class="glyphicon glyphicon-hand-down"></i></a></li>
</ul>

<script>
	var pageNo = 0;
	var pageCount = 0;
	var pageSize = 8;
	$.ajax({
		url : '/pager/order/page-count',
		success: function (response) {
			pageCount=response;
			$(".pager a:eq(0)").click();
		}
	});
	$(".pager a:eq(0)").click(function() {
			pageNo = 0;
			$.ajax({
				url : '/pager/order/page/'+pageNo,
				success :fnSuccess
			})
		return false;
	});
	
	$(".pager a:eq(1)").click(function() {
		if(pageNo > 0){
			pageNo--;
			$.ajax({
				url : '/pager/order/page/'+pageNo,
				success :fnSuccess
			})
		}
		return false;
	});
	
	$(".pager  a:eq(3)").click(function() {
		if(pageNo < pageCount-1){
			pageNo++;
			$.ajax({
				url : '/pager/order/page/'+pageNo,
				success :fnSuccess
			});
		}
		return false;
	});
	
	$(".pager  a:eq(4)").click(function() {
		pageNo = pageCount - 1;
		$.ajax({
			url : '/pager/order/page/'+pageNo,
			success :fnSuccess
		})
		return false;
	});
	
	 function fnSuccess(response) {
			$("#tbody").html("");
			$(response).each(function(index, user) {
				var tr=$("<tr/>");
				$("<td/>").html(user.id).appendTo(tr);
				$("<td/>").html(user.customer.id).appendTo(tr);
				$("<td/>").html(user.orderDate).appendTo(tr);
				$("<td/>").html(user.amount).appendTo(tr);
				var s=`<td class="text-center">
					<a class="btn btn-sm btn-info" href="/admin/order/edit/`+user.id+`">Edit</a>
					<a class="btn btn-sm btn-danger" href="/admin/order/delete/`+user.id+`" onclick="if (!(confirm('Are you sure?'))) return false">Delete</a>
					</td>`;
				$(s).appendTo(tr);
				tr.appendTo("#tbody");
				$("#info").html((pageNo+1) +'/' + pageCount);
			})
		}
	
</script>