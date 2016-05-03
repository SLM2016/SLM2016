app.controller('StudentImportController', ['$scope', '$state', '$timeout', '$rootScope', 'StudentInfoService', 
    function ($scope, $state, $timeout, $rootScope, StudentInfoService) {  

	    var fileChanged = function(files) {
	    	init();
		    $scope.excelFile = files;
		    StudentInfoService.readFile($scope.excelFile, $scope.showPreview, $scope.showJSONPreview)
		        .then(function (xlsxData) {
		        	$scope.sheets = xlsxData.sheets;
		            $scope.items = $scope.sheets[Object.keys($scope.sheets)[0]]
		            updateItems();
		        });
	    };
		
	    // cell data handle
	    var updateItems = function() {
		    $scope.terms = (Object.keys($scope.items[0]));
		    $scope.showData = $scope.items;
		    
	        for( arr1 = 0; arr1<=$scope.items.length; arr1++ ) {
			    for( arr2 =0; arr2<$scope.terms.length; arr2++ ) {
			       	$scope.showData[arr1][arr2] = $scope.items[arr1][$scope.terms[arr2]];
			       	delete $scope.showData[arr1][$scope.terms[arr2]];
			    }    
		    }
	    }
	   	    
	    // transfer data to server
		var uploadFile = function () {
			$scope.isUploading = true;
			StudentInfoService.transferFile($scope.excelFile).then(function(result) {
				$scope.isUploading = false;
				console.log(result.status)
				if(result.status) {
					$scope.isUploadSuccess = true;
				}
				else {
					$scope.isUploadFail = true;
				}
			},function(error) {
				$scope.isUploading = false;
				$scope.isUploadFail = true;
			});
		};

		var init = function() {
			$scope.isUploading = false;
	        $scope.isUploadSuccess = false;
	        $scope.isUploadFail = false;
	        $scope.showPreview = false; 
	        $scope.items = [];
	    	$scope.sheets = [];
	    	$scope.excelFile = undefined;
		}

		/*==========================
            Events
        ==========================*/

        /*==========================
            Members
        ==========================*/

        $scope.isUploading = false;
        $scope.isUploadSuccess = false;
        $scope.isUploadFail = false;
        $scope.showPreview = false; 
	    $scope.showJSONPreview = true; 
	    $scope.items = [];
	    $scope.sheets = [];

        /*==========================
             Methods
        ==========================*/

        $scope.fileChanged = fileChanged;
        $scope.updateItems = updateItems;
        $scope.uploadFile = uploadFile;

        /*==========================
             init
        ==========================*/

        init();
		
}]);