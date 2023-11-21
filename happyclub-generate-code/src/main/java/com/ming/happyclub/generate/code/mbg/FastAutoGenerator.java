package com.ming.happyclub.generate.code.mbg;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.ming.happyclub.generate.code.mbg.enums.MBGEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import static com.ming.happyclub.generate.code.mbg.BaseGenerate.*;
import static com.ming.happyclub.generate.code.mbg.base.BaseColumn.*;

/**
 * @version 1.0
 * @description: TODO
 * @date 2023/11/21
 * @Replenishment: TODO
 */
public class FastAutoGenerator {
    /**
     *
     * 数据库类型转换为java类型
     * @param columnType 数据库类型
     * @return java类型
     */
    private static String columnTypeToJavaType(String columnType) {
        if (StrUtil.isNotEmpty(columnType)) {
            if (Arrays.asList(COLUMN_TYPE_STR).contains(columnType)) {
                return TYPE_STRING;
            }
            if (Arrays.asList(COLUMN_TYPE_TEXT).contains(columnType)) {
                return TYPE_STRING;
            }
            if (Arrays.asList(COLUMN_TYPE_TIME).contains(columnType)) {
                return TYPE_DATE;
            }
            if (Arrays.asList(COLUMN_TYPE_NUMBER).contains(columnType)) {
                return TYPE_INTEGER;
            }
            if (Arrays.asList(COLUMN_TYPE_BIGINT).contains(columnType)) {
                return TYPE_LONG;
            }
            if (Arrays.asList(COLUMN_TYPE_FLOAT).contains(columnType)) {
                return TYPE_DOUBLE;
            }
            if (Arrays.asList(COLUMN_TYPE_DOUBLE).contains(columnType)) {
                return TYPE_DOUBLE;
            }
            if (Arrays.asList(COLUMN_TYPE_DECIMAL).contains(columnType)) {
                return TYPE_BIGDECIMAL;
            }
        }
        return null;
    }


    /**
     * 设置属性，渲染代码模板
     * @param tableName
     * @param dataSourceConfig
     * @param map
     * @param tablePrefixArray
     */
    private static void setAttr(String packageName, String moduleName,String tableName, DataSourceConfig dataSourceConfig, Map<String, Object> map, String[] tablePrefixArray) {
        List<Map<String, Object>> columns = new ArrayList<>();

        // 获取表信息sql
        String tableSql = "select table_name , table_comment from information_schema.tables " +
                "where table_schema = (select database()) and table_name = '" + tableName + "'";
        // 获取字段信息sql
        String columnSql = "select column_name , data_type , column_comment from information_schema.columns " +
                "where table_name = '" + tableName + "' and table_schema = (select database()) and column_name != 'id_new'";
        // 利用现有的dataSourceConfig来获取数据库连接，查询表字段及备注等信息
        try (Connection conn = dataSourceConfig.getConn();
             PreparedStatement psTable = conn.prepareStatement(tableSql);
             ResultSet rsTable = psTable.executeQuery();
             PreparedStatement ps = conn.prepareStatement(columnSql);
             ResultSet rs = ps.executeQuery();
        ) {
            if (rsTable.next()) {
                String table_name = rsTable.getString(MBGEnum.TABLE_NAME.getKey());
                map.put(MBGEnum.TABLENAME.getKey(), table_name);
                map.put(MBGEnum.COMMENT.getKey(), rsTable.getString(MBGEnum.TABLE_NAME.getKey()));

                // 过滤表前缀
                if (tablePrefixArray.length > 0) {
                    for (String s : tablePrefixArray) {
                        table_name = table_name.replaceAll(s, "");
                    }
                }
                // 类名 大驼峰
                map.put(MBGEnum.UPPERCLASSNAME.getKey(), StrUtil.upperFirst(StrUtil.toCamelCase(table_name)));
                // 对象名 小驼峰
                map.put(MBGEnum.LOWERCLASSNAME.getKey(), StrUtil.toCamelCase(table_name));
            }
            while (rs.next()) {
                Map<String, Object> columnMap = new HashMap<>();
                // 列名字、数据类型、java属性名字、java属性类型、备注
                columnMap.put(MBGEnum.COLUMN_NAME.getKey(), rs.getString(MBGEnum.COLUMN_NAME.getKey()));
                columnMap.put(MBGEnum.DATA_TYPE.getKey(), rs.getString(MBGEnum.DATA_TYPE.getKey()));
                columnMap.put(MBGEnum.JAVALOWERATTRNAME.getKey(), StrUtil.toCamelCase(rs.getString(MBGEnum.COLUMN_NAME.getKey())));
                columnMap.put(MBGEnum.JAVAATTRTYPE.getKey(), columnTypeToJavaType(rs.getString(MBGEnum.DATA_TYPE.getKey())));
                columnMap.put(MBGEnum.COLUMN_COMMENT.getKey(), rs.getString(MBGEnum.COLUMN_COMMENT.getKey()));
                columns.add(columnMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put(MBGEnum.COLUMNS.getKey(), columns);
        map.put(MBGEnum.DATETIME.getKey(), DateUtil.now());
        map.put(MBGEnum.PACKAGENAME.getKey(), packageName);
        map.put(MBGEnum.MODULENAME.getKey(), moduleName);
    }


    /**
     * 要生成的表名
     */
    public static final String TABLE_NAME = "tf_mc_notify_into_database";

    /**
     * 父级路径
     */
    public static final String PACKAGE_NAME = "com.ming.scaffold";

    /**
     * 模块名称
     */
    public static final String MODULE_NAME = "mbg";

    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/scaffold?serverTimezone=Asia/Shanghai", "root", "123456")
            .schema("scaffold")
            .keyWordsHandler(new MySqlKeyWordsHandler())
            .build();

    /**
     * @description: 开始生成代码
     * @param: packageName 包名
     * @param: moduleName 模块名
     * @param: tableName 表名
     * @param: dataSourceConfig 数据库配置
     * @date: 2023/11/21
     */
    public static void run(String packageName,
                           String moduleName,
                           String tableName,
                           DataSourceConfig dataSourceConfig,
                           Class<?> cls) {
        AutoGenerator generator = new AutoGenerator(dataSourceConfig);

        StrategyConfig strategyConfig = strategyConfig()
                .addInclude(tableName)
                .build();

        strategyConfig
                .entityBuilder().formatFileName("%sEntity")
                .addSuperEntityColumns("id", "create_by", "create_time", "update_by", "update_time", "delete_flag") // 基于数据库字段，忽略某个字段，【好像不管用】
                .mapperBuilder().formatMapperFileName("%sMapper").formatXmlFileName("%sMapper")
                .serviceBuilder().formatServiceFileName("I%sService")
                .controllerBuilder().formatFileName("%sController");


        generator.strategy(strategyConfig);
        generator.global(globalConfig(cls).build());
        generator.packageInfo(packageConfig(packageName).build());
        generator.template(templateConfig().build());


        // 6 设置自定义属性
        Map<String, Object> map = new HashMap<>();

        setAttr(packageName,moduleName,tableName, dataSourceConfig, map, new String[] {""});

        generator.injection(injectionConfig().customMap(map).build());

        generator.execute();

    }

    public static void main(String[] args) {
        run(PACKAGE_NAME,MODULE_NAME,TABLE_NAME,DATA_SOURCE_CONFIG, FastAutoGenerator.class);
    }
}
