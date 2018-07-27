<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/common/import.jsp" %>
    <!-- ztree -->
    <link rel="stylesheet" href="${context}/jslib/ZTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${context}/jslib/ZTree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${context}/jslib/ZTree/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${context}/jslib/ZTree/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="${context}/plugin/JQuery.cms.core.js"></script>
    <script type="text/javascript">
        var havingTree = ${tree};
        var checkedTrees = ${having};
    </script>
    <script type="text/javascript" src="${context}/js/back/system/resourceset.js"></script>
</head>
<body>
    <input type="hidden" id="roleid" value="${roleid}">
    <div>
        <ul id="resoutrceTree" class="ztree"></ul>
    </div>
</body>
</html>
