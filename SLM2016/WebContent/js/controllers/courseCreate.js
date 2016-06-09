app.controller("CourseCreateController", ['$scope', '$state', '$timeout', '$rootScope', '$q',
    function($scope, $state, $timeout, $rootScope, $q, UploadAttachmentService) {
        var vm = this;
        var showCcList = [];
        var ticketTypeList = [];
        var ticketPriceList = [];
        var showticketList = [];
        var updateticketsList = [];
        var showDateList = [];

        var courseNameSelected = '';
        var locationSelected = '';
        var ccAddressSelected = '';
        var typeSelected = '';
        var ticketTypeSelected = '';

        //set CourseName dropdownList
        this.selectedDropdownCourseNameItem = null;
        this.dropdownCourseNameItems = ['Scrum敏捷方法實作班', '看板方法與精實開發實作班', '軟體重構入門實作班', '敏捷產品經理實作班',
            '敏捷大師百寶箱系列課程', 'Design Patterns入門實作班', 'Design Patterns進階實作班',
            '單元測試與持續整合實作班', '例外處理設計與重構實作班'
        ];

        this.filterCourseNameList = function(userInput) {
            courseNameSelected = userInput;
            var filter = $q.defer();
            //var normalisedInput = userInput.toLowerCase();
            var filteredArray = this.dropdownCourseNameItems.filter(function(type) {
                return type.toLowerCase().indexOf('') == 0;
            });
            filter.resolve(filteredArray);
            return filter.promise;
        };

        this.itemCourseNameSelected = function(item) {
            courseNameSelected = item;
        };

        //set Type dropdownList
        this.selectedDropdownTypeItem = null;
        this.dropdownTypeItems = ['公開班', '企業內訓', '演講'];

        this.filterTypeList = function(userInput) {
            typeSelected = userInput;
            var filter = $q.defer();
            //var normalisedInput = userInput.toLowerCase();
            var filteredArray = this.dropdownTypeItems.filter(function(type) {
                return type.toLowerCase().indexOf('') == 0;
            });
            filter.resolve(filteredArray);
            return filter.promise;
        };

        this.itemTypeSelected = function(item) {
            typeSelected = item;
        };

        //set TicketType dropdownList
        this.selectedDropdownTicketTypeItem = null;
        this.dropdownTicketTypeItems = ['原價', '早鳥', '泰迪之友', '四人團報'];

        this.filterTicketTypeList = function(userInput) {
            ticketTicketTypeSelected = userInput;
            var filter = $q.defer();
            //var normalisedInput = userInput.toLowerCase();
            var filteredArray = this.dropdownTicketTypeItems.filter(function(ticketType) {
                return ticketType.toLowerCase().indexOf('') == 0;
            });
            filter.resolve(filteredArray);
            return filter.promise;
        };

        this.itemTicketTypeSelected = function(item) {
            ticketTicketTypeSelected = item;
        };

        //set CcAddress dropdownList
        this.selectedDropdownCcAddressItem = null;
        this.dropdownCcAddressItems = ['service@teddysoft.tw', 'teddy@teddysoft.tw', 'erica@teddysoft.tw'];

        this.filterCcAddressList = function(userInput) {
            ccAddressSelected = userInput;
            var filter = $q.defer();
            //var normalisedInput = userInput.toLowerCase();
            var filteredArray = this.dropdownCcAddressItems.filter(function(type) {
                return type.toLowerCase().indexOf('') == 0;
            });
            filter.resolve(filteredArray);
            return filter.promise;
        };

        this.itemCcAddressSelected = function(item) {
            ccAddressSelected = item;
        };

        //set Location dropdownList
        this.selectedDropdownLocationItem = null;
        this.dropdownLocationItems = ['延平南路12號4樓', '北科科研1622室', '北科育成305室', '北科育成201室'];

        this.filterLocationList = function(userInput) {
            locationSelected = userInput;
            var filter = $q.defer();
            //var normalisedInput = userInput.toLowerCase();
            var filteredArray = this.dropdownLocationItems.filter(function(type) {
                return type.toLowerCase().indexOf('') == 0;
            });
            filter.resolve(filteredArray);
            return filter.promise;
        };

        this.dropdownStatusItems = ['準備中', '報名中', '取消', '確定開課', '停止報名', '上課中', '課程結束'];
        this.selectedDropdownStatusItem = this.dropdownStatusItems[0];

        var changeloadType = function(type) {
            $scope.loadType = type;
        }

        function addCourse() {
            var data = new Object();
            data.courseName_ = courseNameSelected;
            data.batch_ = $scope.data.batch;
            data.dates_ = $scope.showDateList;
            data.duration_ = parseInt($scope.data.duration);
            data.ticketTypes_ = $scope.ticketTypeList;
            data.prices_ = $scope.ticketPriceList;
            data.location_ = locationSelected;
            data.lecturer_ = $scope.data.lecturer;
            data.hyperlink_ = $scope.data.hyperlink;
            data.ccAddresses_ = $scope.showCcList;
            data.type_ = typeSelected;
            data.status_ = vm.selectedDropdownStatusItem;
            $.post("/SLM2016/CourseManagerServlet",
                JSON.stringify(data)).done(function(data) {
                if (data == "Success") {
                    window.alert("開課成功");
                } else {
                    window.alert("開課失敗\n" + data);
                }
                resetAllDropdownList();
                deleteData();
            });
            setTimeout(function() {
                $scope.$apply(function() {
                    $scope.time = new Date();
                });
            }, 500);
        }

        var clickAddTicketButton = function() {
            var regex = /^[a-zA-Z]+$/;
            if (((ticketTicketTypeSelected) == null) || (($scope.data.price) == null) || (($scope.data.price.length) == 0) | ((ticketTicketTypeSelected.length) == 0)) {
                window.alert("課程票價或票種請修正");
            } else {
                if ((($scope.data.price).match(regex))) {
                    window.alert("票價需要為數字");
                } else {
                    if ((($scope.data.price) > 2147483647) || (($scope.data.price) < 0)) {
                        window.alert("課程金額過大");
                    } else {
                        $scope.ticketTypeList.push(ticketTicketTypeSelected);
                        $scope.ticketPriceList.push($scope.data.price);
                        $scope.showticketList.push(ticketTicketTypeSelected + "  $" + $scope.data.price);
                        ticketTicketTypeSelected = null;
                        $scope.data.price = null;
                    }
                }
            }
        }

        var clickAddCcButton = function() {
            var lastAtPos = (ccAddressSelected).lastIndexOf('@');
            var lastDotPos = (ccAddressSelected).lastIndexOf('.');
            if (((ccAddressSelected) == null) || ((ccAddressSelected) == 0)) {
                window.alert("請輸入副本收件者地址");
            } else {
                if (lastAtPos < lastDotPos && lastAtPos > 0 && (ccAddressSelected).indexOf('@@') == -1 && lastDotPos > 2 && ((ccAddressSelected).length - lastDotPos) > 2) {
                    $scope.showCcList.push(ccAddressSelected);
                    ccAddressSelected = null;
                } else {
                    window.alert("副本收件者地址請修正");
                }
            }
        }

        var clickAddDateButton = function() {
            if ((($scope.data.dateAddresses) == null)) {
                window.alert("請輸入日期");
            } else {
                if ((($scope.data.dateAddresses.length) != 0)) {
                    $scope.data.dateAddresses.setMinutes($scope.data.dateAddresses.getMinutes() - $scope.data.dateAddresses.getTimezoneOffset());
                    $scope.showDateList.push($scope.data.dateAddresses.toISOString().substring(0, 10));
                    $scope.data.dateAddresses = null;
                } else {
                    window.alert("請輸入日期");
                }
            }
        }

        function clickAddCourseButton() {
            if (confirm("是否確認開課!?") == true) {
                checkInput();
            } else {

            }
        }

        function resetAllDropdownList() {
            vm.selectedDropdownStatusItem = vm.dropdownStatusItems[0];
            vm.selectedDropdownCourseNameItem = { readableName: "" };
            vm.selectedDropdownTypeItem = { readableName: "" };
            vm.selectedDropdownTicketTypeItem = { readableName: "" };
            vm.selectedDropdownCcAddressItem = { readableName: "" };
            vm.selectedDropdownLocationItem = { readableName: "" };
        }

        function checkInput() {
            if (((courseNameSelected) == null)) {
                window.alert("課程名稱欄位不可為空白");
                return;
            }
            if (((vm.selectedDropdownStatusItem) == null)) {
                window.alert("狀態欄位不可為空白");
                return;
            }
            if (((courseNameSelected) == 0)) {
                window.alert("課程名稱欄位不可為空白");
                return;
            }
            if (((vm.selectedDropdownStatusItem) == 0)) {
                window.alert("狀態欄位不正確");
                return;
            }
            if ((($scope.data.batch) == null) || (($scope.data.batch).length == 0)) {
                window.alert("梯次欄位不正確");
                return;
            }
            var regex = /^\d+/;
            if ((($scope.data.duration) != null)) {
                var x = $scope.data.duration;
                if (!(String(x).match(regex))) {
                    window.alert("課程時間需要為數字");
                    return;
                }
            }
            addCourse();
        }

        var deleteTicket = function(index) {
            $scope.showticketList.splice(index, '1');
            $scope.ticketTypeList.splice(index, '1');
            $scope.ticketPriceList.splice(index, '1');

        }

        var deleteDate = function(index) {
            $scope.showDateList.splice(index, '1');
        }

        var deleteCc = function(index) {
            $scope.showCcList.splice(index, '1');
        }

        var fileChanged = function(files) {
            $scope.fileName = files;
        };

        var openDatePicker = function() {
            $scope.isDatePickerOpen = true;
        }

        var deleteData = function() {
            $scope.data.batch = "";
            $scope.showDateList = [];
            $scope.data.duration = 0;
            $scope.ticketTypeList = [];
            $scope.ticketPriceList = [];
            $scope.data.lecturer = "";
            $scope.data.hyperlink = "";
            $scope.showCcList = [];
            $scope.showticketList = [];
            document.forms['courseName'].reset();
            document.forms['type'].reset();
            document.forms['ticketType'].reset();
            document.forms['location'].reset();
            document.forms['status'].reset();
            document.forms['ccAddress'].reset();
            courseNameSelected = '';
            locationSelected = '';
            ccAddressSelected = '';
            typeSelected = '';
            ticketTypeSelected = '';
        }

        var init = function() {
            $scope.data = {
                batch: "",
                duration: 0,
                lecturer: "",
                hyperlink: ""
            };
            typeSelected = "";
        }

        /*
         * ========================== Events ==========================
         */

        /*
         * ========================== Members ==========================
         */
        $scope.loadType = "Upload";
        $scope.courseList = [];
        $scope.ticketTypeList = [];
        $scope.ticketPriceList = [];
        $scope.showticketList = [];
        $scope.showCcList = [];
        $scope.showDateList = [];
        $scope.updateticketsList = [];
        $scope.isDatePickerOpen = false;
        $scope.format = 'yyyy 年 MM 月 dd 日';
        $scope.dateOptions = {
            locale: 'ru'
        };
        /*
         * ========================== Methods ==========================
         */

        /*
         * ========================== init ==========================
         */

        $scope.selectedValue;
        $scope.fileChanged = fileChanged;
        $scope.clickAddCourseButton = clickAddCourseButton;
        $scope.clickAddTicketButton = clickAddTicketButton;
        $scope.clickAddDateButton = clickAddDateButton;
        $scope.clickAddCcButton = clickAddCcButton;
        $scope.changeloadType = changeloadType;
        $scope.deleteTicket = deleteTicket;
        $scope.deleteDate = deleteDate;
        $scope.deleteCc = deleteCc;
        $scope.openDatePicker = openDatePicker;
        $scope.deleteData = deleteData;
        init();
    }
]);
