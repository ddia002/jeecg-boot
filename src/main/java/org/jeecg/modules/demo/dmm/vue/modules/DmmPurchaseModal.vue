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
          label="采购表主键">
          <a-input-number v-decorator="[ 'purchaseId', validatorRules.purchaseId ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="采购类型(1.进货  2.公司日常用品)">
          <a-input placeholder="请输入采购类型(1.进货  2.公司日常用品)" v-decorator="['purchaseType', validatorRules.purchaseType ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="进货品id">
          <a-input-number v-decorator="[ 'goodsId', validatorRules.goodsId ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="进货品名称">
          <a-input placeholder="请输入进货品名称" v-decorator="['goodsName', validatorRules.goodsName ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="进货备注">
          <a-input placeholder="请输入进货备注" v-decorator="['purchaseMarks', validatorRules.purchaseMarks ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="采购数量">
          <a-input-number v-decorator="[ 'num', validatorRules.num ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="采购单价">
          <a-input-number v-decorator="[ 'unitPrice', validatorRules.unitPrice ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="采购总价">
          <a-input-number v-decorator="[ 'totalPrice', validatorRules.totalPrice ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="供货商id">
          <a-input-number v-decorator="[ 'supplierId', validatorRules.supplierId ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="公司经手人id">
          <a-input-number v-decorator="[ 'staffId', validatorRules.staffId ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="期望到货时间">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'expectTime', validatorRules.expectTime ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="到货时间">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'arrivalTime', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="创建时间">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'craeteTime', {}]" />
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
    name: "DmmPurchaseModal",
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
        purchaseId:{rules: [{ required: true, message: '请输入采购表主键!' }]},
        purchaseType:{rules: [{ required: true, message: '请输入采购类型(1.进货  2.公司日常用品)!' }]},
        goodsId:{rules: [{ required: true, message: '请输入进货品id!' }]},
        goodsName:{rules: [{ required: true, message: '请输入进货品名称!' }]},
        purchaseMarks:{rules: [{ required: true, message: '请输入进货备注!' }]},
        num:{rules: [{ required: true, message: '请输入采购数量!' }]},
        unitPrice:{rules: [{ required: true, message: '请输入采购单价!' }]},
        totalPrice:{rules: [{ required: true, message: '请输入采购总价!' }]},
        supplierId:{rules: [{ required: true, message: '请输入供货商id!' }]},
        staffId:{rules: [{ required: true, message: '请输入公司经手人id!' }]},
        expectTime:{rules: [{ required: true, message: '请输入期望到货时间!' }]},
        },
        url: {
          add: "/dmm/dmmPurchase/add",
          edit: "/dmm/dmmPurchase/edit",
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
          this.form.setFieldsValue(pick(this.model,'purchaseId','purchaseType','goodsId','goodsName','purchaseMarks','num','unitPrice','totalPrice','supplierId','staffId'))
		  //时间格式化
          this.form.setFieldsValue({expectTime:this.model.expectTime?moment(this.model.expectTime):null})
          this.form.setFieldsValue({arrivalTime:this.model.arrivalTime?moment(this.model.arrivalTime):null})
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
            formData.expectTime = formData.expectTime?formData.expectTime.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.arrivalTime = formData.arrivalTime?formData.arrivalTime.format('YYYY-MM-DD HH:mm:ss'):null;
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