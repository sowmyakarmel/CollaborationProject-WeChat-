/**
 * 
 */


var StudentJobModule=angular.module('StudentJobModule',[]);

StudentJobModule.controller('StudentJobController',function($rootScope,StdJobServices,$location)
{
     this.message="this is Student Job controller";	
     var stdJobCtrl=this;
     
     this.allJobs=function()
     {
    	 StdJobServices.allJobs().then
    	 (
    	            function(success)
    	            {
    	            	console.log(success);
    	            	console.log(success.data);
    	            	angular.forEach(success.data,function(value,key){
    	            		console.log(value.dated);
    	            	})
    	            	stdJobCtrl.allJobs=success.data;
    	            },
    	            function(error)
    	            {
    	            	console.log(error);
    	            }
    	 )
     }
     
     
     this.allJobs();
     
     
     this.applyJob=function()
     {
    	 $location.path("/applyjob");
     }
}		
);





StudentJobModule.service('StdJobServices',function($http,$q,REST_URI)
{
  this.allJobs=function()
  {
	  var deffered=$q.defer();
	  
	  $http.get(REST_URI+'alljobs').then
	  (
		 function(success)
		 {
			 console.log(success);
			 deffered.resolve(success);
		 },
		 function(error)
		 {
			 deffered.reject(error);
		 }
	  )
	  
	  return deffered.promise;
  }
}		
)
