package com.akoya.codex.upgrader;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.QUESTION_MESSAGE;

/**
 * Message dialogs for error, warning, information and confirmation cases with corresponding {@link Icons}
 * Uses texts and titles from {@link Messages} class.
 */
public class MessageDialogs {

    private static boolean isMessageDialogsEnabled = true;

    public static final String[] YES_NO_OPTIONS = {"Yes", "No"};
    public static final String NO_OPTION = YES_NO_OPTIONS[1];

    /**
     * Error message dialog with default title
     */
    public static void showErrorDialog(Component parentComponent, String message) {
        showErrorDialog(parentComponent, message, Messages.ERROR);
    }

    /**
     * Error message dialog with customized title
     */
    public static void showErrorDialog(Component parentComponent, String message, String title) {
        if (isMessageDialogsEnabled) {
            JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.ERROR_MESSAGE, Icons.ERROR);
        }
    }

    /**
     * Information message dialog with default title
     */
    public static void showInformationDialog(Component parentComponent, String message) {
        if (isMessageDialogsEnabled) {
            JOptionPane.showMessageDialog(parentComponent, message, Messages.TITLE_INFORMATION, JOptionPane.INFORMATION_MESSAGE, Icons.INFO_CIRCLE);
        }
    }

    public static void showSuccessDialog(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, Messages.TITLE_INFORMATION, JOptionPane.INFORMATION_MESSAGE, Icons.SUCCESS);
    }

    /**
     * Question message dialog with default title
     */
    public static void showQuestionDialog(Component parentComponent, Object message, String title) {
        if (isMessageDialogsEnabled) {
            JOptionPane.showMessageDialog(parentComponent, message, title, QUESTION_MESSAGE, Icons.QUESTION);
        }
    }

    /**
     * Warning message dialog with customized title
     */
    public static void showWarningDialog(Component parentComponent, Object message, String title) {
        if (isMessageDialogsEnabled) {
            JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.WARNING_MESSAGE, Icons.WARNING);
        }
    }

    /**
     * Confirmation message dialog.
     *
     * @return result of a dialog
     */
    public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType) {
        return JOptionPane.showConfirmDialog(parentComponent, message, title, optionType, messageType, Icons.QUESTION);
    }

    /**
     * Confirmation message dialog
     *
     * @return result of a dialog
     */
    public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType) {
        return showConfirmDialog(parentComponent, message, title, optionType, QUESTION_MESSAGE);
    }

    /**
     * Question message dialog with with initial option
     *
     * @return result of dialog
     */
    public static int showOptionDialog(Component parentComponent, Object message, String title, Object[] options, Object initialValue) {
        return JOptionPane.showOptionDialog(parentComponent, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, Icons.QUESTION, options, initialValue);
    }

    public static void setIsMessageDialogsEnabled(boolean isMessageDialogsEnabled) {
        MessageDialogs.isMessageDialogsEnabled = isMessageDialogsEnabled;
    }
}
