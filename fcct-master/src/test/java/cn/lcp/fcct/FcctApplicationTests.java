package cn.lcp.fcct;

import cn.lcp.fcct.dao.CodeFactoryDao;
import cn.lcp.fcct.po.InformationSchema;
import cn.lcp.fcct.util.CodeFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("cn.lcp.fcct.dao")
public class FcctApplicationTests {
    @Autowired
    private CodeFactoryDao codeFactoryDao;

    @Test
    public void contextLoads() {
        long t = System.currentTimeMillis();
        String database_name = "lopscoop_v2";
        System.out.println("-------------------------开始------------------------------");
        CodeFactory codeFactory = CodeFactory.getInstance();
        //获取所有表名
        List<String> tableNames = new ArrayList<String>();

        // tableNames.addAll(codeFactoryDao.getTableName(database_name));
        tableNames.add("tbl_task_config");

        System.out.println("数据库表数量：" + tableNames.size());
        for (String tableName:tableNames) {
            //获取表相关属性
            System.out.println("表名tableName：" + tableName);
            List<InformationSchema> informationSchemas = codeFactoryDao.getColumnName(database_name, tableName);
            //创建文件
            codeFactory.CreateCode(tableName,informationSchemas);
        }
        System.out.println("-------------------------结束------------------------------");
        System.out.println("----------------耗时:"+(System.currentTimeMillis()-t)+"毫秒------------------------------");
    }

}
