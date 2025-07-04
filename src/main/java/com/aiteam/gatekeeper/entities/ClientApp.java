package com.aiteam.gatekeeper.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client_app")
public class ClientApp extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(name = "db_host")
    private String dbHost;
    @Column(name = "db_port")
    private String dbPort;
    @Column(name = "db_name")
    private String dbName;
    @Column(name = "db_username")
    private String dbUsername;
    @Column(name = "db_password")
    private String dbPassword;
    @Column(name = "user_id")
    private int userId;
}
