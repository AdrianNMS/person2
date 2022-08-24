package com.bank.person2.models.entities;

import com.bank.person2.models.enums.PersonGenre;
import com.bank.person2.models.utils.Audit;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "person")
public class Person extends Audit
{
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private PersonGenre genre;
    private String documentId;
    private String phoneNumber;
    private String email;


}
