package com.challenge.analysis.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConfigUtil {

    private static String PROPERTIES_FILE_LIST_DELIMITER = ",";

    public static String FORM_ACTION_REGEX;
    public static List<String> USER_NAMES_LIST;
    public static List<String> PASSWORD_NAMES_LIST;
    public static int CONNECTION_TIME_OUT_IN_MILLIS;

    public static int CORE_POOL_SIZE;
    public static int MAX_POOL_SIZE;
    public static int MAX_QUEUE_CAPACITY;

    @Value("${jsoup.connection.timeout}")
    public void setConnectionTimeOutInMillis(int connectionTimeOutInMillis) {
        CONNECTION_TIME_OUT_IN_MILLIS = connectionTimeOutInMillis;
    }

    @Value("${login.form.actions.regex}")
    public void setFormActionRegex(String formActionRegex) {
        FORM_ACTION_REGEX = formActionRegex;
    }

    @Value("${login.username.names}")
    public void setUserNamesList(String userNamesList) {
        USER_NAMES_LIST = Arrays.stream(userNamesList.split(PROPERTIES_FILE_LIST_DELIMITER))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    @Value("${login.password.names}")
    public void setPasswordNamesList(String passwordNamesList) {
        PASSWORD_NAMES_LIST = Arrays.stream(passwordNamesList.split(PROPERTIES_FILE_LIST_DELIMITER))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    @Value("${async.queue.capacity}")
    public void setMaxQueueCapacity(int maxQueueCapacity) {
        MAX_QUEUE_CAPACITY = maxQueueCapacity;
    }

    @Value("${async.core.pool.size}")
    public void setCorePoolSize(int corePoolSize) {
        CORE_POOL_SIZE = corePoolSize;
    }

    @Value("${async.max.pool.size}")
    public void setMaxPoolSize(int maxPoolSize) {
        MAX_POOL_SIZE = maxPoolSize;
    }
}
