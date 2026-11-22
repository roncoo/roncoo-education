/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 语言检测工具 — 精准识别用户输入语言类型，区分纯中文、纯英文、中英混合三种模式。
 *
 * <p>核心算法：通过 Unicode 字符集分块统计 + 加权判定，而非简单的字符计数，
 * 能够有效区分代码片段、URL、数字等干扰项，提供可靠的语言类型判定结果。</p>
 *
 * @author wujing
 */
public final class LanguageDetector {

    private LanguageDetector() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // ======================== 语言类型枚举 ========================

    /** 语言类型 */
    public enum LanguageType {
        /** 纯中文 — 输入仅含中文自然语言，无英文单词/句子 */
        CN,
        /** 纯英文 — 输入仅含英文自然语言，无中文字符 */
        EN,
        /** 混合语言 — 输入同时包含有意义的中文和英文内容 */
        MIXED
    }

    // ======================== Unicode 范围常量 ========================

    /** 中文字符 Unicode 范围（CJK 统一表意文字 + 扩展区 + 兼容区） */
    private static final Pattern CHINESE_CHAR = Pattern.compile(
            "[\\u4E00-\\u9FFF\\u3400-\\u4DBF\\uF900-\\uFAFF\\u2F800-\\u2FA1F]");

    /** 英文单词模式（连续字母，用于判定是否存在英文自然语言） */
    private static final Pattern ENGLISH_WORD = Pattern.compile("[a-zA-Z]{2,}");

    /** 中文标点符号 */
    private static final Pattern CHINESE_PUNCTUATION = Pattern.compile(
            "[\\u3000-\\u303F\\uFF00-\\uFFEF]");

    /** 代码/URL 干扰项排除（URL、代码片段特征） */
    private static final Pattern CODE_OR_URL = Pattern.compile(
            "(https?://[\\w./\\-?=&#%]+)" // URL
                    + "|([a-zA-Z_]\\w*\\s*\\([^)]*\\))" // 函数调用
                    + "|([<{][\\s\\S]*?[>}])" // 代码块标记
                    + "|(```[\\s\\S]*?```)", // Markdown 代码块
            Pattern.CASE_INSENSITIVE);

    /** 单独的数字、标点、空格 */
    private static final Pattern NON_LINGUISTIC = Pattern.compile("[\\d\\p{Punct}\\s]+");

    // ======================== 公共 API ========================

    /**
     * 检测输入文本的语言类型。
     *
     * @param text 用户输入文本，不可为 null 或空
     * @return 语言类型（CN / EN / MIXED）
     * @throws IllegalArgumentException 如果 text 为 null 或 blank
     */
    public static LanguageType detect(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Input text must not be null or blank");
        }

        // 1. 预处理：去除代码片段和 URL
        String cleaned = removeCodeAndUrl(text);

        // 2. 提取纯自然语言字符（去除非语言学符号）
        String natural = extractNaturalLanguage(cleaned);

        if (natural.isEmpty()) {
            // 仅有代码/URL，无法判定 → 默认 CN
            return LanguageType.CN;
        }

        // 3. 统计中英文字符
        int chineseCharCount = countChineseChars(natural);
        int englishWordCount = countEnglishWords(natural);
        int totalLength = natural.length();

