package com.example.candiformation.models

data class WhatArticleLikeResponse(
    var articles: List<Int>, // article IDs
    var username: String
)