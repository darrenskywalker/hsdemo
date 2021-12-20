package com.example.hsdemo.controllers;

import com.example.hsdemo.entities.ClubEntity;
import com.example.hsdemo.entities.PersonEntity;
import com.example.hsdemo.repositories.ClubRepository;
import com.example.hsdemo.repositories.PersonRepository;
import com.example.hsdemo.security.jwt.services.JwtUtils;
import com.example.hsdemo.services.PersonService;
import com.example.hsdemo.views.person.AddressView;
import com.example.hsdemo.views.person.ClubView;
import com.example.hsdemo.views.person.PersonView;
import com.example.hsdemo.views.person.PersonalInfoView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {
    private MockMvc mockMvc;
    private JwtUtils jwtUtils = mock(JwtUtils.class);
    private Set<ClubEntity> testClubs;
    private PersonEntity testPersonEntity;
    private PersonView testPersonView;

    @Mock
    private PersonRepository personRepositoryMock;

    @Mock
    private ClubRepository clubRepositoryMock;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new PersonController(personService, jwtUtils))
                .build();

        var costco = new ClubView();
        costco.setName("Costco");
        testClubs = new HashSet<>(Arrays.asList(new ClubEntity(costco)));

        var address = new AddressView();
        address.setStreet("123 Test Street");
        address.setCity("Test City");
        address.setState("Test State");
        address.setZipcode("Test Zip Code");

        var personalInfo = new PersonalInfoView();
        personalInfo.setEmail("testperson@test.com");
        personalInfo.setMobile("801 123 1234");

        testPersonView = new PersonView();
        testPersonView.setFirstName("Test");
        testPersonView.setLastName("Person");
        testPersonView.setClubs(testClubs.stream().map(ClubView::new).collect(Collectors.toSet()));
        testPersonView.setPersonalInfo(personalInfo);
        testPersonView.setAddresses(new HashSet<>(Arrays.asList(address)));
        testPersonEntity = new PersonEntity(testPersonView, testClubs);
        testPersonEntity.setId(1L);
    }

    @Test
    void testCreatePerson() throws Exception {
        when(clubRepositoryMock.findClubEntityByName(any())).thenReturn(testClubs.stream().findFirst().get());
        when(personRepositoryMock.save(any())).thenReturn(testPersonEntity);
        var result = mockMvc.perform(post("/v1/post-person").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(testPersonView)))
               .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        var person = new ObjectMapper().readValue(result, PersonView.class);
        assertTrue(person.getFirstName().equalsIgnoreCase(testPersonView.getFirstName()));
        assertTrue(person.getClubs().stream().toList().get(0).getName().equalsIgnoreCase("Costco"));
    }

    @Test
    void testGetPerson() throws Exception {
        when(personRepositoryMock.getById(anyLong())).thenReturn(testPersonEntity);
        var result = mockMvc.perform(get("/v1/get-person/1"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        var person = new ObjectMapper().readValue(result, PersonView.class);
        assertTrue(person.getFirstName().equalsIgnoreCase(testPersonView.getFirstName()));
        assertTrue(person.getClubs().stream().toList().get(0).getName().equalsIgnoreCase("Costco"));
    }

    @Test
    void testDeletePerson() throws Exception {
        mockMvc.perform(delete("/v1/delete-person/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreatePublicToken() throws Exception {
        when(jwtUtils.generateJwtToken()).thenReturn("TEST_TOKEN");

        mockMvc.perform(get("/v1/get-public-user-token"))
                .andExpect(status().isOk());
    }
}
