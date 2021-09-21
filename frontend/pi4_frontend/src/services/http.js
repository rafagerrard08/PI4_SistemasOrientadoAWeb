import axios from 'axios';
import Qs from 'qs';
import router from '../router';

const http = axios.create({
  baseURL: 'http://localhost:8080/',
  withCredentials: false,
  headers: { 'X-Requested-With': 'XMLHttpRequest' },
  paramsSerializer: params => Qs.stringify(params, {arrayFormat: 'repeat'})
})

http.interceptors.request.use(function(request) {
  vm.$data.loading = true;
  if (!vm.$data.requestCounter)
    vm.$data.requestCounter = 0;
  vm.$data.requestCounter++;
  return request;
}, function (error) {
  vm.$data.requestCounter-- ;
  if (vm.$data.requestCounter < 1)
    vm.$data.loading = false;
  return Promise.reject(error);
})
http.interceptors.response.use(function(response) {
  vm.$data.requestCounter--;
  if (vm.$data.requestCounter < 1)
    vm.$data.loading = false;
  return response;
}, function (error) {
  vm.$data.requestCounter--;
  if (vm.$data.requestCounter < 1)
    vm.$data.loading = false;
  if (error.response) {
    // The request was made and the server responded with a status code
    // that falls out of the range of 2xx
    if (error.response.status == 401) {
      // router.push({ name: '401' })
      return Promise.reject(error);
    }
    if (error.response.status == 403) {
      // router.push({ name: '403' })
      return Promise.reject(error);
    }
    if (error.response.status == 404) {
      // router.push({ name: '404' })
      return Promise.reject(error);
    }
    if (error.response.status == 500) {
      // router.push({ name: '500' })
      return Promise.reject(error);
    }
  }
  return Promise.reject(error);
})

export default http;