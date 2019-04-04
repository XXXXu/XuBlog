package com.blog.crawl.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @Author: xubin
 * @Date: 2019/2/21
 */
public class ImportNewPipeline implements Pipeline {

    private String savepath = "C:\\Users\\xubin\\Desktop/test.txt";

    public void process(ResultItems resultItems, Task task) {
        List<String> titleList =  resultItems.get("title");
        List<String> createTimeList =  resultItems.get("createTime");
        List<String> summaryList =  resultItems.get("summary");
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(savepath,true);
            for (int j = 0; j < titleList.size(); j++)
            {
                System.out.println(titleList.get(j));
                fileWriter.write(titleList.get(j));
                fileWriter.write(createTimeList.get(j));
                fileWriter.write(summaryList.get(j));
                fileWriter.write("\r\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fileWriter.flush();
                fileWriter.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
