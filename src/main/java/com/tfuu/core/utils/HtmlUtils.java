package com.tfuu.core.utils;

public class HtmlUtils {

    private static final char[][] TEXT = new char[64][];

    /**
     * 转义文本中的HTML字符为安全的字符，以下字符被转义：
     * <ul>
     * <li>' 替换为 &amp;#039; (&amp;apos; doesn't work in HTML4)</li>
     * <li>" 替换为 &amp;quot;</li>
     * <li>&amp; 替换为 &amp;amp;</li>
     * <li>&lt; 替换为 &amp;lt;</li>
     * <li>&gt; 替换为 &amp;gt;</li>
     * </ul>
     *
     * @param text
     *            被转义的文本
     * @return 转义后的文本
     */
    public static String escape(String text) {
        return encode(text);
    }

    /**
     * Encoder
     *
     * @param text
     *            被编码的文本
     * @return 编码后的字符
     */
    private static String encode(String text) {
        int len;
        if ((text == null) || ((len = text.length()) == 0)) {
            return StringUtils.EMPTY;
        }
        StringBuilder buffer = new StringBuilder(len + (len >> 2));
        char c;
        for (int i = 0; i < len; i++) {
            c = text.charAt(i);
            if (c < 64) {
                buffer.append(TEXT[c]);
            } else {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }
}
