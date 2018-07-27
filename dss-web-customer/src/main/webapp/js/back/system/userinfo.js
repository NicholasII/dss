var api_getUserPageList = ctx + "/back/userInfo/userPageList"
var api_addUser = ctx + "/back/userInfo/addUser";
var api_updateUser = ctx + "/back/userInfo/updateUser";
var api_deleteUserSelected = ctx+ "/back/userInfo/deleteUser";
var api_getUserById = ctx + "/back/userInfo/getUserById";
var api_having_role = ctx + "/back/userInfo/roleList";

/**
 * 用户信息js
 */
$(function() {
    $('#modalForm').bootstrapValidator({
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        fields : {
            userId : {
                validators : {
                    notEmpty : { message : $message("ErrorMustInput",[ '用户ID' ]) },
                    stringLength: { min: 5,  max: 16,
                        message: $message("ErrorLength2",['用户ID','5','16']) }
                }
            },
            userPwd : {
                validators : { notEmpty : { message : $message("ErrorMustInput",[ '密码' ]) } }
            },
            /*orgName : {
                validators : { notEmpty : { message : $message("ErrorMustInput",[ '组织名' ]) } }
            },*/
            userName : {
                validators : {
                    notEmpty : { message : $message("ErrorMustInput",[ '用户名' ]) },
                    stringLength: { min: 2,  max: 16, message: $message("ErrorLength2",['用户名','2','16']) }
                }
            },
            email : {
                validators : {
                    emailAddress: { message: $message("ErrorFormat",['邮箱']) },
                    stringLength: { min: 0, max: 50, message: $message("ErrorLength",['邮箱','50'])  }
                }
            },
            phone : {
                validators : {
                    stringLength: {  min: 0, max: 11, message: $message("ErrorLength",['手机','11']) },
                    regexp: { regexp: /^(1[0-9])\d{9}$/, message: $message("ErrorFormat",['手机号码']) }
                }
            }
        }
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        var $form = $(e.target);
        var bv = $form.data('bootstrapValidator');
        if($("#isNew").val()== 0) {
            //编辑保存
            doAjax(POST,api_updateUser,$form.serialize(),saveSuccess);
        }
        else if($("#isNew").val() == 1) {
            //新增保存
            doAjax(POST,api_addUser,$form.serialize(),saveSuccess);
        }
    });
	// 表格初始化
	$('#userInfoTable').bootstrapTable({
		cache : false,
		striped : true,
		pagination : true,
		toolbar : '#toolbar',
		pageSize : 10,
		pageNumber : 1,
		pageList : [ 5, 10, 20 ],
		clickToSelect : true,
		sidePagination : 'server',// 设置为服务器端分页
		columns : [ 
		    { field : "", checkbox : true },
            { field : "id", title : "用户ID", align : "center", valign : "middle" ,visible:false},
		    { field : "userId", title : "用户ID", align : "center", valign : "middle" },
			{ field : "userName", title : "用户名", align : "center", valign : "middle" },
            { field : "phone", title : "手机", align : "center", valign : "middle" },
            { field : "email", title : "邮箱", align : "center", valign : "middle" },
            { field : 'status', title : '用户账号状态', align : "center", valign : "middle" },
            { field : "lastLoginTime", title : "最后登录时间", align : "center", valign : "middle" },
			{ field : "remark", title : "备注", align : "center", valign : "middle" },
			{ field : "opration", title : "详情", align : "center", valign : "middle",
				formatter : function(value, row, index) {
					return detailBtn(row.userId);
				}
			} 
		],
		onPageChange : function(size, number) {
			searchUserInfo();
		},
		formatNoMatches : function() {
			return NOT_FOUND_DATAS;
		}
	});
});


$(function() {
	searchUserInfo();
});

function saveSuccess(response){
	var errType = response.errType;
	if(errType != ERROR)	{
		closemodal();
		searchUserInfo();
	}else{
		$("#saveBtn").removeAttr("disabled");
	}
}

/**
 * input框绑定change事件，用于校验
 * @deprecated 
 */
function bindChange(){
	//日期绑定改变时间进行check
	$("#modalForm #userBirthday").bind("change",function(){
		 $('#modalForm').data('bootstrapValidator').updateStatus('userBirthday', 'NOT_VALIDATED', null).validateField('userBirthday');
	});
}

//查询表格信息
function searchUserInfo() {
	var data = getFormJson("searchForm");//获取查询条件
	commonRowDatas("userInfoTable", data, api_getUserPageList, "commonCallback", true);
}

function deleteRow() {
	var rowCount = selectedCount("userInfoTable");
	if (rowCount > 0) {
		// 获取选中行
		var rows = selectedRows("userInfoTable");
		var rowIds = "";
		for ( var i = 0; i < rows.length; i++) {
			rowIds += rows[i].userId + ",";
		}
		var data = {
			userId:rowIds       
		}
		showConfirm(sureDelete, IF_DELETE_INFO, POST, api_deleteUserSelected, data, searchUserInfo);
	} else {
		showOnlyMessage(ERROR, $message("ErrorSelectNoDelete", null));
	}

}

function sureDelete(type, url, data, success) {
	doAjax(POST, url, data, success);
}

