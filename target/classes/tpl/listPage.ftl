<html>
<head>
<title>用户管理</title>
<link rel="stylesheet" type="text/css" href="/CJP/jquery_easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/CJP/jquery_easyui/themes/icon.css"> 

<script type="text/javascript" src="/CJP/jquery_easyui/jquery.min.js"></script>
<script type="text/javascript" src="/CJP/jquery_easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/CJP/jquery_easyui/datagrid-filter.js"></script>
<script type="text/javascript" src="/CJP/jquery_easyui/easyui-lang-zh_CN.js"></script>

<link rel="stylesheet" type="text/css" href="/CJP/jquery_easyui/demo/demo.css"> 
<script type="text/javascript">
//页面初始化
	$(function(){
  		//初始化datagrid
		$('#userInfo').datagrid({
			resizable:true,
			rownumbers:true,
			singleSelect:false,
			ctrlSelect:true,
			striped:true,
			width:'100%',
			height:'100%',
			url:'/CJP/channel/listchannels',
			method:'get',
			toolbar:'#usertb',
			footer:'#userft',
			pageSize:20,//每页显示的记录条数，默认为20  
	    	pageList:[20,50,100],//可以设置每页记录条数的列表 
			pagination:true,
			onUnselect:function(index){
				
			},
			onDblClickRow: function(index){
				
			},
			onLoadSuccess:function(data){
				if(data.total===0) {
					$.messager.alert("提示信息","未找到记录!","info"); 
				}
			},
			columns:[[
				{field:'code',title:'渠道编码',width:'150px',sortable:true},
				{field:'name',title:'渠道名称',width:'150px',sortable:true},
				
				{field:'enable',title:'状态',width:'80px',
					formatter: function(value,row,index){
						if (value == "Y"){
							return '启用';
						} else {
							return '停用';
						}
					}
				},
				{field:'creationtime',title:'创建时间',width:'150px',sortable:true},
				{field:'modifiedtime',title:'更新时间', width:'150px',sortable:true}
			]]
		});
		
		$('#userInfo').datagrid('enableFilter',[{
				field:'enable',
				type:'combobox',
				options:{
					panelHeight:'auto',
					data:[{value:'',text:'全部'},{value:'Y',text:'启用'},{value:'N',text:'停用'}],
					onChange:function(value){
						if (value == ''){
							$('#userInfo').datagrid('removeFilterRule', 'enable');
						} else {
							$('#userInfo').datagrid('addFilterRule', {
								field: 'enable',
								op: 'equal',
								value: value
							});
						}
						$('#userInfo').datagrid('doFilter');
					}
				}
			}
		]);//添加二次过滤
		
		if("SUCCESS" == "${saveResult!""}"){
			$.messager.alert("提示信息","保存成功!","info");
		}
	});
	   
	var intfData = {
		add:function(){
			window.location.href="/CJP/channel/channeloperate?oper=add";
		},
		modify:function(){
			var row = $('#userInfo').datagrid('getSelected');
	        if (row) {
	           	window.location.href="/CJP/channel/channeloperate?oper=modify&channelid="+row.pk_type;
	        } else {
	        	$.messager.alert("提示信息","请选中需要修改的记录!","info");
	        }
		},
		del:function(){
			var row = $('#userInfo').datagrid('getSelected');
	        if (row) {
	        	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
				    if (r){    
				        var rowIndex = $('#userInfo').datagrid('getRowIndex', row);
			           	$('#userInfo').datagrid('deleteRow', rowIndex);
			           	$.ajax({
		                   cache: false,
			               type: "POST",
			               url:"/CJP/channel/deletechannel?channelId="+row.pk_type,
			               async: false,
			               success: function(data) {
			                   if(data === "SUCCESS"){
									$.messager.alert("提示信息","删除成功!","info");
			                   } else {
				                   	$("#userInfo").datagrid("rejectChanges");
				                   	$.messager.alert("提示信息","删除失败!"+data,"info");
			                   }
			               }
		       		   });      
					}    
				});  
	        } else {
	        	$.messager.alert("提示信息","请选中需要删除的记录!","info");
	        }
		},
		refresh:function(){
			$("#userInfo").datagrid("reload","/CJP/channel/listchannels");
		}
	};
</script>
</head>
<body class="easyui-layout">
	<div class="easyui-panel" data-options="fit:true,border:false" style="width:100%;height:100%;">
		<table id="userInfo"></table>
		<div id="usertb">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="intfData.add()">新增</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="intfData.modify()">修改</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="intfData.del()">删除</a>
		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="intfData.refresh()">刷新</a>
		</div>
		<div id="userft"></div>
	</div>
</body>


</html>
