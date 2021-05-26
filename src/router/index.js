import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home.vue'
import Department from '../components/systemmanager/Department.vue'
import Employee from '../components/systemmanager/Employee.vue'
import EditEmployee from '../components/systemmanager/EditEmployee.vue'
import Authority from '../components/systemmanager/Authority.vue'
import Role from '../components/systemmanager/Role.vue'
import DataDictionary from '../components/datamanager/DataDictionary.vue'
import DataDictionaryDetails from '../components/datamanager/DataDictionaryDetails.vue'
import PotentialClient from '../components/clientmanager/PotentialCilent.vue'
import FormalClient from '../components/clientmanager/FormalClient.vue'
import FollowUpHistory from '../components/clientmanager/FollowUpHistory.vue'
import TransferHistory from '../components/clientmanager/TransferHistory.vue'
import DataTable from '../components/datamanager/DataTable.vue'
import Login from '../components/Login.vue'
import Echarts from '../components/echarts/Echarts.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/sys'
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/sys',
    component: Home,
    children: [
      { path: '/sys/department', component: Department },
      { path: '/sys/employee', component: Employee },
      { path: '/sys/employee/edit', component: EditEmployee },
      { path: '/sys/authority', component: Authority },
      { path: '/sys/role', component: Role },
      { path: '/sys/dataDictionary', component: DataDictionary },
      {
        path: '/sys/dataDictionaryDetails',
        component: DataDictionaryDetails,
        children: [
          { path: '/sys/dataTable/:id', component: DataTable }
        ]
      },
      { path: '/sys/clientManager', component: PotentialClient },
      { path: '/sys/formalClientManager', component: FormalClient },
      { path: '/sys/followUpHistory', component: FollowUpHistory },
      { path: '/sys/transferHistory', component: TransferHistory },
      { path: '/sys/echarts', component: Echarts }
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

router.beforeEach((to, from, next) => {
  if (to.path === '/login') return next()
  const token = window.sessionStorage.getItem('token')
  if (!token) return next('/login')
  next()
})

export default router
