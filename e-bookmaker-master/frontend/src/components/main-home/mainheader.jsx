import React from 'react';
import { Link } from 'react-router-dom';
import ebookLogo from '../../assests/images/ebook-logo.png';
const MainHeader = () => {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-success">
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
          data-bs-target="#main_nav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="main_nav">
          <ul className="navbar-nav ms-auto">
            {/* Use 'ms-auto' to push the items to the right */}
            <li className="nav-item dropdown">
              <a className="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">
                Login Options
              </a>
              <ul className="dropdown-menu">
                <li>
                  <Link to="/admin-login" className="dropdown-item">
                    Admin
                  </Link>
                </li>
                <li>
                  <Link to="/author-login" className="dropdown-item">
                    Author
                  </Link>
                </li>
                <li>
                  <Link to="/login" className="dropdown-item">
                    User
                  </Link>
                </li>
              </ul>
            </li>
            <li className="nav-item dropdown">
              <a className="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">
                Register Options
              </a>
              <ul className="dropdown-menu">
                <li>
                  <Link to="/author-register" className="dropdown-item">
                    Author
                  </Link>
                </li>
                <li>
                  <Link to="/register" className="dropdown-item">
                    User
                  </Link>
                </li>
              </ul>
            </li>
          </ul>
        </div>
        {/* navbar-collapse.// */}
      </div>
      {/* container-fluid.// */}
    </nav>
  );
};

export default MainHeader;
