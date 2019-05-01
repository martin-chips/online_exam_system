layui.use(['layer', 'form'], function () {
    var layer = layui.layer,
        $ = layui.jquery,
        form = layui.form;


    $("#mycode").on('click', function () {
        var t = Math.random();
        $("#mycode")[0].src = "/genCaptcha?t= " + t;
    });

    form.on('submit(login)', function (data) {
        var loadIndex = layer.load(2, {
            shade: [0.3, '#333']
        });
        if ($('form').find('input[type="checkbox"]')[0].checked) {
            data.field.rememberMe = true;
        } else {
            data.field.rememberMe = false;
        }
        $.post(data.form.action, data.field, function (res) {
            layer.close(loadIndex);
            if (res.success) {
                location.href = "/" + res.data.url;
            } else {
                layer.msg(res.message);
                $("#mycode").click();
            }
        });
        return false;
    });
});