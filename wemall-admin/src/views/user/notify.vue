<template>
	<div class="app-container">
		<h4>活动管理</h4>
	    <!-- 查询和其他操作 -->
	    <el-card>
	      <div class="filter-container">
	        <el-button type="primary" icon="el-icon-edit" @click="handleCreate">发布新活动</el-button>
	      </div>
	    </el-card>
		<el-table
	    :data="activities"
	    style="width: 100%">
	    	<el-table-column
		      label="活动编号"
		      prop="id">
		    </el-table-column>
		    <el-table-column
		      label="活动名称"
		      prop="name">
		    </el-table-column>
		    <el-table-column
		      label="活动地点"
		      prop="place">
		    </el-table-column>
		    <el-table-column
		      label="活动形式"
		      prop="types">
		    </el-table-column>
		    <el-table-column
		      label="活动时间"
		      prop="time">
		    </el-table-column>
		    <el-table-column
		      label="活动须知"
		      prop="remark">
		    </el-table-column>
		    <el-table-column
		      label="活动状态"
		      prop="status">
		    </el-table-column>
		    <el-table-column align="center" label="操作" class-name="small-padding fixed-width">
		        <template slot-scope="scope">
		          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
		          <el-button type="danger" size="mini" @click="handleDelete(scope.row)">取消活动</el-button>
		        </template>
		    </el-table-column>
		</el-table>
		<!--弹出框-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :model="activity" ref="activity" label-width="100px">
        <el-form-item label="活动名称" prop="name">
          <el-input v-model="activity.name"></el-input>
        </el-form-item>
        <el-form-item label="活动地点" prop="place">
          <el-input v-model="activity.place"></el-input>
        </el-form-item>
        <el-form-item label="活动时长" prop="duration">
          <el-input v-model="activity.duration"></el-input>
        </el-form-item>
        <el-form-item label="活动形式" prop="types">
          <el-input v-model="activity.types"></el-input>
        </el-form-item>
        <el-form-item label="活动时间" prop="time">
          <el-date-picker
            v-model="activity.startTime"
            type="datetime"
            placeholder="活动时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动须知" prop="remark">
          <el-input v-model="activity.remark"></el-input>
        </el-form-item>
        <el-form-item label="活动状态" prop="status">
          <el-input v-model="activity.status"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="cancel">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
        <el-button v-else type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>
	</div>
</template>
<script type="text/javascript">
	import { createActivity, listActivity, updateActivity } from '@/api/activity'
	export default {
		data(){
			return {
				activity: {},
				activities: [],
				dialogFormVisible: false,
				dialogStatus: '',
		        textMap: {
		          update: '编辑活动',
		          create: '创建活动'
		        },
			}
		},
		created(){
			this.getListActivity()
		},
		methods: {
			getListActivity(){
				listActivity().then(response => {
			        let data = response.data
			        console.log(data)
			        if(data.errno == 0){
			           this.activities = data.list
			        }
		        }).catch((err) => {
		          this.activities = []
		        })
			},
			cancel(){
				this.dialogFormVisible = false
			},
			handleUpdate(row){
				this.dialogStatus = 'update'
				this.dialogFormVisible = true
				this.activity = row
			},
			updateData(){
				let activity = this.activity
				updateActivity(activity).then(response => {
			        let data = response.data
			        console.log(data)
			        if(data.errno == 0){
			           console.log('更新成功')
			           this.getListActivity()
			           this.dialogFormVisible = false
			        }
		        }).catch((err) => {
		          console.log(err)
		        })
			},
			handleCreate(row){
				this.dialogStatus = 'create'
				this.dialogFormVisible = true
			},
			handleDelete(row){
				let activity = row
				activity.status = 0
				updateActivity(activity).then(response => {
			        let data = response.data
			        console.log(data)
			        if(data.errno == 0){
			           console.log('取消成功')
			           this.getListActivity()
			           this.dialogFormVisible = false
			        }
		        }).catch((err) => {
		          console.log(err)
		        })
			},
			createData(){
				console.log(this.activity)
				createActivity(this.activity).then(response => {
					let res = response.data
					if(res.errno == 0){
						this.dialogFormVisible = false
			            this.getListActivity()
			            this.$refs['activity'].resetFields();
			            this.$message({
			              message: '添加成功！',
			              type: 'success'
			            })
					}
				}).catch(err => {
					console.log(err)
				})
			}
		}
	}
</script>