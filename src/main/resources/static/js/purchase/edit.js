var prefix = "/purchase"
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
// function validate() {
// 	var icon = "<i class='fa fa-times-circle'></i> ";
// 	$("#signupForm").validate({
// 		rules : {
//             typeName : {
// 				required : true
// 			}
// 		},
// 		messages : {
//             typeName : {
// 				required : icon + "请输入菜单名"
// 			}
// 		}
// 	})
// }
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