// adminService.js
import { useState, useEffect } from 'react';
import axios from 'axios';

const baseURL = 'http://localhost:8092/api/admin';

export const useAdminService = () => {
  const [unauthorizedAuthors, setUnauthorizedAuthors] = useState([]);

  const getUnauthorizedAuthorsList = async () => {
    try {
      const response = await axios.get(`${baseURL}/getunauth`);
      setUnauthorizedAuthors(response.data);
    } catch (error) {
      console.error('Error fetching unauthorized authors:', error);
    }
  };

  useEffect(() => {
    getUnauthorizedAuthorsList();
  }, []); // Fetch the list when the component mounts

  return { unauthorizedAuthors };
};

// adminService.test.js
import axios from 'axios';
import { render, waitFor } from '@testing-library/react';
import { useAdminService } from './adminService';

jest.mock('axios');

describe('AdminService', () => {
  it('should fetch unauthorized authors list', async () => {
    const mockedData = [{ /* your mocked author data here */ }];
    axios.get.mockResolvedValue({ data: mockedData });

    const { result, waitForNextUpdate } = renderHook(() => useAdminService());

    // Wait for the initial data to be fetched
    await waitForNextUpdate();

    expect(result.current.unauthorizedAuthors).toEqual(mockedData);
  });
});
