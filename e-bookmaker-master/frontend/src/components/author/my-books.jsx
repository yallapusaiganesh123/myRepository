import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useBooksServices } from '../../services/book';
import AuthorHeaderComponent from './author-header';

const MyBooksComponent = () => {
  const { authorId } = useParams();
  const [books, setBooks] = useState([]);
  const { getBooksByAuthorId, deleteBookById, setBookCompleted } = useBooksServices();

  useEffect(() => {
    fetchBooks();
  }, [authorId]);

  const fetchBooks = () => {
    if (authorId) {
      getBooksByAuthorId(authorId).then(
        (data) => {
          setBooks(data);
        },
        (error) => {
          console.error('Error fetching books:', error);
        }
      );
    } else {
      console.error('Author ID not found in the URL.');
    }
  };

  const deleteBook = (bookId) => {
    deleteBookById(bookId).then(
      () => {
        console.log('Book deleted successfully from books.');
        fetchBooks();
      },
      (error) => {
        console.error('Error deleting book:', error);
      }
    );
  };

  const setComplete = (bookId) => {
    setBookCompleted(bookId).then(
      () => {
        console.log('Book completed successfully.');
        window.alert('Book with name - ' + bookId + ' completed successfully!');
        fetchBooks();
      },
      (error) => {
        console.error('Error completing book:', error);
      }
    );
  };

  return (
    <div>
      <AuthorHeaderComponent/>
      <div className="py-4 text-center">
        <h3 className="book-verification-text">Your Books</h3>
      </div>
      <div className="container py-2">
        <div className="table-responsive">
          <table className="table table-striped">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Book</th>
                <th scope="col">Genre</th>
                <th scope="col">Description</th>
                <th scope="col">Status</th>
                <th scope="col" className="text-center">
                  Action
                </th>
              </tr>
            </thead>
            <tbody>
              {books.map((book, index) => (
                <tr key={book.id}>
                  <th scope="row">{index + 1}</th>
                  <td>{book.id}</td>
                  <td>[{book.genre}]</td>
                  <td>{book.description}</td>
                  <td>{book.completed ? 'completed' : 'incomplete'}</td>
                  <td>
                    {/* <button
                      type="button"
                      className="btn btn-success btn-margin-right"
                      onClick={() => {
                        // Handle update action
                      }}
                    >
                      Update
                    </button> */}
                    <button
                      type="button"
                      className="btn btn-danger"
                      onClick={() => deleteBook(book.id)}
                    >
                      Delete
                    </button>
                    <button
                      type="button"
                      className="btn btn-success btn-margin-right custom-button"
                      onClick={() => setComplete(book.id)}
                    >
                      Set As Complete
                    </button>
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

export default MyBooksComponent;
