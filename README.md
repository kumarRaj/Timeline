# Timeline


TimeLine

23rd Usable - Usable

Timeline which is like Feed.
Infinite scroll facility.
Like and comment button
Should be able to see your post.
See friends timeline.
Notification feature -> when your friend posts, post comment and like.
You cannot tag.
Search box -> search post on the basis of text. Should work on all timelines.
Login/SignUp feature.
Host -> Able to access it from anywhere.

Login -> See your own timeline.
On clicking on a friend should see friend's timeline.
Search and Follow friend.
Create Post page for empty timeline.
New Post Page.


Technical:
Spring Boot
ReactJS
Redux
MySQL 

Consideration:
Infinite scroll feature will be only present for the loggedIn person timeline.
If anyone likes your post you cannot see the people who liked your post.



Class:
User
    - String firstname
    - String lastname
    - String emailID
    - String phoneNumber
    - Date dateOfBirth

Connections
    - User user
    - List<User> followers
    - List<User> following

Post
    - Integer id
    - User createdBy
    - Date creationTime
    - Date modifiedTime
    - String body
    - List<Photo> photos
    - List<Comment> comments
    - Integer likes

SearchCriteria 
    - key(property name: person and post)
    - value

Comment
    - Integer id
    - String comment

Photo
    - Integer id
    - String path

REST APIs:
/signUp
/login
/user/:id/timeline




Request Response Details:
/signUp[POST]
Request : 
{
    firstname:
    lastname:
    password:
    emailID:
    phoneNumber:
    dateOfBirth:
}
Response :
{
    accountCreation: successful,
    error:{
        id: 
        message: 
    }
}

Request Response Details:
/login[POST]
Request : 
{
    username:
    password:
}
Response :
{
    login: successful,
    token: 
    error:{
        id: 
        message: 
    }
}
    









