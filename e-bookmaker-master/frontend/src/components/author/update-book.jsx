import React, { useState } from 'react';
//import { useHistory } from 'react-router-dom'; // Use the appropriate routing library for React (e.g., react-router-dom)
import { useNavigate } from 'react-router-dom';
import AuthorHeaderComponent from './author-header';
import CancelButton from '../cancelButton';

const UpdateBookComponent = () => {
  const [bookname, setBookname] = useState('');
  const [bookcontent, setBookcontent] = useState('');
  //const history = useHistory();
  const navigate = useNavigate();


  const save = () => {
    let bodyData = {
      bookname: bookname,
      bookcontent: bookcontent,
    };

    fetch('http://localhost:8094/api/bookcontent/update', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(bodyData),
    })
      //.then((response) => response.text())
      .then((resultData) => {
        console.log(resultData);
        alert('Content updated Successfully');
        navigate('/author-home');
      })
      .catch((error) => {
        console.error('Error updating content:', error);
      });
  };

  return (
    <div>
      <AuthorHeaderComponent/>

    
    <section className="vh-100 bg-image-ub">
    <CancelButton/>
      <div className="mask d-flex align-items-center h-100 gradient-custom-3">
        <div className="container h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-12 col-md-9 col-lg-7 col-xl-6">
              <div className="card7 card" style={{ borderRadius: '15px' }}>
                <div className="card-body p-5">
                  <h2 className="text-uppercase text-center mb-5">
                    Add/update Book Content
                  </h2>

                  <form onSubmit={save}>
                    <div className="form-group">
                      <label htmlFor="bookname">
                        Book Name<span className="text-danger">*</span>
                      </label>
                      <input
                        type="text"
                        id="bookname"
                        name="bookname"
                        value={bookname}
                        onChange={(e) => setBookname(e.target.value)}
                        className="form-control"
                        placeholder="Enter bookname"
                        required
                      />
                    </div>

                    <div className="form-group">
                      <label htmlFor="content">
                        Content<span className="text-danger">*</span>
                      </label>
                      <textarea
                        id="content"
                        name="content"
                        value={bookcontent}
                        onChange={(e) => setBookcontent(e.target.value)}
                        className="form-control"
                        placeholder="Enter Book content"
                        required
                        rows="5"
                      ></textarea>
                    </div>
                    <br />
                    <div className="d-flex justify-content-center">
                      <button type="submit" className="btn btn-success">
                        Submit
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
    </div>
  );
};

export default UpdateBookComponent;
