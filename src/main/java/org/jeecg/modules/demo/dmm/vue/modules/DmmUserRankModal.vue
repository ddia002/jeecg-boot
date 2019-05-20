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
          label="会员等级表主键">
          <a-input-number v-decorator="[ 'rankId', validatorRules.rankId ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="会员等级名称">
          <a-input placeholder="请输入会员等级名称" v-decorator="['rankName', validatorRules.rankName ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="开始积分">
          <a-input-number v-decorator="[ 'startScore', validatorRules.startScore ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="结束积分">
          <a-input-number v-decorator="[ 'endScore', validatorRules.endScore ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="折扣">
          <a-input-number v-decorator="[ 'rebate', validatorRules.rebate ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="用户等级图标">
          <a-input placeholder="请输入用户等级图标" v-decorator="['userrankImg', validatorRules.userrankImg ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label=" 删除标志 -1:删除 1:有效">
          <a-input placeholder="请输入 删除标志 -1:删除 1:有效" v-decorator="['dataFlag', validatorRules.dataFlag ]" />
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
    name: "DmmUserRankModal",
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
        rankId:{rules: [{ required: true, message: '请输入会员等级表主键!' }]},
        rankName:{rules: [{ required: true, message: '请输入会员等级名称!' }]},
        startScore:{rules: [{ required: true, message: '请输入开始积分!' }]},
        endScore:{rules: [{ required: true, message: '请输入结束积分!' }]},
        rebate:{rules: [{ required: true, message: '请输入折扣!' }]},
        userrankImg:{rules: [{ required: true, message: '请输入用户等级图标!' }]},
        dataFlag:{rules: [{ required: true, message: '请输入 删除标志 -1:删除 1:有效!' }]},
        },
        url: {
          add: "/dmm/dmmUserRank/add",
          edit: "/dmm/dmmUserRank/edit",
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
          this.form.setFieldsValue(pick(this.model,'rankId','rankName','startScore','endScore','rebate','userrankImg','dataFlag'))
		  //时间格式化
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