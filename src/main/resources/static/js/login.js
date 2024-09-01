function checkLoginStatus() {
    return fetch('/api/check-login-status')
        .then(response => response.json())
        .then(data => data.isLoggedIn)  // Assume the API returns { "isLoggedIn": true/false }
        .catch(error => {
            console.error('Error checking login status:', error);
            return false;
        });
}

document.addEventListener('DOMContentLoaded', function() {
    checkLoginStatus().then(isLoggedIn => {
        const userContentDiv = document.getElementById('user-content');

        if (isLoggedIn) {
            userContentDiv.innerHTML = `
                <h1>Welcome back, User!</h1>
                <a href="/logout">Logout</a>
            `;
        } else {
            userContentDiv.innerHTML = `
                <h1>Welcome to our site!</h1>
                <a href="/login">Login</a>
            `;
        }
    });
});

