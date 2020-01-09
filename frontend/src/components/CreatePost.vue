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
                <vue-editor id="editor" v-model="post.contents" useCustomImageHandler @image-added="handleImageAdded" :editor-toolbar="customToolbar" style="background-color: white"></vue-editor>
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
import { urlVal } from '../lib/url'
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
      user_type: this.$cookies.get('user'),
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
        urlVal + '/posts',
        this.post
      ).then(result => {
        console.log('insert post', result)
        if (result.status === 200) {
          alert('저장 완료')
          this.$router.back()
        }
      }).catch(reason => {
        console.log('error insert', reason.response)
        alert('저장 실패')
      })
    },
    handleImageAdded (file, Editor, cursorLocation, resetUploader) {
      var formData = new FormData()
      formData.append('image', file)

      console.log(formData.get('image'))
      alert('append')

      this.$http.post(
        urlVal + '/image', formData,
        {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }
      )
        .then(result => {
          let url = result.data.url
          Editor.insertEmbed(cursorLocation, 'image', url)
          resetUploader()
        })
        .catch(err => {
          console.log(err)
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
