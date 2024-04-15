import React from 'react';
import { BrowserRouter as Router, Route,Routes } from 'react-router-dom';
import HomeComponent from './components/admin/home'; 
import AuthorVerificationComponent from './components/admin/author-verification';
import BookVerificationComponent from './components/admin/book-verification';
//import AdminLoginComponent from './components/admin/admin-login/AdminLoginComponent';
import AdminLoginComponent from './components/admin/admin-login';

import CreateEbooksComponent from './components/admin/create-ebooks';
//import AdminForgotpasswordComponent from './components/admin/admin-forgotpassword/AdminForgotpasswordComponent';
import AdminForgotPassword from './components/admin/admin-forgotpassword';
import AuthorForgotpasswordComponent from './components/author/author-forgotpassword';
import AuthorRegisterComponent from './components/author/author-register';
import AuthorLoginComponent from './components/author/author-login';
//import AuthorForgotpasswordComponent from './components/author/author-forgotpassword/AuthorForgotpasswordComponent';
import RegisterComponent from './components/user/register';
import LoginComponent from './components/user/login';
import ForgotpasswordComponent from './components/user/forgotpassword';
import MainhomeComponent from './components/main-home/mainhome';
//import MainheaderComponent from './components/main-home/mainheader/MainheaderComponent';
//import MainHeader from './components/main-home/mainheader';
import UserHomeComponent from './components/user/user-home';
//import UserHeaderComponent from './components/user/user-header';
import AuthorHomeComponent from './components/author/author-home';
//import AuthorHeaderComponent from './components/author/author-header';
import ViewEbookComponent from './components/user/view-ebook';
import CreatebookComponent from './components/author/createbook';
import AuthorInputComponent from './components/author/author-input';
import MyBooksComponent from './components/author/my-books';
import UpdateBookComponent from './components/author/update-book';
import AdminViewEbookComponent from './components/admin/admin-view-ebook';


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/admin-home" element={<HomeComponent/>} />
        <Route path="/author-verification" element={<AuthorVerificationComponent/>} />
        <Route path="/book-verification" element={<BookVerificationComponent/>} />
        <Route path="/create-ebooks" element={<CreateEbooksComponent/>} />
        <Route path="/admin-login" element={<AdminLoginComponent/>} />
        <Route path="/admin-forgotpassword" element={<AdminForgotPassword/>} />
        <Route path="/author-login" element={<AuthorLoginComponent/>} />
        <Route path="/author-register" element={<AuthorRegisterComponent/>} />
        <Route path="/author-forgotpassword" element={<AuthorForgotpasswordComponent/>} />
        <Route path="/login" element={<LoginComponent/>} />
        <Route path="/register" element={<RegisterComponent/>} />
        <Route path="/forgotpassword" element={<ForgotpasswordComponent/>} />
        <Route path="/" element={<MainhomeComponent/>} />
        <Route path="/user-home" element={<UserHomeComponent/>} />
        <Route path="/author-home" element={<AuthorHomeComponent/>} />
        <Route path="/view-ebook/:bookId" element={<ViewEbookComponent/>} />
        <Route path="/admin-view-ebook/:bookId" element={<AdminViewEbookComponent/>} />
        <Route path="/create-book" element={<CreatebookComponent/>} />
        <Route path="/mybooks" element={<AuthorInputComponent/>} />
        <Route path="/book-dashboard/:authorId" element={<MyBooksComponent/>} />
        <Route path="/update-book" element={<UpdateBookComponent/>} />
      </Routes>
    </Router>
  );
}

export default App;



