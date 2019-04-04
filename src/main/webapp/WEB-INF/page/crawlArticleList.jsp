<%@ page language="java" import="java.util.*, com.blog.entities.User" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<c:set var="base" scope="session" value="${pageContext.request.contextPath}"/>
<head>
    <title>徐斌的博客</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="${base}/css/style.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${base}/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" />

    <script type="text/javascript" src="${base}/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${base}/js/myjs/cufon-yui.js"></script>
    <script type="text/javascript" src="${base}/js/myjs/common.js"></script>
    <script type="text/javascript" src="${base}/js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <style type="text/css">
        a:hover {text-decoration:none; color:#f44336; cursor: pointer;}
        .article h3{
            margin-top:12px;
            color:#4f4f4f;
        }
    </style>
</head>
<%
    User user = (User)session.getAttribute("user");
%>
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
        </div>
    </div>
    <div class="clr"></div>
    <div class="content">
        <div class="content_resize">
            <div class="mainbar">
                <c:forEach items="${requestScope.pageInfo.list}" var="article">
                    <div class="article mark">
                        <a class="show_article" articleid="${article.id}">
                            <h3>${article.title}</h3>
                            <p>${article.summary}</p>
                        </a>
                        <p>
                            关键词：
                            <a class="getArticlesByType" >
                                <strong>${article.keyword eq null ? "无" : article.keyword}</strong>
                            </a>
                            <span>&nbsp;•&nbsp;</span>
                            <fmt:formatDate value="${article.postTime}" pattern="yyyy-MM-dd  HH:mm:ss"/>
                            <%--<%
                                if(user != null) {
                            %>
                            <span style="float:right">
	      				<a class="edit" articleId="${article.articleId}">
	      					<strong style="color:#4093c6">编辑</strong>
	      				</a>
	      				<span>&nbsp;•&nbsp;</span>
	      				<a class="delete" articleId="${article.articleId}">
	      					<strong style="color:#c92027">删除</strong>
	      				</a>
      				</span>
                            <%
                                }
                            %>
--%>
                        </p>
                    </div>
                </c:forEach>
                <div id="pageInfo" class="article" style="padding:5px 20px 2px 20px; background:none; border:0;">
                    <p>
                        <c:if test="${requestScope.pageInfo.navigatepageNums == null}">
                            暂无数据╮（╯＿╰）╭
                        </c:if>
                        <c:if test="${requestScope.pageInfo.navigatepageNums != null}">
	          	<span id="show_page_data">
	          		共 ${requestScope.pageInfo.pages} 页，
	          		${requestScope.pageInfo.total} 条数据
	          	</span>
                        </c:if>
                        <!-- 共2页，8条数据 -->
                        <span class="butons">
	          	<c:forEach items="${requestScope.pageInfo.navigatepageNums}" var="pageNum">
                    <c:if test="${pageNum == 1}">
                        <a class="active">${pageNum}</a>
                    </c:if>
                    <c:if test="${pageNum != 1}">
                        <a>${pageNum}</a>
                    </c:if>
                </c:forEach>
          	</span>
                    </p>
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
<script type="text/javascript">
    $(function() {
        getTypes();//获取所有分类
        groupArticleByTime();//根据时间分组文章
        function getArticles(url) {
            $.ajax({
                type:"post",
                dataType:"json",
                url: url,
                success: function(data) {
                    //清空节点
                    $(".mark").remove();
                    $("#show_page_data").empty();
                    $("#pageInfo .butons").empty();
                    var articles = data.map.pageInfo.list;
                    $.each(articles, function(index, item) {
                        var time = timestampToTime(item.postTime);
                        //设置class为mark，方便翻页的时候清空节点
                        $("<div></div>").addClass("article mark")
                            .append( $("<a class='show_article'></a>").attr("articleId", item.id)
                                .append($("<h3></h3>").append(item.title))
                                .append($("<p></p>").append(item.summary)) )
                            .append( $("<p></p>").append("关键词：" + item.keyword)
                                .append("<span>&nbsp;&bull;&nbsp;</span>")
                                .append(time) )
                            .insertBefore("#pageInfo");
                        <%--<%
                           if(user != null) {
                         %>
                        $("#pageInfo").prev().find("p :eq(1)").append($("<span style='float:right'></span>")
                            .append($("<a class='edit'></a>").attr("articleId", item.articleId)
                                .append($("<strong style='color:#4093c6'>编辑</strong>")))
                            .append("<span>&nbsp;&bull;&nbsp;</span>")
                            .append($("<a class='delete'></a>").attr("articleId", item.articleId)
                                .append($("<strong style='color:#c92027'>删除</strong>"))));
                        <%
                            }
                         %>--%>
                    });
                    var pageInfo = data.map.pageInfo;
                    $("#show_page_data").append("共"+ pageInfo.pages + "页，"+ pageInfo.total +"条数据");
                    $.each(pageInfo.navigatepageNums, function(index, item) {
                        if(pageInfo.pageNum == item) {
                            $("#pageInfo .butons").append($("<a></a>").addClass("active").append(item));
                        }else {
                            $("#pageInfo .butons").append($("<a></a>").append(item));
                        }
                    });
                }
            });
        }
        $("#pageInfo").on("click", ".butons a", function() {
            var urlPath = window.location.pathname;
            var arrUrlPath = urlPath.split("/");
            //若是分类和时间，还需获取他们的参数
            var paramStr = window.location.search;
            var paramValue = paramStr.split("=");
            /* 获取当前页面的URI，若为undefined，则是正常翻页
             若为getArticlesByTime，则为查询时间之后翻页
             若为getArticlesByType，则为查询类型之后翻页 */
            if("articleList" == arrUrlPath[3] || undefined == arrUrlPath[3]) {
                var url = "${base}/article/crawl/getArticles?pageNum="+$(this).html();
            }else if("getArticlesByTime" == arrUrlPath[3]) {
                var url = "${base}/article/pageChangeByTime?pageNum="+$(this).html()+"&postTime="+paramValue[1];
            }else if("getArticlesByType" == arrUrlPath[3]) {
                var url = "${base}/article/crawl/pageChangeByType?pageNum="+$(this).html()+"&articleTypeId="+paramValue[1];
            }else if("search" == arrUrlPath[3]) {
                var url = "${base}/article/pageChangeBySearch?pageNum="+$(this).html()+"&searchParam="+paramValue[1];
            }
            getArticles(url);
        });
        //查看文章
        $(".mainbar").on("click", ".show_article", function() {
            window.location.href="${base}/article/crawl/showArticle?articleId="+$(this).attr("articleId");
        });
        //删除
        $(document).on("click", ".delete", function() {
            if(window.confirm("确认删除吗")) {
                var articleId = $(this).attr("articleId");
                $.ajax({
                    url:"${base}/article/deleteArticle?&articleId="+articleId,
                    dataType:"json",
                    type:"post",
                    success: function(data) {
                        if(data.msg == "success") {
                            var pageNum = $(".butons .active").html();
                            var urlPath = window.location.pathname;
                            var arrUrlPath = urlPath.split("/");
                            //若是分类和时间，还需获取他们的参数
                            var paramStr = window.location.search;
                            var paramValue = paramStr.split("=");
                            if("articleList" == arrUrlPath[3] || undefined == arrUrlPath[3]) {
                                var url = "${base}/article/getArticles?pageNum="+pageNum;
                            }else if("getArticlesByTime" == arrUrlPath[3]) {
                                var url = "${base}/article/pageChangeByTime?pageNum="+pageNum+"&postTime="+paramValue[1];
                            }else if("getArticlesByType" == arrUrlPath[3]) {
                                var url = "${base}/article/pageChangeByType?pageNum="+pageNum+"&articleTypeId="+paramValue[1];
                            }else if("search" == arrUrlPath[3]) {
                                var url = "${base}/article/pageChangeBySearch?pageNum="+pageNum+"&searchParam="+paramValue[1];
                            }
                            getArticles(url);
                        }
                    }
                });
            }
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