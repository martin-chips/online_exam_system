<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../common/include-header.jsp" %>
<body>
<div class="form-content">
    <form id="form-exam-add" class="form-horizontal">
        <h4 class="form-header h4">基本信息</h4>
        <div class="row">
            <div class="col-sm-6">
                <div class="form-group">
                    <input type="hidden" name="examId" value="${exam.examId}"/>
                    <label class="col-sm-4 control-label"><span style="color: red; ">*</span>试卷标题：</label>
                    <div class="col-sm-8">
                        <input name="examName" autocomplete="off" placeholder="请输入试卷题目" class="form-control" type="text"
                               value="${exam.examName}"
                               required
                               maxlength="30" required>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color: red; ">*</span>开始时间：</label>
                    <div class="col-sm-8">
                        <input type="text" id="examStartDate" placeholder="开始时间" class="datetime-input form-control"
                               readonly
                               required

                               value="<fmt:formatDate value="${exam.examStartDate}" pattern="yyyy-MM-dd HH:mm:ss" />"
                               name="examStartDate"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6">
                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color: red; ">*</span>持续时间：</label>
                    <div class="col-sm-8">
                        <input name="examLastTime" autocomplete="off" class="form-control" type="number"
                               value="${exam.examLastTime}"
                               maxlength="50"
                               placeholder="请输入持续时间" required>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label class="col-xs-2 control-label">指定阅卷老师：</label>
                    <div class="col-xs-4">
                        <select class="form-control select2-hidden-accessible" required name="reviewerId">
                            <option>请选择</option>
                            <c:forEach items="${trachers}" var="teacher">
                                <c:if test="${teacher.id==exam.reviewerId}">
                                    <option value="${teacher.id}" selected>${teacher.nickName}</option>
                                </c:if>
                                <c:if test="${teacher.id==exam.reviewerId}">
                                    <option value="${teacher.id}">${teacher.nickName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label class="col-xs-2 control-label">指定参考学生：</label>
                    <div class="col-xs-4">
                        <select id="studentIds" class="form-control select2-hidden-accessible" multiple="">
                            <c:forEach items="${students}" var="student">
                                <c:if test="${student.studentCheckFlag}">
                                    <option value="${student.id}" selected>${student.nickName}</option>
                                </c:if>
                                <c:if test="${!student.studentCheckFlag}">
                                    <option value="${student.id}">${student.nickName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
        </div>

    </form>
    <h4 class="form-header h4">指定试卷的题目</h4>
    <div class="row">
        <div class="col-sm-12  ">
            <table id="bootstrap-table1" data-mobile-responsive="true"></table>
        </div>
    </div>
    <div class="container-div">
        <div class="row">
            <div class="col-sm-12 search-collapse">
                <form id="role-form">
                    <div class="select-list">
                        <ul>
                            <li>
                                试题名称：<input type="text" name="nickName"/>
                            </li>
                            <li>
                                试题类型：<input type="text" name="loginName"/>
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
                    <i class="fa fa-plus"></i> 添加试题
                </a>
                <span class="text-info"> 请直接选择需要添加的试题</span>
            </div>

            <div class="col-sm-12 select-table table-striped">
                <table id="bootstrap-table" data-mobile-responsive="true"></table>
            </div>
        </div>
    </div>
    <%@include file="../../common/include-footer.jsp" %>
    <input type="hidden" id="checkedQuestion" value="${exam.idsStr}"/>
    <script>
        $(function () {
            var servicesArray = data.split(",");
            $("input[type=checkbox][name=btSelectItem]").each(function (i, e) {
                for (var i = 0; i < servicesArray.length; i++) {
                    if ($(this).val() == servicesArray[i]) {
                        $(this).iCheck('check');
                    }
                }
            });

        });

        var data = $("#checkedQuestion").val();
        var prefix = "/onlineExam/question";
        $(function () {
            var options = {
                url: prefix + "/list",
                createUrl: prefix + "/add",
                updateUrl: prefix + "/update/{id}",
                removeUrl: prefix,
                sortName: "",
                sortOrder: "desc",
                escape: true,
                modalName: "试题",
                clickToSelect: true,
                rememberSelected: true,
                columns: [{
                    field: 'state',
                    checkbox: true,
                    formatter: function (value, item, index) {
                        var temp = data.split(",");
                        for (var i = 0; i < temp.length; i++) {
                            if (temp[i] == item.id) {
                                return {
                                    checked: true//设置选中
                                };
                            }
                        }
                        return {
                            checked: false//设置选中
                        };
                    }
                },
                    {
                        field: 'id',
                        title: '试题ID'
                    },
                    {
                        field: 'title',
                        title: '试题的标题',
                        sortable: true
                    },
                    {
                        field: 'score',
                        title: '分值',
                        sortable: true
                    },
                    {
                        field: 'type',
                        title: '试题类型',
                        formatter: function (value, item, index) {
                            if (item.type == '1') {
                                return '<span class="label label-success">单选</span>';
                            } else if (item.type == '2') {
                                return '<span class="label label-primary">多选</span>';
                            } else if (item.type == '3') {
                                return '<span class="label label-danger">填空</span>';
                            } else if (item.type == '4') {
                                return '<span class="label label-info">判断</span>';
                            } else if (item.type == '5') {
                                return '<span class="label label-warning">简答</span>';
                            }
                        }
                    },
                    {
                        field: 'createDate',
                        title: '创建时间',
                        sortable: true
                    }]
            };
            $.table.init(options);
        });

        function submitHandler() {
            if ($.validate.form()) {
                var data = $("#form-exam-add").serializeArray();
                var ids = {"name": "ids", value: $.table.selectFirstColumns()}
                data.push(ids);
                //获取参加的学生的id
                var studentIds = $.form.selectSelects("studentIds");
                data.push({"name": "studentIds", "value": studentIds});
                console.log(data)
                $.operate.savePut("/onlineExam/exam/update", data);
            }
        }
    </script>

</div>

<%@include file="../../common/include-footer.jsp" %>

<script src="/static/plugin/select/select2.js"></script>

</body>
</html>
