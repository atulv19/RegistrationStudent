package com.apidemo.controller;

import com.apidemo.entity.Registration;
import com.apidemo.payload.RegistrationDto;
import com.apidemo.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/register")
@CrossOrigin(origins = "*")
public class RegistrationController {

   private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    //http://localhost:8080/api/v1/register
    @PostMapping
    public ResponseEntity<String> CreateRegistration(@RequestBody  RegistrationDto registrationDto){
        RegistrationDto registration = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>("Create User", HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRegistration(@RequestParam long id){
        registrationService.deleteRegistration(id);
        return new ResponseEntity<>("Delete Registration",HttpStatus.OK);

    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateRegistration(@PathVariable long id , @RequestBody RegistrationDto registrationDto){
        registrationService.updateRegistration( id, registrationDto);
        return new ResponseEntity<>("Update Registration",HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Registration>> getAllRegistration(){
      List<Registration> registrations =   registrationService.getAllRegistration();
      return new ResponseEntity<>(registrations,HttpStatus.OK);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<Registration> getRegistration(@PathVariable long id) {
        Registration registrationById = registrationService.getRegistrationById(id);
        return new ResponseEntity<>(registrationById, HttpStatus.OK);
    }


}
