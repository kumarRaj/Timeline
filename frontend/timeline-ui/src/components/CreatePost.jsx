import React, { useState } from 'react'
import { makeStyles } from '@material-ui/core/styles'
import Paper from '@material-ui/core/Paper'
import Grid from '@material-ui/core/Grid'
import styles from '../css/createpost.module.css'
import TextField from '@material-ui/core/TextField'
import Button from '@material-ui/core/Button'

const useStyles = makeStyles(theme => ({
  paper: {
    margin: '10px',
    padding: 10,
    color: theme.palette.text.secondary,
    boxShadow:
      '0 14px 5px 0 rgba(0, 0, 0, 0.3), 0 26px 30px 0 rgba(0, 0, 0, 0.50)'
  }
}))

function CreatePost ({ userId }) {
  const classes = useStyles()
  const [blogBody, setBlogBody] = useState('')

  const submit = () => {
    if (blogBody.trim() == '') return
    var myHeaders = new Headers()
    myHeaders.append('Content-Type', 'application/json')
    console.log('blog Body : ', blogBody)
    var raw = JSON.stringify({
      createdBy: { id: userId },
      body: blogBody
    })

    var requestOptions = {
      method: 'POST',
      headers: myHeaders,
      body: raw,
      redirect: 'follow'
    }

    fetch('http://localhost:8080/post/create', requestOptions)
      .then(res => res.json())
      .then(json => {
        console.log('Blog Created', json)
        setBlogBody('')
      })
      .catch(e => console.log(e))
  }
  return (
    <div className={styles.createpost}>
      <Grid item xs={4} />
      <Grid item xs={8}>
        <Paper className={classes.paper}>
          <TextField
            id='blogBody'
            type='string'
            rows='5'
            fullWidth='true'
            multiline='true'
            placeholder='Write your thoughts'
            onChange={e => setBlogBody(e.target.value)}
          />
          <Button
            variant='contained'
            color='primary'
            className={styles.createpost__button}
            onClick={submit}
          >
            Create Post
          </Button>
        </Paper>
      </Grid>
      <Grid item xs={4} />
    </div>
  )
}

export default CreatePost
