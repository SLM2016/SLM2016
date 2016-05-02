app.factory("UploadAttachmentService", [ '$q', '$rootScope', 'Upload',
		function($q, $rootScope, Upload) {
			var service = function(data) {
				angular.extend(this, data);
			}

        service.readFile = function(file, readCells, toJSON) {
            var deferred = $q.defer();

            XLSXReader(file, readCells, toJSON, function(data) {
                $rootScope.$apply(function() {
                    deferred.resolve(data);
                });
            });
            return deferred.promise;
        }
        
		service.transferFile = function (file) {
		    var defer = $q.defer();
				
		    Upload.upload({
		        url: '/SLM2016/StudentAction',
		        withCredential: true,
		        data: {
		            op: 1
		        },
		        file: file
		     }).success(function(file) {
		        	defer.resolve(file);
			 }).error(function(error) {
					defer.reject(error);
					console.error("status : " + status);
			 });
		     return defer.promise;
		   };
        return service;
    }
]);