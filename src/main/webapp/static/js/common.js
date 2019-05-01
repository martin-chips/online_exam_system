(function ($) {
    $.extend({
        _treeTable: {},
        _tree: {},
        // 表格封装处理
        table: {
            _option: {},
            // 初始化表格参数
            init: function (options) {
                var defaults = {
                    id: "bootstrap-table",
                    type: 0, // 0 代表bootstrapTable 1代表bootstrapTreeTable
                    height: undefined,
                    sidePagination: "server",
                    sortName: "",
                    sortOrder: "asc",
                    pagination: true,
                    pageSize: 10,
                    toolbar: "toolbar",
                    striped: false,
                    escape: false,
                    showFooter: false,
                    search: false,
                    showSearch: true,
                    showRefresh: true,
                    showColumns: true,
                    showToggle: true,
                    showExport: false,
                    fixedColumns: false,
                    fixedNumber: 0,
                    rightFixedColumns: false,
                    rightFixedNumber: 0,
                    queryParams: $.table.queryParams,
                };
                var options = $.extend(defaults, options);
                $.table._option = options;
                $('#' + options.id).bootstrapTable({
                    url: options.url,                                   // 请求后台的URL（*）
                    contentType: "application/x-www-form-urlencoded",   // 编码类型
                    method: 'get',                                     // 请求方式（*）
                    cache: false,                                       // 是否使用缓存
                    height: options.height,                             // 表格的高度
                    striped: options.striped,                           // 是否显示行间隔色
                    sortable: true,                                     // 是否启用排序
                    sortStable: true,                                   // 设置为 true 将获得稳定的排序
                    sortName: options.sortName,                         // 排序列名称
                    sortOrder: options.sortOrder,                       // 排序方式  asc 或者 desc
                    pagination: options.pagination,                     // 是否显示分页（*）
                    pageNumber: 1,                                      // 初始化加载第一页，默认第一页
                    pageSize: options.pageSize,                         // 每页的记录行数（*）
                    pageList: [10, 25, 50],                             // 可供选择的每页的行数（*）
                    escape: options.escape,                             // 转义HTML字符串
                    showFooter: options.showFooter,                     // 是否显示表尾
                    iconSize: 'outline',                                // 图标大小：undefined默认的按钮尺寸 xs超小按钮sm小按钮lg大按钮
                    toolbar: '#' + options.toolbar,                     // 指定工作栏
                    sidePagination: options.sidePagination,             // server启用服务端分页client客户端分页
                    search: options.search,                             // 是否显示搜索框功能
                    showSearch: options.showSearch,                     // 是否显示检索信息
                    showRefresh: options.showRefresh,                   // 是否显示刷新按钮
                    showColumns: options.showColumns,                   // 是否显示隐藏某列下拉框
                    showToggle: options.showToggle,                     // 是否显示详细视图和列表视图的切换按钮
                    showExport: options.showExport,                     // 是否支持导出文件
                    fixedColumns: options.fixedColumns,                 // 是否启用冻结列（左侧）
                    fixedNumber: options.fixedNumber,                   // 列冻结的个数（左侧）
                    rightFixedColumns: options.rightFixedColumns,       // 是否启用冻结列（右侧）
                    rightFixedNumber: options.rightFixedNumber,         // 列冻结的个数（右侧）
                    queryParams: options.queryParams,                   // 传递参数（*）
                    columns: options.columns,                           // 显示列信息（*）
                    responseHandler: $.table.responseHandler,           // 在加载服务器发送来的数据之前处理函数
                    onLoadSuccess: $.table.onLoadSuccess,               // 当所有数据被加载时触发处理函数
                });
            },
            //获取当前表格的被选中的id
            getSelectIds: function () {
                var rows = $.common.isEmpty($.table._option.uniqueId) ? $.table.selectFirstColumns() : $.table.selectColumns($.table._option.uniqueId);
                if (rows.length == 0) {
                    $.modal.alertWarning("请至少选择一条记录");
                    return;
                }
                return rows.join();
            },
            // 查询条件
            queryParams: function (params) {
                return {
                    // 传递参数查询参数
                    pageSize: params.limit,
                    pageNum: params.offset / params.limit + 1,
                    searchValue: params.search,
                    orderByColumn: params.sort,
                    isAsc: params.order
                };
            },
            // 请求获取数据后处理回调函数
            responseHandler: function (res) {
                if (res.code == 0) {
                    if ($.common.isNotEmpty($.table._option.sidePagination) && $.table._option.sidePagination == 'client') {
                        return res.rows;
                    } else {
                        return {rows: res.rows, total: res.total};
                    }
                } else {
                    $.modal.alertWarning(res.msg);
                    return {rows: [], total: 0};
                }
            },
            // 当所有数据被加载时触发
            onLoadSuccess: function (data) {
                // 浮动提示框特效
                $("[data-toggle='tooltip']").tooltip();
                // 触发行点击事件
                $("#" + $.table._option.id).on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table load-success.bs.table", function () {
                    var ids = $("#" + $.table._option.id).bootstrapTable("getSelections");
                    $('#' + $.table._option.toolbar + ' .btn-del').toggleClass('disabled', !ids.length);
                    $('#' + $.table._option.toolbar + ' .btn-edit').toggleClass('disabled', ids.length != 1);
                });
            },
            // 序列号生成
            serialNumber: function (index) {
                var table = $('#' + $.table._option.id).bootstrapTable('getOptions');
                var pageSize = table.pageSize;
                var pageNumber = table.pageNumber;
                return pageSize * (pageNumber - 1) + index + 1;
            },
            // 列超出指定长度浮动提示
            tooltip: function (value, length) {
                var _length = $.common.isEmpty(length) ? 20 : length;
                var _text = "";
                var _value = $.common.nullToStr(value);
                if (_value.length > _length) {
                    _text = _value.substr(0, _length) + "...";
                } else {
                    _text = _value;
                }
                return '<a href="#" class="tooltip-show" data-toggle="tooltip" title="' + _value + '">' + _text + '</a>';
            },
            // 下拉按钮切换
            dropdownToggle: function (value) {
                var actions = [];
                actions.push('<div class="btn-group">');
                actions.push('<button type="button" class="btn btn-xs dropdown-toggle" data-toggle="dropdown" aria-expanded="false">');
                actions.push('<i class="fa fa-cog"></i>&nbsp;<span class="fa fa-chevron-down"></span></button>');
                actions.push('<ul class="dropdown-menu">');
                actions.push(value.replace(/<a/g, "<li><a").replace(/<\/a>/g, "</a></li>"));
                actions.push('</ul>');
                actions.push('</div>');
                return actions.join('');
            },
            // 搜索-默认第一个form
            search: function (formId) {
                var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                var params = $("#" + $.table._option.id).bootstrapTable('getOptions');
                params.queryParams = function (params) {
                    var search = {};
                    $.each($("#" + currentId).serializeArray(), function (i, field) {
                        search[field.name] = field.value;
                    });
                    search.pageSize = params.limit;
                    search.pageNum = params.offset / params.limit + 1;
                    search.searchValue = params.search;
                    search.orderByColumn = params.sort;
                    search.isAsc = params.order;
                    return search;
                }
                $("#" + $.table._option.id).bootstrapTable('refresh', params);
            },
            // 导出数据
            exportExcel: function (formId) {
                var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                $.modal.loading("正在导出数据，请稍后...");
                $.post($.table._option.exportUrl, $("#" + currentId).serializeArray(), function (result) {
                    if (result.code == web_status.SUCCESS) {
                        window.location.href = ctx + "common/download?fileName=" + result.msg + "&delete=" + true;
                    } else {
                        $.modal.alertError(result.msg);
                    }
                    $.modal.closeLoading();
                });
            },
            // 下载模板
            importTemplate: function () {
                $.get($.table._option.importTemplateUrl, function (result) {
                    if (result.code == web_status.SUCCESS) {
                        window.location.href = ctx + "common/download?fileName=" + result.msg + "&delete=" + true;
                    } else {
                        $.modal.alertError(result.msg);
                    }
                });
            },
            // 导入数据
            importExcel: function (formId) {
                var currentId = $.common.isEmpty(formId) ? 'importForm' : formId;
                $.form.reset(currentId);
                layer.open({
                    type: 1,
                    area: ['400px', '230px'],
                    fix: false,
                    //不固定
                    maxmin: true,
                    shade: 0.3,
                    title: '导入' + $.table._option.modalName + '数据',
                    content: $('#' + currentId),
                    btn: ['<i class="fa fa-check"></i> 导入', '<i class="fa fa-remove"></i> 取消'],
                    // 弹层外区域关闭
                    shadeClose: true,
                    btn1: function (index, layero) {
                        var file = layero.find('#file').val();
                        if (file == '' || (!$.common.endWith(file, '.xls') && !$.common.endWith(file, '.xlsx'))) {
                            $.modal.msgWarning("请选择后缀为 “xls”或“xlsx”的文件。");
                            return false;
                        }
                        var index = layer.load(2, {shade: false});
                        $.modal.disable();
                        var formData = new FormData();
                        formData.append("file", $('#file')[0].files[0]);
                        formData.append("updateSupport", $("input[name='updateSupport']").is(':checked'));
                        $.ajax({
                            url: $.table._option.importUrl,
                            data: formData,
                            cache: false,
                            contentType: false,
                            processData: false,
                            type: 'POST',
                            success: function (result) {
                                if (result.code == web_status.SUCCESS) {
                                    $.modal.closeAll();
                                    $.modal.alertSuccess(result.msg);
                                    $.table.refresh();
                                } else {
                                    layer.close(index);
                                    $.modal.enable();
                                    $.modal.alertError(result.msg);
                                }
                            }
                        });
                    }
                });
            },
            // 刷新表格
            refresh: function () {
                $("#" + $.table._option.id).bootstrapTable('refresh', {
                    silent: true
                });
            },
            // 查询表格指定列值
            selectColumns: function (column) {
                return $.map($('#' + $.table._option.id).bootstrapTable('getSelections'), function (row) {
                    return row[column];
                });
            },
            // 查询表格首列值
            selectFirstColumns: function () {
                return $.map($('#' + $.table._option.id).bootstrapTable('getSelections'), function (row) {
                    return row[$.table._option.columns[1].field];
                });
            },
            // 回显数据字典
            selectDictLabel: function (datas, value) {
                var actions = [];
                $.each(datas, function (index, dict) {
                    if (dict.dictValue == value) {
                        actions.push("<span class='badge badge-" + dict.listClass + "'>" + dict.dictLabel + "</span>");
                        return false;
                    }
                });
                return actions.join('');
            },
            // 显示表格指定列
            showColumn: function (column) {
                $("#" + $.table._option.id).bootstrapTable('showColumn', column);
            },
            // 隐藏表格指定列
            hideColumn: function (column) {
                $("#" + $.table._option.id).bootstrapTable('hideColumn', column);
            }
        }
    });
})(jQuery);