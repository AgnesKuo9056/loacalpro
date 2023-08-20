<template>
    <div>
        <!-- Comment form -->
        <input v-model="content" placeholder="Comment">
        <button @click="comment">Comment</button>
    </div>
</template>

<script>
    export default {
        name: "Comment",
        data() {
            return {
                content: ''
            };
        },
        methods: {
            comment() {
                // Call API to create a new comment
                // You'll need to implement the API call here
            }
        }
    };
</script>
