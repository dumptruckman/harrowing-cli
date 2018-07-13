package com.dumptruckman.harrow

fun main(args: Array<String>) {
    println("Welcome to the Harrowing CLI tool")
    println("Press ctrl+c any time to quit")
    println()

    beginHarrowing()
}

fun beginHarrowing() {
    var ability = chooseAbility()
    while (ability == null) {
        println()
        ability = chooseAbility()
    }

    val chosenCards = chooseCards(ability)
}

fun chooseAbility(): Ability? {
    println("Choose an ability based on the nature of the question.")
    println("  Strength (STR) - War, Battle, Honor")
    println("  Dexterity (DEX) - Trouble, Children, Entertainment")
    println("  Constitution (CON) - Health, Home, Pain")
    println("  Intelligence (INT) - Money, School, Literature")
    println("  Wisdom (WIS) - Ancient History, Morality, The Gods")
    println("  Charisma (CHA) - Love, Family, Politics")

    print("Your choice? ")
    val choice = readLine() ?: ""
    val ability = Ability.find(choice)
    if (ability == null) {
        println("'$choice' is not a valid selection")
    }

    return ability
}

fun chooseCards(ability: Ability): List<HarrowCard> {
    val chosenCards = mutableListOf<HarrowCard>()


    println("Choose a card from 1 to ${Alignment.values().size} or type 'done' to continue: ")

    var input: String
    do {
       input = readLine() ?: ""
    } while (input != "done")

    return chosenCards
}

fun chooseCard(ability: Ability): HarrowCard {
    return null!!
}

