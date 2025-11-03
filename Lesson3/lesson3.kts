//Exercise 1
package example.myapp

class Aquarium {
    var width: Int = 20
    var height: Int = 40
    var length: Int = 100
}

package example.myapp

fun buildAquarium() {
    val myAquarium = Aquarium()
}

fun main() {
    buildAquarium()
}

    fun printSize() {
        println("Width: $width cm " +
                "Length: $length cm " +
                "Height: $height cm ")
    }

fun buildAquarium() {
    val myAquarium = Aquarium()
    myAquarium.printSize()
}

//Width: 20 cm Length: 100 cm Height: 40 cm

fun buildAquarium() {
    val myAquarium = Aquarium()
    myAquarium.printSize()
    myAquarium.height = 60
    myAquarium.printSize()
}

//Width: 20 cm Length: 100 cm Height: 40 cm
//Width: 20 cm Length: 100 cm Height: 60 cm

class Aquarium(length: Int = 100, width: Int = 20, height: Int = 40) {
    // Dimensions in cm
    var length: Int = length
    var width: Int = width
    var height: Int = height
    ...
}

class Aquarium(var length: Int = 100, var width: Int = 20, var height: Int = 40) {
    ...
}

fun buildAquarium() {
    val aquarium1 = Aquarium()
    aquarium1.printSize()
    // default height and length
    val aquarium2 = Aquarium(width = 25)
    aquarium2.printSize()
    // default width
    val aquarium3 = Aquarium(height = 35, length = 110)
    aquarium3.printSize()
    // everything custom
    val aquarium4 = Aquarium(width = 25, height = 35, length = 110)
    aquarium4.printSize()
}

//Width: 20 cm Length: 100 cm Height: 40 cm
//Width: 25 cm Length: 100 cm Height: 40 cm
//Width: 20 cm Length: 110 cm Height: 35 cm
//Width: 25 cm Length: 110 cm Height: 35 cm

class Aquarium (var length: Int = 100, var width: Int = 20, var height: Int = 40) {
    init {
        println("aquarium initializing")
    }
    init {
        // 1 liter = 1000 cm^3
        println("Volume: ${width * length * height / 1000} liters")
    }
    ...
}

//aquarium initializing
//Volume: 80 liters
//Width: 20 cm Length: 100 cm Height: 40 cm
//aquarium initializing
//Volume: 100 liters
//Width: 25 cm Length: 100 cm Height: 40 cm
//aquarium initializing
//Volume: 77 liters
//Width: 20 cm Length: 110 cm Height: 35 cm
//aquarium initializing
//Volume: 96 liters
//Width: 25 cm Length: 110 cm Height: 35 cm

constructor(numberOfFish: Int) : this() {
    // 2,000 cm^3 per fish + extra room so water doesn't spill
    val tank = numberOfFish * 2000 * 1.1
    // calculate the height needed
    height = (tank / (length * width)).toInt()
}

fun buildAquarium() {
    val aquarium6 = Aquarium(numberOfFish = 29)
    aquarium6.printSize()
    println("Volume: ${aquarium6.width * aquarium6.length * aquarium6.height / 1000} liters")
}

//aquarium initializing
//Volume: 80 liters
//Width: 20 cm Length: 100 cm Height: 31 cm
//Volume: 62 liters

val volume: Int
    get() = width * height * length / 1000  // 1000 cm^3 = 1 liter

fun printSize() {
    println("Width: $width cm " +
            "Length: $length cm " +
            "Height: $height cm "
    )
    // 1 liter = 1000 cm^3
    println("Volume: $volume liters")
}

//aquarium initializing
//Width: 20 cm Length: 100 cm Height: 31 cm
//Volume: 62 liters

var volume: Int
    get() = width * height * length / 1000
    set(value) {
        height = (value * 1000) / (width * length)
    }

fun buildAquarium() {
    val aquarium6 = Aquarium(numberOfFish = 29)
    aquarium6.printSize()
    aquarium6.volume = 70
    aquarium6.printSize()
}

//aquarium initialized
//Width: 20 cm Length: 100 cm Height: 31 cm
//Volume: 62 liters
//Width: 20 cm Length: 100 cm Height: 35 cm
//Volume: 70 liters

var volume: Int
    get() = width * height * length / 1000
    private set(value) {
        height = (value * 1000) / (width * length)
    }

