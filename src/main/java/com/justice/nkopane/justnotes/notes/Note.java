package com.justice.nkopane.justnotes.notes;

import com.justice.nkopane.justnotes.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Note Title Cannot be blank")
    @Size(message = "Title must be min 3 and max 100", min = 3, max = 200)
    private String title;
    @NotBlank(message = "Note Content Cannot be blank")
    @Size(message = "Title must be min 3 and max 1500", min = 3, max = 1500)
    private String content;
    @CreatedDate
    private Date dateCreated;
    @LastModifiedDate
    private Date dateUpdated;
}
