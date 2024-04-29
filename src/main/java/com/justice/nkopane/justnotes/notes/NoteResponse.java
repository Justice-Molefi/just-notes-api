package com.justice.nkopane.justnotes.notes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
public record NoteResponse(String title, String content, Date dateCreated, Date dateUpdated) {
}
