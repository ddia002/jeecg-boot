<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :span="6">
            <a-form-item label="商品表主键">
              <a-input placeholder="请输入商品表主键" v-model="queryParam.goodsId"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="6">
            <a-form-item label="商品编号">
              <a-input placeholder="请输入商品编号" v-model="queryParam.goodsCode"></a-input>
            </a-form-item>
          </a-col>

          <a-col :span="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>

      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <dmmGoods-modal ref="dmmGoodsModal" @ok="modalFormOk"></dmmGoods-modal>
  </a-card>
</template>

<script>
  import DmmGoodsModal from './modules/DmmGoodsModal'
  import { filterObj } from '@/utils/util'
  import { deleteAction,getAction } from '@/api/manage'

  export default {
    name: "DmmGoodsList",
    components: {
      DmmGoodsModal
    },
    data () {
      return {
        description: '员工管理页面',
        // 查询条件
        queryParam: {},
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
		  {
            title: '商品表主键',
            align:"center",
            dataIndex: 'goodsId'
          },
		  {
            title: '商品编号',
            align:"center",
            dataIndex: 'goodsCode'
          },
		  {
            title: '商品货号',
            align:"center",
            dataIndex: 'productNo'
          },
		  {
            title: '商品名称',
            align:"center",
            dataIndex: 'goodsName'
          },
		  {
            title: '商品图片地址',
            align:"center",
            dataIndex: 'goodsImg'
          },
		  {
            title: '市场价格',
            align:"center",
            dataIndex: 'marketPrice'
          },
		  {
            title: '门店价',
            align:"center",
            dataIndex: 'shopPrice'
          },
		  {
            title: '预警库存',
            align:"center",
            dataIndex: 'warnStock'
          },
		  {
            title: '商品总库存',
            align:"center",
            dataIndex: 'goodsStock'
          },
		  {
            title: '单位表id',
            align:"center",
            dataIndex: 'goodsUnit'
          },
		  {
            title: '促销信息',
            align:"center",
            dataIndex: 'goodsTips'
          },
		  {
            title: '是否上架	0:不上架 1:上架',
            align:"center",
            dataIndex: 'isSale'
          },
		  {
            title: '是否热销产品	0:否 1:是',
            align:"center",
            dataIndex: 'isHot'
          },
		  {
            title: '商品分类ID路径',
            align:"center",
            dataIndex: 'goodsCatPath'
          },
		  {
            title: '最后一级商品分类ID	',
            align:"center",
            dataIndex: 'goodsCatId'
          },
		  {
            title: '品牌Id',
            align:"center",
            dataIndex: 'brandId'
          },
		  {
            title: '商品描述	',
            align:"center",
            dataIndex: 'goodsDesc'
          },
		  {
            title: '商品状态	-1:违规 0:未审核 1:已审核',
            align:"center",
            dataIndex: 'goodsStatus'
          },
		  {
            title: '总销售量',
            align:"center",
            dataIndex: 'saleNum'
          },
		  {
            title: '上架时间',
            align:"center",
            dataIndex: 'saleTime'
          },
		  {
            title: '删除标志	-1:删除 1:有效',
            align:"center",
            dataIndex: 'dataStatus'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
        //数据集
        dataSource:[],
        // 分页参数
        ipagination:{
          current: 1,
          pageSize: 10,
          pageSizeOptions: ['10', '20', '30'],
          showTotal: (total, range) => {
            return range[0] + "-" + range[1] + " 共" + total + "条"
          },
          showQuickJumper: true,
          showSizeChanger: true,
          total: 0
        },
        isorter:{
          column: 'createTime',
          order: 'desc',
        },
        loading:false,
        selectedRowKeys: [],
        selectedRows: [],
		url: {
          list: "/dmm/dmmGoods/list",
          delete: "/dmm/dmmGoods/delete",
          deleteBatch: "/dmm/dmmGoods/deleteBatch",
        },
        
      }
    },
    created() {
      this.loadData();
    },
    methods: {
      loadData (arg){
        //加载数据 若传入参数1则加载第一页的内容
        if(arg===1){
          this.ipagination.current = 1;
        }
        var params = this.getQueryParams();//查询条件
        getAction(this.url.list,params).then((res)=>{
          if(res.success){
            this.dataSource = res.result.records;
            this.ipagination.total = res.result.total;
          }
        })
      },
      getQueryParams(){
        var param = Object.assign({}, this.queryParam,this.isorter);
        param.field = this.getQueryField();
        param.pageNo = this.ipagination.current;
        param.pageSize = this.ipagination.pageSize;
        return filterObj(param);
      },
      getQueryField(){
        //TODO 字段权限控制
        var str = "id,";
        for(var a = 0;a<this.columns.length;a++){
          str+=","+this.columns[a].dataIndex;
        }
        return str;
      },
      onSelectChange (selectedRowKeys,selectionRows) {
        this.selectedRowKeys = selectedRowKeys;
        this.selectionRows = selectionRows;
      },
      onClearSelected(){
        this.selectedRowKeys = [];
        this.selectionRows = [];
      },
      searchQuery(){
        this.loadData(1);
      },
      searchReset(){
        var that = this;
        that.queryParam={};
        that.loadData(1);
      },
      batchDel: function(){
        if(this.selectedRowKeys.length<=0){
          this.$message.warning('请选择一条记录！');
          return ;
        }else{
          var ids = "";
          for(var a =0;a<this.selectedRowKeys.length;a++){
            ids+=this.selectedRowKeys[a]+",";
          }
          var that = this;
          this.$confirm({
            title:"确认删除",
            content:"是否删除选中数据?",
            onOk: function(){
              deleteAction(that.url.deleteBatch,{ids: ids}).then((res)=>{
                if(res.success){
                  that.$message.success(res.message);
                  that.loadData();
                  that.onClearSelected();
                }else{
                  that.$message.warning(res.message);
                }
              });
            }
          });
        }
      },
      handleDelete: function(id){
        var that = this;
        deleteAction(that.url.delete,{id: id}).then((res)=>{
          if(res.success){
            that.$message.success(res.message);
            that.loadData();
          }else{
            that.$message.warning(res.message);
          }
        });
      },
      handleEdit: function(record){
        this.$refs.dmmGoodsModal.edit(record);
        this.$refs.dmmGoodsModal.title="编辑";
      },
      handleAdd: function(){
        this.$refs.dmmGoodsModal.add();
        this.$refs.dmmGoodsModal.title="新增";
      },
      handleTableChange(pagination, filters, sorter){
        //分页、排序、筛选变化时触发
        console.log(sorter);
        //TODO 筛选
        if (Object.keys(sorter).length>0){
          this.isorter.column = sorter.field;
          this.isorter.order = "ascend"==sorter.order?"asc":"desc"
        }
        this.ipagination = pagination;
        this.loadData();
      },
      modalFormOk () {
        // 新增/修改 成功时，重载列表
        this.loadData();
      }
    }
  }
</script>
<style lang="less" scoped>
  .ant-card-body .table-operator{
    margin-bottom: 18px;
  }
  .ant-layout-content{
    margin:12px 16px 0 !important;
  }
  .ant-table-tbody .ant-table-row td{
    padding-top:15px;
    padding-bottom:15px;
  }
  .anty-row-operator button{margin: 0 5px}
  .ant-btn-danger{background-color: #ffffff}

  .ant-modal-cust-warp{height: 100%}
  .ant-modal-cust-warp .ant-modal-body{height:calc(100% - 110px) !important;overflow-y: auto}
  .ant-modal-cust-warp .ant-modal-content{height:90% !important;overflow-y: hidden}
</style>