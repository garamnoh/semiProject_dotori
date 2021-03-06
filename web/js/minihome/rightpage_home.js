var columnLength=14;
var rowLength=14;
var signalTable=$("<table>");
var colorStr=$("#colorStrVal").val();
var colorTrArr=colorStr.split("&");

for(let j=0;j<rowLength;j++) {
    let signalTr=$("<tr>");
    let colorTdArr=colorTrArr[j].split("^");
    for(let i=0;i<columnLength;i++) {
        let signalTd=$("<td>");
        signalTd.css("width","20px");
        signalTd.css("height","20px");
        signalTd.css("background-color",colorTdArr[i]);
        signalTr.append(signalTd);
    }
    signalTable.append(signalTr);
}

$("#drawing-signal-box").append(signalTable);

$("#drawing-signal-box td").click((e)=>{
    $(e.target).css("background-color",$("#color-selector").val());
    
    let colorStr="";
    $("#drawing-signal-box tr").each((i,v)=>{
        for(let j=0;j<$(v).children().length;j++) {
            if(j==$(v).children().length-1) {
                colorStr+=$($(v).children()[j]).css("background-color");
            }else{
                console.log($(v).children()[j]);
                colorStr+=$($(v).children()[j]).css("background-color")+"^";
            }
        }
        if(i!=$(v).children().length-1) colorStr+="&";
    });
    console.log(colorStr);
    
    $.ajax({
        url:contextPath+"/ajax/drawingSignal.do",
        type:"post",
        data:{
            "hostMemberId":hostMemberId,
            "colorStr":colorStr
        },
        dataType:"json",
        success:(data)=>{
            console.log(data["colorStr"]);
            socket.send(hostMemberId+"<<<"+data["colorStr"]);
        }
    });
    
});

$("input#resetAllColor").click((e)=>{
    $("#drawing-signal-box td").each((i,v)=>{
        $(v).css("background-color",$("#color-selector").val());
    });

    let colorStr="";
    $("#drawing-signal-box tr").each((i,v)=>{
        for(let j=0;j<$(v).children().length;j++) {
            if(j==$(v).children().length-1) {
                colorStr+=$($(v).children()[j]).css("background-color");
            }else{
                console.log($(v).children()[j]);
                colorStr+=$($(v).children()[j]).css("background-color")+"^";
            }
        }
        if(i!=$(v).children().length-1) colorStr+="&";
    });
    console.log(colorStr);
    
    $.ajax({
        url:contextPath+"/ajax/drawingSignal.do",
        type:"post",
        data:{
            "hostMemberId":hostMemberId,
            "colorStr":colorStr
        },
        dataType:"json",
        success:(data)=>{
            console.log(data["colorStr"]);
            socket.send(hostMemberId+"<<<"+data["colorStr"]);
        }
    });
    
});

socket.onmessage=(e)=>{
    let colorTrArr=e.data.split("&");

    $("#drawing-signal-box tr").each((i,v)=>{
        let colorTdArr=colorTrArr[i].split("^");
        for(let j=0;j<$(v).children().length;j++) {
            $($(v).children()[j]).css("background-color",colorTdArr[j]);
        }
    });
};

$("div.drawing-signal-question").hover((e)=>{
    $(e.target).next().css("display","block");
},(e)=>{
    $(e.target).next().css("display","none");
});