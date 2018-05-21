var prefix = "/goods"

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
                contentType: "application/x-www-form-urlencoded",
                // showRefresh : true,
                // showToggle : true,
                // showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                singleSelect: false, // 设置为true将禁止多选
                pageSize: 4, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                queryParams: function (params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        name: $('#searchName').val(),
                        type: $('#goodsType').val()
                    };
                },
                columns: [
                    {
                        field: 'goodId',
                        align: 'center',
                        title: '序号'
                    }, {
                        field: "goodImg",
                        title: "商品图片",
                        align: 'center',
                        width: "5%",
                        formatter: function (value, row, index) {
                            var e = '<img style ="width: 100px;height: 100px;" src=' + row.goodImg + '/>';
                            return e;
                        }
                    }, {
                        field: 'goodNumber',
                        align: 'center',
                        title: '商品编号'
                    }, {
                        field: 'goodName',
                        align: 'center',
                        title: '商品名称'
                    }, {
                        field: 'goodDepict',
                        align: 'center',
                        title: '商品描述'
                    }, {
                        field: 'goodTypeName',
                        align: 'center',
                        title: '商品类型'
                    }, {
                        field: 'goodPrice',
                        align: 'center',
                        width: "4%",
                        title: '商品价格'
                    }, {
                        field: 'goodStock',
                        align: 'center',
                        width: "4%",
                        title: '库存'
                    },
                    {
                        title: '操作',
                        field: 'goodId',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a  class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="add(\''
                                + row.goodId
                                + '\')"><i class="fa fa-plus"></i></a> ';
                            return e;
                        }
                    }]
            });
}

var goodList = [];
Array.prototype.remove = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};

function add(id) {

    $.ajax({
        type: "GET",
        url: "/goods/isNotStock/"+id+"/"+getNum(id),
        success: function (data) {
            var obj = JSON.parse(data);
            if (obj.result) {
                goodList.push(id);
                $("#goodId").val(goodList.toString());
                $(".goodSize").text(goodList.length);
            } else {
                parent.layer.msg(obj.error);
            }
        }
    });
}

function less(id) {

    goodList.remove(id)
    $("#goodId").val(goodList.toString());
    $(".goodSize").text(goodList.length);
}

function getNum(id) {
    var index = 0;
    $.each(goodList, function (index, value) {
        if (value == id){
            index ++;
        }
    })
    return index;
}

function see(id) {
    if(goodList.length == 0){
        layer.msg('您还未选择商品', {
            time: 0 //不自动关闭
            ,btn: ['去选择']
            ,yes: function(index){
                layer.close(index);
            }
        });
        return;
    }
    layer.open({
        type: 2,
        title: '查看商品',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1120px', '580px'],
        content: 'see/'+id // iframe的url
    });
}

function reLoad() {

    $("#goodId").val(goodList.toString());
    $(".goodSize").text(goodList.length);
}

function getTreeData() {
    $.ajax({
        type: "GET",
        url: "/goodsType/tree",
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
    $('#exampleTable').bootstrapTable('refresh', data.selected);
    if (data.selected == -1) {
        $("#goodsType").val(-1);
        $('#exampleTable').bootstrapTable('refresh');
    } else {
        var opt = data.selected;
        layer.msg("显示" + data.instance.get_selected(true)[0].text + "商品");

        $("#goodsType").val(opt);
        $('#exampleTable').bootstrapTable('refresh');
    }

});

function buy() {
    layer.prompt({title: '请输入会员编号',value: "0"},function (val, index) {
        if (val != 0 ){
            $.ajax({
                type: "post",
                url: "/member/isNotEmpty/" + val,
                async: false,
                error: function (request) {
                    laryer.alert("Connection error");
                },
                success: function (data) {
                    var obj = JSON.parse(data);
                    if (obj.result) {
                        layer.close(index);
                        $("#memberCard").val(val);
                        goBuy();
                    } else {
                        parent.layer.msg(obj.error);
                    }
                }
            });
        }else{
            goBuy();
        }
    });
}

function goBuy() {
    $.ajax({
        cache: true,
        type: "post",
        url: "/addConsume/insert",
        data: {"memberCard": $("#memberCard").val(), "deskId": $("#deskId").val(), "goodId": $("#goodId").val()},
        async: false,
        error: function (request) {
            laryer.alert("Connection error");
        },
        success: function (data) {
            var obj = JSON.parse(data);
            if (obj.result) {
                parent.layer.msg("点菜成功");
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.re();
                parent.layer.close(index);
            } else {
                parent.layer.msg(obj.error);
            }
        }
    });
}