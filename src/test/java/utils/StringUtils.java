package utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static String removeBrackets(String text) {
        text = text.replace("(", "");
        text = text.replace(")", "");
        text = text.replace(".", "");
        return text;
    }

    public static boolean isSorted(List list, boolean isDescOrder) {
        if (isDescOrder)
            list.stream().sorted().toList().reversed();

        List copyList = new ArrayList(list);
        copyList.stream().sorted().toList();
        return copyList.equals(list);
    }
}
