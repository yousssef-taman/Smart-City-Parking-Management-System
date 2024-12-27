import React, { useState } from 'react';
import { Filter } from 'lucide-react';
import { ReservationList } from './Components/ReservationList.jsx';
import { ReservationFilters } from './Components/ReservationFilters.jsx';

export function ReservationManagement({ reservations }) {
    const [filters, setFilters] = useState({
        status: 'all',
        parkingLot: 'all',
        date: 'all',
    });

    const [showFilters, setShowFilters] = useState(false);

    const filteredReservations = reservations.filter(reservation => {
        if (filters.status !== 'all' && reservation.status !== filters.status) return false;
        if (filters.parkingLot !== 'all' && reservation.parkingLot.id !== filters.parkingLot) return false;
        return !(filters.date === 'upcoming' && new Date(reservation.startTime) < new Date());

    });

    const onApprove = (reservationId) => {
        console.log('Approving reservation', reservationId);
        // Make an API call to approve the reservation
    }

    const onReject = (reservationId) => {
        console.log('Rejecting reservation', reservationId);
        // Make an API call to reject the reservation
    }

    return (
        <div className="space-y-6 min-h-screen  max-w-7xl mx-auto py-20">
            <div className="flex justify-between items-center">
                <h2 className="text-2xl font-bold text-gray-900">Reservation Management</h2>
                <button
                    onClick={() => setShowFilters(!showFilters)}
                    className="flex items-center text-gray-600 hover:text-gray-900"
                >
                    <Filter className="w-5 h-5 mr-2" />
                    Filters
                </button>
            </div>

            {showFilters && (
                <ReservationFilters filters={filters} onFilterChange={setFilters} />
            )}

            {filteredReservations.length === 0 ? (
                <div className="text-center py-12 bg-white rounded-lg shadow-md">
                    <p className="text-gray-500">No reservations found matching your filters.</p>
                </div>
            ) : (
                <ReservationList
                    reservations={filteredReservations}
                    onApprove={onApprove}
                    onReject={onReject}
                />
            )}
        </div>
    );
}