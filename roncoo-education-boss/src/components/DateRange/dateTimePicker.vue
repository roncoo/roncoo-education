<!--**

日期时间选择器

配置项:
"type": 'daterange', // 类型，日期：daterange，时间日期：datetimerange
"autofill": 自动填充，true、false,默认自动填充当天
"default": 默认值Array[int, lang], int：当前日期往前推第int天，lang:时间段长度，如:default="[3, 7]" 初始化一个当前日往前推3天，长度为7的时间段
reset：重置functionn(int, langth) //int：当前日期往前推第int天，lang:时间段长度，如reset(3, 7）重置一个往前堆3天，长度为7天的时间段

-->
<template>
  <el-date-picker
    v-model="value"
    :type="type"
    align="right"
    unlink-panels
    value-format="yyyy-MM-dd HH:mm:ss"
    :default-time="['00:00:00', '23:59:59']"
    @change="returnBackFn"
    range-separator="至"
    start-placeholder="开始日期"
    end-placeholder="结束日期"
    :picker-options="pickerOptions">
  </el-date-picker>
</template>
<script>
  export default {
    data() {
      return {
        dates: [],
        value: [],
        pickerOptions: {
          shortcuts: [{
            text: '昨天',
            onClick: picker => {
              const now = this.setNow(1, 1);
              picker.$emit('pick', now)
            }
          }, {
            text: '最近一周',
            onClick: picker => {
              const now = this.setNow(7)
              picker.$emit('pick', now)
            }
          }, {
            text: '最近一个月',
            onClick: picker => {
              const now = this.setNow(30)
              picker.$emit('pick', now)
            }
          }, {
            text: '最近三个月',
            onClick: picker => {
              const now = this.setNow(90)
              picker.$emit('pick', now)
            }
          }]
        }
      }
    },
    props: {
      type: {
        type: String,
        default: 'daterange'
      },
      default: {
        type: Array,
        default: () => {
          return [0, 0]
        }
      },
      autofill: {
        type: Boolean,
        default: false
      }
    },
    model: {
      prop: 'value',
      event: 'returnBack'

    },
    mounted() {
      if (this.autofill) {
        this.reset(this.default[0], this.default[1]);
      }
    },
    methods: {
      setNow(int, leng) {
        //int 前int天, 时间段长度leng天,1天lang=1
        const now = new Date();
        const start = new Date();
        start.setTime(now.getTime() - 3600 * 1000 * 24 * int);
        if (leng) {
          const st = int + 1 - leng;
          now.setTime(now.getTime() - 3600 * 1000 * 24 * st);
        }
        const nowYear = now.getFullYear();
        const nowMonth = now.getMonth();
        const nowDay = now.getDate();
        const endYear = start.getFullYear();
        const endMonth = start.getMonth();
        const endDay = start.getDate();
        return [`${endYear}-${endMonth + 1}-${endDay} 00:00:00`, `${nowYear}-${nowMonth + 1}-${nowDay} 23:59:59`]
      },
      returnBackFn() {
        this.$emit('returnBack', this.value);
      },
      clear() {
        this.value = [];
        this.$emit('returnBack', this.value);
      },
      reset(int = this.default[0], lang = this.default[1]) {
        this.value = this.setNow(int, lang);
        this.$emit('returnBack', this.value);
      }
    }
  }
</script>

<style scoped>

</style>
