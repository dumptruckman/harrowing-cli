package com.dumptruckman.harrow

fun main(args: Array<String>) {
    println("Welcome to the Harrowing CLI tool")
    println("Press ctrl+c any time to quit")
    println()

    var ability = chooseAbility()
    while (ability == null) {
        println()
        ability = chooseAbility()
    }

    println()
    println("First, the Choosing...")
    println()

    val chosenCards = chooseCards(ability)

    val spread = mutableListOf(*HarrowCard.values()).apply { shuffle() }
    println()
    printSpread(chosenCards, spread)

    println()
    println("Press enter to exit.")
    readLine()
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

    while (true) {
        if (chosenCards.size == Alignment.values().size) {
            println("All of the cards have been chosen.")
            break
        }

        val card = chooseCard(ability, chosenCards)
        if (card != null) {
            chosenCards.add(card)

            println(card.displayName)
            println()
        } else {
            // null returned when done with The Choosing
            break
        }
    }

    return chosenCards
}

fun chooseCard(ability: Ability, chosenCards: List<HarrowCard>): HarrowCard? {

    while (true) {
        println("Choose a card from 1 to ${Alignment.values().size} or type 'done' to continue: ")
        val input = readLine() ?: ""

        if (input.toLowerCase() == "done") {
            return null
        }

        val choice = input.toIntOrNull()
        if (choice != null && choice > 0 && choice <= Alignment.values().size) {
            val alignment = Alignment.chosen(choice - 1)
            val card = HarrowCard.getCard(ability, alignment)
            if (card != null) {
                if (isNotChosen(alignment, chosenCards)) {
                    return card
                } else {
                    println("That card has already been chosen!")
                    println()
                }
            } else {
                printAlignmentChoiceError()
            }
        } else {
            printAlignmentChoiceError()
        }
    }

}

fun isNotChosen(alignment: Alignment, chosenCards: List<HarrowCard>): Boolean {
    for (card in chosenCards) {
        if (card.alignment == alignment) {
            return false
        }
    }
    return true
}

fun printAlignmentChoiceError() {
    println("Your selection must be a number between 1 and ${Alignment.values().size}.")
    println()
}

fun printSpread(chosen: List<HarrowCard>, spread: List<HarrowCard?>) {
    println("Chosen cards: $chosen")
    println()
    println("The Spread")

    val a = "10"
    val b = "35"
    println("%${a}s%-${b}s%-${b}s%s".format("", "Lawful", "Neutral", "Chaotic"))
    println("%-${a}s%-${b}s%-${b}s%s".format("Good", spread[0]?.shortDisplayName ?: "", spread[3]?.shortDisplayName ?: "", spread[6]?.shortDisplayName ?: ""))
    println("%-${a}s%-${b}s%-${b}s%s".format("", "(positive past)", "(positive present)", "(positive future)"))
    println("%-${a}s%-${b}s%-${b}s%s".format("Neutral", spread[1]?.shortDisplayName ?: "", spread[4]?.shortDisplayName ?: "", spread[7]?.shortDisplayName ?: ""))
    println("%-${a}s%-${b}s%-${b}s%s".format("", "(neutral past)", "(neutral present)", "(neutral future)"))
    println("%-${a}s%-${b}s%-${b}s%s".format("Evil", spread[2]?.shortDisplayName ?: "", spread[5]?.shortDisplayName ?: "", spread[8]?.shortDisplayName ?: ""))
    println("%-${a}s%-${b}s%-${b}s%s".format("", "(negative past)", "(negative present)", "(negative future)"))
}
