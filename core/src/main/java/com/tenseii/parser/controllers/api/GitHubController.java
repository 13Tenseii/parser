package com.tenseii.parser.controllers.api;

import com.tenseii.parser.dto.GitStatsDto;
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
    @GetMapping(value = "/gitStats/{userName}")
    public String getLinesOfCode(@PathVariable String userName) {
//        try {
//            return gitHubService.getGitStats(userName);
//        } catch (ServiceException exc) {
//            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, exc.getMessage());
//        }
        return "{\"linesOfCode\":39229,\"numberOfCommits\":32,\"numberOfRepos\":7,\"reposStats\":[{\"repoName\":\"protei-test\",\"createdDate\":1564044750,\"updatedDate\":1564044973,\"numberOfCommits\":1,\"linesOfCode\":1186},{\"repoName\":\"smsc-core-api\",\"createdDate\":1579263255,\"updatedDate\":1580898023,\"numberOfCommits\":13,\"linesOfCode\":3794},{\"repoName\":\"twoDbDemo\",\"createdDate\":1574947297,\"updatedDate\":1575026425,\"numberOfCommits\":2,\"linesOfCode\":883},{\"repoName\":\"ddg-test\",\"createdDate\":1563292118,\"updatedDate\":1563292184,\"numberOfCommits\":1,\"linesOfCode\":1014},{\"repoName\":\"AndroidScheduleApp\",\"createdDate\":1568548552,\"updatedDate\":1568800197,\"numberOfCommits\":3,\"linesOfCode\":966},{\"repoName\":\"parser\",\"createdDate\":1592296237,\"updatedDate\":1593163129,\"numberOfCommits\":11,\"linesOfCode\":31193},{\"repoName\":\"vk-token\",\"createdDate\":1582100697,\"updatedDate\":1582100734,\"numberOfCommits\":1,\"linesOfCode\":193}]}";
    }
}
