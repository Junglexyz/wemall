<!-- <template>
  <div class="app-container">
    <h4>关于我们</h4>
    <el-card class="box-card">
      <el-form :model="info" ref="info" label-width="100px">
        <el-form-item label="组织名称" prop="name">
          <el-input v-model="info.name"></el-input>
        </el-form-item>
        <el-form-item label="组织logo" prop="logoUrl">
          <el-upload
            :action="uploadPath" 
            :show-file-list="false"
            :on-success="uploadPicUrl"
            :headers="headers"
            :before-upload="beforeUpload"
            element-loading-text="上传图片中"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif">
            <img v-if="info.logoUrl" :src="info.logoUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"/>
          </el-upload>
        </el-form-item>
        <el-form-item label="官方简介" prop="summary">
          <el-input type="textarea" v-model="info.summary"></el-input>
        </el-form-item>
      </el-form>
      <el-button @click="resetForm('info')">重置</el-button>
	  <el-button type="primary" @click="create()">发布</el-button>
	</el-card>
  </div>
</template>
<script>
  import { createInfo, updateInfo, getInfo } from '@/api/dashboard'
  import { uploadPath } from '@/api/storage'
  export default {
    data() {
      return {
        uploadPath,
        info: {
        },
        isCreate: false
      }
    },
    created() {
      this.getInfos()
    },
    computed: {
      headers() {
        return {
          'X-Upload-Type': 'goods'
        }
      }
    },
    methods: {
      create(){
        console.log(this.info, this.isCreate)
        if(this.isCreate){
          console.log(this.info, this.isCreate)
          updateInfo(this.info).then(response => {
            console.log(response)
            let data = response.data
            if(data.errno == 0){
              console.log('更新成功')
            }
          }).catch(err => {

          })
        } else {
          console.log(this.info, this.isCreate)
          let info = this.info
          createInfo(info).then(response => {
            console.log(response)
            let data = response.data
            if(data.errno == 0){
              console.log('新增成功')
            }
          }).catch(err => {

          })
        }
      },
      getInfos: function(){
        getInfo().then(response => {
          let data = response.data
          if(data.errno == 0){
            this.info = data.data
            this.isCreate = true
          }
        }).catch(err => {

        })
      },
      uploadPicUrl: function(response) {
        console.log(response)
        this.info.logoUrl = 'http://test-jungle.oss-cn-beijing.aliyuncs.com/' + '/goods/'+response.data
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      beforeUpload(file) {
        
      },
      handleChange(val){

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

 -->