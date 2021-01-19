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
                				<button type="reset" class="btn btn-info">List</button>
                			</form>
                		</div>
                	</div>
                </div>
            </div>
            
<!-- 페이지 나누기를 위한 세팅값 -->
<form action="modify" id="myform">
	<input type="hidden" name="bno" value="${getBoard.bno}" />
	<input type="hidden" name="pageNum" value="${cri.pageNum}" />
	<input type="hidden" name="amount" value="${cri.amount}" />
	<input type="hidden" name="type" value="${cri.type}" />
	<input type="hidden" name="keyword" value="${cri.keyword}" />
</form>

<%-- 댓글 리스트 화면 --%>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-comments fa-fw"></i>
				Reply
				<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">New Reply</button>
			</div>
			<div class="panel-body">
				<ul class="chat">
					<li class="left cearfix" data-rno="1">
						<div>
							<div class="header">
								<strong class="primary-front">User00</strong>
								<small class="pull-right text-muted">2021-01-18 10:10</small>
								<p>Good Job!</p>
							</div>
						</div>
					</li>
				</ul>
			</div>
			<div class="panel-footer"></div>
		</div>
	</div>
</div>

<!-- 댓글 등록 버튼 누르면  Modal -->
<!-- 댓글 등록 모달 -->
<div class="modal" tabindex="-1" role="dialog" id="replyModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h5 class="modal-title">Reply</h5>
      </div>
      <div class="modal-body">
        <div class="form-group">
        	<label for="">댓글내용</label>
        	<input type="text" class="form-control" name="reply" value="댓글내용"/>
        </div>
        <div class="form-group">
        	<label for="">작성자</label>
        	<input type="text" class="form-control" name="replyer" value="작성자"/>
        </div>
        <div class="form-group">
        	<label for="">작성일</label>
        	<input type="text" class="form-control" name="replyDate" value="작성일"/>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" id="modalRegisterBtn">등록</button>
        <button type="button" class="btn btn-success" id="modalModifyBtn">수정</button>
        <button type="button" class="btn btn-danger" id="modalRemoveBtn">삭제</button>
        <button type="button" class="btn btn-primary"  id="modalCloseBtn" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>

<script>
		// 현재 글번호 가져오기
		let bnoVal = ${getBoard.bno};
		
		// 댓글 리스트 가져올 영역 가져오기
		let replyUl = $(".chat");
		
		// 모달 영역 가져오기
		let modal = $(".modal");
		// 모달 영역 내 요소 가져오기
		let modalInputReply = modal.find("input[name='reply']");
		let modalInputReplyer = modal.find("input[name='replyer']");
		let modalInputReplyDate = modal.find("input[name='replyDate']");

		let modalModBtn = $("#modalModifyBtn");
		let modalRemoveBtn = $("#modalRemoveBtn");
		let modalRegisterBtn = $("#modalRegisterBtn");
		
		// 댓글 페이지 영역 가져오기
		let replyPageFooter = $(".panel-footer");
		
</script>

<script src="/resources/js/read.js"></script>
<script src="/resources/js/reply.js"></script>
<%@include file="../includes/footer.jsp" %>       