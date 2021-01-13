/**
 * 	modify.jsp에서 쓰는 스크립트
 */

$(function(){
	
	var form = $("#myform");
	
	$("button").click(function(e){
		e.preventDefault(); // 서브밋 기능 막기
		
		var oper = $(this).data("oper");
		console.log(oper);
		
		if (oper =='remove') {	// myform의 action으로 보내기
			form.attr("action", "remove");
			
		} else if(oper =='modify'){	// 기존의 수정 폼
			form = $("form[role='form']");
			
			
		}else if(oper =='list'){	// myform의 action으로 보내기
			form.attr("action", "list")
				.attr("method", "get");
		
			// 폼 안의 bno 삭제하기
			form.find("input[name='bno']").remove();
		}
		form.submit();
	})
	
})