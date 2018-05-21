var prefix = "/goods"
$(function() {
	validateRule();
	//打开图标列表
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
            goodName : {
                required : true
            },
            goodDepict : {
                required : true
            },
			goodPrice : {
                required : true
            },
            goodStock : {
                required : true
            },
            goodImg : {
                required : true
            }
		},
		messages : {
            goodName : {
				required : icon + "商品名称不能为空"
			},
            goodDepict : {
                required : icon + "商品描述不能为空"
            },
            goodPrice : {
                required : icon + "商品单价不能为空"
            },
            goodStock : {
                required : icon + "商品库存不能为空"
            },
            goodImg : {
                required : icon + "请选择商品图片地址"
            }
		}
	})
}