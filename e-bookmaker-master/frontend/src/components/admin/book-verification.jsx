import React, { useEffect, useState } from 'react';
import { Button } from 'react-bootstrap'; // Import Bootstrap Button if not already imported
//import AppHeader from 'react-bootstrap'
import axios from 'axios';
import HeaderComponent from './header';
const BookVerificationComponent = () => {
  const [nonverifiedbooks, setNonVerifiedBooks] = useState([]);

  useEffect(() => {
    getNonVerifiedBooks();
  }, []);

  const getNonVerifiedBooks = () => {

   axios.get('http://localhost:8093/api/book/nonverified/books')
   .then((response) => {
    setNonVerifiedBooks(response.data);
  })
  .catch((error) => {
    console.error('Error fetching non-verified books:', error);
  });
    // Sample data for demonstration
    //const sampleNonVerifiedBooks = [
     // { id: 1, authorName: 'Author 1', genre: 'Fiction', completed: true, description: 'Description 1', verified: false },
      //{ id: 2, authorName: 'Author 2', genre: 'Non-fiction', completed: false, description: 'Description 2', verified: false },
    //];

    //setNonVerifiedBooks(sampleNonVerifiedBooks);
  };
  const verifyBook = (nonverifiedbook) => {
    // Make a request to your API to verify the book
    fetch(`http://localhost:8093/api/book/${nonverifiedbook.id}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      }
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Failed to verify book');
      }
      // Assuming your API returns updated book data upon successful verification
      return response.json();
    })
    .then(data => {
      // Update the UI with the verified book
      setNonVerifiedBooks(nonverifiedbooks.map(book =>
        book.id === nonverifiedbook.id ? { ...book, verified: true } : book
      ));
      window.alert('Book verified successfully!');
    })
    .catch(error => {
      console.error('Error verifying book:', error);
      window.alert('Failed to verify book. Please try again.');
    });
  };

  
  return (
    <div>
      <div>
        {/* Assuming you have a header component */}
        <HeaderComponent/>
      </div>

      <div className="py-4 text-center">
        <h3 className="book-verification-text">Verify New Books</h3>
      </div>
      <div className="container py-4">
        <table className="table table-striped">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Book</th>
              <th scope="col">Author</th>
              <th scope="col">Genre</th>
              <th scope="col">Status</th>
              <th scope="col" className="text-center">
                Description
              </th>
              <th scope="col" className="text-center">
                Action
              </th>
            </tr>
          </thead>
          <tbody>
            {nonverifiedbooks.map((nonverifiedbook, index) => (
              <tr key={index}>
                <th scope="row">{index + 1}</th>
                <td>{nonverifiedbook.id}</td>
                <td>{nonverifiedbook.authorName}</td>
                <td>{nonverifiedbook.genre}</td>
                <td>{nonverifiedbook.completed ? 'Yes' : 'No'}</td>
                <td>{nonverifiedbook.description}</td>
                <td className="text-center">
                  {!nonverifiedbook.verified && (
                    <Button
                      variant="success"
                      className="btn-margin-right"
                      onClick={() => verifyBook(nonverifiedbook)}
                    >
                      Verify
                    </Button>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default BookVerificationComponent;
