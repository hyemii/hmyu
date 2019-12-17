<template>
  <div id="app">
    <sidebar></sidebar>
    <router-view class="contents"/>
    <modals-container/>
  </div>
</template>

<script>
import sidebar from './components/Sidebar'

export default {
  name: 'App',
  components: {
    'sidebar': sidebar
  }
}
</script>

<style>
</style>
