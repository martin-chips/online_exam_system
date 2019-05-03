<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../common/include-header.jsp" %>
<body>
<div class="form-content">
    <form class="layui-form changePwd">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text"
                       value="${user.nickName}"
                       disabled class="layui-input layui-disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">旧密码</label>
            <div class="layui-input-block">
                <input type="password" name="oldPwd" placeholder="请输入旧密码" lay-verify="required|oldPwd"
                       class="layui-input pwd">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码</label>
            <div class="layui-input-block">
                <input type="password" name="newPwd" placeholder="请输入新密码" lay-verify="required|newPwd" id="oldPwd"
                       class="layui-input pwd">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-block">
                <input type="password" name="confirmPwd" placeholder="请确认密码" lay-verify="required|confirmPwd"
                       class="layui-input pwd">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="changePwd">立即修改</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

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
