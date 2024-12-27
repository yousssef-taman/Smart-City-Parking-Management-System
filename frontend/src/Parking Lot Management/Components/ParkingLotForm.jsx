import React, { useState } from 'react';
import { Building2, MapPin, Clock, DollarSign } from 'lucide-react';

export function ParkingLotForm({ parkingLot, onSubmit }) {
    const [formData, setFormData] = useState({
        name: parkingLot?.name || '',
        location: parkingLot?.location || '',
        capacity: {
            regular: parkingLot?.capacity?.regular || 0,
            disabled: parkingLot?.capacity?.disabled || 0,
            ev: parkingLot?.capacity?.ev || 0,
        },
        basePrice: parkingLot?.basePrice || 0,
        peakHours: {
            start: parkingLot?.peakHours?.start || 9,
            end: parkingLot?.peakHours?.end || 17,
        },
        peakMultiplier: parkingLot?.peakMultiplier || 1.5,
    });

    const handleSubmit = (e) => {
        e.preventDefault();
        const lot ={
            ...formData,
            spots: [
                ...Array(formData.capacity.regular).fill(null).map((_, index) => ({
                    id: `spot-${index + 1}`,
                    type: 'regular',
                    status: 'available',
                    floor: Math.floor(index / 20) + 1,
                    spotNumber: `${Math.floor(index / 20) + 1}-${(index % 20) + 1}`,
                })),
                ...Array(formData.capacity.disabled).fill(null).map((_, index) => ({
                    id: `spot-${formData.capacity.regular + index + 1}`,
                    type: 'disabled',
                    status: 'available',
                    floor: Math.floor((formData.capacity.regular + index) / 20) + 1,
                    spotNumber: `${Math.floor((formData.capacity.regular + index) / 20) + 1}-${((formData.capacity.regular + index) % 20) + 1}`,
                })),
                ...Array(formData.capacity.ev).fill(null).map((_, index) => ({
                    id: `spot-${formData.capacity.regular + formData.capacity.disabled + index + 1}`,
                    type: 'ev',
                    status: 'available',
                    floor: Math.floor((formData.capacity.regular + formData.capacity.disabled + index ) / 20) + 1,
                    spotNumber: `${Math.floor((formData.capacity.regular + formData.capacity.disabled + index ) / 20) + 1}-${((formData.capacity.regular + formData.capacity.disabled + index ) % 20) + 1}`,
                }))
            ]
            ,
        }
        onSubmit(lot);
    };

    return (
        <form onSubmit={handleSubmit} className="space-y-6">
            <div>
                <label className="block text-sm font-medium text-gray-700">
                    <div className="flex items-center mb-1">
                        <Building2 className="w-4 h-4 mr-2" />
                        Parking Lot Name
                    </div>
                    <input
                        type="text"
                        value={formData.name}
                        onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                        required
                    />
                </label>
            </div>

            <div>
                <label className="block text-sm font-medium text-gray-700">
                    <div className="flex items-center mb-1">
                        <MapPin className="w-4 h-4 mr-2" />
                        Location
                    </div>
                    <input
                        type="text"
                        value={formData.location}
                        onChange={(e) => setFormData({ ...formData, location: e.target.value })}
                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                        required
                    />
                </label>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                <div>
                    <label className="block text-sm font-medium text-gray-700">
                        Regular Spots
                        <input
                            type="number"
                            value={formData.capacity.regular}
                            onChange={(e) => setFormData({
                                ...formData,
                                capacity: { ...formData.capacity, regular: parseInt(e.target.value) }
                            })}
                            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                            min="0"
                            required
                        />
                    </label>
                </div>
                <div>
                    <label className="block text-sm font-medium text-gray-700">
                        Disabled Spots
                        <input
                            type="number"
                            value={formData.capacity.disabled}
                            onChange={(e) => setFormData({
                                ...formData,
                                capacity: { ...formData.capacity, disabled: parseInt(e.target.value) }
                            })}
                            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                            min="0"
                            required
                        />
                    </label>
                </div>
                <div>
                    <label className="block text-sm font-medium text-gray-700">
                        EV Spots
                        <input
                            type="number"
                            value={formData.capacity.ev}
                            onChange={(e) => setFormData({
                                ...formData,
                                capacity: { ...formData.capacity, ev: parseInt(e.target.value) }
                            })}
                            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                            min="0"
                            required
                        />
                    </label>
                </div>
            </div>

            <div>
                <label className="block text-sm font-medium text-gray-700">
                    <div className="flex items-center mb-1">
                        <DollarSign className="w-4 h-4 mr-2" />
                        Base Price (per hour)
                    </div>
                    <input
                        type="number"
                        value={formData.basePrice}
                        onChange={(e) => setFormData({ ...formData, basePrice: parseFloat(e.target.value) })}
                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                        min="0"
                        step="0.01"
                        required
                    />
                </label>
            </div>

            <div>
                <div className="flex items-center mb-1">
                    <Clock className="w-4 h-4 mr-2" />
                    <span className="text-sm font-medium text-gray-700">Peak Hours</span>
                </div>
                <div className="grid grid-cols-2 gap-4">
                    <label className="block text-sm font-medium text-gray-700">
                        Start Hour
                        <input
                            type="number"
                            value={formData.peakHours.start}
                            onChange={(e) => setFormData({
                                ...formData,
                                peakHours: { ...formData.peakHours, start: parseInt(e.target.value) }
                            })}
                            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                            min="0"
                            max="23"
                            required
                        />
                    </label>
                    <label className="block text-sm font-medium text-gray-700">
                        End Hour
                        <input
                            type="number"
                            value={formData.peakHours.end}
                            onChange={(e) => setFormData({
                                ...formData,
                                peakHours: { ...formData.peakHours, end: parseInt(e.target.value) }
                            })}
                            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                            min="0"
                            max="23"
                            required
                        />
                    </label>
                </div>
            </div>

            <div>
                <label className="block text-sm font-medium text-gray-700">
                    Peak Hour Price Multiplier
                    <input
                        type="number"
                        value={formData.peakMultiplier}
                        onChange={(e) => setFormData({ ...formData, peakMultiplier: parseFloat(e.target.value) })}
                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                        min="1"
                        step="0.1"
                        required
                    />
                </label>
            </div>

            <div className="flex justify-end">
                <button
                    type="submit"
                    className="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-colors"
                >
                    {parkingLot ? 'Update Parking Lot' : 'Create Parking Lot'}
                </button>
            </div>
        </form>
    );
}