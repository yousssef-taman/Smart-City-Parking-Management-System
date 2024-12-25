import React from 'react';
import { MapPin, Clock, CreditCard, Shield, Smartphone, BarChart } from 'lucide-react';

const features = [
    {
        name: 'Real-time Availability',
        description: 'Find available parking spots in real-time with our advanced sensors and mapping system.',
        icon: MapPin,
    },
    {
        name: 'Easy Booking',
        description: 'Reserve your parking spot in advance with just a few taps on your smartphone.',
        icon: Clock,
    },
    {
        name: 'Contactless Payment',
        description: 'Secure and convenient digital payment options for hassle-free transactions.',
        icon: CreditCard,
    },
    {
        name: 'Enhanced Security',
        description: '24/7 monitoring and smart security features to keep your vehicle safe.',
        icon: Shield,
    },
    {
        name: 'Mobile App',
        description: 'Manage everything from our user-friendly mobile application.',
        icon: Smartphone,
    },
    {
        name: 'Analytics',
        description: 'Get insights about parking patterns and usage statistics.',
        icon: BarChart,
    },
];

export default function Features() {
    return (
        <div id="features" className="py-24 bg-gray-50">
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div className="text-center">
                    <h2 className="text-3xl font-extrabold text-gray-900 sm:text-4xl">
                        Smart Features for Modern Cities
                    </h2>
                    <p className="mt-4 max-w-2xl mx-auto text-xl text-gray-500">
                        Our comprehensive parking management system brings intelligence to urban parking.
                    </p>
                </div>

                <div className="mt-20 grid grid-cols-1 gap-8 sm:grid-cols-2 lg:grid-cols-3">
                    {features.map((feature) => (
                        <div key={feature.name} className="relative bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow">
                            <div>
                                <feature.icon className="h-12 w-12 text-blue-600" />
                                <h3 className="mt-4 text-xl font-medium text-gray-900">{feature.name}</h3>
                                <p className="mt-2 text-base text-gray-500">{feature.description}</p>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}