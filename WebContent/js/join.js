function idChkConfirm() {
	//id 중복체크를 했는지 안 했는지 파악 후 안 했으면 처리할 내용 작성
	//중복체크 했으면 title값이 y, 안 했으면 n
	var title = document.getElementsByName("user_id")[0].title;
	if(title == "n") {
		alert("아이디 중복체크 해주세요.");
		document.getElementsByName("user_id")[0].focus();
	}
}
function idChk() {
	//아이디 중복 체크
	var doc_id = document.getElementsByName("user_id")[0];
	if(doc_id.value.trim() == "" || doc_id.value == null) {
		alert("id를 입력해주세요.");
	} else {
		var target = "idChkController.jsp?command=idChk&user_id=" + doc_id.value.trim();
		open(target, "", "width=500, height=300");
	}
}
			
function pwChk() {
	//비밀번호 확인
	var user_pw = document.getElementsByName("user_pw")[0].value;
	var user_pw2 = document.getElementsByName("user_pw2")[0].value;
	
	if(user_pw == user_pw2) {
		document.getElementsByName("user_pw2")[0].setAttribute("style", "border-color: #0dcaf0");
		document.getElementsByName("user_pw2")[0].title = "y";
		document.getElementsByName("user_name")[0].focus();
	} else {
		alert("비밀번호가 일치하지 않습니다.");
		document.getElementsByName("user_pw2")[0].focus();
	}
}
			
function emailChk() {
	var doc_email = document.getElementsByName("user_email")[0];
	if(doc_email.value.trim() == "" || doc_email.value == null) {
		alert("이메일을 입력해주세요.");
	} else {
		var target = "emailChkController.jsp?command=emailChk&user_email=" + doc_email.value.trim();
		open(target, "", "width=500, height=300");
	}
}

function joinChk() {
	//가입구분 체크
	var member = document.getElementsByName("user_member")[0].value;
	var public = document.getElementsByName("public")[0].value;
	var company = document.getElementsByName("company")[0].value;
	
	if(input_member == public || input_member == company) {
		document.getElementsByName("user_member")[0].setAttribute("style", "border-color: #0dcaf0");
	} else {
		alert("(개인/기업) 둘 중 하나만 입력할 수 있습니다.");
		document.getElementsByName("user_member")[0].focus();
	}
	
	//모두 입력되었는지 여부
	var id = document.getElementsByName("user_id")[0].value;
	var pw = document.getElementsByName("user_pw")[0].value;
	var pw2 = document.getElementsByName("user_pw2")[0].value;
	var name = document.getElementsByName("user_name")[0].value;
	var nick = document.getElementsByName("nick_name")[0].value;
	var phone = document.getElementsByName("user_phone")[0].value;
	var email = document.getElementsByName("user_email")[0].value;
	var addr = document.getElementsByName("user_addr")[0].value;
	var addr_de = document.getElementsByName("user_addr_de")[0].value;
	
	if(id != null && pw != null && pw2 != null && name != null && nick != null && phone != null && email != null && addr != null && addr_de != null && member != null) {
		return true;
	} else {
		alert("입력되지 않은 값이 존재합니다.");
		return false;
	}
}

function bizChk() {
	//개인으로 적히면 사업자등록번호 란이 안 보이게, 기업으로 적으면 사업자등록번호 란이 보이게
	var input_member = document.getElementsByName("user_member")[0].value;
	var public = document.getElementsByName("public")[0].value;
	var company = document.getElementsByName("company")[0].value;
	
	if(input_member == public) {
		$("#bizChk").hide();
		$("#biz_num").prop('required', false);
	} else if(input_member == company) {
		$("#bizChk").show();
		$("#biz_num").prop('required', true);
	}
}
		      
function findAddr() {
	new daum.Postcode({
		oncomplete: function(data) {
		    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분
			// 도로명 주소의 노출 규칙에 따라 주소를 표시
	       	// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기
	       	var roadAddr = data.roadAddress; // 도로명 주소 변수
	       	var extraRoadAddr = ''; // 참고 항목 변수
	
	       	// 법정동명이 있을 경우 추가 (법정리는 제외)
	       	// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	       	if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	       	    extraRoadAddr += data.bname;
	       	}
	       	// 건물명이 있고, 공동주택일 경우 추가
	       	if(data.buildingName !== '' && data.apartment === 'Y'){
	       	   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	       	}
	       	// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열
	       	if(extraRoadAddr !== ''){
	       	    extraRoadAddr = ' (' + extraRoadAddr + ')';
	       	}
	
	       	// 우편번호와 주소 정보를 해당 필드에 넣는다.
	       	//document.getElementById('우편번호input').value = data.zonecode;
	       	document.getElementById("user_addr").value = roadAddr; //도로명 주소
	       	document.getElementById("user_addr").value += " " + data.jibunAddress; //상세 주소
	   	
	    	// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
	       	if(roadAddr !== ''){
	       	    document.getElementById("user_addr").value += extraRoadAddr;
	       	} else {
	       	    document.getElementById("user_addr").value += '';
	       	}
		}
	}).open();
}