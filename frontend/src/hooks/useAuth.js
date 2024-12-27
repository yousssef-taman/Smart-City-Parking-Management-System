
export function useAuth() {
    const signUp = async ({ email, password, username, role,license }) => {
        try {
            const body = { username, password ,email, role,license };
            const response = await fetch('http://localhost:8080/api/user', {
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
            const response = await fetch('http://localhost:8080/api/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(body),
            });
            if (!response.ok) throw new Error('Invalid email, password, or role');
            const data = await response.json();
            localStorage.setItem('user', JSON.stringify(data));
        } catch (err) {
            console.log(err);
            throw err;
        }
    };

    return { signUp, login };
}