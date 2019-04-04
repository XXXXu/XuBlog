<%@ page language="java" import="java.util.*, com.blog.entities.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/js/ckfinder/ckfinder.js"></script>
<script src="${pageContext.request.contextPath}/js/ckeditor/config.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<%-- <script src="${pageContext.request.contextPath}/js/jquery.base64.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/base64/base64.js"></script>
<script src="${pageContext.request.contextPath}/js/base64/UnicodeAnsi.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" />

<style type="text/css">
	#add_blog_model .control-label{
		font-size: 12pt;
	}
</style>
</head>
<%
	Article article = (Article)request.getAttribute("article");
	String content = article.getContent();
	content = content.replace(" ", "+");
	content = content.replace("\n", "");
%>

<body>	
	<div class="container">
		<div class="row">
		  <div class="col-md-8">
				<h1>修改博客</h1>
		  </div>
		  <div class="col-md-2 col-md-offset-2">
				<h3>唯有自知<br>唯有向前</h3>
		  </div>
		</div>
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
			  <div class="input-group">
				  <span class="input-group-addon">文章标题</span>
				  <input id="title" name="title" type="text" class="form-control" value="${requestScope.article.title}"/>
			  </div>
			</div>
		</div>
		<div class="row" style="margin-top:10px;">
			<div class="col-md-8 col-md-offset-2">
		  		<textarea name="editor" id="editor" cols="30" rows="10">
		  			
		  		</textarea>
			</div>
			<div class="col-md-2">
		  		<button id="update_blog" type="button" articleId="${requestScope.article.articleId}" class="btn btn-danger btn-lg">确认修改</button>
			</div>
		</div>
	</div>
   
   <script type="text/javascript">
   		$(function() {
		     CKEDITOR.replace('editor', {
		     	toolbar :
	             [
	                //加粗     斜体，     下划线      穿过线      下标字        上标字
	                ['Bold','Italic','Underline','Strike','Subscript','Superscript'],
	                // 数字列表          实体列表            减小缩进    增大缩进
	                ['NumberedList','BulletedList','-','Outdent','Indent'],
	                //左对 齐             居中对齐          右对齐          两端对齐
	                ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	                //超链接  取消超链接 锚点
	                ['Link','Unlink','Anchor'],
	                //图片    flash    表格       水平线            表情       特殊字符        分页符
	                ['Image','Table','HorizontalRule','SpecialChar','PageBreak'],
	                '/',
	                // 样式       格式      字体    字体大小
	                ['Styles','Format','Font','FontSize'],
	                //文本颜色     背景颜色
	                ['TextColor','BGColor'],
	                //全屏           显示区块
	                ['Maximize', 'ShowBlocks','-']
	             ],
		     });
		     //将文章解码
		     var content = '<%=content%>';
		     content = strAnsi2Unicode(decode64(content));
		     //放入到富文本框
		     $("#editor").val(content);
		     
		     $("#update_blog").click(function() {
		      	var articleId = $(this).attr("articleId");
		     	var title = $("#title").val();
		     	var content = CKEDITOR.instances.editor.getData();
		     	var summary = getSummary(content) + "...";
		     	content = encode64(strUnicode2Ansi(content));
		     	$.ajax({
		     		url:"${base}/article/updateArticle",
		     		data:{"articleId":articleId, "title":title, 
		     			  "content":content, "summary":summary},
		     		dataType:"json",
		     		type:"post",
		     		success: function(data) {
		     			if(data.msg == "success") {
		     				//后退一个页面，并刷新
		     				history.go(-1);
		     				location.reload();
		     			}
		     		}
		     	});
		     });
		     
		     function getSummary(content) {
		     	var summary = content.replace(/(\n)/g, "");  
			    summary = summary.replace(/(\t)/g, "");  
			    summary = summary.replace(/(\r)/g, "");  
			    summary = summary.replace(/<\/?[^>]*>/g, "");  
			    summary = summary.replace(/\s*/g, "");
			    /* var summary = content.replace(/<\/?.+?>/g, "");*/
				summary= summary.replace(/&nbsp;/g, " ");
				return summary.length < 150 ? summary : summary.substr(0, 150);
		     }
		});
   </script>
</body>
</html>
