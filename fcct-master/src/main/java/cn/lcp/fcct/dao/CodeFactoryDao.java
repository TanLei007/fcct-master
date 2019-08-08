package cn.lcp.fcct.dao;

import cn.lcp.fcct.po.InformationSchema;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CodeFactoryDao {
    @Select("select table_name from information_schema.tables where table_schema=#{database_name} and table_type='base table'")
    List<String> getTableName(@Param("database_name") String database_name);

    @Select("select column_name,data_type,column_comment from information_schema.columns where table_schema=#{database_name} and table_name=#{table_name}")
    List<InformationSchema> getColumnName(@Param("database_name") String database_name ,@Param("table_name") String table_name);
}
