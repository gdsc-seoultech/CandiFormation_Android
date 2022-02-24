package com.solution_challenge.candiformation.ui.screens.candidate

import com.solution_challenge.candiformation.R

sealed class CandidateItem(
    val name: String,
    val party: String,
    val birth: String,
    val slogan: String,
    val campLocation: String,
    val imageSrc: Int,
    val partyLogo: Int,
    val stringSrc: Int
) {
    object lee_1 : CandidateItem(
        name = "이재명",
        party = "더불어민주당",
        birth = "1964년 12월 22일 (57세)\n경상북도 안동군 예안면 도촌동",
        slogan = "위기에 강한\n유능한 경제대통령",
        campLocation = "대한민국대전환 선거대책위원회\n서울특별시 영등포구 국회대로70길 23",
        imageSrc = R.drawable.lee,
        partyLogo = R.drawable.minjudang_logo,
        stringSrc = R.string.String_1
    )
    object yoon_2 : CandidateItem(
        name = "윤석열",
        party = "국민의힘",
        birth = "1960년 12월 18일 (61세)\n서울특별시 서대문구 연희동",
        slogan = "국민이 키운 윤석열\n내일을 바꾸는 대통령",
        campLocation = "국민의힘 선거대책본부\n서울특별시 영등포구 국회대로70길 12",
        imageSrc = R.drawable.yoon,
        partyLogo = R.drawable.poeplepowerparty_logo,
        stringSrc = R.string.String_2
    )
    object shim_3 : CandidateItem(
        name = "심상정",
        party = "정의당",
        birth = "1959년 2월 20일 (63세)\n경기도 파주시 광탄면",
        slogan = "주4일제 복지국가\n일하는 시민의 대통령",
        campLocation = "심상찮은 선거대책위원회\n서울특별시 영등포구 국회대로70길 7",
        imageSrc = R.drawable.shim,
        partyLogo = R.drawable.justiceparty_logo,
        stringSrc = R.string.String_3
    )
    object ann_4 : CandidateItem(
        name = "안철수",
        party = "국민의당",
        birth = "1962년 2월 26일 (59세)\n경상남도 밀양군 밀양읍 내이리",
        slogan = "바르고 깨끗한 과학경제강국\n믿을 사람, 바른 사람 안철수",
        campLocation = "국민의 도전 선거대책위원회\n서울특별시 영등포구 국회대로74길 10",
        imageSrc = R.drawable.ann,
        partyLogo = R.drawable.thepeoplesparty_logo,
        stringSrc = R.string.String_4
    )
    object oh_5 : CandidateItem(
        name = "오준호",
        party = "기본소득당",
        birth = "1975년 6월 20일 (46세)\n경상북도 대구시",
        slogan = "누구나 나답게\n기본소득 대한민국",
        campLocation = "기대캠프\n서울특별시 영등포구 국회대로68길 23",
        imageSrc = R.drawable.oh_5,
        partyLogo = R.drawable.basicincomeparty_logo,
        stringSrc = R.string.String_5

    )
    object hu_6 : CandidateItem(
        name = "허경영",
        party = "국가혁명당",
        birth = "1947년 7월 13일 (74세)\n경상남도 밀양군",
        slogan = "국가에 돈이 없는게 아니라\n도둑놈들이 많습니다!",
        campLocation = "정권획득을 위한 선거대책위원회\n서울특별시 영등포구 국회대로70길 15-1",
        imageSrc = R.drawable.hu_6,
        partyLogo = R.drawable.nationalrevolutionaryparty_logo,
        stringSrc = R.string.String_6
    )
    object lee_7 : CandidateItem(
        name = "이백윤",
        party = "노동당",
        birth = "1977년 5월 25일 (44세)\n충청남도 서산시",
        slogan = "계속 이렇게 살꺼야?\n바꾸고 싶다면 사회주의",
        campLocation = "노동자민중 사회주의 좌파 공동투쟁본부\n서울특별시 영등포구 국회대로 664",
        imageSrc = R.drawable.lee_7,
        partyLogo = R.drawable.theloborparty_logo,
        stringSrc = R.string.String_7
    )
    object ook_8 : CandidateItem(
        name = "옥은호",
        party = "새누리당",
        birth = "1971년 7월 10일 (50세)",
        slogan = "선거를 국민에게, 자유를 국민에게\n" +
                "선관위 개혁, 부정선거 단죄",
        campLocation = "서울특별시 서초구 서초대로 49길 9",
        imageSrc = R.drawable.ook_8,
        partyLogo = R.drawable.saenuridang_logo,
        stringSrc = R.string.String_8
    )
    object kim_9 : CandidateItem(
        name = "김동연",
        party = "새로운물결",
        birth = "1957년 1월 28일 (65세)\n충청북도 음성군",
        slogan = "품격있는 경제대통령\n기득권공화국에서 기회의 나라로",
        campLocation = "서울특별시 영등포구 버드나루로 84",
        imageSrc = R.drawable.kim_9,
        partyLogo = R.drawable.saroun_logo,
        stringSrc = R.string.String_9
    )
    object kim_10 : CandidateItem(
        name = "김경재",
        party = "신자유민주연합",
        birth = "1942년 11월 3일 (79세)\n전라남도 여수시",
        slogan = "자유대한민국 재건의 선봉장\n자유 우파 구국 대통령",
        campLocation = "서울특별시 영등포구 국회대로 800",
        imageSrc = R.drawable.kim_10,
        partyLogo = R.drawable.shinjayou_logo,
        stringSrc = R.string.String_10
    )
    object choi_11 : CandidateItem(
        name = "조원진",
        party = "우리공화당",
        birth = "1959년 1월 7일 (63세)\n경상북도 대구시 원대동",
        slogan = "부정부패 쓰레기정치 확!\n개혁적인 자유우파 유일 후보",
        campLocation = "서울특별시 영등포구 국회대로 640",
        imageSrc = R.drawable.choi_11,
        partyLogo = R.drawable.ourrepublicparty_logo,
        stringSrc = R.string.String_11
    )
    object kim_12 : CandidateItem(
        name = "김재연",
        party = "진보당",
        birth = "1980년 11월 27일 (41세)\n경상북도 대구시",
        slogan = "당신의 땀이 빛나도록\n일하는 사람들의 정치혁명",
        campLocation = "소금꽃 선거대책위원회\n서울특별시 종로구 사직로 130",
        imageSrc = R.drawable.kim_12,
        partyLogo = R.drawable.junbodang_logo,
        stringSrc = R.string.String_12
    )
    object lee_13 : CandidateItem(
        name = "이경희",
        party = "통일한국당",
        birth = "1974년 1월 20일 (48세)\n충청북도 단양군",
        slogan = "오직 경제! 오직 통일!\n국민분열정치 이제 그만,\n" +
                "국민통합정치 이제 시작!",
        campLocation = "서울특별시 영등포구 국회대로72길 21",
        imageSrc = R.drawable.lee_13,
        partyLogo = R.drawable.tongillhankook_logo,
        stringSrc = R.string.String_13
    )
    object kim_14 : CandidateItem(
        name = "김민찬",
        party = "한류연합당",
        birth = "1958년 2월 4일 (64세)\n서울특별시",
        slogan = "국가를 지키고 국민을 보호하겠습니다.\n" +
                "새로운 시대! 새로운 나라! 새로운 사람!",
        campLocation = "서울특별시 서초구 논현로 64",
        imageSrc = R.drawable.kim_14,
        partyLogo = R.drawable.hanryudang_logo,
        stringSrc = R.string.String_14
    )
}