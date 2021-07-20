$(document).ready(function(){
	$("#calendar").fullCalendar({
		locale: "ko",
		edittable: true,
		navLinks: true,
		eventLimit: true,
		minTime: "00:00:00",
		maxTime: "24:00:00",
		timeFormat: 'HH:mm a',
		events: "calendar.do?command=select",
		eventClick: function(calEvent){
			if(calEvent.end!=null){
				var start = moment(calEvent.start).format('YYYY-MM-DD HH:mm');
				console.log(moment(start).format('YYYY-MM-DD HH:mm a'));
				var end = moment(calEvent.end).format('YYYY-MM-DD HH:mm');
			}else{
				var start = moment(calEvent.start).format('YYYY-MM-DD HH:mm');
				var end = start;
			}
			loadSchedule(calEvent.title, start, end);
			
		}
	});
	
});
 		
function loadSchedule(title, start, end){
	var content="";
	content+="<div style='width:100%; height:30px;'><div style='width:30%; float:left; padding-left:30px'>일정 이름</div><div style='width:60%; float:right;'><input type='text' id='cal_title' value='"+title+"'></div></div>";
	content+="<div style='width:100%; height:50px; display: flex; flex-direction: row; margin-bottom: 2%;'><div style='width:30%; float:left; padding-left:30px'>시작 날짜</div><div style='padding-left: 10px; width:44%; float:right; display: flex; flex-direction: column;'><input type='date' id='cal_start_date' style='margin-bottom: 3%;' value='"+moment(start).format('YYYY-MM-DD')+"'><input type='time' id='cal_start_time' value='"+moment(start).format('HH:mm')+"'></div></div>";
	content+="<div style='width:100%; height:50px; display: flex; flex-direction: row; margin-bottom: 2%;'><div style='width:30%; float:left; padding-left:30px'>마침 날짜</div><div style='padding-left: 10px; width:44%; float:right; display: flex; flex-direction: column;'><input type='date' id='cal_end_date' style='margin-bottom: 3%;' value='"+moment(end).format('YYYY-MM-DD')+"'><input type='time' id='cal_end_time' value='"+moment(end).format('HH:mm')+"'></div></div>";
	content+="<div style='width:100%; height:30px;  display:none;'><div style='width:30%; float:left; padding-left:30px'>일정 이름</div><div style='width:60%; float:right;'><input type='text' id='title' value='"+title+"'></div></div>";
	content+="<div style='width:100%; height:50px;  display:none;'><div style='width:30%; float:left; padding-left:30px'>시작 날짜</div><div style='padding-left: 10px; width:44%; float:right; display: flex; flex-direction: column;'><input type='date' id='start_date' style='margin-bottom: 3%;' value='"+moment(start).format('YYYY-MM-DD')+"'><input type='time' id='start_time' value='"+moment(start).format('HH:mm')+"'></div></div>";
	content+="<div style='width:100%; height:50px;  display:none;'><div style='width:30%; float:left; padding-left:30px'>마침 날짜</div><div style='padding-left: 10px; width:44%; float:right; display: flex; flex-direction: column;'><input type='date' id='end_date' style='margin-bottom: 3%;' value='"+moment(end).format('YYYY-MM-DD')+"'><input type='time' id='end_time' value='"+moment(end).format('HH:mm')+"'></div></div>";
	content+="<div style='width:100%; text-align:center; height:30px; margin-bottom:15px; marin-top:10px'><button onclick='updateSchedule();'>수정 하기</button><button style='margin-left:10px;'onclick='deleteSchedule();'>삭제 하기</button></div>"
	
	openPopup("일정",content,400);
}

