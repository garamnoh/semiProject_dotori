$("#diary_input_btn").click(e=>{
	const content=$(".diary_content_input").val();	
	if(content.trim()==""){
		alert("내용을 입력해주세요.");
	}else{
		$.ajax({
			url:contextPath+"/diary/diaryWrite",
			type:"post",
			data:{			  
				"diary_folder":$("select[name='diary_folder']").val(),
				"diary_content_input":content,
				"loginMemberId":$("input[name='loginMemberId']").val(),
				"hostMemberId":$("input[name='hostMemberId']").val()
			},
			dataType:"html",
			success:data=>{				
				$("#right-page").html(data);
			}
		});		
	}
});
  
$(".diary_del_btn").click(e => {
	if(confirm("정말 삭제하시겠습니까?")){
		$.ajax({
			url:contextPath+"/diary/diaryDelete",
			type:"post",
			data:{
				"diary_no":$(e.target).parent().prev().val(),
				"loginMemberId":$("input[name='loginMemberId']").val(),	
				"hostMemberId":$("input[name='hostMemberId']").val()
			},
			dataType:"html",
			success:data=>{
				$("#right-page").html(data);
			}
		}) 
	}
});

$(".diary_com_btn").click(e=>{
	$(e.target).parents('#diary_content_list').children('#diary_comment_box').show();
	$(e.target).parents('#diary_content_box').css("height", "110px");
	$(e.target).parents('#diary_content_list').css("height", "105px");	
});

$(".diary_up_btn").click(e=>{		
	$(e.target).parents('#diary_content_list').children('#diary_content_content').hide();
	$(e.target).parents('#diary_content_list').children('#diary_comment_box').hide();
	$(e.target).parents('#diary_content_list').children('.diary_comment_list_box').hide();//댓글출력창도닫기
	$(e.target).parents('#diary_content_list').children('#diary_co_comment_box').hide();//대댓글출력창닫기
	$(e.target).parents('#diary_content_list').children('#diary_content_update').show();
	$(e.target).parents('#diary_content_box').css("height", "150px");
	$(e.target).parents('#diary_content_list').css("height", "145px");
});

$(".diary_cancel_btn").click(e=>{	
	$(e.target).parents('#diary_content_list').children('#diary_content_update').hide();
	$(e.target).parents('#diary_content_list').children('#diary_content_content').show();
	$(e.target).parents('#diary_content_box').css("height", "85px");
	$(e.target).parents('#diary_content_list').css("height", "80px");
});

$(".diary_comment_btn").click(e=>{
	const comment=$(e.target).prev().val();
	if(comment.trim()==""){
		alert("댓글을 입력해주세요.");
	}else{
		$.ajax({
			url:contextPath+"/diary/diaryCommentWrite",
			type:"post",
			data:{
				"comment_level":1,
				"loginMemberId":$("input[name='loginMemberId']").val(),	
				"diary_comment":comment,
				"diary_no":$(e.target).parent().prev().prev().val(),			
				"diary_comment_ref":0 //원댓글은 참고댓글번호가 없다
			},
			dataType:"html",
			success:data=>{
				$("#right-page").html(data);
			}
		});
	}
});

$('.like').on('click', (e)=>{
    let unlike = contextPath+"/images/unlike.png";
    let like = contextPath+"/images/like.png";
    let src = $(e.target).attr('src');
    let newSrc = src==unlike ? like : unlike;
    $(e.target).attr('src', newSrc);
    
    const diaryNo = $(e.target).parent().prev().val();
    $.ajax({
    	url: contextPath+"/friends/like",
    	data:{
    		'diaryNo': diaryNo
    	},
    	success: (data)=>{
    		const likeCount = data['likeCount'];
    		$(e.target).parent().children('span').text(likeCount);
    	}
    });
});

$(".diary_update_btn").click(e=>{
	$.ajax({
		url:contextPath+"/diary/diaryUpdate",
		type:"post",
		data:{				
			"diary_no":$(e.target).parent().parent().prev().val(),
			"diary_folder":$(e.target).parent().prev().prev().val(),
			"diary_content_input":$(e.target).parent().prev().val()			
		},
		dataType:"html",
		success:data=>{			
			$("#right-page").html(data);
	 	}
	})
});

$(".diary_co_com_btn").click(e=>{	
	$(e.target).parents('#diary_content_list').children('#diary_comment_box').hide();
	//$(e.target).parents('#diary_content_list').children('#diary_co_comment_box').show();
	$(e.target).next().css("display","block");
	$(e.target).next().show();
	
});

$(".diary_co_del_btn").click(e=>{
	const diaryComment=$(e.target).parent().prev().prev().val();
	console.log(diaryComment);
	if(confirm("정말 삭제하시겠습니까?")){
		$.ajax({
			url:contextPath+"/diary/commentDelete",
			type:"post",
			data:{
				"commentNo":diaryComment,
				"commentWriter":$("input[name='loginMemberId']").val(),
				"hostMemberId":$("input[name='hostMemberId']").val()
			},
			dataType:"html",
			success:data=>{
				$("#right-page").html(data);
			}
		}) 
	}
});

$(".diary_reply_btn").click(e=>{
	if(confirm("정말 삭제하시겠습니까?")){
		$.ajax({
			url:contextPath+"/diary/commentDelete",
			type:"post",
			data:{
				"commentNo":$(e.target).parent().children().val(),
				"commentWriter":$("input[name='loginMemberId']").val(),
				"hostMemberId":$("input[name='hostMemberId']").val()
			},
			dataType:"html",
			success:data=>{
				$("#right-page").html(data);
			}
		}) 
	}
});

$(".diary_co_comment_btn").click(e=>{
	$.ajax({
		url:contextPath+"/diary/diaryCommentWrite",
		type:"post",
		data:{
			"comment_level":2,
			"loginMemberId":$("input[name='loginMemberId']").val(),	
			"diary_comment":$(e.target).prev().val(),						
			"diary_no":$(e.target).parent().parent().prev().val(),
			"diary_comment_ref":$(e.target).parent().parent().prev().prev().val()
		},
		dataType:"html",
		success:data=>{
			$("#right-page").html(data);
		}
	})
});



document.querySelectorAll("#pageBar>a").forEach((v, i)=>{
	$(v).click((e)=>{		
		let strArr=v.id.split("/");
		let cPage=strArr[0];
		let numPerpage=strArr[1];
		console.log(cPage,numPerpage);		
		$.ajax({
			url:contextPath+"/diary/page",
			type:"post",
			data:{				
				"diaryFolderLevel":$(e.target).parent().prev().prev().val(),
				"cPage":cPage,
				"numPerpage":numPerpage,
				"currentPage":$(e.target).parent().prev().val()
			},
			dataType:"html",
			success:data=>{
				$("#right-page").html(data);
			}
		});
	});
});
  
