import {Building2, Car, Edit2, MapPin, Trash2, Zap} from 'lucide-react';

export function ParkingLotCard({ parkingLot, onSelect, onUpdate, onDelete  }) {
    const availableSpots = parkingLot.spots.filter(spot => spot.status === 'available').length;
    const currentHour = new Date().getHours();
    const isPeakHour = currentHour >= parkingLot.peakHours.start && currentHour <= parkingLot.peakHours.end;
    const currentPrice = isPeakHour ? parkingLot.basePrice * parkingLot.peakMultiplier : parkingLot.basePrice;
    const currentUser = JSON.parse(localStorage.getItem('user'));

    return (
        <div className="bg-white rounded-lg shadow-md p-6 hover:shadow-lg transition-shadow">
            <div className="flex justify-between items-start mb-4">
                <div>
                    <div className={"flex gap-2"}>
                        <Building2 className="w-6 h-6 text-blue-600"/>
                        <h3 className="text-xl font-semibold text-gray-900">{parkingLot.name}</h3>
                    </div>
                    <div className="flex items-center text-gray-600 mt-1">
                        <MapPin className="w-4 h-4 mr-1"/>
                        <span className="text-sm">{parkingLot.location}</span>
                    </div>
                </div>
                {currentUser.role === 'manager' && (
                    <div className="flex space-x-2">
                        <button
                            onClick={() => onUpdate(parkingLot)}
                            className="text-blue-600 hover:text-blue-800"
                        >
                            <Edit2 className="w-5 h-5"/>
                        </button>
                        <button
                            onClick={() => onDelete(parkingLot.id)}
                            className="text-red-600 hover:text-red-800"
                        >
                            <Trash2 className="w-5 h-5"/>
                        </button>
                    </div>
                )
                }

            </div>

            <div className="grid grid-cols-3 gap-4 mb-4">
                <div className="text-center p-2 bg-gray-50 rounded">
                    <Car className="w-5 h-5 mx-auto mb-1 text-gray-600"/>
                    <div className="text-sm font-medium">{parkingLot.capacity.regular}</div>
                    <div className="text-xs text-gray-500">Regular</div>
                </div>
                <div className="text-center p-2 bg-gray-50 rounded">
                    <Car className="w-5 h-5 mx-auto mb-1 text-blue-600"/>
                    <div className="text-sm font-medium">{parkingLot.capacity.disabled}</div>
                    <div className="text-xs text-gray-500">Disabled</div>
                </div>
                <div className="text-center p-2 bg-gray-50 rounded">
                    <Zap className="w-5 h-5 mx-auto mb-1 text-green-600" />
                    <div className="text-sm font-medium">{parkingLot.capacity.ev}</div>
                    <div className="text-xs text-gray-500">EV</div>
                </div>
            </div>

            <div className="flex justify-between items-center mb-4">
                <div>
                    <span className="text-2xl font-bold text-gray-900">${currentPrice.toFixed(2)}</span>
                    <span className="text-sm text-gray-500">/hour</span>
                    {isPeakHour && (
                        <span className="ml-2 text-xs font-medium text-orange-600 bg-orange-100 px-2 py-1 rounded">
              Peak Hours
            </span>
                    )}
                </div>
                <div className="text-right">
                    <div className="text-sm font-medium text-gray-900">{availableSpots} spots</div>
                    <div className="text-xs text-gray-500">available</div>
                </div>
            </div>

            <button
                onClick={() => onSelect(parkingLot)}
                className="w-full bg-blue-600 text-white py-2 px-4 rounded hover:bg-blue-700 transition-colors"
            >
                View Details
            </button>
        </div>
    );
}