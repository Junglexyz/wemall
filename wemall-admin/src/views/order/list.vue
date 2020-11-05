<template>
  <div class="app-container">
    <h4>订单列表</h4>
    <el-card>
      <el-select v-model="order.status" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"/>
      </el-select>
      <el-date-picker
        v-model="order.createTime"
        type="datetime"
        placeholder="开始日期时间"/>
      <el-date-picker
        v-model="order.endTime"
        type="datetime"
        placeholder="结束日期时间"/>
      <el-button type="primary" @click="search">立即查找</el-button>
      <el-button type="primary" @click="exportExcel">导出订单</el-button>
    </el-card>
    <el-table
      v-loading="listLoading"
      ref="multipleTable"
      :data="listOrder"
      style="width: 100%;margin-bottom: 20px;"
      row-key="id"
      border
      element-loading-text="正在加载中。。。"
      @selection-change="handleSelectionChange">
      <el-table-column
        type="selection"/>
      <el-table-column
        prop="orderId"
        label="订单编号"/>
      <el-table-column
        prop="totalMoney"
        label="订单总价"/>
      <el-table-column
        prop="packupCode"
        label="取件码"/>
      <el-table-column
        prop="payMoney"
        label="实付金额"/>
      <el-table-column
        prop="returnMoney"
        label="返现金额"/>
      <el-table-column
        prop="status"
        label="状态">
        <template slot-scope="scope">
          <el-tag :type="tag[scope.row.status]">{{ statused[scope.row.status] }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="danger" size="mini" @click="deliver(scope.row)">发货</el-button>
          <el-button type="primary" size="mini" @click="handleOrderDetail(scope.row)">详情</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--弹出框-->
    <el-dialog :visible.sync="dialogFormVisible" title="订单详情">
      <el-table
        :data="orderDetail"
        stripe
        style="width: 100%">
        <el-table-column
          prop="orderId"
          label="订单编号"/>
        <el-table-column
          prop="title"
          label="商品名称"/>
        <el-table-column
          prop="number"
          label="商品数量"/>
        <el-table-column
          prop="sellPrice"
          label="销售价格"/>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <!-- <el-button type="primary" @click="refund">退款</el-button> -->
        <el-button type="primary" @click="deliver">发货</el-button>
      </div>
    </el-dialog>
    <div>
      <el-pagination
        :current-page="currentPage"
        :total="total"
        :page-size="10"
        background
        layout="total,prev, pager, next"
        @current-change="handleCurrentChange"/>
    </div>
  </div>
</template>
<script type="text/javascript">
import { listOrder, updateOrder, exportOrder } from '@/api/order'
export default {
  data() {
    return {
      order: {
        status: 0
      },
      orderDetail: {},
      dialogFormVisible: false,
      listOrder: [],
      listLoading: true,
      options: [{
		          value: '1',
		          label: '待付款'
		        }, {
		          value: '2',
		          label: '待发货'
		        }, {
		          value: '3',
		          label: '待收货'
		        }, {
		          value: '4',
		          label: '待退款'
		        }, {
		          value: '5',
		          label: '已退款'
		        }, {
		          value: '0',
		          label: '全部'
		        }],
		        statused: ['已完结', '待付款', '待发货', '待收货', '待退款', '已退款'],
		        tag: ['', 'success', 'primary', 'info', 'danger', 'warning'],
		        changeInfo: {},
		        currentPage: 1,
	        	total: 0,
	        	multipleSelection: []
		    }
  },
  created() {
    this.getListOrder()
  },
  methods: {
    handleCurrentChange(val) {
	           console.log('当前改变：' + val)
	           this.currentPage = val
	           this.search()
	           this.currentPage = 1
	        },
    getListOrder() {
      const order = {}
      const now = new Date()
      const year = now.getFullYear()
      const month = now.getMonth()
      const day = now.getDate()
      const page = this.currentPage
      order.createTime = this.formatTime(new Date(year, month, day))
      order.endTime = this.formatTime(new Date())
      order.status = 0
      order.page = page
      listOrder({ order: order }).then(response => {
        console.log(response)
        const data = response.data
        if (data.errno == 0) {
          const result = response.data.result
          this.total = result.count
          this.listOrder = result.list
          this.listLoading = false
        }
      }).catch(err => {

      })
    },
    handleOrderDetail(row) {
      console.log(row)
      this.changeInfo = row
      this.orderDetail = row.orderDetail,
      this.dialogFormVisible = true
    },
    exportExcel() {
      window.location.href = this.$store.getters.exportPath + 'order/export?createTime=' + this.order.createTime + '&endTime=' + this.order.endTime
    },
    updateData() {

    },
    cancel() {
      this.dialogFormVisible = false
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
        this.order.page = page
      }
      orders.createTime = this.formatTime(new Date(this.order.createTime))
      orders.endTime = this.formatTime(new Date(this.order.endTime))
      orders.status = this.order.status
      orders.page = this.currentPage
      listOrder({ order: orders }).then(response => {
        console.log(response)
        const data = response.data
        if (data.errno == 0) {
          const result = response.data.result
          this.total = result.count
          this.listOrder = result.list
          this.listLoading = false
        }
      }).catch(err => {
        console.log(err)
      })
    },
    deliver(row) {
      this.$confirm('确认更新状态至发货?', '发货确认', {
		          confirmButtonText: '确定',
		          cancelButtonText: '取消',
		          type: 'warning'
		        }).then(() => {
		          const query = { id: row.id, status: 3, userId: row.userId, orderId: row.orderId, packupCode: row.packupCode, deliveryWay: row.deliveryWay }
		          console.log(query)
		          updateOrder(query).then(response => {
		            console.log(response)
		            const data = response.data
		            if (data.errno == 0) {
		              this.search()
		              this.$message({
		                type: 'success',
		                message: '发货成功!'
		              })
		            }
		          }).catch((err) => {
		            console.log(err)
		            this.$message({
		              type: 'danger',
		              message: '发货失败!'
		            })
		          })
		        }).catch(() => {
		          this.$message({
		            type: 'info',
		            message: '已取消'
		          })
		        })
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
    },
    toggleSelection(rows) {
      console.log(rows)
		        if (rows) {
		          rows.forEach(row => {
		            this.$refs.multipleTable.toggleRowSelection(row)
		          })
		        } else {
		          this.$refs.multipleTable.clearSelection()
		        }

		      	console.log(this.multipleTable)
		      },
		      handleSelectionChange(val) {
		        this.multipleSelection = val
		        console.log(this.multipleSelection)
		      }
  }
}
</script>
