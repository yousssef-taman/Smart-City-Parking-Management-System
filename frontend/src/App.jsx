import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import {Landing} from "./Landing Pge/Landing.jsx";
import SignUpForm from "./Auth/SignUpForm.jsx";
import Navbar from "./Components/Navbar.jsx";
import Footer from "./Components/Footer.jsx";
import LoginForm from "./Auth/LoginForm.jsx";
import {ParkingLotProfiles} from "./Parking Lot Management/ParkingLotProfiles.jsx";
import {Reservations} from "./reservations/Reservations.jsx";
import {DriverProfile} from "./profiles/DriverProfile.jsx";

function App() {
    return (
        <Router>
            <Navbar/>
            <Routes>
                <Route path="*" element={<Landing />} />
                <Route path="/signup" element={<SignUpForm />} />
                <Route path="/login" element={<LoginForm />} />
                <Route path="/ParkingLotManagement" element={<ParkingLotProfiles />} />
                <Route path="/reservations" element={<Reservations />} />
                <Route path="/profile" element={<DriverProfile />} />

            </Routes>
            <Footer/>
        </Router>
    );
}

export default App;