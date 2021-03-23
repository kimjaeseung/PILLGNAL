import Vue from "vue";
import VueRouter from "vue-router";
import Tutorial from "../views/user/Tutorial.vue";
import Home from "../views/Home.vue";
import Detail from "../views/prescription/Detail";
import PrescriptionList from "../views/prescription/PrescriptionList.vue";
import PrescriptionDetail from "../views/prescription/PrescriptionDetail.vue";
import Notification from "../views/Notification.vue";
import Mypage from "../views/mypage/Mypage.vue";
import ProfileImage from "../views/mypage/ProfileImage.vue";
import Settings from "../views/settings/Settings.vue"
import Attention from "../views/settings/Attention.vue"
import Service from "../views/settings/Service.vue"
import Camera from "../views/Camera.vue"
import Login from "../views/user/Login.vue"
import Regist from "../views/user/Regist.vue"


Vue.use(VueRouter);

// const requireAuth = () => (to, from, next) => {
//   //나중에 기능추가할것
//   console.log(to);
//   console.log(from);
//   console.log(next);
// };

const routes = [
  {
    path: "/tutorial",
    name: "Tutorial",
    component: Tutorial,
    // beforeEnter: requireAuth
  },
  {
    path: "/home",
    name: "Home",
    component: Home,
    // beforeEnter: requireAuth
  },
  {
    path: "/detail/:pillNo",
    name: "Detail",
    component: Detail,
    // beforeEnter: requireAuth
  },
  {
    path: "/prescription",
    name: "PrescriptionList",
    component: PrescriptionList,
    // beforeEnter: requireAuth
  },
  {
    path: "/prescription/:presNo",
    name: "PrescriptionDetail",
    component: PrescriptionDetail,
    // beforeEnter: requireAuth
  },
  {
    path: "/notification",
    name: "Notification",
    component: Notification,
    // beforeEnter: requireAuth
  },
  {
    path: "/mypage",
    name: "Mypage",
    component: Mypage,
    // beforeEnter: requireAuth
  },
  {
    path: "/mypage/image",
    name: "ProfileImage",
    component: ProfileImage,
    // beforeEnter: requireAuth
  },
  {
    path: "/settings",
    name: "Settings",
    component: Settings,
    // beforeEnter: requireAuth
  },
  {
    path: "/attention",
    name: "Attention",
    component: Attention,
    // beforeEnter: requireAuth
  },
  {
    path: "/service",
    name: "Service",
    component: Service,
    // beforeEnter: requireAuth
  },
  {
    path: "/camera",
    name: "Camera",
    component: Camera,
    // beforeEnter: requireAuth
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
    // beforeEnter: requireAuth
  },
  {
    path: "/regist",
    name: "Regist",
    component: Regist,
    // beforeEnter: requireAuth
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
