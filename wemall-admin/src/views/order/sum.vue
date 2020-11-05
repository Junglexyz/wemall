<template>
  <div class="app-container">
    <h4>订单汇总</h4>
    <el-card>
      <el-date-picker
        v-model="order.createTime"
        type="datetime"
        placeholder="开始日期时间"/>
      <el-date-picker
        v-model="order.endTime"
        type="datetime"
        placeholder="结束日期时间"/>
      <el-button type="primary" @click="search">立即查找</el-button>
      <el-button type="primary" @click="exportOrderSumExcel">导出</el-button>
    </el-card>
    <el-table
      v-loading="listLoading"
      :data="goods">
      <el-table-column
        prop="title"
        label="商品名称"/>
      <el-table-column
        prop="number"
        label="数量"/>
    </el-table>
  </div>
</template>
<script type="text/javascript">
import { sumOrder } from '@/api/order'
export default {
  data() {
    return {
      goods: [],
      listLoading: true,
      order: {}
    }
  },
  created() {
    this.getSumOrder()
  },
  methods: {
    getSumOrder() {
      const order = {}
      const now = new Date()
      const year = now.getFullYear()
      const month = now.getMonth()
      const day = now.getDate()
      order.createTime = this.formatTime(new Date(year, month, day))
      order.endTime = this.formatTime(new Date())
      console.log(order)
      sumOrder({ order: order }).then(res => {
        console.log(res)
        if (res.data.errno == 0) {
          this.listLoading = false
          this.goods = res.data.list
        }
      }).catch(err => {
        console.log(err)
      })
    },
    search() {
      const orders = {}
      console.log(this.order)
      if (!this.order.createTime) {
        const now = new Date()
        const year = now.getFullYear()
        const month = now.getMonth()
        const day = now.getDate()
        const page = this.currentPage
        this.order.createTime = this.formatTime(new Date(year, month, day))
        this.order.endTime = this.formatTime(now)
      }
      orders.createTime = this.formatTime(new Date(this.order.createTime))
      orders.endTime = this.formatTime(new Date(this.order.endTime))
      console.log(orders)
      sumOrder({ order: orders }).then(res => {
        console.log(res)
        if (res.data.errno == 0) {
          this.listLoading = false
          this.goods = res.data.list
        }
      }).catch(err => {
        console.log(err)
      })
    },
    exportOrderSumExcel() {
      const orders = {}
      if (!this.order.createTime) {
        const now = new Date()
        const year = now.getFullYear()
        const month = now.getMonth()
        const day = now.getDate()
        const page = this.currentPage
        this.order.createTime = this.formatTime(new Date(year, month, day))
        this.order.endTime = this.formatTime(now)
      }
      orders.createTime = this.formatTime(new Date(this.order.createTime))
      orders.endTime = this.formatTime(new Date(this.order.endTime))
      window.location.href = this.$store.getters.exportPath + 'order/exportSum?createTime=' + orders.createTime + '&endTime=' + orders.endTime
    },
    formatTime(date) {
		        console.log(date)
      const year = date.getFullYear()
      const month = date.getMonth() + 1
      const day = date.getDate()
      const hour = date.getHours()
      const minute = date.getMinutes()
      const second = date.getSeconds()
			    return [year, month, day].map(this.formatNumber).join('-') + ' ' + [hour, minute, second].map(this.formatNumber).join(':')
			 },

    formatNumber(n) {
      n = n.toString()
      return n[1] ? n : '0' + n
    }
  }
}
</script>
