/*!
 * B-JUI  v1.2 (http://b-jui.com)
 * Git@OSC (http://git.oschina.net/xknaan/B-JUI)
 * Copyright 2014 K'naan (xknaan@163.com).
 * Licensed under Apache (http://www.apache.org/licenses/LICENSE-2.0)
 */

/* ========================================================================
 * B-JUI: bjui-regional.zh-CN.js  v1.2
 * @author K'naan (xknaan@163.com)
 * http://git.oschina.net/xknaan/B-JUI/blob/master/BJUI/js/bjui-regional.zh-CN.js
 * ========================================================================
 * Copyright 2014 K'naan.
 * Licensed under Apache (http://www.apache.org/licenses/LICENSE-2.0)
 * ======================================================================== */

+function ($) {
    'use strict';
    
    $(function() {
        
        /* 消息提示框 */
        BJUI.setRegional('alertmsg', {
            title  : {error : '错误提示', info : '信息提示', warn : '警告信息', correct : '成功信息', confirm : '确认信息'},
            btnMsg : {ok    : '确定', yes  : '是',   no   : '否',   cancel  : '取消'}
        })
        
        /* dialog */
        BJUI.setRegional('dialog', {
            close    : '关闭',
            maximize : '最大化',
            restore  : '还原',
            minimize : '最小化',
            title    : '弹出窗口'
        })
        
        /* order by */
        BJUI.setRegional('orderby', {
            asc  : '升序',
            desc : '降序'
        })
        
        /* 分页 */
        BJUI.setRegional('pagination', {
            total   : '总记录数/总页数',
            first   : '首页',
            last    : '末页',
            prev    : '上一页',
            next    : '下一页',
            jumpto  : '输入跳转页码，回车确认',
            jump    : '跳转',
            page    : '页',
            refresh : '刷新'
        })
        
        BJUI.setRegional('datagrid', {
            asc       : '升序',
            desc      : '降序',
            showhide  : '显示/隐藏 列',
            filter    : '过滤',
            clear     : '清除',
            lock      : '锁定列',
            unlock    : '解除锁定',
            add       : '添加',
            edit      : '编辑',
            save      : '保存',
            update    : '更新',
            cancel    : '取消',
            del       : '删除',
            prev      : '上一条',
            next      : '下一条',
            refresh   : '刷新',
            query     : '查询',
            'import'  : '导入',
            'export'  : '导出',
            all       : '全部',
            'true'    : '是',
            'false'   : '否',
            selectMsg : '未选中任何行！',
            editMsg   : '请先保存编辑行！',
            saveMsg   : '没有需要保存的行！',
            delMsg    : '确定要删除该行吗？',
            delMsgM   : '确定要删除选中行？'
        })
    
        /* ajax加载提示 */
        BJUI.setRegional('progressmsg', '正在努力加载数据，请稍等...')
        
        /* 日期选择器 */
        BJUI.setRegional('datepicker', {
            close      : '关闭',
            prev       : '上月',
            next       : '下月',
            clear      : '清空',
            ok         : '确定',
            dayNames   : ['日', '一', '二', '三', '四', '五', '六'],
            monthNames : ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
        })
        
        /* navtab右键菜单  */
        BJUI.setRegional('navtabCM', {
            refresh    : '刷新本标签',
            close      : '关闭本标签',
            closeother : '关闭其他标签',
            closeall   : '关闭所有标签'
        })
        
        /* dialog右键菜单 */
        BJUI.setRegional('dialogCM', {
            refresh    : '刷新本窗口',
            close      : '关闭本窗口',
            closeother : '关闭其他窗口',
            closeall   : '关闭所有窗口'
        })
        
        /* upload按钮提示 */
        BJUI.setRegional('upload', {
            upConfirm    : '开始上传',
            upPause      : '暂停上传',
            upCancel     : '取消上传'
        })
    
        /* 503错误提示 */
        BJUI.setRegional('statusCode_503', '服务器当前负载过大或者正在维护！')
        
        /* timeout提示 */
        BJUI.setRegional('sessiontimeout', '会话超时，请重新登陆！')
        
        /* 占位符对应选择器无有效值提示 */
        BJUI.setRegional('plhmsg', '占位符对应的选择器无有效值！')
        
        /* 未定义复选框组名提示 */
        BJUI.setRegional('nocheckgroup', '未定义选中项的组名[复选框的"data-group"]！')
        
        /* 未选中复选框提示 */
        BJUI.setRegional('notchecked', '未选中任何一项！')
        
        /* 未选中下拉菜单提示 */
        BJUI.setRegional('selectmsg', '请选择一个选项！')
        
        /* 表单验证错误提示信息 */
        BJUI.setRegional('validatemsg', '提交的表单中 [{0}] 个字段有错误，请更正后再提交！')
        
        /* 框架名称 */
        BJUI.setRegional('uititle', 'B-JUI')
        
        /* 主navtab标题 */
        BJUI.setRegional('maintab', '我的主页')
        
        /**
         * 
         *  Plugins regional setting
         * 
         */
        /* nice validate - Global configuration */
        $.validator && $.validator.config({
            //stopOnError: false,
            //theme: 'yellow_right',
            defaultMsg: "{0}格式不正确",
            loadingMsg: "正在验证...",
            
            // Custom rules
            rules: {
                digits: [/^\d+$/, '请输入整数']
                ,number: [/^[\-\+]?((([0-9]{1,3})([,][0-9]{3})*)|([0-9]+))?([\.]([0-9]+))?$/, '请输入有效的数字']
                ,letters: [/^[a-z]+$/i, '{0}只能输入字母']
                ,tel: [/^(?:(?:0\d{2,3}[\- ]?[1-9]\d{6,7})|(?:[48]00[\- ]?[1-9]\d{6}))$/, '电话格式不正确']
                ,mobile: [/^1[3-9]\d{9}$/, '手机号格式不正确']
                ,email: [/^[\w\+\-]+(\.[\w\+\-]+)*@[a-z\d\-]+(\.[a-z\d\-]+)*\.([a-z]{2,4})$/i, '邮箱格式不正确']
                ,qq: [/^[1-9]\d{4,}$/, 'QQ号格式不正确']
                //,date: [/^\d{4}-\d{1,2}-\d{1,2}$/, '请输入正确的日期,例:yyyy-mm-dd']
                ,date:[/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/, '请输入正确的日期，例：yyyy-MM-dd']
                //,time: [/^([01]\d|2[0-3])(:[0-5]\d){1,2}$/, '请输入正确的时间,例:14:30或14:30:00']
                ,time: [/^(2[0123]|(1|0?)[0-9]){1}:([0-5][0-9]){1}:([0-5][0-9]){1}$/, '请输入正确的时间，例：HH:mm:ss']
                ,datetime: [/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])\s+(2[0123]|(1|0?)[0-9]){1}:([0-5][0-9]){1}:([0-5][0-9]){1}$/,
                            '请输入正确的日期时间，例：yyyy-MM-dd HH:mm:ss']
                ,ID_card: [/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/, '请输入正确的身份证号码']
                ,url: [/^(https?|ftp):\/\/[^\s]+$/i, '网址格式不正确']
                ,postcode: [/^[1-9]\d{5}$/, '邮政编码格式不正确']
                ,chinese: [/^[\u0391-\uFFE5]+$/, '请输入中文']
                ,username: [/^\w{3,12}$/, '请输入3-12位数字、字母、下划线']
                ,password: [/^[0-9a-zA-Z]{6,16}$/, '密码由6-16位数字、字母组成']
                ,pattern:function(element, params) {
                    if (!params) return true
                    
                    var date = element.value.parseDate(params)
                    
                    return (!date ? this.renderMsg('错误的日期时间格式！', params) : true)
                }
                ,accept: function(element, params) {
                    if (!params) return true
                    
                    var ext = params[0]
                    
                    return (ext === '*') ||
                           (new RegExp('.(?:' + (ext || 'png|jpg|jpeg|gif') + ')$', 'i')).test(element.value) ||
                           this.renderMsg('只接受{1}后缀', ext.replace('|', ','))
                }
                
            }
        })

        /* nice validate - Default error messages */
        $.validator && $.validator.config({
            messages: {
                required: '{0}不能为空',
                remote: '{0}已被使用',
                integer: {
                    '*': '请输入整数',
                    '+': '请输入正整数',
                    '+0': '请输入正整数或0',
                    '-': '请输入负整数',
                    '-0': '请输入负整数或0'
                },
                match: {
                    eq: '{0}与{1}不一致',
                    neq: '{0}与{1}不能相同',
                    lt: '{0}必须小于{1}',
                    gt: '{0}必须大于{1}',
                    lte: '{0}必须小于或等于{1}',
                    gte: '{0}必须大于或等于{1}'
                },
                range: {
                    rg: '请输入{1}到{2}的数',
                    gte: '请输入大于或等于{1}的数',
                    lte: '请输入小于或等于{1}的数'
                },
                checked: {
                    eq: '请选择{1}项',
                    rg: '请选择{1}到{2}项',
                    gte: '请至少选择{1}项',
                    lte: '请最多选择{1}项'
                },
                length: {
                    eq: '请输入{1}个字符',
                    rg: '请输入{1}到{2}个字符',
                    gte: '请至少输入{1}个字符',
                    lte: '请最多输入{1}个字符',
                    eq_2: '',
                    rg_2: '',
                    gte_2: '',
                    lte_2: ''
                }
            }
        })
    })
    
}(jQuery);