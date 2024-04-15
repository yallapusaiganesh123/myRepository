import React from 'react';
import { Navbar, Nav, NavDropdown, Button } from 'react-bootstrap'; // Import Bootstrap components if not already imported
import { Link, useNavigate } from 'react-router-dom'; // Assuming you are using React Router
//import { useHistory } from 'react-router-dom';

const HeaderComponent = () => {
  const navigate=useNavigate();
  //const history=useHistory();
  const navigateToAdminLogin = () => {
    // Replace with your React Router navigation logic
     //history.push('/admin-login');
    navigate('/');  ///
  };
console.log(navigateToAdminLogin); ///
  return (
    <Navbar bg="success" variant="dark" expand="lg" className="px-4 ">
      <div className="container-fluid ">
        <Navbar.Brand as={Link} to="/admin-home" className="text-white">
          Ebook Maker
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="navbarSupportedContent" />
        <Navbar.Collapse id="navbarSupportedContent">
          <Nav className="me-auto mb-2 mb-lg-0">
            <Nav.Link as={Link} to="/admin-home" className="text-white" aria-current="page">
              Home
            </Nav.Link>
            <Nav.Link as={Link} to="/author-verification" className="text-white">
              Authors
            </Nav.Link>
            <NavDropdown title="Manage Books" id="basic-nav-dropdown" className="text-white">
              <NavDropdown.Item as={Link} to="/book-verification">
                Verify Books
              </NavDropdown.Item>
              <NavDropdown.Item as={Link} to="/create-ebooks">
                Create Ebooks
              </NavDropdown.Item>
            </NavDropdown>
          </Nav>
          <ul className="btn-ul">
            <Button variant="light" onClick={navigateToAdminLogin}>
              Logout
            </Button>
          </ul>
        </Navbar.Collapse>
      </div>
    </Navbar>
  );
};

export default HeaderComponent;
