function openTab(text, url, iconCls) {
    if ($("#tabs").tabs("exists", text)) {
        $("#tabs").tabs("select", text);
    } else {
        var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
        $("#tabs").tabs("add", {
            title: text,
            iconCls: iconCls,
            closable: true,
            content: content
        });
    }
}

function logout() {
    $.messager.confirm("来自CRM", "确认要退出吗", function (r) {
        if (r) {
            $.removeCookie("userIdStr")
            $.removeCookie("userName")
            $.removeCookie("trueName")
            $.messager.alert("来自crm", "3秒后退出", "info");
            setTimeout(function () {
                window.location.href = ctx + "/login"
            }, 3000)

        }
    })
}

function openPasswordModifyDialog() {
    $("#dlg").dialog("open").dialog("setTitle", "修改密码")

}

function modifyPassword() {
    $('#fm').form('submit', {
        url: ctx + "/updatePwd",
        onSubmit: function (param) {
            return $("#fm").form("validate");
        },
        success: function (code) {
            var data = JSON.parse(code);
            if (data.code == 200) {
                $.removeCookie("userIdStr")
                $.removeCookie("userName")
                $.removeCookie("trueName")
                $.messager.alert("来自crm", "5秒后退出", "info");
                setTimeout(function () {
                    window.location.href = ctx + "/login"
                }, 5000)

            } else {
                $.messager.alert("来自crm", data.msg, "error");
            }
        }
    })
}

function closePasswordModifyDialog() {
    $("#dlg").dialog("close")
}