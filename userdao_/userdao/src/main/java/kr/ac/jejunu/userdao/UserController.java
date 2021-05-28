package kr.ac.jejunu.userdao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @RequestMapping("/user")
    public User getUser(@RequestParam("id") Integer id) {
        return userDao.get(id);
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
        return new ModelAndView();
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e) {
        ModelAndView modelAndView = new ModelAndView(("error"));
        modelAndView.addObject("e", e);
        return modelAndView;
    }

}
