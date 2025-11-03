//Exercise 1
fun printHello() {
    println("Hello World")
}

printHello()

//Exercise 2
1+1
//kotlin.Int = 2

53-3
//kotlin.Int = 50

50/10
//kotlin.Int = 5

1.0/2.0
//kotlin.Double = 0.5

2.0*3.5
//kotlin.Double = 7.0

6*50
//kotlin.Int = 300

6.0*50.0
//kotlin.Double = 300.0

6.0*50
//kotlin.Double = 300.0

2.times(3)
//kotlin.Int = 6

3.5.plus(4)
//kotlin.Double = 7.5

2.4.div(2)
//kotlin.Double = 1.2

val i: Int = 6

val b1 = i.toByte()
println(b1)

val b2: Byte = 1
println(b2)

val i4: Int = b2.toInt() // OK!
println(i4)
//1

val i5: String = b2.toString()
println(i5)
//1

val i6: Double = b2.toDouble()
println(i6)
//1.0

val oneMillion = 1_000_000
val socialSecurityNumber = 999_99_9999L
val hexBytes = 0xFF_EC_DE_5E
val bytes = 0b11010010_01101001_10010100_10010010

var lakes: Double = 2.5


val numberOfFish = 50
val numberOfPlants = 23
if (numberOfFish > numberOfPlants) {
    println("Good ratio!")
} else {
    println("Unhealthy ratio")
}
// Good ratio!

val fish = 50
if (fish in 1..100) {
    println(fish)
}
// 50

if (numberOfFish == 0) {
    println("Empty tank")
} else if (numberOfFish < 40) {
    println("Got fish!")
} else {
    println("That's a lot of fish!")
}
// That's a lot of fish!

when (numberOfFish) {
    0  -> println("Empty tank")
    in 1..39 -> println("Got fish!")
    else -> println("That's a lot of fish!")
}
// That's a lot of fish!

var marbles: Int? = null
//⇒ error: null can not be a value of a non-null type Int

var fishFoodTreats = 6
//fishFoodTreats = fishFoodTreats?.dec()

fishFoodTreats = fishFoodTreats?.dec() ?: 0

val len = s!!.length

val school = listOf("mackerel", "trout", "halibut")
//println(school) // [mackerel, trout, halibut]

val myList = mutableListOf("tuna", "salmon", "shark")
myList.remove("shark")

val school = arrayOf("shark", "salmon", "minnow")
println(java.util.Arrays.toString(school))
// [shark, salmon, minnow]

val mix = arrayOf("fish", 2)
val numbers = intArrayOf(1,2,3)
val numbers = intArrayOf(1,2,3)
val numbers3 = intArrayOf(4,5,6)
val foo2 = numbers3 + numbers
println(foo2[5])
// 3

val numbers = intArrayOf(1, 2, 3)
val oceans = listOf("Atlantic", "Pacific")
val oddList = listOf(numbers, oceans, "salmon")
println(oddList)
// [[I@89178b4, [Atlantic, Pacific], salmon]

val array = Array (5) { it * 2 }
println(java.util.Arrays.toString(array))
// [0, 2, 4, 6, 8]


val school = arrayOf("shark", "salmon", "minnow")
for (element in school) {
    print(element + " ")
}
// shark salmon minnow

for ((index, element) in school.withIndex()) {
    println("Item at $index is $element\n")
}
// Item at 0 is shark
// Item at 1 is salmon
// Item at 2 is minnow

for (i in 1..5) print(i)
//12345

for (i in 5 downTo 1) print(i)
//54321

for (i in 3..6 step 2) print(i)
//35

for (i in 'd'..'g') print (i)
//defg

var bubbles = 0
while (bubbles < 50) {
    bubbles++
}
println("$bubbles bubbles in the water\n")

do {
    bubbles--
} while (bubbles > 50)
println("$bubbles bubbles in the water\n")

repeat(2) {
    println("A fish is swimming")
}
// 50 bubbles in the water
// 49 bubbles in the water
// A fish is swimmingA fish is swimming
