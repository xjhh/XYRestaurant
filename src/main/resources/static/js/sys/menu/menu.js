var prefix = "/sys/menu"
$(document).ready(function () {
    load();
});
var load = function () {
    $('#exampleTable')
        .bootstrapTreeTable(
            {
                id: 'menuId',
                code: 'menuId',
                parentCode: 'parentId',
                type: "post", // 请求数据的ajax类型
                url: prefix + '/list', // 请求数据的ajax的url
                ajaxParams: {sort:'order_num'}, // 请求数据的ajax的data属性
                striped: true, // 是否各行渐变色
                bordered: true, // 是否显示边框
                expandAll: false, // 是否全部展开
                // toolbar : '#exampleToolbar',
                columns: [
                    {
                        title: '编号',
                        field: 'menuId',
                        visible: false,
                        align: 'center',
                        valign: 'center',
                        width: '5%',
                        formatter: function (item, index) {
                            return '<input type="checkbox" name="checkbox" value='+item.menuId+'>'+item.menuId;
                        }
                    },
                    {
                        title: '名称',
                        valign: 'center',
                        field: 'menuName',
                        width: '20%'
                    },

                    {
                        title: '图标',
                        field: 'menuIco',
                        align: 'center',
                        valign: 'center',
                        width : '5%',
                        formatter: function (item, index) {
                            return item.menuIco == null ? ''
                                : '<i class="' + item.menuIco
                                + ' fa-lg"></i>';
                        }
                    },
                    {
                        title: '类型',
                        field: 'type',
                        align: 'center',
                        valign: 'center',
                        width : '10%',
                        formatter: function (item, index) {

                            if (item.type == 0) {
                                return '<span class="label label-primary">目录</span>';
                            }
                            if (item.type == 1) {
                                return '<span class="label label-success">菜单</span>';
                            }
                            if (item.type == 2) {
                                return '<span class="label label-warning">按钮</span>';
                            }
                        }
                    },
                    {
                        title: '地址',
                        valign: 'center',
                        width : '20%',
                        field: 'menuUri'
                    },
                    {
                        title: '权限标识',
                        valign: 'center',
                        width : '20%',
                        field: 'menuPower'
                    },
                    {
                        title: '操作',
                        field: 'menuId',
                        align: 'center',
                        valign: 'center',
                        formatter: function (item, index) {
                            var e = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + item.menuId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var p = '<a class="btn btn-primary btn-sm '
                                + '" href="#" mce_href="#" title="添加下级" onclick="add(\''
                                + item.menuId
                                + '\')"><i class="fa fa-plus"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm '
                                + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + item.menuId
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            return e + d + p;
                        }
                    }]
            });
}

function reLoad() {
    load();
}

function add(pId) {
    layer.open({
        type: 2,
        title: '增加菜单',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add/' + pId // iframe的url
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (data) {
                var obj = JSON.parse(data);
                if(obj.result) {
                    layer.msg("删除成功");
                    reLoad();
                }else{
                    layer.msg(obj.error);
                }
            }
        });
    })
}

function edit(id, name) {
    layer.open({
        type: 2,
        title: '菜单修改',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id  // iframe的url
    });
}
