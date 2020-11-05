<template>
  <div class="app-container">
    <el-form ref="shopInfo" :model="shopInfo"label-width="100px">
      <el-form-item label="店铺名称" prop="shopname">
        <el-input v-model="shopInfo.shopname"/>
      </el-form-item>
      <el-form-item label="商品Logo" prop="logoImg">
        <el-upload
          :action="uploadPath"
          :show-file-list="false"
          :on-success="uploadPicUrl"
          :headers="headers"
          :before-upload="beforeUpload"
          element-loading-text="上传图片中"
          class="avatar-uploader"
          accept=".jpg,.jpeg,.png,.gif">
          <img v-if="shopInfo.logoImg" :src="shopInfo.logoImg" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"/>
        </el-upload>
      </el-form-item>
      <el-form-item label="店铺简介">
        <el-input v-model="shopInfo.brief"/>
      </el-form-item>
      <el-form-item label="店铺地址" prop="address">
        <el-input v-model="shopInfo.address"/>
      </el-form-item>
      <el-form-item label="联系电话">
        <el-input v-model="shopInfo.contactMobile"/>
      </el-form-item>
      <el-form-item label="店铺照片" prop="instoreImg">
        <el-upload
          :action="uploadPath"
          :show-file-list="false"
          :on-success="uploadStoreImg"
          :headers="headers"
          :before-upload="beforeUpload"
          element-loading-text="上传图片中"
          class="avatar-uploader"
          accept=".jpg,.jpeg,.png,.gif">
          <img v-if="shopInfo.instoreImg" :src="shopInfo.instoreImg" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"/>
        </el-upload>
      </el-form-item>
      <el-form-item label="店内照片" prop="instoreImg">
        <el-upload
          :action="uploadPath"
          :show-file-list="false"
          :on-success="uploadInstoreImg"
          :headers="headers"
          :before-upload="beforeUpload"
          element-loading-text="上传图片中"
          class="avatar-uploader"
          accept=".jpg,.jpeg,.png,.gif">
          <img v-if="shopInfo.storeImg" :src="shopInfo.storeImg" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"/>
        </el-upload>
      </el-form-item>
    </el-form>
    <div class="op-container">
      <el-button @click="resetForm('shopInfo')">重置</el-button>
      <el-button type="primary" @click="create()">更新</el-button>
    </div>
  </div>
</template>
<script type="text/javascript">
import { shopInfo, updateShopInfo } from '@/api/dashboard'
import { uploadPath } from '@/api/storage'
export default {
  data() {
    return {
      uploadPath,
      shopInfo: {}
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
    this.getShopInfo()
  },
  methods: {
    getShopInfo() {
      shopInfo().then(response => {
        const data = response.data
        if (data.errno == 0) {
          this.shopInfo = data.result
        }
      }).catch(err => {
        console.log(err)
      })
    },
    create() {
      console.log(this.shopInfo)
      updateShopInfo(this.shopInfo).then(response => {
        const data = response.data
        if (data.errno == 0) {
          this.getShopInfo()
          alert('更新成功')
        }
      }).catch(err => {
        console.log(err)
      })
    },
    uploadPicUrl: function(response) {
		        // apcdebugger
		        console.log(response)
		        console.log(this.$store.getters.storagePath)
		        this.shopInfo.logoImg = this.$store.getters.storagePath + '/goods/' + response.result
		        this.loading = false
		    },
		    uploadInstoreImg(response) {
		    	console.log(response)
		        this.shopInfo.storeImg = this.$store.getters.storagePath + '/goods/' + response.result
		        this.loading = false
		    },
		    uploadStoreImg(response) {
		    	console.log(response)
		        this.shopInfo.instoreImg = this.$store.getters.storagePath + '/goods/' + response.result
		        this.loading = false
		    },
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
