import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Bookmark from '@/components/Bookmark'
import Posts from '@/components/Posts'
import Post from '@/components/Post'
import CreatePost from '@/components/CreatePost'
import Info from '@/components/Info'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/posts',
      name: 'Posts',
      component: Posts
    },
    {
      path: '/posts/:postId',
      name: 'Post',
      component: Post
    },
    {
      path: '/inspost',
      name: 'CreatePost',
      component: CreatePost
    },
    {
      path: '/bookmark',
      name: 'Bookmark',
      component: Bookmark
    },
    {
      path: '/info',
      name: 'Info',
      component: Info
    }
  ]
})
