package com.ming.happyclub.generate.code.mbg;

import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @version 1.0
 * @description: TODO
 * @date 2023/11/21
 * @Replenishment: TODO
 */
public class BaseGenerate {
    /**
     * 执行数据库脚本
     */
    protected void initDataSource(DataSourceConfig dataSourceConfig) throws SQLException, IOException {
        Connection conn = dataSourceConfig.getConn();
        InputStream inputStream = Files.newInputStream(new File("/sql/init.sql").toPath());
        ScriptRunner scriptRunner = new ScriptRunner(conn);
        scriptRunner.setAutoCommit(true);
        scriptRunner.runScript(new InputStreamReader(inputStream));
        conn.close();
    }

    /**
     * 获取项目绝对路径
     */
    protected static String absPath(Class<?> cls) {
        ProtectionDomain protectionDomain = cls.getProtectionDomain();
        CodeSource codeSource = protectionDomain.getCodeSource();
        URL location = codeSource.getLocation();
        String path = location.getFile();
        String replace = path.replace("/target/classes/", "/src/main/java/");
        return replace.substring(1).replace("/","//");
    }

    /**
     * 全局配置
     */
    protected static GlobalConfig.Builder globalConfig() {
        return new GlobalConfig.Builder()
                .author("ming")
                .enableSwagger()
                .dateType(DateType.TIME_PACK)
                .disableOpenDir()
                .outputDir(absPath(BaseGenerate.class))
                .commentDate("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 包配置
     */
    protected static PackageConfig.Builder packageConfig() {
        return new PackageConfig.Builder()
                .parent(FastAutoGenerator.PARENT_PATH)
                .entity("mbg.domain.entity")
                .service("service")
                .serviceImpl("service.impl")
                .mapper("mbg.mapper")
                .xml("mbg.mapper.xml")
                .controller("controller");
    }

    /**
     * 模板配置
     */
    protected static TemplateConfig.Builder templateConfig() {
        return new TemplateConfig.Builder()
                .disable(TemplateType.ENTITY)
                .entity("/templates/entity.java")
                .service("/templates/service.java")
                .serviceImpl("/templates/serviceImpl.java")
                .mapper("/templates/mapper.java")
                .mapperXml("/templates/mapper.xml")
                .controller("/templates/controller.java");
    }

    /**
     * 策略配置
     */
    protected static StrategyConfig.Builder strategyConfig() {
        return new StrategyConfig.Builder();
    }

    /**
     * 注入配置
     */
    protected static InjectionConfig.Builder injectionConfig() {
        // 测试自定义输出文件之前注入操作，该操作再执行生成代码前 debug 查看
        return new InjectionConfig.Builder().beforeOutputFile((tableInfo, objectMap) -> {
            System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
        });
    }
}
