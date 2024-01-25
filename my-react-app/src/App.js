import React from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Navbar from './Navbar';
import Home from './Home';
import { BrowserRouter as Router,Routes, Route}from "react-router-dom";
import AddUser from './AddUser';
import EditUser from './EditUser';
import ViewUser from './ViewUser';

function App(){
  return (
    <div className='App'>
    <Router>
    <Navbar />
    <Routes>
    
      <Route exact path="/" element={<Home />} />
      <Route exact path="/adduser" element={<AddUser />} />
      <Route exact path="/edituser/:id" element={<EditUser />} />
      <Route exact path="/viewuser/:id" element={<ViewUser />} />
    </Routes>


    </Router>


    
 


    </div>
  );
}

export default App;
