package com.example.candiformation.models

data class CommentBody(
    var articleId: Int,
    var nickname: String,
    var isSecret: Boolean,
    var content: String,
    var createdAt: String
)