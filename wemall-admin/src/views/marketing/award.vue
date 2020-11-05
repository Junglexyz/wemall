<template>
  <div class="app-container">
    <h4>抽奖活动</h4>
    <el-card>
      <div class="filter-container">
        <el-button type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
      </div>
    </el-card>
    <el-table
      v-loading="listLoading"
      :data="listAwards">
      <el-table-column
        prop="id"
        label="id"/>
      <el-table-column
        prop="title"
        label="活动名称"/>
      <el-table-column
        prop="sumMoney"
        label="参与条件"/>
      <el-table-column
        prop="total"
        label="总数量"/>
      <el-table-column
        prop="count"
        label="剩余数量">
        <template slot-scope="scope" @click="handlePrizes">
          <el-tag type="primary" @click="handlePrizes(scope.row)">{{ scope.row.count }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <!-- <el-button type="primary" size="mini" @click="handleAwardUpdate(scope.row)">编辑</el-button> -->
          <el-button type="danger" size="mini" @click="handleAwardDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--弹出框-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="award" :model="award" label-width="100px">
        <el-form-item label="活动名称" prop="title">
          <el-input v-model="award.title"/>
        </el-form-item>
        <el-form-item label="参与金额">
          <el-input v-model="award.sumMoney"/>
        </el-form-item>
        <el-form-item label="奖品份数">
          <el-input v-model="award.count"/>
        </el-form-item>
        <el-card>
          <h3>奖项</h3>
          <el-col :span="10">
            <el-button :plain="true" type="primary" @click="handleAwardGrade">添加</el-button>
          </el-col>
          <el-table :data="awards">
            <el-table-column property="grade" label="奖品等级"/>
            <el-table-column property="count" label="奖品数量">
              <template slot-scope="scope">
                <el-tag type="primary">
                  {{ scope.row.count }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column property="couponId" label="奖品id"/>
            <el-table-column
              align="center"
              label="操作"
              width="250"
              class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <!-- <el-button type="primaryr" size="mini" @click="handleAwardUpdate(scope.row)">编辑</el-button> -->
                <el-button type="danger" size="mini" @click="handleAwardDelete(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-dialog :title="gradeTextMap[gradeStatus]" :visible.sync="gradeVisiable">
            <el-form
              ref="gradeForm"
              :model="gradeForm"
              status-icon
              label-position="left"
              label-width="100px"
              style="width: 400px; margin-left:50px;">
              <el-form-item label="奖品等级" prop="grade">
                <el-input v-model="gradeForm.grade"/>
              </el-form-item>
              <el-form-item label="奖品数量" prop="count">
                <el-input v-model="gradeForm.count"/>
              </el-form-item>
              <el-form-item label="奖品id" prop="count">
                <el-input v-model="gradeForm.couponId"/>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <!-- <el-button @click="specVisiable = false">取消</el-button>
		              <el-button type="primary" @click="handleSpecificationAdd(editGoods)">确定</el-button> -->
              <el-button v-if="gradeStatus=='create'" type="primary" @click="handleGradeAdd">确定</el-button>
              <el-button v-else type="primary" @click="handleGradeUpdate">确定</el-button>
            </div>
          </el-dialog>
        </el-card>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <!-- <el-button type="primary" @click="cancel">取消</el-button> -->
        <!-- <el-button type="primary" @click="createData">确定</el-button> -->
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
        <el-button v-else type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="prizesVisible" title="奖品详情">
      <el-table :data="prizes">
        <el-table-column property="grade" label="奖品等级"/>
        <el-table-column property="couponId" label="优惠券id"/>
        <el-table-column property="used" label="已使用">
          <template slot-scope="scope">
            <el-tag type="primary">
              {{ scope.row.used ? '已抽取' : '未抽取' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column property="userId" label="用户id"/>
        <el-table-column property="userTel" label="用户手机号"/>
      </el-table>
    </el-dialog>
  </div>
</template>
<script type="text/javascript">
import { createAward, listAward, deleteAward, listPrize } from '@/api/award'
export default {
  data() {
    return {
      listAwards: [],
      prizes: [],
      prizesVisible: false,
      listLoading: true,
      award: {},
      awards: [],
      listLoading: true,
      dialogStatus: '',
      textMap: {
			        update: '编辑',
			        create: '创建'
			    },
			    dialogFormVisible: false, // 创建活动
			    gradeVisiable: false, // 创建奖品等级
			    gradeStatus: '',
      gradeTextMap: {
			        update: '编辑',
			        create: '创建'
			    },
			    gradeForm: {}
    }
  },
  created() {
    this.getListAward()
  },
  methods: {
    getListAward() {
      listAward().then(res => {
        console.log(res)
        if (res.data.errno == 0) {
          this.listAwards = res.data.list
          this.listLoading = false
        }
      }).catch(err => {
        console.log(err)
      })
    },
    handlePrizes(row) {
      const awardId = row.id
      this.prizesVisible = true
      listPrize({ awardId: awardId }).then(res => {
        console.log(res)
        if (res.data.errno == 0) {
          this.prizes = res.data.list
          this.listLoading = false
        }
      }).catch(err => {
        console.log(err)
      })
    },
    handleCreate() {
      this.dialogFormVisible = true
      this.dialogStatus = 'create'
    },
    handleAwardGrade() {
      this.gradeVisiable = true
      this.gradeStatus = 'create'
    },
    handleGradeAdd() {
      // var index = this.awards.length - 1
		  //       for (var i = 0; i < this.awards.length; i++) {
		  //         const v = this.awards[i]
		  //         if (v.grade === this.gradeForm.grade) {
		  //           if (v.value === this.gradeForm.value) {
		  //             this.$message({
		  //               type: 'warning',
		  //               message: '已经存在规格值:' + v.value
		  //             })
		  //             this.gradeForm = {}
		  //             this.gradeVisiable = false
		  //             return
		  //           } else {
		  //             index = i
		  //           }
		  //         }
		  //       }

		  //       this.awards.splice(index + 1, 0, this.gradeForm)
		  		this.awards.push(this.gradeForm)
		        this.gradeVisiable = false
		        this.gradeForm = {}
		        console.log(this.awards)
    },
    handleAwardUpdate(row) {
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.award = row
    },
    handleAwardDelete(row) {
      this.$confirm('确认删除?', '删除', {
		          confirmButtonText: '确定',
		          cancelButtonText: '取消',
		          type: 'warning'
		        }).then(() => {
		            deleteAward({ awardId: row.id }).then(res => {
				       console.log(res)
				       if (res.data.errno == 0) {
				       	  this.$message({
			                type: 'success',
			                message: '删除成功!'
			              })
				       }
        }).catch(err => {
          console.log(err)
          this.$message({
			              type: 'danger',
			              message: '删除失败!'
			            })
        })
		        }).catch(() => {
		          this.$message({
		            type: 'info',
		            message: '已取消'
		          })
		        })
    },
    createData() {
      console.log(this.award, this.awards)
      this.award.total = this.award.count
      const award = { award: this.award, prizes: this.awards }
      console.log(award)
      createAward(award).then(res => {
        console.log(res)
      }).catch(err => {
        console.log(err)
      })
    },
    updateData() {

    }
  }
}
</script>
