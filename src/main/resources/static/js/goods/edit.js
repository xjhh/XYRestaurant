var prefix = "/goods"
$(function() {

    layer.closeAll('loading');
	validateRule();

});
$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "post",
		url : prefix + "/update",
		data : $('#signupForm').serialize(),// 你的formid
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
function validate() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
            powerDepict : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入菜单名"
			}
		}
	})
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