// 点击编辑按钮向后台请求要查询的数据
function editRow() {
    var selectRows = selectedCount("userInfoTable");
    if (selectRows == 0){
    	showOnlyMessage(ERROR,$message("ErrorNoSelectEdit",null));
    }else if(selectRows > 1){
    	showOnlyMessage(ERROR,$message("ErrorSelectMultiEdit",null));
    }
    else{
    	var row = selectedRows("userInfoTable");
    	$("#isNew").val('0');
    	checkDetail(row[0].userId);
    }
}

// 关闭modal画面
function closemodal() {
	// 关闭前先清空modal画面的form表单
	$('#modalForm').data('bootstrapValidator').resetForm(true);
	//将hidden项清空
	$("#isNew").val('');
	$("#orgId").val('');
	$("#roleId").val('');
	$('#title').html('');//设置modal的标题
	$("#modalForm #userId2").removeAttr("readonly");
	$('#myModal').modal('hide');
}

//查看用户详细信息
function checkDetail(userId) {
	var data={
	    userId:userId
	};
	doAjax(POST, api_getUserById, data, checkDetailSuccess)
}

function checkDetailSuccess(response){
	if(response.status == 'success')	{
        response = response.user;
		$("#modalForm #userId2").attr("readonly","readonly");
        $("#modalForm #id").val(response.id);
		$("#modalForm #userId2").val(response.userId);
		$("#modalForm #userName2").val(response.userName);
		$("#modalForm #userPwd").val(response.userPwd);
		$("#modalForm #remark").val(response.remark);
		$("#modalForm #phone").val(response.phone);
		$("#modalForm #email").val(response.email);
		$("#modalForm #status").val(response.status);
		$('#title').html('');
		$('#title').append("编辑用户信息");//设置modal的标题
		//$('#myModal').modal('show');
		$('#myModal').modal({show:true,backdrop: 'static', keyboard: false});
	}
	
}

function initOrg(){
	loadOrgTree();
	$('#treeModal').modal({show:true,backdrop: 'static', keyboard: false});
}
function closeTreemodal(){
	$('#treeModal').modal('hide');
}

// 组织树初始化
function loadOrgTree(){
	doAjax(POST, api_org_tree, null, orgtreeCallback)
}
//回调函数中加载组织树
function orgtreeCallback(response){
	var setting = {
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {  
			onClick: clickTreeNoed 
		}  
	};
	$.fn.zTree.init($("#orgTree"), setting, response);
}
// 节点点击事件
function clickTreeNoed(event, treeId, treeNode,clickFlag){
		$("#orgName").val(treeNode.name);
		$("#orgId").val(treeNode.id);
		//由于不是直接在输入框中输入，需要重新出发校验事件
		$('#modalForm')
	    // Get the bootstrapValidator instance
	    .data('bootstrapValidator')
	    //将之前的检测结果清除，以便重新进行检测
	    .updateStatus('orgName', 'NOT_VALIDATED', null)
	    // Validate the field
	    .validateField('orgName');
		$('#treeModal').modal('hide');
}


//初始化角色树
function initRole(){
	loadRoleTree();
	$('#roleModal').modal({show:true,backdrop: 'static', keyboard: false});
	
}
function loadRoleTree(){
    var isNew = $("#isNew").val();
    var data = {};
    if (isNew!=1){
    	data.userid = $("#userId2").val();
	}
	doAjax(POST, api_having_role, data, roleTreeCallback);
}

function roleTreeCallback(response){
    var isNew = $("#isNew").val();
	var alltree = response.all;
	var havingtree = response.having;
    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true,
                idKey:"id"
            },
            key:{
                name:"roleName"
            }
        }
    };

    var nodes = alltree;
    var treeObj = $.fn.zTree.init($("#roleTree"), setting, nodes);

    if(isNew!=1){
        for (var i = 0; i < havingtree.length; i++) {
            var node = havingtree[i];
            var n = treeObj.getNodeByParam("id", node.id, null);
            treeObj.checkNode(n, true, true);
        }
	}




}
//关闭角色选择modal
function closerolemodal(){
	$('#roleModal').modal('hide');
}
//角色选择画面确认按钮压下
function roleSureClick(){
	var treeObj = $.fn.zTree.getZTreeObj("roleTree");
	var nodes = treeObj.getCheckedNodes(true);
	var nodeIds = "";
	var nodeNames = "";
	for(var i=0;i<nodes.length;i++){
		if(i == nodes.length-1){
			nodeIds = nodeIds + nodes[i].id;
			nodeNames = nodeNames + nodes[i].roleName;
		}else{
			nodeIds = nodeIds + nodes[i].id + ","
			nodeNames = nodeNames + nodes[i].roleName + ","
		}
	}
	$("#roleName").val(nodeNames);
	$("#roleId").val(nodeIds);
	//由于不是直接在输入框中输入，需要重新出发校验事件
	/*$('#modalForm')
    // Get the bootstrapValidator instance
    .data('bootstrapValidator')
    //将之前的检测结果清除，以便重新进行检测
    .updateStatus('roleName', 'NOT_VALIDATED', null)
    // Validate the field
    .validateField('roleName');*/
	$('#roleModal').modal('hide');
}

//新增用户
function addNew(){
	$('#title').html('');
	$('#title').append("添加用户");//设置modal的标题
	//$('#userBirthday').val('');
	$("#isNew").val('1');
	$('#myModal').modal({show:true,backdrop: 'static', keyboard: false});
}