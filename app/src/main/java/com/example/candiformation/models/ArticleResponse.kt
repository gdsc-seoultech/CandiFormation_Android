package com.example.candiformation.models

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    // 뉴스 기사 하나에 해당하는 모델
    // 필드는 response와 같은 네이밍을 해야만 함 or SerializeName으로 고쳐야 함

    val id: Int,
    val title: String,
    val content: String,
    val post_time: String,
    val news_agency: String,
    val like_num: Int,
    val comment_num: Int,
    val link: String

)