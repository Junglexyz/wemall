<template>
	<div class="app-container">
		<h4>商品列表</h4>
		<el-card>
			<el-cascader
	            v-model="value"
	            :options="options"
	            :props="{ expandTrigger: 'hover' }"
	            @change="handleChange">
	        </el-cascader>
	        <el-select v-model="status" placeholder="请选择">
			    <el-option
			      v-for="item in statuses"
			      :key="item.value"
			      :label="item.label"
			      :value="item.value">
			    </el-option>
			</el-select>
	        <el-button type="primary" @click="search">立即查找</el-button>
		</el-card>
		<el-table
	      :data="listGoods"
	      v-loading="listLoading">
	      <el-table-column
	        prop="title"
	        label="名称">
	      </el-table-column>
	      <!-- <el-table-column
	        prop="goodsDescrible"
	        label="描述">
	      </el-table-column> -->
	      <el-table-column
	        prop="status"
	        label="状态">
	        <template slot-scope="scope">
	            <el-tag v-if="scope.row.status == 1" type="primary" width="40">在售</el-tag>
	            <el-tag v-if="scope.row.status == 0" type="danger" width="40">下架中</el-tag>
	        </template>
	      </el-table-column>
	      <el-table-column
	        prop="cover"
	        label="封面图">
	        <template slot-scope="scope">
	            <img v-if="scope.row.cover" :src="scope.row.cover" width="40">
	        </template>
	      </el-table-column>
	      <el-table-column
	        prop="sellPrice"
	        label="售价">
	      </el-table-column>
	      <el-table-column
	        prop="special"
	        label="是否特价">
	        <template slot-scope="scope">
	        	{{scope.row.special ? '是' : '否'}}
	        </template>
	      </el-table-column>
	      <el-table-column
	        prop="originPrice"
	        label="原价">
	      </el-table-column>
	      <el-table-column
	        prop="returnCash"
	        label="返现金额">
	      </el-table-column>
	      <el-table-column
	        prop="purchasingPrice"
	        label="进货价格">
	      </el-table-column>
	      <el-table-column
	        prop="purchasingUnit"
	        label="进货单位">
	      </el-table-column>
	      <el-table-column
	        prop="stock"
	        label="库存数量">
	      </el-table-column>
	      <el-table-column
	        prop="options"
	        label="规格">
	      </el-table-column>
	      <el-table-column align="center" label="操作" class-name="small-padding fixed-width">
	        <template slot-scope="scope">
	          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
	          <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
	        </template>
	      </el-table-column>
	    </el-table>
	    <div>
	    	<el-pagination
			  background
			  @current-change="handleCurrentChange"
              :current-page="currentPage"
			  layout="total,prev, pager, next"
			  :total="total"
			  :page-size="10">
			</el-pagination>
	    </div>
	    <!--弹出框-->
	    <el-dialog title="编辑商品" :visible.sync="dialogFormVisible">
	      <el-form :model="editGoods" ref="editGoods" label-width="100px">
	        <el-form-item label="商品名称">
	          <el-input v-model="editGoods.title"></el-input>
	        </el-form-item>
	        <el-form-item label="商品描述">
	          <el-input v-model="editGoods.goodsDescrible"></el-input>
	        </el-form-item>
	        <el-form-item label="商品图片">
	          <el-upload
	            :action="uploadPath" 
	            :show-file-list="false"
	            :on-success="uploadPicUrl" 
	            :headers="headers"
	            :before-upload="beforeUpload"
	            :on-error="errorUpload"
	            v-loading="uploading"
    			element-loading-text="上传图片中"
	            class="avatar-uploader"
	            accept=".jpg,.jpeg,.png,.gif">
	            <img v-if="editGoods.cover" :src="editGoods.cover" class="avatar">
	            <i v-else class="el-icon-plus avatar-uploader-icon"/>
	          </el-upload>
	        </el-form-item>
	        <el-form-item label="商品分类" prop='categoryId'>
	          <el-cascader
	            v-model="value"
	            :options="options"
	            :props="{ expandTrigger: 'hover' }"
	            @change="handleChange">
	          </el-cascader>
	        </el-form-item>
	        <el-form-item label="商品售价" prop="sellPrice">
	          <el-input v-model="editGoods.sellPrice"></el-input>
	        </el-form-item>
	        <el-form-item label="商品原价" prop="originPrice">
	          <el-input v-model="editGoods.originPrice"></el-input>
	        </el-form-item>
	        <el-form-item label="特价商品">
	          <el-switch v-model="editGoods.special"></el-switch>
	        </el-form-item>
	        <el-form-item label="返现金额">
	          <el-input v-model="editGoods.returnCash"></el-input>
	        </el-form-item>
	        <el-form-item label="进货价格">
	          <el-input v-model="editGoods.purchasingPrice"></el-input>
	        </el-form-item>
	        <el-form-item label="进货单位">
	          <el-input v-model="editGoods.purchasingUnit"></el-input>
	        </el-form-item>
	        <el-form-item label="库存数量" prop="stock">
	          <el-input v-model="editGoods.stock"></el-input>
	        </el-form-item>
	        <el-form-item label="规格" >
	          <el-input v-model="editGoods.options" placeholder="不同规格用/分隔,eg:蓝色/红色"></el-input>
	        </el-form-item>
	      </el-form>
	      <div slot="footer" class="dialog-footer">
	      	<el-button type="primary" @click="updateData('under')">{{editGoods.status == 1 ? '下架' : '上架'}}</el-button>
	        <el-button type="primary" @click="cancel">取消</el-button>
	        <el-button type="primary" @click="updateData">确定</el-button>
	      </div>
	    </el-dialog>
	</div>
