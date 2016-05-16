app.factory("StudentInfoService", [ '$q', '$rootScope', '$http', 'Upload',
	function($q, $rootScope, $http, Upload) {
		
		var factory = this;

		// 讀取Excel檔並轉成Json格式
        var readFile = function(file, readCells, toJSON) {
            var deferred = $q.defer();

            XLSXReader(file, readCells, toJSON, function(data) {
                $rootScope.$apply(function() {
                    deferred.resolve(data);
                });
            });
            return deferred.promise;
        }
        
        // 上傳學員資料並存入DB
		var uploadStudentFile = function (file, courseId) {
		    var defer = $q.defer();
				
		    Upload.upload({
		        url: '/SLM2016/StudentAction',
		        withCredential: true,
		        data: {
		            op: 1,
                    courseId: courseId
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

		// 取得學員列表
		var getStudentList = function() {
            var defer = $q.defer();

            $http({
                url: "/SLM2016/StudentAction",
                method: "GET",
                params: {
                    op: 2
                }
            }).success(function(data) {
                defer.resolve(data);
            }).error(function(data, status, headers, config) {
                console.error("status : " + status);
            });

            return defer.promise;
        }

        var getStudentListByCourseId = function(courseId) {
            var defer = $q.defer();

            $http({
                url: "/SLM2016/StudentAction",
                method: "GET",
                params: {
                    op: 5,
                    courseId: courseId
                }
            }).success(function(data) {
                defer.resolve(data);
            }).error(function(data, status, headers, config) {
                console.error("status : " + status);
            });

            return defer.promise;
        }

		/*==========================
            Members
        ==========================*/

        /*==========================
            Methods
        ==========================*/

        factory.readFile = readFile;
        factory.uploadStudentFile = uploadStudentFile;
        factory.getStudentList = getStudentList;
        factory.getStudentListByCourseId = getStudentListByCourseId;
        
        /*==========================
            init
        ==========================*/

        return factory;
    }
]);