package com.pro.base.config;

import com.alibaba.fastjson.parser.ParserConfig;

import java.util.Arrays;
import java.util.List;

/**
 * FastJson的autoType功能配置
 * @see <a href="https://github.com/alibaba/fastjson/wiki/enable_autotype">添加autoType白名单</a>
 */
public class FastJsonAutoTypeConfig {
  private final ParserConfig parserConfig;

  /**
   * 配置FastJson的autoType
   * @param packageList 白名单
   */
  public FastJsonAutoTypeConfig(final List<String> packageList) {
    this(false, packageList);
  }

  /**
   * 配置FastJson的autoType
   * @param packageList 白名单
   */
  public FastJsonAutoTypeConfig(final String... packageList) {
    this(false, Arrays.asList(packageList));
  }

  /**
   * 配置FastJson的autoType
   * @param autoTypeSupport 是否开启autoTypeSupport支持，默认false
   * @param packageList 如果autoTypeSupport=true，为黑名单；autoTypeSupport=false，白名单
   */
  public FastJsonAutoTypeConfig(final boolean autoTypeSupport, final String... packageList) {
    this(autoTypeSupport, Arrays.asList(packageList));
  }

  /**
   * 配置FastJson的autoType
   * @param autoTypeSupport 是否开启autoTypeSupport支持，默认false
   * @param packageList 如果autoTypeSupport=true，为黑名单；autoTypeSupport=false，白名单
   */
  public FastJsonAutoTypeConfig(final boolean autoTypeSupport, final List<String> packageList) {
    int len = packageList.size();
    parserConfig = ParserConfig.getGlobalInstance();
    parserConfig.setAutoTypeSupport(autoTypeSupport);
    if (autoTypeSupport) {
      //黑名单
      for (int i = 0; i < len; i++) {
        parserConfig.addDeny(packageList.get(i));
      }
    } else {
      //白名单
      for (int i = 0; i < len; i++) {
        parserConfig.addAccept(packageList.get(i));
      }
    }
  }

  public ParserConfig getParserConfig() {
    return parserConfig;
  }
}
