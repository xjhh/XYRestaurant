var prefix = "/sys/user"
$(function () {
    laydate({
        elem : '#birth'
    });
});
/**
 * 基本信息提交
 */
$("#base_save").click(function () {
    var hobbyStr = getHobbyStr();
    $("#hobby").val(hobbyStr);
    if($("#basicInfoForm").valid()){
            $.ajax({
                cache : true,
                type : "POST",
                url :"/sys/user/updatePwd",
                data : $('#basicInfoForm').serialize(),
                async : false,
                error : function(request) {
                    laryer.alert("Connection error");
                },
                success : function(data) {
                    var obj = JSON.parse(data);
                    if(obj.result) {
                        parent.layer.msg("修改成功");
                        parent.reLoad();
                        var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                        parent.layer.close(index);
                    }else{
                        parent.layer.msg(obj.error);
                    }
                }
            });
        }

});
$("#pwd_save").click(function () {
    if($("#modifyPwd").valid()){
        $.ajax({
            cache : true,
            type : "POST",
            url :"/sys/user/resetPwd",
            data : $('#modifyPwd').serialize(),
            async : false,
            error : function(request) {
                parent.laryer.alert("Connection error");
            },
            success : function(data) {
                if (data.code == 0) {
                    parent.layer.alert("更新密码成功");
                    $("#photo_info").click();
                } else {
                    parent.layer.alert(data.msg)
                }
            }
        });
    }
});
function getHobbyStr(){
    var hobbyStr ="";
    $(".hobby").each(function () {
        if($(this).is(":checked")){
            hobbyStr+=$(this).val()+";";
        }
    });
   return hobbyStr;
}