package com.gxc.sldz.Utils;

import cn.hutool.extra.emoji.EmojiUtil;

public class emojiUtil {
    /**
     * 判断是否包含emoji表情
     *
     * @param str
     * @return
     */
    public static boolean exist(String str) {
        return EmojiUtil.containsEmoji(str);
    }

    /**
     * 移除字符串中的emoji表情
     *
     * @param str
     * @return
     */
    public static String remove(String str) {
        return EmojiUtil.removeAllEmojis(str);
    }

}
