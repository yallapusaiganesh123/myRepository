// authorService.js
import { useState } from 'react';
import axios from 'axios';

const baseURL = 'http://localhost:8091/api/author';

export const useAuthorService = () => {
  const [verificationResult, setVerificationResult] = useState(null);

  const verifyAuthor = async (authorEmail) => {
    try {
      const response = await axios.post(`${baseURL}/authorize/${authorEmail}`);
      setVerificationResult(response.data);
    } catch (error) {
      console.error('Error verifying author:', error);
    }
  };

  return { verifyAuthor, verificationResult };
};

// authorService.test.js
import axios from 'axios';
import { render, fireEvent, waitFor } from '@testing-library/react';
import { useAuthorService } from './authorService';

jest.mock('axios');

describe('AuthorService', () => {
  it('should verify author', async () => {
    const mockedData = { /* your mocked verification data here */ };
    axios.post.mockResolvedValue({ data: mockedData });

    const { result } = renderHook(() => useAuthorService());

    // Perform the verification
    await act(async () => {
      result.current.verifyAuthor('test@example.com');
    });

    // Wait for the verification result
    await waitFor(() => {
      expect(result.current.verificationResult).toEqual(mockedData);
    });
  });
});
