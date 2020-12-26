import React from 'react'
import './App.css'
import SignIn from './components/SignIn'
import Routes from './components/Routes'
import Comments from './components/Comments'
import Navbar from './components/Navbar'

function App () {
  return (
    <div className='App'>
      <Navbar/>
      <Routes />
      {/* <Comments postId = {2} /> */}
    </div>
  )
}

export default App
