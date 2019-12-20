<template>
  <div id="bookmark">
    <div id="main">
      <section class="three">
        <div class="container" align="center">
          <header>
            <h2>Bookmark</h2>
          </header>
          <table>
            <tr>
              <th>Login Page</th>
            </tr>
            <tr v-for="(bookmark, index) in login_bookmarks" v-bind:key="index">
              <td>
                <a v-bind:href="bookmark.href">{{bookmark.desc}}</a>
              </td>
            </tr>
          </table>
          <table>
            <tr>
              <th>Guide Page</th>
            </tr>
            <tr v-for="(bookmark, index) in guide_bookmarks" v-bind:key="index">
              <td>
                <a v-bind:href="bookmark.href">{{bookmark.desc}}</a>
              </td>
            </tr>
          </table>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Bookmark',
  data () {
    return {
      login_bookmarks: [
        {href: 'https://github.com/hyemii/hmyu.git', desc: 'GitHub'},
        {href: 'http://13.209.101.187:9090', desc: 'Jenkins'},
        {href: 'https://us-east-1.signin.aws.amazon.com/oauth?response_type=code&client_id=arn%3Aaws%3Aiam%3A%3A015428540659%3Auser%2Fhomepage&redirect_uri=https%3A%2F%2Fconsole.aws.amazon.com%2Fconsole%2Fhome%3Fstate%3DhashArgs%2523%26isauthcode%3Dtrue&forceMobileLayout=0&forceMobileApp=0', desc: 'AWS'}
      ],
      guide_bookmarks: [
        {href: 'https://kr.vuejs.org/v2/guide/index.html', desc: 'Vue.js'},
        {href: 'https://bootstrap-vue.js.org/', desc: 'Bootstrap Vue'},
        {href: 'https://fontawesome.com/v4.7.0/icons/', desc: 'Fontawesome'}
      ]
    }
  }
}
</script>

<style scoped>
  table {
    width: 60%;
    text-align: center;
  }
</style>
