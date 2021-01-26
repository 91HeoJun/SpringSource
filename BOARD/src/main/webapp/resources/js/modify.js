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
			
		} else if(oper =='modify'){	// 기존의 수정 폼 보내기
			form = $("form[role='form']");
			
			var str ="";
			// 첨부파일 영역에 정보 수집
			$(".uploadResult ul li").each(function(idx, obj) {
				var job = $(obj);
		
				// 수집된 정보를 hidden 태그로 작성
				str+= "<input type='hidden' name='attachList["+idx+"].uuid' value='"+job.data("uuid")+"'>"
				str+= "<input type='hidden' name='attachList["+idx+"].uploadPath' value='"+job.data("path")+"'>"
				str+= "<input type='hidden' name='attachList["+idx+"].fileName' value='"+job.data("filename")+"'>"
				str+= "<input type='hidden' name='attachList["+idx+"].fileType' value='"+job.data("type")+"'>"
			})
			
			console.log(str);
			form.append(str);
			
			
		}else if(oper =='list'){	// myform의 action으로 보내기
			form.attr("action", "list")
				.attr("method", "get");
		
			// 폼 안의 bno 삭제하기
			form.find("input[name='bno']").remove();
		}
		
		form.submit();
	})
	
	//첨부물
	// .read == 문서가 준비가 되면 이후 function 진행 
	$(document).ready(function() {
		
		var uploadResult = $(".uploadResult ul");
		
		$.getJSON({
			url: 'getAttachList',
			data: {
				bno: bnoVal
				},
			success: function(data) {
				console.log(data);

				var str ="";
				
				$(data).each(function(idx,obj){
					if(obj.fileType) {
						//썸네일 이미지 경로
						var fileCallPath= encodeURIComponent(obj.uploadPath +"\\s_"+obj.uuid+"_"+obj.fileName);
						
						str+= "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
						str+= "<img src='/display?fileName="+fileCallPath+"'><div>"+obj.fileName;
						str+= "<button type='button' class='btn btn-warning btn-circle' data-file='";
						str+= fileCallPath+"' data-type='image'>";
						str+= "<i class='fa fa-times'></i>";
						str+= "</button>";
						str+= "</div></li>";
						
					} else {						

						str+= "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
        				str+= "<img src='/resources/img/attach.png'><div>"+obj.fileName+"</a>";
        				str+= "<button type='button' class='btn btn-warning btn-circle' data-file='";
        				str+= fileCallPath+"' data-type='file'>";
        				str+= "<i class='fa fa-times'></i>";
        				str+= "</button>";
						str+= "</div></li>";

					}
				}) // each문 종료
			uploadResult.append(str)
			}
		}) // getJSON 종료
		
		// 이미지 클릭 시 원본 이미지 보여주기, 일반파일은 다운로드
		$(".uploadResult").on("click", "li", function() {
			var liObj = $(this);
			var path = encodeURIComponent(liObj.data("path")+"\\"+liObj.data("uuid")+"_"+liObj.data("filename"));

			if(liObj.data("type")) {
				showImage(path.replace(new RegExp(/\\/g),"/"));
			} else {
				location.href = "/download?fileName="+ path;
			}
		}) // 이미지 보여주기 종료
		
		// 열린 이미지 닫기
		$(".bigPictureWrapper").click(function() {
			$(".bigPicture").animate({width: '0%', height: '0%'}, 1000);
				setTimeout(function() {
				$(".bigPictureWrapper").hide();
			}, 1000);
		}) // 이미지 닫기 종료
		
		// x버튼 클릭 - 이벤트 위임
		$(".uploadResult").on("click", "button", function(e) {
			e.stopPropagation();
			
			if(confirm("---- 파일을 삭제하시겠습니까? ----")) {
				// span 태그가 속한 부모 <li> 가져오기
				var targetLi = $(this).closest("li");
				targetLi.remove();
			}
		}) // x버튼 종료
		
	// 파일버튼이 클릭되어 변화가 일어나는 경우
	// 현재 목록의 파일을 서버로 보내서 저장하기
	$("input[type='file']").change(function() {
		console.log("upload 호출");


		var inputFile= $("input[name='uploadFile']");
		console.log(inputFile);

		// 첨부 파일 목록
		var files= inputFile[0].files;
      
		// <form> ~ </form> 대체로 ajax로 데이터를 쉽게 전송할 수 있도록 해줌
		var formData= new FormData();

		// 객체 안에 요소 추가
		for(var i=0; i<files.length; i++){
			formData.append("uploadFile",files[i]);
		}

		$.ajax({
			url: "/uploadAjax" ,
			type: "post",
			processData: false,         //데이터를 query string 형태로 보낼 것인지. 결정(기본 application/x-www-form-urlencoded임)
			contentType: false,        //기보ㄴ값은 application/x-www-form-urlencoded(파일 첨부 이므로 multipart/form-data 로 보내야함)
			data:formData,
			success: function(result) {
				console.log(result);
				showUploadedFile(result);
				$("input[name='uploadFile']").val("");
			},
			error: function(xhr,status,error) {
				console.log(status);
			}
		}) 
	}) // 파일 첨부 종료

	function showUploadedFile(uploadResultArr) {

	//결과를 보여줄 영역 가져오기
		var uploadResult = $(".uploadResult ul");
		var str= "";
		$(uploadResultArr).each(function(idx,obj) {  //idx ==index  obj 행
			if(obj.fileType) {
				// 썰네일 이미지 경로
				var fileCallPath= encodeURIComponent(obj.uploadPath +"\\s_"+obj.uuid+"_"+obj.fileName);
	            
				// 원본 이미지 경로
				var originPath= obj.uploadPath +"\\"+obj.uuid+"_"+obj.fileName;
				originPath= originPath.replace(new RegExp(/\\/g),"/");
	
				str+= "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
				str+= "<a href=\"javascript:showImage(\'"+originPath+"\')\">";
				str+= "<img src='/display?fileName="+fileCallPath+"'><div>"
				str+= obj.fileName+"</a>";
				str+= "<button type='button' class='btn btn-warning btn-circle' data-file='";
				str+= fileCallPath+"' data-type='image'>";
				str+= "<i class='fa fa-times'></i>";
				str+= "</button>";
				str+= "</div></li>";

        	}else{
        		// 일반 파일 경로 
        		var fileCallPath=encodeURIComponent(obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName);

				str+= "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
				str+= "<a href='/download?fileName="+fileCallPath+"' >";
        		str+= "<img src='/resources/img/attach.png'><div>"+obj.fileName+"</a>";
        		str+= "<button type='button' class='btn btn-warning btn-circle' data-file='";
        		str+= fileCallPath+"' data-type='file'>";
        		str+= "<i class='fa fa-times'></i>";
        		str+= "</button>";
        		str+= "</div></li>";
               
        	}
        });
	uploadResult.append(str);
	} // 첨부파일 가져오기 종료
		
	})
})

function showImage(fileCallPath){
	$(".bigPictureWrapper").css("display","flex").show();
	$(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"'>").animate({width:'100%', height:'100%'},1000);
}