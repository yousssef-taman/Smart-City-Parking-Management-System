import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Car, CreditCard, User } from 'lucide-react';

export function DriverProfile() {
    const [profile, setProfile] = useState(null);

    useEffect(() => {
        const fetchProfile = async () => {
            const token = localStorage.getItem('token');
            if (token) {
                alert('You must be logged in to view your profile');
                return;
            }

            try {
                const res = await axios.get('/api/users/me', {
                    headers: { 'x-auth-token': token }
                });
                setProfile(res.data);
            } catch (err) {
                console.error(err.response.data);
            }
        };

        // fetchProfile();
        const user = localStorage.getItem('user');
        console.log("user",user);
        if (!user) {
            alert('You must be logged in to view your profile');
            return;
        }
        setProfile(JSON.parse(user));
    }, []);

    if (!profile) {
        return <p>Loading...</p>;
    }

    return (
        <div className="min-h-screen bg-gray-100 pt-16">
            <header className="bg-white shadow">
                <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-6">
                    <h1 className="text-3xl font-bold text-gray-900">Profile</h1>
                </div>
            </header>

            <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
                <div className="bg-white p-6 rounded-lg shadow-md">
                    <div className="flex items-center mb-6">
                        <User className="w-12 h-12 text-blue-600 mr-4"/>
                        <div>
                            <h2 className="text-2xl font-bold">{profile.email || "kamal"}</h2>
                            <p className="text-gray-600">{profile.email || "kamal@emample.com"}</p>
                        </div>
                    </div>
                    <div className="space-y-4">
                        <div className="flex items-center p-4 bg-gray-50 rounded-lg">
                            <Car className="w-6 h-6 text-gray-600 mr-3"/>
                            <div>
                                <p className="text-sm text-gray-600">License Plate</p>
                                <p className="font-medium">{}</p>
                            </div>
                        </div>

                        <div className="flex items-center p-4 bg-gray-50 rounded-lg">
                            <CreditCard className="w-6 h-6 text-gray-600 mr-3"/>
                            <div>
                                <p className="text-sm text-gray-600">Payment Method</p>
                                <p className="font-medium">•••• •••• •••• {}</p>
                            </div>
                        </div>
                    </div>
                </div>

            </main>
        </div>
    );
}