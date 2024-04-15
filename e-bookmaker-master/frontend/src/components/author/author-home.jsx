import React from 'react';
//import AuthorHeaderComponent from './AuthorHeaderComponent'; // Adjust the path based on your project structure
import AuthorHeaderComponent from './author-header';
const AuthorHomeComponent = () => {
  return (
    <div>
      <AuthorHeaderComponent />
      <div className="home-container">
        <header>
          <h1>Welcome to Author Page.</h1>
          <p>Here you can create your books by going to Create new book page!</p>
        </header>
      </div>
    </div>
  );
};

export default AuthorHomeComponent;
