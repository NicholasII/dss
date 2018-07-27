<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions"%>  
<c:set var="context" value="${pageContext.request.contextPath}" />


<link href="${context}/js/bootstrap/bootstrap.min.css?v=3.3.5" type="text/css" rel="stylesheet">
<link href="${context}/js/bootstrap-table/bootstrap-table.min.css" type="text/css" rel="stylesheet">
<link href="${context}/js/bootstrapValivator/dist/css/bootstrapValidator.css" type="text/css" rel="stylesheet"/>


<!-- 引入jQuery库 -->
<script src="${context}/js/jquery/jquery.min.js?v=1.11.3"></script>

<!-- external javascript -->
<script src="${context}/js/bootstrap/bootstrap.min.js?v=3.3.5"></script>
<script type="text/javascript" src="${context}/js/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="${context}/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="${context}/js/bootstrap-notify/dist/bootstrap-notify.min.js"></script>
<script type="text/javascript" src="${context}/js/bootstrapValivator/dist/js/bootstrapValidator.js"></script>

<script src="${context}/js/bootbox/bootbox.min.js"></script>

<!--[if lt IE 9]>
<script src="${context}/jslib/JSON-js-master/json2.js"></script>
<![endif]-->



<!-- 自定义的js -->
<script type="text/javascript" src="${context}/common/common.js"></script>
<script type="text/javascript" src="${context}/common/check.js"></script>
<script type="text/javascript" src="${context}/common/message.js"></script>

<script type="text/javascript">
    var ctx  = "${context}";
</script>



