<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<div style="padding:10px 10px 10px 10px">
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
			       <a href="javascript:void(0)" class="easyui-linkbutton addGroup">添加分组</a>
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
  <div  class="hide itemParamAddTemplate" >
	<li class="param">
		<ul>
			<li>
				<input class="easyui-textbox" style="width: 150px;" name="group"/>&nbsp;
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
	$(function() {
		var row = $("#itemParamList").datagrid('getRowIndex',$("#itemParamList").datagrid('getSelected'));
		initDataParam(paramData[row]);
	});
	
	function initDataParam (value) {
		var json = JSON.parse(value);
		$.each(json,function(i,e){
			var groupInput = $(".param").clone();
			
		});
	}
</script>