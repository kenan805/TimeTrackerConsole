package helpers

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class DatetimeHelper {
    companion object {
        fun secondsToTime(secondsIn: Int): LocalTime {
            var stringTime: String
            var seconds = secondsIn % 60
            var minutes = secondsIn / 60
            if (minutes >= 60) {
                var hours = minutes / 60
                minutes %= 60
                stringTime = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            } else {
                stringTime = String.format("00:%02d:%02d", minutes, seconds)
            }
            var dateTime: LocalTime = LocalTime.parse(stringTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
            return dateTime
        }
    }
}