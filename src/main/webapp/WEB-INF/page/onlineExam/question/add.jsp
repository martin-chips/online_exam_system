<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../common/include-header.jsp" %>
<body>
<div class="form-content">
    <form id="form-notice-add" class="form-horizontal">
        <h4 class="form-header h4">基本信息</h4>

        <div class="form-group">
            <label class="col-xs-3 control-label"><span style="color: red; ">*</span>类型：</label>
            <div class="col-xs-8">
                <label class="radio-box">
                    <input name="type" type="radio" value="1">单选
                </label>
                <label class="radio-box">
                    <input name="type" type="radio" value="2">多选
                </label>
                <label class="radio-box">
                    <input name="type" type="radio" value="3">填空
                </label>
                <label class="radio-box">
                    <input name="type" type="radio" value="4">判断
                </label>
                <label class="radio-box">
                    <input name="type" type="radio" value="5">问答
                </label>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label"><span style="color: red; ">*</span>试题：</label>
            <div class="col-sm-8">
                <input name="title" autocomplete="off" placeholder="请输入试题" class="form-control" type="text"
                       maxlength="100" required>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label"><span style="color: red; ">*</span>分值：</label>
            <div class="col-sm-8">
                <input name="score" autocomplete="off" placeholder="请输入分值" class="form-control" type="number"
                       maxlength="3" required>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label"><span style="color: red; ">*</span>选项A：</label>
            <div class="col-sm-8">
                <input class="form-control" type="text" name="optionA" id="optionA" required>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label"><span style="color: red; ">*</span>选项B：</label>
            <div class="col-sm-8">
                <input class="form-control" type="text" name="optionB" id="optionB" required>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label"><span style="color: red; ">*</span>选项C：</label>
            <div class="col-sm-8">
                <input class="form-control" type="text" name="optionC" id="optionC" required>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label"><span style="color: red; ">*</span>选项D：</label>
            <div class="col-sm-8">
                <input class="form-control" type="text" name="optionD" id="optionD" required>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label"><span style="color: red; ">*</span>单选题答案：</label>
            <div class="col-sm-8" id="radioAnswer">
                <label class="radio-box">
                    <input class="form-control" type="radio" name="radioAnswer" value="A">A
                </label>
                <label class="radio-box">
                    <input class="form-control" type="radio" name="radioAnswer" value="B">B
                </label>
                <label class="radio-box">
                    <input class="form-control" type="radio" name="radioAnswer" value="C">C
                </label>
                <label class="radio-box">
                    <input class="form-control" type="radio" name="radioAnswer" value="D">D
                </label>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label"><span style="color: red; ">*</span>多选题答案：</label>
            <div class="col-sm-8" id="checkboxAnswer">
                <label class="check-box">
                    <input class="form-control" type="checkbox" name="checkboxAnswerA" value="A">A
                </label>
                <label class="radio-box">
                    <input class="form-control" type="checkbox" name="checkboxAnswerB" value="B">B
                </label>
                <label class="radio-box">
                    <input class="form-control" type="checkbox" name="checkboxAnswerC" value="C">C
                </label>
                <label class="radio-box">
                    <input class="form-control" type="checkbox" name="checkboxAnswerD" value="D">D
                </label>
            </div>
        </div>


        <%--判断的答案--%>
        <div class="form-group">
            <label class="col-sm-3 control-label"><span style="color: red; ">*</span>判断题答案：</label>
            <div class="col-sm-8" id="judgeAnswer">
                <label class="radio-box">
                    <input class="form-control" type="radio" value="1" name="judgeAnswer">正确
                </label>
                <label class="radio-box">
                    <input class="form-control" type="radio" value="0" name="judgeAnswer">错误
                </label>
            </div>
        </div>

        <%--填空题答案--%>
        <div class="form-group">
            <label class="col-sm-3 control-label"><span style="color: red; ">*</span>填空答案：</label>
            <div class="col-sm-8">
                <input class="form-control" type="text" name="blackAnswer" id="blackAnswer" required>
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-3 control-label"><span style="color: red; ">*</span>问答答案：</label>
            <div class="col-sm-8">
                <textarea name="shortAnswer" autocomplete="off" maxlength="500" id="shortAnswer" class="form-control"
                          rows="3"></textarea>
            </div>
        </div>

        <h4 class="form-header h4">试题分析备注</h4>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label class="col-xs-3 control-label">内容：</label>
                    <div class="col-xs-8">
                        <textarea name="analyse" autocomplete="off" maxlength="500" class="form-control" id="analyse"
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
    var prefix = "/onlineExam/question";

    $('input').on('ifChecked', function (event) {
        var type = $(event.target).val();
        if (type == "1") {
            //如果是单选
            //ABCD 四个选项显示，单选答案框显示
            $("#optionA").parents(".form-group").show();
            $("#optionB").parents(".form-group").show();
            $("#optionC").parents(".form-group").show();
            $("#optionD").parents(".form-group").show();
            $("#radioAnswer").parents(".form-group").show();
            $("#checkboxAnswer").parents(".form-group").hide();
            $("#judgeAnswer").parents(".form-group").hide();
            $("#blackAnswer").parents(".form-group").hide();
            $("#shortAnswer").parents(".form-group").hide();
        } else if (type == "2") {
            //如果是多选
            //ABCD 四个选项显示，多选答案框显示
            $("#optionA").parents(".form-group").show();
            $("#optionB").parents(".form-group").show();
            $("#optionC").parents(".form-group").show();
            $("#optionD").parents(".form-group").show();
            $("#checkboxAnswer").parents(".form-group").show();
            $("#radioAnswer").parents(".form-group").hide();
            $("#judgeAnswer").parents(".form-group").hide();
            $("#blackAnswer").parents(".form-group").hide();
            $("#shortAnswer").parents(".form-group").hide();
        } else if (type == "3") {
            //填空
            //ABCD 四个选项隐藏
            $("#optionA").parents(".form-group").hide();
            $("#optionB").parents(".form-group").hide();
            $("#optionC").parents(".form-group").hide();
            $("#optionD").parents(".form-group").hide();
            $("#checkboxAnswer").parents(".form-group").hide();
            $("#radioAnswer").parents(".form-group").hide();
            $("#judgeAnswer").parents(".form-group").hide();
            $("#blackAnswer").parents(".form-group").show();
            $("#shortAnswer").parents(".form-group").hide();
        } else if (type == "4") {
            //判断
            $("#optionA").parents(".form-group").hide();
            $("#optionB").parents(".form-group").hide();
            $("#optionC").parents(".form-group").hide();
            $("#optionD").parents(".form-group").hide();
            $("#checkboxAnswer").parents(".form-group").hide();
            $("#radioAnswer").parents(".form-group").hide();
            $("#judgeAnswer").parents(".form-group").show();
            $("#blackAnswer").parents(".form-group").hide();
            $("#shortAnswer").parents(".form-group").hide();
        } else if (type == "5") {
            //简单
            $("#optionA").parents(".form-group").hide();
            $("#optionB").parents(".form-group").hide();
            $("#optionC").parents(".form-group").hide();
            $("#optionD").parents(".form-group").hide();
            $("#checkboxAnswer").parents(".form-group").hide();
            $("#radioAnswer").parents(".form-group").hide();
            $("#judgeAnswer").parents(".form-group").hide();
            $("#blackAnswer").parents(".form-group").hide();
            $("#shortAnswer").parents(".form-group").show();
        }
    });

    /**
     * 获取页面数据
     */
    function getData() {
        var type = $("input[name='type']:checked").val();
        var title = $("input[name='title']").val();
        var score = $("input[name='score']").val();
        var analyse = $("#analyse").val();
        //获取ABCD选项
        var optionA = $("input[name='optionA']").val();
        var optionB = $("input[name='optionB']").val();
        var optionC = $("input[name='optionC']").val();
        var optionD = $("input[name='optionD']").val();
        var data = {"type": type, "title": title, "score": score, "analyse": analyse}
        //单选
        if (type == 1) {
            var radioAnswer = $("input[name='radioAnswer']:checked").val();
            data.answer = radioAnswer;
            data.optionA = optionA;
            data.optionB = optionB;
            data.optionC = optionC;
            data.optionD = optionD;
        } else if (type == 2) {
            //多选
            var checkboxAnswer = $("input[name='checkboxAnswer']:checked").val();
            var s = "";
            $.each($('input:checkbox:checked'), function () {
                s += $(this).val() + ',';   //如果选中，将value添加到变量s中
            });
            data.answer = s;
            data.optionA = optionA;
            data.optionB = optionB;
            data.optionC = optionC;
            data.optionD = optionD;
        } else if (type == 3) {
            //填空
            var blackAnswer = $("input[name='blackAnswer']").val();
            data.answer = blackAnswer;
        } else if (type == 4) {
            var judgeAnswer = $("input[name='judgeAnswer']:checked").val();
            data.answer = judgeAnswer;
            //判断
        } else if (type == 5) {
            //简答
            var shortAnswer = $("#shortAnswer").val();
            data.answer = shortAnswer;
        }
        return data;
    }


    function submitHandler() {
        $.operate.save(prefix + "/add", getData());
    }

</script>
</body>
</html>
