<template>
  <div class="app-container">
    <h4>险情发布</h4>
    <el-card class="box-card">
      <el-form :model="accident" ref="accident" label-width="100px">
        <el-form-item label="险情类型" prop="typesName">
          <el-select v-model="accident.typesName" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.label">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="事发地点">
          <el-col :span="8">
            <el-input v-model="accident.place"></el-input>
            <div @click="handleMap">获取详细地址</div>
            <div v-if="position.longitude">经纬度: {{position.longitude}},{{position.latitude}}</div>
          </el-col>
        </el-form-item>
        <el-form-item label="事发时间" prop="accidentTime">
          <el-date-picker
            v-model="accident.accidentTime"
            type="datetime"
            placeholder="事发时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="事故概述" prop="describe">
          <el-input v-model="accident.summary"></el-input>
        </el-form-item>
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
	  </el-card>
  	<div class="op-container">
  		<el-button @click="resetForm('accident')">重置</el-button>
  		<el-button type="primary" @click="create('accident')">发布</el-button>
  	</div>
    <div class="map-box" v-if="map">
      <div class="map">
        <div class="position">
          <div v-if="position.longitude">经纬度: {{position.longitude}},{{position.latitude}}</div>
          <div @click="getLngLat">确定</div>
        </div>
        <el-amap class="amap-box"
                 :amap-manager="amapManager"
                 :vid="'amap-vue'"
                 :zoom="zoom"
                 :plugin="plugin"
                 :center="center"
                 :events="events"
                 :mapStyle="mapStyle"
        >
          <!-- 标记 -->
            <el-amap-marker v-for="(marker, index) in markers" icon="https://test-jungle.oss-cn-beijing.aliyuncs.com/circle.png" :position="marker.position" :key="index"></el-amap-marker>
        </el-amap>
      </div>
    </div>
  </div>
</template>
<script>
  import { createAccident } from '@/api/accident'
  import {AMapManager, lazyAMapApiLoaderInstance} from 'vue-amap'

  let amapManager = new AMapManager()
  export default {
    data() {
      let self = this
      return {
        map: false,
        options: [{
          value: '1',
          label: '触礁'
        }, {
          value: '2',
          label: '火灾'
        }, {
          value: '3',
          label: '沉船'
        }, {
          value: '4',
          label: '落水'
        }, {
          value: '5',
          label: '疾病'
        },{
          value: '6',
          label: '其他'
        }],
        status: [{
          value: '1',
          label: '搜救中'
        }, {
          value: '2',
          label: '已完结'
        }],
        accident: {
          longitude: '',
          latitude: ''
        },
        position: {},
        address: null,
                searchKey: '',
                amapManager,
                markers: [],
                mapStyle: "amap://styles/grey", //设置地图的显示样式
                searchOption: {
                    city: '全国',
                    citylimit: true
                },
                center: [111.329402, 31.228667],
                zoom: 5,
                lng: 0,
                lat: 0,
                loaded: false,
                events: {
                    init() {
                        lazyAMapApiLoaderInstance.load().then(() => {
                            // self.initSearch()
                        });
                    },
                    // 点击获取地址的数据
                    click(e) {
                        console.log(e)
                        let position = {longitude: e.lnglat.lng, latitude: e.lnglat.lat}
                        console.log(position)
                        self.accident.longitude = e.lnglat.O
                        self.accident.latitude = e.lnglat.P
                        self.position = position
                        self.markers = [{position: [e.lnglat.O, e.lnglat.P]}]
                    }
                },
                // 一些工具插件
                plugin: [
                    {
                        // 定位
                        pName: 'Geolocation',
                        events: {
                            init(o) {
                                // o是高德地图定位插件实例
                                o.getCurrentPosition((status, result) => {
                                    if (result && result.position) {
                                        // 设置经度
                                        self.lng = result.position.lng
                                        // 设置维度
                                        self.lat = result.position.lat
                                        // 设置坐标
                                        self.center = [self.lng, self.lat]
                                        self.markers.push([self.lng, self.lat])
                                        // load
                                        self.loaded = true
                                        // 页面渲染好后
                                        self.$nextTick()
                                    }
                                })
                            }
                        }
                    }
                ]
      }
    },
    created() {

    },
    computed: {
      headers() {
        return {
          'X-Upload-Type': 'goods'
        }
      }
    },
    methods: {
      getLngLat(){
        console.log('long')
        this.map = false
      },
      handleMap(){
        this.map = true
      },
      create(formName){
        console.log(this.accident)
        this.accident.callVolenteer = this.accident.callVolenteer ? 1 : 0
        createAccident(this.accident).then(response => {
          let data = response.data
          console.log(data)
          if(data.errno == 0){
            this.dialogFormVisible = false
            // this.getListCategory()
            this.$refs['accident'].resetFields();
            this.$message({
              message: '添加成功！',
              type: 'success'
            })
          }
        }).catch((err) => {
          this.$message.error('添加失败！');
        })
      },
      getCategory: function(){
        
      },
      uploadPicUrl: function(response) {
        
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      /*handleAvatarSuccess(res, file) {
        this.goods.pictureUrl = URL.createObjectURL(file.raw);
        console.log(res,file)
      },*/
      beforeUpload(file) {
        
      },
      handleChange(val){

      }
    }
  }
</script>
<style>
  .map-box{
    display: flex;
    flex-flow: row nowrap;
    justify-content: center;
    align-items: center;
    height: 100%;
    width: 100%;
    background: rgba(0, 0, 0, 0.5);
    position: absolute;
    top: 0;
    left: 0;
  }
  .position{
    position: relative;
    z-index: 5;
    /*top: 66px;*/
    display: flex;
    flex-flow: row nowrap;
    justify-content: center;
    color: #787878;
    font-size: 20px
  }
  .map{
    height: 400px;
    width: 50%;
    /*position: absolute;
    top: 50px;
    left: 25%;*/
    background: #ccc
  }
  .app-container{
    position: relative;
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




