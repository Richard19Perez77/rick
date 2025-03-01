package com.rick.webservice.rick;

 import jakarta.persistence.*;
 import java.util.Date;

 @Entity
 @Table(name = "user_request_logs")
 public class UserRequestLog {
    
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Temporal(TemporalType.TIMESTAMP)
     private Date timestamp = new Date();

     private String method;
     private String url;
     private String ipAddress;

     @Column(columnDefinition = "jsonb")
     private String headers;

     @Column(columnDefinition = "jsonb")
     private String requestBody;

     // Constructors
     public UserRequestLog() {}

     public UserRequestLog(String method, String url, String ipAddress, String headers, String requestBody) {
         this.method = method;
         this.url = url;
         this.ipAddress = ipAddress;
         this.headers = headers;
         this.requestBody = requestBody;
     }

    // add  Getters and Setters
 }
