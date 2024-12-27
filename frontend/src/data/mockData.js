export const initialParkingLots = [
    {
        id: '1',
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