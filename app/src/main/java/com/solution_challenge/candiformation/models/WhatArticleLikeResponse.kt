package com.solution_challenge.candiformation.models

data class WhatArticleLikeResponse(
    var articles: List<Int>, // article IDs
    var username: String
)