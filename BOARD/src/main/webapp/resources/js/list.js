/**
 * 	list.jsp에서 사용하는 스크립트.
 */

$(function(){
	
	checkModal(result);
	
	// history 재지정
	history.replaceState({}, null, null);
	
	// Modal창 띄우기
	function checkModal(result) {
		if(result == '' || history.state) {
			return;
		}
		
		if(parseInt(result)>0){
			$(".modal-body").html("게시글 " + result + "번이 등록되었습니다.");
		}
		
		$("#myModal").modal("show");
	}
	
	// actionForm 가져오기 - 페이지 이동시 사용할 폼
	var actionForm = $("#actionForm");
	
	// 페이지 번호 클릭 시 동작
	$(".paginate_button a").click(function(e){
		// a 태그 기능 중단
		e.preventDefault();
		
		// pageNum의 값을 사용자가 선택한 값으로 변경
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		
		actionForm.submit();
	})
	
	// amount 변경 시
	$(".form-control").change(function(){
		actionForm.find("input[name='amount']").val($(this).val());
		
		actionForm.submit();
	})
	
	// 제목 클릭 시 동작(현재 글번호, pageNum, amount, 검색정보)
	$(".move").click(function(e){
		e.preventDefault();
		actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
		actionForm.attr("action", "read");	// /board/read
		
		actionForm.submit();
	})
	
	// 검색 시 동작
	$(".btn-default").click(function(){
		// 서치폼 가져온 후 type, keyword가 비어있는지 확인
		// 비어있으면 메시지 띄워준 후 return
		
		var searchForm = $("#searchForm");
		
		var type = $("select[name='type']").val();
		var keyword = $("input[name='keyword']").val();
		
		if(type == '') {
			alert("타입이 비어있습니다.");
			return false;
		} else if(keyword == '') {
			alert("검색어가 비어있습니다.");
			return false;			
		}
		
		// 검색 시 첫 페이지를 보여주기 위해 페이지 번호 변경
		searchForm.find("input[name=pageNum]").val("1");
		searchForm.submit();
	})
	
	
})