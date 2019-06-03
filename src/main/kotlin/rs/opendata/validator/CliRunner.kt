package rs.opendata.validator

import jodd.io.FileUtil
import jodd.json.JsonParser
import jodd.json.JsonSerializer
import jodd.json.TypeJsonParserMap
import jodd.log.LoggerFactory
import jodd.log.impl.SimpleLogger
import rs.opendata.validator.model.*
import rs.opendata.validator.service.ValidatorJob
import kotlin.system.measureTimeMillis


fun main(args: Array<String>) {

    LoggerFactory.setLoggerProvider(SimpleLogger.PROVIDER)

    if (args.size < 2) {
        println("Invalid argument: opendata-validator [input-db] [output-scores]")
        return
    }

    TypeJsonParserMap.get().register(Format::class.java, FormatAdapter())
    TypeJsonParserMap.get().register(License::class.java, LicenseAdapter())

    val databaseJsonFileName = args[0]
    val databaseJsonContent = FileUtil.readString(databaseJsonFileName)

    // parse
    val database = JsonParser.create()
            .parse(databaseJsonContent, Database::class.java)

    // validate
    val milliseconds = measureTimeMillis {
        val scores = ValidatorJob().validate(database)

        // to json
        val scoresJson =
                JsonSerializer.create()
                        .deep(true)
                        .serialize(scores)

        //println(scoresJson)
        FileUtil.writeString(args[1], scoresJson)
    }

    val minutes = milliseconds / 1000 / 60
    val seconds = milliseconds / 1000 % 60

    println("Done in $minutes minutes and $seconds seconds.")
}
