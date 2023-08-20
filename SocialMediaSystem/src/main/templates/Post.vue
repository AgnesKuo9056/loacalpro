<template>
    <div>
        <button @click="createPost" v-if="isLoggedIn">Create Post</button>
        <button @click="commentOnPost" v-if="isLoggedIn">Comment on Post</button>

        <button @click="editProfile" v-if="isLoggedIn">Edit Profile</button>

        <!-- Comment input -->
        <input v-model="commentText" placeholder="Enter your comment" v-if="showCommentInput">
        <button @click="submitComment" v-if="showCommentInput">Submit Comment</button>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                commentText: '',
                showCommentInput: false
            };
        },
        computed: {
            isLoggedIn() {

                return localStorage.getItem('jwtToken') !== null;
            }
        },
        methods: {
            createPost() {
                if (this.isLoggedIn) {
                    console.log('Creating a post');
                } else {
                    this.$router.push('/login');
                }
            },
            commentOnPost() {
                if (this.isLoggedIn) {
                    this.showCommentInput = true;
                } else {
                    this.$router.push('/login');
                }
            },
            editProfile() {
                if (this.isLoggedIn) {
                    console.log('Editing profile');
                } else {
                    this.$router.push('/login');
                }
            },
            submitComment() {
                console.log('Submitting comment:', this.commentText);

                // 重製輸入框,並隱藏
                this.commentText = '';
                this.showCommentInput = false;
            }
        }
    };
</script>
