fun main() {
    val words = mutableListOf<String>()

    println("Введите слова (для завершения введите пустую строку):")
    var input = readLine()
    while (input != null && input.isNotBlank()) {
        words.add(input)
        input = readLine()
    }

    val groupedWords = groupWords(words)
    printGroups(groupedWords)
}

fun groupWords(words: List<String>): List<List<String>> {
    val groups = mutableListOf<MutableList<String>>()

    for (word in words) {
        var foundGroup = false
        for (group in groups) {
            if (groupMatchesWord(group[0], word)) {
                group.add(word)
                foundGroup = true
                break
            }
        }
        if (!foundGroup) {
            groups.add(mutableListOf(word))
        }
    }

    return groups
}

fun groupMatchesWord(groupWord: String, word: String): Boolean {
    val groupWordChars = groupWord.toCharArray()
    val wordChars = word.toCharArray()

    if (groupWordChars.size != wordChars.size) {
        return false
    }

    val groupWordCharCounts = mutableMapOf<Char, Int>()
    for (char in groupWordChars) {
        groupWordCharCounts[char] = groupWordCharCounts.getOrDefault(char, 0) + 1
    }

    val wordCharCounts = mutableMapOf<Char, Int>()
    for (char in wordChars) {
        wordCharCounts[char] = wordCharCounts.getOrDefault(char, 0) + 1
    }

    return groupWordCharCounts == wordCharCounts
}

fun printGroups(groups: List<List<String>>) {
    for (group in groups) {
        println(group.joinToString(", "))
    }
}