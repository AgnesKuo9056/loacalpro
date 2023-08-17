<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link rel="stylesheet" type="text/css" href="dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css" href="bootstrap.min.css"/>
<html>
<head>
<title>设备列表</title>
</head>

<body>
	<input type="hidden" id="page_id" name="page_id" value="device_list"/>

	<table class="table table-striped table-bordered table-hover datatable" id="record_list">
		<thead>
		<tr>
			<th class="table-checkbox"><input type="checkbox" class="group-checkable" data-set="#record_list .checkboxes" /></th>
			<th>设备ID</th>
			<th>设备名称</th>
			<th>文件大小</th>
			<th>登记时间</th>
			<th>图片显示</th>
			<th>操作</th>
		</tr>
		</thead>
	</table>
	<button id="add_button" name="add_button">添加设备</button>

<%--	<input type="button" name="query_btn" id="query_btn" value="以设备编号查询记录">--%>
</body>
</html>
<%--<script>
	$('.datatable').dataTable( {
		"paging":true,
		"searching":false,
		"oLanguage": {
			"aria": {
				"sortAscending": ": activate to sort column ascending",
				"sortDescending": ": activate to sort column descending"
			},
			"sProcessing":   "处理中...",
			"sLengthMenu":   "_MENU_ 记录/页",
			"sZeroRecords":  "没有匹配的记录",
			"sInfo":         "显示第 _START_ 至 _END_ 项记录，共 _TOTAL_ 项",
			"sInfoEmpty":    "显示第 0 至 0 项记录，共 0 项",
			"sInfoFiltered": "(由 _MAX_ 项记录过滤)",
			"sInfoPostFix":  "",
			"sSearch":       "过滤:",
			"oPaginate": {
				"sFirst":    "首页",
				"sPrevious": "上页",
				"sNext":     "下页",
				"sLast":     "末页"
			}
		},
		"aoColumns": [
			{"mRender": function(data, type, full) {
					console.log(data);
					console.log(full);
					sReturn = '<input type="checkbox" class="checkboxes" value="'+full.id+'"/>';
					return sReturn;
				},"orderable": false
			},{"mRender": function(data, type, full) {
					console.log(data);
					console.log(full);
					sReturn =' <span><a href="'+full.op+' target="_blank">'+下载文件+'</a></span>';
					return sReturn;
				},"orderable": false
			},{"mRender": function(data, type, full) {
					console.log(data);
					console.log(full);
					sReturn = '<div>'+full.device_id+'</div>';
					return sReturn;
				},"orderable": false
			},{"mRender": function(data, type, full) {
					console.log(data);
					console.log(full);
					sReturn = full.device_name;
					return sReturn;
				},"orderable": false
			},{"mRender": function(data, type, full) {
					console.log(data);
					console.log(full);
					sReturn = '<div><font color="red">'+full.sizee+'</font></div>';
					return sReturn;
				},"orderable": false
			},{"mRender": function(data, type, full) {
					console.log(data);
					console.log(full);
					sReturn = '<div><font color="red">'+full.create_time+'</font></div>'+'<div><a href="javascript:Page.onModifyRecord('+full.id+')">【修改记录】</a><a href="javascript:Page.onDeleteRecord('+full.id+')">【删除记录】</a><div>';
					return sReturn;
				},"orderable": false
			}],
		"aLengthMenu": [[5,10,15,20,25,40,50,-1],[5,10,15,20,25,40,50,"所有记录"]],
		"fnDrawCallback": function(){$(".checkboxes").uniform();$(".group-checkable").uniform();},
		//"sAjaxSource": "get_record.jsp"
		"sAjaxSource": module+"_"+sub+"_servlet_action?action=get_device_record"
	});
	$('.datatable').find('.group-checkable').change(function () {
		var set = jQuery(this).attr("data-set");
		var checked = jQuery(this).is(":checked");
		jQuery(set).each(function () {
			if (checked) {
				$(this).attr("checked", true);
				$(this).parents('tr').addClass("active");
			} else {
				$(this).attr("checked", false);
				$(this).parents('tr').removeClass("active");
			}
		});
		jQuery.uniform.update(set);
	});
	$('.datatable').on('change', 'tbody tr .checkboxes', function () {
		$(this).parents('tr').toggleClass("active");
	});
</script>--%>
<script src="jquery.min.js"></script>
<script type="text/javascript" src="jquery.uniform.min.js"></script>
<script type="text/javascript" src="jquery.dataTables.min.js"></script>
<script src="device.js"></script>