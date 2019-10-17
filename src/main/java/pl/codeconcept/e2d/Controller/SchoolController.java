package pl.codeconcept.e2d.Controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolController {


    @GetMapping ("/school")
    @PreAuthorize("hasRole('USER')")
    public String school () {
        return "school";
    }


    @GetMapping("/students")
    @PreAuthorize("hasRole('ADMIN')")
    public String users (){
        return users();
    }




}
