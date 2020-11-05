<template>
  <div class="app-container">
    <h4>优惠券管理</h4>
    <el-card>
      <div class="filter-container">
        <el-button type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
      </div>
    </el-card>
    <el-table
      v-loading="listLoading"
      :data="coupons"
      style="width: 100%">
      <el-table-column
        prop="couponId"
        label="优惠券id"/>
      <el-table-column
        prop="title"
        label="名称"/>
      <el-table-column
        prop="targetMoney"
        label="满足的金额"/>
      <el-table-column
        prop="discountMoney"
        label="优惠金额"/>
      <el-table-column
        prop="status"
        label="状态"/>
      <el-table-column
        prop="startTime"
        label="开始时间">
        <template slot-scope="scope">
          {{ formatTime(scope.row.startTime) }}
        </template>
      </el-table-column>
      <el-table-column
        prop="endTime"
        label="结束时间">
        <template slot-scope="scope">
          {{ formatTime(scope.row.endTime) }}
        </template>
      </el-table-column>
      <el-table-column
        prop="number"
        label="发放数量"/>
      <el-table-column align="center" label="操作" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--弹出框-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="coupon" :model="coupon" label-width="100px">
        <el-form-item>
          <el-select v-model="coupon.types" placeholder="优惠类型">
            <el-option
              v-for="item in typeList"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="优惠券名称">
          <el-input v-model="coupon.title"/>
        </el-form-item>
        <el-form-item label="满足金额">
          <el-input v-model="coupon.targetMoney"/>
        </el-form-item>
        <el-form-item label="优惠金额" prop="discountMoney">
          <el-input v-model="coupon.discountMoney"/>
        </el-form-item>
        <el-form-item label="发放数量">
          <el-input v-model="coupon.number"/>
        </el-form-item>
        <el-form-item label="状态">
          <el-input v-model="coupon.status"/>
        </el-form-item>
        <el-form-item label="优惠开始时间" prop="srartTime">
          <el-date-picker
            v-model="coupon.startTime"
            type="datetime"
            placeholder="选择日期时间"/>
        </el-form-item>
        <el-form-item label="优惠结束时间" prop="srartTime">
          <el-date-picker
            v-model="coupon.endTime"
            type="datetime"
            placeholder="选择结束时间"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="cancel">取消</el-button>
        <!-- <el-button type="primary" @click="createData">确定</el-button> -->
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
        <el-button v-else type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script type="text/javascript">
import { createCoupon, listCoupon, updateCoupon, deleteCoupon } from '@/api/coupon'
export default {
  data() {
    return {
      coupon: {},
      dialogFormVisible: false,
      coupons: [],
      listLoading: true,
      dialogStatus: '',
      textMap: {
		          update: '编辑优惠券',
		          create: '创建优惠券'
		        },
		        typeList: [{ value: 1, label: '满减' },
		        { value: 2, label: '普通红包' },
		        { value: 3, label: '不叠加红包' }
		        ]
    }
  },
  created() {
    this.getListCoupon()
  },
  methods: {
    handleCreate() {
      this.dialogFormVisible = true
      this.dialogStatus = 'create'
    },
    cancel() {
      this.dialogFormVisible = false
    },
    getListCoupon() {
      listCoupon().then(response => {
        console.log(response)
        const data = response.data
        if (data.errno == 0) {
          this.coupons = data.list
          this.listLoading = false
        }
      }).catch(err => {
        console.log(err)
        this.listLoading = false
      })
    },
    createData() {
      const coupon = this.coupon
      console.log(this.formatTime(new Date(coupon.startTime)))

      createCoupon(coupon).then(response => {
        const data = response.data
        if (data.errno == 0) {
			            this.dialogFormVisible = false
			            this.getListCoupon()
			            // this.$refs['category'].resetFields();
			            this.$message({
			              message: '添加成功！',
			              type: 'success'
			            })
			        }
      }).catch(err => {
        console.log(err)
      })
    },
    handleUpdate(row) {
      this.dialogFormVisible = true
      this.dialogStatus = 'update'
      console.log(row)
      this.coupon = row
    },
    updateData() {
      updateCoupon(this.coupon).then(response => {
        const data = response.data
        if (data.errno == 0) {
			            this.dialogFormVisible = false
			            this.getListCoupon()
			            // this.$refs['category'].resetFields();
			            this.$message({
			              message: '修改成功！',
			              type: 'success'
			            })
			            this.dialogFormVisible = false
			        }
      }).catch(err => {
        console.log(err)
        this.dialogFormVisible = false
      })
    },
    handleDelete(row) {
      this.$confirm('确认删除此商品?', '删除商品', {
		          confirmButtonText: '确定',
		          cancelButtonText: '取消',
		          type: 'warning'
		        }).then(() => {
		          const query = { couponId: row.couponId }
		          console.log(query)
		          deleteCoupon(query).then(response => {
		            console.log(response)
		            const data = response.data
		            if (data.errno == 0) {
		              this.getListCoupon()
		              this.$message({
		                type: 'success',
		                message: '删除成功!'
		              })
		            }
		          }).catch((err) => {
		            console.log(err)
		            this.$message({
		              type: 'danger',
		              message: '删除失败!'
		            })
		          })
		        }).catch(() => {
		          this.$message({
		            type: 'info',
		            message: '已取消删除'
		          })
		        })
    },
    formatTime(dates) {
			  const date = new Date(dates)
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
