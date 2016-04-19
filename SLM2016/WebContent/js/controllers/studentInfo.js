app.controller('StudentInfoController', ['StudentInfoService', 
    function (StudentInfoService) {
	    var si = this;
	    si.showPreview = false; 
	    si.showJSONPreview = true; 
	    si.items = [];
	    si.sheets = [];

	    si.fileChanged = function(files) {
	    si.sheets = []; 
	    si.excelFile = files[0];
	    //console.log(si.excelFile);
	    StudentInfoService.readFile(si.excelFile, si.showPreview, si.showJSONPreview)
	        .then(function (xlsxData) {
	        	si.sheets = xlsxData.sheets;
	            console.log(si.sheets);
	        });
	    };
		
	    // cell data handle
	    si.updateItems = function() {
		    si.items = si.sheets[si.selectedSheetName];
		    si.terms = (Object.keys(si.items[0]));
		    si.showData = si.items;
		    
	        for( arr1 = 0; arr1<=si.items.length; arr1++ ) {
			    for( arr2 =0; arr2<si.terms.length; arr2++ ) {
			       	si.showData[arr1][arr2] = si.items[arr1][si.terms[arr2]];
			       	delete si.showData[arr1][si.terms[arr2]];
			    }    
		    }
	    }
	   	    
	    // transfer data to server
		si.ok = function () {
			StudentInfoService.transferFile(si.excelFile).then(
					function(result) {
						console.log(result);
					});
		};
		
}]);