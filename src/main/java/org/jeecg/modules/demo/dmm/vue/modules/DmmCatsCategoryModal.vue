<template>
  <a-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
      
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="categoryId">
          <a-input-number v-decorator="[ 'categoryId', validatorRules.categoryId ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="父级分类id">
          <a-input-number v-decorator="[ 'parentId', validatorRules.parentId ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="分类名称">
          <a-input placeholder="请输入分类名称" v-decorator="['catName', validatorRules.catName ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="分类排序号">
          <a-input-number v-decorator="[ 'catSort', validatorRules.catSort ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="删除标识   -1删除   1未删除">
          <a-input placeholder="请输入删除标识   -1删除   1未删除" v-decorator="['dataStatus', validatorRules.dataStatus ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label=" 0隐藏     1显示">
          <a-input placeholder="请输入 0隐藏     1显示" v-decorator="['isShow', validatorRules.isShow ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="分类描述">
          <a-input placeholder="请输入分类描述" v-decorator="['description', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="创建时间">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'craeteTime', validatorRules.craeteTime ]" />
        </a-form-item>
		
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "DmmCatsCategoryModal",
    data () {
      return {
        title:"操作",
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules:{
        categoryId:{rules: [{ required: true, message: '请输入categoryId!' }]},
        parentId:{rules: [{ required: true, message: '请输入父级分类id!' }]},
        catName:{rules: [{ required: true, message: '请输入分类名称!' }]},
        catSort:{rules: [{ required: true, message: '请输入分类排序号!' }]},
        dataStatus:{rules: [{ required: true, message: '请输入删除标识   -1删除   1未删除!' }]},
        isShow:{rules: [{ required: true, message: '请输入 0隐藏     1显示!' }]},
        craeteTime:{rules: [{ required: true, message: '请输入创建时间!' }]},
        },
        url: {
          add: "/dmm/dmmCatsCategory/add",
          edit: "/dmm/dmmCatsCategory/edit",
        },
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'categoryId','parentId','catName','catSort','dataStatus','isShow','description'))
		  //时间格式化
          this.form.setFieldsValue({craeteTime:this.model.craeteTime?moment(this.model.craeteTime):null})
        });

      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            //时间格式化
            formData.craeteTime = formData.craeteTime?formData.craeteTime.format('YYYY-MM-DD HH:mm:ss'):null;
            
            console.log(formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })



          }
        })
      },
      handleCancel () {
        this.close()
      },


    }
  }
</script>

<style scoped>

</style>