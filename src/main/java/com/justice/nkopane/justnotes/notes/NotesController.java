package com.justice.nkopane.justnotes.notes;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {

    @GetMapping
    public String getAllNotes(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return "All notes for " + authentication.getName();
    }
}
