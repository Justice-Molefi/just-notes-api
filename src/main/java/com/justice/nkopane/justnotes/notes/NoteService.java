package com.justice.nkopane.justnotes.notes;

import com.justice.nkopane.justnotes.user.User;
import com.justice.nkopane.justnotes.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public Note createNote(NoteDto noteDto){
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        String authenticatedUserEmail =  getAuthentication().getName();
        Optional<User> authenticatedUser = userRepository.findByEmail(authenticatedUserEmail);

        if(authenticatedUser.isPresent()){
            Note note = Note.builder()
                    .id(UUID.randomUUID())
                    .userId(authenticatedUser.get())
                    .title(noteDto.title())
                    .content(noteDto.content())
                    .dateCreated(currentDate)
                    .dateUpdated(currentDate)
                    .build();
            return noteRepository.save(note);
        }
        return null;
    }

    public List<NoteResponse> getNotes(){
        String authenticatedUserEmail =  getAuthentication().getName();
        Optional<User> authenticatedUser = userRepository.findByEmail(authenticatedUserEmail);

        return authenticatedUser.map(user -> noteRepository.findByUserId(user).
                stream()
                .map(this::mapToNoteResponse)
                .toList()).orElse(null);
    }


    private Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private NoteResponse mapToNoteResponse(Note note) {
        return NoteResponse.builder()
                .content(note.getContent())
                .title(note.getTitle())
                .dateCreated(note.getDateCreated())
                .dateUpdated(note.getDateUpdated())
                .build();
    }
}
