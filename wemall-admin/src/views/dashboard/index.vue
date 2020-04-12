<template>
  <div class="dashboard-editor-container">
    <el-card>
      <div class="general-header">
        <div>今日概览</div>
        <div>更新时间: {{nowDate}}</div>
      </div>
      <el-card>
        <el-date-picker
          v-model="order.createTime"
          type="datetime"
          placeholder="开始日期时间">
        </el-date-picker>
        <el-date-picker
            v-model="order.endTime"
            type="datetime"
            placeholder="结束日期时间">
        </el-date-picker>
        <el-button type="primary" @click="search">查看</el-button>
      </el-card>
      <div class="height"></div>
      <el-row :gutter="20">
       <el-col :span="6" class="total-col order">
          <div class="">订单数量</div>
          <div>{{today.totalOrder}}</div>
        </el-col>
        <el-col :span="6" class="total-col sell">
          <div class="">销售总额</div>
          <div>{{today.totalSales}}</div>
        </el-col>
      </el-row>
    </el-card>

    <el-card>
    	<div class="general-header">
    		<div>店铺概况</div>
    		<div>更新时间: {{nowDate}}</div>
    	</div>
    	<el-row :gutter="20">
  		  <el-col :span="6" class="total-col user">
  		  	<div class="">用户数量</div>
  		  	<div>{{userTotal}}</div>
  		  </el-col>
  		  <el-col :span="6" class="total-col goods">
  		  	<div class="">商品数量</div>
  		  	<div>{{goodsTotal}}</div>
  		  </el-col>
  		  <el-col :span="6" class="total-col order">
  		  	<div class="">订单数量</div>
  		  	<div>{{orderTotal}}</div>
  		  </el-col>
  		  <el-col :span="6" class="total-col sell">
  		  	<div class="">销售总额</div>
  		  	<div>{{salesTotal}}</div>
  		  </el-col>
  		</el-row>
    </el-card>
  </div>
</template>
<style>
  .height{
    height: 30px
  }
  .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  .general-header{
  	display: flex;
  	flex-flow: row nowrap;
  	justify-content: space-between;
  	align-items: center;
  	margin-bottom: 10px
  }
  .total-col{
  	display: flex;
  	flex-flow: column nowrap;
  	justify-content: space-around;
  	align-items: center;
  	padding: 5px;
  	height: 100px
  }
  .user{
  	background: #40c9c6;
  }
  .goods{
  	background: #36a3f7;
  }
  .order{
  	background: #f4516c;
  }
  .sell{
  	background: #34bfa3;
  }
</style>


<script>
import { info,todayInfo } from '@/api/dashboard'
export default {
  components: {
  },
  data() {
    return {
      userTotal: 0,
      goodsTotal: 0,
      salesTotal: 0,
      orderTotal: 0,
      nowDate: '',
      today: {
        totalSales: 0,
        totalOrder: 0,
      },
      order: {}
    }
  },
  created() {
  	this.getInfo(),
    this.todayInfo()
  },
  methods: {
  	getInfo(){
      let params = {}
      let now = new Date()
      params.createTime = this.formatTime(new Date(now.getFullYear(),now.getMonth()-3,now.getDate(),0,0,0))
      params.endTime = this.formatTime(now)
      this.nowDate = this.formatTime(now)
      console.log(params)
  		info(params).then(response => {
	      this.userTotal = response.data.result.userTotal
	      this.goodsTotal = response.data.result.goodsTotal
	      this.orderTotal = response.data.result.orderTotal
	      this.salesTotal = response.data.result.salesTotal
	    })
  	},
    todayInfo(){
      let params = {}
      let now = new Date()
      params.createTime = this.formatTime(new Date(now.getFullYear(),now.getMonth(),now.getDate(),0,0,0))
      params.endTime = this.formatTime(now)
      console.log(params)
      todayInfo(params).then(response => {
        console.log(response)
        let data = response.data
        if(data.errno == 0){
          this.today = data.result
        }
      })
    },
    search(){
      let params = {}
      params.createTime = this.formatTime(this.order.createTime)
      params.endTime = this.formatTime(this.order.endTime)
      console.log(params)
      todayInfo(params).then(response => {
        console.log(response)
        let data = response.data
        if(data.errno == 0){
          this.today = data.result
        }
      })
    },
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    },
    formatTime(date){
    console.log(date)
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