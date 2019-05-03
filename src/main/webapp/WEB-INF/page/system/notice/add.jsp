<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../common/include-header.jsp" %>
<body>
<div class="form-content">
    <form id="form-notice-add" class="form-horizontal">
        <h4 class="form-header h4">基本信息</h4>
        <div class="row">
            <div class="col-sm-6">
                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color: red; ">*</span>标题：</label>
                    <div class="col-sm-8">
                        <input name="title" autocomplete="off" placeholder="请输入标题" class="form-control" type="text"
                               maxlength="30" required>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6">
                <div class="form-group">
                    <label class="col-xs-4 control-label">类型：</label>
                    <div class="col-xs-8">
                        <label class="radio-box">
                            <input name="type" type="radio" value="1" checked>通知
                        </label>
                        <label class="radio-box">
                            <input name="type" type="radio" value="2">公告
                        </label>
                    </div>
                </div>
            </div>
        </div>
        <h4 class="form-header h4">公告内容</h4>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label class="col-xs-2 control-label">内容：</label>
                    <div class="col-xs-10">
                        <textarea name="content" autocomplete="off" maxlength="500" class="form-control"
                                  rows="3"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<%@include file="../../common/include-footer.jsp" %>

<script src="/static/plugin/select/select2.js"></script>
<script>
    var prefix = "/system/notice";

    function submitHandler() {
        var data = $("#form-notice-add").serializeArray();
        $.operate.save(prefix + "/add", data);
    }

</script>
</body>
</html>
