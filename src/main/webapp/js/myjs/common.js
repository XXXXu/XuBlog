function getTypes() {
	$.ajax({
		type : "post",
		dataType : "json",
		url : "/Blog/type/getTypes",
		success : function(data) {
			var types = data.map.types;
			$.each(types, function(index, item) {
				if (item.articleCount > 0) {
					$("#articleType").append(
							$("<li></li>").append($("<a></a>")
										  .attr("articleTypeId", item.articleTypeId)
										  .append(item.name))
										  .append($("<span style='float:right;'></span>")
										  .append(item.articleCount + "篇")));
				}
			});
		}
	});
}
//根据分类查找文章
$(document).on("click", "#articleType li a", function() {
	var articleTypeId = $(this).attr("articleTypeId");
	getArticlesByType(articleTypeId)
});
$(document).on("click", ".getArticlesByType", function() {
	var articleTypeId = $(this).attr("articleTypeId");
	getArticlesByType(articleTypeId)
})

function getArticlesByType(articleTypeId) {
	window.location.href="/Blog/article/getArticlesByType?articleTypeId="+articleTypeId;
}

$(document).on("click", "#articleListByTime li a", function() {
	var postTime = $(this).attr("time");
	getArticleByTime(postTime);
})

function getArticleByTime(postTime) {
	window.location.href="/Blog/article/getArticlesByTime?postTime="+postTime;
}

// 根据年和月，将文章分组
function groupArticleByTime() {
	$.ajax({
		type : "post",
		dataType : "json",
		url : "/Blog/article/groupArticleByTime",
		success : function(data) {
			console.log(data);
			var articleGroup = data.map.articleGroup;
			$.each(articleGroup, function(index, item) {
				var time = timestampToTime(item.postTime);
				var year = time.substr(0, 4);
				var month = parseInt(time.substr(5, 2));
				$("<li></li>").append(
						$("<a></a>").attr("time", time).append(
								year + "年" + month + "月")).append(
						$("<span style='float:right;'></span>").append(
								item.articleCount + "篇")).prependTo(
						"#articleListByTime");
			});
		}
	});
}

//跳转到修改页面
$(document).on("click", ".edit", function() {
	window.location.href="/Blog/article/editArticle?articleId="+$(this).attr("articleId");
});

//搜索
$(document).on("click", "#searchArticle", function() {
	var searchParam = $(this).prev().val();
	searchParam = escape(escape(searchParam));
	window.location.href="/Blog/article/search?searchParam="+searchParam;
	return false;
});

function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
    return Y+M+D+h+m+s;
}