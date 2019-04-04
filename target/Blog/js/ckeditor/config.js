/**
 * @license Copyright (c) 2003-2018, CKSource - Frederico Knabben. All rights
 *          reserved. For licensing, see
 *          https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function(config) {
	// 设置宽高
    config.width = 750;
    config.height = 600;
    
	config.resize_enabled = false; // 禁止拖拽改变尺寸
	config.removePlugins = 'elementspath'; // 删除底边栏
	config.image_previewText = ' '; // 清空图片上传预览的内容
	config.image_prefillDimensions = false; // 禁止图片上传完毕后自动填充图片长和宽
	//工具栏是否可以被收缩
    config.toolbarCanCollapse = true;
    //设置Tab键
    config.tabSpaces = 4
    
    //上传设置
    config.filebrowserBrowseUrl = '/Blog/js/ckfinder/ckfinder.html';
	config.filebrowserImageBrowseUrl = '/Blog/js/ckfinder/ckfinder.html?type=Images';
	config.filebrowserFlashBrowseUrl = '/Blog/js/ckfinder/ckfinder.html?type=Flash';
	config.filebrowserUploadUrl = '/Blog/js/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
	config.filebrowserImageUploadUrl = '/Blog/js/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
	config.filebrowserFlashUploadUrl = '/Blog/js/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';
    
    //设置快捷键
    config.keystrokes = [
       [ CKEDITOR.ALT + 121 /*F10*/, 'toolbarFocus' ],  //获取焦点
        [ CKEDITOR.ALT + 122 /*F11*/, 'elementsPathFocus' ],  //元素焦点
       [ CKEDITOR.SHIFT + 121 /*F10*/, 'contextMenu' ],  //文本菜单
       [ CKEDITOR.CTRL + 90 /*Z*/, 'undo' ],  //撤销
        [ CKEDITOR.CTRL + 89 /*Y*/, 'redo' ],  //重做
        [ CKEDITOR.CTRL + CKEDITOR.SHIFT + 90 /*Z*/, 'redo' ],  //
        [ CKEDITOR.CTRL + 76 /*L*/, 'link' ],  //链接
        [ CKEDITOR.CTRL + 66 /*B*/, 'bold' ],  //粗体
        [ CKEDITOR.CTRL + 73 /*I*/, 'italic' ],  //斜体
        [ CKEDITOR.CTRL + 85 /*U*/, 'underline' ],  //下划线
        [ CKEDITOR.ALT + 109 /*-*/, 'toolbarCollapse' ]
    ]
    //设置HTML文档类型
    config.docType = '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd%22' ;
    //是否对编辑区域进行渲染 plugins/editingblock/plugin.js
    config.editingBlock = true;
    
    //使用搜索时的高亮色 plugins/find/plugin.js
    config.find_highlight = {
        element : 'span',
        styles : { 'background-color' : '#ff0', 'color' : '#00f' }
    };
    
    //从word中粘贴内容时是否移除格式plugins/pastefromword/plugin.js
    config.pasteFromWordRemoveStyle =false;
    
    
    //是否强制复制来的内容去除格式 plugins/pastetext/plugin.js
    config.forcePasteAsPlainText =false //不去除
	config.font_names="宋体/SimSun;新宋体/NSimSun;仿宋_GB2312/FangSong_GB2312;楷体_GB2312/KaiTi_GB2312;黑体/SimHei;微软雅黑/Microsoft YaHei;幼圆/YouYuan;华文彩云/STCaiyun;华文行楷/STXingkai;方正舒体/FZShuTi;方正姚体/FZYaoti;"+ config.font_names;
};
