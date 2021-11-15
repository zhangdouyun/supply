package com.yearbooks.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className CodeGenerator
 * @description TODO
 * @since 2021-10-26 22:36
 */

public class CodeGenerator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + ":");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "!");
    }

    /*主程序入口*/
    public static void main(String[] args) {
        //1.代码生成器；
        AutoGenerator mpg = new AutoGenerator();
        //2.全局配置；
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/supply-admin/src/main/java");
        //3.作者；
        gc.setAuthor("zhangDouYun");
        gc.setDateType(DateType.TIME_PACK);
        //4.打开输出目录；
        gc.setOpen(false);
        //5.xml开启BaseColumnList;
        gc.setBaseColumnList(true);
        //5.1 xml开启BaseResultMap;
        gc.setBaseResultMap(true);
        //6.实体属性swagger2注解；
        gc.setSwagger2(true);

        mpg.setGlobalConfig(gc);

        //7.数据源配置；
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/supply?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia" +
                "/Shanghai");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("090630");
        mpg.setDataSource(dsc);

        //8.包配置；
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.yearbooks.supply")
                .setEntity("pojo")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl")
                .setController("controller");
        mpg.setPackageInfo(pc);

        //9.自定义配置；
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing;
            }
        };

        //10.1 如果模板类型是freemarker；
        String templatePath = "/templates/mapper.xml.ftl";
        //10.2 如果模板类型是velocity；
        /* String templatePath_1 = "/templates/mapper.xml.ftl";*/

        // 11.自定义输出配置；
        List<FileOutConfig> focList = new ArrayList<>();
        // 12.自定义配置被优先输出；
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/supply-admin/src/main/resources/mapper/" + tableInfo.getEntityName() + "mapper"
                        + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        //12.配置模板；
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        //13.配置策略；
        StrategyConfig strategy = new StrategyConfig();
        //13.1数据库表映射到实体的命名策略；
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //13.2 数据库表字段映射到实体的命名策略；
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //13.3 lombok模型；
        strategy.setEntityLombokModel(true);
        //13.4 生成@controller控制器；
        strategy.setRestControllerStyle(false);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        //14 表前缀；
        strategy.setTablePrefix("t_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
