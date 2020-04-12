<template>
	<div class="app-container">
		<h4>搜救记录</h4>
		<el-table
	    :data="records"
	    style="width: 100%">
	    	<el-table-column
		      label="记录编号"
		      prop="id">
		    </el-table-column>
		    <el-table-column
		      label="事发地点"
		      prop="place">
		    </el-table-column>
		    <el-table-column
		      label="事发时间"
		      prop="accidentTime">
		    </el-table-column>
		    <el-table-column
		      label="事故类型"
		      prop="typesName">
		    </el-table-column>
		    <el-table-column
		      label="事故概述"
		      prop="summary">
		    </el-table-column>
		    <el-table-column
		      label="状态"
		      prop="status">
		    </el-table-column>
		    <el-table-column
		      label="参与人数"
		      prop="number">
		      <template slot-scope="scope">
	            <el-tag type="primary" @click="showDetail(scope.row)">{{scope.row.number}}</el-tag>
	          </template>
		    </el-table-column>
		    <el-table-column align="center" label="操作" class-name="small-padding fixed-width">
		        <template slot-scope="scope">
		          <!-- <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">召集志愿者</el-button> -->
		          <el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
		        </template>
		    </el-table-column>
		</el-table>
		<el-dialog title="编辑" :visible.sync="editFormVisible">
	  	    <el-form :model="accident" ref="accident" label-width="100px">
	        <!-- <el-form-item label="险情类型" prop="typesName">
	          <el-select v-model="accident.typesName" placeholder="请选择">
	            <el-option
	              v-for="item in options"
	              :key="item.value"
	              :label="item.label"
	              :value="item.label">
	            </el-option>
	          </el-select>
	        </el-form-item> -->
	        <!-- <el-form-item label="事发地点">
	          <el-col :span="8">
	            <el-input v-model="accident.place"></el-input>
	            <div @click="handleMap">获取详细地址</div>
	            <div v-if="position.longitude">经纬度: {{position.longitude}},{{position.latitude}}</div>
	          </el-col>
	        </el-form-item> -->
	        <!-- <el-form-item label="事发时间" prop="accidentTime">
	          <el-date-picker
	            v-model="accident.accidentTime"
	            type="datetime"
	            placeholder="事发时间">
	          </el-date-picker>
	        </el-form-item>
	        <el-form-item label="事故概述" prop="describe">
	          <el-input v-model="accident.summary"></el-input>
	        </el-form-item> -->
	        <el-form-item label="事故状态">
	          <el-select v-model="accident.status" placeholder="请选择">
	            <el-option
	              v-for="item in status"
	              :key="item.value"
	              :label="item.label"
	              :value="item.value">
	            </el-option>
	          </el-select>
	        </el-form-item>
	        <el-form-item label="召集志愿者">
	          <el-switch v-model="accident.callVolenteer"></el-switch>
	        </el-form-item>
	      </el-form>
	        <div slot="footer" class="dialog-footer">
		        <el-button type="primary" @click="cancel">取消</el-button>
		        <el-button type="primary" @click="updateData">确定</el-button>
		    </div>
	    </el-dialog>
	    <el-dialog title="参与志愿者详情" :visible.sync="detailShow">
	  	    <el-table
		    :data="details"
		    style="width: 100%">
		    	<el-table-column
			      label="志愿者编号"
			      prop="userId">
			    </el-table-column>
			    <el-table-column
			      label="志愿者姓名"
			      prop="username">
			    </el-table-column>
			    <el-table-column
			      label="联系方式"
			      prop="tel">
			    </el-table-column>
			</el-table>
	        <!-- <div slot="footer" class="dialog-footer">
		        <el-button type="primary" @click="cancel">取消</el-button>
		        <el-button type="primary" @click="updateData">确定</el-button>
		    </div> -->
	    </el-dialog>
	</div>
</template>
<script>
	import { listAccident, updateAccident, listActivityRecord } from '@/api/accident'
	export default {
		data(){
			return {
				records: [],
				accident: {},
				editFormVisible: false,
				status: [{
		          value: '1',
		          label: '搜救中'
		        }, {
		          value: '2',
		          label: '已完结'
		        }],
		        details: [],
		        detailShow: false
			}
		},
		created(){
			this.getListAccident()
		},
		methods: {
			getListAccident(){
				listAccident().then(response => {
			        let data = response.data
			        console.log(data)
			        if(data.errno == 0){
			           this.records = data.list
			        }
		        }).catch((err) => {
		          this.records = []
		        })
			},
			// 参与活动人员详情
			showDetail(row){
				console.log(row)
				this.detailShow = true
				listActivityRecord({activityId: row.id}).then(response => {
					let data = response.data
					if(data.errno == 0){
						console.log(data)
						this.details = data.data
					}
				}).catch(err => {

				})
			},
			handleEdit(row){
				this.editFormVisible = true
				this.accident = row
			},
			cancel(){
				this.editFormVisible = false
			},
			updateData(){
				console.log(this.accident)
				this.accident.callVolenteer = this.accident.callVolenteer ? 1 : 0
				updateAccident(this.accident).then(response => {
					let data = response.data
					if(data.errno == 0){
						console.log('更新成功')
						this.editFormVisible = false
						this.getListAccident()
					}
				}).catch(err => {
					console.log(err)
				})
			}
		}
	}
</script>
