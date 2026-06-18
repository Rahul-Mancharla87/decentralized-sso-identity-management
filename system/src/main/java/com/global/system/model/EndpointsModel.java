package com.global.system.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EndpointsModel {

   @Id
   @GeneratedValue
    int id;
    String  endPointName;
    String endpointUrl;
    int status;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEndPointName() {
        return endPointName;
    }
    public void setEndPointName(String endPointName) {
        this.endPointName = endPointName;
    }
    public String getEndpointUrl() {
        return endpointUrl;
    }
    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }


    



    
}
