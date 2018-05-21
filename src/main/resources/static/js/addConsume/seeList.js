var prefix = "/addConsume"
var isupdate;
$(function () {

    // alert(parent.goodList)
    isupdate = ($("#seeType").val() == 2);
    if (isupdate) {
        goodList = $("#goodId").val().split(",");
        $(".goodSize").text(goodList.length);
    } else {
        $(".goodSize").text(parent.goodList.length);
    }

    setTotal();
    load();
});
var goodList = [];

Array.prototype.remove = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};


function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/seeGoodList", // 服务器数据的加载地址
                contentType: "application/x-www-form-urlencoded",
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                singleSelect: false, // 设置为true将禁止多选
                pageSize: 3, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                queryParams: function (params) {
                    if (isupdate) {
                        return {
                            name: goodList.toString()
                        };
                    }
                    return {
                        name: parent.goodList.toString()
                    };
                },
                columns: [
                    {
                        field: 'goodNumber',
                        align: 'center',
                        title: '商品编号',
                        formatter: function (value, row, index) {
                            return row.goodNumber;
                        }
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
                        title: '商品单价'
                    }, {
                        field: 'goodStock',
                        align: 'center',
                        width: "4%",
                        title: '库存'
                    },
                    {
                        title: '购买数量',
                        field: 'goodBuySize',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a onclick="less(\'' + row.goodId + '\' )" ><i class="fa fa-minus-circle" ></i></a>&nbsp;&nbsp;'
                                + row.goodBuySize
                                + '&nbsp;&nbsp;<a onclick="add(\'' + row.goodId + '\')" <i class="fa fa-plus" ></i> </a>';
                            return e;
                        }
                    }, {
                        field: 'goodBuyTotal',
                        align: 'center',
                        width: "4%",
                        title: '商品总价'
                    }]
            });
}


function add(id) {
    $.ajax({
        type: "GET",
        url: "/goods/isNotStock/" + id + "/" + getNum(id),
        success: function (data) {
            var obj = JSON.parse(data);
            if (obj.result) {
                if (!isupdate) {
                    parent.goodList.push(id);
                    $("#goodId").val(parent.goodList.toString());
                    $(".goodSize").text(parent.goodList.length);
                    parent.reLoad();
                } else {
                    goodList.push(id);
                    $("#goodId").val(goodList.toString());
                    $(".goodSize").text(goodList.length);

                }
                setTotal();
                $('#exampleTable').bootstrapTable('refresh');
            } else {
                parent.layer.msg(obj.error);
            }
        }
    });
    setTotal();
}

function updateGoods() {
    $.ajax({
        type: "post",
        url: prefix + "/updateGoodConsume/" + $("#consumeId").val() + "/" + goodList.toString(),
        async: false,
        error: function (request) {
            laryer.alert("Connection error");
        },
        success: function (data) {
            var obj = JSON.parse(data);
            if (obj.result) {
                parent.layer.msg("修改成功");
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            } else {
                parent.layer.msg(obj.error);
            }
        }
    });
}

function getNum(id) {
    var num = 0;
    $.each(isupdate ? goodList : parent.goodList, function (index, value) {
        if (value == id) {
            num += 1;
        }
    })
    return num;
}

function less(id) {
    if (!isupdate) {
        parent.less(id)
        $(".goodSize").text(parent.goodList.length);
        reLoad();
        return
    }
    goodList.remove(id)
    $("#goodId").val(goodList.toString());
    $(".goodSize").text(goodList.length);
    setTotal();
    $('#exampleTable').bootstrapTable('refresh');
}


function setTotal() {
    var list = isupdate ? goodList.toString() : parent.goodList.toString();
    $.ajax({
        type: "GET",
        url: prefix + "/getTotal/" + list,
        success: function (data) {
            $(".total").text(data);
        }
    });

}

function reLoad() {
    setTotal();
    $('#exampleTable').bootstrapTable('refresh');
}