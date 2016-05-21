app.factory("StudentInfoService", [ '$q', '$rootScope', '$http', 'Upload',
	function($q, $rootScope, $http, Upload) {
		
		var factory = this;
		var studentSendmailDataArray;

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
		
		// 取得寄出郵件資料
		var getSendMailInfo = function(mailData) {
            var defer = $q.defer();

            $http({
                url: "/SLM2016/StudentAction",
                method: "GET",
                params: {
                    op: 7,
                    mailData: mailData
                }
            }).success(function(courseData) {
                defer.resolve(courseData);
            }).error(function(courseData, status, headers, config) {
                console.error("status : " + status);
            });

            return defer.promise;
        }
		
		var getCertificationInfo = function(studentId) {
            var defer = $q.defer();

            $http({
                url: "/SLM2016/StudentAction",
                method: "GET",
                params: {
                    op: 8,
                    studentId: studentId
                }
            }).success(function(certificationData) {
                defer.resolve(certificationData);
            }).error(function(certificationData, status, headers, config) {
                console.error("status : " + status);
            });

            return defer.promise;
        }
		
		var putStudentSendMailData = function(data){
			studentSendmailDataArray = data;
		}
		
		var getStudentSendMailData = function(){
			return JSON.stringify(studentSendmailDataArray);
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

        var saveStudentFile = function(file, courseId) {
            var defer = $q.defer();
                
            Upload.upload({
                url: '/SLM2016/StudentAction',
                withCredential: true,
                data: {
                    op: 4,
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
        }

        var updateStudentReceiptStatus = function(receiptNumber,payStatus,receiptStatus,studentId)
        {

            var defer = $q.defer();

            $http({
                url: "/SLM2016/StudentAction",
                method: "POST",
                params: {
                    op: 6,
                    studentId: studentId,
                    receiptEIN: receiptNumber,
                    receiptStatus: receiptStatus,
                    paymentStatus: payStatus
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
        factory.getSendMailInfo = getSendMailInfo;
        factory.putStudentSendMailData = putStudentSendMailData;
        factory.getStudentSendMailData = getStudentSendMailData;
        factory.getStudentListByCourseId = getStudentListByCourseId;
        factory.saveStudentFile = saveStudentFile;
        factory.updateStudentReceiptStatus = updateStudentReceiptStatus;
        factory.getCertificationInfo = getCertificationInfo;       
        /*==========================
            init
        ==========================*/

        return factory;
    }
]);