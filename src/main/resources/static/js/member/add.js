var prefix = "/member"
$(function() {
	validateRule();
});
$.validator.setDefaults({
	submitHandler : function() {
		submit01();
	}
});
function submit01() {
	$.ajax({
		cache : true,
		type : "post",
		url : prefix + "/insert",
		data : $('#signupForm').serialize(),
		async : false,
		error : function(request) {
			laryer.alert("Connection error");
		},
		success : function(data) {
            var obj = JSON.parse(data);
            if(obj.result) {
                parent.layer.msg("添加成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            }else{
                parent.layer.msg(obj.error);
            }
		}
	});
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {

            memberName : {
				required : true
			},
            memberPassword : {
				required : true,
                minlength : 6
			},
            memberPhone : {
				required : true
			}
		},
		messages : {
            memberName : {
                required :  icon + "请输入会员名称"
            },
            memberPassword : {
                required :  icon + "请设置密码",
                minlength : icon + "密码必须6个字符以上"
            },
            memberPhone : {
                required :  icon + "请输入联系电话"
            }
		}
	})
}