package com.blog.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.blog.crawl.other.HttpClientDownloader;
import com.blog.crawl.pipeline.JueJinPipeline;
import com.blog.crawl.spider.JueJinSpider;
import com.blog.entities.CrawlArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.entities.Article;
import com.blog.entities.ArticleGroupByTime;
import com.blog.entities.ArticleLable;
import com.blog.service.ArticleLableService;
import com.blog.service.ArticleService;
import com.blog.util.Escape;
import com.blog.util.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;

@Controller
@RequestMapping("article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ArticleLableService articleLableService;

	@RequestMapping("articleList")
	public String articleList(Map<String, Object> map) {
		PageHelper.startPage(1, 10);
		List<Article> articles = articleService.getArticles();
		PageInfo<Article> pageInfo = new PageInfo<Article>(articles, 8);
		int[] nums = pageInfo.getNavigatepageNums();
		pageInfo.setNavigatepageNums(reverse(nums));

		map.put("pageInfo", pageInfo);
		return "articleList";
	}

	/**
	 * 返回查看文章页面
	 *
	 * @return
	 */
	@RequestMapping("showArticle")
	public String showArticle(Integer articleId, Map<String, Object> map) {
		Article article = articleService.getArticleById(articleId);
		map.put("article", article);
		return "showArticle";
	}

	@RequestMapping("crawl/articleList")
	public String cralwArticleList(Map<String, Object> map) {
		PageHelper.startPage(1, 10);
		List<CrawlArticle> crawlArticle = articleService.getCrawlArticle();
		PageInfo<CrawlArticle> pageInfo = new PageInfo<>(crawlArticle);
		int[] nums = pageInfo.getNavigatepageNums();
		pageInfo.setNavigatepageNums(reverse(nums));
		map.put("pageInfo", pageInfo);
		return "crawlArticleList";
	}

	@RequestMapping("crawl/showArticle")
	public String cralwShowArticle(Long articleId, Map<String, Object> map) {
		CrawlArticle article = articleService.getCrawlArticleById(articleId);
		map.put("article", article);
		return "crawlShowArticle";
	}

    @ResponseBody
    @RequestMapping("crawl/getTodayArticle")
    public Message cralwGetTodayArticle() {
        Message message = new Message();
        if(articleService.cralwGetTodayArticle()) {
            message.setCode(200);
            message.setMsg("数据抓取成功");
        } else {
            message.setCode(100);
            message.setMsg("今日已抓取过数据");
        }
        return message;
    }

	@Scheduled(cron = "0 0 23 * * ?")
    public void cronGetArticle() {
		articleService.cralwGetTodayArticle();
	}

    @ResponseBody
    @RequestMapping("crawl/getArticles")
    public Message getCralwArticles(Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<CrawlArticle> articles = articleService.getCrawlArticle();
        PageInfo<CrawlArticle> pageInfo = new PageInfo<>(articles, 8);

        int[] nums = pageInfo.getNavigatepageNums();
        pageInfo.setNavigatepageNums(reverse(nums));
        return Message.getSuccessMessage().addObject("pageInfo", pageInfo);
    }

	@RequestMapping("addArticle")
	public String addArticle(Article article,
			@RequestParam("lable") Integer lable) {
		article.setTitle(Escape.unescape(article.getTitle()));
		article.setSummary(Escape.unescape(article.getSummary()));
		// 设置额外时间属性
		article.setPostTime(new Date());
		articleService.addArticle(article);
		// 保存该文章与标签的关联表
		ArticleLable articleLable = new ArticleLable(article.getArticleId(),
				lable);
		articleLableService.addArticleLable(articleLable);
		return "redirect:articleList";
	}

	/**
	 * 返回编辑文章
	 */
	@RequestMapping("editArticle")
	public String editArticle(Integer articleId, Map<String, Object> map) {
		Article article = articleService.getArticleById(articleId);
		map.put("article", article);

		return "editArticle";
	}

	@ResponseBody
	@RequestMapping("updateArticle")
	public Message updateArticle(Article article) {
		articleService.updateArticle(article);
		return Message.getSuccessMessage();
	}

	@ResponseBody
	@RequestMapping("deleteArticle")
	public Message deleteArticle(Integer articleId) {
		articleService.deleteArticle(articleId);
		return Message.getSuccessMessage();
	}

	@RequestMapping("writeBlog")
	public String writeBlog() {
		return "writeBlogPage";
	}

	/**
	 * 获取所有文章及文章的分类
	 */
	@ResponseBody
	@RequestMapping("getArticles")
	public Message getArticles(Integer pageNum) {
		PageHelper.startPage(pageNum, 10);
		List<Article> articles = articleService.getArticles();
		PageInfo<Article> pageInfo = new PageInfo<Article>(articles, 8);

		int[] nums = pageInfo.getNavigatepageNums();
		pageInfo.setNavigatepageNums(reverse(nums));
		return Message.getSuccessMessage().addObject("pageInfo", pageInfo);
	}

	/**
	 * 按照时间显示文章的数量
	 */
	@ResponseBody
	@RequestMapping("groupArticleByTime")
	public Message groupArticleByTime() {
		List<ArticleGroupByTime> articleGroup = articleService
				.selectArticleGroup();
		return Message.getSuccessMessage().addObject("articleGroup",
				articleGroup);
	}

	// 根据类型分类
	@RequestMapping("getArticlesByType")
	public String getArticlesByType(Integer articleTypeId,
			Map<String, Object> map) {
		PageHelper.startPage(1, 10);
		List<Article> articles = articleService
				.selectArticleByType(articleTypeId);
		PageInfo<Article> pageInfo = new PageInfo<Article>(articles, 8);

		int[] nums = pageInfo.getNavigatepageNums();
		pageInfo.setNavigatepageNums(reverse(nums));

		map.put("pageInfo", pageInfo);
		return "articleList";
	}

	// 根据类型翻页，返回json数据
	@ResponseBody
	@RequestMapping("pageChangeByType")
	public Message pageChangeByType(Integer articleTypeId, Integer pageNum) {
		PageHelper.startPage(pageNum, 10);
		List<Article> articles = articleService
				.selectArticleByType(articleTypeId);
		PageInfo<Article> pageInfo = new PageInfo<Article>(articles, 8);
		int[] nums = pageInfo.getNavigatepageNums();
		pageInfo.setNavigatepageNums(reverse(nums));

		return Message.getSuccessMessage().addObject("pageInfo", pageInfo);
	}

	// 根据时间分类
	@RequestMapping("getArticlesByTime")
	public String getArticlesByTime(
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date postTime,
			Map<String, Object> map) {
		PageHelper.startPage(1, 10);
		DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		String format = simpleDateFormat.format(postTime);
		List<Article> articles = articleService.selectArticleByTime(format);
		PageInfo<Article> pageInfo = new PageInfo<Article>(articles, 8);
		int[] nums = pageInfo.getNavigatepageNums();
		pageInfo.setNavigatepageNums(reverse(nums));

		map.put("pageInfo", pageInfo);
		return "articleList";
	}

	// 根据时间翻页，返回json数据
	@ResponseBody
	@RequestMapping("pageChangeByTime")
	public Message pageChangeByTime(
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date postTime,
			Integer pageNum) {
		PageHelper.startPage(pageNum, 10);
		DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		String format = simpleDateFormat.format(postTime);
		List<Article> articles = articleService.selectArticleByTime(format);
		PageInfo<Article> pageInfo = new PageInfo<Article>(articles, 8);

		int[] nums = pageInfo.getNavigatepageNums();
		pageInfo.setNavigatepageNums(reverse(nums));

		return Message.getSuccessMessage().addObject("pageInfo", pageInfo);
	}

	@RequestMapping("search")
	public String search(String searchParam, Map<String, Object> map) {
		searchParam = Escape.unescape(searchParam);
		PageHelper.startPage(1, 10);
		List<Article> articles = articleService.searchArticle(searchParam);
		PageInfo<Article> pageInfo = new PageInfo<Article>(articles);

		int[] nums = pageInfo.getNavigatepageNums();
		pageInfo.setNavigatepageNums(reverse(nums));
		map.put("pageInfo", pageInfo);
		return "articleList";
	}

	// 根据类型翻页，返回json数据
	@ResponseBody
	@RequestMapping("pageChangeBySearch")
	public Message pageChangeBySearch(String searchParam, Integer pageNum) {
		searchParam = Escape.unescape(searchParam);
		PageHelper.startPage(pageNum, 10);
		List<Article> articles = articleService.searchArticle(searchParam);
		PageInfo<Article> pageInfo = new PageInfo<Article>(articles, 4);
		int[] nums = pageInfo.getNavigatepageNums();
		pageInfo.setNavigatepageNums(reverse(nums));

		return Message.getSuccessMessage().addObject("pageInfo", pageInfo);
	}

	public int[] reverse(int[] nums) {
		int len = nums.length;
		for (int i = 0; i < (len >> 1); i++) {
			int temp = nums[i];
			nums[i] = nums[len - i - 1];
			nums[len - i - 1] = temp;
		}
		return nums;
	}

}
