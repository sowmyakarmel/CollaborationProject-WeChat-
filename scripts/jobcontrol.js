/**
 * 
 */

var JobModule = angular.module('JobModule', []);
JobModule.controller('JobController', function(JobServices, $location) {
	this.message = "This is Job Controller";
	this.job = {};
	var jobCtrl = this;
	this.jobPosting = function() {
		console.log(jobCtrl.job);
		JobServices.jobPosting(jobCtrl.job).then(

		function(response) {
			console.log(response);
			$location.path("/");
		}, function(error) {
			console.log(error);
		})
	}
})

JobModule.service('JobServices', function($http, $q, REST_URI) {
	this.jobPosting = function(job) {
		var deffered = $q.defer();
		$http.post(REST_URI + 'jobposting', job).then
		(
				function(response)
				{
					console.log(response);
					deffered.resolve(response);
				}, function(error) 
				{
					console.log(error);
					deffered.reject(error);
				});
		return deffered.promise;
	}
})