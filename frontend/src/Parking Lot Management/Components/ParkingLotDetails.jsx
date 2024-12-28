import React, { useState } from 'react';
import { ArrowLeft, Car } from 'lucide-react';
import { ReservationForm } from '../../reservations/ReservationForm';
import { validateReservation } from '../../utils/reservationRules';

export function ParkingLotDetails({ parkingLot, onBack, userRole ,spots }) {
    const [selectedSpot, setSelectedSpot] = useState(null);
    const [selectedSpotId, setSelectedSpotId] = useState(null);
    const [reservationError, setReservationError] = useState('');

    const handleReservation = (reservationData) => {
        const validation = validateReservation(reservationData.reservationTime, reservationData.duration);

        if (!validation.valid) {
            setReservationError(validation.message);
            return;
        }
        setSelectedSpotId(null);
        setSelectedSpot(null);

        // Here you would typically make an API call to create the reservation
        console.log('Creating reservation:', reservationData);
        setReservationError('');
    };
    const onSelect = (spot) => {
        if (spot.status === 'available') {
            setSelectedSpot(spot.id);
            setSelectedSpotId(spot.spotId);
        }
    }
    const getSpotColor = (spot) => {
        switch (spot.status) {
            case 'available':
                return 'bg-green-500';
            case 'occupied':
                return 'bg-red-500';
            case 'reserved':
                return 'bg-yellow-500';
            default:
                return 'bg-gray-500';
        }
    };

    const getSpotIcon = (spot) => {
        switch (spot.type) {
            case 'disabled':
                return '♿';
            case 'ev':
                return '⚡';
            default:
                return '';
        }
    };

    return (
        <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
            <div className="lg:col-span-2 bg-white rounded-lg shadow-lg p-6">
                <div className="flex items-center mb-6">
                    <button
                        onClick={onBack}
                        className="mr-4 text-gray-600 hover:text-gray-900"
                    >
                        <ArrowLeft className="w-6 h-6" />
                    </button>
                    <div>
                        <h2 className="text-2xl font-bold text-gray-900">{parkingLot.lotName}</h2>
                        <p className="text-gray-600">{parkingLot.location}</p>
                    </div>
                </div>

                <div className="grid grid-cols-1 gap-6">
                    {Array.from({ length: Math.ceil(spots.length / 20) }).map((_, floorIndex) => (
                        <div key={floorIndex} className="border rounded-lg p-4">
                            <h3 className="text-lg font-semibold mb-4">Floor {floorIndex + 1}</h3>
                            <div className="grid grid-cols-10 gap-2">
                                {spots
                                    .filter(spot => spot.floor === floorIndex + 1)
                                    .map(spot => (
                                        <div
                                            key={spot.id}
                                            className={`relative aspect-square rounded-lg ${getSpotColor(
                                                spot
                                            )} flex items-center justify-center text-white cursor-pointer hover:opacity-80 transition-opacity ${selectedSpot === spot.id ? 'ring-4 ring-blue-500' : ''}`}
                                            title={`Spot ${spot.spotNumber} - ${spot.status}`}
                                            onClick={() => onSelect(spot)}
                                        >
                                            <Car className="w-6 h-6" />
                                            {getSpotIcon(spot) && (
                                                <span className="absolute top-0 right-0 text-xs bg-white text-gray-900 rounded-full w-4 h-4 flex items-center justify-center">
                          {getSpotIcon(spot)}
                        </span>
                                            )}
                                        </div>
                                    ))}
                            </div>
                        </div>
                    ))}
                </div>

                <div className="mt-6 flex justify-between items-center p-4 bg-gray-50 rounded-lg">
                    <div className="flex gap-4">
                        <div className="flex items-center">
                            <div className="w-4 h-4 rounded-full bg-green-500 mr-2"></div>
                            <span className="text-sm">Available</span>
                        </div>
                        <div className="flex items-center">
                            <div className="w-4 h-4 rounded-full bg-red-500 mr-2"></div>
                            <span className="text-sm">Occupied</span>
                        </div>
                        <div className="flex items-center">
                            <div className="w-4 h-4 rounded-full bg-yellow-500 mr-2"></div>
                            <span className="text-sm">Reserved</span>
                        </div>
                    </div>
                    <div className="text-sm text-gray-600">
                        Updated in real-time
                    </div>
                </div>
            </div>

            {userRole === 'driver' && selectedSpotId && (
                <div className="lg:col-span-1">
                    {reservationError && (
                        <div className="mb-4 p-4 bg-red-100 text-red-700 rounded-lg">
                            {reservationError}
                        </div>
                    )}
                    <ReservationForm
                        parkingLot={parkingLot}
                        spotId={selectedSpotId}
                        onReserve={handleReservation}
                    />
                </div>
            )}
        </div>
    );
}