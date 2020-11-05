<template>
  <div class="app-container">
    <h4>商品列表</h4>
    <el-card>
      <el-form>
        <el-cascader
          v-model="value"
          :options="options"
          :props="{ expandTrigger: 'hover' }"
          @change="handleChange"/>
        <el-select v-model="status" placeholder="全部">
          <el-option
            v-for="item in statuses"
            :key="item.value"
            :label="item.label"
            :value="item.value"/>
        </el-select>
        <el-input v-model="title" placeholder="请输入商品名称" style="width: 200px"/>
        <el-button type="primary" @click="search">立即查找</el-button>
      </el-form>
    </el-card>
    <el-table
      v-loading="listLoading"
      :data="listGoods">
      <el-table-column
        prop="title"
        label="名称"/>
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
          <img v-if="scope.row.cover" :preview-src-list="scope.row.cover" :src="scope.row.cover" width="40">
        </template>
      </el-table-column>
      <el-table-column
        prop="sellPrice"
        label="售价"/>
      <el-table-column
        prop="purchasingUnit"
        label="销售单位"/>
      <el-table-column
        prop="special"
        label="是否特价">
        <template slot-scope="scope">
          {{ scope.row.special ? '是' : '否' }}
        </template>
      </el-table-column>
      <el-table-column
        prop="originPrice"
        label="原价"/>
      <el-table-column
        prop="returnCash"
        label="返现金额"/>
      <el-table-column
        prop="purchasingPrice"
        label="进货价格"/>
      <el-table-column
        prop="purchasingUnit"
        label="进货单位"/>
      <el-table-column
        prop="stock"
        label="库存数量"/>
      <el-table-column
        prop="options"
        label="规格"/>
      <el-table-column align="center" label="操作" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-pagination
        :current-page="currentPage"
        :total="total"
        :page-size="10"
        background
        layout="total,prev, pager, next"
        @current-change="handleCurrentChange"/>
    </div>
    <!--弹出框-->
    <el-dialog :visible.sync="dialogFormVisible" title="编辑商品">
      <el-form ref="editGoods" :model="editGoods" label-width="100px">
        <el-form-item label="商品名称">
          <el-input v-model="editGoods.title"/>
        </el-form-item>
        <el-form-item label="商品描述">
          <el-input v-model="editGoods.goodsDescrible"/>
        </el-form-item>
        <el-form-item label="商品图片">
          <el-upload
            v-loading="uploading"
            :action="uploadPath"
            :show-file-list="false"
            :on-success="uploadPicUrl"
            :headers="headers"
            :before-upload="beforeUpload"
            :on-error="errorUpload"
            element-loading-text="上传图片中"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif">
            <img v-if="editGoods.cover" :src="editGoods.cover" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"/>
          </el-upload>
        </el-form-item>
        <el-form-item label="商品分类" prop="categoryId">
          <el-cascader
            v-model="value"
            :options="options"
            :props="{ expandTrigger: 'hover' }"
            @change="handleChange"/>
        </el-form-item>

        <el-card>
          <h3>规格值</h3>
          <el-col :span="10">
            <el-button :plain="true" type="primary" @click="handleSpecificationShow">添加</el-button>
          </el-col>
          <el-table :data="editGoods.specifications">
            <el-table-column property="specification" label="规格名"/>
            <el-table-column property="price" label="价格">
              <template slot-scope="scope">
                <el-tag type="primary">
                  {{ scope.row.price }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              label="操作"
              width="250"
              class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button type="primaryr" size="mini" @click="handleSpecificationUpdateShow(scope.row)">编辑</el-button>
                <el-button type="danger" size="mini" @click="handleSpecificationDelete(editGoods,scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-dialog :title="textMap[dialogStatus]" :visible.sync="specVisiable">
            <el-form
              ref="specForm"
              :model="specForm"
              status-icon
              label-position="left"
              label-width="100px"
              style="width: 400px; margin-left:50px;">
              <el-form-item label="规格名" prop="specification">
                <el-input v-model="specForm.specification"/>
              </el-form-item>
              <el-form-item label="价格" prop="price">
                <el-input v-model="specForm.price"/>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <!-- <el-button @click="specVisiable = false">取消</el-button>
		              <el-button type="primary" @click="handleSpecificationAdd(editGoods)">确定</el-button> -->
              <el-button v-if="dialogStatus=='create'" type="primary" @click="handleSpecificationAdd(editGoods)">确定</el-button>
              <el-button v-else type="primary" @click="handleSpecificationUpdate">确定</el-button>
            </div>
          </el-dialog>
        </el-card>

        <el-form-item label="商品售价" prop="sellPrice">
          <el-input v-model="editGoods.sellPrice"/>
        </el-form-item>
        <el-form-item label="销售单位">
          <el-input v-model="editGoods.purchasingUnit"/>
        </el-form-item>
        <el-form-item label="商品原价" prop="originPrice">
          <el-input v-model="editGoods.originPrice"/>
        </el-form-item>
        <el-form-item label="特价商品">
          <el-switch v-model="editGoods.special"/>
        </el-form-item>
        <el-form-item label="返现金额">
          <el-input v-model="editGoods.returnCash"/>
        </el-form-item>
        <el-form-item label="进货价格">
          <el-input v-model="editGoods.purchasingPrice"/>
        </el-form-item>
        <el-form-item label="库存数量" prop="stock">
          <el-input v-model="editGoods.stock"/>
        </el-form-item>
        <el-form-item label="规格" >
          <el-input v-model="editGoods.options" placeholder="不同规格用/分隔,eg:蓝色/红色"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="updateData('under')">{{ editGoods.status == 1 ? '下架' : '上架' }}</el-button>
        <el-button type="primary" @click="cancel">取消</el-button>
        <el-button type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { listGoods, updateGoods, deleteGoods } from '@/api/goods'
import { createSpec, updateSpec, deleteSpec } from '@/api/specification'
import { uploadPath, setUploadType } from '@/api/storage'
import { listCategory } from '@/api/category'
export default {
  data() {
    return {
	        uploadPath,
	        statuses: [{ label: '全部', value: null }, { label: '在售', value: 1 }, { label: '下架', value: 0 }],
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
	        status: 1,
	        title: '',
	        specVisiable: false,
	        specForm: {},
	        dialogStatus: '',
	        textMap: {
	          update: '编辑规格',
	          create: '创建规格'
	        }
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
	    this.getList()
	    this.getCategory()
	  },
  methods: {
      	// 获取商品列表
      	getList() {
      	  const categoryId = this.categoryId
      	  const status = this.status
      	  const title = this.title
      	  console.log(title)
      	  const query = { page: this.currentPage, categoryId: categoryId, status: status, title: title }
      	  console.log(query)
	      listGoods(query).then(response => {
	      	console.log(response)
	      	const data = response.data
	      	if (data.errno == 0) {
	      		this.listGoods = data.result.listGoods
	      		this.total = data.result.total
	      		this.listLoading = false
	      	}
	      }).catch((err) => {
	      	console.log(err)
	      	this.listLoading = false
	      })
    	},
    	handleUpdate(row) {
    		console.log(row)
    		const index = this.listGoods.indexOf(row)
    		console.log('indexGoods' + index)
    		this.editGoods = row
    		this.dialogFormVisible = true
    	},
    	// 上传图片
    	uploadPicUrl: function(response) {
	        console.log(response)
	        this.editGoods.cover = this.$store.getters.storagePath + '/goods/' + response.result
	        this.uploading = false
	        console.log(this.editGoods.cover)
	    },
	    // 更新数据
    	updateData(ops) {
    		// 下架
    		if (ops == 'under') {
    			this.editGoods.status = this.editGoods.status == 1 ? 0 : 1
    		}
    		this.editGoods.categoryId = this.categoryId
    		this.editGoods.special = this.editGoods.special ? 1 : 0
    		updateGoods(this.editGoods).then(response => {
    			const data = response.data
    			if (data.errno == 0) {
		            this.dialogFormVisible = false
		            this.currentPage = 1
		            // this.getList()
		            this.$message({
		              message: '更新成功！',
		              type: 'success'
		            })
		        }
    		}).catch(err => {
    			console.log(err)
    			this.$message.error('更新失败！')
    		})
    	},
    	// 取消修改
    	cancel() {
    		this.dialogFormVisible = false
    	},
    	// 当前改变----当前页码改变之后，触发这个函数
    handleCurrentChange(val) {
      console.log('当前改变：' + val)
      this.currentPage = val
      this.getList()
    },
    beforeUpload(file) {
        	console.log(file.type)
	        const isJPG = file.type === 'image/jpg' || file.type === 'image/png' || file.type === 'image/jpeg'
	        const isLt2M = file.size / 1024 / 1024 < 2

	        if (!isJPG) {
	          this.$message.error('上传头像图片只能是 JPG、JPEG 或 PNG 格式!')
	        }
	        if (!isLt2M) {
	          this.$message.error('上传头像图片大小不能超过 2MB!')
	        }
	        if (isJPG && isLt2M) {
	        	this.uploading = true
	        }
	        return isJPG && isLt2M
	    },
	    errorUpload() {
	    	this.uploading = false
	    },
	    handleChange(val) {
	        console.log(val)
	        this.categoryId = val[1]
	    },
	    search() {
	    	this.getList()
	    },
	    getCategory: function() {
	        listCategory().then(response => {
	          const data = response.data
	          console.log(data)
	          if (data.errno == 0) {
	            const categories = data.result
	            const cascader = []
	            for (let i = 0; i < categories.length; i++) {
	              const subCategory = []
	              const child = categories[i].children
	              for (let j = 0; j < child.length; j++) {
	                const subObj = { label: child[j].name, value: child[j].id }
	                subCategory.push(subObj)
	              }
	              const obj = { label: categories[i].name, value: categories[i].id, children: subCategory }
	              cascader.push(obj)
	            }
	            const obj = { label: '全部', value: '', children: [{ label: '全部', value: '' }] }
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
    handleDelete(row) {
        	this.$confirm('确认删除此商品?', '删除商品', {
	          confirmButtonText: '确定',
	          cancelButtonText: '取消',
	          type: 'warning'
	        }).then(() => {
	          const query = { goodsId: row.goodsId }
	          console.log(query)
	          deleteGoods(query).then(response => {
	            console.log(response)
	            const data = response.data
	            if (data.errno == 0) {
	              this.getList()
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
    handleSpecificationShow() {
	        this.specForm = { specification: '', price: '' }
	        this.dialogStatus = 'create'
	        this.specVisiable = true
	    },
	    handleSpecificationAdd(goods) {
	    	this.specForm.goodsId = goods.goodsId
	    	const index = this.listGoods.indexOf(goods)
	    	console.log(this.specForm)
	    	createSpec(this.specForm).then(res => {
	    		console.log(res)
	    		this.specVisiable = false
	    		this.listGoods[index].specifications.push(this.specForm)
	    		if (res.data.errno == 0) {
	    			this.$message({
		              message: '添加成功！',
		              type: 'success'
		            })
	    		}
	    	}).catch(err => {
	    		console.log(err)
	    		this.$message.error('添加失败！')
	    	})
	    },
    handleSpecificationDelete(goods, row) {
	        const index = this.listGoods.indexOf(goods)
	        const specIndex = this.listGoods[index].specifications
	        this.listGoods[index].specifications.splice(specIndex, 1)
	        // 从数据库中删除
	        deleteSpec({ specId: row.id }).then(res => {
	    		console.log(res)
	    		if (res.data.errno == 0) {
	    			this.$message({
		              message: '删除成功！',
		              type: 'success'
		            })
	    		}
	    	}).catch(err => {
	    		console.log(err)
	    		this.$message.error('删除失败！')
	    	})
	    },
	    handleSpecificationUpdateShow(row) {
	    	this.specVisiable = true
	    	this.specForm = row
	    	this.dialogStatus = 'update'
	    },
	    handleSpecificationUpdate() {
	    	// 新增规格不能更新或删除 因为键没有更新
	    	updateSpec(this.specForm).then(res => {
	    		console.log(res)
	    		this.specVisiable = false
	    		if (res.data.errno == 0) {
	    			this.$message({
		              message: '更新成功！',
		              type: 'success'
		            })
	    		}
	    	}).catch(err => {
	    		console.log(err)
	    		this.$message.error('更新失败！')
	    	})
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
