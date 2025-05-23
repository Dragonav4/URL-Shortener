package org.example.s31722tpo10.DataLayer.Models;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.s31722tpo10.Constraints.PasswordComplex;
import org.example.s31722tpo10.Constraints.UniqueUrl;
import org.hibernate.validator.constraints.URL;

public class LinkDTO {
    private String Id;
    @NotBlank(message = "{link.name.blank}")
    @Size(min=5,max=20, message = "{link.name.size}")
    private String name;
    @NotBlank(message = "{link.url.blank}")
    @URL(protocol = "https", message = "{link.url.https}")
    @URL(message = "{link.url.invalid}") //todo нужно ли?
    @UniqueUrl
    private String targetUrl;
    private String redirectUrl;
    private long visitsCount;
    @PasswordComplex()
    private String password;


    public LinkDTO(String id, String name, String targetUrl, String redirectUrl, long visitsCount, String password) {
        Id = id;
        this.name = name;
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
        return name;
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
        this.name = name;
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