package org.example.s31722tpo10.DataLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "links")
public class Link {

    @Id
    @Column(length = 15)
    private String Id;

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false)
    private String targetURL;

    @Column(nullable = false)
    private int visitsCount = 0;

    private String passwordHash;

    public boolean hasPassword() {
        return passwordHash != null;
    }

    public Link() {
    }

    public Link(String id, String name, String targetURL, int visitsCount, String passwordHash) {
        Id = id;
        Name = name;
        this.targetURL = targetURL;
        this.visitsCount = visitsCount;
        this.passwordHash = passwordHash;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public int getVisitsCount() {
        return visitsCount;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }

    public void setVisitsCount(int visitsCount) {
        this.visitsCount = visitsCount;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
