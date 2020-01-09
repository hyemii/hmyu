<template>
  <div id="home">
    <div id="main">
      <section class="one dark cover">
        <div class="container" align="center" v-if="user_type !== 'G' && user_type !== 'A'">
          <h2>LOGIN</h2>
          <div class="login-input" align="center">
            <input type="text" class="form-control" v-model="user_id" placeholder="ID"/>
            <input type="password" class="form-control" v-model="user_pwd" placeholder="Password"/>
            <br>
            <button type="button" @click="login">Login</button>
            <button type="button" @click="guestLogin">Guest</button>
          </div>
        </div>
        <div class="container" v-else>
          <h2>안녕하세요! {{this.$cookies.get('user_id')}} 님</h2>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import { urlVal } from '../lib/url'
export default {
  name: 'Home',
  data () {
    return {
      user_id: '',
      user_pwd: '',
      user_type: this.$cookies.get('user')
    }
  },
  methods: {
    guestLogin () {
      this.$cookies.set('user', 'G')
      this.$cookies.set('user_id', 'guest')
      this.$router.go()
    },
    login () {
      if (!this.user_id) {
        alert('아이디를 입력하세요.')
        return false
      }

      if (!this.user_pwd) {
        alert('비밀번호를 입력하세요.')
        return false
      }

      this.$http.get(
        urlVal + '/user/' + this.user_id
      ).then(result => {
        console.log('get user', result)
        if (result.status === 200) {
          if (this.user_pwd === result.data.userPwd) {
            this.$cookies.set('user', result.data.userType)
            this.$cookies.set('user_id', result.data.userId)
          }
          this.$router.go()
        }
      }).catch(reason => {
        console.log('error login', reason.response)
        alert('로그인 실패')
      })
    }
  }
}
</script>

<style scoped>
  .container {
    margin-top: 12%;
  }

  .login-input {
    margin: 3%;
    padding: 5px;
  }

  input {
    width: 220px;
  }
</style>
