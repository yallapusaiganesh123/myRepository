// author.jsx
export const initialAuthorState = {
    email: '',
    authorname: '',
    password: '',
    age: '',
    about: ''
  };
  
  // author.test.jsx
  import { initialAuthorState } from './author';
  
  describe('Author', () => {
    it('should create an instance', () => {
      const author = { ...initialAuthorState };
      expect(author).toBeTruthy();
    });
  });
  