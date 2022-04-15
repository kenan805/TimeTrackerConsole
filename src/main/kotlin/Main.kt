import consoleUI.MainMenu
import java.time.Duration
import java.time.LocalTime
import java.util.*
import kotlin.concurrent.fixedRateTimer
import kotlin.concurrent.schedule
import kotlin.concurrent.scheduleAtFixedRate
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Local

fun main(args: Array<String>) {
    try {
        MainMenu.main()

    } catch (ex: Exception) {
        println("Error: ${ex.message}")
    }


}



