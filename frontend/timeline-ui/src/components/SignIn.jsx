import React, { useState } from 'react'
import { makeStyles } from '@material-ui/core/styles'
import Paper from '@material-ui/core/Paper'
import Grid from '@material-ui/core/Grid'
import Button from '@material-ui/core/Button'
import TextField from '@material-ui/core/TextField'
import Modal from '@material-ui/core/Modal'
import SignUp from './SignUp'
import { useHistory } from 'react-router-dom'

const useStyles = makeStyles(theme => ({
  paper: {
    padding: theme.spacing(2),
    textAlign: 'center',
    color: theme.palette.text.secondary
  },
  input: {
    margin: '10px',
    width: '70%'
  },
  button: {
    margin: '10px',
    width: '70%'
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    margin: '10px',
    width: '70%'
  },
  container: {
    margin: 'auto',
    marginTop: '50px',
    boxShadow: '20px 20px 60px #808080, -20px -20px 60px #ffffff'
  }
}))

function SignIn (props) {
  const classes = useStyles()
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [openSignUp, setOpenSignUp] = useState(false)
  const history = useHistory()
  const handleSignIn = () => {
    console.log(history)
    history.push('/posts')
    // var myHeaders = new Headers();
    // myHeaders.append("Content-Type", "application/json");
    // var raw = JSON.stringify({
    //   password: password,
    //   emailId: email,
    // });
    // var requestOptions = {
    //   method: "POST",
    //   headers: myHeaders,
    //   body: raw,
    //   redirect: "follow",
    // };
    // fetch("http://localhost:8080/signIn", requestOptions)
    //   .then((response) => response.text())
    //   .then((result) => console.log(result))
    //   .catch((error) => console.log("error", error));
  }

  const handleClose = () => {
    setOpenSignUp(false)
  }

  return (
    <div>
      <Grid item xs={4} className={classes.container}>
        <Paper className={classes.paper}>
          <div>
            <TextField
              className={classes.input}
              id='outlined-helperText'
              label='Email'
              onChange={e => setEmail(e.target.value)}
              variant='outlined'
            />
          </div>

          <div>
            <TextField
              className={classes.input}
              id='outlined-helperText'
              label='Password'
              type='password'
              onChange={e => setPassword(e.target.value)}
              variant='outlined'
            />
          </div>
          <Button
            variant='contained'
            color='primary'
            className={classes.button}
            onClick={handleSignIn}
          >
            Sign In
          </Button>
          <Button
            variant='contained'
            color='primary'
            className={classes.button}
            onClick={() => setOpenSignUp(true)}
          >
            Sign Up
          </Button>
          <Modal open={openSignUp} onClose={handleClose}>
            <SignUp />
          </Modal>
        </Paper>
      </Grid>
    </div>
  )
}

export default SignIn
