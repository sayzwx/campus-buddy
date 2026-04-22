import axios from 'axios'

const request = axios.create({
  baseURL: '/api',
  timeout: 5000
})

export const userApi = {
  getList: () => request.get('/users'),
  deleteById: (id) => request.delete(`/users/${id}`),
  add: (data) => request.post('/users', data)
}

export default request