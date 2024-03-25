package com.example.userservice;

import com.example.userservice.entity.Role;
import com.example.userservice.entity.users.Admin;
import com.example.userservice.repository.AdminRepository;
import com.example.userservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class Initializer implements CommandLineRunner {
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if(!roleRepository.existsById(1L)){
            roleSave();
        }
        if(!adminRepository.existsById(1L)){
            adminSave();
        }
    }

    private void adminSave() {
        Admin admin = new Admin();
        admin.setRole(roleRepository.findById(1L).get());
        admin.setId(1L);
        admin.setEmail("admin@gmail.com");
        admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
        admin.setName("Admin");
        adminRepository.save(admin);
    }

    private void roleSave() {
        Role admin = new Role("ROLE_ADMIN");
        roleRepository.save(admin);
        Role builder = new Role("ROLE_BUILDER");
        roleRepository.save(builder);
        Role notary = new Role("ROLE_NOTARY");
        roleRepository.save(notary);
        Role customer = new Role("ROLE_CUSTOMER");
        roleRepository.save(customer);
    }
}