<template>
  <div id="post">
    <div id="main">
      <section class="three">
        <div class="container" style="padding-top: 20px">
          <table v-if="!flag">
            <tr>
              <td>{{post.subject}}</td>
            </tr>
            <tr>
              <td class="ql-snow"><div class="ql-editor" v-html="post.contents"></div></td>
            </tr>
          </table>
          <table v-if="flag">
            <tr v-if="user_type === 'A'">
              <td width="65%" style="border: none; padding: 3px"><input type="text" name="subject" v-model="post.subject" class="form-control"/></td>
              <td width="35%" style="border: none; padding: 3px">
                <select name="status" id="status" v-model="post.status" class="form-control">
                  <option value="O">공개</option>
                  <option value="P">비공개</option>
                </select>
              </td>
            </tr>
            <tr v-if="user_type === 'G'">
              <td style="border: none; padding: 3px"><input type="text" name="subject" v-model="post.subject" class="form-control"/></td>
            </tr>
            <tr>
              <td colspan="2" style="border: none; padding: 3px">
                <vue-editor v-model="post.contents" :editor-toolbar="customToolbar" style="background-color: white"></vue-editor>
              </td>
            </tr>
          </table>
          <div>
            <router-link :to="'/posts'" tag="button">목록</router-link>
            <button type="button" @click="setMod" v-if="user_type === 'A' || user_id === post.regId">
              <label v-if="!flag">수정</label>
              <label v-if="flag">저장</label>
            </button>
            <button type="button" @click="delPost" v-if="user_type === 'A' || user_id === post.regId">삭제</button>
          </div>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import { VueEditor } from 'vue2-editor'
import { urlVal } from '../lib/url'
export default {
  name: 'Post',
  data () {
    return {
      post: {
        post_id: '',
        subject: '',
        contents: '',
        status: '',
        reg_id: ''
      },
      postId: this.$route.params.postId,
      flag: false,
      user_type: this.$cookies.get('user'),
      user_id: this.$cookies.get('user_id'),
      customToolbar: [
        ['bold', 'italic', 'underline'],
        [{ list: 'ordered' }, { list: 'bullet' }],
        ['blockquote', 'code-block']
      ]
    }
  },
  components: {
    VueEditor
  },
  methods: {
    getPost () {
      this.$http.get(
        urlVal + '/posts/' + this.postId
      ).then(result => {
        if (result.status === 200) {
          console.log(result)
          this.post = result.data
        }
      }).catch(reason => {
        console.log(reason)
      })
    },
    setMod () {
      if (this.flag) {
        if (!this.post.subject) {
          alert('제목을 입력하세요.')
          return false
        }

        if (!this.post.contents) {
          alert('글 내용을 입력하세요.')
          return false
        }

        this.$http.put(
          urlVal + '/posts/' + this.postId,
          this.post
        ).then(result => {
          console.log('modify put', result)
          if (result.status === 200) {
            alert('수정 완료')
            this.flag = false
          }
        }).catch(reason => {
          console.log('error modify', reason.response)
          alert('수정 실패')
        })
      }
      if (!this.flag) {
        this.flag = true
      }
    },
    delPost () {
      this.$http.delete(
        urlVal + '/posts/' + this.postId
      ).then(result => {
        if (result.status === 200) {
          console.log(result)
          alert('삭제 완료')
          this.$router.back()
        }
      }).catch(reason => {
        console.log(reason)
        alert('삭제 실패')
      })
    }
  },
  created () {
    this.getPost()
  }
}
</script>

<style scoped>
</style>
