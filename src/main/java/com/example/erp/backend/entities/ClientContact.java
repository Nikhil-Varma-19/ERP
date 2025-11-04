package com.example.erp.backend.entities;

import com.example.erp.backend.enums.ClientContactType;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Table(name = "client_contacts")
@Entity
public class ClientContact extends  DBCommon{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "contact_type")
    private ClientContactType contactType;

    private String email;

    private String contact;
}
