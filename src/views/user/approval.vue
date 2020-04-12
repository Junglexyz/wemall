<template>
	<div class="app-container">
		<h4>志愿者审批</h4>
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
	      label="年龄"
	      prop="age">
	    </el-table-column>
	    <el-table-column
	      label="性别"
	      prop="sex">
	      <template slot-scope="scope">
            {{scope.row.sex == 1 ? '男' : '女'}}
          </template>
	    </el-table-column>
	    <el-table-column align="center" label="操作" class-name="small-padding fixed-width">
	        <template slot-scope="scope">
	          <el-button type="primary" size="mini" @click="updateData(scope.row, 1)">通过</el-button>
	          <el-button type="danger" size="mini" @click="handleReturn(scope.row)">驳回</el-button>
	        </template>
	    </el-table-column>
	  </el-table>
	  <el-dialog title="确认驳回" :visible.sync="returnFormVisible">
	  	<el-form>
	  		<el-form-item label="驳回原因">
	          <el-input type="textarea" v-model="reason"></el-input>
	        </el-form-item>
	  	</el-form>
	  	<div slot="footer" class="dialog-footer">
	        <el-button type="primary" @click="cancel">取消</el-button>
	        <el-button type="primary" @click="updateData(null, 2)">确定</el-button>
	      </div>
	  </el-dialog>
	</div>
</template>
<style>
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
  import { listUser, updateUser } from '@/api/user'
  import { createIndeed } from '@/api/indeed'
  export default {
    data() {
      return {
        tableData: [],
        returnFormVisible: false,
        reason: '',
        user: {}
      }
    },
    created(){
    	this.getListUser()
    },
    methods: {
    	cancel(){
    		this.returnFormVisible = false
    	},
    	updateData(row, status){
    		let user = {}
    		if(status == 1){
    			user = {userId: row.userId, status: 1}
    		}else{
    			// address临时存储驳回原因
    			user = {userId: this.user.userId, status: 2, address: this.reason}
    		}
    		console.log(user)
    		updateUser(user).then(response => {
			    let data = response.data
			    console.log(data)
			    if(data.errno == 0){
			        console.log('提交成功')
			        this.getListUser()
			        this.user = {}
			        this.returnFormVisible = false
			    }
		    }).catch((err) => {
		        console.log(err)
		        this.user = {}
		    })
    	},
    	handleReturn(row){
    		this.returnFormVisible = true
    		this.user = row
    	},
    	getListUser(){
    		let user = {userName: null, status: 0} 
    		listUser({user: user}).then(response => {
			    let data = response.data
			    console.log(data)
			    if(data.errno == 0){
			        this.tableData = data.list
			    }
		    }).catch((err) => {
		        this.tableData = []
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