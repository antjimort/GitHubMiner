package aiss.github.service;

import aiss.github.model.IssueResponse;

import java.util.List;

public interface IssueService {
     String ISSUES_SINCE = "/issues?since=";

    List<IssueResponse> findAllIssuesFromRepo(String owner, String repo, Integer sinceIssues, Integer maxPages);
}
