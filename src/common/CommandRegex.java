package common;

public class CommandRegex {
    public static final String A_COMMAND_PATTERN = "^@\\S+";
    public static final String C_COMMAND_PATTERN = "^(?=.*[=;])[^=;]*(=[^=;]*)?(;[^=;]*)*[^=;]*$";
    public static final String L_COMMAND_PATTERN = "^\\(\\S+\\)$";
    public static final String COMMENT_PATTERN   = "^//.*";
}
