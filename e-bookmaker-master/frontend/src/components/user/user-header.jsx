import React from 'react';
import ebookLogo from '../../assests/images/ebook-logo.png';
const UserHeaderComponent = () => {
  return (
    <nav className="navbar navbar-expand-lg bg-success navbar-dar px-4">
      <div className="container-fluid">
        <img
          src={ebookLogo}
          alt="Logo"
          width="75"
          height="75"
          className="d-inline-block align-text-top"
        />
        <span className="navbar-brand text-white">Ebook Maker</span>
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
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav ms-auto">
            {/* Use ms-auto class to push items to the right */}
            <li className="nav-item">
              <button type="button" className="btn btn-light" onClick={() => window.location.href = '/'}>
                Logout
              </button>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default UserHeaderComponent;
