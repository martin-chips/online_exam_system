<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>系统用户列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/static/css/user.css" media="all"/>
    <link href="/static//bootstrap-table/bootstrap-table.min.css" rel="stylesheet"/>
    <link href="/static/bootstrap/bootstrap.min.css" rel="stylesheet"/>
    <link href="/static/css/ui.css" rel="stylesheet"/>
    <link href="/static/bootstrap-table/extensions/columns/bootstrap-table-fixed-columns.css" rel="stylesheet"/>
</head>
<body class="childrenBody">

<div class="layui-form users_list">
    <div class="container-div">
        <div class="row">
            <div class="col-sm-12 search-collapse">
                <form id="role-form">
                    <div class="select-list">
                        <ul>
                            <li>
                                角色名称：<input type="text" name="roleName"/>
                            </li>
                            <li>
                                权限字符：<input type="text" name="roleKey"/>
                            </li>
                            <li>
                                角色状态：
                                </select>
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
                <a class="btn btn-success" onclick="$.operate.add()">
                    <i class="fa fa-plus"></i> 新增
                </a>
                <a class="btn btn-primary btn-edit disabled" onclick="$.operate.edit()">
                    <i class="fa fa-edit"></i> 修改
                </a>
                <a class="btn btn-danger btn-del disabled" onclick="$.operate.removeAll()">
                    <i class="fa fa-remove"></i> 删除
                </a>
                <a class="btn btn-warning" onclick="$.table.exportExcel()">
                    <i class="fa fa-download"></i> 导出
                </a>
            </div>
            <div class="col-sm-12 select-table table-striped">
                <table id="bootstrap-table" data-mobile-responsive="true"></table>
            </div>
        </div>
    </div>

</div>
<div id="page"></div>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript" src="/static/js/tools.js"></script>
<script type="text/javascript" src="/static/js/jquery.min.js"></script>
<script src="/static/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/static/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/static/bootstrap-table/extensions/mobile/bootstrap-table-mobile.js"></script>
<script src="/static/bootstrap-table/extensions/toolbar/bootstrap-table-toolbar.min.js"></script>
<script src="/static/bootstrap-table/extensions/columns/bootstrap-table-fixed-columns.js"></script>
<script src="/static/js/common.js"></script>
<script th:inline="javascript">
    var prefix = "/system/user";
    $(function () {
        var options = {
            url: prefix + "/list",
            createUrl: prefix + "/add",
            updateUrl: prefix + "/edit/{id}",
            removeUrl: prefix + "/remove",
            exportUrl: prefix + "/export",
            sortName: "roleSort",
            modalName: "角色",
            columns: [{
                checkbox: true
            },
                {
                    field: 'roleId',
                    title: '角色编号'
                },
                {
                    field: 'roleName',
                    title: '角色名称',
                    sortable: true
                },
                {
                    field: 'roleKey',
                    title: '权限字符',
                    sortable: true
                },
                {
                    field: 'roleSort',
                    title: '显示顺序',
                    sortable: true
                },
                {
                    title: '角色状态',
                    align: 'center',
                    formatter: function (value, row, index) {
                        return statusTools(row);
                    }
                },
                {
                    field: 'createTime',
                    title: '创建时间',
                    sortable: true
                },
                {
                    title: '操作',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var actions = [];
                        actions.push('<a class="btn btn-success btn-xs ' + '" href="#" onclick="$.operate.edit(\'' + row.roleId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
                        actions.push('<a class="btn btn-danger btn-xs ' + '" href="#" onclick="$.operate.remove(\'' + row.roleId + '\')"><i class="fa fa-remove"></i>删除</a>');
                        return actions.join('');
                    }
                }]
        };
        $.table.init(options);
    });

    /* 角色管理-分配数据权限 */
    function rule(roleId) {
        var url = prefix + '/rule/' + roleId;
        $.modal.open("分配数据权限", url);
    }

    /* 角色状态显示 */
    function statusTools(row) {
        if (row.status == 1) {
            return '<i class=\"fa fa-toggle-off text-info fa-2x\" onclick="enable(\'' + row.roleId + '\')"></i> ';
        } else {
            return '<i class=\"fa fa-toggle-on text-info fa-2x\" onclick="disable(\'' + row.roleId + '\')"></i> ';
        }
    }

    /* 角色管理-停用 */
    function disable(roleId) {
        $.modal.confirm("确认要停用角色吗？", function () {
            $.operate.post(prefix + "/changeStatus", {"roleId": roleId, "status": 1});
        })
    }

    /* 角色管理启用 */
    function enable(roleId) {
        $.modal.confirm("确认要启用角色吗？", function () {
            $.operate.post(prefix + "/changeStatus", {"roleId": roleId, "status": 0});
        })
    }
</script>
</body>
</html>