<template>
  <div id="createpost">
    <div id="main">
      <section class="three">
        <div class="container" style="padding-top: 20px">
          <table>
            <tr v-if="user_type === 'A'">
              <td width="65%"><input type="text" name="subject" v-model="post.subject" class="form-control" placeholder="제목"/></td>
              <td width="35%">
                <select name="status" id="status" v-model="post.status" class="form-control">
                  <option value="O">공개</option>
                  <option value="P">비공개</option>
                </select>
              </td>
            </tr>
            <tr v-else>
              <td colspan="2"><input type="text" name="subject" v-model="post.subject" class="form-control" placeholder="제목"/></td>
            </tr>
            <tr>
              <td colspan="2">
                <vue-editor ref="editor" id="editor" v-model="post.contents" style="background-color: white"></vue-editor>
              </td>
            </tr>
          </table>
          <div>
            <router-link :to="'/posts'" tag="button">목록</router-link>
            <button type="button" @click="insPost">저장</button>
          </div>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import { VueEditor } from 'vue2-editor'
export default {
  name: 'CreatePost',
  data () {
    return {
      post: {
        subject: '',
        contents: '',
        status: 'O',
        regId: ''
      },
      user_type: this.$cookies.get('user')
    }
  },
  components: {
    VueEditor
  },
  methods: {
    insPost () {
      if (!this.post.subject) {
        alert('제목을 입력하세요.')
        return false
      }

      if (!this.post.contents) {
        alert('글 내용을 입력하세요.')
        return false
      }

      this.post.regId = this.$cookies.get('user_id')
      console.log(this.post)

      this.$http.post(
        'http://13.209.101.187:8080' + '/posts',
        this.post
      ).then(result => {
        console.log('insert post', result)
        if (result.status === 200) {
          alert('저장완료')
          this.$router.back()
        }
      }).catch(reason => {
        console.log('error insert', reason.response)
        alert('저장실패')
      })
    }
  }
}
</script>

<style scoped>
  table {
    border: none;
  }

  td {
    border: none;
    padding: 3px;
  }
</style>
