(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-0a35"],{"8uWn":function(t,e,a){"use strict";a.r(e);var o=a("t3Un");var n=a("rs3x"),i={data:function(){return{uploadPath:n.a,ad:{},dialogFormVisible:!1,ads:[],listLoading:!0,dialogStatus:"",textMap:{update:"编辑广告",create:"创建广告"}}},computed:{headers:function(){return{"X-Upload-Type":"common"}}},created:function(){this.getListAd()},methods:{handleCreate:function(){this.dialogFormVisible=!0,this.dialogStatus="create"},cancel:function(){this.dialogFormVisible=!1},getListAd:function(){var t=this;Object(o.a)({url:"/ad/list",method:"post"}).then(function(e){console.log(e);var a=e.data;0==a.errno&&(t.ads=a.list,t.listLoading=!1)}).catch(function(e){console.log(e),t.listLoading=!1})},createData:function(){var t=this;(function(t){return Object(o.a)({url:"/ad/create",method:"post",data:t})})(this.ad).then(function(e){0==e.data.errno&&(t.dialogFormVisible=!1,t.ad={},t.$message({message:"添加成功！",type:"success"}),t.getListAd())}).catch(function(t){console.log(t)})},handleUpdate:function(t){this.dialogFormVisible=!0,this.dialogStatus="update",console.log(t),this.ad=t},updateData:function(){var t=this;console.log(this.ad),function(t){return Object(o.a)({url:"/ad/update",method:"post",data:t})}(this.ad).then(function(e){0==e.data.errno&&(t.dialogFormVisible=!1,t.getListAd(),t.ad={},t.$message({message:"修改成功！",type:"success"}),t.dialogFormVisible=!1)}).catch(function(e){console.log(e),t.dialogFormVisible=!1})},handleDelete:function(t){var e=this;this.$confirm("确认删除此商品?","删除商品",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){var a={id:t.id};console.log(a),function(t){return Object(o.a)({url:"/ad/delete",method:"post",data:t})}(a).then(function(t){console.log(t),0==t.data.errno&&(e.getListAd(),e.$message({type:"success",message:"删除成功!"}))}).catch(function(t){console.log(t),e.$message({type:"danger",message:"删除失败!"})})}).catch(function(){e.$message({type:"info",message:"已取消删除"})})},uploadPicUrl:function(t){console.log(t),this.ad.imgUrl=this.$store.getters.storagePath+"common/"+t.result,this.loading=!1,console.log(this.ad)},beforeUpload:function(t){var e="image/jpg"===t.type||"image/png"===t.type||"image/jpeg"===t.type,a=t.size/1024/1024<2;return e||this.$message.error("上传头像图片只能是 JPG 或 PNG 格式!"),a||this.$message.error("上传头像图片大小不能超过 2MB!"),this.loading=!0,e&&a},formatTime:function(t){console.log(t);var e=t.getFullYear(),a=t.getMonth()+1,o=t.getDate(),n=t.getHours(),i=t.getMinutes(),l=t.getSeconds();return[e,a,o].map(this.formatNumber).join("-")+" "+[n,i,l].map(this.formatNumber).join(":")},formatNumber:function(t){return(t=t.toString())[1]?t:"0"+t}}},l=(a("VX4y"),a("ZrdR")),s=Object(l.a)(i,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"container"},[a("h4",[t._v("广告管理")]),t._v(" "),a("el-card",[a("div",{staticClass:"filter-container"},[a("el-button",{attrs:{type:"primary",icon:"el-icon-edit"},on:{click:t.handleCreate}},[t._v("添加")])],1)]),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],staticStyle:{width:"100%"},attrs:{data:t.ads}},[a("el-table-column",{attrs:{prop:"name",label:"名称"}}),t._v(" "),a("el-table-column",{attrs:{prop:"content",label:"内容"}}),t._v(" "),a("el-table-column",{attrs:{prop:"imgUrl",label:"bannaer图片"},scopedSlots:t._u([{key:"default",fn:function(e){return[e.row.imgUrl?a("img",{attrs:{src:e.row.imgUrl,width:"40"}}):t._e()]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作","class-name":"small-padding fixed-width"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(a){t.handleUpdate(e.row)}}},[t._v("编辑")]),t._v(" "),a("el-button",{attrs:{type:"danger",size:"mini"},on:{click:function(a){t.handleDelete(e.row)}}},[t._v("删除")])]}}])})],1),t._v(" "),a("el-dialog",{attrs:{title:t.textMap[t.dialogStatus],visible:t.dialogFormVisible},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-form",{ref:"ad",attrs:{model:t.ad,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"广告名称",prop:"name"}},[a("el-input",{model:{value:t.ad.name,callback:function(e){t.$set(t.ad,"name",e)},expression:"ad.name"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"广告内容"}},[a("el-input",{model:{value:t.ad.content,callback:function(e){t.$set(t.ad,"content",e)},expression:"ad.content"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"bannaer图片"}},[a("el-upload",{staticClass:"avatar-uploader",attrs:{action:t.uploadPath,"show-file-list":!1,"on-success":t.uploadPicUrl,"before-upload":t.beforeUpload,headers:t.headers,"element-loading-text":"上传图片中",accept:".jpg,.jpeg,.png,.gif"}},[t.ad.imgUrl?a("img",{staticClass:"avatar",attrs:{src:t.ad.imgUrl}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:t.cancel}},[t._v("取消")]),t._v(" "),"create"==t.dialogStatus?a("el-button",{attrs:{type:"primary"},on:{click:t.createData}},[t._v("确定")]):a("el-button",{attrs:{type:"primary"},on:{click:t.updateData}},[t._v("确定")])],1)],1)],1)},[],!1,null,null,null);s.options.__file="ad.vue";e.default=s.exports},VX4y:function(t,e,a){"use strict";var o=a("bHai");a.n(o).a},bHai:function(t,e,a){},rs3x:function(t,e,a){"use strict";a.d(e,"a",function(){return o});a("t3Un");var o="http://localhost:8081/admin/storage/upload"}}]);