open class Aquarium (open var length: Int = 100, open var width: Int = 20, open var height: Int = 40) {
    open var volume: Int
        get() = width * height * length / 1000
        set(value) {
            height = (value * 1000) / (width * length)
        }
    open val shape = "rectangle"
    open var water: Double = 0.0
        get() = volume * 0.9

fun printSize() {
    println(shape)
    println("Width: $width cm " +
            "Length: $length cm " +
            "Height: $height cm ")
    // 1 l = 1000 cm^3
    println("Volume: $volume liters Water: $water liters (${water / volume * 100.0}% full)")
}

fun buildAquarium() {
    val aquarium6 = Aquarium(length = 25, width = 25, height = 40)
    aquarium6.printSize()
}

//aquarium initializing
//rectangle
//Width: 25 cm Length: 25 cm Height: 40 cm
//Volume: 25 liters Water: 22.5 liters (90.0% full)

package example.myapp

import java.lang.Math.PI

... // existing Aquarium class

class TowerTank (override var height: Int, var diameter: Int): Aquarium(height = height, width = diameter, length = diameter) {
    override var volume: Int
        // ellipse area = π * r1 * r2
        get() = (width/2 * length/2 * height / 1000 * PI).toInt()
        set(value) {
            height = ((value * 1000 / PI) / (width/2 * length/2)).toInt()
        }

    override var water = volume * 0.8
    override val shape = "cylinder"
}

package example.myapp

fun buildAquarium() {
    val myAquarium = Aquarium(width = 25, length = 25, height = 40)
    myAquarium.printSize()
    val myTower = TowerTank(diameter = 25, height = 40)
    myTower.printSize()
}

//aquarium initializing
//rectangle
//Width: 25 cm Length: 25 cm Height: 40 cm
//Volume: 25 liters Water: 22.5 liters (90.0% full)
//aquarium initializing
//cylinder
//Width: 25 cm Length: 25 cm Height: 40 cm
//Volume: 18 liters Water: 14.4 l (80.0% full)

package example.myapp

abstract class AquariumFish {
    abstract val color: String
}

class Shark: AquariumFish() {
    override val color = "grey"
}


class Plecostomus: AquariumFish() {
    override val color = "gold"
}

package example.myapp

fun makeFish() {
    val shark = Shark()
    val pleco = Plecostomus()

    println("Shark: ${shark.color}")
    println("Plecostomus: ${pleco.color}")
}

fun main () {
    makeFish()
}

//Shark: grey
//Plecostomus: gold

interface FishAction  {
    fun eat()
}

class Shark: AquariumFish(), FishAction {
    override val color = "grey"
    override fun eat() {
        println("hunt and eat fish")
    }
}

class Plecostomus: AquariumFish(), FishAction {
    override val color = "gold"
    override fun eat() {
        println("eat algae")
    }
}

fun makeFish() {
    val shark = Shark()
    val pleco = Plecostomus()
    println("Shark: ${shark.color}")
    shark.eat()
    println("Plecostomus: ${pleco.color}")
    pleco.eat()
}

//Shark: grey
//hunt and eat fish
//Plecostomus: gold
//eat algae

package example.myapp

interface FishAction {
    fun eat()
}

interface FishColor {
    val color: String
}

class Plecostomus: FishAction, FishColor {
    override val color = "gold"
    override fun eat() {
        println("eat algae")
    }
}

class Shark: FishAction, FishColor {
    override val color = "grey"
    override fun eat() {
        println("hunt and eat fish")
    }
}

object GoldColor : FishColor {
    override val color = "gold"
}

class Plecostomus:  FishAction, FishColor by GoldColor {
    override fun eat() {
        println("eat algae")
    }
}

class Plecostomus(fishColor: FishColor = GoldColor):  FishAction,
    FishColor by fishColor {
    override fun eat() {
        println("eat algae")
    }
}

class PrintingFishAction(val food: String) : FishAction {
    override fun eat() {
        println(food)
    }
}

class Plecostomus (fishColor: FishColor = GoldColor):
    FishAction by PrintingFishAction("eat algae"),
    FishColor by fishColor

package example.myapp.decor

class Decoration {
}

data class Decoration(val rocks: String) {
}

fun makeDecorations() {
    val decoration1 = Decoration("granite")
    println(decoration1)
}

Decoration(rocks=granite)

fun makeDecorations() {
    val decoration1 = Decoration("granite")
    println(decoration1)

    val decoration2 = Decoration("slate")
    println(decoration2)

    val decoration3 = Decoration("slate")
    println(decoration3)
    println (decoration1.equals(decoration2))
    println (decoration3.equals(decoration2))
}

//Decoration(rocks=granite)
//Decoration(rocks=slate)
//Decoration(rocks=slate)
//false
//true

val rock = decoration.rock
val wood = decoration.wood
val diver = decoration.diver

val (rock, wood, diver) = decoration

// Here is a data class with 3 properties.
data class Decoration2(val rocks: String, val wood: String, val diver: String){
}

fun makeDecorations() {
    val d5 = Decoration2("crystal", "wood", "diver")
    println(d5)

// Assign all properties to variables.
    val (rock, wood, diver) = d5
    println(rock)
    println(wood)
    println(diver)
}

//Decoration2(rocks=crystal, wood=wood, diver=diver)
//crystal
//wood
//diver

object GoldColor : FishColor {
    override val color = "gold"
}

enum class Color(val rgb: Int) {
    RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF);
}

