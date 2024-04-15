// booksService.js
import { useState, useEffect } from 'react';
import axios from 'axios';


const baseURL = 'http://localhost:8093/api/book';

export const useBooksServices = () => {
  const [nonVerifiedBooks, setNonVerifiedBooks] = useState([]);
  const [verifiedBooks, setVerifiedBooks] = useState([]);

  const getNonVerifiedBookList = async () => {
    try {
      const response = await axios.get(`${baseURL}/nonverified/books`);
      setNonVerifiedBooks(response.data);
    } catch (error) {
      console.error('Error fetching non-verified books:', error);
    }
  };

  const verifyBook = async (bookId) => {
    try {
      await axios.post(`${baseURL}/setauthorized/${bookId}`);
      // Refresh the list of non-verified books after verification
      getNonVerifiedBookList();
    } catch (error) {
      console.error('Error verifying book:', error);
    }
  };

  const getVerifiedBookList = async () => {
    try {
      const response = await axios.get(`${baseURL}/verified/books`);
      setVerifiedBooks(response.data);
    } catch (error) {
      console.error('Error fetching verified books:', error);
    }
  };

  const deleteBookById = async (bookId) => {
    try {
      await axios.delete(`${baseURL}/deletebook/${bookId}`);
      // Refresh the list of verified books after deletion
      getVerifiedBookList();
    } catch (error) {
      console.error('Error deleting book:', error);
    }
  };

  const getBooksByAuthorId = async (authorId) => {
    try {
      const response = await axios.get(`${baseURL}/getbyauthid/${authorId}`);
      return response.data;
    } catch (error) {
      console.error('Error fetching books by author ID:', error);
      return [];
    }
  };

  const setBookCompleted = async (bookId) => {
    try {
      const response = await axios.get(`${baseURL}/setcomplete/${bookId}`);
      return response.data;
    } catch (error) {
      console.error('Error setting book as completed:', error);
      return '';
    }
  };

  useEffect(() => {
    getNonVerifiedBookList();
    getVerifiedBookList();
  }, []); // Fetch the lists when the component mounts

  return {
    nonVerifiedBooks,
    verifiedBooks,
    getNonVerifiedBookList,
    verifyBook,
    getVerifiedBookList,
    deleteBookById,
    getBooksByAuthorId,
    setBookCompleted,
  };
};
