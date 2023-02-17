package com.example.hw_28_spring_boot_web.utilities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Logger {

    public static String logResponse(String response) {
        log.info(response);
        return response;
    }

    public static void logInvokedMethod() {
        log.info("METHOD {{}} WAS CALLED", Thread.currentThread().getStackTrace()[2].getMethodName());
    }
}
