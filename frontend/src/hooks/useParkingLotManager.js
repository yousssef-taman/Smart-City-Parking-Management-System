
export function useParkingLotManager() {
    const createLot = async (lotData,capacity) => {
        try {
            const response = await fetch('http://localhost:8080/api/parkinglots', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(lotData),
            });
            if (!response.ok) throw new Error('Failed to create parking lot');
            const data = await response.json();
            createSpots({lotId: data, type: 1,}, capacity.regular);
            createSpots({lotId: data, type: 2,}, capacity.disabled);
            createSpots({lotId: data, type: 3,}, capacity.ev)
            return data;
        } catch (err) {
            console.error(err);
            throw err;
        }
    };

    const updateLot = async (lotData) => {
        try {
            const response = await fetch(`http://localhost:8080/api/parking-lots/${lotData.id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(lotData),
            });
            if (!response.ok) throw new Error('Failed to update parking lot');
            return await response.json();
        } catch (err) {
            console.error(err);
            throw err;
        }
    };

    const deleteLot = async (lotId) => {
        try {
            const response = await fetch(`http://localhost:8080/api/parking-lots/${lotId}`, {
                method: 'DELETE',
            });
            if (!response.ok) throw new Error('Failed to delete parking lot');
        } catch (err) {
            console.error(err);
            throw err;
        }
    };

    const createSpots = async (spot, capacity) => {
        try {
            const response = await fetch(`http://localhost:8080/api/spot/create?capacity=${capacity}`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(spot),
            });
            if (!response.ok) throw new Error('Failed to create spots');

        } catch (err) {
            console.error(err);
            throw err;
        }
    };

    return { createLot, updateLot, deleteLot,createSpots };
}