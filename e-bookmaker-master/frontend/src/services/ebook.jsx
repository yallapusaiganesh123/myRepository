//import { useState, useEffect } from 'react';

const useEbookService = () => {
  const baseURL = 'http://localhost:8095/api/ebook';

  const getAlleBookList = async () => {
    try {
      const response = await fetch(`${baseURL}/allebooks`);
      const data = await response.json();
      return data;
    } catch (error) {
      console.error('Error fetching books:', error);
      throw error; // You might want to handle errors differently
    }
  };

  const addToEbooks = async (bookId) => {
    try {
      const response = await fetch(`${baseURL}/addebook/${bookId}`, {
        method: 'POST',
      });
      const data = await response.text();
      return data;
    } catch (error) {
      console.error('Error adding to ebooks:', error);
      throw error;
    }
  };

  const viewEbook = async (bookId) => {
    try {
      const response = await fetch(`${baseURL}/${bookId}`);
      const data = await response.json();
      return data;
    } catch (error) {
      console.error('Error viewing ebook:', error);
      throw error;
    }
  };

  return {
    getAlleBookList,
    addToEbooks,
    viewEbook,
  };
};

export default useEbookService;
