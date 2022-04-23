package com.fy.tecnotreedemo.service;

import com.fy.tecnotreedemo.domain.comment.Comment;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface getComments {
     CompletableFuture<Set<Comment>> catchComments() throws InterruptedException;
}
