import React, { useState } from 'react'
import Select from '@material-ui/core/Select'
import { makeStyles } from '@material-ui/core/styles'
import Paper from '@material-ui/core/Paper'
import Grid from '@material-ui/core/Grid'
import MenuItem from '@material-ui/core/MenuItem'
import styles from '../css/search.module.css'
import InputLabel from '@material-ui/core/InputLabel'
import FormControl from '@material-ui/core/FormControl'
import TextField from '@material-ui/core/TextField'
import Button from '@material-ui/core/Button'
import { hostname, port } from "../constants/properties";

const useStyles = makeStyles(theme => ({
  formControl: {
    margin: theme.spacing(1),
    width: '100%',
    // flex: 1,
    padding: 10
  },
  selectEmpty: {
    marginTop: theme.spacing(2)
  },
  paper: {
    margin: '10px',
    padding: 10,
    color: theme.palette.text.secondary,
    boxShadow:
      '0 14px 5px 0 rgba(0, 0, 0, 0.3), 0 26px 30px 0 rgba(0, 0, 0, 0.50)'
    // display: 'flex'
  },
  select: {
    width: '20%'
  },
  textField: {
    width: '50%',
    // flex: 0,
    padding: 10
  }
}))

function Search () {
  const classes = useStyles()
  let [searchCriteria, setSearchCriteria] = useState('Blog')
  let [query, setQuery] = useState('')
  const search = () => {
    console.log(searchCriteria, query)
    var requestOptions = {
      method: 'GET',
      redirect: 'follow'
    }

    fetch(
      "http://" + hostname + ":" + port + "/search/" + searchCriteria + "?query=" + query,
      requestOptions
    )
      .then(res => res.json())
      .then(json => {
        console.log(json)
      })
      .catch(e => console.log(e))
  }
  return (
    <div className={styles.search}>
      <Grid item xs={4} />
      <Grid item xs={8}>
        <Paper className={classes.paper}>
          <FormControl variant='filled' className={classes.formControl}>
            <InputLabel id='demo-simple-select-filled-label'>
              Search Criteria
            </InputLabel>
            <Select
              className={classes.select}
              labelId='demo-simple-select-filled-label'
              id='demo-simple-select-filled'
              value='search'
              onChange={e => setSearchCriteria(e.target.value)}
            >
              <MenuItem value='blog'>Blog</MenuItem>
              <MenuItem value='person'>Person</MenuItem>
            </Select>
          </FormControl>
          <TextField
            className={classes.textField}
            id='search'
            type='string'
            rows='1'
            placeholder='Write your thoughts'
            onChange={e => setQuery(e.target.value)}
          />
          <>
            <img
              src='https://www.flaticon.com/svg/static/icons/svg/44/44623.svg'
              height='30px'
              alt='search'
              onClick={search}
            />
          </>
        </Paper>
      </Grid>
      <Grid item xs={4} />
    </div>
  )
}

export default Search
