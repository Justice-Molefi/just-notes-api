package com.justice.nkopane.justnotes.note;

import com.justice.nkopane.justnotes.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Note{
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userId;
    private String title;
    private String content;
    @CreatedDate
    private Date dateCreated;
    @LastModifiedDate
    private Date dateUpdated;
}
