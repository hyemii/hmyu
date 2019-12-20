<template>
  <div id="sidebar">
    <div id="header">
      <div class="top">

        <!-- Logo -->
        <div id="logo">
          <span class="image avatar48"><img src="../../static/img/avatar.jpg" alt="" /></span>
          <h1 id="title">Hyemi</h1>
          <p>dutjz26@gmail.com</p>
        </div>

        <!-- Nav -->
        <nav id="nav">
          <ul>
            <li><router-link :to="'/'" tag="a" @click.native="click_toggle"><span class="icon solid fa-home">Home</span></router-link></li>
            <li><router-link :to="'/posts'" tag="a" @click.native="click_toggle"><span class="icon solid fa-archive">Posts</span></router-link></li>
            <li><router-link :to="'/bookmark'" tag="a" @click.native="click_toggle"><span class="icon solid fa-bookmark">Bookmark</span></router-link></li>
            <li><router-link :to="'/info'" tag="a" @click.native="click_toggle"><span class="icon solid fa-cog">Info</span></router-link></li>
            <li><router-link :to="'/'" tag="a" @click.native="logout">
              <span v-if="user_type === 'G' || user_type === 'A'">{{this.$cookies.get('user_id')}} Logout</span>
            </router-link></li>
          </ul>
        </nav>
      </div>

      <div class="bottom" align="center">
        <ul class="icons">
          <li><a href="https://github.com/hyemii/hmyu.git" class="icon brands fa-github" style="font-size: 2em"><span class="label">Github</span></a></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Sidebar ',
  data () {
    return {
      user_type: this.$cookies.get('user')
    }
  },
  methods: {
    click_toggle () {
      document.body.className = document.body.className.replace('header-visible', '')
    },
    logout () {
      console.log('click')
      document.body.className = document.body.className.replace('header-visible', '')
      this.$cookies.set('user', null)
      this.$cookies.set('user_id', null)
      this.$router.go()
    }
  }
}
</script>
