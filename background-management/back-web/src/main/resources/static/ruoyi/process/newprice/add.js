$("#form-user-add").validate({
	submitHandler:function(form){
		add();
	}
});

function add() {
	var price = $("input[name='price']").val();
	var selectDate = $("input[name='selectDate']").val();
	$.ajax({
		cache : true,
		type : "POST",
		url : ctx + "process/newprice/save",
		data : {
			"price": price,
			"selectDate": selectDate,
			
		},
		async : false,
		error : function(request) {
			$.modalAlert("系统错误", modal_status.FAIL);
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("修改成功,正在刷新数据请稍后……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
					$.parentReload();
				});
			} else {
				$.modalAlert(data.msg, modal_status.FAIL);
			}

		}
	});
}
