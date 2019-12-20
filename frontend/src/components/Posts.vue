<template>
  <div id="posts">
    <div id="main">
      <section class="three">
        <div class="container">
          <header>
            <h2>Posts</h2>
            <router-link :to="'/inspost'" tag="button" style="float: right" v-if="user_type !== 'null'">작성</router-link><br>
          </header>
          <table>
            <thead>
              <tr>
                <th class="no">No</th>
                <th>Subject</th>
                <th class="no">Post ID</th>
              </tr>
            </thead>
            <tbody v-if="lists.length > 0">
              <tr v-for="(list, index) in lists" v-bind:key="index">
                <td class="no">{{totalCount - (currentPage -1) * pageSize - index}}</td>
                <td>
                  <span class="icon solid fa-lock" v-if="list.status === 'P'"></span>
                  <router-link :to="'/posts/' + list.postId" tag="a" >
                   {{list.subject}}
                  </router-link>
                </td>
                <td class="no">{{list.regId}}</td>
              </tr>
            </tbody>
            <tbody v-else>
            <tr>
              <td colspan="3" class="data-none">
                등록된 데이터가 없습니다.
              </td>
            </tr>
            </tbody>
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
      lists: [],
      totalCount: 0,
      currentPage: 0,
      pageSize: 5,
      pagenationSize: 5,
      user_type: this.$cookies.get('user'),
      view: ''
    }
  },
  components: {
    'bPagination': bPagination
  },
  watch: {
    currentPage: function currentPage () {
      this.getList()
    }
  },
  methods: {
    getList () {
      this.view = this.user_type
      if (this.user_type === 'null') { this.view = 'G' }
      this.$http.get(
        'http://13.209.101.187:8080' + '/posts',
        {
          params: {
            type: this.view,
            page: this.currentPage - 1,
            size: this.pageSize
          }
        }
      ).then(result => {
        if (result.status === 200) {
          console.log(result.data)
          this.lists = result.data.content
          this.totalCount = result.data.totalElements
        }
      }).catch(reason => {
        console.log('list error', reason)
      })
    }
  }
}
</script>

<style scoped>
</style>
