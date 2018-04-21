var prefix = "/goodsType"
$(function () {
    load();

});
$('#exampleTable').on('load-success.bs.table', function (e, data) {
    if (data.total && !data.rows.length) {
        $('#exampleTable').bootstrapTable('selectPage').bootstrapTable('refresh');
    }
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'typeId', // 列字段名
                        align: 'center',
                        title: '序号' // 列标题
                    },
                    {
                        field: 'typeName',
                        align: 'center',
                        title: '描述'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.typeId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.typeId
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            return e+d;
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
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
            beforeSend: function (request) {
                index = layer.load();
            },
            success: function (r) {

                var obj = JSON.parse(r);
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

function add() {
    layer.open({
        type: 2,
        title: '增加用户权限',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add' // iframe的url
    });
}

function edit(id) {
    layer.open({
        type: 2,
        title: '权限修改',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id  // iframe的url
    });
}
function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['typeId'];

        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            traditional :true,
            url: prefix + '/batchRemove',
            success: function (r) {

                var obj = JSON.parse(r);
                if(obj.result) {
                    layer.msg("删除成功");
                    reLoad();
                }else{
                    layer.msg(obj.error);
                }
            }
        });
    }, function () {
    });
}