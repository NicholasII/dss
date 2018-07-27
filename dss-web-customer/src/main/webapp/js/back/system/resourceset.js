var api_addResourceAuth = ctx + "/back/authInfo/authResource";
var api_addResourceAuthBat = ctx + "/back/authInfo/authResourceBat";
var api_deleteResourceAuth = ctx + "/back/authInfo/unauthResource";
var api_deleteResourceAuthBat = ctx + "/back/authInfo/unauthResourceBat";


$(function() {

    var treeObj = $("#resoutrceTree").myZtree({
        check:{
            enable:true,
            chkboxType:{ "Y" : "s", "N" : "s" },
            chkStyle : "checkbox"
        },
        data:{
            key:{
				url:"url2"
            },
            simpleData:{
                enable:true,
                idKey:"id",
                pIdKey:"pid",
                rootPid:"-1"
            }
        },
        callback:{
            beforeCheck:function(treeId,treeNode){
                if (!treeNode.checked) {
                    //var ps = treeObj.getCheckParentNodes(treeNode,false);
                    var ps = new Array();
                    ps.push(treeNode);
                    addGroupChannel(ps);
                }else {
                    var cs = new Array();
                    treeObj.getCheckChildNodes(treeNode,true,cs);
                    cs.push(treeNode);
                    deleteGroupChannel(cs);
                }
            },
            onCheck:function(event,treeId,treeNode){

            }
        }
    },havingTree);

    initTree();
    function initTree() {
        for (var i = 0; i < checkedTrees.length; i++) {
            var node = checkedTrees[i];
            var n = treeObj.getNodeByParam("id", node.resid, null);
            treeObj.checkNode(n, true, true);
        }
    }
});


function addGroupChannel(ps) {
    var roleid = $("#roleid").val();
    var array = new Array();
    for (var i = 0; i < ps.length; i++) {
        var node = ps[i];
        if (node.name != "全部功能") {
            var data = {};
            data.id = node.id;
            data.pid = node.pid;
            data.name = node.name;
            data.url = node.url;
            array.push(data);
        }
    }
    var jsonString = JSON.stringify(array);
    var send = {};
    if(ps.length>1){
        send.list = jsonString;
        send.rid = roleid;
        docommonAjax("post",api_addResourceAuthBat, send, addStatus)
    }else{
        send.rid = roleid;
        send.resid = array[0].id;
        docommonAjax("post",api_addResourceAuth, send, addStatus)
    }

}
function deleteGroupChannel(cs) {
    var roleid = $("#roleid").val();
    var array = new Array();
    for (var i = 0; i < cs.length; i++) {
        var node = cs[i];
        if (node.name != "全部功能") {
            var data = {};
            data.id = node.id;
            data.pid = node.pid;
            data.name = node.name;
            data.url = node.url;
            array.push(data);
        }
    }
    var jsonString = JSON.stringify(array);
    var send = {};
    send.data = jsonString;

    if(cs.length>1){
        send.list = jsonString;
        send.rid = roleid;
        docommonAjax("post",api_deleteResourceAuthBat, send, deleteStatus)
    }else{
        send.rid = roleid;
        send.resid = array[0].id;
        docommonAjax("post",api_deleteResourceAuth,  send, deleteStatus)
    }
}

function addStatus(data){
    data = eval("("+data+")");
    var status = data.status;
    if (status=="success") {
        alert("添加成功")
    }else {
        alert("添加失败");
    }

}
function deleteStatus(data) {
    data = eval("("+data+")");
    var status = data.status;
    if (status=="success") {
        alert("解除授权成功")
    }else {
        alert("解除授权失败");
    }
}
