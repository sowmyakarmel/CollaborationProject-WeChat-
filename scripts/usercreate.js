/**
 * 
 */


var regapp=angular.module('regmodule',[]);

regapp.controller('RegisterController',function(RegisterService){
	
	this.message='this is reg controller';
	var regCtrl=this;
	regCtrl.user={};
	this.regUser=function()
	{
		alert('heolooo')
		console.log(regCtrl.user);
		RegisterService.registerUser(regCtrl.user).then
		(
			function(response)
			{
				console.log(response);
			},
			function(error)
			{
				console.log(error);
			}
		)
		
	}
});


regapp.service('RegisterService',function($http,REST_URI,$q)
		{
	
	this.registerUser=function(user)
	{
		console.log(user);
		var deffered=$q.defer();
		
		$http.post(REST_URI+'usersave/',user).then(
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
