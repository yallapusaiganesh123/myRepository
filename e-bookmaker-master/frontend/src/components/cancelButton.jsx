import React from 'react';
import { Link } from 'react-router-dom'; // Assuming you're using React Router for navigation
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTimes } from '@fortawesome/free-solid-svg-icons';

const CancelButton = () => {
  const handleCancel = () => {
    // Handle cancel action here, e.g., navigate to home page
    // You can replace '/' with the appropriate route for your home page
    window.location.href = '/';
  };

  return (
    <button onClick={handleCancel} className="cancel-button">
      <FontAwesomeIcon icon={faTimes} />
    </button>
  );
};

export default CancelButton;
