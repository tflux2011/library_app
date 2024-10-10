package edu.miu;

import java.awt.Color;
import java.awt.Font;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JComponent;


public class Util {
    public static final Color DARK_BLUE = Color.BLUE.darker();
    public static final Color ERROR_MESSAGE_COLOR = Color.RED.darker();
    public static final Color INFO_MESSAGE_COLOR = new Color(24, 98, 19);
    public static final Color LINK_AVAILABLE = DARK_BLUE;
    public static final Color LINK_NOT_AVAILABLE = Color.gray;

    public static Font makeSmallFont(Font f) {
        return new Font(f.getName(), f.getStyle(), f.getSize() - 2);
    }

    public static void adjustLabelFont(JComponent label, Color color, boolean bigger) {
        if (bigger) {
            Font f = new Font(label.getFont().getName(), label.getFont().getStyle(), label.getFont().getSize() + 2);
            label.setFont(f);
        } else {
            Font f = new Font(label.getFont().getName(), label.getFont().getStyle(), label.getFont().getSize() - 2);
            label.setFont(f);
        }
        label.setForeground(color);
    }

    public static List<String> numericSort(List<String> list) {
        Collections.sort(list, new NumericSortComparator());
        return list;
    }

    public static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public static User findUser(List<User> list, User user) {
        for (User u : list) {
            if (!u.equals(user)) continue;
            return u;
        }
        return null;
    }

    static class NumericSortComparator
            implements Comparator<String> {
        NumericSortComparator() {
        }

        @Override
        public int compare(String s, String t) {
            int tInt;
            if (!Util.isNumeric(s) || !Util.isNumeric(t)) {
                throw new IllegalArgumentException("Input list has non-numeric characters");
            }
            int sInt = Integer.parseInt(s);
            if (sInt < (tInt = Integer.parseInt(t))) {
                return -1;
            }
            if (sInt == tInt) {
                return 0;
            }
            return 1;
        }
    }
}
