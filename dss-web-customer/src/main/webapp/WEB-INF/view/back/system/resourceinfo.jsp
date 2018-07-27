<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-control" content="no-cache">
    <title>JUNE_WEB_NEW</title>
    <%@ include file="/common/import.jsp" %>
    <!-- 自定义的js -->
    <script type="text/javascript" src="${context}/js/back/system/resourceinfo.js"></script>
</head>
<body>
<div class="ibox-content">
    <div class="row row-lg">
        <div class="col-sm-12">
            <!-- Example Card View -->
            <div class="example-wrap">
                <div class="example">
                    <form id="searchForm" method="post" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">资源ID</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="id"
                                       id="id"/>
                            </div>
                            <label class="col-sm-1 control-label">资源名</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="name"
                                       id="name"/>
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
                            <button id="add" type="button" class="btn btn-default" onclick="addNew()">添加资源</button>
                            <button id="update" type="button" class="btn btn-default" onclick="editRow()">更新资源</button>
                            <button id="delete" type="button" class="btn btn-default" onclick="deleteRow()">删除资源</button>
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
                        <div class="col-md-8">
                            <div class="form-group">
                                <label for="name2">资源名称</label>
                                <input type="text" name="name" class="form-control" id="name2">
                            </div>
                            <div class="form-group">
                                <label for="url">资源地址</label>
                                <input type="text" name="url" class="form-control" id="url">
                            </div>
                            <div class="form-group">
                                <label for="permission">权限名称(可选)</label>
                                <input type="text" name="permission" class="form-control" id="permission">
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
</body>
</html>