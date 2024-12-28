import React from 'react';
import { Clock, MapPin, User, Car } from 'lucide-react';
import { ReservationStatusBadge } from './ReservationStatusBadge.jsx';
import { formatDateTime } from '../../utils/dateUtils.js';

export function ReservationList({ reservations, onApprove, onReject }) {
    const currentUser=JSON.parse(localStorage.getItem('user'));
    return (
        <div className="space-y-4">
            {reservations.map(reservation => (
                <div key={reservation.id} className="bg-white rounded-lg shadow-md p-6">
                    <div className="flex justify-between items-start mb-4">
                        <div className="space-y-2">
                            <div className="flex items-center space-x-2">
                                <User className="w-5 h-5 text-gray-600" />
                                <span className="font-semibold">{reservation.user.name}</span>
                            </div>
                            <div className="flex items-center space-x-2 text-gray-600">
                                <MapPin className="w-4 h-4" />
                                <span>{reservation.parkingLot.name}</span>
                            </div>
                            <div className="flex items-center space-x-2 text-gray-600">
                                <Car className="w-4 h-4" />
                                <span>Spot {reservation.spotNumber}</span>
                            </div>
                            <div className="flex items-center space-x-2 text-gray-600">
                                <Clock className="w-4 h-4" />
                                <span>{formatDateTime(reservation.startTime)} ({reservation.duration}h)</span>
                            </div>
                        </div>
                        <ReservationStatusBadge status={reservation.status} />
                    </div>

                    {reservation.status === 'pending' && currentUser.role === 'manager' && (
                        <div className="flex space-x-3">
                            <button
                                onClick={() => onApprove(reservation.id)}
                                className="flex-1 bg-green-600 text-white px-4 py-2 rounded-lg hover:bg-green-700 transition-colors"
                            >
                                Approve
                            </button>
                            <button
                                onClick={() => onReject(reservation.id)}
                                className="flex-1 bg-red-600 text-white px-4 py-2 rounded-lg hover:bg-red-700 transition-colors"
                            >
                                Reject
                            </button>
                        </div>
                    )}
                </div>
            ))}
        </div>
    );
}