<template>
    <div>
        <form @submit.prevent="registerUser">
            <input v-model="user.phoneNnum" placeholder="Phone Number" v-validate="'required'">
            <input v-model="user.userName" placeholder="Username" v-validate="'required'">
            <input v-model="user.password" type="password" placeholder="Password" v-validate="'required'">
            <button type="submit">Register</button>
        </form>
    </div>
</template>

<script>
    import { ValidationObserver, ValidationProvider, extend } from 'vee-validate';
    import { required } from 'vee-validate/dist/rules';
    import axios from 'axios';

    extend('required', required);

    export default {
        components: {
            ValidationObserver,
            ValidationProvider
        },
        data() {
            return {
                user: {
                    phoneNnum: '',
                    userName: '',
                    password: ''
                }
            };
        },
        methods: {
            async registerUser() {
                try {
                    await this.$validator.validateAll();

                    if (!this.errors.any()) {
                        const response = await axios.post('/api/register', this.user);
                        console.log(response.data); // Registration successful message
                    }
                } catch (error) {
                    console.error('Error registering user:', error);
                }
            }
        }
    };
</script>
