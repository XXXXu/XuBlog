<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登录页面</title>
	<link rel="stylesheet" href="${base}/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" />

	<script type="text/javascript" src="${base}/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${base}/js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
  	<style type="text/css">
  		body{
			    background: url("/Blog/images/background2.jpg");
			    background-size: 100%;
			    /* animation-name:myfirst;
			    animation-duration:12s; */
			    /*变换时间*/
			    /* animation-delay:2s; */
			    /*动画开始时间*/
			    /* animation-iteration-count:infinite; */
			    /*下一周期循环播放*/
			    /* animation-play-state:running; */
			    /*动画开始运行 */
			}
			/* @keyframes myfirst
			{
			    0%   {background:url("/Blog/images/background2.jpg")}
			    34%  {background:url("/Blog/images/background1.jpg")}
			    67%  {background:url("/Blog/images/background3.jpg")}
			    100% {background:url("/Blog/images/background2.jpg")}
			} */
			.form{background: rgba(255,255,255,0.2);width:400px;margin:120px auto;}
			/*阴影*/
			.fa{display: inline-block;top: 27px;left: 6px;position: relative;color: #ccc;}
			input[type="text"],input[type="password"]{padding-left:26px;}
			.checkbox{padding-left:21px;}
  	</style>
  </head>
  <body>
	<div class="container">
        <div class="form row">
            <div class="form-horizontal col-md-offset-3" id="login_form">
                <h3 class="form-title">LOGIN</h3>
                <div class="col-md-9">
                    <div id="userNameGroup" class="form-group">
                        <i class="fa fa-user fa-lg"></i>
                        <input class="form-control required" type="text" placeholder="Username" id="userName" name="userName" autofocus="autofocus" maxlength="20"/>
                    </div>
                    <div class="form-group">
                            <i class="fa fa-lock fa-lg"></i>
                            <input class="form-control required" type="password" placeholder="Password" id="password" name="password" maxlength="15"/>
                    </div>
                    <div class="form-group">
                        <label class="checkbox">
                            <input type="checkbox" name="remember" value="1"/>记住我
                        </label>
                    </div>
                    <div class="form-group col-md-offset-9">
                        <button id="submit" type="submit" class="btn btn-success pull-right" name="submit">登录</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    	$("#submit").click(function() {
    		var userName = $("#userName").val();
	    	var password = $("#password").val();
	    	$.ajax({
	    		url:"${base}/login/validatedLogin",
	    		data:{"userName":userName, "password":password},
	    		dataType:"json",
	    		type:"post",
	    		success:function(data) {
	    			if(data.msg == "fail") {
	    				$("#helpBlock").remove();
	    				$("#userNameGroup").removeClass("has-error");
	    				$("#userNameGroup").append($("<span id='helpBlock' class='help-block'></span>")
	    								   .append("用户名或密码错误")).addClass("has-error");
	    			}else {
	    				//登陆成功，跳转页面
	    				window.location.href="${base}/article/articleList";
	    			}
	    		}
	    	});
    	});
    </script>
  </body>
</html>
