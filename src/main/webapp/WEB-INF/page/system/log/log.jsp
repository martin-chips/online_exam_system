<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../common/include-header.jsp" %>

<body class="gray-bg">

<div class="container-div">
    <div class="row">
        <div class="col-sm-12 search-collapse">
            <form id="role-form">
                <div class="select-list">
                    <ul>
                        <li>
                            用户名称：<input type="text" name="username"/>
                        </li>
                        <li>
                            用户账号：<input type="text" name="loginName"/>
                        </li>
                        <li class="select-time">
                            <label>创建时间： </label>
                            <input type="text" class="time-input" id="startTime" placeholder="开始时间"
                                   name="params[beginTime]"/>
                            <span>-</span>
                            <input type="text" class="time-input" id="endTime" placeholder="结束时间"
                                   name="params[endTime]"/>
                        </li>
                        <li>
                            <a class="btn btn-primary btn-rounded btn-sm" onclick="$.table.search()"><i
                                    class="fa fa-search"></i>&nbsp;搜索</a>
                            <a class="btn btn-warning btn-rounded btn-sm" onclick="$.form.reset()"><i
                                    class="fa fa-refresh"></i>&nbsp;重置</a>
                        </li>
                    </ul>
                </div>
            </form>
        </div>

        <div class="btn-group-sm" id="toolbar" role="group">
            <a class="btn btn-danger btn-del disabled" onclick="$.operate.removeAll()">
                <i class="fa fa-remove"></i> 删除
            </a>
        </div>
        <div class="col-sm-12 select-table table-striped">
            <table id="bootstrap-table" data-mobile-responsive="true"></table>
        </div>
    </div>
</div>
<%@include file="../../common/include-footer.jsp" %>
<script>
    var prefix = "/system/log";
    $(function () {
        var options = {
            url: prefix + "/list",
            removeUrl: prefix,
            detailUrl: prefix + "/detail/{id}",
            sortName: "",
            height: 600,
            modalName: "日志",
            columns: [{
                checkbox: true
            },
                {
                    field: 'id',
                    title: 'ID'
                },
                {
                    field: 'type',
                    title: '请求类型'
                },
                {
                    field: 'username',
                    title: '操作者'
                },
                {
                    field: 'requestUri',
                    title: '请求地址'
                },
                {
                    field: 'httpMethod',
                    title: '操作方式'
                },
                {
                    field: 'useTime',
                    title: '执行时间(ms)'
                },
                {
                    field: 'browser',
                    title: '浏览器'
                },
                {
                    field: 'createDate',
                    title: '创建时间',
                }, //单元格内容水平居中
                {
                    title: '操作',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var actions = [];
                        actions.push('<a class="btn btn-warning btn-xs ' + '" href="#" onclick="$.operate.detail(\'' + row.id + '\')"><i class="fa fa-edit"></i>详细</a> ');
                        actions.push('<a class="btn btn-danger btn-xs ' + '" href="#" onclick="$.operate.remove(\'' + row.id + '\')"><i class="fa fa-remove"></i>删除</a>');
                        return actions.join('');
                    }
                }
            ]
        };
        $.table.init(options);
    });
</script>
</body>
</html>
