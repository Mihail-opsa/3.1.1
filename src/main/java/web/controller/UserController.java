package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String homeRedirect() {
        return "redirect:/users";
    }


    @GetMapping("/users") // все пользователи доступ
    public String allUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "allUsers";
    }

    @PostMapping("/add") // добавление юзера
    public String addUser(
            @RequestParam("name") String name,
            @RequestParam("lastname") String lastName,
            @RequestParam("age") Byte age) {
        userService.createUser(name, lastName, age);

        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteUser(
            @RequestParam("id") Long id) {
        userService.delete(id);
//        return "redirect:/";
        return "redirect:/users";
    }

// убранан вся бизнес логика
    @PostMapping("/update")
    public String updateUser(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("lastname") String lastName,
            @RequestParam("age") Byte age) {
            userService.updateUser(id, name, lastName, age);
        return "redirect:/";
    }
}
