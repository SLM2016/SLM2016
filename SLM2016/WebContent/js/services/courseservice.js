app.factory('CourseService', ['$q', '$http', '$timeout', '$rootScope',
    function($q, $http, $timeout, $rootScope){
		
		var factory = this;

        var getCourseList = function() {
            var defer = $q.defer();

            $http({
                url: "/SLM2016/CourseManagerServlet",
                method: "GET"
            }).success(function(data) {
                defer.resolve(data);
            }).error(function(data, status, headers, config) {
                console.error("status : " + status);
            });

            return defer.promise;
        }

        var getCourseSimpleList = function() {
            var defer = $q.defer();

            $http({
                url: "/SLM2016/CourseManagerServlet",
                method: "GET",
                headers: {
                    simpleData: true
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

        factory.getCourseList = getCourseList;
        factory.getCourseSimpleList = getCourseSimpleList;

        /*==========================
            init
        ==========================*/

        return factory;
    }
])