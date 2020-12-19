import React, { useEffect, useState } from 'react'
import Comment from './Comment'
import { makeStyles } from '@material-ui/core/styles'
import Paper from '@material-ui/core/Paper'
import Grid from '@material-ui/core/Grid'
import styles from '../css/comments.module.css'

const useStyles = makeStyles(theme => ({
  container: {
    margin: 'auto',
    marginTop: '50px',
    // boxShadow: "20px 20px 60px #808080, -20px -20px 60px #ffffff",
    position: 'absolute',
    width: '90%',
    backgroundColor: theme.palette.background.paper,
    border: '2px solid #000',
    boxShadow: theme.shadows[5]
  },
  paper: {
    margin: '10px',
    padding: 10,
    color: theme.palette.text.secondary,
    boxShadow:
      '0 14px 5px 0 rgba(0, 0, 0, 0.3), 0 26px 30px 0 rgba(0, 0, 0, 0.50)'
  }
}))

function getModalStyle () {
  const top = 45
  const left = 48

  return {
    top: `${top}%`,
    left: `${left}%`,
    transform: `translate(-${top}%, -${left}%)`
  }
}

function Comments ({ postId }) {
  const classes = useStyles()
  const [comments, setComments] = useState([])
  const [modalStyle] = useState(getModalStyle)

  useEffect(async () => {
    var requestOptions = {
      method: 'GET',
      redirect: 'follow'
    }

    await fetch(
      'http://localhost:8080/post/' + postId + '/comment/',
      requestOptions
    )
      .then(res => res.json())
      .then(json => {
        console.log(json)
        setComments(json)
      })
      .catch(e => console.log(e))
  }, [])

  return (
    <div className={styles.comments}>
      <Grid item xs={4} />
      <Grid item xs={8} style={modalStyle} className={classes.container}>
        <Paper className={classes.paper}>
          {comments &&
            comments.map((comment, i) => (
              <Comment key={i} commentObject={comment} />
            ))}
        </Paper>
      </Grid>
    </div>
  )
}

export default Comments
