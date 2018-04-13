<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<div style="padding:10px 10px 10px 10px" >
	<form id="itemParamEditForm" class="itemForm" method="post">
		<input type="hidden" name="id"/>
	    <table >
	        <tr>
	            <td>商品类目:</td>
	            <td>
	            	<input class="easyui-textbox" name="itemCatId" style="width:40px"></input>	
	            	<input type="hidden" name="id">
	            </td>
	        </tr>
	       <tr>
	       	   <td> 规格参数：</td>
	       	   <td>
			       <ul>
	        			<li><a href="javascript:void(0)" 
	        				class="easyui-linkbutton addGroup">添加分组</a>
        				</li>
        				<!-- 加入 参数 -->
	        		</ul>			   
        		</td>
	       </tr>
	       <tr>
	       	   <td>创建日期：</td>
	       	   <td><input id="created-edit" class="easyui-textbox" 
	       	   type="text" name="created" style="width: 280px;"
	       	   	   data-options="required:true" ></input></td>
	       </tr>
	       <tr>
	           <td>更新日期：</td>
	           <td><input id="updated-edit" class="easyui-textbox" 
		           type="text" name="updated"  style="width: 280px;"
		           data-options="required:true"></input></td>
	       </tr>
	       <tr>
	           <td>
	               <div style="padding:5px">
	    		       <a href="javascript:void(0)" 
	    		           class="easyui-linkbutton submit" >提交</a>
				   </div>
	           </td>
	       </tr>
	    </table>
	</form>
<div  class="hide itemParamAddTemplate">
	<li class="param">
		<ul>
			<li>
				<input class="easyui-textbox" style="width: 150px;" name="group" />&nbsp;
				<a class="easyui-linkbutton addParam" href="javascript:void(0)"  
					title="添加参数" data-options="plain:true,iconCls:'icon-add'"></a>
			</li>
			<li>
				<span>|-------</span>
				<input class="easyui-textbox" style="width: 150px;" name="param" />&nbsp;
				<a class="easyui-linkbutton delParam" href="javascript:void(0)"  
					title="删除" data-options="plain:true,iconCls:'icon-cancel'"></a>						
			</li>
		</ul>
	</li>
</div>
</div>
    
    <script>
    $(function(){  
    	var row = $('#itemParamList').datagrid('getRowIndex', $("#itemParamList").datagrid('getSelected')) ;
    	initDataParams(paramData[row])//paramData instanceof Array是数组
    });  
    
    function initDataParams (value) {
    	var json = JSON.parse(value);//解析json
		$.each(json,function(i,e){//i 当前的位置，e  当前的元素. e.group
			//参数组个数=循环次数, 添加参数组
			var temple = $(".itemParamAddTemplate li").eq(0).clone();
			//往 刚加入的input（参数组） 填入数据
			temple.find("[name=group]").val(e.group);
			$(".addGroup").parent().parent().append(temple);//在另一li下
			temple.find(".addParam").click(function(){
				var li = $(".itemParamAddTemplate li").eq(2).clone();
				li.find(".delParam").click(function(){
					$(this).parent().remove();
					$(this).parent().find("input").val();
				});
				li.appendTo($(this).parentsUntil("ul").parent());
			});
			$(".delParam").click(function(){
				$(this).parent().remove();
				$(this).parent().find("input").val();
			});
			//去掉同时生成的param
			temple.find("[name=param]").parent().remove();
			//param个数生成参数项个数
			$.each(e.params,function(i,_e) {
				var li = $(".itemParamAddTemplate li").eq(2).clone();
				li.find(".delParam").click(function() {
					$(this).parent().remove();
					$(this).parent().find("input").val();
				});
				li.appendTo(temple.children());
				li.find("[name=param]").val(_e);
			});
		});
    }
    
    $(".addGroup").click(function(){
		  var temple = $(".itemParamAddTemplate li").eq(0).clone(true);
		  $(".addGroup").parent().parent().append(temple);
		  
		  temple.find(".addParam").click(function(){
			  var li = $(".itemParamAddTemplate li").eq(2).clone();
			  li.find(".delParam").click(function(){
				  $(this).parent().remove();
			  });
			  li.appendTo($(this).parentsUntil("ul").parent());
		  });
		  
		  temple.find(".delParam").click(function(){
			  $(this).parent().remove();
		  });
	 });
    
    $("#itemParamEditForm .submit").click(function(){
		var params = [];
		var groups_input = $("#itemParamEditForm [name=group]");
		groups_input.each(function(i,e){
			var params_input = $(e).parentsUntil(".param").find("[name=param]");
			var _params = [];
			var _valGroup = $(e).val();//获得 组名
alert(i+":"+_valGroup)
			//alert(_valGroup)//获取不到组的值----------------------------------
			
			params_input.each(function(_i,_e){
				var _valParam = $(_e).siblings("input").val();
				if($.trim(_valParam).length>0){
					_params.push(_valParam);
					//alert(_valParam)//获取到项的值----------------------------------
				}
			});
			if($.trim(_valGroup).length>0 && _params.length > 0){
				params.push({
					"group":_valGroup,
					"params":_params
				});					
			}
		});
		alert(JSON.stringify(params))
		var url = "/item/param/update/"+$("#itemParamEditForm [name=id]").val();
//		$.post(url,{"paramData":JSON.stringify(params)},function(data){
//			if(data.status == 200){
//				$.messager.alert('提示','修改商品规格成功!',undefined,function(){
//					$(".panel-tool-close").click();
//					$("#itemParamEditForm").datagrid("reload");
//				});
//			}
//		});
	});
    
    </script>