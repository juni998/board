package com.board.board.repository;

import com.board.board.domain.Post;
import com.board.board.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);

}
