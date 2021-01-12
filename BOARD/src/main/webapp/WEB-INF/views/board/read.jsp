<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Read</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>            
            <div class="row">
                <div class="col-lg-12">
                	<div class="panel panel-default">
                        <div class="panel-heading">
                           Board Read Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                			<form action="/board/read" role="form">
                				<div class="form-group">
                					<label>Bno</label>
                					<input class="form-control" name="bno" readonly="readonly" value="${getBoard.bno}">                				
                				</div> 
                				<div class="form-group">
                					<label>Title</label>
                					<input class="form-control" name="title" readonly="readonly" value="${getBoard.title}">                				
                				</div>  
                				<div class="form-group">
                					<label>Content</label>
                					<textarea class="form-control" rows="3" name="content" readonly="readonly">${getBoard.content}</textarea>               				
                				</div> 
                				<div class="form-group">
                					<label>Writer</label>
                					<input class="form-control" name="writer" readonly="readonly"  value="${getBoard.writer}">                				
                				</div>  
                				<button type="button" class="btn btn-default" >Modify</button>     			
                				<button type="reset" class="btn btn-info" onclick="location.href='list'">List</button>          			
                			</form>
                		</div>
                	</div>
                </div>
            </div>
<!-- 페이지 나누기를 위한 세팅값 -->
<form action="modify" id="myform">
	<input type="hidden" name="bno" value="${getBoard.bno}" />
</form>
<script>
	$(function(){
		var form = $("#myform");
		
		$(".btn-default").click(function(){
			form.submit();
		})
		
	})
</script>
<%@include file="../includes/footer.jsp" %>       