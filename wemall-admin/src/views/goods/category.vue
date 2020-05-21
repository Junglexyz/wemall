<template>
	<div class="container">
    <h4>类目管理</h4>
    <!-- 查询和其他操作 -->
    <el-card>
      <div class="filter-container">
        <el-button type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
      </div>
    </el-card>
		<el-table
		    :data="listCat"
		    style="width: 100%;margin-bottom: 20px;"
		    row-key="id"
		    border
        v-loading="listLoading" 
        element-loading-text="正在加载中。。。"
		    :tree-props="{children: 'children'}">
		    <el-table-column
		      prop="id"
		      label="类目id"
		      sortable>
		    </el-table-column>
		    <el-table-column
		      prop="name"
		      label="类目名称">
		    </el-table-column>
        <el-table-column
          prop="picUrl"
          label="类目图片">
          <template slot-scope="scope">
              <img v-if="scope.row.picUrl" :src="scope.row.picUrl" width="50" height="50">
          </template>
        </el-table-column>
        <el-table-column
          prop="level"
          label="类目级别">
          <template slot-scope="scope">
            <el-tag :type="scope.row.level === 'L1' ? 'primary' : 'success' ">{{ scope.row.level === 'L1' ? '一级类目' : '二级类目' }}</el-tag>
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
      <el-form :model="category" ref="category" label-width="100px">
        <el-form-item label="类目名称" prop="name">
          <el-input v-model="category.name"></el-input>
        </el-form-item>
        <el-form-item label="类目图片" prop="name" v-loading="loading">
          <el-upload
              :action="uploadPath" 
              :show-file-list="false"
              :on-success="uploadPicUrl" 
              :before-upload="beforeUpload"
              :on-error="errorUpload"
              :headers="headers"
              element-loading-text="上传图片中"
              class="avatar-uploader"
              accept=".jpg,.jpeg,.png,.gif">
              <img v-if="category.picUrl" :src="category.picUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"/>
            </el-upload>
        </el-form-item>
        <el-form-item label="类目级别" prop="level">
          <el-select v-model="category.level" placeholder="请选择类目级别">
            <el-option label="一级类目" value="L1"></el-option>
            <el-option label="二级目录" value="L2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item v-if="category.level === 'L2'" label="父级类目" prop="pid">
          <el-select v-model="category.pid">
            <el-option v-for="item in listCat" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="类目简介">
          <el-input v-model="category.desc"></el-input>
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="cancel">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
        <el-button v-else type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>
	</div>
</template>
<script>
  import { createCategory, listCategory, updateCategory, deleteCategory } from '@/api/category'
  import { uploadPath } from '@/api/storage'
  export default {
    data() {
      return {
        uploadPath,
        listCat: [],
        dialogFormVisible: false,
        category:{
          id: '',
          name: '',
          level: '',
          pid: ''
        },
        listLoading: true,
        dialogStatus: '',
        textMap: {
          update: '编辑类目',
          create: '创建类目'
        },
        loading: false
      }
    },
    computed: {
      headers() {
        return {
          'X-Upload-Type': 'goods'
        }
      }
    },
    created() {
	    this.getListCategory()
	  },
    methods: {
      // 获取目录列表
      getListCategory() {
        console.log(this.$store.getters.storagePath)
	      listCategory().then(response => {
          let data = response.data
          console.log(data)
          if(data.errno == 0){
            this.listCat = data.result
            this.listLoading = false
          }
	        
	      }).catch((err) => {
	      	console.log(err)
          this.listLoading = false
	      })
    	},
      // 编辑按钮触发
      handleUpdate(row) {
        console.log(row)
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.category = row
      },
      // 删除按钮触发
      handleCreate(){
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
      },
      // 创建类目
      createData(){
        console.log(this.category)
        createCategory(this.category).then(response => {
          let data = response.data
          console.log(data)
          if(data.errno == 0){
            this.dialogFormVisible = false
            this.getListCategory()
            this.$refs['category'].resetFields();
            this.$message({
              message: '添加成功！',
              type: 'success'
            })
          }
        }).catch((err) => {
          this.$message.error('添加失败！');
        })
      },
      // 更新类目
      updateData(){
        updateCategory(this.category).then(response => {
          let data = response.data
          console.log(data)
          if(data.errno == 0){
            this.dialogFormVisible = false
            this.getListCategory()
            this.$message({
              message: '更新成功！',
              type: 'success'
            })
            this.$refs['category'].resetFields();
          }
          
        }).catch((err) => {
          console.log(err)
          this.$message.error('更新失败！');
        })
      },
      // 删除类目
      handleDelete(row){
        this.$confirm('确认删除此目录?', '删除目录', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let query = {id: row.id, level: row.level, pid: row.pid}
          console.log(query)
          deleteCategory(query).then(response => {
            console.log(response)
            let data = response.data
            if(data.errno == 0){
              this.getListCategory()
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
            } else {
              this.$message({
                type: 'danger',
                message: data.errmsg
              });
            }
          }).catch((err) => {
            console.log(err)
            this.$message({
              type: 'danger',
              message: err.data.errmsg
            });
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      },
      // 关闭弹窗
      cancel(){
        this.dialogFormVisible = false
      },
      beforeUpload(file) {
          console.log(file.type)
          const isJPG = file.type === 'image/jpg' || file.type ==='image/png' || file.type ==='image/jpeg';
          const isLt2M = file.size / 1024 / 1024 < 2;

          if (!isJPG) {
            this.$message.error('上传头像图片只能是 JPG、JPEG 或 PNG 格式!');
          }
          if (!isLt2M) {
            this.$message.error('上传头像图片大小不能超过 2MB!');
          }
          if(isJPG && isLt2M){
            this.loading = true
          }
          return isJPG && isLt2M;
      },
      errorUpload(){
        this.loading = false
      },
      // 上传图片
      uploadPicUrl: function(response) {
          console.log(response)
          this.category.picUrl = this.$store.getters.storagePath + 'goods/'+response.result
          this.loading = false
          console.log(this.category.picUrl)
      },
    },
  }
</script>
<style type="text/css">
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