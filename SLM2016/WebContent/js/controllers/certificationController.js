app.controller("CertificationController",['$scope', '$state', '$timeout', '$rootScope',
	function($scope, $state, $timeout, $rootScope){

		var ClickGenerateButton=function() {
		    var name = document.getElementById('studentName').value;
		    var id = document.getElementById('certificationId').value;
			var data=new Object();
			data.id_=id;
			data.owner_=name;
			console.log(name);
			
			$.post("/SLM2016/CertificationServlet",JSON.stringify(data))
			.done(function(data)
			{
				document.getElementById("someImg").setAttribute('src','data:image/png;base64,'+data);
			});
		}
        

        var init = function() {
        }

        $scope.ClickGenerateButton = ClickGenerateButton;
        /*==========================
            Events
        ==========================*/

        /*==========================
            Members
        ==========================*/

        /*==========================
             Methods
        ==========================*/

        /*==========================
             init
        ==========================*/

        init();
	}
]);