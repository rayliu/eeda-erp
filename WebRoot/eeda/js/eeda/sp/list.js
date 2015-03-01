
$(document).ready(function() {
	
    var setTdTitle=function(nRow){
        $('td:eq(0)', nRow).attr('data-title','小区/大厦名称');
        $('td:eq(1)', nRow).attr('data-title','租售状态');
        $('td:eq(2)', nRow).attr('data-title','房型');
        $('td:eq(3)', nRow).attr('data-title','区域');
        $('td:eq(4)', nRow).attr('data-title','面积(平)');
        $('td:eq(5)', nRow).attr('data-title','金额');
        $('td:eq(6)', nRow).attr('data-title','描述');
        $('td:eq(7)', nRow).attr('data-title','跟进情况');
        $('td:eq(8)', nRow).attr('data-title','创建人');
        $('td:eq(9)', nRow).attr('data-title','创建日期');
    }

	//datatable, 动态处理
    var oTable = $('#eeda-table').dataTable({
        //"sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
        "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
        "sPaginationType": "bootstrap",
        "iDisplayLength": 10,
    	"oLanguage": {
            "sUrl": "/eeda/dataTables.ch.txt"
        },
        //"sPaginationType": "full_numbers",
        "bProcessing": false,
        "bServerSide": true,
        "sAjaxSource": "/sp/list",
        "aoColumns": [
            { 
                "mDataProp": null, 
                "sWidth": "95px",
                "fnRender": function(obj) {                    
                    return  "<a class='btn btn-info send-mail' href='#' id='"+obj.aData.ID+"'>"+
                                "<i class='icon-edit icon-white'></i>"+
                                "发邮件"+
                            "</a>";
                }
            }, 
            {"mDataProp": "MAIL_SENT_TIME", "sWidth": "95px"},
            {"mDataProp": null, "sWidth": "55px"}, 
            {"mDataProp":"MAINTENANCE_OFFICE", "sWidth": "55px"},
            {"mDataProp":"PROVIDER_NAME",
            	"sWidth": "20%",
                "fnRender": function(obj) {                    
                    return  "<a href='/sp/edit/"+obj.aData.ID+"' id='"+obj.aData.ID+"'>"+
                                obj.aData.PROVIDER_NAME+                                
                            "</a> <br>"+
                            "<a href='http://www.baidu.com/#wd="+obj.aData.PROVIDER_NAME+"' class='btn btn-info' target='_blank' >查找</a>";
                }
            },
            {"mDataProp":"CONTACT",
            	"sWidth": "30%",
            	"fnRender": function(obj) {                    
                    return  "联系人："+obj.aData.CONTACT+"<br>"+
                    		"电话："+obj.aData.PHONE_NO+ "<br>"+
                    		"传真："+obj.aData.FAX_NO+ "<br>"+
                    		"地址："+obj.aData.ADDRESS1;
                }
            },        	
        	{"mDataProp":"EMAIL", "sWidth": "20%"}
            /*{"mDataProp":"PHONE_NO"},
            {"mDataProp":"FAX_NO"},
            {"mDataProp":"ADDRESS1"},
            {"mDataProp":"COUNTRY"},        
        	{"mDataProp":"PROVINCE"},
            {"mDataProp":"CITY", "sWidth": "10%"}*/
        ]
    });

    $("#eeda-table").on("click", 'a.send-mail', function(e){
        e.preventDefault();
        id=$(this).attr('id');
        $.post("/sp/sendMarketingMail/", {id: id}, function(data){

        });
	});
	
    $('.editLink').click(function(e){
        e.preventDefault();
        url=$(this).attr('href');
        url=url+buildQueryParas();
        window.location.href = url; 
    });
    
    
    
	$("#resetBtn").on("click", function(e){
		e.preventDefault();
		var input_box = $('#eeda-table_filter input').first();
        input_box.val('');

        $("#status").val('').trigger('change');
        $("#type").val('').trigger('change');
        $("#region").val('').trigger('change');

        $('#area_min').val('').trigger('change');
		$('#area_max').val('').trigger('change');
		$('#rent_min').val('').trigger('change');
		$('#rent_max').val('').trigger('change');
		$('#total_min').val('').trigger('change');
		$('#total_max').val('').trigger('change');
		$('#fitler_building_no').val('').trigger('change');
		$('#fitler_building_unit').val('').trigger('change');
		$('#fitler_room_no').val('').trigger('change');
        
        $('#totalFilterDiv').hide();
        $('#rentFilterDiv').hide();
        
        $("#area_min").val(0).trigger('keyup'); 
        $("#rent_min").val(0).trigger('keyup'); 
        localStorage.clear();
        $("#area_min").val("");
        $("#rent_min").val("");
        oTable.fnFilter('', null, false, true);
        
    });

	$("#office").on("change", function(){
        var typeVal = $(this).val();
        localStorage.setItem('officeFilter', $("#office").val());
        oTable.fnFilter(typeVal, 3, false, true);
    });

    $("#type").on("change", function(){
		localStorage.setItem('type', $("#type").val());
        var typeVal = $(this).val();
        if('allDepartment'!=typeVal){
        	oTable.fnFilter(typeVal, 5, false, true);
        }else{
        	oTable.fnFilter('', 5, false, true);
        	oTable.fnDraw(); 
        }        
    });
    
    $("#region").on("change", function(){
		localStorage.setItem('region', $("#region").val());
        var typeVal = $(this).val();
        oTable.fnFilter(typeVal, 6, false, true);
    });

    /* Add event listeners to the two range filtering inputs */
	$('#area_min').on("keyup", function() {
		localStorage.setItem('area_min', $("#area_min").val());
        var typeVal = $(this).val()+"-"+$('#area_max').val();
		oTable.fnFilter(typeVal, 7, false, true);
	});
	$('#area_max').on("keyup", function() {
		localStorage.setItem('area_max', $("#area_max").val());
        var typeVal = $('#area_min').val()+"-"+$(this).val();
		oTable.fnFilter(typeVal, 7, false, true);
	});
	$('#rent_min').on("keyup", function() {
		localStorage.setItem('rent_min', $("#rent_min").val());
		var typeVal = $(this).val()+"-"+$('#rent_max').val();
        oTable.fnFilter(typeVal, 8, false, true);
	});
	$('#rent_max').on("keyup", function() {
		localStorage.setItem('rent_max', $("#rent_max").val());
		var typeVal = $('#rent_min').val()+"-"+$(this).val();
        oTable.fnFilter(typeVal, 8, false, true);
	});
	$('#total_min').on("keyup", function() {
		localStorage.setItem('total_min', $("#total_min").val());
		oTable.fnDraw(); 
	});
	$('#total_max').on("keyup", function() {
		localStorage.setItem('total_max', $("#total_max").val());
		oTable.fnDraw(); 
	});
	$('#fitler_building_no').on("keyup", function() {
		localStorage.setItem('fitler_building_no', $("#fitler_building_no").val());
		oTable.fnFilter($(this).val(), 0, false, true);
	});
	$('#fitler_building_unit').on("keyup", function() {
		localStorage.setItem('fitler_building_unit', $("#fitler_building_unit").val());
		oTable.fnFilter($(this).val(), 1, false, true);
	});
	$('#fitler_room_no').on("keyup", function() {
		localStorage.setItem('fitler_room_no', $("#fitler_room_no").val());
		oTable.fnFilter($(this).val(), 2, false, true);
	});

	jQuery.fn.limit=function(){ 
	    var self = $("td[limit]"); 
	    self.each(function(){ 
	        var objString = $(this).text(); 
	        var objLength = $(this).text().length; 
	        var num = $(this).attr("limit"); 
	        if(objLength > num){ 
				$(this).attr("title",objString); 
	            objString = $(this).text(objString.substring(0,num) + "..."); 
	        } 
	    }) 
	} 

	$("#eeda-table").limit(); 
	
	var initLocalStorage= function(){
		//local storage
		if(localStorage.getItem("officeFilter")){
			$("#office").val(localStorage.getItem("officeFilter")).trigger('change');  
		} 
	}
	
	initLocalStorage();
} );