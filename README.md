# 🎓 Campus Buddy - 校园助手系统

> 基于 Spring Boot 3 + Vue 3 + TiDB Cloud 的全栈用户管理系统，专为**高质量实习面试**打造的标准化工程实践项目。
>
> **核心亮点**：支持 Docker Compose 一键拉起全栈服务，数据持久化于云端，具备企业级 CI/CD 自动化构建流程。

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.10-green.svg)](https://spring.io/projects/spring-boot)
[![Vue](https://img.shields.io/badge/Vue.js-3.4-brightgreen.svg)](https://vuejs.org/)
[![Docker](https://img.shields.io/badge/Docker-Compose-blue.svg)](https://www.docker.com/)
[![CI](https://github.com/sayzwx/campus-buddy/actions/workflows/ci.yml/badge.svg)](https://github.com/sayzwx/campus-buddy/actions/workflows/ci.yml)

---

## 🌟 为什么这个项目值得看？ (Interview Highlights)

- **🚀 5 分钟极速上手**：无需配置本地 MySQL 或 Node 环境，通过 docker compose up 即可一键启动前后端及数据库连接。
- **☁️ 云原生架构**：采用 **TiDB Cloud Serverless** 替代传统本地数据库，实现存储计算分离，支持 SSL 加密与 IP 白名单，符合企业安全规范。
- **🛡️ 健壮性设计**：
  - 后端：Jakarta Validation (@Email, @NotBlank) 双重校验，防止非法数据入库。
  - 前端：Element Plus 表单实时校验 + Axios 拦截器统一异常处理。
- **⚙️ 自动化交付**：集成 **GitHub Actions**，每次代码推送自动触发 Maven/Npm 构建与 Docker 镜像合规性检查，确保交付质量。

---

## 🛠️ 技术栈 (Tech Stack)

| 类别 | 技术选型 | 说明 |
| :--- | :--- | :--- |
| **后端** | Java 17, Spring Boot 3.1.10, MyBatis-Plus 3.5.9 | 零 SQL 实现 CRUD，兼容 TiDB MySQL 协议 |
| **数据库** | TiDB Cloud (Serverless) | 永久免费额度，高可用，弹性扩展 |
| **前端** | Vue 3 (Composition API), Vite 4.5.3 | <script setup> 语法糖，热更新秒级响应 |
| **UI 组件** | Element Plus | 现代化组件库，内置校验规则 |
| **部署** | Docker, Docker Compose, Nginx | 多阶段构建，最终镜像仅 ~22MB |

---

## 🚀 快速开始 (Quick Start)

### 方式一：Docker 一键启动（推荐 ⭐）

适合演示、面试展示或快速体验。

#### 1. 前置准备
- 安装 [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- 获取 TiDB Cloud 连接信息（见下方「数据库配置」章节）

#### 2. 配置环境变量
在项目根目录新建 .env 文件（已加入 .gitignore，不会泄露密码）：
```env
DB_HOST=gateway01.ap-southeast-1.prod.aws.tidbcloud.com
DB_PORT=4000
DB_NAME=test
DB_USER=3UZwoUTDH31aB7V.root
DB_PASSWORD=

```
#### 3. 启动服务
```env
# 1. 克隆项目
git clone https://github.com/sayzwx/campus-buddy.git
cd campus-buddy
# 2. 一键启动（首次需等待镜像拉取与构建，约 3 分钟）
docker compose up --build -d
# 3. 访问应用
# 前端页面：http://localhost
# 后端 API： http://localhost:8080/api/users
