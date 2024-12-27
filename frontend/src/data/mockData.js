export const initialParkingLots = [
    {
        id: 1,
        name: 'Downtown Plaza Parking',
        location: '123 Main Street',
        capacity: {
            regular: 100,
            disabled: 10,
            ev: 5,
        },
        basePrice: 5.00,
        peakHours: {
            start: 9,
            end: 17,
        },
        peakMultiplier: 1.5,
        spots: Array(115).fill(null).map((_, index) => ({
            id: `spot-${index + 1}`,
            type: index < 100 ? 'regular' : index < 110 ? 'disabled' : 'ev',
            status: Math.random() > 0.7 ? 'occupied' : Math.random() > 0.5 ? 'available' : 'reserved',
            floor: Math.floor(index / 20) + 1,
            spotNumber: `${Math.floor(index / 20) + 1}-${(index % 20) + 1}`,
        })),
    },
];

// frontend/src/data/mockData.js

export const initialReservations = [
    {
        id: 1,
        user: {
            id: 1,
            name: 'John Doe',
        },
        parkingLot: {
            id: 1,
            name: 'Main Street Parking',
        },
        spotNumber: '1-1',
        startTime: '2023-11-01T08:00:00Z',
        duration: 2,
        status: 'pending',
    },
    {
        id: 2,
        user: {
            id: 2,
            name: 'Jane Smith',
        },
        parkingLot: {
            id: 2,
            name: 'Downtown Parking',
        },
        spotNumber: '2-5',
        startTime: '2023-11-01T09:00:00Z',
        duration: 3,
        status: 'approved',
    },
    {
        id: 3,
        user: {
            id: 3,
            name: 'Alice Johnson',
        },
        parkingLot: {
            id: 1,
            name: 'Main Street Parking',
        },
        spotNumber: '1-10',
        startTime: '2023-11-01T10:00:00Z',
        duration: 1,
        status: 'rejected',
    },
];