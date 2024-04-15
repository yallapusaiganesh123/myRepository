import React from 'react';
import { Link } from 'react-router-dom'; // Assuming you are using React Router
import ebookLogo from '../../assests/images/ebook-logo.png';
function AuthorHeaderComponent() {
  return (
    <nav className="navbar navbar-expand-lg bg-success navbar-dark px-4">
      <div className="container-fluid">
        <img
          src={ebookLogo}
          alt="Logo"
          width="75"
          height="75"
          className="d-inline-block align-text-top" />
        <span className="navbar-brand text-white">Ebook Maker</span>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav me-auto">
            {/* Use me-auto class to push items to the left */}
            <li className="nav-item">
              <Link className="nav-link" to="/mybooks">
                My Books
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/create-book">
                Create New Book
              </Link>
            </li>
          </ul>
        </div>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <ul className="navbar-nav ms-auto">
          <li className="nav-item">
            <button type="button" className="btn btn-light">
              <Link to="/">Logout</Link>
            </button>
          </li>
        </ul>
      </div>
    </nav>
  );
}

export default AuthorHeaderComponent;
