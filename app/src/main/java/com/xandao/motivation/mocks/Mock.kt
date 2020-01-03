package com.xandao.motivation.mocks

import com.xandao.motivation.util.MotivationConstants.PHRASE_FILTER
import java.util.*

class Phrase(val description: String, val category: Int)

class Mock {

    private val mListPhrase: List<Phrase> = listOf(Phrase("haha", PHRASE_FILTER.HAPPY),
        Phrase("hehe", PHRASE_FILTER.HAPPY),
        Phrase("hihi", PHRASE_FILTER.SUN),
        Phrase("hoho", PHRASE_FILTER.SUN),
        Phrase("huhu", PHRASE_FILTER.SUN),
        Phrase("kaka", PHRASE_FILTER.HAPPY),
        Phrase("keke", PHRASE_FILTER.SUN))


    fun getPhrase(value: Int): String {
        val filter = mListPhrase.filter { it -> it.category == value || value == PHRASE_FILTER.ALL }

        val rand = Random().nextInt(filter.size)

        return filter[rand].description
    }
}