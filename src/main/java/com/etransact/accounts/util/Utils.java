package com.etransact.accounts.util;


import com.etransact.accounts.dto.response.ApiResponse;
import com.etransact.accounts.dto.response.PagedContent;
import com.etransact.accounts.enums.ResponseMessage;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import java.util.*;

@SuppressWarnings({"rawtypes", "unused"})
public class Utils {

    private static final Logger log = LoggerFactory.getLogger(Utils.class);


    public static String formatAmount(Double amount) {
        return String.format("%,.2f", amount);
    }

    public static <T> ApiResponse<T> wrapInApiResponse(T data, String resquestId) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(ResponseMessage.SUCCESS.getCode());
        response.setMessage(ResponseMessage.SUCCESS.toString());
        response.setRequestId(resquestId);
        response.setData(data);
        return response;
    }

    @SuppressWarnings("unchecked")
    public static <T, W> ApiResponse<PagedContent<W>> wrapInPagedApiResponse(Page page, List<W> data, String sessionId) {
        ApiResponse<PagedContent<W>> response = new ApiResponse<>();
        response.setCode(ResponseMessage.SUCCESS.getCode());
        response.setMessage(ResponseMessage.SUCCESS.toString());
        response.setRequestId(sessionId);
        response.setData(new PagedContent(page, data));
        return response;
    }

    public static HashMap<String, String> getDuplicateConstraintMessage(String cause) {
        int beginIndex = cause.lastIndexOf("Detail:");
        String msg = cause.substring(beginIndex);
        String[] msgHalves = msg.split("=");
        String firstHalf = msgHalves[0];
        String secondHalf = msgHalves[1];
        String key = firstHalf.substring(firstHalf.indexOf("(") + 1, firstHalf.indexOf(")"));
        if (key.contains("_")) {
            key = String.join(" ", key.split("_"));
        }

        String value = secondHalf.substring(secondHalf.indexOf("("), secondHalf.indexOf(")") + 1);
        HashMap<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("value", value);
        return map;
    }

    public static String getSQLConstraintMessage(String cause) {
        int beginIndex = cause.lastIndexOf("Detail:");
        String msg = cause.substring(beginIndex);
        String[] msgHalves = msg.split("=");
        String firstHalf = msgHalves[0];
        String key = firstHalf.substring(firstHalf.indexOf("(") + 1, firstHalf.indexOf(")"));
        String userMessage;
        if (key.contains(",")) {
            List<String> clean = new ArrayList<>();
            Arrays.stream(key.split(",")).forEach((s) -> {
                clean.add(String.join(" ", s.split("_")));
            });
            clean.set(clean.size() - 1, " and" + (String)clean.get(clean.size() - 1));
            userMessage = String.join(",", clean);
            userMessage = userMessage.replaceFirst(", and", " and");
        } else {
            String join = String.join(" ", key.split("_"));
            userMessage = join.substring(0, 1).toUpperCase() + join.substring(1);
        }

        return userMessage;
    }

}
