import React, { useState,useEffect } from 'react';
import { ParkingLotCard } from './components/ParkingLotCard';
import { ParkingLotDetails } from './components/ParkingLotDetails';
import { initialParkingLots } from '../data/mockData';
import {Building, Plus} from 'lucide-react';
import {ParkingLotForm} from "./Components/ParkingLotForm.jsx";
import {useParkingLotManager} from "../hooks/useParkingLotManager";

export const ParkingLotProfiles = () => {
    const currentUser = JSON.parse(localStorage.getItem('user'));
    const {createLot , updateLot, deleteLot, createSpots ,getAllLots} = useParkingLotManager();
    const [selectedLot, setSelectedLot] = useState(null);
    const [parkingLots, setParkingLots] = useState([]);

    const [isCreating, setIsCreating] = useState(false);
    const [editingLot, setEditingLot] = useState(null);

    // get all parking lots
    useEffect(() => {
        getAllLots().then(data => setParkingLots(data));
    }, []);

    const onDeleteLot = (lotId) => {
        setParkingLots(parkingLots.filter(lot => lot.id!== lotId));
    };
    const onUpdateLot = (lotData) => {
        setEditingLot(lotData);
    }
    const Update = (lotData) => {
        setParkingLots(parkingLots.map(lot => (lot.id === lotData.id ? lotData : lot)));
    }

    const Create = (lotData) => {
        setParkingLots([...parkingLots, { ...lotData, id: parkingLots.length + 1 }]);
    }
    const handleCreate = (lotData) => {
    console.log(parkingLots);
        Create(lotData);

        setIsCreating(false);
        const lot = {
            lotName: lotData.name,
            location: lotData.location,
            capacity: lotData.spots.length,
            pricingStructure: lotData.basePrice,
            managerId: currentUser.id,
            startPeekTime: lotData.peakHours.start,
            endPeekTime: lotData.peakHours.end,
            priceMultiplier: lotData.peakMultiplier,
        }
        createLot(lot,lotData.capacity) ;
    };
    const handleUpdate = (lotData) => {
        Update({ ...lotData, id: editingLot.id });
        setEditingLot(null);
    };

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
                        {!isCreating && !editingLot && currentUser.role === "manager" && (
                            <button
                                onClick={() => setIsCreating(true)}
                                className="flex items-center bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-colors ml-auto"
                            >
                                <Plus className="w-4 h-4 mr-2" />
                                Add New Lot
                            </button>
                        )}
                    </div>
                </div>
            </header>

            <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
                {isCreating && (
                    <div className="bg-white rounded-lg shadow-md p-6">
                        <h3 className="text-xl font-semibold mb-4">Create New Parking Lot</h3>
                        <ParkingLotForm onSubmit={handleCreate} />
                    </div>
                )}

                {editingLot && (
                    <div className="bg-white rounded-lg shadow-md p-6">
                        <h3 className="text-xl font-semibold mb-4">Edit Parking Lot</h3>
                        <ParkingLotForm parkingLot={editingLot} onSubmit={handleUpdate} />
                    </div>
                )}

                {selectedLot ? (
                    <ParkingLotDetails
                        parkingLot={selectedLot}
                        onBack={() => setSelectedLot(null)}
                        updateSpotStatus={updateSpotStatus}
                        userRole={currentUser.role}
                    />
                ) : (
                    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                        {parkingLots.map(lot => (
                            <ParkingLotCard
                                key={lot.id}
                                parkingLot={lot}
                                onSelect={setSelectedLot}
                                onUpdate={onUpdateLot}
                                onDelete={onDeleteLot}
                            />
                        ))}
                    </div>
                )}
            </main>
        </div>
    );
}