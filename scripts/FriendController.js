var FriendModule=angular.module('FriendModule',[]);

FriendModule.controller('FriendController',function(FriendServices,$rootScope,$cookies,$cookieStore)
{
    this.message="this is friend controller";
  var  frndCtrl=this;
   this.tab='suggested'
    this.allUsers={}
    frndCtrl.friendShip={};
    frndCtrl.currentUser=$cookieStore.get("currentUser");
    this.suggestedFriends=function(userId)
    {
    	 console.log(userId);
         FriendServices.suggestedFriends(userId).then
         (
             function(success)
             {
                 console.log("suggested friends::::"+success);
                 $cookieStore.put('allUsers',success.data);
                 frndCtrl.allUsers=success.data;

             },
             function(error)
             {
                 console.log(error);
             }
         )
    }

     this.getFriends=function()
     {
        FriendServices.getFriends(frndCtrl.currentUser).then(

            function(response)
            {
                console.log("friends:::::"+response);
                frndCtrl.friends=response.data;

            },
            function(error)
            {
                console.log(error);
            }
        )
     }

     this.getFriendRequests=function()
     {
          FriendServices.getFriendRequests(frndCtrl.currentUser.userid).then(
              function(response)
              {
                  console.log("friends Requests:::::::::::::::"+response.data);
                  frndCtrl.frndRequests=response.data;
              },
              function(error)
              {
                  console.log(error);
              }
          )
     }

    this.suggestedFriends(frndCtrl.currentUser.userid);
    this.getFriends();
    this.getFriendRequests();

    this.addFriend=function(friendid)
    {
        alert("fid"+friendid);
       alert("uid"+ frndCtrl.currentUser.userid)
       
       angular.forEach(frndCtrl.allUsers,function(value,key)
       {
           console.log(value.userId==friendid)
           if(value.userid==friendid)
           {
               frndCtrl.friend=value;
           }
       });
       frndCtrl.friendShip.userId=frndCtrl.currentUser.userid;
       frndCtrl.friendShip.friendId=friendid;
       
       console.log("friendship object"+frndCtrl.friendShip);
       
       FriendServices.addFriend(frndCtrl.friendShip).then(

           function(response)
           {
               console.log(response);
               frndCtrl.allUsers=response.data;
               frndCtrl.suggestedFriends(frndCtrl.currentUser.userid);
               
           },function(error)
           {
               console.log(error);
           }
       )
       
    }

    this.selectTab=function(tabvalue)
    {
        frndCtrl.tab=tabvalue;
    }

   

    this.updateStatus=function(friendId,status)
    {
        console.log(friendId+"   "+status);

        FriendServices.updateStatus(frndCtrl.currentUser.userid,friendId,status).then(
            function(response)
            {
                console.log(response);
                frndCtrl.frndRequests=response.data;
                frndCtrl.getFriends();
                frndCtrl.getFriendRequests();
            },
            function(error)
            {
                console.log(error);
            }
        )
    }
})
//service
FriendModule.service('FriendServices',function($http,$q,REST_URI)
{
    this.suggestedFriends=function(userId)
    {
        var deffered=$q.defer();
        $http.get(REST_URI+"suggestedFriends/"+userId).then
        (
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
 
    this.getFriendRequests=function(userId)
    {
        var deffered=$q.defer();
        $http.post(REST_URI+"getFriendrequests",userId).then(
            function(response)
            {
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

    this.getFriends=function(user)
    {
        var deffered=$q.defer();
        $http.post(REST_URI+"getfriends",user)
       .then(
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
    this.addFriend=function(friendShip)
    {
        console.log("friend id  "+friendShip.friendId);
        console.log("user id  "+friendShip.userId);
        var deffered=$q.defer();
        $http.post(REST_URI+"addfriend",friendShip).then
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
        )
        return deffered.promise;
    }

    this.updateStatus=function(friendId,userId,status)
    {
        var deffered=$q.defer();
        $http.post(REST_URI+'updateStatus/'+friendId+'/'+userId,status).then(
            function(response)
            {
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
})
