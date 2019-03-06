var initEdit = function(editEditor, url) {
 	var E = window.wangEditor;
 	var editor = new E(editEditor);
    var $desc = $(editEditor).next('textarea');
    // 自定义菜单配置
    editor.customConfig.menus = [
        'head',  // 标题
	    'bold',  // 粗体
	    'fontSize',  // 字号
	    'fontName',  // 字体
	    'italic',  // 斜体
	    'underline',  // 下划线
	    'strikeThrough',  // 删除线
	    'foreColor',  // 文字颜色
	    'backColor',  // 背景颜色
	    'link',  // 插入链接
//	    'list',  // 列表
	    'justify',  // 对齐方式
	    'quote',  // 引用
	    'emoticon',  // 表情
	    'image',  // 插入图片
	    'table',  // 表格
//	    'video',  // 插入视频
	    'code',  // 插入代码
	    'undo',  // 撤销
	    'redo'  // 重复
    ]
    editor.customConfig.zIndex = 1;
    editor.customConfig.uploadImgServer = url;
    editor.customConfig.uploadFileName = 'descFile';
    editor.customConfig.onchange = function (html) {
        $desc.val(html);// 监控变化，同步更新到 textarea
    }
    editor.customConfig.linkImgCallback = function (url) {
    	$desc.val(url);// 网络图片插入
	}
	editor.customConfig.uploadImgHooks = {
			customInsert: function (insertImg, result, editor) {
                var url =result.data;
                insertImg(url);
         		$desc.val(url);
            }
    }
    editor.create()
    
    $desc.val(editor.txt.html()); // 初始化 textarea 的值（进来时候的显示）
};