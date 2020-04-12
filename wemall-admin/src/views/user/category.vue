<template>
	<div class="app-container">
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
		      label="类目id">
		    </el-table-column>
		    <el-table-column
		      prop="name"
		      label="类目名称">
		    </el-table-column>
        <el-table-column
          prop="describe"
          label="类目简介">
        </el-table-column>
        <!-- <el-table-column
          prop="level"
          label="类目级别">
          <template slot-scope="scope">
            <el-tag :type="scope.row.level === 'L1' ? 'primary' : 'success' ">{{ scope.row.level === 'L1' ? '一级类目' : '二级类目' }}</el-tag>
          </template>
        </el-table-column> -->
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
          <el-input v-model="category.typeName"></el-input>
        </el-form-item>
        <el-form-item label="类目描述" prop="name">
          <el-input v-model="category.describe"></el-input>
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
<script>
  export default {
    data() {
      return {
        listCat: [{
          id: 1000001,
          name: '心理咨询'
        },{
          id: 1000002,
          name: '法律指导'
        }],
        dialogFormVisible: false,
        category:{
          id: '',
          name: '',
          level: '',
          pid: ''
        },
        listLoading: false,
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
	    // this.getListCategory()
	  },
    methods: {
      // 获取目录列表
      getListCategory() {
	      
    	},
      // 编辑按钮触发
      handleUpdate(row) {
        /*console.log(row)
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.category = row*/
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
       /* updateCategory(this.category).then(response => {
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
        })*/
      },
      // 删除类目
      handleDelete(row){
        /*this.$confirm('确认删除此目录?', '删除目录', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let query = {id: row.id, level: row.level}
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
        });*/
      },
      // 关闭弹窗
      cancel(){
        this.dialogFormVisible = false
      },
      beforeUpload(file) {
          /*console.log(file.type)
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
          return isJPG && isLt2M;*/
      },
      errorUpload(){
        // this.loading = false
      },
      // 上传图片
      uploadPicUrl: function(response) {
         /* console.log(response)
          this.category.picUrl = 'http://test-jungle.oss-cn-beijing.aliyuncs.com/goods/'+response.result
          this.loading = false
          console.log(this.category.picUrl)*/
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