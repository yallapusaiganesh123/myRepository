// admin.jsx
export class Admin {
    email = '';
    username = '';
    password = '';
  }
  
  // admin.test.jsx
  import { Admin } from './admin';
  
  describe('Admin', () => {
    it('should create an instance', () => {
      const admin = new Admin();
      expect(admin).toBeTruthy();
    });
  });
  