import React, { useState } from 'react';
import { Link} from 'react-router-dom'; // Use the appropriate routing library for React (e.g., react-router-dom)import { useNavigate } from 'react-router-dom';
//history
import { useNavigate } from 'react-router-dom';
import CancelButton from '../cancelButton';
const RegisterComponent = () => {
  const [email, setEmail] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [age, setAge] = useState('');
  //const history = useHistory();
  const navigate = useNavigate();

  const save = (e) => {
    e.preventDefault(); // Prevent the default form submission behavior

    let bodyData = {
      email: email,
      username: username,
      password: password,
      age: age,
    };

    fetch('http://localhost:8090/api/user/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(bodyData),
    })
    
    .then((resultData) => {
      console.log(resultData);
      alert('User Registered Successfully');
      navigate('/');
    })
    .catch((error) => {
      console.error('Error during registration:', error);
      alert('Failed to register user');
    });
};

  return (
    <section className="vh-100 bg-image-ur">
      
      <div className="mask d-flex align-items-center h-100 gradient-custom-3">
        <div className="container h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-12 col-md-9 col-lg-7 col-xl-6">
              <div className="card4 card" style={{ borderRadius: '15px' }}>
              <CancelButton/>
                <div className="card-body p-5">
                  <h2 className="text-uppercase text-center mb-5">User Registration</h2>

                  <form onSubmit={save}>
                    <div className="form-group">
                      <label htmlFor="email">
                        Email<span className="text-danger">*</span>
                      </label>
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
                      <div
                        className={
                          'text-danger' +
                          (email === '' ? ' d-block' : ' d-none')
                        }
                      >
                        Email is required
                      </div>
                    </div>

                    <div className="form-group">
                      <label htmlFor="username">
                        Name<span className="text-danger">*</span>
                      </label>
                      <input
                        type="text"
                        id="username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        name="username"
                        className="form-control"
                        placeholder="Enter your name"
                        required
                      />
                      <div
                        className={
                          'text-danger' +
                          (username === '' ? ' d-block' : ' d-none')
                        }
                      >
                        Name is required
                      </div>
                    </div>

                    <div className="form-group">
                      <label htmlFor="password">
                        Password<span className="text-danger">*</span>
                      </label>
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
                      <div
                        className={
                          'text-danger' +
                          (password === '' ? ' d-block' : ' d-none')
                        }
                      >
                        Password is required
                      </div>
                    </div>

                    <div className="form-group">
                      <label htmlFor="age">
                        Age<span className="text-danger">*</span>
                      </label>
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
                      <div
                        className={
                          'text-danger' +
                          (age === '' ? ' d-block' : ' d-none')
                        }
                      >
                        Age is required
                      </div>
                    </div>

                    <br />
                    <div className="d-flex justify-content-center">
                      <button type="submit" className="btn btn-success">
                        Register
                      </button>
                    </div>

                    <p className="text-center text-muted mt-5 mb-0">
                      Already have an account?{' '}
                      <Link to="/login" className="fw-bold text-body">
                        <u>Login here</u>
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

export default RegisterComponent;
