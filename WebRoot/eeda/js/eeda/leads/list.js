$.fn.dataTableExt.afnFiltering.push(
    function( oSettings, aData, iDataIndex ) {


    	if(filterBuildingNo() && filterBuildingUnit() && filterRoomNo() && isTypeInRange() && isAreaInRange() && isAmountInRange() ){
    		return true;
    	}
    	return false;

    	function filterBuildingNo(){
    		var buildingNo = parseInt($("#fitler_building_no").val())*1;
        	if(buildingNo){
        		var iCellValue = aData[0];
        		var cellValue= $(iCellValue)[3].innerText.trim();
        		var index = cellValue.indexOf('栋');
        		if(index>=0){
        			var tempBuildingNo = cellValue.substr(0, index);
        			if(tempBuildingNo){
        				if(buildingNo==tempBuildingNo){
        					return true;
        				}
        				return false;
        			}        			
        		}        		
        		return false;
        	}        	
        	return true; 
    	}

    	function filterBuildingUnit(){
    		var buildingUnit = parseInt($("#fitler_building_unit").val())*1;
        	if(buildingUnit){
        		var iCellValue = aData[0];
        		var cellValue= $(iCellValue)[3].innerText.trim();
        		var startIndex = cellValue.indexOf('-')+1;
        		var endIndex = cellValue.indexOf('单元');
        		if(endIndex>=0){
        			var tempBuildingUnit = cellValue.substr(startIndex, endIndex-startIndex);
        			if(tempBuildingUnit){
        				if(buildingUnit==tempBuildingUnit){
        					return true;
        				}
        				return false;
        			}        			
        		}        		
        		return false;
        	}        	
        	return true;        	
    	}

    	function filterRoomNo(){
    		var roomNo = parseInt($("#fitler_room_no").val())*1;
        	if(roomNo){
        		var iCellValue = aData[0];
        		var cellValue= $(iCellValue)[3].innerText.trim();
        		var startIndex = cellValue.lastIndexOf('-')+1;
        		var endIndex = cellValue.indexOf('房');
        		if(endIndex>=0){
        			var tempRoomNo = cellValue.substr(startIndex, endIndex-startIndex);
        			if(tempRoomNo){
        				if(tempRoomNo.indexOf(roomNo)>=0){
        					return true;
        				}
        				return false;
        			}        			
        		}        		
        		return false;
        	}        	
        	return true;        	
    	}

        function isTypeInRange(){
        	var typeVal = $("#type").val();
        	if('allDepartment'==typeVal){
        		var iCellValue = aData[2].substr(0, 1);
        		if(iCellValue<=6){
        			return true;
        		}
        		return false;
        	}        	
        	return true;        	
        };

    	function isAreaInRange(){
    		var iColumn = 4;

			var iMin = parseInt($('#area_min').val())*1;
			var iMax = parseInt($('#area_max').val())*1;
	         
	        var iCellValue = aData[iColumn] == "" ? 0 : aData[iColumn]*1;
	        if (!iMin && !iMax){
	            return true;
	        }else if ( !iMin && iCellValue <= iMax ){
	            return true;
	        }
	        else if ( iMin <= iCellValue && !iMax ){
	            return true;
	        }
	        else if ( iMin <= iCellValue && iCellValue <= iMax ){
	            return true;
	        }
	        return false;
    	};

    	function isAmountInRange(){
    		var iColumn = 5;
    		var range, iCellValue, iMin, iMax;
	        var statusVal = $("#status").val();

	        if(statusVal==''){
	        	return true;
	        }else if(statusVal=='出租' || statusVal=='已租'){
	        	iCellValue = aData[iColumn].substr(0, aData[iColumn].length-2);
	        	iMin = parseInt($('#rent_min').val())*1;
				iMax = parseInt($('#rent_max').val())*1;
	        }else{
	        	iCellValue = aData[iColumn].substr(0, aData[iColumn].length-1);
	        	iMin = parseInt($('#total_min').val())*1;
				iMax = parseInt($('#total_max').val())*1;
	        }

	        if ( !iMin && !iMax){
	            return true;
	        }else if ( !iMin && iCellValue <= iMax ){
	            return true;
	        }else if ( iMin <= iCellValue && !iMax ){
	            return true;
	        }else if ( iMin <= iCellValue && iCellValue <= iMax ){
	            return true;
	        }
	        return false;
    	};
        
    }
);


