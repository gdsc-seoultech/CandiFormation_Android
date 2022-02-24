package com.solution_challenge.candiformation.models

data class CommentResponse(
    val id: Int,
    val nickname: String,
    val isSecret: Boolean,
    val articleId: Int,
    val likeNum: Int,
    val content: String,
    val createdAt: String
)