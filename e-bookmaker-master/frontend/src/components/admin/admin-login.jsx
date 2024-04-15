
 
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
 
import { useNavigate } from 'react-router-dom';
import CancelButton from '../cancelButton';
 
const AdminLoginComponent = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
 
  const login = (event) => {
    event.preventDefault();
 
    const bodyData = {
      email: email,
      password: password,
    };
 
    fetch('http://localhost:8092/api/admin/login', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify(bodyData),
})
  .then((response) => response.json())
  .then((resultData) => {
      if (resultData.message === 'Login success') {
          alert("login success")
          navigate('/admin-home');
      } else {
          alert('Invalid login credentials');
      }
  })
  .catch((error) => {
      console.error('Error during login:', error);
      alert('An unexpected error occurred');
  });
  };
 
  return (
    <section className="vh-100 bg-image">
      <div className="mask d-flex align-items-center h-100 gradient-custom-3">
        <div className="container h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-12 col-md-9 col-lg-7 col-xl-6">
            
              <div className="card-al card" style={{ borderRadius: '15px' }}>
              <CancelButton/>
                <div className="card-body p-5">
                  <h2 className="text-uppercase text-center mb-5"> Admin Login</h2>
 
                  <form onSubmit={login}>
                    <div className="form-group">
                      <label htmlFor="email">Email<span className="text-danger">*</span></label>
                      <input
                        type="email"
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        name="email"
                        className="form-control"
                        placeholder="Enter your email"
                        required
                      />
                      {email === '' && (
                        <div className="text-danger">Email is required</div>
                      )}
                    </div>
 
                    <div className="form-group">
                      <label htmlFor="password">Password<span className="text-danger">*</span></label>
                      <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        name="password"
                        className="form-control"
                        placeholder="Enter password"
                        required
                      />
                      {password === '' && (
                        <div className="text-danger">Password is required</div>
                      )}
                    </div>
 
                    <br />
 
                    <div className="d-flex justify-content-center">
                      <button type="submit" className="btn btn-primary">
                        Login
                      </button>
                    </div>
 
                    <p className="text-center text-muted mt-3">
                      <Link to="/admin-forgotpassword" className="fw-bold text-body">
                        Forgot Password?
                      </Link>
                    </p>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};
 
export default AdminLoginComponent;