/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.config;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件读取工具类
 *
 * @author wujing
 */
public final class SystemUtil {

    private SystemUtil() {
    }

    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("system.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getProperty(String keyName) {
        return properties.getProperty(keyName, "").trim();
    }

    public static final String PERIOD_VIDEO_PATH = getProperty("period_video_path");
    public static final String PIC_PATH = getProperty("pic_path");
    public static final String DOC_STORAGE_PATH = getProperty("doc_storage_path");
    public static final String PIC_STORAGE_PATH = getProperty("pic_storage_path");

    public static final String TEST_COURSE = getProperty("test_course");

    public static final String POLYV_UPLOADVIDEO = getProperty("polyv_uploadVideo");
    public static final String POLYV_DELETEVIDEO = getProperty("polyv_deleteVideo");
    public static final String POLYV_QUESTION = getProperty("polyv_question");
    public static final String POLYV_GETTOKEN = getProperty("polyv_getToken");
}
