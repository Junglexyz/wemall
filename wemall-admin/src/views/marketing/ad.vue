<template>
	<div class="container">
		<h4>广告管理</h4>
		<el-card>
	      <div class="filter-container">
	        <el-button type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
	      </div>
	    </el-card>
	    <el-table
	      :data="ads"
	      style="width: 100%"
	      v-loading="listLoading">
	      <el-table-column
	        prop="name"
	        label="名称">
	      </el-table-column>
	      <el-table-column
	        prop="content"
	        label="内容">
	      </el-table-column>
	      <el-table-column
	        prop="imgUrl"
	        label="bannaer图片">
	        <template slot-scope="scope">
	            <img v-if="scope.row.imgUrl" :src="scope.row.imgUrl" width="40">
	        </template>
	      </el-table-column>
	      <el-table-column align="center" label="操作" class-name="small-padding fixed-width">
	        <template slot-scope="scope">
	          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
	          <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
	        </template>
	      </el-table-column>
	    </el-table>
	    <!--弹出框-->
	    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
	      <el-form :model="ad" ref="ad" label-width="100px">
	        <el-form-item label="广告名称" prop="name">
	          <el-input v-model="ad.name"></el-input>
	        </el-form-item>
	        <el-form-item label="广告内容">
	          <el-input v-model="ad.content"></el-input>
	        </el-form-item>
	        <el-form-item label="bannaer图片">
	          <el-upload
	            :action="uploadPath" 
	            :show-file-list="false"
	            :on-success="uploadPicUrl"
	            :before-upload="beforeUpload"
	            :headers="headers"
	            element-loading-text="上传图片中"
	            class="avatar-uploader"
	            accept=".jpg,.jpeg,.png,.gif">
	            <img v-if="ad.imgUrl" :src="ad.imgUrl" class="avatar">
	            <i v-else class="el-icon-plus avatar-uploader-icon"/>
	          </el-upload>
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
	import { createAd, listAd ,updateAd, deleteAd} from '@/api/ad'
	import { uploadPath } from '@/api/storage'
	export default {
		data(){
			return {
				uploadPath,
				ad: {},
				dialogFormVisible: false,
				ads: [],
				listLoading: true,
				dialogStatus: '',
				textMap: {
		          update: '编辑广告',
		          create: '创建广告'
		        }
			}
		},
		computed: {
	      headers() {
	        return {
	          'X-Upload-Type': 'common'
	        }
	      }
	    },
		created(){
			this.getListAd()
		},
		methods: {
			handleCreate(){
				this.dialogFormVisible = true
				this.dialogStatus = 'create'
			},
			cancel(){
				this.dialogFormVisible = false
			},
			getListAd(){
				listAd().then(response => {
					console.log(response)
					let data = response.data
					if(data.errno == 0){
						this.ads = data.list
						this.listLoading = false
					}
				}).catch(err => {
					console.log(err)
					this.listLoading = false
				})
			},
			createData(){
				let ad = this.ad

				createAd(ad).then(response => {
					let data = response.data;
					if(data.errno == 0){
			            this.dialogFormVisible = false
			            // this.$refs['category'].resetFields();
			            this.ad = {}
			            this.$message({
			              message: '添加成功！',
			              type: 'success'
			            })
			            this.getListAd()
			        }
				}).catch(err =>{
					console.log(err)
				}) 
			},
			handleUpdate(row){
				this.dialogFormVisible = true
				this.dialogStatus = 'update'
				console.log(row)
				this.ad = row
			},
			updateData(){
				console.log(this.ad)
				updateAd(this.ad).then(response => {
					let data = response.data;
					if(data.errno == 0){
			            this.dialogFormVisible = false
			            this.getListAd()
			            // this.$refs['ad'].resetFields();
			            this.ad = {}
			            this.$message({
			              message: '修改成功！',
			              type: 'success'
			            })
			            this.dialogFormVisible = false
			        }
				}).catch(err =>{
					console.log(err)
					this.dialogFormVisible = false
				}) 
			},
			handleDelete(row){
				this.$confirm('确认删除此商品?', '删除商品', {
		          confirmButtonText: '确定',
		          cancelButtonText: '取消',
		          type: 'warning'
		        }).then(() => {
		          let query = {id: row.id}
		          console.log(query)
		          deleteAd(query).then(response => {
		            console.log(response)
		            let data = response.data
		            if(data.errno == 0){
		              this.getListAd()
		              this.$message({
		                type: 'success',
		                message: '删除成功!'
		              });
		            }
		          }).catch((err) => {
		            console.log(err)
		            this.$message({
		              type: 'danger',
		              message: '删除失败!'
		            });
		          })
		        }).catch(() => {
		          this.$message({
		            type: 'info',
		            message: '已取消删除'
		          });          
		        });
			},
			uploadPicUrl: function(response) {
		        // apcdebugger
		        console.log(response)
		        this.ad.imgUrl = this.$store.getters.storagePath + 'common/'+response.result
		        this.loading = false
		        console.log(this.ad)
		    },
		    beforeUpload(file) {
		        const isJPG = file.type === 'image/jpg' || file.type ==='image/png' || file.type === 'image/jpeg';
		        const isLt2M = file.size / 1024 / 1024 < 2;

		        if (!isJPG) {
		          this.$message.error('上传头像图片只能是 JPG 或 PNG 格式!');
		        }
		        if (!isLt2M) {
		          this.$message.error('上传头像图片大小不能超过 2MB!');
		        }
		        this.loading = true
		        return isJPG && isLt2M;
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
<style>
  .app-container{
  	/*width: 100%;*/
  	/*height: 100vh;*/
  	/*display: flex;
  	flex-flow: column nowrap;
  	justify-content: center;
  	align-items: center;
    color: #ffffff*/
  }

  .el-card {
    margin-bottom: 10px;
  }

  .el-tag + .el-tag {
    margin-left: 10px;
  }

  .input-new-keyword {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }

  .avatar-uploader .el-upload {
    width: 145px;
    height: 145px;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #20a0ff;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 120px;
    height: 120px;
    line-height: 120px;
    text-align: center;
  }

  .avatar {
    width: 145px;
    height: 145px;
    display: block;
  }
</style>
