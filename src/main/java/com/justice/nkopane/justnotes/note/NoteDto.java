package com.justice.nkopane.justnotes.note;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Builder
@Data
public class NoteDto {
    private UUID id;
    private String title;
    private String content;
    private Date dateCreated;
    private Date dateUpdated;

}
