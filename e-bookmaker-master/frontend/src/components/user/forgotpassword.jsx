import React, { useState } from 'react';
//import { useHistory } from 'react-router-dom'; // Use the appropriate routing library for React (e.g., react-router-dom)
import { useNavigate } from 'react-router-dom';
import CancelButton from '../cancelButton';
const ForgotpasswordComponent = () => {
  const [email, setEmail] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [age, setAge] = useState('');
  //const history = useHistory();
  const navigate = useNavigate();


  const save = () => {
    let bodyData = {
      email: email,
      username: username,
      password: password,
      age: age,
    };

    fetch('http://localhost:8090/api/user/update', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(bodyData),
    })
      .then((response) => response.text())
      .then((resultData) => {
        console.log(resultData);
        alert('Details Updated Successfully');
        navigate.push('/login');
      })
      .catch((error) => {
        console.error('Error updating details:', error);
      });
  };

  return (
    <section className="vh-100 bg-image-ufp">
      
      <div className="mask d-flex align-items-center h-100 gradient-custom-3">
        <div className="container h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-12 col-md-9 col-lg-7 col-xl-6">
              <div className="card5 card" style={{ borderRadius: '15px' }}>
              <CancelButton/>
                <div className="card-body p-5">
                  <h2 className="text-uppercase text-center mb-5">
                    Update your details as User
                  </h2>

                  <form onSubmit={save}>
                    <div className="form-group">
                      <label htmlFor="email">
                        Email<span className="text-danger">*</span>
                      </label>
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
                    </div>

                    <div className="form-group">
                      <label htmlFor="username">
                        Name<span className="text-danger">*</span>
                      </label>
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
                    </div>

                    <div className="form-group">
                      <label htmlFor="password">
                        Password<span className="text-danger">*</span>
                      </label>
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
                    </div>

                    <div className="form-group">
                      <label htmlFor="age">
                        Age<span className="text-danger">*</span>
                      </label>
                      <input
                        type="text"
                        id="age"
                        name="age"
                        value={age}
                        onChange={(e) => setAge(e.target.value)}
                        className="form-control"
                        placeholder="Enter your age"
                        required
                      />
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

export default ForgotpasswordComponent;
