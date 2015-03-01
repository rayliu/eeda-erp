$.fn.dataTableExt.afnFiltering.push(
    function( oSettings, aData, iDataIndex ) {


    	if( isTypeInRange() && isAreaInRange() && isAmountInRange() ){
    		return true;
    	}
    	return false;

        function isTypeInRange(){
        	var typeVal = $("#type").val();
        	if('allDepartment'==typeVal){
        		var iCellValue = aData[4].substr(0, 1);
        		if(iCellValue<=6){
        			return true;
        		}
        		return false;
        	}        	
        	return true;        	
        };

    	function isAreaInRange(){
    		var iColumn = 6;

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
    		var iColumn = 7;
    		var range, iCellValue, iMin, iMax;
	        var statusVal = $("#status").val();

	        if(statusVal==''){
	        	return true;
	        }else if(statusVal=='求租' || statusVal=='已租'){
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
	var oTable = $('.datatable').dataTable({
		"sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
		"sPaginationType": "bootstrap",
		"oLanguage": {
			"sUrl": "/eeda/dataTables.ch.txt"
		},
		"aoColumnDefs": [
	      { "sWidth": "15%", "aTargets": [ 0 ] },
	      { "sWidth": "8%", "aTargets": [ 1 ] },
	      { "sWidth": "8%", "aTargets": [ 2 ] },
	      { "sWidth": "8%", "aTargets": [ 3 ] },
	      { "sWidth": "8%", "aTargets": [ 4 ] },
	      { "sWidth": "8%", "aTargets": [ 5 ] },
	      { "sWidth": "8%", "aTargets": [ 6 ] },
	      { "sWidth": "8%", "aTargets": [ 7 ] },
	      { "sWidth": "7%", "aTargets": [ 8 ] },
	      { "sWidth": "10%", "aTargets": [ 9 ] },
          { "sWidth": "15%", "aTargets": [ 10 ] }        
	    ],
	    "aaSorting": [[ 9, "desc" ]],
        "fnInitComplete": function(oSettings, json) {         
          if(window.location.search){
            initSearch();
          }else{
            $("#user").val(userId).trigger('change');
          }
        }
	} );
	/* //datatable, 动态处理
    $('#eeda-table').dataTable({
    	"oLanguage": {
            "sUrl": "dataTables.ch.txt"
        },
        "sPaginationType": "full_numbers",
        "bProcessing": true,
        "bServerSide": true,
        "sAjaxSource": "listLeads",
        "aoColumns": [   
        	{"mData":"TITLE"},
        	{"mData":"STATUS"},
            {"mData":"TYPE"},
            {"mData":"REGION"},
            {"mData":"INTRO"},
        	{"mData":"REMARK"},
            {"mData":"LOWEST_PRICE"},
            {"mData":"AGENT_FEE"},
            {"mData":"INTRODUCER"},
        	{"mData":"SALES"},
            {"mData":"FOLLOWER"},
            {"mData":"FOLLOWER_PHONE"},
            {"mData":"OWNER"},
            <% if(shiro.hasAnyRole("admin,property_mananger,property_internal_user")){ %>
        	{"mData":"OWNER_PHONE"},
        	<%}%>
        	{"mData":"CREATOR"},
            {"mData":"STATUS"},
            {"mData":"CREATE_DATE"}                        
        ],
        "aoColumnDefs": [ {
	      "aTargets": [ 0 ],
	      "mData": "download_link",
	      "mRender": function ( data, type, full ) {
	        return '<a href="'+data+'">Download</a>';
	      }
	    }]
    });*/

	var getFilterVal=function(){
		return $("#status").val()+' '+$("#type").val()+' '+$("#region").val();
	}
	var getQueryStringRegExp = eeda.getQueryStringRegExp;
    
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
            +'&priority='+$('#priority').val()
            +'&user='+$('#user').val()
            +'&area_min='+$('#area_min').val()
            +'&area_max='+$('#area_max').val()
            +'&rent_min='+$('#rent_min').val()
            +'&rent_max='+$('#rent_max').val()
            +'&total_min='+$('#total_min').val()
            +'&total_max='+$('#total_max').val()
    }
    
    var initSearch=function(){
		
		//var input_box = $('#eeda-table_filter input').first();
        //input_box.val('');
        
        $("#status").val(getQueryStringRegExp("status")).trigger('change');
        $("#type").val(getQueryStringRegExp("type")).trigger('change');
        $("#region").val(getQueryStringRegExp("region")).trigger('change');
        $("#priority").val(getQueryStringRegExp("priority")).trigger('change');
        $("#user").val(getQueryStringRegExp("user")).trigger('change');
        
        $('#area_min').val(getQueryStringRegExp("area_min")).trigger('change');
		$('#area_max').val(getQueryStringRegExp("area_max")).trigger('change');
		$('#rent_min').val(getQueryStringRegExp("rent_min")).trigger('change');
		$('#rent_max').val(getQueryStringRegExp("rent_max")).trigger('change');
		$('#total_min').val(getQueryStringRegExp("total_min")).trigger('change');
		$('#total_max').val(getQueryStringRegExp("total_max")).trigger('change');
        
    };
	$("#resetBtn").on("click", function(e){
		e.preventDefault();
		var input_box = $('#eeda-table_filter input').first();
        input_box.val('');

        $("#status").val('').trigger('change');
        $("#type").val('').trigger('change');
        $("#region").val('').trigger('change');
        $("#priority").val('').trigger('change');
        $("#user").val(userId).trigger('change');

        $('#area_min').val('').trigger('change');
		$('#area_max').val('').trigger('change');
		$('#rent_min').val('').trigger('change');
		$('#rent_max').val('').trigger('change');
		$('#total_min').val('').trigger('change');
		$('#total_max').val('').trigger('change');
        
        $('#totalFilterDiv').hide();
        $('#rentFilterDiv').hide();

        oTable.fnFilter('', null, false, true);
        
    });

	$("#status").on("change", function(){
        var typeVal = $(this).val();
        oTable.fnFilter(typeVal, 3, false, true);
        if(typeVal==''){
        	$('#totalFilterDiv').hide();
        	$('#rentFilterDiv').hide();
        }else if(typeVal=='求租' || typeVal=='已租' ){
        	$('#totalFilterDiv').hide();
        	$('#rentFilterDiv').show();
        }else{
        	$('#totalFilterDiv').show();
        	$('#rentFilterDiv').hide();
        }

    });

    $("#type").on("change", function(){
        var typeVal = $(this).val();
        if('allDepartment'!=typeVal){
        	oTable.fnFilter(typeVal, 4, false, true);
        }else{
        	oTable.fnFilter('', 4, false, true);
        	oTable.fnDraw(); 
        }        
    });

    $("#user").on("change", function(){
        var typeVal = $(this).val();
        oTable.fnFilter(typeVal, 8, false, true);
    });
    
    $("#region").on("change", function(){
        var typeVal = $(this).val();
        oTable.fnFilter(typeVal, 5, false, true);
    });

    $("#priority").on("change", function(){
        var typeVal = $(this).val();
        oTable.fnFilter(typeVal, 2, false, true);
    });

    /* Add event listeners to the two range filtering inputs */
	$('#area_min').on("keyup", function() {
		oTable.fnDraw(); 
	});
	$('#area_max').on("keyup", function() {
		oTable.fnDraw(); 
	});
	$('#rent_min').on("keyup", function() {
		oTable.fnDraw(); 
	});
	$('#rent_max').on("keyup", function() {
		oTable.fnDraw(); 
	});
	$('#total_min').on("keyup", function() {
		oTable.fnDraw(); 
	});
	$('#total_max').on("keyup", function() {
		oTable.fnDraw(); 
	});
	$('#fitler_building_no').on("keyup", function() {
		oTable.fnDraw(); 
	});
	$('#fitler_building_unit').on("keyup", function() {
		oTable.fnDraw(); 
	});
	$('#fitler_room_no').on("keyup", function() {
		oTable.fnDraw(); 
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
	
	
} );