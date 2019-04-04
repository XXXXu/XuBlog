<%@ page import="java.text.SimpleDateFormat, java.text.DateFormat"%>
<%@ page language="java" import="java.util.*, com.blog.entities.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<c:set var="base" scope="session" value="${pageContext.request.contextPath}"/>
<%
    CrawlArticle article = (CrawlArticle)request.getAttribute("article");
    String content = article.getContent();
    content = content.replace("\n", "");

    String articleId = article.getArticleUrl();
    String articleType = article.getKeyword();
    String title = article.getTitle();
    String author = article.getAuthor();
    String keyword = article.getKeyword();

    DateFormat dateFomate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    String date = dateFomate.format(article.getPostTime());

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
        body {
            height:100px;
        }
        .content .mainbar .article{
            width:900px;
        }
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
          		<%--<button id="write_comment" type="button" class="btn btn-info">发表评论</button>--%>
                    作者：
          		<a id="author"><%=author %></a>
          	</span>
                    </h2>
                    <p>
                        <%=date %>
                        <span style="float:right;">
          		标签：
          		<a><%=keyword==null ? "无":keyword %></a>
          	</span>
                    </p>
                    <!-- <p id="content">
                    </p> -->
                    <div id="content" style="overflow:auto; padding-bottom:20px;">

                    </div>
                </div>
                <%--<div id="showComment" class="article">
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
                </div>--%>
                <div style="height:30px;">

                </div>
            </div>
            <%--<div class="sidebar">
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
        </div>--%>
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
        $("#content").html(content);

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
