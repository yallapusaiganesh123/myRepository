// book.jsx
export const initialBookState = {
    id: '',
    authorId: '',
    authorName: '',
    description: '',
    genre: '',
    content: '',
    completed: false,
    verified: false
  };
  
  // book.test.jsx
  import { initialBookState } from './book';
  
  describe('Book', () => {
    it('should create an instance', () => {
      const book = { ...initialBookState };
      expect(book).toBeTruthy();
    });
  });
  