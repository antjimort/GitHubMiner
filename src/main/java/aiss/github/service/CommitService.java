package aiss.github.service;

import aiss.github.model.Commit;

import java.util.List;

public interface CommitService {
     String COMMITS_SINCE = "/commits?since=";

    List<Commit> findAllCommitsFromRepo(String owner, String repo, Integer sinceCommits,
                                        Integer maxPages);
}
