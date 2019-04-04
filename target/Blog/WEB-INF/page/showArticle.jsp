<%@ page import="java.text.SimpleDateFormat, java.text.DateFormat"%>
<%@ page language="java" import="java.util.*, com.blog.entities.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<c:set var="base" scope="session" value="${pageContext.request.contextPath}"/>
<%
	Article article = (Article)request.getAttribute("article");
	String content = article.getContent();
	content = content.replace(" ", "+");
	content = content.replace("\n", "");
	
	int articleId = article.getArticleId();
	String articleType = article.getArticleType().getName();
	Integer articleTypeId = article.getArticleType().getArticleTypeId();
	String title = article.getTitle();
	
	DateFormat dateFomate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
	String date = dateFomate.format(article.getPostTime());
	List<Comment> comments = article.getComments();
	int commentCount = comments.size();
	
	User user = (User)session.getAttribute("user");
%>
<head>
<title>我的博客</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${base}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${base}/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" />

<style type="text/css">
	a:hover {text-decoration:none; color:#f44336; cursor: pointer;}
	.article h3{
		margin-top:12px;
		color:#4f4f4f;
	}
</style>
</head>
<body>

<!-- Modal -->
	<div class="modal fade" id="comment" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content" style="width:500px;">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">友善发言的的人运气都不会太差</h4>
	      </div>
	      <div class="modal-body">
	        <form id="add_blog_comment" class="form-horizontal">
	        	<div class="form-group">
				    <label class="col-sm-2 control-label">邮箱:</label>
				    <div id="emailInput" class="col-sm-8">
				      <input type="email" name="email" class="form-control" placeholder="Email">
				      <span class="help-block"></span>
				    </div>
				 </div>
				 <div class="form-group">
				    <label class="col-sm-2 control-label">评论:</label>
				    <div id="commentInput" class="col-sm-8">
				      <textarea class="form-control" name="content" rows="4"></textarea>
				      <span class="help-block"></span>
				    </div>
				 </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button id="save_comment" type="button" class="btn btn-primary">发布</button>
	        <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- Modal -->
<div class="modal fade" id="aboutMeModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">关于我</h4>
      </div>
      <div class="modal-body">
        <div style="text-align: center;">
			<p>记录成长的点点滴滴</p>
			<img src="${base}/images/model.jpg" width="100px" height="100px" class="img-circle">
			<div style="padding-top: 20px">
				<p>
					个人微信 ：xubin-xx
				</p>
				<p>
					个人QQ ：982479748
				</p>
				<p>
					邮箱 ：982479748@qq.com
				</p>
				<p>
					GitHub ：https://github.com/XXXXu
				</p>
				<p>
					CSDN ：https://blog.csdn.net/qq_35353212
				</p>
			</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">关闭</button>
      </div>
    </div>
  </div>
</div>

<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="logo">
        <h1><a href="${base}">Xubin's<span> Blog</span></a></h1>
      </div>
      <div class="clr"></div>
      <div class="menu_nav">
        <ul>
			<li class="active"><a href="${base}">主页</a></li>
			<li><a href="${base}/article/articleList">我的文章</a></li>
          <!-- <li>
          	<a href="support.html">分类</a>
          </li> -->
          <li><a href="${base}/photo/photoList">相册</a></li>
          <li><a id="show_model">关于我</a></li>
          <li class="last"><a href="${base}/login/loginPage">管理</a></li>
        	<%
        	if(user != null) {
				
          %>
          <li><a href="${base}/article/writeBlog">写博客</a></li>
          <li><a href="${base}/photo/uploadImagePage">上传照片</a></li>
          <li><a href="${base}/login/exitLogin">退出登录</a></li>
          <%
          	}
           %>
        </ul>
       <div class="search">
        <form id="form" name="form" method="post" action="">
          <input name="q" type="text" class="keywords" maxlength="20" placeholder="搜索..." />
          <input name="b" type="image" id="searchArticle" src="${base}/images/search.png" class="button" />
        </form>
      </div>
      <!--/search -->
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="clr"></div>
  <div class="content">
    <div class="content_resize">
      <div class="mainbar">
      	<div class="article">
          <h2>
          	<%=title %>
          	<span style="float:right;">
          		<button id="write_comment" type="button" class="btn btn-info">发表评论</button>
          	</span>
          </h2>
          <p>
          	<%=date %>
          	<span style="float:right;">
          		标签：
          		<a id="typeName" articletypeid="<%=articleTypeId %>"><%=articleType %></a>
          	</span>
          </p>
          <!-- <p id="content">
          </p> -->
          <div id="content" style="overflow:auto; padding-bottom:20px;">
          	
          </div>
        </div>
        <div id="showComment" class="article">
        	 <%
        		if(commentCount == 0) {
        	 %>
        	 		<h2 id="commentCount"><span>暂无评论，请留下您宝贵的意见(๑•̀ㅂ•́)و✧</span></h2>
        	 <%
        	 	}else {
        	  %>
	          		<h2 id="commentCount"><span><%=commentCount%></span> 条评论</h2>
	         <%
	          	}
	          %>
          <div class="comment">
          	<c:forEach items="<%=comments%>" var="comment">
	            <img src="${base}/images/userpic.gif" width="40" height="40" class="userpic" />
	            <p>
	            	邮箱：${comment.email}<br/>
	            	<fmt:formatDate value="${comment.postTime }" pattern="yyyy-MM-dd  HH:mm:ss"/>
	            	<%
	        		if(user != null) {
			        %>
			          <span style="float:right; margin-top:-18px;">
		          		<button class="delete_comment btn btn-danger" commentId="${comment.commentId}" type="button">删除评论</button>
		          	  </span>
			        <%
			        }
           			%>
	            </p>
	            <p>
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            	${comment.content}
	            </p>
          	</c:forEach>
          </div>
        </div>
        <div style="height:30px;">
        	
        </div>
      </div>
      <div class="sidebar">
        <div class="gadget">
          <h2>文章分类</h2>
          <ul id="articleType" class="sb_menu">
            
          </ul>
        </div>
        <div class="gadget">
          <h2><span>文章存档</span></h2>
          <ul id="articleListByTime" class="sb_menu">
          </ul>
        </div>
        <!-- <div class="gadget">
          <h2>Wise Words</h2>
          <div class="clr"></div>
          <p>   <img src="images/test_1.gif" alt="image" width="18" height="17" /> <em>We can let circumstances rule us, or we can take charge and rule our lives from within </em>.<img src="images/test_2.gif" alt="image" width="18" height="17" /></p>
          <p style="float:right;"><strong>Earl Nightingale</strong></p>
        </div> -->
      </div>
      <div class="clr"></div>
    </div>
  </div>
</div>

<script type="text/javascript" src="${base}/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${base}/js/myjs/common.js"></script>
<script type="text/javascript" src="${base}/js/myjs/cufon-yui.js"></script>
<script type="text/javascript" src="${base}/js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script src="${base}/js/base64/base64.js"></script>
<script src="${base}/js/base64/UnicodeAnsi.js"></script>
<script type="text/javascript">
	$(function() {
		$("#write_comment").click(function() {
			$('#comment').modal({
				keyboard: false
			});
		});
		//根据文章类型和时间，将文章分组
		getTypes();
		groupArticleByTime();
		var content = '<%=content%>';
		content = strAnsi2Unicode(decode64(content));
		$("#content").html(content);

		 function submitComment(comment) {
		 	comment.push({name:"articleId", value:<%=articleId%>});
		 	$.ajax({
		 		url:"${base}/comment/addComment",
		 		data:comment,
		 		type:"post",
		 		dataType:"json",
		 		success:function(data) {
		 			alert("评论成功，谢谢您的意见!")
		 			$("#add_blog_comment")[0].reset();
		 			$('#comment').modal('hide');
		 			var comment = data.map.comment;
		 			var commentCount = $("#commentCount span").html();
		 			if(isNaN(parseInt(commentCount))) {
		 				$("#commentCount span").empty();
		 				$("#commentCount").append("<span>1</span> 条评论");
		 			}else {
		 				$("#commentCount span").html(parseInt(commentCount)+1);
		 			}
		            $("<p></p>").append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
		            			.append(comment.content)
		            			.prependTo("#showComment .comment");
		            
		           	$("<p></p>").append("邮箱：").append(comment.email)
		           				.append("<br/>").append(timestampToTime(comment.postTime))
		           				.append("<span style='float:right; margin-top:-18px'><button class='delete_comment btn btn-danger' commentId="+comment.commentId+" type='button'>删除评论</button></span>")
		           				.prependTo("#showComment .comment");
		           	$("#showComment .comment").prepend("<img src='${base}/images/userpic.gif' width='40' height='40' class='userpic'/>");
		 		}
		 	});
		 };
		 //点击保存评论先进行后台验证，验证成功再提交submitComment
		 $("#save_comment").click(function() {
		 	var comment = $("#add_blog_comment").serializeArray();
		 	console.log(comment);
		 	$.ajax({
		 		url:"${base}/comment/validComment",
		 		type:"post",
		 		data:comment,
		 		dataType:"json",
		 		success:function(data) {
					//清空之前验证的样式
		 			$("#emailInput").removeClass("has-error");
		 			$("#commentInput").removeClass("has-error");
		 			$("#emailInput .help-block").empty();
		 			$("#commentInput .help-block").empty();
		 			if(data.msg == "validSuccess") {
		 				submitComment(comment);
		 			}else {
		 				if(undefined != data.map.email) {
			 				$("#emailInput").addClass("has-error");
			 				$("#emailInput .help-block").html(data.map.email);
		 				}
		 				if(undefined != data.map.content) {
		 					$("#commentInput").addClass("has-error");
			 				$("#commentInput .help-block").html(data.map.content);
		 				}
		 			}
		 		}
		 	});
		 	return false;
		 });
		 $(document).on("click", ".delete_comment", function() {
		 	if(window.confirm("确认要删除该评论吗?")) {
			 	var commentId = $(this).attr("commentId");
			 	$.ajax({
			 		url:"${base}/comment/deleteComment?commentId="+commentId,
			 		type:"post",
			 		dataType:"json",
			 		success:function(data) {
			 			if(data.msg == "success") {
			 				history.go(0);
			 			}
			 		}
			 	});
		 	}
		 });
		 
		 $("#typeName").click(function() {
		 	var articleTypeId = $(this).attr("articleTypeId");
			getArticlesByType(articleTypeId);
		 });
		 $("#show_model").click(function() {
			$('#aboutMeModel').modal({
				keyboard: false
			});
		  });
	});
</script>
</body>
</html>
