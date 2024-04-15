import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import UserHeaderComponent from './user-header';
const UserHomeComponent = () => {
  const [ebooks, setEbooks] = useState([]);
  const [filteredEbooks, setFilteredEbooks] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    getAlleBooks();
  }, []);
  // Assume you have a service named ebookService with a getAlleBookList function
const ebookService = {
  getAlleBookList: async () => {
    // Replace this with your actual API call to fetch ebooks
    try {
      const response = await fetch('http://localhost:8095/api/ebook/allebooks');
      return await response.json();
    } catch (error) {
      return console.error('Error fetching ebooks:', error);
    }
  },
};




  const getAlleBooks = () => {
    // Assume ebookService.getAlleBookList() is a function that returns a Promise
    ebookService.getAlleBookList().then((data) => {
      setEbooks(data);
      setFilteredEbooks(data);
      console.log(data);
    });
  };

  const handleSearchChange = () => {
    setFilteredEbooks(
      (ebooks ?? []).filter(
        (ebook) =>
          ebook.bookId.toLowerCase().includes(searchTerm.toLowerCase()) ||
          ebook.genre.toLowerCase().includes(searchTerm.toLowerCase())
      )
    );
  };

  return (
    <div>
      <div>
      <UserHeaderComponent/>
      </div>

      <div className="py-4 text-center">
        <h3 className="home-text py-3">New Books Available!</h3>
        <form
          className="d-flex mx-auto search"
          role="search"
          onSubmit={(e) => e.preventDefault()}
        >
          <div
            className="p-1 bg-light rounded rounded-pill shadow"
            style={{ width: '70%', margin: '0 auto' }}
          >
            <div className="input-group">
              <input
                type="search"
                placeholder="What're you searching for?"
                aria-describedby="button-addon1"
                className="form-control border-0 bg-light"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
              />
              <div className="input-group-append">
                <button
                  id="button-addon1"
                  type="submit"
                  className="btn btn-link text-primary"
                  onClick={handleSearchChange}
                >
                  <i className="fa fa-search"></i>
                </button>
              </div>
            </div>
          </div>
        </form>
      </div>

      <div className="container py-4">
        <div className="row">
          {filteredEbooks?.map((ebook) => (
            <div className="col-lg-3 col-md-6 mb-4" key={ebook.bookId}>
              <div className="card">
                <div className="card-body text-center">
                  <h4 className="card-title">{ebook.bookId}</h4>
                </div>
                <ul className="list-group list-group-flush">
                  <li className="list-group-item">
                    <b>Author: </b>
                    {ebook.authorName}
                  </li>
                  <li className="list-group-item">
                    <strong>Genre: </strong> {ebook.genre}
                  </li>
                </ul>
                <div className="card-body">
                  <p className="card-text">{ebook.description}</p>
                  <div className="btn-div d-flex justify-content-end">
                    <Link
                      to={`/view-ebook/${ebook.bookId}`}
                      className="btn btn-primary"
                    >
                      View
                    </Link>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default UserHomeComponent;
