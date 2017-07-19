/**
 * 
 */


var loginapp=angular.module('loginapp',[]);
loginapp.controller('LoginController',function(LoginService,$location,$rootScope,$cookies,$cookieStore){
	
	this.message='this is login controller';
	var logCtrl=this;
	logCtrl.user={};
	
	this.logintest=function()
	{
		alert('helooo')
		console.log(logCtrl.user);
		LoginService.login(logCtrl.user).then
		(
			function(response)
			{
				console.log(response);
				$cookieStore.put('currentUser',response.data);
				if(response.data.role=='Admin')
					{
					console.log("admin part")
					$cookies.put('role',response.data.role);
					
					$rootScope.Userrole=$cookies.get('role');
					}
				else if(response.data.role=='student')
					{
					console.log("student part")
					$cookies.put('role',response.data.role);
					$rootScope.Userrole=$cookies.get('role');
					
					}
				if(response.status="200")
					{
					console.log('status 200')
					$location.path("/");
					}else
						{
						$location.path("/");
						}
			},
			function(error)
			{
				console.log(error);
			}
		)
		
	}
});

loginapp.service('LoginService',function($http,REST_URI,$q){
	
	this.login=function(user)
	{
		console.log(user);
		var deffered=$q.defer();
		
		$http.post(REST_URI+'login/',user).then(
				function(response)
				{
					console.log(response);
					deffered.resolve(response);
				},function(error)
				{
					console.log(error);
					deffered.reject(error);
				}
		)
		
		
		
	return deffered.promise;
	}
	
})
