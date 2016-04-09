app.controller("InvoiceController",['$scope', '$state', '$timeout', '$rootScope',
	function($scope, $state, $timeout, $rootScope){

        var changeInvoiceType = function(type) {
            $scope.invoiceType = type;
        }

        var setTodayString = function() {
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth() + 1;
            var yyyy = today.getFullYear() - 1911;
            $scope.today = "中華民國 " + yyyy + " 年 " + mm + " 月 " + dd + " 日";
        }

        var showInvoiceResult = function() {
            $scope.isResultShow = true;
        }

        var clearInvoiceResult = function() {
        }

        var init = function() {
            setTodayString();
        }


        /*==========================
            Events
        ==========================*/

        /*==========================
            Members
        ==========================*/

        $scope.invoiceType = "THREE";
        $scope.today;
        $scope.isResultShow = true;
        /*==========================
             Methods
        ==========================*/

        $scope.changeInvoiceType = changeInvoiceType;
        $scope.showInvoiceResult = showInvoiceResult;
        $scope.clearInvoiceResult = clearInvoiceResult;

        /*==========================
             init
        ==========================*/

        init();

	}
]);