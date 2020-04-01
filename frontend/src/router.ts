import Vue from 'vue'
import Router from 'vue-router'

import Overview from './views/Overview.vue'
import CreateNote from './views/CreateNote.vue'

Vue.use(Router)

export default new Router({
  routes: [
    { path: '/', component: Overview },
    { path: '/new', component: CreateNote }
  ]
})
