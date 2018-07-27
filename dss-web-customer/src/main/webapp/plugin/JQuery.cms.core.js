(function($){
	$.fn.extend({
		"commonulli":function(options){
			options=$.extend({
				selected:"navSelected",
				titleTagName:"h3"
			},options||{});
			var titleNode = $(this).find("ul>"+options.titleTagName);
			var selectNode = $(this).find("ul."+options.selected+">"+options.titleTagName);
			titleNode.css("cursor,pointer");
			titleNode.nextAll().css("display","none");
			selectNode.css("display","block");
			titleNode.click(function(){
				var isCheck = $(this).parent().hasClass(options.selected);
				if(isCheck){
					$(this).parent().removeClass(options.selected);
					$(this).nextAll().slideUp();
				}else{
					$(this).parent().addClass(options.selected);
					$(this).nextAll().slideDown();
				}
			});
			return this;
		},
		"myZtree":function(options,zNodes){
			var setting = $.extend({
				view: {
					dblClickExpand: false,
					selectedMulti: false
				}
			},options||{});
			var zTreeObj = $.fn.zTree.init($(this), setting, zNodes);
			zTreeObj.expandAll(true);
			zTreeObj.getCheckParentNodes = getCheckParentNodes;
			zTreeObj.getCheckChildNodes = getCheckChildNodes;
			/*
			 * 获取指定节点所有未选中/选中的父节点
			 */
			function getCheckParentNodes(treeNode,isCheck) {
				var ps = new Array();
				var pn;
				while ((pn = treeNode.getParentNode())) {
					if (pn.checked==isCheck) ps.push(pn);
					treeNode = pn;
				}
				return ps;
			}
            /*
             * 获取指定节点所有选中/未选中的孩子节点
             */
			function getCheckChildNodes(treeNode,isCheck,cs) {
                var ccs;
				if((ccs=treeNode.children)){
                    for (var index = 0; index < ccs.length; index++) {
                        var c = ccs[index];
                        if(c.checked == isCheck) cs.push(c);
                        
                        getCheckChildNodes(c,isCheck,cs);
                    }
                }
			}
			return zTreeObj;
		}
	});
})(jQuery)