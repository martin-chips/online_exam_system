<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../common/include-header.jsp" %>
<body>
<form class="layui-form">
    <div class="user_left">
        <input class="layui-hide" name="id" value="${user.id}"/>
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" value="${user.loginName}" name="loginName" disabled
                       class="layui-input layui-disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">昵称</label>
            <div class="layui-input-block">
                <input type="text" value="${user.nickName}" name="nickName" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色</label>
            <div class="layui-input-block">
                <c:if test="${user.userType.equals('1')}">
                    <span>管理员</span>
                </c:if>
                <c:if test="${user.userType.equals('2')}">
                    <span>教师</span>
                </c:if>
                <c:if test="${user.userType.equals('3')}">
                    <span value="">学生</span>
                </c:if>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-block">
                <input type="tel" value="${user.tel}" name="tel" placeholder="请输入手机号码" lay-verify="phone"
                       class="layui-input userPhone">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text" value="${user.email}" name="email" placeholder="请输入邮箱" lay-verify="email"
                       class="layui-input userEmail">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" class="layui-textarea myself"
                          name="remark">${user.remark}</textarea>
            </div>
        </div>
    </div>
    <div class="layui-form-item" style="margin-left: 5%;">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="changeUser">立即提交</button>
            <button type="button" class="layui-btn layui-btn-primary restuserinfo">重置</button>
        </div>
    </div>
</form>

<%@include file="../common/include-footer.jsp" %>
<script>
    var prefix = "/system/user";

    function submitHandler() {
        var data = $("#form-user-update").serializeArray();
        var status = $("input[id='status']").is(':checked') == true ? 0 : 1;
        $.operate.savePut(prefix + "/update", data);
    }

</script>
</body>
</html>
