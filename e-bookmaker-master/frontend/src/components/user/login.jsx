import React, { useState } from 'react';
import { Link} from 'react-router-dom'; // Use the appropriate routing library for React (e.g., react-router-dom)
import { useNavigate } from 'react-router-dom';
import CancelButton from '../cancelButton';

const LoginComponent = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  
  //const history = useHistory();
  const navigate = useNavigate();

  const login = (e) => {
    e.preventDefault();
     // Prevent the default form submission behavior
     if (!email.trim()) {
      alert('Email is required');
      return;
    }
    
    if (!password.trim()) {
      alert('Password is required');
      return;
    }

    console.log(email);
    console.log(password);

    let bodyData = {
      email: email,
      password: password, // Typo in the original code, should be "password"
    };

    fetch('http://localhost:8090/api/user/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(bodyData),
    })
      .then((response) => response.json())
      .then((resultData) => {
          if (resultData.message === "Login success") {
              navigate('/user-home');
              alert('login success')
          } else {
              alert("Invalid Credentials");
          }
      })
      .catch((error) => {
          console.error('Error during login:', error);
          alert('An unexpected error occurred');
      });
      };

  return (
    <section className="vh-100 bg-image-ul">
      <div className="mask d-flex align-items-center h-100 gradient-custom-3">
        <div className="container h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-12 col-md-9 col-lg-7 col-xl-6">
              <div className="card-ul card" style={{ borderRadius: '15px' }}>
              <CancelButton/>
                <div className="card-body p-5">
                  <h2 className="text-uppercase text-center mb-5">User Login</h2>

                  <form onSubmit={login}>
                    <div className="form-group">
                      <label htmlFor="email">
                        Email<span className="text-danger">*</span>
                      </label>
                      <input
                        type="email"
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="form-control"
                        placeholder="Enter your email"
                        pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}"
                        required
                      />
                      {/* <div
                        className={
                          'text-danger' +
                          (email === '' ? ' d-block' : ' d-none')
                        }
                      >
                        Email is required
                      </div> */}
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
                    </div>

                    <br />

                    <div className="d-flex justify-content-center">
                      <button
                        type="submit"
                        className="btn btn-success"
                        onClick={login}
                      >
                        Login
                      </button>
                    </div>

                    <p className="text-center text-muted mt-3">
                      <Link
                        to="/forgotpassword"
                        className="fw-bold text-body"
                      >
                        Forgot login details?
                      </Link>
                    </p>

                    <p className="text-center text-muted mt-5 mb-0">
                      Don't have an account?{' '}
                      <Link to="/register" className="fw-bold text-body">
                        <u>Register here</u>
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

export default LoginComponent;
