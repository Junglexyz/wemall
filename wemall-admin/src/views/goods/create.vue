<template>
  <div class="app-container">
    <h4>创建商品</h4>
    <el-card>
      <el-upload
        :on-success="handleAvatarSuccess"
        :data="uploadData"
        :before-upload="xlsbeforeUpload"
        :headers="headers"
        action="http://junglechen.xyz:8081/admin/goods/excel">
        <el-button type="primary">xls上传商品</el-button>
        <el-tooltip class="item" effect="dark" content="文件格式前缀为类目id eg:123456goods.xls" placement="top-start">
          <i class="el-icon-question"/>
        </el-tooltip>
      </el-upload>
    </el-card>
    <el-card class="box-card">
      <el-form ref="goods" :model="goods" :rules="rules" label-width="100px">
        <el-form-item label="商品名称" prop="title">
          <el-input v-model="goods.title"/>
        </el-form-item>
        <el-form-item label="商品图片" prop="cover">
          <el-upload
            :action="uploadPath"
            :show-file-list="false"
            :on-success="uploadPicUrl"
            :headers="headers"
            :before-upload="beforeUpload"
            element-loading-text="上传图片中"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif">
            <img v-if="goods.cover" :src="goods.cover" class="avatar">
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
        <el-form-item label="商品描述">
          <el-input v-model="goods.goodsDescribe"/>
        </el-form-item>

        <el-card class="box-card">
          <h3>商品规格</h3>
          <el-form
            v-if="specifications.length == 0"
            ref="specForm"
            :rules="rules"
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
            <el-button type="primary" @click="handleSpecificationAdd">确定</el-button>
          </el-form>
          <el-row :gutter="20" type="flex" align="middle" style="padding:20px 0;">
            <!-- <el-col :span="10">
              <el-radio-group v-model="multipleSpec" @change="specChanged">
                <el-radio-button :label="false">默认标准规格</el-radio-button>
                <el-radio-button :label="true">多规格支持</el-radio-button>
              </el-radio-group>
            </el-col> -->
            <el-col :span="10">
              <el-button :plain="true" type="primary" @click="handleSpecificationShow">添加</el-button>
            </el-col>
          </el-row>

          <el-table :data="specifications">
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
                <el-button type="danger" size="mini" @click="handleSpecificationDelete(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>

          <el-dialog :visible.sync="specVisiable" title="设置规格">
            <el-form
              ref="specForm"
              :rules="rules"
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
              <el-button @click="specVisiable = false">取消</el-button>
              <el-button type="primary" @click="handleSpecificationAdd">确定</el-button>
            </div>
          </el-dialog>
        </el-card>

        <el-form-item label="商品售价" prop="sellPrice">
          <el-input v-model="goods.sellPrice"/>
        </el-form-item>
        <el-form-item label="特价商品">
          <el-switch v-model="goods.special"/>
        </el-form-item>
        <el-form-item label="商品原价" prop="originPrice">
          <el-input v-model="goods.originPrice"/>
        </el-form-item>
        <el-form-item label="返现金额">
          <el-input v-model="goods.returnCash"/>
        </el-form-item>
        <el-form-item label="进货价格">
          <el-input v-model="goods.purchasingPrice"/>
        </el-form-item>
        <el-form-item label="进货单位">
          <el-input v-model="goods.purchasingUnit"/>
        </el-form-item>
        <el-form-item label="库存数量" prop="stock">
          <el-input v-model="goods.stock"/>
        </el-form-item>
        <el-form-item label="规格" >
          <el-input v-model="goods.options" placeholder="不同规格用/分隔,eg:蓝色/红色"/>
        </el-form-item>
      </el-form>
    </el-card>
    <div class="op-container">
      <el-button @click="resetForm('goods')">重置</el-button>
      <el-button type="primary" @click="create('goods')">创建</el-button>
    </div>

  </div>
