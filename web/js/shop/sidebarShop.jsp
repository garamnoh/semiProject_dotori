<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
$('.menuOption>.menuTitle').on('click', (e)=>{
    $(e.target).next().slideToggle();
    $('.menuOption>.menuTitle').not($(e.target)).next().slideUp();
});
$(".menuSub>p").on("click",(e)=>{
    let menuTitle=e.target.id;
    $.ajax({
        url:"<%=request.getContextPath()%>/page/"+menuTitle+".do",
        type:"post",
        dataType:"html",
        success:(data)=>{
            $("#contentBox").html(data);
        }
    });
});