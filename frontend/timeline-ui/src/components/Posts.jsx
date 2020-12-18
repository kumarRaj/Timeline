import React, { useEffect, useState } from 'react'
import Post from './Post'
import CreatePost from './CreatePost'

function Posts () {
  const [like, setLike] = useState(0)
  const [comment, setComment] = useState('')
  const [posts, setPosts] = useState([])
  const [userId, setUserId] = useState(4)

  useEffect(async () => {
    var requestOptions = {
      method: 'GET',
      redirect: 'follow'
    }

    await fetch('http://localhost:8080/post/timeline/' + userId, requestOptions)
      .then(res => res.json())
      .then(json => {
        console.log(json)
        setPosts(json)
      })
      .catch(e => console.log(e))
  }, [])
  console.log('posts', posts)

  return (
    // <div></div>
    <div >
      <CreatePost userId={userId} />
      {posts && posts.map((post, i) => <Post key={i} post={post} />)}
    </div>
  )
}

export default Posts
