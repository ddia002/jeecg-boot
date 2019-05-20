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
          label="商品表主键">
          <a-input-number v-decorator="[ 'goodsId', validatorRules.goodsId ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="商品编号">
          <a-input placeholder="请输入商品编号" v-decorator="['goodsCode', validatorRules.goodsCode ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="商品货号">
          <a-input placeholder="请输入商品货号" v-decorator="['productNo', validatorRules.productNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="商品名称">
          <a-input placeholder="请输入商品名称" v-decorator="['goodsName', validatorRules.goodsName ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="商品图片地址">
          <a-input placeholder="请输入商品图片地址" v-decorator="['goodsImg', validatorRules.goodsImg ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="市场价格">
          <a-input-number v-decorator="[ 'marketPrice', validatorRules.marketPrice ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="门店价">
          <a-input-number v-decorator="[ 'shopPrice', validatorRules.shopPrice ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="预警库存">
          <a-input-number v-decorator="[ 'warnStock', validatorRules.warnStock ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="商品总库存">
          <a-input-number v-decorator="[ 'goodsStock', validatorRules.goodsStock ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="单位表id">
          <a-input-number v-decorator="[ 'goodsUnit', validatorRules.goodsUnit ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="促销信息">
          <a-input placeholder="请输入促销信息" v-decorator="['goodsTips', validatorRules.goodsTips ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="是否上架	0:不上架 1:上架">
          <a-input placeholder="请输入是否上架	0:不上架 1:上架" v-decorator="['isSale', validatorRules.isSale ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="是否热销产品	0:否 1:是">
          <a-input placeholder="请输入是否热销产品	0:否 1:是" v-decorator="['isHot', validatorRules.isHot ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="商品分类ID路径">
          <a-input placeholder="请输入商品分类ID路径" v-decorator="['goodsCatPath', validatorRules.goodsCatPath ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="最后一级商品分类ID	">
          <a-input-number v-decorator="[ 'goodsCatId', validatorRules.goodsCatId ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="品牌Id">
          <a-input-number v-decorator="[ 'brandId', validatorRules.brandId ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="商品描述	">
          <a-input placeholder="请输入商品描述	" v-decorator="['goodsDesc', validatorRules.goodsDesc ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="商品状态	-1:违规 0:未审核 1:已审核">
          <a-input placeholder="请输入商品状态	-1:违规 0:未审核 1:已审核" v-decorator="['goodsStatus', validatorRules.goodsStatus ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="总销售量">
          <a-input-number v-decorator="[ 'saleNum', validatorRules.saleNum ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="上架时间">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'saleTime', validatorRules.saleTime ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="删除标志	-1:删除 1:有效">
          <a-input placeholder="请输入删除标志	-1:删除 1:有效" v-decorator="['dataStatus', validatorRules.dataStatus ]" />
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
    name: "DmmGoodsModal",
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
        goodsId:{rules: [{ required: true, message: '请输入商品表主键!' }]},
        goodsCode:{rules: [{ required: true, message: '请输入商品编号!' }]},
        productNo:{rules: [{ required: true, message: '请输入商品货号!' }]},
        goodsName:{rules: [{ required: true, message: '请输入商品名称!' }]},
        goodsImg:{rules: [{ required: true, message: '请输入商品图片地址!' }]},
        marketPrice:{rules: [{ required: true, message: '请输入市场价格!' }]},
        shopPrice:{rules: [{ required: true, message: '请输入门店价!' }]},
        warnStock:{rules: [{ required: true, message: '请输入预警库存!' }]},
        goodsStock:{rules: [{ required: true, message: '请输入商品总库存!' }]},
        goodsUnit:{rules: [{ required: true, message: '请输入单位表id!' }]},
        goodsTips:{rules: [{ required: true, message: '请输入促销信息!' }]},
        isSale:{rules: [{ required: true, message: '请输入是否上架	0:不上架 1:上架!' }]},
        isHot:{rules: [{ required: true, message: '请输入是否热销产品	0:否 1:是!' }]},
        goodsCatPath:{rules: [{ required: true, message: '请输入商品分类ID路径!' }]},
        goodsCatId:{rules: [{ required: true, message: '请输入最后一级商品分类ID	!' }]},
        brandId:{rules: [{ required: true, message: '请输入品牌Id!' }]},
        goodsDesc:{rules: [{ required: true, message: '请输入商品描述	!' }]},
        goodsStatus:{rules: [{ required: true, message: '请输入商品状态	-1:违规 0:未审核 1:已审核!' }]},
        saleNum:{rules: [{ required: true, message: '请输入总销售量!' }]},
        saleTime:{rules: [{ required: true, message: '请输入上架时间!' }]},
        dataStatus:{rules: [{ required: true, message: '请输入删除标志	-1:删除 1:有效!' }]},
        },
        url: {
          add: "/dmm/dmmGoods/add",
          edit: "/dmm/dmmGoods/edit",
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
          this.form.setFieldsValue(pick(this.model,'goodsId','goodsCode','productNo','goodsName','goodsImg','marketPrice','shopPrice','warnStock','goodsStock','goodsUnit','goodsTips','isSale','isHot','goodsCatPath','goodsCatId','brandId','goodsDesc','goodsStatus','saleNum','dataStatus'))
		  //时间格式化
          this.form.setFieldsValue({saleTime:this.model.saleTime?moment(this.model.saleTime):null})
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
            formData.saleTime = formData.saleTime?formData.saleTime.format('YYYY-MM-DD HH:mm:ss'):null;
            
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