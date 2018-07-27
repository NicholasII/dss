<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="translated-ltr">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title>旅客服务系统后台 - 登录</title>
    <%@ include file="common/import.jsp"%>
    <meta name="keywords" content="机场移动体验系统后台">
    <meta name="description" content="为机场移动服务提供全方位支撑保障">
    <link href="${context}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${context}/css/font-awesome.css" rel="stylesheet">
    <link href="${context}/css/bootstrap_3.3.6_css_bootstrap.min.css" rel="stylesheet">
    <link href="${context}/css/style.min.css" rel="stylesheet">
    <link href="${context}/css/login.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <!--<meta http-equiv="refresh" content="0;ie.html"/>--><!--[endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location
        }
        ;
    </script>

    <link type="text/css" rel="stylesheet" charset="UTF-8" href="${context}/css/translateelement.css">
</head>

<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            <div class="signin-info">
                <div class="logopanel m-b">
                    <h1><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">[H +]</font></font></h1>
                </div>
                <div class="m-b"></div>
                <h4><font style="vertical-align: inherit;"><font
                        style="vertical-align: inherit;">欢迎使用</font></font><strong><font
                        style="vertical-align: inherit;"><font style="vertical-align: inherit;">H
                    +后台主题UI框架</font></font></strong></h4>
                <ul class="m-b">
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i><font style="vertical-align: inherit;"><font
                            style="vertical-align: inherit;"> 优势一</font></font></li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i><font style="vertical-align: inherit;"><font
                            style="vertical-align: inherit;"> 优势二</font></font></li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i><font style="vertical-align: inherit;"><font
                            style="vertical-align: inherit;"> 优势三</font></font></li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i><font style="vertical-align: inherit;"><font
                            style="vertical-align: inherit;"> 优势四</font></font></li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i><font style="vertical-align: inherit;"><font
                            style="vertical-align: inherit;"> 优势五</font></font></li>
                </ul>
                <strong><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">还没有账号？
                </font></font><a href="http://www.zi-han.net/theme/hplus/login_v2.html#"><font
                        style="vertical-align: inherit;"><font style="vertical-align: inherit;">立即注册»</font></font></a>
                </strong>
            </div>
        </div>
        <div class="col-sm-5">
            <form method="post" action="/login">
                <h4 class="no-margins"><font style="vertical-align: inherit;"><font
                        style="vertical-align: inherit;">登录：</font></font></h4>
                <p class="m-t-md"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">登录到H
                    +后台主题UI框架</font></font></p>
                <input type="text" class="form-control uname" placeholder="用户名" name="username">
                <input type="password" class="form-control pword m-b" placeholder="密码" name="password">
                <a href="http://www.zi-han.net/theme/hplus/login_v2.html"><font style="vertical-align: inherit;"><font
                        style="vertical-align: inherit;">忘记密码了？</font></font></a>
                <button class="btn btn-success btn-block"><font style="vertical-align: inherit;"><font
                        style="vertical-align: inherit;">登录</font></font></button>
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">
            ©2015版权所有。</font><font style="vertical-align: inherit;">H +
        </font></font></div>
    </div>
</div>
<div id="goog-gt-tt" class="skiptranslate" dir="ltr">
    <div style="padding: 8px;">
        <div>
            <div class="logo"><img src="${context}/img/translate_24dp.png" width="20" height="20"
                                   alt="Google 翻译"></div>
        </div>
    </div>
    <div class="top" style="padding: 8px; float: left; width: 100%;"><h1 class="title gray">原文</h1></div>
    <div class="middle" style="padding: 8px;">
        <div class="original-text"></div>
    </div>
    <div class="bottom" style="padding: 8px;">
        <div class="activity-links"><span class="activity-link">提供更好的翻译建议</span><span class="activity-link"></span>
        </div>
        <div class="started-activity-container">
            <hr style="color: #CCC; background-color: #CCC; height: 1px; border: none;">
            <div class="activity-root"></div>
        </div>
    </div>
    <div class="status-message" style="display: none;"></div>
</div>


<div class="goog-te-spinner-pos">
    <div class="goog-te-spinner-animation">
        <svg xmlns="http://www.w3.org/2000/svg" class="goog-te-spinner" width="96px" height="96px" viewBox="0 0 66 66">
            <circle class="goog-te-spinner-path" fill="none" stroke-width="6" stroke-linecap="round" cx="33" cy="33"
                    r="30"></circle>
        </svg>
    </div>
</div>
</body>
</html>