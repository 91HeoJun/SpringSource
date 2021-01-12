<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Modify</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>            
            <div class="row">
                <div class="col-lg-12">
                	<div class="panel panel-default">
                        <div class="panel-heading">
                           Board Modify Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                			<form action="" method="post" role="form">
                				<div class="form-group">
                					<label>Bno</label>
                					<input class="form-control" name="bno" readonly="readonly" value="${getBoard.bno}">                				
                				</div> 
                				<div class="form-group">
                					<label>Title</label>
                					<input class="form-control" name="title" value="${getBoard.title}">                				
                				</div>  
                				<div class="form-group">
                					<label>Content</label>
                					<textarea class="form-control" rows="3" name="content">${getBoard.content}</textarea>               				
                				</div> 
                				<div class="form-group">
                					<label>Writer</label>
                					<input class="form-control" name="writer" readonly="readonly" value="${getBoard.writer}">                				
                				</div>  
                				<button type="submit" data-oper='modify' class="btn btn-default">Modify</button>              			
                				<button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>              			
                				<button type="submit" data-oper='list' class="btn btn-info">List</button>              			
                			</form>
                		</div>
                	</div>
                </div>
            </div>            
<%-- remove와 list를 위한 폼--%>

<!-- 페이지 나누기를 위한 세팅값 -->
<form action="" id="myform" method="post">
	<input type="hidden" name="bno" value="${getBoard.bno}" />
</form>

<%-- 스크립트 --%>
<script>
$(function(){
	var form = $("#myform");
	$("button").click(function(e){
		e.preventDefault(); // 서브밋 기능 막기
		
		var oper = $(this).data("oper");
		console.log(oper);
		
		if (oper =='remove') {	// myform의 action으로 보내기
			form.attr("action", "remove");
			
		} else if(oper =='modify'){	// 기존의 수정 폼
			form = $()"form[role='form']");
			
		}else if(oper =='list'){	// myform의 action으로 보내기
			form.attr("action", "list")
				.attr("method", "get");
		
			// 폼 안의 bno 삭제하기
			form.find("input[name='bno']").remove();
		}
		form.submit();
	})
})
</script>
<%@include file="../includes/footer.jsp" %>       