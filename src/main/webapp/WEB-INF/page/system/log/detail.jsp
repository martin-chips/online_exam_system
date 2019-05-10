<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../common/include-header.jsp" %>
<body>
<div class="wrapper wrapper-content animated fadeInRight ibox-content">
    <form class="form-horizontal m-t" id="signupForm">
        <div class="form-group">
            <label class="col-sm-2 control-label">操作类型：</label>
            <div class="form-control-static">
                ${log.type}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">访问时间：</label>
            <div class="form-control-static">
                <fmt:formatDate value="${log.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">操作URL：</label>
            <div class="form-control-static">
                ${log.requestUri}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">操作者：</label>
            <div class="form-control-static">
                ${log.username}-${log.area}-${log.province}-${log.city} -${log.isp}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">操作详细：</label>
            <div class="form-control-static">
                ${log.classMethod}<span> ${log.httpMethod}</span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">请求参数：</label>
            <div class="form-control-static">
                <pre>
                    ${log.data}
                </pre>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">返回参数：</label>
            <div class="form-control-static">
                <pre id="operParam">
                    ${log.response}
                </pre>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">耗时ms：</label>
            <div class="form-control-static">
                ${log.useTime}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">访问设备：</label>
            <div class="form-control-static">
                ${log.browser}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">异常信息：</label>
            <div class="form-control-static">
                ${log.exception}
            </div>
        </div>
    </form>
</div>

<%@include file="../../common/include-footer.jsp" %>

<script src="/static/plugin/select/select2.js"></script>
</body>
</html>
