package sophiatech.Restaurant;

import java.time.LocalTime;

public class Hours {

    private LocalTime hour_start;
    private LocalTime hour_end;

    public Hours(LocalTime start, LocalTime end){
        this.hour_start = start;
        this.hour_end = end;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Hours hours_to_check = (Hours) obj;
        return hours_to_check.hour_end.equals(hour_end) && hours_to_check.hour_start.equals(hour_start);
    }

    public LocalTime getHour_end() {
        return hour_end;
    }

    public LocalTime getHour_start() {
        return hour_start;
    }

    public void setHour_end(LocalTime hour_end) {
        this.hour_end = hour_end;
    }

    public void setHour_start(LocalTime hour_start) {
        this.hour_start = hour_start;
    }
}
