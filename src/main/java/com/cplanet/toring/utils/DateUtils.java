package com.cplanet.toring.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateUtils {

    static public String toStringLocalDateTimeWithFormat(LocalDateTime time, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return Optional.ofNullable(time).map(formatter::format).orElse("");
    }

    static public String toStringYYYYMMDDHHMMSS(LocalDateTime time) {

        return toStringLocalDateTimeWithFormat(time, "yyyyMMddHHmmss");
    }


}
