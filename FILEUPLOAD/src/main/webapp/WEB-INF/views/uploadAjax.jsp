<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/mycss.css" />
</head>
<body>
	
	<h1>Upload Ajax</h1>
	<div class="uploadDiv">
		<input type="file" name="uploadFile" id="" multiple/>
	</div>
	<button>upload</button>
	<div class="uploadResult">
		<ul></ul>
	</div>
	
	<div class="bigPictureWrapper">
		<div class="bigPicture"></div>
	</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>

<script>
$(function() {
    $("button").click(function() {
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
		
/* 		for (let key of formData.keys) {
			console.log(key);
		}
		for (let value of formData.values()) {
			console.log(value);
		} */
		
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
	})
	
	function showUploadedFile(uploadResultArr) {
    	// 결과물 보여줄 영역 가져오기
		var uploadResult = $(".uploadResult ul");
    	
    	$(uploadResultArr).each();
	}
    
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
     }
    
    // 열린 이미지 닫기
    $(".bigPictureWrapper").click(function() {
    	$(".bigPicture").animate({width: '0%', height: '0%'}, 1000);
    	setTimeout(function() {
			$(".bigPictureWrapper").hide();
    	}, 1000);
	})
	
	// X 버튼 클릭 - 이벤트 위임
	$(".uploadResult").on("click", "span", function() {
		
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
	})
    
})

// 이미지 열기 + 애니메이션
function showImage(fileCallpath) {
	$(".bigPictureWrapper").css("display", "flex").show();
	$(".bigPicture").html("<img src='/display?fileName="+fileCallpath+"'>").animate({width: '100%', height: '100%'}, 1000);
}
</script>
</body>
</html>