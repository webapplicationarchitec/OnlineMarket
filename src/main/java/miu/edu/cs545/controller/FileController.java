package miu.edu.cs545.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileController {
    @PostMapping("/file/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

       System.out.println( "You successfully uploaded " + file.getOriginalFilename() + "!");
       // redirectAttributes.addFlashAttribute("message",
       //         "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }
}
