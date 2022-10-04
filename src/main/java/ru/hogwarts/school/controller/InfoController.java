package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.InfoService;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;

@RequestMapping("/info")
@RestController
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @Value("${server.port}")
    Integer port;

    @GetMapping
    public ResponseEntity getInfoAboutStart() {
        return ResponseEntity.ok("Приложение запущено!");

    }

    @GetMapping("/getPort")
    public Integer getServerPort() {
        return port;
    }

    @GetMapping("/modified-logic")
    public String modifiedLogic() {
        return infoService.modifiedLogic();
    }
}
