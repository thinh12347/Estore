<%@ page pageEncoding="utf-8"%>
<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Gửi cho bạn bè</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>Email người gửi</label> <input id="sender"
						class="form-control" type="email">

				</div>
				<div class="form-group">
					<label>Email người nhận</label> <input id="email"
						class="form-control" type="email">

				</div>
				<div class="form-group">
					<label>Nội dung</label>
					<textarea id="comments" class="form-control" rows="3"></textarea>
				</div>
				<input type="hidden" id="id">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default btn-send">Gửi</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
			</div>
		</div>

	</div>
</div>

