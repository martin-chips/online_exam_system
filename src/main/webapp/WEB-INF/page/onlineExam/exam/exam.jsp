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
                            试卷名称：<input type="text" name="nickName" />
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
            <a class="btn btn-success" onclick="$.operate.addFull()">
                <i class="fa fa-plus"></i> 新增
            </a>
            <a class="btn btn-primary btn-edit disabled" onclick="$.operate.editFull()">
                <i class="fa fa-edit"></i> 修改
            </a>
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
<script th:inline="javascript">
    var prefix = "/onlineExam/exam";



    $(function () {
        var options = {
            url: prefix + "/list",
            createUrl: prefix + "/add",
            updateUrl: prefix + "/update/{id}",
            removeUrl: prefix,
            sortName: "",
            height: 500,
            modalName: "试卷",
            columns: [{
                checkbox: true
            },
                {
                    field: 'examId',
                    title: '试卷编号'
                },
                {
                    field: 'examName',
                    title: '试卷题目',
                    sortable: true
                },
                {
                    field: 'examStartDate',
                    title: '考试开始时间',
                    sortable: true
                },
                {
                    field: 'examLastTime',
                    title: '试卷持续时间（分钟）',
                    sortable: true
                },
                {
                    field: 'reviewerId',
                    title: '批卷人ID',
                    sortable: true
                },
                {
                    field: 'createDate',
                    title: '创建时间',
                    sortable: true
                },
                {
                    title: '操作',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var actions = [];
                        actions.push('<a class="btn btn-success btn-xs ' + '" href="#" onclick="$.operate.editFull(\'' + row.examId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
                        actions.push('<a class="btn btn-danger btn-xs ' + '" href="#" onclick="$.operate.remove(\'' + row.examId + '\')"><i class="fa fa-remove"></i>删除</a>');
                        return actions.join('');
                    }
                }]
        };
        $.table.init(options);
    });
</script>
</body>
</html>
