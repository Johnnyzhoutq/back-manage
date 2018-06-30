var prefix = ctx + "process/price"


$(document).ready(function (){
	$('body').layout({ west__size: 185 });
});

/*用户管理-新增*/
function addPrice() {
    var url = prefix + '/add';
    layer_showAuto("修改牌价", url);
}


