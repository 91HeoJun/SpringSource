<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</head>
<body>

	<div class="container">
		<h3>도서정보</h3>
		<button type="button" id="all">전체 조회</button>
		<button type="button" id="get">단일 조회</button>
		<button type="button" id="remove">정보 삭제</button>
		<button type="button" id="update">정보 수정</button>
		<button type="button" id="input">정보 입력</button>
		
		
		<div id="result">
			<table class="table"></table>
		</div>
	</div>

<script>
$(function(){
	
	// 전체 가져오기
	$("#all").click(function(){
		
		$.getJSON({
			url:'/list',
			success:function(data){
				console.log(data);
				
				var str = "";
	
				$(data).each(function(idx, item){
	
				str += "<tr><td>";
				str += item.code + "</td><td>" + item.title + "</td>";
				str += "<td>" + item.writer + "</td>";
				str += "<td>" + item.price + "</td></tr>";
	
				})
				
				$(".table").html(str);
			}
		})
	})	
	
	// 단일 가져오기
	$("#get").click(function(){
		
		$.getJSON({
			url:'/1003',
			success:function(item){
				console.log(item);
				
				var str = "";

				str += "<tr><td>";
				str += item.code + "</td><td>" + item.title + "</td>";
				str += "<td>" + item.writer + "</td>";
				str += "<td>" + item.price + "</td></tr>";
				
				$(".table").html(str);
			}
		})
	})
	
	// 도서정보 삭제
	$("#remove").click(function(){
		
		$.ajax({
			url:'/7412',
			type:'delete',
			
			success:function(data) {
				console.log(data);
				$(".table").html(data)
			},
			
			error:function(xhr, txtStatus, error) {
				$(".table").html(xhr.responseText);
			}
		})
	})

	// 도서정보 수정
	$("#update").click(function(){

		let param = {
			code : 1003,
			price : 30000
		}
		
		$.ajax({
			url:"/update",
			type:"put",
			contentType:"application/json",	// application/x-www-form-urlencoded;charset=UTF-8
			data:JSON.stringify(param),
			
			success:function(data) {
				$(".table").html(data);
			},
			
			error:function(xhr, txtStatus, error) {
				$(".table").html(xhr.responseText);
			}
		})		
	})
	
	// 도서정보 입력
	$("#input").click(function(){
		
 		let param = {
			code : 1004,
			title : 불금이면,
			writer : 허준,
			price : 500
		}
 		
 		$.ajax({
 			url:"/new",
			type:"post",
 			contentType:"application/json",
 			data:JSON.stringify(param),
 			
			success:function(data) {
				$(".table").html(data);
			},
			
			error:function(xhr, txtStatus, error) {
				$(".table").html(xhr.responseText);
			}	
 		})
	})
	
	

	
})
</script>
</body>
</html>