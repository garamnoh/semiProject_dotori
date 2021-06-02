document.getElementById("albumMenu").addEventListener("click",(e)=>{
    $.ajax({
        url:contextPath+"/page/minihomeRightPageToAlbum.do",
        type:"post",
        data:{
            "hostMemberId":hostMemberId,
            "folder":e.target.innerText
        },
        dataType:"html",
        success:(data)=>{
            $("#right-page").html(data);
        }
    });
});

$("#addFolderBtn").click((e)=>{
    $.ajax({
        url:contextPath+"/page/minihomeLeftPageToAlbum.do",
        type:"post",
        data:{
            "hostMemberId":hostMemberId,
            "addFolderTitle":$("#addFolderTitle").val()
        },
        dataType:"html",
        success:(data)=>{
            $("#left-page").html(data);
        }
    });
});

$("#deleteFolderBtn").click((e)=>{
    $.ajax({
        url:contextPath+"/page/minihomeLeftPageToAlbum.do",
        type:"post",
        data:{
            "hostMemberId":hostMemberId,
            "deleteFolderTarget":$("#deleteFolderTarget").val()
        },
        dataType:"html",
        success:(data)=>{
            $("#left-page").html(data);
        }
    });
});

$("#uploadPhotoBtn").click((e)=>{
    $.ajax({
        url:contextPath+"/page/minihomeRightPageToUpdateAlbum.do",
        type:"post",
        data:{
            "loginMemberId":loginMemberId,
            "hostMemberId":hostMemberId
        },
        dataType:"html",
        success:(data)=>{
            $("#right-page").html(data);
        }
    });
});