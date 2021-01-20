/**
 *  ---- 파일 첨부가 없는 상태
 */


<script>
/* $(function () {
	$("button").click(function(e) {
		e.preventDefault(); // 이동 막기

		
		// 일반 방식으로 보낼때
  		$.ajax({
			url : '/product',
			type : 'post',
			data:$("form").serialize(),
			success:function(result){
				console.log(result);
				
			}
		})  */
		
/* 		 $.ajax({
			url : '/product',
			type : 'post',
			data:$("form").serialize(),
			success:function(result){
				console.log(result);
			},
			error:Function(xhr, txtStatus, error){
				console.log(txtStatus.responseText);
			}
		}) */

		
		/* let form = {
			code:$('#code').val(),
			category:$('#category').val(),
			pname:$('#pname').val(),
			amount:$('#amount').val(),
			price:$('#price').val(),
			etc:$('#etc').val(),
		} */
		
		 // json으로 전송하기
/* 		 $.ajax({
			url : '/product',
			type : 'post',
			contentType: 'application/json;charset=utf-8',
			data:JSON.stringify(form),
			success:function(result){
				console.log(result);
			},
			error:function(xhr, txtStatus, error) {
				console.log(txtStatus.responseText);
			}
		}) */
	})
})
</script>