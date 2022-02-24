package com.solution_challenge.candiformation.models

data class CommentBody(
    var articleId: Int,
    var nickname: String,
    var isSecret: Boolean,
    var content: String,
    var createdAt: String
)