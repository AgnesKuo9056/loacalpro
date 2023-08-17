var module="device";
var sub="file";
/*================================================================================*/

jQuery(document).ready(function () {
	Page.init();

})
/* ================================================================================ */
//关于页面的控件生成等操作都放在Page里
var Page = function() {
	/*----------------------------------------入口函数  开始----------------------------------------*/
	var initPageControl=function(){
		pageId=$("#page_id").val();
		if(pageId=="device_list"){
			initDeviceList();
		}
/*		if(pageId=="device_add"){
			initDeviceAdd();
		}*/
/*		if(pageId=="device_modify"){
			initDeviceModify();
		}
		if(pageId=="device_search"){
			initDeviceQuery();
		}*/
	};
	/*----------------------------------------入口函数  结束----------------------------------------*/
	var columnsData=undefined;
	var recordResult=undefined;
	/*----------------------------------------业务函数  开始----------------------------------------*/
	/*------------------------------针对各个页面的入口  开始------------------------------*/
	var initDeviceList=function(){
		initDeviceListControlEvent();
		initDeviceRecordList();
	}
/*	var initDeviceAdd=function(){
		initDeviceAddControlEvent();
	}*/
/*	var initDeviceModify=function(){
		initDeviceModifyControlEvent();
		initDeviceRecordView();
	}
	var initDeviceQuery=function () {
		initDeviceQueryControlEvent();
	}*/
	/*------------------------------针对各个页面的入口 结束------------------------------*/
	// var getUrlParam=function(name){
	// 	//获取url中的参数
	// 	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	// 	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	// 	console.log(r);
	// 	if (r != null) return decodeURI(r[2]); return null; //返回参数值，如果是中文传递，就用decodeURI解决乱码，否则用unescape
	// }
	var initDeviceListControlEvent=function(){
		$("#help_button").click(function() {help();});
	//	$('#query_btn').click(function() {onqueryRecord();});
		$('#add_button').click(function() {onAddRecord();});
	}
/*	var initDeviceAddControlEvent=function(){
		$("#help_button").click(function() {help();});
		$('#add_button').click(function() {submitAddRecord();});
	}*/
	/*var initDeviceModifyControlEvent=function(){
		$("#help_button").click(function() {help();});
		$('#modify_button').click(function() {submitModifyRecord();});
	}
	var initDeviceQueryControlEvent=function () {
		$("#help_button").click(function() {help();});
		$('#search_btn').click(function() {submitSearchRecord();});
		$('#back_btn').click(function() {backtoDeviceList();});
	}
	var initDeviceRecordView=function(){

		var id=getUrlParam("id");
		var data={};
		data.action="get_device_record";
		data.id=id;
		$.post(module+"_"+sub+"_servlet_action",data,function(json){
			console.log(JSON.stringify(json));
			if(json.result_code==0){
				var list=json.aaData;
				if(list!=undefined && list.length>0){
					for(var i=0;i<list.length;i++){
						var record=list[i];
						$("#device_id").val(record.device_id);
						$("#device_name").val(record.device_name);
						$("#sizee").val(record.sizee);
						$("#create_time").val(record.create_time);

					}
				}
			}
		})
	}*/
	var onAddRecord=function(){
		window.location.href="device_add.jsp";
	}
/*	var submitAddRecord=function(){
		var url="device_file_servlet_action";
		var data={};
		data.action="add_device_record";
		data.device_id=$("#device_id").val();
		data.device_name=$("#device_name").val();
		data.create_time=$("#create_time").val();
		$.post(url,data,function(json){
			if(json.result_code==0){
				alert("已经完成设备添加。");
				window.location.href="device_list.jsp";
			}
		});
	}*/

	/*var submitSearchRecord =function () {
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
					"sAjaxSource": module+"_"+sub+"_servlet_action?action=query_device_record&device_id="+$("#qu_device_id").val()
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

			}


	var submitModifyRecord=function(){
		if(confirm("您确定要修改该记录吗？")){
			var id=getUrlParam("id");
			var url="device_file_servlet_action";
			var data={};
			data.action="modify_device_record";
			data.id=id;
			data.device_id=$("#device_id").val();
			data.device_name=$("#device_name").val();
			data.creator=$("#creator").val();
			data.create_time=$("#create_time").val();
			$.post(url,data,function(json){
				if(json.result_code==0){
					alert("已经完成设备修改。");
					window.location.href="device_list.jsp";
				}
			});
		}
	}
*/
	
	var initDeviceRecordList=function(){
		getDeviceRecordList();
	}
/*	var initDeviceMobileRecord=function(){
		getDeviceMobileRecord();
	}*/

/*	var getDeviceRecordList1=function(){
		$.post(module+"_"+sub+"_servlet_action?action=get_device_record",function(json){
			console.log(JSON.stringify(json));
			if(json.result_code==0){
				var list=json.aaData;
				var html="";
				if(list!=undefined && list.length>0){
					for(var i=0;i<list.length;i++){
						var record=list[i];
						html=html+"<div>序号："+i+"<div>";
						html=html+"<div>设备ID："+record.device_id+"<div>";
						html=html+"<div>设备名称："+record.device_name+"<div>";
						html=html+"<div><a href=\"javascript:Page.onModifyRecord("+record.id+")\">【修改记录】</a><a href=\"javascript:Page.onDeleteRecord("+record.id+")\">【删除记录】</a><div>";
						html=html+"<p>";
					}
				}
				$("#record_list_div").html(html);
			}
		})
	}*/
	var getDeviceRecordList=function () {
		$('.datatable').dataTable( {
			"destroy": true,
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

					sReturn = '<input type="checkbox" class="checkboxes" value="'+full.id+'"/>';
					return sReturn;
				},"orderable": false
			},{"mRender": function(data, type, full) {

					sReturn = '<div>'+full.device_id+'</div>';
					return sReturn;
				},"orderable": false
			},{"mRender": function(data, type, full) {

					sReturn = full.device_name;
					return sReturn;
				},"orderable": false
			},{"mRender": function(data, type, full) {

					sReturn = '<div><font color="red">'+full.sizee+'</font></div>';
					return sReturn;
				},"orderable": false
			},{"mRender": function(data, type, full) {

				sReturn = '<div><font color="red">'+full.create_time+'</font></div>'/*+'<div><a href="javascript:Page.onDeleteRecord('+full.id+')">【删除记录】</a><div>'*/;
					return sReturn;
				},"orderable": false
			},{"mRender": function(data, type, full) {
			if(full.image=="true") {

					sReturn='<img src="' + full.op +'" width="200" height="200"/>';
					return sReturn;
				}else{
					sReturn='<span>'+"文档为:"+full.op+'</span>';
					return sReturn;
				}

				},"orderable": false
			},{"mRender": function(data, type, full) {

						sReturn =' <span><a href="'+full.op+'" target="_blank">'+"下载"+'</a></span>';
						return sReturn;
					},"orderable": false
				}
			],
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

	}

	var onDeleteRecord = function(id){
		if(confirm("您确定要删除这条记录吗？")){
			if(id>-1){
				var url="device_file_servlet_action";
				var data={};
				data.action="delete_device_record";
				data.id=id;
				$.post(url,data,function(json){
					if(json.result_code==0){
						getDeviceRecordList();
					}
				})
			}
		}
	};

/*	var backtoDeviceList=function () {
		window.location.href="device_list.jsp";

	}*/
	// var onqueryRecord = function () {
	// 	window.location.href="device_search.jsp";
	// }
	// var onModifyRecord=function(id){
	// 	window.location.href="device_modify.jsp?id="+id;
	// }
	//Page return 开始
	return {
		init: function() {
			initPageControl();
		},
		onDeleteRecord:function(id){
			onDeleteRecord(id);
		},
/*		onModifyRecord:function(id){
			onModifyRecord(id);
		}*/
	}
}();//Page
/*================================================================================*/
