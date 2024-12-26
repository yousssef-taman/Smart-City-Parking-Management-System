import React from 'react';
import {useNavigate} from "react-router-dom";

export default function Hero() {
    const navigate = useNavigate();
    return (
        <div className="relative bg-white pt-16">
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 pt-20">
                <div className="text-center">
                    <h1 className="text-4xl tracking-tight font-extrabold text-gray-900 sm:text-5xl md:text-6xl">
                        <span className="block">Smart City Parking</span>
                        <span className="block text-blue-600">Made Simple</span>
                    </h1>
                    <p className="mt-3 max-w-md mx-auto text-base text-gray-500 sm:text-lg md:mt-5 md:text-xl md:max-w-3xl">
                        Find, book, and pay for parking spots in real-time. Our smart parking solution makes city parking effortless.
                    </p>
                    <div className="mt-5 max-w-md mx-auto sm:flex sm:justify-center md:mt-8">
                        <div className="rounded-md shadow">
                            <button
                                onClick={() => navigate('/signup')}
                                className="w-full flex items-center justify-center px-8 py-3 border border-transparent text-base font-medium rounded-md text-white bg-blue-600 hover:bg-blue-700 md:py-4 md:text-lg md:px-10">
                                Get Started
                            </button>
                        </div>
                        <div className="mt-3 rounded-md shadow sm:mt-0 sm:ml-3">
                            <a href="#" className="w-full flex items-center justify-center px-8 py-3 border border-transparent text-base font-medium rounded-md text-blue-600 bg-white hover:bg-gray-50 md:py-4 md:text-lg md:px-10">
                                Learn More
                            </a>
                        </div>
                    </div>
                </div>
                <div className="mt-16 relative">
                    <img
                        className="rounded-lg shadow-xl w-full object-cover h-[600px]"
                        src="https://images.unsplash.com/photo-1506521781263-d8422e82f27a?auto=format&fit=crop&q=80"
                        alt="Smart parking visualization"
                    />
                </div>
            </div>
        </div>
    );
}