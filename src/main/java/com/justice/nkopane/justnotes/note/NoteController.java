package com.justice.nkopane.justnotes.note;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<NoteDto>> getNotes(){
        List<NoteDto> notes = noteService.getNotes();
        if(notes != null)
            return ResponseEntity.status(HttpStatus.OK).body(notes);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping
    public ResponseEntity<NoteDto> createNote(@Valid @RequestBody NoteRequest noteRequest){
        noteService.createNote(noteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateNote(@RequestBody NoteDto noteDto){
        noteService.updateNote(noteDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable String id){
        UUID uuid = UUID.fromString(id);
        noteService.deleteNote(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
