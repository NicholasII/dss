<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-control" content="no-cache">
    <%@ include file="/common/import.jsp" %>
    <!-- ztree -->
    <link rel="stylesheet" href="${context}/jslib/ZTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
   <%-- <script type="text/javascript" src="${context}/jslib/ZTree/js/jquery-1.4.4.min.js"></script>--%>
    <script type="text/javascript" src="${context}/jslib/ZTree/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${context}/jslib/ZTree/js/jquery.ztree.excheck.js"></script>
    <!-- 自定义的js -->
    <script type="text/javascript" src="${context}/js/back/system/userinfo.js"></script>
</head>
<body>
<div class="ibox-content">
    <div class="row row-lg">
        <div class="col-sm-12">
            <!-- Example Card View -->
            <div class="example-wrap">
                <h4 class="example-title">用户信息</h4>
                <div class="example">
                    <form id="searchForm" method="post" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">用户ID</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="userId"
                                       id="userId"/>
                            </div>
                            <label class="col-sm-1 control-label">用户名</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="userName"
                                       id="userName"/>
                            </div>
                            <button id="search" type="button" class="btn btn-default" onclick="searchUserInfo()">查询
                            </button>
                            <%--<june:btn type="search" onclick="searchUserInfo()"></june:btn>--%>
                        </div>
                    </form>
                    <div class="col-md-12">
                        <div id="toolbar" class="btn-group">
                            <%--<june:btn type="insert"></june:btn>
                            <june:btn type="update"></june:btn>
                            <june:btn type="delete"></june:btn>--%>
                            <button id="add" type="button" class="btn btn-default" onclick="addNew()">添加用户</button>
                            <button id="update" type="button" class="btn btn-default" onclick="editRow()">更新用户</button>
                            <button id="delete" type="button" class="btn btn-default" onclick="deleteRow()">删除用户</button>
                        </div>
                        <table id="userInfoTable">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="height: auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" onclick="closemodal()">×</button>
                <p id="title"></p>
            </div>

            <form id="modalForm">
                <!--设置该属性，用于判断是新建还是编辑0：编辑，1新建  -->
                <input type="hidden" name="isNew" class="form-control" id="isNew">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="userId">用户ID</label>
                                <input type="text" name="userId" class="form-control" id="userId2">
                            </div>
                            <%--<div class="form-group">
                                <label for="orgName">所属机构</label>
                                <input type="text" name="orgName" class="form-control" id="orgName" onfocus="initOrg()">
                                <input type="hidden" name="orgId" class="form-control" id="orgId">
                            </div>--%>
                            <div class="form-group" style="display: none">
                                <input type="hidden" name="id" class="form-control" id="id">
                            </div>

                            <div class="form-group">
                                <label for="roleName">用户角色</label>
                                <input type="text" name="roleName" class="form-control" id="roleName" onfocus="initRole()">
                                <input type="hidden" name="roleId" class="form-control" id="roleId">
                            </div>
                            <div class="form-group">
                                <label for="remark">备注</label>
                                <input type="text" name="remark" class="form-control" id="remark">
                            </div>
                            <div class="form-group">
                                <label for="status">用户账号状态</label>
                                <select class="selectpicker form-control" data-style="btn-success" id="status" name="status">
                                    <option value="0">正常</option>
                                    <option value="1">锁定</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group" id="passWord">
                                <label for="userPwd">密码</label>
                                <input type="password" name="userPwd" class="form-control" id="userPwd">
                            </div>
                            <div class="form-group">
                                <label for="userName2">用户名</label>
                                <input type="text" name="userName" class="form-control" id="userName2">
                            </div>

                            <div class="form-group">
                                <label for="email">邮箱</label>
                                <input type="text" name="email" class="form-control" id="email">
                            </div>
                            <div class="form-group">
                                <label for="phone">手机</label>
                                <input type="text" name="phone" class="form-control" id="phone">
                            </div>


                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" onclick="closemodal()">取消</button>
                    <button type="submit" class="btn btn-default">添加</button>
                </div>
            </form>
        </div>
    </div>
</div>


<div id="modal_june_web_new"></div>
<div class="modal fade bs-example-modal-sm" id="treeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-sm" style="height: auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" onclick="closeTreemodal()">×</button>
                组织机构选择
            </div>

            <div class="modal-body">
                <div class="row">
                    <div>
                        <div class="zTreeDemoBackground" style="width: 100%; height: 260px; overflow: auto;">
                            <ul id="orgTree" class="ztree"></ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary"
                        onclick="closeTreemodal()">取消
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade bs-example-modal-sm" id="roleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-sm" style="height: auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" onclick="closerolemodal()">×</button>
                角色选择
            </div>

            <div class="modal-body">
                <div class="row">
                    <div>
                        <div class="zTreeDemoBackground" style="width: 100%; height: 260px; overflow: auto;">
                            <ul id="roleTree" class="ztree"></ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary"
                        onclick="closerolemodal()">取消
                </button>
                <button type="button" class="btn btn-primary" onclick="roleSureClick()">确定</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>