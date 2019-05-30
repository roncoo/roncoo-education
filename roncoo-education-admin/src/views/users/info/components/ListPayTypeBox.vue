<template>
  <div>
    <el-dialog width="70%" :title="payTypeTitle" :visible.sync="toggleBox">
      <el-table :data="payTypeList" height="300">
        <el-table-column type="index" label="序号" width="50"></el-table-column>
        <el-table-column property="payName" label="支付方式"></el-table-column>
        <el-table-column label="状态" width="80">
          <template slot-scope="scope">
            <span :class="textClass(scope.row.status)">{{textStatus[scope.row.status]}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="80">
          <template slot-scope="scope">
            <el-button size="mini" type="primary" @click.native="togglePayTypeStatus(scope.row)">{{buttonStatus[scope.row.status]}}</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  props: {
    togglePayTypeBox: {
      type: Boolean,
      default: false
    },
    payTypeList: {
      type: [Object, Array],
      default() {
        return []
      }
    },
    payTypeTitle: {
      type: String,
      default: ''
    }
  },
  computed: {
    toggleBox: {
      get() {
        return this.togglePayTypeBox
      },
      set() {
        this.$emit('closePayTypeBox')
      }
    }
  },
  data() {
    return {
      buttonStatus: {
        ACTIVE: '关闭',
        UNACTIVE: '启用'
      },
      textStatus: {
        ACTIVE: '已开启',
        UNACTIVE: '已关闭'
      }
    }
  },
  methods: {
    ...mapActions('users', ['updatePayType']),
    textClass(status) {
      return {
        c_red: status === 'UNACTIVE',
        c_blue: status === 'ACTIVE'
      }
    },
    togglePayTypeStatus(info) {
      console.log(info)
      const { userNo, payCode, payType, status, payName } = info
      const params = {
        userNo,
        payCode,
        payType,
        status,
        payName
      }
      if (status === 'UNACTIVE') {
        params.status = 'ACTIVE'
      } else {
        params.status = 'UNACTIVE'
      }
      this.loading.show()
      this.updatePayType(params).then(res => {
        this.loading.hide()
        console.log(res)
        this.$emit('togglePayTypeStatus', params)
      }).catch(() => {
        this.loading.hide()
      })
    }
  }
}
</script>

<style lang="scss"></style>
