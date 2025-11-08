package com.example.erp.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "email_details")
@Entity
@Getter
@Setter
public class EmailDetail extends DBCommon{


    private String host;

    private String username;

    private String password;

    private int port;

   private boolean auth=Boolean.TRUE;

   private boolean starttls=Boolean.TRUE;

}