</template>
<script>
import { createGoods } from '@/api/goods'
import { listCategory } from '@/api/category'
import { uploadPath } from '@/api/storage'
import { getToken } from '@/utils/auth'
export default {
  data() {
    var checkSellPrice = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('销售价格不为空'))
      }
      // 校验小数
      const pattern = /^[0-9]+([.]{1}[0-9]+){0,1}$/
      if (pattern.test(value)) {
        callback()
      } else {
        callback(new Error('输入值必须为小数或整数'))
      }
    }
    return {
      uploadPath,
      goods: {
        title: '',
        goodsDescribe: '',
        sellPrice: 0,
        originPrice: 0,
        cover: '',
        special: false
      },
      specVisiable: false,
      specForm: { specification: '', price: '' },
      multipleSpec: false,
      specifications: [],
      loading: true,
      rules: {
        title: [
          { required: true, message: '请输入商品名称', trigger: 'blur' }
        ],
        sellPrice: [
          { required: true, validator: checkSellPrice, trigger: 'blur' }
        ],
        // cover: [
        //   {required: true, message: '请上传照片', trigger: 'blur'}
        // ],
        categoryId: [
          { required: true, message: '请选择商品分类', trigger: 'blur' }
        ],
        stock: [
          { required: true, message: '请输入库存', trigger: 'blur' }
        ]
      },
      value: [],
      options: [],
      uploadData: {}
    }
  },
  computed: {
    headers() {
      return {
        'X-Upload-Type': 'goods',
        'X-Litemall-Admin-Token': getToken()
      }
    }
  },
  created() {
    this.getCategory()
  },
  methods: {
    handleSpecificationShow() {
      this.specForm = { specification: '', price: '' }
      this.specVisiable = true
    },
    handleSpecificationAdd() {
      var index = this.specifications.length - 1
      for (var i = 0; i < this.specifications.length; i++) {
        const v = this.specifications[i]
        if (v.specification === this.specForm.specification) {
          if (v.value === this.specForm.value) {
            this.$message({
              type: 'warning',
              message: '已经存在规格值:' + v.value
            })
            this.specForm = {}
            this.specVisiable = false
            return
          } else {
            index = i
          }
        }
      }

      this.specifications.splice(index + 1, 0, this.specForm)
      this.specVisiable = false
      console.log(this.specifications.length)

      // this.specToProduct()
    },
    handleSpecificationDelete(row) {
      console.log(row)
      const index = this.specifications.indexOf(row)
      this.specifications.splice(index, 1)
    },
    xlsbeforeUpload() {
      this.uploadData = { categoryId: 1000 }
    },
    handleAvatarSuccess(res) {
      console.log(res)
    },
    create(formName) {
      console.log(this.goods)
      console.log(this.specifications)
      if (this.specifications.lenth == 0) {
        this.$message.error('未填规格值！')
        return
      }
      this.goods.special = this.goods.special ? 1 : 0
      this.$refs[formName].validate((valid) => {
        console.log(this.goods)
        if (valid) {
          console.log(valid)
          const goodsAllinone = { goods: this.goods, specifications: this.specifications }
          createGoods(goodsAllinone).then(response => {
            console.log(response)
            const data = response.data
            if (data.errno == 0) {
              this.$message({
                message: '添加成功！',
                type: 'success'
              })
            }
          }).catch(err => {
            console.log(err)
            this.$message.error('添加失败！')
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
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
          console.log(cascader)
          this.options = cascader
        }
      }).catch((err) => {
        console.log(err)
        this.listLoading = false
      })
    },
    uploadPicUrl: function(response) {
      // apcdebugger
      console.log(response)
      this.goods.cover = this.$store.getters.storagePath + '/goods/' + response.result
      this.loading = false
      console.log(this.goods.cover)
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    /* handleAvatarSuccess(res, file) {
        this.goods.pictureUrl = URL.createObjectURL(file.raw);
        console.log(res,file)
      },*/
    beforeUpload(file) {
      const isJPG = file.type === 'image/jpg' || file.type === 'image/png' || file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 或 PNG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      this.loading = true
      return isJPG && isLt2M
    },
    handleChange(val) {
      console.log(val)
      this.goods.categoryId = val[1]
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

