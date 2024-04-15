import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import HeaderComponent from './header';

const AdminViewEbookComponent = () => {
  const [ebook, setEbook] = useState(null);
  const { bookId } = useParams();

  useEffect(() => {
    const fetchEbook = async () => {
      try {
        const response = await fetch(`http://localhost:8095/api/ebook/${bookId}`);
        const data = await response.json();
        setEbook(data);
      } catch (error) {
        console.error('Error fetching ebook:', error);
      }
    };

    fetchEbook();
  }, [bookId]);

  const downloadBook = () => {
    if (ebook) {
      const content = `
        ${ebook.bookId}\n\n
        -By ${ebook.authorName}\n\n
        ${ebook.content}
      `;
      const blob = new Blob([content], { type: 'text/plain' });
      const filename = `${ebook.bookId}.doc`;
      const mimeType =
        'application/vnd.openxmlformats-officedocument.wordprocessingml.document';
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = filename;
      a.click();
      window.URL.revokeObjectURL(url);
    }
  };

  return (
    <div>
      <div>
        <HeaderComponent/>
      </div>
      <div className="py-4 text-center">
        <div className="container">
          <div className="book-page">
            <div className="book-title">
              <h1>{ebook?.bookId}</h1>
            </div>
            <div className="book-author">
              <h3>-By {ebook?.authorName}</h3>
            </div>
            <div className="book-content">
              <p>{ebook?.content}</p>
            </div>
          </div>
          <div className="download-button">
            <button onClick={downloadBook}>Download DOC</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminViewEbookComponent;
