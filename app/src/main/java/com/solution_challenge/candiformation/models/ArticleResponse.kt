package com.solution_challenge.candiformation.models

data class ArticleResponse(
    // 뉴스 기사 하나에 해당하는 모델
    // 필드는 response와 같은 네이밍을 해야만 함 or SerializeName으로 고쳐야 함

    val id: Int,
    val title: String,
    val thumnail: String,
    val news_agency: String,
    val link: String,
    val like_num: Int,
    val comment_num: Int,
    val images: String,
    val date_time: String

)