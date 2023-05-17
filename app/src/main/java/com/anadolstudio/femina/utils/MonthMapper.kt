package com.anadolstudio.femina.utils

import android.content.Context

object MonthMapper {

    fun monthMap(context: Context, monthIndex: Int, case: CaseSystem = CaseSystem.NOMINATIVE): String {
        val monthString = Month(monthIndex, getMonth(context, monthIndex))

        return when (case) {
            CaseSystem.NOMINATIVE, CaseSystem.ACCUSATIVE -> monthString.toNominative()
            CaseSystem.GENITIVE -> monthString.toGenitive()
            CaseSystem.DATIVE -> monthString.toDative()
            CaseSystem.INSTRUMENTAL -> monthString.toInstrumental()
            CaseSystem.PREPOSITIONAL -> monthString.toPrepositional()
        }
    }

    private fun getMonth(context: Context, month: Int): String = when (month) { // TODO add to strings
        0 -> "январ"
        1 -> "феврал"
        2 -> "март"
        3 -> "апрел"
        4 -> "май"
        5 -> "июн"
        6 -> "июл"
        7 -> "август"
        8 -> "сентябр"
        9 -> "октябр"
        10 -> "ноябр"
        else -> "декабр"
    }

    // В творительном падеже окончание -ём только у месяцев с сентября по февраль включительно
    // этот метод как раз и помогает понять, нужно ли ставить -ём или нет
    private fun Month.isColdMonth(): Boolean = this.index !in 2..7

    private fun Month.toNominative(): String = this.word.addEnding { lastLetter ->
        when (lastLetter) {
            "р", "л", "н" -> LetterTask.Add("ь")
            else -> LetterTask.NoChange
        }
    }

    private fun Month.toGenitive(): String = this.word.addEnding { lastLetter ->
        when (lastLetter) {
            "р", "л", "н" -> LetterTask.Add("я")
            "т" -> LetterTask.Add("а")
            "й" -> LetterTask.Change("я")
            else -> LetterTask.NoChange
        }
    }

    private fun Month.toDative(): String = this.word.addEnding { lastLetter ->
        when (lastLetter) {
            "р", "л", "н" -> LetterTask.Add("ю")
            "т" -> LetterTask.Add("у")
            "й" -> LetterTask.Change("ю")
            else -> LetterTask.NoChange
        }
    }

    private fun Month.toInstrumental(): String = this.word.addEnding { lastLetter ->
        when (lastLetter) {
            "р", "л", "н" -> LetterTask.Add(
                if (this.isColdMonth()) "ём" else "ем"
            )
            "й" -> LetterTask.Change("ем")
            else -> LetterTask.Add("ом")
        }
    }

    private fun Month.toPrepositional(): String = this.word.addEnding { lastLetter ->
        when (lastLetter) {
            "р", "л", "н", "т" -> LetterTask.Add("е")
            "й" -> LetterTask.Change("е")
            else -> LetterTask.NoChange
        }
    }

    private fun String.addEnding(condition: (String) -> LetterTask): String {
        if (this.isEmpty()) return this

        val lastLetter = this.last().toString()

        return when (val task = condition.invoke(lastLetter)) {
            is LetterTask.Add -> this + task.letter
            is LetterTask.Change -> this.substring(0, lastIndex) + task.letter
            is LetterTask.NoChange -> this
        }
    }

    private sealed class LetterTask {

        object NoChange : LetterTask()

        class Add(val letter: String) : LetterTask()

        class Change(val letter: String) : LetterTask()
    }

    private data class Month(val index: Int, val word: String)

    enum class CaseSystem {
        NOMINATIVE,
        GENITIVE,
        ACCUSATIVE,
        DATIVE,
        INSTRUMENTAL,
        PREPOSITIONAL
    }

}
