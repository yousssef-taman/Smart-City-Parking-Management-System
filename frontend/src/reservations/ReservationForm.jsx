import React, { useState } from 'react';
import axios from 'axios';

export function ReservationForm({ parkingLot, spotId, onReserve }) {
    const [startTime, setStartTime] = useState('');
    const [duration, setDuration] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        const token = localStorage.getItem('token');
        if (!token) {
            alert('You must be logged in to make a reservation');
            return;
        }

        const reservationData = {
            parkingLotId: parkingLot.id,
            spotId,
            startTime,
            duration,
        };

        try {
            const res = await axios.post('/api/reservations', reservationData, {
                headers: { 'x-auth-token': token }
            });
            onReserve(res.data);
        } catch (err) {
            console.error(err.response.data);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="bg-white p-6 rounded-lg shadow-md">
            <h2 className="text-2xl font-bold mb-4">Reserve Spot {spotId}</h2>
            <div className="mb-4">
                <label className="block text-gray-700">Start Time</label>
                <input
                    type="datetime-local"
                    value={startTime}
                    onChange={(e) => setStartTime(e.target.value)}
                    className="w-full p-2 border rounded"
                    required
                />
            </div>
            <div className="mb-4">
                <label className="block text-gray-700">Duration (hours)</label>
                <input
                    type="number"
                    value={duration}
                    onChange={(e) => setDuration(e.target.value)}
                    className="w-full p-2 border rounded"
                    required
                    min={1}
                />
            </div>
            <div className="mb-4 p-4 bg-gray-50 rounded-lg">
                <div className="flex justify-between mb-2">
                    <span>Base Price</span>
                    <span>${parkingLot.basePrice.toFixed(2)}/hour</span>
                </div>
                <div className="flex justify-between font-bold">
                    <span>Total</span>
                    <span>${(parkingLot.basePrice * duration).toFixed(2)}</span>
                </div>
            </div>
            <button type="submit" className="bg-blue-600 text-white py-2 px-4 rounded hover:bg-blue-700">
                Reserve
            </button>
        </form>
    );
}