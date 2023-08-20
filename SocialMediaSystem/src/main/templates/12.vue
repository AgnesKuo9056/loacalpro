<template>
    <div>
        <button @click="showAddPostForm" v-if="isLoggedIn">Add Post</button>
        <!-- AddPost input -->
        <input v-model="newPostContent" placeholder="Enter your Post" v-if="showAddPostInput">
        <button @click="addPost" v-if="showAddPostInput">Submit AddPost</button>

        <div v-for="post in posts" :key="post.id" class="post">
            <div class="post-content">
                <p v-if="!post.editing">{{ post.content }}</p>
                <textarea v-else v-model="post.editedContent"></textarea>
                <!-- updatePost submit -->
                <button @click="updatePost(post)" v-if="post.editing">Submit EditPost</button>
                <button @click="editPost(post)">Edit</button>
                <button @click="deletePost(post.id)">Delete</button>
            </div>

            <div class="comment-section">
                <input v-model="post.newComment" placeholder="Add a comment">
                <button @click="addComment(post)" v-if="isLoggedIn">Add Comment</button>
                <div v-for="comment in post.comments" :key="comment.id" class="comment">
                    {{ comment.content }}
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        data() {
            return {
                posts: [],
                newPostContent: '',
                showAddPostInput: false,
            };
        },
        computed: {
            isLoggedIn() {
                return localStorage.getItem('jwtToken') !== null;
            }
        },
        created() {
            this.fetchPosts();
        },
        methods: {
            async fetchPosts() {
                try {
                    const response = await axios.get('/api/posts');
                    this.posts = response.data.map(post => ({
                        ...post,
                        editing: false,
                        editedContent: post.content,
                        showEditPostInput: false,
                        newComment: '',
                    }));
                } catch (error) {
                    console.error('Error fetching posts:', error);
                }
            },
            async addPost() {
                try {
                    const response = await axios.post('/api/posts', { content: this.newPostContent });
                    this.posts.push({
                        ...response.data,
                        editing: false,
                        editedContent: response.data.content,
                        showEditPostInput: false,
                        newComment: '',
                    });
                    this.newPostContent = '';
                    this.showAddPostInput = false;
                } catch (error) {
                    console.error('Error adding post:', error);
                }
            },
            showAddPostForm() {
                if (this.isLoggedIn) {
                    this.showAddPostInput = true;
                } else {
                    this.$router.push('/login');
                }
            },
            editPost(post) {
                // Reset editing and showEditPostInput for all posts
                this.posts.forEach(p => {
                    p.editing = false;
                    p.showEditPostInput = false;
                });
                // Set editing and showEditPostInput for the clicked post
                post.editing = true;
                post.showEditPostInput = true;
            },
            async updatePost(post) {
                try {
                    await axios.put(`/api/posts/${post.id}`, { content: post.editedContent });
                    post.content = post.editedContent;
                    post.editing = false;
                    post.showEditPostInput = false;
                } catch (error) {
                    console.error('Error updating post:', error);
                }
            },
            async deletePost(postId) {
                try {
                    await axios.delete(`/api/posts/${postId}`);
                    this.posts = this.posts.filter(post => post.id !== postId);
                } catch (error) {
                    console.error('Error deleting post:', error);
                }
            },
            async addComment(post) {
                try {
                    const response = await axios.post(`/api/posts/${post.id}/comments`, { content: post.newComment });
                    post.comments.push(response.data);
                    post.newComment = '';
                } catch (error) {
                    console.error('Error adding comment:', error);
                }
            },
        },
    };
</script>