        // 4. 加权判定
        return classifyLanguage(chineseCharCount, englishWordCount, totalLength);
    }

    /**
     * 检测输入文本的语言类型（带详细分析结果）。
     *
     * @param text 用户输入文本
     * @return 语言检测详情，包含类型和各项统计数据
     */
    public static LanguageDetectionResult detectWithDetail(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Input text must not be null or blank");
        }

        String cleaned = removeCodeAndUrl(text);
        String natural = extractNaturalLanguage(cleaned);

        int chineseCharCount = countChineseChars(natural);
        int englishWordCount = countEnglishWords(natural);
        int totalLength = natural.length();

        double chineseRatio = totalLength > 0 ? (double) chineseCharCount / totalLength : 0;
        LanguageType type = classifyLanguage(chineseCharCount, englishWordCount, totalLength);

        return new LanguageDetectionResult(type, chineseCharCount, englishWordCount,
                totalLength, chineseRatio);
    }

    /**
     * 检查给定文本是否符合目标语言类型的一致性要求。
     *
     * @param text     待检查的文本
     * @param expected 期望的语言类型
     * @return true 表示文本语言与期望一致
     */
    public static boolean isConsistent(String text, LanguageType expected) {
        if (text == null || text.isBlank()) {
            return true;
        }
        LanguageType actual = detect(text);
        if (actual == LanguageType.MIXED || expected == LanguageType.MIXED) {
            return actual == expected;
        }
        return actual == expected || actual == LanguageType.MIXED && expected == LanguageType.MIXED;
    }

    // ======================== 内部处理方法 ========================

    /**
     * 移除文本中的代码片段和 URL，避免干扰语言检测。
     */
    private static String removeCodeAndUrl(String text) {
        return CODE_OR_URL.matcher(text).replaceAll(" ");
    }

    /**
     * 提取纯自然语言字符，去除非语言学符号（数字、标点、空白）。
     */
    private static String extractNaturalLanguage(String text) {
        return NON_LINGUISTIC.matcher(text).replaceAll("");
    }

    /**
     * 统计中文字符数量。
     */
    private static int countChineseChars(String text) {
        int count = 0;
        Matcher m = CHINESE_CHAR.matcher(text);
        while (m.find()) {
            count++;
        }
        // 也统计中文标点
        Matcher pm = CHINESE_PUNCTUATION.matcher(text);
        while (pm.find()) {
            count++;
        }
        return count;
    }

    /**
     * 统计英文单词数量（连续 2 个及以上字母视为一个单词）。
     */
    private static int countEnglishWords(String text) {
        int count = 0;
        Matcher m = ENGLISH_WORD.matcher(text);
        while (m.find()) {
            count++;
        }
        return count;
    }

    /**
     * 加权判定语言类型。
     *
     * <p>判定逻辑：
     * <ul>
     *   <li>中文占比 ≥ 85% 且无英文单词 → CN</li>
     *   <li>英文单词 ≥ 1 且无中文字符 → EN</li>
     *   <li>中文占比 ≥ 75% 且英文单词 ≤ 1 → CN（技术术语夹杂）</li>
     *   <li>同时存在中文和英文单词 → MIXED</li>
     *   <li>其它 → 默认 EN</li>
     * </ul>
     */
    private static LanguageType classifyLanguage(int chineseCount, int englishWordCount, int totalLength) {
        double chineseRatio = totalLength > 0 ? (double) chineseCount / totalLength : 0;

        // 纯中文：中文占比极高，且无英文单词
        if (chineseRatio >= 0.85 && englishWordCount == 0) {
            return LanguageType.CN;
        }

        // 纯英文：有英文单词，且无中文字符
        if (englishWordCount >= 1 && chineseCount == 0) {
            return LanguageType.EN;
        }

        // 中文为主，极少量英文术语（如"API"、"HTTP"） → 仍视为 CN
        if (chineseRatio >= 0.75 && englishWordCount <= 1) {
            return LanguageType.CN;
        }

        // 英文为主，无中文 → EN
        if (chineseCount == 0 && englishWordCount >= 1) {
            return LanguageType.EN;
        }

        // 同时存在中文和英文 → MIXED
        if (chineseCount > 0 && englishWordCount >= 1) {
            return LanguageType.MIXED;
        }

        // 兜底：仅有英文单词 → EN
        if (englishWordCount >= 1) {
            return LanguageType.EN;
        }

        // 完全无法判定 → 默认 EN
        return LanguageType.EN;
    }

    // ======================== 结果封装类 ========================

    /**
     * 语言检测详情结果。
     */
    public static class LanguageDetectionResult {
        private final LanguageType type;
        private final int chineseCharCount;
        private final int englishWordCount;
        private final int totalNaturalLength;
        private final double chineseRatio;

        LanguageDetectionResult(LanguageType type, int chineseCharCount, int englishWordCount,
                int totalNaturalLength, double chineseRatio) {
            this.type = type;
            this.chineseCharCount = chineseCharCount;
            this.englishWordCount = englishWordCount;
            this.totalNaturalLength = totalNaturalLength;
            this.chineseRatio = chineseRatio;
        }

        public LanguageType getType() {
            return type;
        }

        public int getChineseCharCount() {
            return chineseCharCount;
        }

        public int getEnglishWordCount() {
            return englishWordCount;
        }

        public int getTotalNaturalLength() {
            return totalNaturalLength;
        }

        public double getChineseRatio() {
            return chineseRatio;
        }

        @Override
        public String toString() {
            return String.format(
                    "LanguageDetectionResult{type=%s, chineseChars=%d, englishWords=%d, totalLength=%d, chineseRatio=%.2f}",
                    type, chineseCharCount, englishWordCount, totalNaturalLength, chineseRatio);
        }
    }
}
