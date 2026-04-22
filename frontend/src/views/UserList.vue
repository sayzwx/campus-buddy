<template>
  <div style="padding: 20px;">
    <el-card>
      <template #header>
        <div style="display: flex; justify-content: space-between; align-items: center;">
          <h2 style="margin: 0;">🎓 校园助手 - 用户管理</h2>
          <div>
            <el-button type="primary" @click="openAddDialog">➕ 新增用户</el-button>
            <el-button @click="loadData">🔄 刷新</el-button>
          </div>
        </div>
      </template>

      <!-- 数据表格 -->
      <el-table :data="users" border stripe style="width: 100%">
        <!-- ✅ 序号列：自动从 1 开始，删除后自动重排 -->
        <el-table-column type="index" label="序号" width="60" align="center" />
        
        <!-- 隐藏数据库 ID（或按需展示） -->
        <!-- <el-table-column prop="id" label="ID" width="80" /> -->
        
        <el-table-column prop="username" label="用户名" />
        <el-table-column prop="email" label="邮箱" />
        <el-table-column prop="role" label="角色" width="120">
          <template #default="{ row }">
            <el-tag :type="row.role === 'admin' ? 'danger' : 'success'">
              {{ row.role }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template #default="{ row }">
            <el-popconfirm title="确定删除吗？" @confirm="handleDelete(row.id)">
              <template #reference>
                <el-button type="danger" size="small">删除</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 新增用户弹窗 -->
    <el-dialog v-model="dialogVisible" title="新增用户" width="500px" :close-on-click-modal="false">
      <el-form 
        ref="formRef" 
        :model="form" 
        :rules="rules" 
        label-width="80px"
        @keyup.enter="handleAdd"
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名（3-20 位）" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="example@campus.edu" />
        </el-form-item>
        <el-form-item label="角色" prop="role">
          <el-select v-model="form.role" placeholder="请选择角色" style="width: 100%">
            <el-option label="学生" value="student" />
            <el-option label="教师" value="teacher" />
            <el-option label="管理员" value="admin" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleAdd" :loading="submitting">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { userApi } from '../api/user'

const users = ref([])
const dialogVisible = ref(false)
const formRef = ref(null)
const submitting = ref(false)

// 表单数据
const form = ref({
  username: '',
  email: '',
  role: 'student'
})

// ✅ 表单校验规则（前端实时校验）
const rules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 20, message: '长度 3-20 位', trigger: 'blur' }
  ],
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    // ✅ 邮箱正则校验（标准 RFC 5322 简化版）
    { 
      pattern: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/, 
      message: '请输入有效的邮箱格式', 
      trigger: 'blur' 
    }
  ],
  role: [
    { required: true, message: '请选择角色', trigger: 'change' }
  ]
}

// 加载数据
const loadData = async () => {
  try {
    const res = await userApi.getList()
    users.value = res.data
  } catch (e) {
    ElMessage.error('加载失败: ' + e.message)
  }
}

// 打开新增弹窗（重置表单）
const openAddDialog = () => {
  form.value = { username: '', email: '', role: 'student' }
  dialogVisible.value = true
  // 重置校验状态
  setTimeout(() => formRef.value?.clearValidate(), 0)
}

// 删除用户
const handleDelete = async (id) => {
  try {
    await userApi.deleteById(id)
    ElMessage.success('删除成功')
    loadData()  // 👈 删除后刷新，序号自动重排
  } catch (e) {
    ElMessage.error('删除失败: ' + e.message)
  }
}

// 新增用户
const handleAdd = async () => {
  // ✅ 前端表单校验
  if (!formRef.value) return
  try {
    await formRef.value.validate()
  } catch {
    return // 校验不通过，不提交
  }
  
  submitting.value = true
  try {
    await userApi.add(form.value)
    ElMessage.success('新增成功')
    dialogVisible.value = false
    loadData()  // 👈 新增后刷新
  } catch (e) {
    // 后端校验失败（如邮箱重复）
    const msg = e.response?.data?.message || e.message || '新增失败'
    ElMessage.error(msg)
  } finally {
    submitting.value = false
  }
}

onMounted(loadData)
</script>