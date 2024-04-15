import React, { useState } from 'react';
//import { useHistory } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import CancelButton from '../cancelButton';

 


const AdminForgotPassword = () => {
  const navigate = useNavigate();
  //const history = useHistory();
  const [email, setEmail] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const save = (event) => {
    event.preventDefault();

    const bodyData = {
      email,
      username,
      password,
    };
    

    fetch('http://localhost:8092/api/admin/update', {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(bodyData),
    })
    .then((response) => response.text())
      .then((resultData) => {
        console.log(resultData);
        alert('Details Updated Successfully');
        navigate('/admin-login');
      })
      .catch((error) => {
        console.error('Error updating details:', error);
      });
      
  };

  return (
    <section className="vh-100 bg-image">
      
      <div className="mask d-flex align-items-center h-100 gradient-custom-3">
        <div className="container h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-12 col-md-9 col-lg-7 col-xl-6">
              <div className="card card1" style={{ borderRadius: '15px' }}>
              <CancelButton/>
                <div className="card-body p-5">
                  <h2 className="text-uppercase text-center mb-5"> Admin Forgot Password</h2>

                  <form onSubmit={save}>
                    <div className="form-group">
                      <label htmlFor="email">Email<span className="text-danger">*</span></label>
                      <input
                        type="email"
                        id="email"
                        name="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="form-control"
                        placeholder="Enter your email"
                        required
                      />
                      {email && !email.includes('@') && (
                        <div className="text-danger">Please enter a valid email</div>
                      )}
                    </div>

                    <div className="form-group">
                      <label htmlFor="username">Username<span className="text-danger">*</span></label>
                      <input
                        type="text"
                        id="username"
                        name="username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        className="form-control"
                        placeholder="Enter your name"
                        required
                      />
                      {username && username.length < 3 && (
                        <div className="text-danger">Name should be at least 3 characters</div>
                      )}
                    </div>

                    <div className="form-group">
                      <label htmlFor="password">Password<span className="text-danger">*</span></label>
                      <input
                        type="password"
                        id="password"
                        name="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="form-control"
                        placeholder="Enter password"
                        required
                      />
                      {password && password.length < 6 && (
                        <div className="text-danger">Password should be at least 6 characters</div>
                      )}
                    </div>
                    <br />

                    <div className="d-flex justify-content-center">
                      <button type="submit" className="btn btn-success">
                        Update
                      </button>
                    </div>
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

export default AdminForgotPassword;
