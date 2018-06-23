package com.challenge.analysis.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConfigUtil {

    public static String FORM_ACTION_REGEX;
    public static List<String> USER_NAMES_LIST;
    public static List<String> PASSWORD_NAMES_LIST;

    @Value("${login.form.actions.regex}")
    public void setFormActionRegex(String formActionRegex) {
        FORM_ACTION_REGEX = formActionRegex;
    }

    @Value("${login.username.names}")
    public void setUserNamesList(String userNamesList) {
        USER_NAMES_LIST = Arrays.stream(userNamesList.split(HTMLAnalysisConstants.PROPERTIES_FILE_LIST_DELIMITER))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    @Value("${login.password.names}")
    public void setPasswordNamesList(String passwordNamesList) {
        PASSWORD_NAMES_LIST = Arrays.stream(passwordNamesList.split(HTMLAnalysisConstants.PROPERTIES_FILE_LIST_DELIMITER))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
