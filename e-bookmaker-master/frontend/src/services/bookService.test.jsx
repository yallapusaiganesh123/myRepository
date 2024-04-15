// booksService.test.js
import { renderHook, act } from '@testing-library/react-hooks';
import axios from 'axios';
import { useBooksServices } from './booksService';

jest.mock('axios');

describe('BooksService', () => {
  it('should fetch non-verified books list', async () => {
    const mockedData = [{ /* your mocked non-verified book data here */ }];
    axios.get.mockResolvedValue({ data: mockedData });

    const { result, waitForNextUpdate } = renderHook(() => useBooksServices());

    // Wait for the initial data to be fetched
    await waitForNextUpdate();

    expect(result.current.nonVerifiedBooks).toEqual(mockedData);
  });

  // ... other test cases
});
