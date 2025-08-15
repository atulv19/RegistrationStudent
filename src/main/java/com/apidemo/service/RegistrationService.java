package com.apidemo.service;

import com.apidemo.entity.Registration;
import com.apidemo.exception.ResourceNotFoudException;
import com.apidemo.payload.RegistrationDto;
import com.apidemo.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {
    private RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }
    public RegistrationDto createRegistration(RegistrationDto registrationDto){
        Registration registration1 = ConvertToEntity(registrationDto);
        Registration save = registrationRepository.save(registration1);
          return ConvertToDto(save);
    }

    Registration ConvertToEntity(RegistrationDto registrationDto){
        Registration registration = new Registration();
        registration.setName(registrationDto.getName());
       registration.setEmail(registrationDto.getEmail());
        registration.setMobile(registrationDto.getMobile());
        return  registration;
    }

    RegistrationDto ConvertToDto(Registration registration){
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setName(registration.getName());
        registrationDto.setEmail(registration.getEmail());
        registrationDto.setMobile(registration.getMobile());
        return registrationDto;
    }

    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }

    public void updateRegistration(long id, RegistrationDto registrationDto) {
        Optional<Registration> byId = registrationRepository.findById(id);
        if(byId.isPresent()){
            Registration reg = byId.get();
            reg.setName(registrationDto.getName());
            reg.setEmail(registrationDto.getEmail());
            reg.setMobile(registrationDto.getMobile());
            registrationRepository.save(reg);
        }

    }

    public List<Registration> getAllRegistration() {
        List<Registration> registrations = registrationRepository.findAll();
        return registrations;
    }

    public Registration getRegistrationById(long id){
        Registration reg = registrationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoudException("Record not found")
        );
            return reg;
    }
}
