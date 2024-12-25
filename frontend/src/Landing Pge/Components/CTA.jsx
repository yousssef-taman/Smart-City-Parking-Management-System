import React from 'react';

export default function CTA() {
    return (
        <div id="contact" className="bg-white">
            <div className="max-w-7xl mx-auto py-16 px-4 sm:px-6 lg:px-8">
                <div className="bg-blue-700 rounded-lg shadow-xl overflow-hidden">
                    <div className="pt-10 pb-12 px-6 sm:pt-16 sm:px-16 lg:py-16 lg:pr-0 xl:py-20 xl:px-20">
                        <div className="lg:self-center lg:max-w-2xl">
                            <h2 className="text-3xl font-extrabold text-white sm:text-4xl">
                                <span className="block">Ready to get started?</span>
                                <span className="block">Transform your city's parking today.</span>
                            </h2>
                            <p className="mt-4 text-lg leading-6 text-blue-200">
                                Join the smart parking revolution and make parking hassles a thing of the past.
                                Get in touch with our team to learn more about implementing SmartPark in your city.
                            </p>
                            <a
                                href="#"
                                className="mt-8 bg-white border border-transparent rounded-md shadow px-6 py-3 inline-flex items-center text-base font-medium text-blue-600 hover:bg-blue-50"
                            >
                                Contact Sales
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}