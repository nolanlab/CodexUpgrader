package com.akoya.codex.upgrader;

import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Icons
 */
public final class Icons {
    private static final Logger LOGGER = LoggerFactory.getLogger(Icons.class);

    static {
        IconFontSwing.register(FontAwesome.getIconFont());
    }

    private static final int ICON_SIZE = 15;

    private static ImageIcon getCodexLogo() {
        try {
            URL iconResource = Icons.class.getClassLoader().getResource("CODEXlogo.png");
            ImageIcon logo = new ImageIcon(ImageIO.read(iconResource.openStream()));
            return logo;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new IllegalStateException("CODEX logotype/icon wasn't loaded!");
        }
    }

    private static ImageIcon getScaledCodexLogo() {
        Image image = CODEX_LOGO.getImage();
        Image scaledImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public static final ImageIcon CODEX_LOGO = getCodexLogo();
    public static final Icon TRASH = IconFontSwing.buildIcon(FontAwesome.TRASH, ICON_SIZE);
    public static final Icon PLUS = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, ICON_SIZE);
    public static final Icon CREATE_CHILD_PLOT = IconFontSwing.buildIcon(FontAwesome.AREA_CHART, ICON_SIZE);
    public static final Icon SORT_ASC = IconFontSwing.buildIcon(FontAwesome.SORT_ALPHA_ASC, ICON_SIZE, Color.black);
    public static final Icon COMBINE = IconFontSwing.buildIcon(FontAwesome.LINK, ICON_SIZE);
    public static final Icon TOGGLE_ON = IconFontSwing.buildIcon(FontAwesome.TOGGLE_ON, ICON_SIZE);
    public static final Icon TOGGLE_OFF = IconFontSwing.buildIcon(FontAwesome.TOGGLE_OFF, ICON_SIZE);
    public static final Icon CLUSTER_WITH_DND = IconFontSwing.buildIcon(FontAwesome.MAGIC, ICON_SIZE);
    public static final Icon CLUSTER_X_SHIFT = IconFontSwing.buildIcon(FontAwesome.CUBES, ICON_SIZE);
    public static final Icon SETTINGS = IconFontSwing.buildIcon(FontAwesome.COG, ICON_SIZE);
    public static final Icon SETTINGS_2 = IconFontSwing.buildIcon(FontAwesome.COG, 2 * ICON_SIZE);
    public static final Icon AUTO_ADJUST = IconFontSwing.buildIcon(FontAwesome.ADJUST, ICON_SIZE);
    public static final Icon RESET_TO_MINMAX = IconFontSwing.buildIcon(FontAwesome.ARROWS_H, ICON_SIZE);
    public static final Icon OK = IconFontSwing.buildIcon(FontAwesome.CHECK, ICON_SIZE);
    public static final Icon CANCEL = IconFontSwing.buildIcon(FontAwesome.TIMES, ICON_SIZE);
    public static final Icon REFRESH = IconFontSwing.buildIcon(FontAwesome.REFRESH, ICON_SIZE);

    //history panel
    public static final Icon CARET_UP = IconFontSwing.buildIcon(FontAwesome.CARET_UP, ICON_SIZE);
    public static final Icon NO_COMMENTS = IconFontSwing.buildIcon(FontAwesome.COMMENTS_O, ICON_SIZE);

    public static final Icon CHART = IconFontSwing.buildIcon(FontAwesome.BAR_CHART, ICON_SIZE);
    public static final Icon COLUMNS = IconFontSwing.buildIcon(FontAwesome.COLUMNS, ICON_SIZE);
    public static final Icon TABLE = IconFontSwing.buildIcon(FontAwesome.TABLE, ICON_SIZE);
    public static final Icon FOLDER_OPEN = IconFontSwing.buildIcon(FontAwesome.FOLDER_OPEN, ICON_SIZE);

    //for exporting
    public static final Icon DOWNLOAD = IconFontSwing.buildIcon(FontAwesome.DOWNLOAD, ICON_SIZE);
    public static final Icon CLIPBOARD = IconFontSwing.buildIcon(FontAwesome.CLIPBOARD, ICON_SIZE);

    //for move up and down icons
    public static final Icon ARROW_UP = IconFontSwing.buildIcon(FontAwesome.ARROW_CIRCLE_UP, ICON_SIZE);
    public static final Icon ARROW_DOWN = IconFontSwing.buildIcon(FontAwesome.ARROW_CIRCLE_DOWN, ICON_SIZE);

    //for activities
    public static final Icon STOP_ACTIVITY = IconFontSwing.buildIcon(FontAwesome.STOP_CIRCLE, ICON_SIZE);
    public static final Icon REMOVE_ACTIVITY = IconFontSwing.buildIcon(FontAwesome.TRASH, ICON_SIZE);

    public static final Icon SCALE = IconFontSwing.buildIcon(FontAwesome.ARROWS_H, ICON_SIZE);
    public static final Icon RESET_SCALE = IconFontSwing.buildIcon(FontAwesome.REFRESH, ICON_SIZE);
    public static final Icon RESET_TITLE = IconFontSwing.buildIcon(FontAwesome.UNDO, ICON_SIZE);


    //for dialog message
    public static final Icon INFO_CIRCLE = IconFontSwing.buildIcon(FontAwesome.INFO_CIRCLE, 2 * ICON_SIZE);
    public static final Icon ERROR = IconFontSwing.buildIcon(FontAwesome.BAN, 2 * ICON_SIZE, Color.red);
    public static final Icon WARNING = IconFontSwing.buildIcon(FontAwesome.EXCLAMATION_TRIANGLE, 2 * ICON_SIZE);
    public static final Icon QUESTION = IconFontSwing.buildIcon(FontAwesome.QUESTION_CIRCLE, 2 * ICON_SIZE);
    public static final Icon SUCCESS = IconFontSwing.buildIcon(FontAwesome.CHECK_CIRCLE, 2 * ICON_SIZE, Color.GREEN.darker());

    public final static Icon ASCENDING = UIManager.getIcon("Table.ascendingSortIcon");
    public final static Icon DESCENDING = UIManager.getIcon("Table.descendingSortIcon");
}
