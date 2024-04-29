package com.justice.nkopane.justnotes.notes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NoteDto(
        @NotBlank(message = "Note Title Cannot be blank")
        @Size(message = "Title must be min 3 and max 100", min = 3, max = 200)
        String title,
        @NotBlank(message = "Note Content Cannot be blank")
        @Size(message = "Title must be min 3 and max 1500", min = 3, max = 1500)
        String content) {
}
