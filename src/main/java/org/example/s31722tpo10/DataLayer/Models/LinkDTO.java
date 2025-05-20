package org.example.s31722tpo10.DataLayer.Models;


public class LinkDTO {
    private String Id;
    private String Name;
    private String targetUrl;
    private String redirectUrl;
    private long visitsCount;
    private String password;


    public LinkDTO(String id, String name, String targetUrl, String redirectUrl, long visitsCount, String password) {
        Id = id;
        Name = name;
        this.targetUrl = targetUrl;
        this.redirectUrl = redirectUrl;
        this.visitsCount = visitsCount;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LinkDTO() {

    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public long getVisitsCount() {
        return visitsCount;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void setVisitsCount(long visitsCount) {
        this.visitsCount = visitsCount;
    }


}