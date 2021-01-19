/**
 * 	댓글과 관련된 스크립드
 */

var replyService = (function() {

	function add(reply, callback) {
		console.log("Add method 호출");

		$.ajax({
			type : 'post',
			url : '/replies/new',
			contentType : 'application/json;charset=utf-8',
			data:JSON.stringify(reply),
			success:function(result) {
				if(callback) {
					callback(result);
				}
			}
		})
	} // add end

	function getList(param, callback) {
		console.log("getList 호출");

		var bno = param.bno;
		var page = param.page||1;

		$.getJSON({
			url : '/replies/pages/'+bno+'/'+page,
			success:function(data){
				if(data!=null) {
					if(callback) {
						//console.log(data.replyCnt, data.list);
						callback(data.replyCnt, data.list);
					}
				}
			}
		})
	} //getList end

	function remove(rno, callback) {
		console.log("remove 호출");

		$.ajax({
			url : '/replies/'+rno,
			type : 'delete',
			success:function(result) {
				if(callback) {
					callback(result);
				}
			}
		})	
	} // remove end

	function update(reply, callback) {
		console.log("update 호출")

		$.ajax({
			url : '/replies/'+reply.rno,
			type : 'put',
			contentType : 'application/json;charset=utf-8',
			data:JSON.stringify(reply),
			success:function(result) {
				if(callback) {
					callback(result);
				}
			}
		})
	} // update end

	function get(rno, callback) {
		console.log("get 호출")

		$.getJSON({
			url : '/replies/'+rno,
			success:function(data) {
				if(callback){
					callback(data);
				}
			}
		})
	} // get end

	function displayTime(timeVal) {
		console.log(timeVal);
		
		var today = new Date();
		var gap = today.getTime() - timeVal;
		var dateObj = new Date(timeVal);

		if(gap < (1000*60*60*24)) {	// 댓글이 달린 날짜가 오늘일 경우 (밀리세컨드*분*초*시)
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var sc = dateObj.getSeconds();

			// 9보다 클 경우 그대로, 작을경우 '0'을 붙여서 출력 
			return [(hh>9?'':'0')+hh, ':', (mi>9?'':'0')+mi, ':', (sc>9?'':'0')+sc].join('');

		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth()+1;
			var dd = dateObj.getDate();

			return [yy, '/', (mm>9?'':'0')+mm, '/', (dd>9?'':'0')+dd].join('');			
		}
	}
		
	return{
		add : add,
		getList : getList,
		remove : remove,
		update : update,
		get : get,
		displayTime : displayTime
	}
	
})();