enum class Direction(val degrees: Int) {
    NORTH(0), SOUTH(180), EAST(90), WEST(270)
}

fun main() {
    println(Direction.EAST.name)
    println(Direction.EAST.ordinal)
    println(Direction.EAST.degrees)
}

//EAST
//2
//90

class Choice {
    companion object {
        var name: String = "lyric"
        fun showDescription(name:String) = println("My favorite $name")
    }
}

fun main() {
    println(Choice.name)
    Choice.showDescription("pick")
    Choice.showDescription("selection")
}

//lyric
//My favorite pick
//My favorite selection

val twoLists = fish.partition { isFreshWater(it) }
println("freshwater: ${twoLists.first}")
println("saltwater: ${twoLists.second}")

val equipment = "fish net" to "catching fish"
println("${equipment.first} used for ${equipment.second}")

//    fish net used for catching fish

val numbers = Triple(6, 9, 42)
println(numbers.toString())
println(numbers.toList())

//    (6, 9, 42)
//    [6, 9, 42]

val equipment2 = ("fish net" to "catching fish") to "equipment"
println("${equipment2.first} is ${equipment2.second}\n")
println("${equipment2.first.second}")

//    (fish net, catching fish) is equipment
//    catching fish

val equipment = "fish net" to "catching fish"
val (tool, use) = equipment
println("$tool is used for $use")

//    fish net is used for catching fish

val numbers = Triple(6, 9, 42)
val (n1, n2, n3) = numbers
println("$n1 $n2 $n3")

//    6 9 42

val list = listOf(1, 5, 3, 4)
println(list.sum())

//13

val list = listOf(1, 5, 3, 4)
println(list.sum())

//    error: none of the following functions can be called with the arguments supplied:

val list2 = listOf("a", "bbb", "cc")
println(list2.sumBy { it.length })
//6

val list2 = listOf("a", "bbb", "cc")
for (s in list2.listIterator()) {
    println("$s ")
}

//a bbb cc

val scientific = hashMapOf("guppy" to "poecilia reticulata", "catfish" to "corydoras", "zebra fish" to "danio rerio" )
println (scientific.get("guppy"))
//poecilia reticulata

println(scientific.get("zebra fish"))
//danio rerio

println("scientific.get("swordtail"")
//null

println(scientific.getOrDefault("swordtail", "sorry, I don't know"))
//sorry, I don't know

println(scientific.getOrElse("swordtail") {"sorry, I don't know"})
//sorry, I don't know

const val rocks = 3
val value1 = complexFunctionCall() // OK
const val CONSTANT1 = complexFunctionCall() // NOT ok

object Constants {
    const val CONSTANT2 = "object constant"
}
val foo = Constants.CONSTANT2

class MyClass {
    companion object {
        const val CONSTANT3 = "constant in companion"
    }
}

fun String.hasSpaces(): Boolean {
    val found = this.indexOf(' ')
    // also valid: this.indexOf(" ")
    // returns positive number index in String or -1 if not found
    return found != -1
}

fun String.hasSpaces() = indexOf(" ") != -1

class AquariumPlant(val color: String, private val size: Int)

fun AquariumPlant.isRed() = color == "red"    // OK
fun AquariumPlant.isBig() = size > 50         // gives error

//    error: cannot access 'size': it is private in 'AquariumPlant'

open class AquariumPlant(val color: String, private val size: Int)

class GreenLeafyPlant(size: Int) : AquariumPlant("green", size)

fun AquariumPlant.print() = println("AquariumPlant")
fun GreenLeafyPlant.print() = println("GreenLeafyPlant")

val plant = GreenLeafyPlant(size = 10)
plant.print()
println("\n")
val aquariumPlant: AquariumPlant = plant
aquariumPlant.print()  // what will it print?

//    GreenLeafyPlant
//    AquariumPlant

val AquariumPlant.isGreen: Boolean
    get() = color == "green"

aquariumPlant.isGreen

//    res4: kotlin.Boolean = true

fun AquariumPlant?.pull() {
    this?.apply {
        println("removing $this")
    }
}

val plant: AquariumPlant? = null
plant.pull()

