<%@ page language="java" import="java.util.*, com.github.pagehelper.PageInfo,com.blog.entities.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	PageInfo<Photo> pageInfo = (PageInfo)request.getAttribute("pageInfo");
    List<Photo> photos = pageInfo.getList();
    int[] navigatepageNums = pageInfo.getNavigatepageNums();
    int photoCount = photos.size();
    double rows = Math.ceil(photoCount*1.0/4);
    
    User user = (User)session.getAttribute("user");
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>相册</title>
    
    <link href="${base}/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" />
  	<link href="${base}/css/style.css" rel="stylesheet" type="text/css" />
  	<link href="${base}/css/lightbox.css" rel="stylesheet" type="text/css" />
  	<style type="text/css">
  	</style>
  </head>
  
  <body>
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
	      <div class="menu_nav" style="margin:10px auto;">
	        <ul>
				<li class="active"><a href="${base}">主页</a></li>
				<li><a href="${base}/article/articleList">我的文章</a></li>
	          <!-- <li>
	          	<a href="support.html">分类</a>
	          </li> -->
	          <li><a href="${base}/photo/photoList">相册</a></li>
	          <li><a id="show_model" href="#">关于我</a></li>
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
  </div>
  <div class="content">
    <div class="container">
    	<%
    		if(rows > 0) {
		    	for(int i = 0; i < rows; i++) {
		    	int begin = i*4;
		    	int end = Math.min((i+1)*4-1, photoCount-1);
	    %>
	    <hr/>
		<div class="row" >
			<c:forEach items="${requestScope.pageInfo.list}" var="photo" begin="<%=begin%>" end="<%=end%>">
				  <div class="col-xs-6 col-md-3" >
				    <a href="${photo.url}" class="thumbnail" data-lightbox="roadtrip">
				      <img src="${photo.url}" style="height: 180px;" />
				    </a>
				    <div style="text-align: center;">
		                <span><label style="font-size:14pt; color: #0c0c0c;">${photo.photoDesc}</label></span>
		                <%
				        	if(user != null) {
								
				          %>
				          <span style="float:right;"><button type="button" photoId="${photo.photoId}" class="delete btn btn-danger btn-sm">删除</button></span>
				          <%
				          	}
				           %>
		            </div>
				  </div>
			</c:forEach>
		</div>
		<%
				}
			}
		%>
			<nav id="pageInfo" aria-label="Page navigation">
			  <ul style="float:right;" class="pagination pagination-lg">
			    <li>
			      <a href="#" pageNum="${requestScope.pageInfo.prePage}" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			    <%
			    	for(int i = 0; i < navigatepageNums.length; i++) {
			    		if(pageInfo.getPageNum() == navigatepageNums[i]) {
			     %>
				    <li class="active"><a pageNum="<%=navigatepageNums[i]%>" href="#"><%=navigatepageNums[i]%></a></li>
			     <%
			    		}else {
			     %>
			    	 <li><a pageNum="<%=navigatepageNums[i]%>" href="#"><%=navigatepageNums[i]%></a></li>
			     <%
			     		}
			     	}
			      %>
			    <li>
			      <a href="#" pageNum="${requestScope.pageInfo.nextPage}" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			  </ul>
			</nav>		
		<div style="height:40px;"></div>
	</div>
	</div>
    <script type="text/javascript" src="${base}/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${base}/js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
	<script type="text/javascript" src="${base}/js/myjs/cufon-yui.js"></script>
	<script type="text/javascript" src="${base}/js/myjs/common.js"></script>
	<script type="text/javascript" src="${base}/js/lightbox.js"></script>
  	<script type="text/javascript">
  		$("#show_model").click(function() {
			 $('#aboutMeModel').modal({
				keyboard: false
			});
		});
	    lightbox.option({
	      'resizeDuration': 200,
	      'wrapAround': true,
	      //如果为true，则在支持触摸的设备上始终可以看到在查看图像集时鼠标悬停时出现的左侧和右侧导航箭头。
	      alwaysShowNavOnTouchDevices:true
	    })
	    $(".delete").click(function() {
	    	if(confirm("确认删除该相片吗?")) {
		    	var photoId = $(this).attr("photoId");
		    	var url = $(this).parent().parent().prev().attr("href");
		    	window.location.href="${base}/photo/deletePhoto?photoId="+photoId+"&url="+url;
	    	}
	    });
	    $("#pageInfo a").click(function() {
	    	var pageNum = $(this).attr("pageNum")
	    	window.location.href="${base}/photo/photoList?pageNum="+pageNum;
	    });
  	</script>
  </body>
</html>
