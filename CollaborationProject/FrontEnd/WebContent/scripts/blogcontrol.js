/**
 * 
 */



var BlogModule=angular.module('BlogModule',[]);
BlogModule.controller('BlogController',function(BlogServices,$cookieStore,$rootScope){
	this.message="This a blog";
	this.blog={};
	var blogCtrl=this;
	$rootScope.currentUser=$cookieStore.get('currentUser');
	blogCtrl.submitBlog=function()
	{
		console.log(blogCtrl.blog);
		BlogServices.submitBlog(blogCtrl.blog,$rootScope.currentUser.userid).then
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
	
	this.allBlogs=function()
	{
		BlogServices.allBlogs().then
		(
				function(success)
				{
					console.log(success);
					$cookiestore.put('allBlogs',success.data);
					$rootScope.allBlogs=$cookieStore.get('allBlogs');
				},
				function(error)
				{
					console.log(error);
				}
		)
	}
	
	
	this.allBlogs();
	this.decision=function(decision,blogId)
	{
		console.log(decision+" "+blogId);
		BlogServices.decision(decision,blogId).then
		(
				function(error)
				{
					console.log(error);
				}
				)
	}
	
})


BlogModule.service('BlogServices',function(REST_URI,$http,$q)
		{
		this.submitBlog=function(blog,userid)
		{
			var deffered=$q.defer();
			$http.post(REST_URI+"blog/"+userid,blog).then
			(
			
					function(response)
					{
						console.log(response);
						deffered.resolve(response);
					},function(error)
					{
						console.log(error);
						deffered.reject(error);
					}
			);
			return deffered.promise;
		}
		})