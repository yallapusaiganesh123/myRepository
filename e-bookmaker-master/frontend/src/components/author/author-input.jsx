import React, { useState } from 'react';
//import { useHistory } from 'react-router-dom'; // Use the appropriate routing library for React (e.g., react-router-dom)
import { useNavigate } from 'react-router-dom';
import AuthorHeaderComponent from './author-header';
const AuthorInputComponent = () => {
  //const history = useHistory();
  const [authorId, setAuthorId] = useState('');
  const navigate = useNavigate();

  const onSubmit = (e) => {
    e.preventDefault();
    if (authorId.trim() !== '') {
      navigate(`/book-dashboard/${authorId}`);
    }
  };

  return (
    <div>
      <div>
        {/* Include AuthorHeaderComponent JSX here if needed */}
        <AuthorHeaderComponent/>
      </div>
      <div className="container py-4 text-center">
        <h3 className="book-verification-text">Confirm Your Author Id</h3>
      </div>
      <div className="container d-flex justify-content-center">
        <form onSubmit={onSubmit} className="col-md-6 shadow p-3 bg-white rounded">
          <div className="form-group py-3">
            <label htmlFor="authorId">Author ID:</label>
            <input
              type="text"
              className="form-control"
              id="authorId"
              value={authorId}
              onChange={(e) => setAuthorId(e.target.value)}
              name="authorId"
              required
            />
          </div>
          <button type="submit" className="btn btn-primary">Show Books</button>
        </form>
      </div>
    </div>
  );
};

export default AuthorInputComponent;
