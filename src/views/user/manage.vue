<template>
	<div class="app-container">
    <el-card>
        <el-form :model="user" ref="user">
          <el-col :span="6">
            <el-form-item label="" prop="userName">
              <el-input v-model="user.userName" placeholder="姓名"></el-input>
            </el-form-item>
          </el-col>
        </el-form>
        <el-col :span="6">
          <div class="filter-container">
            <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
            <el-button type="primary" icon="el-icon-edit" @click="handleImport">导出</el-button>
          </div>
        </el-col>
      </el-card>
		<el-table
	    :data="tableData"
	    style="width: 100%">
	    <el-table-column type="expand">
	      <template slot-scope="props">
	        <el-form label-position="right" inline class="demo-table-expand">
	          <el-form-item label="婚姻状况">
	            <span>{{ props.row.married }}</span>
	          </el-form-item>
	          <el-form-item label="电子邮箱">
	            <span>{{ props.row.email }}</span>
	          </el-form-item>
	          <el-form-item label="居住地址">
	            <span>{{ props.row.address }}</span>
	          </el-form-item>
	          <el-form-item label="学历">
	            <span>{{ props.row.academic }}</span>
	          </el-form-item>
	          <el-form-item label="单位名称">
	            <span>{{ props.row.unitName }}</span>
	          </el-form-item>
	          <el-form-item label="单位地址">
	            <span>{{ props.row.unitAddress }}</span>
	          </el-form-item>
	          <el-form-item label="职业">
	            <span>{{ props.row.work }}</span>
	          </el-form-item>
	          <el-form-item label="申请时间">
	            <span>{{ formatTime(props.row.createTime) }}</span>
	          </el-form-item>
	        </el-form>
	      </template>
	    </el-table-column>
	    <el-table-column
	      label="姓名"
	      prop="userName">
	    </el-table-column>
      <el-table-column
        label="等级"
        prop="grade">
      </el-table-column>
	    <el-table-column
	      label="生日"
	      prop="birthday">
	    </el-table-column>
	    <el-table-column
	      label="性别"
	      prop="sex">
        <template slot-scope="scope">
          {{scope.row.sex == 1 ? '男' : '女'}}
        </template>
	    </el-table-column>
      <el-table-column align="center" label="志愿历程" class-name="small-padding fixed-width">
        <template slot-scope="scope">
            <el-tag type="primary" size="mini" @click="handleDeed(scope.row)">点击查看详情</el-tag>
        </template>
      </el-table-column>
	  </el-table>
    <div>
        <el-pagination
        background
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        layout="total, prev, pager, next"
        :total="total"
        :page-size="5">
      </el-pagination>
    </div>
    <el-dialog title="志愿历程详情" :visible.sync="deedVisible">
      <el-scrollbar style="height: 200px" class="el-scrollbar_wrap">
        <el-timeline >
          <el-timeline-item
            v-for="(activity, index) in activities"
            :key="index"
            :color="activity.color"
            :icon="activity.icon"
            :timestamp="formatTime(activity.createTime)">
            {{activity.title}}
          </el-timeline-item>
        </el-timeline>
      </el-scrollbar>
      <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="handleIndeed">新增历程</el-button>
      </div>
    </el-dialog>
    <el-dialog title="新增历程" :visible.sync="editVisible">
      <el-form :model="indeed" ref="indeed" label-width="100px">
        <el-form-item label="历程简要" prop="title">
          <el-input v-model="indeed.title"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="addIndeed">新增</el-button>
      </div>
    </el-dialog>
	</div>
</template>
<style>
  .el-scrollbar__wrap{
    overflow-x: hidden;
  }
  .dialog-header{
    display: flex;
    flex-flow: row nowrap;
    justify-content: flex-end;
    align-items: center;
    padding: 20px;
  }
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>

<script>
  import { listUser, searchUser } from '@/api/user'
  import { listIndeed, createIndeed } from '@/api/indeed'
  export default {
    data() {
      return {
        activity: {},
        tableData: [],
        deedVisible: false,
        editVisible: false,
        activities: [],
        user: {},
        indeed: {},
        userId: '',
        currentPage: 1,
        total: 0
      }
    },
    created(){
      this.getUsers()
    },
    methods: {
      handleCurrentChange(val){
        console.log("当前改变："+val, this.user.userName)
        this.currentPage = val
        if(this.user.userName != null){
          this.search()
        }else{
          this.getUsers()
        }
        console.log(this.currentPage)
        // this.currentPage = 1
      },
      handleDeed(row){
        console.log(row)
        this.activities = []
        this.userId = row.userId
        let userId = row.userId
        listIndeed({userId: userId}).then(response => {
          let data = response.data
          if(data.errno == 0){
            console.log(data)
            this.deedVisible = true
            /*data.list[0].icon = 'el-icon-edit'
            data.list[1].icon = 'el-icon-present'
            data.list[0].color = '#108ee9'
            data.list[1].color = '#208ee7'*/
            this.activities = data.list
          }
        }).catch(err => {

        })
      },
      handleImport(){
        window.location.href = 'http://localhost:8081/admin/user/export'
      },
      getUsers(){
        console.log('getUser')
        let user = {userName: null}
        let page = this.currentPage
        listUser({user: user, page: page}).then(response => {
            let data = response.data
            console.log(data)
            if(data.errno == 0){
              this.tableData = data.data.list
              this.total = data.data.count
            }
        }).catch((err) => {
            
        })
      },
      search(){
        console.log('search')
        let user = this.user
        console.log(user,this.currentPage)
        this.total = 0
        this.tableData = []
        listUser({user: user, page: this.currentPage}).then(response => {
          let data = response.data
          console.log(data)
          if(data.errno == 0){
            this.tableData = data.data.list
            this.total = data.data.count
            console.log(user,this.currentPage)
          }
        }).catch((err) => {
            
        })
      },
      handleIndeed(){
        this.editVisible = true
      },
      addIndeed(){
        this.indeed.userId = this.userId
        let indeed = this.indeed
        console.log(indeed)
        createIndeed(indeed).then(response => {
            let data = response.data
            console.log(data)
            if(data.errno == 0){
              alert('新增成功')
              let userId = this.userId
              listIndeed({userId: userId}).then(response => {
                let data = response.data
                if(data.errno == 0){
                  console.log(data)
                  this.deedVisible = true
                  /*data.list[0].icon = 'el-icon-edit'
                  data.list[1].icon = 'el-icon-present'
                  data.list[0].color = '#108ee9'
                  data.list[1].color = '#208ee7'*/
                  this.activities = data.list
                }
              }).catch(err => {

              })
              this.editVisible = false
            }
        }).catch((err) => {
            this.editVisible = false
        })
      },
      formatTime(dates){
        let date = new Date(dates)
        const year = date.getFullYear()
        const month = date.getMonth() + 1
        const day = date.getDate()
        const hour = date.getHours()
        const minute = date.getMinutes()
        const second = date.getSeconds()
        return [year, month, day].map(this.formatNumber).join('-') + ' ' + [hour, minute, second].map(this.formatNumber).join(':')
       },

      formatNumber (n) {
        n = n.toString()
        return n[1] ? n : '0' + n
      }
    }
  }
</script>