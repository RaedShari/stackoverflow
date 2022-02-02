package com.raed.stackoverflow.data;

import com.raed.stackoverflow.model.Comment;
import com.raed.stackoverflow.util.Utilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByParentIdAndParentType(Long parentId, Utilities.ParentType parentType);
}
