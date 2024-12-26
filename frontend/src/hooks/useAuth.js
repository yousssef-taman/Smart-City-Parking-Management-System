
export function useAuth() {
    const signUp = async ({ email, password, name, role }) => {
        try {
            const body = { email, password, name, role };
            const response = await fetch('http://localhost:8080/auth/signup', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(body),
            });
            if (!response.ok) throw new Error('Failed to sign up');
        } catch (err) {
            console.log(err);
            throw err;
        }
    };

    const login = async ({ email, password, role }) => {
        try {
            const body = { email, password, role };
            const response = await fetch('http://localhost:8080/auth/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(body),
            });
            if (!response.ok) throw new Error('Invalid email, password, or role');
        } catch (err) {
            console.log(err);
            throw err;
        }
    };

    return { signUp, login };
}