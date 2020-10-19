$(function() {
    $( "#accordion" ).accordion();
});

$.ajax({
    url:'/getClientInfoList',
    data:{},
    type:'post',
    async: false,
    cache:false,
    dataType:'json',
    success:function(data){
        var html = "";
        $("#clientInfo").empty();
        for (var i = 0 ; i < data.length ; i++) {
            var item = data[i];
            html += '    <div class="panel panel-default">'+
                '        <div class="panel-heading" role="tab" id="getClientInfoList_' +i+'">'+
                '            <h4 class="panel-title">'+
                '                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">'+
                '                    客户端 ' + i +
                '                </a>'+
                '            </h4>'+
                '        </div>'+
                '        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">'+
                '            <div class="panel-body">'+
                '               ' + item +
                '            </div>'+
                '        </div>'+
                '    </div>';
            $("#clientInfo").append(html);
        }
        getGlobalClientInfo();
    },
    error:function(){
        alert("异常！");
    }
});

function getGlobalClientInfo(){
    $.ajax({
        url:'/getGlobalClientInfo',
        data:{},
        type:'post',
        async: false,
        cache:false,
        dataType:'json',
        success:function(data){
            var html = "";
            html += '    <div class="panel panel-default">'+
                '        <div class="panel-heading" role="tab" id="getGlobalClientInfo">'+
                '            <h4 class="panel-title">'+
                '                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">'+
                '                    服务端' +
                '                </a>'+
                '            </h4>'+
                '        </div>'+
                '        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">'+
                '            <div class="panel-body">'+
                '               ' + 'data' +
                '            </div>'+
                '        </div>'+
                '    </div>';
            $("#clientInfo").append(html);
            console.info(html);
        },
        error:function(){
            alert("异常！");
        }
    });
}
