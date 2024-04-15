import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import CancelButton from '../cancelButton';

const AuthorRegisterComponent = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [authorname, setAuthorname] = useState('');
  const [password, setPassword] = useState('');
  const [age, setAge] = useState('');
  const [about, setAbout] = useState('');

  const save = (e) => {
    e.preventDefault();
    let bodyData = {
      email: email,
      authorname: authorname,
      password: password,
      age: age,
      about: about
    };

    fetch("http://localhost:8091/api/author/register", {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(bodyData),
    })
    
    .then(resultData => {
      console.log(resultData);
      alert("Author Registered Successfully");
      navigate('/');
    })
    .catch(error => {
      console.error('Error during registration:', error);
      alert('Failed to register author');
    });
  };

  return (
    <section className="vh-100 bg-image-ar">
     
      <div className="mask d-flex align-items-center h-100 gradient-custom-3">
        <div className="container h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-12 col-md-9 col-lg-7 col-xl-6">
              <div className="card2 card" style={{ borderRadius: '15px' }}>
              <CancelButton/>
                <div className="card-body p-5">
                  <h2 className="text-uppercase text-center mb-5">Author Registration</h2>

                  <form onSubmit={save} className="ng-form">
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
                    </div>

                    <div className="form-group">
                      <label htmlFor="authorname">Name<span className="text-danger">*</span></label>
                      <input
                        type="text"
                        id="authorname"
                        value={authorname}
                        onChange={(e) => setAuthorname(e.target.value)}
                        name="authorname"
                        className="form-control"
                        placeholder="Enter your name"
                        required
                      />
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

                    <div className="form-group">
                      <label htmlFor="age">Age<span className="text-danger">*</span></label>
                      <input
                        type="text"
                        id="age"
                        value={age}
                        onChange={(e) => setAge(e.target.value)}
                        name="age"
                        className="form-control"
                        placeholder="Enter your age"
                        required
                      />
                    </div>

                    <div className="form-group">
                      <label htmlFor="about">About<span className="text-danger">*</span></label>
                      <input
                        type="text"
                        id="about"
                        value={about}
                        onChange={(e) => setAbout(e.target.value)}
                        name="about"
                        className="form-control"
                        placeholder="Enter something about yourself"
                        required
                      />
                    </div>

                    <br />

                    <div className="d-flex justify-content-center">
                      <button type="submit" className="btn btn-success">Register</button>
                    </div>

                    <p className="text-center text-muted mt-5 mb-0">Already have an account? <a href="/author-login" className="fw-bold text-body"><u>Login here</u></a></p>
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

export default AuthorRegisterComponent;
