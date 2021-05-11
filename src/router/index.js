import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home.vue'
import Department from '../components/systemmanager/Department.vue'
import Employee from '../components/systemmanager/Employee.vue'
import EditEmployee from '../components/systemmanager/EditEmployee.vue'
import Authority from '../components/systemmanager/Authority.vue'
import Role from '../components/systemmanager/Role.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/sys'
  },
  {
    path: '/sys',
    component: Home,
    children: [
      { path: '/sys/department', component: Department },
      { path: '/sys/employee', component: Employee },
      { path: '/sys/employee/edit', component: EditEmployee },
      { path: '/sys/authority', component: Authority },
      { path: '/sys/role', component: Role }
    ]
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
