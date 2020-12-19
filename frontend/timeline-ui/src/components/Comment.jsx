import React from 'react'

function Comment ({ commentObject }) {
  const { commentedBy, comment } = commentObject
  return (
    <div>
      {commentedBy}
      <br />
      {comment}
      <hr />
    </div>
  )
}

export default Comment
