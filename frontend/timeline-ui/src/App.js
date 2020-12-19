import React from 'react'
import './App.css'
import SignIn from './components/SignIn'
import Routes from './components/Routes'
import Comments from './components/Comments'

function App () {
  return (
    <div className='App'>
      <Routes />
      {/* <Comments postId = {2} /> */}
    </div>
  )
}

export default App
