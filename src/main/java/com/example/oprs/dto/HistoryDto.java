package com.example.oprs.dto;

import com.example.oprs.enums.Event;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HistoryDto {
    private Event event;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    private Long userId;
    private Long requestInfoId;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRequestInfoId() {
        return requestInfoId;
    }

    public void setRequestInfoId(Long requestInfoId) {
        this.requestInfoId = requestInfoId;
    }
}
