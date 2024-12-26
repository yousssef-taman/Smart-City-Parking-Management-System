import Hero from "./Components/Hero.jsx";
import Features from "./Components/Features.jsx";
import CTA from "./Components/CTA.jsx";
import Stats from "./Components/Stats.jsx";

export const Landing = () => {
    return (
        <>
            <div className="min-h-screen bg-white">
                <Hero/>
                <Features/>
                <Stats/>
                <CTA/>
            </div>
        </>
    )
}