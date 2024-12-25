const ParkingSpotType = ['regular', 'disabled', 'ev'];

const ParkingSpotStatus = ['available', 'occupied', 'reserved'];

const ParkingSpot = {
    id: String,
    type: ParkingSpotType,
    status: ParkingSpotStatus,
    floor: Number,
    spotNumber: String,
};

const ParkingLot = {
    id: String,
    name: String,
    location: String,
    capacity: {
        regular: Number,
        disabled: Number,
        ev: Number,
    },
    basePrice: Number,
    spots: [ParkingSpot],
    peakHours: {
        start: Number,
        end: Number,
    },
    peakMultiplier: Number,
};

module.exports = { ParkingSpotType, ParkingSpotStatus, ParkingSpot, ParkingLot };