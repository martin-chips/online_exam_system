<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../common/include-header.jsp" %>

<body class="gray-bg">

<div class="container-div">
    <div class="row">
        <div class="col-sm-12 search-collapse">
            <form id="role-form">
                <div class="select-list">
                    <ul>
                        <li>
                            试卷名称：<input type="text" name="nickName"/>
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

            <a class="btn btn-primary btn-edit disabled" onclick="$.operate.editFull()">
                <i class="fa fa-edit"></i> 开始考试
            </a>
        </div>

        <div class="col-sm-12 select-table table-striped">
            <table id="bootstrap-table" data-mobile-responsive="true"></table>
        </div>
    </div>
</div>
<%@include file="../common/include-footer.jsp" %>
<script th:inline="javascript">
    var prefix = "/exam/student";
    $(function () {
        var options = {
            url: prefix + "/list",
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
                }, {
                    field: 'accessrd',
                    title: '状态',
                    formatter: function (value, item, index) {
                        if (item.accessed) {
                            return '<span class="label label-success">已做</span>';
                        } else if (!item.accessed) {
                            return '<span class="label label-primary">未做</span>';
                        }
                    }
                },
                {
                    field: 'score',
                    title: '试卷总分',
                    sortable: true
                },
                {
                    field: 'totalScore',
                    title: '考试成绩',
                    formatter: function (value, item, index) {
                        if (item.accessed && item.totalScore != null) {
                            return '<span class="label label-info">' + item.totalScore + '</span>';
                        } else if (item.accessed && item.totalScore == null) {
                            return '<span class="label label-danger">' + "正在阅卷中" + '</span>';
                        }
                    }
                },
                {
                    title: '操作',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var actions = [];
                        if (row.accessed == true) {
                            actions.push('<a class="btn btn-warning btn-xs ' + '" target="_blank" href="/exam/student/detail/' + row.examId + '"><i class="fa fa-edit"></i>详情</a> ');
                        } else {
                            actions.push('<a class="btn btn-success btn-xs ' + '" target="_blank" href="/exam/student/' + row.examId + '"><i class="fa fa-edit"></i>开始考试</a> ');
                        }
                        return actions.join('');
                    }
                }]
        };
        $.table.init(options);
    });

    function makeExam(examId) {
        $.operate.get("/exam/student/" + examId);
    }
</script>
</body>
</html>
