var prefix = "/purchase"
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
            purchaseName  : {
				required : true
			},
            purchasePrice  : {
				required : true
			},
            purchaseNumber  : {
				required : true
			},
            purchaseAddr  : {
				required : true
			}
		},
		messages : {
            purchaseName  : {
                required : icon + "请输入采购商品名称"
            },
            purchasePrice  : {
                required : icon + "请输入商品价格"
            },
            purchaseNumber  : {
                required : icon + "请输入商品数量"
            },
            purchaseAddr  : {
                required : icon + "请输入商品采购地址"
            }
		}
	})
}