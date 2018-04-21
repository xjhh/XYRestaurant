var prefix = "/sys/user"

var power = -1;
$(function () {
    getTreeData();
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                contentType : "application/x-www-form-urlencoded",
                // showRefresh : true,
                // showToggle : true,
                // showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                singleSelect: false, // 设置为true将禁止多选
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                queryParams: function (params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        name: $('#searchName').val(),
                        managerPower:$('#power').val()
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'managerId', // 列字段名
                        title: '序号' // 列标题
                    },
                    {
                        field: 'managerUsername',
                        title: '用户名'
                    },
                    {
                        field: 'managerPower',
                        title: '用户权限'
                    },
                    {
                        title: '操作',
                        field: 'managerId',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a  class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.managerId
                                + '\')"><i class="fa fa-edit "></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.managerId
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm" href="#" title="重置密码"  mce_href="#" onclick="resetPwd(\''
                                + row.managerId
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e + d + f;
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    // iframe层
    layer.open({
        type: 2,
        title: '增加用户',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add'
    });
}

function edit(id) {
    layer.open({
        type: 2,
        title: '用户修改',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            type: 'POST',
            data: {
                'id': id
            },
            traditional: true,
            url: prefix + '/batchRemove',
            success: function (r) {

                var obj = JSON.parse(r);
                if (obj.result) {
                    layer.msg("删除成功");
                    reLoad();
                } else {
                    layer.msg(obj.error);
                }
            }
        });
    })
}

function resetPwd(id) {
    layer.open({
        type: 2,
        title: '重置密码',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['400px', '260px'],
        content: prefix + '/resetPwd/' + id // iframe的url
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
            ids[i] = row['managerId'];

        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            }, contentType: "application/x-www-form-urlencoded",
            traditional: true,
            url: prefix + '/batchRemove',
            success: function (r) {

                var obj = JSON.parse(r);
                if (obj.result) {
                    layer.msg("删除成功");
                    reLoad();
                } else {
                    layer.msg(obj.error);
                }
            }
        });
    }, function () {
    });
}

function getTreeData() {
    $.ajax({
        type: "GET",
        url: "/sys/power/tree",
        success: function (tree) {
            loadTree(tree);
        }
    });
}

function loadTree(tree) {
    $('#jstree').jstree({
        'core': {
            'data': tree
        },
        "plugins": ["search"]
    });
    $('#jstree').jstree().open_all();
}

$('#jstree').on("changed.jstree", function (e, data) {
    // load(data.selected);
        $('#exampleTable').bootstrapTable('refresh',data.selected );
    if (data.selected == -1) {
        power = "";
        var opt = {
            query: {
                powmanagerPowerer: -1,
            }
        }
        $("#power").val(-1);
        $('#exampleTable').bootstrapTable('refresh');
    } else {
        var opt = {
            query: {
                managerPower: data.selected[1],
            }
        }
        $("#power").val(data.selected);
        $('#exampleTable').bootstrapTable('refresh');
    }

});