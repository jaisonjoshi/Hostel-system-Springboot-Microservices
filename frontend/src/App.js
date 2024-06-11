import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Notification from './components/Notification';
import AdminDetails from './pages/Admin/AdminDetails';
import CreateRoom from './pages/Admin/CreateRoom';
import HostelDetails from './pages/Admin/HostelDetails';
import Login from './pages/Admin/Login';
import Payment from './pages/Admin/Payment';
import Register from './pages/Admin/Register';
import ResidentDetails from './pages/Admin/ResidentDetails';
import ResidentRegistration from './pages/Admin/ResidentRegistration';
import RoomDetails from './pages/Admin/RoomDetails';
import StudentLogin from './pages/student/Login';
import UserHostelDetails from './pages/student/UserHostelDetails';
import UserPayment from './pages/student/UserPayment';
import Dashboard from './pages/Admin/Dashboard';
import UserDashboard from './pages/student/UserDashboard';
import PrivateRoutesAdmin from './util/PrivateRoutesAdmin';
import PrivateRoutesUser from './util/PrivateRoutesUser';

function App() {
  
  return (
      <div className="App">
        <Notification />
        <Routes>
          <Route element={<PrivateRoutesAdmin />} >
          <Route path="/admin" >

            <Route path="dashboard" element={<Dashboard />} />
            <Route path='edit-admin-details' element={<AdminDetails />} />
            <Route path='create-room' element={<CreateRoom />} />
            <Route path="resident-registration" element={<ResidentRegistration />} />
            <Route path="hostel-details" element={<HostelDetails />} />
            <Route path="room-details" element={<RoomDetails />} />
            <Route path="resident-details" element={<ResidentDetails />} />
            <Route path="payment" element={<Payment />} />
          </Route>
          </Route>


          <Route path='/admin' >
            <Route path="register" element={<Register />} />
            <Route path="login" element={<Login />} />
          </Route>


          <Route element={<PrivateRoutesUser />} >

          <Route path='/' >
            <Route path="dashboard" element={<UserDashboard />} />
            <Route path='hostel-details' element={<UserHostelDetails />} />
            <Route path='payment' element={<UserPayment />} />

          </Route>

          </Route>

          <Route path="/login" element={<StudentLogin />} />



         






        </Routes>
      </div>



  );
}

export default App;
