var prefix = ctx + "process/person"


$(document).ready(function(){
	$('body').layout({ west__size: 185 });
	queryUserList();
});

function queryUserList() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'userId',
            title: 'Id'
        },
        {
            field: 'userName',
            title: '用户昵称'
        },
        {
            field: 'mobile',
            title: '手机号码'
        },
        {
            field: 'email',
            title: '电子邮件'
        },
        {
            field: 'createTime',
            title: '注册时间'
        },
        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
            	actions.push('<a class="btn btn-info btn-xs ' + listFlag + '" href="#" onclick="detail(\'' + row.userId + '\')"><i class="fa fa-list-ul"></i>业绩</a> ');
            	actions.push('<a class="btn btn-warning btn-xs ' + logFlag + '" href="#" onclick="log(\'' + row.userId + '\')"><i class="fa fa-search"></i>日志</a>');
            	return actions.join('');
            }
        }
        ];
	var url = prefix + "/list";
	$.initTableParams(columns, url, queryParams);
}


/*用户业绩-详细*/
function detail(id) {
    var url = prefix + '/detail/' + id;  
    layer_showAuto("用户业绩详细", url);
}


/*用户--日志*/
function log(userId) {
	var pre = ctx + "process/log"
    var url = pre + '/search/' + userId;
	layer_show("用户日志查询", url, 1300, null);
}


function queryParams(params) {
	return {
		// 传递参数查询参数
		pageSize:       params.limit,
		pageNum:        params.offset / params.limit + 1,
		searchValue:    params.search,
		orderByColumn:  params.sort,
		isAsc:          params.order
	};
}













