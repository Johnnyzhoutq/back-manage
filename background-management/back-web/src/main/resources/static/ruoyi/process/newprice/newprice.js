var prefix = ctx + "process/newprice"

$(document).ready(function() {
	$('body').layout({
		west__size: 185
	});
	var b = new Date();
	var c = b.getDate();
	var a = b.getMonth();
	var e = b.getFullYear();
	$("#calendar").fullCalendar({
		 height: 750,
		 header: {
			left: "prev,next",
			center: "title",
			right: "month,agendaWeek,agendaDay"
		},

		events: function(start, end, callback) {
			var date = b.getDate();
			$.ajax({
				url: prefix + "/search",
				type: "GET",
				data: {
					date: date
				},
				success: function(data) { // 获取当前月的数据
					var events = [];
					for (var i = 0; i < data.length; i++) {
						var title = data[i].title;
						var evtstart = new Date(Date.parse(data[i].start));
						var evtend = new Date(Date.parse(data[i].end));
						events.push({
							title: title,
							start: evtstart,
							end: evtend,
						});
					}
					callback(events);
				}
			});
		},

		dayClick: function(date, allDay, jsEvent, view) { //日期点击后弹出的jq ui的框，添加记录
			var selectDate = formatDate(date);
			
			var currentTime = new Date();
			var currentDate = formatDate(currentTime);
		
			if(new Date(selectDate.replace("-","/")) < new Date(currentDate.replace("-","/"))) {
				 $.modalAlert("亲 , 牌价已过期不能编辑了!!", modal_status.FAIL);
				 return;
			}

			var url = prefix + "/add/" + selectDate;
			layer_show("牌价管理", url, 800, 300);
		}
	});

});

function formatDate(date){
	// 修改牌价的弹窗
	var seperator1 = "-";
	
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var selectDate = date.getFullYear() + seperator1 + month + seperator1 + strDate ;
	
	return selectDate;
}
