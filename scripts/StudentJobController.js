/**
 * 
 */
var StudentJobModule=angular.module('StudentJobModule',[]);

StudentJobModule.controller('StudentJobController',function($rootScope,StdJobServices,$location,$cookieStore)
{
     this.message="this is Student Job controller";	
     var stdJobCtrl=this;
     this.currentUser=$cookieStore.get("currentUser");
     console.log(stdJobCtrl.currentUser);
     this.allJobs=function(userId)
     {
    	 StdJobServices.allJobs(userId).then
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
     
     this.appliedJobs= function(userId)
     {
    	 StdJobServices.appliedJobs(userId).then
    	 (
    	     function(success)
    	     {
    	    	 stdJobCtrl.appliedJobs=success.data;
    	     },
    	     function(error){
    	    	 console.log(error);
    	    	 
    	    	 
    	     }
    	 )
     }
     
     
     this.allJobs(stdJobCtrl.currentUser.userid);
     this.appliedJobs(stdJobCtrl.currentUser.userid)
     
     this.applyJob=function(jobId)
     {
     	
     	var jobApplicants=
     		{
     			"userId":stdJobCtrl.currentUser.userid,
     			"jobId":jobId,
     		
     		}
     	
     	console.log(jobApplicants);
     	StdJobServices.applyJob(jobApplicants).then
     	(
     		function(response) {
     			console.log(response.data);
     			stdJobCtrl.allJobs=response.data;
			},
			function(error)
			{
				
			}
     	)
     }
     this.appliedJobsbtn=function()
     {
    	 $location.path('/appliedJobs');
     }
     
     
     this.allJobsbtn=function()
     {
    	 $location.path('/jobs');
     }
		
}		
);





StudentJobModule.service('StdJobServices',function($http,$q,REST_URI)
{
  this.allJobs=function(userId)
  {
	  var deffered=$q.defer();
	  
	  $http.get(REST_URI+'alljobs/'+userId).then
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
  
  this.applyJob=function(jobApplicants)
  {
	  console.log(jobApplicants);
	  var deffered=$q.defer();
	  
	  $http.post(REST_URI+"applyJob",jobApplicants).
	  then(
			  function(response) {
				  console.log(response);
				  deffered.resolve(response);
				
			},
			function(error)
			{
				console.log(error);
				deffered.reject(error);
				
			}
		)
		
		return deffered.promise;
  }
  
  this.appliedJobs=function(userId)
  {
	 var deffered=$q.defer();
	 $http.get(REST_URI+"appliedJobs/"+userId).
	 then(
	      function(success)
	      {
	    	  console.log(success);
	    	  deffered.resolve(success);
	      },
	      function(error)
	      {
	    	  console.log(error);
	    	  deffered.reject(error);
	    	  
	    	  
	      }
	      
	 )
	 return deffered.promise;
	 
  }
}		
)