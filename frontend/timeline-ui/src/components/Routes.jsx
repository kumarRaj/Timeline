import React from 'react'
import { Switch, Route } from 'react-router-dom'
import SignIn from './SignIn'
import Posts from './Posts'

function Routes () {
  return (
    <div>
      <Switch>
        <Route exact path='/' render={props => <SignIn {...props} />} />
        <Route path='/posts' render={props => <Posts {...props} />} />
      </Switch>
    </div>
  )
}

export default Routes
