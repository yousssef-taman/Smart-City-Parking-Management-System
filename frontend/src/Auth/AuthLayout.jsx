import React from 'react';
import { Car } from 'lucide-react';
import PropTypes from "prop-types";


export default function AuthLayout(Props) {
    return (
        <div className="min-h-screen bg-gray-50 flex flex-col justify-center py-12 sm:px-6 lg:px-8">
            <div className="sm:mx-auto sm:w-full sm:max-w-md">
                <div className="flex justify-center">
                    <Car className="h-12 w-12 text-blue-600" />
                </div>
                <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">{Props.title}</h2>
            </div>
            <div className="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
                <div className="bg-white py-8 px-4 shadow sm:rounded-lg sm:px-10">
                    {Props.children}
                </div>
            </div>
        </div>
    );
}

AuthLayout.propTypes  = {
    title: PropTypes.string.isRequired,
    children: PropTypes.node.isRequired,
}