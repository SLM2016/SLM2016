app.directive('invoiceModel', ['$rootScope',
    function($rootScope) {
        return {
            restrict: "E",
            scope: true,
            templateUrl: "templates/directives/invoice-model.html",
            link: function(scope, element, attrs) {},
            controller: ['$scope', '$state', '$timeout', function($scope, $state, $timeout) {

                var setTodayString = function() {
                    var today = new Date();
                    var dd = today.getDate();
                    var mm = today.getMonth() + 1;
                    var yyyy = today.getFullYear() - 1911;
                    $scope.today = "中華民國 " + yyyy + " 年 " + mm + " 月 " + dd + " 日";
                    $scope.todayDD = dd;
                    $scope.todayMM = mm;
                    $scope.todayYY = yyyy;
                    var dateWord = ['O','一','二','三','四','五','六','七','八','九','十','十一','十二'];
                    if(mm == 1 || mm == 2) {
                        $scope.invoiceMonth = dateWord[1] + "、" + dateWord[2];
                    }
                    else if(mm == 3 || mm == 4) {
                        $scope.invoiceMonth = dateWord[3] + "、" + dateWord[4];
                    }
                    else if(mm == 5 || mm == 6) {
                        $scope.invoiceMonth = dateWord[5] + "、" + dateWord[6];
                    }
                    else if(mm == 7 || mm == 8) {
                        $scope.invoiceMonth = dateWord[7] + "、" + dateWord[8];
                    }
                    else if(mm == 9 || mm == 10) {
                        $scope.invoiceMonth = dateWord[9] + "、" + dateWord[10];
                    }
                    else if(mm == 11 || mm == 12) {
                        $scope.invoiceMonth = dateWord[11] + "、" + dateWord[12];
                    }
                    var yyArray = yyyy.toString().split("");
                    $scope.invoiceYear = dateWord[yyArray[0]]  + " " + dateWord[yyArray[1]] + " " + dateWord[yyArray[2]];
                }

                var onDateTimeChange = function() {
                    var dd = $scope.data.time.getDate();
                    var mm = $scope.data.time.getMonth() + 1;
                    var yyyy = $scope.data.time.getFullYear() - 1911;
                    $scope.todayDD = dd;
                    $scope.todayMM = mm;
                    $scope.todayYY = yyyy;
                    var dateWord = ['O','一','二','三','四','五','六','七','八','九','十','十一','十二'];
                    if(mm == 1 || mm == 2) {
                        $scope.invoiceMonth = dateWord[1] + "、" + dateWord[2];
                    }
                    else if(mm == 3 || mm == 4) {
                        $scope.invoiceMonth = dateWord[3] + "、" + dateWord[4];
                    }
                    else if(mm == 5 || mm == 6) {
                        $scope.invoiceMonth = dateWord[5] + "、" + dateWord[6];
                    }
                    else if(mm == 7 || mm == 8) {
                        $scope.invoiceMonth = dateWord[7] + "、" + dateWord[8];
                    }
                    else if(mm == 9 || mm == 10) {
                        $scope.invoiceMonth = dateWord[9] + "、" + dateWord[10];
                    }
                    else if(mm == 11 || mm == 12) {
                        $scope.invoiceMonth = dateWord[11] + "、" + dateWord[12];
                    }
                    var yyArray = yyyy.toString().split("");
                    $scope.invoiceYear = dateWord[yyArray[0]]  + " " + dateWord[yyArray[1]] + " " + dateWord[yyArray[2]];
                }

                var openDatePicker = function() {
                    $scope.isDatePickerOpen = true;
                }

                var init = function() {
                    setTodayString();
                }

                /*==========================
                    Events
                ==========================*/

                $scope.$on("OPEN_INVOICE_MODEL", function(event, data) {
                    console.log(data)
                    $scope.studentList = data.list;
                    $scope.currentIndex = index;
                })

                /*==========================
                    Members
                ==========================*/
                $scope.studentList = [];
                $scope.currentIndex;
                $scope.invoiceType = "THREE";

                $scope.today;
                $scope.todayDD = "";
                $scope.todayMM = "";
                $scope.todayYY = ""
                $scope.invoiceMonth = "";
                $scope.invoiceYear = ""
                $scope.isResultShow = false;
                $scope.data = {
                    company: "",
                    companyid: "",
                    companyidArray: [],
                    salesDollar: undefined,
                    taxRate: 5,
                    businessTax: undefined,
                    totalDollar: undefined,
                    itemName: "教育訓練",
                    itemNumber: 1,
                    itemDollar: undefined,
                    itemTotalDollar: undefined,
                    time: new Date()
                }
                $scope.totalWord = [{
                    numberWord: "",
                    unit: "億"
                }, {
                    numberWord: "",
                    unit: "仟"
                }, {
                    numberWord: "",
                    unit: "佰"
                }, {
                    numberWord: "",
                    unit: "拾"
                }, {
                    numberWord: "",
                    unit: "萬"
                }, {
                    numberWord: "",
                    unit: "仟"
                }, {
                    numberWord: "",
                    unit: "佰"
                }, {
                    numberWord: "",
                    unit: "拾"
                }, {
                    numberWord: "",
                    unit: "元"
                }]

                $scope.format = 'yyyy 年 MM 月 dd 日';
                $scope.isDatePickerOpen = false;
                $scope.dateOptions = {
                    locale: 'ru'
                };

                /*==========================
                    Methods
                ==========================*/

                $scope.openDatePicker = openDatePicker;
                $scope.onDateTimeChange = onDateTimeChange;
                
                /*==========================
                    init
                ==========================*/

                init();
            }]
        }
    }
]);
