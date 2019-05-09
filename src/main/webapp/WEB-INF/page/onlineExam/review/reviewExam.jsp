<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../common/include-header.jsp" %>
<style>
    .exam-bar {
        background: #fff9ec none repeat scroll 0% 0%;
        height: 40px;
        font-size: 16px;
        margin-bottom: 0;
        padding-top: 10px;
    }
</style>
<body class="gray-bg">

<div class="container-div">
    <input type="hidden" id="examId" value="${exam.examId}">
    <input type="hidden" id="stuId" value="${stuId}">
    <nav class="navbar navbar-fixed-top exam-bar" role="navigation">
        <div class="col-lg-4 text-right">
            当前试卷为【${exam.examName}】，考试用户为【${sessionScope.user.nickName}】
        </div>
        <div class="col-lg-4 text-center">考试时长：${exam.lastTime} 分钟
        </div>
        <div class="col-lg-4 text-center">
            成绩：正在阅卷中
        </div>
    </nav>

    <div class="text-center" style="margin-top: 100px">
        <h1 class="h1 text-center text-warning ">${exam.examName} </h1>
        <span class="text-right">试卷满分：${exam.score}</span>
    </div>


    <c:if test="${exam.radioQuestion!=null && exam.radioQuestion.size()!=0}">
        <div class="form-content radio-box-exam">
            <h1 class="form-header">单选题</h1>
            <c:forEach items="${exam.radioQuestion}" varStatus="varStat" var="radio">
                <div class="form-group">
                    <label class=" control-label">${varStat.index+1}: ${radio.title}</label><span
                        class="text-info h6">本题${radio.score}分</span>
                    <div class="col-sm-12">
                        <label class="radio-box">
                            <input class="form-control" type="radio" name="${radio.id}" mtype="single" value="A"
                                   disabled
                                   <c:if test="${radio.optionACheckedStu eq 'A'}">checked</c:if>
                            > <c:out
                                value="${radio.optionA}"
                                escapeXml='false'/>
                        </label>
                        <br>
                        <label class="radio-box">
                            <input class="form-control" type="radio" name="${radio.id}" mtype="single" value="B"
                                   disabled
                                   <c:if test="${radio.optionBCheckedStu eq 'B'}">checked</c:if>
                            > <c:out value="${radio.optionB}"
                                     escapeXml='false'/>
                        </label>
                        <br>
                        <label class="radio-box">
                            <input class="form-control" type="radio" mtype="single" name="${radio.id}" disabled
                                   value="C"
                                   <c:if test="${radio.optionCCheckedStu eq 'C'}">checked</c:if>
                            > <c:out value="${radio.optionC}"
                                     escapeXml='false'/>
                        </label>
                        <br>
                        <label class="radio-box">
                            <input class="form-control" type="radio" mtype="single" name="${radio.id}" disabled
                                   value="D"
                                   <c:if test="${radio.optionDCheckedStu eq 'D'}">checked</c:if>
                            > <c:out value="${radio.optionD}"
                                     escapeXml='false'/>
                        </label>
                        <br>
                    </div>
                    <c:if test="${radio.finalScore !=null}">
                        <div class="text-warning">最终得分：${radio.finalScore}</div>
                    </c:if>
                    <c:if test="${radio.finalScore ==null && radio.finalScore==''}">
                        <div class="text-warning">最终得分：正在阅卷中</div>
                    </c:if>
                    <c:if test="${radio.answer !=null && radio.answer!=''}">
                        <div class="text-warning">正确答案：${radio.answer}</div>
                    </c:if>
                    <c:if test="${radio.analyse != null && radio.analyse!=''}">
                        <div class="text-danger">解析：${radio.analyse}</div>
                    </c:if>
                </div>
                <br>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${exam.checkboxQuestion!=null && exam.checkboxQuestion.size()!=0}">
        <div class=" form-content">
            <h1 class="form-header">多选题</h1>
            <c:forEach items="${exam.checkboxQuestion}" varStatus="varStat" var="checkbox">
                <div class="form-group">
                    <label class=" control-label">${varStat.index+1}: ${checkbox.title}</label><span
                        class="text-info h6">  本题${checkbox.score}分</span>
                    <div class="col-sm-12">
                        <label class="check-box">
                            <input class="form-control" type="checkbox" mtype="Multiple" value="A" disabled
                                   <c:if test="${checkbox.optionACheckedStu=='A'}">checked</c:if>
                                   name="${checkbox.id}">
                            <c:out
                                    value="${checkbox.optionA}"
                                    escapeXml='false'/>
                        </label>
                        <br>
                        <label class="radio-box">
                            <input class="form-control" type="checkbox" mtype="Multiple" value="B" disabled
                                   <c:if test="${checkbox.optionBCheckedStu=='B'}">checked</c:if>
                                   name="${checkbox.id}">
                            <c:out value="${checkbox.optionB}"
                                   escapeXml='false'/>
                        </label>
                        <br>
                        <label class="radio-box">
                            <input class="form-control" type="checkbox" mtype="Multiple" value="C" disabled
                                   <c:if test="${checkbox.optionCCheckedStu=='C'}">checked</c:if>
                                   name="${checkbox.id}">
                            <c:out value="${checkbox.optionC}"
                                   escapeXml='false'/>
                        </label>
                        <br>
                        <label class="radio-box">
                            <input class="form-control" type="checkbox" mtype="Multiple" value="D" disabled
                                   <c:if test="${checkbox.optionDCheckedStu=='D'}">checked</c:if>
                                   name="${checkbox.id}">
                            <c:out value="${checkbox.optionD}"
                                   escapeXml='false'/>
                        </label>
                        <br>
                    </div>
                </div>
                <c:if test="${checkbox.finalScore !=null}">
                    <div class="text-warning">最终得分：${checkbox.finalScore}</div>
                </c:if>
                <c:if test="${checkbox.finalScore ==null && checkbox.finalScore==''}">
                    <div class="text-warning">最终得分：正在阅卷中</div>
                </c:if>
                <c:if test="${checkbox.answer !=null && checkbox.answer!=''}">
                    <div class="text-warning">正确答案：${checkbox.answer}</div>
                </c:if>
                <c:if test="${checkbox.analyse != null && checkbox.analyse!=''}">
                    <div class="text-danger">解析：${checkbox.analyse}</div>
                </c:if>
                <br>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${exam.balckQuestion!=null && exam.balckQuestion.size()!=0}">
        <div class="form-content">
            <h1 class="form-header">填空题</h1>
            <c:forEach items="${exam.balckQuestion}" varStatus="varStat" var="black">
                <div class="form-group">
                    <label class=" control-label">${varStat.index+1}: ${black.title}:
                        <input type="text" readonly
                               value="${black.textAnswerStu}"
                               class="form-control"></label>
                    <span class="text-info h6">  本题${black.score}分</span>
                </div>
                <div class=" text-warning">请判分：<input class="form-control" name="${black.id}"
                                                      onblur="updateTextScore('${black.id}')"
                                                      value="${black.finalScore}"></div>
                <c:if test="${black.answer !=null && black.answer!=''}">
                    <div class="text-warning">正确答案：${black.answer}</div>
                </c:if>
                <c:if test="${black.analyse != null && black.analyse!=''}">
                    <div class="text-danger">解析：${black.analyse}</div>
                </c:if>
            </c:forEach>

        </div>
    </c:if>

    <c:if test="${exam.judgeQuestion!=null && exam.judgeQuestion.size()!=0}">
        <div class="form-content">
            <h1 class="form-header">判断题</h1>
            <c:forEach items="${exam.judgeQuestion}" varStatus="varStat" var="judge">
                <div class="form-group">
                    <label class=" control-label">${varStat.index+1}: ${judge.title}</label><span
                        class="text-info h6">  本题${judge.score}分</span>
                    <br>
                    <label class="radio-box">
                        <input class="form-control" type="radio" mtype="judge" value="1" name="${judge.id}" disabled
                               <c:if test="${judge.judgeAnswer1Stu == '1'}">checked</c:if>
                        >正确
                    </label>
                    <label class="radio-box">
                        <input class="form-control" type="radio" mtype="judge" value="0" name="${judge.id}" disabled
                               <c:if test="${judge.judgeAnswer0Stu == '0'}">checked</c:if>
                        >错误
                    </label>
                </div>
                <c:if test="${judge.finalScore !=null}">
                    <div class="text-warning">最终得分：${judge.finalScore}</div>
                </c:if>
                <c:if test="${judge.answer !=null && judge.answer!=''}">
                    <div class="text-warning">正确答案：${judge.answer=='0'?"错误":"正确"}</div>
                </c:if>
                <c:if test="${judge.analyse != null && judge.analyse!=''}">
                    <div class="text-danger">解析：${judge.analyse}</div>
                </c:if>
                <br>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${exam.shortQuestion!=null && exam.shortQuestion.size()!=0}">
        <div class="form-content">
            <h1 class="form-header">简答</h1>
            <c:forEach items="${exam.shortQuestion}" varStatus="varStat" var="shorta">
                <div class="form-group">
                    <label class=" control-label">${varStat.index+1}: ${shorta.title}</label><span
                        class="text-info h6">  本题${shorta.score}分</span>
                    <textarea autocomplete="off" maxlength="500" id="shortAnswer" readonly
                              class="form-control"
                              rows="3">${shorta.textAnswerStu}</textarea>
                    <br>
                </div>

                <div class="text-warning">请判分：
                    <input type="number" class="form-control " name="${shorta.id}" value="${shorta.finalScore}"
                           onblur="updateTextScore('${shorta.id}')">
                </div>

                <c:if test="${shorta.answer !=null && shorta.answer!=''}">
                    <div class="text-warning">正确答案：${shorta.answer}</div>
                </c:if>
                <c:if test="${shorta.analyse != null && shorta.analyse!=''}">
                    <div class="text-danger">解析：${shorta.analyse}</div>
                </c:if>
            </c:forEach>
        </div>
    </c:if>
    <div class="row">
        <div class="col-sm-offset-5 col-sm-10">
            <button type="button" class="btn btn-lg btn-danger" onclick="finishReview()"><i class="fa fa-check"></i>提 交
                成 绩
            </button>
        </div>
    </div>
</div>
<%@include file="../../common/include-footer.jsp" %>
<script src="/static/plugin/select/select2.js"></script>

<script>
    function updateTextScore(name) {
        var score = $("[name='" + name + "']").val();
        $.ajax({
            data: {"questionId": name, "finalScore": score, "examId": $("#examId").val(), "stuId": $("#stuId").val()},
            url: "/onlineExam/review/reviewExam",
            async: true, //异步提交
            type: "post",
            success: function (result) {
                if (result.code != 0) {
                    $.modal.alertError(result.msg);
                }
            }
        });
    }

    function finishReview() {
        $.modal.loading("正在批卷中，请稍后...");
        $.ajax({
            data: {"examId": $("#examId").val(), "stuId": $("#stuId").val()},
            url: "/onlineExam/review/finisReview",
            async: true, //异步提交
            type: "post",
            success: function (result) {
                if (result.code != 0) {
                    $.modal.alertError(result.msg);
                }
            }
        })
    }
</script>
</body>
</html>
