import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router)

// function loggedIn() {
//   return !!localStorage.ctpjToken;
// }

// function requiredAuth(to, from, next) {
//   if (!loggedIn()) {
//     next({
//       path: '/401',
//       component: NotAuthenticatedComponent,
//       query: { redirect: to.fullPath }
//     })
//   } else {
//     next();
//   }
// }

export default new Router({
    base: process.env.BASE_URL,
    // scrollBehavior (to, from, savedPosition) {
    //   return { x: 0, y: 0 }
    // },
    routes: [{
            path: '/',
            name: 'default',
            component: () =>
                import ( /* webpackChunkName: "default" */ './views/Home'),
            meta: {
                descricao: "Tela de listagem"
            }
        },
        {
            path: '/home',
            name: 'home',
            component: () =>
                import ( /* webpackChunkName: "home" */ './views/Home'),
            meta: {
                descricao: "Tela de listagem"
            }
        },
        {
            path: '/cadastro/:id',
            name: 'cadastro',
            component: () =>
                import ( /* webpackChunkName: "cadastro" */ './views/Cadastro'),
            meta: {
                descricao: "Tela de cadastro/atualização"
            }
        },
        {
            path: '/produto/:Id',
            name: 'produto',
            component: () =>
                import ( /* webpackChunkName: "produto" */ './views/Produto'),
            meta: {
                descricao: "Tela de produto"
            }
        },
        {
            path: '/login',
            name: 'login',
            component: () =>
                import ( /* webpackChunkName: "login" */ './views/Login'),
            meta: {
                descricao: "Tela de Login"
            }
        },

        {
            path: '/usuarios',
            name: 'usuarios',
            component: () =>
                import ( /* webpackChunkName: "usuarios" */ './views/Usuarios'),
            meta: {
                descricao: "Tela de Usuarios"
            }
        },
        {
            path: '/backoffice',
            name: 'backoffice',
            component: () =>
                import ( /* webpackChunkName: "backoffice" */ './views/Backoffice'),
            meta: {
                descricao: "Tela de backoffice"
            }
        },

        {
            path: '/cadastroUsuario/',
            name: 'cadastroUsuario',
            component: () =>
                import ( /* webpackChunkName: "cadastroUsuario" */ './views/CadastroUsuario'),
            meta: {
                descricao: "Tela de Cadastro Usuario"
            }
        },

        {
            path: '/atualizarUsuario/:idUsuario',
            name: 'atualizarUsuario',
            component: () =>
                import ( /* webpackChunkName: "cadastroUsuario" */ './views/CadastroUsuario'),
            meta: {
                descricao: "Tela de Atualizacao de Usuario"
            }
        },
    ]
})