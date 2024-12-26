import React, { useState } from 'react';
import { ParkingLotCard } from './components/ParkingLotCard';
import { ParkingLotDetails } from './components/ParkingLotDetails';
import { initialParkingLots } from '../data/mockData';
import { Building } from 'lucide-react';

export const ParkingLotProfiles = () => {
    const [selectedLot, setSelectedLot] = useState(null);
    const [parkingLots, setParkingLots] = useState(initialParkingLots);

    const updateSpotStatus = (lotId, spotId, newStatus) => {
        setParkingLots(parkingLots.map(lot => {
            if (lot.id === lotId) {
                return {
                    ...lot,
                    spots: lot.spots.map(spot =>
                        spot.id === spotId ? { ...spot, status: newStatus } : spot
                    )
                };
            }
            return lot;
        }));
    };

    return (
        <div className="min-h-screen bg-gray-100 pt-16">
            <header className="bg-white shadow">
                <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-6">
                    <div className="flex items-center">
                        <Building className="w-8 h-8 text-blue-600 mr-3" />
                        <h1 className="text-3xl font-bold text-gray-900">
                            Parking Lot Management
                        </h1>
                    </div>
                </div>
            </header>

            <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
                {selectedLot ? (
                    <ParkingLotDetails
                        parkingLot={selectedLot}
                        onBack={() => setSelectedLot(null)}
                        updateSpotStatus={updateSpotStatus}
                        userRole={'driver'}
                    />
                ) : (
                    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                        {parkingLots.map(lot => (
                            <ParkingLotCard
                                key={lot.id}
                                parkingLot={lot}
                                onSelect={setSelectedLot}
                            />
                        ))}
                    </div>
                )}
            </main>
        </div>
    );
}