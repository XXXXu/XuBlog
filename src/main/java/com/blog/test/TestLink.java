package com.blog.test;

import java.util.Collections;
import java.util.List;


import com.blog.dao.ArticleMapper;
import com.blog.dao.ArticleTypeMapper;
import com.blog.entities.ArticleType;

/**
 * 测试dao层
 * @author mi
 * 使用@ContextConfiguration，指定spring配置文件的路径
 * 用spring自动注入的方式测试
 */
/*
@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:applicationContext.xml"})
public class TestLink {
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private ArticleTypeMapper articleTypeMapper;
	
	@Test
	public void test1() {
		System.out.println(articleMapper);
		//List<Article> selectByExample = articleMapper.selectByExample(null);
		*/
/*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for (Article article : selectByExample) {
			String format = simpleDateFormat.format(article.getPostTime());
			System.out.println(format);
			System.out.println(article);
		}*//*

		List<ArticleType> list = articleTypeMapper.selectByExample(null);
		for (ArticleType articleType : list) {
			System.out.println(articleType);
		}
	}
}
*/
