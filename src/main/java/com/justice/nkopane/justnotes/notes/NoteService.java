package com.justice.nkopane.justnotes.notes;

import com.justice.nkopane.justnotes.user.User;
import com.justice.nkopane.justnotes.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final Authentication authentication;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository, Authentication authentication) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.authentication = authentication;
    }

    public Note createNote(NoteDto noteDto){
        String authenticatedUser =  authentication.getName();
        Optional<User> user = userRepository.findByEmail(authenticatedUser);

        if(user.isPresent()){
            Note note = Note.builder()
                    .id(UUID.randomUUID())
                    .userId(user.get())
                    .title(noteDto.title())
                    .content(noteDto.content())
                    .build();
           return noteRepository.save(note);
        }

        return null;
    }
}
