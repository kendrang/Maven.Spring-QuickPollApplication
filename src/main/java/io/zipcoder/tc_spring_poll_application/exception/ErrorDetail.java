package io.zipcoder.tc_spring_poll_application.exception;

import com.sun.corba.se.pept.transport.InboundConnectionCache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetail {

    String title;
    Integer status;
    String detail;
    Long timeStamp;
    String developerMessage;

    Map<String, List<ValidationError>> errorMap = new HashMap<>();

    public ErrorDetail(){

    }

    public ErrorDetail(String title, Integer status, String detail, Long timeStamp, String developerMessage) {
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.timeStamp = timeStamp;
        this.developerMessage = developerMessage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public Map<String, List<ValidationError>> getErrors() {
        return errorMap;
    }
}
