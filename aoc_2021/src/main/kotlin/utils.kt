import java.io.File

internal fun readInputFile(fileName: String) = File("src/main/resources", fileName).readLines()
