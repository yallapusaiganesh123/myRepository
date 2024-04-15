// Routes.jsx
import { BrowserRouter as Router, Route, Switch,Routes } from 'react-router-dom';

import HomeComponent from './components/admin/home';
import AuthorVerificationComponent from './components/admin/author-verification';
import BookVerificationComponent from './components/admin/book-verification';
import CreateEbooksComponent from './components/admin/create-ebooks';
import AdminForgotPassword from './components/admin/admin-forgotpassword';
import AdminLoginComponent from './components/admin/admin-login';
import AuthorRegisterComponent from './components/author/author-register';
import AuthorLoginComponent from './components/author/author-login';
import AuthorForgotpasswordComponent from './components/author/author-forgotpassword';
import ForgotpasswordComponent from './components/user/forgotpassword';
import LoginComponent from './components/user/login';
import RegisterComponent from './components/user/register';
import MainHome from './components/main-home/mainhome';
import UserHomeComponent from './components/user/user-home';
import AuthorHomeComponent from './components/author/author-home';
import ViewEbookComponent from './components/user/view-ebook';
import CreateEbooksComponent from './components/author/createbook';
import AuthorInputComponent from './components/author/author-input';
import MyBooksComponent from './components/author/my-books';
import UpdateBookComponent from './components/author/update-book';
import AdminViewEbookComponent from './components/admin/admin-view-ebook';
const Routess = () => {
  return (
    <Router>
      <Routes>
        <Route path="/admin-home" component={HomeComponent} />
        <Route path="/author-verification" component={AuthorVerificationComponent} />
        <Route path="/book-verification" component={BookVerificationComponent} />
        <Route path="/create-ebooks" component={CreateEbooksComponent} />
        <Route path="/admin-login" component={AdminLoginComponent} />
        <Route path="/admin-forgotpassword" component={AdminForgotPassword} />
        <Route path="/author-login" component={AuthorLoginComponent} />
        <Route path="/author-register" component={AuthorRegisterComponent} />
        <Route path="/author-forgotpassword" component={AuthorForgotpasswordComponent} />
        <Route path="/login" component={LoginComponent} />
        <Route path="/register" component={RegisterComponent} />
        <Route path="/forgotpassword" component={ForgotpasswordComponent} />
        <Route path="/" element={<MainHome/>} exact/>
        <Route path="/user-home" component={UserHomeComponent} />
        <Route path="/author-home" component={AuthorHomeComponent} />
        <Route path="/view-ebook/:bookId" component={ViewEbookComponent} />
        <Route path="/admin-view-ebook/:bookId" component={AdminViewEbookComponent} />
        <Route path="/create-book" component={CreatebooksComponent} />
        <Route path="/mybooks" component={AuthorInputComponent} />
        <Route path="/book-dashboard/:authorId" component={MyBooksComponent} />
        <Route path="/update-book" component={UpdateBookComponent} />
      </Routes>
    </Router>
  );
};

export default Routes;
