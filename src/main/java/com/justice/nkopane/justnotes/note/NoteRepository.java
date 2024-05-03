package com.justice.nkopane.justnotes.note;

import com.justice.nkopane.justnotes.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, UUID> {
    List<Note> findByUserId(User id);
}
