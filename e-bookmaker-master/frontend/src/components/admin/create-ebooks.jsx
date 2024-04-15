import React, { useEffect, useState } from 'react';
import { Button } from 'react-bootstrap'; // Import Bootstrap Button if not already imported
import axios from 'axios';
import HeaderComponent from './header';
const CreateEbooksComponent = () => {
  const [verifiedbooks, setVerifiedBooks] = useState([]);

  useEffect(() => {
    getVerifiedBooks();
  }, []);

  const getVerifiedBooks = () => {
    // Replace the following logic with your API call to fetch verified books
    // e.g., axios.get('your_api_endpoint').then((response) => setVerifiedBooks(response.data));
    // Make sure to handle the promise accordingly
    axios.get('http://localhost:8093/api/book/verified/books').then((response) => setVerifiedBooks(response.data));
    // Sample data for demonstration
    //const sampleVerifiedBooks = [
     // { id: 1, authorName: 'Author 1', genre: 'Fiction', description: 'Description 1' },
     // { id: 2, authorName: 'Author 2', genre: 'Non-fiction', description: 'Description 2' },
    //];

    //setVerifiedBooks(sampleVerifiedBooks);
  };

  const addToEbooks = (bookId) => {
    fetch(`http://localhost:8095/api/ebook/addebook/${bookId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      }
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Failed to add ebook');
      }
      return response.json(); // Parse response body as JSON
    })
    .then(data => {
      console.log('Ebook added successfully:', data);
      window.alert('Ebook added successfully!'); // Display success message
      // Optionally, you can also update the UI or perform any other actions here
      // Delete the book after adding to ebooks
      // deleteBookById(bookId); // Commented out for now
    })
    .catch(error => {
      console.error('Error adding ebook:', error.message);
      window.alert('Failed to add ebook. Please try again.');
      // Handle the error, such as displaying an error message to the user
    });
  };
  const deleteBookById = (bookId) => {
    // Replace the following logic with your API call to delete the book
    // e.g., axios.delete(`your_api_endpoint/${bookId}`)
    // Make sure to handle the promise accordingly

    console.log(`Book deleted successfully after adding to ebooks for bookId: ${bookId}`);
    // You can navigate to the desired route after successful deletion
    // e.g., this.props.history.push('/admin-home');
  };

  return (
    <div>
      <div>
        {/* Assuming you have a header component */}
        <HeaderComponent/>
      </div>

      <div className="py-4 text-center">
        <h3 className="book-verification-text">List of Verified Books</h3>
      </div>
      <div className="container py-2">
        <div className="table-responsive">
          <table className="table table-striped">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Book</th>
                <th scope="col">Author</th>
                <th scope="col">Genre</th>
                <th scope="col">Description</th>
                <th scope="col" className="text-center">
                  Action
                </th>
              </tr>
            </thead>
            <tbody>
              {verifiedbooks.map((verifiedbook, index) => (
                <tr key={index}>
                  <th scope="row">{index + 1}</th>
                  <td>{verifiedbook.id}</td>
                  <td>{verifiedbook.authorName}</td>
                  <td>{verifiedbook.genre}</td>
                  <td>{verifiedbook.description}</td>
                  <td className="text-center">
                    <div className="button-container">
                      <Button
                        variant="success"
                        className="btn-margin-right custom-button"
                        onClick={() => addToEbooks(verifiedbook.id)}
                      >
                        Create Ebook
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default CreateEbooksComponent;
