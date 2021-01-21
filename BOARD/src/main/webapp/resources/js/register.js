/**
 * 	파일첨부와 관련된 스크립트
 */

/*$(function() {
	//파일버튼이 클릭되어 변화가 일어나는 경우
	// 현재 목록의 파일을 서버로 보내서 저장하기
	$("intput[type='file']").change(function(){
		console.log("업로드 호출");

		var inputFile = $("input[name='uploadFile']");
		console.log(inputFile);
       
		//첨부 파일 목록
		var files = inputFile[0].files;
		
		// <form> ~ </form> 대체로 ajax로 데이터를 쉽게 전송할 수 있도록 해줌
		var formData = new FormData();
		
		// 객체 안에 요소 추가
		for (var i=0; i<files.length; i++) {
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			url : '/uploadAjax',
			type : 'post',
			processData:false, // 데이터를 query String 형태로 보낼 것인지 결정(기본값 : application/x-www-form-urlencoded)
			contentType:false, // 기본값 : application/x-www-form-urlcoded(파일첨부이므로, multipart/form-data로 보내야 함!)
			data:formData,
			success:function(result){
				console.log(result);
				showUploadedFile(result);
			},
			error:function(xhr, status, error){
				console.log("xhr.status");
			}
		})
	}) //파일 첨부 종료
    
    function showUploadedFile(uploadResultArr) {
        //결과를 보여줄 영역 가져오기
        var uploadResult = $(".uploadResult ul");
        var str = "";
        $(uploadResultArr).each(function(idx,obj) {	//var i=0
        	if (obj.image) {
        		// 썸네일 파일 경로
        		var fileCallpath = encodeURIComponent(obj.uploadPath+"\\s_"+obj.uuid+"_"+obj.fileName);
        		
        		// 원본 이미지 경로
        		var originPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
        		originPath = originPath.replace(new RegExp(/\\/g),"/");
        		
        		str += "<li>";
        		str += "<a href=\"javascript:showImage(\'"+originPath+"\')\">";
        		str += "<img src='/display?fileName="+fileCallpath+"'>";
        		str += "<br />"+obj.fileName+"</a>";
        		str += "<span data-file='"+fileCallpath+"' data-type='image'> X </span>"
        		str += "</li>";

        	} else {
        		// 일반 파일 경로
        		var fileCallpath = encodeURIComponent(obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName);

        		str += "<li><a href='/download?fileName="+ fileCallpath +"'>";
        		str += "<img src='/resources/img/attach.png'><br />"+obj.fileName+"</a>";
        		str += "<span data-file='"+fileCallpath+"' data-type='file'> X </span>"
        		str += "</li>";
        	}
        });

        uploadResult.append(str);
     } // 첨부파일 가져오기 종료
})

function showImage(fileCallpath) {
	$(".bigPictureWrapper").css("display", "flex").show();
	$(".bigPicture").html("<img src='/display?fileName="+fileCallpath+"'>").animate({width: '100%', height: '100%'}, 1000);
}*/

$(function() {
	
	// 게시글 등록 버튼 동작
	$("button[type='submit']").click(function(e) {
		e.preventDefault();
		
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
		
		// hidden  태그를 게시글 등록 폼에 추가한 후 폼 전송하기
		
		
	})
	
	
	
	
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

	$(".uploadResult").on("click", "button", function() {
		
		// 해당 파일 경로 / 타입 가져오기
		var targetFile = $(this).data("file");
		var targetType = $(this).data("type");

		// span 태그가 속한 부모 <li> 가져오기
		var targetLi = $(this).closest("li");

		// 화면 목록에서 제거 / 서버 폴더에서 제거
		$.ajax({
			url: '/deleteFile',
			type: 'post',
			data: {
				fileName: targetFile,
				type: targetType
			},
			success: function(result) {
				console.log(result);
				targetLi.remove();
			}
		})
	}) // x버튼 종료
	
	// 열린 이미지 닫기
	$(".bigPictureWrapper").click(function() {
		$(".bigPicture").animate({width: '0%', height: '0%'}, 1000);
		setTimeout(function() {
			$(".bigPictureWrapper").hide();
		}, 1000);
	})
})

function showImage(fileCallPath){
	$(".bigPictureWrapper").css("display","flex").show();
	$(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"'>").animate({width:'100%', height:'100%'},1000);  
}