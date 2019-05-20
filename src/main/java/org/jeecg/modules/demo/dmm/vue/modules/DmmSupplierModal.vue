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
          label="供应商主键">
          <a-input-number v-decorator="[ 'supplierId', validatorRules.supplierId ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="供应商编号">
          <a-input placeholder="请输入供应商编号" v-decorator="['supplierCode', validatorRules.supplierCode ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="供应商名称">
          <a-input placeholder="请输入供应商名称" v-decorator="['supplierName', validatorRules.supplierName ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="供应商手机">
          <a-input placeholder="请输入供应商手机" v-decorator="['supplierPhone', validatorRules.supplierPhone ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="供应商地址">
          <a-input placeholder="请输入供应商地址" v-decorator="['address', validatorRules.address ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="供应商状态    0停用  1启用">
          <a-input placeholder="请输入供应商状态    0停用  1启用" v-decorator="['supplierStatus', validatorRules.supplierStatus ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="供应商登陆名">
          <a-input placeholder="请输入供应商登陆名" v-decorator="['loginName', validatorRules.loginName ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="供应商登陆密码">
          <a-input placeholder="请输入供应商登陆密码" v-decorator="['loginPwd', validatorRules.loginPwd ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="供应商类型   1.活体 2.食品用品  3服务商">
          <a-input placeholder="请输入供应商类型   1.活体 2.食品用品  3服务商" v-decorator="['supplierType', validatorRules.supplierType ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="是否合格   0不合格   1合格">
          <a-input placeholder="请输入是否合格   0不合格   1合格" v-decorator="['qualifiedStatus', validatorRules.qualifiedStatus ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="删除标志    -1已删除   1未删除">
          <a-input placeholder="请输入删除标志    -1已删除   1未删除" v-decorator="['dateStatus', validatorRules.dateStatus ]" />
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
    name: "DmmSupplierModal",
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
        supplierId:{rules: [{ required: true, message: '请输入供应商主键!' }]},
        supplierCode:{rules: [{ required: true, message: '请输入供应商编号!' }]},
        supplierName:{rules: [{ required: true, message: '请输入供应商名称!' }]},
        supplierPhone:{rules: [{ required: true, message: '请输入供应商手机!' }]},
        address:{rules: [{ required: true, message: '请输入供应商地址!' }]},
        supplierStatus:{rules: [{ required: true, message: '请输入供应商状态    0停用  1启用!' }]},
        loginName:{rules: [{ required: true, message: '请输入供应商登陆名!' }]},
        loginPwd:{rules: [{ required: true, message: '请输入供应商登陆密码!' }]},
        supplierType:{rules: [{ required: true, message: '请输入供应商类型   1.活体 2.食品用品  3服务商!' }]},
        qualifiedStatus:{rules: [{ required: true, message: '请输入是否合格   0不合格   1合格!' }]},
        dateStatus:{rules: [{ required: true, message: '请输入删除标志    -1已删除   1未删除!' }]},
        craeteTime:{rules: [{ required: true, message: '请输入创建时间!' }]},
        },
        url: {
          add: "/dmm/dmmSupplier/add",
          edit: "/dmm/dmmSupplier/edit",
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
          this.form.setFieldsValue(pick(this.model,'supplierId','supplierCode','supplierName','supplierPhone','address','supplierStatus','loginName','loginPwd','supplierType','qualifiedStatus','dateStatus'))
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