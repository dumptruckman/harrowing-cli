package com.dumptruckman.harrow

import java.util.Random

enum class Alignment(val longName: String) {
    LG("Lawful Good"),
    NG("Neutral Good"),
    CG("Chaotic Good"),
    LN("Lawful Neutral"),
    N("Neutral"),
    CN("Chaotic Neutral"),
    LE("Lawful Evil"),
    NE("Neutral Evil"),
    CE("Chaotic Evil");

    override fun toString(): String {
        return longName;
    }

    companion object {
        fun random(): Alignment = values()[(0 until values().size).random()]
        fun chosen(ordinal: Int): Alignment {
            if (ordinal >= 0 && ordinal < values().size) {
                return values()[ordinal]
            } else {
                throw IllegalArgumentException("Ordinal must be between 0 and ${values().size - 1}")
            }
        }
    }
}

enum class Ability(val longName: String) {
    STR("Strength"),
    DEX("Dexterity"),
    CON("Constitution"),
    INT("Intelligence"),
    WIS("Wisdom"),
    CHA("Charisma");

    override fun toString(): String {
        return longName;
    }

    companion object {
        private val shortNameMap = mapOf(*(values().map { Pair(it.name.toLowerCase(), it) }.toTypedArray()))
        private val longNameMap = mapOf(*(values().map { Pair(it.longName.toLowerCase(), it) }.toTypedArray()))

        fun find(name: CharSequence): Ability? {
            val indexName = name.toString().toLowerCase()
            return shortNameMap[indexName] ?: longNameMap[indexName]
        }
    }
}

private fun ClosedRange<Int>.random() = Random().nextInt((endInclusive + 1) - start) +  start