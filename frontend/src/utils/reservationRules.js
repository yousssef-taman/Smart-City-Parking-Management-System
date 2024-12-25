export const RESERVATION_RULES = {
    MAX_DURATION: 24, // hours
    MIN_NOTICE: 15, // minutes
    GRACE_PERIOD: 15, // minutes
    NO_SHOW_PENALTY: 10, // dollars
    CANCELLATION_WINDOW: 60, // minutes before start time
};

export function validateReservation(startTime, duration) {
    const now = new Date();
    const start = new Date(startTime);
    const minutesUntilStart = (start.getTime() - now.getTime()) / (1000 * 60);

    if (minutesUntilStart < RESERVATION_RULES.MIN_NOTICE) {
        return {
            valid: false,
            message: `Reservations must be made at least ${RESERVATION_RULES.MIN_NOTICE} minutes in advance`,
        };
    }

    if (duration > RESERVATION_RULES.MAX_DURATION) {
        return {
            valid: false,
            message: `Reservations cannot exceed ${RESERVATION_RULES.MAX_DURATION} hours`,
        };
    }

    return { valid: true };
}