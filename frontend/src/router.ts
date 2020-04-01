import Vue from 'vue'
import Router from 'vue-router'

import Overview from './views/Overview.vue'
import CreateNote from './views/CreateNote.vue'
<<<<<<< HEAD
import EditNote from "@/views/EditNote.vue";
=======
>>>>>>> upstream/develop

Vue.use(Router)

export default new Router({
  routes: [
    { path: '/', component: Overview },
<<<<<<< HEAD
    { path: '/new', component: CreateNote },
    { path: '/edit/:id', component: EditNote}
=======
    { path: '/new', component: CreateNote }
>>>>>>> upstream/develop
  ]
})
