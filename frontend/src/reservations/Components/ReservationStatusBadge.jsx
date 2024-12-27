import React from 'react';

const STATUS_STYLES = {
    pending: 'bg-yellow-100 text-yellow-800',
    approved: 'bg-green-100 text-green-800',
    rejected: 'bg-red-100 text-red-800',
    completed: 'bg-gray-100 text-gray-800',
    cancelled: 'bg-gray-100 text-gray-800',
};

export function ReservationStatusBadge({ status }) {
    return (
        <span className={`px-3 py-1 rounded-full text-sm font-medium ${STATUS_STYLES[status]}`}>
      {status.charAt(0).toUpperCase() + status.slice(1)}
    </span>
    );
}