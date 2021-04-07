import Vue from 'vue';
import VueRouter from 'vue-router';
import store from '../store/index.js';
import Tutorial from '../views/user/Tutorial.vue';
import Home from '../views/Home.vue';
import Detail from '../views/prescription/Detail';
import PrescriptionList from '../views/prescription/PrescriptionList.vue';
import PrescriptionDetail from '../views/prescription/PrescriptionDetail.vue';
import Notification from '../views/Notification.vue';
import Mypage from '../views/mypage/Mypage.vue';
import ProfileImage from '../views/mypage/ProfileImage.vue';
import Settings from '../views/settings/Settings.vue';
import Attention from '../views/settings/attention/Attention.vue';
import Service from '../views/settings/Service.vue';
import Camera from '../views/Camera.vue';
import Login from '../components/user/Login.vue';
import Regist from '../components/user/Regist.vue';
import FamilyRegist from '../views/family/FamilyRegist.vue';
import FamilyRegistList from '../views/family/FamilyRegistList.vue';
import FamilyList from '../views/family/FamilyList.vue';
import Main from '../views/Main.vue';
import Auth from '../views/user/Auth.vue';
import Time from '../views/settings/Time.vue';
import PillMethod from '../views/PillMethod.vue';
import AttentionRegist from '../views/settings/attention/AttentionRegist.vue';
import Voice from '../views/Voice.vue';

import { refreshToken, getUser } from '@/api/auth.js';

Vue.use(VueRouter);

const requireAuth = async (to, from, next) => {
  let user = store.getters.user;
  if (user === undefined || user === null || Object.keys(user).length === 0) {
    if (!localStorage['access-token'] || localStorage['access-token'] === '')
      next('/');
    else {
      await refreshToken();
      if (!localStorage['access-token'] || localStorage['access-token'] === '')
        next('/');
      else {
        await getUser();
        next();
      }
    }
  } else {
    await refreshToken();
    if (!localStorage['access-token'] || localStorage['access-token'] === '')
      next('/');
    else next();
  }
};

const routes = [
  {
    path: '',
    name: 'Main',
    component: Main,
    beforeEnter: (to, from, next) => {
      if (!localStorage['access-token'] || localStorage['access-token'] === '')
        next();
      else next('/home');
    },
    children: [
      {
        path: '',
        name: 'Login',
        component: Login,
      },
      {
        path: '/regist',
        name: 'Regist',
        component: Regist,
      },
    ],
  },
  {
    path: '/auth',
    name: 'Auth',
    component: Auth,
  },
  {
    path: '/tutorial',
    name: 'Tutorial',
    component: Tutorial,
    beforeEnter: requireAuth,
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    beforeEnter: requireAuth,
  },
  {
    path: '/detail/:pillNo',
    name: 'Detail',
    component: Detail,
    beforeEnter: requireAuth,
  },
  {
    path: '/prescription',
    name: 'PrescriptionList',
    component: PrescriptionList,
    
    beforeEnter: requireAuth,
  },
  {
    path: '/prescription/:presNo',
    name: 'PrescriptionDetail',
    component: PrescriptionDetail,
    beforeEnter: requireAuth,
  },
  {
    path: '/notification',
    name: 'Notification',
    component: Notification,
    beforeEnter: requireAuth,
  },
  {
    path: '/mypage',
    name: 'Mypage',
    component: Mypage,
    beforeEnter: requireAuth,
  },
  {
    path: '/mypage/image',
    name: 'ProfileImage',
    component: ProfileImage,
    beforeEnter: requireAuth,
  },
  {
    path: '/settings',
    name: 'Settings',
    component: Settings,
    beforeEnter: requireAuth,
  },
  {
    path: '/time',
    name: 'Time',
    component: Time,
    beforeEnter: requireAuth,
  },
  {
    path: '/attention',
    name: 'Attention',
    component: Attention,
    beforeEnter: requireAuth,
  },
  {
    path: '/attentionregist',
    name: 'AttentionRegist',
    component: AttentionRegist,
    beforeEnter: requireAuth,
  },
  {
    path: '/service',
    name: 'Service',
    component: Service,
    beforeEnter: requireAuth,
  },
  {
    path: '/familylist',
    name: 'FamilyList',
    component: FamilyList,
    beforeEnter: requireAuth,
  },
  {
    path: '/familyregist',
    name: 'FamilyRegist',
    component: FamilyRegist,
    beforeEnter: requireAuth,
  },
  {
    path: '/familyregist/list',
    name: 'FamilyRegistList',
    component: FamilyRegistList,
    props: true,
    beforeEnter: requireAuth,
  },
  {
    path: '/camera',
    name: 'Camera',
    component: Camera,
    props: true,
    beforeEnter: requireAuth,
  },
  {
    path: '/pillmethod',
    name: 'PillMethod',
    component: PillMethod,
    props: true,
    beforeEnter: requireAuth,
  },
  {
    path: "/voice",
    name: "voice",
    component: Voice,
    // beforeEnter: requireAuth
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
