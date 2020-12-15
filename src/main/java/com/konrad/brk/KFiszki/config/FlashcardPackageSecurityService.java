package com.konrad.brk.KFiszki.config;

import com.konrad.brk.KFiszki.model.User;
import com.konrad.brk.KFiszki.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FlashcardPackageSecurityService {

    private UserService userService;

    public FlashcardPackageSecurityService(UserService userService) {
        this.userService = userService;
    }

    public boolean isOwnerOfFlashcardPackage(String username, String id){
        User user = userService.getUserByUsername(username);
        List<String> flashcardsPackagesIds = user.getFlashcardsPackagesIds();
        return flashcardsPackagesIds.contains(id);
    }
}
