package com.example.security.controller;

import com.example.security.domain.dto.security.ImageCodeTo;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class HomeController {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private Producer producer;

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletRequest request, HttpServletResponse response, String uuid) throws Exception {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        String code=producer.createText();
        BufferedImage image=producer.createImage(code);
        ImageCodeTo imageCode=new ImageCodeTo(image,code, LocalDateTime.now().plusMinutes(1));
        sessionStrategy.setAttribute(new ServletWebRequest(request),"captcha",imageCode);
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
}
