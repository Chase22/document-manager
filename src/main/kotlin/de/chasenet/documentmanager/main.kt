package de.chasenet.documentmanager

import de.chasenet.documentmanager.data.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate

fun main() {
    Database.connect("jdbc:h2:./test", driver = "org.h2.Driver")

    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(Documents, Tags, DocumentTags)
    }

    val document = transaction {
        Document.new {
            name = "testdocument"
            path = "new"
            date = LocalDate.now()
        }
    }

    val tag = transaction {
        Tag.new {
            tag = "TestTag"
        }
    }

    transaction {
        document.tags = SizedCollection(listOf(tag))
    }
    transaction {
        println(Document.findById(document.id)?.tags?.map { it.tag })
    }
}