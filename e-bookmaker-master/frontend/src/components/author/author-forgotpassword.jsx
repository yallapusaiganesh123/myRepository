import React, { useState } from 'react';
//import { useHistory } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import CancelButton from '../cancelButton';
const AuthorForgotpasswordComponent = () => {
  const [email, setEmail] = useState('');
  const [authorname, setAuthorName] = useState('');
  const [password, setPassword] = useState('');
  const [age, setAge] = useState('');
  const [about, setAbout] = useState('');

  //const history = useHistory();
  const navigate = useNavigate();

  const save = () => {
    const bodyData = {
      email,
      authorname,
      password,
      age,
      about,
    };

    fetch('http://localhost:8091/api/author/update', {
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
        navigate('/author-login');
      })
      .catch((error) => {
        console.error('Error updating details:', error);
      });
  };

  return (
    <section className="vh-100  bg-image-au-forgotpass">
     
      <div className="mask d-flex align-items-center h-100 gradient-custom-3">
        <div className="container h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-12 col-md-9 col-lg-7 col-xl-6">
              <div className="card3 card" style={{ borderRadius: '15px' }}>
              <CancelButton/>
                <div className="card-body p-5">
                  <h2 className="text-uppercase text-center mb-5">Update your details as Author</h2>

                  <form onSubmit={(e) => e.preventDefault()}>
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
                    </div>

                    <div className="form-group">
                      <label htmlFor="authorname">Name<span className="text-danger">*</span></label>
                      <input
                        type="text"
                        id="authorname"
                        name="authorname"
                        value={authorname}
                        onChange={(e) => setAuthorName(e.target.value)}
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
                        name="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
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
                        name="age"
                        value={age}
                        onChange={(e) => setAge(e.target.value)}
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
                        name="about"
                        value={about}
                        onChange={(e) => setAbout(e.target.value)}
                        className="form-control"
                        placeholder="Enter something about yourself"
                        required
                      />
                    </div>

                    <br />

                    <div className="d-flex justify-content-center">
                      <button type="submit" className="btn btn-success" onClick={save}>
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

export default AuthorForgotpasswordComponent;
