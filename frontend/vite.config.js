import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  server: {
    port: 5173,
    proxy: {
      '/api': {
        target: 'http://localhost:8080',  // 转发给后端
        changeOrigin: true,                // 修改请求头 Host
        // 注意：后端 Controller 已有 @RequestMapping("/api")，所以不需要 rewrite
      }
    }
  }
})