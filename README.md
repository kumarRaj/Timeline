# Timeline

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


New Update 
Completed :
User can create a post
User can see his own Post
User can like a post
User can comment on a post
User can view comments
Search functionality API created

Todo:
    
Search functionality:
    Scenario/Task:
    - User should be able to search any of his own blogs.
    - User should be able to search any person form his FirstName or Lastname.
    - There will be a button which will contain the search category, in this case Blog and Person. If the user doesn't select anything. Blog will be the default selection.
    - Onclick of the search button, user can view search result.
    - No typehead will be provided.
    - search results should open a new page. On search of a Blog, Result should display a list of Blogs matching the search result. Blogs will be loaded 10 at a time. If the user scrolls down and reaches the 8th blog. Next set of 10 blogs will be loaded.
    - On search of a person. User should display a list of 10 persons. Only the person name should be displayed.
    - On click of the person name, It should open the timeline for the particular user.

Connections:
    Scenario/Task:
    - An User can have a list of followers(People who follow that him)
    - An User can follow other users.
    - On viewing an user's timeline. On the top the First Name and Last Name will be displayed. Next to the Name. A button will be present to follow/unfollow.
    - A person can't follow a user multiple times.
    - For the logged In Person an user can view the people who are following him/her.
    - On clicking on his own name, his own timeline will open. Instead of flow and Unfollow button. There will be a button named "Following".
    - On click of Following button. A popup should open displaying the list of users that follow him.
    - The popup should only contain the list of names.

Timeline Page UI:
    - refer to the image 

Notification:
    - A person can view the notifications if the person clicks on the notification Icon.
    - A person will be notified if anyone has liked / commented on his/her post.
    - On click of the notification Icon a popup will display.
    - The popup should only contain the below two messages.
        <User> has liked your post <A truncated version of the post(Only first 15 letters)>
        <User> has commented on your post <A truncated version of the post(Only first 15 letters)>
    - Only latest 10 activities will be displayed.
    - There will be no links provided for User and for Post.

InfiniteScroll
    - After a person logs in an user can see a list of post.
    - All posts will be categorized in this manner.
        1. The Users he follows, there posts will be displayed.
        2. The Posts that are commented or liked by the users he follows will be displayed.
        3. The Users who follow him, there posts will be displayed.
        4. Any random post will be displayed. Whom the loggedIn Person may not be connected in any way.
    - Infinte Scroll will only be there in the homepage of the loggedIn person.

LogIn
    - User must log in avail any of the other features.
    - If a user directly tries to access any route from url without logging in. He/She should be redirected to the login page.
