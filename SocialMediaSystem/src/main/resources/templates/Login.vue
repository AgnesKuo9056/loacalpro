<template>
    <div>
        <form @submit.prevent="loginUser">
            <input v-model="credentials.phoneNumber" placeholder="Phone Number" required>
            <input v-model="credentials.password" type="password" placeholder="Password" required>
            <button type="submit">Login</button>
        </form>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        data() {
            return {
                credentials: {
                    phoneNumber: '',
                    password: ''
                }
            };
        },
        methods: {
            async loginUser() {
                try {
                    const response = await axios.post('/api/login', this.credentials);
                    const token = response.data.token;
                    localStorage.setItem('jwtToken', token); // Save token to local storage
                    console.log('Login successful');
                    this.$router.push('/Post'); // Redirect to dashboard or desired page
                } catch (error) {
                    console.error('Error logging in:', error);
                }
            }
        }
    };
</script>
