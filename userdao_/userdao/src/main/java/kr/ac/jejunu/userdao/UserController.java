package kr.ac.jejunu.userdao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @GetMapping(value = "/user/{id}")
    public User getUser(@PathVariable("id") Integer id, HttpSession httpSession) {
        System.out.println("******************* User *******************");
        User user = userDao.get(id);
        httpSession.setAttribute("user", user);
        return user;
    }

    @GetMapping(value = "/user/session", produces = "application/json")
    public User sessionUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    @RequestMapping(path="/upload", method = RequestMethod.GET)
    public void upload() {
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public ModelAndView upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String path = request.getServletContext().getRealPath("/")
                + "/WEB-INF/static/" + file.getOriginalFilename(); // file 이름을 같이 담아!
        File saveFile = new File(path);
        FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(file.getBytes());
        bufferedOutputStream.close();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", "/images" + file.getOriginalFilename());
        return new ModelAndView();
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e) {
        ModelAndView modelAndView = new ModelAndView(("error"));
        modelAndView.addObject("e", e);
        return modelAndView;
    }

}
