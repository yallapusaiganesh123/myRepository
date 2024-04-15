import React from 'react';
import MainHeader from './mainheader'; // Assuming your MainHeader component is in a file named MainHeader.jsx

const MainHome = () => {
  return (
    <div>
      <MainHeader />

      <div className="home-container">
        <header>
          <h1>Welcome to Ebook Maker</h1>
          <p>Create, publish, and share your own eBooks easily!</p>
        </header>
      </div>

      <footer className="text-center text-black bg-success">
        {/* Copyright */}
        © 2024 Copyright: EBookMaker.com || Team 5 
        {/* Copyright */}
      </footer>
    </div>
  );
};

export default MainHome;
