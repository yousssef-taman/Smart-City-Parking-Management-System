import React from 'react';
import { Car } from 'lucide-react';
import { useNavigate } from 'react-router-dom';

export default function Navbar() {
    const navigate = useNavigate();
    const isLogin = localStorage.getItem('user') !== null;
    const Logout = () => {
        localStorage.removeItem('user');
        navigate('/');
    }

    return (
        <nav className="bg-white shadow-sm fixed w-full z-10">
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div className="flex justify-between h-16">
                    <div className="flex items-center cursor-pointer"
                         onClick={() => navigate('/')}
                    >
                        <Car className="h-8 w-8 text-blue-600"/>
                        <span className="ml-2 text-xl font-bold text-gray-900">SmartPark</span>
                    </div>
                    <div className="hidden md:flex items-center space-x-8">
                        <a href="/frontend/public#features" className="text-gray-600 hover:text-blue-600">Features</a>
                        <a href="/frontend/public#stats" className="text-gray-600 hover:text-blue-600">Stats</a>
                        <a href="/frontend/public#contact" className="text-gray-600 hover:text-blue-600">Contact</a>
                        {isLogin ? (
                            <button
                                className="text-gray-600 hover:text-blue-600"
                                onClick={Logout}
                            >
                                Logout
                            </button>
                        ) : (
                            <button
                                className="bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700"
                                onClick={() => navigate('/signup')}
                            >
                                Get Started
                            </button>
                        )}

                    </div>
                </div>
            </div>
        </nav>
    );
}