$(document).ready(function() {
	
	//datatable, 静态处理
	/*var oTable = $('.datatable').dataTable({
		"sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
		"sPaginationType": "bootstrap",
		"oLanguage": {
			"sUrl": "dataTables.ch.txt"
		},
		"aoColumnDefs": [
	      { "sWidth": "15%", "aTargets": [ 0 ] },
	      { "sWidth": "8%", "aTargets": [ 1 ] },
	      { "sWidth": "5%", "aTargets": [ 2 ] },
	      { "sWidth": "5%", "aTargets": [ 3 ] },
	      { "sWidth": "8%", "aTargets": [ 4 ] },
	      { "sWidth": "10%", "aTargets": [ 5 ] },
	      { "sWidth": "15%", "aTargets": [ 6 ] },
	      { "sWidth": "25%", "aTargets": [ 7 ] },
	      { "sWidth": "7%", "aTargets": [ 8 ] },
	      { "sWidth": "10%", "aTargets": [ 9 ] }					      
	    ],
	    "aaSorting": [[ 9, "desc" ]],
        "fnInitComplete": function(oSettings, json) {          
          initSearch();
        }
	} );
*/
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
        "sAjaxSource": "/listLeads",
        "aoColumns": [   
            {"mDataProp":"BUILDING_NO", "bVisible": false},
            {"mDataProp":"BUILDING_UNIT", "bVisible": false},
            {"mDataProp":"ROOM_NO", "bVisible": false},
        	{   
                "sWidth": "15%",             
                "mDataProp":"BUILDING_NAME",
                "fnRender": function(obj) {
                    var returnStr = "<a href='/editLeads/"+obj.aData.ID+"''>"+obj.aData.BUILDING_NAME+"</a>";
                    if(isAdminPmInt){
                        returnStr +="<div class='building_info'>"+
                            obj.aData.BUILDING_NO+"栋"+
                            obj.aData.BUILDING_UNIT+"单元" +  
                            obj.aData.ROOM_NO+"房"+
                        "</div>";
                    }
                        
                    return returnStr;                        
                }
            },
        	{"mDataProp":"STATUS","sWidth": "8%"},
            {"mDataProp":"TYPE"},
            {"mDataProp":"REGION","sWidth": "8%"},
            {"mDataProp":"AREA"},
            {"mDataProp":"TOTAL"},
            {"mDataProp":"INTRO", "sWidth": "20%",
                "fnRender": function(obj) {
                    var intro=obj.aData.INTRO;
                    return limitLength(intro);
                }
            },
        	{"mDataProp":"REMARK",
                "fnRender": function(obj) {
                    var remark=obj.aData.REMARK;
                    return limitLength(remark);  
                }
            },            
        	{"mDataProp":"CREATOR"},
            {"mDataProp":"CREATE_DATE", "sWidth": "10%"},
            { 
                "mDataProp": null, 
                "sWidth": "8%",
                "bVisible": isAdminPmInt, 
                "fnRender": function(obj) {                    
                    return "<a class='btn btn-info' href='/editLeads/"+obj.aData.ID+"'>"+
                                "<i class='icon-edit icon-white'></i>"+
                                "编辑"+
                            "</a>"+
                            "<a class='btn btn-danger' href='/deleteLeads/"+obj.aData.ID+"'>"+
                                "<i class='icon-trash icon-white'></i>"+ 
                                "删除"+
                            "</a>";
                }
            }                         
        ],
        "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
            setTdTitle(nRow);
        },
        "fnServerData": function ( sSource, aoData, fnCallback ) {
            /* Add some extra data to the sender */
            //aoData.push( { "name": "more_data", "value": "my_value" } );
            $.getJSON( sSource, aoData, function (json) { 
                /* Do whatever additional processing you want on the callback, then tell DataTables */
                fnCallback(json)
            } );
        }
        
    });

    var limitLength=function(str){
        var objLength = str.length;
        if(objLength > 50){ 
            str = str.substring(0,50) + "..."; 
        } 
        return str;
    }

    var getQueryStringRegExp = eeda.getQueryStringRegExp;
    
	var getFilterVal=function(){
		return $("#status").val()+' '+$("#type").val()+' '+$("#region").val();
	}
	
    $('.editLink').click(function(e){
        e.preventDefault();
        url=$(this).attr('href');
        url=url+buildQueryParas();
        window.location.href = url; 
    });
    
    var buildQueryParas=function(){
        return '?status='+$("#status").val()
            +'&type='+$("#type").val()
            +'&region='+$("#region").val()
            +'&area_min='+$('#area_min').val()
            +'&area_max='+$('#area_max').val()
            +'&rent_min='+$('#rent_min').val()
            +'&rent_max='+$('#rent_max').val()
            +'&total_min='+$('#total_min').val()
            +'&total_max='+$('#total_max').val()
            +'&fitler_building_no='+$('#fitler_building_no').val()
            +'&fitler_building_unit='+$('#fitler_building_unit').val()
            +'&fitler_room_no='+$('#fitler_room_no').val();
    }
    
    var initSearch=function(){
		
		//var input_box = $('#eeda-table_filter input').first();
        //input_box.val('');
        
        $("#status").val(getQueryStringRegExp("status")).trigger('change');
        $("#type").val(getQueryStringRegExp("type")).trigger('change');
        $("#region").val(getQueryStringRegExp("region")).trigger('change');

        $('#area_min').val(getQueryStringRegExp("area_min")).trigger('change');
		$('#area_max').val(getQueryStringRegExp("area_max")).trigger('change');
		$('#rent_min').val(getQueryStringRegExp("rent_min")).trigger('change');
		$('#rent_max').val(getQueryStringRegExp("rent_max")).trigger('change');
		$('#total_min').val(getQueryStringRegExp("total_min")).trigger('change');
		$('#total_max').val(getQueryStringRegExp("total_max")).trigger('change');
		$('#fitler_building_no').val(getQueryStringRegExp("fitler_building_no")).trigger('change');
		$('#fitler_building_unit').val(getQueryStringRegExp("fitler_building_unit")).trigger('change');
		$('#fitler_room_no').val(getQueryStringRegExp("fitler_room_no")).trigger('change');
        
    };
    
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

	$("#status").on("change", function(){
		localStorage.setItem('status', $("#status").val());
        var typeVal = $(this).val();
        oTable.fnFilter(typeVal, 4, false, true);
        if(typeVal==''){
        	$('#totalFilterDiv').hide();
        	$('#rentFilterDiv').hide();
        }else if(typeVal=='出租' || typeVal=='已租' ){            
            $('#total_min').val('').trigger('change');
            $('#total_max').val('').trigger('change');
        	$('#totalFilterDiv').hide();
        	$('#rentFilterDiv').show();
        }else{
            $('#rent_min').val('').trigger('change');
            $('#rent_max').val('').trigger('change');
        	$('#totalFilterDiv').show();
        	$('#rentFilterDiv').hide();
        }

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
		if(localStorage.getItem("status")){
			$("#status").val(localStorage.getItem("status")).trigger('change');  
		} 
		
		if(localStorage.getItem("region")){
			$("#region").val(localStorage.getItem("region")).trigger('change');  
		} 
	
		if(localStorage.getItem("type")){
			$("#type").val(localStorage.getItem("type")).trigger('change');  
		} 
			
		if(localStorage.getItem("area_min")){
			$("#area_min").val(localStorage.getItem("area_min")).trigger('keyup');  
		} 	
			
		if(localStorage.getItem("area_max")){
			$("#area_max").val(localStorage.getItem("area_max")).trigger('keyup');  
		} 				
		
		if(localStorage.getItem("rent_min")){
			$("#rent_min").val(localStorage.getItem("rent_min")).trigger('keyup');  
		} 	
		
		if(localStorage.getItem("rent_max")){
			$("#rent_max").val(localStorage.getItem("rent_max")).trigger('keyup');  
		} 				
		
		if(localStorage.getItem("fitler_building_no")){
			$("#fitler_building_no").val(localStorage.getItem("fitler_building_no")).trigger('keyup');  
		} 				
		
		if(localStorage.getItem("fitler_building_unit")){
			$("#fitler_building_unit").val(localStorage.getItem("fitler_building_unit")).trigger('keyup');  
		} 	
		
		if(localStorage.getItem("fitler_room_no")){
			$("#fitler_room_no").val(localStorage.getItem("fitler_room_no")).trigger('keyup');  
		} 				
	};
	
	initLocalStorage();
	
} );