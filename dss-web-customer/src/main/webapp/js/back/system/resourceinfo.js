var api_getUserPageList = ctx + "/back/authInfo/resourcePageList"
var api_addUser = ctx + "/back/authInfo/addResource";
var api_updateUser = ctx + "/back/authInfo/updateResource";
var api_deleteUserSelected = ctx+ "/back/authInfo/deleteResource";
var api_getUserById = ctx + "/back/authInfo/findResource";
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
            name : {
                validators : {
                    notEmpty : { message : $message("ErrorMustInput",[ '角色名' ]) }
                }
            },
			url :{
                validators : {
                    notEmpty : { message : $message("ErrorMustInput",[ '地址' ]) }
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
		    { field : "id", title : "资源id", align : "center", valign : "middle" },
			{ field : "name", title : "资源名", align : "center", valign : "middle" },
            { field : "url", title : "链接地址", align : "center", valign : "middle" },
            { field : "permission", title : "权限名称", align : "center", valign : "middle" },
			{ field : "opration", title : "操作", align : "center", valign : "middle",
				formatter : function(value, row, index) {
					return detailBtn(row.id);
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
			rowIds += rows[i].id + ",";
		}
		var data = {
			id:rowIds
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
    	checkDetail(row[0].id);
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
	doAjax(POST, api_getUserById, data, checkDetailSuccess)
}

function checkDetailSuccess(response){
	if(response.status == 'success')	{
		$("#modalForm #name2").attr("readonly","readonly");
		$("#modalForm #name").val(response.name);
		$("#modalForm #url").val(response.url);
		$("#modalForm #permission").val(response.permission);
		$('#title').html('');
		$('#title').append("编辑资源信息");//设置modal的标题
		$('#myModal').modal({show:true,backdrop: 'static', keyboard: false});
	}
	
}


//新增用户
function addNew(){
	$('#title').html('');
	$('#title').append("添加角色");//设置modal的标题
	//$('#userBirthday').val('');
	$("#isNew").val('1');
	$('#myModal').modal({show:true,backdrop: 'static', keyboard: false});
}