</template>
<script>
	import { listGoods, updateGoods, deleteGoods } from '@/api/goods'
	import { uploadPath, setUploadType } from '@/api/storage'
    import { listCategory } from '@/api/category'
    export default {
      data() {
        return {
	        uploadPath,
	        statuses: [{label:'全部', value: null},{label:'在售', value: 1},{label:'下架', value: 0}],
	        listGoods: [],
	        editGoods: [],
	        listLoading: true,
	        dialogFormVisible: false,
	        currentPage: 1,
	        total: 0,
	        uploading: false,
	        value: [],
	        options: [],
	        categoryId: '',
	        status: 1
    	}
      },
      created() {
	    this.getList()
	    this.getCategory()
	  },
	  computed: {
        headers() {
	      return {
	        'X-Upload-Type': 'goods'
	      }
	    }
      },
      methods:{
      	// 获取商品列表
      	getList() {
      	  let categoryId = this.categoryId
      	  let status = this.status
      	  let query = { page: this.currentPage, categoryId: categoryId, status: status }
      	  console.log(query)
	      listGoods(query).then(response => {
	      	console.log(response)
	      	let data = response.data
	      	if(data.errno == 0){
	      		this.listGoods = data.result.listGoods
	      		this.total = data.result.total
	      		this.listLoading = false
	      	}
	      }).catch((err) => {
	      	console.log(err)
	      	this.listLoading = false
	      })
    	},
    	handleUpdate(row){
    		console.log(row)
    		this.editGoods = row
    		this.dialogFormVisible = true
    	},
    	// 上传图片
    	uploadPicUrl: function(response) {
	        console.log(response)
	        this.editGoods.cover = this.$store.getters.storagePath + '/goods/'+response.result
	        this.uploading = false
	        console.log(this.editGoods.cover)
	    },
	    // 更新数据
    	updateData(ops){
    		// 下架
    		if(ops == 'under'){
    			this.editGoods.status = this.editGoods.status == 1 ? 0 : 1
    		}
    		this.editGoods.categoryId = this.categoryId
    		this.editGoods.special = this.editGoods.special ? 1 : 0
    		updateGoods(this.editGoods).then(response => {
    			let data = response.data
    			if(data.errno == 0){
		            this.dialogFormVisible = false
		            this.currentPage = 1
		            this.getList()
		            this.$message({
		              message: '更新成功！',
		              type: 'success'
		            });
		            this.getList()
		        }
    		}).catch(err => {
    			console.log(err)
    			this.$message.error('更新失败！');
    		})
    	},
    	// 取消修改
    	cancel(){
    		this.dialogFormVisible = false
    	},
    	// 当前改变----当前页码改变之后，触发这个函数
        handleCurrentChange(val){
           console.log("当前改变："+val)
           this.currentPage = val
           this.getList()
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
	        	this.uploading = true
	        }
	        return isJPG && isLt2M;
	    },
	    errorUpload(){
	    	this.uploading = false
	    },
	    handleChange(val){
	        console.log(val)
	        this.categoryId = val[1]
	    },
	    search(){
	    	this.getList()
	    },
	    getCategory: function(){
	        listCategory().then(response => {
	          let data = response.data
	          console.log(data)
	          if(data.errno == 0){
	            let categories = data.result
	            let cascader = []
	            for(let i = 0; i < categories.length; i ++){
	              let subCategory = []
	              let child  = categories[i].children
	              for(let j = 0; j < child.length; j++){
	                let subObj = {label: child[j].name, value: child[j].id}
	                subCategory.push(subObj)
	              }
	              let obj = {label: categories[i].name, value: categories[i].id, children: subCategory}
	              cascader.push(obj)
	            }
	            let obj = {label: '全部', value: '', children: [{label: '全部', value: ''}]}
	            cascader.push(obj)
	            console.log(cascader)
	            this.options = cascader
	          }
	          
	        }).catch((err) => {
	          console.log(err)
	          this.listLoading = false
	        })
	    },
        // 删除商品
        handleDelete(row){
        	this.$confirm('确认删除此商品?', '删除商品', {
	          confirmButtonText: '确定',
	          cancelButtonText: '取消',
	          type: 'warning'
	        }).then(() => {
	          let query = {goodsId: row.goodsId}
	          console.log(query)
	          deleteGoods(query).then(response => {
	            console.log(response)
	            let data = response.data
	            if(data.errno == 0){
	              this.getList()
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
        }
      }
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