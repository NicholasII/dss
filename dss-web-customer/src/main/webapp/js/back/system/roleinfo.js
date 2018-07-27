var api_getUserPageList = ctx + "/back/authInfo/rolePageList"
var api_addUser = ctx + "/back/authInfo/addRole";
var api_updateUser = ctx + "/back/authInfo/updateRole";
var api_deleteUserSelected = ctx+ "/back/authInfo/deleteRole";
var api_getRoleById = ctx + "/back/authInfo/findRole";
var api_getResourceTree = ctx + "/back/authInfo/resourcetree";
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
            roleName2 : {
                validators : {
                    notEmpty : { message : $message("ErrorMustInput",[ '角色名' ]) },
                    stringLength: { min: 2,  max: 16, message: $message("ErrorLength2",['角色名','2','16']) }
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
		    { field : "id", title : "角色id", align : "center", valign : "middle" },
			{ field : "roleName", title : "角色名", align : "center", valign : "middle" },
            { field : "roleType", title : "角色类型", align : "center", valign : "middle" },
            { field : "roleDesc", title : "角色描述", align : "center", valign : "middle" },
            { field : 'createTime', title : '创建时间', align : "center", valign : "middle" },
            { field : "updateTime", title : "更新时间", align : "center", valign : "middle" },
			{ field : "opration", title : "操作", align : "center", valign : "middle",
				formatter : function(value, row, index) {
					return detailBtn(row.id);
				}
			},
            { field : "opration", title : "授权", align : "center", valign : "middle",
                formatter : function(value, row, index) {
                    return authBtn(row.id);
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
function checkDetail(rid) {
	var data={
	    id:rid
	};
	doAjax(POST, api_getRoleById, data, checkDetailSuccess)
}

function checkDetailSuccess(response){
	if(response.status == 'success')	{
		$("#modalForm #userId2").attr("readonly","readonly");
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

function closeTreemodal(){
	$('#treeModal').modal('hide');
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
	var data={
	    id:$("#roleId").val()
	};
	doAjax(POST, api_role_tree, data, roleTreeCallback);
}

function roleTreeCallback(response){
	var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		} 
	};
	$.fn.zTree.init($("#roleTree"), setting, response);
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
			nodeNames = nodeNames + nodes[i].name;
		}else{
			nodeIds = nodeIds + nodes[i].id + ","
			nodeNames = nodeNames + nodes[i].name + ","
		}
	}
	$("#roleName").val(nodeNames);
	$("#roleId").val(nodeIds);
	//由于不是直接在输入框中输入，需要重新出发校验事件
	$('#modalForm')
    // Get the bootstrapValidator instance
    .data('bootstrapValidator')
    //将之前的检测结果清除，以便重新进行检测
    .updateStatus('roleName', 'NOT_VALIDATED', null)
    // Validate the field
    .validateField('roleName');
	$('#treeModal').modal('hide');
	$('#roleModal').modal('hide');
}

//新增用户
function addNew(){
	$('#title').html('');
	$('#title').append("添加角色");//设置modal的标题
	//$('#userBirthday').val('');
	$("#isNew").val('1');
	$('#myModal').modal({show:true,backdrop: 'static', keyboard: false});
}

//资源授权
function authDetail(roleid) {
	var data= [];
    var role = {};
    role.name = "roleid";
    role.value = roleid;
    data.push(role);
    formSubmit(api_getResourceTree,data);
}