function updateSchedule(){
	//수정 값
	var cal_title=$("#cal_title").val();
	var cal_start_date=$("#cal_start_date").val();
	var cal_start_time = $("#cal_start_time").val();
	var cal_end_date=$("#cal_end_date").val();
	var cal_end_time = $("#cal_start_time").val();
	
	cal_end_date = moment(cal_end_date).add(1, 'days').format('YYYY-MM-DD');
	
	//기존 값
	var title=$("#title").val();
	var start_date=$("#start_date").val();
	var start_time=$("#start_time").val();
	var end_date=$("#end_date").val();
	var end_time=$("#end_time").val();
	
	var info = '{"b_title":"'+title+'","b_start":"'+(start_date + "T" + start_time)+'","b_end":"'+(end_date + "T" + end_time)+'"}';
	
	var cal_info = '{"title":"'+cal_title+'","start":"'+(cal_start_date + "T" + cal_start_time)+'","end":"'+(cal_end_date + "T" + cal_end_time)+'"}';
	
	$.ajax({
		type:"POST",
		url:"calendar.do?command=update",
		data: {cal_info: cal_info, info: info},
		success:function(result){
			if(result>0){
				closeMessage('winAlert');
				alert("수정되었습니다");
				$("#calendar").fullCalendar("refetchEvents");
			}else{
				closeMessage('winAlert');
				alert("실패하였습니다");
				$("#calendar").fullCalendar("refetchEvents");
			}
		}
	})
}
		
function deleteSchedule(){
	var title = $("#cal_title").val();
	var start_date = $("#cal_start_date").val();
	var start_time = $("#cal_start_time").val();
	var end_date = $("#cal_end_date").val();
	var end_time = $("#cal_end_time").val();
	
	var info = '{"b_title":"'+title+'","b_start":"'+(start_date + "T" + start_time)+'","b_end":"'+(end_date + "T" + end_time)+'"}';
	
	$.ajax({
		type:"POST",
		url:"calendar.do?command=delete",
		data: {info: info},
		success:function(result){
			if(result>0){
				closeMessage('winAlert');
				alert("삭제되었습니다");
				$("#calendar").fullCalendar("refetchEvents");
			}else{
				closeMessage('winAlert');
				alert("실패하였습니다");
				$("#calendar").fullCalendar("refetchEvents");
			}
		}
	})
}
		
function addSchedule(){
	var content="";
	content+="<div style='width:100%; height:30px;'><div style='width:30%; float:left; padding-left:30px'>일정 이름</div><div style='width:60%; float:right;'><input type='text' id='cal_title' required='required'></div></div>";
	content+="<div style='width:100%; height:50px; display: flex; flex-direction: row; margin-bottom: 2%;'><div style='width:30%; float:left; padding-left:30px'>시작 날짜</div><div style='padding-left: 10px; width:44%; float:right; display: flex; flex-direction: column;'><input type='date' id='cal_start_date' required='required' style='margin-bottom: 3%;'><input type='time' id='cal_start_time' required='required'></div></div>";
	content+="<div style='width:100%; height:50px; display: flex; flex-direction: row; margin-bottom: 2%;'><div style='width:30%; float:left; padding-left:30px'>마침 날짜</div><div style='padding-left: 10px; width:44%; float:right; display: flex; flex-direction: column;'><input type='date' id='cal_end_date' required='required' style='margin-bottom: 3%;'><input type='time' id='cal_end_time' required='required'></div></div>";
	content+="<div style='width:100%; text-align:center; height:30px; margin-bottom:15px; marin-top:10px'><button onclick='saveSchedule();'>저장하기</button></div>"
	openPopup("일정 등록",content,400);
}

function saveSchedule(){
	var cal_title=$("#cal_title").val();
	var cal_start_date = $("#cal_start_date").val();
	var cal_start_time = $("#cal_start_time").val();
	var cal_end_date=$("#cal_end_date").val();
	var cal_end_time = $("#cal_end_time").val();
	
	//cal_end_date = moment(cal_end_date).add(1, 'days').format('YYYY-MM-DD');
	
	var cal_info = '{"title":"'+cal_title+'","start":"'+(cal_start_date + "T" + cal_start_time)+'","end":"'+(cal_end_date + "T" + cal_end_time)+'"}';
	
	$.ajax({
		type:"POST",
		url:"calendar.do?command=save",
		data: {cal_info: cal_info},
		success:function(result){
			if(result>0){
				closeMessage('winAlert');
				alert("저장되었습니다");
				$("#calendar").fullCalendar("refetchEvents");
			}else{
				closeMessage('winAlert');
				alert("실패하였습니다");
				$("#calendar").fullCalendar("refetchEvents");
			}
		}
	})

}

function openPopup(name, content, width){
	$("#alert_name").html(name);
	$("#alert_content").html(content);
	openMessage("winAlert",width);
}

function openMessage(id,width){
	$('#'+id).css("width",width+"px");
	$('#'+id).bPopup();
}

function closeMessage(id){
	$('#'+id).bPopup().close();
}