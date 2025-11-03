package com.hitss.springboot.taskmanager.validation;

import com.hitss.springboot.taskmanager.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String> {
   @Autowired
   private UserService userService;

   public boolean isValid(String value, ConstraintValidatorContext context) {
      if (this.userService == null) {
         return true;
      } else {
         return !this.userService.existsByUsername(value);
      }
   }
}