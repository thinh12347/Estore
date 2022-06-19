<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<table class="table table-hover">
	<thead>
		<tr>
			<th>Email</th>
			<th>Tên</th>
			<th>Kích hoạt</th>
			<th>Admin</th>
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
		url : '/pager/customer/page-count',
		success: function (response) {
			pageCount=response;
			$(".pager a:eq(0)").click();
		}
	});
	$(".pager a:eq(0)").click(function() {
			pageNo = 0;
			$.ajax({
				url : '/pager/customer/page/'+pageNo,
				success :fnSuccess
			})
		return false;
	});
	
	$(".pager a:eq(1)").click(function() {
		if(pageNo > 0){
			pageNo--;
			$.ajax({
				url : '/pager/customer/page/'+pageNo,
				success :fnSuccess
			})
		}
		return false;
	});
	
	$(".pager  a:eq(3)").click(function() {
		if(pageNo < pageCount-1){
			pageNo++;
			$.ajax({
				url : '/pager/customer/page/'+pageNo,
				success :fnSuccess
			});
		}
		return false;
	});
	
	$(".pager  a:eq(4)").click(function() {
		pageNo = pageCount - 1;
		$.ajax({
			url : '/pager/customer/page/'+pageNo,
			success :fnSuccess
		})
		return false;
	});
	
	 function fnSuccess(response) {
			$("#tbody").html("");
			$(response).each(function(index, user) {
				var tr=$("<tr/>");
				$("<td/>").html(user.id).appendTo(tr);
				$("<td/>").html(user.fullname).appendTo(tr);
				$("<td/>").html(user.activated?'Có':'Không').appendTo(tr);
				$("<td/>").html(user.admin?'Admin':'User').appendTo(tr);
				var s=`<td class="text-center">
					<a class="btn btn-sm btn-info" href="/admin/customer/edit/`+user.id+`">Sửa</a>
					<a class="btn btn-sm btn-danger" href="/admin/customer/delete/`+user.id+`" onclick="if (!(confirm('Bạn có muốn xóa? tất cả đơn hàng của khách cũng bị xóa'))) return false">Xóa</a>
					</td>`;
				$(s).appendTo(tr);
				tr.appendTo("#tbody");
				$("#info").html((pageNo+1) +'/' + pageCount);
			})
		}
	
</script>