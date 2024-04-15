// ebook.jsx
export const initialEbookState = {
    bookId: '',
    authorId: '',
    authorName: '',
    description: '',
    genre: '',
    content: ''
  };
  
  // ebook.test.jsx
  import { initialEbookState } from './ebook';
  
  describe('Ebook', () => {
    it('should create an instance', () => {
      const ebook = { ...initialEbookState };
      expect(ebook).toBeTruthy();
    });
  });
  