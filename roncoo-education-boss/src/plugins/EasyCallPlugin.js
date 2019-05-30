
export default function install(Vue) {
  if (install.installed) return;
  install.installed = true;

  Vue.prototype.tips = function(text = '', type = 'info', time = 2000) {
    console.log(text)
    try {
      this.$message({
        type: type,
        message: text,
        duration: time
      });
    } catch (err) {
      console.debug(err, 'err');
    }
  };

  Vue.prototype.loading = {
    show(txt = 'Loading') {
      this.__loading__ = Vue.prototype.$loading({
        lock: true,
        text: txt,
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });
    },
    hide() {
      this.__loading__.close()
    }
  };
}
