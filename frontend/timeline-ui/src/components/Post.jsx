import React, { useState } from 'react'
import { makeStyles } from '@material-ui/core/styles'
import Paper from '@material-ui/core/Paper'
import Grid from '@material-ui/core/Grid'
import styles from '../css/post.module.css'

const useStyles = makeStyles(theme => ({
  paper: {
    margin: '10px',
    padding: 10,
    color: theme.palette.text.secondary
  }
}))

function Post ({ post }) {
  let { username, body, likesCount, isLikedByUser } = post
  const classes = useStyles()
  let [like, setLike] = useState(likesCount)
  let [isLiked, setisLiked] = useState(isLikedByUser)

  const handleLike = () => {
    console.log(like)
    if (isLiked) {
      setLike(like - 1)
      setisLiked(false)
    } else {
      setLike(like + 1)
      setisLiked(true)
    }
    var myHeaders = new Headers()
    myHeaders.append('Content-Type', 'application/json')
    var raw = JSON.stringify({
      likedBy: { id: post.userId },
      post: { id: post.postId }
    })

    var requestOptions = {
      method: 'POST',
      headers: myHeaders,
      body: raw,
      redirect: 'follow'
    }

    fetch('http://localhost:8080/post/like', requestOptions)
      .then(res => res.json())
      .then(json => console.log(json))
      .catch(e => console.log(e))
  }

  return (
    <div className={styles.post}>
      <Grid item xs={4} />
      <Grid item xs={8}>
        <Paper className={classes.paper}>
          <h3>{username}</h3>
          <p>{body}</p>
          <div className={styles.post__like} onClick={handleLike}>
            <p>{like}</p>
            {isLiked ? (
              <img
                src='https://www.flaticon.com/svg/static/icons/svg/783/783343.svg'
                height='30px'
                alt='Like'
              />
            ) : (
              <img
                src='https://www.flaticon.com/svg/static/icons/svg/889/889221.svg'
                height='30px'
                alt='Like'
              />
            )}
          </div>
          <div className={styles.post__comment}>
            <input
              name='comment'
              placeholder='Enter Comments'
              className={styles.post__input}
            />
            <button className={styles.post__button}>Comment</button>
          </div>
        </Paper>
      </Grid>
      <Grid item xs={4} />
    </div>
  )
}

export default Post
//https://www.flaticon.com/svg/static/icons/svg/889/889221.svg
// [
// {
// "userId": 1,
// "username": "Sunandan",
// "postId": 2,
// "likesCount": 2,
// "body": "First Blog"
// },
// {
// "userId": 1,
// "username": "Sunandan",
// "postId": 3,
// "likesCount": 0,
// "body": "Second Blog"
// }
// ]
