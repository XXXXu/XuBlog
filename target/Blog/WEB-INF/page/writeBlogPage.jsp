<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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

<body>
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content" style="width:600px;">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">发布博客</h4>
	      </div>
	      <div id="add_blog_model" class="modal-body">
	        <form class="form-horizontal">
	        	<div class="form-group">
					<label class="col-sm-3 control-label">文章标签:</label>
				    <div id="show_lable" class="col-sm-4">
				    	<select class="form-control">
						</select>
				    </div>
				    <div id="add_lable" style="margin-top:5px; margin-left:-10px;" class="col-sm-5">
					    <a href="#"><span class="glyphicon glyphicon glyphicon-plus"></span> 添加标签</a>
				    	<!-- <input style="width:6em;" type="text" /> -->
				    </div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">博客分类:</label>
				    <div id="show_type" class="col-sm-4">
				    	<select class="form-control">
						</select>
				    </div>
				    <div id="add_type" style="margin-top:5px; margin-left:-10px;" class="col-sm-5">
					    <a href="#"><span class="glyphicon glyphicon glyphicon-plus"></span> 添加标签</a>
				    </div>
				</div>
				<div class="form-group">
				    <label class="col-sm-3 control-label">个人分类: </label>
				    <div class="col-sm-8">
						<select class="form-control">
						  <option>1</option>
						  <option>2</option>
						  <option>3</option>
						  <option>4</option>
						  <option>5</option>
						</select>
				    </div>
				</div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button id="save_blog" type="button" class="btn btn-primary">发布博客</button>
	        <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="container">
		<div class="row">
		  <div class="col-md-8">
				<h1>写博客</h1>
		  </div>
		  <div class="col-md-2 col-md-offset-2">
				<h3>唯有自知<br>唯有向前</h3>
		  </div>
		</div>
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
			  <div class="input-group">
				  <span class="input-group-addon">文章标题</span>
				  <input id="title" name="title" type="text" class="form-control" placeholder="请输入文章标题" aria-describedby="basic-addon1">
			  </div>
			</div>
		</div>
		<div class="row" style="margin-top:10px;">
			<div class="col-md-8 col-md-offset-2">
		  		<textarea name="editor" id="editor" cols="30" rows="10"></textarea>
			</div>
			<div class="col-md-2">
		  		<button id="release_blog" type="button" class="btn btn-danger btn-lg">发布博客</button>
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
		     //发送ajax请求填充模态框lable和type的数据
			 getLables();
			 getTypes();
		     $("#release_blog").click(function() {
			     $('#myModal').modal({
				  keyboard: false
				 });
		     });
		     function getLables() {
		     	$.ajax({
		     		type:"post",
		     		dataType:"json",
		     		url:"${pageContext.request.contextPath}/lable/getLables",
		     		success: function(data) {
		     			var labels = data.map.lables;
		     			$.each(labels, function(index, item) {
		     				$("<option></option>").prop("value", item.lableId)
		     								  .append(item.name).appendTo("#show_lable select");
		     			});
		     		}
		     	});
		     }
		     function getTypes() {
		     	$.ajax({
		     		type:"post",
		     		dataType:"json",
		     		url:"${pageContext.request.contextPath}/type/getTypes",
		     		success: function(data) {
		     			var types = data.map.types;
		     			$.each(types, function(index, item) {
		     				$("<option></option>").prop("value", item.articleTypeId)
		     								  .append(item.name).appendTo("#show_type select");
		     			});
		     		}
		     	});
		     }
		     
		     $("#save_blog").click(function() {
		     	var lable = $("#show_lable select").val();
		     	var type = $("#show_type select").val();
		     	var title = $("#title").val();
		     	var content = CKEDITOR.instances.editor.getData();
		     	var summary = getSummary(content) + "...";
		     	content = encode64(strUnicode2Ansi(content));
		     	title = escape(escape(title));
		     	summary = escape(escape(summary));
		     	window.location.href = "${pageContext.request.contextPath}/article/addArticle?"+
		     							"title=" + title +
		     							"&content="+ content +
		     							"&type="+ type +
		     							"&lable="+ lable +
		     							"&summary="+ summary;
		     });
		     function getSummary(content) {
		     	var summary = content.replace(/(\n)/g, "");  
			    summary = summary.replace(/(\t)/g, "");  
			    summary = summary.replace(/(\r)/g, "");  
			    summary = summary.replace(/<\/?[^>]*>/g, "");  
			    summary = summary.replace(/\s*/g, "");
			    /* var summary = content.replace(/<\/?.+?>/g, "");*/
				summary= summary.replace(/&nbsp;/g, " ");
				summary = summary.length < 150 ? summary : summary.substr(0, 150)
				return summary;
		     }
		     		     
		     function changeLable(element, id) {
		     	if(element.find("span").hasClass("glyphicon glyphicon-plus")) {
			     	element.find("span").removeClass("glyphicon glyphicon-plus");
		     		element.find("span").addClass("glyphicon glyphicon-minus");
		     		$("<input type='text' />").attr("style", "width:15px;").attr("textLen", "15")
		     								.addClass("lableInput").appendTo("#"+ id +"");
		     	}else {
		     		element.find("span").removeClass("glyphicon glyphicon-minus");
		     		element.find("span").addClass("glyphicon glyphicon-plus");
		     		$("#"+ id + " .lableInput").remove();
		     	}
		     }
		     /* 点击添加标签，右侧出现输入框 */
		     $("#add_lable a").click(function() {
		     	changeLable($(this), "add_lable");
		     });
		     $("#add_type a").click(function() {
		     	changeLable($(this), "add_type");
		     });
		     $(document).on("input",".lableInput", function() {
		     	/* 设置输入框的长度随输入文字长度而改变，初始长度15px，增加一个字符+15px
		     	输入框发生变化判断字符是否增多，是则+15，否则-15 */
		     	//alert($(this).attr("textLen")+" "+$(this).val().length);
		     	var textLen = parseInt($(this).attr("textLen"));
		     	var afterlen = parseInt($(this).val().length)*15;
		     	var beforelen = textLen-15;
		     	if(afterlen > beforelen) {
		     		$(this).attr("style", "width:"+(textLen+15)+"px;");
		     		$(this).attr("textLen", textLen+15);
		     	}else {
		     		$(this).attr("style", "width:"+(textLen-15)+"px;");
		     		$(this).attr("textLen", textLen-15);
		     	}
		     });
		     $("#add_lable").on("change", ".lableInput", function() {
		     	$.ajax({
		     		type: "post",
		     		dataType: "json",
		     		data: {name:$(this).val()},
		     		url:"${pageContext.request.contextPath}/lable/addLable",
		     		success: function(data) {
		     			var lable = data.map.lable;
		     			$("<option></option>").prop("value", lable.lableId)
		     								  .append(lable.name).appendTo("#show_lable select");
		     			$("#show_lable select").val([lable.lableId]);
		     			//输入完成，关闭输入框
		     			changeLable($("#add_lable a"), "add_lable");
		     		}
		     	});
		     });
		     $("#add_type").on("change", ".lableInput", function() {
		     	$.ajax({
		     		type: "post",
		     		dataType: "json",
		     		data: {name:$(this).val()},
		     		url:"${pageContext.request.contextPath}/type/addType",
		     		success: function(data) {
		     			var type = data.map.type;
		     			$("<option></option>").prop("value", type.articleTypeId)
		     								  .append(type.name).appendTo("#show_type select");
		     			$("#show_type select").val([type.articleTypeId]);
		     			//输入完成，关闭输入框
		     			changeLable($("#add_type a"), "add_type");
		     		}
		     	});
		     });
		});
   </script>
</body>
</html>
