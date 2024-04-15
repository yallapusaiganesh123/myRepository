import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import CancelButton from '../cancelButton';

const CreateEbookComponents = () => {
  const navigate = useNavigate();
  const [id, setId] = useState('');
  const [authorId, setAuthorId] = useState('');
  const [authorName, setAuthorName] = useState('');
  const [description, setDescription] = useState('');
  const [genre, setGenre] = useState('');

  const save = (e) => {
    e.preventDefault();
    let bodyData = {
      id: id,
      authorId: authorId,
      authorName: authorName,
      description: description,
      genre: genre,
    };

    fetch('http://localhost:8093/api/book/createbook', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(bodyData),
    })
      //.then((response) => response.json())
      .then((resultData) => {
        console.log(resultData);
        alert('Book Added Successfully');
        navigate('/update-book');
      })
      .catch((error) => {
        console.error('Error during book creation:', error);
      });
  };

  return (
    <section className="vh-100 bg-image-cb">
      <CancelButton/>
      <div className="mask d-flex align-items-center h-100 gradient-custom-3">
        <div className="container h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-12 col-md-9 col-lg-7 col-xl-6">
              <div className="card6 card" style={{ borderRadius: '15px' }}>
                <div className="card-body p-5">
                  <h2 className="text-uppercase text-center mb-5">Create new Book</h2>

                  <form onSubmit={save} className="ng-form">
                    <div className="form-group">
                      <label htmlFor="bookname">
                        Book Name<span className="text-danger">*</span>
                      </label>
                      <input
                        type="text"
                        id="bookname"
                        value={id}
                        onChange={(e) => setId(e.target.value)}
                        name="bookname"
                        className="form-control"
                        placeholder="Enter book name"
                        required
                      />
                    </div>

                    <div className="form-group">
                      <label htmlFor="email">
                        Email<span className="text-danger">*</span>
                      </label>
                      <input
                        type="email"
                        id="email"
                        value={authorId}
                        onChange={(e) => setAuthorId(e.target.value)}
                        name="email"
                        className="form-control"
                        placeholder="Enter your email"
                        required
                      />
                    </div>

                    <div className="form-group">
                      <label htmlFor="authorname">
                        Author Name<span className="text-danger">*</span>
                      </label>
                      <input
                        type="text"
                        id="authorname"
                        value={authorName}
                        onChange={(e) => setAuthorName(e.target.value)}
                        name="authorname"
                        className="form-control"
                        placeholder="Enter your name"
                        required
                      />
                    </div>

                    <div className="form-group">
                      <label htmlFor="text">
                        Description<span className="text-danger">*</span>
                      </label>
                      <input
                        type="text"
                        id="description"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                        name="description"
                        className="form-control"
                        placeholder="Description"
                        required
                      />
                    </div>

                    <div className="form-group">
                      <label htmlFor="genre">
                        Genre<span className="text-danger">*</span>
                      </label>
                      <input
                        type="text"
                        id="genre"
                        value={genre}
                        onChange={(e) => setGenre(e.target.value)}
                        name="genre"
                        className="form-control"
                        placeholder="Genre"
                        required
                      />
                    </div>

                    <br />

                    <div className="d-flex justify-content-center">
                      <button type="submit" className="btn btn-success">
                        Create
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

export default CreateEbookComponents;
