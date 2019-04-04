<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>上传照片</title>
<link href="${base}/css/webuploader.css" rel="stylesheet" type="text/css" />
<link href="${base}/css/uplodeStyle.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${base}/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" />

<script type="text/javascript" src="${base}/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${base}/js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="${base}/js/myjs/webuploader.js"></script>
<script type="text/javascript" src="${base}/js/myjs/demo.js"></script>
</head>

<body>
	<div class="content" style="margin: 30px auto;">
    <div class="container">
		<div class="input-group">
		  <span class="input-group-addon" id="basic-addon1">图片描述</span>
		  <input id="photoDesc" type="text" name="photoDesc" class="form-control">
		</div>
		<div id="wrapper">
	        <div id="container">
	            <!--头部，相册选择和格式选择-->
	            <div id="uploader">
	                <div class="queueList">
	                    <div id="dndArea" class="placeholder">
	                        <div id="filePicker"></div>
	                        <p>或将照片拖到这里，单次最多可选300张</p>
	                    </div>
	                </div>
	                <div class="statusBar" style="display:none;">
	                    <div class="progress">
	                        <span class="text">0%</span>
	                        <span class="percentage"></span>
	                    </div><div class="info"></div>
	                    <div class="btns">
	                        <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	   </div>
	  </div>
</body>
</html>
