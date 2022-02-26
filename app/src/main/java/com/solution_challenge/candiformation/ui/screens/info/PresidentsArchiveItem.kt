package com.solution_challenge.candiformation.ui.screens.info

import com.solution_challenge.candiformation.R

sealed class PresidentsArchiveItem (
    val name: String,
    val birth: String,
    val tenure: String,
    val religion: String,
    val family: String,
    val imageSrc: Int,
) {
    object former_1_lee: PresidentsArchiveItem(
        name = "이승만",
        birth = "1875. 3. 26 ~ 1965. 7. 19",
        tenure = "1948. 7 ~ 1960. 4",
        religion = "기독교",
        family = "영부인 : 프란체스카, 자녀 : 1남",
        imageSrc = R.drawable.former_1_lee
    )
    object former_2_yoon: PresidentsArchiveItem(
        name = "윤보선",
        birth = "1897. 8. 26 ~ 1990. 7. 18",
        tenure = "1960. 8 ~ 1962. 3",
        religion = "기독교",
        family = "영부인 : 공덕귀, 자녀 : 2남2녀",
        imageSrc = R.drawable.former_2_yun
    )
    object former_3_park: PresidentsArchiveItem(
        name = "박정희",
        birth = "1917. 11. 14. ~ 1979. 10. 26",
        tenure = "1962. 3 ~ 1979. 10",
        religion = "불교",
        family = "영부인 : 육영수, 자녀 : 1남2녀",
        imageSrc = R.drawable.former_3_park
    )
    object former_4_choi: PresidentsArchiveItem(
        name = "최규하",
        birth = "1919. 7. 16 ~ 2006. 10. 22",
        tenure = "1979. 10 ~ 1980. 8",
        religion = "없음",
        family = "영부인 : 홍기, 자녀 : 2남1녀",
        imageSrc = R.drawable.former_4_choi
    )
    object former_5_chun: PresidentsArchiveItem(
        name = "전두환",
        birth = "1931. 1. 18 ~ 2021. 11. 23",
        tenure = "1980. 9 ~ 1988. 2",
        religion = "불교",
        family = "영부인 : 이순자, 자녀 : 3남1녀",
        imageSrc = R.drawable.former_5_chun
    )
    object former_6_roh: PresidentsArchiveItem(
        name = "노태우",
        birth = "1932. 12. 4 ~ 2021. 10. 26",
        tenure = "1988. 2 ~ 1993. 2",
        religion = "불교",
        family = "영부인 : 김옥숙, 자녀 : 1남1녀",
        imageSrc = R.drawable.former_6_roh
    )
    object former_7_kim: PresidentsArchiveItem(
        name = "김영삼",
        birth = "1928.12.4(음)~2015.11.22",
        tenure = "1993. 2 ~ 1998. 2",
        religion = "기독교",
        family = "영부인 : 손명순, 자녀 : 2남3녀",
        imageSrc = R.drawable.former_7_kim
    )
    object former_8_kim: PresidentsArchiveItem(
        name = "김대중",
        birth = "1923.12.3(음)~2009.8.18",
        tenure = "1998. 2 ~ 2003. 2",
        religion = "천주교",
        family = "영부인 : 이희호, 자녀 : 3남",
        imageSrc = R.drawable.former_8_kim
    )
    object former_9_roh: PresidentsArchiveItem(
        name = "노무현",
        birth = "1946.9.1~2009.5.23",
        tenure = "2003. 2 ~ 2008. 2",
        religion = "없음",
        family = "영부인 : 권양숙, 자녀 : 1남1녀",
        imageSrc = R.drawable.former_9_roh
    )
    object former_10_lee: PresidentsArchiveItem(
        name = "이명박",
        birth = "1941. 12. 19 ~",
        tenure = "2008. 2 ~ 2013. 2",
        religion = "기독교",
        family = "영부인 : 김윤옥, 자녀 : 1남3녀",
        imageSrc = R.drawable.former_10_lee
    )
    object former_11_park: PresidentsArchiveItem(
        name = "박근혜",
        birth = "1952. 2. 2 ~",
        tenure = "2013. 2 ~ 2017. 3",
        religion = "무교",
        family = "미혼",
        imageSrc = R.drawable.former_11_park
    )
    object former_12_moon: PresidentsArchiveItem(
        name = "문재인",
        birth = "1953. 1. 24 ~",
        tenure = "2017.5.10 ~ 현재",
        religion = "천주교",
        family = "영부인: 김정숙 , 자녀: 1남1녀",
        imageSrc = R.drawable.former_12_moon
    )
}