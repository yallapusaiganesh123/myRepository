import React, { useEffect, useState } from 'react';
import { Button } from 'react-bootstrap'; // Import Bootstrap Button if not already imported
import axios from 'axios';
import HeaderComponent from './header'
const AuthorVerificationComponent = () => {
  const [unauthorizedauthors, setUnauthorizedAuthors] = useState([]);

  useEffect(() => {
    getUnauthorizedAuthors();
  }, []);

  const getUnauthorizedAuthors = () => {
    // Replace the following logic with your API call to fetch unauthorized authors
    // e.g., axios.get('your_api_endpoint').then((response) => setUnauthorizedAuthors(response.data));
    // Make sure to handle the promise accordingly
    axios.get('http://localhost:8091/api/author/unauthorized').then((response) => setUnauthorizedAuthors(response.data));
    // Sample data for demonstration
    //const sampleUnauthorizedAuthors = [
      //{ authorname: 'Author 1', email: 'author1@example.com', age: 30, about: 'About Author 1' },
      //{ authorname: 'Author 2', email: 'author2@example.com', age: 25, about: 'About Author 2' },
    //];

    //setUnauthorizedAuthors(sampleUnauthorizedAuthors);
  };

  const verifyAuthor = (unauthorizedauthor) => {
    // Replace the following logic with your API call to verify the author
    // e.g., axios.post('your_api_endpoint', { email: unauthorizedauthor.email })
    // Make sure to handle the promise accordingly

    // Sample data for demonstration
    const updatedUnauthorizedAuthors = unauthorizedauthors.filter(
      (author) => author.email !== unauthorizedauthor.email
    );

    setUnauthorizedAuthors(updatedUnauthorizedAuthors);
    window.alert('Author verified successfully!');
  };

  return (
    <div>
    <HeaderComponent/>
      <div className="py-4 text-center">
        <h3 className="book-verification-text">Verify New Authors</h3>
      </div>
      <div className="container py-4">
        <table className="table table-striped">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Author</th>
              <th scope="col">Email ID</th>
              <th scope="col">Age</th>
              <th scope="col" className="text-center">
                About
              </th>
              <th scope="col" className="text-center">
                Action
              </th>
            </tr>
          </thead>
          <tbody>
            {unauthorizedauthors.map((unauthorizedauthor, index) => (
              <tr key={index}>
                <th scope="row">{index + 1}</th>
                <td>{unauthorizedauthor.authorname}</td>
                <td>{unauthorizedauthor.email}</td>
                <td>{unauthorizedauthor.age}</td>
                <td>{unauthorizedauthor.about}</td>
                <td className="text-center">
                  <Button
                    variant="success"
                    onClick={() => verifyAuthor(unauthorizedauthor)}
                  >
                    Verify
                  </Button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default AuthorVerificationComponent;
