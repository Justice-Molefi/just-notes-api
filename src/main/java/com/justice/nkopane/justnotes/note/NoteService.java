package com.justice.nkopane.justnotes.note;

import com.justice.nkopane.justnotes.user.User;
import com.justice.nkopane.justnotes.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public void createNote(NoteRequest noteRequest){
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        String authenticatedUserEmail =  getAuthentication().getName();
        Optional<User> authenticatedUser = userRepository.findByEmail(authenticatedUserEmail);

        if(authenticatedUser.isPresent()){
            Note note = Note.builder()
                    .id(UUID.randomUUID())
                    .userId(authenticatedUser.get())
                    .title(noteRequest.title())
                    .content(noteRequest.content())
                    .dateCreated(currentDate)
                    .dateUpdated(currentDate)
                    .build();
            noteRepository.save(note);
        }
    }

    public List<NoteDto> getNotes(){
        String authenticatedUserEmail =  getAuthentication().getName();
        Optional<User> authenticatedUser = userRepository.findByEmail(authenticatedUserEmail);

        return authenticatedUser.map(user -> noteRepository.findByUserId(user).
                stream()
                .map(this::mapToNoteDto)
                .toList()).orElse(null);
    }

    public void deleteNote(UUID uuid){
        noteRepository.deleteById(uuid);
    }

    public void updateNote(NoteDto newNote){
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        Optional<Note> DbNote = noteRepository.findById(newNote.getId());

        if(DbNote.isPresent()){
            Note note = DbNote.get();
            note.setContent(newNote.getContent());
            note.setTitle(newNote.getTitle());
            note.setDateUpdated(currentDate);
            noteRepository.save(note);
        }
    }

    private Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private NoteDto mapToNoteDto(Note note) {
        return NoteDto.builder()
                .id(note.getId())
                .content(note.getContent())
                .title(note.getTitle())
                .dateCreated(note.getDateCreated())
                .dateUpdated(note.getDateUpdated())
                .build();
    }
}
