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
          label="会员表主键">
          <a-input-number v-decorator="[ 'userId', validatorRules.userId ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="登陆名">
          <a-input placeholder="请输入登陆名" v-decorator="['loginName', validatorRules.loginName ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="密码">
          <a-input placeholder="请输入密码" v-decorator="['loginPwd', validatorRules.loginPwd ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="0保密  1男 2女">
          <a-input placeholder="请输入0保密  1男 2女" v-decorator="['userSex', validatorRules.userSex ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="昵称">
          <a-input placeholder="请输入昵称" v-decorator="['nickName', validatorRules.nickName ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="真实姓名">
          <a-input placeholder="请输入真实姓名" v-decorator="['trueName', validatorRules.trueName ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="出生日期">
          <a-date-picker v-decorator="[ 'brithday', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="用户头像地址">
          <a-input placeholder="请输入用户头像地址" v-decorator="['userPhoto', validatorRules.userPhoto ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="用户手机">
          <a-input placeholder="请输入用户手机" v-decorator="['userPhone', validatorRules.userPhone ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="用户积分">
          <a-input-number v-decorator="[ 'userScore', validatorRules.userScore ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="用户剩余积分">
          <a-input-number v-decorator="[ 'userSurplusScore', validatorRules.userSurplusScore ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="用户编号">
          <a-input placeholder="请输入用户编号" v-decorator="['userCode', validatorRules.userCode ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="收入水平(1:0-2000    2:2000-5000   3:5000-1w   4:1w+)">
          <a-input placeholder="请输入收入水平(1:0-2000    2:2000-5000   3:5000-1w   4:1w+)" v-decorator="['income', validatorRules.income ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="职业 1.学生 2白领 3个体">
          <a-input placeholder="请输入职业 1.学生 2白领 3个体" v-decorator="['profession', validatorRules.profession ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="推荐人id">
          <a-input-number v-decorator="[ 'refereeUser', validatorRules.refereeUser ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="会员等级id">
          <a-input-number v-decorator="[ 'rankId', validatorRules.rankId ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="用户账号状态 0:停用 1:启用">
          <a-input placeholder="请输入用户账号状态 0:停用 1:启用" v-decorator="['userStatus', validatorRules.userStatus ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="	-1:删除 1:有效">
          <a-input placeholder="请输入	-1:删除 1:有效" v-decorator="['dataFlag', validatorRules.dataFlag ]" />
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
    name: "DmmUserModal",
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
        userId:{rules: [{ required: true, message: '请输入会员表主键!' }]},
        loginName:{rules: [{ required: true, message: '请输入登陆名!' }]},
        loginPwd:{rules: [{ required: true, message: '请输入密码!' }]},
        userSex:{rules: [{ required: true, message: '请输入0保密  1男 2女!' }]},
        nickName:{rules: [{ required: true, message: '请输入昵称!' }]},
        trueName:{rules: [{ required: true, message: '请输入真实姓名!' }]},
        userPhoto:{rules: [{ required: true, message: '请输入用户头像地址!' }]},
        userPhone:{rules: [{ required: true, message: '请输入用户手机!' }]},
        userScore:{rules: [{ required: true, message: '请输入用户积分!' }]},
        userSurplusScore:{rules: [{ required: true, message: '请输入用户剩余积分!' }]},
        userCode:{rules: [{ required: true, message: '请输入用户编号!' }]},
        income:{rules: [{ required: true, message: '请输入收入水平(1:0-2000    2:2000-5000   3:5000-1w   4:1w+)!' }]},
        profession:{rules: [{ required: true, message: '请输入职业 1.学生 2白领 3个体!' }]},
        refereeUser:{rules: [{ required: true, message: '请输入推荐人id!' }]},
        rankId:{rules: [{ required: true, message: '请输入会员等级id!' }]},
        userStatus:{rules: [{ required: true, message: '请输入用户账号状态 0:停用 1:启用!' }]},
        dataFlag:{rules: [{ required: true, message: '请输入	-1:删除 1:有效!' }]},
        },
        url: {
          add: "/dmm/dmmUser/add",
          edit: "/dmm/dmmUser/edit",
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
          this.form.setFieldsValue(pick(this.model,'userId','loginName','loginPwd','userSex','nickName','trueName','userPhoto','userPhone','userScore','userSurplusScore','userCode','income','profession','refereeUser','rankId','userStatus','dataFlag'))
		  //时间格式化
          this.form.setFieldsValue({brithday:this.model.brithday?moment(this.model.brithday):null})
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
            formData.brithday = formData.brithday?formData.brithday.format():null;
            
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