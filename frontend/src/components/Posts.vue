<template>
  <div id="posts">
    <div id="main">
      <section class="three">
        <div class="container">
          <header>
            <h2>Posts</h2>
          </header>
          <table>
            <tr>
              <th class="no">No</th>
              <th>Subject</th>
            </tr>
            <tr v-for="(list, index) in lists" v-bind:key="index">
              <td>{{list.no}}</td>
              <td>{{list.desc}} 번 째 글</td>
            </tr>
          </table>
          <bPagination align="center" class="pagination" size="md" :total-rows="totalCount" v-model="currentPage" :per-page="pageSize" :limit="pagenationSize"/>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import bPagination from 'bootstrap-vue/es/components/pagination/pagination'

export default {
  name: 'Posts',
  data () {
    return {
      lists: [
        {no: '1', desc: '1'},
        {no: '2', desc: '1'},
        {no: '3', desc: '1'},
        {no: '4', desc: '1'},
        {no: '5', desc: '1'},
        {no: '6', desc: '1'},
        {no: '7', desc: '1'}
      ],
      totalCount: 7,
      currentPage: 0,
      pageSize: 5,
      pagenationSize: 5
    }
  },
  components: {
    'bPagination': bPagination
  }
}
</script>

<style scoped>
</style>
