package com.des.hackathon.tenant.beans;

public class RealmsInfo {

    private String id;
    private String realm;
    private Boolean enabled;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "RealmsInfo{" +
                "id='" + id + '\'' +
                ", realm='" + realm + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
