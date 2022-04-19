package org.techtown.shoppingapp.kakaodatas

data class kakaodata(
    val documents: ArrayList<Document>,
    val meta: Meta
) {

    /**
     * filter -> { 내부 (true) 만 남겨라 }
     * toList -> 남긴걸 다시  List 형태로 만들어라.
     *
     * filter 내부의 true / false 기준 -> documents 내부의 roadAddress 내용이 있는지 (null 인지) 확인 후 boolean 값으로 받아옴.
     */

    fun getList(): List<Document> {
        return documents.filter {
            it.isRoadAddress()
        }.toList()
    }

}