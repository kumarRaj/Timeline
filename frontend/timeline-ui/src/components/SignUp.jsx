import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Paper from "@material-ui/core/Paper";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import { hostname, port } from "../constants/properties";

const useStyles = makeStyles((theme) => ({
  paper: {
    padding: theme.spacing(2),
    textAlign: "center",
    color: theme.palette.text.secondary,
  },
  input: {
    margin: "10px",
    width: "70%",
  },
  button: {
    margin: "10px",
    width: "70%",
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    margin: "10px",
    width: "70%",
  },
  container: {
    margin: "auto",
    marginTop: "50px",
    // boxShadow: "20px 20px 60px #808080, -20px -20px 60px #ffffff",
    position: "absolute",
    width: "90%",
    backgroundColor: theme.palette.background.paper,
    border: "2px solid #000",
    boxShadow: theme.shadows[5],
  },
}));

function getModalStyle() {
  const top = 45;
  const left = 48;

  return {
    top: `${top}%`,
    left: `${left}%`,
    transform: `translate(-${top}%, -${left}%)`,
  };
}
console.log(hostname, port);
function SignUp() {
  const classes = useStyles();
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [dateOfBirth, setdateOfBirth] = useState("");
  const [modalStyle] = useState(getModalStyle);

  const handleSignUp = (e) => {
    e.preventDefault();
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
      firstName: firstName,
      lastName: lastName,
      password: password,
      emailId: email,
      phoneNumber: phoneNumber,
      dateOfBirth: dateOfBirth,
    });

    var requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: raw,
      redirect: "follow",
    };
    console.log("http://" + hostname + ":" + port + "/signUp");
    fetch("http://" + hostname + ":" + port + "/signUp", requestOptions)
      .then((response) => response.text())
      .then((result) => console.log(result))
      .catch((error) => console.log("error", error));
  };

  return (
    <div>
      <Grid item xs={5} style={modalStyle} className={classes.container}>
        <Paper className={classes.paper}>
          <form onSubmit={handleSignUp} autoComplete="off">
            <div>
              <TextField
                className={classes.input}
                id="outlined-helperText"
                label="First Name"
                required
                onChange={(e) => setFirstName(e.target.value)}
                variant="outlined"
              />
            </div>

            <div>
              <TextField
                className={classes.input}
                id="outlined-helperText"
                label="Last Name"
                required
                onChange={(e) => setLastName(e.target.value)}
                variant="outlined"
              />
            </div>

            <div>
              <TextField
                className={classes.input}
                id="outlined-helperText"
                label="Email"
                required
                helperText="Email will be the username"
                onChange={(e) => setEmail(e.target.value)}
                variant="outlined"
              />
            </div>

            <div>
              <TextField
                className={classes.input}
                id="outlined-helperText"
                label="Password"
                type="password"
                required
                onChange={(e) => setPassword(e.target.value)}
                variant="outlined"
              />
            </div>

            <div>
              <TextField
                className={classes.input}
                id="outlined-helperText"
                label="Phone Number"
                required
                onChange={(e) => setPhoneNumber(e.target.value)}
                variant="outlined"
              />
            </div>

            <div>
              <TextField
                id="date"
                label="Date of Birth"
                type="date"
                required
                className={classes.textField}
                onChange={(e) => setdateOfBirth(e.target.value)}
                InputLabelProps={{
                  shrink: true,
                }}
              />
            </div>
            <Button
              variant="contained"
              color="primary"
              type="submit"
              className={classes.button}
            >
              Sign Up
            </Button>
          </form>
        </Paper>
      </Grid>
    </div>
  );
}

export default SignUp;
