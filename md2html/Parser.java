package md2html;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    private static final Map<String, Boolean> flags = new HashMap<>() {{
        put("*", false);
        put("_", false);
        put("**", false);
        put("__", false);
        put("--", false);
        put("`", false);
        put("```", false);
    }};

    private static final Map<String, String> elements = new HashMap<>() {{
        put("*", "em");
        put("_", "em");
        put("**", "strong");
        put("__", "strong");
        put("-", "-");
        put("--", "s");
        put("`", "code");
        put("```", "pre");
    }};

    private static final Map<Character, String> special = new HashMap<>() {{
        put('<', "&lt;");
        put('>', "&gt;");
        put('&', "&amp;");
    }};

    private static StringBuilder sb;
    private static StringBuilder sep;

    private static String paragraph;
    private static void isSep() {
        if (!sep.isEmpty()) {
            sb.append(sep);
            sep.setLength(0);
        }
    }

    private static int convert(char symbol, String single, String seq, int index) {
        isSep();
        boolean hasSeq = true;
        for (int i = 1; i < seq.length(); i++){
            if (!(index + i < paragraph.length()) || !(paragraph.charAt(index + i) == symbol)) {
                hasSeq = false;
                break;
            }
        }
        if (!flags.get("```") || hasSeq) {
            if (hasSeq) {
                sb.append("<");
                if (!flags.get(seq)) {
                    flags.put(seq, true);
                } else {
                    sb.append("/");
                    flags.put(seq, false);
                }
                sb.append(elements.get(seq)).append(">");
                index += seq.length() - 1;
                return index;
            }
            sb.append("<");
            if (hasNext(paragraph.substring(index + 1), symbol) && !flags.get(single)) {
                flags.put(single, true);
            } else if (flags.get(single)) {
                sb.append("/");
                flags.put(single, false);
            } else {
                sb.setCharAt(sb.length() - 1, paragraph.charAt(index));
                return index;
            }
            sb.append(elements.get(single)).append(">");
            return index;
        }
        sb.append(symbol);
        return index;
    }
    private static boolean hasNext(String string, char symbol) {
        if (string.indexOf(symbol) >= 0 && string.indexOf('\\') != string.indexOf(symbol) - 1) {
            return true;
        } else {
            string = string.substring(string.indexOf(symbol) + 1);
            while (!string.isEmpty()) {
                if (string.indexOf(symbol) >= 0) {
                    if (string.indexOf('\\') != string.indexOf(symbol) - 1) {
                        return true;
                    } else {
                        string = string.substring(string.indexOf(symbol) + 1);
                    }
                } else {
                    return false;
                }
            }
        }
        return false;
    }
    public static String parseParagraph(String string) {
        paragraph = string;
        sb = new StringBuilder();
        int count = 0;
        boolean head = false;
        sep = new StringBuilder();
        StringBuilder begin = new StringBuilder();

        while (string.charAt(count) == '#') {
            begin.append(string.charAt(count));
            count++;
        }

        if (count != 0 && string.charAt(count) == ' ') {
            sb.append("<h").append(count).append(">");
            count++;
            head = true;
        } else if (count > 0){
            sb.append("<p>");
            sb.append(begin);
        } else {
            sb.append("<p>");
        }

        for (int i = count; i < string.length(); i++) {
            boolean preCode = flags.get("```");
            if (string.charAt(i) == '*' && !preCode) {
                i = convert('*', "*", "**", i);
            } else if (string.charAt(i) == '_' && !preCode) {
                i = convert('_', "_", "__", i);
            } else if (string.charAt(i) == '-' && string.charAt(i + 1) == '-' && !preCode) {
                i = convert('-', "-", "--", i);
            } else if (string.charAt(i) == '`') {
                i = convert('`', "`", "```", i);
            } else if (special.containsKey(string.charAt(i))) {
                sb.append(special.get(string.charAt(i)));
            } else if (string.charAt(i) == '\\') {
                i++;
                sb.append(string.charAt(i));
            } else if (System.lineSeparator().contains(Character.toString(string.charAt(i)))) {
                sep.append(string.charAt(i));
            } else {
                if (sep.isEmpty()) {
                    sb.append(string.charAt(i));
                } else {
                    sb.append(sep);
                    sep.setLength(0);
                    sb.append(string.charAt(i));
                }
            }
        }
        if (head) {
            sb.append("</h").append(count - 1).append(">");
        } else {
            sb.append("</p>");
        }
        sb.append(System.lineSeparator());
        sep.setLength(0);

        return sb.toString();
    }
}