package com.tenseii.parser.controllers.api;

import com.tenseii.parser.exceptions.ServiceException;
import com.tenseii.parser.services.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping(path = "api")
public class GitHubController extends com.tenseii.parser.controllers.Controller {

    private GitHubService gitHubService;

    @Autowired
    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @ResponseBody
    @GetMapping(value = "/linesOfCode/{userName}")
    public String getLinesOfCode(@PathVariable String userName) {
//        try {
//            return generateSimpleJsonResponse("linesOfCode", gitHubService.getLinesOfCode(userName));
//        } catch (ServiceException exc) {
//            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, exc.getMessage());
//        }
        return "{\"linesOfCode\":20585;}";
    }
}
