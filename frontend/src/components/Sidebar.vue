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
            <li><router-link :to="'/info'" tag="a" @click.native="click_toggle"><span class="icon solid fa-info">Info</span></router-link></li>
          </ul>
        </nav>

        <div class="bottom">
          <!-- Social Icons -->
          <ul class="icons">
            <li><a href="#" class="icon brands fa-twitter"><span class="label">Twitter</span></a></li>
            <li><a href="#" class="icon brands fa-facebook-f"><span class="label">Facebook</span></a></li>
            <li><a href="#" class="icon brands fa-github"><span class="label">Github</span></a></li>
            <li><a href="#" class="icon brands fa-dribbble"><span class="label">Dribbble</span></a></li>
            <li><a href="#" class="icon solid fa-envelope"><span class="label">Email</span></a></li>
          </ul>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Sidebar ',
  methods: {
    click_toggle () {
      document.body.className = document.body.className.replace('header-visible', '')
    }
  }
}
</script>
