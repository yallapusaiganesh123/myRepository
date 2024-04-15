// ebookService.test.js
import axios from 'axios';
import { renderHook, act } from '@testing-library/react-hooks';
import { useEbookService } from './ebookService';

jest.mock('axios');

describe('EbookService', () => {
  it('should fetch the list of ebooks', async () => {
    const mockedData = [{ /* your mocked ebook data here */ }];
    axios.get.mockResolvedValue({ data: mockedData });

    const { result, waitForNextUpdate } = renderHook(() => useEbookService());

    // Wait for the initial data to be fetched
    await waitForNextUpdate();

    expect(result.current.ebooks).toEqual(mockedData);
  });

  it('should view an ebook', async () => {
    const mockedData = { /* your mocked ebook data here */ };
    axios.get.mockResolvedValue({ data: mockedData });

    const { result } = renderHook(() => useEbookService());

    await act(async () => {
      const ebook = await result.current.viewEbook('bookId123');
      expect(ebook).toEqual(mockedData);
    });
  });

  // Add more tests for other functions in your service...
});
