import React from 'react';

const stats = [
    { id: 1, name: 'Parking Spots', value: '10,000+' },
    { id: 2, name: 'Cities', value: '25+' },
    { id: 3, name: 'Daily Users', value: '50,000+' },
    { id: 4, name: 'Time Saved', value: '15 min' },
];

export default function Stats() {
    return (
        <div id="stats" className="bg-blue-600">
            <div className="max-w-7xl mx-auto py-12 px-4 sm:py-16 sm:px-6 lg:px-8">
                <div className="max-w-4xl mx-auto text-center">
                    <h2 className="text-3xl font-extrabold text-white sm:text-4xl">
                        Trusted by cities worldwide
                    </h2>
                    <p className="mt-3 text-xl text-blue-200">
                        Making parking smarter, one spot at a time
                    </p>
                </div>
                <dl className="mt-10 grid grid-cols-1 gap-8 sm:grid-cols-2 lg:grid-cols-4">
                    {stats.map((stat) => (
                        <div key={stat.id} className="flex flex-col">
                            <dt className="order-2 mt-2 text-lg leading-6 font-medium text-blue-200">
                                {stat.name}
                            </dt>
                            <dd className="order-1 text-5xl font-extrabold text-white">
                                {stat.value}
                            </dd>
                        </div>
                    ))}
                </dl>
            </div>
        </div>
    );
}