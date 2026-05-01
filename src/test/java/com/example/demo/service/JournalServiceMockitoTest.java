package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
// import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.entity.JournalEntity;
import com.example.demo.repository.JournalEntityRepository;
import com.example.demo.repository.UserRepository;

import jakarta.inject.Inject;

@ExtendWith(MockitoExtension.class)
public class JournalServiceMockitoTest {

    @Mock
    private JournalEntityRepository jrs;

    @Mock
    private UserRepository urs;

    @InjectMocks
    private JournalEntityService service;


    @Test
void testGetAllJournal() {
    List<JournalEntity> list = new ArrayList<>();
    list.add(new JournalEntity());

    when(jrs.findAll()).thenReturn(list);

    ResponseEntity<List<JournalEntity>> response = service.getAllJournal();

    assertEquals(HttpStatus.FOUND, response.getStatusCode());
    assertEquals(1, response.getBody().size());
}

@Test
void testFindById() {
    JournalEntity journal = new JournalEntity();
    journal.setId("1");

    when(jrs.findById("1")).thenReturn(Optional.of(journal));

    Optional<JournalEntity> result = service.findById("1");

    assertTrue(result.isPresent());
    assertEquals("1", result.get().getId());
}
}
