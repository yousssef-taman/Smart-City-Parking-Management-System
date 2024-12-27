
export function useParkingLotManager() {
    const createLot = async (lotData) => {
        try {
            const response = await fetch('http://localhost:8080/api/parkinglots', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(lotData),
            });
            if (!response.ok) throw new Error('Failed to create parking lot');
            data = JSON.parse(response.body);
            console.log(data);
            console.log(typeof data);
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

    return { createLot, updateLot, deleteLot };
}