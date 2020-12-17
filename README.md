# Timeline


Deadline - 23rd Dec

- Timeline which is like Feed.
- Infinite scroll facility.
- Like and comment button
- Should be able to see your post.
- See friends timeline.
- Notification feature -> when your friend posts, post comment and like.
- You cannot tag.
- Search box -> search post on the basis of text. Should work on all timelines.
- Login/SignUp feature.
- Host -> Able to access it from anywhere.

## Flow 
- Login -> See your own timeline.
- On clicking on a friend should see friend's timeline.
- Search and Follow friend.
- Create Post page for empty timeline.
- New Post Page.


## Technical
- Spring Boot
- ReactJS
- Redux
- MySQL 

## Consideration
- Infinite scroll feature will be only present for the loggedIn person timeline.
- If anyone likes your post you cannot see the people who liked your post.




## Class
- User
    - String firstname
    - String lastname
    - String emailID
    - String phoneNumber
    - Date dateOfBirth

- Connections
    - User user
    - List<User> followers
    - List<User> following

- Post
    - Integer id
    - User createdBy
    - Date creationTime
    - Date modifiedTime
    - String body
    - List<Photo> photos
    - List<Comment> comments
    - Integer likes

- SearchCriteria 
    - key(property name: person and post)
    - value

- Comment
    - Integer id
    - String comment

- Photo
    - Integer id
    - String path

- REST APIs
```
/signUp
/login
/user/:id/timeline
```



## Request Response Details:
```
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
```

## Request Response Details:
```
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
```

4.  Login - 2 days
    - Login Page
    - Spring Security

    ## Task :
    - User should be able to login
    - Signout should work properly
    - If the user is opening any url without logging in. He should be redirected to log in page.


 
1. UserCreation - 1 day

    ## Task

    - User is able to signup and create his account(/signUp [POST])

2. Timeline - 4 days
    - Research
    - Post Creation
    - Infinite scroll(loggedIn User)
    - Freinds timeline
    - Likes and Comments

    ## Task

    - User is able to create a post.(/post [POST])
    - User is able to Like and Comment on a post.(/post/id [POST])
    - User is able to scroll and see all post in timeline.(/timeline/my/:pageId [GET])
    - User is able to see friends timeline (/timeline/:FriendUserID [GET])-- dependency on Search
    - User should be able to follow and make friends.(/timeline/connect/ [POST])(Vice-versa)

3. Search -  2 days
   - Person 
   - Post

    ## Task

    - User should be able to search on the basis of posts.(/search/post?search= [GET])
    - User should be able to search person()(/search/person?search= [GET])


5. Notification  -  4 days
    - Research

6. Testing - 5 days


7. Deployment - 1 day
    - Dockerize TimelineUI and TimelineService
    - Push in dockerhub
    - setup GKE
    - configure your project in GKE
    
19th December - 7 pm

