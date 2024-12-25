import React, { useState, useEffect } from 'react';
import axios from 'axios';

export function Reservations() {
    const [reservations, setReservations] = useState([]);

    useEffect(() => {
        const fetchReservations = async () => {
            const token = localStorage.getItem('token');
            if (!token) {
                alert('You must be logged in to view your reservations');
                return;
            }

            try {
                const res = await axios.get('/api/reservations/myreservations', {
                    headers: { 'x-auth-token': token }
                });
                setReservations(res.data);
            } catch (err) {
                console.error(err.response.data);
            }
        };

        fetchReservations();
    }, []);

    return (
        <div className="min-h-screen bg-gray-100 pt-16">
            <header className="bg-white shadow">
                <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-6">
                    <h1 className="text-3xl font-bold text-gray-900">My Reservations</h1>
                </div>
            </header>

            <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
                {reservations.length > 0 ? (
                    <div className="grid grid-cols-1 gap-6">
                        {reservations.map(reservation => (
                            <div key={reservation._id} className="bg-white p-6 rounded-lg shadow-md">
                                <h2 className="text-2xl font-bold mb-4">{reservation.parkingLotId.name}</h2>
                                <p className="text-gray-600">Spot: {reservation.spotId}</p>
                                <p className="text-gray-600">Start Time: {new Date(reservation.startTime).toLocaleString()}</p>
                                <p className="text-gray-600">End Time: {new Date(reservation.endTime).toLocaleString()}</p>
                            </div>
                        ))}
                    </div>
                ) : (
                    <p className="text-gray-600">No reservations found.</p>
                )}
            </main>
        </div>
    );
}