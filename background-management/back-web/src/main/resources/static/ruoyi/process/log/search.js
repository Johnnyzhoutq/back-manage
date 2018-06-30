var prefix = ctx + "process/log"
var currentUserId = $("#currentUserId").val();
var columns = [{
    checkbox: true
},
{
    field: 'userId',
    title: 'Id'
},
{
    field: 'serviceName',
    title: '操作名称'
},
{
    field: 'coinNum',
    title: '数目'
},
{
    field: 'serviceType',
    title: '操作类型'
},
{
    field: 'time',
    title: '时间'
}
];

$(function() {
	var logType = 13;//默认
	getTableData(currentUserId,logType);
});


function getTableData(currentUserId,logType){
	var url = prefix + "/list/"+currentUserId+"/"+logType;
	$.initChildTableParams(columns, url,queryParams);
}


function queryParams(params) {
	return {
		// 传递参数查询参数
		pageSize:       params.limit,
		pageNum:        params.offset / params.limit + 1,
		searchValue:    params.search,
		orderByColumn:  params.sort,
		isAsc:          params.order,
	};
}

