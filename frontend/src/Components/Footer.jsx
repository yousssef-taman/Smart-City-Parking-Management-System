import React from 'react';
import { Car } from 'lucide-react';

export default function Footer() {
    return (
        <footer className="bg-gray-900">
            <div className="max-w-7xl mx-auto py-12 px-4 sm:px-6 lg:px-8">
                <div className="flex items-center justify-center">
                    <Car className="h-8 w-8 text-white" />
                    <span className="ml-2 text-xl font-bold text-white">SmartPark</span>
                </div>
                <nav className="mt-8">
                    <div className="flex justify-center space-x-6">
                        <a href="#" className="text-gray-400 hover:text-gray-300">About</a>
                        <a href="/frontend/public#features" className="text-gray-400 hover:text-gray-300">Features</a>
                        <a href="#" className="text-gray-400 hover:text-gray-300">Pricing</a>
                        <a href="/frontend/public#contact" className="text-gray-400 hover:text-gray-300">Contact</a>
                    </div>
                </nav>
                <p className="mt-8 text-center text-base text-gray-400">
                    © {new Date().getFullYear()} SmartPark. All rights reserved.
                </p>
            </div>
        </footer>
    );
}