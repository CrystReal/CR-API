package com.updg.CR_API.Utils;

import org.bukkit.util.ChatPaginator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringUtil {

    /**
     * Возвращает склоненное слово на основе цифры формата int
     *
     * @param number Значение
     * @param form1  Первая форма (1 блок)
     * @param form2  Вторая форма (2 блока)
     * @param form3  Третья форма (5 блоков)
     * @return string
     */
    public static String plural(int number, String form1, String form2, String form3) {
        int n1 = Math.abs(number) % 100;
        int n2 = number % 10;
        if (n1 > 10 && n1 < 20) return form3;
        if (n2 > 1 && n2 < 5) return form2;
        if (n2 == 1) return form1;
        return form3;
    }

    /**
     * Возвращает склоненное слово на основе цифры формата double
     *
     * @param number Значение
     * @param form1  Первая форма (1 блок)
     * @param form2  Вторая форма (2 блока)
     * @param form3  Третья форма (5 блоков)
     * @return string
     */
    public static String plural(double number, String form1, String form2, String form3) {
        double n1 = Math.abs(number) % 100;
        double n2 = number % 10;
        if (n1 > 10 && n1 < 20) return form3;
        if (n2 > 1 && n2 < 5) return form2;
        if (n2 == 1) return form1;
        return form3;
    }

    /**
     * Возвращает склоненное слово на основе цифры формата float
     *
     * @param number Значение
     * @param form1  Первая форма (1 блок)
     * @param form2  Вторая форма (2 блока)
     * @param form3  Третья форма (5 блоков)
     * @return string
     */
    public static String plural(float number, String form1, String form2, String form3) {
        float n1 = Math.abs(number) % 100;
        float n2 = number % 10;
        if (n1 > 10 && n1 < 20) return form3;
        if (n2 > 1 && n2 < 5) return form2;
        if (n2 == 1) return form1;
        return form3;
    }

    /**
     * Разбивает строку по заданому коллчиеству символов.
     *
     * @param text       Строка для разбивки
     * @param lineLength Длинна строки
     * @return Массив строк
     */
    public static List<String> wrapWords(String text, int lineLength) {
        String[] intendedLines = text.split("\\n");
        ArrayList<String> lines = new ArrayList<String>();
        for (String intendedLine : intendedLines) {
            Collections.addAll(lines, ChatPaginator.wordWrap(intendedLine, lineLength));
        }

        return lines;
    }
}
