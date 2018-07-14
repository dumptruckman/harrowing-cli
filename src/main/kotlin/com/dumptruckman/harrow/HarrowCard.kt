package com.dumptruckman.harrow

import com.dumptruckman.harrow.Ability.*
import com.dumptruckman.harrow.Alignment.*

enum class HarrowCard(val ability: Ability, val alignment: Alignment, val longName: String) {
    PALADIN(STR, LG, "The Paladin"),
    KEEP(STR, NG, "The Keep"),
    BIG_SKY(STR, CG, "The Big Sky"),
    FORGE(STR, LN, "The Forge"),
    BEAR(STR, N, "The Bear"),
    UPRISING(STR, CN, "The Uprising"),
    FIEND(STR, LE, "The Fiend"),
    BEATING(STR, NE, "The Beating"),
    CYCLONE(STR, CE, "The Cyclone"),

    DANCE(DEX, LG, "The Dance"),
    CRICKET(DEX, NG, "The Cricket"),
    JUGGLER(DEX, CG, "The Juggler"),
    LOCKSMITH(DEX, LN, "The Locksmith"),
    PEACOCK(DEX, N, "The Peacock"),
    RABBIT_PRINCE(DEX, CN, "The Rabbit Prince"),
    AVALANCHE(DEX, LE, "The Avalanche"),
    CROWS(DEX, NE, "The Crows"),
    DEMONS_LANTERN(DEX, CE, "The Demon's Lantern"),

    TRUMPET(CON, LG, "The Trumpet"),
    SURVIVOR(CON, NG, "The Survivor"),
    DESERT(CON, CG, "The Desert"),
    BRASS_DWARF(CON, LN, "The Brass Dwarf"),
    TEAMSTER(CON, N, "The Teamster"),
    MOUNTAIN_MAN(CON, CN, "The Mountain Man"),
    TANGLED_BRIAR(CON, LE, "The Tangled Briar"),
    SICKNESS(CON, NE, "The Sickness"),
    WAXWORKS(CON, CE, "The Waxworks"),

    HIDDEN_TRUTH(INT, LG, "The Hidden Truth"),
    WANDERER(INT, NG, "The Wanderer"),
    JOKE(INT, CG, "The Joke"),
    INQUISITOR(INT, LN, "The Inquisitor"),
    FOREIGN_TRADER(INT, N, "The Foreign Trader"),
    VISION(INT, CN, "The Vision"),
    RAKSHASA(INT, LE, "The Rakshasa"),
    IDIOT(INT, NE, "The Idiot"),
    SNAKEBITE(INT, CE, "The Snakebite"),

    WINGED_SERPENT(WIS, LG, "The Winged Serpent"),
    MIDWIFE(WIS, NG, "The Midwife"),
    PUBLICAN(WIS, CG, "The Publican"),
    QUEEN_MOTHER(WIS, LN, "The Queen Mother"),
    OWL(WIS, N, "The Owl"),
    CARNIVAL(WIS, CN, "The Carnival"),
    ECLIPSE(WIS, LE, "The Eclipse"),
    MUTE_HAG(WIS, NE, "The Mute Hag"),
    LOST(WIS, CE, "The Lost"),

    EMPTY_THRONE(CHA, LG, "The Empty Throne"),
    THEATER(CHA, NG, "The Theater"),
    UNICORN(CHA, CG, "The Unicorn"),
    MARRIAGE(CHA, LN, "The Marriage"),
    TWIN(CHA, N, "The Twin"),
    COURTESAN(CHA, CN, "The Courtesan"),
    TYRANT(CHA, LE, "The Tyrant"),
    BETRAYAL(CHA, NE, "The Betrayal"),
    LIAR(CHA, CE, "The Liar");

    override fun toString(): String {
        return longName
    }

    val displayName = "$longName (${ability.longName}, ${alignment.longName})"
    val shortDisplayName = "$longName (${ability.name}, ${alignment.name})"

    companion object {
        private val cardMap = mapOf(*(Ability.values().map {
            ability -> Pair(ability, mapOf(*(values()
                .filter { it.ability == ability }
                .map { Pair(it.alignment, it) }
                .toTypedArray())))
        }.toTypedArray()))

        fun getCard(ability: Ability, alignment: Alignment): HarrowCard? {
            return cardMap[ability]?.get(alignment)
        }
    }
}